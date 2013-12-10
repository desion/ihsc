//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.dao.CountryDao;
import cn.com.bhh.erp.entity.Country;

import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the base business of deal with the country.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class CountryBusiness extends BaseBusiness {
    /**
     * create the country
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Country
     */
    public void createCountry(Country country) {
        try {
            init();

            // Start UOC

            CountryDao countryDao = new CountryDao(conn);
            country.setName(country.getName().trim());
            country.setNameEn(country.getNameEn().trim());
            country.setShortName(country.getShortName().trim());
            country.setShortNameEn(country.getShortNameEn().trim());

            // check whether the same name exists.
            if (countryDao.getCountByCountryName(country) > 0) {
                errors.add("BSE00000,country.name");
                return;
            }

            // check whether the same english name exists.
            if (countryDao.getCountByCountryNameEn(country) > 0) {
                errors.add("BSE00000,country.nameEn");
                return;
            }

            // check whether the same short name exists.
            if (countryDao.getCountByCounryShortName(country) > 0) {
                errors.add("BSE00000,country.shortName");
                return;
            }

            // check whether the same short english name exists.
            if (countryDao.getCountByCountryShortNameEn(country) > 0) {
                errors.add("BSE00000,country.shortNameEn");
                return;
            }

            countryDao.create(country);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Country
     */
    public void deleteCountry(Country country) {
        try {
            init();

            // Start UOC

            CountryDao countryDao = new CountryDao(conn);
            int provinceCounts = countryDao.getProvinceCountOfCountry(country);

            // check whether this country has provinces
            if (provinceCounts > 0) {
                errors.add("BSE01102");
                return;
            }

            countryDao.delete(country);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * moidfy country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Country
     */
    public void modifyCountryInfo(Country country) {
        try {
            init();

            // Start UOC

            CountryDao countryDao = new CountryDao(conn);

            country.setName(country.getName().trim());
            country.setNameEn(country.getNameEn().trim());
            country.setShortName(country.getShortName().trim());
            country.setShortNameEn(country.getShortNameEn().trim());

            // check whether the same name exists beside self.
            if (countryDao.getCountByCountryNameModify(country) > 0) {
                errors.add("BSE00000,country.name");
                return;
            }

            // check whether the same english name exists beside self.
            if (countryDao.getCountByCountryNameEnModify(country) > 0) {
                errors.add("BSE00000,country.nameEn");
                return;
            }

            // check whether the same short name exists beside self.
            if (countryDao.getCountByCountryShortNameModify(country) > 0) {
                errors.add("BSE00000,country.shortName");
                return;
            }

            // check whether the same short english name exists beside self.
            if (countryDao.getCountByCountryShortNameEnModify(country) > 0) {
                errors.add("BSE00000,country.shortNameEn");
                return;
            }

            countryDao.modifyCountryInfo(country);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the country.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCountry&gt
     */
    public List<Country> searchAllCountry() {
        List<Country> countryOut = new ArrayList<Country>();

        try {
            init();

            // Start UOC

            CountryDao countryDao = new CountryDao(conn);
            countryOut = countryDao.searchAll();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return countryOut;
    }

    /**
     * get one country by id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Country
     * @return Country
     */
    public Country getSingleCountry(Country country) {
        Country countryOut = null;

        try {
            init();

            // Start UOC

            CountryDao countryDao = new CountryDao(conn);
            countryOut = countryDao.searchById(country);

            if (null == countryOut) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return countryOut;
    }
}
