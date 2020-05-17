package me.zoro.kitetsu.proxy.jdk;

import com.sun.javafx.binding.StringFormatter;
import netscape.security.UserTarget;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author luguanquan
 * @date 2020/5/17 1:19 下午
 * <p>
 * 类似 Retrofit 动态代理中，定义的接口是任意的，传入接口直接得到动态代理实现类
 */

public class AnyInterfaceProxy {

	public static <T> T create(Class<T> service) {
		return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new AnyInvocation());
	}

	public static class AnyInvocation implements InvocationHandler {
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// do common things and return the result
			StringBuilder argsStr = new StringBuilder();
			if (args != null && args.length > 0) {
				for (Object arg : args) {
					argsStr.append(arg.toString() + ",");
				}
				argsStr.deleteCharAt(argsStr.length() - 1);
			}
			String result = String.format("Proxy Result: method=%s,args=[%s]", method.getName(),
					argsStr.toString());
			return result;
		}
	}
}
