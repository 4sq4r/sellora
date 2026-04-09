//package kz.sellora.core.service.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jws;
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import kz.sellora.configuration.security.JwtProperties;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//@Slf4j
//@Service
//public class JwtService {
//
//    private final JwtProperties jwtProperties;
//    private final SecretKey key;
//
//    public JwtService(JwtProperties jwtProperties) {
//        this.jwtProperties = jwtProperties;
//        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
//    }
//
//    public String generateAccessToken(String username) {
//        return generateAccessToken(username, null, null);
//    }
//
//    public String generateAccessToken(String username, String companyId, String companyName) {
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + jwtProperties.getAccessTokenExpiration());
//        var builder = Jwts.builder()
//            .subject(username)
//            .issuedAt(now)
//            .expiration(expiry)
//            .claims()
//            .claim("type", "access");
//
//        if (companyId != null) {
//            builder.claim("companyId", companyId);
//        }
//        if (companyName != null) {
//            builder.claim("companyName", companyName);
//        }
//
//        return builder
//            .signWith(key)
//            .compact();
//    }
//
//    public String extractUsername(String token) {
//        return parse(token).getPayload().getSubject();
//    }
//
//    public String generateRefreshToken(String username, String deviceId) {
//
//        Date now = new Date();
//        Date expiry = new Date(now.getTime() + jwtProperties.getRefreshTokenExpiration());
//
//        return Jwts.builder()
//            .subject(username)
//            .issuedAt(now)
//            .expiration(expiry)
//            .claim("type", "refresh")
//            .claim("deviceId", deviceId)
//            .id(UUID.randomUUID().toString())
//            .signWith(key)
//            .compact();
//    }
//
//    public boolean isValidToken(String token) {
//        try {
//            parse(token);
//            return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//
//    public boolean isTokenRefresh(String token) {
//        try {
//            Claims claims = parse(token).getPayload();
//            return !"refresh".equals(claims.get("type"));
//        } catch (JwtException e) {
//            return true;
//        }
//    }
//
//    private Jws<Claims> parse(String token) {
//        return Jwts.parser()
//            .verifyWith(key)
//            .build()
//            .parseSignedClaims(token);
//    }
//}
