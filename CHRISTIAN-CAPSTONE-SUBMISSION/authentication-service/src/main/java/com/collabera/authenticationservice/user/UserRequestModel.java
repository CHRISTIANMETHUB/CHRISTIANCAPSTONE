package com.collabera.authenticationservice.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserRequestModel {

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String firstName;

    @NonNull
    private String middleName;

    @NonNull
    private String lastName;

    @NonNull
    private String username;
}
