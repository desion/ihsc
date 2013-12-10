//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.CompanyTypeDao;
import cn.com.bhh.erp.entity.CompanyType;

import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the base business of deal with the company type.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class CompanyTypeBusiness extends BaseBusiness {
    /**
     * create the company type
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param companyType
     */
    public void createCompanyType(CompanyType comType) {
        try {
            init();

            // Start UOC

            CompanyTypeDao comTypeDao = new CompanyTypeDao(conn);
            comType.setName(comType.getName().trim());

            if (comTypeDao.getCountByComTypeName(comType) > 0) {
                // decide whether db has the same name with the input name
                errors.add("BSE00000,companyType.name");
                return;
            }
            comType.setCreateTime(TimeUtil.getNow());
            comType.setModifyTime(TimeUtil.getNow());
            comTypeDao.createCompanyType(comType);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the company type
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param companyType
     */
    public void deleteCompanyType(CompanyType comType) {
        try {
            init();

            // Start UOC

            CompanyTypeDao comTypeDao = new CompanyTypeDao(conn);
            CompanyDao comDao = new CompanyDao(conn);

            CompanyType dbComType = comTypeDao.searchCompanyTypeById(comType);

            if (dbComType == null) {
                errors.add("BSF00006");
                return;
            }

            if (dbComType.getModifyFlg() == 0) {
                errors.add("BSE01403");
                return;
            }

            // check the company size that related to this company type.
            if (comDao.getCompSizeByComType(comType) > 0) {
                errors.add("BSE01401");
                return;
            }

            comTypeDao.deleteCompanyType(comType);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the company type.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param companyType
     */
    public void modifyCompanyTypeInfo(CompanyType comType) {
        try {
            init();

            // Start UOC

            CompanyTypeDao comTypeDao = new CompanyTypeDao(conn);
            comType.setName(comType.getName().trim());

            if (comTypeDao.getCountByComTypeNameModify(comType) > 0) {
                // decide whether db has the same name with the input name
                errors.add("BSE00000,companyType.name");
                return;
            }

            comTypeDao.modifyCompanyTypeInfo(comType);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the company type.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltCompanyType&gt
     */
    public List<CompanyType> searchAllCompanyType() {
        List<CompanyType> comTypeOut = new ArrayList<CompanyType>();

        try {
            init();

            // Start UOC

            CompanyTypeDao comTypeDao = new CompanyTypeDao(conn);
            comTypeOut = comTypeDao.selectAllCompanyType();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comTypeOut;
    }

    /**
     * get the company type by id
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param comType
     * @return CompanyType
     */
    public CompanyType getSingleCompanyType(CompanyType comType) {
        CompanyType comTypeOut = null;

        try {
            init();

            // Start UOC

            CompanyTypeDao comTypeDao = new CompanyTypeDao(conn);
            comTypeOut = comTypeDao.searchCompanyTypeById(comType);

            if (null == comTypeOut) {
                errors.add("BSF00006");
            } else {
                if (comTypeOut.getModifyFlg() == 0) {
                    errors.add("BSE01402");
                }
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return comTypeOut;
    }
}
