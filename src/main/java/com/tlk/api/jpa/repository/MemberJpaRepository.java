package com.tlk.api.jpa.repository;

import com.tlk.api.jpa.MemberJpa;
import com.tlk.api.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberJpa, Integer> {
    MemberJpa findByLoginIdAndLoginPassword(String loginId, String loginPassword);

    Long countByLoginId(String loginId);

}
