//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.dao.ActionDao;
import cn.com.bhh.erp.entity.Action;

import java.util.ArrayList;
import java.util.List;

/**
 * deal with the action table.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class ActionBusiness extends BaseBusiness {
    /**
     * list the actions by type.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltAction&gt
     */
    public List<Action> getActionByType(Integer type) {
        List<Action> actionsOut = new ArrayList<Action>();

        try {
            init();

            // Start UOC

            ActionDao actionDao = new ActionDao(conn);
            actionsOut = actionDao.selectActionByType(type);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return actionsOut;
    }

    /**
     * search all the actions of the group.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return List&ltActionGroup&gt
     */
    public List<Action> getActionsByGroupAndType(Integer groupId, Integer type) {
        List<Action> actionsOut = new ArrayList<Action>();

        try {
            init();

            // Start UOC

            ActionDao actionDao = new ActionDao(conn);
            actionsOut = actionDao.searchActionByGroupAndType(groupId, type);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return actionsOut;
    }
}
