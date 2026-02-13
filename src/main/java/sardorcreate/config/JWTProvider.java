package sardorcreate.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import sardorcreate.util.MessageService;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JWTProvider {

    @Value("")
    private String SECRET_KEY;

    @Value("")
    private long TOKEN_EXPIRATION;

    public String generateAccessToken(UserDetails userDetails) {return generateToken(userDetails, TOKEN_EXPIRATION);}

    private String generateToken(UserDetails userDetails, long expiration) {

        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
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

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

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
