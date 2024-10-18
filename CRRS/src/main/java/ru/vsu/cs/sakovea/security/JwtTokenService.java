package ru.vsu.cs.sakovea.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.vsu.cs.sakovea.models.User;
import ru.vsu.cs.sakovea.models.UserCompPerm;
import ru.vsu.cs.sakovea.models.UserDetailsImpl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtTokenService {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expirationTime;

    public String generateToken(UserDetailsImpl userDetails) {
        UserCompPerm userRole = userDetails.getUserCompPerm();
        User user = userDetails.getUser();
        Map<String, Object> claims = getStringObjectMap(userRole, user);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    private static Map<String, Object> getStringObjectMap(UserCompPerm userRole, User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("name", user.getName());
        claims.put("surname", user.getSurname());
        claims.put("thirdname", user.getThirdname());
        claims.put("login", user.getLogin());
        claims.put("userCompPerms", user.getRoles().getLast());
        return claims;
    }


    public String getName(String token) throws RuntimeException {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getSurname(String token) {
        return getAllClaimsFromToken(token).get("surname", String.class);
    }

    public String getPatronymic(String token) {
        return getAllClaimsFromToken(token).get("patronymic", String.class);
    }


    public String getUserCompPerms(String token) {
        return getAllClaimsFromToken(token).get("userCompPerms", String.class);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) throws RuntimeException {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Неверный JWT", e);
        }
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public boolean validateToken(String token, UserDetailsImpl userDetails) {
        final String username = getName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
