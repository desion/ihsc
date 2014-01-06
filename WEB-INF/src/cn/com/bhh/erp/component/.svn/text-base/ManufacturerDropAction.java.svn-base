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
 * manufactury company
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ManufacturerDropAction extends BaseAction {
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    
    /**
     * get all manufacturer
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public ManufacturerDropAction() {
        CompanyBusiness companyBusiness = new CompanyBusiness();
        String subSql = " AND TYPE_ID LIKE '%,1,%' ";
        comDropList = companyBusiness.getCompanyByType(subSql);
    }
    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the comDropList
     */
    public List<Company> getComDropList() {
        return comDropList;
    }

    /**
     * @param comDropList the comDropList to set
     */
    public void setComDropList(List<Company> comDropList) {
        this.comDropList = comDropList;
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
