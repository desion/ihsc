//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * fault info
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class Fault implements Serializable {
    private Integer id;
    private String managementId;
    private String managementIdHidden;
    private Integer installationId;
    private Integer faultType;
    private String faultTypeName;
    private String occurDate;
    private String occurTime;
    private String reportDate;
    private String reportTime;
    private Integer repairState;
    private String finishDate;
    private String finishTime;
    private Integer repairCompanyId;
    private String repairCompanyName;
    private Integer supporterId;
    private String supporterName;
    private Integer followerId;
    private String followerName;
    private String operatorName;
    private Integer supportType;
    private String supportTypeName;
    private Integer faultPart;
    private String faultPartName;
    private Integer faultPartType;
    private String faultPartTypeName;
    private String isState;
    private Integer applicationVersion = -1;
    private String applicationVersionDetail1;
    private String applicationVersionDetail2;
    private Integer locked;
    private Integer deleted;
    private Integer affirmFlag;
    private Integer affirmantId;
    private String affirmTime;
    private String createTime;
    private Integer creatorId;
    private String modifyTime;
    private Integer modifierId;
    private Integer exclusiveKey;
    private Integer customerId;
    private String operation;
    private String handleDetail;
    private String note;
    private String subCompany;
    private String installPlace;
    private Integer agentId;
    private String supportFlg;
    private String installDate;
    
    //2010/01/07 add
    private String mainEpver;
    private String bidEpver;
    private String ecErrorCode;
    private String mtcCode;
    private Integer cashControlLight = -1;
    private Integer faultLight = -1;
    private String shineLight;
    private String stayShineLight;
    private String outLight;
    private Integer leftOnlineLight = -1;
    private Integer rightOnlineLight = -1;
    private String epVer;
    private String faultMachineType;
    
    private List<FaultHandle> faultHandleList=new ArrayList<FaultHandle>();
    
    /** 最终用户公司 */
    private String customerCompanyName;
    
    /** 代理公司 */
    private String agentCompanyName;
    
    /** 型名 */
    private String model;

    /** 机种 */
    private String productCategoryName;

    /** 城市  */
    private String cityName;

    /** 客户 */
    private String customerName;

    /** 制造号 */
    private String manufactureNo;

    /** 用途 */
    private String purpose;

    /** 保修开始时间 */
    private String guaranteeStartDate;

    /** 保修结束时间 */
    private String guaranteeEndDate;

    /** 保修时间 */
    private String guaranteeDate;

    /** BRM EP Version  */
    private String brmEpVer;

    /** BV EP Version  */
    private String bvEpVer;

    /** 发生状况1 */
    private Integer occurCondiction1 = -1;

    /** 发生状况2 */
    private Integer occurCondiction2 = -1;

    /** 错误代码 */
    private String errorCode;

    /** RX278P/K表示 */
    private String rx278;

    /** 运行计数器 */
    private String counter;

    /** 现象 */
    private String appearance;

    /** 原因 */
    private String reason;

    /** 对策 */
    private String strategy;

    /** 详细对策 */
    private String strategyDetail;

    /** 故障状态编号 */
    private Integer resultId;

    /** 结果/故障状态名称 */
    private String result;

    /** 发生故障前操作1 */
    private String operation1;

    /** 发生故障前操作2 */
    private String operation2;

    /** 发生故障前操作3 */
    private String operation3;

    /** 发生故障前操作4 */
    private String operation4;

    /** 发生故障前操作5 */
    private String operation5;

    /** 发生故障前操作6 */
    private String operation6;

    /** 发生故障前操作7 */
    private String operation7;

    /** 发生故障前操作8 */
    private String operation8;

    /** 残留纸币 */
    private Integer cashLeft = -1;

    /** 异常显示 */
    private Integer exceptionDisplay = -1;

    /** 有无显示 */
    private Integer display = -1;

    /** 后灯 */
    private Integer backLight = -1;

    /** 显示内容 */
    private String displayContent;

    /** 能否输入 */
    private Integer inputable = -1;

    /** 敲击声 */
    private Integer knockSound = -1;

    /** 复位有效 */
    private Integer reset = -1;

    /** 切断电源 */
    private Integer cutPower = -1;

    /** 正常启动 */
    private Integer rebootNormally = -1;

    /** 运行计数器 */
    private String motionCounter;

    /** 错误码 */
    private String errorNo;

    /** 无应答时画面显示 */
    private String noRepon;

    /** 其它显示 */
    private String otherDisplay;

    /** 追踪信息 */
    private Integer traceInfomation = -1;

    /** 故障记录 */
    private Integer faultRecord = -1;

    /** 统计记录 */
    private Integer statistics = -1;

    /** 交易日志 */
    private Integer tradeLog = -1;

    /** 程序版本 */
    private Integer applicationVersion1 = -1;

    /** 其它 */
    private String others;

    /** C盘RAS */
    private Integer cras = -1;

    /** D盘RAS */
    private Integer dras = -1;

    /** MCU详细LOG */
    private Integer mcuLog = -1;

    /** 系统事件查看器 */
    private Integer systemEvent = -1;

    /** D盘BillBox */
    private Integer dbillbox = -1;

    /** D盘Collect */
    private Integer dcollect = -1;

    /** C盘FEP */
    private Integer cfep = -1;

    /** C盘ERR */
    private Integer cerr = -1;

    /** 客户收到日 */
    private String receiveDate;

    /** 备件返还 */
    private String returnDate;

    /** 备件申请日期 */
    private String applyDate;

    /** 备件发送日期 */
    private String deliverDate;

    /** 备件更换日期 */
    private String replaceDate;

    /** IHSC收到日期 */
    private String ihscReceiveDate;

    /** sort */
    private String sortType;
    
    /** sort key */
    private String sort;

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }
    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }
    /**
     * @return the sortType
     */
    public String getSortType() {
        return sortType;
    }
    /**
     * @param sortType the sortType to set
     */
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the managementIdHidden
     */
    public String getManagementIdHidden() {
        return managementIdHidden;
    }
    /**
     * @param managementIdHidden the managementIdHidden to set
     */
    public void setManagementIdHidden(String managementIdHidden) {
        this.managementIdHidden = managementIdHidden;
    }
    /**
     * @return the installationId
     */
    public Integer getInstallationId() {
        return installationId;
    }
    /**
     * @param installationId the installationId to set
     */
    public void setInstallationId(Integer installationId) {
        this.installationId = installationId;
    }
    /**
     * @return the faultType
     */
    public Integer getFaultType() {
        return faultType;
    }
    /**
     * @param faultType the faultType to set
     */
    public void setFaultType(Integer faultType) {
        this.faultType = faultType;
    }
    /**
     * @return the faultTypeName
     */
    public String getFaultTypeName() {
        return faultTypeName;
    }
    /**
     * @param faultTypeName the faultTypeName to set
     */
    public void setFaultTypeName(String faultTypeName) {
        this.faultTypeName = faultTypeName;
    }
    /**
     * @return the appearance
     */
    public String getAppearance() {
        return appearance;
    }
    /**
     * @param appearance the appearance to set
     */
    public void setAppearance(String appearance) {
        this.appearance = appearance;
    }
    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }
    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    /**
     * @return the strategy
     */
    public String getStrategy() {
        return strategy;
    }
    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    /**
     * @return the strategyDetail
     */
    public String getStrategyDetail() {
        return strategyDetail;
    }
    /**
     * @param strategyDetail the strategyDetail to set
     */
    public void setStrategyDetail(String strategyDetail) {
        this.strategyDetail = strategyDetail;
    }
    /**
     * @return the resultId
     */
    public Integer getResultId() {
        return resultId;
    }
    /**
     * @param resultId the resultId to set
     */
    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }
    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
    /**
     * @param result the result to set
     */
    public void setResult(String result) {
        this.result = result;
    }
    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }
    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * @return the occurDate
     */
    public String getOccurDate() {
        return occurDate;
    }
    /**
     * @param occurDate the occurDate to set
     */
    public void setOccurDate(String occurDate) {
        this.occurDate = occurDate;
    }
    /**
     * @return the occurTime
     */
    public String getOccurTime() {
        return occurTime;
    }
    /**
     * @param occurTime the occurTime to set
     */
    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }
    /**
     * @return the reportDate
     */
    public String getReportDate() {
        return reportDate;
    }
    /**
     * @param reportDate the reportDate to set
     */
    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    /**
     * @return the reportTime
     */
    public String getReportTime() {
        return reportTime;
    }
    /**
     * @param reportTime the reportTime to set
     */
    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }
    /**
     * @return the repairState
     */
    public Integer getRepairState() {
        return repairState;
    }
    /**
     * @param repairState the repairState to set
     */
    public void setRepairState(Integer repairState) {
        this.repairState = repairState;
    }
    /**
     * @return the finishDate
     */
    public String getFinishDate() {
        return finishDate;
    }
    /**
     * @param finishDate the finishDate to set
     */
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
    /**
     * @return the finishTime
     */
    public String getFinishTime() {
        return finishTime;
    }
    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
    /**
     * @return the repairCompanyId
     */
    public Integer getRepairCompanyId() {
        return repairCompanyId;
    }
    /**
     * @param repairCompanyId the repairCompanyId to set
     */
    public void setRepairCompanyId(Integer repairCompanyId) {
        this.repairCompanyId = repairCompanyId;
    }
    /**
     * @return the repairCompanyName
     */
    public String getRepairCompanyName() {
        return repairCompanyName;
    }
    /**
     * @param repairCompanyName the repairCompanyName to set
     */
    public void setRepairCompanyName(String repairCompanyName) {
        this.repairCompanyName = repairCompanyName;
    }
    /**
     * @return the supporterId
     */
    public Integer getSupporterId() {
        return supporterId;
    }
    /**
     * @param supporterId the supporterId to set
     */
    public void setSupporterId(Integer supporterId) {
        this.supporterId = supporterId;
    }
    /**
     * @return the supporterName
     */
    public String getSupporterName() {
        return supporterName;
    }
    /**
     * @param supporterName the supporterName to set
     */
    public void setSupporterName(String supporterName) {
        this.supporterName = supporterName;
    }
    /**
     * @return the followerId
     */
    public Integer getFollowerId() {
        return followerId;
    }
    /**
     * @param followerId the followerId to set
     */
    public void setFollowerId(Integer followerId) {
        this.followerId = followerId;
    }
    /**
     * @return the followerName
     */
    public String getFollowerName() {
        return followerName;
    }
    /**
     * @param followerName the followerName to set
     */
    public void setFollowerName(String followerName) {
        this.followerName = followerName;
    }
    /**
     * @return the operatorName
     */
    public String getOperatorName() {
        return operatorName;
    }
    /**
     * @param operatorName the operatorName to set
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    /**
     * @return the supportType
     */
    public Integer getSupportType() {
        return supportType;
    }
    /**
     * @param supportType the supportType to set
     */
    public void setSupportType(Integer supportType) {
        this.supportType = supportType;
    }
    /**
     * @return the supportTypeName
     */
    public String getSupportTypeName() {
        return supportTypeName;
    }
    /**
     * @param supportTypeName the supportTypeName to set
     */
    public void setSupportTypeName(String supportTypeName) {
        this.supportTypeName = supportTypeName;
    }
    /**
     * @return the faultPart
     */
    public Integer getFaultPart() {
        return faultPart;
    }
    /**
     * @param faultPart the faultPart to set
     */
    public void setFaultPart(Integer faultPart) {
        this.faultPart = faultPart;
    }
    /**
     * @return the faultPartName
     */
    public String getFaultPartName() {
        return faultPartName;
    }
    /**
     * @param faultPartName the faultPartName to set
     */
    public void setFaultPartName(String faultPartName) {
        this.faultPartName = faultPartName;
    }
    /**
     * @return the faultPartType
     */
    public Integer getFaultPartType() {
        return faultPartType;
    }
    /**
     * @param faultPartType the faultPartType to set
     */
    public void setFaultPartType(Integer faultPartType) {
        this.faultPartType = faultPartType;
    }
    /**
     * @return the faultPartTypeName
     */
    public String getFaultPartTypeName() {
        return faultPartTypeName;
    }
    /**
     * @param faultPartTypeName the faultPartTypeName to set
     */
    public void setFaultPartTypeName(String faultPartTypeName) {
        this.faultPartTypeName = faultPartTypeName;
    }
    /**
     * @return the isState
     */
    public String getIsState() {
        return isState;
    }
    /**
     * @param isState the isState to set
     */
    public void setIsState(String isState) {
        this.isState = isState;
    }
    /**
     * @return the applicationVersion
     */
    public Integer getApplicationVersion() {
        return applicationVersion;
    }
    /**
     * @param applicationVersion the applicationVersion to set
     */
    public void setApplicationVersion(Integer applicationVersion) {
        this.applicationVersion = applicationVersion;
    }
    /**
     * @return the applicationVersionDetail1
     */
    public String getApplicationVersionDetail1() {
        return applicationVersionDetail1;
    }
    /**
     * @param applicationVersionDetail1 the applicationVersionDetail1 to set
     */
    public void setApplicationVersionDetail1(String applicationVersionDetail1) {
        this.applicationVersionDetail1 = applicationVersionDetail1;
    }
    /**
     * @return the applicationVersionDetail2
     */
    public String getApplicationVersionDetail2() {
        return applicationVersionDetail2;
    }
    /**
     * @param applicationVersionDetail2 the applicationVersionDetail2 to set
     */
    public void setApplicationVersionDetail2(String applicationVersionDetail2) {
        this.applicationVersionDetail2 = applicationVersionDetail2;
    }
    /**
     * @return the locked
     */
    public Integer getLocked() {
        return locked;
    }
    /**
     * @param locked the locked to set
     */
    public void setLocked(Integer locked) {
        this.locked = locked;
    }
    /**
     * @return the deleted
     */
    public Integer getDeleted() {
        return deleted;
    }
    /**
     * @param deleted the deleted to set
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    /**
     * @return the affirmFlag
     */
    public Integer getAffirmFlag() {
        return affirmFlag;
    }
    /**
     * @param affirmFlag the affirmFlag to set
     */
    public void setAffirmFlag(Integer affirmFlag) {
        this.affirmFlag = affirmFlag;
    }
    /**
     * @return the affirmantId
     */
    public Integer getAffirmantId() {
        return affirmantId;
    }
    /**
     * @param affirmantId the affirmantId to set
     */
    public void setAffirmantId(Integer affirmantId) {
        this.affirmantId = affirmantId;
    }
    /**
     * @return the affirmTime
     */
    public String getAffirmTime() {
        return affirmTime;
    }
    /**
     * @param affirmTime the affirmTime to set
     */
    public void setAffirmTime(String affirmTime) {
        this.affirmTime = affirmTime;
    }
    /**
     * @return the createTime
     */
    public String getCreateTime() {
        return createTime;
    }
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * @return the creatorId
     */
    public Integer getCreatorId() {
        return creatorId;
    }
    /**
     * @param creatorId the creatorId to set
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }
    /**
     * @return the modifyTime
     */
    public String getModifyTime() {
        return modifyTime;
    }
    /**
     * @param modifyTime the modifyTime to set
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    /**
     * @return the modifierId
     */
    public Integer getModifierId() {
        return modifierId;
    }
    /**
     * @param modifierId the modifierId to set
     */
    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }
    /**
     * @return the exclusiveKey
     */
    public Integer getExclusiveKey() {
        return exclusiveKey;
    }
    /**
     * @param exclusiveKey the exclusiveKey to set
     */
    public void setExclusiveKey(Integer exclusiveKey) {
        this.exclusiveKey = exclusiveKey;
    }
    /**
     * @return the customerId
     */
    public Integer getCustomerId() {
        return customerId;
    }
    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    /**
     * @return the operation
     */
    public String getOperation() {
        return operation;
    }
    /**
     * @param operation the operation to set
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }
    /**
     * @return the handleDetail
     */
    public String getHandleDetail() {
        return handleDetail;
    }
    /**
     * @param handleDetail the handleDetail to set
     */
    public void setHandleDetail(String handleDetail) {
        this.handleDetail = handleDetail;
    }
    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }
    /**
     * @param note the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }
    /**
     * @return the subCompany
     */
    public String getSubCompany() {
        return subCompany;
    }
    /**
     * @param subCompany the subCompany to set
     */
    public void setSubCompany(String subCompany) {
        this.subCompany = subCompany;
    }
    /**
     * @return the installPlace
     */
    public String getInstallPlace() {
        return installPlace;
    }
    /**
     * @param installPlace the installPlace to set
     */
    public void setInstallPlace(String installPlace) {
        this.installPlace = installPlace;
    }
    /**
     * @return the installDate
     */
    public String getInstallDate() {
        return installDate;
    }
    /**
     * @param installDate the installDate to set
     */
    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }
    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }
    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }
    /**
     * @return the agentId
     */
    public Integer getAgentId() {
        return agentId;
    }
    /**
     * @param agentId the agentId to set
     */
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**
     * @return the manufactureNo
     */
    public String getManufactureNo() {
        return manufactureNo;
    }
    /**
     * @param manufactureNo the manufactureNo to set
     */
    public void setManufactureNo(String manufactureNo) {
        this.manufactureNo = manufactureNo;
    }
    /**
     * @return the supportFlg
     */
    public String getSupportFlg() {
        return supportFlg;
    }
    /**
     * @param supportFlg the supportFlg to set
     */
    public void setSupportFlg(String supportFlg) {
        this.supportFlg = supportFlg;
    }
    /**
     * @return the occurCondiction1
     */
    public Integer getOccurCondiction1() {
        return occurCondiction1;
    }
    /**
     * @param occurCondiction1 the occurCondiction1 to set
     */
    public void setOccurCondiction1(Integer occurCondiction1) {
        this.occurCondiction1 = occurCondiction1;
    }
    /**
     * @return the occurCondiction2
     */
    public Integer getOccurCondiction2() {
        return occurCondiction2;
    }
    /**
     * @param occurCondiction2 the occurCondiction2 to set
     */
    public void setOccurCondiction2(Integer occurCondiction2) {
        this.occurCondiction2 = occurCondiction2;
    }
    /**
     * @return the rx278
     */
    public String getRx278() {
        return rx278;
    }
    /**
     * @param rx278 the rx278 to set
     */
    public void setRx278(String rx278) {
        this.rx278 = rx278;
    }
    /**
     * @return the counter
     */
    public String getCounter() {
        return counter;
    }
    /**
     * @param counter the counter to set
     */
    public void setCounter(String counter) {
        this.counter = counter;
    }
    /**
     * @return the operation1
     */
    public String getOperation1() {
        return operation1;
    }
    /**
     * @param operation1 the operation1 to set
     */
    public void setOperation1(String operation1) {
        this.operation1 = operation1;
    }
    /**
     * @return the operation2
     */
    public String getOperation2() {
        return operation2;
    }
    /**
     * @param operation2 the operation2 to set
     */
    public void setOperation2(String operation2) {
        this.operation2 = operation2;
    }
    /**
     * @return the operation3
     */
    public String getOperation3() {
        return operation3;
    }
    /**
     * @param operation3 the operation3 to set
     */
    public void setOperation3(String operation3) {
        this.operation3 = operation3;
    }
    /**
     * @return the operation4
     */
    public String getOperation4() {
        return operation4;
    }
    /**
     * @param operation4 the operation4 to set
     */
    public void setOperation4(String operation4) {
        this.operation4 = operation4;
    }
    /**
     * @return the operation5
     */
    public String getOperation5() {
        return operation5;
    }
    /**
     * @param operation5 the operation5 to set
     */
    public void setOperation5(String operation5) {
        this.operation5 = operation5;
    }
    /**
     * @return the operation6
     */
    public String getOperation6() {
        return operation6;
    }
    /**
     * @param operation6 the operation6 to set
     */
    public void setOperation6(String operation6) {
        this.operation6 = operation6;
    }
    /**
     * @return the operation7
     */
    public String getOperation7() {
        return operation7;
    }
    /**
     * @param operation7 the operation7 to set
     */
    public void setOperation7(String operation7) {
        this.operation7 = operation7;
    }
    /**
     * @return the operation8
     */
    public String getOperation8() {
        return operation8;
    }
    /**
     * @param operation8 the operation8 to set
     */
    public void setOperation8(String operation8) {
        this.operation8 = operation8;
    }
    /**
     * @return the cashLeft
     */
    public Integer getCashLeft() {
        return cashLeft;
    }
    /**
     * @param cashLeft the cashLeft to set
     */
    public void setCashLeft(Integer cashLeft) {
        this.cashLeft = cashLeft;
    }
    /**
     * @return the exceptionDisplay
     */
    public Integer getExceptionDisplay() {
        return exceptionDisplay;
    }
    /**
     * @param exceptionDisplay the exceptionDisplay to set
     */
    public void setExceptionDisplay(Integer exceptionDisplay) {
        this.exceptionDisplay = exceptionDisplay;
    }
    /**
     * @return the display
     */
    public Integer getDisplay() {
        return display;
    }
    /**
     * @param display the display to set
     */
    public void setDisplay(Integer display) {
        this.display = display;
    }
    /**
     * @return the backLight
     */
    public Integer getBackLight() {
        return backLight;
    }
    /**
     * @param backLight the backLight to set
     */
    public void setBackLight(Integer backLight) {
        this.backLight = backLight;
    }
    /**
     * @return the displayContent
     */
    public String getDisplayContent() {
        return displayContent;
    }
    /**
     * @param displayContent the displayContent to set
     */
    public void setDisplayContent(String displayContent) {
        this.displayContent = displayContent;
    }
    /**
     * @return the inputable
     */
    public Integer getInputable() {
        return inputable;
    }
    /**
     * @param inputable the inputable to set
     */
    public void setInputable(Integer inputable) {
        this.inputable = inputable;
    }
    /**
     * @return the knockSound
     */
    public Integer getKnockSound() {
        return knockSound;
    }
    /**
     * @param knockSound the knockSound to set
     */
    public void setKnockSound(Integer knockSound) {
        this.knockSound = knockSound;
    }
    /**
     * @return the reset
     */
    public Integer getReset() {
        return reset;
    }
    /**
     * @param reset the reset to set
     */
    public void setReset(Integer reset) {
        this.reset = reset;
    }
    /**
     * @return the cutPower
     */
    public Integer getCutPower() {
        return cutPower;
    }
    /**
     * @param cutPower the cutPower to set
     */
    public void setCutPower(Integer cutPower) {
        this.cutPower = cutPower;
    }
    /**
     * @return the rebootNormally
     */
    public Integer getRebootNormally() {
        return rebootNormally;
    }
    /**
     * @param rebootNormally the rebootNormally to set
     */
    public void setRebootNormally(Integer rebootNormally) {
        this.rebootNormally = rebootNormally;
    }
    /**
     * @return the motionCounter
     */
    public String getMotionCounter() {
        return motionCounter;
    }
    /**
     * @param motionCounter the motionCounter to set
     */
    public void setMotionCounter(String motionCounter) {
        this.motionCounter = motionCounter;
    }
    /**
     * @return the errorNo
     */
    public String getErrorNo() {
        return errorNo;
    }
    /**
     * @param errorNo the errorNo to set
     */
    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }
    /**
     * @return the noRepon
     */
    public String getNoRepon() {
        return noRepon;
    }
    /**
     * @param noRepon the noRepon to set
     */
    public void setNoRepon(String noRepon) {
        this.noRepon = noRepon;
    }
    /**
     * @return the otherDisplay
     */
    public String getOtherDisplay() {
        return otherDisplay;
    }
    /**
     * @param otherDisplay the otherDisplay to set
     */
    public void setOtherDisplay(String otherDisplay) {
        this.otherDisplay = otherDisplay;
    }
    /**
     * @return the traceInfomation
     */
    public Integer getTraceInfomation() {
        return traceInfomation;
    }
    /**
     * @param traceInfomation the traceInfomation to set
     */
    public void setTraceInfomation(Integer traceInfomation) {
        this.traceInfomation = traceInfomation;
    }
    /**
     * @return the faultRecord
     */
    public Integer getFaultRecord() {
        return faultRecord;
    }
    /**
     * @param faultRecord the faultRecord to set
     */
    public void setFaultRecord(Integer faultRecord) {
        this.faultRecord = faultRecord;
    }
    /**
     * @return the statistics
     */
    public Integer getStatistics() {
        return statistics;
    }
    /**
     * @param statistics the statistics to set
     */
    public void setStatistics(Integer statistics) {
        this.statistics = statistics;
    }
    /**
     * @return the tradeLog
     */
    public Integer getTradeLog() {
        return tradeLog;
    }
    /**
     * @param tradeLog the tradeLog to set
     */
    public void setTradeLog(Integer tradeLog) {
        this.tradeLog = tradeLog;
    }
    /**
     * @return the applicationVersion1
     */
    public Integer getApplicationVersion1() {
        return applicationVersion1;
    }
    /**
     * @param applicationVersion1 the applicationVersion1 to set
     */
    public void setApplicationVersion1(Integer applicationVersion1) {
        this.applicationVersion1 = applicationVersion1;
    }
    /**
     * @return the others
     */
    public String getOthers() {
        return others;
    }
    /**
     * @param others the others to set
     */
    public void setOthers(String others) {
        this.others = others;
    }
    /**
     * @return the cras
     */
    public Integer getCras() {
        return cras;
    }
    /**
     * @param cras the cras to set
     */
    public void setCras(Integer cras) {
        this.cras = cras;
    }
    /**
     * @return the dras
     */
    public Integer getDras() {
        return dras;
    }
    /**
     * @param dras the dras to set
     */
    public void setDras(Integer dras) {
        this.dras = dras;
    }
    /**
     * @return the mcuLog
     */
    public Integer getMcuLog() {
        return mcuLog;
    }
    /**
     * @param mcuLog the mcuLog to set
     */
    public void setMcuLog(Integer mcuLog) {
        this.mcuLog = mcuLog;
    }
    /**
     * @return the systemEvent
     */
    public Integer getSystemEvent() {
        return systemEvent;
    }
    /**
     * @param systemEvent the systemEvent to set
     */
    public void setSystemEvent(Integer systemEvent) {
        this.systemEvent = systemEvent;
    }
    /**
     * @return the dbillbox
     */
    public Integer getDbillbox() {
        return dbillbox;
    }
    /**
     * @param dbillbox the dbillbox to set
     */
    public void setDbillbox(Integer dbillbox) {
        this.dbillbox = dbillbox;
    }
    /**
     * @return the dcollect
     */
    public Integer getDcollect() {
        return dcollect;
    }
    /**
     * @param dcollect the dcollect to set
     */
    public void setDcollect(Integer dcollect) {
        this.dcollect = dcollect;
    }
    /**
     * @return the cfep
     */
    public Integer getCfep() {
        return cfep;
    }
    /**
     * @param cfep the cfep to set
     */
    public void setCfep(Integer cfep) {
        this.cfep = cfep;
    }
    /**
     * @return the cerr
     */
    public Integer getCerr() {
        return cerr;
    }
    /**
     * @param cerr the cerr to set
     */
    public void setCerr(Integer cerr) {
        this.cerr = cerr;
    }
    /**
     * @return the receiveDate
     */
    public String getReceiveDate() {
        return receiveDate;
    }
    /**
     * @param receiveDate the receiveDate to set
     */
    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }
    /**
     * @return the returnDate
     */
    public String getReturnDate() {
        return returnDate;
    }
    /**
     * @param returnDate the returnDate to set
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    /**
     * @return the productCategoryName
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }
    /**
     * @param productCategoryName the productCategoryName to set
     */
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }
    /**
     * @return the cityName
     */
    public String getCityName() {
        return cityName;
    }
    /**
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    /**
     * @return the purpose
     */
    public String getPurpose() {
        return purpose;
    }
    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    /**
     * @return the guaranteeStartDate
     */
    public String getGuaranteeStartDate() {
        return guaranteeStartDate;
    }
    /**
     * @param guaranteeStartDate the guaranteeStartDate to set
     */
    public void setGuaranteeStartDate(String guaranteeStartDate) {
        this.guaranteeStartDate = guaranteeStartDate;
    }
    /**
     * @return the guaranteeEndDate
     */
    public String getGuaranteeEndDate() {
        return guaranteeEndDate;
    }
    /**
     * @param guaranteeEndDate the guaranteeEndDate to set
     */
    public void setGuaranteeEndDate(String guaranteeEndDate) {
        this.guaranteeEndDate = guaranteeEndDate;
    }
    /**
     * @return the guaranteeDate
     */
    public String getGuaranteeDate() {
        return guaranteeDate;
    }
    /**
     * @param guaranteeDate the guaranteeDate to set
     */
    public void setGuaranteeDate(String guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
    }
    /**
     * @return the brmEpVer
     */
    public String getBrmEpVer() {
        return brmEpVer;
    }
    /**
     * @param brmEpVer the brmEpVer to set
     */
    public void setBrmEpVer(String brmEpVer) {
        this.brmEpVer = brmEpVer;
    }
    /**
     * @return the bvEpVer
     */
    public String getBvEpVer() {
        return bvEpVer;
    }
    /**
     * @param bvEpVer the bvEpVer to set
     */
    public void setBvEpVer(String bvEpVer) {
        this.bvEpVer = bvEpVer;
    }
    /**
     * @return the applyDate
     */
    public String getApplyDate() {
        return applyDate;
    }
    /**
     * @param applyDate the applyDate to set
     */
    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }
    /**
     * @return the deliverDate
     */
    public String getDeliverDate() {
        return deliverDate;
    }
    /**
     * @param deliverDate the deliverDate to set
     */
    public void setDeliverDate(String deliverDate) {
        this.deliverDate = deliverDate;
    }
    /**
     * @return the replaceDate
     */
    public String getReplaceDate() {
        return replaceDate;
    }
    /**
     * @param replaceDate the replaceDate to set
     */
    public void setReplaceDate(String replaceDate) {
        this.replaceDate = replaceDate;
    }
    /**
     * @return the ihscReceiveDate
     */
    public String getIhscReceiveDate() {
        return ihscReceiveDate;
    }
    /**
     * @param ihscReceiveDate the ihscReceiveDate to set
     */
    public void setIhscReceiveDate(String ihscReceiveDate) {
        this.ihscReceiveDate = ihscReceiveDate;
    }
    /**
     * @return the customerCompanyName
     */
    public String getCustomerCompanyName() {
        return customerCompanyName;
    }
    /**
     * @param customerCompanyName the customerCompanyName to set
     */
    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }
    /**
     * @return the agentCompanyName
     */
    public String getAgentCompanyName() {
        return agentCompanyName;
    }
    /**
     * @param agentCompanyName the agentCompanyName to set
     */
    public void setAgentCompanyName(String agentCompanyName) {
        this.agentCompanyName = agentCompanyName;
    }
    /**
     * @return the faultHandleList
     */
    public List<FaultHandle> getFaultHandleList() {
        return faultHandleList;
    }
    /**
     * @param faultHandleList the faultHandleList to set
     */
    public void setFaultHandleList(List<FaultHandle> faultHandleList) {
        this.faultHandleList = faultHandleList;
    }
    /**
     * @return the mainEpver
     */
    public String getMainEpver() {
        return mainEpver;
    }
    /**
     * @param mainEpver the mainEpver to set
     */
    public void setMainEpver(String mainEpver) {
        this.mainEpver = mainEpver;
    }
    /**
     * @return the bidEpver
     */
    public String getBidEpver() {
        return bidEpver;
    }
    /**
     * @param bidEpver the bidEpver to set
     */
    public void setBidEpver(String bidEpver) {
        this.bidEpver = bidEpver;
    }
 
    /**
     * @return the ecErrorCode
     */
    public String getEcErrorCode() {
        return ecErrorCode;
    }
    /**
     * @param ecErrorCode the ecErrorCode to set
     */
    public void setEcErrorCode(String ecErrorCode) {
        this.ecErrorCode = ecErrorCode;
    }
    /**
     * @return the mtcCode
     */
    public String getMtcCode() {
        return mtcCode;
    }
    /**
     * @param mtcCode the mtcCode to set
     */
    public void setMtcCode(String mtcCode) {
        this.mtcCode = mtcCode;
    }
    /**
     * @return the cashControlLight
     */
    public Integer getCashControlLight() {
        return cashControlLight;
    }
    /**
     * @param cashControlLight the cashControlLight to set
     */
    public void setCashControlLight(Integer cashControlLight) {
        this.cashControlLight = cashControlLight;
    }
    /**
     * @return the faultLight
     */
    public Integer getFaultLight() {
        return faultLight;
    }
    /**
     * @param faultLight the faultLight to set
     */
    public void setFaultLight(Integer faultLight) {
        this.faultLight = faultLight;
    }
    /**
     * @return the shineLight
     */
    public String getShineLight() {
        return shineLight;
    }
    /**
     * @param shineLight the shineLight to set
     */
    public void setShineLight(String shineLight) {
        this.shineLight = shineLight;
    }

    /**
     * @return the stayShineLight
     */
    public String getStayShineLight() {
        return stayShineLight;
    }
    /**
     * @param stayShineLight the stayShineLight to set
     */
    public void setStayShineLight(String stayShineLight) {
        this.stayShineLight = stayShineLight;
    }
    /**
     * @return the outLight
     */
    public String getOutLight() {
        return outLight;
    }
    /**
     * @param outLight the outLight to set
     */
    public void setOutLight(String outLight) {
        this.outLight = outLight;
    }
    /**
     * @return the leftOnlineLight
     */
    public Integer getLeftOnlineLight() {
        return leftOnlineLight;
    }
    /**
     * @param leftOnlineLight the leftOnlineLight to set
     */
    public void setLeftOnlineLight(Integer leftOnlineLight) {
        this.leftOnlineLight = leftOnlineLight;
    }
    /**
     * @return the rightOnlineLight
     */
    public Integer getRightOnlineLight() {
        return rightOnlineLight;
    }
    /**
     * @param rightOnlineLight the rightOnlineLight to set
     */
    public void setRightOnlineLight(Integer rightOnlineLight) {
        this.rightOnlineLight = rightOnlineLight;
    }
    /**
     * @return the epVer
     */
    public String getEpVer() {
        return epVer;
    }
    /**
     * @param epVer the epVer to set
     */
    public void setEpVer(String epVer) {
        this.epVer = epVer;
    }
    /**
     * @return the faultMachineType
     */
    public String getFaultMachineType() {
        return faultMachineType;
    }
    /**
     * @param faultMachineType the faultMachineType to set
     */
    public void setFaultMachineType(String faultMachineType) {
        this.faultMachineType = faultMachineType;
    }

}
