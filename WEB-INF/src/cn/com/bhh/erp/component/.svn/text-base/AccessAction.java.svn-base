//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.ActionBusiness;
import cn.com.bhh.erp.entity.Action;

/**
 * this is a compononet to list all the actions from action_tbl,and select the
 * group actions from action_group_tbl according to the groupId
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class AccessAction extends BaseAction {
    private List<Action> actionList = new ArrayList<Action>();
    private Integer selectedGroupId = null;

    public AccessAction() {
        initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    /**
     * prepare date for the checks list.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @throws Exception
     */
    public void initData() {
        ActionBusiness ab = new ActionBusiness();

        if (selectedGroupId == null) {
            actionList = ab.getActionByType(0);
        } else {
            actionList = ab.getActionsByGroupAndType(selectedGroupId, 0);
        }
    }

    /**
     * @return the actionList
     */
    public List<Action> getActionList() {
        return actionList;
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
