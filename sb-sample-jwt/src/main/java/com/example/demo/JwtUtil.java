package com.example.demo;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtUtil {
	
	public static final String SECRET = "PHnkGGZgpOOUHLRdYG3MtvB6S5lg1ltxiIw1rD0P9MI="; //测试用
	public static final String TOKEN_PREFIX = "Bearer "; // jwt前面一般都会加Bearer
	public static final String HEADER_STRING = "Authorization"; // 请求的时候将JWT放入Header中该键值对中
	
	private static final Log logger = LogFactory.getLog(JwtUtil.class);
	
	/**
	 * 生成Key
	 * @return
	 */
	public static String createKey() {
		Key secret = MacProvider.generateKey(SignatureAlgorithm.HS256);
	    byte[] secretBytes = secret.getEncoded();
	    String base64SecretBytes = Base64.getEncoder().encodeToString(secretBytes);
	    return base64SecretBytes;
	}
	
	/**
	 * 生成JWT
	 * @param id
	 * @param subject
	 * @param secret
	 * @param ttlMillis
	 * @return
	 */
	public static String createJWT(String subject, String base64SecretBytes, long ttlMillis) {	
		String id = UUID.randomUUID().toString().replace("-", "");
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		
		JwtBuilder builder = Jwts.builder()
				.setId(id)
				.setSubject(subject)
				.setIssuedAt(now)
				.setNotBefore(now)//A JWT obtained before this timestamp should not be used.
				.signWith(SignatureAlgorithm.HS256, base64SecretBytes);
		
		//if it has been specified, let's add the expiration
		if (ttlMillis > 0) {
		    builder.setExpiration(new Date(nowMillis + ttlMillis)); //A JWT obtained after this timestamp should not be used.
		}
		
		String jwt = builder.compact();
		logger.debug("JWT : "+jwt);
		
		return jwt;
	}

	/**
	 * 检验Token，成功返回payload
	 * @param token
	 */
	public static Map<String, Object> parseJWT(String jwt, String base64SecretBytes) throws Exception{
        try {
        		Map<String, Object> claims = Jwts.parser()        
        			   .setSigningKey(base64SecretBytes)
        			   .parseClaimsJws(jwt)
        			   .getBody();
        		
        		logger.debug("Claims : " + claims.toString());
        		return claims;
        }catch (Exception e){
        		e.printStackTrace();
        		throw e;
        }
	}
	
	public static void main(String[] args) {
		String secret1 = JwtUtil.createKey();
		String secret2 = JwtUtil.createKey();
		
		logger.debug("secret1 : " + secret1);
		logger.debug("secret2 : " + secret2);
		
		//Test1
		if(false){
			String jwt = JwtUtil.createJWT("subject", secret1, 0);
			try {
				JwtUtil.parseJWT(jwt, secret2);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Test2
		if(false){
			String jwt = JwtUtil.createJWT("subject", secret1, 1500l);
			for(int i=0; i<10; i++) {
				try {
					JwtUtil.parseJWT(jwt, secret1);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					Thread.currentThread().sleep(100l);
				} catch (InterruptedException e) {}
			}
		}
		
	}
}
