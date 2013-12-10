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

import cn.com.bhh.erp.common.TimeUtil;

/**
 * 
 * installation_history_tbl
 * @author liugd
 * @since 1.0
 * @version 1.0
 *
 */
public class InstallationHistoryDao extends BaseDao{
    
    public InstallationHistoryDao(Connection conn) {
        super(conn);
    }

    /**
     * create installation's history
     * @author liugd
     * @param id
     * @param dateNow
     * @throws Exception
     */
    public void create(Integer id) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = "INSERT INTO INSTALLATION_HISTORY_TBL(" +
                                    "   ID," +
                                    "   START_DATE," +
                                    "   END_DATE," +
                                    "   PRODUCT_ID," +
                                    "   MANUFACTURE_NO," +
                                    "   CUSTOMER_ID," +
                                    "   SALES_CONTRACT_COMP_ID," +
                                    "   INSTALL_COMP_ID," +
                                    "   INSTALLER," +
                                    "   INSTALLER_ID," +
                                    "   FIRST_REPAIR_COMPANY_ID," +
                                    "   NOW_REPAIR_COMPANY_ID," +
                                    "   INDENTURE_NO," +
                                    "   FOB_DATE," +
                                    "   INSTALL_DATE," +
                                    "   OPEN_DATE," +
                                    "   ACCEPT_DATE," +
                                    "   GUARANTEE_START_DATE," +
                                    "   GUARANTEE_END_DATE," +
                                    "   GUARANTEE_PERIOD," +
                                    "   BRANCH_COMPANY_NAME," +
                                    "   INSTALL_PLACE," +
                                    "   INST_PLACE_TYPE_ID," +
                                    "   USE_STATUS_ID," +
                                    "   PURPOSE," +
                                    "   CONTACT," +
                                    "   OFFICE_PHONE," +
                                    "   MOBILE_PHONE," +
                                    "   FAX," +
                                    "   EMAIL," +
                                    "   BRM_EP_VER," +
                                    "   BV_EP_VER," +
                                    "   BHCL_EP_VER," +
                                    "   TRCL_EP_VER," +
                                    "   KEY_NO," +
                                    "   NOTE," +
                                    "   OP_SYS," +
                                    "   OS_PERMIT," +
                                    "   PLATFORM," +
                                    "   PLATFORM_REV," +
                                    "   MCU," +
                                    "   BV," +
                                    "   HCM," +
                                    "   JPR," +
                                    "   SPR," +
                                    "   DESKEY," +
                                    "   AFFIRM_FLAG," +
                                    "   AFFIRMANT_ID," +
                                    "   AFFIRM_TIME," +
                                    "   DELETED," +
                                    "   CREATE_TIME," +
                                    "   CREATOR_ID," +
                                    "   MODIFY_TIME," +
                                    "   MODIFIER_ID," +
                                    "   EXCLUSIVE_KEY)" +
                            "   SELECT" +
                            "   ID," +
                            "   START_DATE," +
                                "'" + TimeUtil.getNowDate() + "' END_DATE," +
                            "   PRODUCT_ID," +
                            "   MANUFACTURE_NO," +
                            "   CUSTOMER_ID," +
                            "   SALES_CONTRACT_COMP_ID," +
                            "   INSTALL_COMP_ID," +
                            "   INSTALLER," +
                            "   INSTALLER_ID," +
                            "   FIRST_REPAIR_COMPANY_ID," +
                            "   NOW_REPAIR_COMPANY_ID," +
                            "   INDENTURE_NO," +
                            "   FOB_DATE," +
                            "   INSTALL_DATE," +
                            "   OPEN_DATE," +
                            "   ACCEPT_DATE," +
                            "   GUARANTEE_START_DATE," +
                            "   GUARANTEE_END_DATE," +
                            "   GUARANTEE_PERIOD," +
                            "   BRANCH_COMPANY_NAME," +
                            "   INSTALL_PLACE," +
                            "   INST_PLACE_TYPE_ID," +
                            "   USE_STATUS_ID," +
                            "   PURPOSE," +
                            "   CONTACT," +
                            "   OFFICE_PHONE," +
                            "   MOBILE_PHONE," +
                            "   FAX," +
                            "   EMAIL," +
                            "   BRM_EP_VER," +
                            "   BV_EP_VER," +
                            "   BHCL_EP_VER," +
                            "   TRCL_EP_VER," +
                            "   KEY_NO," +
                            "   NOTE," +
                            "   OP_SYS," +
                            "   OS_PERMIT," +
                            "   PLATFORM," +
                            "   PLATFORM_REV," +
                            "   MCU," +
                            "   BV," +
                            "   HCM," +
                            "   JPR," +
                            "   SPR," +
                            "   DESKEY," +
                            "   AFFIRM_FLAG," +
                            "   AFFIRMANT_ID," +
                            "   AFFIRM_TIME," +
                            "   DELETED," +
                            "   CREATE_TIME," +
                            "   CREATOR_ID," +
                            "   MODIFY_TIME," +
                            "   MODIFIER_ID," +
                            "   EXCLUSIVE_KEY" +
                            "   FROM INSTALLATION_TBL" +
                            "   WHERE " +
                            "       ID = ?";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, id);
            
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "create");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "create");
                throw e;
            }
        }
    }
    
    /**
     * delete installation history
     * @author liugd
     * @param Integer id
     * @throws Exception
     */
    public void deleteById(Integer id) throws Exception {
        PreparedStatement pstmt = null;
        try {
            // Start UOC
            String sql = "DELETE " +
                        " FROM " +
                        "   INSTALLATION_HISTORY_TBL" +
                        " WHERE " +
                        "   ID = ?" ;
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, id);
            
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteById");
            throw e;
        } finally {
            try {
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
     * get count by customer company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   customerId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCustomerId(Integer customerId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_HISTORY_TBL " +
                       " WHERE  " +
                       " CUSTOMER_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, customerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountCustomerId");
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
                logSQLException(e, "getCountCustomerId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by sale company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   saleContractComId
     * @return  int
     * @throws  SQLException
     */
    public int getSaleContractComId(Integer saleContractComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_HISTORY_TBL " +
                       " WHERE  " +
                       " SALES_CONTRACT_COMP_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, saleContractComId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getSaleContractComId");
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
                logSQLException(e, "getSaleContractComId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by  sale company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   insatllCompId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByInsatllCompId(Integer insatllCompId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_HISTORY_TBL " +
                       " WHERE  " +
                       " INSTALL_COMP_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, insatllCompId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByInsatllCompId");
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
                logSQLException(e, "getCountBySalesContractCompId");
                throw e;
            }
        }

        return count;
    }
    
    
    /**
     * get count by first repair company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   firstRepairCompanyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByFirstRepairCompanyId(Integer firstRepairCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_HISTORY_TBL " +
                       " WHERE  " +
                       " FIRST_REPAIR_COMPANY_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, firstRepairCompanyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByFirstRepairCompanyId");
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
                logSQLException(e, "getCountByFirstRepairCompanyId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by current repair company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   nowRepairCompanyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByNowRepairCompanyId(Integer nowRepairCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_HISTORY_TBL " +
                       " WHERE  " +
                       " NOW_REPAIR_COMPANY_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, nowRepairCompanyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByNowRepairCompanyId");
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
                logSQLException(e, "getCountByNowRepairCompanyId");
                throw e;
            }
        }

        return count;
    }
}
