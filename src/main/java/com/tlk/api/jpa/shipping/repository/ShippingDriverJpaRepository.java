package com.tlk.api.jpa.shipping.repository;

import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingDriverJpaRepository extends JpaRepository<ShippingDriverJpa, Integer> {
    ShippingDriverJpa findByMemberId(Integer memberId);
}
