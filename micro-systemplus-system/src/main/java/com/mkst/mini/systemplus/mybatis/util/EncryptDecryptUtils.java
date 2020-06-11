package com.mkst.mini.systemplus.mybatis.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

import com.mkst.mini.systemplus.common.config.Global;
import com.mkst.mini.systemplus.mybatis.annotation.EncryptDecryptField;
import com.mkst.mini.systemplus.util.Sm4Util;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *  Bean数据加密工具类
 *  Created by gin on 2019-08-23.
 * </pre>
 */
@Slf4j
public class EncryptDecryptUtils {

	/**
	 * 多field加密方法
	 * 
	 * @param declaredFields
	 * @param parameterObject
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> T encrypt(Field[] declaredFields, T parameterObject) throws IllegalAccessException {
		for (Field field : declaredFields) {
			EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
			if (Objects.isNull(annotation)) {
				continue;
			}
			encrypt(field, parameterObject);
		}
		return parameterObject;
	}

	/**
	 * 单个field加密方法
	 * 
	 * @param field
	 * @param parameterObject
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> T encrypt(Field field, T parameterObject) throws IllegalAccessException {
		field.setAccessible(true);
		Object object = field.get(parameterObject);
		if (object instanceof BigDecimal) {
			// TODO 定制BigDecimal类型的加密算法
		} else if (object instanceof Integer) {
			// TODO 定制Integer类型的加密算法
		} else if (object instanceof Long) {
			// TODO 定制Long类型的加密算法
		} else if (object instanceof String) {
			String value = (String) object;
			if(StringUtils.isNotBlank(value)) {
				// String 加密算法
				String sm4Key = Global.getConfig("mybatis.secretkey");
				try {
					field.set(parameterObject, Sm4Util.encryptEcb(sm4Key, value));
				} catch (Exception e) {
					log.error("数据加密错误：value={}", value, e);
				}
			}
		}
		field.setAccessible(false);
		return parameterObject;
	}

	/**
	 * 解密方法
	 * 
	 * @param result
	 * @param <T>
	 * @return
	 * @throws IllegalAccessException
	 */
	public static <T> T decrypt(T result) throws IllegalAccessException {
		Class<?> parameterObjectClass = result.getClass();
		Field[] declaredFields = parameterObjectClass.getDeclaredFields();
		decrypt(declaredFields, result);
		return result;
	}

	/**
	 * 多个field解密方法
	 * 
	 * @param declaredFields
	 * @param result
	 * @throws IllegalAccessException
	 */
	public static void decrypt(Field[] declaredFields, Object result) throws IllegalAccessException {
		for (Field field : declaredFields) {
			EncryptDecryptField annotation = field.getAnnotation(EncryptDecryptField.class);
			if (Objects.isNull(annotation)) {
				continue;
			}
			decrypt(field, result);
		}
	}

	/**
	 * 单个field解密方法
	 * 
	 * @param field
	 * @param result
	 * @throws IllegalAccessException 
	 */
	public static void decrypt(Field field, Object result) throws IllegalAccessException {
		field.setAccessible(true);
		Object object = field.get(result);
		if (object instanceof BigDecimal) {
			// TODO 定制BigDecimal类型的解密算法
		} else if (object instanceof Integer) {
			// TODO 定制Integer类型的解密算法
		} else if (object instanceof Long) {
			// TODO 定制Long类型的解密算法
		} else if (object instanceof String) {
			// String类型的解密算法
			String value = (String) object;
			if(StringUtils.isNotBlank(value)) {
				String sm4Key = Global.getConfig("mybatis.secretkey");
				try {
					field.set(result, Sm4Util.decryptEcb(sm4Key, value));
				} catch (Exception e) {
//					log.error("数据解密错误：value={}", value, e);
				}
			}

		}
	}

	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
	}
}
