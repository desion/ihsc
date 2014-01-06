//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.FaultBusiness;
import cn.com.bhh.erp.business.InstallationApplyBusiness;
import cn.com.bhh.erp.business.InstallationBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.InstallationApply;

/**
 * prepare the data for the main page.
 * 
 */
@SuppressWarnings("serial")
public class MainPageAction extends BaseAction {
    private List<Installation> installationList = new ArrayList<Installation>();
    private List<Fault> faultList = new ArrayList<Fault>();
    private List<InstallationApply> applyInstallList = new ArrayList<InstallationApply>();
   

    public String execute() throws Exception {
        //unconfirmed installation list
        InstallationBusiness installBussiness = new InstallationBusiness();
        installationList = installBussiness.serchUseStatus(loginUser);
        if (installBussiness.hasErrors()) {
            setActionMessages(getMessageText(installBussiness.getErrors()));
            return INPUT;
        }      

        //unconfirmed fault list
        FaultBusiness faultBusiness = new FaultBusiness();
        faultList = faultBusiness.getFaultList(loginUser);
        if (faultBusiness.hasErrors()) {
            setActionMessages(getMessageText(faultBusiness.getErrors()));
            return INPUT;
        }
        
        //unconfirm installation apply list.
        InstallationApplyBusiness insatllApplyBusiness = new InstallationApplyBusiness();
        applyInstallList = insatllApplyBusiness.serchApplyList(loginUser);
        if (insatllApplyBusiness.hasErrors()) {
            setActionMessages(getMessageText(insatllApplyBusiness.getErrors()));
            return INPUT;
        }
        
        //format the date
        DateFormat format = new DateFormat();
        for (int i = 0; i < installationList.size(); i++) {
            Installation temp = installationList.get(i);
            temp.setInstallDate(format.changeDate(temp.getInstallDate()));
        }
        
        
        for (int i = 0; i < faultList.size(); i++) {
            Fault temp = faultList.get(i);
            temp.setOccurDate(format.changeDate(temp.getOccurDate()));
        }
        
        for (int i = 0; i < applyInstallList.size(); i++) {
            InstallationApply temp = applyInstallList.get(i);
            temp.setApplyDate(format.changeDate(temp.getApplyDate()));
        }
 
        return SUCCESS;
    }
    
    /**
     * @return the installationList
     */
    public List<Installation> getInstallationList() {
        return installationList;
    }
 
    /**
     * @return the faultList
     */
    public List<Fault> getFaultList() {
        return faultList;
    }

    /**
     * @return the applyInstallList
     */
    public List<InstallationApply> getApplyInstallList() {
        return applyInstallList;
    }

    

    
    
}
