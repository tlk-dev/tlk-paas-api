package com.tlk.api.jpa.shipping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_shipping_order_product")
public class ShippingOrderProductJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 인덱스
    private Long shippingOrderProductId;

    // 배송 정보 인덱스
    private Long shippingOrderId;

    // 상품명
    private String productName;

    // 상품 개수
    private Integer productCount;

    // 상품 이미지 경로
    private String productImgUrl;

    public ShippingOrderProductJpa genTestData(Long shippingOrderId, String productName, String productImgUrl) {
        ShippingOrderProductJpa testProductJpa = new ShippingOrderProductJpa();
        testProductJpa.shippingOrderId = shippingOrderId;
        testProductJpa.productName = productName;
        testProductJpa.productCount = 1;
        testProductJpa.productImgUrl = productImgUrl;

        return testProductJpa;
    }

}
