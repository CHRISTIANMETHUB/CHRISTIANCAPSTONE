package com.collabera.authenticationservice.user;

import com.collabera.authenticationservice.common.util.NameUtil;
import com.collabera.authenticationservice.common.util.NumberUtil;
import com.collabera.authenticationservice.common.util.PasswordUtil;
import com.collabera.authenticationservice.config.jwt.JwtUserDetailsService;
import com.collabera.authenticationservice.config.jwt.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Override
    public String saveUser(UserRequestModel userRequestModel) {
        Optional<UserEntity> checkByEmail = userRepository.findByEmail(userRequestModel.getEmail());

        Optional<UserEntity> checkByUsername = userRepository.findByUsername(userRequestModel.getUsername());
        if(checkByUsername.isPresent())
            throw new UserException(UserMessage.USER_EMAIL_EXIST);
        if(checkByEmail.isPresent())
            throw new UserException(UserMessage.USER_EMAIL_EXIST);
        Optional<UserEntity> checkByPhoneNumber = userRepository.findByPhoneNumber(userRequestModel.getPhoneNumber());
        if(checkByPhoneNumber.isPresent())
            throw new UserException(UserMessage.USER_PHONE_EXIST);
        if(!PasswordUtil.isValidPassword(userRequestModel.getPassword()))
            throw new UserException(UserMessage.USER_PASSWORD_INVALID);
        if(!NameUtil.isValidName(userRequestModel.getFirstName()) ||
                !NameUtil.isValidName(userRequestModel.getMiddleName()) || !NameUtil.isValidName(userRequestModel.getLastName()))
            throw new UserException(UserMessage.USER_INVALID_NAME);
        if(!NumberUtil.isValidNumber(userRequestModel.getPhoneNumber()))
            throw new UserException((UserMessage.USER_INVALID_PHONE_NUMBER));
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestModel.getUsername());
        userEntity.setEmail(userRequestModel.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userRequestModel.getPassword()));
        userEntity.setPhoneNumber(userRequestModel.getPhoneNumber());
        userEntity.setFirstName(userRequestModel.getFirstName().toUpperCase());
        userEntity.setMiddleName(userRequestModel.getMiddleName().toUpperCase());
        userEntity.setLastName(userRequestModel.getLastName().toUpperCase());

        userRepository.save(userEntity);

        return UserMessage.USER_SAVE_SUCCESS;
    }

    @Override
    public Boolean validateToken(HttpServletRequest request) {
        try {
            String tokenHeader = request.getHeader("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring(7);
                return tokenManager.validateJwtToken(token, jwtUserDetailsService.loadUserByUsername(tokenManager.getUsernameFromToken(token)));
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false;
        }
    }

}
