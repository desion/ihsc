package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.InstallationBusiness;
import cn.com.bhh.erp.entity.Installation;

@SuppressWarnings("serial")
public class InstPlaceTypeAction extends BaseAction{
    private List<Installation> list = new ArrayList<Installation>();
    private Integer selectedTypeId;
    
    public InstPlaceTypeAction(){
        InstallationBusiness ib = new InstallationBusiness();
        list = ib.serchInstPlaceType();
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the selectedTypeId
     */
    public Integer getSelectedTypeId() {
        return selectedTypeId;
    }

    /**
     * @param selectedTypeId the selectedTypeId to set
     */
    public void setSelectedTypeId(Integer selectedTypeId) {
        this.selectedTypeId = selectedTypeId;
    }

    /**
     * @return the typeList
     */
    public List<Installation> getList() {
        return list;
    }
}
