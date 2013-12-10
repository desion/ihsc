package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.action.UserAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.User;

/**
 * install company
 * @author sunyx
 *
 */
@SuppressWarnings("serial")
public class InstallCompanyAction extends BaseAction{
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    private Integer productCategoryId;

    public InstallCompanyAction(){
 
    }
    
    @SuppressWarnings("unchecked")
    public void initData(){
        CompanyBusiness cb = new CompanyBusiness();
        String subSql = "";
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        
        if(productCategoryId!=null && productCategoryId!=-1){
          subSql = subSql +  
          " AND ID IN " +
          " (" +
          "  SELECT " +
          "  AGENT_ID " +
          "  FROM AGENT_PRODUCT_TBL " +
          "  WHERE PRODUCT_CATEGORY_ID ="
             +productCategoryId+ 
            ")" ;
        }
        if(!loginUser.filter("company_mng_all_data")){
            subSql = " AND ID = " + loginUser.getCompanyID();
            comDropList = cb.getInstallCompany(subSql);
        } else {
            comDropList = cb.getInstallCompany(subSql);
        }
    }
    
    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * @return the selectedComId
     */
    public Integer getSelectedComId() {
        return selectedComId;
    }

    /**
     * @param selectedComId the selectedComId to set
     */
    public void setSelectedComId(Integer selectedComId) {
        this.selectedComId = selectedComId;
    }

    /**
     * @return the comDropList
     */
    public List<Company> getComDropList() {
        return comDropList;
    }

    /**
     * @return the productCategoryId
     */
    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * @param productCategoryId the productCategoryId to set
     */
    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }
    

}
