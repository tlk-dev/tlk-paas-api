package com.tlk.api.utils;

public class TlkPaasUtils {

    /**
     * 패키징 코드 생성
     * @param prevPackageCode
     * @return
     */
    public static String generatePackageCode(String prevPackageCode) {
        String generateCode = "";
        if (!"".equals(prevPackageCode)) {
            String splitStr[] = prevPackageCode.split("_");
            String number = splitStr[1];
            generateCode = String.format("%07d", Integer.parseInt(number) + 1);
        }
        return generateCode;
    }
}
