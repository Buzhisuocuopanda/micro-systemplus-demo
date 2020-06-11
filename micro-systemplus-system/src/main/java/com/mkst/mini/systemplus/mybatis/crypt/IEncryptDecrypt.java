package com.mkst.mini.systemplus.mybatis.crypt;

import java.lang.reflect.Field;

/**
 * <pre>
 *  加解密接口, 可实现自定义方法定义加密解密规则
 *  Created by gin on 2019-08-23.
 * </pre>
 */
public interface IEncryptDecrypt {

	/**
	 * 加密方法
	 * @param declaredFields 反射bean成员变量
	 * @param parameterObject Mybatis入参
	 * @param <T>
	 * @return
	 */
	public <T> T encrypt(Field[] declaredFields, T parameterObject) throws Exception;


	/**
	 * 解密方法
	 * @param result Mybatis 返回值，需要判断是否是ArrayList类型
	 * @param <T>
	 * @return
	 */
	public <T> T decrypt(T result) throws Exception;

}