//****************************************
// ProjectName ITインフラ改造作業
// CreateDate 08/12/15
// Copyright © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.InstApplyDao;
import cn.com.bhh.erp.dao.InstApplyTypeDao;
import cn.com.bhh.erp.dao.InstallationDao;
import cn.com.bhh.erp.dao.UseStatusDao;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.InstallationApply;
import cn.com.bhh.erp.entity.User;

/**
 * 产品使用情况申请管理
 * @author sunyx
 * @version 1.0
 * @since 1.0
 */
public class InstallationApplyBusiness extends BaseBusiness{

    /**
     * affirmFlag:not be affirmed
     */
    static final Integer zero = 0;
    
    /**
     * affirmFlag:be affirmed
     */
    static final Integer one = 1;
    
    /**
     * deleted:be deleted
     */
    static final Integer deleted = 1;
    
    /**
     * apply type : change use status to running
     */
    static final Integer typeOne = 1;
    
    /**
     * apply type : change use status to stop
     */
    static final Integer typeTwo = 2;
    
    /**
     * apply type : change install place
     */
    static final Integer typeThree = 3;
    
    /**
     * apply type : change guarantee date
     */
    static final Integer typeFour = 4;
    
    /**
     * apply type : change contact information
     */
    static final Integer typeFive = 5;
    
    /**
     * apply type : make a mistake of add this installation
     */
    static final Integer typeSix = 6;
    
    /**
     * use status : running
     */
    static final Integer useStatusOne = 1;
    
    /**
     * use status : stop
     */
    static final Integer useStatusTwo = 2;
    
    /**
     * input max length
     */
    static final int maxLength = 100;
    
    public void inputCheck(InstallationApply instApply){
        try {
            init();

            // Start UOC
            if(instApply.getAdminNote().length() > maxLength){
                instApply.setAdminNote(instApply.getAdminNote().substring(0, maxLength));
            }
            if(instApply.getApplyerNote().length() > maxLength){
                instApply.setApplyerNote(instApply.getApplyerNote().substring(0, maxLength));
            }
            
            if((instApply.getType().compareTo(typeOne) == 0
                    || instApply.getType().compareTo(typeTwo) == 0)
                    && instApply.getUseStatusId() == null){
                errors.add("BSE02006,useStatusId");
                return;
            }
            
            if(instApply.getType().compareTo(typeThree) == 0){
                if(instApply.getInstallPlace() == null
                        || "".equals(instApply.getInstallPlace())){
                    errors.add("BSE02006,installPlace");
                }
                if(instApply.getInstPlaceTypeId() == null
                        || "".equals(instApply.getInstPlaceTypeId())){
                    errors.add("BSE02006,instPlaceTypeName");
                }
                return;
            }
            
            if(instApply.getType().compareTo(typeFour) == 0){
                if(instApply.getGuaranteeStartDate() == null
                        || "".equals(instApply.getGuaranteeStartDate())){
                    errors.add("BSE02006,guaranteeStartDate");
                } 
                if(instApply.getGuaranteeEndDate() == null
                        || "".equals(instApply.getGuaranteeEndDate())) {
                    errors.add("BSE02006,guaranteeEndDate");
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
     * apply type drop
     * @author sunyx
     * @return
     */
    public List<InstallationApply> getApplyTypeForDrop(){
        List<InstallationApply> list = new ArrayList<InstallationApply>();
        try {
            init();

            // Start UOC
            InstApplyTypeDao aTdao= new InstApplyTypeDao(super.conn);
            list = aTdao.getApplyTypeForDrop();
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
    
    /**
     * @author sunyx
     * @param instApp
     */
    public void doModifyInstApply(InstallationApply instApp, Installation inst, User user){
        try {
            init();
            
            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            DateFormat format = new DateFormat();
            if(inst.getGuaranteeStartDate() != null){
                inst.setGuaranteeStartDate(format.format(inst.getGuaranteeStartDate(), "instApply.guaranteeStartDate"));
            }
            if(inst.getGuaranteeEndDate() != null){
                inst.setGuaranteeEndDate(format.format(inst.getGuaranteeEndDate(), "instApply.guaranteeEndDate"));
            }
            
            if(errors.size()>0){
                return;
            }
            
            if(instApp.getType().compareTo(typeOne) == 0
                    || instApp.getType().compareTo(typeTwo) == 0){
                instApp.setInstallPlace(inst.getInstallPlace());
                instApp.setInstPlaceTypeId(inst.getInstPlaceTypeId());
                instApp.setGuaranteeStartDate(inst.getGuaranteeStartDate());
                instApp.setGuaranteeEndDate(inst.getGuaranteeEndDate());
                instApp.setGuaranteePeriod(inst.getGuaranteePeriod());
                instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
                instApp.setContact(inst.getContact());
                instApp.setOfficePhone(inst.getOfficePhone());
                instApp.setMobilePhone(inst.getMobilePhone());
                instApp.setFax(inst.getFax());
                instApp.setEmail(inst.getEmail());
            }
            
            if(instApp.getType().compareTo(typeThree) == 0){
                instApp.setUseStatusId(inst.getUseStatusId());
                instApp.setGuaranteeStartDate(inst.getGuaranteeStartDate());
                instApp.setGuaranteeEndDate(inst.getGuaranteeEndDate());
                instApp.setGuaranteePeriod(inst.getGuaranteePeriod());
                instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
                instApp.setContact(inst.getContact());
                instApp.setOfficePhone(inst.getOfficePhone());
                instApp.setMobilePhone(inst.getMobilePhone());
                instApp.setFax(inst.getFax());
                instApp.setEmail(inst.getEmail());
            }
            
            if(instApp.getType().compareTo(typeFour) == 0){
                instApp.setInstallPlace(inst.getInstallPlace());
                instApp.setInstPlaceTypeId(inst.getInstPlaceTypeId());
                instApp.setUseStatusId(inst.getUseStatusId());
                instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
                instApp.setContact(inst.getContact());
                instApp.setOfficePhone(inst.getOfficePhone());
                instApp.setMobilePhone(inst.getMobilePhone());
                instApp.setFax(inst.getFax());
                instApp.setEmail(inst.getEmail());
            }
            
            if(instApp.getType().compareTo(typeFive) == 0){
                instApp.setInstallPlace(inst.getInstallPlace());
                instApp.setInstPlaceTypeId(inst.getInstPlaceTypeId());
                instApp.setUseStatusId(inst.getUseStatusId());
                instApp.setGuaranteeStartDate(inst.getGuaranteeStartDate());
                instApp.setGuaranteeEndDate(inst.getGuaranteeEndDate());
                instApp.setGuaranteePeriod(inst.getGuaranteePeriod());
                instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
            }
            
            if(instApp.getType().compareTo(typeSix) == 0){
                instApp.setInstallPlace(inst.getInstallPlace());
                instApp.setInstPlaceTypeId(inst.getInstPlaceTypeId());
                instApp.setUseStatusId(inst.getUseStatusId());
                instApp.setGuaranteeStartDate(inst.getGuaranteeStartDate());
                instApp.setGuaranteeEndDate(inst.getGuaranteeEndDate());
                instApp.setGuaranteePeriod(inst.getGuaranteePeriod());
                instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
                instApp.setContact(inst.getContact());
                instApp.setOfficePhone(inst.getOfficePhone());
                instApp.setMobilePhone(inst.getMobilePhone());
                instApp.setFax(inst.getFax());
                instApp.setEmail(inst.getEmail());
            }

            instApp.setModifyTime(TimeUtil.getNow());
            instApp.setModifierId(user.getId());

            instApplyDao.doModify(instApp);
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * @author sunyx
     * @return List&ltInstallationApply&gt
     */
    public List<InstallationApply> serchApplyList(int currPage, int pageSize,User user){
        List<InstallationApply> list = new ArrayList<InstallationApply>();
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);

            int begin;
            int end;

            if (currPage <= 1) {
                begin = 0;
                end = pageSize;
            } else {
                begin = (currPage - 1) * pageSize;
                end = begin + pageSize;
            }

            list = instApplyDao.serchApplyList(begin,end,user);

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
    
    /**
     * @author xiangzq
     * @return List&ltInstallationApply&gt
     */
    public List<InstallationApply> serchApplyList(User loginUser){
        List<InstallationApply> list = new ArrayList<InstallationApply>();
        try {
            init();

            // Start UOC
            
            InstApplyDao instApplyDao = new InstApplyDao(conn);
            String subSql = "";
            if (!loginUser.filter("install_mng_all_data")) {
                subSql = subSql + " AND A.NOW_REPAIR_COMPANY_ID = "+loginUser.getCompanyID();
            }
            list = instApplyDao.serchApplyList(subSql);

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
    /**
     * @author sunyx
     * @param instApply
     * @return InstallationApply
     */
    public InstallationApply serchApplyByIdExkey(InstallationApply instApply, User user){
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApply = instApplyDao.serchApply(instApply);

            if (!user.filter("install_mng_all_data") 
                    && (user.getCompanyID().compareTo(instApply.getNowRepairCompanyId()) != 0)) {
                errors.add("BSF00008");
            }
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return instApply;
    }
    
    public Integer serchInstallId(InstallationApply instApply){
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApply = instApplyDao.serchApply(instApply);

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return instApply.getInstallId();
    }
    
    /**
     * @author sunyx
     * @param instApply
     * @return
     */
    public InstallationApply serchApplyForModify(InstallationApply instApply){
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApply = instApplyDao.serchApply(instApply);
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return instApply;
    }
    
    /**
     * do the check of installation information for add an apply
     * @author sunyx
     * @param instApply
     * @param user
     * @return 
     */
    public void addApplyCheck (InstallationApply instApply, Installation inst, User user) {

        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn); 
            
            instApply.setInstallId(inst.getId());
            if(instApplyDao.addCheck(instApply) != 0){
                errors.add("BSE02004");
                return;
            }
            
            InstApplyTypeDao aTDao = new InstApplyTypeDao(super.conn);
            if(aTDao.getCount(instApply.getType()) == 0){
                errors.add("BSE00017,applyTypeName");
                return;
            }
            
            if(instApply.getType().compareTo(typeOne) == 0
                    || instApply.getType().compareTo(typeTwo) == 0){
                UseStatusDao ud = new UseStatusDao(super.conn);
                if(ud.getCountByID(instApply.getUseStatusId()) == 0){
                    errors.add("BSE00017,useStatusId");
                    return;
                }
            }
            
            if(instApply.getType().compareTo(typeOne) == 0
                    && inst.getUseStatusId().compareTo(useStatusOne) == 0){
                errors.add("BSE02002");
                return;
            }
            
            if(instApply.getType().compareTo(typeOne) == 0
                    && instApply.getUseStatusId().compareTo(useStatusTwo) == 0){
                errors.add("BSE02007,running");
                return;
            }
            
            if(instApply.getType().compareTo(typeTwo) == 0
                    && inst.getUseStatusId().compareTo(useStatusTwo) == 0){
                errors.add("BSE02003");
                return;
            }
            
            if(instApply.getType().compareTo(typeTwo) == 0
                    && instApply.getUseStatusId().compareTo(useStatusOne) == 0){
                errors.add("BSE02007,stop");
                return;
            }
            
            if(instApply.getType().compareTo(typeFour) == 0
                    && instApply.getGuaranteeStartDate().compareTo(instApply.getGuaranteeEndDate()) >= 0){
                errors.add("BSE01702");
                return;
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
     * add an apply
     * @author sunyx
     * @param instApp
     * @param inst
     */
    public void addAply(InstallationApply instApp,Installation inst){

        try {
            init();
            
            // Start UOC
            instApp.setApplyDate(TimeUtil.getNowDate());
            instApp.setInstallId(inst.getId());
            instApp.setProductId(inst.getProductId());
            instApp.setManufactureNo(inst.getManufactureNo());
            instApp.setCustomerId(inst.getCustomerId());
            instApp.setSaleContractCompId(inst.getSaleContractCompId());
            instApp.setInstallCompId(inst.getInstallCompId());
            
            if(inst.getInstaller() != null){
                instApp.setInstaller(inst.getInstaller()); 
            } else {
                instApp.setInstaller(""); 
            }
            
            if(inst.getInstallerId() != null){
                instApp.setInstallerId(inst.getInstallerId()); 
            } else {
                instApp.setInstallerId(""); 
            }
            
            instApp.setFirstRepairCompanyId(inst.getFirstRepairCompanyId());
            instApp.setNowRepairCompanyId(inst.getNowRepairCompanyId());
            
            if(inst.getIndentureNo() != null){
                instApp.setIndentureNo(inst.getIndentureNo());  
            } else {
                instApp.setIndentureNo("");
            }
            
            if(inst.getFobDate() != null){
                instApp.setFobDate(inst.getFobDate());  
            } else {
                instApp.setFobDate("");  
            }
            
            instApp.setInstallDate(inst.getInstallDate());
            instApp.setOpenDate(inst.getOpenDate());
            
            if(inst.getAcceptDate() != null){
                instApp.setAcceptDate(inst.getAcceptDate());
            } else {
                instApp.setAcceptDate("");
            }
            
            if(instApp.getType().compareTo(typeFour) != 0){
                instApp.setGuaranteeStartDate(inst.getGuaranteeStartDate()); 
                instApp.setGuaranteeEndDate(inst.getGuaranteeEndDate());
                if(inst.getGuaranteePeriod() != null){
                    instApp.setGuaranteePeriod(inst.getGuaranteePeriod());
                } else {
                    instApp.setGuaranteePeriod(""); 
                }
            }
            
            if(inst.getBranchCompanyName() != null){
                instApp.setSubCompany(inst.getBranchCompanyName()); 
            } else {
                instApp.setSubCompany(""); 
            }
            
            if(instApp.getType().compareTo(typeThree) != 0){
                if(inst.getInstallPlace() != null){
                    instApp.setInstallPlace(inst.getInstallPlace());  
                } else {
                    instApp.setInstallPlace("");  
                }
                instApp.setInstPlaceTypeId(inst.getInstPlaceTypeId());
            }
            
            if(instApp.getType().compareTo(typeTwo) != 0
                    && instApp.getType().compareTo(typeOne) != 0){
                instApp.setUseStatusId(inst.getUseStatusId());
            }
            
            instApp.setPurpose(inst.getPurpose());
            
            if(inst.getBrmEpVer() != null){
                instApp.setBrmEpVer(inst.getBrmEpVer());
            } else {
                instApp.setBrmEpVer("");
            }
            
            if(inst.getBvEpVer() != null){
                instApp.setBvEpVer(inst.getBvEpVer());  
            } else {
                instApp.setBvEpVer("");  
            }
            
            if(inst.getBhclEpVer() != null)     {
                instApp.setBhclEpVer(inst.getBhclEpVer());  
            } else {
                instApp.setBhclEpVer("");  
            }
            
            if(inst.getTrclEpVer() != null)     {
                instApp.setTrclEpVer(inst.getTrclEpVer());  
            } else {
                instApp.setTrclEpVer("");  
            }
            
            if(inst.getKeyNo() != null){
                instApp.setKeyNo(inst.getKeyNo());
            } else {
                instApp.setKeyNo("");
            }
            
            if(inst.getNote() != null){
                instApp.setNote(inst.getNote());
            } else {
                instApp.setNote("");
            }
            
            instApp.setOs(inst.getOs());
            //2009/08/20 add
            instApp.setOsPermitId(inst.getOsPermitId());
            
            instApp.setJpr(inst.getJpr());
            instApp.setPlatform(inst.getPlatform());
            instApp.setPlatformRev(inst.getPlatformRev());
            instApp.setHcm(inst.getHcm());
            instApp.setSpr(inst.getSpr());
            instApp.setMcu(inst.getMcu());
            instApp.setDeskey(inst.getDeskey());
            instApp.setBv(inst.getBv());
            
            if(instApp.getType().compareTo(typeFive) != 0){
                instApp.setContact(inst.getContact());
                instApp.setOfficePhone(inst.getOfficePhone());
                instApp.setMobilePhone(inst.getMobilePhone());
                instApp.setFax(inst.getFax());
                instApp.setEmail(inst.getEmail());
            }

            instApp.setCreateTime(TimeUtil.getNow());
            instApp.setModifyTime(TimeUtil.getNow());

            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApplyDao.addApply(instApp);
            // End UOC
            
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

    }

    /**
     * do the check of installation information for add an apply
     * @author sunyx
     * @param instApply
     * @param user
     * @return 
     */
    public void updateCheck (InstallationApply instApply, Installation inst, User user) {

        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn); 
            if(instApplyDao.updateCheck(instApply) != 0){
                errors.add("BSE02004");
                return;
            }

            InstApplyTypeDao aTDao = new InstApplyTypeDao(super.conn);
            if(aTDao.getCount(instApply.getType()) == 0){
                errors.add("BSE00017,applyTypeName");
                return;
            }
            
            if(instApply.getType().compareTo(typeOne) == 0
                    || instApply.getType().compareTo(typeTwo) == 0){
                UseStatusDao ud = new UseStatusDao(super.conn);
                if(ud.getCountByID(instApply.getUseStatusId()) == 0){
                    errors.add("BSE00017,useStatusId");
                    return;
                }
            }

            if(instApply.getType().compareTo(typeOne) == 0
                    && inst.getUseStatusId().compareTo(useStatusOne) == 0){
                errors.add("BSE02002");
                return;
            }
            
            if(instApply.getType().compareTo(typeOne) == 0
                    && instApply.getUseStatusId().compareTo(useStatusTwo) == 0){
                errors.add("BSE02007,running");
                return;
            }
            
            if(instApply.getType().compareTo(typeTwo) == 0
                    && inst.getUseStatusId().compareTo(useStatusTwo) == 0){
                errors.add("BSE02003");
                return;
            }
            
            if(instApply.getType().compareTo(typeTwo) == 0
                    && instApply.getUseStatusId().compareTo(useStatusOne) == 0){
                errors.add("BSE02007,stop");
                return;
            }
            
            if(instApply.getType().compareTo(typeFour) == 0
                    && instApply.getGuaranteeStartDate().compareTo(instApply.getGuaranteeEndDate()) >= 0){
                errors.add("BSE01702");
                return;
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
     * do the check of affirm an apply
     * @author sunyx
     * @param instApply
     * @param user
     * @return 
     */
    public InstallationApply doApplyCheck(InstallationApply instApply, Installation inst, User user) {

        try {
            init();

            // Start UOC
            if(!user.hasPermission("PR004_70")){
                errors.add("BSF00008");
            }
            
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApply = instApplyDao.serchApply(instApply);

            InstallationDao instDao = new InstallationDao(super.conn);
            inst = instDao.getInstallationByIdExclusive(instApply.getInstallId(),inst.getExclusiveKey());

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return instApply;
    }
    
    /**
     * do the check of affirm an apply
     * @author sunyx
     * @param instApply
     * @param user
     * @return 
     */
    public void doApply(InstallationApply instApply) {

        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            InstallationDao instDao = new InstallationDao(super.conn);

            Integer deletedFlag = 1;

            if(instApply.getType().compareTo(typeOne) == 0
                    || instApply.getType().compareTo(typeTwo) == 0){
                instDao.doModifyUseStatusForApply(instApply);
            }
            
            if(instApply.getType().compareTo(typeThree) == 0){
                instDao.doModifyInstPlaceForApply(instApply);
            }
            
            if(instApply.getType().compareTo(typeFour) == 0){
                instDao.doModifyGuaranteeForApply(instApply);
            }
            
            if(instApply.getType().compareTo(typeFive) == 0){
                instDao.doModifyContactForApply(instApply);
            }
            
            if(instApply.getType().compareTo(typeSix) == 0){
                instDao.doModifyWrongForApply(instApply,deletedFlag);
            }

            instApplyDao.delete(instApply.getId(), instApply.getExclusiveKey());
            // End UOC


        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return;
    }
    
    /**
     * @author sunyx
     * @param id
     * @param excusiveKey
     */
    public void deleteCheck(InstallationApply instApply, User user){
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApply = instApplyDao.serchApply(instApply);
            
            if (!user.filter("install_mng_all_data") 
                    && (user.getCompanyID().compareTo(instApply.getNowRepairCompanyId()) != 0)) {
                errors.add("BSF00008");
                return;
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
     * @author sunyx
     * @param id
     * @param excusiveKey
     */
    public void delete(Integer id,Integer excusiveKey){
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            instApplyDao.delete(id, excusiveKey);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * @author sunyx
     * @return
     */
    public int getCounts(User user){
        int count = 0;
        try {
            init();

            // Start UOC
            InstApplyDao instApplyDao = new InstApplyDao(super.conn);
            
            if(user.filter("install_mng_all_data")){
                count = instApplyDao.getCounts();
            } else {
                count = instApplyDao.getCountsByNowCompId(user.getCompanyID());
            }
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return count;
    }
    
    /**
     * select installation information when affirmFlag isn't equal 0(not affirmed)
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param id
     * @return Installation
     */
    public Installation serchInstall(Integer id, User user) {
        Installation installation = new Installation();

        try {
            init();

            // Start UOC
            InstallationDao installationDao = new InstallationDao(conn);

            installation = installationDao.serchUseStatusDetail(id, "");
            if(installation.getId() == null
                    || installation.getDeleted().compareTo(deleted) == 0){
                errors.add("BSE01712");
                return installation;
            }
            
            if (!user.filter("install_mng_all_data") 
                    && (user.getCompanyID().compareTo(installation.getNowRepairCompanyId()) != 0)) {
                errors.add("BSF00008");
                return installation;
            }
            
            DateFormat format = new DateFormat();
            if(installation.getFobDate() != null){
                installation.setFobDate(format.changeDate(installation.getFobDate()));
            }
            if(installation.getInstallDate() != null){
                installation.setInstallDate(format.changeDate(installation.getInstallDate()));
            }
            if(installation.getOpenDate() != null){
                installation.setOpenDate(format.changeDate(installation.getOpenDate()));
            }
            if(installation.getAcceptDate() != null){
                installation.setAcceptDate(format.changeDate(installation.getAcceptDate()));
            }
            if(installation.getGuaranteeStartDate() != null){
                installation.setGuaranteeStartDate(format.changeDate(installation.getGuaranteeStartDate()));
            }
            if(installation.getGuaranteeEndDate() != null){
                installation.setGuaranteeEndDate(format.changeDate(installation.getGuaranteeEndDate()));
            }
            
            if(installation.getAffirmFlag().compareTo(zero) == 0){
                errors.add("BSE02001");
                return installation;
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
     * display by type
     * @author sunyx
     * @param instApply
     * @return
     */
    public InstallationApply forDisplay(InstallationApply instApply){
            if(instApply.getType().compareTo(typeOne) == 0
                    ||instApply.getType().compareTo(typeTwo) == 0){
                instApply.setInstallPlace(null);
                instApply.setInstPlaceTypeName(null);
                instApply.setGuaranteeEndDate(null);
                instApply.setGuaranteePeriod(null);
                instApply.setGuaranteeStartDate(null);
                instApply.setNowRepairCompanyName(null);
                instApply.setContact(null);
                instApply.setOfficePhone(null);
                instApply.setMobilePhone(null);
                instApply.setFax(null);
                instApply.setEmail(null);
            }

            if(instApply.getType().compareTo(typeThree) == 0){
                instApply.setGuaranteeEndDate(null);
                instApply.setGuaranteePeriod(null);
                instApply.setGuaranteeStartDate(null);
                instApply.setUseStatus(null);
                instApply.setNowRepairCompanyName(null);
                instApply.setContact(null);
                instApply.setOfficePhone(null);
                instApply.setMobilePhone(null);
                instApply.setFax(null);
                instApply.setEmail(null);
            }
            
            if(instApply.getType().compareTo(typeFour) == 0){
                instApply.setInstallPlace(null);
                instApply.setInstPlaceTypeName(null);
                instApply.setUseStatus(null);
                instApply.setNowRepairCompanyName(null);
                instApply.setContact(null);
                instApply.setOfficePhone(null);
                instApply.setMobilePhone(null);
                instApply.setFax(null);
                instApply.setEmail(null);
            }
            
            if(instApply.getType().compareTo(typeFive) == 0){
                instApply.setInstallPlace(null);
                instApply.setInstPlaceTypeName(null);
                instApply.setGuaranteeEndDate(null);
                instApply.setGuaranteePeriod(null);
                instApply.setGuaranteeStartDate(null);
                instApply.setUseStatus(null);
                instApply.setNowRepairCompanyName(null);
            }
            
            if(instApply.getType().compareTo(typeSix) == 0){
                instApply.setInstallPlace(null);
                instApply.setInstPlaceTypeName(null);
                instApply.setGuaranteeEndDate(null);
                instApply.setGuaranteePeriod(null);
                instApply.setGuaranteeStartDate(null);
                instApply.setUseStatus(null);
                instApply.setNowRepairCompanyName(null);
                instApply.setContact(null);
                instApply.setOfficePhone(null);
                instApply.setMobilePhone(null);
                instApply.setFax(null);
                instApply.setEmail(null);
            }

        return instApply;
    }
}

