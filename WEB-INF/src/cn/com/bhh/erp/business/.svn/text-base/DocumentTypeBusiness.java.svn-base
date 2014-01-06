//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.DocumentTypeDao;
import cn.com.bhh.erp.entity.DocumentType;

public class DocumentTypeBusiness extends BaseBusiness{

    public List<DocumentType> getDocumentTypeList() {
        List<DocumentType> docTypeList = new ArrayList<DocumentType>();
        
        try{
            init();
            
            // Strat UOC
            
            DocumentTypeDao faultTypeDao = new DocumentTypeDao(conn);
            docTypeList = faultTypeDao.getAllDocumentType();
            
            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return docTypeList;
    }
}
