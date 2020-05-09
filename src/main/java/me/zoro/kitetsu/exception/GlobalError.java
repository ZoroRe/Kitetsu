package me.zoro.kitetsu.exception;

/**
 * @author luguanquan
 * @date 2020-05-09 13:59
 */
public class GlobalError {

	/**
	 * 错误码
	 */
	private int code;
	/**
	 * 中文错误文本
	 */
	private String zh;
	/**
	 * 英文错误文本
	 */
	private String en;

	public GlobalError(int code, String zh, String en) {
		this.code = code;
		this.zh = zh;
		this.en = en;
	}

	public GlobalError(int code, String zh) {
		this.code = code;
		this.zh = zh;
	}

	public HttpError formatHttpError() {
		return new HttpError(code, zh);
	}

	public static final GlobalError UNKNOWN_ERROR = new GlobalError(-100, "未知错误");
	public static final GlobalError AUTH_FAIL_ERROR = new GlobalError(-101, "无权访问");

}
