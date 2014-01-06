//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.FaultPartTypeDao;
import cn.com.bhh.erp.entity.FaultPartType;

public class FaultPartTypeBusiness extends BaseBusiness{

    public List<FaultPartType> getFaultPartTypeList() {
        List<FaultPartType> faultPartTypeList = new ArrayList<FaultPartType>();
        
        try{
            init();
            
            // Strat UOC
            FaultPartTypeDao faultPartTypeDao = new FaultPartTypeDao(conn);
            faultPartTypeList = faultPartTypeDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return faultPartTypeList;
    }
}
