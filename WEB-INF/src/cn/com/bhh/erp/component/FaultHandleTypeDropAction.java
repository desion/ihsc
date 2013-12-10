package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.FaultHandleBusiness;
import cn.com.bhh.erp.entity.FaultHandle;

@SuppressWarnings("serial")
public class FaultHandleTypeDropAction extends BaseAction{

    private List<FaultHandle> handleTypelist = new ArrayList<FaultHandle>();
    
    private Integer selectedFaultHandleType;

    public FaultHandleTypeDropAction() {
        FaultHandleBusiness fhb = new FaultHandleBusiness();
        handleTypelist = fhb.getHandleTypeForDrop();
    }

    @Override
    public String execute() throws Exception {
        FaultHandleBusiness fhb = new FaultHandleBusiness();
        handleTypelist = fhb.getHandleTypeForDrop();
        return SUCCESS;
    }

    public List<FaultHandle> getHandleTypelist() {
        return handleTypelist;
    }

    public Integer getSelectedFaultHandleType() {
        return selectedFaultHandleType;
    }

    public void setSelectedFaultHandleType(Integer selectedFaultHandleType) {
        this.selectedFaultHandleType = selectedFaultHandleType;
    }


}
