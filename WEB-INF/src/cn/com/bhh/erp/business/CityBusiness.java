//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.dao.CityDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.ProvinceDao;
import cn.com.bhh.erp.entity.City;
import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the base business of deal with the city.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class CityBusiness extends BaseBusiness {
    /**
     * create the city
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param city
     */
    public void createCity(City city) {
        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            String cityName = city.getName().trim();
            city.setName(cityName);

            if (cityDao.getCountByName(city) > 0) {
                // decide whether db has the same name with the input name
                errors.add("BSE00000,city.name");
                return;
            }

            ProvinceDao provinceDao = new ProvinceDao(conn);
            if (provinceDao.getCountByProvinceId(city.getProvinceId()) == 0) {
                errors.add("BSE01303");
                return;
            }
            String shortName = city.getShortName();
            shortName = shortName.trim().toUpperCase();
            city.setShortName(shortName);

            cityDao.createCity(city);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the city .
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param city
     */
    public void deleteCity(City city) {
        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            CompanyDao comDao = new CompanyDao(conn);

            // get the company count related to the city from company_tbl
            if (comDao.getCompanyCountByCity(city) > 0) {
                errors.add("BSE01301");
                return;
            }

            cityDao.deleteCity(city);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * moidfy city
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param city
     */
    public void modifyCityInfo(City city) {
        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            String cityName = city.getName().trim();
            city.setName(cityName);

            // get the same name count of city
            int sameNameCount = cityDao.getCountByNameModify(city);
            if (sameNameCount > 0) {
                errors.add("BSE00000,city.name");
                return;
            }

            // check whether the province selected exsits.
            ProvinceDao provinceDao = new ProvinceDao(conn);
            if (provinceDao.getCountByProvinceId(city.getProvinceId()) == 0) {
                errors.add("BSE01303");
                return;
            }

            String shortName = city.getShortName();
            shortName = shortName.trim().toUpperCase();
            city.setShortName(shortName);
            
            cityDao.modifyCityInfo(city);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }


    /**
     * get the city for drop component.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCity&gt
     */
    public List<City> getCitysForDrop(
            boolean isInCompany,
            Integer provinceId,
            Integer bankId,
            Integer saleComId) {
        List<City> citysOut = new ArrayList<City>();

        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            citysOut = cityDao.getCityDrop(isInCompany,provinceId,bankId,saleComId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return citysOut;
    }

    /**
     * get city by id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param city
     * @return City
     */
    public City getSingleCity(City city) {
        City cityOut = null;

        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            cityOut = cityDao.searchCityById(city);

            if (null == cityOut) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return cityOut;
    }



    /**
     * get city by page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param currPage
     * @param pageSize
     * @return List&ltCity&gt
     */
    public List<City> getCitysByPage(City city,int currPage, int pageSize) {
        List<City> citysOut = new ArrayList<City>();

        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }

            citysOut = cityDao.getCitysByPage(city,intBegin, intEnd);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return citysOut;
    }

    /**
     * get the city counts.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return int
     */
    public int getCityCounts(City city) {
        int cityCounts = 0;

        try {
            init();

            // Start UOC

            CityDao cityDao = new CityDao(conn);
            cityCounts = cityDao.getCityCounts(city);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return cityCounts;
    }
}
