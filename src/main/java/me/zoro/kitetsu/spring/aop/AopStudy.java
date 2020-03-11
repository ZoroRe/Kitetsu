package me.zoro.kitetsu.spring.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author luguanquan
 * @date 2020-03-11 18:32
 */
// 说明这个注解用于方法上
@Target(ElementType.METHOD)
// 说明方法在运行时保留，这样才能运行时获取到注解以及相关信息
@Retention(RetentionPolicy.RUNTIME)
public @interface AopStudy {
	/**
	 * 这个注解可以设置的参数
	 * @return
	 */
	String value();
}
