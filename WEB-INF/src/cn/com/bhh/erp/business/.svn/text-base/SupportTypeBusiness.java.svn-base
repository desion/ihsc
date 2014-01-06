//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.SupportTypeDao;
import cn.com.bhh.erp.entity.SupportType;

public class SupportTypeBusiness extends BaseBusiness{

    public List<SupportType> getSupportTypeList() {
        List<SupportType> supportTypeList = new ArrayList<SupportType>();
        
        try{
            init();
            
            // Strat UOC
            SupportTypeDao supportTypeDao = new SupportTypeDao(conn);
            supportTypeList = supportTypeDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return supportTypeList;
    }
}
