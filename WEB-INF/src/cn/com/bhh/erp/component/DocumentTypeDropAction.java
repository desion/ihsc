//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.DocumentTypeBusiness;
import cn.com.bhh.erp.entity.DocumentType;


/**
 * this is a component to list all
 * the document type  from document_type_tbl,and select
 * the relative item according to the
 * selected document id
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class DocumentTypeDropAction extends BaseAction {
    private List<DocumentType> docTypeDropList = new ArrayList<DocumentType>();
    private Integer selectedDocumentTypeId;

    public DocumentTypeDropAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * prepare the data for the city drop list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     */
    public void initData() {
        DocumentTypeBusiness documentTypeBusiness = new DocumentTypeBusiness();
        docTypeDropList = documentTypeBusiness.getDocumentTypeList();
    }

    /**
     * @return the docTypeDropList
     */
    public List<DocumentType> getDocTypeDropList() {
        return docTypeDropList;
    }

    /**
     * @param docTypeDropList the docTypeDropList to set
     */
    public void setDocTypeDropList(List<DocumentType> docTypeDropList) {
        this.docTypeDropList = docTypeDropList;
    }

    /**
     * @return the selectedDocumentTypeId
     */
    public Integer getSelectedDocumentTypeId() {
        return selectedDocumentTypeId;
    }

    /**
     * @param selectedDocumentTypeId the selectedDocumentTypeId to set
     */
    public void setSelectedDocumentTypeId(Integer selectedDocumentTypeId) {
        this.selectedDocumentTypeId = selectedDocumentTypeId;
    }

   
}
