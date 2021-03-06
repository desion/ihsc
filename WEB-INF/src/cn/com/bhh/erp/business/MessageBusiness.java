//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.MessageCompanyDao;
import cn.com.bhh.erp.dao.MessageDao;
import cn.com.bhh.erp.entity.Message;
import cn.com.bhh.erp.entity.MessageCompany;
import cn.com.bhh.erp.entity.User;

/**
 * inlcude the base business of deal with the message.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class MessageBusiness extends BaseBusiness {

    /**
     * create a message,and add the message company relation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     * @param messageCompanyList
     */
    public void createMessage(Message message, List<Integer> messageCompanyList) {
        try {
            init();

            // Start UOC

            MessageDao messageDao = new MessageDao(conn);

            Integer messageId = new GetPK(conn).getPK("MESSAGE_TBL_ID_SEQ");

            message.setId(messageId);
            message.setCreateTime(TimeUtil.getNow());
            message.setModifyTime(TimeUtil.getNow());
            // create the message
            messageDao.createMessage(message);

            // create the company message relation
            MessageCompanyDao messageCompanyDao = new MessageCompanyDao(conn);
            CompanyDao companyDao = new CompanyDao(conn);
            for (Integer companyId : messageCompanyList) {
                if (companyDao.getCountByCompanyId(companyId) == 0) {
                    errors.add("BSE01014");
                    return;
                }
                MessageCompany messageCompany = new MessageCompany();
                messageCompany.setMessageId(messageId);
                messageCompany.setCompanyId(companyId);
                messageCompanyDao.createMessageCompany(messageCompany);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     */
    public void deleteMessage(Message message) {
        try {
            init();

            // Start UOC

            MessageCompanyDao messageCompanyDao = new MessageCompanyDao(conn);
            messageCompanyDao.deleteMessageGroupByMessageId(message.getId());

            MessageDao messageDao = new MessageDao(conn);
            messageDao.deleteMessage(message);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the message,and modify the message company relation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     * @param messageCompanyList
     */
    public void modifyMessage(Message message, List<Integer> messageCompanyList) {
        try {
            init();

            // Start UOC

            MessageDao messageDao = new MessageDao(conn);
            message.setModifyTime(TimeUtil.getNow());
            messageDao.modifyMessage(message);

            Integer messageId = message.getId();

            MessageCompanyDao messageCompanyDao = new MessageCompanyDao(conn);
            messageCompanyDao.deleteMessageGroupByMessageId(messageId);
            CompanyDao companyDao = new CompanyDao(conn);
            for (Integer companyId : messageCompanyList) {
                if (companyDao.getCountByCompanyId(companyId) == 0) {
                    errors.add("BSE01014");
                    return;
                }
                MessageCompany messageCompany = new MessageCompany();
                messageCompany.setMessageId(messageId);
                messageCompany.setCompanyId(companyId);
                messageCompanyDao.createMessageCompany(messageCompany);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the message list.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List<Message>
     */
    public List<Message> searchAllMessage(User loginUser) {
        List<Message> messagesOut = new ArrayList<Message>();

        try {
            init();

            // Start UOC

            MessageDao messageDao = new MessageDao(conn);
            messagesOut = messageDao.searchAllMessage(loginUser);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return messagesOut;
    }

    /**
     * get the scroll message for company.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param companyId
     * @return List<Message>
     */
    public List<Message> searchAllMessageByCompanyId(Integer companyId) {
        List<Message> messagesOut = new ArrayList<Message>();

        try {
            init();

            // Start UOC

            MessageDao messageDao = new MessageDao(conn);
            messagesOut = messageDao.searchMessageByCompanyId(companyId);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return messagesOut;
    }

    /**
     * get a message by message id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param message
     * @return Message
     */
    public Message getMessageById(Message message) {
        Message messageOut = null;

        try {
            init();

            // Start UOC

            MessageDao messageDao = new MessageDao(conn);
            messageOut = messageDao.searchMessageById(message);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return messageOut;
    }
}
