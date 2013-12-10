package cn.com.bhh.erp.entity;

import java.math.BigDecimal;

public class AgentProduct {

    private Integer agentId;
    private Integer productCategoryId;
    private String startDate;
    
    private Integer pCId;
    
    /** product category name */
    private String pCName;

    /** product category description */
    private String pCDescription;

    /** product category level */
    private Integer pCCateLevel;

    /** product category parent ID */
    private BigDecimal pCParentId;
    
    public Integer getAgentId() {
        return agentId;
    }
    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }
    public Integer getProductCategoryId() {
        return productCategoryId;
    }
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public Integer getPCId() {
        return pCId;
    }
    public void setPCId(Integer id) {
        pCId = id;
    }
    public String getPCName() {
        return pCName;
    }
    public void setPCName(String name) {
        pCName = name;
    }
    public String getPCDescription() {
        return pCDescription;
    }
    public void setPCDescription(String description) {
        pCDescription = description;
    }
    public Integer getPCCateLevel() {
        return pCCateLevel;
    }
    public void setPCCateLevel(Integer cateLevel) {
        pCCateLevel = cateLevel;
    }
    public BigDecimal getPCParentId() {
        return pCParentId;
    }
    public void setPCParentId(BigDecimal parentId) {
        pCParentId = parentId;
    }
}
