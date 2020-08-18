package com.tlk.api.repository;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.define.err.PaaSException;
import com.tlk.api.define.member.MemberTypeDefine;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.jpa.member.repository.MemberJpaRepository;
import com.tlk.api.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    private static final Logger logger = LoggerFactory.getLogger(MemberRepository.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    public ApiResultObjectDTO regMemberRepository(String loginId, String loginPassword, String memberType,
                                                  String memberName, String memberMobileNumber, String memberZipCode, String memberAddress,
                                                  String memberAddressDetail, String memberBirthday, String memberGender, String memberEmailAddress,
                                                  String deviceUuId, Integer osType, String pushToken, boolean isPush) {
        int code = PaasCodeDefine.OK;
        Integer memberId = null;
        //아이디 중복 체크
        Long memberCount = memberJpaRepository.countByLoginId(loginId);
        if (memberCount > 0L) {
            logger.error("memberCount ::" + memberCount);
            code = PaaSErrCode.CUSTOM_DUPLICATED_USER_ID.code();
        } else {
            //paas_member 저장
            memberId = memberService.regMember(loginId, loginPassword, memberType);
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
        }
        return new ApiResultObjectDTO(memberId, code);
    }
}
