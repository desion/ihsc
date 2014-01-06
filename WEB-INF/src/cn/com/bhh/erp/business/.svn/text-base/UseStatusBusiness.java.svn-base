//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.UseStatusDao;
import cn.com.bhh.erp.entity.UseStatus;

public class UseStatusBusiness extends BaseBusiness{

    public List<UseStatus> getStatusList() {
        List<UseStatus> statusList = new ArrayList<UseStatus>();
        
        try{
            init();
            
            // Strat UOC
            UseStatusDao useStatusDao = new UseStatusDao(conn);
            statusList = useStatusDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return statusList;
    }
}
