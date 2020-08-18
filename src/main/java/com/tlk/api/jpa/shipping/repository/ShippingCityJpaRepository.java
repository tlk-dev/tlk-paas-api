package com.tlk.api.jpa.shipping.repository;

import com.tlk.api.jpa.shipping.ShippingCityJpa;
import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingCityJpaRepository extends JpaRepository<ShippingCityJpa, Integer> {
}
