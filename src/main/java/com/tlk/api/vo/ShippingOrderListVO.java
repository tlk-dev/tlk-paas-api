package com.tlk.api.vo;

import lombok.Data;

@Data
public class ShippingOrderListVO {

    private Long shippingOrderId;

    private String clientOrderCode;

    private Integer shippingOrderStatus;

    private String packagingCode;
}
