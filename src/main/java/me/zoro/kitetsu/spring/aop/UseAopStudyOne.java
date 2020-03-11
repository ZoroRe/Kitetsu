package me.zoro.kitetsu.spring.aop;

import org.springframework.stereotype.Component;

/**
 * @author luguanquan
 * @date 2020-03-11 23:04
 */
@Component
public class UseAopStudyOne {

	@AopStudy("World")
	public String hello() {
		System.out.println("hello by others");
		return "hello";
	}
}
