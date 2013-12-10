//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CityBusiness;
import cn.com.bhh.erp.entity.City;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a component to list all
 * the city from city_tbl,and select
 * the relative item according to the
 * selectedCityId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class CityDropAction extends BaseAction {
    private List<City> cityDropList = new ArrayList<City>();
    private Integer selectedCityId;
    private Integer provinceId;
    private Integer bankId;
    private Integer saleComId;
    private Integer useFlag; //0:列出所有的城市，1：仅列出公司表中存在的城市，默认列出所有。
    public CityDropAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * prepare the data for the city drop list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     */
    public void initData() {
       CityBusiness cityBusiness = new CityBusiness();
       if(useFlag!=null){
           if(useFlag==1){
               //仅列出公司表中存在的城市
               cityDropList = cityBusiness.getCitysForDrop(true,provinceId,bankId,saleComId);
           }else{
               cityDropList = cityBusiness.getCitysForDrop(false,provinceId,bankId,saleComId);
           }
       }else{
           cityDropList = cityBusiness.getCitysForDrop(false,provinceId,bankId,saleComId);
       }
   
    }

    /**
     * @return the cityDropList
     */
    public List<City> getCityDropList() {
        return cityDropList;
    }


    /**
     * @return the selectedCityId
     */
    public Integer getSelectedCityId() {
        return selectedCityId;
    }

    /**
     * @param selectedCityId the selectedCityId to set
     */
    public void setSelectedCityId(Integer selectedCityId) {
        this.selectedCityId = selectedCityId;
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
