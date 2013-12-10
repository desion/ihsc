//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.ActionGroupDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.GroupDao;
import cn.com.bhh.erp.dao.UserGroupDao;
import cn.com.bhh.erp.entity.ActionGroup;
import cn.com.bhh.erp.entity.Group;
import cn.com.bhh.erp.entity.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the base business of deal with the group.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class GroupBusiness extends BaseBusiness {
    /**
     * create group.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param group
     * @param accessActionList
     * @param filterActionList
     */
    public void createGroup(Group group, List<Integer> accessActionList, List<Integer> filterActionList) {
        try {
            init();

            // Start UOC

            GroupDao groupDao = new GroupDao(conn);
            group.setName(group.getName().trim());

            if (groupDao.getCountByGroupName(group) > 0) {
                // decide whether db has the same name with the input name
                errors.add("BSE00000,group.name");
                return;
            }

            Integer groupId = new GetPK(conn).getPK("GROUP_TBL_ID_SEQ");

            // create the group
            group.setId(groupId);
            group.setCreateTime(TimeUtil.getNow());
            group.setModifyTime(TimeUtil.getNow());
            groupDao.create(group);

            ActionGroupDao acDao = new ActionGroupDao(conn);
            // insert the group acess actions into the action_group_tbl;
            for (Integer actionId : accessActionList) {
                ActionGroup actionGroup = new ActionGroup();
                actionGroup.setGroupId(groupId);
                actionGroup.setActionId(actionId);
                acDao.create(actionGroup);
            }

            // insert the group data filter actions into the action_group_tbl;
            for (Integer actionId : filterActionList) {
                ActionGroup actionGroup = new ActionGroup();
                actionGroup.setGroupId(groupId);
                actionGroup.setActionId(actionId);
                acDao.create(actionGroup);
            }


            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete group.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     */
    public void deleteGroup(Group group) {
        try {
            init();

            // Start UOC

            UserGroupDao userGroupDao = new UserGroupDao(conn);
            UserGroup userGroup = new UserGroup();
            userGroup.setGroupId(group.getId());

            if (userGroupDao.getGroupUserCount(userGroup) > 0) {
                errors.add("BSE01601");

                return;
            }

            // delete the group rights from group_action_tbl
            ActionGroupDao acGroupDao = new ActionGroupDao(conn);
            ActionGroup actionGroup = new ActionGroup();
            actionGroup.setGroupId(group.getId());
            acGroupDao.deleteActionByGroup(actionGroup);

            // delete group
            GroupDao groupDao = new GroupDao(conn);
            groupDao.delete(group);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify group info.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param group
     * @param accessActionList
     * @param filterActionList
     */
    public void modifyGroupInfo(Group group, List<Integer> accessActionList, List<Integer> filterActionList) {
        try {
            init();

            // Start UOC

            GroupDao groupDao = new GroupDao(conn);
            group.setName(group.getName().trim());

            if (groupDao.getCountByGroupNameModify(group) > 0) {
                errors.add("BSE00000,group.name");
                return;
            }

            group.setModifyTime(TimeUtil.getNow());
            groupDao.modifyGroupInfo(group);

            ActionGroupDao actionGroupDao = new ActionGroupDao(conn);
            ActionGroup actionGroup = new ActionGroup();
            Integer groupId = group.getId();
            // delete the actions from the action_group_tbl
            actionGroup.setGroupId(groupId);
            actionGroupDao.deleteActionByGroup(actionGroup);

            // add the group access actions to the action_group_tbl
            for (Integer actionId : accessActionList) {
                ActionGroup ag = new ActionGroup();
                ag.setGroupId(groupId);
                ag.setActionId(actionId);
                actionGroupDao.create(ag);
            }

            // add the group filter actions to the action_group_tbl
            for (Integer actionId : filterActionList) {
                ActionGroup ag = new ActionGroup();
                ag.setGroupId(groupId);
                ag.setActionId(actionId);
                actionGroupDao.create(ag);
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the group infomation.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltGroup&gt
     */
    public List<Group> getAllGroup() {
        List<Group> citysOut = new ArrayList<Group>();

        try {
            init();

            // Start UOC

            GroupDao groupDao = new GroupDao(conn);
            citysOut = groupDao.searchAllGroup();

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return citysOut;
    }


    /**
     * get one group by id.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param group
     * @return Group
     */
    public Group getSingleGroup(Group group) {
        Group groupOut = null;

        try {
            init();

            // Start UOC

            GroupDao groupDao = new GroupDao(conn);
            groupOut = groupDao.searchGroupById(group);

            if (null == groupOut) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return groupOut;
    }
}
