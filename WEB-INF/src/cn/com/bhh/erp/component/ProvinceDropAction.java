//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.ProvinceBusiness;
import cn.com.bhh.erp.entity.Province;


/**
 * this is a component to list all
 * the province from province_tbl,and select
 * the relative item according to the
 * selectedProvinceId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class ProvinceDropAction extends BaseAction {
    private List<Province> provinceDropList = new ArrayList<Province>();
    private Integer selectedProvinceId;
    private Integer countryId;
    private Integer bankId;
    private Integer saleComId;
    private Integer useFlag;//0:列出所有的省份，1：仅列出公司表中存在的省份。
    public ProvinceDropAction() {
       initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    private void initData(){
        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        if(countryId!=null){
            provinceDropList = provinceBusiness.searchProvinceByCountryId(countryId);
        }else{
            if(useFlag!=null){
                if(useFlag==1){
                    provinceDropList = provinceBusiness.searchAllProvince(true,bankId,saleComId);
                }else{
                    provinceDropList = provinceBusiness.searchAllProvince(false,bankId,saleComId);
                }
            }else{
                //默认列出所有省份
                provinceDropList = provinceBusiness.searchAllProvince(false,bankId,saleComId);
            }
        }
    }
    /**
     * @return the provinceDropList
     */
    public List<Province> getProvinceDropList() {
        return provinceDropList;
    }


    /**
     * @return the selectedProvinceId
     */
    public Integer getSelectedProvinceId() {
        return selectedProvinceId;
    }

    /**
     * @param selectedProvinceId the selectedProvinceId to set
     */
    public void setSelectedProvinceId(Integer selectedProvinceId) {
        this.selectedProvinceId = selectedProvinceId;
    }

    /**
     * @return the countryId
     */
    public Integer getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the useFlag
     */
    public Integer getUseFlag() {
        return useFlag;
    }

    /**
     * @param useFlag the useFlag to set
     */
    public void setUseFlag(Integer useFlag) {
        this.useFlag = useFlag;
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
