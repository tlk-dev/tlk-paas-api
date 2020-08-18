package com.tlk.api.jpa.shipping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_shipping_gu")
public class ShippingGuJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingGuId;

    // 배송 구 이름
    private String shippingGuName;

    // 배송 시 인덱스
    private Integer shippingCityId;


}
