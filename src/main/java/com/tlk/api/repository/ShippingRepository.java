package com.tlk.api.repository;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.define.shipping.ShipmentStatusDefine;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.dto.ShipmentListDTO;
import com.tlk.api.jpa.shipping.ShippingOrderJpa;
import com.tlk.api.jpa.shipping.repository.ShippingOrderJpaRepository;
import com.tlk.api.service.ShippingService;
import com.tlk.api.vo.ShippingOrderListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class ShippingRepository {

    @Autowired
    private ShippingService shippingService;

    /**
     * 출고관리 리스트
     * @param requestDate 요청일(YYYY-MM-DD)
     * @return
     */
    public ApiResultObjectDTO getShipmentList(String requestDate) {
        int resultCode = PaasCodeDefine.OK;

        ShipmentListDTO dto = null;
        Integer shippingOrderCount = 0;
        List<HashMap<String, Object>> shipmentList = new ArrayList<>();

        if (!"".equals(requestDate)) {
            List<ShippingOrderListVO>shippingOrderList = shippingService.getShippingOrderList(requestDate);
            if (shippingOrderList.size() > 0) {
                shippingOrderCount = shippingService.getShippingOrderCount(requestDate);
                for (ShippingOrderListVO vo : shippingOrderList) {
                    HashMap<String, Object>shipmentInfo = new HashMap<>();
                    shipmentInfo.put("shippingOrderId", vo.getShippingOrderId());
                    shipmentInfo.put("orderCode", vo.getClientOrderCode());
                    shipmentInfo.put("packagingCode", vo.getPackagingCode());
                    shipmentInfo.put("orderCnt", shippingService.getShippingProductCount(vo.getShippingOrderId()));
                    shipmentInfo.put("statusCode", vo.getShippingOrderStatus());
                    shipmentInfo.put("status", ShipmentStatusDefine.getShipmentStatusName(vo.getShippingOrderStatus()));

                    shipmentList.add(shipmentInfo);
                }
            }
            dto = new ShipmentListDTO(
                requestDate, shippingOrderCount, shipmentList
            );
        } else {
            resultCode = PaaSErrCode.CUSTOM_PARAM_ERROR_REQUEST_DATE.code();
        }
        return new ApiResultObjectDTO(dto, resultCode);
    }
}
