package com.mkst.mini.systemplus.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.mkst.mini.systemplus.common.enums.DataSourceType;

/**
 * 自定义多数据源切换注解
 * 
 * @author admin
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource
{
    /**
     * 切换数据源名称
     */
    public String value() default "MASTER";
}
