package me.zoro.kitetsu.algorithm.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.algorithm.leetcode.LeetCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author luguanquan
 * @date 2020-03-21 23:21
 */
@DisplayName("LeetCode 算法")
@Slf4j
public class LeetCodeTest {

	@DisplayName("LeetCode 43")
	@Test
	public void test43() {
//		assertEquals("6", LeetCode.q43_multiply("2", "3"));
		assertEquals("56088", LeetCode.q43_multiply("123", "456"));
		int c = '9';
		log.info("xxxxxxxxx {}", c);
	}

}
