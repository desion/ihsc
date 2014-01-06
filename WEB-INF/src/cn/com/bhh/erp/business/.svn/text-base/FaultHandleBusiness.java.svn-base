//****************************************
// ProjectName ITインフラ改造作業
// CreateDate 08/12/15
// Copyright © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.FaultHandleDao;
import cn.com.bhh.erp.dao.FaultHandleTypeDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.FaultHandle;
import cn.com.bhh.erp.entity.User;

public class FaultHandleBusiness extends BaseBusiness{

    /**
     * get handle type for drop
     * @author sunyx
     * @return
     */
    public List<FaultHandle> getHandleTypeForDrop(){
        List<FaultHandle> list = new ArrayList<FaultHandle>();
        try{
            init();
            
            // Start UOC
            FaultHandleTypeDao fhtd = new FaultHandleTypeDao(conn);
            list = fhtd.getHandleType();
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
    
    /**
     * the check of create load
     * @author sunyx
     * @param fault
     */
    public void creatLoadCheck(Integer id){
        try{
            init();
            
            // Start UOC
            FaultDao fd = new FaultDao(conn);
            if(fd.getCountById(id) == 0){
                errors.add("BSF00006");
                return;
            }
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * the check of create
     * @author sunyx 
     */
    public void doCreateCheck(FaultHandle faultHandle,User loginUser){
        try{
            init();
            
            // Start UOC
            UserDao ud = new UserDao(conn);
            if(ud.getCountById(faultHandle.getSupporterId(),loginUser) == 0){
                errors.add("BSE00017,faultHandle.supporterId");
                return;
            }
            
            CompanyDao cd = new CompanyDao(conn);
            if(cd.getCountByCompanyId(faultHandle.getOperateCompanyId()) == 0){
                errors.add("BSE00017,faultHandle.operateCompanyId");
                return;
            }

            FaultHandleTypeDao fhtd = new FaultHandleTypeDao(conn);
            if(fhtd.getCountById(faultHandle.getHandleType()) == 0){
                errors.add("BSE00017,faultHandle.handleType");
                return;
            }
            
            StringBuffer start = new StringBuffer();
            start.append(faultHandle.getStartDate());
            start.append(faultHandle.getStartTime());
            
            StringBuffer end = new StringBuffer();
            end.append(faultHandle.getEndDate());
            end.append(faultHandle.getEndTime());
            
            if(faultHandle.getStartDate().compareTo(faultHandle.getEndDate()) > 0){
                errors.add("BSE02201");
            } else if(faultHandle.getStartDate().compareTo(faultHandle.getEndDate()) == 0
                    && start.toString().compareTo(end.toString()) > 0){
                errors.add("BSE02201");
            }
            
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * time format(delete "/" and ":" for insert or update)
     * @author sunyx
     * @param faultHandle
     */
    public void timeFormat(FaultHandle faultHandle){
        // Start UOC
        DateFormat df = new DateFormat();
        
        faultHandle.setStartDate(df.format(faultHandle.getStartDate(), "faultHandle.startDate"));
        faultHandle.setStartTime(df.formatTime(faultHandle.getStartTime(), "faultHandle.startTime"));
        faultHandle.setEndDate(df.format(faultHandle.getEndDate(), "faultHandle.endDate"));
        faultHandle.setEndTime(df.formatTime(faultHandle.getEndTime(), "faultHandle.endTime"));
        
        if(df.hasErrors()){
            for(int i=0;i<df.getErrors().size();i++){
                errors.add(df.getErrors().get(i));
            }
        }
        // End UOC
    }
    
    /**
     * time change(add "/",":" for display)
     * @author sunyx
     * @param faultHandle
     */
    public void timeChange(FaultHandle faultHandle){
        // Start UOC
        DateFormat df = new DateFormat();
        
        faultHandle.setStartDate(df.changeDate(faultHandle.getStartDate()));
        faultHandle.setStartTime(df.changeTime(faultHandle.getStartTime()));
        faultHandle.setEndDate(df.changeDate(faultHandle.getEndDate()));
        faultHandle.setEndTime(df.changeTime(faultHandle.getEndTime()));
       
        // End UOC
    }
    
    /**
     * do create fault handle
     * @author sunyx
     * @param faultHandle
     */
    public void doCreate(FaultHandle faultHandle){
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            fhd.create(faultHandle);
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * serch handle information
     * @author sunyx
     * @param faultHandle
     * @return
     */
    public Integer serchFaultId(Integer id){
        FaultHandle fh = new FaultHandle();
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            if(fhd.getCountById(id) == 0){
                errors.add("BSF00006");
            }

            fh = fhd.serchById(id);

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return fh.getFaultId();
    }
    
    /**
     * do modify
     * @author sunyx
     * @param faultHandle
     */
    public void doModify(FaultHandle faultHandle){
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            fhd.modify(faultHandle);
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    public List<FaultHandle> serchList(Integer faultId){
        List<FaultHandle> fhList = new ArrayList<FaultHandle>();
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            fhList = fhd.serchAllByFaultId(faultId);
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return fhList;
    }
    
    /**
     * serch handle information
     * @author sunyx
     * @param faultHandle
     * @return
     */
    public FaultHandle serchDetail(FaultHandle faultHandle){
        FaultHandle fh = new FaultHandle();
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            if(fhd.getCountById(faultHandle.getId()) == 0){
                errors.add("BSF00006");
                return fh;
            }

            fh = fhd.serchById(faultHandle.getId());

            timeChange(fh);
            
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return fh;
    }
    
    /**
     * serch handle information
     * @author sunyx
     * @param faultHandle
     * @return
     */
    public Fault serchFault(Integer FaultId){
        Fault ft = new Fault();
        try{
            init();
            
            // Start UOC
            FaultDao faultDao = new FaultDao(conn);
            ft = faultDao.getFaultDetailById(FaultId, null);
            if (ft.getId() == null) {
                errors.add("BSF00006");
                return ft;
            }

            Fault faultHeader = new Fault();
            faultHeader = faultDao.getFaultHeaderFr(ft.getInstallationId(), ft.getReportDate());
            if (faultHeader.getManufactureNo() == null) {
                errors.add("BSF00006");
                return ft;
            } else {
                ft.setModel(faultHeader.getModel());
                ft.setProductCategoryName(faultHeader.getProductCategoryName());
                ft.setCityName(faultHeader.getCityName());
                ft.setCustomerName(faultHeader.getCustomerName());
                ft.setSubCompany(faultHeader.getSubCompany());
                ft.setInstallPlace(faultHeader.getInstallPlace());
            }

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return ft;
    }
    
    /**
     * delete che
     * @author sunyx
     */
    public void delete(FaultHandle faultHandle){
        try{
            init();
            
            // Start UOC
            FaultHandleDao fhd = new FaultHandleDao(conn);
            fhd.delete(faultHandle.getId());
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * permission check
     * @author sunyx
     * @param faultHandle
     * @param user
     */
    public void permissionCheck(Integer faultId, User user){
        try{
            init();
            
            // Start UOC
            FaultDao fd = new FaultDao(conn);
            Integer nowRepaireCompId = fd.sercgNowRepaireComp(faultId);
            if(nowRepaireCompId.compareTo(new Integer(0)) == 0){
                return;
            } else if(!user.filter("fault_mng_all_data")
                            && nowRepaireCompId.compareTo(user.getCompanyID()) != 0){
                errors.add("BSF00008");
            }

            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
}
