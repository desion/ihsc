package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.FaultHandle;

/**
 * fault_handle_type
 * @author sunyx
 */
public class FaultHandleTypeDao extends BaseDao{

    public FaultHandleTypeDao(Connection conn) {
        super(conn);
    }

    /**
     * for drop
     * @return List&ltFaultHandle&gt
     * @throws SQLException
     */
    public List<FaultHandle> getHandleType ()throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int index = 0;

        try {
            // Start UOC
            List<FaultHandle> list = new ArrayList<FaultHandle>();
            
            String sql = "SELECT" +
                        " ID," +
                        " NAME" +
                        " FROM" +
                        " FAULT_HANDLE_TYPE_TBL";
            
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            FaultHandle fh = null;
            
            while(rs.next()){
                fh = new FaultHandle();
                index = 0;
                fh.setHandleType(rs.getInt(++index));
                fh.setHandleTypeName(rs.getString(++index));
                
                list.add(fh);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getHandleType");
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
                logSQLException(e, "getHandleType");
                throw e;
            }
        }
    }
    
    public int getCountById(Integer id)throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT" +
                        " COUNT(*)" +
                        " FROM" +
                        " FAULT_HANDLE_TYPE_TBL" +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getHandleType");
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
                logSQLException(e, "getHandleType");
                throw e;
            }
        }
        return count;
    }
}
