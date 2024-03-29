package br.com.Felipe.App.Contatos.configuration;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenUtil {

	private String secret = "umSegredoMuitoLongoQueTemMaisDe256BitsParaSerSeguroComHMACSHA";
	private long validadeMilisegundos = 3600000;
	
	public String createToken(String username) {
		Date agora = new Date();
		Date validade = new Date(agora.getTime() + validadeMilisegundos);
		
		byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());
		
		Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);
		
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(agora)
				.setExpiration(validade)
				.signWith(secretKey)
				.compact();
	}
	
	
	public boolean validateToken(String token) {
		try {
			byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());			
			Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(apiKeySecretByte)
					.parseClaimsJws(token);
			return !claims.getBody().getExpiration().before(new Date());
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getUserNameFromToken(String token) {
		try {
			byte[] apiKeySecretByte = Base64.getEncoder().encode(secret.getBytes());			
			Key secretKey = Keys.hmacShaKeyFor(apiKeySecretByte);
			
			Jws<Claims> claims = Jwts.parser().setSigningKey(apiKeySecretByte)
					.parseClaimsJws(token);
			return claims.getBody().getSubject();
		} catch (Exception e) {
			return "";
		}
	}
}
