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
 * sale company
 * @author sunyx
 *
 */
@SuppressWarnings("serial")
public class SaleCompanyAction extends BaseAction{
    private List<Company> comDropList = new ArrayList<Company>();
    private Integer selectedComId;
    private Integer agentComId;
    
   
    public SaleCompanyAction(){
    }
    
    @SuppressWarnings("unchecked")
    public void initData(){
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        CompanyBusiness cb = new CompanyBusiness();
        comDropList = cb.getSaleCompany(loginUser,agentComId);
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
