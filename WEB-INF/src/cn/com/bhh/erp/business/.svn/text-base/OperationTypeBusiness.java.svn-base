//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.OperationTypeDao;
import cn.com.bhh.erp.entity.OperationType;

/**
 * operation type drop down list business.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
public class OperationTypeBusiness extends BaseBusiness{

    /**
     * get operation type list.
     * @author luyan
     * @since 1.0
     * @return operation type list
     */
    public List<OperationType> getOperationTypeList() {
        List<OperationType> operationType = new ArrayList<OperationType>();
        
        try{
            init();
            
            // Start UOC
            OperationTypeDao operationTypeDao = new OperationTypeDao(conn);
            operationType = operationTypeDao.searchAll();
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return operationType;
    }
}
