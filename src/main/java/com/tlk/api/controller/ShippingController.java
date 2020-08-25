package com.tlk.api.controller;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.dto.ApiResultListDTO;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.dto.ShipmentListDTO;
import com.tlk.api.jpa.shipping.ShippingCityJpa;
import com.tlk.api.jpa.shipping.ShippingGuJpa;
import com.tlk.api.repository.ShippingRepository;
import com.tlk.api.service.ShippingService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shipping")
public class ShippingController {

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private ShippingRepository shippingRepository;

    @RequestMapping(value = "/cityList", method = RequestMethod.GET)
    @ApiOperation("배송가능한 시 가져오기")
    public ResponseEntity<ApiResultListDTO> getShippingCityList() {
        List<ShippingCityJpa> cityList = shippingService.getShippingCityList();
        ApiResultListDTO resultListDTO = new ApiResultListDTO(
                cityList, PaasCodeDefine.OK
        );
        return ResponseEntity.ok(resultListDTO);
    }

    @RequestMapping(value = "/guList", method = RequestMethod.GET)
    @ApiOperation("배송가능한 구 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shippingCityId", value = "배송가능한 시 아이디(/shipping/cityList 아이디 값)", dataType = "int", paramType = "query", required = true)
    })
    public ResponseEntity<ApiResultListDTO> getShippingGuList(@RequestParam("shippingCityId") Integer shippingCityId) {
        List<ShippingGuJpa> guList = shippingService.getShippingGuList(shippingCityId);
        ApiResultListDTO resultListDTO = new ApiResultListDTO(
                guList, PaasCodeDefine.OK
        );
        return ResponseEntity.ok(resultListDTO);
    }

    @RequestMapping(value = "/getShipmentManage", method = RequestMethod.GET)
    @ApiOperation("출고관리")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestDate", value = "출고요청일(YYYY-MM-DD)", dataType = "string", paramType = "query", required = true)
    })
    public ResponseEntity<ApiResultObjectDTO> getShipmentManage(@RequestParam("requestDate") String requestDate) {
        ApiResultObjectDTO resultObjectDTO = shippingRepository.getShipmentList(requestDate);
        return ResponseEntity.ok(resultObjectDTO);
    }

    @RequestMapping(value = "/regTestOrderData", method = RequestMethod.POST)
    @ApiOperation("테스트용 주문 데이터 생성")
    public ResponseEntity regTestOrderData() throws Exception {

        Long shippingOrderId = shippingService.regTestShipmentOrder(1, "C20200812132411", "12345", "서울시 강남구 강남대로464", "3층 320호",
                1, "109123090918230", "01062585228", "안지호", "2020-08-22 12:00:00", "부재시 연락");
        if (shippingOrderId > 0L) {
            shippingService.regTestShipmentOrderProduct(shippingOrderId, "", "");
        }
        Thread.sleep(1000);
        Long shippingOrderId2 = shippingService.regTestShipmentOrder(1, "C20200812132422", "12345", "서울시 강남구 강남대로465", "3층 310호",
                1, "109123090918231","01029854636", "박지선", "2020-08-22 13:14:22", "부재시 문앞");
        if (shippingOrderId2 > 0L) {
            shippingService.regTestShipmentOrderProduct(shippingOrderId2, "", "");
        }
        return ResponseEntity.ok(shippingOrderId);
    }
}
