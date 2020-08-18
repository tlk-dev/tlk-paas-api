package com.tlk.api.jpa.shipping.repository;

import com.tlk.api.jpa.shipping.ShippingGuJpa;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingGuJpaRepository extends JpaRepository<ShippingGuJpa, Integer> {
    List<ShippingGuJpa> findAllByShippingCityId(Integer shippingCityId, Sort sort);
}
