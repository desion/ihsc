//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/22
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.ProductCategory;


/**
 * list the company product category proxy tree,
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class CompanyProductCategoryAgentAction extends BaseAction {
    private List<ProductCategory> levelOneProductCateList = new ArrayList<ProductCategory>();
    private List<Integer> companyAgentProductCateList;
    private Integer agentComId;
    public CompanyProductCategoryAgentAction() {
        createTree();
    }

    @Override
    public String execute() throws Exception {
        createTree();
        return SUCCESS;
    }

    public void createTree(){
        
        if(agentComId!=null){
            CompanyBusiness companyBussiness=new CompanyBusiness();
            companyAgentProductCateList=companyBussiness.getCompanyAgentProductCategory(agentComId);
        }
        
        ProductCategoryBusiness productCateBussiness = new ProductCategoryBusiness();
        //get the level one product category
        levelOneProductCateList = productCateBussiness.searchLevelOneProductCategoryList();
        for(ProductCategory levelOneProductCategory:levelOneProductCateList){
            List<ProductCategory> levelTwoProductCateList=new ArrayList<ProductCategory>();
            //get the level two product category
            levelTwoProductCateList=productCateBussiness.searchProductCategoryListByParentId(levelOneProductCategory.getId());
            if(levelTwoProductCateList.size()>0){
                // set the child category of the level one product category
                levelOneProductCategory.setChildCategoryList(levelTwoProductCateList);
                  for(ProductCategory levelTwoProductCategory:levelTwoProductCateList){
                      
                       List<ProductCategory> levelThreeProductCateList=new ArrayList<ProductCategory>();
                       //get the level three product category
                       levelThreeProductCateList=productCateBussiness.searchProductCategoryListByParentId(levelTwoProductCategory.getId());
                       
                       if(levelThreeProductCateList.size()>0){
                           //set the child category of the level two product category.
                           levelTwoProductCategory.setChildCategoryList(levelThreeProductCateList);
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
     * @return the companyAgentProductCateList
     */
    public List<Integer> getCompanyAgentProductCateList() {
        return companyAgentProductCateList;
    }


    /**
     * @return the agentComId
     */
    public Integer getAgentComId() {
        return agentComId;
    }

    /**
     * @param agentComId the agentComId to set
     */
    public void setAgentComId(Integer agentComId) {
        this.agentComId = agentComId;
    }

  
    
   
}
