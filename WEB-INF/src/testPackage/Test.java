/**
 * 
 */
package testPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.db.ConnectionManager;
import cn.com.bhh.erp.db.NoConnectionAvailableException;
import cn.com.bhh.erp.db.TimeoutException;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.User;

/**
 * 
 *
 */
public class Test {

    /**
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  void
     * @throws  Exception
     */
    public static void main(String[] args) {
//        Date baseDate = new Date();
//        Date dtBaseDate = null;
//        String strBaseDate = "20080855";
//        if(strBaseDate!=null && !"".equals(strBaseDate.trim())){
//            DateFormat dateFormat = new DateFormat();
//            dtBaseDate = dateFormat.stringToDate(strBaseDate, "fault.baseDate");
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//            System.out.println("date:"+dateFormat.getErrors().size());
//        }else{
//            dtBaseDate = new Date();
//        }
//        
//        //上周星期一日期
//        String beforeLastMonday=TimeUtil.getBeforeLastMonday(baseDate);
//        //上周星期天日期
//        String beforeLastSunday=TimeUtil.getBeforeLastSunday(baseDate);
//        //本周星期一日期
//        String lastMonday=TimeUtil.getLastMonday(baseDate);
//        //本周星期天日期
//        String lastSunday=TimeUtil.getLastSunday(baseDate);
//        System.out.println(beforeLastSunday);
//        System.out.println(beforeLastMonday);
//        System.out.println(lastMonday);
//        System.out.println(lastSunday);
        //testJson();
//        test2();
//        String time1  =  TimeUtil.getNowNoSecond();
//        String time2  =  TimeUtil.getNow();
//        System.out.println(time1.substring(8));
//          System.out.println(time2.substring(8));
    	  ConnectionManager dcm;
    	     Connection conn = null;
    	     dcm = ConnectionManager.getInstance();
    	try {
			conn = dcm.getConnection(ConnectionManager.ORACLE);
			conn = dcm.getConnection(ConnectionManager.ORACLE);
	        conn.setAutoCommit(false);
	        System.out.println(Integer.MAX_VALUE);
	        UserDao userdao = new UserDao(conn);
	        User user = new User();
	        user.setName("zkcl");
	        int n = userdao.getCountByUserName(user);
	        System.out.println(n);
		} catch (SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(NoConnectionAvailableException e){
			
		}catch(TimeoutException e){
			
		}
    	dcm.freeConnection(ConnectionManager.ORACLE, conn);
    	
    }
    
    public void test1(){
        List<Integer> idList = new ArrayList<Integer>();
        for(int i = 0;i<2;i++){
            idList.add(i);
        }
        
        StringBuffer strBuf = new StringBuffer("");
        int installIdSize = idList.size();
        if(installIdSize == 1){
            Integer installId = idList.get(0);
            strBuf.append(" AND A.ID ="+installId+"");
        }else{
            for(int k=0 ;k<installIdSize;k++){
                if(k == 0){
                    strBuf.append(" AND A.ID IN ("+idList.get(k));
                }else if(k == installIdSize-1){
                    strBuf.append(","+idList.get(k)+")");
                }else{
                    strBuf.append(","+idList.get(k));
                }
                    
            }
        }
        System.out.println(strBuf.toString());
    }
    
    public static void test2(){
        //String regex="";
        String str="ty[aaa$$$))";
        Pattern p = Pattern.compile("[^\u4E00-\u9FA5]*");
        Matcher matcher = p.matcher(str);
        boolean b= matcher.matches();
        System.out.println(b);
        System.out.println("hello");
         
      }
    
    public static void testJson(){
        List<Company> bankCustomers = new ArrayList<Company>();
        for(int i= 0;i<3;i++){
            Company c = new Company();
            c.setId(i);
            c.setShortName("name"+i);
            bankCustomers.add(c);
        }
        int bankSize = bankCustomers.size();
        StringBuffer jsonData = new StringBuffer();
        for(int i = 0;i<bankSize;i++){
            Company bankCom = bankCustomers.get(i);
            if(i==0 && i == bankSize-1){
                jsonData.append("{"+bankCom.getId()+":"+bankCom.getShortName()+"}");
            }else{
                if(i == 0){
                    jsonData.append("{"+bankCom.getId()+":"+bankCom.getShortName()+",");
                }else if(i == bankSize-1){
                    jsonData.append(bankCom.getId()+":"+bankCom.getShortName()+"}");
                }else{
                    jsonData.append(bankCom.getId()+":"+bankCom.getShortName()+",");
                }
                
            }
        }
        System.out.println(jsonData.toString());
      }
    
    public static void test100(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(new Integer(1));
        list.add(new Integer(3));
        list.add(new Integer(5));
        list.add(null);
        System.out.println(list.contains(1));
        System.out.println(list.contains(new Integer(1)));
        System.out.println(list.contains(new Integer(3)));
        System.out.println(list.contains(null));
        
        
    }
   
    
    public static void testTime(){
        DateFormat dateFormat = new DateFormat();
        System.out.println(dateFormat.changeTime("2222"));
    }
    
    
    } 