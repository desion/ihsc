//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.DocumentAgentDao;
import cn.com.bhh.erp.entity.DocumentAgent;

/**
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class DocumentAgentBusiness extends BaseBusiness {
   
    /**
     * get the company list by document id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @return  List<DocumentAgent>
     * @throws  Exception
     */
    public List<DocumentAgent> getCompanyByDocumentId(Integer documentId) {
        List<DocumentAgent> documentAgentOutList = new ArrayList<DocumentAgent>();

        try {
            init();

            // Start UOC

            DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
            documentAgentOutList = documentAgentDao.searchCompanyByDocumentId(documentId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return documentAgentOutList;
    }
    
    
    /**
     * get the document list that the company
     * has permission.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   companyId
     * @return  List<Integer>
     */
    public List<Integer> getDocListById(Integer companyId) {
        List<Integer> docIdList = new ArrayList<Integer>();

        try {
            init();

            // Start UOC

            DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
            docIdList = documentAgentDao.searchDocumentByCompanyId(companyId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return docIdList;
    }
    
}
