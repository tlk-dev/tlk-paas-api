package com.tlk.api.jpa.member.repository;

import com.tlk.api.jpa.member.MemberJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberJpa, Integer> {
    MemberJpa findByLoginIdAndLoginPassword(String loginId, String loginPassword);

    Long countByLoginId(String loginId);

}
