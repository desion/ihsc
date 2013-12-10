/**
 * 
 */
package cn.com.bhh.erp.common;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
    
    //将Integer数组转化格式为 ：   ,1,4,5,（前后都带逗号）
   public static String arrayToString(List<Integer> list){
       String str = "";
        int length = list.size();
        for (int index = 0; index < length; index++) {
            if (index != 0)
                str += list.get(index) + ",";
            else
                str += "," + list.get(index) + ",";
        }
        return str;
   }
   
   //将字符串 ",1,4,5," 转化为Integer数组
   public static List<Integer> stringToArray(String str) {
        List<Integer> intList = new ArrayList<Integer>();
        int strLength = str.length();
        if (strLength >= 3) {
           str = str.substring(1,strLength);
           String[] tempStr = str.split(",");
           for(String string:tempStr){
               Integer val = Integer.parseInt(string);
               intList.add(val);
           }
        }
        return intList;
    }
}
