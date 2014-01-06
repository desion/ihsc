//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.DocumentAgentDao;
import cn.com.bhh.erp.dao.DocumentDao;
import cn.com.bhh.erp.dao.DocumentProductCategoryDao;
import cn.com.bhh.erp.dao.DocumentProductDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.ProductCategoryDao;
import cn.com.bhh.erp.dao.ProductDao;
import cn.com.bhh.erp.entity.Document;
import cn.com.bhh.erp.entity.DocumentAgent;
import cn.com.bhh.erp.entity.DocumentProduct;
import cn.com.bhh.erp.entity.DocumentProductCategory;
import cn.com.bhh.erp.entity.User;



public class DocumentBusiness extends BaseBusiness {
   
    /**
     * create a document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   document
     * @param   file
     * @param   agentDocProCateList
     * @param   agentDocProductList
     * @param   docCompanyList
     */
    public void createProductDocument(Document document,File file,
                                     List<Integer> agentDocProCateList,
                                     List<Integer> agentDocProductList,
                                     List<Integer> docCompanyList) {
        try {
            init();

            // Start UOC
 
            DocumentDao documentDao = new DocumentDao(conn);
            InputStream inputStream = new FileInputStream(file);
            Integer documentId = new GetPK(conn).getPK("DOC_TBL_ID_SEQ");
            document.setId(documentId);
            //create the document.
            documentDao.createDocument(document, inputStream);

            //create the document product category relation.
            DocumentProductCategoryDao documentProductCategoryDao = new DocumentProductCategoryDao(conn);
            ProductCategoryDao productCategoryDao = new ProductCategoryDao(conn);
            for(Integer productCategoryId:agentDocProCateList){
                DocumentProductCategory documentProductCategory = new DocumentProductCategory();
                //check whether the product category is deleted
                if(productCategoryDao.getCountByProductCategoryId(productCategoryId) == 0){
                    errors.add("BSE02307");
                    return;
                }
                documentProductCategory.setDocumentId(documentId);
                documentProductCategory.setProductCategoryId(productCategoryId);
                documentProductCategoryDao.createDocumentProductCategory(documentProductCategory);
            }
            
            //create the document product relation.
            DocumentProductDao documentProductDao = new DocumentProductDao(conn);
            ProductDao productDao = new ProductDao(conn);
            for(Integer productId:agentDocProductList){
                //check whether the product is deleted.
                if(productDao.getCountById(productId) == 0){
                     errors.add("BSE02308");
                     return;
                }
                DocumentProduct documentProduct = new DocumentProduct();
                documentProduct.setDocumentId(documentId);
                documentProduct.setProductId(productId);
                documentProductDao.createProductDocument(documentProduct);
            }
            
            //create the document company relation.
            DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            for(Integer companyId:docCompanyList){
                //check whether the company is deleted.
                if(companyDao.getCountByCompanyId(companyId) == 0){
                     errors.add("BSE02305");
                     return;
                }
                DocumentAgent documentAgent = new DocumentAgent();
                documentAgent.setDocumentId(documentId);
                documentAgent.setCompanyId(companyId);
                documentAgentDao.createDocumentAgent(documentAgent);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

  
    
    /**
     * get all the documents.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Document>
     */
    public List<Document> getDocumentList(Document document,User loginUser) {
        
        List<Document> productDocumentList = new ArrayList<Document>();
        
        try {
            
            init();

            // Start UOC
            
            DocumentDao documentDao = new DocumentDao(conn);
            String subSql = "";
            if (!loginUser.filter("doc_mng_all_data")) {
                subSql = " AND A.ID IN ( SELECT DOC_ID FROM DOC_AGENT_TBL WHERE COMPANY_ID = "
                        + loginUser.getCompanyID() + " ) " ;
            } 
            
            if(document != null){
                productDocumentList = documentDao.getDocumentList(document,subSql);
            }else{
                productDocumentList = documentDao.getDocumentList(subSql);
            }              

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return productDocumentList;
    }
    
    
  /**
   * get the document binary data.  
   * @auther  xiangzq
   * @version 1.0
   * @since   1.0
   * @param   doc
   * @return  Document
   */
  public  Document  searchProductDocumentById(Document doc) {
        
       Document document = new Document();
        
        try {
            
            init();

            // Start UOC
            
            DocumentDao documentDao = new DocumentDao(conn);
            document = documentDao.searchDocumentDataById(doc);
            if(document == null ){
                errors.add("BSE02304");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        
        return document;
    }
  
 

  /**
   * delete a document of the product. 
   * @auther  xiangzq
   * @version 1.0
   * @since   1.0
   * @param   document
   * @return  void
   */
  public  void  deleteProductDocumentByDocId(Document document) {
      
       
       try {
           
           init();

           // Start UOC
           
           Integer documentId = document.getId();
           DocumentDao documentDao = new DocumentDao(conn);
           if(documentId == null || documentDao.getCountByDocId(documentId) == 0 ){
               errors.add("BSE02304");
           }else{
               
               //delete document product category relation.
               DocumentProductCategoryDao documentProductCategoryDao = new DocumentProductCategoryDao(conn);
               documentProductCategoryDao.deleteDocumentProductCategoryByDocId(documentId);
               
               //delete the document product relation.
               DocumentProductDao documentProductDao = new DocumentProductDao(conn);
               documentProductDao.deleteProductDocumentByDocId(documentId);
               
               //delete the document company relation.
               DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
               documentAgentDao.deleteAgentByDocumentId(documentId);
               
               documentDao.deleteDocumentById(documentId);
           }

           // End UOC

       } catch (Exception e) {
           handleException(e);
       } finally {
           finish();
       }
       
   }

  
//  /**
//   * update the document permission.
//   * @auther  xiangzq
//   * @version 1.0
//   * @since   1.0
//   * @param   companyIdList
//   * @param   document
//   * @return  void
//   */
//  public  void  updateDocumentPermission(List<Integer> companyIdList,Document document) {
//      
//       try {
//           
//           init();
//
//           // Start UOC
//           
//            Integer documentId = document.getId();
//            DocumentDao documentDao = new DocumentDao(conn);
//            if(documentDao.getCountByDocId(documentId) == 0){
//                errors.add("BSE02304");
//                return;
//            }
//            DocumentAgentDao documentAgentDao = new DocumentAgentDao(conn);
//            documentAgentDao.deleteAgentByDocumentId(documentId);
//            
//            CompanyDao companyDao = new CompanyDao(conn);
//            
//            for(Integer companyId:companyIdList){
//                if(companyDao.getCountByCompanyId(companyId) == 0){
//                    errors.add("BSE02305");
//                    return;
//                }
//                DocumentAgent documentAgent = new DocumentAgent();
//                documentAgent.setDocumentId(documentId);
//                documentAgent.setCompanyId(companyId);
//                documentAgentDao.createDocumentAgent(documentAgent);
//            }
//         
//
//           // End UOC
//
//       } catch (Exception e) {
//           handleException(e);
//       } finally {
//           finish();
//       }
//       
//   }
  
  /**
   * get the document agent product 
   * list by the document id.
   * @auther  xiangzq
   * @version 1.0
   * @since   1.0
   * @param   documentId
   * @return  List<Integer>
   */
  public List<Integer> getDocumentAgentProductList(Integer documentId){
      List<Integer> documentAgentProductList = new ArrayList<Integer>();
      
      try {
          init();

          // Start UOC
           
            DocumentProductDao documentProductDao = new DocumentProductDao(conn);
            documentAgentProductList = documentProductDao.getAgentProductListByDocumentId(documentId);

          // End UOC

      } catch (Exception e) {
          handleException(e);
      } finally {
          finish();
      }
      
      return documentAgentProductList;
  }
  
  
  /**
   * get the document agent product
   * category list by the document id.
   * @auther  xiangzq
   * @version 1.0
   * @since   1.0
   * @param   documentId
   * @return  List<Integer>
   */
  public List<Integer> getDocumentAgentProductCategoryList(Integer documentId){
      List<Integer> documentAgentProductCategoryList = new ArrayList<Integer>();
      
      try {
          init();

          // Start UOC
           
            DocumentProductCategoryDao documentProductCategoryDao = new DocumentProductCategoryDao(conn);
            documentAgentProductCategoryList = documentProductCategoryDao.getAgentProductCategoryListByDocumentId(documentId);

          // End UOC

      } catch (Exception e) {
          handleException(e);
      } finally {
          finish();
      }
      
      return documentAgentProductCategoryList;
  }
  
  
  /**
   * get the document by id.
   * @auther  xiangzq
   * @version 1.0
   * @since   1.0
   * @param   documentId
   * @return  Document
   */
  public Document getDocumentById(Integer documentId){
      Document document = null;
      
      try {
          init();

          // Start UOC
           
           DocumentDao documentDao = new DocumentDao(conn);
           document = documentDao.getDocumetById(documentId);
           if(document == null){
               errors.add("BSE02304");
           }

          // End UOC

      } catch (Exception e) {
          handleException(e);
      } finally {
          finish();
      }
      
      return document;
      
  }
     
}
