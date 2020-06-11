package com.mkst.mini.systemplus.common.aspectj;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.mkst.mini.systemplus.common.annotation.DataSource;
import com.mkst.mini.systemplus.common.utils.StringUtils;
import com.mkst.mini.systemplus.framework.datasource.DynamicDataSourceContextHolder;

/**
 * 多数据源处理
 * 
 * @author admin
 */
@Aspect
@Order(1)
@Component
public class DataSourceAspect
{
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("@annotation(com.mkst.mini.systemplus.common.annotation.DataSource)")
    public void dsPointCut()
    {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        MethodSignature signature = (MethodSignature) point.getSignature();

//        Method method = signature.getMethod();
//        
//        Parameter[] mParameters = method.getParameters();
//        System.out.println(point.getArgs());
//         System.out.println(mParameters[0]);
//        DataSource dataSource = method.getAnnotation(DataSource.class);
        Object[] dsourceIds = point.getArgs();
        Object dataSource = dsourceIds.length>0?dsourceIds[0]:null;
        if (StringUtils.isNotNull(dataSource))
        {
            DynamicDataSourceContextHolder.setDateSoureType(String.valueOf(dataSource));
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDateSoureType();
        }
    }
}
