package com.mkst.mini.systemplus.mybatis.interceptor;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Properties;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
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
 *  Mybatis 返回值加解密拦截器
 *  Created by gin on 2019-08-23.
 * </pre>
 */
@Intercepts({ @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class }) })
@ConditionalOnProperty(value = "mybatis.decrypt", havingValue = "true")
@Component
public class ResultInterceptor implements Interceptor {

	@Autowired
	private IEncryptDecrypt encryptDecrypt;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object result = invocation.proceed();
		if (Objects.isNull(result)) {
			return null;
		}

		if (result instanceof ArrayList<?>) {
			ArrayList<?> resultList = (ArrayList<?>) result;
			if (CollectionUtils.isNotEmpty(resultList) && needToDecrypt(resultList.get(0))) {
				for (int i = 0; i < resultList.size(); i++) {
					encryptDecrypt.decrypt(resultList.get(i));
				}
			}
		} else {
			if (needToDecrypt(result)) {
				encryptDecrypt.decrypt(result);
			}
		}
		return result;
	}

	public boolean needToDecrypt(Object object) {
		Class<?> objectClass = object.getClass();
		EncryptDecryptClass encryptDecryptClass = AnnotationUtils.findAnnotation(objectClass,
				EncryptDecryptClass.class);
		if (Objects.nonNull(encryptDecryptClass)) {
			return true;
		}
		return false;
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}
}
