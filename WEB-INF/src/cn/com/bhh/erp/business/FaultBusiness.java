//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.FaultDetailDao;
import cn.com.bhh.erp.dao.FaultHandleDao;
import cn.com.bhh.erp.dao.FaultPartDao;
import cn.com.bhh.erp.dao.FaultPartTypeDao;
import cn.com.bhh.erp.dao.FaultSparesDao;
import cn.com.bhh.erp.dao.FaultStatusDao;
import cn.com.bhh.erp.dao.FaultTypeDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.InstallationDao;
import cn.com.bhh.erp.dao.ProductCategoryDao;
import cn.com.bhh.erp.dao.SupportTypeDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.FaultSpares;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.User;

/**
 * fault business
 * 
 * @author liugd
 * @version 1.0
 * @since 1.0
 * 
 */
public class FaultBusiness extends BaseBusiness {
    private static final Integer ZERO = new Integer("0");
    private static final Integer ONE = new Integer("1");
    private static final Integer TWO = new Integer("2");
    private static final Integer THREE = new Integer("3");
    private static final Integer FOUR = new Integer("4");
    private static final Integer FIVE = new Integer("5");
    private static final Integer SIX = new Integer("6");
    private static final Integer SEVEN = new Integer("7");

    /**
     * GET FAULT COUNT
     * 
     * @author liugd
     * @param fault
     * @param user
     * @version 1.0
     * @since 1.0
     * @return
     */
    public int getFaultCount(Fault fault, User user) {
        int count = 0;

        try {
            init();

            // Start UOC
            FaultDao faultationDao = new FaultDao(conn);
            count = faultationDao.getFaultCount(fault, user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return count;
    }

    /**
     * get fault information list
     * 
     * @author liugd
     * @param fault
     * @param user
     * @param currPage
     * @param pageSize
     * @version 1.0
     * @since 1.0
     * @return
     */
    public List<Fault> getFaultList(Fault fault, User user, int currPage, int pageSize) {
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            faultList = faultDao.getFaultList(fault, user, intBegin, intEnd);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }
    
    /**
     * 获取指定安装机器的故障列表
     * 
     * @author xiangzq
     * @param fault
     * @param user
     * @version 1.0
     * @since 1.0
     * @return
     */
    public List<Fault> getFaultListByInstallationId(Integer installationId, User user) {
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            faultList = faultDao.getFaultListByInstallationId(installationId, user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    } 
    

    /**
     * get the not deleted and not repared fault list.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Fault>
     * @throws Exception
     */
    public List<Fault> getFaultList(User loginUser) {
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Strat UOC

            FaultDao faultDao = new FaultDao(conn);
            String subSql = "";
            if (!loginUser.filter("fault_mng_all_data")) {
                subSql = subSql + " AND D.NOW_REPAIR_COMPANY_ID = "+loginUser.getCompanyID();
            }
            faultList = faultDao.getFaultList(subSql);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }
    
    /**
     * get the fault list that the time
     * between startDate and endDate.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Fault>
     */
    public List<Fault> getWeeklyFaultList(
            String baseDate,
            String agentCompanyName,
            String model,
            Integer affirmFlag,
            Integer faultTypeId){
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Strat UOC
            
            FaultDao faultDao = new FaultDao(conn);
            faultList = faultDao.getFaultWeekly(baseDate, agentCompanyName, model, affirmFlag,faultTypeId);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }
    
    /**
     * get the fault list that the time
     * between startDate and endDate.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Fault>
     */
    public List<Fault> getWeeklyFaultList(
            String startDate,
            String endDate,
            String agentCompanyName,
            String model,
            Integer faultTypeId){
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Strat UOC
            
            FaultDao faultDao = new FaultDao(conn);
            faultList = faultDao.getFaultWeekly(startDate, endDate,agentCompanyName,model,faultTypeId);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }
    
    /**
     * get the agent company name list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<String>
     */
    public List<String> getRepairCompanyNameList(String baseDate,Integer faultTypeId){
        List<String> agentCompanyList = new ArrayList<String>();

        try {
            init();

            // Strat UOC
            
            FaultDao faultDao = new FaultDao(conn);
            agentCompanyList = faultDao.getRepairCompanyNameListForFaultWeekly(baseDate,faultTypeId);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return agentCompanyList;
    }
    
    
    /**
     * get the model name list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   baseDate
     * @param   agentCompanyName
     * @param   affirmFlag ss
     * @return  List<String>
     */
    public List<String> getModelNameList(
            String baseDate,
            String agentCompanyName,
            Integer affirmFlag,
            Integer faultTypeId){
        List<String> agentCompanyList = new ArrayList<String>();

        try {
            init();

            // Strat UOC
            
            FaultDao faultDao = new FaultDao(conn);
            agentCompanyList = faultDao.getProductModelListForFaultWeekly(baseDate,agentCompanyName,affirmFlag,faultTypeId);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return agentCompanyList;
    }
    
    /**
     * get the model name list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   startDate
     * @param   endDate
     * @param   agentCompanyName
     * @return  List<String>
     */
    public List<String> getModelNameList(
            String startDate,
            String endDate,
            String agentCompanyName,
            Integer faultTypeId){
        List<String> agentCompanyList = new ArrayList<String>();

        try {
            init();

            // Strat UOC
            
            FaultDao faultDao = new FaultDao(conn);
            agentCompanyList = faultDao.getProductModelListForFaultWeekly(startDate, endDate,agentCompanyName,faultTypeId);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return agentCompanyList;
    }



    /**
     * @author liugd
     * @param fault
     * @param user
     * @param countSize
     * @return List<Fault>
     */
    public List<Fault> getDownLoadData(Fault fault, User user, int countSize) {
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Start UOC
            int count = 0;
            FaultDao faultDao = new FaultDao(conn);
            count = faultDao.getFaultCount(fault, user);
            if (count == 0) {
                errors.add("BSE01707");
            } else {
                if (count < countSize) {
                    countSize = count;
                }
                faultList = faultDao.getFaultList(fault, user, 0, countSize);
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }
    
    /**
     * @author liugd
     * @param fault
     * @param user
     * @param countSize
     * @return List<Fault>
     */
    public List<Fault> getSelectedDownLoadData(Fault fault,List<Integer> idList, User user) {
        List<Fault> faultList = new ArrayList<Fault>();

        try {
            init();

            // Start UOC
            
            FaultDao faultDao = new FaultDao(conn);
     
            
            StringBuffer strBuf = new StringBuffer("");
            int faultIdSize = idList.size();
          
            if(faultIdSize == 1){
                Integer faultId = idList.get(0);
                strBuf.append(" AND A.ID ="+faultId+"");
            }else{
                for(int k=0 ;k<faultIdSize;k++){
                    if(k == 0){
                        strBuf.append(" AND A.ID IN ("+idList.get(k));
                    }else if(k == faultIdSize-1){
                        strBuf.append(","+idList.get(k)+")");
                    }else{
                        strBuf.append(","+idList.get(k));
                    }     
                }
            }
  
            faultList = faultDao.getSelectedFaultList(fault,strBuf.toString(), user);
            int count = faultList.size();
            if (count == 0) {
                errors.add("BSE01707");
            } 
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultList;
    }

    /**
     * GET FAULT COUNT
     * 
     * @author liugd
     * @param fault
     * @param user
     * @version 1.0
     * @since 1.0
     * @return
     */
    @SuppressWarnings("unchecked")
    public void upload(ArrayList excelList, User user, String[] dicInfo) {
        
        try {
            init();

            // Start UOC
            String companyCode = "";
            String managementId = "";
            InstallationDao installationDao = new InstallationDao(conn);
            FaultTypeDao faultTypeDao = new FaultTypeDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            UserDao userDao = new UserDao(conn);
            FaultPartDao faultPartDao = new FaultPartDao(conn);
            FaultPartTypeDao faultPartTypeDao = new FaultPartTypeDao(conn);
            SupportTypeDao supportTypeDao = new SupportTypeDao(conn);
            FaultDao faultDao = new FaultDao(conn);
            FaultDetailDao faultDetailDao = new FaultDetailDao(conn);
            FaultStatusDao faultStatusDao = new FaultStatusDao(conn);
            ProductCategoryDao  productCategoryDao = new ProductCategoryDao(conn);

            // date input check
            ArrayList<String> rowList;
            ArrayList<Fault> faultList = new ArrayList<Fault>();
            Fault fault;
            Integer id;
            String strNowDateTime = TimeUtil.getNowNoSecond();
            for (int i = 2; i <= excelList.size() + 1; i++) {
                
                rowList = (ArrayList<String>) excelList.get(i - 2);
                //获取该故障对应机器的机型 2010/01/25
                String model = rowList.get(2);
                fault = new Fault();
                fault.setReportDate(strNowDateTime.substring(0, 8));
                fault.setReportTime(strNowDateTime.substring(8));
                // not null check
                String nullErr = "BSE00009," + i;
                if ("".equals(rowList.get(1))) {
                    errors.add(nullErr + ",fault.manufactureNo");
                }
                if ("".equals(rowList.get(5))) {
                    errors.add(nullErr + ",fault.faultTypeName");
                }
                if ("".equals(rowList.get(6))) {
                    errors.add(nullErr + ",fault.repairCompanyName");
                }
                if ("".equals(rowList.get(7))) {
                    errors.add(nullErr + ",fault.occurDate");
                }
                if ("".equals(rowList.get(8))) {
                    errors.add(nullErr + ",fault.occurTime");
                }
                if ("".equals(rowList.get(9))) {
                    errors.add(nullErr + ",fault.operatorName");
                }
                if ("".equals(rowList.get(25))) {
                    errors.add(nullErr + ",fault.appearance");
                }
                if ("".equals(rowList.get(28))) {
                    errors.add(nullErr + ",fault.result");
                }
                if (errors.size() > 0) {
                    break;
                }

                String notExistsErr = "BSE00015," + i;
                String subSql = "";
//                if (rowList.get(1).length() < 1 || rowList.get(1).length()>10) {

                Pattern p = Pattern.compile("\\S{1,10}");
                Matcher matcher = p.matcher(rowList.get(1));
                if(!matcher.matches()){
                    // length error
                    errors.add("BSE01710," + i + ",fault.manufactureNo");
                } else {
//                    try {
//                        Integer.parseInt(rowList.get(1));
                        subSql = " AND A.DELETED=0";
                        if (!user.filter("install_mng_all_data")) {
                            subSql = subSql + " AND A.NOW_REPAIR_COMPANY_ID = " + user.getCompanyID();
                        }
                        Installation installOut = installationDao.getInstallByMamuNoAndProCateName(rowList.get(1),rowList.get(3), subSql);
                        if (installOut.getId() == null) {
                            // data exists
                            errors.add(notExistsErr + ",fault.manufactureNo");
                        } else {
                            fault.setInstallationId(installOut.getId());
                        }
//                    } catch (Exception e) {
//                        // type error
//                        errors.add("BSE01710," + i + ",fault.manufactureNo");
//                    }
                }

                id = faultTypeDao.getIdByName(rowList.get(5));
                if (id == null) {
                    // data exists
                    errors.add(notExistsErr + ",fault.faultTypeName");
                } else {
                    if (Integer.valueOf(1).compareTo(id) == 0 && "".equals(rowList.get(11))) {
                        errors.add("BSE02109," + i);
                    } else {
                        fault.setFaultType(id);
                    }
                    fault.setFaultType(id);
                }

                if (!user.filter("company_mng_all_data")) {
                    subSql = " AND ID = " + user.getCompanyID() + " AND TYPE_ID LIKE '%,3,%'";
                } else {
                    subSql = " AND TYPE_ID LIKE '%,3,%'";
                }
                id = companyDao.getIdByShortName(rowList.get(6), subSql);
                if (id == null) {
                    // data not exists
                    errors.add(notExistsErr + ",fault.repairCompanyName");
                } else {
                    fault.setRepairCompanyId(id);
                }

                DateFormat format;
                String date = "";
                String time = "";
                String dateErr = "BSE00014," + i;
                String timeErr = "BSE00025," + i;
                String lenErr = "BSE00012," + i;
                if (rowList.get(7).length() < 8 || rowList.get(7).length() > 10) {
                    // length error
                    errors.add(lenErr + ",fault.occurDate,8,10");
                } else {
                    format = new DateFormat();
                    date = format.format(rowList.get(7), "fault.occurDate");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",fault.occurDate");
                    } else {
                        fault.setOccurDate(date);
                    }
                }

                if (rowList.get(8).length() < 4 || rowList.get(8).length() > 5) {
                    // length error
                    errors.add(lenErr + ",fault.occurTime,4,5");
                } else {
                    format = new DateFormat();
                    time = format.formatTime(rowList.get(8), "fault.occurTime");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(timeErr + ",fault.occurTime");
                    } else {
                        fault.setOccurTime(time);
                    }
                }
                
                String reportDateTime = fault.getReportDate() + fault.getReportTime();
                String occurDateTime = null;
                if (fault.getOccurDate() != null && fault.getOccurTime() != null) {
                    occurDateTime = fault.getOccurDate() + fault.getOccurTime();
                    if (occurDateTime.compareTo(reportDateTime) > 0) {
                        //发生日时必须在报告日时以前。
                        errors.add("BSE02124," + i);
                    }
                }

                if (rowList.get(9).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.operatorName,0,40");
                } else {
                    fault.setOperatorName(rowList.get(9));
                }

                if(rowList.get(10) != null && !"".equals(rowList.get(10))){
                    id = userDao.getIdByName(rowList.get(10),user);
                    if (id == null) {
                        // data not exists
                        errors.add(notExistsErr + ",fault.supporterName");
                    } else {
                        fault.setSupporterId(id);
                    }  
                }

                if (!"".equals(rowList.get(11))) {
                    //获取该故障对应机器的顶层机种名称。
                    String topProCategoryName = productCategoryDao.getTopProCateNameByModel(model);
                    
                    if(!"G-ABIO".equals(topProCategoryName) && !"ATM".equals(topProCategoryName)){
                        topProCategoryName="STANDARD";
                    }
                    id = faultPartDao.getIdByName(rowList.get(11),topProCategoryName);
                    if (id == null) {
                        // data not exists
                        errors.add(notExistsErr + ",fault.faultPartName");
                    } else {
                        fault.setFaultPart(id);
                        fault.setFaultMachineType(topProCategoryName);
                    }
                }

                if (!"".equals(rowList.get(12))) {
                    id = faultPartTypeDao.getIdByName(rowList.get(12));
                    if (id == null) {
                        // data not exists
                        errors.add(notExistsErr + ",fault.faultPartTypeName");
                    } else {
                        fault.setFaultPartType(id);
                    }
                }

                if (!"".equals(rowList.get(13)) && !"I".equals(rowList.get(13)) && !"S".equals(rowList.get(13))) {
                    errors.add("BSE02110," + i);
                } else {
                    fault.setIsState(rowList.get(13));
                }

                if (!"".equals(rowList.get(14))) {
                    id = appVerCheck(rowList.get(14), dicInfo);
                    if (id == null) {
                        errors.add("BSE02111," + i);
                    } else {
                        fault.setApplicationVersion(id);
                    }
                }

                if (!"".equals(rowList.get(15)) && rowList.get(15).length() > 20) {
                    // length error
                    errors.add(lenErr + ",fault.applicationVersionDetail1,0,20");
                } else {
                    fault.setApplicationVersionDetail1(rowList.get(15));
                }

                if (!"".equals(rowList.get(16)) && rowList.get(16).length() > 20) {
                    // length error
                    errors.add(lenErr + ",fault.applicationVersionDetail2,0,20");
                } else {
                    fault.setApplicationVersionDetail2(rowList.get(16));
                }

                if (!"".equals(rowList.get(17))) {
                    id = supportTypeDao.getIdByName(rowList.get(17));
                    if (id == null) {
                        // data not exists
                        errors.add(notExistsErr + ",fault.supportTypeName");
                    } else {
                        fault.setSupportType(id);
                    }
                }
                
                if (!"".equals(rowList.get(18))) {
                    if (rowList.get(18).length() < 8 || rowList.get(18).length() > 10) {
                        // length error
                        errors.add(lenErr + ",fault.finishDate,8,10");
                    } else {
                        format = new DateFormat();
                        date = format.format(rowList.get(18), "fault.finishDate");
                        if (format.hasErrors()) {
                            // type error
                            errors.add(dateErr + ",fault.finishDate");
                        } else {
                            fault.setFinishDate(date);
                        }
                    }
                }

                if (!"".equals(rowList.get(19))) {
                    if (rowList.get(19).length() < 4 || rowList.get(19).length() > 5) {
                        // length error
                        errors.add(lenErr + ",fault.finishTime,4,5");
                    } else {
                        format = new DateFormat();
                        time = format.formatTime(rowList.get(19), "fault.finishTime");
                        if (format.hasErrors()) {
                            // type error
                            errors.add(timeErr + ",fault.finishTime");
                        } else {
                            fault.setFinishTime(time);
                        }
                    }
                }
                
                String finishDateTime = null;
                if (fault.getFinishDate() != null) {
                    if (fault.getFinishTime() == null) {
                        finishDateTime = fault.getFinishDate() + "000000";
                    } else {
                        finishDateTime = fault.getFinishDate() + fault.getFinishTime();
                    }
                    if (occurDateTime != null && finishDateTime != null && occurDateTime.compareTo(finishDateTime) > 0) {
                        //结束日时必须在发生日时以后。
                        errors.add("BSE02125," + i);
                    }
                } else if ("".equals(rowList.get(18)) && fault.getFinishTime() != null) {
                    //若输入结束时间，则结束日期也必须输入。
                    errors.add("BSE02126," + i);
                }
                
                if (!"".equals(rowList.get(20))) {
                    id = occurCon1Check(rowList.get(20), dicInfo);
                    if (id == null) {
                        errors.add("BSE02112," + i);
                    } else {
                        fault.setOccurCondiction1(id);
                    }
                }
                
                if (!"".equals(rowList.get(21))) {
                    id = occurCon2Check(rowList.get(21), dicInfo);
                    if (id == null) {
                        errors.add("BSE02113," + i);
                    } else {
                        fault.setOccurCondiction2(id);
                    }
                }
                
                if (!"".equals(rowList.get(22)) && rowList.get(22).length() > 10) {
                    // length error
                    errors.add(lenErr + ",fault.errorCode,0,10");
                } else {
             
                    Pattern pattern = Pattern.compile("[^\\u4E00-\\u9FA5]*");
                    Matcher matcher2 = pattern.matcher(rowList.get(22));
                    if(!matcher2.matches()){
                        errors.add("BSE01030,"+i+",fault.errorCode");
                    }else{
                        fault.setErrorCode(rowList.get(22));
                    }
                }
                
                if (!"".equals(rowList.get(23)) && rowList.get(23).length() > 4) {
                    // length error
                    errors.add(lenErr + ",fault.rx278,0,4");
                } else {
                    fault.setRx278(rowList.get(23));
                }
                
                if (!"".equals(rowList.get(24)) && rowList.get(24).length() > 4) {
                    // length error
                    errors.add(lenErr + ",fault.counter,0,4");
                } else {
                    fault.setCounter(rowList.get(24));
                }
                
                if (rowList.get(25).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.appearance,0,40");
                } else {
                    fault.setAppearance(rowList.get(25));
                }
                
                if (!"".equals(rowList.get(26)) && rowList.get(26).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.reason,0,40");
                } else {
                    fault.setReason(rowList.get(26));
                }
                
                if (!"".equals(rowList.get(27)) && rowList.get(27).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.strategy,0,40");
                } else {
                    fault.setStrategy(rowList.get(27));
                }
                
                id = faultStatusDao.getIdByName(rowList.get(28));
                if (id == null) {
                    // data exists
                    errors.add(notExistsErr + ",fault.result");
                } else {
                    fault.setResultId(id);
                }
                
                if (!"".equals(rowList.get(29)) && rowList.get(29).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation1,0,30");
                } else {
                    fault.setOperation1(rowList.get(29));
                }
                
                if (!"".equals(rowList.get(30)) && rowList.get(30).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation2,0,30");
                } else {
                    fault.setOperation2(rowList.get(30));
                }
                
                if (!"".equals(rowList.get(31)) && rowList.get(31).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation3,0,30");
                } else {
                    fault.setOperation3(rowList.get(31));
                }
                
                if (!"".equals(rowList.get(32)) && rowList.get(32).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation4,0,30");
                } else {
                    fault.setOperation4(rowList.get(32));
                }
                
                if (!"".equals(rowList.get(33)) && rowList.get(33).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation5,0,30");
                } else {
                    fault.setOperation5(rowList.get(33));
                }
                
                if (!"".equals(rowList.get(34)) && rowList.get(34).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation6,0,30");
                } else {
                    fault.setOperation6(rowList.get(34));
                }
                
                if (!"".equals(rowList.get(35)) && rowList.get(35).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation7,0,30");
                } else {
                    fault.setOperation7(rowList.get(35));
                }
                
                if (!"".equals(rowList.get(36)) && rowList.get(36).length() > 30) {
                    // length error
                    errors.add(lenErr + ",fault.operation8,0,30");
                } else {
                    fault.setOperation8(rowList.get(36));
                }
                
                String hasNotHasErr = "BSE02114," + i;
                if (!"".equals(rowList.get(37))) {
                    id = hasNotHasDataCheck(rowList.get(37), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.cashLeft");
                    } else {
                        fault.setCashLeft(id);
                    }
                }
                
                if (!"".equals(rowList.get(38))) {
                    id = hasNotHasDataCheck(rowList.get(38), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.exceptionDisplay");
                    } else {
                        fault.setExceptionDisplay(id);
                    }
                }
                
                if (!"".equals(rowList.get(39))) {
                    id = hasNotHasDataCheck(rowList.get(39), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.display");
                    } else {
                        fault.setDisplay(id);
                    }
                }
                
                if (!"".equals(rowList.get(40))) {
                    id = hasNotHasDataCheck(rowList.get(40), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.backLight");
                    } else {
                        fault.setBackLight(id);
                    }
                }
                
                if (!"".equals(rowList.get(41)) && rowList.get(41).length() > 15) {
                    // length error
                    errors.add(lenErr + ",fault.displayContent,0,15");
                } else {
                    fault.setDisplayContent(rowList.get(41));
                }

                if (!"".equals(rowList.get(42))) {
                    id = hasNotHasDataCheck(rowList.get(42), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.inputable");
                    } else {
                        fault.setInputable(id);
                    }
                }

                if (!"".equals(rowList.get(43))) {
                    id = hasNotHasDataCheck(rowList.get(43), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.knockSound");
                    } else {
                        fault.setKnockSound(id);
                    }
                }

                if (!"".equals(rowList.get(44))) {
                    id = resetCheck(rowList.get(44), dicInfo);
                    if (id == null) {
                        errors.add("BSE02115," + i);
                    } else {
                        fault.setReset(id);
                    }
                }

                if (!"".equals(rowList.get(45))) {
                    id = cutPowerCheck(rowList.get(45), dicInfo);
                    if (id == null) {
                        errors.add("BSE02117," + i);
                    } else {
                        fault.setCutPower(id);
                    }
                }

                if (!"".equals(rowList.get(46))) {
                    id = rebootNormallyCheck(rowList.get(46), dicInfo);
                    if (id == null) {
                        errors.add("BSE02118," + i);
                    } else {
                        fault.setRebootNormally(id);
                    }
                }
                
                if (!"".equals(rowList.get(47)) && rowList.get(47).length() > 4) {
                    // length error
                    errors.add(lenErr + ",fault.motionCounter,0,4");
                } else {
                    fault.setMotionCounter(rowList.get(47));
                }
                
                if (!"".equals(rowList.get(48)) && rowList.get(48).length() > 10) {
                    // length error
                    errors.add(lenErr + ",fault.errorNo,0,10");
                } else {
                    fault.setErrorNo(rowList.get(48));
                }
                
                if (!"".equals(rowList.get(49)) && rowList.get(49).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.noRepon,0,40");
                } else {
                    fault.setNoRepon(rowList.get(49));
                }
                
                if (!"".equals(rowList.get(50)) && rowList.get(50).length() > 40) {
                    // length error
                    errors.add(lenErr + ",fault.otherDisplay,0,40");
                } else {
                    fault.setOtherDisplay(rowList.get(50));
                }
                
                if (!"".equals(rowList.get(51))) {
                    id = hasNotHasDataCheck(rowList.get(51), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.traceInfomation");
                    } else {
                        fault.setTraceInfomation(id);
                    }
                }

                if (!"".equals(rowList.get(52))) {
                    id = hasNotHasDataCheck(rowList.get(52), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.faultRecord");
                    } else {
                        fault.setFaultRecord(id);
                    }
                }

                if (!"".equals(rowList.get(53))) {
                    id = hasNotHasDataCheck(rowList.get(53), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.statistics");
                    } else {
                        fault.setStatistics(id);
                    }
                }

                if (!"".equals(rowList.get(54))) {
                    id = hasNotHasDataCheck(rowList.get(54), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.tradeLog");
                    } else {
                        fault.setTradeLog(id);
                    }
                }

                if (!"".equals(rowList.get(55))) {
                    id = hasNotHasDataCheck(rowList.get(55), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.applicationVersion1");
                    } else {
                        fault.setApplicationVersion1(id);
                    }
                }

                if (!"".equals(rowList.get(56))) {
                    id = hasNotHasDataCheck(rowList.get(56), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.cras");
                    } else {
                        fault.setCras(id);
                    }
                }

                if (!"".equals(rowList.get(57))) {
                    id = hasNotHasDataCheck(rowList.get(57), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.dras");
                    } else {
                        fault.setDras(id);
                    }
                }

                if (!"".equals(rowList.get(58))) {
                    id = hasNotHasDataCheck(rowList.get(58), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.mcuLog");
                    } else {
                        fault.setMcuLog(id);
                    }
                }

                if (!"".equals(rowList.get(59))) {
                    id = hasNotHasDataCheck(rowList.get(59), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.systemEvent");
                    } else {
                        fault.setSystemEvent(id);
                    }
                }

                if (!"".equals(rowList.get(60))) {
                    id = hasNotHasDataCheck(rowList.get(60), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.dbillbox");
                    } else {
                        fault.setDbillbox(id);
                    }
                }

                if (!"".equals(rowList.get(61))) {
                    id = hasNotHasDataCheck(rowList.get(61), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.dcollect");
                    } else {
                        fault.setDcollect(id);
                    }
                }

                if (!"".equals(rowList.get(62))) {
                    id = hasNotHasDataCheck(rowList.get(62), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.cfep");
                    } else {
                        fault.setCfep(id);
                    }
                }

                if (!"".equals(rowList.get(63))) {
                    id = hasNotHasDataCheck(rowList.get(63), dicInfo);
                    if (id == null) {
                        errors.add(hasNotHasErr + ",fault.cerr");
                    } else {
                        fault.setCerr(id);
                    }
                }
                
                if (!"".equals(rowList.get(64)) && rowList.get(64).length() > 80) {
                    // length error
                    errors.add(lenErr + ",fault.others,0,80");
                } else {
                    fault.setOthers(rowList.get(64));
                }
                
                if (!"".equals(rowList.get(65))) {
                    if (rowList.get(65).length() < 8 || rowList.get(65).length() > 10) {
                        // length error
                        errors.add(lenErr + ",fault.receiveDate,8,10");
                    } else {
                        format = new DateFormat();
                        date = format.format(rowList.get(65), "fault.receiveDate");
                        if (format.hasErrors()) {
                            // type error
                            errors.add(dateErr + ",fault.receiveDate");
                        } else {
                            fault.setReceiveDate(date);
                        }
                    }
                }
                
                if (!"".equals(rowList.get(66))) {
                    if (rowList.get(66).length() < 8 || rowList.get(66).length() > 10) {
                        // length error
                        errors.add(lenErr + ",fault.returnDate,8,10");
                    } else {
                        format = new DateFormat();
                        date = format.format(rowList.get(66), "fault.returnDate");
                        if (format.hasErrors()) {
                            // type error
                            errors.add(dateErr + ",fault.returnDate");
                        } else {
                            fault.setReturnDate(date);
                        }
                    }
                }
                
                if(rowList.get(67) != null && !"".equals(rowList.get(67))){
                    id = userDao.getIdByName(rowList.get(67),user);
                    if (id == null) {
                        // data exists
                        errors.add(notExistsErr + ",fault.followerName");
                    } else {
                        fault.setFollowerId(id);
                    }
                }

                if (!"".equals(rowList.get(68)) && rowList.get(68).length() > 800) {
                    // length error
                    errors.add(lenErr + ",fault.strategyDetail,0,800");
                } else {
                    fault.setStrategyDetail(rowList.get(68));
                }
                
                if (errors.size() > 0) {
                    break;
                }
                faultList.add(fault);
            }
            
            if (errors.size() == 0) {
                for (int index = 0; index < faultList.size(); index++) {
                    Fault faultIn = faultList.get(index);
                    // 日立管理编号编辑
                    companyCode = companyDao.getCodeByCompanyId(faultIn.getRepairCompanyId());
                    if(companyCode!=null){
                        companyCode = companyCode.toUpperCase();
                    }
                    String dateOfManagementId = faultIn.getReportDate().substring(2);
                    ArrayList<String> managementIdList = faultDao.getManagementIdList(companyCode + "%", faultIn
                            .getReportDate());
                    if (managementIdList == null || managementIdList.size() == 0) {
                        managementId = companyCode + "-" + dateOfManagementId + "-001";
                    } else {
                        int seq = 0;
                        for (int i = 0; i < managementIdList.size(); i++) {
                            String tmpMngId = managementIdList.get(i);
                            int pos = tmpMngId.lastIndexOf("-");
                            String seqStr = "";
                            seqStr = tmpMngId.substring(pos + 1, tmpMngId.length());
                            if (Integer.parseInt(seqStr) > seq) {
                                seq = Integer.parseInt(seqStr);
                            }
                        }
                        seq++;
                        if (seq < 10) {
                            managementId = companyCode + "-" + dateOfManagementId + "-00" + String.valueOf(seq);
                        } else if (seq < 100) {
                            managementId = companyCode + "-" + dateOfManagementId + "-0" + String.valueOf(seq);
                        } else {
                            managementId = companyCode + "-" + dateOfManagementId + "-" + String.valueOf(seq);
                        }
                    }
                    // ID取得
                    Integer faultId = new GetPK(conn).getPK("FAULT_TBL_ID_SEQ");
                    faultIn.setId(faultId);
                    faultIn.setManagementId(managementId);
                    faultIn.setRepairState(0);
                    faultIn.setLocked(0);
                    faultIn.setDeleted(0);
                    faultIn.setAffirmFlag(0);
                    faultIn.setCreatorId(user.getId());
                    faultDao.insert(faultIn);
                    faultDetailDao.insert(faultIn);
                }
            }
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }


    /**
     * search fault header for register.
     * @author luyan
     * @since 1.0
     * @param install installation information
     * @return fault header information
     */
    public Fault searchFaultHeaderFr(Installation install) {
        Fault fault = new Fault();

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            fault = faultDao.getFaultHeaderFr(install.getId(), TimeUtil.getNowDate());
            if (fault.getManufactureNo() == null) {
                errors.add("BSF00006");
            } else {
                DateFormat dateFormat = new DateFormat();
                fault.setGuaranteeDate(dateFormat.changeDate(fault.getGuaranteeStartDate())
                        + " - " + dateFormat.changeDate(fault.getGuaranteeEndDate()));
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return fault;
    }

    /**
     * insert fault information business.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param faultIn fault information
     */
    public void insertFault(User loginUser, Fault faultIn, List<FaultSpares> faultSparesListIn) {

        try {
            init();

            // Start UOC
            int count = 0;
            String companyCode = "";
            String managementId = "";
            boolean errFlg = false;
            FaultDao faultDao = new FaultDao(conn);
            FaultDetailDao faultDetailDao = new FaultDetailDao(conn);
            FaultSparesDao faultSparesDao = new FaultSparesDao(conn);
            FaultTypeDao faultTypeDao = new FaultTypeDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            UserDao userDao = new UserDao(conn);
            FaultPartDao faultPartDao = new FaultPartDao(conn);
            FaultPartTypeDao faultPartTypeDao = new FaultPartTypeDao(conn);
            SupportTypeDao supportTypeDao = new SupportTypeDao(conn);
            FaultStatusDao faultStatusDao = new FaultStatusDao(conn);
            InstallationDao installationDao = new InstallationDao(conn);

            //check
            //if　画面.故障类别＝＂硬件＂
            if (Integer.valueOf(1).compareTo(faultIn.getFaultType()) == 0
                    && faultIn.getFaultPart() == null) {
                errors.add("BSE02108");
                errFlg = true;
            }

            //日期时间检查
            DateFormat dateFormat = new DateFormat();
            faultIn.setOccurDate(dateFormat.format(faultIn.getOccurDate(), "fault.occurDate"));
            faultIn.setOccurTime(dateFormat.formatTime(faultIn.getOccurTime(), "fault.occurTime"));
            faultIn.setReportDate(dateFormat.format(faultIn.getReportDate(), "fault.reportDate"));
            faultIn.setReportTime(dateFormat.formatTime(faultIn.getReportTime(), "fault.reportTime"));
            if (faultIn.getFinishDate() != null && faultIn.getFinishDate().trim().length() > 0) {
                faultIn.setFinishDate(dateFormat.format(faultIn.getFinishDate(), "fault.finishDate"));
            }
            if (faultIn.getFinishTime() != null && faultIn.getFinishTime().trim().length() > 0) {
                faultIn.setFinishTime(dateFormat.formatTime(faultIn.getFinishTime(), "fault.finishTime"));
            }
            if (dateFormat.hasErrors()) {
                errors.addAll(dateFormat.getErrors());
                errFlg = true;
            }

            //使用备件部分check
            for (int i = 0; i < faultSparesListIn.size(); i++) {
                String tmpDate = null;
                FaultSpares faultSpares = faultSparesListIn.get(i);
                if (faultSpares.getName() != null && faultSpares.getName().trim().length() > 0) {
                    if (faultSpares.getNo() == null || faultSpares.getNo().trim().length() == 0) {
                        errors.add("BSE02129," + (i + 1) + ",fault.no");
                        errFlg = true;
                    } else {
                        try {
                            int number = Integer.parseInt(faultSpares.getNo());
                            faultSpares.setNo(String.valueOf(number));
                        } catch (Exception e) {
                            errors.add("BSE02133," + (i + 1) + ",fault.no");
                            errFlg = true;
                        }
                    }
                    if (faultSpares.getApplyDate() != null && faultSpares.getApplyDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getApplyDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.applyDate");
                            errFlg = true;
                        } else {
                            faultSpares.setApplyDate(tmpDate);
                        }
                    } else {
                        errors.add("BSE02129," + (i + 1) + ",fault.applyDate");
                        errFlg = true;
                    }
                    if (faultSpares.getDeliverDate()!= null && faultSpares.getDeliverDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getDeliverDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.deliverDate");
                            errFlg = true;
                        } else {
                            faultSpares.setDeliverDate(tmpDate);
                        }
                    }
                    if (faultSpares.getReplaceDate()!= null && faultSpares.getReplaceDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getReplaceDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.replaceDate");
                            errFlg = true;
                        } else {
                            faultSpares.setReplaceDate(tmpDate);
                        }
                    }
                    if (faultSpares.getReceiveDate()!= null && faultSpares.getReceiveDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getReceiveDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.ihscReceiveDate");
                            errFlg = true;
                        } else {
                            faultSpares.setReceiveDate(tmpDate);
                        }
                    }
                }
            }

            //日期时间关联检查
            String reportDateTime = faultIn.getReportDate() + faultIn.getReportTime();
            String occurDateTime = null;
            String finishDateTime = null;
            if (!dateFormat.dateCheck(faultIn.getOccurDate())
                    && dateFormat.timeCheck(faultIn.getOccurTime())) {
                occurDateTime = faultIn.getOccurDate() + faultIn.getOccurTime();
                if (occurDateTime.compareTo(reportDateTime) > 0) {
                    //发生日时必须在报告日时以前。
                    errors.add("BSE02127");
                    errFlg = true;
                }
            }
            if (faultIn.getFinishDate() != null
                    && faultIn.getFinishDate().trim().length() != 0
                    && !dateFormat.dateCheck(faultIn.getFinishDate())) {
                if (faultIn.getFinishTime() == null || faultIn.getFinishTime().trim().length() == 0) {
                    finishDateTime = faultIn.getFinishDate() + "000000";
                } else if (dateFormat.timeCheck(faultIn.getFinishTime())) {
                    finishDateTime = faultIn.getFinishDate() + faultIn.getFinishTime();
                }
                if (occurDateTime != null && finishDateTime != null && occurDateTime.compareTo(finishDateTime) > 0) {
                    //结束日时必须在发生日时以后。
                    errors.add("BSE02122");
                    errFlg = true;
                }
            } else if ((faultIn.getFinishDate() == null || faultIn.getFinishDate().trim().length() == 0)
                    && (faultIn.getFinishTime() != null && faultIn.getFinishTime().trim().length() != 0)) {
                //若输入结束时间，则结束日期也必须输入。
                errors.add("BSE02123");
                errFlg = true;
            }

            //所选故障类别在《故障类别表》存在检查
            count = faultTypeDao.getCountByID(faultIn.getFaultType());
            if (count <= 0) {
                errors.add("BSE00017,fault.faultType");
                errFlg = true;
            }

            //所选维修公司在《公司表》存在检查
            count = companyDao.getCountByCompanyId(faultIn.getRepairCompanyId());
            if (count <= 0) {
                errors.add("BSE01012,fault.repairCompanyId");
                errFlg = true;
            }

            //所选受理者在《用户表》存在检查
            if (faultIn.getSupporterId() != null) {
                count = userDao.getCountById(faultIn.getSupporterId(),loginUser);
                if (count <= 0) {
                    errors.add("BSE02102");
                    errFlg = true;
                }
            }

            //所选跟踪者在《用户表》存在检查
            if (faultIn.getFollowerId() != null) {
                count = userDao.getCountById(faultIn.getFollowerId(),loginUser);
                if (count <= 0) {
                    errors.add("BSE02102");
                    errFlg = true;
                }
            }

            //所选故障部位在《故障部位表》存在检查
            if (faultIn.getFaultPart() != null) {
                count = faultPartDao.getCountByID(faultIn.getFaultPart());
                if (count <= 0) {
                    errors.add("BSE00017,fault.faultPart");
                    errFlg = true;
                }
            }

            //所选故障种类在《故障部位种类表》存在检查
            if (faultIn.getFaultPartType() != null) {
                count = faultPartTypeDao.getCountByID(faultIn.getFaultPartType());
                if (count <= 0) {
                    errors.add("BSE00017,fault.faultPartType");
                    errFlg = true;
                }
            }

            //所选支援方式在《支援方式表》存在检查
            if (faultIn.getSupportType() != null) {
                count = supportTypeDao.getCountByID(faultIn.getSupportType());
                if (count <= 0) {
                    errors.add("BSE00017,fault.supportType");
                    errFlg = true;
                }
            }

            //所选故障状态在《故障状态表》存在检查
            if (faultIn.getResultId() != null) {
                count = faultStatusDao.getCountByID(faultIn.getResultId());
                if (count <= 0) {
                    errors.add("BSE00017,fault.resultId");
                    errFlg = true;
                }
            }

            //安装编号存在检查
            count = installationDao.getCountById(faultIn.getInstallationId());
            if (count <= 0) {
                errors.add("BSE02104");
                errFlg = true;
            }

            Integer  resultId  = faultIn.getResultId();
            if(Integer.valueOf(2).compareTo(resultId) == 0){
                //如果状态为完了时，则结束前必选项必须填写
                if(faultIn.getFaultType()==1){    
                    if (faultIn.getFaultPart() == null
                          || Integer.valueOf(0).compareTo(faultIn.getFaultPart()) >= 0) {
                      errors.add("BSE02139,fault.faultPart");
                      errFlg = true;
                    }
                  }
                  if (faultIn.getFaultPartType() == null
                          || Integer.valueOf(0).compareTo(faultIn.getFaultPartType()) >= 0) {
                      errors.add("BSE02139,fault.faultPartType");
                      errFlg = true;
                  }
                  if (faultIn.getIsState() == null || faultIn.getIsState().trim().length() == 0) {
                      errors.add("BSE02139,fault.isState");
                      errFlg = true;
                  }
                  if (faultIn.getSupportType() == null
                          || Integer.valueOf(0).compareTo(faultIn.getSupportType()) >= 0) {
                      errors.add("BSE02139,fault.supportType");
                      errFlg = true;
                  }
                  if (faultIn.getFinishDate() == null || faultIn.getFinishDate().trim().length() == 0) {
                      errors.add("BSE02140,fault.finishDate");
                      errFlg = true;
                  }
                  if (faultIn.getFinishTime() == null || faultIn.getFinishTime().trim().length() == 0) {
                      errors.add("BSE02140,fault.finishTime");
                      errFlg = true;
                  }
                  if (faultIn.getReason() == null || faultIn.getReason().trim().length() == 0) {
                      errors.add("BSE02140,fault.reason");
                      errFlg = true;
                  }
                  if (faultIn.getStrategy() == null || faultIn.getStrategy().trim().length() == 0) {
                      errors.add("BSE02140,fault.strategy");
                      errFlg = true;
                  }
            }
           
            //insert
            if (!errFlg) {
                //日立管理编号编辑
                companyCode = companyDao.getCodeByCompanyId(faultIn.getRepairCompanyId());
                if(companyCode!=null){
                    companyCode = companyCode.toUpperCase();
                }
                String dateOfManagementId = faultIn.getReportDate().substring(2);
                ArrayList<String> managementIdList = faultDao.getManagementIdList(companyCode + "%", faultIn.getReportDate());
                if (managementIdList == null || managementIdList.size() == 0) {
                    managementId = companyCode + "-" + dateOfManagementId + "-001";
                } else {
                    int seq = 0;
                    for (int i = 0; i < managementIdList.size(); i++) {
                        String tmpMngId = managementIdList.get(i);
                        int pos = tmpMngId.lastIndexOf("-");
                        String seqStr = "";
                        seqStr = tmpMngId.substring(pos + 1, tmpMngId.length());
                        if (Integer.parseInt(seqStr) > seq) {
                            seq=Integer.parseInt(seqStr);
                        }
                    }
                    seq++;
                    if (seq < 10) {
                        managementId = companyCode + "-" + dateOfManagementId + "-00" + String.valueOf(seq);
                    } else if (seq < 100) {
                        managementId = companyCode + "-" + dateOfManagementId + "-0" + String.valueOf(seq);
                    } else {
                        managementId = companyCode + "-" + dateOfManagementId + "-" + String.valueOf(seq);
                    }
                }
                //过滤使用备件list
                List<FaultSpares> faultSparesList = new ArrayList<FaultSpares>();
                for (FaultSpares faultSpares:faultSparesListIn) {
                    if (faultSpares.getName() != null && faultSpares.getName().trim().length() > 0) {
                        faultSparesList.add(faultSpares);
                    }
                }
                //获取故障详细表中的申请日、发送日、更换日、ISHC收到日
                String apply_date = "00000000";
                String deliver_date = "00000000";
                String replace_date = "00000000";
                String receive_date = "00000000";
                for (int i = 0; i < faultSparesList.size(); i++) {
                    if (i == 0) {
                        apply_date = faultSparesList.get(i).getApplyDate();
                        replace_date = faultSparesList.get(i).getReplaceDate();
                    }
                    if (faultSparesList.get(i).getDeliverDate() != null
                            && faultSparesList.get(i).getDeliverDate().trim().length() > 0
                            && deliver_date.compareTo(faultSparesList.get(i).getDeliverDate().trim()) < 0) {
                        deliver_date = faultSparesList.get(i).getDeliverDate().trim();
                    }
                    if (faultSparesList.get(i).getReceiveDate() != null
                            && faultSparesList.get(i).getReceiveDate().trim().length() > 0
                            && receive_date.compareTo(faultSparesList.get(i).getReceiveDate().trim()) < 0) {
                        receive_date = faultSparesList.get(i).getReceiveDate().trim();
                    }
                }
                //ID取得
                Integer faultId = new GetPK(conn).getPK("FAULT_TBL_ID_SEQ");
                faultIn.setId(faultId);
                faultIn.setManagementId(managementId);
                if (!"00000000".equals(apply_date)) {
                    faultIn.setApplyDate(apply_date);
                }
                if (!"00000000".equals(deliver_date)) {
                    faultIn.setDeliverDate(deliver_date);
                }
                if (!"00000000".equals(replace_date)) {
                    faultIn.setReplaceDate(replace_date);
                }
                if (!"00000000".equals(receive_date)) {
                    faultIn.setIhscReceiveDate(receive_date);
                }
                faultIn.setRepairState(0);
                faultIn.setLocked(0);
                faultIn.setDeleted(0);
                faultIn.setAffirmFlag(0);
                faultIn.setCreatorId(loginUser.getId());
                faultDao.insert(faultIn);
//                faultDetailDao.insert(faultIn);
                faultDetailDao.insertAbio(faultIn);
                for (FaultSpares faultSpares:faultSparesList) {
                    faultSpares.setFaultId(faultId);
                    faultSparesDao.insert(faultSpares);
                }
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

    
    /**
     * search fault detail.
     * @author luyan
     * @since 1.0
     * @param faultIdIn fault ID
     * @return fault detail information
     */
    public Fault searchFaultDetailById(Integer faultIdIn, String managementIdIn) {
        Fault fault = new Fault();

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            if(managementIdIn != null){
                managementIdIn = managementIdIn.toUpperCase();
            }
            
            fault = faultDao.getFaultDetailById(faultIdIn, managementIdIn);
            if (fault.getId() == null) {
                errors.add("BSF00006");
                return fault;
            }
            Fault faultHeader = new Fault();
            faultHeader = faultDao.getFaultHeaderFr(fault.getInstallationId(), fault.getReportDate());
            if (faultHeader.getManufactureNo() == null) {
                errors.add("BSF00006");
            } else {
                DateFormat dateFormat = new DateFormat();
                fault.setModel(faultHeader.getModel());
                fault.setProductCategoryName(faultHeader.getProductCategoryName());
                fault.setCityName(faultHeader.getCityName());
                fault.setCustomerName(faultHeader.getCustomerName());
                fault.setManufactureNo(faultHeader.getManufactureNo());
                fault.setPurpose(faultHeader.getPurpose());
                fault.setGuaranteeStartDate(faultHeader.getGuaranteeStartDate());
                fault.setGuaranteeEndDate(faultHeader.getGuaranteeEndDate());
                fault.setGuaranteeDate(dateFormat.changeDate(faultHeader.getGuaranteeStartDate())
                        + " - " + dateFormat.changeDate(faultHeader.getGuaranteeEndDate()));
//                fault.setBrmEpVer(faultHeader.getBrmEpVer());
//                fault.setBvEpVer(faultHeader.getBvEpVer());
                fault.setAgentId(faultHeader.getAgentId());
                fault.setInstallDate(faultHeader.getInstallDate());
                fault.setRepairCompanyId(faultHeader.getRepairCompanyId());
                fault.setRepairCompanyName(faultHeader.getRepairCompanyName());
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return fault;
    }

    /**
     * search fault information by fault ID for modify.
     * @author luyan
     * @since 1.0
     * @param faultIn fault ID and exclusive key
     * @return fault information
     */
    public Fault searchFaultByIdFm(Fault faultIn) {
        Fault fault = new Fault();

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            fault = faultDao.getFaultByIdFm(faultIn);
            if (fault.getId() == null) {
                errors.add("BSF00006");
            }
            fault.setManagementIdHidden(fault.getManagementId());

            Fault faultHeader = new Fault();
            faultHeader = faultDao.getFaultHeaderFr(fault.getInstallationId(), fault.getReportDate());
            if (faultHeader.getManufactureNo() == null) {
                errors.add("BSF00006");
            } else {
                DateFormat dateFormat = new DateFormat();
                fault.setModel(faultHeader.getModel());
                fault.setProductCategoryName(faultHeader.getProductCategoryName());
                fault.setCityName(faultHeader.getCityName());
                fault.setCustomerName(faultHeader.getCustomerName());
                fault.setManufactureNo(faultHeader.getManufactureNo());
                fault.setPurpose(faultHeader.getPurpose());
                fault.setGuaranteeStartDate(faultHeader.getGuaranteeStartDate());
                fault.setGuaranteeEndDate(faultHeader.getGuaranteeEndDate());
                fault.setGuaranteeDate(dateFormat.changeDate(faultHeader.getGuaranteeStartDate())
                        + " - " + dateFormat.changeDate(faultHeader.getGuaranteeEndDate()));
//                fault.setBrmEpVer(faultHeader.getBrmEpVer());
//                fault.setBvEpVer(faultHeader.getBvEpVer());
                fault.setInstallDate(faultHeader.getInstallDate());
                fault.setRepairCompanyId(faultHeader.getRepairCompanyId());
                fault.setRepairCompanyName(faultHeader.getRepairCompanyName());
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return fault;
    }

    /**
     * search fault spares by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIdIn fault ID
     * @return fault spares information
     */
    public List<FaultSpares> searchFaultSparesByFaultId(Integer faultIdIn) {
        List<FaultSpares> faultSparesListOut = new ArrayList<FaultSpares>();

        try {
            init();

            // Start UOC
            FaultSparesDao faultSparesDao = new FaultSparesDao(conn);
            faultSparesListOut = faultSparesDao.getFaultSparesListByFaultId(faultIdIn);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultSparesListOut;
    }

    /**
     * modify fault information business.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param faultIn fault information
     */
    public void modifyFault(User loginUser, Fault faultIn, List<FaultSpares> faultSparesListIn) {

        try {
            init();

            // Start UOC
            int count = 0;
            String managementId = "";
            FaultDao faultDao = new FaultDao(conn);
            FaultDetailDao faultDetailDao = new FaultDetailDao(conn);
            FaultSparesDao faultSparesDao = new FaultSparesDao(conn);
            FaultTypeDao faultTypeDao = new FaultTypeDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            UserDao userDao = new UserDao(conn);
            FaultPartDao faultPartDao = new FaultPartDao(conn);
            FaultPartTypeDao faultPartTypeDao = new FaultPartTypeDao(conn);
            SupportTypeDao supportTypeDao = new SupportTypeDao(conn);
            FaultStatusDao faultStatusDao = new FaultStatusDao(conn);

            //check
            //if　画面.故障类别＝＂硬件＂
            if (Integer.valueOf(1).compareTo(faultIn.getFaultType()) == 0
                    && faultIn.getFaultPart() == null) {
                errors.add("BSE02108");
            }

            //日立管理编号形式检查
            if (!faultIn.getManagementIdHidden().equals(faultIn.getManagementId())) {
                int oldPos = faultIn.getManagementIdHidden().lastIndexOf("-");
                int newPos = faultIn.getManagementId().lastIndexOf("-");
                if (!faultIn.getManagementIdHidden().substring(0, oldPos + 1)
                        .equals(faultIn.getManagementId().substring(0, newPos + 1))) {
                    errors.add("BSE02105");
                }
                try {
                    int seq = new Integer(faultIn.getManagementId().substring(newPos + 1));
                    if (seq <= 0) {
                        errors.add("BSE02106");
                        managementId = faultIn.getManagementId();
                    } else if (seq < 10) {
                        managementId = faultIn.getManagementId().substring(0, newPos + 1) + "00" + String.valueOf(seq);
                    } else if (seq < 100) {
                        managementId = faultIn.getManagementId().substring(0, newPos + 1) + "0" + String.valueOf(seq);
                    } else {
                        managementId = faultIn.getManagementId().substring(0, newPos + 1) + String.valueOf(seq);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    errors.add("BSE02106");
                    managementId = faultIn.getManagementId();
                }

                //日立管理编号存在检查
                count = faultDao.getCountByManagementId(managementId);
                if (count > 0) {
                    errors.add("BSE02107");
                }
            } else {
                managementId = faultIn.getManagementIdHidden();
            }

            //日期时间检查
            DateFormat dateFormat = new DateFormat();
            faultIn.setOccurDate(dateFormat.format(faultIn.getOccurDate(), "fault.occurDate"));
            faultIn.setOccurTime(dateFormat.formatTime(faultIn.getOccurTime(), "fault.occurTime"));
            if (faultIn.getFinishDate() != null && faultIn.getFinishDate().trim().length() > 0) {
                faultIn.setFinishDate(dateFormat.format(faultIn.getFinishDate(), "fault.finishDate"));
            }
            if (faultIn.getFinishTime() != null && faultIn.getFinishTime().trim().length() > 0) {
                faultIn.setFinishTime(dateFormat.formatTime(faultIn.getFinishTime(), "fault.finishTime"));
            }
            if (dateFormat.hasErrors()) {
                errors.addAll(dateFormat.getErrors());
            }

            //使用备件部分check
            for (int i = 0; i < faultSparesListIn.size(); i++) {
                String tmpDate = null;
                FaultSpares faultSpares = faultSparesListIn.get(i);
                if (faultSpares.getName() != null && faultSpares.getName().trim().length() > 0) {
                    if (faultSpares.getNo() == null || faultSpares.getNo().trim().length() == 0) {
                        errors.add("BSE02129," + (i + 1) + ",fault.no");
                    } else {
                        try {
                            int number = Integer.parseInt(faultSpares.getNo());
                            faultSpares.setNo(String.valueOf(number));
                        } catch (Exception e) {
                            errors.add("BSE02133," + (i + 1) + ",fault.no");
                        }
                    }
                    if (faultSpares.getApplyDate()!= null && faultSpares.getApplyDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getApplyDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.applyDate");
                        } else {
                            faultSpares.setApplyDate(tmpDate);
                        }
                    } else {
                        errors.add("BSE02129," + (i + 1) + ",fault.applyDate");
                    }
                    if (faultSpares.getDeliverDate()!= null && faultSpares.getDeliverDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getDeliverDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.deliverDate");
                        } else {
                            faultSpares.setDeliverDate(tmpDate);
                        }
                    }
                    if (faultSpares.getReplaceDate()!= null && faultSpares.getReplaceDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getReplaceDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.replaceDate");
                        } else {
                            faultSpares.setReplaceDate(tmpDate);
                        }
                    }
                    if (faultSpares.getReceiveDate()!= null && faultSpares.getReceiveDate().trim().length() > 0) {
                        tmpDate = dateFormat.format(faultSpares.getReceiveDate());
                        if (tmpDate == null) {
                            errors.add("BSE02128," + (i + 1) + ",fault.ihscReceiveDate");
                        } else {
                            faultSpares.setReceiveDate(tmpDate);
                        }
                    }
                }
            }

            //日期时间关联检查
            String reportDateTime = dateFormat.format(faultIn.getReportDate(), "fault.reportDate")
                    + dateFormat.formatTime(faultIn.getReportTime(), "fault.reportTime");
            String occurDateTime = null;
            String finishDateTime = null;
            if (!dateFormat.dateCheck(faultIn.getOccurDate())
                    && dateFormat.timeCheck(faultIn.getOccurTime())) {
                occurDateTime = faultIn.getOccurDate() + faultIn.getOccurTime();
                if (occurDateTime.compareTo(reportDateTime) > 0) {
                    //发生日时必须在报告日时以前。
                    errors.add("BSE02121");
                }
            }
            if (faultIn.getFinishDate() != null
                    && faultIn.getFinishDate().trim().length() != 0
                    && !dateFormat.dateCheck(faultIn.getFinishDate())) {
                if (faultIn.getFinishTime() == null || faultIn.getFinishTime().trim().length() == 0) {
                    finishDateTime = faultIn.getFinishDate() + "000000";
                } else if (dateFormat.timeCheck(faultIn.getFinishTime())) {
                    finishDateTime = faultIn.getFinishDate() + faultIn.getFinishTime();
                }
                if (occurDateTime != null && finishDateTime != null && occurDateTime.compareTo(finishDateTime) > 0) {
                    //结束日时必须在发生日时以后。
                    errors.add("BSE02122");
                }
            } else if ((faultIn.getFinishDate() == null || faultIn.getFinishDate().trim().length() == 0)
                    && (faultIn.getFinishTime() != null && faultIn.getFinishTime().trim().length() != 0)) {
                //若输入结束时间，则结束日期也必须输入。
                errors.add("BSE02123");
            }

            //所选故障类别在《故障类别表》存在检查
            count = faultTypeDao.getCountByID(faultIn.getFaultType());
            if (count <= 0) {
                errors.add("BSE00017,fault.faultType");
            }

            //所选维修公司在《公司表》存在检查
            count = companyDao.getCountByCompanyId(faultIn.getRepairCompanyId());
            if (count <= 0) {
                errors.add("BSE01012,fault.repairCompanyId");
            }

            //所选受理者在《用户表》存在检查
            if (faultIn.getSupporterId() != null) {
                count = userDao.getCountById(faultIn.getSupporterId(),loginUser);
                if (count <= 0) {
                    errors.add("BSE02102");
                }
            }

            //所选跟踪者在《用户表》存在检查
            if (faultIn.getFollowerId() != null) {
                count = userDao.getCountById(faultIn.getFollowerId(),loginUser);
                if (count <= 0) {
                    errors.add("BSE02102");
                }
            }

            //所选故障部位在《故障部位表》存在检查
            if (faultIn.getFaultPart() != null) {
                count = faultPartDao.getCountByID(faultIn.getFaultPart());
                if (count <= 0) {
                    errors.add("BSE00017,fault.faultPart");
                }
            }

            //所选故障种类在《故障部位种类表》存在检查
            if (faultIn.getFaultPartType() != null) {
                count = faultPartTypeDao.getCountByID(faultIn.getFaultPartType());
                if (count <= 0) {
                    errors.add("BSE00017,fault.faultPartType");
                }
            }

            //所选支援方式在《支援方式表》存在检查
            if (faultIn.getSupportType() != null) {
                count = supportTypeDao.getCountByID(faultIn.getSupportType());
                if (count <= 0) {
                    errors.add("BSE00017,fault.supportType");
                }
            }

            //所选故障状态在《故障状态表》存在检查
            if (faultIn.getResultId() != null) {
                count = faultStatusDao.getCountByID(faultIn.getResultId());
                if (count <= 0) {
                    errors.add("BSE00017,fault.resultId");
                }
            }

            //2010/02/23 add start
            Integer  repairState  = faultIn.getResultId();
            if(Integer.valueOf(2).compareTo(repairState) == 0){
                //如果状态为完了时，则结束前必选项必须填写
                if(faultIn.getFaultType()==1){    
                    if (faultIn.getFaultPart() == null
                          || Integer.valueOf(0).compareTo(faultIn.getFaultPart()) >= 0) {
                      errors.add("BSE02139,fault.faultPart");
                    }
                  }
                  if (faultIn.getFaultPartType() == null
                          || Integer.valueOf(0).compareTo(faultIn.getFaultPartType()) >= 0) {
                      errors.add("BSE02139,fault.faultPartType");
                  }
                  if (faultIn.getIsState() == null || faultIn.getIsState().trim().length() == 0) {
                      errors.add("BSE02139,fault.isState");
                  }
                  if (faultIn.getSupportType() == null
                          || Integer.valueOf(0).compareTo(faultIn.getSupportType()) >= 0) {
                      errors.add("BSE02139,fault.supportType");
                  }
                  if (faultIn.getFinishDate() == null || faultIn.getFinishDate().trim().length() == 0) {
                      errors.add("BSE02140,fault.finishDate");
                  }
                  if (faultIn.getFinishTime() == null || faultIn.getFinishTime().trim().length() == 0) {
                      errors.add("BSE02140,fault.finishTime");
                  }
                  if (faultIn.getReason() == null || faultIn.getReason().trim().length() == 0) {
                      errors.add("BSE02140,fault.reason");
                  }
                  if (faultIn.getStrategy() == null || faultIn.getStrategy().trim().length() == 0) {
                      errors.add("BSE02140,fault.strategy");
                  }
            }
            
            //2010/02/23 add end
            
            //insert
            if (errors.size() == 0) {
                //过滤使用备件list
                List<FaultSpares> faultSparesList = new ArrayList<FaultSpares>();
                for (FaultSpares faultSpares:faultSparesListIn) {
                    if (faultSpares.getName() != null && faultSpares.getName().trim().length() > 0) {
                        faultSparesList.add(faultSpares);
                    }
                }
                //获取故障详细表中的申请日、发送日、更换日、ISHC收到日
                String apply_date = "00000000";
                String deliver_date = "00000000";
                String replace_date = "00000000";
                String receive_date = "00000000";
                for (int i = 0; i < faultSparesList.size(); i++) {
                    if (i == 0) {
                        apply_date = faultSparesList.get(i).getApplyDate();
                        replace_date = faultSparesList.get(i).getReplaceDate();
                    }
                    if (faultSparesList.get(i).getDeliverDate() != null
                            && faultSparesList.get(i).getDeliverDate().trim().length() > 0
                            && deliver_date.compareTo(faultSparesList.get(i).getDeliverDate().trim()) < 0) {
                        deliver_date = faultSparesList.get(i).getDeliverDate().trim();
                    }
                    if (faultSparesList.get(i).getReceiveDate() != null
                            && faultSparesList.get(i).getReceiveDate().trim().length() > 0
                            && receive_date.compareTo(faultSparesList.get(i).getReceiveDate().trim()) < 0) {
                        receive_date = faultSparesList.get(i).getReceiveDate().trim();
                    }
                }
                //update
                faultIn.setManagementId(managementId);
                if (!"00000000".equals(apply_date)) {
                    faultIn.setApplyDate(apply_date);
                }
                if (!"00000000".equals(deliver_date)) {
                    faultIn.setDeliverDate(deliver_date);
                }
                if (!"00000000".equals(replace_date)) {
                    faultIn.setReplaceDate(replace_date);
                }
                if (!"00000000".equals(receive_date)) {
                    faultIn.setIhscReceiveDate(receive_date);
                }
                faultIn.setModifierId(loginUser.getId());
                faultDao.modifyById(faultIn);
//                faultDetailDao.modifyById(faultIn);
                faultDetailDao.modifyAbioById(faultIn);
                faultSparesDao.deleteByFaultId(faultIn.getId());
                for (FaultSpares faultSpares:faultSparesList) {
                    faultSpares.setFaultId(faultIn.getId());
                    faultSparesDao.insert(faultSpares);
                }
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

    /**
     * modify fault information by ID for business delete/recover.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param faultIn fault information
     * @param delFlg delete flag
     */
    public void modifyFaultFd(User loginUser, Fault faultIn, Integer delFlg) {

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);

            //delete/recover
            faultIn.setModifierId(loginUser.getId());
            faultIn.setDeleted(delFlg);
            faultDao.modifyByIdFd(faultIn);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

    /**
     * modify fault informations that are selected
     * @author sunyx
     * @param finishChkList
     * @param loginUser
     */
    public void modifySelectFaultFf(List<Integer> finishChkList,User loginUser) {

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            Fault fault = new Fault();

            for(int i=1;i<=finishChkList.size();i++){
                Integer faultId = finishChkList.get(i-1);
                // get fault by id
                fault = faultDao.getFaultById(faultId);
            
                // input check.
                if(fault.getId() != null){
                    
                    if(fault.getRepairState() == 1){
                        errors.add("BSE02135," + fault.getManagementId());
                    }else{
                        if(fault.getResultId()!= 2){
                            errors.add("BSE02142," + fault.getManagementId());
                        }
                        
                        if(fault.getFaultType()==1){
                            if (fault.getFaultPart() == null
                                    || Integer.valueOf(0).compareTo(fault.getFaultPart()) >= 0) {
                                errors.add("BSE02130," + fault.getManagementId() + ",fault.faultPart");
                            }
                        }
                        if (fault.getFaultPartType() == null
                                || Integer.valueOf(0).compareTo(fault.getFaultPartType()) >= 0) {
                            errors.add("BSE02130," + fault.getManagementId() + ",fault.faultPartType");
                        }
                        if (fault.getIsState() == null || fault.getIsState().trim().length() == 0) {
                            errors.add("BSE02130," + fault.getManagementId() + ",fault.isState");
                        }
                        if (fault.getSupportType() == null
                                || Integer.valueOf(0).compareTo(fault.getSupportType()) >= 0) {
                            errors.add("BSE02130," + fault.getManagementId() + ",fault.supportType");
                        }
                        if (fault.getFinishDate() == null || fault.getFinishDate().trim().length() == 0) {
                            errors.add("BSE02131," + fault.getManagementId() + ",fault.finishDate");
                        }
                        if (fault.getFinishTime() == null || fault.getFinishTime().trim().length() == 0) {
                            errors.add("BSE02131," + fault.getManagementId() + ",fault.finishTime");
                        }
                        if (fault.getReason() == null || fault.getReason().trim().length() == 0) {
                            errors.add("BSE02131," + fault.getManagementId() + ",fault.reason");
                        }
                        if (fault.getStrategy() == null || fault.getStrategy().trim().length() == 0) {
                            errors.add("BSE02131," + fault.getManagementId() + ",fault.strategy");
                        } 
                    }
                    
                    // finish fault
                        fault.setModifierId(loginUser.getId());
                        fault.setRepairState(ONE);
                        faultDao.setExclusiveCheck(false);
                        faultDao.modifyByIdFf(fault);
                }
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }
    
    /**
     * modify fault information by ID for finish/cancel finish.
     * @author luyan
     * @since 1.0
     * @param loginUser login user information
     * @param faultIn fault information
     * @param finishFlg finish flag
     */
    public void modifyFaultFf(User loginUser, Fault faultIn, Integer finishFlg) {

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);

            // input check.
            //结束故障操作
            Fault dbFault = faultDao.getFaultById(faultIn.getId());
            if(dbFault==null){
                //该记录不存在则报错
                errors.add("BSF00006");
                
            }else{
                    if (Integer.valueOf(1).equals(finishFlg)) {
                        if(dbFault.getFaultType()==1){    
                          if (dbFault.getFaultPart() == null
                                || Integer.valueOf(0).compareTo(dbFault.getFaultPart()) >= 0) {
                            errors.add("BSE02119,fault.faultPart");
                          }
                        }
                        if (dbFault.getFaultPartType() == null
                                || Integer.valueOf(0).compareTo(dbFault.getFaultPartType()) >= 0) {
                            errors.add("BSE02119,fault.faultPartType");
                        }
                        if (dbFault.getIsState() == null || dbFault.getIsState().trim().length() == 0) {
                            errors.add("BSE02119,fault.isState");
                        }
                        if (dbFault.getSupportType() == null
                                || Integer.valueOf(0).compareTo(dbFault.getSupportType()) >= 0) {
                            errors.add("BSE02119,fault.supportType");
                        }
                        if (dbFault.getFinishDate() == null || dbFault.getFinishDate().trim().length() == 0) {
                            errors.add("BSE02120,fault.finishDate");
                        }
                        if (dbFault.getFinishTime() == null || dbFault.getFinishTime().trim().length() == 0) {
                            errors.add("BSE02120,fault.finishTime");
                        }
                        if (dbFault.getReason() == null || dbFault.getReason().trim().length() == 0) {
                            errors.add("BSE02120,fault.reason");
                        }
                        if (dbFault.getStrategy() == null || dbFault.getStrategy().trim().length() == 0) {
                            errors.add("BSE02120,fault.strategy");
                        }
                        if(dbFault.getResultId() != 2 ){
                            errors.add("BSE02141,fault.resultId");
                        }
                    }
                    //取消故障操作...
                   
        
                    // finish/cancel finish
                    if (errors.size() == 0) {
                        dbFault.setModifierId(loginUser.getId());
                        dbFault.setRepairState(finishFlg);
                        faultDao.modifyByIdFf(dbFault);
                    }
            
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

    /**
     * delete fault information by ID.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     */
    public void deleteFault(Fault faultIn) {

        try {
            init();

            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            FaultDetailDao faultDetailDao = new FaultDetailDao(conn);
            FaultHandleDao faultHandleDao = new FaultHandleDao(conn);
            FaultSparesDao faultSparesDao = new FaultSparesDao(conn);
            // business deleted check.
            Fault fault = faultDao.getFaultByIdFm(faultIn);
            if (Integer.valueOf(0).equals(fault.getDeleted())) {
                errors.add("BSE00008");
                return;
            }

            // fault handle information exist check.
            int count = faultHandleDao.getCountByFaultId(faultIn.getId());
            if (count > 0) {
                errors.add("BSE02116");
                return;
            }

            // delete
            //2010/01/12
            faultSparesDao.deleteByFaultId(faultIn.getId());
            
            faultDetailDao.deleteById(faultIn);
            faultDao.deleteById(faultIn);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（1:有、0:无、其他：空）
     */
    private Integer hasNotHasDataCheck(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[0].equals(inputData)) {
            outData = ONE;
        } else if (dic[1].equals(inputData)) {
            outData = ZERO;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（0:日立AP、1:统一AP、其他：空）
     */
    private Integer appVerCheck(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[2].equals(inputData)) {
            outData = ZERO;
        } else if (dic[3].equals(inputData)) {
            outData = ONE;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（0:装置正在启动中、1:正在交易中、2:工作人员正在处理中、其他：空）
     */
    private Integer occurCon1Check(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[4].equals(inputData)) {
            outData = ZERO;
        } else if (dic[5].equals(inputData)) {
            outData = ONE;
        } else if (dic[6].equals(inputData)) {
            outData = TWO;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（0:支付、1:存款、2:余额确认、3:装填、4:回收、5:TEST中、6:待机中、7:其它、其他：空）
     */
    private Integer occurCon2Check(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[7].equals(inputData)) {
            outData = ZERO;
        } else if (dic[8].equals(inputData)) {
            outData = ONE;
        } else if (dic[9].equals(inputData)) {
            outData = TWO;
        } else if (dic[10].equals(inputData)) {
            outData = THREE;
        } else if (dic[11].equals(inputData)) {
            outData = FOUR;
        } else if (dic[12].equals(inputData)) {
            outData = FIVE;
        } else if (dic[13].equals(inputData)) {
            outData = SIX;
        } else if (dic[14].equals(inputData)) {
            outData = SEVEN;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（1:能、0:不能、其他：空）
     */
    private Integer resetCheck(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[15].equals(inputData)) {
            outData = ONE;
        } else if (dic[16].equals(inputData)) {
            outData = ZERO;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（1:可、0:否、其他：空）
     */
    private Integer cutPowerCheck(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[17].equals(inputData)) {
            outData = ONE;
        } else if (dic[18].equals(inputData)) {
            outData = ZERO;
        }
        return outData;
    }

    /**
     * @author liugd
     * @param inputData
     * @param dic
     * @return outData:返回（1:正常启动了、0:没启动、其他：空）
     */
    private Integer rebootNormallyCheck(String inputData, String[] dic) {
        Integer outData = null;
        if (dic[19].equals(inputData)) {
            outData = ONE;
        } else if (dic[20].equals(inputData)) {
            outData = ZERO;
        }
        return outData;
    }
}
