//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/08
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable{
    private Integer id;
    private String content;
    private String createTime;
    private Integer creatorId;
    private String modifyTime;
    private Integer modifierId;
    private Integer exclusiveKey;
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
     * @return the content
     */
    public String getContent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
