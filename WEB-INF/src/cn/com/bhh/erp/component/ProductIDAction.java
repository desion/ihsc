//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
import cn.com.bhh.erp.business.ProductBusiness;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.User;

@SuppressWarnings("serial")
public class ProductIDAction extends BaseAction {
    private List<Product> productList = new ArrayList<Product>();
    private Integer selectedProductId;

    @SuppressWarnings("unchecked")
    public ProductIDAction() {
        ProductBusiness productBusiness = new ProductBusiness();
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        productList = productBusiness.getProductList(loginUser);
    }
    @Override
    public String execute() throws Exception {
    	return SUCCESS;
    }


	public List<Product> getProductList() {
        return productList;
    }


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }


    public Integer getSelectedProductId() {
        return selectedProductId;
    }


    public void setSelectedProductId(Integer selectedProductId) {
        this.selectedProductId = selectedProductId;
    }

}
