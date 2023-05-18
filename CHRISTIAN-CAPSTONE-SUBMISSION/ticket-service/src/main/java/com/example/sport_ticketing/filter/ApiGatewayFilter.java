package com.example.sport_ticketing.filter;

import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiGatewayFilter implements Filter {

    private static final String API_GATEWAY_HOST = "localhost:8282";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String forwardedHost = request.getHeader("X-Forwarded-Host");

        if (API_GATEWAY_HOST.equals(forwardedHost)) {
            // Host matches API Gateway, proceed with the request
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // Host does not match API Gateway, reject the request
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request");
        }
    }
}
