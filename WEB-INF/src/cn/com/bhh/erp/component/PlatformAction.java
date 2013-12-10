package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.PlatformBusiness;
import cn.com.bhh.erp.entity.Platform;

@SuppressWarnings("serial")
public class PlatformAction extends BaseAction{

    private List<Platform> platformList = new ArrayList<Platform>();
    private Integer selectedPlatform;
    
    public PlatformAction(){
        PlatformBusiness pb = new PlatformBusiness();
        platformList = pb.getPlatformListForDrop();
    }

    /**
     * @return the selectedPlatform
     */
    public Integer getSelectedPlatform() {
        return selectedPlatform;
    }

    /**
     * @param selectedPlatform the selectedPlatform to set
     */
    public void setSelectedPlatform(Integer selectedPlatform) {
        this.selectedPlatform = selectedPlatform;
    }

    /**
     * @return the platformList
     */
    public List<Platform> getPlatformList() {
        return platformList;
    }

}
