package cn.com.bhh.erp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class FaultHandle implements Serializable{

    private Integer id;
    private Integer faultId;
    private Integer supporterId;
    private String supporterFamily;
    private String supporterGiven;
    private Integer operateCompanyId;
    private String operateCompanyName;
    private String operatorName;
    private String handleDetail;
    private String startDate;
    private String startTime;
    private String endDate;
    private String endTime;
    private String status;
    private Integer handleType;
    private String handleTypeName;
    private String note;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getFaultId() {
        return faultId;
    }
    public void setFaultId(Integer faultId) {
        this.faultId = faultId;
    }
    public Integer getSupporterId() {
        return supporterId;
    }
    public void setSupporterId(Integer supporterId) {
        this.supporterId = supporterId;
    }
    public String getSupporterFamily() {
        return supporterFamily;
    }
    public void setSupporterFamily(String supporterFamily) {
        this.supporterFamily = supporterFamily;
    }
    public String getSupporterGiven() {
        return supporterGiven;
    }
    public void setSupporterGiven(String supporterGiven) {
        this.supporterGiven = supporterGiven;
    }
    public Integer getOperateCompanyId() {
        return operateCompanyId;
    }
    public void setOperateCompanyId(Integer operateCompanyId) {
        this.operateCompanyId = operateCompanyId;
    }
    public String getOperateCompanyName() {
        return operateCompanyName;
    }
    public void setOperateCompanyName(String operateCompanyName) {
        this.operateCompanyName = operateCompanyName;
    }
    public String getOperatorName() {
        return operatorName;
    }
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    public String getHandleDetail() {
        return handleDetail;
    }
    public void setHandleDetail(String handleDetail) {
        this.handleDetail = handleDetail;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getHandleType() {
        return handleType;
    }
    public void setHandleType(Integer handleType) {
        this.handleType = handleType;
    }
    public String getHandleTypeName() {
        return handleTypeName;
    }
    public void setHandleTypeName(String handleTypeName) {
        this.handleTypeName = handleTypeName;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
