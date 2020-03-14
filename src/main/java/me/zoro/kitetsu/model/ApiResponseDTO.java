package me.zoro.kitetsu.model;

import lombok.Data;

/**
 * @author luguanquan
 * @date 2020-03-14 21:56
 */
@Data
public class ApiResponseDTO<T> {
	private Integer code;
	private String message;
	private Long timestamp;
	private T data;

	public ApiResponseDTO() {
		timestamp = System.currentTimeMillis();
	}

	public ApiResponseDTO(T data) {
		code = 0;
		message = "success";
		timestamp = System.currentTimeMillis();
		this.data = data;
	}
}
