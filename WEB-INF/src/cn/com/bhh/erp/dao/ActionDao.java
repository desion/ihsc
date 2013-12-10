//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.entity.Action;
import cn.com.bhh.erp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Action table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class ActionDao extends BaseDao {
    public ActionDao(Connection conn) {
        super(conn);
    }

    /**
     * select the action by user id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  List&ltString&gt
     * @throws  Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> selectAllActionByUserId(User user) throws Exception {
        List<String> list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                " SELECT " +
                " ACTION_TBL.NAME " +
                " FROM USER_TBL,USER_GROUP_TBL,ACTION_GROUP_TBL,ACTION_TBL"+ 
                " WHERE " +
                " USER_TBL.ID = ? " +
                " AND USER_TBL.ID = USER_GROUP_TBL.USER_ID" + 
                " AND USER_GROUP_TBL.GROUP_ID = ACTION_GROUP_TBL.GROUP_ID" + 
                " AND ACTION_GROUP_TBL.ACTION_ID = ACTION_TBL.ID" +
                " AND ACTION_TBL.TYPE = 0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;

                list.add(rs.getString(++index));
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAllActionByUserId");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "selectAllActionByUserId");
                throw e;
            }
        }

        return list;
    }
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  List<String>
     * @throws  Exception
     */
    public List<String> searchAllDropAction() throws Exception {
        List<String> list = new ArrayList<String>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                       " SELECT  " +
                       " NAME "+
                       " FROM ACTION_TBL"+ 
                       " WHERE" +
                       " TYPE=1 ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            int index=0;
            while (rs.next()) {
                index = 0;
                list.add(rs.getString(++index));
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchAllDropAction");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "searchAllDropAction");
                throw e;
            }
        }

        return list;
    }
    

    /**
     * select all filter by user id
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  List&ltString&gt
     * @throws  Exception
     */
    @SuppressWarnings("unchecked")
    public List<String> selectAllDataFilterByUserId(User user) throws Exception {
        List<String> list = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                " SELECT " +
                " ACTION_TBL.NAME " +
                " FROM USER_TBL,USER_GROUP_TBL,ACTION_GROUP_TBL,ACTION_TBL"+ 
                " WHERE " +
                " USER_TBL.ID = ? " +
                " AND USER_TBL.ID = USER_GROUP_TBL.USER_ID" + 
                " AND USER_GROUP_TBL.GROUP_ID = ACTION_GROUP_TBL.GROUP_ID" + 
                " AND ACTION_GROUP_TBL.ACTION_ID = ACTION_TBL.ID" +
                " AND ACTION_TBL.TYPE = 2";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;

                list.add(rs.getString(++index));
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAllDataFilterByUserId");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "selectAllDataFilterByUserId");
                throw e;
            }
        }

        return list;
    }

    
    /**
     * select action from action_tbl by type.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltAction&gt
     * @throws  Exception
     */
    public List<Action> selectActionByType(Integer type) throws Exception {
        List<Action> list = new ArrayList<Action>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                   " SELECT " +
                   " ID," +
                   " NAME," +
                   " DESCRIPTION " +
                   " FROM " +
                   " ACTION_TBL  " +
                   " WHERE " +
                   " TYPE=? " +
                   " ORDER BY NAME";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index,type);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                index = 0;
                Action action = new Action();
                action.setId(rs.getInt(++index));
                action.setName(rs.getString(++index));
                action.setDescription(rs.getString(++index));
                list.add(action);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectActionByType ");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "selectActionByType ");
                throw e;
            }
        }

        return list;
    }

    
    /**
     * select the records from action_group_tbl
     * by the rule of group id and type
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   ActionGroup
     * @return  List&ltActionGroup&gt
     * @throws  SQLException
     */
    public List<Action> searchActionByGroupAndType(Integer groupId,Integer type) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Action> list = new ArrayList<Action>();

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " A.ID," +
                       " A.NAME," +
                       " A.DESCRIPTION," +
                       " B.GROUP_ID   " +
                       " FROM  " +
                       " ACTION_TBL A " +
                       " LEFT OUTER JOIN " +
                       " ACTION_GROUP_TBL B " +
                       " ON " +
                       " A.ID=B.ACTION_ID " +
                       " AND B.GROUP_ID=? "+
                       " WHERE " +
                       " A.TYPE=? "+
                       " ORDER BY A.NAME ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, groupId);
            pstmt.setInt(++index, type);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Action action = new Action();
                index = 0;
                action.setId(rs.getInt(++index));
                action.setName(rs.getString(++index));
                action.setDescription(rs.getString(++index));
                action.setGroupId(rs.getBigDecimal(++index));
                list.add(action);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchActionByGroupAndType");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "searchActionByGroupAndType");
                throw e;
            }
        }

        return list;
    }
    
    
    /**
     * judge the selected group whether has 
     * the right of deleting group.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  Exception
     */
    public int getCountByGroupId(Integer groupId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count =0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " ACTION_GROUP_TBL A,ACTION_TBL B" +
                       " WHERE " +
                       " A.ACTION_ID = B.ID " +
                       " AND A.GROUP_ID=?" +
                       " AND B.TYPE = 0 " +
                       " AND B.NAME='BS006_40'";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, groupId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByGroupId");
            throw e;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "getCountByGroupId");
                throw e;
            }
        }
    }
}
