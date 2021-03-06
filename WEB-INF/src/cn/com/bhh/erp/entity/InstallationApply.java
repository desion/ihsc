package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class InstallationApply implements Serializable {

    private Integer id;
    private Integer type;
    private String typeName;
    private Integer applyerId;
    private String applyerName;
    private String applyDate;
    private String applyerNote;
    private String adminNote;
    private Integer installId;
    private Integer productId;
    private String model;
    private String productCategoryName;
    private String manufactureNo;
    private Integer customerId;
    private Integer customerCityId;
    private String customerName;
    private String customerCity;
    private String customerProvince;
    private Integer saleContractCompId;
    private String saleContractCompName;
    private Integer installCompId;
    private String installCompName;
    private String installer;
    private String installerId;
    private Integer firstRepairCompanyId;
    private String firstRepairCompanyName;
    private Integer nowRepairCompanyId;
    private String nowRepairCompanyName;
    private String indentureNo;
    private String fobDate;
    private String installDate;
    private String openDate;
    private String acceptDate;
    private String guaranteeStartDate;
    private String guaranteeEndDate;
    private String guaranteePeriod;
    private String subCompany;
    private String installPlace;
    private Integer instPlaceTypeId;
    private String instPlaceTypeName;
    private Integer useStatusId;
    private String useStatus;
    private Integer purpose;
    private String purposeName;
    private String contact;
    private String officePhone;
    private String mobilePhone;
    private String fax;
    private String email;
    private String brmEpVer;
    private String bvEpVer;
    private String keyNo;
    private String note;
    private BigDecimal os;
    private String osName;
    private Integer platform;
    private String platformName;
    private String platformRev;
    private String mcu;
    private String bv;
    private String hcm;
    private String jpr;
    private String spr;
    private String deskey;
    private Integer affirmFlag;
    private Integer affirmantId;
    private String affirmTime;
    private String createTime;
    private Integer creatorId;
    private String modifyTime;
    private Integer modifierId;
    private Integer exclusiveKey;
    //2009/08/20 add
    private BigDecimal osPermitId;
    private String osPermitName;
    
    //2010/02/05 add
    private String bhclEpVer;
    private String trclEpVer;
    
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
     * @return the type
     */
    public Integer getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }
    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    /**
     * @return the applyerId
     */
    public Integer getApplyerId() {
        return applyerId;
    }
    /**
     * @param applyerId the applyerId to set
     */
    public void setApplyerId(Integer applyerId) {
        this.applyerId = applyerId;
    }
    /**
     * @return the applyerName
     */
    public String getApplyerName() {
        return applyerName;
    }
    /**
     * @param applyerName the applyerName to set
     */
    public void setApplyerName(String applyerName) {
        this.applyerName = applyerName;
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
     * @return the applyerNote
     */
    public String getApplyerNote() {
        return applyerNote;
    }
    /**
     * @param applyerNote the applyerNote to set
     */
    public void setApplyerNote(String applyerNote) {
        this.applyerNote = applyerNote;
    }
    /**
     * @return the adminNote
     */
    public String getAdminNote() {
        return adminNote;
    }
    /**
     * @param adminNote the adminNote to set
     */
    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }
    /**
     * @return the installId
     */
    public Integer getInstallId() {
        return installId;
    }
    /**
     * @param installId the installId to set
     */
    public void setInstallId(Integer installId) {
        this.installId = installId;
    }
    /**
     * @return the productId
     */
    public Integer getProductId() {
        return productId;
    }
    /**
     * @param productId the productId to set
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
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
     * @return the customerCityId
     */
    public Integer getCustomerCityId() {
        return customerCityId;
    }
    /**
     * @param customerCityId the customerCityId to set
     */
    public void setCustomerCityId(Integer customerCityId) {
        this.customerCityId = customerCityId;
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
     * @return the customerCity
     */
    public String getCustomerCity() {
        return customerCity;
    }
    /**
     * @param customerCity the customerCity to set
     */
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }
    /**
     * @return the customerProvince
     */
    public String getCustomerProvince() {
        return customerProvince;
    }
    /**
     * @param customerProvince the customerProvince to set
     */
    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }
    /**
     * @return the saleContractCompId
     */
    public Integer getSaleContractCompId() {
        return saleContractCompId;
    }
    /**
     * @param saleContractCompId the saleContractCompId to set
     */
    public void setSaleContractCompId(Integer saleContractCompId) {
        this.saleContractCompId = saleContractCompId;
    }
    /**
     * @return the saleContractCompName
     */
    public String getSaleContractCompName() {
        return saleContractCompName;
    }
    /**
     * @param saleContractCompName the saleContractCompName to set
     */
    public void setSaleContractCompName(String saleContractCompName) {
        this.saleContractCompName = saleContractCompName;
    }
    /**
     * @return the installCompId
     */
    public Integer getInstallCompId() {
        return installCompId;
    }
    /**
     * @param installCompId the installCompId to set
     */
    public void setInstallCompId(Integer installCompId) {
        this.installCompId = installCompId;
    }
    /**
     * @return the installCompName
     */
    public String getInstallCompName() {
        return installCompName;
    }
    /**
     * @param installCompName the installCompName to set
     */
    public void setInstallCompName(String installCompName) {
        this.installCompName = installCompName;
    }
    /**
     * @return the installer
     */
    public String getInstaller() {
        return installer;
    }
    /**
     * @param installer the installer to set
     */
    public void setInstaller(String installer) {
        this.installer = installer;
    }
    /**
     * @return the installerId
     */
    public String getInstallerId() {
        return installerId;
    }
    /**
     * @param installerId the installerId to set
     */
    public void setInstallerId(String installerId) {
        this.installerId = installerId;
    }
    /**
     * @return the firstRepairCompanyId
     */
    public Integer getFirstRepairCompanyId() {
        return firstRepairCompanyId;
    }
    /**
     * @param firstRepairCompanyId the firstRepairCompanyId to set
     */
    public void setFirstRepairCompanyId(Integer firstRepairCompanyId) {
        this.firstRepairCompanyId = firstRepairCompanyId;
    }
    /**
     * @return the firstRepairCompanyName
     */
    public String getFirstRepairCompanyName() {
        return firstRepairCompanyName;
    }
    /**
     * @param firstRepairCompanyName the firstRepairCompanyName to set
     */
    public void setFirstRepairCompanyName(String firstRepairCompanyName) {
        this.firstRepairCompanyName = firstRepairCompanyName;
    }
    /**
     * @return the nowRepairCompanyId
     */
    public Integer getNowRepairCompanyId() {
        return nowRepairCompanyId;
    }
    /**
     * @param nowRepairCompanyId the nowRepairCompanyId to set
     */
    public void setNowRepairCompanyId(Integer nowRepairCompanyId) {
        this.nowRepairCompanyId = nowRepairCompanyId;
    }
    /**
     * @return the nowRepairCompanyName
     */
    public String getNowRepairCompanyName() {
        return nowRepairCompanyName;
    }
    /**
     * @param nowRepairCompanyName the nowRepairCompanyName to set
     */
    public void setNowRepairCompanyName(String nowRepairCompanyName) {
        this.nowRepairCompanyName = nowRepairCompanyName;
    }
    /**
     * @return the indentureNo
     */
    public String getIndentureNo() {
        return indentureNo;
    }
    /**
     * @param indentureNo the indentureNo to set
     */
    public void setIndentureNo(String indentureNo) {
        this.indentureNo = indentureNo;
    }
    /**
     * @return the fobDate
     */
    public String getFobDate() {
        return fobDate;
    }
    /**
     * @param fobDate the fobDate to set
     */
    public void setFobDate(String fobDate) {
        this.fobDate = fobDate;
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
     * @return the openDate
     */
    public String getOpenDate() {
        return openDate;
    }
    /**
     * @param openDate the openDate to set
     */
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
    /**
     * @return the acceptDate
     */
    public String getAcceptDate() {
        return acceptDate;
    }
    /**
     * @param acceptDate the acceptDate to set
     */
    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
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
     * @return the guaranteePeriod
     */
    public String getGuaranteePeriod() {
        return guaranteePeriod;
    }
    /**
     * @param guaranteePeriod the guaranteePeriod to set
     */
    public void setGuaranteePeriod(String guaranteePeriod) {
        this.guaranteePeriod = guaranteePeriod;
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
     * @return the instPlaceTypeId
     */
    public Integer getInstPlaceTypeId() {
        return instPlaceTypeId;
    }
    /**
     * @param instPlaceTypeId the instPlaceTypeId to set
     */
    public void setInstPlaceTypeId(Integer instPlaceTypeId) {
        this.instPlaceTypeId = instPlaceTypeId;
    }
    /**
     * @return the instPlaceTypeName
     */
    public String getInstPlaceTypeName() {
        return instPlaceTypeName;
    }
    /**
     * @param instPlaceTypeName the instPlaceTypeName to set
     */
    public void setInstPlaceTypeName(String instPlaceTypeName) {
        this.instPlaceTypeName = instPlaceTypeName;
    }
    /**
     * @return the useStatusId
     */
    public Integer getUseStatusId() {
        return useStatusId;
    }
    /**
     * @param useStatusId the useStatusId to set
     */
    public void setUseStatusId(Integer useStatusId) {
        this.useStatusId = useStatusId;
    }
    /**
     * @return the useStatus
     */
    public String getUseStatus() {
        return useStatus;
    }
    /**
     * @param useStatus the useStatus to set
     */
    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }
    /**
     * @return the purpose
     */
    public Integer getPurpose() {
        return purpose;
    }
    /**
     * @param purpose the purpose to set
     */
    public void setPurpose(Integer purpose) {
        this.purpose = purpose;
    }
    /**
     * @return the purposeName
     */
    public String getPurposeName() {
        return purposeName;
    }
    /**
     * @param purposeName the purposeName to set
     */
    public void setPurposeName(String purposeName) {
        this.purposeName = purposeName;
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
     * @return the keyNo
     */
    public String getKeyNo() {
        return keyNo;
    }
    /**
     * @param keyNo the keyNo to set
     */
    public void setKeyNo(String keyNo) {
        this.keyNo = keyNo;
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
     * @return the os
     */
    public BigDecimal getOs() {
        return os;
    }
    /**
     * @param os the os to set
     */
    public void setOs(BigDecimal os) {
        this.os = os;
    }
    /**
     * @return the osName
     */
    public String getOsName() {
        return osName;
    }
    /**
     * @param osName the osName to set
     */
    public void setOsName(String osName) {
        this.osName = osName;
    }
    /**
     * @return the platform
     */
    public Integer getPlatform() {
        return platform;
    }
    /**
     * @param platform the platform to set
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
    /**
     * @return the platformName
     */
    public String getPlatformName() {
        return platformName;
    }
    /**
     * @param platformName the platformName to set
     */
    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }
    /**
     * @return the platformRev
     */
    public String getPlatformRev() {
        return platformRev;
    }
    /**
     * @param platformRev the platformRev to set
     */
    public void setPlatformRev(String platformRev) {
        this.platformRev = platformRev;
    }
    /**
     * @return the mcu
     */
    public String getMcu() {
        return mcu;
    }
    /**
     * @param mcu the mcu to set
     */
    public void setMcu(String mcu) {
        this.mcu = mcu;
    }
    /**
     * @return the bv
     */
    public String getBv() {
        return bv;
    }
    /**
     * @param bv the bv to set
     */
    public void setBv(String bv) {
        this.bv = bv;
    }
    /**
     * @return the hcm
     */
    public String getHcm() {
        return hcm;
    }
    /**
     * @param hcm the hcm to set
     */
    public void setHcm(String hcm) {
        this.hcm = hcm;
    }
    /**
     * @return the jpr
     */
    public String getJpr() {
        return jpr;
    }
    /**
     * @param jpr the jpr to set
     */
    public void setJpr(String jpr) {
        this.jpr = jpr;
    }
    /**
     * @return the spr
     */
    public String getSpr() {
        return spr;
    }
    /**
     * @param spr the spr to set
     */
    public void setSpr(String spr) {
        this.spr = spr;
    }
    /**
     * @return the deskey
     */
    public String getDeskey() {
        return deskey;
    }
    /**
     * @param deskey the deskey to set
     */
    public void setDeskey(String deskey) {
        this.deskey = deskey;
    }
    /**
     * @return the contact
     */
    public String getContact() {
        return contact;
    }
    /**
     * @param contact the contact to set
     */
    public void setContact(String contact) {
        this.contact = contact;
    }
    /**
     * @return the officePhone
     */
    public String getOfficePhone() {
        return officePhone;
    }
    /**
     * @param officePhone the officePhone to set
     */
    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }
    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }
    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }
    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @return the osPermitId
     */
    public BigDecimal getOsPermitId() {
        return osPermitId;
    }
    /**
     * @param osPermitId the osPermitId to set
     */
    public void setOsPermitId(BigDecimal osPermitId) {
        this.osPermitId = osPermitId;
    }
    /**
     * @return the osPermitName
     */
    public String getOsPermitName() {
        return osPermitName;
    }
    /**
     * @param osPermitName the osPermitName to set
     */
    public void setOsPermitName(String osPermitName) {
        this.osPermitName = osPermitName;
    }
    /**
     * @return the bhclEpVer
     */
    public String getBhclEpVer() {
        return bhclEpVer;
    }
    /**
     * @param bhclEpVer the bhclEpVer to set
     */
    public void setBhclEpVer(String bhclEpVer) {
        this.bhclEpVer = bhclEpVer;
    }
    /**
     * @return the trclEpVer
     */
    public String getTrclEpVer() {
        return trclEpVer;
    }
    /**
     * @param trclEpVer the trclEpVer to set
     */
    public void setTrclEpVer(String trclEpVer) {
        this.trclEpVer = trclEpVer;
    }
    
    
}
