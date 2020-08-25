package com.tlk.api.jpa.shipping.repository;

import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import com.tlk.api.jpa.shipping.ShippingOrderJpa;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingOrderJpaRepository extends JpaRepository<ShippingOrderJpa, Integer> {
    List<ShippingOrderJpa>findAllByShippingRequestDate(String shippingRequestDate, Sort sort);
    Long countByShippingRequestDate(String shippingRequestDate);
}
