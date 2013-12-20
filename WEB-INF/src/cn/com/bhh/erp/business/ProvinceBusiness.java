//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.dao.CountryDao;
import cn.com.bhh.erp.dao.ProvinceDao;
import cn.com.bhh.erp.entity.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the business of deal with the province.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class ProvinceBusiness extends BaseBusiness {
    /**
     * create province
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void createProvince(Province province) throws Exception {
        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            String provinceName = province.getName().trim();
            province.setName(provinceName);

            // check whether the same name exists.
            if (provinceDao.getCountByProvinceName(province) > 0) {
                errors.add("BSE00000,province.name");
                return;
            }

            // check whether the selected country exsits.
            CountryDao countryDao = new CountryDao(conn);
            if (countryDao.getCountByCountryId(province.getCountryId()) == 0) {
                errors.add("BSE01203");
                return;
            }
            String shortName = province.getShortName();
            shortName = shortName.trim().toUpperCase();
            province.setShortName(shortName);
            
            provinceDao.createProvince(province);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the province.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void deleteProvince(Province province) throws Exception {
        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            // check whether the province has citys.
            int cityCounts = provinceDao.getCityCountOfProvince(province);

            if (cityCounts > 0) {
                errors.add("BSE01202");
                return;
            }
            provinceDao.deleteProvince(province);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * moidfy province information.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     */
    public void modifyProvinceInfo(Province province) throws Exception {
        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            String provinceName = province.getName().trim();

            province.setName(provinceName);

            // check whether the same name exists beside self.
            int sameNameCount = provinceDao.getCountByProvinceNameModify(province);

            if (sameNameCount > 0) {
                errors.add("BSE00000,province.name");
                return;
            }

            // check whether the selected country exsits.
            CountryDao countryDao = new CountryDao(conn);
            if (countryDao.getCountByCountryId(province.getCountryId()) == 0) {
                errors.add("BSE01203");
                return;
            }

            String shortName = province.getShortName();
            shortName = shortName.trim().toUpperCase();
            province.setShortName(shortName);
            
            provinceDao.modifyProvinceInfo(province);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * list all the province information.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<Province> searchAllProvince(boolean isInCompany,Integer bankId,Integer saleComId) {
        List<Province> provinceOut = new ArrayList<Province>();

        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceOut = provinceDao.searchAllProvince(isInCompany,bankId,saleComId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return provinceOut;
    }
    
    /**
     * list all the province information.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltProvince&gt
     */
    public List<Province> searchAllProvince() {
        List<Province> provinceOut = new ArrayList<Province>();

        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceOut = provinceDao.searchAllProvince();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return provinceOut;
    }
    
    /**
     * list all the province information of the country.
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   countryId
     * @return  List&ltProvince&gt
     */
    public List<Province> searchProvinceByCountryId(Integer countryId) {
        List<Province> provinceOut = new ArrayList<Province>();

        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceOut = provinceDao.searchProvinceByCountryId(countryId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return provinceOut;
    }

    

    /**
     * get one province by id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param province
     * @return Province
     */
    public Province getSingleProvince(Province province) throws Exception {
        Province provinceOut = null;

        try {
            init();

            // Start UOC

            ProvinceDao provinceDao = new ProvinceDao(conn);
            provinceOut = provinceDao.searchProvinceById(province);

            if (null == provinceOut) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return provinceOut;
    }

}
