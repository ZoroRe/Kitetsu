package me.zoro.kitetsu.spring.context;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.algorithm.data.structure.Queue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author luguanquan
 * @date 2020-03-12 19:17
 *
 * 通过实现接口 ApplicationContextAware 获取 ApplicationContext 的类必须加上 @Component，否则无效
 * 同时要注意必须等到 spring 初始化完成才设置进来，比如你在某个 service 的 PostConstruct 就试图获取这个 context,则很可能获取不到
 */
@Slf4j
@Component
public class SpringContextUtil implements ApplicationContextAware {

	private static ApplicationContext context;

	/**
	 * Spring初始化时，会通过该方法将ApplicationContext对象注入。
	 *
	 * @param applicationContext
	 * @throws BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if(applicationContext != null){
			context = applicationContext;
		}
	}

	public static <T> T getBean(Class<T> clazz) throws BeansException {
		if (context != null) {
			return context.getBean(clazz);
		}
		return null;
	}

}
