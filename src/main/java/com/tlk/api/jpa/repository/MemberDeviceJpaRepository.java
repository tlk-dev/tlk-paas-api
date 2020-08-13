package com.tlk.api.jpa.repository;

import com.tlk.api.jpa.MemberDetailJpa;
import com.tlk.api.jpa.MemberDeviceJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDeviceJpaRepository extends JpaRepository<MemberDeviceJpa, Integer> {

}
