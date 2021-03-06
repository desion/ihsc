//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Fault;
import cn.com.bhh.erp.entity.User;


public class FaultDao extends BaseDao {
    public FaultDao(Connection conn) {
        super(conn);
    }

    /**
     * get fault count by user id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   userId
     * @return  int
     * @throws  SQLException
     */
    public int getFaultCountBySupporterId(Integer suppoerterId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " COUNT(*) " +
                        " FROM " + 
                        " FAULT_TBL " + 
                        " WHERE " + 
                        " SUPPORTER_ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, suppoerterId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaltByUser");
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
                logSQLException(e, "getFaltByUser");
                throw e;
            }
        }

        return count;
    }
    

    /**
     * get fault count
     * @auther  liugd
     * @param   fault
     * @param   user
     * @version 1.0
     * @since   1.0
     * @return  int
     * @throws  SQLException
     */
    public int getFaultCount(Fault fault, User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT COUNT(*)" +
                        " FROM (SELECT DISTINCT A.ID" +
                        "       FROM FAULT_TBL A" +
                        "       LEFT OUTER JOIN FAULT_DETAIL_TBL B ON A.ID=B.ID" +
                        "       LEFT OUTER JOIN FAULT_HANDLE_TBL C ON A.ID=C.FAULT_ID" +
                        "       LEFT OUTER JOIN INSTALLATION_VIEW D ON A.INSTALLATION_ID=D.ID AND A.REPORT_DATE >= D.START_DATE AND A.REPORT_DATE <= D.END_DATE " +
                        "       WHERE 0=0";
                        if (!user.filter("fault_mng_all_data")) {
                            sql = sql + " AND (D.NOW_REPAIR_COMPANY_ID = ? )";
                        }
                        if (!user.hasPermission("FA001_32")) {
                            sql = sql + " AND A.DELETED = '0'";
                        }
                        if (!user.hasPermission("PR005_34")) {
                            sql = sql + " AND D.DELETED = '0'";
                        }
                        if (fault.getFaultType() != null) {
                            sql = sql + " AND A.FAULT_TYPE = ?";
                        }
                        if (fault.getAppearance() != null && !"".equals(fault.getAppearance())) {
                            sql = sql + " AND B.APPEARANCE LIKE ?";
                        }
                        if (fault.getReason() != null && !"".equals(fault.getReason())) {
                            sql = sql + " AND B.REASON LIKE ?";
                        }
                        if (fault.getStrategy() != null && !"".equals(fault.getStrategy())) {
                            sql = sql + " AND B.STRATEGY LIKE ?";
                        }
                        if (fault.getErrorCode() != null && !"".equals(fault.getErrorCode())) {
                            sql = sql + " AND B.ERROR_CODE LIKE ?";
                        }
                        if (fault.getFaultPart() != null) {
                            sql = sql + " AND A.FAULT_PART = ?";
                        }
                        if (fault.getFaultPartType() != null) {
                            sql = sql + " AND A.FAULT_PART_TYPE = ?";
                        }
                        if (fault.getIsState() != null && !"".equals(fault.getIsState())) {
                            sql = sql + " AND A.IS_STATE = ?";
                        }
                        if (fault.getSupportType() != null) {
                            sql = sql + " AND A.SUPPORT_TYPE = ?";
                        }
                        if (fault.getOccurDate() != null && !"".equals(fault.getOccurDate())) {
                            sql = sql + " AND A.OCCUR_DATE = ?";
                        }
                        if (fault.getReportDate() != null && !"".equals(fault.getReportDate())) {
                            sql = sql + " AND A.REPORT_DATE = ?";
                        }
                        if (fault.getFinishDate() != null && !"".equals(fault.getFinishDate())) {
                            sql = sql + " AND A.FINISH_DATE = ?";
                        }
                        if (fault.getRepairCompanyId() != null) {
                            sql = sql + " AND A.REPAIR_COMPANY_ID = ?";
                        }
                        if (fault.getOperatorName() != null && !"".equals(fault.getOperatorName())) {
                            sql = sql + " AND A.OPERATOR_NAME LIKE ?";
                        }
                        if (fault.getSupporterId() != null) {
                            sql = sql + " AND A.SUPPORTER_ID = ?";
                        }
                        if (fault.getOperation() != null && !"".equals(fault.getOperation())) {
                            sql = sql + " AND (B.OPERATION_1 LIKE ?" +
                                        "         OR B.OPERATION_2 LIKE ?" +
                                        "         OR B.OPERATION_3 LIKE ?" +
                                        "         OR B.OPERATION_4 LIKE ?" +
                                        "         OR B.OPERATION_5 LIKE ?" +
                                        "         OR B.OPERATION_6 LIKE ?" +
                                        "         OR B.OPERATION_7 LIKE ?" +
                                        "         OR B.OPERATION_8 LIKE ?)";
                        }
                        if (fault.getResult() != null && !"".equals(fault.getResult())) {
                            sql = sql + " AND B.RESULT LIKE ?";
                        }
                        if (fault.getHandleDetail() != null && !"".equals(fault.getHandleDetail())) {
                            sql = sql + " AND C.HANDLE_DETAIL LIKE ?";
                        }
                        if (fault.getNote() != null && !"".equals(fault.getNote())) {
                            sql = sql + " AND C.NOTE LIKE ?";
                        }
                        if (fault.getRepairState() != null) {
                            sql = sql + " AND A.REPAIR_STATE = ?";
                        }

                        if (fault.getInstallationId() != null) {
                            sql = sql + " AND A.INSTALLATION_ID = ?";
                        }
                        if (fault.getManagementId() != null && !"".equals(fault.getManagementId())) {
                            sql = sql + " AND A.MANAGEMENT_ID = ?";
                        }
                        
                        if(fault.getFaultMachineType()!=null && !"".equals(fault.getFaultMachineType())){
                            sql = sql + " AND A.FAULT_MACHINE_TYPE = ?";
                        }
                        sql = sql + " )";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            if (!user.filter("fault_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            if (fault.getFaultType() != null) {
                pstmt.setInt(++index, fault.getFaultType());
            }
            if (fault.getAppearance() != null && !"".equals(fault.getAppearance())) {
                pstmt.setString(++index, fault.getAppearance() + "%");
            }
            if (fault.getReason() != null && !"".equals(fault.getReason())) {
                pstmt.setString(++index, fault.getReason() + "%");
            }
            if (fault.getStrategy() != null && !"".equals(fault.getStrategy())) {
                pstmt.setString(++index, fault.getStrategy() + "%");
            }
            if (fault.getErrorCode() != null && !"".equals(fault.getErrorCode())) {
                pstmt.setString(++index, fault.getErrorCode() + "%");
            }
            if (fault.getFaultPart() != null) {
                pstmt.setInt(++index, fault.getFaultPart());
            }
            if (fault.getFaultPartType() != null) {
                pstmt.setInt(++index, fault.getFaultPartType());
            }
            if (fault.getIsState() != null && !"".equals(fault.getIsState())) {
                pstmt.setString(++index, fault.getIsState());
            }
            if (fault.getSupportType() != null) {
                pstmt.setInt(++index, fault.getSupportType());
            }
            if (fault.getOccurDate() != null && !"".equals(fault.getOccurDate())) {
                pstmt.setString(++index, fault.getOccurDate());
            }
            if (fault.getReportDate() != null && !"".equals(fault.getReportDate())) {
                pstmt.setString(++index, fault.getReportDate());
            }
            if (fault.getFinishDate() != null && !"".equals(fault.getFinishDate())) {
                pstmt.setString(++index, fault.getFinishDate());
            }
            if (fault.getRepairCompanyId() != null) {
                pstmt.setInt(++index, fault.getRepairCompanyId());
            }
            if (fault.getOperatorName() != null && !"".equals(fault.getOperatorName())) {
                pstmt.setString(++index, fault.getOperatorName() + "%");
            }
            if (fault.getSupporterId() != null) {
                pstmt.setInt(++index, fault.getSupporterId());
            }
            if (fault.getOperation() != null && !"".equals(fault.getOperation())) {
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
            }
            if (fault.getResult() != null && !"".equals(fault.getResult())) {
                pstmt.setString(++index, fault.getResult() + "%");
            }
            if (fault.getHandleDetail() != null && !"".equals(fault.getHandleDetail())) {
                pstmt.setString(++index, fault.getHandleDetail() + "%");
            }
            if (fault.getNote() != null && !"".equals(fault.getNote())) {
                pstmt.setString(++index, fault.getNote() + "%");
            }
            if (fault.getRepairState() != null) {
                pstmt.setInt(++index, fault.getRepairState());
            }
            if (fault.getInstallationId() != null) {
                pstmt.setInt(++index, fault.getInstallationId());
            }
            if (fault.getManagementId() != null && !"".equals(fault.getManagementId())) {
                pstmt.setString(++index, fault.getManagementId());
            }
            
            if(fault.getFaultMachineType()!=null && !"".equals(fault.getFaultMachineType())){
                pstmt.setString(++index, fault.getFaultMachineType());
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultCount");
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
                logSQLException(e, "getFaultCount");
                throw e;
            }
        }

        return count;
    }

    /**
     * get fault information list
     * @author liugd
     * @param fault
     * @param user
     * @param intBegin
     * @param intEnd
     * @return List<Fault>
     * @throws SQLException
     */
    public List<Fault> getFaultList(Fault fault, User user, int intBegin, int intEnd) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
            String sql = "SELECT" +
                        "   ID, " +
                        "   MANAGEMENT_ID, " +
                        "   INSTALLATION_ID, " +
                        "   FAULT_TYPE, " +
                        "   FAULT_TYPE_NAME, " +
                        "   OCCUR_DATE, " +
                        "   OCCUR_TIME, " +
                        "   REPORT_DATE, " +
                        "   REPORT_TIME, " +
                        "   REPAIR_STATE, " +
                        "   FINISH_DATE, " +
                        "   FINISH_TIME, " +
                        "   REPAIR_COMPANY_ID, " +
                        "   REPAIR_COMPANY_NAME, " +
                        "   SUPPORTER_ID, " +
                        "   SUPPORTER_NAME, " +
                        "   FOLLOWER_ID," +
                        "   FOLLOWERNAME," +
                        "   OPERATOR_NAME, " +
                        "   SUPPORT_TYPE, " +
                        "   SUPPORT_TYPE_NAME, " +
                        "   FAULT_PART, " +
                        "   FAULT_PART_NAME, " +
                        "   FAULT_PART_TYPE, " +
                        "   FAULT_PART_TYPE_NAME, " +
                        "   IS_STATE, " +
                        "   APPLICATION_VERSION, " +
                        "   APPLICATION_VERSION_DETAIL_1, " +
                        "   APPLICATION_VERSION_DETAIL_2, " +
                        "   LOCKED, " +
                        "   DELETED, " +
                        "   EXCLUSIVE_KEY, " +
                        "   OCCUR_CONDICTION_1, " +
                        "   OCCUR_CONDICTION_2, " +
                        "   ERROR_CODE, " +
                        "   RX278, " +
                        "   COUNTER, " +
                        "   APPEARANCE, " +
                        "   REASON, " +
                        "   STRATEGY, " +
                        "   STRATEGY_DETAIL," +
                        "   RESULT, " +
                        "   RESULTNAME, " +
                        "   OPERATION_1, " +
                        "   OPERATION_2, " +
                        "   OPERATION_3, " +
                        "   OPERATION_4, " +
                        "   OPERATION_5, " +
                        "   OPERATION_6, " +
                        "   OPERATION_7, " +
                        "   OPERATION_8, " +
                        "   CASH_LEFT, " +
                        "   EXCEPTION_DISPLAY, " +
                        "   DISPLAY, " +
                        "   BACK_LIGHT, " +
                        "   DISPLAY_CONTENT, " +
                        "   INPUTABLE, " +
                        "   KNOCK_SOUND, " +
                        "   RESET, " +
                        "   CUT_POWER, " +
                        "   REBOOT_NORMALLY, " +
                        "   MOTION_COUNTER, " +
                        "   ERROR_NO, " +
                        "   NO_REPON, " +
                        "   OTHER_DISPLAY, " +
                        "   TRACE_INFOMATION, " +
                        "   FAULT_RECORD, " +
                        "   STATISTICS, " +
                        "   TRADE_LOG, " +
                        "   APPLICATION_VERSION_1, " +
                        "   OTHERS, " +
                        "   C_RAS, " +
                        "   D_RAS, " +
                        "   MCU_LOG, " +
                        "   SYSTEM_EVENT, " +
                        "   D_BILLBOX, " +
                        "   D_COLLECT, " +
                        "   C_FEP, " +
                        "   C_ERR, " +
                        "   APPLY_DATE," +
                        "   DELIVER_DATE," +
                        "   REPLACE_DATE," +
                        "   RECEIVE_DATE, " +
                        "   BRANCH_COMPANY_NAME," +
                        "   INSTALL_PLACE," +
                        "   MANUFACTURE_NO," +
                        "   PRODUCT_MODEL," +
                        "   PRODUCT_CATEGORY_NAME," +
                        "   CUSTOMER_NAME" +
                        " FROM " +
                        "   (" +
                        "   SELECT" +
                        "   ROWNUM NO,K.*" +
                        "   FROM" +
                                " (" +
                                " SELECT DISTINCT" +
                                "   A.ID, " +
                                "   A.MANAGEMENT_ID, " +
                                "   A.INSTALLATION_ID, " +
                                "   A.FAULT_TYPE, " +
                                "   J.NAME FAULT_TYPE_NAME, " +
                                "   A.OCCUR_DATE, " +
                                "   A.OCCUR_TIME, " +
                                "   A.REPORT_DATE, " +
                                "   A.REPORT_TIME, " +
                                "   A.REPAIR_STATE, " +
                                "   A.FINISH_DATE, " +
                                "   A.FINISH_TIME, " +
                                "   A.REPAIR_COMPANY_ID, " +
                                "   I.SHORT_NAME REPAIR_COMPANY_NAME, " +
                                "   A.SUPPORTER_ID, " +
                                "   E.FAMILY_NAME || E.GIVEN_NAME SUPPORTER_NAME, " +
                                "   A.FOLLOWER_ID," +
                                "   L.FAMILY_NAME || L.GIVEN_NAME FOLLOWERNAME," +
                                "   A.OPERATOR_NAME, " +
                                "   A.SUPPORT_TYPE, " +
                                "   F.NAME SUPPORT_TYPE_NAME, " +
                                "   A.FAULT_PART, " +
                                "   G.NAME FAULT_PART_NAME, " +
                                "   A.FAULT_PART_TYPE, " +
                                "   H.NAME FAULT_PART_TYPE_NAME, " +
                                "   A.IS_STATE, " +
                                "   A.APPLICATION_VERSION, " +
                                "   A.APPLICATION_VERSION_DETAIL_1, " +
                                "   A.APPLICATION_VERSION_DETAIL_2, " +
                                "   A.LOCKED, " +
                                "   A.DELETED, " +
                                "   A.EXCLUSIVE_KEY, " +
                                "   B.OCCUR_CONDICTION_1, " +
                                "   B.OCCUR_CONDICTION_2, " +
                                "   B.ERROR_CODE, " +
                                "   B.RX278, " +
                                "   B.COUNTER, " +
                                "   B.APPEARANCE, " +
                                "   B.REASON, " +
                                "   B.STRATEGY, " +
                                "   B.STRATEGY_DETAIL, " +
                                "   B.RESULT, " +
                                "   K.NAME RESULTNAME," +
                                "   B.OPERATION_1, " +
                                "   B.OPERATION_2, " +
                                "   B.OPERATION_3, " +
                                "   B.OPERATION_4, " +
                                "   B.OPERATION_5, " +
                                "   B.OPERATION_6, " +
                                "   B.OPERATION_7, " +
                                "   B.OPERATION_8, " +
                                "   B.CASH_LEFT, " +
                                "   B.EXCEPTION_DISPLAY, " +
                                "   B.DISPLAY, " +
                                "   B.BACK_LIGHT, " +
                                "   B.DISPLAY_CONTENT, " +
                                "   B.INPUTABLE, " +
                                "   B.KNOCK_SOUND, " +
                                "   B.RESET, " +
                                "   B.CUT_POWER, " +
                                "   B.REBOOT_NORMALLY, " +
                                "   B.MOTION_COUNTER, " +
                                "   B.ERROR_NO, " +
                                "   B.NO_REPON, " +
                                "   B.OTHER_DISPLAY, " +
                                "   B.TRACE_INFOMATION, " +
                                "   B.FAULT_RECORD, " +
                                "   B.STATISTICS, " +
                                "   B.TRADE_LOG, " +
                                "   B.APPLICATION_VERSION APPLICATION_VERSION_1, " +
                                "   B.OTHERS, " +
                                "   B.C_RAS, " +
                                "   B.D_RAS, " +
                                "   B.MCU_LOG, " +
                                "   B.SYSTEM_EVENT, " +
                                "   B.D_BILLBOX, " +
                                "   B.D_COLLECT, " +
                                "   B.C_FEP, " +
                                "   B.C_ERR, " +
                                "   B.APPLY_DATE," +
                                "   B.DELIVER_DATE," +
                                "   B.REPLACE_DATE," +
                                "   B.RECEIVE_DATE, " +
                                "   D.BRANCH_COMPANY_NAME," +
                                "   D.INSTALL_PLACE," +
                                "   D.MANUFACTURE_NO," +
                                "   D.PRODUCT_MODEL," +
                                "   D.PRODUCT_CATEGORY_NAME," +
                                "   D.CUSTOMER_NAME" +
                                " FROM FAULT_TBL A" +
                                "   LEFT OUTER JOIN FAULT_DETAIL_TBL B ON A.ID=B.ID" +
                                "   LEFT OUTER JOIN FAULT_HANDLE_TBL C ON A.ID=C.FAULT_ID" +
                                "   LEFT OUTER JOIN INSTALLATION_VIEW D ON A.INSTALLATION_ID=D.ID AND A.REPORT_DATE >= D.START_DATE AND A.REPORT_DATE <= D.END_DATE " +
                                "   LEFT OUTER JOIN USER_TBL E ON E.ID = A.SUPPORTER_ID AND E.ID > 0" +
                                "   LEFT OUTER JOIN SUPPORT_TYPE_TBL F ON F.ID = A.SUPPORT_TYPE" +
                                "   LEFT OUTER JOIN FAULT_PART_TBL G ON G.ID = A.FAULT_PART" +
                                "   LEFT OUTER JOIN FAULT_PART_TYPE_TBL H ON H.ID = A.FAULT_PART_TYPE" +
                                "   LEFT OUTER JOIN COMPANY_TBL I ON I.ID = A.REPAIR_COMPANY_ID AND I.ID > 0" +
                                "   LEFT OUTER JOIN FAULT_TYPE_TBL J ON J.ID = A.FAULT_TYPE" +
                                "   LEFT OUTER JOIN FAULT_STATUS_TBL K ON K.ID = B.RESULT" +
                                "   LEFT OUTER JOIN USER_TBL L ON L.ID = A.FOLLOWER_ID" +
                                " WHERE 0=0 ";
                                if (!user.filter("fault_mng_all_data")) {
                                    sql = sql + " AND (D.NOW_REPAIR_COMPANY_ID = ? )";
                                }
                                if (!user.hasPermission("FA001_32")) {
                                    sql = sql + " AND A.DELETED = '0'";
                                }
                                if (!user.hasPermission("PR005_34")) {
                                    sql = sql + " AND D.DELETED = '0'";
                                }
                                if (fault.getFaultType() != null) {
                                    sql = sql + " AND A.FAULT_TYPE = ?";
                                }
                                if (fault.getAppearance() != null && !"".equals(fault.getAppearance())) {
                                    sql = sql + " AND B.APPEARANCE LIKE ?";
                                }
                                if (fault.getReason() != null && !"".equals(fault.getReason())) {
                                    sql = sql + " AND B.REASON LIKE ?";
                                }
                                if (fault.getStrategy() != null && !"".equals(fault.getStrategy())) {
                                    sql = sql + " AND B.STRATEGY LIKE ?";
                                }
                                if (fault.getErrorCode() != null && !"".equals(fault.getErrorCode())) {
                                    sql = sql + " AND B.ERROR_CODE LIKE ?";
                                }
                                if (fault.getFaultPart() != null) {
                                    sql = sql + " AND A.FAULT_PART = ?";
                                }
                                if (fault.getFaultPartType() != null) {
                                    sql = sql + " AND A.FAULT_PART_TYPE = ?";
                                }
                                if (fault.getIsState() != null && !"".equals(fault.getIsState())) {
                                    sql = sql + " AND A.IS_STATE = ?";
                                }
                                if (fault.getSupportType() != null) {
                                    sql = sql + " AND A.SUPPORT_TYPE = ?";
                                }
                                if (fault.getOccurDate() != null && !"".equals(fault.getOccurDate())) {
                                    sql = sql + " AND A.OCCUR_DATE = ?";
                                }
                                if (fault.getReportDate() != null && !"".equals(fault.getReportDate())) {
                                    sql = sql + " AND A.REPORT_DATE = ?";
                                }
                                if (fault.getFinishDate() != null && !"".equals(fault.getFinishDate())) {
                                    sql = sql + " AND A.FINISH_DATE = ?";
                                }
                                if (fault.getRepairCompanyId() != null) {
                                    sql = sql + " AND A.REPAIR_COMPANY_ID = ?";
                                }
                                if (fault.getOperatorName() != null && !"".equals(fault.getOperatorName())) {
                                    sql = sql + " AND A.OPERATOR_NAME LIKE ?";
                                }
                                if (fault.getSupporterId() != null) {
                                    sql = sql + " AND A.SUPPORTER_ID = ?";
                                }
                                if (fault.getOperation() != null && !"".equals(fault.getOperation())) {
                                    sql = sql + " AND (B.OPERATION_1 LIKE ?" +
                                                "         OR B.OPERATION_2 LIKE ?" +
                                                "         OR B.OPERATION_3 LIKE ?" +
                                                "         OR B.OPERATION_4 LIKE ?" +
                                                "         OR B.OPERATION_5 LIKE ?" +
                                                "         OR B.OPERATION_6 LIKE ?" +
                                                "         OR B.OPERATION_7 LIKE ?" +
                                                "         OR B.OPERATION_8 LIKE ?)";
                                }
                                if (fault.getResultId() != null) {
                                    sql = sql + " AND B.RESULT = ?";
                                }
                                if (fault.getHandleDetail() != null && !"".equals(fault.getHandleDetail())) {
                                    sql = sql + " AND C.HANDLE_DETAIL LIKE ?";
                                }
                                if (fault.getNote() != null && !"".equals(fault.getNote())) {
                                    sql = sql + " AND C.NOTE LIKE ?";
                                }
                                if (fault.getRepairState() != null) {
                                    sql = sql + " AND A.REPAIR_STATE = ?";
                                }
                                if (fault.getAgentId() != null) {
                                    sql = sql + " AND D.NOW_REPAIR_COMPANY_ID = ?";
                                }
                                if (fault.getInstallationId() != null) {
                                    sql = sql + " AND A.INSTALLATION_ID = ?";
                                }
                                if (fault.getManagementId() != null && !"".equals(fault.getManagementId())) {
                                    sql = sql + " AND A.MANAGEMENT_ID = ?";
                                }
                                
                                if(fault.getFaultMachineType()!=null && !"".equals(fault.getFaultMachineType())){
                                    sql = sql + " AND A.FAULT_MACHINE_TYPE = ?";
                                }
                                
                                if (fault.getSort() == null || "".equals(fault.getSort())) {
                                    sql = sql + " ORDER BY A.ID DESC  ";
                                } else {
                                    if ("managementId".equals(fault.getSort())) {
                                       sql = sql + " ORDER BY A.MANAGEMENT_ID ";
                                    } else if ("manufactureNo".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY D.MANUFACTURE_NO ";
                                    }else if ("faultType".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY J.NAME ";
                                    }else if ("productCategoryName".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY D.PRODUCT_CATEGORY_NAME ";
                                    }else if ("customerName".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY D.CUSTOMER_NAME ";
                                    } else if ("subComInstallPlace".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY D.BRANCH_COMPANY_NAME ";
                                    } else if ("model".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY D.PRODUCT_MODEL ";
                                    } else if ("repairState".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY A.REPAIR_STATE ";
                                    } else if ("occurDate".equals(fault.getSort())) {
                                        sql = sql + " ORDER BY A.OCCUR_DATE ";
                                    } else {
                                        sql = sql + " ORDER BY A.MANAGEMENT_ID ";
                                    }
                                    
                                    if("desc".equalsIgnoreCase(fault.getSortType())){
                                        sql = sql + " DESC ";
                                    }else{
                                        sql = sql + " ASC ";
                                    }
                                    
                                    if (!"managementId".equals(fault.getSort())){
                                        sql = sql + " , A.MANAGEMENT_ID ASC ";
                                    }
                                    
                                }
            sql = sql + " ) K " +
                        " WHERE" +
                        "   ROWNUM <= ?" +
                        " ) " +
                        " WHERE" +
                        "   NO > ?";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            Fault faultOut = null;
            if (!user.filter("fault_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            if (fault.getFaultType() != null) {
                pstmt.setInt(++index, fault.getFaultType());
            }
            if (fault.getAppearance() != null && !"".equals(fault.getAppearance())) {
                pstmt.setString(++index, fault.getAppearance() + "%");
            }
            if (fault.getReason() != null && !"".equals(fault.getReason())) {
                pstmt.setString(++index, fault.getReason() + "%");
            }
            if (fault.getStrategy() != null && !"".equals(fault.getStrategy())) {
                pstmt.setString(++index, fault.getStrategy() + "%");
            }
            if (fault.getErrorCode() != null && !"".equals(fault.getErrorCode())) {
                pstmt.setString(++index, fault.getErrorCode() + "%");
            }
            if (fault.getFaultPart() != null) {
                pstmt.setInt(++index, fault.getFaultPart());
            }
            if (fault.getFaultPartType() != null) {
                pstmt.setInt(++index, fault.getFaultPartType());
            }
            if (fault.getIsState() != null && !"".equals(fault.getIsState())) {
                pstmt.setString(++index, fault.getIsState());
            }
            if (fault.getSupportType() != null) {
                pstmt.setInt(++index, fault.getSupportType());
            }
            if (fault.getOccurDate() != null && !"".equals(fault.getOccurDate())) {
                pstmt.setString(++index, fault.getOccurDate());
            }
            if (fault.getReportDate() != null && !"".equals(fault.getReportDate())) {
                pstmt.setString(++index, fault.getReportDate());
            }
            if (fault.getFinishDate() != null && !"".equals(fault.getFinishDate())) {
                pstmt.setString(++index, fault.getFinishDate());
            }
            if (fault.getRepairCompanyId() != null) {
                pstmt.setInt(++index, fault.getRepairCompanyId());
            }
            if (fault.getOperatorName() != null && !"".equals(fault.getOperatorName())) {
                pstmt.setString(++index, fault.getOperatorName() + "%");
            }
            if (fault.getSupporterId() != null) {
                pstmt.setInt(++index, fault.getSupporterId());
            }
            if (fault.getOperation() != null && !"".equals(fault.getOperation())) {
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
                pstmt.setString(++index, fault.getOperation() + "%");
            }
            if (fault.getResultId() != null) {
                pstmt.setInt(++index, fault.getResultId());
            }
            if (fault.getHandleDetail() != null && !"".equals(fault.getHandleDetail())) {
                pstmt.setString(++index, fault.getHandleDetail() + "%");
            }
            if (fault.getNote() != null && !"".equals(fault.getNote())) {
                pstmt.setString(++index, fault.getNote() + "%");
            }
            if (fault.getRepairState() != null) {
                pstmt.setInt(++index, fault.getRepairState());
            }
            if (fault.getAgentId() != null) {
                pstmt.setInt(++index, fault.getAgentId());
            }
            if (fault.getInstallationId() != null) {
                pstmt.setInt(++index, fault.getInstallationId());
            }
            if (fault.getManagementId() != null && !"".equals(fault.getManagementId())) {
                pstmt.setString(++index, fault.getManagementId());
            }
            
            if(fault.getFaultMachineType()!=null && !"".equals(fault.getFaultMachineType())){
                pstmt.setString(++index, fault.getFaultMachineType());
            }
            
            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                faultOut = new Fault();
                index = 0;
                
                faultOut.setId(rs.getInt(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setInstallationId(rs.getInt(++index));
                faultOut.setFaultType(rs.getInt(++index));
                faultOut.setFaultTypeName(rs.getString(++index));
                faultOut.setOccurDate(rs.getString(++index));
                faultOut.setOccurTime(rs.getString(++index));
                faultOut.setReportDate(rs.getString(++index));
                faultOut.setReportTime(rs.getString(++index));
                faultOut.setRepairState(rs.getInt(++index));
                faultOut.setFinishDate(rs.getString(++index));
                faultOut.setFinishTime(rs.getString(++index));
                faultOut.setRepairCompanyId(rs.getInt(++index));
                faultOut.setRepairCompanyName(rs.getString(++index));
                faultOut.setSupporterId(rs.getInt(++index));
                faultOut.setSupporterName(rs.getString(++index));
                faultOut.setFollowerId(rs.getInt(++index));
                faultOut.setFollowerName(rs.getString(++index));
                faultOut.setOperatorName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setSupportType(rs.getInt(index));
                }
                faultOut.setSupportTypeName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setFaultPart(rs.getInt(index));
                }
                faultOut.setFaultPartName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setFaultPartType(rs.getInt(index));
                }
                faultOut.setFaultPartTypeName(rs.getString(++index));
                faultOut.setIsState(rs.getString(++index));
                faultOut.setApplicationVersion(rs.getInt(++index));
                faultOut.setApplicationVersionDetail1(rs.getString(++index));
                faultOut.setApplicationVersionDetail2(rs.getString(++index));
                faultOut.setLocked(rs.getInt(++index));
                faultOut.setDeleted(rs.getInt(++index));
                faultOut.setExclusiveKey(rs.getInt(++index));
                faultOut.setOccurCondiction1(rs.getInt(++index));
                faultOut.setOccurCondiction2(rs.getInt(++index));
                faultOut.setErrorCode(rs.getString(++index));
                faultOut.setRx278(rs.getString(++index));
                faultOut.setCounter(rs.getString(++index));
                faultOut.setAppearance(rs.getString(++index));
                faultOut.setReason(rs.getString(++index));
                faultOut.setStrategy(rs.getString(++index));
                faultOut.setStrategyDetail(rs.getString(++index));
                faultOut.setResultId(rs.getInt(++index));
                faultOut.setResult(rs.getString(++index));
                faultOut.setOperation1(rs.getString(++index));
                faultOut.setOperation2(rs.getString(++index));
                faultOut.setOperation3(rs.getString(++index));
                faultOut.setOperation4(rs.getString(++index));
                faultOut.setOperation5(rs.getString(++index));
                faultOut.setOperation6(rs.getString(++index));
                faultOut.setOperation7(rs.getString(++index));
                faultOut.setOperation8(rs.getString(++index));
                faultOut.setCashLeft(rs.getInt(++index));
                faultOut.setExceptionDisplay(rs.getInt(++index));
                faultOut.setDisplay(rs.getInt(++index));
                faultOut.setBackLight(rs.getInt(++index));
                faultOut.setDisplayContent(rs.getString(++index));
                faultOut.setInputable(rs.getInt(++index));
                faultOut.setKnockSound(rs.getInt(++index));
                faultOut.setReset(rs.getInt(++index));
                faultOut.setCutPower(rs.getInt(++index));
                faultOut.setRebootNormally(rs.getInt(++index));
                faultOut.setMotionCounter(rs.getString(++index));
                faultOut.setErrorNo(rs.getString(++index));
                faultOut.setNoRepon(rs.getString(++index));
                faultOut.setOtherDisplay(rs.getString(++index));
                faultOut.setTraceInfomation(rs.getInt(++index));
                faultOut.setFaultRecord(rs.getInt(++index));
                faultOut.setStatistics(rs.getInt(++index));
                faultOut.setTradeLog(rs.getInt(++index));
                faultOut.setApplicationVersion1(rs.getInt(++index));
                faultOut.setOthers(rs.getString(++index));
                faultOut.setCras(rs.getInt(++index));
                faultOut.setDras(rs.getInt(++index));
                faultOut.setMcuLog(rs.getInt(++index));
                faultOut.setSystemEvent(rs.getInt(++index));
                faultOut.setDbillbox(rs.getInt(++index));
                faultOut.setDcollect(rs.getInt(++index));
                faultOut.setCfep(rs.getInt(++index));
                faultOut.setCerr(rs.getInt(++index));
                faultOut.setApplyDate(rs.getString(++index));
                faultOut.setDeliverDate(rs.getString(++index));
                faultOut.setReplaceDate(rs.getString(++index));
                faultOut.setReceiveDate(rs.getString(++index));
                faultOut.setSubCompany(rs.getString(++index));
                faultOut.setInstallPlace(rs.getString(++index));
                faultOut.setManufactureNo(rs.getString(++index));
                faultOut.setModel(rs.getString(++index));
                faultOut.setProductCategoryName(rs.getString(++index));
                faultOut.setCustomerName(rs.getString(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultList");
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
                logSQLException(e, "getFaultList");
                throw e;
            }
        }
    }
    /**
     * get fault information of the installation
     * @author xiangzq
     * @param fault
     * @param user
     * @return List<Fault>
     * @throws SQLException
     */
    public List<Fault> getFaultListByInstallationId(Integer installationId, User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
        String sql =
                        " SELECT " +
                        "   A.ID, " +
                        "   A.MANAGEMENT_ID, " +
                        "   A.INSTALLATION_ID, " +
                        "   A.OCCUR_DATE, " +
                        "   A.OCCUR_TIME, " +
                        "   A.REPORT_DATE, " +
                        "   A.REPORT_TIME, " +
                        "   B.ERROR_CODE, " +
                        "   B.APPEARANCE, " +
                        "   B.STRATEGY, " +
                        "   B.STRATEGY_DETAIL, " +
                        "   B.RESULT," +
                        "   B.EC_ERROR_CODE" +
                        " FROM FAULT_TBL A" +
                        "   LEFT OUTER JOIN FAULT_DETAIL_TBL B ON A.ID=B.ID" +
                        "   LEFT OUTER JOIN INSTALLATION_VIEW D ON A.INSTALLATION_ID=D.ID AND A.REPORT_DATE >= D.START_DATE AND A.REPORT_DATE <= D.END_DATE " +
                        " WHERE 0=0 "+
                        " AND A.INSTALLATION_ID = ?";
                        if (!user.filter("fault_mng_all_data")) {
                            sql = sql + " AND (D.NOW_REPAIR_COMPANY_ID = ? )";
                        }
                        if (!user.hasPermission("FA001_32")) {
                            sql = sql + " AND A.DELETED = '0'";
                        }
                        if (!user.hasPermission("PR005_34")) {
                            sql = sql + " AND D.DELETED = '0'";
                        }
                        sql = sql + " ORDER BY A.MANAGEMENT_ID ";   
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            Fault faultOut = null;
            pstmt.setInt(++index, installationId);
            if (!user.filter("fault_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            } 
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                faultOut = new Fault();
                index = 0;
                
                faultOut.setId(rs.getInt(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setInstallationId(rs.getInt(++index));
                faultOut.setOccurDate(rs.getString(++index));
                faultOut.setOccurTime(rs.getString(++index));
                faultOut.setReportDate(rs.getString(++index));
                faultOut.setReportTime(rs.getString(++index));
                faultOut.setErrorCode(rs.getString(++index));
                faultOut.setAppearance(rs.getString(++index));      
                faultOut.setStrategy(rs.getString(++index));
                faultOut.setStrategyDetail(rs.getString(++index));
                faultOut.setResult(rs.getString(++index));
                faultOut.setEcErrorCode(rs.getString(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultListByInstallationId");
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
                logSQLException(e, "getFaultListByInstallationId");
                throw e;
            }
        }
    }  
    /**
     * get the selected fault information list
     * @author xiangzq
     * @param fault
     * @param user
     * @return List<Fault>
     * @throws SQLException
     */
    public List<Fault> getSelectedFaultList(Fault fault,String subSql, User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
            String sql =
                        " SELECT DISTINCT" +
                        "   A.ID, " +
                        "   A.MANAGEMENT_ID, " +
                        "   A.INSTALLATION_ID, " +
                        "   A.FAULT_TYPE, " +
                        "   J.NAME FAULT_TYPE_NAME, " +
                        "   A.OCCUR_DATE, " +
                        "   A.OCCUR_TIME, " +
                        "   A.REPORT_DATE, " +
                        "   A.REPORT_TIME, " +
                        "   A.REPAIR_STATE, " +
                        "   A.FINISH_DATE, " +
                        "   A.FINISH_TIME, " +
                        "   A.REPAIR_COMPANY_ID, " +
                        "   I.SHORT_NAME REPAIR_COMPANY_NAME, " +
                        "   A.SUPPORTER_ID, " +
                        "   E.FAMILY_NAME || E.GIVEN_NAME SUPPORTER_NAME, " +
                        "   A.FOLLOWER_ID," +
                        "   L.FAMILY_NAME || L.GIVEN_NAME FOLLOWERNAME," +
                        "   A.OPERATOR_NAME, " +
                        "   A.SUPPORT_TYPE, " +
                        "   F.NAME SUPPORT_TYPE_NAME, " +
                        "   A.FAULT_PART, " +
                        "   G.NAME FAULT_PART_NAME, " +
                        "   A.FAULT_PART_TYPE, " +
                        "   H.NAME FAULT_PART_TYPE_NAME, " +
                        "   A.IS_STATE, " +
                        "   A.APPLICATION_VERSION, " +
                        "   A.APPLICATION_VERSION_DETAIL_1, " +
                        "   A.APPLICATION_VERSION_DETAIL_2, " +
                        "   A.LOCKED, " +
                        "   A.DELETED, " +
                        "   A.EXCLUSIVE_KEY, " +
                        "   B.OCCUR_CONDICTION_1, " +
                        "   B.OCCUR_CONDICTION_2, " +
                        "   B.ERROR_CODE, " +
                        "   B.RX278, " +
                        "   B.COUNTER, " +
                        "   B.APPEARANCE, " +
                        "   B.REASON, " +
                        "   B.STRATEGY, " +
                        "   B.STRATEGY_DETAIL, " +
                        "   B.RESULT, " +
                        "   K.NAME RESULTNAME," +
                        "   B.OPERATION_1, " +
                        "   B.OPERATION_2, " +
                        "   B.OPERATION_3, " +
                        "   B.OPERATION_4, " +
                        "   B.OPERATION_5, " +
                        "   B.OPERATION_6, " +
                        "   B.OPERATION_7, " +
                        "   B.OPERATION_8, " +
                        "   B.CASH_LEFT, " +
                        "   B.EXCEPTION_DISPLAY, " +
                        "   B.DISPLAY, " +
                        "   B.BACK_LIGHT, " +
                        "   B.DISPLAY_CONTENT, " +
                        "   B.INPUTABLE, " +
                        "   B.KNOCK_SOUND, " +
                        "   B.RESET, " +
                        "   B.CUT_POWER, " +
                        "   B.REBOOT_NORMALLY, " +
                        "   B.MOTION_COUNTER, " +
                        "   B.ERROR_NO, " +
                        "   B.NO_REPON, " +
                        "   B.OTHER_DISPLAY, " +
                        "   B.TRACE_INFOMATION, " +
                        "   B.FAULT_RECORD, " +
                        "   B.STATISTICS, " +
                        "   B.TRADE_LOG, " +
                        "   B.APPLICATION_VERSION APPLICATION_VERSION_1, " +
                        "   B.OTHERS, " +
                        "   B.C_RAS, " +
                        "   B.D_RAS, " +
                        "   B.MCU_LOG, " +
                        "   B.SYSTEM_EVENT, " +
                        "   B.D_BILLBOX, " +
                        "   B.D_COLLECT, " +
                        "   B.C_FEP, " +
                        "   B.C_ERR, " +
                        "   B.APPLY_DATE," +
                        "   B.DELIVER_DATE," +
                        "   B.REPLACE_DATE," +
                        "   B.RECEIVE_DATE, " +
                        "   D.BRANCH_COMPANY_NAME," +
                        "   D.INSTALL_PLACE," +
                        "   D.MANUFACTURE_NO," +
                        "   D.PRODUCT_MODEL," +
                        "   D.PRODUCT_CATEGORY_NAME," +
                        "   D.CUSTOMER_NAME" +
                        " FROM FAULT_TBL A" +
                        "   LEFT OUTER JOIN FAULT_DETAIL_TBL B ON A.ID=B.ID" +
                        "   LEFT OUTER JOIN FAULT_HANDLE_TBL C ON A.ID=C.FAULT_ID" +
                        "   LEFT OUTER JOIN INSTALLATION_VIEW D ON A.INSTALLATION_ID=D.ID AND A.REPORT_DATE >= D.START_DATE AND A.REPORT_DATE <= D.END_DATE " +
                        "   LEFT OUTER JOIN USER_TBL E ON E.ID = A.SUPPORTER_ID AND E.ID > 0" +
                        "   LEFT OUTER JOIN SUPPORT_TYPE_TBL F ON F.ID = A.SUPPORT_TYPE" +
                        "   LEFT OUTER JOIN FAULT_PART_TBL G ON G.ID = A.FAULT_PART" +
                        "   LEFT OUTER JOIN FAULT_PART_TYPE_TBL H ON H.ID = A.FAULT_PART_TYPE" +
                        "   LEFT OUTER JOIN COMPANY_TBL I ON I.ID = A.REPAIR_COMPANY_ID AND I.ID > 0" +
                        "   LEFT OUTER JOIN FAULT_TYPE_TBL J ON J.ID = A.FAULT_TYPE" +
                        "   LEFT OUTER JOIN FAULT_STATUS_TBL K ON K.ID = B.RESULT" +
                        "   LEFT OUTER JOIN USER_TBL L ON L.ID = A.FOLLOWER_ID" +
                        " WHERE 0=0 ";
                        if (!user.filter("fault_mng_all_data")) {
                            sql = sql + " AND (D.NOW_REPAIR_COMPANY_ID = ? )";
                        }
                        if (!user.hasPermission("FA001_32")) {
                            sql = sql + " AND A.DELETED = '0'";
                        }
                        if (!user.hasPermission("PR005_34")) {
                            sql = sql + " AND D.DELETED = '0'";
                        }
                        sql = sql + subSql;
                        
                        if (fault.getSort() == null || "".equals(fault.getSort())) {
                            sql = sql + " ORDER BY A.OCCUR_DATE DESC,D.PRODUCT_MODEL ASC,A.MANAGEMENT_ID ASC  ";
                        } else {
                            if ("managementId".equals(fault.getSort())) {
                               sql = sql + " ORDER BY A.MANAGEMENT_ID ";
                            } else if ("manufactureNo".equals(fault.getSort())) {
                                sql = sql + " ORDER BY D.MANUFACTURE_NO ";
                            }else if ("faultType".equals(fault.getSort())) {
                                sql = sql + " ORDER BY J.NAME ";
                            }else if ("productCategoryName".equals(fault.getSort())) {
                                sql = sql + " ORDER BY D.PRODUCT_CATEGORY_NAME ";
                            }else if ("customerName".equals(fault.getSort())) {
                                sql = sql + " ORDER BY D.CUSTOMER_NAME ";
                            } else if ("subComInstallPlace".equals(fault.getSort())) {
                                sql = sql + " ORDER BY D.BRANCH_COMPANY_NAME ";
                            } else if ("model".equals(fault.getSort())) {
                                sql = sql + " ORDER BY D.PRODUCT_MODEL ";
                            } else if ("repairState".equals(fault.getSort())) {
                                sql = sql + " ORDER BY A.REPAIR_STATE ";
                            } else if ("occurDate".equals(fault.getSort())) {
                                sql = sql + " ORDER BY A.OCCUR_DATE ";
                            } else {
                                sql = sql + " ORDER BY A.MANAGEMENT_ID ";
                            }
                            
                            if("desc".equalsIgnoreCase(fault.getSortType())){
                                sql = sql + " DESC ";
                            }else{
                                sql = sql + " ASC ";
                            }
                            
                            if (!"managementId".equals(fault.getSort())){
                                sql = sql + " , A.MANAGEMENT_ID ASC ";
                            }
                            
                        }
                        
                     
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            Fault faultOut = null;
            if (!user.filter("fault_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                faultOut = new Fault();
                index = 0;
                
                faultOut.setId(rs.getInt(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setInstallationId(rs.getInt(++index));
                faultOut.setFaultType(rs.getInt(++index));
                faultOut.setFaultTypeName(rs.getString(++index));
                faultOut.setOccurDate(rs.getString(++index));
                faultOut.setOccurTime(rs.getString(++index));
                faultOut.setReportDate(rs.getString(++index));
                faultOut.setReportTime(rs.getString(++index));
                faultOut.setRepairState(rs.getInt(++index));
                faultOut.setFinishDate(rs.getString(++index));
                faultOut.setFinishTime(rs.getString(++index));
                faultOut.setRepairCompanyId(rs.getInt(++index));
                faultOut.setRepairCompanyName(rs.getString(++index));
                faultOut.setSupporterId(rs.getInt(++index));
                faultOut.setSupporterName(rs.getString(++index));
                faultOut.setFollowerId(rs.getInt(++index));
                faultOut.setFollowerName(rs.getString(++index));
                faultOut.setOperatorName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setSupportType(rs.getInt(index));
                }
                faultOut.setSupportTypeName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setFaultPart(rs.getInt(index));
                }
                faultOut.setFaultPartName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    faultOut.setFaultPartType(rs.getInt(index));
                }
                faultOut.setFaultPartTypeName(rs.getString(++index));
                faultOut.setIsState(rs.getString(++index));
                faultOut.setApplicationVersion(rs.getInt(++index));
                faultOut.setApplicationVersionDetail1(rs.getString(++index));
                faultOut.setApplicationVersionDetail2(rs.getString(++index));
                faultOut.setLocked(rs.getInt(++index));
                faultOut.setDeleted(rs.getInt(++index));
                faultOut.setExclusiveKey(rs.getInt(++index));
                faultOut.setOccurCondiction1(rs.getInt(++index));
                faultOut.setOccurCondiction2(rs.getInt(++index));
                faultOut.setErrorCode(rs.getString(++index));
                faultOut.setRx278(rs.getString(++index));
                faultOut.setCounter(rs.getString(++index));
                faultOut.setAppearance(rs.getString(++index));
                faultOut.setReason(rs.getString(++index));
                faultOut.setStrategy(rs.getString(++index));
                faultOut.setStrategyDetail(rs.getString(++index));
                faultOut.setResultId(rs.getInt(++index));
                faultOut.setResult(rs.getString(++index));
                faultOut.setOperation1(rs.getString(++index));
                faultOut.setOperation2(rs.getString(++index));
                faultOut.setOperation3(rs.getString(++index));
                faultOut.setOperation4(rs.getString(++index));
                faultOut.setOperation5(rs.getString(++index));
                faultOut.setOperation6(rs.getString(++index));
                faultOut.setOperation7(rs.getString(++index));
                faultOut.setOperation8(rs.getString(++index));
                faultOut.setCashLeft(rs.getInt(++index));
                faultOut.setExceptionDisplay(rs.getInt(++index));
                faultOut.setDisplay(rs.getInt(++index));
                faultOut.setBackLight(rs.getInt(++index));
                faultOut.setDisplayContent(rs.getString(++index));
                faultOut.setInputable(rs.getInt(++index));
                faultOut.setKnockSound(rs.getInt(++index));
                faultOut.setReset(rs.getInt(++index));
                faultOut.setCutPower(rs.getInt(++index));
                faultOut.setRebootNormally(rs.getInt(++index));
                faultOut.setMotionCounter(rs.getString(++index));
                faultOut.setErrorNo(rs.getString(++index));
                faultOut.setNoRepon(rs.getString(++index));
                faultOut.setOtherDisplay(rs.getString(++index));
                faultOut.setTraceInfomation(rs.getInt(++index));
                faultOut.setFaultRecord(rs.getInt(++index));
                faultOut.setStatistics(rs.getInt(++index));
                faultOut.setTradeLog(rs.getInt(++index));
                faultOut.setApplicationVersion1(rs.getInt(++index));
                faultOut.setOthers(rs.getString(++index));
                faultOut.setCras(rs.getInt(++index));
                faultOut.setDras(rs.getInt(++index));
                faultOut.setMcuLog(rs.getInt(++index));
                faultOut.setSystemEvent(rs.getInt(++index));
                faultOut.setDbillbox(rs.getInt(++index));
                faultOut.setDcollect(rs.getInt(++index));
                faultOut.setCfep(rs.getInt(++index));
                faultOut.setCerr(rs.getInt(++index));
                faultOut.setApplyDate(rs.getString(++index));
                faultOut.setDeliverDate(rs.getString(++index));
                faultOut.setReplaceDate(rs.getString(++index));
                faultOut.setReceiveDate(rs.getString(++index));
                faultOut.setSubCompany(rs.getString(++index));
                faultOut.setInstallPlace(rs.getString(++index));
                faultOut.setManufactureNo(rs.getString(++index));
                faultOut.setModel(rs.getString(++index));
                faultOut.setProductCategoryName(rs.getString(++index));
                faultOut.setCustomerName(rs.getString(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getSelectedFaultList");
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
                logSQLException(e, "getSelectedFaultList");
                throw e;
            }
        }
    }
    
    /**
     * get the unchecked and undeleted fault list for the main page
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   subSql
     * @return  List<Fault>
     * @throws  Exception
     */
    public List<Fault> getFaultList(String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
            String sql = 
                        " SELECT K.* FROM " +
                        " ("+
                        "   SELECT" +
                        "       A.ID," +
                        "       A.MANAGEMENT_ID," +
                        "       A.INSTALLATION_ID," +
                        "       A.REPAIR_STATE," +
                        "       A.OCCUR_DATE," +
                        "       D.INSTALL_PLACE," +
                        "       A.DELETED," +
                        "       D.MANUFACTURE_NO," +
                        "       J.NAME FAULT_TYPE_NAME," +
                        "       D.PRODUCT_MODEL," +
                        "       D.CUSTOMER_NAME," +
                        "       D.BRANCH_COMPANY_NAME " +
                        "   FROM FAULT_TBL A" +
                        "       LEFT OUTER JOIN INSTALLATION_VIEW D ON A.INSTALLATION_ID=D.ID AND A.REPORT_DATE >= D.START_DATE AND A.REPORT_DATE <= D.END_DATE " +
                        "       LEFT OUTER JOIN FAULT_TYPE_TBL J ON J.ID = A.FAULT_TYPE " +
                        "   WHERE " +
                        "   A.DELETED = 0 " +
                        "   AND A.REPAIR_STATE =0 ";
                            sql=sql+subSql;
                            sql=sql+" ORDER BY A.ID DESC " +
                        " ) K " +
                        " WHERE ROWNUM <= 20 ";
                        
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Fault faultOut = new Fault();
                index = 0;
                faultOut.setId(rs.getInt(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setInstallationId(rs.getInt(++index));
                faultOut.setRepairState(rs.getInt(++index));
                faultOut.setOccurDate(rs.getString(++index));
                faultOut.setInstallPlace(rs.getString(++index));
                faultOut.setDeleted(rs.getInt(++index));
                faultOut.setManufactureNo(rs.getString(++index));
                faultOut.setFaultTypeName(rs.getString(++index));
                faultOut.setModel(rs.getString(++index));
                faultOut.setCustomerName(rs.getString(++index));
                faultOut.setSubCompany(rs.getString(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultList");
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
                logSQLException(e, "getFaultList");
                throw e;
            }
        }
    }

    /**
     * get the fault list that agent company name is
     * agentCompanyName and fault affirm flag is affirmFlag
     * and report date between
     * startDate and endDate.
     * weekly report.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   startDate
     * @param   endDate
     * @param   agentCompanyName
     * @param   model
     * @param   affirmFlag
     * @return  List<Fault>
     * @throws  SQLException
     */
    public List<Fault> getFaultWeekly(
            String baseDate,
            String agentCompanyName,
            String model,
            Integer affirmFlag,
            Integer faultTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
            String sql = 
                "  SELECT " +
                "  A.ID, " +
                "  G.MODEL MODEL, "+
                "  F.SHORT_NAME NOW_REPAIR_COMPANY_NAME,"+
                "  C.NAME FAULT_PART_NAME,"+
                "  A.REPORT_DATE,"+
                "  E.SHORT_NAME CUSTOMER_NAME,"+
                "  B.MANUFACTURE_NO,"+
                "  A.MANAGEMENT_ID,"+
                "  D.NAME SUPPORT_TYPE_NAME ," +
                "  H.APPEARANCE APPEARANCE,"+
                "  H.STRATEGY STRATEGY,"+
                "  H.STRATEGY_DETAIL STRATEGY_DETAIL,"+
                "  J.NAME RESULT," +
                "  H.RECEIVE_DATE RECEIVE_DATE,"+
                "  H.APPLY_DATE APPLY_DATE," +
                "  H.REPLACE_DATE REPLACE_DATE," +
                "  H.DELIVER_DATE DELIVER_DATE," +
                "  H.TRACE_INFOMATION, " +
                "  H.FAULT_RECORD, " +
                "  H.STATISTICS, " +
                "  H.TRADE_LOG," +
                "  H.APPLICATION_VERSION "+
                "  FROM  "+
                "  FAULT_TBL A "+
                "  LEFT OUTER JOIN  INSTALLATION_TBL B ON A.INSTALLATION_ID=B.ID"+
                "  LEFT OUTER JOIN  FAULT_PART_TBL C ON A.FAULT_PART=C.ID  "+
                "  LEFT OUTER JOIN  SUPPORT_TYPE_TBL D ON A.SUPPORT_TYPE=D.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL E ON B.CUSTOMER_ID=E.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL F ON B.NOW_REPAIR_COMPANY_ID=F.ID" +
                "  LEFT OUTER JOIN  PRODUCT_TBL G ON B.PRODUCT_ID=G.ID " +
                "  LEFT OUTER JOIN  FAULT_DETAIL_TBL H ON A.ID=H.ID " +
                "  LEFT OUTER JOIN  FAULT_STATUS_TBL J ON H.RESULT=J.ID " +
                "  LEFT OUTER JOIN  PRODUCT_CATEGORY_TBL M ON G.PRODUCT_CATEGORY_ID=M.ID " +
                "  WHERE  A.REPORT_DATE<=? " +
                "  AND F.SHORT_NAME =? " +
                "  AND M.NAME=? " +
                "  AND A.REPAIR_STATE=?" +
                "  AND A.DELETED=0 " ;
                if(faultTypeId!=null){
                    sql = sql + " AND A.FAULT_TYPE = ? ";
                }
                sql = sql +  "  ORDER BY  A.MANAGEMENT_ID ";
               
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, baseDate);
            pstmt.setString(++index, agentCompanyName);
            pstmt.setString(++index, model);
            pstmt.setInt(++index,affirmFlag);
            if(faultTypeId!=null){
               pstmt.setInt(++index,faultTypeId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Fault faultOut = new Fault();
                index = 0;
                faultOut.setId(rs.getInt(++index));
                faultOut.setModel(rs.getString(++index));
                faultOut.setRepairCompanyName(rs.getString(++index));
                faultOut.setFaultPartName(rs.getString(++index));
                faultOut.setReportDate(rs.getString(++index));
                faultOut.setCustomerCompanyName(rs.getString(++index));
                faultOut.setManufactureNo(rs.getString(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setSupportTypeName(rs.getString(++index));
                faultOut.setAppearance(rs.getString(++index));
                faultOut.setStrategy(rs.getString(++index));
                faultOut.setStrategyDetail(rs.getString(++index));
                faultOut.setResult(rs.getString(++index));
                faultOut.setReceiveDate(rs.getString(++index));
                faultOut.setApplyDate(rs.getString(++index));
                faultOut.setReplaceDate(rs.getString(++index));
                faultOut.setDeliverDate(rs.getString(++index));
                faultOut.setTraceInfomation(rs.getInt(++index));
                faultOut.setFaultRecord(rs.getInt(++index));
                faultOut.setStatistics(rs.getInt(++index));
                faultOut.setTradeLog(rs.getInt(++index));
                faultOut.setApplicationVersion(rs.getInt(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultWeekly");
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
                logSQLException(e, "getFaultWeekly");
                throw e;
            }
        }
    }
    
    /**
     * get the fault list that agent company name is
     * agentCompanyName and report date between
     * startDate and endDate.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   startDate
     * @param   endDate
     * @param   agentCompanyName
     * @param   model
     * @return  List<Fault>
     * @throws  SQLException
     */
    public List<Fault> getFaultWeekly(
            String startDate,
            String endDate,
            String agentCompanyName,
            String model,
            Integer faultTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Fault> list = new ArrayList<Fault>();

        try {
            // Start UOC
            String sql = 
                " SELECT " +
                "  A.ID, " +
                "  G.MODEL MODEL, "+
                "  F.SHORT_NAME NOW_REPAIR_COMPANY_NAME,"+
                "  C.NAME FAULT_PART_NAME,"+
                "  A.REPORT_DATE,"+
                "  E.SHORT_NAME CUSTOMER_NAME,"+
                "  B.MANUFACTURE_NO,"+
                "  A.MANAGEMENT_ID,"+
                "  D.NAME SUPPORT_TYPE_NAME ," +
                "  H.APPEARANCE APPEARANCE,"+
                "  H.STRATEGY STRATEGY,"+
                "  H.STRATEGY_DETAIL STRATEGY_DETAIL,"+
                "  J.NAME RESULT," +
                "  H.RECEIVE_DATE RECEIVE_DATE,"+
                "  H.APPLY_DATE APPLY_DATE," +
                "  H.REPLACE_DATE REPLACE_DATE," +
                "  H.DELIVER_DATE DELIVER_DATE," +
                "  H.TRACE_INFOMATION, " +
                "  H.FAULT_RECORD, " +
                "  H.STATISTICS, " +
                "  H.TRADE_LOG," +
                "  H.APPLICATION_VERSION "+
                "  FROM  "+
                "  FAULT_TBL A "+
                "  LEFT OUTER JOIN  INSTALLATION_TBL B ON A.INSTALLATION_ID=B.ID"+
                "  LEFT OUTER JOIN  FAULT_PART_TBL C ON A.FAULT_PART=C.ID  "+
                "  LEFT OUTER JOIN  SUPPORT_TYPE_TBL D ON A.SUPPORT_TYPE=D.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL E ON B.CUSTOMER_ID=E.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL F ON B.NOW_REPAIR_COMPANY_ID=F.ID" +
                "  LEFT OUTER JOIN  PRODUCT_TBL G ON B.PRODUCT_ID=G.ID " +
                "  LEFT OUTER JOIN  FAULT_DETAIL_TBL H ON A.ID=H.ID " +
                "  LEFT OUTER JOIN  FAULT_STATUS_TBL J ON H.RESULT=J.ID " +
                "  LEFT OUTER JOIN  PRODUCT_CATEGORY_TBL M ON G.PRODUCT_CATEGORY_ID=M.ID " +
                "  WHERE A.REPORT_DATE>=? AND A.REPORT_DATE<=? " +
                "  AND F.SHORT_NAME =? " +
                "  AND M.NAME=? " +
                "  AND A.DELETED=0 " ;
                if(faultTypeId!=null){
                    sql = sql + " AND A.FAULT_TYPE = ? ";
                }
                sql = sql + "  ORDER BY A.MANAGEMENT_ID ";

            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, startDate);
            pstmt.setString(++index, endDate);
            pstmt.setString(++index, agentCompanyName);
            pstmt.setString(++index,  model);
            if(faultTypeId!=null){
                pstmt.setInt(++index,  faultTypeId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Fault faultOut = new Fault();
                index = 0;
                faultOut.setId(rs.getInt(++index));
                faultOut.setModel(rs.getString(++index));
                faultOut.setRepairCompanyName(rs.getString(++index));
                faultOut.setFaultPartName(rs.getString(++index));
                faultOut.setReportDate(rs.getString(++index));
                faultOut.setCustomerCompanyName(rs.getString(++index));
                faultOut.setManufactureNo(rs.getString(++index));
                faultOut.setManagementId(rs.getString(++index));
                faultOut.setSupportTypeName(rs.getString(++index));
                faultOut.setAppearance(rs.getString(++index));
                faultOut.setStrategy(rs.getString(++index));
                faultOut.setStrategyDetail(rs.getString(++index));
                faultOut.setResult(rs.getString(++index));
                faultOut.setReceiveDate(rs.getString(++index));
                faultOut.setApplyDate(rs.getString(++index));
                faultOut.setReplaceDate(rs.getString(++index));
                faultOut.setDeliverDate(rs.getString(++index));
                faultOut.setTraceInfomation(rs.getInt(++index));
                faultOut.setFaultRecord(rs.getInt(++index));
                faultOut.setStatistics(rs.getInt(++index));
                faultOut.setTradeLog(rs.getInt(++index));
                faultOut.setApplicationVersion(rs.getInt(++index));
                list.add(faultOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultWeekly");
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
                logSQLException(e, "getFaultWeekly");
                throw e;
            }
        }
    }
    
    /**
     * get the agent company name list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   baseDate
     * @return  List<String>
     * @throws  SQLException
     */
    public List<String> getRepairCompanyNameListForFaultWeekly(String baseDate,Integer faultTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();

        try {
            // Start UOC
            String sql = 
               " SELECT " +
                "  DISTINCT F.SHORT_NAME REPAIR_COMPANY_NAME "+
                "  FROM  "+
                "  FAULT_TBL A "+
                "  LEFT OUTER JOIN  INSTALLATION_TBL B ON A.INSTALLATION_ID=B.ID"+
                "  LEFT OUTER JOIN  FAULT_PART_TBL C ON A.FAULT_PART=C.ID  "+
                "  LEFT OUTER JOIN  SUPPORT_TYPE_TBL D ON A.SUPPORT_TYPE=D.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL E ON B.CUSTOMER_ID=E.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL F ON B.NOW_REPAIR_COMPANY_ID=F.ID" +
                "  LEFT OUTER JOIN  PRODUCT_TBL G ON B.PRODUCT_ID=G.ID " +
                "  LEFT OUTER JOIN  FAULT_DETAIL_TBL H ON A.ID=H.ID " +
                "  WHERE REPORT_DATE<=? " +
                "  AND A.DELETED=0 " ;
            if(faultTypeId!=null){
                sql = sql + " AND A.FAULT_TYPE = ? ";
            }
                sql = sql + "  ORDER BY REPAIR_COMPANY_NAME ";
               
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, baseDate);
            if(faultTypeId!=null){
                pstmt.setInt(++index, faultTypeId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                String agentCompanyName=rs.getString(++index);
                list.add(agentCompanyName);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getAgentCompanyNameListForFaultWeekly");
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
                logSQLException(e, "getAgentCompanyNameListForFaultWeekly");
                throw e;
            }
        }
    }

    /**
     * get the product model list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   startDate
     * @param   endDate
     * @return  List<String>
     * @throws  SQLException
     */
    public List<String> getProductModelListForFaultWeekly(
            String startDate,
            String endDate,
            String repairCompanyName,
            Integer faultTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();

        try {
            // Start UOC
            String sql = 
               " SELECT " +
                "  DISTINCT M.NAME MODEL "+
                "  FROM  "+
                "  FAULT_TBL A "+
                "  LEFT OUTER JOIN  INSTALLATION_TBL B ON A.INSTALLATION_ID=B.ID"+
                "  LEFT OUTER JOIN  FAULT_PART_TBL C ON A.FAULT_PART=C.ID  "+
                "  LEFT OUTER JOIN  SUPPORT_TYPE_TBL D ON A.SUPPORT_TYPE=D.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL E ON B.CUSTOMER_ID=E.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL F ON B.NOW_REPAIR_COMPANY_ID=F.ID" +
                "  LEFT OUTER JOIN  PRODUCT_TBL G ON B.PRODUCT_ID=G.ID " +
                "  LEFT OUTER JOIN  FAULT_DETAIL_TBL H ON A.ID=H.ID" +
                "  LEFT OUTER JOIN  PRODUCT_CATEGORY_TBL M ON G.PRODUCT_CATEGORY_ID=M.ID " +
                "  WHERE REPORT_DATE>=? AND REPORT_DATE<=? " +
                "  AND F.SHORT_NAME=? " +
                "  AND A.DELETED=0 " ;
                if(faultTypeId!=null){
                    sql = sql + " AND A.FAULT_TYPE = ? ";
                }
                sql = sql + "  ORDER BY MODEL ";
               
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, startDate);
            pstmt.setString(++index, endDate);
            pstmt.setString(++index, repairCompanyName);
            if(faultTypeId!=null){
                pstmt.setInt(++index, faultTypeId);
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                String model=rs.getString(++index);
                list.add(model);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getProductModelListForFaultWeekly");
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
                logSQLException(e, "getProductModelListForFaultWeekly");
                throw e;
            }
        }
    }
    
    
    /**
     * get the product model list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   baseDate
     * @return  List<String>
     * @throws  SQLException
     */
    public List<String> getProductModelListForFaultWeekly(
            String baseDate,
            String repairCompanyName,
            Integer affirmFlag,
            Integer faultTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<String>();

        try {
            // Start UOC
            String sql = 
               " SELECT " +
                "  DISTINCT M.NAME MODEL "+
                "  FROM  "+
                "  FAULT_TBL A "+
                "  LEFT OUTER JOIN  INSTALLATION_TBL B ON A.INSTALLATION_ID=B.ID"+
                "  LEFT OUTER JOIN  FAULT_PART_TBL C ON A.FAULT_PART=C.ID  "+
                "  LEFT OUTER JOIN  SUPPORT_TYPE_TBL D ON A.SUPPORT_TYPE=D.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL E ON B.CUSTOMER_ID=E.ID"+
                "  LEFT OUTER JOIN  COMPANY_TBL F ON B.NOW_REPAIR_COMPANY_ID=F.ID" +
                "  LEFT OUTER JOIN  PRODUCT_TBL G ON B.PRODUCT_ID=G.ID " +
                "  LEFT OUTER JOIN  FAULT_DETAIL_TBL H ON A.ID=H.ID " +
                "  LEFT OUTER JOIN  PRODUCT_CATEGORY_TBL M ON G.PRODUCT_CATEGORY_ID=M.ID " +
                "  WHERE  REPORT_DATE<=? " +
                "  AND F.SHORT_NAME=? " +
                "  AND A.REPAIR_STATE=? " +
                "  AND A.DELETED=0 " ;
            if(faultTypeId!=null){
                sql = sql + " AND A.FAULT_TYPE = ? ";
            }
               sql = sql +  "  ORDER BY MODEL ";
               
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, baseDate);
            pstmt.setString(++index, repairCompanyName);
            pstmt.setInt(++index, affirmFlag);
            if(faultTypeId!=null){
                pstmt.setInt(++index, faultTypeId);
            }
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                String model=rs.getString(++index);
                list.add(model);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getProductModelListForFaultWeekly");
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
                logSQLException(e, "getProductModelListForFaultWeekly");
                throw e;
            }
        }
    }

    
    /**
     * get fault count by installation id
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   InstallId
     * @return  int
     * @throws  SQLException
     */
    public int getFaultCountByInstallId(Integer InstallId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " COUNT(*) " +
                        " FROM " + 
                        " FAULT_TBL " + 
                        " WHERE " + 
                        " INSTALLATION_ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, InstallId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultCountByInstallId");
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
                logSQLException(e, "getFaultCountByInstallId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by  repair company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   repairCompanyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByRepairCompanyId(Integer repairCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " FAULT_TBL " +
                       " WHERE  " +
                       " REPAIR_COMPANY_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, repairCompanyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByRepairCompanyId");
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
                logSQLException(e, "getCountByRepairCompanyId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by id
     * @author sunyx
     * @param id
     * @return int
     * @throws SQLException
     */
    public int getCountById(Integer id) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = "SELECT" +
                        " COUNT(*)" +
                        " FROM " +
                        " FAULT_TBL" +
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
     * get count by faultPartId
     * @author xiangzq
     * @param id
     * @return int
     * @throws SQLException
     */
    public int getCountByFaultPartId(Integer faultPartId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = "SELECT" +
                        " COUNT(*)" +
                        " FROM " +
                        " FAULT_TBL" +
                        " WHERE" +
                        " FAULT_PART = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultPartId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByFaultPartId");
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
                logSQLException(e, "getCountByFaultPartId");
                throw e;
            }
        }

        return count;
    }

    /**
     * get count by management ID.
     * @author  luyan
     * @since   1.0
     * @param   managementId management ID
     * @return  count
     * @throws  SQLException
     */
    public int getCountByManagementId(String managementId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " + 
                "   COUNT(*) " +
                " FROM " + 
                "   FAULT_TBL " + 
                " WHERE " + 
                "   MANAGEMENT_ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, managementId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByManagementId");
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
                logSQLException(e, "getCountByManagementId");
                throw e;
            }
        }

        return count;
    }

    /**
     * get fault header for register.
     * @author  luyan
     * @since   1.0
     * @param   installationId installation ID
     * @param   reportDate report date
     * @return  fault header information
     * @throws  SQLException
     */
    public Fault getFaultHeaderFr(Integer installationId, String reportDate) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Fault fault = new Fault();

        try {
            // Start UOC
            String sql = " SELECT " +
                "   PRODUCT_TBL.MODEL, " +
                "   PRODUCT_CATEGORY_TBL.NAME PRODUCT_CATEGORY_NAME, " +
                "   COMP_VIEW.PROVINCE_NAME || ' ' || COMP_VIEW.CITY_NAME, " +
                "   COMP_VIEW.SHORT_NAME COMPANY_SHORT_NAME, " +
                "   INSTALLATION_VIEW.MANUFACTURE_NO, " +
                "   PURPOSE_TBL.NAME PURPOSE_NAME, " +
                "   INSTALLATION_VIEW.GUARANTEE_START_DATE, " +
                "   INSTALLATION_VIEW.GUARANTEE_END_DATE, " +
//                "   INSTALLATION_VIEW.BRM_EP_VER, " +
//                "   INSTALLATION_VIEW.BV_EP_VER, " +
                "   INSTALLATION_VIEW.INSTALL_DATE, " +
                "   INSTALLATION_VIEW.BRANCH_COMPANY_NAME, " +
                "   INSTALLATION_VIEW.INSTALL_PLACE, " +
                "   INSTALLATION_VIEW.NOW_REPAIR_COMPANY_ID, " +
                "   INSTALLATION_VIEW.NOW_REPAIR_COMPANY_NAME " +
                " FROM " +
                "   ( SELECT " +
                "       INSTALLATION_TBL.ID, " +
                "       INSTALLATION_TBL.PRODUCT_ID, " +
                "       INSTALLATION_TBL.CUSTOMER_ID, " +
                "       INSTALLATION_TBL.MANUFACTURE_NO, " +
                "       INSTALLATION_TBL.PURPOSE, " +
                "       INSTALLATION_TBL.NOW_REPAIR_COMPANY_ID, " +
                "       COMPANY_VIEW.SHORT_NAME NOW_REPAIR_COMPANY_NAME, " +
                "       INSTALLATION_TBL.GUARANTEE_START_DATE, " +
                "       INSTALLATION_TBL.GUARANTEE_END_DATE, " +
//                "       INSTALLATION_TBL.BRM_EP_VER, " +
//                "       INSTALLATION_TBL.BV_EP_VER, " +
                "       INSTALLATION_TBL.INSTALL_DATE, " +
                "       INSTALLATION_TBL.BRANCH_COMPANY_NAME, " +
                "       INSTALLATION_TBL.INSTALL_PLACE " +
                "     FROM " +
                "       INSTALLATION_TBL " +
                "     INNER JOIN " +
                "       COMPANY_VIEW " +
                "     ON " +
                "       INSTALLATION_TBL.NOW_REPAIR_COMPANY_ID = COMPANY_VIEW.ID " +
                "     WHERE " +
                "       INSTALLATION_TBL.START_DATE <= ? " +
                "       AND INSTALLATION_TBL.END_DATE >= ? " +
                "     UNION" +
                "     SELECT " +
                "       INSTALLATION_HISTORY_TBL.ID, " +
                "       INSTALLATION_HISTORY_TBL.PRODUCT_ID, " +
                "       INSTALLATION_HISTORY_TBL.CUSTOMER_ID, " +
                "       INSTALLATION_HISTORY_TBL.MANUFACTURE_NO, " +
                "       INSTALLATION_HISTORY_TBL.PURPOSE, " +
                "       INSTALLATION_HISTORY_TBL.NOW_REPAIR_COMPANY_ID, " +
                "       COMPANY_VIEW.SHORT_NAME NOW_REPAIR_COMPANY_NAME, " +
                "       INSTALLATION_HISTORY_TBL.GUARANTEE_START_DATE, " +
                "       INSTALLATION_HISTORY_TBL.GUARANTEE_END_DATE, " +
//                "       INSTALLATION_HISTORY_TBL.BRM_EP_VER, " +
//                "       INSTALLATION_HISTORY_TBL.BV_EP_VER, " +
                "       INSTALLATION_HISTORY_TBL.INSTALL_DATE, " +
                "       INSTALLATION_HISTORY_TBL.BRANCH_COMPANY_NAME, " +
                "       INSTALLATION_HISTORY_TBL.INSTALL_PLACE " +
                "     FROM " +
                "       INSTALLATION_HISTORY_TBL " +
                "     INNER JOIN " +
                "       COMPANY_VIEW " +
                "     ON " +
                "       INSTALLATION_HISTORY_TBL.NOW_REPAIR_COMPANY_ID = COMPANY_VIEW.ID " +
                "     WHERE " +
                "       INSTALLATION_HISTORY_TBL.START_DATE <= ? " +
                "       AND INSTALLATION_HISTORY_TBL.END_DATE >= ? ) INSTALLATION_VIEW " +
                " INNER JOIN " +
                "   PRODUCT_TBL " +
                " ON " +
                "   PRODUCT_TBL.ID = INSTALLATION_VIEW.PRODUCT_ID " +
                " INNER JOIN " +
                "   PRODUCT_CATEGORY_TBL " +
                " ON " +
                "   PRODUCT_CATEGORY_TBL.ID = PRODUCT_TBL.PRODUCT_CATEGORY_ID " +
                " INNER JOIN " +
                "   PURPOSE_TBL " +
                " ON " +
                "   PURPOSE_TBL.ID = INSTALLATION_VIEW.PURPOSE " +
                " LEFT JOIN " +
                "   ( SELECT " +
                "       COMPANY_TBL.ID, " +
                "       COMPANY_TBL.MAIN_COMPANY_NAME, " +
                "       COMPANY_TBL.SHORT_NAME, " +
                "       COMPANY_TBL.START_DATE, " +
                "       COMPANY_TBL.END_DATE, " +
                "       CITY_TBL.NAME CITY_NAME, " +
                "       PROVINCE_TBL.NAME PROVINCE_NAME " +
                "     FROM " +
                "       COMPANY_TBL, CITY_TBL, PROVINCE_TBL " +
                "     WHERE " +
                "       COMPANY_TBL.CITY_ID = CITY_TBL.ID " +
                "       AND CITY_TBL.PROVINCE_ID = PROVINCE_TBL.ID " +
                "     UNION " +
                "     SELECT " +
                "       COMPANY_HISTORY_TBL.ID, " +
                "       COMPANY_HISTORY_TBL.MAIN_COMPANY_NAME, " +
                "       COMPANY_HISTORY_TBL.SHORT_NAME, " +
                "       COMPANY_HISTORY_TBL.START_DATE, " +
                "       COMPANY_HISTORY_TBL.END_DATE, " +
                "       CITY_TBL.NAME CITY_NAME, " +
                "       PROVINCE_TBL.NAME PROVINCE_NAME " +
                "     FROM " +
                "       COMPANY_HISTORY_TBL, CITY_TBL, PROVINCE_TBL " +
                "     WHERE " +
                "       COMPANY_HISTORY_TBL.CITY_ID = CITY_TBL.ID " +
                "       AND CITY_TBL.PROVINCE_ID = PROVINCE_TBL.ID ) COMP_VIEW " +
                " ON " +
                "    COMP_VIEW.ID = INSTALLATION_VIEW.CUSTOMER_ID " +
                "    AND COMP_VIEW.START_DATE <= ? " +
                "    AND COMP_VIEW.END_DATE >= ? " +
                " WHERE " +
                "   INSTALLATION_VIEW.ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setString(++index, reportDate);
            pstmt.setString(++index, reportDate);
            pstmt.setString(++index, reportDate);
            pstmt.setString(++index, reportDate);
            pstmt.setString(++index, reportDate);
            pstmt.setString(++index, reportDate);
            pstmt.setInt(++index, installationId);

            rs = pstmt.executeQuery();

            index = 0;
            if (rs.next()) {
                fault.setModel(rs.getString(++index));
                fault.setProductCategoryName(rs.getString(++index));
                fault.setCityName(rs.getString(++index));
                fault.setCustomerName(rs.getString(++index));
                fault.setManufactureNo(rs.getString(++index));
                fault.setPurpose(rs.getString(++index));
                fault.setGuaranteeStartDate(rs.getString(++index));
                fault.setGuaranteeEndDate(rs.getString(++index));
//                fault.setBrmEpVer(rs.getString(++index));
//                fault.setBvEpVer(rs.getString(++index));
                fault.setInstallDate(rs.getString(++index));
                fault.setSubCompany(rs.getString(++index));
                fault.setInstallPlace(rs.getString(++index));
                fault.setRepairCompanyId(rs.getInt(++index));
                fault.setRepairCompanyName(rs.getString(++index));
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultHeaderFr");
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
                logSQLException(e, "getFaultHeaderFr");
                throw e;
            }
        }

        return fault;
    }

    /**
     * get management ID list by company code and report date.
     * @author luyan
     * @since 1.0
     * @param companyCode company code
     * @param reportDate report date
     * @return management ID list
     * @throws SQLException
     */
    public ArrayList<String> getManagementIdList(String companyCode, String reportDate) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<String> managementIdList = new ArrayList<String>();

        try {
            // Start UOC
            String sql = " SELECT " +
                "   MANAGEMENT_ID " +
                " FROM " +
                "   FAULT_TBL " +
                " WHERE " +
                "   MANAGEMENT_ID LIKE ? " +
                "   AND REPORT_DATE = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setString(++index, companyCode);
            pstmt.setString(++index, reportDate);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                String tmpMngId = rs.getString(++index);
                managementIdList.add(tmpMngId);
            }
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

        return managementIdList;
    }

    /**
     * insert fault information.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void insert(Fault faultIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " INSERT INTO FAULT_TBL ( " +
                "   ID, " +
                "   MANAGEMENT_ID, " +
                "   INSTALLATION_ID, " +
                "   FAULT_TYPE, " +
                "   OCCUR_DATE, " +
                "   OCCUR_TIME, " +
                "   REPORT_DATE, " +
                "   REPORT_TIME, " +
                "   REPAIR_STATE, " +
                "   FINISH_DATE, " +
                "   FINISH_TIME, " +
                "   REPAIR_COMPANY_ID, " +
                "   SUPPORTER_ID, " +
                "   FOLLOWER_ID, " +
                "   OPERATOR_NAME, " +
                "   SUPPORT_TYPE, " +
                "   FAULT_PART, " +
                "   FAULT_PART_TYPE, " +
                "   IS_STATE, " +
                "   APPLICATION_VERSION, " +
                "   APPLICATION_VERSION_DETAIL_1, " +
                "   APPLICATION_VERSION_DETAIL_2, " +
                "   LOCKED, " +
                "   DELETED, " +
                "   CREATE_TIME, " +
                "   CREATOR_ID, " +
                "   MODIFY_TIME, " +
                "   MODIFIER_ID," +
                "   FAULT_MACHINE_TYPE  " +
                " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,? ) ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getId());
            pstmt.setString(++index, faultIn.getManagementId());
            pstmt.setInt(++index, faultIn.getInstallationId());
            pstmt.setInt(++index, faultIn.getFaultType());
            pstmt.setString(++index, faultIn.getOccurDate());
            pstmt.setString(++index, faultIn.getOccurTime());
            pstmt.setString(++index, faultIn.getReportDate());
            pstmt.setString(++index, faultIn.getReportTime());
            pstmt.setInt(++index, faultIn.getRepairState());
            pstmt.setString(++index, faultIn.getFinishDate());
            pstmt.setString(++index, faultIn.getFinishTime());
            pstmt.setInt(++index, faultIn.getRepairCompanyId());
            if (faultIn.getSupporterId() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, faultIn.getSupporterId());
            }
            if (faultIn.getFollowerId() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, faultIn.getFollowerId());
            }
            pstmt.setString(++index, faultIn.getOperatorName());
            if (faultIn.getSupportType() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getSupportType()));
            }
            if (faultIn.getFaultPart() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getFaultPart()));
            }
            if (faultIn.getFaultPartType() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getFaultPartType()));
            }
            pstmt.setString(++index, faultIn.getIsState());
            pstmt.setInt(++index, faultIn.getApplicationVersion());
            pstmt.setString(++index, faultIn.getApplicationVersionDetail1());
            pstmt.setString(++index, faultIn.getApplicationVersionDetail2());
            pstmt.setInt(++index, faultIn.getLocked());
            pstmt.setInt(++index, faultIn.getDeleted());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, faultIn.getCreatorId());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, faultIn.getCreatorId());
            pstmt.setString(++index, faultIn.getFaultMachineType());
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
     * get fault detail information by fault ID.
     * @author  luyan
     * @since   1.0
     * @param   faultId fault ID
     * @return  fault detail information
     * @throws  SQLException
     */
    public Fault getFaultDetailById(Integer faultId, String managementIdIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Fault fault = new Fault();

        try {
            // Start UOC
            String sql = " SELECT " +
                "   FAULT_TBL.ID, " +
                "   FAULT_TBL.MANAGEMENT_ID, " +
                "   FAULT_TBL.INSTALLATION_ID, " +
                "   FAULT_TBL.FAULT_TYPE, " +
                "   FAULT_TYPE_TBL.NAME FAULT_TYPE_NAME, " +
                "   FAULT_TBL.OCCUR_DATE, " +
                "   FAULT_TBL.OCCUR_TIME, " +
                "   FAULT_TBL.REPORT_DATE, " +
                "   FAULT_TBL.REPORT_TIME, " +
                "   FAULT_TBL.REPAIR_STATE, " +
                "   FAULT_TBL.FINISH_DATE, " +
                "   FAULT_TBL.FINISH_TIME, " +
                "   FAULT_TBL.REPAIR_COMPANY_ID, " +
                "   COMPANY_TBL.SHORT_NAME REPAIR_COMPANY_NAME, " +
                "   FAULT_TBL.SUPPORTER_ID, " +
                "   USER_TBL.FAMILY_NAME || USER_TBL.GIVEN_NAME SUPPORTER_NAME, " +
                "   FAULT_TBL.FOLLOWER_ID, " +
                "   USER_TBL1.FAMILY_NAME || USER_TBL1.GIVEN_NAME FOLLOWER_NAME, " +
                "   FAULT_TBL.OPERATOR_NAME, " +
                "   FAULT_TBL.SUPPORT_TYPE, " +
                "   SUPPORT_TYPE_TBL.NAME SUPPORT_TYPE_NAME, " +
                "   FAULT_TBL.FAULT_PART, " +
                "   FAULT_PART_TBL.NAME FAULT_PART_NAME, " +
                "   FAULT_TBL.FAULT_PART_TYPE, " +
                "   FAULT_PART_TYPE_TBL.NAME FAULT_PART_TYPE_NAME, " +
                "   FAULT_TBL.IS_STATE, " +
                "   FAULT_TBL.APPLICATION_VERSION, " +
                "   FAULT_TBL.APPLICATION_VERSION_DETAIL_1, " +
                "   FAULT_TBL.APPLICATION_VERSION_DETAIL_2, " +
                "   FAULT_TBL.LOCKED, " +
                "   FAULT_TBL.DELETED, " +
                "   FAULT_TBL.EXCLUSIVE_KEY," +
                "   FAULT_TBL.FAULT_MACHINE_TYPE, " +
                "   FAULT_DETAIL_TBL.OCCUR_CONDICTION_1, " +
                "   FAULT_DETAIL_TBL.OCCUR_CONDICTION_2, " +
                "   FAULT_DETAIL_TBL.ERROR_CODE, " +
                "   FAULT_DETAIL_TBL.RX278, " +
                "   FAULT_DETAIL_TBL.COUNTER, " +
                "   FAULT_DETAIL_TBL.APPEARANCE, " +
                "   FAULT_DETAIL_TBL.REASON, " +
                "   FAULT_DETAIL_TBL.STRATEGY, " +
                "   FAULT_DETAIL_TBL.STRATEGY_DETAIL, " +
                "   FAULT_DETAIL_TBL.RESULT, " +
                "   FAULT_STATUS_TBL.NAME RESULT_NAME, " +
                "   FAULT_DETAIL_TBL.OPERATION_1, " +
                "   FAULT_DETAIL_TBL.OPERATION_2, " +
                "   FAULT_DETAIL_TBL.OPERATION_3, " +
                "   FAULT_DETAIL_TBL.OPERATION_4, " +
                "   FAULT_DETAIL_TBL.OPERATION_5, " +
                "   FAULT_DETAIL_TBL.OPERATION_6, " +
                "   FAULT_DETAIL_TBL.OPERATION_7, " +
                "   FAULT_DETAIL_TBL.OPERATION_8, " +
                "   FAULT_DETAIL_TBL.CASH_LEFT, " +
                "   FAULT_DETAIL_TBL.EXCEPTION_DISPLAY, " +
                "   FAULT_DETAIL_TBL.DISPLAY, " +
                "   FAULT_DETAIL_TBL.BACK_LIGHT, " +
                "   FAULT_DETAIL_TBL.DISPLAY_CONTENT, " +
                "   FAULT_DETAIL_TBL.INPUTABLE, " +
                "   FAULT_DETAIL_TBL.KNOCK_SOUND, " +
                "   FAULT_DETAIL_TBL.RESET, " +
                "   FAULT_DETAIL_TBL.CUT_POWER, " +
                "   FAULT_DETAIL_TBL.REBOOT_NORMALLY, " +
                "   FAULT_DETAIL_TBL.MOTION_COUNTER, " +
                "   FAULT_DETAIL_TBL.ERROR_NO, " +
                "   FAULT_DETAIL_TBL.NO_REPON, " +
                "   FAULT_DETAIL_TBL.OTHER_DISPLAY, " +
                "   FAULT_DETAIL_TBL.TRACE_INFOMATION, " +
                "   FAULT_DETAIL_TBL.FAULT_RECORD, " +
                "   FAULT_DETAIL_TBL.STATISTICS, " +
                "   FAULT_DETAIL_TBL.TRADE_LOG, " +
                "   FAULT_DETAIL_TBL.APPLICATION_VERSION APPLICATION_VERSION_1, " +
                "   FAULT_DETAIL_TBL.OTHERS, " +
                "   FAULT_DETAIL_TBL.C_RAS, " +
                "   FAULT_DETAIL_TBL.D_RAS, " +
                "   FAULT_DETAIL_TBL.MCU_LOG, " +
                "   FAULT_DETAIL_TBL.SYSTEM_EVENT, " +
                "   FAULT_DETAIL_TBL.D_BILLBOX, " +
                "   FAULT_DETAIL_TBL.D_COLLECT, " +
                "   FAULT_DETAIL_TBL.C_FEP, " +
                "   FAULT_DETAIL_TBL.C_ERR, " +
                "   FAULT_DETAIL_TBL.APPLY_DATE, " +
                "   FAULT_DETAIL_TBL.DELIVER_DATE, " +
                "   FAULT_DETAIL_TBL.REPLACE_DATE, " +
                "   FAULT_DETAIL_TBL.RECEIVE_DATE," +
                
                "   FAULT_DETAIL_TBL.EC_ERROR_CODE, " +
                "   FAULT_DETAIL_TBL.MTC_CODE, " +
                "   FAULT_DETAIL_TBL.EP_VER, " +
                "   FAULT_DETAIL_TBL.BRM_EP_VER, " +
                "   FAULT_DETAIL_TBL.BV_EP_VER, " +
                "   FAULT_DETAIL_TBL.MAIN__EP_VER, " +
                "   FAULT_DETAIL_TBL.BID_EP_VER, " +
                "   FAULT_DETAIL_TBL.CASH_CONTROL_LIGHT, " +
                "   FAULT_DETAIL_TBL.FAULT_LIGHT, " +
                "   FAULT_DETAIL_TBL.LEFT_ONLINE, " +
                "   FAULT_DETAIL_TBL.RIHGT_ONLINE, " +
                "   FAULT_DETAIL_TBL.SHINE_LIGHT, " +
                "   FAULT_DETAIL_TBL.STAY_SHINE_LIGHT, " +
                "   FAULT_DETAIL_TBL.OUT_LIGHT " +
                "   " +
                " FROM " +
                "   FAULT_TBL " +
                "   INNER JOIN FAULT_DETAIL_TBL " +
                "   ON FAULT_DETAIL_TBL.ID = FAULT_TBL.ID " +
                "   LEFT JOIN FAULT_TYPE_TBL " +
                "   ON FAULT_TYPE_TBL.ID = FAULT_TBL.FAULT_TYPE " +
                "   LEFT JOIN COMPANY_TBL " +
                "   ON COMPANY_TBL.ID = FAULT_TBL.REPAIR_COMPANY_ID " +
                "   AND COMPANY_TBL.ID > 0 " +
                "   LEFT JOIN USER_TBL " +
                "   ON USER_TBL.ID = FAULT_TBL.SUPPORTER_ID " +
                "   AND USER_TBL.ID > 0 " +
                "   LEFT JOIN USER_TBL USER_TBL1 " +
                "   ON USER_TBL1.ID = FAULT_TBL.FOLLOWER_ID " +
                "   AND USER_TBL1.ID > 0 " +
                "   LEFT JOIN SUPPORT_TYPE_TBL " +
                "   ON SUPPORT_TYPE_TBL.ID = FAULT_TBL.SUPPORT_TYPE " +
                "   LEFT JOIN FAULT_PART_TBL " +
                "   ON FAULT_PART_TBL.ID = FAULT_TBL.FAULT_PART " +
                "   LEFT JOIN FAULT_PART_TYPE_TBL " +
                "   ON FAULT_PART_TYPE_TBL.ID = FAULT_TBL.FAULT_PART_TYPE" +
                "   LEFT JOIN FAULT_STATUS_TBL " +
                "   ON FAULT_STATUS_TBL.ID = FAULT_DETAIL_TBL.RESULT" +
                " WHERE " +
                "   0 = 0 ";

            if (managementIdIn == null) {
                sql = sql + "   AND FAULT_TBL.ID = ? ";
            } else {
                sql = sql + "   AND FAULT_TBL.MANAGEMENT_ID = ? ";
            }

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            if (managementIdIn == null) {
                pstmt.setInt(++index, faultId);
            } else {
                pstmt.setString(++index, managementIdIn);
            }

            rs = pstmt.executeQuery();

            index = 0;
            if (rs.next()) {
                fault.setId(rs.getInt(++index));
                fault.setManagementId(rs.getString(++index));
                fault.setInstallationId(rs.getInt(++index));
                fault.setFaultType(rs.getInt(++index));
                fault.setFaultTypeName(rs.getString(++index));
                fault.setOccurDate(rs.getString(++index));
                fault.setOccurTime(rs.getString(++index));
                fault.setReportDate(rs.getString(++index));
                fault.setReportTime(rs.getString(++index));
                fault.setRepairState(rs.getInt(++index));
                fault.setFinishDate(rs.getString(++index));
                fault.setFinishTime(rs.getString(++index));
                fault.setRepairCompanyId(rs.getInt(++index));
                fault.setRepairCompanyName(rs.getString(++index));
                fault.setSupporterId(rs.getInt(++index));
                fault.setSupporterName(rs.getString(++index));
                fault.setFollowerId(rs.getInt(++index));
                fault.setFollowerName(rs.getString(++index));
                fault.setOperatorName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setSupportType(rs.getInt(index));
                }
                fault.setSupportTypeName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPart(rs.getInt(index));
                }
                fault.setFaultPartName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPartType(rs.getInt(index));
                }
                fault.setFaultPartTypeName(rs.getString(++index));
                fault.setIsState(rs.getString(++index));
                fault.setApplicationVersion(rs.getInt(++index));
                fault.setApplicationVersionDetail1(rs.getString(++index));
                fault.setApplicationVersionDetail2(rs.getString(++index));
                fault.setLocked(rs.getInt(++index));
                fault.setDeleted(rs.getInt(++index));
                fault.setExclusiveKey(rs.getInt(++index));
                fault.setFaultMachineType(rs.getString(++index));
                fault.setOccurCondiction1(rs.getInt(++index));
                fault.setOccurCondiction2(rs.getInt(++index));
                fault.setErrorCode(rs.getString(++index));
                fault.setRx278(rs.getString(++index));
                fault.setCounter(rs.getString(++index));
                fault.setAppearance(rs.getString(++index));
                fault.setReason(rs.getString(++index));
                fault.setStrategy(rs.getString(++index));
                fault.setStrategyDetail(rs.getString(++index));
                fault.setResultId(rs.getInt(++index));
                fault.setResult(rs.getString(++index));
                fault.setOperation1(rs.getString(++index));
                fault.setOperation2(rs.getString(++index));
                fault.setOperation3(rs.getString(++index));
                fault.setOperation4(rs.getString(++index));
                fault.setOperation5(rs.getString(++index));
                fault.setOperation6(rs.getString(++index));
                fault.setOperation7(rs.getString(++index));
                fault.setOperation8(rs.getString(++index));
                fault.setCashLeft(rs.getInt(++index));
                fault.setExceptionDisplay(rs.getInt(++index));
                fault.setDisplay(rs.getInt(++index));
                fault.setBackLight(rs.getInt(++index));
                fault.setDisplayContent(rs.getString(++index));
                fault.setInputable(rs.getInt(++index));
                fault.setKnockSound(rs.getInt(++index));
                fault.setReset(rs.getInt(++index));
                fault.setCutPower(rs.getInt(++index));
                fault.setRebootNormally(rs.getInt(++index));
                fault.setMotionCounter(rs.getString(++index));
                fault.setErrorNo(rs.getString(++index));
                fault.setNoRepon(rs.getString(++index));
                fault.setOtherDisplay(rs.getString(++index));
                fault.setTraceInfomation(rs.getInt(++index));
                fault.setFaultRecord(rs.getInt(++index));
                fault.setStatistics(rs.getInt(++index));
                fault.setTradeLog(rs.getInt(++index));
                fault.setApplicationVersion1(rs.getInt(++index));
                fault.setOthers(rs.getString(++index));
                fault.setCras(rs.getInt(++index));
                fault.setDras(rs.getInt(++index));
                fault.setMcuLog(rs.getInt(++index));
                fault.setSystemEvent(rs.getInt(++index));
                fault.setDbillbox(rs.getInt(++index));
                fault.setDcollect(rs.getInt(++index));
                fault.setCfep(rs.getInt(++index));
                fault.setCerr(rs.getInt(++index));
                fault.setApplyDate(rs.getString(++index));
                fault.setDeliverDate(rs.getString(++index));
                fault.setReplaceDate(rs.getString(++index));
                fault.setIhscReceiveDate(rs.getString(++index));
                
                fault.setEcErrorCode(rs.getString(++index));
                fault.setMtcCode(rs.getString(++index));
                fault.setEpVer(rs.getString(++index));
                fault.setBrmEpVer(rs.getString(++index));
                fault.setBvEpVer(rs.getString(++index));
                fault.setMainEpver(rs.getString(++index));
                fault.setBidEpver(rs.getString(++index));
                fault.setCashControlLight(rs.getInt(++index));
                fault.setFaultLight(rs.getInt(++index));
                fault.setLeftOnlineLight(rs.getInt(++index));
                fault.setRightOnlineLight(rs.getInt(++index));
                fault.setShineLight(rs.getString(++index));
                fault.setStayShineLight(rs.getString(++index));
                fault.setOutLight(rs.getString(++index));
                
                
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultDetailById");
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
                logSQLException(e, "getFaultDetailById");
                throw e;
            }
        }

        return fault;
    }
    
    /**
     * get agent_id for permission check
     * @author sunyx
     * @param faultId
     * @return
     * @throws SQLException
     */
    public Integer sercgNowRepaireComp(Integer faultId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " SELECT " +
                        " INSTALLATION_TBL.NOW_REPAIR_COMPANY_ID" +
                        " FROM " +
                        " FAULT_TBL" +
                        " LEFT OUTER JOIN INSTALLATION_TBL ON FAULT_TBL.INSTALLATION_ID = INSTALLATION_TBL.ID" +
                        " WHERE" +
                        " FAULT_TBL.ID = ?";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultId);
            rs = pstmt.executeQuery();
            
            Integer nowRepaireCompId = new Integer(0);
            if(rs.next()){
                index = 0;
                nowRepaireCompId = rs.getInt(++index);
            }

            return nowRepaireCompId;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultDetailById");
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
                logSQLException(e, "getFaultDetailById");
                throw e;
            }
        }
    }

    /**
     * get fault information by fault ID for modify.
     * @author  luyan
     * @since   1.0
     * @param   faultId fault ID
     * @return  fault detail information
     * @throws  SQLException
     */
    public Fault getFaultByIdFm(Fault faultIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Fault fault = new Fault();

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  FAULT_TBL "
                    + " WHERE "
                    + "  ID = ? ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, faultIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != faultIn.getExclusiveKey()) {
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

            String sql = " SELECT " +
                "   FAULT_TBL.ID, " +
                "   FAULT_TBL.MANAGEMENT_ID, " +
                "   FAULT_TBL.INSTALLATION_ID, " +
                "   FAULT_TBL.FAULT_TYPE, " +
                "   FAULT_TYPE_TBL.NAME FAULT_TYPE_NAME, " +
                "   FAULT_TBL.OCCUR_DATE, " +
                "   FAULT_TBL.OCCUR_TIME, " +
                "   FAULT_TBL.REPORT_DATE, " +
                "   FAULT_TBL.REPORT_TIME, " +
                "   FAULT_TBL.REPAIR_STATE, " +
                "   FAULT_TBL.FINISH_DATE, " +
                "   FAULT_TBL.FINISH_TIME, " +
                "   FAULT_TBL.REPAIR_COMPANY_ID, " +
                "   COMPANY_TBL.SHORT_NAME REPAIR_COMPANY_NAME, " +
                "   FAULT_TBL.SUPPORTER_ID, " +
                "   USER_TBL.FAMILY_NAME || USER_TBL.GIVEN_NAME SUPPORTER_NAME, " +
                "   FAULT_TBL.FOLLOWER_ID, " +
                "   USER_TBL1.FAMILY_NAME || USER_TBL1.GIVEN_NAME FOLLOWER_NAME, " +
                "   FAULT_TBL.OPERATOR_NAME, " +
                "   FAULT_TBL.SUPPORT_TYPE, " +
                "   SUPPORT_TYPE_TBL.NAME SUPPORT_TYPE_NAME, " +
                "   FAULT_TBL.FAULT_PART, " +
                "   FAULT_PART_TBL.NAME FAULT_PART_NAME, " +
                "   FAULT_TBL.FAULT_PART_TYPE, " +
                "   FAULT_PART_TYPE_TBL.NAME FAULT_PART_TYPE_NAME, " +
                "   FAULT_TBL.IS_STATE, " +
                "   FAULT_TBL.APPLICATION_VERSION, " +
                "   FAULT_TBL.APPLICATION_VERSION_DETAIL_1, " +
                "   FAULT_TBL.APPLICATION_VERSION_DETAIL_2, " +
                "   FAULT_TBL.LOCKED, " +
                "   FAULT_TBL.DELETED, " +
                "   FAULT_TBL.EXCLUSIVE_KEY, " +
                "   FAULT_TBL.FAULT_MACHINE_TYPE," +
                "   FAULT_DETAIL_TBL.OCCUR_CONDICTION_1, " +
                "   FAULT_DETAIL_TBL.OCCUR_CONDICTION_2, " +
                "   FAULT_DETAIL_TBL.ERROR_CODE, " +
                "   FAULT_DETAIL_TBL.RX278, " +
                "   FAULT_DETAIL_TBL.COUNTER, " +
                "   FAULT_DETAIL_TBL.APPEARANCE, " +
                "   FAULT_DETAIL_TBL.REASON, " +
                "   FAULT_DETAIL_TBL.STRATEGY, " +
                "   FAULT_DETAIL_TBL.STRATEGY_DETAIL, " +
                "   FAULT_DETAIL_TBL.RESULT, " +
                "   FAULT_STATUS_TBL.NAME RESULT_NAME, " +
                "   FAULT_DETAIL_TBL.OPERATION_1, " +
                "   FAULT_DETAIL_TBL.OPERATION_2, " +
                "   FAULT_DETAIL_TBL.OPERATION_3, " +
                "   FAULT_DETAIL_TBL.OPERATION_4, " +
                "   FAULT_DETAIL_TBL.OPERATION_5, " +
                "   FAULT_DETAIL_TBL.OPERATION_6, " +
                "   FAULT_DETAIL_TBL.OPERATION_7, " +
                "   FAULT_DETAIL_TBL.OPERATION_8, " +
                "   FAULT_DETAIL_TBL.CASH_LEFT, " +
                "   FAULT_DETAIL_TBL.EXCEPTION_DISPLAY, " +
                "   FAULT_DETAIL_TBL.DISPLAY, " +
                "   FAULT_DETAIL_TBL.BACK_LIGHT, " +
                "   FAULT_DETAIL_TBL.DISPLAY_CONTENT, " +
                "   FAULT_DETAIL_TBL.INPUTABLE, " +
                "   FAULT_DETAIL_TBL.KNOCK_SOUND, " +
                "   FAULT_DETAIL_TBL.RESET, " +
                "   FAULT_DETAIL_TBL.CUT_POWER, " +
                "   FAULT_DETAIL_TBL.REBOOT_NORMALLY, " +
                "   FAULT_DETAIL_TBL.MOTION_COUNTER, " +
                "   FAULT_DETAIL_TBL.ERROR_NO, " +
                "   FAULT_DETAIL_TBL.NO_REPON, " +
                "   FAULT_DETAIL_TBL.OTHER_DISPLAY, " +
                "   FAULT_DETAIL_TBL.TRACE_INFOMATION, " +
                "   FAULT_DETAIL_TBL.FAULT_RECORD, " +
                "   FAULT_DETAIL_TBL.STATISTICS, " +
                "   FAULT_DETAIL_TBL.TRADE_LOG, " +
                "   FAULT_DETAIL_TBL.APPLICATION_VERSION APPLICATION_VERSION_1, " +
                "   FAULT_DETAIL_TBL.OTHERS, " +
                "   FAULT_DETAIL_TBL.C_RAS, " +
                "   FAULT_DETAIL_TBL.D_RAS, " +
                "   FAULT_DETAIL_TBL.MCU_LOG, " +
                "   FAULT_DETAIL_TBL.SYSTEM_EVENT, " +
                "   FAULT_DETAIL_TBL.D_BILLBOX, " +
                "   FAULT_DETAIL_TBL.D_COLLECT, " +
                "   FAULT_DETAIL_TBL.C_FEP, " +
                "   FAULT_DETAIL_TBL.C_ERR, " +
                "   FAULT_DETAIL_TBL.APPLY_DATE, " +
                "   FAULT_DETAIL_TBL.DELIVER_DATE, " +
                "   FAULT_DETAIL_TBL.REPLACE_DATE, " +
                "   FAULT_DETAIL_TBL.RECEIVE_DATE, " +
                
                "   FAULT_DETAIL_TBL.EC_ERROR_CODE, " +
                "   FAULT_DETAIL_TBL.MTC_CODE, " +
                "   FAULT_DETAIL_TBL.EP_VER, " +
                "   FAULT_DETAIL_TBL.BRM_EP_VER, " +
                "   FAULT_DETAIL_TBL.BV_EP_VER, " +
                "   FAULT_DETAIL_TBL.MAIN__EP_VER, " +
                "   FAULT_DETAIL_TBL.BID_EP_VER, " +
                "   FAULT_DETAIL_TBL.CASH_CONTROL_LIGHT, " +
                "   FAULT_DETAIL_TBL.FAULT_LIGHT, " +
                "   FAULT_DETAIL_TBL.LEFT_ONLINE, " +
                "   FAULT_DETAIL_TBL.RIHGT_ONLINE, " +
                "   FAULT_DETAIL_TBL.SHINE_LIGHT, " +
                "   FAULT_DETAIL_TBL.STAY_SHINE_LIGHT, " +
                "   FAULT_DETAIL_TBL.OUT_LIGHT " +
                
                " FROM " +
                "   FAULT_TBL " +
                "   INNER JOIN FAULT_DETAIL_TBL " +
                "   ON FAULT_DETAIL_TBL.ID = FAULT_TBL.ID " +
                "   LEFT JOIN FAULT_TYPE_TBL " +
                "   ON FAULT_TYPE_TBL.ID = FAULT_TBL.FAULT_TYPE " +
                "   LEFT JOIN COMPANY_TBL " +
                "   ON COMPANY_TBL.ID = FAULT_TBL.REPAIR_COMPANY_ID " +
                "   AND COMPANY_TBL.ID > 0 " +
                "   LEFT JOIN USER_TBL " +
                "   ON USER_TBL.ID = FAULT_TBL.SUPPORTER_ID " +
                "   AND USER_TBL.ID > 0 " +
                "   LEFT JOIN USER_TBL USER_TBL1 " +
                "   ON USER_TBL1.ID = FAULT_TBL.FOLLOWER_ID " +
                "   AND USER_TBL1.ID > 0 " +
                "   LEFT JOIN SUPPORT_TYPE_TBL " +
                "   ON SUPPORT_TYPE_TBL.ID = FAULT_TBL.SUPPORT_TYPE " +
                "   LEFT JOIN FAULT_PART_TBL " +
                "   ON FAULT_PART_TBL.ID = FAULT_TBL.FAULT_PART " +
                "   LEFT JOIN FAULT_PART_TYPE_TBL " +
                "   ON FAULT_PART_TYPE_TBL.ID = FAULT_TBL.FAULT_PART_TYPE" +
                "   LEFT JOIN FAULT_STATUS_TBL " +
                "   ON FAULT_STATUS_TBL.ID = FAULT_DETAIL_TBL.RESULT " +
                " WHERE " +
                "   FAULT_TBL.ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setInt(++index, faultIn.getId());

            rs = pstmt.executeQuery();

            index = 0;
            if (rs.next()) {
                fault.setId(rs.getInt(++index));
                fault.setManagementId(rs.getString(++index));
                fault.setInstallationId(rs.getInt(++index));
                fault.setFaultType(rs.getInt(++index));
                fault.setFaultTypeName(rs.getString(++index));
                fault.setOccurDate(rs.getString(++index));
                fault.setOccurTime(rs.getString(++index));
                fault.setReportDate(rs.getString(++index));
                fault.setReportTime(rs.getString(++index));
                fault.setRepairState(rs.getInt(++index));
                fault.setFinishDate(rs.getString(++index));
                fault.setFinishTime(rs.getString(++index));
                fault.setRepairCompanyId(rs.getInt(++index));
                fault.setRepairCompanyName(rs.getString(++index));
                fault.setSupporterId(rs.getInt(++index));
                fault.setSupporterName(rs.getString(++index));
                fault.setFollowerId(rs.getInt(++index));
                fault.setFollowerName(rs.getString(++index));
                fault.setOperatorName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setSupportType(rs.getInt(index));
                }
                fault.setSupportTypeName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPart(rs.getInt(index));
                }
                fault.setFaultPartName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPartType(rs.getInt(index));
                }
                fault.setFaultPartTypeName(rs.getString(++index));
                fault.setIsState(rs.getString(++index));
                fault.setApplicationVersion(rs.getInt(++index));
                fault.setApplicationVersionDetail1(rs.getString(++index));
                fault.setApplicationVersionDetail2(rs.getString(++index));
                fault.setLocked(rs.getInt(++index));
                fault.setDeleted(rs.getInt(++index));
                fault.setExclusiveKey(rs.getInt(++index));
                fault.setFaultMachineType(rs.getString(++index));
                
                fault.setOccurCondiction1(rs.getInt(++index));
                fault.setOccurCondiction2(rs.getInt(++index));
                fault.setErrorCode(rs.getString(++index));
                fault.setRx278(rs.getString(++index));
                fault.setCounter(rs.getString(++index));
                fault.setAppearance(rs.getString(++index));
                fault.setReason(rs.getString(++index));
                fault.setStrategy(rs.getString(++index));
                fault.setStrategyDetail(rs.getString(++index));
                fault.setResultId(rs.getInt(++index));
                fault.setResult(rs.getString(++index));
                fault.setOperation1(rs.getString(++index));
                fault.setOperation2(rs.getString(++index));
                fault.setOperation3(rs.getString(++index));
                fault.setOperation4(rs.getString(++index));
                fault.setOperation5(rs.getString(++index));
                fault.setOperation6(rs.getString(++index));
                fault.setOperation7(rs.getString(++index));
                fault.setOperation8(rs.getString(++index));
                fault.setCashLeft(rs.getInt(++index));
                fault.setExceptionDisplay(rs.getInt(++index));
                fault.setDisplay(rs.getInt(++index));
                fault.setBackLight(rs.getInt(++index));
                fault.setDisplayContent(rs.getString(++index));
                fault.setInputable(rs.getInt(++index));
                fault.setKnockSound(rs.getInt(++index));
                fault.setReset(rs.getInt(++index));
                fault.setCutPower(rs.getInt(++index));
                fault.setRebootNormally(rs.getInt(++index));
                fault.setMotionCounter(rs.getString(++index));
                fault.setErrorNo(rs.getString(++index));
                fault.setNoRepon(rs.getString(++index));
                fault.setOtherDisplay(rs.getString(++index));
                fault.setTraceInfomation(rs.getInt(++index));
                fault.setFaultRecord(rs.getInt(++index));
                fault.setStatistics(rs.getInt(++index));
                fault.setTradeLog(rs.getInt(++index));
                fault.setApplicationVersion1(rs.getInt(++index));
                fault.setOthers(rs.getString(++index));
                fault.setCras(rs.getInt(++index));
                fault.setDras(rs.getInt(++index));
                fault.setMcuLog(rs.getInt(++index));
                fault.setSystemEvent(rs.getInt(++index));
                fault.setDbillbox(rs.getInt(++index));
                fault.setDcollect(rs.getInt(++index));
                fault.setCfep(rs.getInt(++index));
                fault.setCerr(rs.getInt(++index));
                fault.setApplyDate(rs.getString(++index));
                fault.setDeliverDate(rs.getString(++index));
                fault.setReplaceDate(rs.getString(++index));
                fault.setIhscReceiveDate(rs.getString(++index));
                
                fault.setEcErrorCode(rs.getString(++index));
                fault.setMtcCode(rs.getString(++index));
                fault.setEpVer(rs.getString(++index));
                fault.setBrmEpVer(rs.getString(++index));
                fault.setBvEpVer(rs.getString(++index));
                fault.setMainEpver(rs.getString(++index));
                fault.setBidEpver(rs.getString(++index));
                fault.setCashControlLight(rs.getInt(++index));
                fault.setFaultLight(rs.getInt(++index));
                fault.setLeftOnlineLight(rs.getInt(++index));
                fault.setRightOnlineLight(rs.getInt(++index));
                fault.setShineLight(rs.getString(++index));
                fault.setStayShineLight(rs.getString(++index));
                fault.setOutLight(rs.getString(++index));
                
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultByIdFm");
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
                logSQLException(e, "getFaultByIdFm");
                throw e;
            }
        }

        return fault;
    }

    /**
     * modify fault information by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void modifyById(Fault faultIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  FAULT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, faultIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != faultIn.getExclusiveKey()) {
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

            String sql = " UPDATE " +
                "   FAULT_TBL " +
                " SET " +
                "   MANAGEMENT_ID = ?, " +
                "   FAULT_TYPE = ?, " +
                "   OCCUR_DATE = ?, " +
                "   OCCUR_TIME = ?, " +
                "   FINISH_DATE = ?, " +
                "   FINISH_TIME = ?, " +
                "   REPAIR_COMPANY_ID = ?, " +
                "   SUPPORTER_ID = ?, " +
                "   FOLLOWER_ID = ?, " +
                "   OPERATOR_NAME = ?, " +
                "   SUPPORT_TYPE = ?, " +
                "   FAULT_PART = ?, " +
                "   FAULT_PART_TYPE = ?, " +
                "   IS_STATE = ?, " +
                "   APPLICATION_VERSION = ?, " +
                "   APPLICATION_VERSION_DETAIL_1 = ?, " +
                "   APPLICATION_VERSION_DETAIL_2 = ?, " +
                "   MODIFY_TIME = ?, " +
                "   MODIFIER_ID = ?, " +
                "   EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 " +
                " WHERE " +
                "   ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, faultIn.getManagementId());
            pstmt.setInt(++index, faultIn.getFaultType());
            pstmt.setString(++index, faultIn.getOccurDate());
            pstmt.setString(++index, faultIn.getOccurTime());
            pstmt.setString(++index, faultIn.getFinishDate());
            pstmt.setString(++index, faultIn.getFinishTime());
            pstmt.setInt(++index, faultIn.getRepairCompanyId());
            if (faultIn.getSupporterId() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, faultIn.getSupporterId());
            }
            if (faultIn.getFollowerId() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, faultIn.getFollowerId());
            }
            pstmt.setString(++index, faultIn.getOperatorName());
            if (faultIn.getSupportType() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getSupportType()));
            }
            if (faultIn.getFaultPart() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getFaultPart()));
            }
            if (faultIn.getFaultPartType() == null) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setBigDecimal(++index, new BigDecimal(faultIn.getFaultPartType()));
            }
            pstmt.setString(++index, faultIn.getIsState());
            pstmt.setInt(++index, faultIn.getApplicationVersion());
            pstmt.setString(++index, faultIn.getApplicationVersionDetail1());
            pstmt.setString(++index, faultIn.getApplicationVersionDetail2());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, faultIn.getModifierId());
            pstmt.setInt(++index, faultIn.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyById");
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
                logSQLException(e, "modifyById");
                throw e;
            }
        }
    }

    /**
     * modify fault information by fault ID for business delete/recover.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void modifyByIdFd(Fault faultIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  FAULT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, faultIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != faultIn.getExclusiveKey()) {
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

            String sql = " UPDATE " +
                "   FAULT_TBL " +
                " SET " +
                "   DELETED = ?, " +
                "   MODIFY_TIME = ?, " +
                "   MODIFIER_ID = ?, " +
                "   EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 " +
                " WHERE " +
                "   ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getDeleted());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, faultIn.getModifierId());
            pstmt.setInt(++index, faultIn.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyByIdFd");
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
                logSQLException(e, "modifyByIdFd");
                throw e;
            }
        }
    }

    /**
     * modify fault information by fault ID for finish/cancel finish.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void modifyByIdFf(Fault faultIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  FAULT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, faultIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != faultIn.getExclusiveKey()) {
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

            String sql = " UPDATE " +
                "   FAULT_TBL " +
                " SET " +
                "   REPAIR_STATE = ?, " +
                "   MODIFY_TIME = ?, " +
                "   MODIFIER_ID = ?, " +
                "   EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 " +
                " WHERE " +
                "   ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getRepairState());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, faultIn.getModifierId());
            pstmt.setInt(++index, faultIn.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyByIdFf");
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
                logSQLException(e, "modifyByIdFf");
                throw e;
            }
        }
    }

    /**
     * delete fault information by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void deleteById(Fault faultIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  FAULT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, faultIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != faultIn.getExclusiveKey()) {
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

            String sql = " DELETE FROM " +
                "   FAULT_TBL " +
                " WHERE " +
                "   ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteById");
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
                logSQLException(e, "deleteById");
                throw e;
            }
        }
    }
    
    /**
     * get fault by id
     * @author sunyx
     * @param faultId
     * @return Fault
     * @throws Exception
     */
    public Fault getFaultById(Integer faultId) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Fault fault = new Fault();
        
        try {
            // Start UOC
            String sql = " SELECT" +
                        " A.ID," +
                        " A.MANAGEMENT_ID," +
                        " A.FAULT_TYPE," +
                        " A.FAULT_PART," +
                        " A.FAULT_PART_TYPE," +
                        " A.IS_STATE," +
                        " A.SUPPORT_TYPE," +
                        " A.FINISH_DATE," +
                        " A.FINISH_TIME," +
                        " A.REPAIR_STATE," +
                        " A.DELETED," +
                        " B.REASON," +
                        " B.STRATEGY," +
                        " B.RESULT," +
                        " A.EXCLUSIVE_KEY " +
                        " FROM FAULT_TBL A" +
                        " LEFT OUTER JOIN FAULT_DETAIL_TBL B ON A.ID=B.ID" +
                        " WHERE " +
                        " A.ID = ? ";
            pstmt = conn.prepareStatement(sql);
    
            int index = 0;
            pstmt.setInt(++index, faultId);
            rs = pstmt.executeQuery();
            
            index = 0;
            if (rs.next()) {
                fault.setId(rs.getInt(++index));
                fault.setManagementId(rs.getString(++index));
                fault.setFaultType(rs.getInt(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPart(rs.getInt(index));
                }
                if (rs.getBigDecimal(++index) != null) {
                    fault.setFaultPartType(rs.getInt(index));
                }
                fault.setIsState(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    fault.setSupportType(rs.getInt(index));
                }
                fault.setFinishDate(rs.getString(++index));
                fault.setFinishTime(rs.getString(++index));
                fault.setRepairState(rs.getInt(++index));
                fault.setDeleted(rs.getInt(++index));
                fault.setReason(rs.getString(++index));
                fault.setStrategy(rs.getString(++index));
                fault.setResultId(rs.getInt(++index));
                fault.setExclusiveKey(rs.getInt(++index));
            }
            
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteById");
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
                logSQLException(e, "deleteById");
                throw e;
            }
        }
        
        return fault;
    }
}