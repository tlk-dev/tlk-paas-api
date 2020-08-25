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
public class PackagingJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 인덱스
    private Integer packagingId;

    // 패키징 자산 코드
    private String packagingCode;

    // EPD 고유 번호
    private String epdMacAddress;

    // 고객사 정보 인덱스
    private Integer clientId;

    // 패키징 상태(정상, 이상)
    private Integer packagingStatus;

    // 입고 처리 상태(입고, 미입고)
    private Integer receiveStatus;

    // 회수 요청 상태(회수요청, 교환/반품)
    private Integer retrieveStatus;

    // 배터리상태
    private Integer batteryStatus;

    // 온도계 상태
    private Integer thermometerStatus;

    // 조도 상태
    private Integer illuminanceStatus;

    // 패키지 종류
    private Integer packageType;

    // 생성일
    private String createDate;

    // 백업여부
    private Boolean isBackup;

    // 초기화여부
    private Boolean isReset;

    // 담당자
    private Integer manageMemberId;

    // 출고일시
    private String shipmentDate;

    // 회수일시
    private String returnDate;

    // 초기화일시
    private String resetDate;

    // 배송정보 인덱스
    private Long shippingOrderId;


}
