package me.zoro.kitetsu.exception;

import lombok.Data;

/**
 * @author luguanquan
 * @date 2020-05-09 13:56
 */
@Data
public class GlobalException extends RuntimeException{
	private HttpError httpError;
}
