package com.mkst.mini.systemplus.util;

import cn.hutool.extra.qrcode.BufferedImageLuminanceSource;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class QRCode {
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 生成QRCode二维码<br>
	 * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
	 * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
	 * 修改为UTF-8，否则中文编译后解析不了<br>
	 */
	public static void encode(String contents, String filePath) {
		HashMap<EncodeHintType, Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 字符集，包含中文的话就要utf-8
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 纠错等级，等级越高图片越不清晰
		hints.put(EncodeHintType.MARGIN, 0); // 边距

		encode(contents, filePath, BarcodeFormat.QR_CODE, 300, 300, hints);
	}

	/**
	 * 生成QRCode二维码<br>
	 * 在编码时需要将com.google.zxing.qrcode.encoder.Encoder.java中的<br>
	 * static final String DEFAULT_BYTE_MODE_ENCODING = "ISO8859-1";<br>
	 * 修改为UTF-8，否则中文编译后解析不了<br>
	 */
	public static void encode(String contents, String filePath, BarcodeFormat format, int width, int height,
			Map<EncodeHintType, ?> hints) {
		try {
			// 消除乱码
			contents = new String(contents.getBytes("UTF-8"), "ISO-8859-1");
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, format, width, height);
			// 写入到本地
			File file = new File(filePath);
			File fileParent = file.getParentFile();
			if (!fileParent.exists()) {
				fileParent.mkdirs();
			}
			writeToFile(bitMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成二维码图片<br>
	 * 
	 * @param matrix
	 * @param format 图片格式
	 * @param file   生成二维码图片位置
	 * @throws IOException
	 */
	public static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		ImageIO.write(image, format, file);
	}

	/**
	 * 生成二维码内容<br>
	 * 
	 * @param matrix
	 * @return
	 */
	public static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) == true ? BLACK : WHITE);
			}
		}
		return image;
	}

	/**
	 * 解析QRCode二维码
	 */
	@SuppressWarnings("unchecked")
	public static String decode(File file) throws Exception {
		BufferedImage image = ImageIO.read(file);
		if (image == null) {
			throw new NullPointerException("Could not decode image");
		}
		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Hashtable hints = new Hashtable();
		// 解码设置编码方式为：utf-8
		hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
		Result result = new MultiFormatReader().decode(bitmap, hints);
		return result.getText();
	}

	/**
	 * 解析二维码
	 *
	 * @param path 二维码图片地址
	 * @return 不是二维码的内容返回null,是二维码直接返回识别的结果
	 * @throws Exception
	 */
	public static String decode(String path) throws Exception {
		return decode(new File(path));
	}
}
