package com.tlk.api.jpa;


import com.tlk.api.define.MemberTypeDefine;
import com.tlk.api.utils.DateUtils;
import com.tlk.api.utils.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_member")
public class MemberJpa implements Serializable {

    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberId;

    // 회원 로그인 아이디
    private String loginId;

    // 회원 로그인 패스워드
    private String loginPassword;

    // 회원 종류(1,2,3...)
    private Integer memberType;

    // 탈퇴여부
    private Boolean dismissYn;

    // 생성일
    private String createDate;

    @Builder
    public MemberJpa(String loginId, String loginPassword, String memberType) {
        this.loginId = loginId;
        this.loginPassword = SecurityUtil.encryptSHA256(loginPassword);
        this.memberType = MemberTypeDefine.getMemberType(memberType);
        this.dismissYn = false;
        this.createDate = DateUtils.returnNow();
    }

}
