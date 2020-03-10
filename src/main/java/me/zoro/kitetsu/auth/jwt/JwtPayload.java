package me.zoro.kitetsu.auth.jwt;

import lombok.Data;

/**
 * @author luguanquan
 * @date 2020-03-10 21:17
 */
@Data
public class JwtPayload {

	private String name = null;
	private String mail = null;
	private String type = null;
	private String iss = null;
	private String sub = null;
	private Long iat = null;
	private Long exp = null;
	private String jti = null;
}
