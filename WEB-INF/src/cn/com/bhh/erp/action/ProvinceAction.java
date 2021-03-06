//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.ProvinceBusiness;
import cn.com.bhh.erp.entity.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * this class include the functions: create,delete,modify and list province.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ProvinceAction extends BaseAction {

    private Province province = new Province();

    private List<Province> provinceList = new ArrayList<Province>();

    /**
     * list the province
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listProvince() throws Exception {

        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        provinceList = provinceBusiness.searchAllProvince(false,null,null);
        if (provinceBusiness.hasErrors()) {
            setActionMessages(getMessageText(provinceBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * direct to the province create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createProvincePre() throws Exception {

        province = new Province();
        return SUCCESS;
    }

    /**
     * the operation of deleting province.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteProvince() throws Exception {

        if (null == province.getId() || null == province.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        provinceBusiness.deleteProvince(province);
        if (provinceBusiness.hasErrors()) {
            setActionMessages(getMessageText(provinceBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the modify province page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyProvincePre() throws Exception {

        if (null == province.getId() || null == province.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        province = provinceBusiness.getSingleProvince(province);
        if (provinceBusiness.hasErrors()) {
            setActionMessages(getMessageText(provinceBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of modifying province.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyProvince() throws Exception {

        if (null == province.getId() || null == province.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }
        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        provinceBusiness.modifyProvinceInfo(province);
        if (provinceBusiness.hasErrors()) {
            setActionMessages(getMessageText(provinceBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * the operation of creating province.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createProvince() throws Exception {

        ProvinceBusiness provinceBusiness = new ProvinceBusiness();
        provinceBusiness.createProvince(province);
        if (provinceBusiness.hasErrors()) {
            setActionMessages(getMessageText(provinceBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * @return the province
     */
    public Province getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(Province province) {
        this.province = province;
    }

    /**
     * @return the provinceList
     */
    public List<Province> getProvinceList() {
        return provinceList;
    }

    /**
     * @param provinceList the provinceList to set
     */
    public void setProvinceList(List<Province> provinceList) {
        this.provinceList = provinceList;
    }

   

}
