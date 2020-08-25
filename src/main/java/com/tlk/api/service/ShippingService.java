package com.tlk.api.service;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.jpa.shipping.*;
import com.tlk.api.jpa.shipping.repository.*;
import com.tlk.api.mapper.ShippingMapper;
import com.tlk.api.vo.ShippingOrderListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShippingService {

    @Autowired
    private ShippingDriverJpaRepository shippingDriverJpaRepository;

    @Autowired
    private ShippingCityJpaRepository shippingCityJpaRepository;

    @Autowired
    private ShippingGuJpaRepository shippingGuJpaRepository;

    @Autowired
    private ShippingOrderJpaRepository shippingOrderJpaRepository;

    @Autowired
    private ShippingOrderProductJpaRepository shippingOrderProductJpaRepository;

    @Autowired
    private ShippingMapper shippingMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public ApiResultObjectDTO regShippingDriver(Integer memberId, Integer shippingGuId, String bankName, String bankCode, Integer vehicleType,
                                     String vehicleNumber, Integer vehicleStatus, Integer shippingDriverType, String shippingStartTime,
                                     String shippingEndTime, boolean adminApproveYn, boolean shippingPossibleYn, String driverImgFile) {
        int code = PaasCodeDefine.OK;
        ShippingDriverJpa driverJpa = new ShippingDriverJpa();
        if (memberId == null) {
            code = PaaSErrCode.CUSTOM_PARAM_ERROR_MEMBER_ID.code();
        } else {
            driverJpa = new ShippingDriverJpa(
                    memberId, shippingGuId, bankName, bankCode, vehicleType, vehicleNumber, vehicleStatus,
                    shippingDriverType, shippingStartTime, shippingEndTime, adminApproveYn, shippingPossibleYn, driverImgFile
            );
            shippingDriverJpaRepository.save(driverJpa);
        }
        return new ApiResultObjectDTO(driverJpa, code) ;
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

    @Transactional(readOnly = true)
    public List<ShippingOrderListVO> getShippingOrderList(String requestDate) {
        return shippingMapper.selectShippingOrderListByRequestDate(requestDate);
//        return shippingOrderJpaRepository.findAllByShippingRequestDate(
//                requestDate, Sort.by(Sort.Direction.DESC, "shippingOrderId")
//        );
    }

    @Transactional(readOnly = true)
    public Integer getShippingOrderCount(String requestDate) {
        Long shippingCnt = shippingOrderJpaRepository.countByShippingRequestDate(requestDate);
        return shippingCnt.intValue();
    }

    @Transactional(readOnly = true)
    public Integer getShippingProductCount(Long shippingOrderId) {
        return shippingMapper.selectProductSumByShippingOrderId(shippingOrderId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Long regTestShipmentOrder(Integer clientId, String clientOrderCode, String shippingZipCode, String shippingAddress,
                                        String shippingAddressDetail, Integer shippingGuId, String deliveryCode, String orderMobileNumber,
                                     String orderUserName, String orderDate, String orderRequestComment) {
        ShippingOrderJpa orderJpa = new ShippingOrderJpa().regPrevShipment(
            clientId, clientOrderCode, shippingZipCode, shippingAddress, shippingAddressDetail, shippingGuId, deliveryCode,
            orderMobileNumber, orderUserName, orderDate, orderRequestComment
        );
        shippingOrderJpaRepository.save(orderJpa);
        return orderJpa.getShippingOrderId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void regTestShipmentOrderProduct(Long shippingOrderId, String productName, String productImgUrl) {
        if (shippingOrderId != null) {
            List<Map<String, String>>testDataArr = getTestOrderProductData();
            for (int i=0; i<testDataArr.size(); i++) {
                Map resultMap = testDataArr.get(i);
                ShippingOrderProductJpa testProductJpa = new ShippingOrderProductJpa().genTestData(
                    shippingOrderId, resultMap.get("productName").toString(), resultMap.get("productImgUrl").toString()
                );
                shippingOrderProductJpaRepository.save(testProductJpa);
            }
        }
    }

    /**
     * 테스트용 상품 목록 생성
     * @return
     */
    private List<Map<String, String>> getTestOrderProductData() {
        String productImgUrl[] = {
                "http://item.ssgcdn.com/99/11/91/item/1000028911199_i1_232.jpg",
                "http://item.ssgcdn.com/05/98/33/item/1000040339805_i1_232.jpg",
                "http://item.ssgcdn.com/03/54/82/item/2097000825403_i1_232.jpg",
                "http://item.ssgcdn.com/40/90/16/item/1000027169040_i1_232.jpg",
                "http://item.ssgcdn.com/94/53/26/item/1000045265394_i1_232.jpg"
        };
        String productName[] = {
                "CJ 비비고 갈비탕 400g", "CJ 비비고 갈비탕 400g", "유명산지 아삭한복숭아 1.5KG 내외(박스)",
                "[펩시]콜라 (250ml*6캔)", "CJ 비비고 감자탕 460g"
        };
        List<Map<String, String>>arr = new ArrayList<>();
        for (int i=0; i<productImgUrl.length; i++) {
            Map<String, String>map = new HashMap<>();
            map.put("productName", productName[i]);
            map.put("productImgUrl", productImgUrl[i]);

            arr.add(map);
        }
        return arr;
    }
}
