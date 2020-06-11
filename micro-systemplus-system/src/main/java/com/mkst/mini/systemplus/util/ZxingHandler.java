package com.mkst.mini.systemplus.util;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 条形码和二维码编码解码
 * 
 * @author ThinkGem
 * @version 2014-02-28
 */
public class ZxingHandler {
	
	private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;


	/**
	 * 二维码编码
	 * 
	 * @param contents
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public static String encode2(String contents, int width, int height, String imgPath) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		// 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		// 指定编码格式
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
					BarcodeFormat.QR_CODE, width, height, hints);

		    BufferedImage image = toBufferedImage(bitMatrix);
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        if (!ImageIO.write(image, "jpg", baos)) {
	            throw new IOException("Could not write an image of format jpg");
	        }
	        return "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

   
}