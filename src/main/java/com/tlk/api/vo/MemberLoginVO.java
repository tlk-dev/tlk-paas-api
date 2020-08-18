package com.tlk.api.vo;

import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import lombok.Data;

@Data
public class MemberLoginVO {

    private Integer memberId;

    private Integer memberType;

    private String memberName;

    private String memberMobileNumber;

    private String memberEmailAddress;

    private String deviceUuId;

    private Integer osType;

    private String pushToken;

    private boolean isPush;

    private ShippingDriverJpa shippingDriverInfo;
}
