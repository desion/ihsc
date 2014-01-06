//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CountryBusiness;
import cn.com.bhh.erp.entity.Country;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a component to list all
 * the country from country_tbl,and select
 * the relative item according to the
 * selectedCountryId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class CountryDropAction extends BaseAction {
    private List<Country> countryDropList = new ArrayList<Country>();
    private Integer selectedCountryId;

    public CountryDropAction() {
        CountryBusiness cb = new CountryBusiness();
        countryDropList = cb.searchAllCountry();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the countryDropList
     */
    public List<Country> getCountryDropList() {
        return countryDropList;
    }


    /**
     * @return the selectedCountryId
     */
    public Integer getSelectedCountryId() {
        return selectedCountryId;
    }

    /**
     * @param selectedCountryId the selectedCountryId to set
     */
    public void setSelectedCountryId(Integer selectedCountryId) {
        this.selectedCountryId = selectedCountryId;
    }
}
