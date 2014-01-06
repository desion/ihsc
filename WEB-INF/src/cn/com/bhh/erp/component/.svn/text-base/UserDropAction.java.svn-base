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
import cn.com.bhh.erp.business.UserBusiness;
import cn.com.bhh.erp.entity.User;

@SuppressWarnings("serial")
public class UserDropAction extends BaseAction {
    private List<User> userList;
    private Integer selectedUser;
    private String selectedUserName;


    @SuppressWarnings("unchecked")
    public UserDropAction() {
        if (loginUser == null) {
            ActionContext context = ActionContext.getContext();
            Map session = context.getSession();
            loginUser = (User) session.get(UserAction.USER);
        }
        userList = new ArrayList<User>();
        UserBusiness userBusiness = new UserBusiness();
        userList = userBusiness.getUserList(loginUser);
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
     * @return the selectedUser
     */
    public Integer getSelectedUser() {
        return selectedUser;
    }


    /**
     * @param selectedUser the selectedUser to set
     */
    public void setSelectedUser(Integer selectedUser) {
        this.selectedUser = selectedUser;
    }


    /**
     * @return the selectedUserName
     */
    public String getSelectedUserName() {
        return selectedUserName;
    }


    /**
     * @param selectedUserName the selectedUserName to set
     */
    public void setSelectedUserName(String selectedUserName) {
        this.selectedUserName = selectedUserName;
    }

}
