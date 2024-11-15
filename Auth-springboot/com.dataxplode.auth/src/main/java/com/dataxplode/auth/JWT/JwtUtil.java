package com.dataxplode.auth.JWT;


import com.dataxplode.auth.Models.RoleModel.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String secretKey = "WhyS0$eri0us";

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClamis(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractAllClamis(String token){
        return Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String email, Role role, String name, String contactNumber, Long id, String status){
        Map<String,Object> claims = new HashMap<>();
        claims.put("role",role.getRoleName());
        claims.put("name",name);
        claims.put("contactNumber",contactNumber);
        claims.put("id",Long.toString(id));
        claims.put("status",status);
        claims.put("email",email);
        return createToken(claims,email);

    }

    private String createToken(Map<String, Object> claims,String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }



     public Boolean validateToken(String token, UserDetails userDetails){
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    // Method to generate a reset token
    public String generateResetToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 900000)) // Token valid for 15 min
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Method to validate the reset token
    public boolean validateResetToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
            // Additional validation can be added here
            return true;
        } catch (Exception e) {
            return false; // Token is invalid or expired
        }
    }

    // Method to extract email from the token
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

}
