//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.CompanyTypeBusiness;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.CompanyType;

import java.util.ArrayList;
import java.util.List;

/**
 * include the functions: create,modify,delete and list companyType.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class CompanyTypeAction extends BaseAction {
    private CompanyType companyType = new CompanyType();
    private List<CompanyType> comTypeList = new ArrayList<CompanyType>();

    /**
     * list the companyType
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listComType() throws Exception {
        CompanyTypeBusiness companyTypeBusiness = new CompanyTypeBusiness();
        comTypeList = companyTypeBusiness.searchAllCompanyType();

        if (companyTypeBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyTypeBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the create companyType page
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createComTypePre() throws Exception {
        companyType = new CompanyType();

        return SUCCESS;
    }

    /**
     * direct to company type create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createComType() throws Exception {
        CompanyTypeBusiness companyTypeBusiness = new CompanyTypeBusiness();
        companyType.setCreatorID(loginUser.getId());
        companyType.setModifierID(loginUser.getId());
        companyTypeBusiness.createCompanyType(companyType);

        if (companyTypeBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyTypeBusiness.getErrors()));

            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of delete companyType
     * 
     * @author xiangzq08
     *@return String
     */
    public String deleteComType() throws Exception {
        if (null == companyType.getId() || null == companyType.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CompanyTypeBusiness companyTypeBusiness = new CompanyTypeBusiness();
        companyTypeBusiness.deleteCompanyType(companyType);

        if (companyTypeBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyTypeBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the modify company type page.
     * 
     * @author xiangzq08
     *@return String
     */
    public String modifyComTypePre() throws Exception {
        if (null == companyType.getId() || null == companyType.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        CompanyTypeBusiness companyTypeBusiness = new CompanyTypeBusiness();
        companyType = companyTypeBusiness.getSingleCompanyType(companyType);

        if (companyTypeBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyTypeBusiness.getErrors()));

            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation and modify the company type.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyComType() throws Exception {

        if (null == companyType.getId() || null == companyType.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        CompanyTypeBusiness companyTypeBusiness = new CompanyTypeBusiness();
        companyType.setModifierID(loginUser.getId());
        companyType.setModifyTime(TimeUtil.getNow());
        companyTypeBusiness.modifyCompanyTypeInfo(companyType);

        if (companyTypeBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyTypeBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

  

    /**
     * @return the companyType
     */
    public CompanyType getCompanyType() {
        return companyType;
    }

    /**
     * @param companyType the companyType to set
     */
    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    /**
     * @return the comTypeList
     */
    public List<CompanyType> getComTypeList() {
        return comTypeList;
    }

    /**
     * @param comTypeList the comTypeList to set
     */
    public void setComTypeList(List<CompanyType> comTypeList) {
        this.comTypeList = comTypeList;
    }
}
