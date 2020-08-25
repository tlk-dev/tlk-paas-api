package com.tlk.api;

import com.tlk.api.dto.ShipmentListDTO;
import com.tlk.api.jpa.shipping.ShippingOrderJpa;
import com.tlk.api.jpa.shipping.ShippingOrderProductJpa;
import com.tlk.api.service.ShippingService;
import com.tlk.api.vo.ShippingOrderListVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShippingServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ShippingServiceTest.class);

    @Autowired
    private ShippingService shippingService;

    @Test
    public void getShippingOrderListTest() {
        List<ShippingOrderListVO> list = shippingService.getShippingOrderList("2020-11-01");
        for (ShippingOrderListVO jpa : list) {
            logger.info(">> " + jpa.getShippingOrderId());
        }
    }

    @Test
    public void getShippingOrderCount() {
//        int jpaCnt = shippingService.getShippingOrderCount("2020-11-01");
//        logger.info("count >> " + jpaCnt);
    }
}
