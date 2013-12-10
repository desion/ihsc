//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import cn.com.bhh.erp.entity.Purpose;

public class PurposeDao extends BaseDao{

    public PurposeDao(Connection conn){
        super(conn);
    }

    /**
     * get count by id.
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  SQLException
     */
    public int getCountByID(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " PURPOSE_TBL " +
                    " WHERE " +
                    " ID=?  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByID");
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
                logSQLException(e, "getCountByID");
                throw e;
            }
        }
    }
    

    /**
     * get id by name.
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   String
     * @return  Integer
     * @throws  SQLException
     */
    public Integer getIdByName(String name) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " PURPOSE_TBL " +
                    " WHERE " +
                    " NAME=?  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                id = rs.getInt(++index);
            }

            return id;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getIdByName");
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
                logSQLException(e, "getIdByName");
                throw e;
            }
        }
    }
    
    public List<Purpose> searchAll() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            List<Purpose> list = new ArrayList<Purpose>();
            String sql = "select"
                        + " id,name"
                        + " from purpose_tbl"
                        + " order by id";
            pstmt = conn.prepareStatement(sql);
            Purpose productOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                productOut = new Purpose();
                index = 0;
                productOut.setId(rs.getInt(++index));
                productOut.setName(rs.getString(++index));

                list.add(productOut);
            }
            return list;
        } catch(SQLException e){
            logSQLException(e, "search error");
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
                logSQLException(e, "selectAll");
                throw e;
            }
        }
    }
}
