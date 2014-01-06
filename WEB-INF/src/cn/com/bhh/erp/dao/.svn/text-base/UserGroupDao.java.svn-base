//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.entity.UserGroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserGroupDao extends BaseDao {
    public UserGroupDao(Connection conn) {
        super(conn);
    }

    /**
     * create user group
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   UserGroup
     * @throws  SQLException
     */
    public void create(UserGroup userGroup) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                     " INSERT INTO USER_GROUP_TBL (" +
                     " USER_ID," +
                     " GROUP_ID" +
                     " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, userGroup.getUserId());
            pstmt.setInt(++index, userGroup.getGroupId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "create");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "create");
                throw e;
            }
        }
    }

    /**
     * modify user group
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   UserGroup
     * @throws  SQLException
     */
    public void modifyUserGroup(UserGroup userGroup) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                      " UPDATE USER_GROUP_TBL SET " +
                      " GROUP_ID=? " +
                      " WHERE " +
                      " USER_ID=? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, userGroup.getGroupId());
            pstmt.setInt(++index, userGroup.getUserId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyUserGroup");
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
                logSQLException(e, "modifyUserGroup");
                throw e;
            }
        }
    }

    /**
     * delete the user group
     * according to the user id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   UserGroup
     * @throws  SQLException
     */
    public void deleteUserGroupByUser(UserGroup userGroup) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                     " DELETE " +
                     " FROM " +
                     " USER_GROUP_TBL " +
                     " WHERE " +
                     " USER_ID=? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, userGroup.getUserId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteUserGroupByUser");
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
                logSQLException(e, "deleteUserGroupByUser");
                throw e;
            }
        }
    }

    /**
     * get the group count referenced
     * by user
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   UserGroup
     * @return  int
     * @throws  SQLException
     */
    public int getGroupUserCount(UserGroup userGroup) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                      " SELECT " +
                      " COUNT(*) " +
                      " FROM " +
                      " USER_GROUP_TBL " +
                      " WHERE " +
                      " GROUP_ID=?"; 
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, userGroup.getGroupId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getGroupUserCount");
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
                logSQLException(e, "getGroupUserCount");
                throw e;
            }
        }

        return count;
    }
}
