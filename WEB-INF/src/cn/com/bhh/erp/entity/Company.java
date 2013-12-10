//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class Company implements Serializable {
    private Integer id;
    private String code;
    private String aplyStartDate;
    private String aplyEndDate;
    private String shortName;
    private String typeId;
    private String address1;
    private String address2;
    private Integer cityId;
    private String zipCode;
    private String tel1;
    private String tel2;
    private String fax;
    private String homePage;
    private String email;
    private String createTime;
    private Integer creatorId;
    private String modifyTime;
    private Integer modifierId;
    private Integer exclusiveKey;
    private String companyTypeName;
    private String cityName;
    private Integer deleted;
    private Integer customerFlag=-1;
    private String mainCompanyName;
    private String subCompanyName;
    private String sort;
    private String sortType;
    private Integer provinceId;
    private String provinceName;
    private Integer bankId;
    private String bankName;
    private String companyNameShow;
    private String municipality;
    
    private List<Integer> comTypeList = new ArrayList<Integer>();

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
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the aplyStartDate
     */
    public String getAplyStartDate() {
        return aplyStartDate;
    }

    /**
     * @param aplyStartDate the aplyStartDate to set
     */
    public void setAplyStartDate(String aplyStartDate) {
        this.aplyStartDate = aplyStartDate;
    }

    /**
     * @return the aplyEndDate
     */
    public String getAplyEndDate() {
        return aplyEndDate;
    }

    /**
     * @param aplyEndDate the aplyEndDate to set
     */
    public void setAplyEndDate(String aplyEndDate) {
        this.aplyEndDate = aplyEndDate;
    }


    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return the address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * @return the address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * @param address2 the address2 to set
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * @return the cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * @param cityId the cityId to set
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * @return the zipCode
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode the zipCode to set
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the tel1
     */
    public String getTel1() {
        return tel1;
    }

    /**
     * @param tel1 the tel1 to set
     */
    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    /**
     * @return the tel2
     */
    public String getTel2() {
        return tel2;
    }

    /**
     * @param tel2 the tel2 to set
     */
    public void setTel2(String tel2) {
        this.tel2 = tel2;
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
     * @return the homePage
     */
    public String getHomePage() {
        return homePage;
    }

    /**
     * @param homePage the homePage to set
     */
    public void setHomePage(String homePage) {
        this.homePage = homePage;
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
     * @return the companyTypeName
     */
    public String getCompanyTypeName() {
        return companyTypeName;
    }

    /**
     * @param companyTypeName the companyTypeName to set
     */
    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName;
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
     * @return the customerFlag
     */
    public Integer getCustomerFlag() {
        return customerFlag;
    }

    /**
     * @param customerFlag the customerFlag to set
     */
    public void setCustomerFlag(Integer customerFlag) {
        this.customerFlag = customerFlag;
    }

    /**
     * @return the mainCompanyName
     */
    public String getMainCompanyName() {
        return mainCompanyName;
    }

    /**
     * @param mainCompanyName the mainCompanyName to set
     */
    public void setMainCompanyName(String mainCompanyName) {
        this.mainCompanyName = mainCompanyName;
    }

    /**
     * @return the subCompanyName
     */
    public String getSubCompanyName() {
        return subCompanyName;
    }

    /**
     * @param subCompanyName the subCompanyName to set
     */
    public void setSubCompanyName(String subCompanyName) {
        this.subCompanyName = subCompanyName;
    }

    /**
     * @return the comTypeList
     */
    public List<Integer> getComTypeList() {
        return comTypeList;
    }

    /**
     * @param comTypeList the comTypeList to set
     */
    public void setComTypeList(List<Integer> comTypeList) {
        this.comTypeList = comTypeList;
    }

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
     * @return the provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * @param provinceId the provinceId to set
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * @return the provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * @return the bankId
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * @param bankId the bankId to set
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the companyNameShow
     */
    public String getCompanyNameShow() {
        return companyNameShow;
    }

    /**
     * @param companyNameShow the companyNameShow to set
     */
    public void setCompanyNameShow(String companyNameShow) {
        this.companyNameShow = companyNameShow;
    }

    /**
     * @return the municipality
     */
    public String getMunicipality() {
        return municipality;
    }

    /**
     * @param municipality the municipality to set
     */
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    
}
