package com.collabera.authenticationservice.config.jwt;

import com.collabera.authenticationservice.common.constant.ApiEndpoint;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.apache.http.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
//Filter base class that aims to guarantee a single execution per request dispatch, on any servlet container.
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private TokenManager tokenManager;

    private static final String API_GATEWAY_HOST = "localhost:8282";
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String forwardedHost = request.getHeader("X-Forwarded-Host");
            if (!API_GATEWAY_HOST.equals(forwardedHost)) {
                throw new HttpException();
            }

            if(!request.getRequestURI().contains(ApiEndpoint.LOGIN) && !request.getRequestURI().equals(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS)
                    && !request.getRequestURI().equals(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS + ApiEndpoint.VALIDATE_JWT_TOKEN)) {
                String tokenHeader = request.getHeader("Authorization");
                String username = null;
                String token = null;
                if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                    token = tokenHeader.substring(7);
                    username = tokenManager.getUsernameFromToken(token);
                } else {
                    log.error("Bearer String not found in token");
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    boolean isValidToken = tokenManager.validateJwtToken(token, userDetails);
                    if (isValidToken) {
                        UsernamePasswordAuthenticationToken
                                authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());
                        authenticationToken.setDetails(new
                                WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            log.error("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            log.error("JWT Token has expired");
        } catch (MalformedJwtException e) {
            log.error("Unable to read JSON value");
        } catch (HttpException e){
            log.error("X-Forwarded-Host not found");
        }
    }
}
