//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   2010/01/13
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.ProductCategory;

@SuppressWarnings("serial")
public class TopProductCategoryDropAction extends BaseAction {
    private List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
    private Integer selectedProductCategoryName;

    public TopProductCategoryDropAction() {
        initData();
    }
    @Override
    public String execute() throws Exception {
        initData();
    	return SUCCESS;
    }
    
    public void initData(){
        ProductCategoryBusiness productCategoryBusiness = new ProductCategoryBusiness();
        productCategoryList = productCategoryBusiness.getTopCategory();
    }
    /**
     * @return the productCategoryList
     */
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }
    /**
     * @param productCategoryList the productCategoryList to set
     */
    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
    /**
     * @return the selectedProductCategoryName
     */
    public Integer getSelectedProductCategoryName() {
        return selectedProductCategoryName;
    }
    /**
     * @param selectedProductCategoryName the selectedProductCategoryName to set
     */
    public void setSelectedProductCategoryName(Integer selectedProductCategoryName) {
        this.selectedProductCategoryName = selectedProductCategoryName;
    }
 
    

   
}
