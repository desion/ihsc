//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.business.FaultBusiness;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.ExcelHandle;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.DownloadData;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.FaultSpares;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.sysConfig.SysConfigManager;

import com.opensymphony.xwork2.ActionContext;


/**
 *
 * FA001:FAULT MENTENENCE
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class FaultAction extends BaseAction {
    private List<Fault> faultList = new ArrayList<Fault>();
    private Fault fault = new Fault();
    private List<Company> compList = new ArrayList<Company>();
    private Installation install = new Installation();
    private String faultActionFrom;
    private File upload;
    private String uploadFileName;
    private String uploadContentType;
    private static final Integer ZERO=new Integer("0");
    private static final Integer ONE=new Integer("1");
    private static final Integer TWO=new Integer("2");
    private static final Integer THREE=new Integer("3");
    private static final Integer FOUR=new Integer("4");
    private static final Integer FIVE=new Integer("5");
    private static final Integer SIX=new Integer("6");
    private static final Integer SEVEN=new Integer("7");
    private String managementId;
    private List<FaultSpares> faultSparesList = new ArrayList<FaultSpares>(15);
    private List<Integer> finishChkList = new ArrayList<Integer>();
    private List<String> errorCodes = new ArrayList<String>();
    private List<Fault> faultHistoryList = new ArrayList<Fault>();
    private static final String GABIO_SUCCESS = "gabio_success";
    private static final String STANDARD_SUCCESS = "standard_success";
    private String valErrPage;
    /**
     * @return the valErrPage
     */
    public String getValErrPage() {
        return valErrPage;
    }

    /**
     * @param valErrPage the valErrPage to set
     */
    public void setValErrPage(String valErrPage) {
        this.valErrPage = valErrPage;
    }

    /**
     * FA001_10:fault info list
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    public String searchFaultList() throws Exception {
        DateFormat format = new DateFormat();
        fault.setOccurDate(format.format(fault.getOccurDate(), "fault.occurDate"));
        fault.setReportDate(format.format(fault.getReportDate(), "fault.reportDate"));
        fault.setFinishDate(format.format(fault.getFinishDate(), "fault.finishDate"));
        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return INPUT;
        }

        faultList = new ArrayList<Fault>();
        FaultBusiness faultBusiness = new FaultBusiness();
        // cal the pageSize and totlePages
        setTotalCount(faultBusiness.getFaultCount(fault, loginUser));
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return INPUT;
        }

        faultList = faultBusiness.getFaultList(fault, loginUser, currPage, pageSize);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return INPUT;
        }

        for(int i=0;i<faultList.size();i++){
            Fault ft = faultList.get(i);
            ft.setOccurDate(format.changeDate(ft.getOccurDate()));
        }
        return SUCCESS;
    }

    /**
     * FA001_11:show fault detail page.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showFaultDetail() throws Exception {
        if (fault.getId() == null || Integer.valueOf(0).compareTo(fault.getId()) >= 0) {
            return ILLEGAL_ERR;
        }
        FaultBusiness faultBusiness = new FaultBusiness();
        Fault tmpFault = faultBusiness.searchFaultDetailById(fault.getId(), null);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        
        faultHistoryList = faultBusiness.getFaultListByInstallationId(tmpFault.getInstallationId(), loginUser);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        
        List<FaultSpares> tmpFaultSparesList = faultBusiness.searchFaultSparesByFaultId(tmpFault.getId());
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        fault = tmpFault;
        faultSparesList = tmpFaultSparesList;

        DateFormat dateFormat = new DateFormat();
        fault.setInstallDate(dateFormat.changeDate(fault.getInstallDate()));
        fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
        fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
        fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
        fault.setApplyDate(dateFormat.changeDate(fault.getApplyDate()));
        fault.setDeliverDate(dateFormat.changeDate(fault.getDeliverDate()));
        fault.setReplaceDate(dateFormat.changeDate(fault.getReplaceDate()));
        fault.setIhscReceiveDate(dateFormat.changeDate(fault.getIhscReceiveDate()));
        fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
        fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
        fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
        for (int i = 0; i < faultSparesList.size(); i++) {
            faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
            faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
            faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
            faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
        }
        
        String faultMachineType=fault.getFaultMachineType();
        if("ATM".equals(faultMachineType)){
           return SUCCESS;
        }else if("G-ABIO".equals(faultMachineType)){
            return GABIO_SUCCESS;
        }else{
            return STANDARD_SUCCESS;
        }
        
    }

    /**
     * FA001_12:show fault detail page by management ID.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showFaultDetailByMngId() throws Exception {
        if (fault.getManagementId() == null || fault.getManagementId().trim().length() == 0) {
            return INPUT;
        }
        FaultBusiness faultBusiness = new FaultBusiness();
        Fault tmpFault = faultBusiness.searchFaultDetailById(null, fault.getManagementId());
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        faultHistoryList = faultBusiness.getFaultListByInstallationId(tmpFault.getInstallationId(), loginUser);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        
        List<FaultSpares> tmpFaultSparesList = faultBusiness.searchFaultSparesByFaultId(tmpFault.getId());
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        fault = tmpFault;
        faultSparesList = tmpFaultSparesList;

        DateFormat dateFormat = new DateFormat();
        fault.setInstallDate(dateFormat.changeDate(fault.getInstallDate()));
        fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
        fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
        fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
        fault.setApplyDate(dateFormat.changeDate(fault.getApplyDate()));
        fault.setDeliverDate(dateFormat.changeDate(fault.getDeliverDate()));
        fault.setReplaceDate(dateFormat.changeDate(fault.getReplaceDate()));
        fault.setIhscReceiveDate(dateFormat.changeDate(fault.getIhscReceiveDate()));
        fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
        fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
        fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
        for (int i = 0; i < faultSparesList.size(); i++) {
            faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
            faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
            faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
            faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
        }
        
        String faultMachineType=fault.getFaultMachineType();
        if("ATM".equals(faultMachineType)){
           return SUCCESS;
        }else if("G-ABIO".equals(faultMachineType)){
            return GABIO_SUCCESS;
        }else{
            return STANDARD_SUCCESS;
        }
    }

    /**
    *
    * FA001_13:fault search
    * @author liugd
    * @version 1.0
    * @since 1.0
    * @exception Exception
    */
    public String faultSearch() throws Exception {
        DateFormat format = new DateFormat();
        format.format(fault.getOccurDate(), "fault.occurDate");
        format.format(fault.getReportDate(), "fault.reportDate");
        format.format(fault.getFinishDate(), "fault.finishDate");

        if (format.hasErrors()) {
            setActionMessages(getMessageText(format.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * FA001_20:show fault create page.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showFaultCreate() throws Exception {
        if (install.getId() == null || Integer.valueOf(0).compareTo(install.getId()) >= 0) {
            return ILLEGAL_ERR;
        }
        FaultBusiness faultBusiness = new FaultBusiness();
        fault = faultBusiness.searchFaultHeaderFr(install);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        ProductCategoryBusiness productCategoryBusiness= new ProductCategoryBusiness();
        
        String topProCategoryName = productCategoryBusiness.searchTopProCateName(fault.getModel());
        if (productCategoryBusiness.hasErrors()) {
            setActionMessages(getMessageText(productCategoryBusiness.getErrors()));
            return ERROR;
        }
        
        for (int i = 0; i < 1; i++) {
            FaultSpares tmpFaultSpares = new FaultSpares();
            faultSparesList.add(tmpFaultSpares);
        }
        
        fault.setFaultMachineType(topProCategoryName);
        if("ATM".equals(topProCategoryName.trim())){
            this.valErrPage="FA001_21_1";
           return SUCCESS;
        }else if("G-ABIO".equals(topProCategoryName.trim())){
            this.valErrPage="FA001_21_2";
            return GABIO_SUCCESS;
        }else{
            this.valErrPage="FA001_21_3";
            return STANDARD_SUCCESS;
        }
    }

    /**
     * FA001_21:create fault information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String createFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();
        String strNowDateTime = TimeUtil.getNowNoSecond();
        fault.setInstallationId(install.getId());
        fault.setReportDate(strNowDateTime.substring(0, 8));
        fault.setReportTime(strNowDateTime.substring(8));
//        String faultMachineType = fault.getFaultMachineType();
        
        DateFormat dateFormat = new DateFormat();

        faultBusiness.insertFault(loginUser, fault, faultSparesList);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
            fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
            fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
            fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
            fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
            fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
            for (int i = 0; i < faultSparesList.size(); i++) {
                faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
                faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
                faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
                faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
            }
            return ERROR;

        }

         return SUCCESS;
    }

 
    
    /**
     * FA001_30:show fault modify page.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showFaultModify() throws Exception {
        if (fault.getId() == null || Integer.valueOf(0).compareTo(fault.getId()) >= 0
                || fault.getExclusiveKey() == null || Integer.valueOf(0).compareTo(fault.getExclusiveKey()) > 0) {
            return ILLEGAL_ERR;
        }
        FaultBusiness faultBusiness = new FaultBusiness();
        fault = faultBusiness.searchFaultByIdFm(fault);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        faultSparesList = faultBusiness.searchFaultSparesByFaultId(fault.getId());
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        DateFormat dateFormat = new DateFormat();
        fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
        fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
        fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
        fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
        fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
        fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
        if (faultSparesList.size() > 0) {
            for (int i = 0; i < faultSparesList.size(); i++) {
                faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
                faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
                faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
                faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
            }
        } else {
            faultSparesList.add(new FaultSpares());
        }
        
        String faultMachineType = fault.getFaultMachineType();
        if("ATM".equals(faultMachineType)){
            this.valErrPage="FA001_31_1";
            return SUCCESS;
         }else if("G-ABIO".equals(faultMachineType)){
             this.valErrPage="FA001_31_2";
             return GABIO_SUCCESS;
         }else{
             this.valErrPage="FA001_31_3";
             return STANDARD_SUCCESS;
         }
    }

    /**
     * FA001_31:modify fault information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String modifyFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        DateFormat dateFormat = new DateFormat();

        faultBusiness.modifyFault(loginUser, fault, faultSparesList);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
            fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
            fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
            fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
            fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
            fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
            for (int i = 0; i < faultSparesList.size(); i++) {
                faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
                faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
                faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
                faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
            }
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * FA001_32:business delete fault information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.modifyFaultFd(loginUser, fault, ONE);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * FA001_32:recover fault information.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String recoverFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.modifyFaultFd(loginUser, fault, ZERO);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * FA001_36:finish select fault handle.
     * @author sunyx
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String finishSelectFault() throws Exception{
        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.modifySelectFaultFf(finishChkList,loginUser);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return INPUT;
        }
        
        return SUCCESS;
    }

    /**
     * FA001_34:finish fault handle.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String finishFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.modifyFaultFf(loginUser, fault, ONE);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            //2009/08/19 add
            List<String> errors = faultBusiness.getErrors();
            for (int i = 0; i < errors.size(); ++i) {
                String msg = errors.get(i);
                String[] params = msg.split(",");
                int paramSize = params.length;

                if (paramSize > 1) {
                    String errorSource = params[1];
                    errorSource = errorSource.replace(".", "");
                    this.errorCodes.add(errorSource);
                }
            }
            
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * FA001_35:cancel finish fault handle.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String cancelFinishFault() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.modifyFaultFf(loginUser, fault, ZERO);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * FA001_40:delete fault information forever.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String deleteFaultF() throws Exception {

        FaultBusiness faultBusiness = new FaultBusiness();

        faultBusiness.deleteFault(fault);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * FA001_50
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception
     */
    public String uploadLoad() throws Exception {
        return SUCCESS;
    }
   
    /**
     * FA001_70:agent company reference
     * @author liugd 
     * @version 1.0
     * @since 1.0
     * @exception
     */
    public String agentFaultLoad() throws Exception {
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
     * FA001_71:search page load
     * @version 1.0
     * @since 1.0
     * @author liugd
     * @exception
     */
    public String faultSearchLoad() throws Exception {
        return SUCCESS;
    }
    
    /**
     * FA001_72:management id search page load
     * @version 1.0
     * @since 1.0
     * @author liugd
     * @exception
     */
    public String managementIdSearchLoad() throws Exception {
        return SUCCESS;
    }

    /**
     * FA001_80:show fault detail page for print.
     * @author luyan
     * @since 1.0
     * @return action forward
     * @throws Exception
     */
    public String showFaultDetailFp() throws Exception {
        if (fault.getId() == null || Integer.valueOf(0).compareTo(fault.getId()) >= 0) {
            return ILLEGAL_ERR;
        }
        FaultBusiness faultBusiness = new FaultBusiness();
        Fault tmpFault = faultBusiness.searchFaultDetailById(fault.getId(), null);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        List<FaultSpares> tmpFaultSparesList = faultBusiness.searchFaultSparesByFaultId(tmpFault.getId());
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return ERROR;
        }
        fault = tmpFault;
        faultSparesList = tmpFaultSparesList;

        DateFormat dateFormat = new DateFormat();
        fault.setInstallDate(dateFormat.changeDate(fault.getInstallDate()));
        fault.setOccurDate(dateFormat.changeDate(fault.getOccurDate()));
        fault.setReportDate(dateFormat.changeDate(fault.getReportDate()));
        fault.setFinishDate(dateFormat.changeDate(fault.getFinishDate()));
        fault.setApplyDate(dateFormat.changeDate(fault.getApplyDate()));
        fault.setDeliverDate(dateFormat.changeDate(fault.getDeliverDate()));
        fault.setReplaceDate(dateFormat.changeDate(fault.getReplaceDate()));
        fault.setIhscReceiveDate(dateFormat.changeDate(fault.getIhscReceiveDate()));
        fault.setOccurTime(dateFormat.changeTime(fault.getOccurTime()));
        fault.setReportTime(dateFormat.changeTime(fault.getReportTime()));
        fault.setFinishTime(dateFormat.changeTime(fault.getFinishTime()));
        for (int i = 0; i < faultSparesList.size(); i++) {
            faultSparesList.get(i).setApplyDate(dateFormat.changeDate(faultSparesList.get(i).getApplyDate()));
            faultSparesList.get(i).setDeliverDate(dateFormat.changeDate(faultSparesList.get(i).getDeliverDate()));
            faultSparesList.get(i).setReplaceDate(dateFormat.changeDate(faultSparesList.get(i).getReplaceDate()));
            faultSparesList.get(i).setReceiveDate(dateFormat.changeDate(faultSparesList.get(i).getReceiveDate()));
        }
        
        String faultMachineType=fault.getFaultMachineType();
        if("ATM".equals(faultMachineType)){
           return SUCCESS;
        }else if("G-ABIO".equals(faultMachineType)){
            return GABIO_SUCCESS;
        }else{
            return STANDARD_SUCCESS;
        }
    }

    /**
     * excel file download
     * @author liugd
     * @since 1.0
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String download() throws Exception {
        
        String fileName = "fault_info_" + TimeUtil.getNow();
        
        DownloadData downloadData = new DownloadData();

        // head info
        String[] headInfo = {   "view.fault.label.managementId",
                                "fault.manufactureNo",
                                "fault.model",
                                "fault.productCategoryName",
                                "fault.customerName",
                                "fault.faultTypeName",
                                "fault.repairCompanyName",
                                "fault.occurDate",
                                "fault.occurTime",
                                "fault.operatorName",
                                "fault.supporterName",
                                "fault.faultPartName",
                                "fault.faultPartTypeName",
                                "fault.isState",
                                "fault.applicationVersion",
                                "fault.applicationVersionDetail1",
                                "fault.applicationVersionDetail2",
                                "fault.supportTypeName",
                                "fault.finishDate",
                                "fault.finishTime",
                                "fault.occurCondiction1",
                                "fault.occurCondiction2",
                                "fault.errorCode",
                                "fault.rx278",
                                "fault.counter",
                                "fault.appearance",
                                "fault.reason",
                                "fault.strategy",
                                "fault.result",
                                "fault.operation1",
                                "fault.operation2",
                                "fault.operation3",
                                "fault.operation4",
                                "fault.operation5",
                                "fault.operation6",
                                "fault.operation7",
                                "fault.operation8",
                                "fault.cashLeft",
                                "fault.exceptionDisplay",
                                "fault.display",
                                "fault.backLight",
                                "fault.displayContent",
                                "fault.inputable",
                                "fault.knockSound",
                                "fault.reset",
                                "fault.cutPower",
                                "fault.rebootNormally",
                                "fault.motionCounter",
                                "fault.errorNo",
                                "fault.noRepon",
                                "fault.otherDisplay",
                                "fault.traceInfomation",
                                "fault.faultRecord",
                                "fault.statistics",
                                "fault.tradeLog",
                                "fault.applicationVersion1",
                                "fault.cras",
                                "fault.dras",
                                "fault.mcuLog",
                                "fault.systemEvent",
                                "fault.dbillbox",
                                "fault.dcollect",
                                "fault.cfep",
                                "fault.cerr",
                                "fault.others",
                                "fault.receiveDate",
                                "fault.returnDate",
                                "fault.followerName",
                                "fault.strategyDetail"
                            };

        for (int i = 0; i < headInfo.length; i++) {
            headInfo[i] = getText(headInfo[i]);
        }
        downloadData.setHead(headInfo);

        int countSize = SysConfigManager.getCountSize();

        faultList = new ArrayList<Fault>();
        FaultBusiness faultBusiness = new FaultBusiness();
        if (managementId == null || "".equals(managementId)) {
            DateFormat format = new DateFormat();
            fault.setOccurDate(format.format(fault.getOccurDate(), "fault.occurDate"));
            fault.setReportDate(format.format(fault.getReportDate(), "fault.reportDate"));
            fault.setFinishDate(format.format(fault.getFinishDate(), "fault.finishDate"));
            if (format.hasErrors()) {
                setActionMessages(getMessageText(format.getErrors()));
                return INPUT;
            }
            faultList = faultBusiness.getDownLoadData(fault, loginUser, countSize);
            if (faultBusiness.hasErrors()) {
                setActionMessages(getMessageText(faultBusiness.getErrors()));
                return INPUT;
            }
        } else {
            // from list page's export 
            if(finishChkList.size()==0){
                return ILLEGAL_ERR;
            }
            
            faultList = faultBusiness.getSelectedDownLoadData(fault,finishChkList, loginUser);
            if (faultBusiness.hasErrors()) {
                setActionMessages(getMessageText(faultBusiness.getErrors()));
                return ERROR;
            }
        }
        
        List dataList = new ArrayList();
        DateFormat dateFormat = new DateFormat();
        
            String radioHas = getText("view.fault.radio.label.has");
            String radioHasNot = getText("view.fault.radio.label.hasnot");
            List<String> hasOrNotList = new ArrayList<String>();
            hasOrNotList.add(radioHas);
            hasOrNotList.add(radioHasNot);
            
            String hitachiAP = getText("view.fault.label.hitachiAP");
            String unificationAP = getText("view.fault.label.unificationAP");
            List<String> apList = new ArrayList<String>();
            apList.add(hitachiAP);
            apList.add(unificationAP);
            
            String oc1DeviceStarting = getText("view.fault.label.oc1.deviceStarting");
            String oc1Trading = getText("view.fault.label.oc1.trading");
            String oc1StaffHandling = getText("view.fault.label.oc1.staffHandling");
            List<String> oc1List = new ArrayList<String>();
            oc1List.add(oc1DeviceStarting);
            oc1List.add(oc1Trading);
            oc1List.add(oc1StaffHandling);
            
            String oc2Pay = getText("view.fault.label.oc2.pay");
            String oc2Save = getText("view.fault.label.oc2.save");
            String oc2BalanceConfirm = getText("view.fault.label.oc2.balanceConfirm");
            String oc2Loading = getText("view.fault.label.oc2.loading");
            String oc2Recovery = getText("view.fault.label.oc2.recovery");
            String oc2Testing = getText("view.fault.label.oc2.testing");
            String oc2Waiting = getText("view.fault.label.oc2.waiting");
            String oc2Other = getText("view.fault.label.oc2.other");
            List<String> oc2List = new ArrayList<String>();
            oc2List.add(oc2Pay);
            oc2List.add(oc2Save);
            oc2List.add(oc2BalanceConfirm);
            oc2List.add(oc2Loading);
            oc2List.add(oc2Recovery);
            oc2List.add(oc2Testing);
            oc2List.add(oc2Waiting);
            oc2List.add(oc2Other);
            
            String msg1Can = getText("view.fault.label.msg1.can");
            String msg1Cannot = getText("view.fault.label.msg1.cannot");
            List<String> msg1List = new ArrayList<String>();
            msg1List.add(msg1Can);
            msg1List.add(msg1Cannot);
            
            String msg3Can = getText("view.fault.label.msg3.can");
            String msg3Cannot = getText("view.fault.label.msg3.cannot");
            List<String> msg3List = new ArrayList<String>();
            msg3List.add(msg3Can);
            msg3List.add(msg3Cannot);
            
            String msg4Normal = getText("view.fault.label.msg4.normal");
            String msg4Abnormal = getText("view.fault.label.msg4.abnormal");
            List<String> msg4List = new ArrayList<String>();
            msg4List.add(msg4Normal);
            msg4List.add(msg4Abnormal);
            
        for (int i = 0; i < faultList.size(); i++) {
            List row = new ArrayList();
            Fault faultOut = faultList.get(i);
            
            row.add(faultOut.getManagementId());
            row.add(faultOut.getManufactureNo());
            row.add(faultOut.getModel());
            row.add(faultOut.getProductCategoryName());
            row.add(faultOut.getCustomerName());
            row.add(faultOut.getFaultTypeName());
            row.add(faultOut.getRepairCompanyName());
            row.add(dateFormat.changeDate(faultOut.getOccurDate()));
            row.add(dateFormat.changeTime(faultOut.getOccurTime()));
            row.add(faultOut.getOperatorName());
            row.add(faultOut.getSupporterName());
            row.add(faultOut.getFaultPartName());
            row.add(faultOut.getFaultPartTypeName());
            row.add(faultOut.getIsState());
            row.add(appVerChange(faultOut.getApplicationVersion(),apList));
            row.add(faultOut.getApplicationVersionDetail1());
            row.add(faultOut.getApplicationVersionDetail2());
            row.add(faultOut.getSupportTypeName());
            row.add(dateFormat.changeDate(faultOut.getFinishDate()));
            row.add(dateFormat.changeTime(faultOut.getFinishTime()));
            row.add(occurCon1Change(faultOut.getOccurCondiction1(),oc1List));
            row.add(occurCon2Change(faultOut.getOccurCondiction2(),oc2List));
            row.add(faultOut.getErrorCode());
            row.add(faultOut.getRx278());
            row.add(faultOut.getCounter());
            row.add(faultOut.getAppearance());
            row.add(faultOut.getReason());
            row.add(faultOut.getStrategy());
            row.add(faultOut.getResult());
            row.add(faultOut.getOperation1());
            row.add(faultOut.getOperation2());
            row.add(faultOut.getOperation3());
            row.add(faultOut.getOperation4());
            row.add(faultOut.getOperation5());
            row.add(faultOut.getOperation6());
            row.add(faultOut.getOperation7());
            row.add(faultOut.getOperation8());
            row.add(hasNotHasDataChange(faultOut.getCashLeft(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getExceptionDisplay(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getDisplay(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getBackLight(),hasOrNotList));
            row.add(faultOut.getDisplayContent());
            row.add(hasNotHasDataChange(faultOut.getInputable(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getKnockSound(),hasOrNotList));
            row.add(resetChange(faultOut.getReset(),msg1List));
            row.add(cutPowerChange(faultOut.getCutPower(),msg3List));
            row.add(rebootNormallyChange(faultOut.getRebootNormally(),msg4List));
            row.add(faultOut.getMotionCounter());
            row.add(faultOut.getErrorNo());
            row.add(faultOut.getNoRepon());
            row.add(faultOut.getOtherDisplay());
            row.add(hasNotHasDataChange(faultOut.getTraceInfomation(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getFaultRecord(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getStatistics(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getTradeLog(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getApplicationVersion1(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getCras(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getDras(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getMcuLog(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getSystemEvent(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getDbillbox(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getDcollect(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getCfep(),hasOrNotList));
            row.add(hasNotHasDataChange(faultOut.getCerr(),hasOrNotList));
            row.add(faultOut.getOthers());
            row.add(dateFormat.changeDate(faultOut.getReceiveDate()));
            row.add(dateFormat.changeDate(faultOut.getReturnDate()));
            row.add(faultOut.getFollowerName());
            row.add(faultOut.getStrategyDetail());
            
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
            return INPUT;
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
        ArrayList excelList = excelHandle.getExcelList(uploadFileName, upload, 69);
        if (excelHandle.hasErrors()) {
            setActionMessages(getMessageText(excelHandle.getErrors()));
            return INPUT;
        }

        FaultBusiness faultBusiness = new FaultBusiness();
        String[] dicInfo = {
                            "view.fault.radio.label.has",
                            "view.fault.radio.label.hasnot",
                            "view.fault.label.hitachiAP",
                            "view.fault.label.unificationAP",
                            "view.fault.label.oc1.deviceStarting",
                            "view.fault.label.oc1.trading",
                            "view.fault.label.oc1.staffHandling",
                            "view.fault.label.oc2.pay",
                            "view.fault.label.oc2.save",
                            "view.fault.label.oc2.balanceConfirm",
                            "view.fault.label.oc2.loading",
                            "view.fault.label.oc2.recovery",
                            "view.fault.label.oc2.testing",
                            "view.fault.label.oc2.waiting",
                            "view.fault.label.oc2.other",
                            "view.fault.label.msg1.can",
                            "view.fault.label.msg1.cannot",
                            "view.fault.label.msg3.can",
                            "view.fault.label.msg3.cannot",
                            "view.fault.label.msg4.normal",
                            "view.fault.label.msg4.abnormal"
                            };
        
        for (int i = 0; i < dicInfo.length; i++) {
            dicInfo[i] = getText(dicInfo[i]).trim();
        }
        faultBusiness.upload(excelList, loginUser, dicInfo);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（1:有、0:无、其他：空）
     */
    private String hasNotHasDataChange(Integer inputData,List<String> hasOrNot){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ONE) == 0) {
                outData = hasOrNot.get(0);
            } else if (inputData.compareTo(ZERO) == 0) {
                outData = hasOrNot.get(1);;
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（0:日立AP、1:统一AP、其他：空）
     */
    private String appVerChange(Integer inputData,List<String> aP){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ZERO) == 0) {
                outData = aP.get(0);
            } else if (inputData.compareTo(ONE) == 0) {
                outData = aP.get(1);
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（0:装置正在启动中、1:正在交易中、2:工作人员正在处理中、其他：空）
     */
    private String occurCon1Change(Integer inputData,List<String> oc1){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ZERO) == 0) {
                outData = oc1.get(0);
            } else if (inputData.compareTo(ONE) == 0) {
                outData = oc1.get(1);
            } else if (inputData.compareTo(TWO) == 0) {
                outData = oc1.get(2);
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（0:支付、1:存款、2:余额确认、3:装填、4:回收、5:TEST中、6:待机中、7:其它、其他：空）
     */
    private String occurCon2Change(Integer inputData,List<String> oc2){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ZERO) == 0) {
                outData = oc2.get(0);
            } else if (inputData.compareTo(ONE) == 0) {
                outData = oc2.get(1);
            } else if (inputData.compareTo(TWO) == 0) {
                outData = oc2.get(2);
            } else if (inputData.compareTo(THREE) == 0) {
                outData = oc2.get(3);
            } else if (inputData.compareTo(FOUR) == 0) {
                outData = oc2.get(4);
            } else if (inputData.compareTo(FIVE) == 0) {
                outData = oc2.get(5);
            } else if (inputData.compareTo(SIX) == 0) {
                outData = oc2.get(6);
            } else if (inputData.compareTo(SEVEN) == 0) {
                outData = oc2.get(7);
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（1:能、0:不能、其他：空）
     */
    private String resetChange(Integer inputData,List<String> msg1){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ONE) == 0) {
                outData = msg1.get(0);
            } else if (inputData.compareTo(ZERO) == 0) {
                outData = msg1.get(1);
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（1:可、0:否、其他：空）
     */
    private String cutPowerChange(Integer inputData,List<String> msg3){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ONE) == 0) {
                outData = msg3.get(0);
            } else if (inputData.compareTo(ZERO) == 0) {
                outData = msg3.get(1);;
            }
        }
        return outData;
    }
    
    /**
     * @author liugd
     * @param inputData
     * @return outData:返回（1:正常启动了、0:没启动、其他：空）
     */
    private String rebootNormallyChange(Integer inputData,List<String> msg4){
        String outData = "";
        if (inputData != null) {
            if (inputData.compareTo(ONE) == 0) {
                outData = msg4.get(0);
            } else if (inputData.compareTo(ZERO) == 0) {
                outData = msg4.get(1);
            }
        }
        return outData;
    }
    
    /**
     * @return the fault
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * @param fault the fault to set
     */
    public void setFault(Fault fault) {
        this.fault = fault;
    }

    /**
     * @return the compList
     */
    public List<Company> getCompList() {
        return compList;
    }

    /**
     * @param compList the compList to set
     */
    public void setCompList(List<Company> compList) {
        this.compList = compList;
    }

    /**
     * @param faultList the faultList to set
     */
    public void setFaultList(List<Fault> faultList) {
        this.faultList = faultList;
    }

    /**
     * @return the faultList
     */
    public List<Fault> getFaultList() {
        return faultList;
    }

    /**
     * @return the install
     */
    public Installation getInstall() {
        return install;
    }

    /**
     * @param install the install to set
     */
    public void setInstall(Installation install) {
        this.install = install;
    }

    /**
     * @return the faultActionFrom
     */
    public String getFaultActionFrom() {
        return faultActionFrom;
    }

    /**
     * @param faultActionFrom the faultActionFrom to set
     */
    public void setFaultActionFrom(String faultActionFrom) {
        this.faultActionFrom = faultActionFrom;
    }

    /**
     * @return the upload
     */
    public File getUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(File upload) {
        this.upload = upload;
    }

    /**
     * @return the uploadFileName
     */
    public String getUploadFileName() {
        return uploadFileName;
    }

    /**
     * @param uploadFileName the uploadFileName to set
     */
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    /**
     * @return the uploadContentType
     */
    public String getUploadContentType() {
        return uploadContentType;
    }

    /**
     * @param uploadContentType the uploadContentType to set
     */
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    /**
     * @return the managementId
     */
    public String getManagementId() {
        return managementId;
    }

    /**
     * @param managementId the managementId to set
     */
    public void setManagementId(String managementId) {
        this.managementId = managementId;
    }

    /**
     * @return the faultSparesList
     */
    public List<FaultSpares> getFaultSparesList() {
        return faultSparesList;
    }

    /**
     * @param faultSparesList the faultSparesList to set
     */
    public void setFaultSparesList(List<FaultSpares> faultSparesList) {
        this.faultSparesList = faultSparesList;
    }

    /**
     * @return the finishChkList
     */
    public List<Integer> getFinishChkList() {
        return finishChkList;
    }

    /**
     * @param finishChkList the finishChkList to set
     */
    public void setFinishChkList(List<Integer> finishChkList) {
        this.finishChkList = finishChkList;
    }

    /**
     * @return the errorCodes
     */
    public List<String> getErrorCodes() {
        return errorCodes;
    }

    /**
     * @param errorCodes the errorCodes to set
     */
    public void setErrorCodes(List<String> errorCodes) {
        this.errorCodes = errorCodes;
    }

    /**
     * @return the faultHistoryList
     */
    public List<Fault> getFaultHistoryList() {
        return faultHistoryList;
    }

    /**
     * @param faultHistoryList the faultHistoryList to set
     */
    public void setFaultHistoryList(List<Fault> faultHistoryList) {
        this.faultHistoryList = faultHistoryList;
    }

    
}
