//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.SupportTypeBusiness;
import cn.com.bhh.erp.entity.SupportType;

@SuppressWarnings("serial")
public class SupportTypeDropAction extends BaseAction {
    private List<SupportType> supportTypeList = new ArrayList<SupportType>();
    private Integer selectedSupportType;

    public SupportTypeDropAction() {
        SupportTypeBusiness supportTypeBusiness = new SupportTypeBusiness();
        supportTypeList = supportTypeBusiness.getSupportTypeList();
    }


    /**
     * @return the supportTypeList
     */
    public List<SupportType> getSupportTypeList() {
        return supportTypeList;
    }


    /**
     * @param supportTypeList the supportTypeList to set
     */
    public void setSupportTypeList(List<SupportType> supportTypeList) {
        this.supportTypeList = supportTypeList;
    }


    /**
     * @return the selectedSupportType
     */
    public Integer getSelectedSupportType() {
        return selectedSupportType;
    }


    /**
     * @param selectedSupportType the selectedSupportType to set
     */
    public void setSelectedSupportType(Integer selectedSupportType) {
        this.selectedSupportType = selectedSupportType;
    }

    
}
