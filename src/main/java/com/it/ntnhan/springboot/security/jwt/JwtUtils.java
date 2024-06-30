package com.it.ntnhan.springboot.security.jwt;

import com.it.ntnhan.springboot.security.UserDetailsImpl;
import com.it.ntnhan.springboot.security.dto.ClaimName;
import com.it.ntnhan.springboot.utils.DateTimeUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.it.ntnhan.springboot.utils.DateTimeUtils.convertToSqlDateTime;

@Component
public class JwtUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_KIND = "Bearer";
    public static final String AUTHENTICATION_HEADER = "Authorization";

    @Value("${app.authentication.jwtSecretKey}")
    private String jwtSecret;

    @Value("${app.authentication.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {
        return generateJwtToken(authentication, null);
    }

    public String generateJwtToken(Authentication authentication, Map<String, Object> claims) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        Set<String> userRoles = userPrincipal
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        Map<String, Object> _claims = claims != null
                ? new HashMap<>(claims)
                : new HashMap<>();

        _claims.put(ClaimName.EMAIL.getValue(), userPrincipal.getEmail());
        _claims.put(ClaimName.USER_ID.getValue(), userPrincipal.getId());
        _claims.put(ClaimName.ROLES.getValue(), userRoles);
        _claims.put(ClaimName.ENABLE.getValue(), userPrincipal.isEnabled());
        _claims.put(ClaimName.TYPE.getValue(), TOKEN_KIND);
        _claims.put(ClaimName.VALID_FROM.getValue(), convertToSqlDateTime(userPrincipal.getValidFrom()));
        _claims.put(ClaimName.VALID_TO.getValue(), convertToSqlDateTime(userPrincipal.getValidTo()));
        _claims.put(ClaimName.CREATED_AT.getValue(), convertToSqlDateTime(userPrincipal.getCreatedAt()));

        return Jwts.builder()
                .header().type(TOKEN_TYPE)
                .and()
                .id(UUID.randomUUID().toString())
                .subject((userPrincipal.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(getSigningKey())
                .claims(_claims)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().verifyWith(getSigningKey())
                .build().parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean isValidJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT token is expired: {}", e.getMessage());
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT secret key: {}", e.getMessage());
        } catch (SecurityException e) {
            LOGGER.error("JWT had security issue: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
