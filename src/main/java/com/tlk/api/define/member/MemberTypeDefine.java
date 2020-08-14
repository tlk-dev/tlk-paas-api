package com.tlk.api.define.member;

/**
 * 멤버 종류 정의
 */
public enum MemberTypeDefine {
    ADMIN(1, "관리자"),
    USER(2, "사용자"),
    DELIVER(3, "배송기사"),
    OPERATOR(4, "오퍼레이터");

    Integer memberType;

    String memberTypeName;

    MemberTypeDefine(Integer memberType, String memberTypeName) {
        this.memberType = memberType;
        this.memberTypeName = memberTypeName;
    }

    public static Integer getMemberType(String memberTypeName) {
        for (MemberTypeDefine memberTypeDefine : MemberTypeDefine.values()) {
            if (memberTypeName.equals(memberTypeDefine.toString())) {
                return memberTypeDefine.memberType;
            }
        }
        return null;
    }

}
