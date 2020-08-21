package com.tlk.api.utils;

import javax.rmi.CORBA.Util;
import java.io.File;

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

    public static String isNullString(String str) {
        String str2 = "";
        if (str == null) {
            str = str2;
        }
        return str;
    }

    public static String concatPath(String... paths) {
        String separator = File.separator;
        StringBuilder buffer = new StringBuilder();
        for(String path : paths){
            if(path == null) continue;
            path = path.trim();
            if(!path.startsWith(separator) && !path.startsWith("http")){
                path = separator + path;
            }
            if(path.endsWith(separator)){
                int length = path.length();
                path = path.substring(0, length-1);
            }
            buffer.append(path);
        }
        return buffer.toString();
    }

    /**
     * YYYY-MM-DD hh:mm:ss 형식을 YYYY-MM-DD로 변환
     * @param ymdhms
     * @return
     */
    public static String convertYyyymmdd(String ymdhms) {
        String yyyymmdd = "";
        if (!"".equals(ymdhms)) {
            String ymd[] = ymdhms.split(" ");
            yyyymmdd = ymd[0];
        }
        return yyyymmdd;
    }

    public static void main(String[] args) {
        String s = "TP_0000001";
        System.out.println(convertYyyymmdd("2020-08-13 17:15:15"));
    }
}
