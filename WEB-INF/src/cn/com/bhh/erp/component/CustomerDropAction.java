//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;


/**
 * customer company
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CustomerDropAction extends BaseAction {
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    private Integer selectedProCateId;
    private Integer agentComId;
    private Integer bankId;
    private Integer provinceId;
    private Integer cityId;

    /**
     * get all customer
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
   
    public CustomerDropAction() {
//        initData();
    }
    
    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    
    @SuppressWarnings("unchecked")
    public void initData(){
        CompanyBusiness companyBusiness = new CompanyBusiness();
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        comDropList = companyBusiness.getCustomerCompanyByUser(loginUser,selectedProCateId,agentComId,bankId,provinceId,cityId);
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

    /**
     * @return the selectedProCateId
     */
    public Integer getSelectedProCateId() {
        return selectedProCateId;
    }

    /**
     * @param selectedProCateId the selectedProCateId to set
     */
    public void setSelectedProCateId(Integer selectedProCateId) {
        this.selectedProCateId = selectedProCateId;
    }

    /**
     * @return the agentComId
     */
    public Integer getAgentComId() {
        return agentComId;
    }

    /**
     * @param agentComId the agentComId to set
     */
    public void setAgentComId(Integer agentComId) {
        this.agentComId = agentComId;
    }

    /**
     * @return the bankId
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * @param bankId the bankId to set
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId the provinceId to set
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return the cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }


   
}
