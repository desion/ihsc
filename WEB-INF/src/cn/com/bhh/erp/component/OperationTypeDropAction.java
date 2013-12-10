//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.OperationTypeBusiness;
import cn.com.bhh.erp.entity.OperationType;

import java.util.ArrayList;
import java.util.List;


/**
 * operation type drop down list action.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class OperationTypeDropAction extends BaseAction {
    private List<OperationType> operationTypeDropList = new ArrayList<OperationType>();
    private Integer selectedOperationTypeId;
    
    /**
     * get all operation type.
     * @author luyan
     * @since 1.0
     * @exception Exception
     */
    public OperationTypeDropAction() {
        OperationTypeBusiness operationTypeBusiness = new OperationTypeBusiness();
        operationTypeDropList = operationTypeBusiness.getOperationTypeList();
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the operationTypeDropList
     */
    public List<OperationType> getOperationTypeDropList() {
        return operationTypeDropList;
    }

    /**
     * @param operationTypeDropList the operationTypeDropList to set
     */
    public void setOperationTypeDropList(List<OperationType> operationTypeDropList) {
        this.operationTypeDropList = operationTypeDropList;
    }

    /**
     * @return the selectedOperationTypeId
     */
    public Integer getSelectedOperationTypeId() {
        return selectedOperationTypeId;
    }

    /**
     * @param selectedOperationTypeId the selectedOperationTypeId to set
     */
    public void setSelectedOperationTypeId(Integer selectedOperationTypeId) {
        this.selectedOperationTypeId = selectedOperationTypeId;
    }

}
