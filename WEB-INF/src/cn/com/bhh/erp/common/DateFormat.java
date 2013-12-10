package cn.com.bhh.erp.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.bhh.erp.business.BaseBusiness;
/**
 * 输入的年月日的合法性检查
 * @author liugd
 * @version 1.0
 * @since 1.0
 * @return
 */
public class DateFormat extends BaseBusiness{

    /**
     * 输入的年月日的合法性检查。
     * @author luyan
     * @since 1.0
     * @param date 检查用的日期
     * @return 合法时返回八位日期，不合法时返回null
     */
    public String format(String date) {
        boolean errFlg = true;
        String dateOut = date;
        if(date != null && !"".equals(date)) {
            date = date.trim();
            
            if(date.indexOf("/") >=0 || date.indexOf("-") >=0) {
                String dataArr[] = null;
                if(date.indexOf("/") >=0 ) {
                    dataArr = date.split("/");
                } else {
                    dataArr = date.split("-");
                }
                if(dataArr.length == 3) {
                    if((dataArr[0].length() == 4 || dataArr[0].length() == 2) 
                            && (dataArr[1].length() >=1 && dataArr[1].length() <=2) 
                            && (dataArr[2].length() >= 1 && dataArr[2].length() <= 2)) {
                        if (dataArr[0].length() == 2) {
                            dataArr[0] = "20" + dataArr[0];
                        }
                        if(dataArr[1].length() == 1) {
                            dataArr[1] = "0" + dataArr[1];
                        }
                        if(dataArr[2].length() == 1) {
                            dataArr[2] = "0" + dataArr[2];
                        }
                        
                        errFlg = dateCheck(dataArr[0] + dataArr[1] + dataArr[2]);
                        if (!errFlg) {
                            dateOut = dataArr[0] + dataArr[1] + dataArr[2];
                        }
                    }
                }
            } else {
                if(date.length() == 8) {
                    errFlg = dateCheck(date);
                }
            }
            if(errFlg) {
                dateOut = null;
            }
        }
        
        return dateOut;
    }

    /**
     * 输入的年月日的合法性检查
     * @author liugd
     * @param date:检查用的日期
     * @param location：输入的日期出错时，出错的位置
     * @return
     */
    public String format(String date, String location) {
        boolean errFlg = true;
        String dateOut = date;
        if(date != null && !"".equals(date)) {
            date = date.trim();
            
            if(date.indexOf("/") >=0 || date.indexOf("-") >=0) {
                String dataArr[] = null;
                if(date.indexOf("/") >=0 ) {
                    dataArr = date.split("/");
                } else {
                    dataArr = date.split("-");
                }
                if(dataArr.length == 3) {
                    if((dataArr[0].length() == 4 || dataArr[0].length() == 2) 
                            && (dataArr[1].length() >=1 && dataArr[1].length() <=2) 
                            && (dataArr[2].length() >= 1 && dataArr[2].length() <= 2)) {
                        if (dataArr[0].length() == 2) {
                            dataArr[0] = "20" + dataArr[0];
                        }
                        if(dataArr[1].length() == 1) {
                            dataArr[1] = "0" + dataArr[1];
                        }
                        if(dataArr[2].length() == 1) {
                            dataArr[2] = "0" + dataArr[2];
                        }
                        
                        errFlg = dateCheck(dataArr[0] + dataArr[1] + dataArr[2]);
                        if (!errFlg) {
                            dateOut = dataArr[0] + dataArr[1] + dataArr[2];
                        }
                    }
                }
            } else {
                if(date.length() == 8) {
                    errFlg = dateCheck(date);
                }
            }
            if(errFlg == true) {
                errors.add("BSE00005," + location);
            }
        }
        
        return dateOut;
    }
    
    /**
     * 判断输入的年月日是不是合法的年月日
     * @param data
     * @return 合法时：errFlg=false
     */
    public boolean dateCheck(String data) {
        boolean errFlg = true;
        int intY;
        int intM;
        int intD;
        try {
            intY = Integer.parseInt(data.substring(0, 4));
            intM = Integer.parseInt(data.substring(4, 6));
            intD = Integer.parseInt(data.substring(6, 8));

            if(intM >0 && intM <= 12 && intD > 0) {
                switch (intM) {
                case 4:
                case 6:
                case 9:
                case 11: 
                    // 4、6、9、11月份最大天数为30天
                    if (intD <= 30) {
                        errFlg = false;
                    }
                    break;
                case 2: 
                    // 区别闰年
                    if ((intY % 4 == 0 && intY % 100 != 0) || intY % 400 == 0) {
                        // 闰年2月份最大天数为29天
                        if (intD <= 29) {
                            errFlg = false;
                        }
                    } else {
                        // 非闰年2月份最大天数为28天
                        if (intD <= 28) {
                            errFlg = false;
                        }
                    }
                    break;
                default: 
                    // 1、3、5、7、8、10、12月份最大天数为31天
                    if (intD <= 31) {
                        errFlg = false;
                    }
                }
            }
        } catch (Exception e) {
        }
        return errFlg;
    }
    
    /**
     * 日期变换：给日期加上/
     * @param date 八位日期(yyyyMMdd)
     * @return 变换后日期(yyyy/MM/dd)
     */
    public String changeDate(String date){
        String dateOut = date;
        if (date != null && date.length() == 8) {
            if (!dateCheck(date)) {
                dateOut = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
            }
        }
        return dateOut;
    }

   
    /**
     * 日期变换：给日期加上/，时间加上:
     * @param date
     * @return
     */
    public static String changeDateTime(String date){
        String dateOut = date;
        if (date != null && date.length() == 14) {
            dateOut = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8)+
            " "+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12,14);
        }
        return dateOut;
    }    
    

    /**
     * 时间变换：时间加上":"
     * @param time 六位时间(HHMM)
     * @return 变换后时间(HH:MM)
     */
    public String changeTime(String time){
        String timeOut = time;
        if (time != null && time.trim().length() == 4) {
            if (timeCheck(time)) {
                timeOut = time.substring(0, 2) + ":" + time.substring(2, 4);
            }
        }
        return timeOut;
    }
    
    

    
    /**
     * 时间变换：时间去掉":"
     * @param time:HH:MM
     * @param location
     * @return
     */
    public String formatTime(String time, String location){
        boolean errFlg = false;
        String timeOut = "";
        if(time != null && !"".equals(time)) {
            time = time.trim();
            
            if(time.indexOf(":") >=0) {
                String timeArr[] = null;
                timeArr = time.split(":");
                if(timeArr.length == 2) {
                    if(timeArr[0].length() == 2
                       && (timeArr[1].length() >=1 && timeArr[1].length() <=2)) {
                        if(timeArr[1].length() == 1) {
                            timeArr[1] = "0" + timeArr[1];
                        }
           
                        errFlg = timeCheck(timeArr[0] + timeArr[1]);
                        if (errFlg) {
                            timeOut = timeArr[0] + timeArr[1];
                        }
                    }
                }
            } else {
                if(time.length() == 4) {
                    errFlg = timeCheck(time);
                    timeOut = time;
                }
            }
            if(errFlg == false) {
                errors.add("BSE00023," + location);
                return time;
            }
        }
        
        return timeOut;
    }

    /**
     * 判断输入的时间是不是合法的时间，时分：HH:MM
     * @param time
     * @return 合法时返回true，否则返回false
     */
    public boolean timeCheck(String time){
        boolean isLegally = true;
        int intH;
        int intM;
        try {
            intH = Integer.parseInt(time.substring(0, 2));
            intM = Integer.parseInt(time.substring(2, 4));
         
            if(intH >= 24 || intH < 0){
                isLegally = false;
            }
            if(intM >= 60 || intM < 0){
                isLegally = false;
            }
        } catch (Exception e) {
            isLegally = false;
        }

        return isLegally;
    }
    
    /**
     * 将字符串类型的日期转化为Date类型
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   strDate  字符串类型日期如：20080812
     * @param   itemName，该日期对于的名称，国际化用
     * @return  Date
     * @throws  Exception
     */
    public Date stringToDate(String strDate,String itemName){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        //日期分析严格
        dateFormat.setLenient(false);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            errors.add("BSE00005," + itemName);
        }
        return date;
    }
    
//////////////////////////////////////////////////////////////////////////////////////////    
//  /**
//  * 时间变换：时间去掉":"
//  * @param time:HH:MM:SS
//  * @param location
//  * @return
//  */
// public String formatTime2(String time, String location){
//     boolean errFlg = false;
//     String timeOut = "";
//     if(time != null && !"".equals(time)) {
//         time = time.trim();
//         
//         if(time.indexOf(":") >=0) {
//             String timeArr[] = null;
//             timeArr = time.split(":");
//             if(timeArr.length == 3) {
//                 if(timeArr[0].length() == 2
//                         && (timeArr[1].length() >=1 && timeArr[1].length() <=2) 
//                         && (timeArr[2].length() >= 1 && timeArr[2].length() <= 2)) {
//                     if(timeArr[1].length() == 1) {
//                         timeArr[1] = "0" + timeArr[1];
//                     }
//                     if(timeArr[2].length() == 1) {
//                         timeArr[2] = "0" + timeArr[2];
//                     }
//                     
//                     errFlg = timeCheck(timeArr[0] + timeArr[1] + timeArr[2]);
//                     if (errFlg) {
//                         timeOut = timeArr[0] + timeArr[1] + timeArr[2];
//                     }
//                 }
//             }
//         } else {
//             if(time.length() == 6) {
//                 errFlg = timeCheck(time);
//                 timeOut = time;
//             }
//         }
//         if(errFlg == false) {
//             errors.add("BSE00023," + location);
//             return time;
//         }
//     }
//     
//     return timeOut;
// }
 
 
///**
//* 判断输入的时间是不是合法的时间,时分秒：HH:MM:SS
//* @param time
//* @return 合法时返回true，否则返回false
//*/
//public boolean timeCheck2(String time){
//  boolean isLegally = true;
//  int intH;
//  int intM;
//  int intS;
//
//  try {
//      intH = Integer.parseInt(time.substring(0, 2));
//      intM = Integer.parseInt(time.substring(2, 4));
//      intS = Integer.parseInt(time.substring(4, 6));
//
//      if(intH >= 24 || intH < 0){
//          isLegally = false;
//      }
//      if(intM >= 60 || intM < 0){
//          isLegally = false;
//      }
//      if(intS >= 60 || intS < 0){
//          isLegally = false;
//      }
//  } catch (Exception e) {
//      isLegally = false;
//  }
//
//  return isLegally;
//}

 
///**
//* 时间变换：时间加上":"
//* @param time 六位时间(HHMMSS)
//* @return 变换后时间(HH:MM:SS)
//*/
//public String changeTime2(String time){
//  String timeOut = time;
//  if (time != null && time.length() == 6) {
//      if (timeCheck(time)) {
//          timeOut = time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
//      }
//  }
//  return timeOut;
//}


///**
//* 日期变换：给日期加上/，时间加上:
//* @param date
//* @return
//*/
//public static String changeDateTime2(String date){
//  String dateOut = date;
//  if (date != null && date.length() == 14) {
//      dateOut = date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8)+
//      " "+date.substring(8,10)+":"+date.substring(10,12)+":"+date.substring(12,14);
//  }
//  return dateOut;
//}
//    
    
}
