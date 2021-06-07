package com.skynet.jwtsecurity.utils;

import static com.skynet.jwtsecurity.utils.CookieCreator.CookieBuilder.from;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import com.skynet.commons.property.PortalProperties;
import com.skynet.jwtsecurity.JWTSecurityProperties;
import org.springframework.stereotype.Component;


@Component
public class CookieCreator {

    private static final String ROOT_PATH = "/";
    private static final String[] SISENSE_COOKIE_NAMES = {".prism_anonymous", ".ASPXANONYMOUS", ".prism_shared"};

    private final JWTSecurityProperties securityProperties;
    private final PortalProperties portalProperties;

    public CookieCreator(final JWTSecurityProperties jwtSecurityProperties, final PortalProperties portalProperties) {
        this.securityProperties = jwtSecurityProperties;
        this.portalProperties = portalProperties;
    }

    public Cookie createJwtCookie(final String value, final boolean rememberMe) {
        return from(JWTSecurityProperties.AUTHORIZATION_COOKIE, value)
                .path(ROOT_PATH)
                .maxAge(getTokenValidityInSec(rememberMe))
                .domain(portalProperties.getHost())
                .httpOnly(true)
                .secure(securityProperties.isSecureCookie())
                .build();
    }

    public Cookie invalidateJwtCookie() {
        return from(JWTSecurityProperties.AUTHORIZATION_COOKIE, null)
                .path(ROOT_PATH)
                .maxAge(0)
                .domain(portalProperties.getHost())
                .httpOnly(true)
                .secure(securityProperties.isSecureCookie())
                .build();
    }

    public List<Cookie> invalidateCookies() {
        final List<Cookie> cookies = new ArrayList<>(SISENSE_COOKIE_NAMES.length);
        final String domain = "sisense." + portalProperties.getHost();
        for (String sisenseCookie : SISENSE_COOKIE_NAMES) {
            final Cookie cookie = from(sisenseCookie, null)
                    .path(ROOT_PATH)
                    .domain(domain)
                    .maxAge(0)
                    .httpOnly(true)
                    .build();
            cookies.add(cookie);
        }
        return cookies;
    }


    private int getTokenValidityInSec(final boolean rememberMe) {
        return rememberMe ? securityProperties.getTokenValidityForRememberMeInSec() :
                securityProperties.getTokenValidityInSec();
    }

    public static class CookieBuilder {

        private final Cookie cookie;

        public static CookieBuilder from(final String name, final String value) {
            return new CookieBuilder(name, value);
        }

        private CookieBuilder(final String name, final String value) {
            this.cookie = new Cookie(name, value);
        }

        public CookieBuilder maxAge(final int maxAgeSeconds) {
            cookie.setMaxAge(maxAgeSeconds);
            return this;
        }

        public CookieBuilder path(final String path) {
            cookie.setPath(path);
            return this;
        }

        public CookieBuilder domain(final String domain) {
            cookie.setDomain(domain);
            return this;
        }

        public CookieBuilder secure(final boolean secure) {
            cookie.setSecure(secure);
            return this;
        }

        public CookieBuilder httpOnly(final boolean httpOnly) {
            cookie.setHttpOnly(httpOnly);
            return this;
        }

        public Cookie build() {
            return cookie;
        }
    }
}
