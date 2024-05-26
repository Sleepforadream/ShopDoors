package com.shopdoors.dao.repository;

import com.shopdoors.dao.entity.AuthorizeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorizeUserRepository extends JpaRepository<AuthorizeUser, Long> {
    Optional<AuthorizeUser> findByEmail(String email);

    Optional<AuthorizeUser> findByNickName(String nickName);

}