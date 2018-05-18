package com.csit.javaweb_02.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解
 * @Retention
 * @Target
 * @Document 是否体现在java doc
 * @Inherited 是否能够被子类继承
 * @author Administrator
 *2018年5月4日下午4:28:02
 */
@Retention(RetentionPolicy.RUNTIME)//注解的保留策略RetentionPolicy.SOURCE仅在源码中，RetentionPolicy.CLASS在字节码中,RetentionPolicy.RUNTIME在字节码中，而且可以反射获得
@Target({ElementType.TYPE})
public @interface Service {

}
