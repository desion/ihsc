//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class DocumentAgent implements Serializable {
    private Integer companyId;
    private Integer documentId;
    
    /**
     * @return the companyId
     */
    public Integer getCompanyId() {
        return companyId;
    }
    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    /**
     * @return the documentId
     */
    public Integer getDocumentId() {
        return documentId;
    }
    /**
     * @param documentId the documentId to set
     */
    public void setDocumentId(Integer documentId) {
        this.documentId = documentId;
    }

   
}
