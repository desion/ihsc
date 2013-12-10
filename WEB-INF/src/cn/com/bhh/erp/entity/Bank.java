//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/08/20
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;

/**
 * operation type info.
 * @author xiangzq
 * @version 2.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class Bank implements Serializable {
    private Integer id;
    private String name;
    private String shortName;
    private Integer cateFlag;
    private String createTime;
    private Integer creatorId;
    private String modifyTime;
    private Integer modifierId;
    private Integer exclusiveKey;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * @return the cateFlag
     */
    public Integer getCateFlag() {
        return cateFlag;
    }

    /**
     * @param cateFlag the cateFlag to set
     */
    public void setCateFlag(Integer cateFlag) {
        this.cateFlag = cateFlag;
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
    
    
}
