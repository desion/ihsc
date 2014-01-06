//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.CountryBusiness;
import cn.com.bhh.erp.entity.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * this class include the functions: create,delete,modify and list country.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CountryAction extends BaseAction {
    private Country country = new Country();
    private List<Country> countryList = new ArrayList<Country>();

    /**
     * list the country
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listCountry() throws Exception {
        CountryBusiness countryBusiness = new CountryBusiness();
        countryList = countryBusiness.searchAllCountry();

        if (countryBusiness.hasErrors()) {
            setActionMessages(getMessageText(countryBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the country create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCountryPre() throws Exception {
        country = new Country();

        return SUCCESS;
    }

    /**
     * the operation of deleting country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteCountry() throws Exception {

        if (null == country.getId() || null == country.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CountryBusiness countryBusiness = new CountryBusiness();
        countryBusiness.deleteCountry(country);
        if (countryBusiness.hasErrors()) {
            setActionMessages(getMessageText(countryBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the modify country page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCountryPre() throws Exception {
        if (null == country.getId() || null == country.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CountryBusiness countryBusiness = new CountryBusiness();
        country = countryBusiness.getSingleCountry(country);

        if (countryBusiness.hasErrors()) {
            setActionMessages(getMessageText(countryBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of modifying country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCountry() throws Exception {

        if (null == country.getId() || null == country.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CountryBusiness countryBusiness = new CountryBusiness();
        countryBusiness.modifyCountryInfo(country);

        if (countryBusiness.hasErrors()) {
            setActionMessages(getMessageText(countryBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of creating country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCountry() throws Exception {
        CountryBusiness countryBusiness = new CountryBusiness();
        countryBusiness.createCountry(country);

        if (countryBusiness.hasErrors()) {
            setActionMessages(getMessageText(countryBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the countryList
     */
    public List<Country> getCountryList() {
        return countryList;
    }

    /**
     * @param countryList the countryList to set
     */
    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
