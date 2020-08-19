package com.tlk.api.define.shipping;

/**
 * 패키지 상태관련 코드 정의
 */
public enum PackageStatusDefine {
    NORMAL(1, "정상"),
    CHECK(2, "점검"),
    DISPOSAL(3, "폐기");

    int packageStatus;

    String packageStatusName;

    PackageStatusDefine(int packageStatus, String packageStatusName) {
        this.packageStatus = packageStatus;
        this.packageStatusName = packageStatusName;
    }
}
