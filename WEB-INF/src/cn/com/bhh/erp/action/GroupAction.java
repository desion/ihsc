//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.business.GroupBusiness;
import cn.com.bhh.erp.entity.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * this class include the functions: create,delete,modify and list group.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class GroupAction extends BaseAction {
    private Group group = new Group();
    private List<Group> groupList = new ArrayList<Group>();
    private List<Integer> accessActionList = new ArrayList<Integer>();
    private List<Integer> filterActionList = new ArrayList<Integer>();
    private Integer modifyFlag=0;

    /**
     * list the group
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String listGroup() throws Exception {
        GroupBusiness gb = new GroupBusiness();
        groupList = gb.getAllGroup();

        if (gb.hasErrors()) {
            setActionMessages(getMessageText(gb.getErrors()));

            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * direct to the group create page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createGroupPre() throws Exception {
        
        group = new Group();
        return SUCCESS;
    }

    /**
     * the operation of deleting group.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String deleteGroup() throws Exception {

        if (null == group.getId() || null == group.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        GroupBusiness gb = new GroupBusiness();
        gb.deleteGroup(group);

        if (gb.hasErrors()) {
            setActionMessages(getMessageText(gb.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * direct to the modify group page.
     * 
     * @author xiangzq08
     *@return String
     */
    public String modifyGroupPre() throws Exception {
        
        if (null == group.getId() || null == group.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        GroupBusiness gb = new GroupBusiness();
        group = gb.getSingleGroup(group);

        if (gb.hasErrors()) {
            setActionMessages(getMessageText(gb.getErrors()));
            return INPUT;
        }
        return SUCCESS;
    }

    /**
     * the operation of modifying group.
     * 
     * @author xiangzq08
     *@return String
     */
    public String modifyGroup() throws Exception {

        if (null == group.getId() || null == group.getExclusiveKey()) {
            return ILLEGAL_ERR;
        }

        GroupBusiness gb = new GroupBusiness();
        group.setModifierId(loginUser.getId());
        gb.modifyGroupInfo(group, accessActionList, filterActionList);

        if (gb.hasErrors()) {
            setActionMessages(getMessageText(gb.getErrors()));
            modifyFlag=1;
            return INPUT;
        }
        modifyFlag=0;
        return SUCCESS;
    }

    /**
     * the operation of creating group.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createGroup() throws Exception {
        
        GroupBusiness gb = new GroupBusiness();
        group.setModifierId(loginUser.getId());
        group.setCreatorId(loginUser.getId());
        gb.createGroup(group, accessActionList, filterActionList);

        if (gb.hasErrors()) {
            setActionMessages(getMessageText(gb.getErrors()));
            return INPUT;
        }

        return SUCCESS;
    }

    /**
     * @return the group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * @param group the group to set
     */
    public void setGroup(Group group) {
        this.group = group;
    }

    /**
     * @return the groupList
     */
    public List<Group> getGroupList() {
        return groupList;
    }

    /**
     * @param groupList the groupList to set
     */
    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    /**
     * @return the accessActionList
     */
    public List<Integer> getAccessActionList() {
        return accessActionList;
    }

    /**
     * @param accessActionList the accessActionList to set
     */
    public void setAccessActionList(List<Integer> accessActionList) {
        this.accessActionList = accessActionList;
    }

    /**
     * @return the filterActionList
     */
    public List<Integer> getFilterActionList() {
        return filterActionList;
    }

    /**
     * @param filterActionList the filterActionList to set
     */
    public void setFilterActionList(List<Integer> filterActionList) {
        this.filterActionList = filterActionList;
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

   
}
