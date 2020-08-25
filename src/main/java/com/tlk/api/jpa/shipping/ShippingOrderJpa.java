package com.tlk.api.jpa.shipping;

import com.tlk.api.utils.DateUtils;
import com.tlk.api.utils.TlkPaasUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_shipping_order")
public class ShippingOrderJpa {

    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingOrderId;

    // 패키지 인덱스
    private Integer packagingId;

    // 자체 주문 코드
    private String paasOrderCode;

    // 배송요청 고객사 코드
    private String clientOrderCode;

    // 배송지 우편번호
    private String shippingZipCode;

    // 배송지 주소
    private String shippingAddress;

    // 배송지 상세
    private String shippingAddressDetail;

    // 배송 상태
    private Integer shippingOrderStatus;

    // 배송 구(강남구,...)
    private Integer shippingGuId;

    // 생성일
    private String createDate;

    // 택배 코드
    private String deliveryCode;

    // 주문자 연락처
    private String orderMobileNumber;

    // 주문자 이름
    private String orderUserName;

    // 주문일시
    private String orderDate;

    // 요청사항
    private String orderRequestComment;

    // 배송요청일
    private String shippingRequestDate;

    public ShippingOrderJpa regPrevShipment(Integer clientId, String clientOrderCode, String shippingZipCode, String shippingAddress,
                                            String shippingAddressDetail, Integer shippingGuId, String deliveryCode, String orderMobileNumber,
                                            String orderUserName, String orderDate, String orderRequestComment) {
        ShippingOrderJpa prevOrderJpa = new ShippingOrderJpa();
        prevOrderJpa.paasOrderCode = TlkPaasUtils.generateOrderCode(clientId);
        prevOrderJpa.clientOrderCode = clientOrderCode;
        prevOrderJpa.shippingZipCode = shippingZipCode;
        prevOrderJpa.shippingAddress = shippingAddress;
        prevOrderJpa.shippingAddressDetail = shippingAddressDetail;
        prevOrderJpa.shippingOrderStatus = 1;
        prevOrderJpa.shippingGuId = shippingGuId;
        prevOrderJpa.createDate = DateUtils.returnNow();
        prevOrderJpa.deliveryCode = deliveryCode;
        prevOrderJpa.orderMobileNumber = orderMobileNumber;
        prevOrderJpa.orderUserName = orderUserName;
        prevOrderJpa.orderDate = orderDate;
        prevOrderJpa.orderRequestComment = orderRequestComment;
        return prevOrderJpa;
    }

}
