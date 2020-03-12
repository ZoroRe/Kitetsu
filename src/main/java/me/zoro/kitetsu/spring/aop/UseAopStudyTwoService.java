package me.zoro.kitetsu.spring.aop;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.spring.context.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author luguanquan
 * @date 2020-03-11 19:50
 */
@Service
@Slf4j
public class UseAopStudyTwoService {

	@Autowired
	UseAopStudyOne useAopStudyOne;

	@Scheduled(fixedDelay = 20)
	public void test() {
		// 调用这个DI的对象就会触发切面相关代码
		useAopStudyOne.hello();
		// 调用这个自身对象的方法没有切面代码
		hello();
		// 如果需要调用自身的方法，同时执行切面方法，需要获取真正的代理类 查看一些网上自定义类,一些参考：https://www.ktanx.com/blog/p/326
		// 但必须等 spring 初始完成后，所以如果这个方法是在 PostConstruct 执行就不会触发,因为 context 还是空的
		UseAopStudyTwoService two = SpringContextUtil.getBean(UseAopStudyTwoService.class);
		if (two != null) {
			two.hello();
		}else{
			log.error("bean is null : UseAopStudyTwoService");
		}

	}

	@AopStudy("")
	public String hello() {
		System.out.println("hello by self");
		return "hellp";
	}

}
