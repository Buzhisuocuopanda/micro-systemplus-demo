package com.mkst.mini.systemplus.mybatis.crypt;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import com.mkst.mini.systemplus.mybatis.util.EncryptDecryptUtils;

/**
 * <pre>
 *  加解密接口实现
 *  Created by gin on 2019-08-23.
 * </pre>
 */
@Component
public class EncryptDecryptImpl implements IEncryptDecrypt {
	@Override
	public <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {
		return EncryptDecryptUtils.encrypt(declaredFields, parameterObject);
	}

	@Override
	public <T> T decrypt(T result) throws IllegalAccessException {
		return EncryptDecryptUtils.decrypt(result);
	}
}
