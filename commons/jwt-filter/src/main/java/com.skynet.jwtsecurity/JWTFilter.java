package com.skynet.jwtsecurity;

import com.skynet.jwtsecurity.utils.CookieCreator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filters incoming requests and installs a Spring Security Authentication if a valid jwt is found.
 */
@AllArgsConstructor
@Slf4j
public class JWTFilter extends OncePerRequestFilter {
    private TokenService tokenService;
    private CookieCreator cookieCreator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String jwt = tokenService.resolveToken(request);
        if (StringUtils.isBlank(jwt)) {
            log.debug("Received a blank token");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Cookie authTokenCookie = WebUtils.getCookie(request, JWTSecurityProperties.AUTHORIZATION_BEARER_COOKIE);
            if (authTokenCookie != null) {
                if (tokenService.getAuthentication(jwt).isAuthenticated()) {
                    Authentication authentication = tokenService.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                Authentication authentication = tokenService.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (SignatureException | MalformedJwtException
                | ExpiredJwtException | UnsupportedJwtException
                | IllegalArgumentException e) {
            log.trace("Error while parsing jwt: ", e);
        }
        filterChain.doFilter(request, response);
    }
}
