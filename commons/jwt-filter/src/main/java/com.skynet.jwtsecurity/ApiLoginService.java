package com.skynet.jwtsecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
@Data
public class ApiLoginService {

  @Autowired
  private JWTSecurityProperties jwtSecurityProperties;

  private final AntPathMatcher antPathMatcher = new AntPathMatcher();
  private List<IpAddressMatcher> ipAddressMatchers = new ArrayList<>();

  @PostConstruct
  public void init() {
    jwtSecurityProperties.getApiIpMasks().stream()
        .map(IpAddressMatcher::new)
        .collect(Collectors.toCollection(() -> ipAddressMatchers));
  }

  public boolean isApiLogin(HttpServletRequest request) {
    return (isAmeyoEndpoint(request) || isApiLoginEndpoint(request)) && verifyIp(request);
  }

  private boolean isAmeyoEndpoint(HttpServletRequest request) {
    return hasParameter(request, "requestXml")
        && uriMatches(request, "/api/sso/ameyo/login_logout");
  }

  private boolean isApiLoginEndpoint(HttpServletRequest request) {
    return uriMatches(request, "/api/providermgmt/authenticate/sso-login");
  }

  private boolean uriMatches(HttpServletRequest request, String pattern) {
    return antPathMatcher.match(pattern, request.getRequestURI());
  }

  private boolean hasParameter(HttpServletRequest request, String parameter) {
    return StringUtils.isNotBlank(request.getParameter(parameter));
  }

  private boolean verifyIp(HttpServletRequest request) {
    return ipAddressMatchers.isEmpty() || getRemoteAddress(request)
        .map(remoteIp -> ipAddressMatchers.stream()
            .anyMatch(ipAddressMatcher -> ipAddressMatcher.matches(remoteIp)))
        //local connection
        .orElse(true);
  }

  @SneakyThrows
  private Optional<String> getRemoteAddress(HttpServletRequest request) {
    String remoteIp = request.getHeader("X-Forwarded-For");
    if (StringUtils.isBlank(remoteIp)) {
      remoteIp = request.getRemoteAddr();
      if ("0:0:0:0:0:0:0:1".equals(remoteIp)) {
        return Optional.empty();
      }
    }
    return Optional.of(remoteIp);
  }
}
