package me.zoro.kitetsu.boxed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author luguanquan
 * @date 2020-03-20 22:47
 */
@DisplayName("自动装箱拆箱/String特殊场景 验证")
public class BoxedTest {

	@DisplayName("验证 String a = \"abc\" 在常量池引用同一个对象")
	@Test
	public void testString1() {
		String a = "abc";
		String b = "abc";
		assertTrue(a == b);
	}

	@DisplayName("验证 String a = new String(\"abc\") 在堆上创建")
	@Test
	public void testString2() {
		String a = new String("abc");
		String b = new String("abc");
		assertFalse(a == b);
	}

	@DisplayName("验证 Integer 在 -128 - 127 之间使用缓存，之外在堆创建")
	@Test
	public void testInteger() {
		Integer a1 = -128;
		Integer a2 = -128;
		assertTrue(a1 == a2);

		Integer b1 = 127;
		Integer b2 = 127;
		assertTrue(b1 == b2);

		Integer c1 = -129;
		Integer c2 = -129;
		assertFalse(c1 == c2);

		Integer d1 = 128;
		Integer d2 = 128;
		assertFalse(d1 == d2);
	}

	@DisplayName("验证 Integer 自动拆箱不会有方法值引用传入")
	@Test
	public void testIntegerBoxed() {
		Integer value = new Integer(1);
		BoxedUtils.addOne(value);
		assertEquals(1, value);
	}

	@DisplayName("验证 AtomicInteger 参数传入是指引用")
	@Test
	public void testABoxed() {
		AtomicInteger value = new AtomicInteger(1);
		BoxedUtils.addOne(value);
		assertEquals(2, value.get());
	}


}
