//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.AgentProductDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.InstApplyDao;
import cn.com.bhh.erp.dao.InstPlaceTypeDao;
import cn.com.bhh.erp.dao.InstallationDao;
import cn.com.bhh.erp.dao.InstallationHistoryDao;
import cn.com.bhh.erp.dao.OSDao;
import cn.com.bhh.erp.dao.OsPermitDao;
import cn.com.bhh.erp.dao.PlatformDao;
import cn.com.bhh.erp.dao.ProductDao;
import cn.com.bhh.erp.dao.PurposeDao;
import cn.com.bhh.erp.dao.UseStatusDao;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.User;

/**
 * intallation business
 * 
 * @author liugd
 * @version 1.0
 * @since 1.0
 * 
 */
public class InstallationBusiness extends BaseBusiness {

    /**
     * serch use status information
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws Exception
     */
    public List<Installation> serchUseStatus(Installation install, User user, int currPage, int pageSize)
            throws Exception {
        List<Installation> installationList = new ArrayList<Installation>();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }
            installationList = installationDao.serchUseStatus(install, user, intBegin, intEnd);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installationList;
    }

    /**
     * GET INSTALLATION COUNT
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @return int
     * @throws Exception
     */
    public int getInstallationCount(Installation install, User user) throws Exception {
        int count = 0;

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);
            count = installationDao.getInstallationCount(install, user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return count;
    }

    /**
     * GET INSTALLATION DATA FOR DOWNLOAD
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws Exception
     */
    public List<Installation> getDownLoadData(Installation install, User user, int countSize) throws Exception {
        List<Installation> installationList = new ArrayList<Installation>();

        try {
            init();

            // Start UOC
            int count = 0;
            InstallationDao installationDao = new InstallationDao(conn);
            count = installationDao.getInstallationCount(install, user);
            if (count == 0) {
                errors.add("BSE01707");
            } else {
                if (count < countSize) {
                    countSize = count;
                }
                installationList = installationDao.serchUseStatus(install, user, 0, countSize);
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installationList;
    }
    
    
    /**
     * GET CHECKED INSTALLATION DATA FOR DOWNLOAD
     * 
     * @author xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws Exception
     */
    public List<Installation> getDownLoadCheckedData(Installation install,List<Integer> idList, User user) throws Exception {
        List<Installation> installationList = new ArrayList<Installation>();

        try {
            init();

            // Start UOC
           
            InstallationDao installationDao = new InstallationDao(conn);
            
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
            
            installationList = installationDao.serchUseStatusChecked(install,strBuf.toString(), user);
            int count = installationList.size();
            if (count == 0) {
                errors.add("BSE01707");
            } 
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installationList;
    }

    /**
     * get Intallation by id,exclusiveKey
     * 
     * @author liugd
     * @param id
     * @param exclusiveKey
     * @return Installation
     * @throws Exception
     */
    public Installation getInstallationByIdExclusive(Integer id, Integer exclusiveKey) throws Exception {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            installation = installationDao.getInstallationByIdExclusive(id, exclusiveKey);
            if (installation.getDeleted().compareTo(new Integer(0)) != 0) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installation;
    }

    /**
     * get Intallation by id
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param id
     * @param exclusiveKey
     * @return Installation
     * @throws Exception
     */
    public Installation getInstallationById(Integer id) throws Exception {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            installation = installationDao.getInstallationById(id);
            if (installation.getId() == null) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installation;
    }

    /**
     * delete
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param id
     * @param exclusiveKey
     * @throws Exception
     */
    public void delete(Integer id, Integer exclusiveKey) throws Exception {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            // delete check
            installation = installationDao.getInstallationById(id);
            if (installation.getId() == null) {
                errors.add("BSF00006");
                return;
            } else {
                if (installation.getDeleted().compareTo(new Integer(1)) != 0) {
                    // data not logic delete
                    errors.add("BSE00008");
                    return;
                }
            }

            FaultDao faultDao = new FaultDao(conn);
            if (faultDao.getFaultCountByInstallId(id) > 0) {
                // has fault information
                errors.add("BSE01705");
                return;
            }

            InstApplyDao instApplyDao = new InstApplyDao(conn);
            if (instApplyDao.getCountByInstallId(id) > 0) {
                // has apply information
                errors.add("BSE01711");
                return;
            }

            // installation delete
            installationDao.deleteById(id, exclusiveKey);

            // installation history delete
            if (installation.getStartDate().compareTo("00000000") != 0) {
                InstallationHistoryDao installationHistoryDao = new InstallationHistoryDao(conn);
                installationHistoryDao.deleteById(id);
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get installation by manufactureNo
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param manufactureNo
     * @return Installation
     * @throws Exception
     */
    public Installation getInstallByMamuNo(Installation install, User user) throws Exception {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);
            String subSql = "";
            if (!user.filter("install_mng_all_data")) {
                subSql = subSql + " AND A.NOW_REPAIR_COMPANY_ID = " + user.getCompanyID();
            }
            if (!user.hasPermission("PR005_34")) {
                subSql = subSql + " AND A.DELETED = '0'";
            }
            installation = installationDao.getInstallByMamuNo(install.getManufactureNo(),install.getProductCategoryId(), subSql);

            if (installation.getManufactureNo() == null || "".equals(installation.getManufactureNo())) {
                // data not exists
                errors.add("BSE01704");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installation;
    }

    public List<Installation> serchUseStatus(User user) throws Exception {
        List<Installation> installationList = new ArrayList<Installation>();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);
            String subSql = "";
            if (!user.filter("install_mng_all_data")) {
                subSql = subSql + " AND A.NOW_REPAIR_COMPANY_ID = " + user.getCompanyID();
            }
            installationList = installationDao.serchUseStatus(subSql);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installationList;
    }

    /**
     * serch detail use status information
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param id
     * @return Installation
     * @throws Exception
     */
    public Installation serchUseStatusDetail(Integer id, User user) throws Exception {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);
            String subSql = "";
            if (!user.filter("install_mng_all_data")) {
                subSql = subSql + " AND A.NOW_REPAIR_COMPANY_ID = " + user.getCompanyID();
            }
            if (!user.hasPermission("PR005_34")) {
                subSql = subSql + " AND A.DELETED = '0'";
            }
            installation = installationDao.serchUseStatusDetail(id, subSql);
            if (installation.getId() == null) {
                errors.add("BSE01712");
            }
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return installation;
    }

    /**
     * insert installation
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void createInstallation(Installation installation, User user) throws Exception {
        try {
            init();

            // Start UOC

            // date check
            if (!"".equals(installation.getFobDate())
                    && (installation.getInstallDate().compareTo(installation.getFobDate()) < 0)) {
                errors.add("BSE01713");
            }

            if (!"".equals(installation.getOpenDate())
                    && (installation.getOpenDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01720");
            }

            if (!"".equals(installation.getAcceptDate())
                    && (installation.getAcceptDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01701");
            }

            if (!"".equals(installation.getAcceptDate()) && !"".equals(installation.getOpenDate())
                    && (installation.getAcceptDate().compareTo(installation.getOpenDate()) < 0)) {
                errors.add("BSE01721");
            }

            if (installation.getGuaranteeEndDate().compareTo(installation.getGuaranteeStartDate()) < 0) {
                errors.add("BSE01702");
            }

            if (errors.size() > 0) {
                return;
            }

            InstallationDao installationDao = new InstallationDao(conn);

            if (installationDao.getCountByManufactureNo(installation.getManufactureNo(), installation.getProductCategoryId()) > 0) {
                // data exists
                errors.add("BSE00000,install.manufactureNo");

                return;
            }

            ProductDao productDao = new ProductDao(conn);

            if (productDao.getCountById(installation.getProductId()) == 0) {
                // data not exists
                errors.add("BSE00006,install.productId");
                return;
            }
            
            
            

            companyCheck(installation, user, "1");
            //check whether the repair company has agent this product.
            
            if (errors.size() > 0) {
                return;
            }
            
            if(productDao.getCountByAgentId(installation.getProductId(),installation.getInstallCompId()) == 0){
                errors.add("BSE01729,install.installCompId");
                return;
            }

            InstPlaceTypeDao iptd = new InstPlaceTypeDao(conn);
            if (iptd.getCountByID(installation.getInstPlaceTypeId()) == 0) {
                // data not exists
                errors.add("BSE00017,install.instPlaceTypeId");
                return;
            }

            UseStatusDao UseStatusDao = new UseStatusDao(conn);
            if (UseStatusDao.getCountByID(installation.getUseStatusId()) == 0) {
                // data not exists
                errors.add("BSE00017,install.useStatusId");
                return;
            }

            PurposeDao PurposeDao = new PurposeDao(conn);
            if (PurposeDao.getCountByID(installation.getPurpose()) == 0) {
                // data not exists
                errors.add("BSE00017,install.purpose");
                return;
            }

            if (installation.getOs() != null && !"".equals(installation.getOs())) {
                OSDao OSDao = new OSDao(conn);
                if (OSDao.getCountById(installation.getOs().intValue()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.OS");
                    return;
                }
            }
            
            //2009/08/20 add
            if (installation.getOsPermitId() != null) {
                OsPermitDao osPermitDao = new OsPermitDao(conn);
                if (osPermitDao.getCountById(installation.getOsPermitId().intValue())==0) {
                    // data not exists
                    errors.add("BSE00017,install.osPermit");
                    return;
                }
            }

            if(installation.getPlatform()!=null){
                PlatformDao PlatformDao = new PlatformDao(conn);
                if (PlatformDao.getCountById(installation.getPlatform()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.platform");
                    return;
                }
            }else{
                installation.setPlatform(-1);
            }

            // create the installation
            installation.setCreateTime(TimeUtil.getNow());
            installation.setModifyTime(TimeUtil.getNow());
            installationDao.create(installation);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * installation upload
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void upload(ArrayList excelList, User user) throws Exception {
        try {
            init();

            // Start UOC
            ProductDao productDao = new ProductDao(conn);
            InstallationDao installationDao = new InstallationDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            UseStatusDao useStatusDao = new UseStatusDao(conn);
            InstPlaceTypeDao instPlaceTypeDao = new InstPlaceTypeDao(conn);
            PurposeDao purposeDao = new PurposeDao(conn);
            OSDao oSDao = new OSDao(conn);
            OsPermitDao osPermitDao = new OsPermitDao(conn);
            PlatformDao platformDao = new PlatformDao(conn);
            // date input check
            ArrayList<String> rowList;
            ArrayList<Installation> installList = new ArrayList<Installation>();
            Installation installation;
            List<Product> productNameList = null;
            if (!user.filter("prod_cate_mng_all_data")) {
                AgentProductDao agentProductDao = new AgentProductDao(conn);
                productNameList = agentProductDao.serchAllProductByAgentId(user.getCompanyID());
            }
            for (int i = 2; i <= excelList.size() + 1; i++) {
                rowList = (ArrayList<String>) excelList.get(i - 2);
                String customerCompanyMainNameInCell = rowList.get(0);  //用户公司  0
                String subCompanyNameInCell = rowList.get(1);           //分店名 1
                String branchCompanyNameInCell = rowList.get(4);        //支店名4
                String installPlaceInCell = rowList.get(5);             //安装地点5
                String instPlaceTypeIdInCell = rowList.get(6);          //安装地点类型6
                String contactInCell = rowList.get(7);                  //银行联系人7
                String officePhoneInCell = rowList.get(8);              //座机8
                String mobilePhoneInCell = rowList.get(9);              //手机9
                String faxInCell = rowList.get(10);                      //传真10
                String emailInCell = rowList.get(11);                    //邮箱11
                String productCategoryNameInCell = rowList.get(12);      //产品机种 12
                String pruductModelInCell = rowList.get(13);             //产品型号13
                String manufactureNoInCell = rowList.get(14);            //制造号14
                String indentureNoInCell = rowList.get(15);              //合同编号15
                String fobDateInCell = rowList.get(16);                  //FOB 16
                String installDateInCell = rowList.get(17);              //安装日期 17
                String openDateInCell = rowList.get(18);                 //开通日18
                String acceptDateInCell = rowList.get(19);               //验收日期19
                String guaranteeStartDateInCell = rowList.get(20);       //保修开始日20
                String guaranteeEndDateInCell = rowList.get(21);         //保修结束日21
                String guaranteePeriodInCell = rowList.get(22);          //保修期（月） 22
                String useStatusIdInCell = rowList.get(23);              //使用状态 23
                String purposeInCell = rowList.get(24);                  //用途 24
                String brmEpVerInCell = rowList.get(25);                 //BRM_EP  25
                String bvEpVerInCell = rowList.get(26);                  //BV_EP  26
                String keyNoInCell = rowList.get(27);                    //钥匙号  27
                String OSInCell = rowList.get(28);                       //OS  28
                String jprInCell = rowList.get(29);                      //JPR 29
                String platformInCell = rowList.get(30);                 //平台  30 
                String platformRevInCell = rowList.get(31);              //平台REV  31
                String sprInCell = rowList.get(32);                      //SPR 32
                String mcuInCell = rowList.get(33);                      //MCU 33
                String deskeyInCell = rowList.get(34);                   //DESKEY 34 
                String hcmInCell = rowList.get(35);                      //HCM 35
                String bvInCell = rowList.get(36);                       //BV 36
                String noteInCell = rowList.get(37);                     //备注   37
                String saleContractCompNameInCell = rowList.get(38);     //销售商 38 
                String installCompanyNameInCell = rowList.get(39);       //安装公司  39
                String nowRepairCompanyNameLabelInCell = rowList.get(40);//维修公司（现在维修公司） 40 
                String installerInCell = rowList.get(41);                //安装人  41
                String installerIdInCell = rowList.get(42);              //安装人编号  42
                String osPermitInCell = rowList.get(43);                 //OS许可项 43
                
                installation = new Installation();
                // not null check
                String nullErr = "BSE00009," + i;
                if ("".equals(pruductModelInCell)) {
                    errors.add(nullErr + ",install.pruductModel");
                }
                if ("".equals(manufactureNoInCell)) {
                    errors.add(nullErr + ",install.manufactureNo");
                }
                if ("".equals(customerCompanyMainNameInCell)) {
                    errors.add(nullErr + ",install.customerCompany");
                }
                if ("".equals(saleContractCompNameInCell)) {
                    errors.add(nullErr + ",install.saleContractCompId");
                }
                if ("".equals(installDateInCell)) {
                    errors.add(nullErr + ",install.installDate");
                }
                if ("".equals(guaranteeStartDateInCell)) {
                    errors.add(nullErr + ",install.guaranteeStartDate");
                }
                if ("".equals(guaranteeEndDateInCell)) {
                    errors.add(nullErr + ",install.guaranteeEndDate");
                }
                if ("".equals(branchCompanyNameInCell)) {
                    errors.add(nullErr + ",install.branchCompanyName");
                }
                if ("".equals(useStatusIdInCell)) {
                    errors.add(nullErr + ",install.useStatusId");
                }
                if ("".equals(instPlaceTypeIdInCell)) {
                    errors.add(nullErr + ",install.instPlaceTypeId");
                }
                if ("".equals(purposeInCell)) {
                    errors.add(nullErr + ",install.purpose");
                }
                if ("".equals(installCompanyNameInCell)) {
                    errors.add(nullErr + ",install.installCompId");
                }
//                if ("".equals(subCompanyNameInCell)) {
//                    errors.add(nullErr + ",install.subCompanyName");
//                }
//                if ("".equals(platformInCell)) {
//                    errors.add(nullErr + ",install.platform");
//                }
//                if ("".equals(platformRevInCell)) {
//                    errors.add(nullErr + ",install.platformRev");
//                }

                if (errors.size() > 0) {
                    break;
                }

                String notExistsErr = "BSE00015," + i;

                Integer productId = null;
                if (!user.filter("prod_cate_mng_all_data")) {
                    for (int index = 0; index < productNameList.size(); index++) {
                        if (productNameList.get(index).getModel().equals(pruductModelInCell)) {
                            productId = productNameList.get(index).getId();
                            break;
                        }
                    }
                } else {
                    productId = productDao.getIdByModel(pruductModelInCell);
                }
                if (productId == null) {
                    // data not exists
                    errors.add(notExistsErr + ",install.pruductModel");
                    break;
                } else {
                    installation.setProductId(productId);
                }

                Pattern p = Pattern.compile("\\S{1,10}");
                Matcher matcher = p.matcher(manufactureNoInCell);
//                if (manufactureNoInCell.length()<1 || manufactureNoInCell.length() > 10) {
                if(!matcher.matches()){
                    // length error
                    errors.add("BSE01710," + i + ",install.manufactureNo");
                    break;
                } else {
//                    try {
//                        Integer.parseInt(manufactureNoInCell);
                             
                        Installation tempInstall = installationDao.getInstallByMamuNoAndProCateName(manufactureNoInCell, productCategoryNameInCell,"");
                        if (tempInstall.getId() != null) {
                            // data exists
                            if (!user.filter("install_mng_all_data")
                                    && (tempInstall.getNowRepairCompanyId().compareTo(user.getCompanyID()) != 0)) {
                                // no premission
                                errors.add("BSE00022," + i);
                                break;
                            }
                            if (tempInstall.getDeleted().compareTo(new Integer(0)) != 0) {
                                // date has been logic deleted
                                errors.add("BSE01716," + i);
                                break;
                            }
                            if (tempInstall.getAffirmFlag().compareTo(new Integer(0)) != 0) {
                                // data has been arrirmed
                                errors.add("BSE01717," + i);
                                break;
                            }
                            
                            // id for update
                            installation.setId(tempInstall.getId());
                            installation.setExclusiveKey(tempInstall.getExclusiveKey());
                            installation.setAffirmFlag(tempInstall.getAffirmFlag());
                            
                            //check whether nowRepairCompany is existed
                            String subSql=null;
                            if (!user.filter("company_mng_all_data")) {
                                subSql =" AND ID = " + user.getCompanyID() + "  AND TYPE_ID LIKE '%,3,%' ";
                            } else {
                                subSql = " AND (TYPE_ID LIKE '%,3,%') ";
                            }
                            
//                            Integer nowRepairCompanyId = companyDao.getIdByName(nowRepairCompanyNameLabelInCell, "", subSql);
                            Integer nowRepairCompanyId = companyDao.getIdByShortName(nowRepairCompanyNameLabelInCell,subSql);
                            if (nowRepairCompanyId == null) {
                                // data not exists
                                errors.add(notExistsErr + ",install.nowRepairCompanyNameLabel");
                                break;
                            } else {
                                installation.setNowRepairCompanyId(nowRepairCompanyId);
                                installation.setFirstRepairCompanyId(nowRepairCompanyId);
                            }
                            
                          
                        }
                        installation.setManufactureNo(manufactureNoInCell);
                        for (int j = i + 1; j <= excelList.size() + 1; j++) {
                            ArrayList<String> forwordRowList = (ArrayList<String>) excelList.get(j - 2);//获取前
                            if (manufactureNoInCell.equals(forwordRowList.get(14)) 
                                    && productCategoryNameInCell.equals(forwordRowList.get(12))) {///?????????????????????制造号重复？？？
                                // data Repeat
                                errors.add("BSE00024," + i + "," + j + ",install.manufactureNo");
                                break;
                            }
                        }
//                    } catch (Exception e) {
//                        // type error
//                        errors.add("BSE01710," + i + ",install.manufactureNo");
//                        break;
//                    }
                }

                String subSql = "";
                Integer companyID = null;
                boolean isRight = true;
                if (!user.filter("company_mng_all_data")) {
                    subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 0 AND AGENT_ID="
                        + user.getCompanyID() + " ) " +
                        " OR ID = " + user.getCompanyID() + " ) AND (TYPE_ID LIKE '%,4,%') ";
                } else {
                    subSql = " AND (TYPE_ID LIKE '%,4,%') ";
                }

                companyID = companyDao.getIdByName(customerCompanyMainNameInCell, subCompanyNameInCell, subSql);
                if (companyID == null) {
                    // data not exists
                    errors.add(notExistsErr + ",install.customerCompany");
                    isRight = false;
                    break;
                } else {
                    installation.setCustomerId(companyID);
                }

                if (!user.filter("company_mng_all_data")) {
                    subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 1 AND AGENT_ID="
                        + user.getCompanyID() + " ) " + 
                        " OR ID = " + user.getCompanyID() + ") AND (TYPE_ID LIKE '%,2,%') ";
                } else {
                    subSql = " AND (TYPE_ID LIKE '%,2,%') ";
                }
//                companyID = companyDao.getIdByName(saleContractCompNameInCell, "", subSql);
                companyID = companyDao.getIdByShortName(saleContractCompNameInCell, subSql);
                if (companyID == null) {
                    // data not exists
                    errors.add(notExistsErr + ",install.saleContractCompId");
                    isRight = false;
                    break;
                } else {
                    installation.setSaleContractCompId(companyID);
                }

                DateFormat format;
                String date = "";
                String dateErr = "BSE00014," + i;
                String lenErr = "BSE00012," + i;
                if (!"".equals(fobDateInCell) && (fobDateInCell.length() < 8 || fobDateInCell.length() > 10)) {
                    // length error
                    errors.add(lenErr + ",install.fobDate,8,10");
                    break;
                } else {
                    format = new DateFormat();
                    date = format.format(fobDateInCell, "install.fobDate");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",install.fobDate");
                        break;
                    } else {
                        installation.setFobDate(date);
                    }
                }//hhhhhhhhhhhhhhhhhhhhh

                if (installDateInCell.length() < 8 || installDateInCell.length() > 10) {
                    // length error
                    errors.add(lenErr + ",install.installDate,8,10");
                    break;
                } else {
                    format = new DateFormat();
                    date = format.format(installDateInCell, "install.installDate");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",install.installDate");
                        break;
                    } else {
                        installation.setInstallDate(date);
                    }
                }

                if (installation.getFobDate() != null && installation.getInstallDate() != null
                        && (installation.getInstallDate().compareTo(installation.getFobDate()) < 0)) {
                    // date input error
                    errors.add("BSE01714," + i);
                    break;
                }
                
                if(openDateInCell != null
                        && !"".equals(openDateInCell)){
                    if (openDateInCell.length() < 8 || openDateInCell.length() > 10) {
                        // length error
                        errors.add(lenErr + ",install.openDate,8,10");
                        break;
                    } else {
                        format = new DateFormat();
                        date = format.format(openDateInCell, "install.openDate");
                        if (format.hasErrors()) {
                            // type error
                            errors.add(dateErr + ",install.openDate");
                            break;
                        } else {
                            installation.setOpenDate(date);
                        }
                    }
                }

                if (installation.getOpenDate() != null && installation.getInstallDate() != null
                        && (installation.getOpenDate().compareTo(installation.getInstallDate()) < 0)) {
                    // date input error
                    errors.add("BSE01723," + i);
                    break;
                }

                if (!"".equals(acceptDateInCell) && (acceptDateInCell.length() < 8 || acceptDateInCell.length() > 10)) {
                    // length error
                    errors.add(lenErr + ",install.acceptDate,8,10");
                    break;
                } else if (!"".equals(acceptDateInCell)) {
                    format = new DateFormat();
                    date = format.format(acceptDateInCell, "install.acceptDate");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",install.acceptDate");
                        break;
                    } else {
                        installation.setAcceptDate(date);
                    }
                }

                if (installation.getInstallDate() != null && installation.getAcceptDate() != null
                        && !"".equals(acceptDateInCell)
                        && (installation.getAcceptDate().compareTo(installation.getInstallDate()) < 0)) {
                    // date input error
                    errors.add("BSE01708," + i);
                    break;
                }
                
                if (installation.getOpenDate() != null && installation.getAcceptDate() != null
                        && !"".equals(acceptDateInCell)
                        && (installation.getAcceptDate().compareTo(installation.getOpenDate()) < 0)) {
                    // date input error
                    errors.add("BSE01724," + i);
                    break;
                }

                if (guaranteeStartDateInCell.length() < 8 || guaranteeStartDateInCell.length() > 10) {
                    // length error
                    errors.add(lenErr + ",install.guaranteeStartDate,8,10");
                    break;
                } else {
                    format = new DateFormat();
                    date = format.format(guaranteeStartDateInCell, "install.guaranteeStartDate");
                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",install.guaranteeStartDate");
                        break;
                    } else {
                        installation.setGuaranteeStartDate(date);
                    }
                }

                if (guaranteeEndDateInCell.length() < 8 || guaranteeEndDateInCell.length() > 10) {
                    // length error
                    errors.add(lenErr + ",install.guaranteeEndDate,8,10");
                    break;
                } else {
                    format = new DateFormat();
                    date = format.format(guaranteeEndDateInCell, "install.guaranteeEndDate");

                    if (format.hasErrors()) {
                        // type error
                        errors.add(dateErr + ",install.guaranteeEndDate");
                        break;
                    } else {
                        installation.setGuaranteeEndDate(date);
                    }
                }

                if (installation.getGuaranteeStartDate() != null && installation.getGuaranteeEndDate() != null
                        && (installation.getGuaranteeEndDate().compareTo(installation.getGuaranteeStartDate()) < 0)) {
                    // date input error
                    errors.add("BSE01709," + i);
                    break;
                }

                if (!"".equals(guaranteePeriodInCell) && guaranteePeriodInCell.length() > 2) {
                    // length error
                    errors.add(lenErr + ",install.guaranteePeriod,0,2");
                    break;
                } else if (!"".equals(guaranteePeriodInCell)) {
                    try {
                        Integer.parseInt(guaranteePeriodInCell);
                        installation.setGuaranteePeriod(guaranteePeriodInCell);
                    } catch (Exception e) {
                        // type error
                        errors.add("BSE01718," + i);
                        break;
                    }
                }

                Integer useStatusId = useStatusDao.getIdByName(useStatusIdInCell);
                if (useStatusId == null) {
                    // data not exists
                    errors.add("BSE00018," + i + ",install.useStatusId");
                    break;
                } else {
                    installation.setUseStatusId(useStatusId);
                }

                Integer instPlaceType = instPlaceTypeDao.getIdByName(instPlaceTypeIdInCell);
                if (instPlaceType == null) {
                    // data not exists
                    errors.add("BSE00018," + i + ",install.instPlaceTypeId");
                    break;
                } else {
                    installation.setInstPlaceTypeId(instPlaceType);
                }

                Integer purpose = purposeDao.getIdByName(purposeInCell);
                if (purpose == null) {
                    // data not exists
                    errors.add("BSE00018," + i + ",install.purpose");
                    break;
                } else {
                    installation.setPurpose(purpose);
                }   

                if(!user.filter("company_mng_all_data")){
                    subSql = " AND ID = " + user.getCompanyID() + " AND TYPE_ID LIKE '%,3,%'" ;
                } else {
                    subSql = " AND TYPE_ID LIKE '%,3,%' ";
                }
//                companyID = companyDao.getIdByName(installCompanyNameInCell, "", subSql);
                companyID = companyDao.getIdByShortName(installCompanyNameInCell,subSql);
                if (companyID == null) {
                    // data not exists
                    errors.add(notExistsErr + ",install.installCompId");
                    isRight = false;
                    break;
                } else {
                    if(productDao.getCountByAgentId(installation.getProductId(),companyID) == 0){
                        errors.add("BSE01730,"+i+",install.installCompId");
                        break;
                    }

                    installation.setInstallCompId(companyID);
                }
                
                if (isRight && user.filter("company_mng_all_data")) {
                    // CustomerId is not a customer of install company
                    if (!installation.getCustomerId().equals(installation.getInstallCompId())) {
                        subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 0 AND AGENT_ID="
                            + installation.getInstallCompId() + " ) " +
                            " OR ID = " + installation.getInstallCompId() + " ) AND (TYPE_ID LIKE '%,4,%') ";
                        if (companyDao.getCountByID(installation.getCustomerId(), subSql) == 0) {
                            // data not exists
                            errors.add("BSE01727," + i);
                            break;
                        }
                    }
                    // saleID is not a customer of install company
                    if (!installation.getSaleContractCompId().equals(installation.getInstallCompId())) {
                        subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 1 AND AGENT_ID="
                            + installation.getInstallCompId() + " ) " +
                            " OR ID = " + installation.getInstallCompId() + " ) AND (TYPE_ID LIKE '%,2,%') ";
                        if (companyDao.getCountByID(installation.getSaleContractCompId(), subSql) == 0) {
                            // data not exists
                            errors.add("BSE01728," + i);
                            break;
                        }
                    }
                    
                }
                
                if(OSInCell != null
                        && !"".equals(OSInCell)){
                    BigDecimal osType = oSDao.getIdByName(OSInCell);
                    if (osType == null) {
                        // data not exists
                        errors.add("BSE00018," + i + ",install.OS");
                        break;
                    } else {
                        installation.setOs(osType);
                    }
                }
                
                //2009/08/21 add
                if(osPermitInCell != null
                        && !"".equals(osPermitInCell)){
                    BigDecimal osPermitType = osPermitDao.getIdByName(osPermitInCell);
                    if (osPermitType == null) {
                        // data not exists
                        errors.add("BSE00018," + i + ",install.osPermit");
                        break;
                    } else {
                        installation.setOsPermitId(osPermitType);
                    }
                }
                if(!"".equals(platformInCell)){
                    Integer platform = platformDao.getIdByName(platformInCell);
                    if (platform == null) {
                        // data not exists
                        errors.add("BSE00018," + i + ",install.platform");
                        break;
                    } else {
                        installation.setPlatform(platform);
                    }
                }else{
                    installation.setPlatform(-1);
                }
              
                
                if (!"".equals(installerInCell) && installerInCell.length() > 40) {
                    // length error
                    errors.add(lenErr + ",install.installer,0,40");
                } else {
                    installation.setInstaller(installerInCell);
                }

                if (!"".equals(installerIdInCell) && installerIdInCell.length() > 40) {
                    // length error
                    errors.add(lenErr + ",install.installerId,0,40");
                } else {
                    installation.setInstallerId(installerIdInCell);
                }

                if (!"".equals(indentureNoInCell) && indentureNoInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.indentureNo,0,20");
                } else {
                    installation.setIndentureNo(indentureNoInCell);
                }

                if (!"".equals(branchCompanyNameInCell) && branchCompanyNameInCell.length() > 40) {
                    // length error
                    errors.add(lenErr + ",install.branchCompanyName,0,40");
                } else {
                    installation.setBranchCompanyName(branchCompanyNameInCell);
                }

                if (!"".equals(installPlaceInCell) && installPlaceInCell.length() > 40) {
                    // length error
                    errors.add(lenErr + ",install.installPlace,0,40");
                } else {
                    installation.setInstallPlace(installPlaceInCell);
                }

                if (!"".equals(brmEpVerInCell) && brmEpVerInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.brmEpVer,0,20");
                } else {
                    installation.setBrmEpVer(brmEpVerInCell);
                }

                if (!"".equals(bvEpVerInCell) && bvEpVerInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.bvEpVer,0,20");
                } else {
                    installation.setBvEpVer(bvEpVerInCell);
                }

                if (!"".equals(keyNoInCell) && keyNoInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.keyNo,0,20");
                } else {
                    installation.setKeyNo(keyNoInCell);
                }

                if (!"".equals(noteInCell) && noteInCell.length() > 800) {
                    // length error
                    errors.add(lenErr + ",install.note,0,800");
                } else {
                    installation.setNote(noteInCell);
                }
                
                if (!"".equals(jprInCell) && jprInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.jpr,0,20");
                } else {
                    installation.setJpr(jprInCell);
                }
                
                if (!"".equals(platformRevInCell) && platformRevInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.platformRev,0,20");
                } else {
                    installation.setPlatformRev(platformRevInCell);
                }
                
                if (!"".equals(sprInCell) && sprInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.spr,0,20");
                } else {
                    installation.setSpr(sprInCell);
                }
                
                if (!"".equals(mcuInCell) && mcuInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.mcu,0,20");
                } else {
                    installation.setMcu(mcuInCell);
                }
                
                if (!"".equals(deskeyInCell) && deskeyInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.deskey,0,20");
                } else {
                    installation.setDeskey(deskeyInCell);
                }
                
                if (!"".equals(hcmInCell) && hcmInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.hcm,0,20");
                } else {
                    installation.setHcm(hcmInCell);
                }
                
                if (!"".equals(bvInCell) && bvInCell.length() > 200) {
                    // length error
                    errors.add(lenErr + ",install.bv,0,200");
                } else {
                    installation.setBv(bvInCell);
                }

                if (!"".equals(contactInCell) && contactInCell.length() > 40) {
                    // length error
                    errors.add(lenErr + ",install.contact,0,40");
                } else {
                    installation.setContact(contactInCell);
                }
                
                if (!"".equals(officePhoneInCell) && officePhoneInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.officePhone,0,20");
                } else {
                    installation.setOfficePhone(officePhoneInCell);
                }
                
                if (!"".equals(mobilePhoneInCell) && mobilePhoneInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.mobilePhone,0,20");
                } else {
                    installation.setMobilePhone(mobilePhoneInCell);
                }
                
                if (!"".equals(faxInCell) && faxInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.fax,0,20");
                } else {
                    installation.setFax(faxInCell);
                }
                
                if (!"".equals(emailInCell) && emailInCell.length() > 20) {
                    // length error
                    errors.add(lenErr + ",install.email,0,20");
                } else {
                    installation.setEmail(emailInCell);
                }
                
                if (errors.size() > 0) {
                    break;
                }

                installList.add(installation);
            }

            if (errors.size() == 0) {
                for (int i = 0; i < installList.size(); i++) {
                    installation = installList.get(i);

                    installation.setModifierId(user.getId());
                    installation.setModifyTime(TimeUtil.getNow());
                    if (installation.getId() == null) {
                        // create the installation
                        installation.setCreatorId(user.getId());
                        installation.setCreateTime(TimeUtil.getNow());
                        installationDao.create(installation);
                    } else {
                        // update the installation
//                        installationDao.UploadModify(installation);
//                        installationDao.modify(installation, 0);
                        installationDao.modifyByUpload(installation, 0);
                    }
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
     * modify installation
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyInstallation(Installation installation, User user) throws Exception {
        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            // date check
            if (!"".equals(installation.getFobDate())
                    && (installation.getInstallDate().compareTo(installation.getFobDate()) < 0)) {
                errors.add("BSE01713");
            }

            if (!"".equals(installation.getOpenDate())
                    && (installation.getOpenDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01720");
            }
            
            if (!"".equals(installation.getAcceptDate())
                    && (installation.getAcceptDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01701");
            }

            if (!"".equals(installation.getAcceptDate() ) && !"".equals(installation.getOpenDate())
                    && (installation.getAcceptDate().compareTo(installation.getOpenDate()) < 0)) {
                errors.add("BSE01721");
            }

            if (installation.getGuaranteeEndDate().compareTo(installation.getGuaranteeStartDate()) < 0) {
                errors.add("BSE01702");
            }

            if (errors.size() > 0) {
                return;
            }

            InstPlaceTypeDao iptd = new InstPlaceTypeDao(conn);
            if (iptd.getCountByID(installation.getInstPlaceTypeId()) == 0) {
                // data not exists
                errors.add("BSE00017,install.instPlaceTypeId");
                return;
            }

            if (installation.getOs() != null && !"".equals(installation.getOs())) {
                OSDao OSDao = new OSDao(conn);
                if (OSDao.getCountById(installation.getOs().intValue()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.OS");
                    return;
                }
            }

            //2009/08/20 add
            if (installation.getOsPermitId() != null) {
                OsPermitDao osPermitDao = new OsPermitDao(conn);
                if (osPermitDao.getCountById(installation.getOsPermitId().intValue()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.osPermit");
                    return;
                }
            }
            if(installation.getPlatform()!=null && installation.getPlatform()!=-1){
                PlatformDao PlatformDao = new PlatformDao(conn);
                if (PlatformDao.getCountById(installation.getPlatform()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.platform");
                    return;
                }
            }else{
                installation.setPlatform(-1);
            }
            

            if (installation.getAffirmFlag() == 0) {
                companyCheck(installation, user, "2");
                if (errors.size() > 0) {
                    return;
                }
                
                ProductDao productDao = new ProductDao(conn);
                if(productDao.getCountByAgentId(installation.getProductId(),installation.getInstallCompId()) == 0){
                    errors.add("BSE01729,install.installCompId");
                    return;
                }
                
                UseStatusDao UseStatusDao = new UseStatusDao(conn);
                if (UseStatusDao.getCountByID(installation.getUseStatusId()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.useStatusId");
                    return;
                }

                PurposeDao PurposeDao = new PurposeDao(conn);
                if (PurposeDao.getCountByID(installation.getPurpose()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.purpose");
                    return;
                }
            } else {
                // when affirmed,nowRepaireCompanyId must not be null
                if (installation.getNowRepairCompanyId() == null) {
                    errors.add("BSE00026,install.nowRepairCompanyId");
                    return;
                }

                // nowRepaireCompanyId check
                String subSql = " AND (TYPE_ID LIKE '%,3,%') ";
                CompanyDao companyDao = new CompanyDao(conn);
                if (companyDao.getCountByID(installation.getNowRepairCompanyId(), subSql) == 0) {
                    // data not exists
                    errors.add("BSE01012,install.nowRepairCompanyId");
                    return;
                }
                
                ProductDao productDao = new ProductDao(conn);
                if(productDao.getCountByAgentId(installation.getProductId(),installation.getNowRepairCompanyId()) == 0){
                    errors.add("BSE01729,install.nowRepairCompanyId");
                    return;
                }
            }

            // update the installation
            installation.setModifyTime(TimeUtil.getNow());
            installationDao.modify(installation, 0);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify installation
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyHistory(Installation installation, User user) throws Exception {
        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            // date check
            if (!"".equals(installation.getFobDate())
                    && (installation.getInstallDate().compareTo(installation.getFobDate()) < 0)) {
                errors.add("BSE01713");
            }

            if (!"".equals(installation.getOpenDate())
                    && (installation.getOpenDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01720");
            }
            
            if (!"".equals(installation.getAcceptDate())
                    && (installation.getAcceptDate().compareTo(installation.getInstallDate()) < 0)) {
                errors.add("BSE01701");
            }

            if (!"".equals(installation.getAcceptDate()) && !"".equals(installation.getOpenDate())
                    && (installation.getAcceptDate().compareTo(installation.getOpenDate()) < 0)) {
                errors.add("BSE01721");
            }

            if (installation.getGuaranteeEndDate().compareTo(installation.getGuaranteeStartDate()) < 0) {
                errors.add("BSE01702");
            }

            if (errors.size() > 0) {
                return;
            }

            InstPlaceTypeDao iptd = new InstPlaceTypeDao(conn);
            if (iptd.getCountByID(installation.getInstPlaceTypeId()) == 0) {
                // data not exists
                errors.add("BSE00017,install.instPlaceTypeId");
                return;
            }

            if (installation.getOs() != null && !"".equals(installation.getOs())) {
                OSDao OSDao = new OSDao(conn);
                if (OSDao.getCountById(installation.getOs().intValue()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.OS");
                    return;
                }
            }

            //2009/08/20 add
            if (installation.getOsPermitId() != null) {
                OsPermitDao osPermitDao = new OsPermitDao(conn);
                if (osPermitDao.getCountById(installation.getOsPermitId().intValue()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.osPermit");
                    return;
                }
            }
            if(installation.getPlatform()!=null && installation.getPlatform()!=-1){
                PlatformDao PlatformDao = new PlatformDao(conn);
                if (PlatformDao.getCountById(installation.getPlatform()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.platform");
                    return;
                }
            }else{
                installation.setPlatform(-1);
            }

            if (installation.getAffirmFlag() == 0) {
                companyCheck(installation, user, "2");
                if (errors.size() > 0) {
                    return;
                }

                ProductDao productDao = new ProductDao(conn);
                if(productDao.getCountByAgentId(installation.getProductId(),installation.getInstallCompId()) == 0){
                    errors.add("BSE01729,install.installCompId");
                    return;
                }
                
                UseStatusDao UseStatusDao = new UseStatusDao(conn);
                if (UseStatusDao.getCountByID(installation.getUseStatusId()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.useStatusId");
                    return;
                }

                PurposeDao PurposeDao = new PurposeDao(conn);
                if (PurposeDao.getCountByID(installation.getPurpose()) == 0) {
                    // data not exists
                    errors.add("BSE00017,install.purpose");
                    return;
                }
            } else {
                // when affirmed,nowRepaireCompanyId must not be null
                if (installation.getNowRepairCompanyId() == null) {
                    errors.add("BSE00026,install.nowRepairCompanyId");
                    return;
                }

                // nowRepaireCompanyId check
                String subSql = " AND (TYPE_ID LIKE '%,3,%') ";
                CompanyDao companyDao = new CompanyDao(conn);
                if (companyDao.getCountByID(installation.getNowRepairCompanyId(), subSql) == 0) {
                    // data not exists
                    errors.add("BSE01012,install.nowRepairCompanyId");
                    return;
                }
                
                ProductDao productDao = new ProductDao(conn);
                if(productDao.getCountByAgentId(installation.getProductId(),installation.getNowRepairCompanyId()) == 0){
                    errors.add("BSE01729,install.nowRepairCompanyId");
                    return;
                }
            }

            if (TimeUtil.getNowNextDay().compareTo(installation.getStartDate()) > 0) {
                // insert into installation_history_tbl
                InstallationHistoryDao installationHistoryDao = new InstallationHistoryDao(conn);
                installationHistoryDao.create(installation.getId());

                // update the installation_tbl(history update)
                installation.setModifyTime(TimeUtil.getNow());
                installationDao.modify(installation, 1);
            } else {
                // update the installation_tbl
                installation.setModifyTime(TimeUtil.getNow());
                installationDao.modify(installation, 0);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify installation affirmFlg,deleted
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyState(Integer id, Integer exclusiveKey, int modifyType, Integer modifyFlg, Integer userId)
            throws Exception {
        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            // update the installation
            String timeNow = TimeUtil.getNow();

            installationDao.modifyState(id, exclusiveKey, modifyType, modifyFlg, timeNow, userId);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify installation affirmFlg
     * 
     * @author xiangzq
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyState(List<Integer> idList, Integer modifyFlg, Integer userId) throws Exception {
        try {
            init();

            // Start UOC

            InstallationDao installationDao = new InstallationDao(conn);
            String timeNow = TimeUtil.getNow();
            for (Integer id : idList) {
                Installation installation = installationDao.getInstallationById(id);
                if(installation==null){
                    errors.add("BSE01734");
                }else{
                    if(installation.getAffirmFlag()==1){
                        errors.add("BSE01732,"+installation.getManufactureNo());
                    }
                    if(installation.getDeleted()==1){
                        errors.add("BSE01733,"+installation.getManufactureNo());
                    }  
                }       

               installationDao.modifyState(id, modifyFlg, timeNow, userId);
      
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * check login user's modify qualification
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @param user
     * @throws Exception
     */
    public void modifyQualifyCheck(Installation install, User user) throws Exception {
        // Start UOC
        if (install.getAffirmFlag() == 1) {
            if (!user.hasPermission("PR005_32")) {
                errors.add("BSE01715");
            }
        }
        // End UOC
    }

    /**
     * check company
     * 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @param user
     * @param checkFlg
     *            (1:create; 2:update)
     * @throws Exception
     */
    public void companyCheck(Installation installation, User user, String checkFlg) throws Exception {
        // Start UOC
        CompanyDao companyDao = new CompanyDao(conn);
        String subSql = "";
        // customer company
        if (!user.filter("company_mng_all_data")) {
            subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 0 AND AGENT_ID="
                    + user.getCompanyID() + " ) " +
                    " OR ID = " + user.getCompanyID() + " ) AND (TYPE_ID LIKE '%,4,%') ";
        } else {
            subSql = " AND (TYPE_ID LIKE '%,4,%') ";
        }
        if (companyDao.getCountByID(installation.getCustomerId(), subSql) == 0) {
            // data not exists
            errors.add("BSE01012,install.customerId");
            return;
        }

        // sale company
        if (!user.filter("company_mng_all_data")) {
            subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 1 AND AGENT_ID="
                    + user.getCompanyID() + " ) " + 
                    " OR ID = " + user.getCompanyID() + ") AND (TYPE_ID LIKE '%,2,%') ";
        } else {
            subSql = " AND (TYPE_ID LIKE '%,2,%') ";
        }
        if (companyDao.getCountByID(installation.getSaleContractCompId(), subSql) == 0) {
            // data not exists
            errors.add("BSE01012,install.saleContractCompId");
            return;
        }

        // install company
        if(!user.filter("company_mng_all_data")){
            subSql = " AND ID = " + user.getCompanyID() + " AND TYPE_ID LIKE '%,3,%'" ;
        } else {
            subSql = " AND TYPE_ID LIKE '%,3,%' ";
        }
        if (companyDao.getCountByID(installation.getInstallCompId(), subSql) == 0) {
            // data not exists
            errors.add("BSE01012,install.installCompId");
            return;
        }
        
        
        if (user.filter("company_mng_all_data")) {
            // CustomerId is not a customer of install company
            if (!installation.getCustomerId().equals(installation.getInstallCompId())) {
                subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 0 AND AGENT_ID="
                    + installation.getInstallCompId() + " ) " +
                    " OR ID = " + installation.getInstallCompId() + " ) AND (TYPE_ID LIKE '%,4,%') ";
                if (companyDao.getCountByID(installation.getCustomerId(), subSql) == 0) {
                    // data not exists
                    errors.add("BSE01725");
                    return;
                }
            }
            // saleID is not a customer of install company
            if (!installation.getSaleContractCompId().equals(installation.getInstallCompId())) {
                subSql = " AND (ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE CATE_FLAG = 1 AND AGENT_ID="
                    + installation.getInstallCompId() + " ) " +
                    " OR ID = " + installation.getInstallCompId() + " ) AND (TYPE_ID LIKE '%,2,%') ";
                if (companyDao.getCountByID(installation.getSaleContractCompId(), subSql) == 0) {
                    // data not exists
                    errors.add("BSE01726");
                    return;
                }
            }
            
        }
        // End UOC
    }

    /**
     * serch install place type
     * 
     * @author sunyx
     * @return
     */
    public List<Installation> serchInstPlaceType() {
        List<Installation> list = new ArrayList<Installation>();
        try {
            init();

            // Start UOC
            InstPlaceTypeDao dao = new InstPlaceTypeDao(conn);
            list = dao.getTypeList();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
}
