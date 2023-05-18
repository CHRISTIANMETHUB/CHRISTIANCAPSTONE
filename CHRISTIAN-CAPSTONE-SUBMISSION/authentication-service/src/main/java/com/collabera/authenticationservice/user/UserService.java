package com.collabera.authenticationservice.user;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    String saveUser(UserRequestModel userRequestModel);

    Boolean validateToken(HttpServletRequest request);
}
