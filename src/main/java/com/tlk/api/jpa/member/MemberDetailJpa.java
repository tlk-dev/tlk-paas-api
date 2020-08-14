package com.tlk.api.jpa.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_member_detail")
public class MemberDetailJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberDetailId;

    // 회원 인덱스
    private Integer memberId;

    // 회원 이름
    private String memberName;

    // 회원 핸드폰 번호
    private String memberMobileNumber;

    // 우편번호
    private String memberZipCode;

    // 주소(도,시,구,동..)
    private String memberAddress;

    // 주소 상세
    private String memberAddressDetail;

    // 회원 생년월일
    private String memberBirthday;

    // 성별(남<M>,여<F>)
    private String memberGender;

    // 회원 이메일
    private String memberEmailAddress;

    public MemberDetailJpa(Integer memberId, String memberName, String memberMobileNumber, String memberZipCode,
                           String memberAddress, String memberAddressDetail, String memberBirthday, String memberGender, String memberEmailAddress) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberMobileNumber = memberMobileNumber;
        this.memberZipCode = memberZipCode;
        this.memberAddress = memberAddress;
        this.memberAddressDetail = memberAddressDetail;
        this.memberBirthday = memberBirthday;
        this.memberGender = memberGender;
        this.memberEmailAddress = memberEmailAddress;
    }
}
