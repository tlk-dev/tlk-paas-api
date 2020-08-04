package com.tlk.api.jpa.repository;

import com.tlk.api.inter.UserJapInter;
import com.tlk.api.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
    UserJpa findAllByUserName(String userName);

    UserJapInter findByUserNameAndPassword(String userName, String password);
}
