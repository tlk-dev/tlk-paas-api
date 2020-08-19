package com.tlk.api.jpa.member.repository;

import com.tlk.api.jpa.member.MemberDetailJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailJpaRepository extends JpaRepository<MemberDetailJpa, Integer> {
    MemberDetailJpa findByMemberNameAndMemberMobileNumber(String memberName, String memberMobileNumber);
}
