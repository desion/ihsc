package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.OSBusiness;
import cn.com.bhh.erp.entity.OS;

@SuppressWarnings("serial")
public class OSAction extends BaseAction{
    private List<OS> OSList = new ArrayList<OS>();
    private Integer selectedOS;
    
    public OSAction(){
        OSBusiness ob = new OSBusiness();
        OSList = ob.getOSListForDrop();
    }
    /**
     * @return the selectedOS
     */
    public Integer getSelectedOS() {
        return selectedOS;
    }
    /**
     * @param selectedOS the selectedOS to set
     */
    public void setSelectedOS(Integer selectedOS) {
        this.selectedOS = selectedOS;
    }
    /**
     * @return the oSList
     */
    public List<OS> getOSList() {
        return OSList;
    }
    
}
