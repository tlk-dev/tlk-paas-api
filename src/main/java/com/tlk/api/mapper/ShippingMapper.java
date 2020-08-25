package com.tlk.api.mapper;

import com.tlk.api.vo.MemberLoginVO;
import com.tlk.api.vo.ShippingOrderListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShippingMapper {

    Integer selectProductSumByShippingOrderId(@Param("shippingOrderId") Long shippingOrderId);

    List<ShippingOrderListVO> selectShippingOrderListByRequestDate(@Param("requestDate") String requestDate);
}
