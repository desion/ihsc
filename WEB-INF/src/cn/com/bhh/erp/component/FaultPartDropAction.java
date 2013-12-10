//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.FaultPartBusiness;
import cn.com.bhh.erp.entity.FaultPart;

@SuppressWarnings("serial")
public class FaultPartDropAction extends BaseAction {
    private List<FaultPart> faultPartList = new ArrayList<FaultPart>();
    private Integer selectedFaultPart;
    private String faultMachineType;

    public FaultPartDropAction() {
        initData();
    }


    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }
    
    public void initData() {
        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPartList = faultPartBusiness.getFaultPartList(faultMachineType);
     }
    
    /**
     * @return the faultPartList
     */
    public List<FaultPart> getFaultPartList() {
        return faultPartList;
    }


    /**
     * @param faultPartList the faultPartList to set
     */
    public void setFaultPartList(List<FaultPart> faultPartList) {
        this.faultPartList = faultPartList;
    }


    /**
     * @return the selectedFaultPart
     */
    public Integer getSelectedFaultPart() {
        return selectedFaultPart;
    }


    /**
     * @param selectedFaultPart the selectedFaultPart to set
     */
    public void setSelectedFaultPart(Integer selectedFaultPart) {
        this.selectedFaultPart = selectedFaultPart;
    }


    /**
     * @param faultMachineType the faultMachineType to set
     */
    public void setFaultMachineType(String faultMachineType) {
        this.faultMachineType = faultMachineType;
    }

    
}
