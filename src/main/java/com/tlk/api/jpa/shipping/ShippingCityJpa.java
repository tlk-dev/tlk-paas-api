package com.tlk.api.jpa.shipping;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_shipping_city")
public class ShippingCityJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shippingCityId;

    // 배송 시 이름
    private String shippingCityName;
}
