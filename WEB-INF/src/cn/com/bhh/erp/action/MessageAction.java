//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.MessageBusiness;
import cn.com.bhh.erp.entity.Message;

/**
 * this class include the functions: create,delete,modify and list message.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class MessageAction extends BaseAction {
    
    private Message message = new Message();
    private List<Message> messageList = new ArrayList<Message>();
    private List<Integer> companyIdList = new ArrayList<Integer>();
    private Integer modifyFlag=0;
    private List<Message> scrollMessageList = new ArrayList<Message>();
    
    /**
     * display the scroll message on the
     * home page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  String
     * @throws  Exception
     */
    public String displayMessage() throws Exception {
        MessageBusiness messageBusiness = new MessageBusiness();
        scrollMessageList = messageBusiness.searchAllMessageByCompanyId(loginUser.getCompanyID());
        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }
    
    
    /**
     * list the message
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listMessage() throws Exception {
        
        MessageBusiness messageBusiness = new MessageBusiness();
        messageList = messageBusiness.searchAllMessage(loginUser);   
        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the message create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createMessagePre() throws Exception {
        message = new Message();
        return SUCCESS;
    }

    /**
     * the operation of deleting message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteMessage() throws Exception {

        if (null == message.getId() || null == message.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        MessageBusiness messageBusiness = new MessageBusiness();
        messageBusiness.deleteMessage(message);

        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * direct to the modify message page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyMessagePre() throws Exception {
        
        if (null == message.getId() || null == message.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        MessageBusiness messageBusiness = new MessageBusiness();
        Message dbMessage = messageBusiness.getMessageById(message);

        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        message = dbMessage;
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * modify the message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return String
     * @throws Exception
     */
    public String modifyMessage() throws Exception {

        if (null == message.getId() || null == message.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        MessageBusiness messageBusiness = new MessageBusiness();
        message.setModifierId(loginUser.getId());
        messageBusiness.modifyMessage(message, companyIdList);

        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * the operation of creating message.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createMessage() throws Exception {
        
        MessageBusiness messageBusiness = new MessageBusiness();
        message.setModifierId(loginUser.getId());
        message.setCreatorId(loginUser.getId());
        messageBusiness.createMessage(message, companyIdList);

        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * direct to the detail page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String directToDetail() throws Exception {
        
        if (null == message.getId() || null == message.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        MessageBusiness messageBusiness = new MessageBusiness();
        Message dbMessage = messageBusiness.getMessageById(message);
        if (messageBusiness.hasErrors()) {
            setActionMessages(getMessageText(messageBusiness.getErrors()));
            return INPUT;
        }
        message = dbMessage;
        return SUCCESS;
    }

    

    /**
     * @return the message
     */
    public Message getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Message message) {
        this.message = message;
    }

    /**
     * @return the messageList
     */
    public List<Message> getMessageList() {
        return messageList;
    }

    /**
     * @param messageList the messageList to set
     */
    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    /**
     * @return the companyIdList
     */
    public List<Integer> getCompanyIdList() {
        return companyIdList;
    }

    /**
     * @param companyIdList the companyIdList to set
     */
    public void setCompanyIdList(List<Integer> companyIdList) {
        this.companyIdList = companyIdList;
    }

    /**
     * @return the modifyFlag
     */
    public Integer getModifyFlag() {
        return modifyFlag;
    }

    /**
     * @param modifyFlag the modifyFlag to set
     */
    public void setModifyFlag(Integer modifyFlag) {
        this.modifyFlag = modifyFlag;
    }


    /**
     * @return the scrollMessageList
     */
    public List<Message> getScrollMessageList() {
        return scrollMessageList;
    }


    /**
     * @param scrollMessageList the scrollMessageList to set
     */
    public void setScrollMessageList(List<Message> scrollMessageList) {
        this.scrollMessageList = scrollMessageList;
    }

   

   
}
