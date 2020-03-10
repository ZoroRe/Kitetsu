package me.zoro.kitetsu.algorithm.str;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-03-08 22:58
 */
public class RabinKarpTest {

	@Test
	@DisplayName("寻找子字符串之RK算法")
	public void rabinKarpTest() {
		RabinKarp searcher = new RabinKarp();
		assertEquals(-1, searcher.rabinKarp(null, null));
		assertEquals(-1, searcher.rabinKarp("ababaxy", null));
		assertEquals(-1, searcher.rabinKarp("xy", "xyz"));
		assertEquals(-1, searcher.rabinKarp("abababA", "xyz"));
		assertEquals(-1, searcher.rabinKarp("ababaxy", "xyz"));
		assertEquals(5, searcher.rabinKarp("ababaxy", "xy"));
		assertEquals(0, searcher.rabinKarp("ababaxy", "ab"));
		assertEquals(0, searcher.rabinKarp("ababaxy", "abab"));
		assertEquals(6, searcher.rabinKarp("ababababcxy", "abc"));
	}
}
