package com.csit.javaweb_02.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ע��
 * @Retention
 * @Target
 * @Document �Ƿ�������java doc
 * @Inherited �Ƿ��ܹ�������̳�
 * @author Administrator
 *2018��5��4������4:28:02
 */
@Retention(RetentionPolicy.RUNTIME)//ע��ı�������RetentionPolicy.SOURCE����Դ���У�RetentionPolicy.CLASS���ֽ�����,RetentionPolicy.RUNTIME���ֽ����У����ҿ��Է�����
@Target({ElementType.TYPE})
public @interface Service {

}
