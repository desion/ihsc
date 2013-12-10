package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.InstallationApplyBusiness;
import cn.com.bhh.erp.entity.InstallationApply;

@SuppressWarnings("serial")
public class ApplyTypeAction extends BaseAction{

    private List<InstallationApply> list = new ArrayList<InstallationApply>();
    
    private Integer selectedapplyType;
        
    public ApplyTypeAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * prepare the data for the city drop list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     */
    public void initData() {
        InstallationApplyBusiness ib = new InstallationApplyBusiness();
        list = ib.getApplyTypeForDrop();
    }

    public List<InstallationApply> getList() {
        return list;
    }

    public void setList(List<InstallationApply> list) {
        this.list = list;
    }

    public Integer getSelectedapplyType() {
        return selectedapplyType;
    }

    public void setSelectedapplyType(Integer selectedapplyType) {
        this.selectedapplyType = selectedapplyType;
    }
}
