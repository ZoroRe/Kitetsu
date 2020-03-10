package numer;

/**
 * @author luguanquan
 * @date 2020-03-07 12:34
 * @email luguanquans@qq.com
 * 进制转换工具类
 */
public final class NumberConverterUtils {

	private static final int MAX_ONE_CHARTER_OF_62 = 62;
	private static final char[] CHARS_62 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
			'y', 'z', 'A', 'B', 'C',
			'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
			'Y', 'Z'};

	/**
	 * 62进制，可用于短域名设计
	 * @param number 十进制数自然数
	 * @return 转换成62进制的字符串
	 */
	public static String decimalTo62(Long number) {
		if (number == null || number < 0) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		while (number >= 0) {
			builder.append(CHARS_62[(int) (number % MAX_ONE_CHARTER_OF_62)]);
			number = number / MAX_ONE_CHARTER_OF_62;
			if (number == 0) {
				break;
			}
		}
		return builder.reverse().toString();
	}

	/**
	 * 从符合62进制的字符串中转成10进制自然数
	 *
	 * @param source
	 * @return
	 */
	public static Long decimalFrom62(String source) {
		if (source == null) {
			return null;
		}
		Long result = 0L;
		int length = source.length();
		for (int i = 0; i < length; i++) {
			// 注意 0 的 0 次幂为1，所以0不用处理,否则反而多加1
			if (source.charAt(i) != '0') {
				result += (long) (indexOfChars62(source.charAt(i)) * Math.pow(MAX_ONE_CHARTER_OF_62, length - i - 1));
			}
		}
		return result;
	}

	private static Integer indexOfChars62(char c) {
		for (int i = 0; i < CHARS_62.length; i++) {
			if (c == CHARS_62[i]) {
				return i;
			}
		}
		return null;
	}

}
