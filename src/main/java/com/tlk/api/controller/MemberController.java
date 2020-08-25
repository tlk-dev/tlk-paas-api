package com.tlk.api.controller;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.member.MemberTypeDefine;
import com.tlk.api.dto.ApiResultListDTO;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.repository.MemberRepository;
import com.tlk.api.service.FileUploadService;
import com.tlk.api.service.MemberService;
import com.tlk.api.service.ShippingService;
import com.tlk.api.utils.JsonBuilder;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/api/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private FileUploadService fileUploadService;

    @Autowired
    private MemberRepository memberRepository;

    @RequestMapping(value = "/regMember", method = RequestMethod.POST)
    @ApiOperation("회원 가입")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "아이디", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginPassword", value = "패스워드", dataType = "string", paramType = "query", required = true),
            //@ApiImplicitParam(name = "memberType", value = "회원종류(관리자:ADMIN, 사용자:USER, 배송기사:DELIVER, 오퍼레이터:OPERATOR)", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "isUser", value = "사용자 여부", dataType = "boolean", paramType = "query", required = true, defaultValue = "false"),
            @ApiImplicitParam(name = "isDeliver", value = "배송기사 여부", dataType = "boolean", paramType = "query", required = true, defaultValue = "false"),
            @ApiImplicitParam(name = "isOperator", value = "오퍼레이터 여부", dataType = "boolean", paramType = "query", required = true, defaultValue = "false"),
            @ApiImplicitParam(name = "memberName", value = "이름", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberMobileNumber", value = "핸드폰번호(01012341234)", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberZipCode", value = "우편번호(12345)", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberAddress", value = "주소", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberAddressDetail", value = "주소 상세", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberBirthday", value = "생년월일(YYYY-MM-DD)", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberGender", value = "성별(M:남, F:여)", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "memberEmailAddress", value = "이메일 주소", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "deviceUuId", value = "기기 UUID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "osType", value = "os종류(1: 안드, 2: IOS)", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "pushToken", value = "푸시 토큰 값", dataType = "string", paramType = "query", required = false),
            @ApiImplicitParam(name = "isPush", value = "푸시허용여부(false : 비허용, true : 허용)", dataType = "boolean", paramType = "query", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 900, message = "CUSTOM :: 아이디 중복")
    })
    public ResponseEntity<ApiResultObjectDTO> regMember(@RequestParam(value = "loginId") String loginId,
                                                        @RequestParam(value = "loginPassword") String loginPassword,
                                                        //@RequestParam(value = "memberType") String memberType,
                                                        @RequestParam(value = "isUser") boolean isUser,
                                                        @RequestParam(value = "isDeliver") boolean isDeliver,
                                                        @RequestParam(value = "isOperator") boolean isOperator,
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

        ApiResultObjectDTO resultObjectDTO = memberRepository.regMemberRepository(
                loginId, loginPassword, isUser, isDeliver, isOperator, memberName, memberMobileNumber, memberZipCode, memberAddress,
                memberAddressDetail, memberBirthday, memberGender, memberEmailAddress, deviceUuId, osType, pushToken, isPush
        );
        return ResponseEntity.ok(resultObjectDTO);
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
            @ApiImplicitParam(name = "driverImgFile", value = "배송기사 증명사진 파일", dataType = "file", paramType = "form", required = false)
    })
    @ApiResponses(value = {
        @ApiResponse(code = 800, message = "PARAM :: MEMBER_ID 없음")
    })
    public ResponseEntity<ApiResultObjectDTO> regShippingDriverInfo(
            @RequestParam(value = "memberId") Integer memberId,
            @RequestParam(value = "shippingGuId") Integer shippingGuId,
            @RequestParam(value = "bankName", required = false) String bankName,
            @RequestParam(value = "bankAccount", required = false) String bankAccount,
            @RequestParam(value = "vehicleType") Integer vehicleType,
            @RequestParam(value = "vehicleNumber") String vehicleNumber,
            @RequestParam(value = "vehicleStatus") Integer vehicleStatus,
            @RequestParam(value = "shippingDriverType") Integer shippingDriverType,
            @RequestParam(value = "shippingStartTime") String shippingStartTime,
            @RequestParam(value = "shippingEndTime") String shippingEndTime,
            MultipartHttpServletRequest request) {
        ApiResultObjectDTO result = new ApiResultObjectDTO();
        if (memberId > 0) {
            String driverImgFile = fileUploadService.uploadSingleFile(request);

            result = shippingService.regShippingDriver(
                    memberId, shippingGuId, bankName, bankAccount, vehicleType, vehicleNumber, vehicleStatus,
                    shippingDriverType, shippingStartTime, shippingEndTime, false, false, driverImgFile
            );
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation("회원 로그인")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginId", value = "아이디", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "loginPassword", value = "패스워드", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberType", value = "회원종류(사용자:USER, 배송기사:DELIVER, 오퍼레이터:OPERATOR)", dataType = "string", paramType = "query", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 901, message = "CUSTOM :: 로그인 에러(아이디 또는 비밀번호가 틀림)"),
            @ApiResponse(code = 902, message = "CUSTOM :: 관리자 승인하지 않은 배송기사")
    })
    public ResponseEntity<ApiResultObjectDTO> getLoginMember(@RequestParam(value = "loginId") String loginId,
                                    @RequestParam(value = "loginPassword") String loginPassword,
                                    @RequestParam(value = "memberType") String memberType) {
        return ResponseEntity.ok(memberService.getLoginMember(loginId, loginPassword, memberType));
    }

    @RequestMapping(value = "/findLoginId", method = RequestMethod.GET)
    @ApiOperation("회원 아이디 찾기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberName", value = "회원 이름", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "memberMobileNumber", value = "회원 핸드폰 번호", dataType = "string", paramType = "query", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(code = 903, message = "CUSTOM :: 사용자가 없음")
    })
    public ResponseEntity<ApiResultObjectDTO> findLoginId(@RequestParam(value = "memberName") String memberName,
                                                             @RequestParam(value = "memberMobileNumber") String memberMobileNumber) {
        return ResponseEntity.ok(memberService.getLoginId(memberName, memberMobileNumber));
    }

    @RequestMapping(value = "/modifyPassword", method = RequestMethod.PUT)
    @ApiOperation("회원 비밀번호 변경")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "회원 아이디", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "newPassword", value = "변경될 새로운 비밀번호", dataType = "string", paramType = "query", required = true)
    })
    public ResponseEntity<ApiResultObjectDTO> modifyMemberPassword(@RequestParam(value = "memberId") Integer memberId,
                                                                   @RequestParam(value = "newPassword") String newPassword) {
        memberService.modifyMemberPassword(memberId, newPassword);
        ApiResultObjectDTO resultObjectDTO = new ApiResultObjectDTO(memberId, PaasCodeDefine.OK);
        return ResponseEntity.ok(resultObjectDTO);
    }

}
