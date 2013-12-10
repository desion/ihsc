//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import cn.com.bhh.erp.entity.ActionGroup;


/**
 * action_group_tbl table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class ActionGroupDao extends BaseDao {
    public ActionGroupDao(Connection conn) {
        super(conn);
    }


    /**
     * delete the record from action_group_tbl
     * by the rule group id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   ActionGroup
     * @throws  SQLException
     */
    public void deleteActionByGroup(ActionGroup actionGroup) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " ACTION_GROUP_TBL " +
                    " WHERE  " +
                    " GROUP_ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, actionGroup.getGroupId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "delete");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "delete");
                throw e;
            }
        }
    }

    /**
     * create ActionGroup.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   ActionGroup
     * @throws  SQLException
     */
    public void create(ActionGroup actionGroup) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO ACTION_GROUP_TBL (" +
            		  " GROUP_ID," +
            		  " ACTION_ID" +
            		  " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, actionGroup.getGroupId());
            pstmt.setInt(++index, actionGroup.getActionId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "insert");
                throw e;
            }
        }
    }

  
}
