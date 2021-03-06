package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.Installation;

public class InstPlaceTypeDao extends BaseDao{

    public InstPlaceTypeDao(Connection conn) {
        super(conn);
    }

    /**
     * get list for drop
     * @author sunyx
     * @return
     * @throws SQLException
     */
    public List<Installation> getTypeList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Installation> list = new ArrayList<Installation>();
        Installation inst = null;
        try{
            // Start UOC
            String sql = " SELECT" +
                        " ID," +
                        " NAME" +
                        " FROM " +
                        " INST_PLACE_TYPE_TBL ";
            pstmt = conn.prepareStatement(sql);
            
            rs = pstmt.executeQuery();
            int index = 0;
            
            while(rs.next()){
                index = 0;
                inst = new Installation();
                inst.setInstPlaceTypeId(rs.getInt(++index));
                inst.setInstPlaceTypeName(rs.getString(++index));
                list.add(inst);
            }
            
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getTypeList");
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
                logSQLException(e, "getTypeList");
                throw e;
            }
        }
    }
    
    /**
     * get count by id.
     * @author sunyx
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
                    " INST_PLACE_TYPE_TBL " +
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
     * get the id by the name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   installPlaceTypeName
     * @return  int
     * @throws  Exception
     */
    public Integer getIdByName(String installPlaceTypeName) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer count = null;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " INST_PLACE_TYPE_TBL " +
                    " WHERE " +
                    " NAME= ? ";
           
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, installPlaceTypeName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

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
