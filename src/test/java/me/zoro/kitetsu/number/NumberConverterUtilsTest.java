package me.zoro.kitetsu.number;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author luguanquan
 * @date 2020-03-07 13:01
 * @email luguanquans@qq.com
 */
@DisplayName("进制转化测试")
public class NumberConverterUtilsTest {

	@Test
	@DisplayName("10进制自然数转换成62进制的字符")
	public void decimalTo62Test() {
		assertEquals("0", NumberConverterUtils.decimalTo62(0L));
		assertEquals("1", NumberConverterUtils.decimalTo62(1L));
		assertEquals("a", NumberConverterUtils.decimalTo62(10L));
		assertEquals("Z", NumberConverterUtils.decimalTo62(61L));
		assertEquals("10", NumberConverterUtils.decimalTo62(62L));
		assertEquals("11", NumberConverterUtils.decimalTo62(63L));
		assertEquals("ZZ", NumberConverterUtils.decimalTo62(3843L));
		assertEquals("ZZZ", NumberConverterUtils.decimalTo62(238327L));
		assertEquals("ZZZZ", NumberConverterUtils.decimalTo62(14776335L));
		assertEquals("ZZZZZ", NumberConverterUtils.decimalTo62(916132831L));
		assertEquals("100000", NumberConverterUtils.decimalTo62(916132832L));
	}

	@Test
	@DisplayName("62进制字符串转换成10进制自然数")
	public void decimalFrom62Test(){
		assertEquals(0L, NumberConverterUtils.decimalFrom62("0"));
		assertEquals(1L, NumberConverterUtils.decimalFrom62("1"));
		assertEquals(10L, NumberConverterUtils.decimalFrom62("a"));
		assertEquals(61L, NumberConverterUtils.decimalFrom62("Z"));
		assertEquals(62L, NumberConverterUtils.decimalFrom62("10"));
		assertEquals(63L, NumberConverterUtils.decimalFrom62("11"));
		assertEquals(3843L, NumberConverterUtils.decimalFrom62("ZZ"));
		assertEquals(238327L, NumberConverterUtils.decimalFrom62("ZZZ"));
		assertEquals(14776335L, NumberConverterUtils.decimalFrom62("ZZZZ"));
		assertEquals(916132831L, NumberConverterUtils.decimalFrom62("ZZZZZ"));
	}
}
