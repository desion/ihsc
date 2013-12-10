//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.FaultHandleBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.FaultHandle;

@SuppressWarnings("serial")
public class FaultHandleAction extends BaseAction{

    private Fault fault = new Fault();
    private FaultHandle faultHandle = new FaultHandle();
    private List<FaultHandle> faultHandleList = new ArrayList<FaultHandle>();
    
    /**
     * serch list
     * @author sunyx
     * @return
     */
    public String serchFaultHandleList() throws Exception{
        if(fault.getId() == null){
            return ILLEGAL_ERR;
        }
        
        FaultHandleBusiness fhb = new FaultHandleBusiness();
        
        fhb.permissionCheck(fault.getId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return INPUT;
        }
        
        faultHandleList = fhb.serchList(fault.getId());
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return INPUT;
        }

        DateFormat df = new DateFormat();
        for(int i=0;i<faultHandleList.size();i++){
            FaultHandle fh = faultHandleList.get(i);

            fh.setStartDate(df.changeDate(fh.getStartDate()));
            fh.setEndDate(df.changeDate(fh.getEndDate()));
            if(fh.getStartTime() != null){
                fh.setStartTime(df.changeTime(fh.getStartTime()));
            }
            if(fh.getEndTime() != null){
                fh.setEndTime(df.changeTime(fh.getEndTime()));
            }
        }
        return SUCCESS;
    }

    /**
     * serch detail information
     * @author sunyx
     * @return
     */
    public String serchDetail() throws Exception{
        if(faultHandle.getId() == null){
            return ILLEGAL_ERR;
        }

        FaultHandleBusiness fhb = new FaultHandleBusiness();

        faultHandle = fhb.serchDetail(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.permissionCheck(faultHandle.getFaultId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fault = fhb.serchFault(faultHandle.getFaultId());
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        DateFormat df = new DateFormat();
        fault.setOccurDate(df.changeDate(fault.getOccurDate()));
        fault.setOccurTime(df.changeTime(fault.getOccurTime()));

        return SUCCESS;
    }
    
    /**
     * create load
     * @author sunyx
     * @return
     */
    public String createFaultHandleLoad() throws Exception{
        if(fault.getId() == null){
            return ILLEGAL_ERR;
        }

        FaultHandleBusiness fhb = new FaultHandleBusiness();
        
        fhb.permissionCheck(fault.getId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return INPUT;
        }
        
        fhb.creatLoadCheck(fault.getId());
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }
    
    /**
     * create fault handle information
     * @author sunyx
     * @return
     */
    public String doCreateFaultHandle() throws Exception{
        if(faultHandle.getFaultId() == null){
            return ILLEGAL_ERR;
        }

        FaultHandleBusiness fhb = new FaultHandleBusiness();
        
        fhb.permissionCheck(faultHandle.getFaultId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }
        
        fhb.timeFormat(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.creatLoadCheck(faultHandle.getFaultId());
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }
        
        fhb.doCreateCheck(faultHandle,loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.doCreate(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * modify load
     * @author sunyx
     * @return
     * @throws Exception
     */
    public String modifyFaultHandleLoad() throws Exception{
        if(faultHandle.getId() == null){
            return ILLEGAL_ERR;
        }

        FaultHandleBusiness fhb = new FaultHandleBusiness();

        faultHandle = fhb.serchDetail(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.permissionCheck(faultHandle.getFaultId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * do modify fault handle information
     * @author sunyx
     * @return
     */
    public String doModifyFaultHandle() throws Exception{
        if(faultHandle.getId() == null){
            return ILLEGAL_ERR;
        }

        FaultHandleBusiness fhb = new FaultHandleBusiness();

        faultHandle.setFaultId(fhb.serchFaultId(faultHandle.getId()));
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.permissionCheck(faultHandle.getFaultId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.timeFormat(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.doCreateCheck(faultHandle,loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.doModify(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * delete fault handle
     * @author sunyx
     * @return
     * @throws Exception
     */
    public String delete() throws Exception{
        if(faultHandle.getId() == null){
            return ILLEGAL_ERR;
        }
        
        FaultHandleBusiness fhb = new FaultHandleBusiness();

        faultHandle.setFaultId(fhb.serchFaultId(faultHandle.getId()));
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.permissionCheck(faultHandle.getFaultId(), loginUser);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fhb.delete(faultHandle);
        if(fhb.hasErrors()){
            setActionMessages(getMessageText(fhb.getErrors()));
            return ERROR;
        }

        fault.setId(faultHandle.getFaultId());
        return SUCCESS;
    }
    
    public Fault getFault() {
        return fault;
    }

    public void setFault(Fault fault) {
        this.fault = fault;
    }

    public FaultHandle getFaultHandle() {
        return faultHandle;
    }

    public void setFaultHandle(FaultHandle faultHandle) {
        this.faultHandle = faultHandle;
    }

    public List<FaultHandle> getFaultHandleList() {
        return faultHandleList;
    }

    public void setFaultHandleList(List<FaultHandle> faultHandleList) {
        this.faultHandleList = faultHandleList;
    }
}
