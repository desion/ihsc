//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************

package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.FaultPartBusiness;
import cn.com.bhh.erp.entity.FaultPart;

/**
 * include the functions as follows: create,modify,delete city
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FaultPartAction extends BaseAction {
    private FaultPart faultPart = new FaultPart();
    private List<FaultPart> faultPartList = new ArrayList<FaultPart>();

    /**
     * list the city by page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listFaultPart() throws Exception {
        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPartList = faultPartBusiness.getFaultPartList(null);
        if (faultPartBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultPartBusiness.getErrors()));
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
    public String createFaultPartPre() throws Exception {
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
    public String deleteFaultPart() throws Exception {

        if (null == faultPart.getId()) {
            return ILLEGAL_ERR;
        }

        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPartBusiness.deleteFaultPart(faultPart);
        if (faultPartBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultPartBusiness.getErrors()));
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
    public String modifyFaultPartPre() throws Exception {

        if (null == faultPart.getId()) {
            return ILLEGAL_ERR;
        }

        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPart = faultPartBusiness.getFaultPartById(faultPart);
        if (faultPartBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultPartBusiness.getErrors()));
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
    public String modifyFaultPart() throws Exception {

        if (null == faultPart.getId()) {
            return ILLEGAL_ERR;
        }

        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPartBusiness.modifyFaultPart(faultPart);
        if (faultPartBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultPartBusiness.getErrors()));
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
    public String createFaultPart() throws Exception {

        FaultPartBusiness faultPartBusiness = new FaultPartBusiness();
        faultPartBusiness.createFaultPart(faultPart);
        if (faultPartBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultPartBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * @return the faultPart
     */
    public FaultPart getFaultPart() {
        return faultPart;
    }

    /**
     * @param faultPart the faultPart to set
     */
    public void setFaultPart(FaultPart faultPart) {
        this.faultPart = faultPart;
    }

    /**
     * @return the faultPartList
     */
    public List<FaultPart> getFaultPartList() {
        return faultPartList;
    }

    /**
     * @param faultPartList the faultPartList to set
     */
    public void setFaultPartList(List<FaultPart> faultPartList) {
        this.faultPartList = faultPartList;
    }
    
    

}
