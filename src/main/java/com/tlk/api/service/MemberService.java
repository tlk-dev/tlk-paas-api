package com.tlk.api.service;

import com.tlk.api.define.err.PaaSErrCode;
import com.tlk.api.define.err.PaaSException;
import com.tlk.api.jpa.MemberDetailJpa;
import com.tlk.api.jpa.MemberDeviceJpa;
import com.tlk.api.jpa.MemberJpa;
import com.tlk.api.jpa.UserJpa;
import com.tlk.api.jpa.repository.MemberDetailJpaRepository;
import com.tlk.api.jpa.repository.MemberDeviceJpaRepository;
import com.tlk.api.jpa.repository.MemberJpaRepository;
import com.tlk.api.utils.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Autowired
    private MemberDetailJpaRepository memberDetailJpaRepository;

    @Autowired
    private MemberDeviceJpaRepository memberDeviceJpaRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Integer regMember(String loginId, String loginPassword, String memberType) {
        //아이디 중복 체크
        Long memberCount = memberJpaRepository.countByLoginId(loginId);
        logger.info("memberCount ::" + memberCount);
        if (memberCount > 0L) {
            throw new PaaSException(PaaSErrCode.CUSTOM_DUPLICATED_USER_ID);
        }
        MemberJpa memberJpa = new MemberJpa(loginId, loginPassword, memberType);
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

    @Transactional(readOnly = true)
    public MemberJpa getMember(String loginId, String loginPassword) {
        MemberJpa memberInfo = memberJpaRepository.findByLoginIdAndLoginPassword(
                loginId, SecurityUtil.encryptSHA256(loginPassword)
        );
        return memberInfo;
    }
}
