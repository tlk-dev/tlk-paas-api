package com.tlk.api.controller;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.member.MemberTypeDefine;
import com.tlk.api.service.MemberService;
import com.tlk.api.service.ShippingService;
import com.tlk.api.utils.JsonBuilder;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShippingService shippingService;

    @RequestMapping(value = "/regMember", method = RequestMethod.POST)
    @ApiOperation("회원 가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "아이디", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginPassword", value = "패스워드", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberType", value = "회원종류(관리자:ADMIN, 사용자:USER, 배송기사:DELIVER, 오퍼레이터:OPERATOR", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberName", value = "이름", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberMobileNumber", value = "핸드폰번호(01012341234)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberZipCode", value = "우편번호(12345)", dataType = "int", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberAddress", value = "주소", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberAddressDetail", value = "주소 상세", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberBirthday", value = "생년월일(YYYY-MM-DD)", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberGender", value = "성별(M:남, F:여)", dataType = "int", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberEmailAddress", value = "이메일 주소", dataType = "int", paramType = "query", required = false),
            @ApiImplicitParam(name = "deviceUuId", value = "기기 UUID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "osType", value = "os종류(1: 안드, 2: IOS)", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "pushToken", value = "푸시 토큰 값", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "isPush", value = "푸시허용여부(false : 비허용, true : 허용)", dataType = "boolean", paramType = "query", required = true)
    })
    public @ResponseBody String regMember(@RequestParam(value = "loginId") String loginId,
                                          @RequestParam(value = "loginPassword") String loginPassword,
                                          @RequestParam(value = "memberType") String memberType,
                                          @RequestParam(value = "memberName") String memberName,
                                          @RequestParam(value = "memberMobileNumber") String memberMobileNumber,
                                          @RequestParam(value = "memberZipCode", required = false) String memberZipCode,
                                          @RequestParam(value = "memberAddress", required = false) String memberAddress,
                                          @RequestParam(value = "memberAddressDetail", required = false) String memberAddressDetail,
                                          @RequestParam(value = "memberBirthday", required = false) String memberBirthday,
                                          @RequestParam(value = "memberGender", required = false) String memberGender,
                                          @RequestParam(value = "memberEmailAddress") String memberEmailAddress,
                                          @RequestParam(value = "deviceUuId") String deviceUuId,
                                          @RequestParam(value = "osType") Integer osType,
                                          @RequestParam(value = "pushToken", required = false) String pushToken,
                                          @RequestParam(value = "isPush") boolean isPush) {
        Integer memberId = memberService.regMember(loginId, loginPassword, memberType);
        if (memberId > 0) {
            memberService.regMemberDetail(
                    memberId, memberName, memberMobileNumber, memberZipCode, memberAddress,
                    memberAddressDetail, memberBirthday, memberGender, memberEmailAddress
            );
            memberService.regMemberDevice(memberId, deviceUuId, osType, pushToken, isPush);
            //사용자 일때만 포인트 등록
            if (MemberTypeDefine.getMemberType(memberType) == 2) {
                memberService.regMemberPoint(memberId, 0);
            }
        }
        return new JsonBuilder().add(PaasCodeDefine.RESULT, memberId).build();
    }

    @RequestMapping(value = "/regShippingDriver", method = RequestMethod.POST)
    @ApiOperation("배송기사 정보 등록")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "회원 인덱스(/regMember 에서 나온 리턴 값)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "shippingGuId", value = "구 아이디", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "bankName", value = "은행명", dataType = "String", paramType = "query", required = false),
            @ApiImplicitParam(name = "bankAccount", value = "계좌번호", dataType = "int", paramType = "query", required = false),
            @ApiImplicitParam(name = "vehicleType", value = "차량 종류(1:이륜차, 2:승용차, 3:화물차)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "vehicleNumber", value = "차량 번호", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "vehicleStatus", value = "차량 상태(1:정상, 2:사고)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "shippingDriverType", value = "배송기사 종류(1:배송, 2:회수, 3:배송+회수)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "shippingStartTime", value = "배송 시작 시간(HH:MM)", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "shippingEndTime", value = "배송 마감 시간(HH:MM)", dataType = "string", paramType = "query", required = true),
    })
    public @ResponseBody String regShippingDriverInfo(
                                        @RequestParam(value = "memberId") Integer memberId,
                                        @RequestParam(value = "shippingGuId") Integer shippingGuId,
                                        @RequestParam(value = "bankName", required = false) String bankName,
                                        @RequestParam(value = "bankAccount", required = false) String bankAccount,
                                        @RequestParam(value = "vehicleType") Integer vehicleType,
                                        @RequestParam(value = "vehicleNumber") String vehicleNumber,
                                        @RequestParam(value = "vehicleStatus") Integer vehicleStatus,
                                        @RequestParam(value = "shippingDriverType") Integer shippingDriverType,
                                        @RequestParam(value = "shippingStartTime") String shippingStartTime,
                                        @RequestParam(value = "shippingEndTime") String shippingEndTime) {
        if (memberId > 0) {
            shippingService.regShippingDriver(
                    memberId, shippingGuId, bankName, bankAccount, vehicleType, vehicleNumber, vehicleStatus,
                    shippingDriverType, shippingStartTime, shippingEndTime, false, false
            );
        }
        return new JsonBuilder().add(PaasCodeDefine.RESULT, memberId).build();
    }

    @RequestMapping(value = "/getMemberInfo", method = RequestMethod.GET)
    @ApiOperation("")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "아이디", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginPassword", value = "패스워드", dataType = "string", paramType = "query", required = true)
    })
    public ResponseEntity getMember(@RequestParam(value = "loginId") String loginId,
                                    @RequestParam(value = "loginPassword") String loginPassword) {
        return ResponseEntity.ok(memberService.getMember(loginId, loginPassword));
    }

}
