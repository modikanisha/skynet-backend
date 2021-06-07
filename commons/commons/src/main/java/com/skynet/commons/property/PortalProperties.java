package com.skynet.commons.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("portal")
@Getter
@Setter
public class PortalProperties {

    private String scheme;
    private String host;
    private String port;
    private String contextPath;

    private String userMail;
    private String userName;

    private String supportEmail;
    private String supportPhoneNumber;
}
