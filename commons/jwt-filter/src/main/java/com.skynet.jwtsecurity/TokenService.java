package com.skynet.jwtsecurity;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class TokenService {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String PAYERS = "payers";

    private final JWTSecurityProperties jwtSecurityProperties;


    //private final MongoTemplate mongoTemplate;

    public String createToken(Authentication authentication, boolean rememberMe) {
        final EmployeeManagement principal = (EmployeeManagement) authentication.getPrincipal();

        final String delimiter = ",";
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(delimiter));

        final long now = System.currentTimeMillis();
        Date validity;
        if (rememberMe) {
            validity = new Date(now + jwtSecurityProperties.getTokenValidityForRememberMe());
        } else {
            validity = new Date(now + jwtSecurityProperties.getTokenValidity());
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecurityProperties.getSecretWord())
                .setExpiration(validity)
                .compact();
    }

    /**
     * Performs spring interceptor call to underlying cache manager to get appropriate cache record.
     * If cache doesn't contains such - performs this method and maps result in cache.
     * <p>
     * Throws jwt validation RuntimeException (from parseClaimsJws())
     * viz. SignatureException, MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException
     */
    @Cacheable(value = "token_authentication", condition = "#token != null", key = "#token")
    public Authentication getAuthentication(String token) {
        Claims claims = getClaimsJws(token).getBody();
        log.trace("-Create Authentication-");


        final String regex = ",";
        final Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(regex))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());


        final EmployeeManagement principal = new EmployeeManagement(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    private Jws<Claims> getClaimsJws(final String authToken) {
        return Jwts.parser().setSigningKey(jwtSecurityProperties.getSecretWord()).parseClaimsJws(authToken);
    }


    public String resolveToken(HttpServletRequest request) {
        Cookie authTokenCookie = WebUtils.getCookie(request, JWTSecurityProperties.AUTHORIZATION_COOKIE);
        if (authTokenCookie == null) {
            authTokenCookie = WebUtils.getCookie(request, JWTSecurityProperties.AUTHORIZATION_BEARER_COOKIE);
            if(authTokenCookie == null){
                return null;
            }
        }

        String token = authTokenCookie.getValue();
        return StringUtils.hasText(token) ? token : null;
    }

    /**
     * Returns json web token if exist.
     */
    public String getToken() {
        HttpServletRequest request = getRequest();
        return request == null ? null : resolveToken(request);
    }

    /**
     * Returns user email which have been set in SecurityContext during JWTFilter derived from request cookie with jwt.
     * Use only in context of http Session/Request be available.
     */
    public Optional<String> getUserEmailFromHttpRequest() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof User)) {
            return Optional.empty();
        }
        return Optional.of(auth.getName());
    }

    /**
     * Used in application project as dependency
     */
    public String getUserEmailFromToken(String token) {
        return token == null ? null : getClaimsJws(token).getBody().getSubject();
    }

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    /**
     * Performs spring interceptor call to underlying cache manager to evict appropriate cache record
     */
    @CacheEvict(value = "token_authentication", key = "#token")
    public void cacheEvict(String token) {
    }
}
