package com.collabera.authenticationservice.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @NonNull
    private Integer userId;

    @Column(name = "email")
    @NonNull
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Column(name = "phone_number")
    @NonNull
    private String phoneNumber;

    @Column(name = "first_name")
    @NonNull
    private String firstName;


    @Column(name = "middle_name")
    @NonNull
    private String middleName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "username")
    @NonNull
    private String username;

}
