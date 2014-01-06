//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.FaultStatusDao;
import cn.com.bhh.erp.entity.FaultStatus;

/**
 * fault status drop down list business.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
public class FaultStatusBusiness extends BaseBusiness{

    /**
     * get fault status list.
     * @author luyan
     * @since 1.0
     * @return fault status list
     */
    public List<FaultStatus> getFaultStatusList() {
        List<FaultStatus> faultStatusList = new ArrayList<FaultStatus>();
        
        try{
            init();
            
            // Start UOC
            FaultStatusDao faultStatusDao = new FaultStatusDao(conn);
            faultStatusList = faultStatusDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return faultStatusList;
    }
}
