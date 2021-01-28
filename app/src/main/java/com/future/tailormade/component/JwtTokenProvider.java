package com.future.tailormade.component;

import com.future.tailormade.model.entity.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private Long jwtExpirationTime;

    @Value("${app.jwtRefreshExpirationInMs}")
    private Long jwtRefreshExpirationTime;

    public String getKey() {
        return jwtSecret;
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserIdFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateAccessToken(User user) {
        return generateToken(user, jwtExpirationTime);
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, jwtRefreshExpirationTime);
    }

    public String generateToken(User user, Long expiryTime) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("ROLE", user.getRole());
        return doGenerateToken(claims, user.getId(), expiryTime);
    }

    public String doGenerateToken(
            Map<String, Object> claims,
            String userId,
            Long expirationTime
    ) {
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userId)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parse(token);
            return !isTokenExpired(token);
        } catch (SignatureException
                | MalformedJwtException
                | ExpiredJwtException
                | UnsupportedJwtException
                | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
