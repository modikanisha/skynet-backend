package com.skynet.jwtsecurity;

import com.skynet.jwtsecurity.utils.CookieCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public abstract class AbstractSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final TokenService tokenProvider;
    private final CookieCreator cookieCreator;
    @Autowired
    private ApiLoginService apiLoginService;

    public AbstractSecurityConfiguration(TokenService tokenProvider, CookieCreator cookieCreator) {
        this.tokenProvider = tokenProvider;
        this.cookieCreator = cookieCreator;
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                .antMatchers("/api/**").authenticated()     // uncomment when security will be active
                //.anyRequest().permitAll()   //NFR-38                  // comment when security will be active
                .anyRequest().authenticated()  // DPT-1 Pentest 
                .and()
                .apply(securityConfigurerAdapter())
                .and()
                .httpBasic().disable()
                .formLogin().disable();
        http.addFilterAfter(new XSSFilter(apiLoginService), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider, cookieCreator);
    }
}
