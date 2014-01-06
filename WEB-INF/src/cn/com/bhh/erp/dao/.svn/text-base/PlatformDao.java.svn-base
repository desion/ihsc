package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.Platform;

public class PlatformDao extends BaseDao{

    public PlatformDao(Connection conn) {
        super(conn);
    }

    /**
     * get all platform for drop
     * @author sunyx
     * @return
     * @throws SQLException
     */
    public List<Platform> getPlatformListForDrop() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Platform> list = new ArrayList<Platform>();
        
        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " ID," +
                        " NAME " +
                        " FROM " + 
                        " PLATFORM_TBL ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            rs = pstmt.executeQuery();

            Platform pf = null;
            while (rs.next()) {
                index = 0;
                pf = new Platform();
                pf.setId(rs.getInt(++index));
                pf.setName(rs.getString(++index));
                
                list.add(pf);
            }
            
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getPlatformListForDrop");
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
                logSQLException(e, "getPlatformListForDrop");
                throw e;
            }
        }
    }
    /**
     * get count by id
     * @author sunyx
     * @param id
     * @return
     * @throws SQLException
     */
    public int getCountById(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " COUNT(*)" +
                        " FROM " + 
                        " PLATFORM_TBL " +
                        " WHERE" +
                        " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            
            return count;
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
                    " PLATFORM_TBL " +
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
}
