package com.tlk.api.controller;

import com.tlk.api.service.MemberService;
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

    @RequestMapping(value = "/reg", method = RequestMethod.POST)
    @ApiOperation("회원 가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "아이디", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginPassword", value = "패스워드", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberType", value = "회원종류", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberName", value = "이름", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberMobileNumber", value = "핸드폰번호", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberZipCode", value = "우편번호", dataType = "int", paramType = "query", required = false),
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
                                          @RequestParam(value = "isPush") boolean isPush
                                          ) {
        Integer memberId = memberService.regMember(loginId, loginPassword, memberType);
        if (memberId > 0) {
            memberService.regMemberDetail(
                    memberId, memberName, memberMobileNumber, memberZipCode, memberAddress,
                    memberAddressDetail, memberBirthday, memberGender, memberEmailAddress
            );
            memberService.regMemberDevice(memberId, deviceUuId, osType, pushToken, isPush);
        }
        return new JsonBuilder().add("result", memberId).build();
    }
}
