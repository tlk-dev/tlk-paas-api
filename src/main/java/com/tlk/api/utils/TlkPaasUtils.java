package com.tlk.api.utils;

public class TlkPaasUtils {

    /**
     * 패키징 코드 생성
     * @param prevPackageCode
     * @return
     */
    public static String generatePackagingCode(String prevPackageCode) {
        String generateCode = "";
        if (!"".equals(prevPackageCode)) {
            String splitStr[] = prevPackageCode.split("_");
            String number = splitStr[1];
            generateCode = String.format("%07d", Integer.parseInt(number) + 1);
        } else {
            generateCode = "TP_0000001";
        }
        return generateCode;
    }

    /**
     * 주문 코드 생성
     * @param clientId 고객사 아이디
     * @return
     */
    public static String generateOrderCode(int clientId) {
        String orderCode = "TO" + clientId + "_" + DateUtils.returnNowDateByYyyymmddhhmmss() + "";
        return orderCode;
    }

    public static void main(String[] args) {
        System.out.println(generateOrderCode(1));
    }
}
