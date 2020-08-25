package com.tlk.api.define.shipping;


import com.tlk.api.define.member.MemberTypeDefine;

/**
 * 출고상태 정의
 */
public enum ShipmentStatusDefine {
    PREPARE(1, "출고대기"),
    COMPLETE(2, "출고완료");

    int shipmentStatus;

    String shipmentStatusName;

    ShipmentStatusDefine(int shipmentStatus, String shipmentStatusName) {
        this.shipmentStatus = shipmentStatus;
        this.shipmentStatusName = shipmentStatusName;
    }

    public static String getShipmentStatusName(int shipmentStatus) {
        for (ShipmentStatusDefine define : ShipmentStatusDefine.values()) {
            if (shipmentStatus == define.shipmentStatus) {
                return define.shipmentStatusName;
            }
        }
        return null;
    }
}
