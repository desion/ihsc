//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.common;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;


/**
 * util about time
 * @author fenghy
 *
 */
public final class TimeUtil {
    private TimeUtil() {
    }

    public static String getNow() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }

    public static String getNowNoSecond() {
        return new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
    }
    
    public static String getNowDate() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }
    
    public static String getTodayStart() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " 00:00:00";
    }
    
    public static String getTodayEnd() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date()) + " 24:00:00";
    }

    /**
     * to the day according to the parammeter date
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Date,addDays
     * @return  String
     */
    public static String getDate(Date date, int addDays) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, addDays);

        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * get the next day.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     */
    public static String getNowNextDay() {
        return getDate(new Date(), 1);
    }
    
    /**
     * get the Sunday date according to
     * the week
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   week-example:1 the next Sunday,-1 the previous Sunday,0 this Sunday
     * @return  String
     */
    public static  String getSundayDate(int week,Date baseDate){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int plusDay = 0;
        if (dayOfWeek == 1) {
            plusDay = 7 * week;
        } else {
            plusDay = 8 - dayOfWeek + 7 * week;
        }
        c.add(Calendar.DATE, plusDay);
        return sdf.format(c.getTime());
    }
    
    
    /**
     * get the Monday date according to the
     * the week.
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   week-example:1 the next Monday,-1 the previous Monday,0 this Monday
     * @return  String
     */
    public static  String getMondayDate(int week,Date baseDate){
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(baseDate);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int plusDay = 0;
        if (dayOfWeek == 1) {
            plusDay = -6 + 7 * week;
        } else if (dayOfWeek == 2) {
            plusDay = week * 7;
        } else {
            plusDay = 2 - dayOfWeek + 7 * week;
        }
        c.add(Calendar.DATE, plusDay);
        return sdf.format(c.getTime());
    }
    
    /**
     * get the the Sunday before privous Monday date.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public static String getLastMonday(Date baseDate) {
         return getMondayDate(-1,baseDate);
    }
    
    
    /**
     * get the privous Monday date.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public static String getBeforeLastMonday(Date baseDate) {
         return getMondayDate(-2,baseDate);
    }
    
    /**
     * get the privous Monday date.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public static String getLastSunday(Date baseDate) {
         return getSundayDate(-1,baseDate);
    }
    
    
    /**
     * get the the Monday before the privous Monday date.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public static String getBeforeLastSunday(Date baseDate) {
         return getSundayDate(-2,baseDate);
    }
    
    
    
    
}
