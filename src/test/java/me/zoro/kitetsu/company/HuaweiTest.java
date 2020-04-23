package me.zoro.kitetsu.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-04-08 20:13
 */

public class HuaweiTest {

	@DisplayName("测试")
	@Test
	public void maxNoRepeatSubstringTest() {
		String s = "abcabc";
		Assertions.assertEquals("abc", Huawei.maxNoRepeatSubstring(s));
		s = "abcabcde";
		assertEquals("abcde", Huawei.maxNoRepeatSubstring(s));
		s = "aaaa";
		assertEquals("a", Huawei.maxNoRepeatSubstring(s));
		s = "abababcabab";
		assertEquals("abc", Huawei.maxNoRepeatSubstring(s));
		s =
				"aaaaabcdedfsajdkfjaskdfjksjfksjfkdjfksjdkfjaskdjfkasjfksjdkfjkdjfksadjfksjdkfjaskdfjslkjksjkjkjkfjskdfjksdjfkdsjjjsdfkjskdfjskdfjd";
		assertEquals("abcde", Huawei.maxNoRepeatSubstring(s));

	}
}
