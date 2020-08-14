package com.tlk.api.jpa.member.repository;

import com.tlk.api.jpa.member.MemberPointJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPointJpaRepository extends JpaRepository<MemberPointJpa, Integer> {
}
