package com.tlk.api.service;

import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import com.tlk.api.jpa.shipping.repository.ShippingDriverJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShippingService {

    @Autowired
    private ShippingDriverJpaRepository shippingDriverJpaRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer regShippingDriver(Integer memberId, Integer shippingGuId, String bankName, String bankCode, Integer vehicleType,
                                     String vehicleNumber, Integer vehicleStatus, Integer shippingDriverType, String shippingStartTime,
                                     String shippingEndTime, boolean adminApproveYn, boolean shippingPossibleYn) {
        ShippingDriverJpa driverJpa = new ShippingDriverJpa(
                memberId, shippingGuId, bankName, bankCode, vehicleType, vehicleNumber, vehicleStatus,
                shippingDriverType, shippingStartTime, shippingEndTime, adminApproveYn, shippingPossibleYn
        );
        shippingDriverJpaRepository.save(driverJpa);
        return driverJpa.getShippingDriverId();
    }
}
