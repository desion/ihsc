//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class AgentCustomer implements Serializable {
    private Integer agentId;
    private Integer customerId;
    private Integer cateFlag;

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
    
    
}
