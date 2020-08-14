package com.tlk.api.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_member_device")
public class MemberDeviceJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberPushId;

    // 회원 인덱스
    private Integer memberId;

    // uuId
    private String deviceUuId;

    // OS 종류(1: 안드, 2: IOS)
    private Integer osType;

    // 푸시 토큰
    private String pushToken;

    // 푸시 허용 여부
    private Boolean isPush;

    public MemberDeviceJpa(Integer memberId, String deviceUuId, Integer osType, String pushToken, boolean isPush) {
        this.memberId = memberId;
        this.deviceUuId = deviceUuId;
        this.osType = osType;
        this.pushToken = pushToken;
        this.isPush = isPush;
    }

}
