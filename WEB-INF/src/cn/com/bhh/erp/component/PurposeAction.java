//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.PurposeBusiness;
import cn.com.bhh.erp.entity.Purpose;

@SuppressWarnings("serial")
public class PurposeAction extends BaseAction {
    private List<Purpose> purposeList = new ArrayList<Purpose>();
    private Integer selectedPurposeId;


    public PurposeAction() {
        PurposeBusiness purposeBusiness = new PurposeBusiness();
        purposeList = purposeBusiness.getPurposeList();
    }


    @Override
    public String execute() throws Exception {

    	return SUCCESS;
    }


    public List<Purpose> getPurposeList() {
        return purposeList;
    }


    public void setPurposeList(List<Purpose> purposeList) {
        this.purposeList = purposeList;
    }


    public Integer getSelectedPurposeId() {
        return selectedPurposeId;
    }


    public void setSelectedPurposeId(Integer selectedPurposeId) {
        this.selectedPurposeId = selectedPurposeId;
    }

}
