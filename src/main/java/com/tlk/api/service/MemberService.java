package com.tlk.api.service;

import com.tlk.api.define.PaasCodeDefine;
import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.define.err.PaaSException;
import com.tlk.api.define.member.MemberTypeDefine;
import com.tlk.api.dto.ApiResultObjectDTO;
import com.tlk.api.jpa.member.MemberDetailJpa;
import com.tlk.api.jpa.member.MemberDeviceJpa;
import com.tlk.api.jpa.member.MemberJpa;
import com.tlk.api.jpa.member.MemberPointJpa;
import com.tlk.api.jpa.member.inter.MemberJpaFindIdInter;
import com.tlk.api.jpa.member.repository.MemberDetailJpaRepository;
import com.tlk.api.jpa.member.repository.MemberDeviceJpaRepository;
import com.tlk.api.jpa.member.repository.MemberJpaRepository;
import com.tlk.api.jpa.member.repository.MemberPointJpaRepository;
import com.tlk.api.jpa.shipping.ShippingDriverJpa;
import com.tlk.api.mapper.MemberMapper;
import com.tlk.api.utils.SecurityUtil;
import com.tlk.api.utils.StringUtils;
import com.tlk.api.vo.MemberLoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private MemberDetailJpaRepository memberDetailJpaRepository;

    @Autowired
    private MemberDeviceJpaRepository memberDeviceJpaRepository;

    @Autowired
    private MemberPointJpaRepository memberPointJpaRepository;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private MemberMapper memberMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer regMember(String loginId, String loginPassword, boolean isUser, boolean isDeliver, boolean isOperator) {
        MemberJpa memberJpa = new MemberJpa(loginId, loginPassword, isUser, isDeliver, isOperator);
        memberJpaRepository.save(memberJpa);
        return memberJpa.getMemberId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer regMemberDetail(Integer memberId, String memberName, String memberMobileNumber, String memberZipCode, String memberAddress,
                                   String memberAddressDetail, String memberBirthday, String memberGender, String memberEmailAddress) {
        MemberDetailJpa memberDetailJpa = new MemberDetailJpa(
                memberId, memberName, memberMobileNumber, memberZipCode, memberAddress, memberAddressDetail, memberBirthday, memberGender, memberEmailAddress
        );
        memberDetailJpaRepository.save(memberDetailJpa);
        return memberDetailJpa.getMemberDetailId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer regMemberDevice(Integer memberId, String deviceUuId, Integer osType, String pushToken, boolean isPush) {
        MemberDeviceJpa deviceJpa = new MemberDeviceJpa(
                memberId, deviceUuId, osType, pushToken, isPush
        );
        memberDeviceJpaRepository.save(deviceJpa);
        return deviceJpa.getMemberPushId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void regMemberPoint(Integer memberId, Integer point) {
        MemberPointJpa pointJpa = new MemberPointJpa(memberId, String.valueOf(point));
        memberPointJpaRepository.save(pointJpa);
    }

    @Transactional(readOnly = true)
    public MemberJpa getMember(String loginId, String loginPassword) {
        MemberJpa memberInfo = memberJpaRepository.findByLoginIdAndLoginPassword(
                loginId, SecurityUtil.encryptSHA256(loginPassword)
        );
        return memberInfo;
    }

    @Transactional(readOnly = true)
    public ApiResultObjectDTO getLoginMember(String loginId, String loginPassword, String memberType) {
        int resultCode = PaasCodeDefine.OK;
        MemberLoginVO memberLogin = memberMapper.selectMemberAtLogin(
                loginId, SecurityUtil.encryptSHA256(loginPassword), MemberTypeDefine.getMemberType(memberType)
        );
        if (memberLogin == null) {
            resultCode = PaaSErrCode.CUSTOM_LOGIN_ERROR.code();
        } else {
            //배송기사일때
            if (MemberTypeDefine.getMemberType(memberType) == 3) {
                ShippingDriverJpa shippingDriverInfo = shippingService.getShippingDriverInfo(memberLogin.getMemberId());
                //관리자 승인 여부 확인
                if (shippingDriverInfo.getAdminApproveYn()) {
                    memberLogin.setShippingDriverInfo( shippingDriverInfo );
                } else {
                    memberLogin = null;
                    resultCode = PaaSErrCode.CUSTOM_ADMIN_NOT_APPROVE.code();
                }
            }
        }
        return new ApiResultObjectDTO(memberLogin, resultCode);
    }

    @Transactional(readOnly = true)
    public ApiResultObjectDTO getLoginId(String memberName, String memberMobileNumber) {
        int resultCode = PaasCodeDefine.OK;
        MemberJpa memberJpa = new MemberJpa();
        //member_detail에서 이름과 핸드폰번로호 조회
        MemberDetailJpa detailJpa = memberDetailJpaRepository.findByMemberNameAndMemberMobileNumber(memberName, memberMobileNumber);
        if (detailJpa == null) {
            resultCode = PaaSErrCode.CUSTOM_NOT_USER.code();
        } else {
            //조회된 사용자가 있으면 로그인 아이디 가져오기
            MemberJpaFindIdInter findIdInter = memberJpaRepository.findByMemberId(detailJpa.getMemberId());

            memberJpa.setLoginId(findIdInter.getLoginId());
            memberJpa.setCreateDate(StringUtils.convertYyyymmdd(findIdInter.getCreateDate()));
        }
        return new ApiResultObjectDTO(memberJpa, resultCode);
    }

    public void modifyMemberPassword(Integer memberId, String newPassword) {
        Optional<MemberJpa> memberJpa = memberJpaRepository.findById(memberId);
        if (memberJpa != null) {
            MemberJpa updateMemberJpa = new MemberJpa(memberJpa, newPassword);
            memberJpaRepository.save(updateMemberJpa);
        }
    }
}
