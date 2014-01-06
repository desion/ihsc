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

import cn.com.bhh.erp.entity.FaultSpares;


public class FaultSparesDao extends BaseDao {
    public FaultSparesDao(Connection conn) {
        super(conn);
    }

    /**
     * insert fault spares information.
     * @author luyan
     * @since 1.0
     * @param faultSparesIn fault spares information
     * @throws SQLException
     */
    public void insert(FaultSpares faultSparesIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " INSERT INTO FAULT_SPARES_TBL ( " +
                "   FAULT_ID, " +
                "   NAME, " +
                "   PICTURE_NO, " +
                "   WAREHOUSE, " +
                "   NO, " +
                "   APPLY_DATE, " +
                "   DELIVER_DATE, " +
                "   REPLACE_DATE, " +
                "   RECEIVE_DATE " +
                " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";


            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultSparesIn.getFaultId());
            pstmt.setString(++index, faultSparesIn.getName());
            pstmt.setString(++index, faultSparesIn.getPictureNo());
            pstmt.setString(++index, faultSparesIn.getWarehouse());
            pstmt.setString(++index, faultSparesIn.getNo());
            pstmt.setString(++index, faultSparesIn.getApplyDate());
            pstmt.setString(++index, faultSparesIn.getDeliverDate());
            pstmt.setString(++index, faultSparesIn.getReplaceDate());
            pstmt.setString(++index, faultSparesIn.getReceiveDate());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert");
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
                logSQLException(e, "insert");
                throw e;
            }
        }
    }

    /**
     * delete fault spares information by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIdIn fault ID
     * @throws SQLException
     */
    public void deleteByFaultId(Integer faultIdIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            String sql = " DELETE FROM " +
                "   FAULT_SPARES_TBL " +
                " WHERE " +
                "   FAULT_ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIdIn);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteByFaultId");
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
                logSQLException(e, "deleteByFaultId");
                throw e;
            }
        }
    }

    /**
     * get fault spares information by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIdIn fault ID
     * @throws SQLException
     */
    public List<FaultSpares> getFaultSparesListByFaultId(Integer faultIdIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FaultSpares> faultSparesListOut = new ArrayList<FaultSpares>();

        try {
            // Start UOC
            String sql = " SELECT " +
                "   ID, " +
                "   FAULT_ID, " +
                "   NAME, " +
                "   PICTURE_NO, " +
                "   WAREHOUSE, " +
                "   NO, " +
                "   APPLY_DATE, " +
                "   DELIVER_DATE, " +
                "   REPLACE_DATE, " +
                "   RECEIVE_DATE " +
                " FROM " +
                "   FAULT_SPARES_TBL " +
                " WHERE " +
                "   FAULT_ID = ? ";

            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, faultIdIn);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                FaultSpares faultSpares = new FaultSpares();
                faultSpares.setId(rs.getInt(++index));
                faultSpares.setFaultId(rs.getInt(++index));
                faultSpares.setName(rs.getString(++index));
                faultSpares.setPictureNo(rs.getString(++index));
                faultSpares.setWarehouse(rs.getString(++index));
                faultSpares.setNo(rs.getString(++index));
                faultSpares.setApplyDate(rs.getString(++index));
                faultSpares.setDeliverDate(rs.getString(++index));
                faultSpares.setReplaceDate(rs.getString(++index));
                faultSpares.setReceiveDate(rs.getString(++index));
                faultSparesListOut.add(faultSpares);
            }
            return faultSparesListOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultSparesListByFaultId");
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
                logSQLException(e, "getFaultSparesListByFaultId");
                throw e;
            }
        }
    }
}
