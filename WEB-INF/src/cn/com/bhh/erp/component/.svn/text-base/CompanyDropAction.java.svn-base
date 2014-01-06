//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.entity.Company;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a component to list all
 * the company from company_tbl,and select
 * the relative item according to the
 * selectedComId
 *
 */
@SuppressWarnings("serial")
public class CompanyDropAction extends BaseAction {
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    public CompanyDropAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    public void initData() {
       
        CompanyBusiness cb = new CompanyBusiness();
        comDropList=cb.getCompanyForUser();
    }

    /**
     * @return the comDropList
     */
    public List<Company> getComDropList() {
        return comDropList;
    }


    /**
     * @return the selectedComId
     */
    public Integer getSelectedComId() {
        return selectedComId;
    }

    /**
     * @param selectedComId the selectedComId to set
     */
    public void setSelectedComId(Integer selectedComId) {
        this.selectedComId = selectedComId;
    }  
}
