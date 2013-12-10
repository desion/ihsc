//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.UseStatusBusiness;
import cn.com.bhh.erp.entity.UseStatus;

@SuppressWarnings("serial")
public class UseStatusAction extends BaseAction {
    private List<UseStatus> statusList = new ArrayList<UseStatus>();
    private Integer selectedStatusId;


    public UseStatusAction() {
        UseStatusBusiness useStatusBusiness = new UseStatusBusiness();
        statusList = useStatusBusiness.getStatusList();
    }


    @Override
    public String execute() throws Exception {

    	return SUCCESS;
    }


    public List<UseStatus> getStatusList() {
        return statusList;
    }


    public void setStatusList(List<UseStatus> statusList) {
        this.statusList = statusList;
    }


    public Integer getSelectedStatusId() {
        return selectedStatusId;
    }


    public void setSelectedStatusId(Integer selectedStatusId) {
        this.selectedStatusId = selectedStatusId;
    }

}
