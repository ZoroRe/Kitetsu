package me.zoro.kitetsu.spring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author luguanquan
 * @date 2020-03-11 19:50
 */
@Service
public class UseAopStudyTwoService {

	@Autowired
	UseAopStudyOne useAopStudyOne;

	@PostConstruct
	public void test(){
		// 调用这个DI的对象就会触发切面相关代码
		useAopStudyOne.hello();
		// 调用这个自身对象的方法没有切面代码
		hello();
	}

	@AopStudy("")
	public String hello(){
		System.out.println("hello by self");
		return "hellp";
	}

}
