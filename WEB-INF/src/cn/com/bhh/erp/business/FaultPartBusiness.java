//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.FaultPartDao;
import cn.com.bhh.erp.entity.FaultPart;

public class FaultPartBusiness extends BaseBusiness{

    public List<FaultPart> getFaultPartList(String faultMachineType) {
        List<FaultPart> faultPartList = new ArrayList<FaultPart>();
        
        try{
            init();
            
            // Strat UOC
            FaultPartDao faultPartDao = new FaultPartDao(conn);
            if(faultMachineType!=null ){
               if(!"G-ABIO".equals(faultMachineType) && !"ATM".equals(faultMachineType)){
                    faultMachineType="STANDARD";
               }
            }
            
            faultPartList = faultPartDao.searchAll(faultMachineType);
            
            // End UOC
            
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return faultPartList;
    }
    
    /**
     * create a message,and add the message company relation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     * @param messageCompanyList
     */
    public void createFaultPart(FaultPart faultPart) {
        try {
            init();

            // Start UOC

            FaultPartDao faultPartDao = new FaultPartDao(conn);
            Integer faultPartId = faultPartDao.getIdByName(faultPart.getName(), faultPart.getFaultMachineType());
            if(faultPartId !=null){
                errors.add("BSE00000,faultPart.name");
            }else{
                faultPartDao.createFaultPart(faultPart);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     */
    public void deleteFaultPart(FaultPart faultPart) {
        try {
            init();

            // Start UOC
 
             FaultPartDao faultPartDao = new FaultPartDao(conn);
             FaultDao faultDao = new FaultDao(conn);
             if(faultDao.getCountByFaultPartId(faultPart.getId())>0){
                 errors.add("BSE02401");
             }else{
                 faultPartDao.deleteFaultPart(faultPart);
             }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the faultPart.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param faultPart
     */
    public void modifyFaultPart(FaultPart faultPart) {
        try {
            init();

            // Start UOC

            FaultPartDao faultPartDao = new FaultPartDao(conn);

            // get the same name count of city
            int sameNameCount = faultPartDao.getCountByNameModify(faultPart);
            if (sameNameCount > 0) {
                errors.add("BSE00000,faultPart.name");
                return;
            }
            
            faultPartDao.modifyFaultPart(faultPart);
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }
    
    /**
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   faultPart
     * @return  FaultPart
     * @throws  Exception
     */
    public FaultPart getFaultPartById(FaultPart faultPart) throws Exception {
        FaultPart faultPartOut = null;

        try {
            init();

            // Start UOC

            FaultPartDao faultPartDao = new FaultPartDao(conn);
            faultPartOut = faultPartDao.searchFaultPartById(faultPart);

            if (null == faultPartOut) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return faultPartOut;
    }

}
