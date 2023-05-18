package com.ses.gateway.jwt;

import com.ses.gateway.exception.ApiGatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    @Autowired
    private JwtRouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            boolean secured = validator.isSecured.test(exchange.getRequest());
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if(exchange.getRequest().getPath().value().contains("login")){
                    return chain.filter(exchange);
                }
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION) ) {
                    throw new ApiGatewayException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new ApiGatewayException("Unauthorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public JwtFilter() {
        super(Config.class);
    }

    public static class Config{

    }

}
