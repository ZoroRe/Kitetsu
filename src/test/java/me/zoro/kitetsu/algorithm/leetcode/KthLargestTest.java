package me.zoro.kitetsu.algorithm.leetcode;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-04-15 21:41
 */
@DisplayName("Leetcode703测试")
public class KthLargestTest {

	@DisplayName("使用PriorityQueue方法测试")
	@Test
	public void solutionOneTest() {

		KthLargest.SolutionOne solution = new KthLargest.SolutionOne(1, new int[]{});
		assertEquals(-4, solution.add(-4));
		assertEquals(-3, solution.add(-3));
		assertEquals(0, solution.add(0));
		assertEquals(2, solution.add(2));
	}
}
