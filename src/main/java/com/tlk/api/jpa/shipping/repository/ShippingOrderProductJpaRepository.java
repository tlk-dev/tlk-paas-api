package com.tlk.api.jpa.shipping.repository;

import com.tlk.api.jpa.shipping.ShippingOrderJpa;
import com.tlk.api.jpa.shipping.ShippingOrderProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingOrderProductJpaRepository extends JpaRepository<ShippingOrderProductJpa, Integer> {
}
