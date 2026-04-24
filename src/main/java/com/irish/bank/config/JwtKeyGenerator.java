package com.irish.bank.config;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // secure 256-bit key
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("Your secure JWT key (Base64): " + base64Key);
    }
}