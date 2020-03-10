package me.zoro.kitetsu.auth.jwt;

import lombok.Data;

/**
 * @author luguanquan
 * @date 2020-03-10 21:16
 */
@Data
public class JwtHeader {
	private String typ;
	private String alg;
}
