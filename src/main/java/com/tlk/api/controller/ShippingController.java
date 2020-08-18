package com.tlk.api.controller;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.dto.ApiResultListDTO;
import com.tlk.api.jpa.shipping.ShippingCityJpa;
import com.tlk.api.jpa.shipping.ShippingGuJpa;
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
            @ApiImplicitParam(name = "shippingCityId", value = "배송가능한 시 아이디(/cityList 아이디 값)", dataType = "int", paramType = "query", required = true)
    })
    public ResponseEntity<ApiResultListDTO> getShippingGuList(@RequestParam("shippingCityId") Integer shippingCityId) {
        List<ShippingGuJpa> guList = shippingService.getShippingGuList(shippingCityId);
        ApiResultListDTO resultListDTO = new ApiResultListDTO(
                guList, PaasCodeDefine.OK
        );
        return ResponseEntity.ok(resultListDTO);
    }
}
