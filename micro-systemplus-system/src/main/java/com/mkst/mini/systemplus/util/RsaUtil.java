package com.mkst.mini.systemplus.util;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.Provider;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.mkst.mini.systemplus.common.utils.StringUtils;

public class RsaUtil {
	public static String decode(String password) {
		if (password==null){
			password = "";
		}
		String hexModulus = SysConfigUtil.getKey("modulus");
		String hexPrivateExponent = SysConfigUtil.getKey("private");
		// 这就是上面html输出的密文
		String encryptPassword = password;
		Provider provider = new BouncyCastleProvider();

		
		byte[] rawPasswordByte;
		try {
			KeyFactory keyFac = KeyFactory.getInstance("RSA", provider);
			RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(hexModulus, 16), new BigInteger(hexPrivateExponent, 16));
			// 生成用于解密的私钥
			RSAPrivateKey pk = (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
			// 解密
			Cipher cipher = Cipher.getInstance("RSA", provider);
			cipher.init(2, pk);
			rawPasswordByte = cipher.doFinal(Hex.decodeHex(encryptPassword.toCharArray()));
			password=StringUtils.reverse(new String(rawPasswordByte));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return password;
		
		
	}
}
