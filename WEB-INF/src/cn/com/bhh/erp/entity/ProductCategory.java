//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


/**
 * Class of Product Category Structure
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class ProductCategory implements Serializable {
    /** product category ID */
    private Integer id;

    /** product category name */
    private String name;

    /** product category description */
    private String description;

    /** product category level */
    private Integer cateLevel;

    /** product category parent ID */
    private BigDecimal parentId;

    /** product category deleted flag */
    private Integer deleted;

    /** product category create time */
    private String createTime;

    /** product category creator ID */
    private Integer creatorId;

    /** product category modify time */
    private String modifyTime;

    /** product category modifier ID */
    private Integer modifierId;

    /** product category exclusive key */
    private Integer exclusiveKey;
    
    private List<ProductCategory> childCategoryList=null;
    private List<Product> childProductList = null;

    /**
     * @return the product category ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the product category ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the product category name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the product category name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the product category description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the product category description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the product category level
     */
    public Integer getCateLevel() {
        return cateLevel;
    }

    /**
     * @param cateLevel the product category level to set
     */
    public void setCateLevel(Integer cateLevel) {
        this.cateLevel = cateLevel;
    }

    /**
     * @return the product category parent ID
     */
    public BigDecimal getParentId() {
        return parentId;
    }

    /**
     * @param parentId the product category parent ID to set
     */
    public void setParentId(BigDecimal parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the product category deleted flag
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * @param deleted the product category deleted flag to set
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the product category create time
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the product category create time to set
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the product category creator ID
     */
    public Integer getCreatorId() {
        return creatorId;
    }

    /**
     * @param creatorId the product category creator ID to set
     */
    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * @return the product category modify time
     */
    public String getModifyTime() {
        return modifyTime;
    }

    /**
     * @param modifyTime the product category modify time to set
     */
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * @return the product category modifier ID
     */
    public Integer getModifierId() {
        return modifierId;
    }

    /**
     * @param modifierId the product category modifier ID to set
     */
    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * @return the product category exclusive key
     */
    public Integer getExclusiveKey() {
        return exclusiveKey;
    }

    /**
     * @param exclusiveKey the product category exclusive key to set
     */
    public void setExclusiveKey(Integer exclusiveKey) {
        this.exclusiveKey = exclusiveKey;
    }

    /**
     * @return the childCategoryList
     */
    public List<ProductCategory> getChildCategoryList() {
        return childCategoryList;
    }

    /**
     * @param childCategoryList the childCategoryList to set
     */
    public void setChildCategoryList(List<ProductCategory> childCategoryList) {
        this.childCategoryList = childCategoryList;
    }

    /**
     * @return the childProductList
     */
    public List<Product> getChildProductList() {
        return childProductList;
    }

    /**
     * @param childProductList the childProductList to set
     */
    public void setChildProductList(List<Product> childProductList) {
        this.childProductList = childProductList;
    }
    
    
}
