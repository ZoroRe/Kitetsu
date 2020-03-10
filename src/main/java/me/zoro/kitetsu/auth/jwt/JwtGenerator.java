package me.zoro.kitetsu.auth.jwt;

import lombok.extern.slf4j.Slf4j;
import me.zoro.kitetsu.hash.HashUtils;
import me.zoro.kitetsu.json.JsonHelper;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

/**
 * @author luguanquan
 * @date 2020-03-10 21:17
 * jwt是一种简单的认证方式，通过保存一些能识别客户身份(不敏感)的数据以及对其加密，发送给客户端，从而当客户端请求需要验证的资源时，对客户端
 * 提供的jwt进行验证，jwt 相对于 cookie, token 其特点是不需要后台存储，只需要根据客户端提供的jwt用服务器保存的密钥验证，验证通过即可
 * header.payload.signature
 * header: 原始内容为 json 字符串，typ 表示类型， alg表示加密算法,对其进行base64的url encoder 得到
 * payload: 原始内容为 json 字符串，包含能识别客户端的一些信息，以及签发机构，签发时间等信息,通过 base64 的 url encoder 得到
 * signature: 将 header 和 payload 用 . 连接起来，再使用 header 的 alg 指定加密算法以及服务器保存的密钥进行加盐加密，然后下发给客户端。
 * 这样客户端提交 jwt 只要能成功解密，就能识别出客户端的信息，也能验证身份。但是对加密用的密钥一定要保存好
 */
@Slf4j
public class JwtGenerator {

	public String generate(String userId, String keySecret) {
		// 第一部分
		JwtHeader jwtHeader = new JwtHeader();
		jwtHeader.setTyp("JWT");
		jwtHeader.setAlg("HS256");
		String header = Base64.getUrlEncoder().encodeToString(JsonHelper.toJson(jwtHeader).getBytes()).replace("=", "");

		//
		JwtPayload jwtPayload = new JwtPayload();
		jwtPayload.setName("zoro");
		jwtPayload.setIss(userId);
		jwtPayload.setMail("zoro@hello.com");
		jwtPayload.setType("0");
		jwtPayload.setSub("kiteesu");
		Long current = System.currentTimeMillis() / 1000;
		jwtPayload.setIat(current);
		// 有效期十秒
		jwtPayload.setExp(current + 10);
		jwtPayload.setJti(HashUtils.md5(UUID.randomUUID().toString() + current));
		String payload = Base64.getUrlEncoder().encodeToString(JsonHelper.toJson(jwtPayload).getBytes()).replace("=", "");

		String signature = HmacSha256((header + "." + payload).getBytes(), keySecret.getBytes()).replace("=", "");
		String jwt = header + "." + payload + "." + signature;
		return jwt;
	}

	public static String HmacSha256(byte[] data, byte[] key) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			String hash = Base64.getUrlEncoder().encodeToString(mac.doFinal(data));
			return hash.replace("=", "");
		} catch (NoSuchAlgorithmException e) {
			log.error("HmacSha256 加密失败:{}", e);
		} catch (InvalidKeyException e) {
			log.error("HmacSha256 加密失败:{}", e);
		}
		return null;
	}
}
