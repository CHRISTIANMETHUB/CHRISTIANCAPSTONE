package com.collabera.authenticationservice.config.jwt;

import com.collabera.authenticationservice.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class JwtController {
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(ApiEndpoint.LOGIN)
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel
                                                request) throws DisabledException, BadCredentialsException {


        authenticationManager.authenticate(
                new
                        UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword())
        );
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}
