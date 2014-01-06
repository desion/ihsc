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

import cn.com.bhh.erp.entity.OperationType;

/**
 * operation type dao.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
public class OperationTypeDao extends BaseDao{

    public OperationTypeDao(Connection conn){
        super(conn);
    }

    /**
     * search all operation type.
     * @author luyan
     * @since 1.0
     * @return operation type list
     * @throws SQLException
     */
    public List<OperationType> searchAll() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            List<OperationType> operationTypeList = new ArrayList<OperationType>();
            String sql = " SELECT " +
                "   ID, " +
                "   NAME " +
                " FROM " +
                "   OPERATION_TYPE_TBL " +
                " ORDER BY ID ";
            pstmt = conn.prepareStatement(sql);
            OperationType operationType = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                operationType = new OperationType();
                index = 0;
                operationType.setId(rs.getInt(++index));
                operationType.setName(rs.getString(++index));

                operationTypeList.add(operationType);
            }
            return operationTypeList;
        } catch(SQLException e){
            logSQLException(e, "searchAll");
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

    /**
     * get count by ID.
     * @author  luyan
     * @since   1.0
     * @param   id ID
     * @return  count
     * @throws  SQLException
     */
    public int getCountById(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql= " SELECT " +
                "   COUNT(*) " +
                " FROM " +
                "   OPERATION_TYPE_TBL " +
                " WHERE " +
                "   ID = ? ";
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
}
