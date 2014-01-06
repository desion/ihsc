//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.business.DocumentAgentBusiness;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.DocumentAgent;

@SuppressWarnings("serial")
public class DocumentAgentAction extends BaseAction {
    private List<Company> companyList = new ArrayList<Company>();
    private List<DocumentAgent> selectedCompanyList = new ArrayList<DocumentAgent>();
    private Integer documentId;

    public DocumentAgentAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * prepare the data
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  void
     */
    public void initData() {
        CompanyBusiness companyBussiness = new CompanyBusiness();
        DocumentAgentBusiness documentAgentBusiness = new DocumentAgentBusiness();
        companyList = companyBussiness.getCompanyFoMessage();
        if (documentId != null) {
            selectedCompanyList = documentAgentBusiness.getCompanyByDocumentId(documentId);
        }
    }

    /**
     * @return the companyList
     */
    public List<Company> getCompanyList() {
        return companyList;
    }



    /**
     * @return the selectedCompanyList
     */
    public List<DocumentAgent> getSelectedCompanyList() {
        return selectedCompanyList;
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
