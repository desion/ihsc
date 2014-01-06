//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************

package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;
import cn.com.bhh.erp.business.CityBusiness;
import cn.com.bhh.erp.entity.City;

/**
 * include the functions as follows: create,modify,delete city
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CityAction extends BaseAction {
    private City city = new City();
    private List<City> cityList = new ArrayList<City>();

    /**
     * list the city by page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listCity() throws Exception {
        CityBusiness cityBusiness = new CityBusiness();

        setTotalCount(cityBusiness.getCityCounts(city));

        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }

        cityList = cityBusiness.getCitysByPage(city,currPage, pageSize);
        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * direct to create city page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCityPre() throws Exception {
        city = new City();
        return SUCCESS;
    }

    /**
     * delete the city.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteCity() throws Exception {

        if (null == city.getId() || null == city.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CityBusiness cityBusiness = new CityBusiness();
        cityBusiness.deleteCity(city);
        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to city infomation page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCityPre() throws Exception {

        if (null == city.getId() || null == city.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CityBusiness cityBusiness = new CityBusiness();
        city = cityBusiness.getSingleCity(city);
        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of modify the city.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyCity() throws Exception {

        if (null == city.getId() || null == city.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CityBusiness cityBusiness = new CityBusiness();
        cityBusiness.modifyCityInfo(city);
        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * create the city operation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createCity() throws Exception {

        CityBusiness cityBusiness = new CityBusiness();
        cityBusiness.createCity(city);
        if (cityBusiness.hasErrors()) {
            setActionMessages(getMessageText(cityBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }
    
    /**
     * direct to the city search page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String searchCityPre() throws Exception {
        return SUCCESS;
    }
    
    /**
     * the operation of excuting search
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String searchCity() throws Exception {
        return SUCCESS;
    }
    
    

    /**
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return the cityList
     */
    public List<City> getCityList() {
        return cityList;
    }

    /**
     * @param cityList the cityList to set
     */
    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

}
