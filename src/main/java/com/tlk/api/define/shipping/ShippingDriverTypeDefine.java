package com.tlk.api.define.shipping;

/**
 * 배송기사 종류 정의
 */
public enum ShippingDriverTypeDefine {
    DELIVERY(1, "배송"),
    COLLECTION(2, "회수"),
    ALL(3, "배송+회수");

    int shippingDriverType;

    String shippingDriverTypeName;

    ShippingDriverTypeDefine(int shippingDriverType, String shippingDriverTypeName) {
        this.shippingDriverType = shippingDriverType;
        this.shippingDriverTypeName = shippingDriverTypeName;
    }
}
