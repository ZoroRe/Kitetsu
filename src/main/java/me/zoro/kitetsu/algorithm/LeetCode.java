package me.zoro.kitetsu.algorithm;

import lombok.extern.slf4j.Slf4j;

/**
 * @author luguanquan
 * @date 2020-03-21 23:21
 */
@Slf4j
public class LeetCode {

	public static String q43_multiply(String num1, String num2) {
		if ("0".equals(num1) || "0".equals(num2)) {
			return "0";
		}
		char[] c1 = num1.toCharArray();
		char[] c2 = num2.toCharArray();
		int[] array = new int[c1.length + c2.length];
		int carry = 0;
		int startIndex = array.length - 2;
		int tempIndex = 0;
		for (int i = num1.length() - 1; i >= 0; i--) {
			for (int j = num2.length() - 1; j >= 0; j--) {
				tempIndex = startIndex - i - j;
				array[tempIndex] = carry + (c1[i] - '0') * (c2[j] - '0') + array[tempIndex];
				carry = array[tempIndex] / 10;
				array[tempIndex] = array[tempIndex] % 10;
			}
			if (carry > 0) {
				array[startIndex - i + 1] = carry;
				carry = 0;
			}
		}
		StringBuilder builder = new StringBuilder();
		int index = array.length - 1;
		if (array[index] != 0) {
			builder.append(array[index]);
		}
		while (index > 0) {
			builder.append(array[--index]);
		}
		return builder.toString();
	}
}
