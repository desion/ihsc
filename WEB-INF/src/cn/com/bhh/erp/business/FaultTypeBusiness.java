//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.FaultTypeDao;
import cn.com.bhh.erp.entity.FaultType;

public class FaultTypeBusiness extends BaseBusiness{

    public List<FaultType> getFaultTypeList() {
        List<FaultType> faultTypeList = new ArrayList<FaultType>();
        
        try{
            init();
            
            // Strat UOC
            FaultTypeDao faultTypeDao = new FaultTypeDao(conn);
            faultTypeList = faultTypeDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return faultTypeList;
    }
}
