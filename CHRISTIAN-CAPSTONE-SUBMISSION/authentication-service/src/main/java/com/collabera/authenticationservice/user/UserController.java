package com.collabera.authenticationservice.user;

import com.collabera.authenticationservice.common.constant.ApiEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody @Validated UserRequestModel userRequestModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequestModel));
    }

    @PostMapping(ApiEndpoint.VALIDATE_JWT_TOKEN)
    public ResponseEntity<Boolean> validateToken(HttpServletRequest request) {
        return ResponseEntity.ok(userService.validateToken(request));
    }

    @PostMapping("/hello")
    public ResponseEntity<String> hello()  {
        return ResponseEntity.ok("your a valid user");
    }
}
