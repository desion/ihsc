
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.*;
import cn.com.bhh.erp.common.ExcelHandle;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.*;
import cn.com.bhh.erp.sysConfig.SysConfigManager;

import com.opensymphony.xwork2.ActionContext;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

/**
 * PR005:installation mentenence
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class TopupOrderAction extends BaseAction {
    private List<Installation> installationList;
    private Installation install = new Installation();
    private List<Integer> affirmChkList = new ArrayList<Integer>();
    private String manufactureNo;
    private List<Company> compList = null;
    private boolean guaranteeFlg;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;

    /**
     * PR005_10:get installation list
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String serchUseStatus() throws Exception {
        DateFormat format = new DateFormat();
        installationList = new ArrayList<Installation>();
        
        install.setFobDate(format.format(install.getFobDate(), "install.fobDate"));
        install.setInstallDate(format.format(install.getInstallDate(), "install.installDate"));
        install.setOpenDate(format.format(install.getOpenDate(), "install.openDate"));
        install.setAcceptDate(format.format(install.getAcceptDate(), "install.acceptDate"));
        install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate"));
        install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate"));
        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        // cal the pageSize and totlePages
        setTotalCount(installationBusiness.getInstallationCount(install, loginUser));
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        installationList = installationBusiness.serchUseStatus(install, loginUser, currPage, pageSize);
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }

        for (int i = 0; i < installationList.size(); i++) {
            Installation temp = installationList.get(i);
            temp.setInstallDate(format.changeDate(temp.getInstallDate()));
        }

        return SUCCESS;
    }

    /**
     * PR005_13:installation search
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationSearch() throws Exception {
        DateFormat format = new DateFormat();
        format.format(install.getFobDate(), "install.fobDate");
        format.format(install.getInstallDate(), "install.installDate");
        format.format(install.getOpenDate(), "install.openDate");
        format.format(install.getAcceptDate(), "install.acceptDate");
        format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate");
        format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate");

        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }
    
    /**
     * affirm the select product installtion
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  String
     * @throws  Exception
     */
    public String installSelectAffirm() throws Exception {
        InstallationBusiness installationBusiness = new InstallationBusiness();
        Integer affirmFlg = new Integer(1);
        installationBusiness.modifyState(affirmChkList, affirmFlg, loginUser.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }
    

    /**
     * PR005_20:create page load
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String createInstallationLoad() throws Exception {
        if (install.getProductId() == null) {
            return ILLEGAL_ERR;
        }
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return INPUT;
        }
        install.setFaultMachineType(machineType);
        
        return SUCCESS;
    }

    /**
     * PR005_22
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String tempCreateLoad() throws Exception {
        if (install.getId() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        install = installationBusiness.getInstallationById(install.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return INPUT;
        }
        install.setFaultMachineType(machineType);
        

        DateFormat dateFormat = new DateFormat();
        install.setFobDate(dateFormat.changeDate(install.getFobDate()));
        install.setInstallDate(dateFormat.changeDate(install.getInstallDate()));
        install.setOpenDate(dateFormat.changeDate(install.getOpenDate()));
        install.setAcceptDate(dateFormat.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(dateFormat.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(dateFormat.changeDate(install.getGuaranteeEndDate()));
        install.setRepairCompanyId(install.getNowRepairCompanyId());
        install.setManufactureNo("");

        return SUCCESS;
    }

    /**
     * PR005_30
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String modifyInstallationLoad() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        install = installationBusiness.getInstallationByIdExclusive(install.getId(), install.getExclusiveKey());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }

        installationBusiness.modifyQualifyCheck(install, loginUser);
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return INPUT;
        }
        install.setFaultMachineType(machineType);
        

        DateFormat dateFormat = new DateFormat();
        install.setFobDate(dateFormat.changeDate(install.getFobDate()));
        install.setInstallDate(dateFormat.changeDate(install.getInstallDate()));
        install.setOpenDate(dateFormat.changeDate(install.getOpenDate()));
        install.setAcceptDate(dateFormat.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(dateFormat.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(dateFormat.changeDate(install.getGuaranteeEndDate()));

        return SUCCESS;
    }

    /**
     * PR005_70
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String agentInstallationLoad() throws Exception {
        compList = new ArrayList<Company>();
        CompanyBusiness companyBusiness = new CompanyBusiness();

        compList = companyBusiness.getAllAgent(loginUser);
        if (companyBusiness.hasErrors()) {
            setActionMessages(getMessageText(companyBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * PR005_71
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationSearchLoad() throws Exception {
        return SUCCESS;
    }

    /**
     * PR005_72
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String manufactureNoSearchLoad() throws Exception {
        return SUCCESS;
    }

    /**
     * PR005_50
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String uploadLoad() throws Exception {
        return SUCCESS;
    }

    /**
     * PR005_12
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String searchByManuNo() throws Exception {
        if (install.getManufactureNo() == null || "".equals(install.getManufactureNo())) {
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();

        Installation installOut = installationBusiness.getInstallByMamuNo(install, loginUser);

        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(installOut.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return INPUT;
        }
        
        installOut.setFaultMachineType(machineType);
        
        install = installOut;
        DateFormat format = new DateFormat();
        install.setFobDate(format.changeDate(install.getFobDate()));
        install.setInstallDate(format.changeDate(install.getInstallDate()));
        install.setOpenDate(format.changeDate(install.getOpenDate()));
        install.setAcceptDate(format.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
        return SUCCESS;
    }

    /**
     * PR005_73
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String guaranteeSearchLoad() throws Exception {
        return SUCCESS;
    }

    /**
     * PR005_74
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String guaranteeSearch() throws Exception {
        if ((install.getManufactureNo() == null) || "".equals(install.getManufactureNo())) {
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();

        Installation installOut = installationBusiness.getInstallByMamuNo(install, loginUser);
        DateFormat dateFormat = new DateFormat();
        installOut.setInstallDate(dateFormat.changeDate(installOut.getInstallDate()));
        installOut.setGuaranteeStartDate(dateFormat.changeDate(installOut.getGuaranteeStartDate()));
        installOut.setGuaranteeEndDate(dateFormat.changeDate(installOut.getGuaranteeEndDate()));
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        install = installOut;
        String nowDate = TimeUtil.getNowDate();

        if ((nowDate.compareTo(install.getGuaranteeStartDate()) >= 0)
                && (nowDate.compareTo(install.getGuaranteeEndDate()) <= 0)) {
            // in guarantee
            guaranteeFlg = true;
        } else {
            // out guarantee
            guaranteeFlg = false;
        }

        return SUCCESS;
    }

    /**
     * PR005_11:search installation detail
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String serchUseStatusDetail() throws Exception {
        if (install.getId() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();
        install = installationBusiness.serchUseStatusDetail(install.getId(), loginUser);
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
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

        return SUCCESS;
    }

    /**
     * PR005_32:installation affirm
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationAffirm() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        Integer affirmFlg = new Integer(1);
        installationBusiness.modifyState(install.getId(), install.getExclusiveKey(), 1, affirmFlg, loginUser.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        
        return SUCCESS;
    }

    /**
     * PR005_33:installation affirm cancel
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationAffirmCancel() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        Integer affirmFlg = new Integer(0);
        installationBusiness.modifyState(install.getId(), install.getExclusiveKey(), 1, affirmFlg, loginUser.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * PR005_34:installation deleted flag update
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationLogicDel() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        Integer affirmFlg = new Integer(1);
        installationBusiness.modifyState(install.getId(), install.getExclusiveKey(), 2, affirmFlg, loginUser.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * PR005_35:deleted installation recover
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationRecover() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        Integer affirmFlg = new Integer(0);
        installationBusiness.modifyState(install.getId(), install.getExclusiveKey(), 2, affirmFlg, loginUser.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * PR005_40:delete installation
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String installationDelete() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        installationBusiness.delete(install.getId(), install.getExclusiveKey());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * PR005_21:installation create
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     * 
     */
    public String createInstallation() throws Exception {
        if (install.getProductId() == null) {
            return ILLEGAL_ERR;
        }
        DateFormat format = new DateFormat();
        install.setFobDate(format.format(install.getFobDate(), "install.fobDate"));
        install.setInstallDate(format.format(install.getInstallDate(), "install.installDate"));
        install.setOpenDate(format.format(install.getOpenDate(), "install.OpenDate"));
        install.setAcceptDate(format.format(install.getAcceptDate(), "install.acceptDate"));
        install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate"));
        install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate"));

        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        install.setModifierId(loginUser.getId());
        install.setCreatorId(loginUser.getId());
        installationBusiness.createInstallation(install, loginUser);

        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        return SUCCESS;
    }

    /**
     * PR005_31
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String modifyInstallation() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        DateFormat format = new DateFormat();
        install.setFobDate(format.format(install.getFobDate(), "install.fobDate"));
        install.setInstallDate(format.format(install.getInstallDate(), "install.installDate"));
        install.setOpenDate(format.format(install.getOpenDate(), "install.openDate"));
        install.setAcceptDate(format.format(install.getAcceptDate(), "install.acceptDate"));
        install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate"));
        install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate"));

        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        
        Installation outData = installationBusiness.getInstallationById(install.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return ERROR;
        }
        
        installationBusiness.modifyQualifyCheck(outData, loginUser);
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return ERROR;
        }

        install.setModifierId(loginUser.getId());
        install.setAffirmFlag(outData.getAffirmFlag());
        if(outData.getAffirmFlag().equals(new Integer(0))){
            install.setFirstRepairCompanyId(install.getInstallCompId());
            install.setNowRepairCompanyId(install.getInstallCompId());
        }
        installationBusiness.modifyInstallation(install, loginUser);

        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return INPUT;
        }
        
        return SUCCESS;
    }

    /**
     * PR005_36
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String modifyHistory() throws Exception {
        if (install.getId() == null || install.getExclusiveKey() == null) {
            return ILLEGAL_ERR;
        }
        DateFormat format = new DateFormat();
        install.setFobDate(format.format(install.getFobDate(), "install.fobDate"));
        install.setInstallDate(format.format(install.getInstallDate(), "install.installDate"));
        install.setOpenDate(format.format(install.getOpenDate(), "install.openDate"));
        install.setAcceptDate(format.format(install.getAcceptDate(), "install.acceptDate"));
        install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate"));
        install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate"));

        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        
        Installation outData = installationBusiness.getInstallationById(install.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return ERROR;
        }

        installationBusiness.modifyQualifyCheck(outData, loginUser);
        if (installationBusiness.hasErrors()) {
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return ERROR;
        }
        
        install.setModifierId(loginUser.getId());
        install.setAffirmFlag(outData.getAffirmFlag());
        install.setStartDate(outData.getStartDate());
        if(outData.getAffirmFlag().equals(new Integer(0))){
            install.setFirstRepairCompanyId(install.getInstallCompId());
            install.setNowRepairCompanyId(install.getInstallCompId());
        }
        installationBusiness.modifyHistory(install, loginUser);

        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            install.setFobDate(format.changeDate(install.getFobDate()));
            install.setInstallDate(format.changeDate(install.getInstallDate()));
            install.setOpenDate(format.changeDate(install.getOpenDate()));
            install.setAcceptDate(format.changeDate(install.getAcceptDate()));
            install.setGuaranteeStartDate(format.changeDate(install.getGuaranteeStartDate()));
            install.setGuaranteeEndDate(format.changeDate(install.getGuaranteeEndDate()));
            return INPUT;
        }
        
        return SUCCESS;
    }

    /**
     * excel file download
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public String download() throws Exception {

        String fileName = "installation_info_" + TimeUtil.getNow();
        DownloadData downloadData = new DownloadData();

        // head info       
        String[] headInfo = { 
                "install.customerCompany",          //用户公司  0
                "install.subCompanyName",           //分店名 1
                "install.customerProvince",         //用户公司所在省份 2
                "install.customerCity",             //用户公司所在城市 3
                "install.branchCompanyName",        //支店名4
                "install.installPlace",             //安装地点5
                "install.instPlaceTypeId",          //安装地点类型6
                "install.contact",                  //银行联系人7
                "install.officePhone",              //座机8
                "install.mobilePhone",              //手机9
                "install.fax",                      //传真10
                "install.email",                    //邮箱11
                "install.productCategory",          //产品类型12
                "install.pruductModel",             //产品型号13
                "install.manufactureNo",            //制造号14
                "install.indentureNo",              //合同编号15
                "install.fobDate",                  //FOB 16
                "install.installDate",              //安装日期 17
                "install.openDate",                 //开通日18
                "install.acceptDate",               //验收日期19
                "install.guaranteeStartDate",       //保修开始日20
                "install.guaranteeEndDate",         //保修结束日21
                "install.guaranteePeriod",          //保修期（月） 22
                "install.useStatusId",              //使用状态 23
                "install.purpose",                  //用途 24
                "install.brmEpVer",                 //BRM_EP  25
                "install.bvEpVer",                  //BV_EP  26
                "install.keyNo",                    //钥匙号  27
                "install.OS",                       //OS  28
                "install.jpr",                      //JPR 29
                "install.platform",                 //平台  30 
                "install.platformRev",              //平台REV  31
                "install.spr",                      //SPR 32
                "install.mcu",                      //MCU 33
                "install.deskey",                   //DESKEY 34 
                "install.hcm",                      //HCM 35
                "install.bv",                       //BV 36
                "install.note",                     //备注   37
                "install.saleContractCompId",       //销售商 38 
                "install.installCompanyName",       //安装公司（IW维修公司）  39
                "install.nowRepairCompanyNameLabel",//维修公司（现在维修公司） 40 
                "install.installer",                //安装人  41
                "install.installerId",              //安装人编号  42
                "install.osPermit"                 //os许可项 43
                };

        
        for (int i = 0; i < headInfo.length; i++) {
            headInfo[i] = getText(headInfo[i]);
        }
        downloadData.setHead(headInfo);

        int countSize = SysConfigManager.getCountSize();

        installationList = new ArrayList<Installation>();
        InstallationBusiness installationBusiness = new InstallationBusiness();
        DateFormat format = new DateFormat();
        if (manufactureNo == null || "".equals(manufactureNo)) {
            // from search page's export 
            install.setFobDate(format.format(install.getFobDate(), "install.fobDate"));
            install.setInstallDate(format.format(install.getInstallDate(), "install.installDate"));
            install.setOpenDate(format.format(install.getOpenDate(), "install.openDate"));
            install.setAcceptDate(format.format(install.getAcceptDate(), "install.acceptDate"));
            install.setGuaranteeStartDate(format.format(install.getGuaranteeStartDate(), "install.guaranteeStartDate"));
            install.setGuaranteeEndDate(format.format(install.getGuaranteeEndDate(), "install.guaranteeEndDate"));
            if (format.hasErrors()) {
                setActionMessages(getMessageText(format.getErrors()));
                return INPUT;
            }
            installationList = installationBusiness.getDownLoadData(install, loginUser, countSize);
            if (installationBusiness.hasErrors()) {
                setActionMessages(getMessageText(installationBusiness.getErrors()));
                return INPUT;
            }
        } else {
            // from list page's export 
            
            if(affirmChkList.size()==0){
                return ILLEGAL_ERR;
            }      
            installationList = installationBusiness.getDownLoadCheckedData(install,affirmChkList, loginUser);

            if (installationBusiness.hasErrors()) {
                setActionMessages(getMessageText(installationBusiness.getErrors()));
                return ERROR;
            }
        }
        List dataList = new ArrayList();
        DateFormat dateFormat = new DateFormat();
        for (int i = 0; i < installationList.size(); i++) {
            List row = new ArrayList();
            Installation installation = installationList.get(i);
            row.add(installation.getCustomerMainName());                            // 0 用户公司 
            row.add(installation.getCustomerSubName());                             // 1 分店名  
            row.add(installation.getCustomerProvince());                            // 2 用户公司所在省份 
            row.add(installation.getCustomerCity());                                // 3 用户公司所在城市 
            row.add(installation.getBranchCompanyName());                           // 4 支店名  
            row.add(installation.getInstallPlace());                                // 5 安装地点 
            row.add(installation.getInstPlaceTypeName());                           // 6 安装地点类型 
            row.add(installation.getContact());                                     // 7 银行联系人 
            row.add(installation.getOfficePhone());                                 // 8 座机 
            row.add(installation.getMobilePhone());                                 // 9 手机 
            row.add(installation.getFax());                                         // 10 传真 
            row.add(installation.getEmail());                                       // 11 邮箱 
            row.add(installation.getProductCategoryName());                         // 12 产品类型 
            row.add(installation.getModel());                                       // 13 产品型号 
            row.add(installation.getManufactureNo());                               // 14 制造号 
            row.add(installation.getIndentureNo());                                 // 15 合同编号 
            row.add(dateFormat.changeDate(installation.getFobDate()));              // 16 FOB
            row.add(dateFormat.changeDate(installation.getInstallDate()));          // 17 安装日期 
            row.add(dateFormat.changeDate(installation.getOpenDate()));             // 18 开通日
            row.add(dateFormat.changeDate(installation.getAcceptDate()));           // 19 验收日期
            row.add(dateFormat.changeDate(installation.getGuaranteeStartDate()));   // 20 保修开始日
            row.add(dateFormat.changeDate(installation.getGuaranteeEndDate()));     // 21 保修结束日
            row.add(installation.getGuaranteePeriod());                             // 22 保修期（月）
            row.add(installation.getUseStatus());                                   // 23 使用状态
            row.add(installation.getPurposeName());                                 // 24 用途
            row.add(installation.getBrmEpVer());                                    // 25 BRM_EP
            row.add(installation.getBvEpVer());                                     // 26 BV_EP
            row.add(installation.getKeyNo());                                       // 27 钥匙号
            row.add(installation.getOsName());                                      // 28 OS
            row.add(installation.getJpr());                                         // 29 JPR
            row.add(installation.getPlatformName());                                // 30 平台
            row.add(installation.getPlatformRev());                                 // 31 平台REV
            row.add(installation.getSpr());                                         // 32 SPR
            row.add(installation.getMcu());                                         // 33 MCU
            row.add(installation.getDeskey());                                      // 34 DESKEY
            row.add(installation.getHcm());                                         // 35 HCM
            row.add(installation.getBv());                                          // 36 BV
            row.add(installation.getNote());                                        // 37 备注
//            row.add(installation.getSaleContractCompName());                        // 38 销售商
//            row.add(installation.getInstallCompName());                             // 39 安装公司
//            row.add(installation.getNowRepairMainCompanyName());                    // 40 维修公司（现在维修公司）
            row.add(installation.getSaleContractCompShortName());                   // 38 销售商
            row.add(installation.getInstallCompShortName());                        // 39 安装公司
            row.add(installation.getNowRepairCompanyName());                        // 40 维修公司（现在维修公司）
            row.add(installation.getInstaller());                                   // 41 安装人
            row.add(installation.getInstallerId());                                 // 42 安装人编号
            row.add(installation.getOsPermitName());                                // 43 os许可项
            dataList.add(row);
        }
        
        downloadData.setDataList(dataList);
        ExcelHandle excelHandle = new ExcelHandle();

        ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

        OutputStream os = response.getOutputStream();
        excelHandle.export(os, downloadData);
        if (excelHandle.hasErrors()) {
            ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(true);
            setActionMessages(getMessageText(excelHandle.getErrors()));
            if (manufactureNo == null || "".equals(manufactureNo)) {
                return INPUT;
            } else {
                return ERROR;
            }
        }
        return SUCCESS;
    }

    /**
     * excel file upload
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public String upload() throws Exception {
        ExcelHandle excelHandle = new ExcelHandle();
        ArrayList excelList = excelHandle.getExcelList(uploadFileName, upload, 44);//39 的数字应该改
        if (excelHandle.hasErrors()) {
            setActionMessages(getMessageText(excelHandle.getErrors()));
            return INPUT;
        }

        InstallationBusiness installationBusiness = new InstallationBusiness();
        installationBusiness.upload(excelList, loginUser);
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * PR005_80 print installation info
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String print() throws Exception {
        if (install.getId() == null) {
            return ILLEGAL_ERR;
        }
        InstallationBusiness installationBusiness = new InstallationBusiness();

        install = installationBusiness.getInstallationById(install.getId());
        if (installationBusiness.hasErrors()) {
            setActionMessages(getMessageText(installationBusiness.getErrors()));
            return INPUT;
        }
        
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String machineType = productCategoryBusiness.searchTopProCateName(install.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return INPUT;
        }
        
        install.setFaultMachineType(machineType);
        

        DateFormat dateFormat = new DateFormat();
        install.setFobDate(dateFormat.changeDate(install.getFobDate()));
        install.setInstallDate(dateFormat.changeDate(install.getInstallDate()));
        install.setOpenDate(dateFormat.changeDate(install.getOpenDate()));
        install.setAcceptDate(dateFormat.changeDate(install.getAcceptDate()));
        install.setGuaranteeStartDate(dateFormat.changeDate(install.getGuaranteeStartDate()));
        install.setGuaranteeEndDate(dateFormat.changeDate(install.getGuaranteeEndDate()));

        return SUCCESS;
    }

    public List<Installation> getInstallationList() {
        return installationList;
    }

    public void setInstallationList(List<Installation> installationList) {
        this.installationList = installationList;
    }

    public Installation getInstall() {
        return install;
    }

    public void setInstall(Installation install) {
        this.install = install;
    }

    public List<Company> getCompList() {
        return compList;
    }

    public void setCompList(List<Company> compList) {
        this.compList = compList;
    }

    public boolean isGuaranteeFlg() {
        return guaranteeFlg;
    }

    public void setGuaranteeFlg(boolean guaranteeFlg) {
        this.guaranteeFlg = guaranteeFlg;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }
    
    public String getManufactureNo() {
        return manufactureNo;
    }

    public void setManufactureNo(String manufactureNo) {
        this.manufactureNo = manufactureNo;
    }

    /**
     * @return the affirmChkList
     */
    public List<Integer> getAffirmChkList() {
        return affirmChkList;
    }

    /**
     * @param affirmChkList the affirmChkList to set
     */
    public void setAffirmChkList(List<Integer> affirmChkList) {
        this.affirmChkList = affirmChkList;
    }
  
}
