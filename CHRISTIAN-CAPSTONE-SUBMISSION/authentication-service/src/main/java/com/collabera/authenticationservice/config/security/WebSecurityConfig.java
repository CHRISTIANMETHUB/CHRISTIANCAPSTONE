package com.collabera.authenticationservice.config.security;

import com.collabera.authenticationservice.config.jwt.JwtAuthenticationEntryPoint;
import com.collabera.authenticationservice.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//Indicates that a class declares one or more @Bean methods and may be processed by the Spring container.
@Configuration
public class WebSecurityConfig {

    //Locates the user based on the username.
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter filter;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

//    Defines a filter chain which is capable of being matched against an HttpServletRequest. in order to decide whether it applies to that request.
//It allows configuring web based security for specific http requests
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/users/login").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/users/validateJwtToken").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
