//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.PurposeDao;
import cn.com.bhh.erp.entity.Purpose;

public class PurposeBusiness extends BaseBusiness{

    public List<Purpose> getPurposeList() {
        List<Purpose> purposeList = new ArrayList<Purpose>();
        
        try{
            init();
            
            // Strat UOC
            PurposeDao purposeDao = new PurposeDao(conn);
            purposeList = purposeDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return purposeList;
    }
}
