package com.example.amazon.test.task.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    private static final String SECRET_KEY = "HfJ68/oZYD2+le0NJyIk2/5SFrqCs4A5qgMz0DmWZAStZL59PWf/Me/KcNBfw97e";
    public String extractUser(String user){
        return extractClaim(user,Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String user = extractUser(token);
        return (user.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
    return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey(){
        byte [] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
