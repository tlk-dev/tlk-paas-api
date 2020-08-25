package com.tlk.api.define.err;

public enum PaaSErrCode {

    BAD_REQUEST(400, "Bad request, parameter not accepted"),
    NOT_AUTHENTICATE(401, "Not authenticated"),

    CUSTOM_PARAM_ERROR_MEMBER_ID(800, "PARAM :: MEMBER_ID 없음"),
    CUSTOM_PARAM_ERROR_REQUEST_DATE(801, "PARAM :: REQUEST_DATE 없음"),

    CUSTOM_DUPLICATED_USER_ID(900, "CUSTOM :: 아이디 중복"),
    CUSTOM_LOGIN_ERROR(901, "CUSTOM :: 로그인 에러(아이디 또는 비밀번호가 틀림)"),
    CUSTOM_ADMIN_NOT_APPROVE(902, "CUSTOM :: 관리자 승인하지 않은 배송기사"),
    CUSTOM_NOT_USER(903, "CUSTOM :: 사용자가 없음")
    ;

    int code;
    String msg;

    PaaSErrCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

    public static String getPaaSErrorMessage(int code) {
        for (PaaSErrCode errCode : PaaSErrCode.values()) {
            if (code == errCode.code) {
                return errCode.msg();
            }
        }
        return null;
    }


}
