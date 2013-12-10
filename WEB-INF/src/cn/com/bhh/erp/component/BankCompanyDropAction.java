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
public class BankCompanyDropAction extends BaseAction {
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    private Integer bankId;
    private Integer provinceId;
    private Integer cityId;
    private Integer saleComId;
    public BankCompanyDropAction() {
        
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    public void initData() {
       
        CompanyBusiness companyBusiness = new CompanyBusiness();
        comDropList=companyBusiness.getCompanyCustomer(bankId, provinceId, cityId,saleComId);
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

    /**
     * @return the saleComId
     */
    public Integer getSaleComId() {
        return saleComId;
    }

    /**
     * @param saleComId the saleComId to set
     */
    public void setSaleComId(Integer saleComId) {
        this.saleComId = saleComId;
    }  
    
    
}
