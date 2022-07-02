package com.industry.util;

import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lc
 * @description: jwt工具类
 * @date 2021/6/18 21:09
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtUtil {
    @Value("${jwt.header}")
    private String header;
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 生成token
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {

        Date nowDate = new Date();
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(nowDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 解析token
     *
     * @param jwt
     * @return
     */
    public Claims getClaimByToken(String jwt) {
        Claims claims;
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }

    /**
     * 验证token是否过期
     *
     * @param claims
     * @return
     */
    public boolean isTokenExpired(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

}
