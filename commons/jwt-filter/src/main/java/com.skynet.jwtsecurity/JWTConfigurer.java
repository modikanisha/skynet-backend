package com.skynet.jwtsecurity;

import com.skynet.jwtsecurity.utils.CookieCreator;
import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JWTConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenService tokenProvider;
    private final CookieCreator cookieCreator;

    @Override
    public void configure(HttpSecurity http) {
        JWTFilter customFilter = new JWTFilter(tokenProvider, cookieCreator);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
