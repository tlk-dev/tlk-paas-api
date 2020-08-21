package com.tlk.api.service;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.jpa.shipping.ShippingCityJpa;
import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import com.tlk.api.jpa.shipping.ShippingGuJpa;
import com.tlk.api.jpa.shipping.repository.ShippingCityJpaRepository;
import com.tlk.api.jpa.shipping.repository.ShippingDriverJpaRepository;
import com.tlk.api.jpa.shipping.repository.ShippingGuJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShippingService {

    @Autowired
    private ShippingDriverJpaRepository shippingDriverJpaRepository;

    @Autowired
    private ShippingCityJpaRepository shippingCityJpaRepository;

    @Autowired
    private ShippingGuJpaRepository shippingGuJpaRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public ApiResultObjectDTO regShippingDriver(Integer memberId, Integer shippingGuId, String bankName, String bankCode, Integer vehicleType,
                                     String vehicleNumber, Integer vehicleStatus, Integer shippingDriverType, String shippingStartTime,
                                     String shippingEndTime, boolean adminApproveYn, boolean shippingPossibleYn, String driverImgFile) {
        int code = PaasCodeDefine.OK;
        Integer shippingDriverId = null;
        if (memberId == null) {
            code = PaaSErrCode.CUSTOM_PARAM_ERROR_MEMBER_ID.code();
        } else {
            ShippingDriverJpa driverJpa = new ShippingDriverJpa(
                    memberId, shippingGuId, bankName, bankCode, vehicleType, vehicleNumber, vehicleStatus,
                    shippingDriverType, shippingStartTime, shippingEndTime, adminApproveYn, shippingPossibleYn, driverImgFile
            );
            shippingDriverJpaRepository.save(driverJpa);

            shippingDriverId = driverJpa.getShippingDriverId();
        }
        return new ApiResultObjectDTO(shippingDriverId, code) ;
    }

    @Transactional(readOnly = true)
    public ShippingDriverJpa getShippingDriverInfo(Integer memberId) {
        ShippingDriverJpa shippingDriverInfo = new ShippingDriverJpa();
        if (memberId > 0L) {
            shippingDriverInfo = shippingDriverJpaRepository.findByMemberId(memberId);
        }
        return shippingDriverInfo;
    }

    @Transactional(readOnly = true)
    public List<ShippingCityJpa> getShippingCityList() {
        List<ShippingCityJpa> cityList = shippingCityJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "shippingCityName"));
        return cityList;
    }

    @Transactional(readOnly = true)
    public List<ShippingGuJpa> getShippingGuList(Integer shippingCityId) {
        List<ShippingGuJpa> guList = shippingGuJpaRepository.findAllByShippingCityId(shippingCityId, Sort.by(Sort.Direction.ASC, "shippingGuName"));
        return guList;
    }
}
