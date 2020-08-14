package com.tlk.api.define.shipping;

/**
 * 차량 상태 정의
 */
public enum VehicleStatusDefine {
    NORMAL(1, "정상"),
    ACCIDENT(2, "사고");

    int vehicleStatus;

    String vehicleStatusName;

    VehicleStatusDefine(int vehicleStatus, String vehicleStatusName) {
        this.vehicleStatus = vehicleStatus;
        this.vehicleStatusName = vehicleStatusName;
    }
}
