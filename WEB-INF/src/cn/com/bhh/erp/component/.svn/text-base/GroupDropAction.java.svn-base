//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.GroupBusiness;
import cn.com.bhh.erp.entity.Group;

import java.util.ArrayList;
import java.util.List;


/**
 * this is a component to list all
 * the group from group_tbl,and select
 * the relative item according to the
 * selectedGroupId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class GroupDropAction extends BaseAction {
    private List<Group> groupDropList = new ArrayList<Group>();
    private Integer selectedGroupId;

    public GroupDropAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    /**
     * prepare the data for the city drop list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     */
    public void initData() {
        GroupBusiness cb = new GroupBusiness();
        groupDropList = cb.getAllGroup();
    }

    /**
     * @return the groupDropList
     */
    public List<Group> getGroupDropList() {
        return groupDropList;
    }


    /**
     * @return the selectedGroupId
     */
    public Integer getSelectedGroupId() {
        return selectedGroupId;
    }

    /**
     * @param selectedGroupId the selectedGroupId to set
     */
    public void setSelectedGroupId(Integer selectedGroupId) {
        this.selectedGroupId = selectedGroupId;
    }
}
