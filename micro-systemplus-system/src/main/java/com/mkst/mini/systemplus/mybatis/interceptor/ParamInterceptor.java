package com.mkst.mini.systemplus.mybatis.interceptor;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.mkst.mini.systemplus.mybatis.annotation.EncryptDecryptClass;
import com.mkst.mini.systemplus.mybatis.crypt.IEncryptDecrypt;

/**
 * <pre>
 *  Mybatis 参数拦截器
 *  Created by gin on 2019-08-23.
 * </pre>
 */
@Intercepts({ @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class) })
@ConditionalOnProperty(value = "mybatis.encrypt", havingValue = "true")
@Component
public class ParamInterceptor implements Interceptor {

	@Autowired
	private IEncryptDecrypt encryptDecrypt;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {

		// 拦截 ParameterHandler 的 setParameters 方法 动态设置参数
		if (invocation.getTarget() instanceof ParameterHandler) {
			ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();

			Field sqlField = parameterHandler.getClass().getDeclaredField("boundSql");
			sqlField.setAccessible(true);
			sqlField.get(parameterHandler);
			Object sqlObject = sqlField.get(parameterHandler);
			if (Objects.nonNull(sqlObject)) {
				Class<?> parameterObjectClass = sqlObject.getClass();
				Field[] declaredFields = parameterObjectClass.getDeclaredFields();
				for (Field field : declaredFields) {
					if("sql".equals(field.getName())) {
						field.setAccessible(true);
						String boundSql = (String) field.get(sqlObject);
						System.out.println(boundSql);
						// 解决insert时获取uuid，参数加密两次的问题
						// select replace(uuid(),'-','') from dual
						if(StringUtils.isNotBlank(boundSql) && boundSql.replaceAll(" ", "").toLowerCase().startsWith("selectreplace")) {
							return invocation.proceed();
						}
					}
				}
			}
			// 反射获取 参数对像
			Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
			parameterField.setAccessible(true);
			Object parameterObject = parameterField.get(parameterHandler);
			if (Objects.nonNull(parameterObject)) {
				Class<?> parameterObjectClass = parameterObject.getClass();
				EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(parameterObjectClass,
						EncryptDecryptClass.class);
				if (Objects.nonNull(encryptDecryptClass)) {
					Field[] declaredFields = parameterObjectClass.getDeclaredFields();

					encryptDecrypt.encrypt(declaredFields, parameterObject);
				}
			}
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}