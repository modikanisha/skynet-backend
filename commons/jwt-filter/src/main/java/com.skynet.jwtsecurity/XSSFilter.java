package com.skynet.jwtsecurity;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class XSSFilter extends OncePerRequestFilter {

    private final ApiLoginService apiLoginService;

    @Override
    public void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        //LOGGER.info("Logging request in XSS Filter::"+request.getContentType());

        //get param values and check if they contain bad data
        Map<String, String[]> allParamsMap = request.getParameterMap();
        for (Map.Entry<String, String[]> entry : allParamsMap.entrySet()) {
            String[] paramValues = entry.getValue();
            for (String val : paramValues) {
               //log.info("Request input parameter in XSS Filter :: " + val);
                if (!isDataValid(val)) {
                    //return bad request response
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Invalid data encountered in request");
                return;
                }
            }
        }

        //chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
        chain.doFilter(request, response);
    }

   protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
       return apiLoginService.isApiLogin(request);
   }

    private Boolean isDataValid(String s) throws UnsupportedEncodingException {
        Boolean isValidFlag = Boolean.TRUE;
        /*log.info("htmlEscape :: "+HtmlUtils.htmlEscape(s));
        log.info("URLDecoder :: "+URLDecoder.decode(s, "UTF-8" ));*/
        if (!(s.equalsIgnoreCase(HtmlUtils.htmlEscape(s)))
                && (s.equalsIgnoreCase(URLDecoder.decode(s, StandardCharsets.UTF_8.name())))) {
            isValidFlag = Boolean.FALSE;
            log.info("Invalid input data in XSS Filter :: " + s);
            return isValidFlag;
        }

        return isValidFlag;
    }

}
