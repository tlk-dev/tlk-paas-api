package com.tlk.api.jpa.packaging;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_package")
public class PackageJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 인덱스
    private Integer packageId;

    // 패키지 자산 코드
    private String packageCode;

    // EPD 고유 번호
    private String epdMacAddress;

    // 고객사 코드
    private String clientCode;

    // 출하 상태
    private Integer shipmentStatus;

    // 배터리상태
    private Integer batteryStatus;

    // 온도계 상태
    private Integer thermometerStatus;

    // 조도 상태
    private Integer illuminanceStatus;

    // EPD 상태
    private Integer epdStatus;

    // 패키지 종류
    private Integer packageType;

    // 담당자
    private Integer manageMemberId;

    //등록기기
    private String regDevice;

    // 생성일
    private String createDate;


}
