package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.OsPermitBusiness;
import cn.com.bhh.erp.entity.OsPermit;

@SuppressWarnings("serial")
public class OsPermitAction extends BaseAction{
    private List<OsPermit> osPermitList = new ArrayList<OsPermit>();
    private Integer selectedOsPermit;
    
    public OsPermitAction(){
        OsPermitBusiness osPermitBusiness = new OsPermitBusiness();
        osPermitList = osPermitBusiness.getOsPermitListForDrop();
    }


    /**
     * @return the osPermitList
     */
    public List<OsPermit> getOsPermitList() {
        return osPermitList;
    }


    /**
     * @param osPermitList the osPermitList to set
     */
    public void setOsPermitList(List<OsPermit> osPermitList) {
        this.osPermitList = osPermitList;
    }


    /**
     * @return the selectedOsPermit
     */
    public Integer getSelectedOsPermit() {
        return selectedOsPermit;
    }

    /**
     * @param selectedOsPermit the selectedOsPermit to set
     */
    public void setSelectedOsPermit(Integer selectedOsPermit) {
        this.selectedOsPermit = selectedOsPermit;
    }
   
    
}
