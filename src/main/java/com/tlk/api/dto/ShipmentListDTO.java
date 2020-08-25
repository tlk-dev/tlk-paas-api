package com.tlk.api.dto;

import com.tlk.api.utils.DateUtils;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
public class ShipmentListDTO {

    private String requestDate;

    private String requestDay;

    private Integer shipmentCnt;

    private List<HashMap<String, Object>>shipmentList;

    public ShipmentListDTO(String requestDate, Integer shipmentCnt, List<HashMap<String, Object>>shipmentList) {
        this.requestDate = requestDate;
        this.requestDay = DateUtils.getDayOfWeek(requestDate);
        this.shipmentCnt = shipmentCnt;
        this.shipmentList = shipmentList;
    }

}
