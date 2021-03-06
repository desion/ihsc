
//****************************************
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.UserBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.User;

import com.opensymphony.xwork2.ActionContext;

/**
 * all stuff about the user, such as login, logout, change password, etc.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class UserAction extends BaseAction {
    /**
     * the key used to store the online users's session in the Applicion.
     */
    public static final String ONLINE_USERS = "online_users";

    /**
     * the key used to store the login user's instance in the session.
     */
    public static final String USER = "user";

    /**
     * receive the user inforation from the form
     */
    
  
    private String goTo="BS010_00";
    
    private User user = new User();

    /**
     * store the user lists from the DB
     */
    private List<User> userList = new ArrayList<User>();
    private List<User> onLineUserList = new ArrayList<User>();
    private String oldPassword;
    private String passwordRepeat;
    private String newPassword;

    /**
     * verify the user when login in.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String loginVerify() throws Exception {
        UserBusiness userBusiness = new UserBusiness();
        User userOut = userBusiness.loginVerify(user);
        

        if (userBusiness.hasErrors()) {
            setActionErrors(getMessageText(userBusiness.getErrors()));
            return ERROR;
        }

        userOut.setLastLoginIP(ServletActionContext.getRequest().getRemoteAddr());
        userOut.setLastLoginTime(TimeUtil.getNow());

        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();

        session.put(USER, userOut);

        Map app = context.getApplication();
        HashSet onlineUsers = (HashSet) app.get(UserAction.ONLINE_USERS);

        if (onlineUsers == null) {
            onlineUsers = new HashSet();
        }

        onlineUsers.add(session);
        app.put(UserAction.ONLINE_USERS, onlineUsers);

        userBusiness.modifyLogonInfo(userOut);

        if (userBusiness.hasErrors()) {
            setActionErrors(getMessageText(userBusiness.getErrors()));
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * direct to the login page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String login() throws Exception {
        return INPUT;
    }

    /**
     * the user logout,move the user from the application,and set current user
     * session invalidate.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String logout() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map app = context.getApplication();
        Map session = context.getSession();
        HashSet onlineUsers = (HashSet) app.get(UserAction.ONLINE_USERS);

        if (onlineUsers != null) {
            onlineUsers.remove(session);
        }
        session.remove(USER);
        return NONE;
    }

    /**
     * direct to user create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createUserPre() throws Exception {
        user = new User();
        return SUCCESS;
    }

    /**
     * the operation of create user.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createUser() throws Exception {
        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        user.setCreatorID(loginUser.getId());
        userBusiness.createUser(user);

        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * delete the user temporarily.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteUserForTemp() throws Exception {

        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        userBusiness.deleteUserForTemp(user);
        if (userBusiness.hasErrors()) {
            user.setDeleted(0);
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        // remove all the session of this user
        clearLoginUser();

        return SUCCESS;
    }

    /**
     * recover the user who is deleted temporarily.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String recoverUser() throws Exception {

        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        userBusiness.recoverUser(user);

        if (userBusiness.hasErrors()) {
            user.setDeleted(1);
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * delete the user from DB.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteUserForever() throws Exception {

        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        userBusiness.deleteUserForever(user);

        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        clearLoginUser();
        return SUCCESS;
    }

    /**
     * lock the user.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String lockUser() throws Exception {

        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        userBusiness.lockUser(user);
        if (userBusiness.hasErrors()) {
            user.setLocked(0);
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        clearLoginUser();
        return SUCCESS;
    }

    /**
     * unlock the user.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String unLockUser() throws Exception {

        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifierID(loginUser.getId());
        userBusiness.unLockUser(user);

        if (userBusiness.hasErrors()) {
            user.setLocked(1);
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the user detail page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String directToDetail() throws Exception {
        
        UserBusiness userBusiness = new UserBusiness();
        User dbUser = userBusiness.getUserById(user);
        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        user = dbUser;
        return SUCCESS;
    }

    /**
     * direct to the query page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String searchUserPre() throws Exception {
        
        return SUCCESS;
    }

    /**
     * execute the query operation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return String
     * @throws
     */
    public String searchUser() throws Exception {
        
        return SUCCESS;
    }

    /**
     * the login user change password.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String changePassword() throws Exception {
        
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.changeLoginUserPassword(loginUser, user);
        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        clearLoginUser();
        return SUCCESS;
    }

    /**
     * modify the user
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyUserInfo() throws Exception {
        
        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        user.setModifyTime(TimeUtil.getNow());
        user.setModifierID(loginUser.getId());
        userBusiness.modifyUserInfo(user);

        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * list all the users.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listUsers() throws Exception {
        
        UserBusiness cb = new UserBusiness();
        // cal the pageSize and totlePages
        setTotalCount(cb.getUserCounts(user,loginUser));
        if (cb.hasErrors()) {
            setActionMessages(getMessageText(cb.getErrors()));
            return INPUT;
        }

        userList = cb.getUsersByPage(currPage, pageSize, user,loginUser);
        if (cb.hasErrors()) {
            setActionMessages(getMessageText(cb.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * list online users.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public String listOnline() throws Exception {
        
        onLineUserList.clear();
        Map app = ActionContext.getContext().getApplication();
        HashSet onlineUsers = (HashSet) app.get(UserAction.ONLINE_USERS);

        if (onlineUsers != null) {
            Iterator it = onlineUsers.iterator();
            onLineUserList = new ArrayList<User>();

            while (it.hasNext()) {
                try {
                    Map session = (Map) it.next();
                    if (session != null) {
                        User userTmp = (User) session.get(USER);
                        if (userTmp != null) {
                            onLineUserList.add(userTmp);
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        // format the datetime
        for (User user : onLineUserList) {
            user.setLastLoginTime(DateFormat.changeDateTime(user.getLastLoginTime()));
        }

        return SUCCESS;
    }

    /**
     * direct to the user modify page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyUserPre() throws Exception {
        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        User dbUser = userBusiness.getUserById(user);
        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        user = dbUser;
        return SUCCESS;
    }

    /**
     * prepare the data for the user template create
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String templateCreateUserPre() throws Exception {
        if (null == user.getId() || null == user.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        UserBusiness userBusiness = new UserBusiness();
        User dbUser = userBusiness.getUserById(user);
        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        user = dbUser;
        user.setPassword("");
        user.setName("");

        return SUCCESS;
    }

    /**
     * direct to the login user modify info.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyLoginUserPre() throws Exception {
        UserBusiness userBusiness = new UserBusiness();
        User dbUser = userBusiness.getLoginUserById(loginUser);
        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        user = dbUser;
        return SUCCESS;
    }

    /**
     * modify the login user info.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String modifyLoginUserInfo() throws Exception {

        UserBusiness userBusiness = new UserBusiness();
        user.setModifyTime(TimeUtil.getNow());
        user.setModifierID(loginUser.getId());
        userBusiness.modifyLoginUserInfo(user);

        if (userBusiness.hasErrors()) {
            setActionMessages(getMessageText(userBusiness.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

   /**
     * direct to the change password page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return String
     * @throws Exception
     */
    public String preChangePassword() throws Exception {
        return SUCCESS;
    }

    /**
     * clear the session of login user
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param
     * @return void
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private void clearLoginUser() {
        ActionContext context = ActionContext.getContext();
        Map app = context.getApplication();
        HashSet onlineUsers = (HashSet) app.get(UserAction.ONLINE_USERS);

        if (onlineUsers != null) {
            Iterator it = onlineUsers.iterator();

            while (it.hasNext()) {
                try {
                    Map session = (Map) it.next();
                    User onLineUser = (User) session.get(UserAction.USER);

                    if (onLineUser != null && user.getId() != null) {
                        if (onLineUser.getId().compareTo(user.getId()) == 0) {
                            it.remove();
                            session.clear();
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the userList
     */
    public List<User> getUserList() {
        return userList;
    }

    /**
     * @param userList the userList to set
     */
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    /**
     * @return the onLineUserList
     */
    public List<User> getOnLineUserList() {
        return onLineUserList;
    }

    /**
     * @param onLineUserList the onLineUserList to set
     */
    public void setOnLineUserList(List<User> onLineUserList) {
        this.onLineUserList = onLineUserList;
    }

    /**
     * @return the oldPassword
     */
    public String getOldPassword() {
        return oldPassword;
    }

    /**
     * @param oldPassword the oldPassword to set
     */
    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    /**
     * @return the passwordRepeat
     */
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    /**
     * @param passwordRepeat the passwordRepeat to set
     */
    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    /**
     * @return the newPassword
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * @param newPassword the newPassword to set
     */
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * @return the goTo
     */
    public String getGoTo() {
        return goTo;
    }

    /**
     * @param goTo the goTo to set
     */
    public void setGoTo(String goTo) {
        if(goTo!=null && !"".equals(goTo.trim())){
            this.goTo = goTo;
        }
    }
    
    
    
}
