package sardorcreate.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import sardorcreate.entity.User;
import sardorcreate.util.MessageService;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTProvider {

    @Value("${jwt.secret-key}")
    private String secret_key;

    @Value("${jwt.expiration}")
    private Duration expiration;

    public String generateAccessToken(User user) {return generateToken(user, expiration.toMillis());}

    private String generateToken(User user, long expiration) {

        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signKey())
                .compact();
    }

    public String extractUsername(String token) {return extractClaims(token, Claims::getSubject);}

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(signKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidToken(String token, UserDetails userDetails) {

        final String login = extractUsername(token);

        return (login.equals(userDetails.getUsername()) && !isTokenExpiration(token));
    }

    private boolean isTokenExpiration(String token) { return extractExpiration(token).before(new Date());}

    private Date extractExpiration(String token) { return extractClaims(token, Claims::getExpiration);}

    private Key signKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret_key);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    public void invalidateToken(String token) {

        Claims claims = extractAllClaims(token);

        if (claims == null) {
            throw new BadCredentialsException(MessageService.getMessage("INVALID_TOKEN"));
        }

        claims.setExpiration(new Date());

        Jwts.builder()
                .setClaims(claims)
                .signWith(signKey())
                .compact();
    }
}
