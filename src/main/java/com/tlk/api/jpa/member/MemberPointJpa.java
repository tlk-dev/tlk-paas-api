package com.tlk.api.jpa.member;

import com.tlk.api.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "paas_member_point")
public class MemberPointJpa {
    // 인덱스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memberPointId;

    // 회원 인덱스
    private Integer memberId;

    // 적립 또는 사용한 포인트
    private String point;

    // 생성일
    private String createDate;

    public MemberPointJpa(Integer memberId, String point) {
        this.memberId = memberId;
        this.point = point;
        this.createDate = DateUtils.returnNow();
    }

}
