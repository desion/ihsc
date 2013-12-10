//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.CompanyBusiness;
import cn.com.bhh.erp.business.MessageCompanyBusiness;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.MessageCompany;


/**
 * this is a compononet to list all
 * the company from company_tbl,and select
 * the customer company from company_tbl
 * according to the messageId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class MessageCompanyAction extends BaseAction {
    private List<Company> companyList = new ArrayList<Company>();
    private List<MessageCompany> selectedCompanyList = new ArrayList<MessageCompany>();
    private Integer messageId;

    public MessageCompanyAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * prepare the data
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  void
     */
    public void initData() {
        CompanyBusiness companyBussiness = new CompanyBusiness();
        MessageCompanyBusiness messageCompanyBusiness = new MessageCompanyBusiness();
        companyList = companyBussiness.getCompanyFoMessage();

        if (messageId != null) {
            selectedCompanyList = messageCompanyBusiness.getCompanyByMessageId(messageId);
        }
    }

    /**
     * @return the companyList
     */
    public List<Company> getCompanyList() {
        return companyList;
    }


    /**
     * @return the selectedCompanyList
     */
    public List<MessageCompany> getSelectedCompanyList() {
        return selectedCompanyList;
    }


    /**
     * @return the messageId
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    
}
