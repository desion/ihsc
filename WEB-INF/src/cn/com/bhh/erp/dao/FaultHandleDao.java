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

import cn.com.bhh.erp.entity.FaultHandle;

public class FaultHandleDao extends BaseDao {
    public FaultHandleDao(Connection conn) {
        super(conn);
    }

    /**
     * get fault handle count
     * by user id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   userId
     * @return  int
     * @throws  SQLException
     */
    public int getFaultHandleCountBySupporterId(Integer supporterId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                      " SELECT " +
                      " COUNT(*) " +
                      " FROM " +
                      " FAULT_HANDLE_TBL " +
                      " WHERE " +
                      " SUPPORTER_ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, supporterId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getFaultHandleCountBySupporterId");
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
                logSQLException(e, "getFaultHandleCountBySupporterId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by  operator company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   operatorCompanyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByOperatorCompanyId(Integer operatorCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " FAULT_HANDLE_TBL " +
                       " WHERE  " +
                       " OPERATE_COMPANY_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, operatorCompanyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByOperatorCompanyId");
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
                logSQLException(e, "getCountByOperatorCompanyId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * create fault handle information
     * @author sunyx
     * @param faultHandle
     * @throws SQLException
     */
    public void create(FaultHandle faultHandle) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " INSERT INTO FAULT_HANDLE_TBL (" +
                        " FAULT_ID " +
                        " ,SUPPORTER_ID " +
                        " ,OPERATE_COMPANY_ID " +
                        " ,OPERATOR_NAME " +
                        " ,HANDLE_DETAIL " +
                        " ,START_DATE " +
                        " ,START_TIME " +
                        " ,END_DATE " +
                        " ,END_TIME " +
                        " ,STATUS " +
                        " ,HANDLE_TYPE " +
                        " ,NOTE " +
                        " )VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, faultHandle.getFaultId());
            pstmt.setInt(++index, faultHandle.getSupporterId());
            pstmt.setInt(++index, faultHandle.getOperateCompanyId());
            pstmt.setString(++index, faultHandle.getOperatorName());
            pstmt.setString(++index, faultHandle.getHandleDetail());
            pstmt.setString(++index, faultHandle.getStartDate());
            pstmt.setString(++index, faultHandle.getStartTime());
            pstmt.setString(++index, faultHandle.getEndDate());
            pstmt.setString(++index, faultHandle.getEndTime());
            pstmt.setString(++index, faultHandle.getStatus());
            pstmt.setInt(++index, faultHandle.getHandleType());
            pstmt.setString(++index, faultHandle.getNote());
            
            rs = pstmt.executeQuery();
            
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "create");
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
                logSQLException(e, "create");
                throw e;
            }
        }
        return;
    }
    
    /**
     * modify fault handle information
     * @author sunyx
     * @param faultHandle
     * @throws SQLException
     */
    public void modify(FaultHandle faultHandle) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " UPDATE FAULT_HANDLE_TBL SET" +
                        " FAULT_ID = ? " +
                        " ,SUPPORTER_ID = ? " +
                        " ,OPERATE_COMPANY_ID = ? " +
                        " ,OPERATOR_NAME = ? " +
                        " ,HANDLE_DETAIL = ? " +
                        " ,START_DATE = ? " +
                        " ,START_TIME = ? " +
                        " ,END_DATE = ? " +
                        " ,END_TIME = ? " +
                        " ,STATUS = ? " +
                        " ,HANDLE_TYPE = ? " +
                        " ,NOTE = ? " +
                        " WHERE " +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultHandle.getFaultId());
            pstmt.setInt(++index, faultHandle.getSupporterId());
            pstmt.setInt(++index, faultHandle.getOperateCompanyId());
            pstmt.setString(++index, faultHandle.getOperatorName());
            pstmt.setString(++index, faultHandle.getHandleDetail());
            pstmt.setString(++index, faultHandle.getStartDate());
            pstmt.setString(++index, faultHandle.getStartTime());
            pstmt.setString(++index, faultHandle.getEndDate());
            pstmt.setString(++index, faultHandle.getEndTime());
            pstmt.setString(++index, faultHandle.getStatus());
            pstmt.setInt(++index, faultHandle.getHandleType());
            pstmt.setString(++index, faultHandle.getNote());
            pstmt.setInt(++index, faultHandle.getId());
            
            rs = pstmt.executeQuery();
            
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modify");
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
                logSQLException(e, "modify");
                throw e;
            }
        }
        return;
    }
    
    /**
     * serch by id 
     * @author sunyx
     * @param id
     * @return
     * @throws SQLException
     */
    public FaultHandle serchById(Integer id) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " SELECT" +
                        " A.ID " +
                        " ,A.FAULT_ID " +
                        " ,A.SUPPORTER_ID " +
                        " ,B.FAMILY_NAME" +
                        " ,B.GIVEN_NAME" +
                        " ,A.OPERATE_COMPANY_ID " +
                        " ,C.SHORT_NAME" +
                        " ,A.OPERATOR_NAME " +
                        " ,A.HANDLE_DETAIL " +
                        " ,A.START_DATE " +
                        " ,A.START_TIME " +
                        " ,A.END_DATE " +
                        " ,A.END_TIME " +
                        " ,A.STATUS " +
                        " ,A.HANDLE_TYPE " +
                        " ,D.NAME" +
                        " ,A.NOTE " +
                        " FROM " +
                        " FAULT_HANDLE_TBL A" +
                        " LEFT OUTER JOIN USER_TBL B ON A.SUPPORTER_ID = B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL C ON A.OPERATE_COMPANY_ID = C.ID" +
                        " LEFT OUTER JOIN FAULT_HANDLE_TYPE_TBL D ON A.HANDLE_TYPE = D.ID" +
                        " WHERE " +
                        " A.ID = ? ";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, id);
            rs = pstmt.executeQuery();

            FaultHandle fh = new FaultHandle();
            if(rs.next()){
                index = 0;
                fh.setId(rs.getInt(++index));
                fh.setFaultId(rs.getInt(++index));
                fh.setSupporterId(rs.getInt(++index));
                fh.setSupporterFamily(rs.getString(++index));
                fh.setSupporterGiven(rs.getString(++index));
                fh.setOperateCompanyId(rs.getInt(++index));
                fh.setOperateCompanyName(rs.getString(++index));
                fh.setOperatorName(rs.getString(++index));
                fh.setHandleDetail(rs.getString(++index));
                fh.setStartDate(rs.getString(++index));
                fh.setStartTime(rs.getString(++index));
                fh.setEndDate(rs.getString(++index));
                fh.setEndTime(rs.getString(++index));
                fh.setStatus(rs.getString(++index));
                fh.setHandleType(rs.getInt(++index));
                fh.setHandleTypeName(rs.getString(++index));
                fh.setNote(rs.getString(++index));
            }

            return fh;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modify");
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
                logSQLException(e, "modify");
                throw e;
            }
        }
    }
    
    /**
     * serch detail
     * @author sunyx
     * @return
     * @throws SQLException
     */
    public List<FaultHandle> serchAllByFaultId(Integer faultId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FaultHandle> fhList = new ArrayList<FaultHandle>();

        try {
            // Start UOC
            String sql = " SELECT" +
                        " A.ID " +
                        " ,A.FAULT_ID " +
                        " ,A.SUPPORTER_ID " +
                        " ,A.OPERATE_COMPANY_ID " +
                        " ,B.SHORT_NAME " +
                        " ,A.OPERATOR_NAME " +
                        " ,A.HANDLE_DETAIL " +
                        " ,A.START_DATE " +
                        " ,A.START_TIME " +
                        " ,A.END_DATE " +
                        " ,A.END_TIME " +
                        " ,A.STATUS " +
                        " ,A.HANDLE_TYPE " +
                        " ,A.NOTE " +
                        " FROM " +
                        " FAULT_HANDLE_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.OPERATE_COMPANY_ID = B.ID" +
                        " WHERE " +
                        " A.FAULT_ID = ?" +
                        " ORDER BY ID DESC";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, faultId);
            rs = pstmt.executeQuery();
            
            FaultHandle fh = null;
            while (rs.next()){
                fh = new FaultHandle();
                index = 0; 
                fh.setId(rs.getInt(++index));
                fh.setFaultId(rs.getInt(++index));
                fh.setSupporterId(rs.getInt(++index));
                fh.setOperateCompanyId(rs.getInt(++index));
                fh.setOperateCompanyName(rs.getString(++index));
                fh.setOperatorName(rs.getString(++index));
                fh.setHandleDetail(rs.getString(++index));
                fh.setStartDate(rs.getString(++index));
                fh.setStartTime(rs.getString(++index));
                fh.setEndDate(rs.getString(++index));
                fh.setEndTime(rs.getString(++index));
                fh.setStatus(rs.getString(++index));
                fh.setHandleType(rs.getInt(++index));
                fh.setNote(rs.getString(++index));
                
                fhList.add(fh);
            }
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "serchAll");
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
                logSQLException(e, "serchAll");
                throw e;
            }
        }
        return fhList;
    }
    



    /**
     * delete
     * @author sunyx
     * @param id
     * @throws SQLException
     */
    public void delete(Integer id) throws SQLException{
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " FAULT_HANDLE_TBL " +
                       " WHERE " +
                       " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, id);

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "delete");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "delete");
                throw e;
            }
        }
    }
    
    /**
     * get count by ID.
     * @author sunyx
     * @param   id
     * @return  int
     * @throws  SQLException
     */
    public int getCountById(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " FAULT_HANDLE_TBL " +
                       " WHERE  " +
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
     * get count by fault ID.
     * @author luyan
     * @since 1.0
     * @param faultIdIn fault ID
     * @return count
     * @throws SQLException
     */
    public int getCountByFaultId(Integer faultIdIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
                "   COUNT(*) " +
                " FROM " +
                "   FAULT_HANDLE_TBL " +
                " WHERE  " +
                "   FAULT_ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIdIn);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByFaultId");
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
                logSQLException(e, "getCountByFaultId");
                throw e;
            }
        }
        return count;
    }
}
