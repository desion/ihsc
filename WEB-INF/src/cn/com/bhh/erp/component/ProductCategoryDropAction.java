//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
import cn.com.bhh.erp.business.ProductCategoryBusiness;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class ProductCategoryDropAction extends BaseAction {
    private List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
    private Integer selectedProductCategoryId;
    private Integer cateLevel;

    public ProductCategoryDropAction() {
        initData();
    }
    @Override
    public String execute() throws Exception {
        initData();
    	return SUCCESS;
    }
    
    @SuppressWarnings("unchecked")
    public void initData(){
        ProductCategoryBusiness productCategoryBusiness = new ProductCategoryBusiness();
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        if(cateLevel != null){
            productCategoryList = productCategoryBusiness.getProductCategoryThreeList(loginUser);
        }else{
            productCategoryList = productCategoryBusiness.getProductCategoryList(loginUser);
        }
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
     * @return the selectedProductCategoryId
     */
    public Integer getSelectedProductCategoryId() {
        return selectedProductCategoryId;
    }
    /**
     * @param selectedProductCategoryId the selectedProductCategoryId to set
     */
    public void setSelectedProductCategoryId(Integer selectedProductCategoryId) {
        this.selectedProductCategoryId = selectedProductCategoryId;
    }
    
    /**
     * @return the cateLevel
     */
    public Integer getCateLevel() {
        return cateLevel;
    }
    /**
     * @param cateLevel the cateLevel to set
     */
    public void setCateLevel(Integer cateLevel) {
        this.cateLevel = cateLevel;
    }

   
}
