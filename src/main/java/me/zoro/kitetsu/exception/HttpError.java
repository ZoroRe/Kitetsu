package me.zoro.kitetsu.exception;

import lombok.Data;

/**
 * @author luguanquan
 * @date 2020-05-09 13:57
 */
@Data
public class HttpError {
	private Integer code = 0;
	private String message;
	private Long timestamp;

	public HttpError(Integer code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = System.currentTimeMillis();
	}
}
