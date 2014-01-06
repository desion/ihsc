package cn.com.bhh.erp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.OS;

public class OSDao extends BaseDao{

    public OSDao(Connection conn) {
        super(conn);
    }

    /**
     * get OS list
     * @author sunyx
     * @param suppoerterId
     * @return
     * @throws SQLException
     */
    public List<OS> getOSListForDrop() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<OS> list = new ArrayList<OS>();
        
        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " ID," +
                        " NAME " +
                        " FROM " + 
                        " OS_TBL ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            rs = pstmt.executeQuery();

            OS os = null;
            while (rs.next()) {
                index = 0;
                os = new OS();
                os.setId(rs.getInt(++index));
                os.setName(rs.getString(++index));
                
                list.add(os);
            }
            
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getOSListForDrop");
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
                logSQLException(e, "getOSListForDrop");
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
                    " OS_TBL " +
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
    public BigDecimal getIdByName(String name) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        BigDecimal id = null;
        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " OS_TBL " +
                    " WHERE " +
                    " NAME=?  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, name);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                id = rs.getBigDecimal(++index);
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
