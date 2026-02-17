package sardorcreate.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sardorcreate.dto.JWTDto;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Duration expiration;

    public String encode(Long id, String login, String role) {

        return Jwts.builder()
                .setIssuer("Sardorcreate")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration.toMillis()))
                .claim("id", id)
                .claim("login", login)
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public JWTDto decode(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            JWTDto dto = new JWTDto();

            System.out.println("token = " + token);
            Number id = (Number) claims.get("id");

            if (id != null) {
                dto.setId(id.longValue());
            }

            dto.setLogin((String) claims.get("login"));
            dto.setRole((String) claims.get("role"));

            System.out.println("dto = " + dto);
            return dto;

        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }
}
