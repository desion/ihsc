//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.InstallationApplyBusiness;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.InstallationApply;

/**
 * PR004:产品使用情况申请管理
 * @author sunyx
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class InstallationApplyAction extends BaseAction{

    private List<InstallationApply> instApplyList = new ArrayList<InstallationApply>();
    private InstallationApply instApply = new InstallationApply();
    private Installation install = new Installation();

    /**
     * load the page of add an apply
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String addInstApplyLoad() throws Exception{
        if(install.getId() == null){
            return ILLEGAL_ERR;
        }
        
        InstallationApplyBusiness instApplyBusi = new InstallationApplyBusiness();
        install = instApplyBusi.serchInstall(install.getId(),loginUser);
        if (instApplyBusi.hasErrors()) {
            install = new Installation();
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return ERROR;
        }
        install.setFaultMachineType(machineType);
        
        DateFormat format = new DateFormat();
        install.setFobDate(format.changeDate(install.getFobDate()));
        install.setInstallDate(format.changeDate(install.getInstallDate()));
        install.setOpenDate(format.changeDate(install.getOpenDate()));
        install.setAcceptDate(format.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
        
        instApply.setApplyDate(TimeUtil.getNowDate());
        instApply.setApplyDate(format.changeDate(instApply.getApplyDate()));
        
        return SUCCESS;
    }
    
    /**
     * do the add apply
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String doAddInstApply() throws Exception{
        if(install.getId() == null){
            return ILLEGAL_ERR;
        }

        InstallationApplyBusiness instApplyBusi = new InstallationApplyBusiness();
        instApplyBusi.inputCheck(instApply);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        install = instApplyBusi.serchInstall(install.getId(), loginUser);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        DateFormat format = new DateFormat();
        if(instApply.getGuaranteeStartDate() != null){
            instApply.setGuaranteeStartDate(format.format(instApply.getGuaranteeStartDate(), "instApply.guaranteeStartDate"));
        }
        if(instApply.getGuaranteeEndDate() != null){
            instApply.setGuaranteeEndDate(format.format(instApply.getGuaranteeEndDate(), "instApply.guaranteeEndDate"));
        }
        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return ERROR;
        }

        instApplyBusi.addApplyCheck(instApply,install,loginUser);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        instApply.setApplyerId(loginUser.getId());
        instApply.setCreatorId(loginUser.getId());
        instApply.setModifierId(loginUser.getId());
        
        if(install.getFobDate() != null){
            install.setFobDate(format.format(install.getFobDate()));
        }
        if(install.getInstallDate() != null){
            install.setInstallDate(format.format(install.getInstallDate()));
        }
        if(install.getOpenDate() != null){
            install.setOpenDate(format.format(install.getOpenDate()));
        }
        if(install.getAcceptDate() != null){
            install.setAcceptDate(format.format(install.getAcceptDate()));
        }
        if(install.getGuaranteeStartDate() != null){
            install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate()));
        }
        if(install.getGuaranteeEndDate() != null){
            install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate()));
        }
        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return ERROR;
        }
        
        instApplyBusi.addAply(instApply, install);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        return SUCCESS;
    }
    
    /**
     * serch apply list
     * @author sunyx
     * @return String
     */
    public String serchInstApplyList(){
        InstallationApplyBusiness instApplyBusiness = new InstallationApplyBusiness(); 
        
        setTotalCount(instApplyBusiness.getCounts(loginUser));
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return INPUT;
        }
        
        instApplyList = instApplyBusiness.serchApplyList(currPage,pageSize,loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return INPUT;
        }

        DateFormat format = new DateFormat();
        for(int i=0;i<instApplyList.size();i++){
            InstallationApply ia = instApplyList.get(i);
            ia.setApplyDate(format.changeDate(ia.getApplyDate()));
        }
        return SUCCESS;
    }
    
    /**
     * serch a detail information of an apply 
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String serchInstApplyListDetail() throws Exception{
        if(instApply.getId() == null
                || instApply.getExclusiveKey() == null){
            return ILLEGAL_ERR;
        }

        DateFormat format = new DateFormat();
        
        InstallationApplyBusiness instApplyBusiness = new InstallationApplyBusiness();
        instApply = instApplyBusiness.serchApplyByIdExkey(instApply,loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        instApply.setApplyDate(format.changeDate(instApply.getApplyDate()));
        instApply.setFobDate(format.changeDate(instApply.getFobDate()));
        instApply.setInstallDate(format.changeDate(instApply.getInstallDate()));
        instApply.setOpenDate(format.changeDate(install.getOpenDate()));
        instApply.setAcceptDate(format.changeDate(instApply.getAcceptDate()));
        instApply.setGuaranteeStartDate(format.changeDate(instApply.getGuaranteeStartDate()));
        instApply.setGuaranteeEndDate(format.changeDate(instApply.getGuaranteeEndDate()));
        
        install = instApplyBusiness.serchInstall(instApply.getInstallId(),loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return ERROR;
        }
        install.setFaultMachineType(machineType);
        
        
        install.setFobDate(format.changeDate(install.getFobDate()));
        install.setInstallDate(format.changeDate(install.getInstallDate()));
        install.setOpenDate(format.changeDate(install.getOpenDate()));
        install.setAcceptDate(format.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
        
        instApply = instApplyBusiness.forDisplay(instApply);
        return SUCCESS;
    }

    /**
     * load the page of modify an apply
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String modifyInstApplyLoad() throws Exception{
        if(instApply.getId() == null
                || instApply.getExclusiveKey() == null){
            return ILLEGAL_ERR;
        }
        
        DateFormat format = new DateFormat();

        InstallationApplyBusiness instApplyBusiness = new InstallationApplyBusiness();
        instApply = instApplyBusiness.serchApplyForModify(instApply);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        instApply.setApplyDate(format.changeDate(instApply.getApplyDate()));
        instApply.setFobDate(format.changeDate(instApply.getFobDate()));
        instApply.setInstallDate(format.changeDate(instApply.getInstallDate()));
        instApply.setOpenDate(format.changeDate(install.getOpenDate()));
        instApply.setAcceptDate(format.changeDate(instApply.getAcceptDate()));
        instApply.setGuaranteeStartDate(format.changeDate(instApply.getGuaranteeStartDate()));
        instApply.setGuaranteeEndDate(format.changeDate(instApply.getGuaranteeEndDate()));

        install = instApplyBusiness.serchInstall(instApply.getInstallId(),loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return ERROR;
        }
        install.setFaultMachineType(machineType);
        
        install.setFobDate(format.changeDate(install.getFobDate()));
        install.setInstallDate(format.changeDate(install.getInstallDate()));
        install.setOpenDate(format.changeDate(install.getOpenDate()));
        install.setAcceptDate(format.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate())); 

        return SUCCESS;
        
    }
    
    /**
     * modify the apply
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String doModifyInstApply() throws Exception{
        if(instApply.getId() == null
                || instApply.getExclusiveKey() == null){
            return ILLEGAL_ERR;
        }
        InstallationApplyBusiness instApplyBusiness = new InstallationApplyBusiness();       
        
        instApplyBusiness.inputCheck(instApply);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }

        instApply.setInstallId(instApplyBusiness.serchInstallId(instApply));
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        
        install = instApplyBusiness.serchInstall(instApply.getInstallId(), loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return ERROR;
        }
        
        InstallationApplyBusiness instApplyBusi = new InstallationApplyBusiness();
        instApplyBusi.updateCheck(instApply,install,loginUser);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        DateFormat format = new DateFormat();
        if(instApply.getGuaranteeStartDate() != null){
            instApply.setGuaranteeStartDate(format.format(instApply.getGuaranteeStartDate(), "instApply.guaranteeStartDate"));
        }
        if(instApply.getGuaranteeEndDate() != null){
            instApply.setGuaranteeEndDate(format.format(instApply.getGuaranteeEndDate(), "instApply.guaranteeEndDate"));
        }
        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return ERROR;
        }
        
        instApplyBusi.doModifyInstApply(instApply,install,loginUser);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        return SUCCESS;
    }
    
    /**
     * do the apply
     * @author sunyx
     * @return String
     * @throws Exception
     */
    public String doApply() throws Exception{
        if(instApply.getId() == null 
                || instApply.getExclusiveKey() == null
                || install.getExclusiveKey() == null){
            return ILLEGAL_ERR;
        }

        InstallationApplyBusiness instApplyBusi = new InstallationApplyBusiness();
        instApply = instApplyBusi.doApplyCheck(instApply,install,loginUser);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }
        
        instApplyBusi.doApply(instApply);
        if (instApplyBusi.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusi.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * delete the selected apply
     * @author sunyx
     * @return
     * @throws Exception
     */
    public String deleteInstApply() throws Exception{
        if(instApply.getId() == null 
                || instApply.getExclusiveKey() == null){
            return ILLEGAL_ERR;
        }
        
        InstallationApplyBusiness instApplyBusiness = new InstallationApplyBusiness();
        instApplyBusiness.deleteCheck(instApply, loginUser);
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return SUCCESS;
        }
        
        instApplyBusiness.delete(instApply.getId(), instApply.getExclusiveKey());
        if (instApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(instApplyBusiness.getErrors()));
            return SUCCESS;
        }
        
        return SUCCESS;
    }
    
    public List<InstallationApply> getInstApplyList() {
        return instApplyList;
    }

    public void setInstApplyList(List<InstallationApply> instApplyList) {
        this.instApplyList = instApplyList;
    }

    public InstallationApply getInstApply() {
        return instApply;
    }

    public void setInstApply(InstallationApply instApply) {
        this.instApply = instApply;
    }

    public Installation getInstall() {
        return install;
    }

    public void setInstall(Installation install) {
        this.install = install;
    }
}
