package com.tlk.api.jpa.shipping;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_shipping_driver")
public class ShippingDriverJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingDriverId;

    private Integer memberId;

    // 배송 구 아이디
    private Integer shippingGuId;

    // 은행명
    private String bankName;

    // 은행 계좌번호
    private String bankAccount;

    // 차량 종류(1,2,3,...)
    private Integer vehicleType;

    // 차량 번호
    private String vehicleNumber;

    // 차량 상태
    private Integer vehicleStatus;

    // 배송기사 종류(1,2,3)
    private Integer shippingDriverType;

    // 배송 가능 시작 시간
    private String shippingStartTime;

    // 배송 가능 종료 시간
    private String shippingEndTime = PaasCodeDefine.DEFAULT_TIME;

    // 관리자 승인 여부
    private Boolean adminApproveYn;

    // 배송 가능 여부
    private Boolean shippingPossibleYn;

    public ShippingDriverJpa(Integer memberId, Integer shippingGuId, String bankName, String bankAccount, Integer vehicleType,
                             String vehicleNumber, Integer vehicleStatus, Integer shippingDriverType, String shippingStartTime,
                             String shippingEndTime, Boolean adminApproveYn, Boolean shippingPossibleYn) {
        this.memberId = memberId;
        this.shippingGuId = shippingGuId;
        this.bankName = bankName;
        this.bankAccount = bankAccount;
        this.vehicleType = vehicleType;
        this.vehicleNumber = vehicleNumber;
        this.vehicleStatus = vehicleStatus;
        this.shippingDriverType = shippingDriverType;
        this.shippingStartTime = StringUtils.isNullValue(shippingStartTime, PaasCodeDefine.DEFAULT_TIME);
        this.shippingEndTime = StringUtils.isNullValue(shippingEndTime, PaasCodeDefine.DEFAULT_TIME);
        this.adminApproveYn = adminApproveYn;
        this.shippingPossibleYn = shippingPossibleYn;
    }

}
