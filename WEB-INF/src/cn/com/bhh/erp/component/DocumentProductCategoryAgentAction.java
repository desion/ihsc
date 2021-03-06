//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/22
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
//import cn.com.bhh.erp.business.DocumentBusiness;
import cn.com.bhh.erp.business.ProductBusiness;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;


/**
 * list the company product category proxy tree,
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class DocumentProductCategoryAgentAction extends BaseAction {
    private List<ProductCategory> levelOneProductCateList = new ArrayList<ProductCategory>();
   
    private List<Integer> documentAgentProductCateList;
    private List<Integer> documentAgentProductList;
    private Integer documentId;
    public DocumentProductCategoryAgentAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    public void initData(){
        
        if(documentId!=null){
            
//            DocumentBusiness documentBusiness = new DocumentBusiness();
//            
//            //  获取该文档对应的产品分类关系
//            documentAgentProductCateList = documentBusiness.getDocumentAgentProductCategoryList(documentId);
//            
//            // 获取该文档对应的产品关系
//            documentAgentProductList = documentBusiness.getDocumentAgentProductList(documentId);
        }
        
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        
        
        ProductCategoryBusiness productCateBussiness = new ProductCategoryBusiness();
        ProductBusiness productBusiness = new ProductBusiness();
        
        //获取第一级产品分类
        levelOneProductCateList = productCateBussiness.searchLevelOneProductCategoryList(loginUser);
        for(ProductCategory levelOneProductCategory:levelOneProductCateList){
            List<ProductCategory> levelTwoProductCateList=new ArrayList<ProductCategory>();
            //获取第二级产品分类
            levelTwoProductCateList=productCateBussiness.searchProductCategoryListByParentId(levelOneProductCategory.getId(),loginUser);
            if(levelTwoProductCateList.size()>0){
                // 设置第一级产品分类的子产品分类
                levelOneProductCategory.setChildCategoryList(levelTwoProductCateList);
                  for(ProductCategory levelTwoProductCategory:levelTwoProductCateList){
                      
                       List<ProductCategory> levelThreeProductCateList=new ArrayList<ProductCategory>();
                       //获取第三级产品分类
                       levelThreeProductCateList=productCateBussiness.searchProductCategoryListByParentId(levelTwoProductCategory.getId(),loginUser);
                       
                       if(levelThreeProductCateList.size()>0){
                           //设置第二级产品分类的子产品分类
                           levelTwoProductCategory.setChildCategoryList(levelThreeProductCateList);
                           for(ProductCategory levelThreeProductCategory:levelThreeProductCateList){
                               List<Product> leafProductList= new ArrayList<Product>();
                               //获取第三级产品分类的产品
//                               leafProductList = productBusiness.searchProductList(levelThreeProductCategory.getId(),loginUser);
                               //设置第三级产品分类的所有产品
                               levelThreeProductCategory.setChildProductList(leafProductList);
                               
                           }
                       }
                    }
                }
          
           }
    }
    
    /**
     * @return the levelOneProductCateList
     */
    public List<ProductCategory> getLevelOneProductCateList() {
        return levelOneProductCateList;
    }

    /**
     * @return the documentAgentProductCateList
     */
    public List<Integer> getDocumentAgentProductCateList() {
        return documentAgentProductCateList;
    }

    /**
     * @param documentAgentProductCateList the documentAgentProductCateList to set
     */
    public void setDocumentAgentProductCateList(List<Integer> documentAgentProductCateList) {
        this.documentAgentProductCateList = documentAgentProductCateList;
    }

    /**
     * @return the documentAgentProductList
     */
    public List<Integer> getDocumentAgentProductList() {
        return documentAgentProductList;
    }

    /**
     * @param documentAgentProductList the documentAgentProductList to set
     */
    public void setDocumentAgentProductList(List<Integer> documentAgentProductList) {
        this.documentAgentProductList = documentAgentProductList;
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
