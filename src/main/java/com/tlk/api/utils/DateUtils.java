package com.tlk.api.utils;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public final static DateTimeZone UTC_TIME_ZONE = DateTimeZone.UTC;
    public final static DateTimeZone KOR_TIME_ZONE = DateTimeZone.UTC;
    public final static String DF_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DF_YMD_PATTERN = "yyyy-MM-dd";

    /**
     * 데이트 객체를 패턴에 따라 문자로 변환한다.
     *
     * @param date 데이트 객체.
     * @param pattern 문자열 패턴
     * @return 패턴화 문자.
     */
    public static String dateToStr(Date date, String pattern, DateTimeZone timeZone){
        if(date == null){
            return null;
        }
        DateTime dt = new DateTime(date, timeZone);
        return dt.toString(pattern);
    }

    /**
     * 현재 시각의 데이트 객체를 반환한다.
     * @return
     */
    public static Date now() {
        DateTime dateTime = new DateTime();
        return dateTime.toDate();
    }

    public static String nowToStrUTC() {
        DateTime dt = new DateTime(now(), UTC_TIME_ZONE);
        return dt.toString(DF_TIME_PATTERN);
    }

    public static String nowToStrUTCNoHms(String pattern) {
        DateTime dt = new DateTime(now(), UTC_TIME_ZONE);
        return dt.toString(pattern + " 00:00:00");
    }

    public static Date stringToDate(String yyyymmdd) throws Exception {
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date to = transFormat.parse(yyyymmdd);
        return to;
    }

    public static String todayToStr() {
        Date today = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return transFormat.format(today);
    }

    /**
     * 오늘이 두개 비교날짜 사이에 있거나 두날짜보다 작으면 true, 오늘이 두개 날짜보다 전부 크면 false
     * @param startDate
     * @param endDate
     * @return
     */
    public static boolean isBetweenDateFromToday(String startDate, String endDate) throws Exception {
        String today = todayToStr();    //현재날짜String
        Date currentDate; //현재날짜 Date
        Date date2; //시작일
        Date date3; //종료일

        DateFormat df1= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        date2 = df1.parse(startDate);
        date3 = df1.parse(endDate);
        currentDate = df1.parse(today);

        int compare = currentDate.compareTo(date2);
        int compare2 = currentDate.compareTo(date3);

        if (compare == 1 && compare2 ==1) {
            return false;
        }
        return true;
    }

    public static String todayToStrKor() {
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String yyyy =  sdf.format(today);
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        String mm =  sdf2.format(today);
        SimpleDateFormat sdf3 = new SimpleDateFormat("dd");
        String dd =  sdf3.format(today);

        System.out.println(yyyy);
        return yyyy + "년 " + mm + "월 " + dd + "일";
    }

    public static String getYearLastTwo(){
        Date date = new Date();
        SimpleDateFormat formatter =new SimpleDateFormat("yy", Locale.KOREA);
        return formatter.format(date);
    }

    public static String returnNow() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(today);
    }

    public static String returnNowDateByYyyymmddhhmmss() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(today);
    }

    /**
     * 현재 년도를 리턴
     * @return
     */
    public static String getNowYear() {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("yyyy");
        return sdf.format(today);
    }

    /**
     * <pre>
     * 1. Comment : yyyy-mm-dd 형식의 값으로 더한 날짜를 구하기
     * 2. 작성자 : 안지호
     * 3. 작성일 : 2016. 07. 26
     * </pre>
     * @param yyyy_mm_dd
     * @param type
     * @param plusDay
     * @return type:YYYY => yyyy, type:MMDD => MMdd, type:YYYYMMDD => yyyy-MM-dd)
     */
    public static String plusDay(String yyyy_mm_dd, String type, int plusDay) {
        String MMdd = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(yyyy_mm_dd);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, plusDay);
        String strDate = dateFormat.format(cal.getTime());
        String[] str = strDate.split("-");
        if ("MMDD".equals(type)) {
            MMdd = str[1]+"-"+str[2];
        } else if ("YYYY".equals(type)) {
            MMdd = str[0];
        } else if ("YYYYMMDD".equals(type)) {
            MMdd = strDate;
        }
        return MMdd;
    }

    /**
     * 현재 날짜를 리턴
     * @param dateType 날짜형식(ex : yyyy-MM-dd, yyyyMMdd ....)
     * @return
     */
    public static String returnToDate(String dateType) {
        Date today = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat(dateType);
        return sdf.format(today);
    }

    /**
     * yyyy-mm-dd -> yyyymmdd 변환
     */
    public static String convertDateFormat(String date) throws Exception {
        SimpleDateFormat fromDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmdd");
        Date originDate = fromDateFormat.parse(date);
        String newDate = dateFormat.format(originDate);
        return newDate;
    }

    /**
     * 요일구하기
     * @param date
     * @return
     */
    public static String getDayOfWeek(String date) {
        String day = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String[] week = {"일","월","화","수","목","금","토"};
        Calendar cal = Calendar.getInstance();
        Date getDate;
        try {
            getDate = format.parse(convertDateFormat(date));
            cal.setTime(getDate);
            int w = cal.get(Calendar.DAY_OF_WEEK)-1;
            day = week[w];
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getDayOfWeek("2020-11-01"));
    }
}
