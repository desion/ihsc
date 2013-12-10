package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.FaultTypeBusiness;
import cn.com.bhh.erp.entity.FaultType;

@SuppressWarnings("serial")
public class FaultTypeDropAction extends BaseAction {
    private List<FaultType> faultTypeList = new ArrayList<FaultType>();
    private Integer selectedFaultType;


    public FaultTypeDropAction() {
        FaultTypeBusiness faultTypeBusiness = new FaultTypeBusiness();
        faultTypeList = faultTypeBusiness.getFaultTypeList();
    }


    /**
     * @return the faultTypeList
     */
    public List<FaultType> getFaultTypeList() {
        return faultTypeList;
    }


    /**
     * @param faultTypeList the faultTypeList to set
     */
    public void setFaultTypeList(List<FaultType> faultTypeList) {
        this.faultTypeList = faultTypeList;
    }


    /**
     * @return the selectedFaultType
     */
    public Integer getSelectedFaultType() {
        return selectedFaultType;
    }


    /**
     * @param selectedFaultType the selectedFaultType to set
     */
    public void setSelectedFaultType(Integer selectedFaultType) {
        this.selectedFaultType = selectedFaultType;
    }

}
