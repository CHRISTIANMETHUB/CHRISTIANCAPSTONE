package com.ses.gateway.jwt;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JwtRouteValidator {

    public static final List<String> openApiEndpoints = Arrays
            .asList("authentication-service/users/login",
                    "authentication-service/users/register",
                    "eureka");

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints.stream().noneMatch(uri -> request.getURI().getPath().contains(uri));

}
