package com.tlk.api.utils;

public class StringUtils {

    /**
     * @설명 : 빈값인경우 체크,널대체값이 있는경우
     */
    public static String isNullValue(String str2, String NulltoStr) {
        String str = str2;
        if (str == null){
            str = NulltoStr;
        } else if (str.equals("")){
            str = NulltoStr;
        }
        return str;
    }
}
