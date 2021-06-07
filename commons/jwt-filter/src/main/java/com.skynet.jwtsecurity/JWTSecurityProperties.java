package com.skynet.jwtsecurity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@ConfigurationProperties(prefix = "jwt", ignoreUnknownFields = false)
@Validated
public class JWTSecurityProperties {

    public static final String AUTHORIZATION_COOKIE = "ryl_auth_token";
    public static final String AUTHORIZATION_BEARER_COOKIE = "ryl_bearer_token";
    private static final long SEC_IN_MS = 1000;

    @NotBlank
    private String secretWord;
    @Positive
    private long tokenValidity;
    @Positive
    private long tokenValidityForRememberMe;
    private boolean secureCookie;
    private List<String> apiIpMasks = new ArrayList<>();

    public int getTokenValidityInSec() {
        return (int) (tokenValidity / SEC_IN_MS);
    }
    public int getTokenValidityForRememberMeInSec() {
        return (int) (tokenValidityForRememberMe / SEC_IN_MS);
    }
}
