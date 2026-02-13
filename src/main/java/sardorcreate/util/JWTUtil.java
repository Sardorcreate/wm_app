package sardorcreate.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import sardorcreate.dto.JWTDto;
import sardorcreate.enums.Role;

import javax.crypto.SecretKey;
import java.util.Date;

public class JWTUtil {

    @Value("${jwt.secret}")
    private static String SECRET;

    private static SecretKey getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public static String encode(long id, String login, Role role) {

        return Jwts.builder()
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000))
                .issuer("Sardorcreate")
                .claim("id", id)
                .claim("login", login)
                .claim("role", role.name())
                .signWith(getKey()).compact();
    }

    public static JWTDto decode(String token) {

        try {

            Claims claims = Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Long id = claims.get("id", Long.class);
            String login = claims.get("login", String.class);
            String role = claims.get("role", String.class);

            JWTDto dto = new JWTDto();

            dto.setId(id);
            dto.setLogin(login);
            dto.setRole(Role.valueOf(role));

            return dto;
        } catch (JwtException e) {
            throw new RuntimeException("Token is incorrect");
        }
    }
}
