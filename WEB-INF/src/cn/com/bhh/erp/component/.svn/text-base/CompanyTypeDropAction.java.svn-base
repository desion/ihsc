//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CompanyTypeBusiness;
import cn.com.bhh.erp.entity.CompanyType;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a component to list all
 * the company type from company_type_tbl,and select
 * the relative item according to the
 * selectedComTypeId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class CompanyTypeDropAction extends BaseAction {
    private List<CompanyType> comTypeDropList = new ArrayList<CompanyType>();
    private Integer comId;

    public CompanyTypeDropAction() {
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
        CompanyTypeBusiness cb = new CompanyTypeBusiness();
        comTypeDropList = cb.searchAllCompanyType();
    }

    /**
     * @return the comTypeDropList
     */
    public List<CompanyType> getComTypeDropList() {
        return comTypeDropList;
    }

   
    /**
     * @return the comId
     */
    public Integer getComId() {
        return comId;
    }

    /**
     * @param comId the comId to set
     */
    public void setComId(Integer comId) {
        this.comId = comId;
    }
}
