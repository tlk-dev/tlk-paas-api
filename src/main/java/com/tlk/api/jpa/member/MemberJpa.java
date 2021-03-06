package com.tlk.api.jpa.member;

import com.tlk.api.define.member.MemberTypeDefine;
import com.tlk.api.utils.DateUtils;
import com.tlk.api.utils.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Optional;

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

    // 탈퇴여부
    private Boolean dismissYn;

    // 생성일
    private String createDate;

    // 사용자 여부
    private Boolean isUser;

    // 배송기사 여부
    private Boolean isDeliver;

    // 관리자 여부
    private Boolean isAdmin;

    // 오퍼레이터 여부
    private Boolean isOperator;

    @Builder
    public MemberJpa(String loginId, String loginPassword, boolean isUser, boolean isDeliver, boolean isOperator) {
        this.loginId = loginId;
        this.loginPassword = SecurityUtil.encryptSHA256(loginPassword);
        //this.memberType = MemberTypeDefine.getMemberType(memberType);
        this.dismissYn = false;
        this.createDate = DateUtils.returnNow();
        this.isUser = isUser;
        this.isAdmin = false;
        this.isDeliver= isDeliver;
        this.isOperator = isOperator;
    }

    public MemberJpa(Optional<MemberJpa>optional, String newPassword) {
        this.memberId = optional.get().memberId;
        this.loginId = optional.get().getLoginId();
        this.loginPassword = SecurityUtil.encryptSHA256(newPassword);
        this.dismissYn = optional.get().dismissYn;
        this.createDate = optional.get().createDate;
    }

}
