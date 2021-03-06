//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Group table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class GroupDao extends BaseDao {
    public GroupDao(Connection conn) {
        super(conn);
    }

    /**
     * delete group
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @throws  Exception
     */
    public void delete(Group group) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = 
                          " SELECT " +
                          " EXCLUSIVE_KEY " +
                          " FROM GROUP_TBL " +
                          " WHERE ID = ? " +
                          " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, group.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != group.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("recode is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }

            // Start UOC
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " GROUP_TBL " +
                       " WHERE " +
                       " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, group.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "delete");
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
                logSQLException(e, "delete");
                throw e;
            }
        }
    }

    /**
     * create group.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @throws  SQLException
     */
    public void create(Group group) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO GROUP_TBL (" +
                      " ID," +
                      " NAME," +
                      " DESCRIPTION," +
                      " CREATOR_ID," +
                      " CREATE_TIME," +
                      " MODIFIER_ID," +
                      " MODIFY_TIME" +
                      " ) VALUES(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, group.getId());
            pstmt.setString(++index, group.getName());
            pstmt.setString(++index, group.getDescription());
            pstmt.setInt(++index, group.getCreatorId());
            pstmt.setString(++index, group.getCreateTime());
            pstmt.setInt(++index, group.getModifierId());
            pstmt.setString(++index, group.getModifyTime());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert group");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "insert group");
                throw e;
            }
        }
    }

    /**
     * get all group.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltGroup&gt
     * @throws  SQLException
     */
    public List<Group> searchAllGroup() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Group>list = new ArrayList<Group>();
            String sql = 
                      " SELECT " +
                      " ID," +
                      " NAME," +
                      " DESCRIPTION," +
                      " EXCLUSIVE_KEY " +
                      " FROM GROUP_TBL " +
                      " WHERE " +
                      " ID>0 " +
                      " ORDER BY NAME "; 
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Group group = new Group();
                index = 0;
                group.setId(rs.getInt(++index));
                group.setName(rs.getString(++index));
                group.setDescription(rs.getString(++index));
                group.setExclusiveKey(rs.getInt(++index));
                list.add(group);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchAllGroup");
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
                logSQLException(e, "searchAllGroup");
                throw e;
            }
        }
    }

    /**
     * get group by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @return  Group
     * @throws  Exception
     */
    public Group searchGroupById(Group group) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Group groupOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM " +
                            " GROUP_TBL " +
                            " WHERE ID = ? " ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, group.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != group.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("record is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            
            
            String sql =
                       " SELECT " +
                       " ID," +
                       " NAME," +
                       " DESCRIPTION," +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " GROUP_TBL " +
                       " WHERE " +
                       " ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, group.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                groupOut = new Group();
                index = 0;
                groupOut.setId(rs.getInt(++index));
                groupOut.setName(rs.getString(++index));
                groupOut.setDescription(rs.getString(++index));
                groupOut.setExclusiveKey(rs.getInt(++index));
            }

            return groupOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchGroupById");
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
                logSQLException(e, "searchGroupById");
                throw e;
            }
        }
    }

    /**
     * get group count by name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @return  int
     * @throws  SQLException
     */
    public int getCountByGroupName(Group group) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " GROUP_TBL " +
                    " WHERE " +
                    " NAME = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, group.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByGroupName");
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
                logSQLException(e, "getCountByGroupName");
                throw e;
            }
        }

        return count;
    }
    
    
    /**
     * get group count by group id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   groupId
     * @return  int
     * @throws  SQLException
     */
    public int getCountById(Integer groupId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " GROUP_TBL " +
                    " WHERE " +
                    " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, groupId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountById");
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
                logSQLException(e, "getCountById");
                throw e;
            }
        }

        return count;
    }



    /**
     * modify group information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @throws  Exception
     */
    public void modifyGroupInfo(Group group) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
                if (exclusiveCheck) {
                    String sql = 
                        " SELECT " +
                        " EXCLUSIVE_KEY " +
                        " FROM GROUP_TBL " +
                        " WHERE ID = ? " +
                        " FOR UPDATE NOWAIT";
                    

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, group.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != group.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("recode is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            Integer nowExclusiveKey=group.getExclusiveKey();
            group.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                      " UPDATE GROUP_TBL  SET " +
                      " NAME=?, " +
                      " DESCRIPTION=?," +
                      " MODIFIER_ID=?," +
                      " MODIFY_TIME=?, " +
                      " EXCLUSIVE_KEY=? " +
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, group.getName());
            pstmt.setString(++index, group.getDescription());
            pstmt.setInt(++index, group.getModifierId());
            pstmt.setString(++index, group.getModifyTime());
            pstmt.setInt(++index, group.getExclusiveKey());
            pstmt.setInt(++index, group.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateGroupInfo");
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
                logSQLException(e, "updateGroupInfo");
                throw e;
            }
        }
    }

    /**
     * get group count
     * by name except self
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Group
     * @return  int
     * @throws  SQLException
     */
    public int getCountByGroupNameModify(Group group) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                      " SELECT " + 
                      " COUNT(*) " + 
                      " FROM " + 
                      " GROUP_TBL A " +
                      " WHERE EXISTS " +
                      " ( "+ 
                      "   SELECT " + 
                      "   NAME " + 
                      "   FROM " + 
                      "   GROUP_TBL B " + 
                      "   WHERE A.ID != B.ID "+
                      "   AND B.ID =?" + 
                      "   ) " + 
                      " AND A.NAME= ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, group.getId());
            pstmt.setString(++index, group.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByGroupNameModify");
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
                logSQLException(e, "getCountByGroupNameModify");
                throw e;
            }
        }

        return count;
    }
}
