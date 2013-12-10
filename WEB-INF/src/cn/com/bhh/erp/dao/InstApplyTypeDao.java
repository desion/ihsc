package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.InstallationApply;

public class InstApplyTypeDao extends BaseDao{

    public InstApplyTypeDao(Connection conn) {
        super(conn);
    }

    public int getCount(Integer id) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            int count = 0;
            String sql = " SELECT" +
                        " COUNT(*)" +
                        " FROM " +
                        " INST_APPLY_TYPE_TBL " +
                        " WHERE " +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);
            int index = 0 ;
            pstmt.setInt(++index, id);
            
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                index = 0;
                count = rs.getInt(++index);
            }
            
            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getApplyTypeForDrop");
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
                logSQLException(e, "getApplyTypeForDrop");
                throw e;
            }
        }
    }
    
    public List<InstallationApply> getApplyTypeForDrop() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<InstallationApply> list = new ArrayList<InstallationApply>();
            String sql = "SELECT " +
                        " ID," +
                        " NAME" +
                        " FROM" +
                        " INST_APPLY_TYPE_TBL " +
                        " ORDER BY ID";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            InstallationApply ia = null;
            while (rs.next()){
                int index = 0;
                ia = new InstallationApply();
                ia.setType(rs.getInt(++index));
                ia.setTypeName(rs.getString(++index));
                
                list.add(ia);
            }
            
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getApplyTypeForDrop");
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
                logSQLException(e, "getApplyTypeForDrop");
                throw e;
            }
        }
    }
    
    
    
   
}
