//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.FaultPartTypeBusiness;
import cn.com.bhh.erp.entity.FaultPartType;

@SuppressWarnings("serial")
public class FaultPartTypeDropAction extends BaseAction {
    private List<FaultPartType> faultPartTypeList = new ArrayList<FaultPartType>();
    private Integer selectedFaultPartType;


    public FaultPartTypeDropAction() {
        FaultPartTypeBusiness faultPartTypeBusiness = new FaultPartTypeBusiness();
        faultPartTypeList = faultPartTypeBusiness.getFaultPartTypeList();
    }


    /**
     * @return the faultPartTypeList
     */
    public List<FaultPartType> getFaultPartTypeList() {
        return faultPartTypeList;
    }


    /**
     * @param faultPartTypeList the faultPartTypeList to set
     */
    public void setFaultPartTypeList(List<FaultPartType> faultPartTypeList) {
        this.faultPartTypeList = faultPartTypeList;
    }


    /**
     * @return the selectedFaultPartType
     */
    public Integer getSelectedFaultPartType() {
        return selectedFaultPartType;
    }


    /**
     * @param selectedFaultPartType the selectedFaultPartType to set
     */
    public void setSelectedFaultPartType(Integer selectedFaultPartType) {
        this.selectedFaultPartType = selectedFaultPartType;
    }


}
