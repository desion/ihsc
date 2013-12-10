//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.dao.AgentCustomerDao;
import cn.com.bhh.erp.entity.AgentCustomer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class AgentCustomerBusiness extends BaseBusiness {
    /**
     * get the customers of the agent company
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param agentComId
     * @param cateFlag 0:银行 1：销售商
     * @return List&ltAgentCustomer&gt
     */
    public List<AgentCustomer> getCustomerByAgent(Integer agentComId,Integer cateFlag) {
        List<AgentCustomer> agentCustomerOut = new ArrayList<AgentCustomer>();

        try {
            init();

            // Start UOC

            AgentCustomerDao acDao = new AgentCustomerDao(conn);
            agentCustomerOut = acDao.searchCustomerByAgent(agentComId,cateFlag);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return agentCustomerOut;
    }

}
