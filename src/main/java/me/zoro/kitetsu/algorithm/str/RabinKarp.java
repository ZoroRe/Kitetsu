package me.zoro.kitetsu.algorithm.str;

/**
 * @author luguanquan
 * @date 2020-03-08 22:13
 * @email luguanquans@qq.com
 * 寻找子字符串相关算法
 */
public class RabinKarp {


	/**
	 * 由 Rabin 和 Karp 设计的查找子字符串算法，算法原理是将子字符串按照一定规则hash,如按位加，如26(只有26字母不区分大小写的字符串),
	 * 52(只有字母区分大小写的字符串) 或根据字符串可能取值设计一个进制。按位加比如26个字母每位一个数加起来，如 abc 就是 1+2+3=6,
	 * xyz=24+25+26=75,然后 hash 相等再一一比较，类似先 hash 后 equals 才是最终相等
	 *
	 * @param source
	 * @param sub
	 * @return 找不到子串返回-1,找到返回首次出现的其实地址
	 */
	public int rabinKarp(String source, String sub) {
		if (source == null || sub == null) {
			return -1;
		}
		if (source.length() < sub.length()) {
			return -1;
		}
		int hashSub = rabinKarpHash(sub);
		int subLength = sub.length();
		int searchLength = source.length() - subLength + 1;
		String curSub;
		for (int i = 0; i < searchLength; i++) {
			// 计算子串的 hash 必须重复利用，否则计算性能无法提高,要保证重用就不能溢出和求模,这个地方还需要优化
			curSub = source.substring(i, i + subLength);
			if (hashSub != rabinKarpHash(curSub)) {
				continue;
			}
			if (isEquals(sub, curSub)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 提供 rabinKarp 使用的 hash 算法，这里使用52进制，然后对 10000求模,计算时遇到溢出不处理
	 * ASCII 中 A=65,Z=90,a=97,122
	 *
	 * @param source
	 * @return
	 */
	private int rabinKarpHash(String source) {
		if (source == null || source.length() == 0) {
			return 0;
		}
		long result = 0L;
		int last = source.length() - 1;
		for (int i = last; i > -1; i--) {
			result += valueOfChar(source.charAt(last)) * Math.pow(52, last - i);
		}
		return (int) (result % 10000);
	}

	private int valueOfChar(char c) {
		return c >= 'a' ? (27 + (c - 'a')) : (c - 'A' + 1);
	}

	/**
	 * 比较两个字符串是否相等，提供rabinKarp使用,由rabinKarp确认传入的字符串非空
	 *
	 * @param source
	 * @param target
	 * @return
	 */
	private Boolean isEquals(String source, String target) {
		if (source == null && target == null) {
			return true;
		}
		if (source.length() != target.length()) {
			return false;
		}
		for (int i = 0; i < source.length(); i++) {
			if (source.charAt(i) != target.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
