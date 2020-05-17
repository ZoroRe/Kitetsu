package me.zoro.kitetsu.proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author luguanquan
 * @date 2020/5/17 3:40 下午
 */
@Slf4j
public class CglibProxyNotInvokeDefault implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		log.info("这里是拦截器 MethodInterceptor: cglib proxy invoke");
//		原类的方法的调用方式，注释后不会调用
//		Object result = methodProxy.invokeSuper(o, objects);
		return null;
	}
}
