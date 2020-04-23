package me.zoro.kitetsu.company;

import java.util.HashSet;
import java.util.Set;

/**
 * @author luguanquan
 * @date 2020-04-08 19:52
 * 无重复最长子串，只包含字母的
 */
public class Huawei {

	public static String maxNoRepeatSubstring(String source) {
		if (source == null || source.length() <= 1) {
			return source;
		}
		int startIndex = 0;
		int lastMax = 0;
		int curMax = 0;
		Set<String> set = new HashSet<>();
		String lastMaxSubstring = null;
		char[] arr = source.toCharArray();
		char c;
		for (int i = 0; i < arr.length; i++) {
			c = arr[i];
			if (set.contains("" + c)) {
				if (curMax > lastMax) {
					lastMax = curMax;
					lastMaxSubstring = source.substring(startIndex, i);
				}
				startIndex = indexOf(source, c, i - 1) + 1;
				set.clear();
				for (int j = startIndex; j <= i; j++) {
					set.add(arr[j] + "");
				}
				curMax = i - startIndex + 1;
			} else {
				set.add("" + c);
				curMax++;
				if (i == arr.length - 1 && curMax > lastMax) {
					lastMaxSubstring = source.substring(startIndex, arr.length);
				}
			}
		}
		return lastMaxSubstring;
	}

	private static int indexOf(String source, char c, int end) {
		for (int i = end; i >= 0; i--) {
			if (source.charAt(i) == c) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String answer = Huawei.maxNoRepeatSubstring(
				"abababaxkjfkasdkfjasdlkfjlaksdjflkasjdflkasjdlfkjsldkfjksdjfklasjdfl;" +
						"sdjfkjsl;dfjkklasjdglkjsdlkgjaksldgjkasjdgklsjdkgjaklsdjgklasjdgk" +
						"'ljasdgjaskldgjklasdjgklasjdgkasjdkgjaskdgjkasdjgkasjdgksajdklgjaskdgjkalsdjgkasjdgkjas;" +
						"lkdgjldsguoaisdjfklasjdabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZfklasjdklfhasldkfjkasljdfklsajfsadfiosej;" +
						"kfajekfjaskldjfkasdjfokajsdfdfasfjsakfjklsafjksafjskdjfksajdfklsajfkasjdfkasjdfkasjfkasjfkcabab");
		System.out.println(answer);
	}

}
