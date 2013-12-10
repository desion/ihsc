//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.AgentCustomerBusiness;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.entity.AgentCustomer;
import cn.com.bhh.erp.entity.Company;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a compononet to list all
 * the company from company_tbl,and select
 * the customer company from company_tbl
 * according to the agentComId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class CompanyAgentCustomerAction extends BaseAction {
    private List<Company> customerList = new ArrayList<Company>();
    private List<AgentCustomer> selectedCustomers = new ArrayList<AgentCustomer>();
    private Integer agentComId;

    public CompanyAgentCustomerAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * prepare the data for the drop list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     */
    public void initData() {
        CompanyBusiness cb = new CompanyBusiness();
        AgentCustomerBusiness acb = new AgentCustomerBusiness();
        customerList = cb.getCompanyFoDelegate();

        if (agentComId != null) {
            selectedCustomers = acb.getCustomerByAgent(agentComId,1);
        }
    }

    /**
     * @return the customerList
     */
    public List<Company> getCustomerList() {
        return customerList;
    }


    /**
     * @return the selectedCustomers
     */
    public List<AgentCustomer> getSelectedCustomers() {
        return selectedCustomers;
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
