package me.zoro.kitetsu.spring.aop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author luguanquan
 * @date 2020-03-11 19:45
 * <p>
 * 注意，这里不是严格的单元测试，只是方便调用然后看执行输出，了解 aop 切面编程的知识
 */
@DisplayName("切面编程测试")
public class AopStudyTest {


	@Test
	@DisplayName("调用使用了自定义切面的切点方法")
	public void aopStudyRun() {
		TestAop aop = new TestAop();
		aop.hello();
	}

	public static class TestAop {

		@AopStudy("World")
		public String hello() {
			return "hello";
		}
	}
}
