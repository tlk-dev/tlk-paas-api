package com.tlk.api.define.shipping;

/**
 * 차량 종류 정의
 */
public enum VehicleTypeDefine {
    BIKE(1, "2륜차"),
    CAR(2, "승용차"),
    TRUCK(3, "화물차");

    int vehicleType;

    String vehicleTypeName;

    VehicleTypeDefine(int vehicleType, String vehicleTypeName) {
        this.vehicleType = vehicleType;
        this.vehicleTypeName = vehicleTypeName;
    }
}
