package academy.codilas.loyaltycard.domain.service;

import academy.codilas.loyaltycard.domain.model.Admin;
import academy.codilas.loyaltycard.property.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenServiceImpl implements TokenService {

    private final JwtProperties jwtProperties;

    @Autowired
    public TokenServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String createToken(Admin admin) {

        // Calculate the expiration date based on the current time and expiration time in milliseconds
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtProperties.ttlMillis());

        // Build JWT claims
        ClaimsBuilder claimsBuilder =
                Jwts.claims().issuedAt(now).expiration(expiration).subject(admin.getId().toString());

        Claims claims = claimsBuilder.build();

        // Create and sign the JWT token
        return Jwts.builder().claims(claims).signWith(getSecretKey()).compact();
    }

    public boolean isValidToken(String token) {

        try {
            Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public String getAdminId(String token) {

        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    private SecretKey getSecretKey() {

        return Keys.hmacShaKeyFor(jwtProperties.secret().getBytes(StandardCharsets.UTF_8));
    }
}
