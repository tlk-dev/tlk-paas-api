package com.tlk.api.jpa.member.repository;

import com.tlk.api.jpa.member.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
    UserJpa findAllByUserName(String userName);

    UserJpa findByUserNameAndPassword(String userName, String password);
}
