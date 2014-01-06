//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.FaultStatusBusiness;
import cn.com.bhh.erp.entity.FaultStatus;

import java.util.ArrayList;
import java.util.List;


/**
 * fault status drop down list action.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FaultStatusDropAction extends BaseAction {
    private List<FaultStatus> faultStatusDropList = new ArrayList<FaultStatus>();
    private Integer selectedFaultStatusId;
    
    /**
     * get all fault status.
     * @author luyan
     * @since 1.0
     * @exception Exception
     */
    public FaultStatusDropAction() {
        FaultStatusBusiness faultStatusBusiness = new FaultStatusBusiness();
        faultStatusDropList = faultStatusBusiness.getFaultStatusList();
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the faultStatusDropList
     */
    public List<FaultStatus> getFaultStatusDropList() {
        return faultStatusDropList;
    }

    /**
     * @param faultStatusDropList the faultStatusDropList to set
     */
    public void setFaultStatusDropList(List<FaultStatus> faultStatusDropList) {
        this.faultStatusDropList = faultStatusDropList;
    }

    /**
     * @return the selectedFaultStatusId
     */
    public Integer getSelectedFaultStatusId() {
        return selectedFaultStatusId;
    }

    /**
     * @param selectedFaultStatusId the selectedFaultStatusId to set
     */
    public void setSelectedFaultStatusId(Integer selectedFaultStatusId) {
        this.selectedFaultStatusId = selectedFaultStatusId;
    }

}
