package com.collabera.authenticationservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    public Optional<UserEntity> findByUsername(String username);
    public Optional<UserEntity> findByEmail(String username);

    public Optional<UserEntity> findByPhoneNumber(String phoneNumber);
}
