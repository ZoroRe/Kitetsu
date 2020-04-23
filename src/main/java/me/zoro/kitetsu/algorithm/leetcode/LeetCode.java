package me.zoro.kitetsu.algorithm.leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @author luguanquan
 * @date 2020-03-21 23:21
 */
@Slf4j
public class LeetCode {

	/**
	 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
	 * https://leetcode-cn.com/problems/multiply-strings/
	 * @param num1
	 * @param num2
	 * @return
	 */
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

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		while(in.hasNextLine()){
			String info = in.nextLine();
			int[] chars = new int[60];
			char[] array = info.toCharArray();
			StringBuilder builder = new StringBuilder();
			int index;
			for(int i = 0; i < array.length; i++){
				index = array[i] - 'A';
				if(chars[index] == 0){
					builder.append(array[i]);
					chars[index] = 1;
				}
			}
			System.out.print(builder.toString());
		}
		in.close();
	}
}
