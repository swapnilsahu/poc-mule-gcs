package com.mule.google.cloud.storage;


import io.jsonwebtoken.Jwts;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;


public class GenerateJWTToken {


	public static String createJwtSignedHMAC() throws InvalidKeySpecException, NoSuchAlgorithmException {


		PrivateKey privateKey = getPrivateKey();


		Instant now = Instant.now();
		String jwtToken = Jwts.builder().setIssuer("xxxxxxxx@xxxxxxxx.iam.gserviceaccount.com")  
				.setSubject("xxxxxxxx@xxxxxxxx.iam.gserviceaccount.com") 
				.claim("scope", "https://www.googleapis.com/auth/devstorage.read_write") 
				.setAudience("https://oauth2.googleapis.com/token").setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(60l, ChronoUnit.MINUTES))).signWith(privateKey).compact();


		return jwtToken;
	}


	private static PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String rsaPrivateKey = "-----BEGIN PRIVATE KEY-----"
				+ "xxxxxxxxxxxxxxx"
				+ "xxxxxxxxxxxxxxx"
				+ "-----END PRIVATE KEY-----";


		rsaPrivateKey = rsaPrivateKey.replace("-----BEGIN PRIVATE KEY-----", "");
		rsaPrivateKey = rsaPrivateKey.replace("-----END PRIVATE KEY-----", "");


		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(rsaPrivateKey));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PrivateKey privKey = kf.generatePrivate(keySpec);
		return privKey;
	}
}