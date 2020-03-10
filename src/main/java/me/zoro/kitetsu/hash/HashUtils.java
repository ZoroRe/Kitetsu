package me.zoro.kitetsu.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author luguanquan
 * @date 2020-03-10 21:35
 */
public class HashUtils {

	/**
	 * 摘要md5
	 * @param str
	 * @return
	 */
	public static String md5(String str) {
		return digest("MD5", str.getBytes());
	}

	/**
	 * 摘要md5
	 * @param bytes
	 * @return
	 */
	public static String md5(byte[] bytes) {
		return digest("MD5", bytes);
	}

	/**
	 * 摘要sha256
	 * @param str
	 * @return
	 */
	public static String sha1(String str) {
		return digest("SHA-1", str.getBytes());
	}

	/**
	 * 摘要sha256
	 * @param bytes
	 * @return
	 */
	public static String sha1(byte[] bytes) {
		return digest("SHA-1", bytes);
	}

	/**
	 * 摘要sha256
	 * @param str
	 * @return
	 */
	public static String sha256(String str) {
		return digest("SHA-256", str.getBytes());
	}

	/**
	 * 摘要sha256
	 * @param bytes
	 * @return
	 */
	public static String sha256(byte[] bytes) {
		return digest("SHA-256", bytes);
	}

	/**
	 * 摘要数据
	 * @param method 摘要方法
	 * @param bytes
	 * @return 发生异常返回null
	 */
	private static String digest(String method, byte[] bytes) {
		try {
			MessageDigest md = MessageDigest.getInstance(method);
			md.update(bytes);
			byte[] b = md.digest();
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < b.length; i++) {
				result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}

}
