//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.MessageCompanyDao;
import cn.com.bhh.erp.entity.MessageCompany;

/**
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class MessageCompanyBusiness extends BaseBusiness {
    /**
     * get the company list with the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param messageId
     * @return List&ltMessageCompany&gt
     */
    public List<MessageCompany> getCompanyByMessageId(Integer messageId) {
        List<MessageCompany> messageCompanysOut = new ArrayList<MessageCompany>();

        try {
            init();

            // Start UOC

            MessageCompanyDao messageCompanyDao = new MessageCompanyDao(conn);
            messageCompanysOut = messageCompanyDao.searchCompanyByMessageId(messageId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return messageCompanysOut;
    }

}
