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

import cn.com.bhh.erp.entity.Fault;


public class FaultDetailDao extends BaseDao {
    public FaultDetailDao(Connection conn) {
        super(conn);
    }

    /**
     * insert fault detail information.
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
            String sql = " INSERT INTO FAULT_DETAIL_TBL ( " +
                "   ID, " +
                "   OCCUR_CONDICTION_1, " +
                "   OCCUR_CONDICTION_2, " +
                "   ERROR_CODE, " +
                "   RX278, " +
                "   COUNTER, " +
                "   APPEARANCE, " +
                "   REASON, " +
                "   STRATEGY, " +
                "   STRATEGY_DETAIL, " +
                "   RESULT, " +
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
                "   APPLICATION_VERSION, " +
                "   OTHERS, " +
                "   C_RAS, " +
                "   D_RAS, " +
                "   MCU_LOG, " +
                "   SYSTEM_EVENT, " +
                "   D_BILLBOX, " +
                "   D_COLLECT, " +
                "   C_FEP, " +
                "   C_ERR, " +
                "   APPLY_DATE, " +
                "   DELIVER_DATE, " +
                "   REPLACE_DATE, " +
                "   RECEIVE_DATE " +
                " ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ";


            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getId());
            pstmt.setInt(++index, faultIn.getOccurCondiction1());
            pstmt.setInt(++index, faultIn.getOccurCondiction2());
            pstmt.setString(++index, faultIn.getErrorCode());
            pstmt.setString(++index, faultIn.getRx278());
            pstmt.setString(++index, faultIn.getCounter());
            pstmt.setString(++index, faultIn.getAppearance());
            pstmt.setString(++index, faultIn.getReason());
            pstmt.setString(++index, faultIn.getStrategy());
            pstmt.setString(++index, faultIn.getStrategyDetail());
            pstmt.setInt(++index, faultIn.getResultId());
            pstmt.setString(++index, faultIn.getOperation1());
            pstmt.setString(++index, faultIn.getOperation2());
            pstmt.setString(++index, faultIn.getOperation3());
            pstmt.setString(++index, faultIn.getOperation4());
            pstmt.setString(++index, faultIn.getOperation5());
            pstmt.setString(++index, faultIn.getOperation6());
            pstmt.setString(++index, faultIn.getOperation7());
            pstmt.setString(++index, faultIn.getOperation8());
            pstmt.setInt(++index, faultIn.getCashLeft());
            pstmt.setInt(++index, faultIn.getExceptionDisplay());
            pstmt.setInt(++index, faultIn.getDisplay());
            pstmt.setInt(++index, faultIn.getBackLight());
            pstmt.setString(++index, faultIn.getDisplayContent());
            pstmt.setInt(++index, faultIn.getInputable());
            pstmt.setInt(++index, faultIn.getKnockSound());
            pstmt.setInt(++index, faultIn.getReset());
            pstmt.setInt(++index, faultIn.getCutPower());
            pstmt.setInt(++index, faultIn.getRebootNormally());
            pstmt.setString(++index, faultIn.getMotionCounter());
            pstmt.setString(++index, faultIn.getErrorNo());
            pstmt.setString(++index, faultIn.getNoRepon());
            pstmt.setString(++index, faultIn.getOtherDisplay());
            pstmt.setInt(++index, faultIn.getTraceInfomation());
            pstmt.setInt(++index, faultIn.getFaultRecord());
            pstmt.setInt(++index, faultIn.getStatistics());
            pstmt.setInt(++index, faultIn.getTradeLog());
            pstmt.setInt(++index, faultIn.getApplicationVersion1());
            pstmt.setString(++index, faultIn.getOthers());
            pstmt.setInt(++index, faultIn.getCras());
            pstmt.setInt(++index, faultIn.getDras());
            pstmt.setInt(++index, faultIn.getMcuLog());
            pstmt.setInt(++index, faultIn.getSystemEvent());
            pstmt.setInt(++index, faultIn.getDbillbox());
            pstmt.setInt(++index, faultIn.getDcollect());
            pstmt.setInt(++index, faultIn.getCfep());
            pstmt.setInt(++index, faultIn.getCerr());
            pstmt.setString(++index, faultIn.getApplyDate());
            pstmt.setString(++index, faultIn.getDeliverDate());
            pstmt.setString(++index, faultIn.getReplaceDate());
            pstmt.setString(++index, faultIn.getIhscReceiveDate());
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
     * 
     * @auther  xiangzq
     * @version 2.0
     * @since   1.0
     * @param   
     * @return  void
     * @throws  Exception
     */
    public void insertAbio(Fault faultIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " INSERT INTO FAULT_DETAIL_TBL ( " +
                "   ID, " +
                "   OCCUR_CONDICTION_1, " +
                "   OCCUR_CONDICTION_2, " +
                "   APPEARANCE, " +
                "   REASON, " +
                "   STRATEGY, " +
                "   STRATEGY_DETAIL, " +
                "   RESULT, " +
                "   OPERATION_1, " +
                "   OPERATION_2, " +
                "   OPERATION_3, " +
                "   OPERATION_4, " +
                "   OPERATION_5, " +
                "   OPERATION_6, " +
                "   OPERATION_7, " +
                "   OPERATION_8," +
                "   RESET , " +
                "   CUT_POWER ," +
                "   REBOOT_NORMALLY, " +
                "   MOTION_COUNTER, " +
                "   ERROR_NO, " +
                "   NO_REPON, " +
                "   OTHER_DISPLAY, " +
                "   TRACE_INFOMATION, " +
                "   FAULT_RECORD, " +
                "   STATISTICS, " +
                "   TRADE_LOG, " +
                "   APPLICATION_VERSION, " +
                "   OTHERS, " +
                "   APPLY_DATE, " +
                "   DELIVER_DATE, "+
                "   REPLACE_DATE, " ;
                StringBuffer sbSql = new StringBuffer(sql);    
                if(faultIn.getErrorCode()!=null){
                    sbSql.append(" ERROR_CODE, ");
                }
                if(faultIn.getRx278()!=null){
                    sbSql.append(" RX278, ");
                }
                if(faultIn.getCounter()!=null){
                    sbSql.append(" COUNTER, ");
                }
                if(faultIn.getCashLeft()!=null){
                    sbSql.append(" CASH_LEFT, ");
                }
                if(faultIn.getExceptionDisplay()!=null){
                    sbSql.append(" EXCEPTION_DISPLAY, ");
                }
                if(faultIn.getDisplay()!=null){
                    sbSql.append(" DISPLAY, ");
                }
                if(faultIn.getBackLight()!=null){
                    sbSql.append(" BACK_LIGHT, ");
                }
                if(faultIn.getDisplay()!=null){
                    sbSql.append(" DISPLAY_CONTENT, ");
                }
                if(faultIn.getInputable()!=null){
                    sbSql.append(" INPUTABLE , ");
                }
                if(faultIn.getKnockSound()!=null){
                    sbSql.append(" KNOCK_SOUND , ");
                }
                
                if(faultIn.getEcErrorCode()!=null){
                    sbSql.append(" EC_ERROR_CODE , ");
                }
                if(faultIn.getMtcCode()!=null){
                    sbSql.append(" MTC_CODE , ");
                }
                if(faultIn.getCashControlLight()!=null){
                    sbSql.append(" CASH_CONTROL_LIGHT , ");
                }
                if(faultIn.getFaultLight()!=null){
                    sbSql.append(" FAULT_LIGHT , ");
                }
                if(faultIn.getShineLight()!=null){
                    sbSql.append(" SHINE_LIGHT , ");
                }
                if(faultIn.getStayShineLight()!=null){
                    sbSql.append(" STAY_SHINE_LIGHT , ");
                }
                if(faultIn.getOutLight()!=null){
                    sbSql.append(" OUT_LIGHT , ");
                }
                
                if(faultIn.getLeftOnlineLight()!=null){
                    sbSql.append(" LEFT_ONLINE , ");
                }
                if(faultIn.getRightOnlineLight()!=null){
                    sbSql.append(" RIHGT_ONLINE , ");
                }
                
                if(faultIn.getCras()!=null){
                    sbSql.append(" C_RAS , ");
                }
                
                if(faultIn.getDras()!=null){
                    sbSql.append(" D_RAS , ");
                }
                if(faultIn.getMcuLog()!=null){
                    sbSql.append(" MCU_LOG , ");
                }
                if(faultIn.getSystemEvent()!=null){
                    sbSql.append(" SYSTEM_EVENT , ");
                }
                if(faultIn.getDbillbox()!=null){
                    sbSql.append(" D_BILLBOX , ");
                }
                if(faultIn.getDcollect()!=null){
                    sbSql.append(" D_COLLECT , ");
                }
                if(faultIn.getCfep()!=null){
                    sbSql.append(" C_FEP, ");
                }
                if(faultIn.getCerr()!=null){
                    sbSql.append(" C_ERR , ");
                }
                
                if(faultIn.getBrmEpVer()!=null){
                    sbSql.append(" BRM_EP_VER  , ");
                }
                if(faultIn.getBvEpVer()!=null){
                    sbSql.append(" BV_EP_VER , ");
                }
                
                if(faultIn.getMainEpver()!=null){
                    sbSql.append(" MAIN__EP_VER , ");
                }
                
                if(faultIn.getBidEpver()!=null){
                    sbSql.append(" BID_EP_VER  , ");
                }
                
                if(faultIn.getEpVer()!=null){
                    sbSql.append(" EP_VER  , ");
                }
 
             
                sbSql.append(" RECEIVE_DATE ");
                
                sbSql.append(" ) VALUES (  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ");
                sbSql.append(" ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,");
                
                if(faultIn.getErrorCode()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getRx278()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getCounter()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getCashLeft()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getExceptionDisplay()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getDisplay()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getBackLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getDisplay()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getInputable()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getKnockSound()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getEcErrorCode()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getMtcCode()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getCashControlLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getFaultLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getShineLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getStayShineLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getOutLight()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getLeftOnlineLight()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getRightOnlineLight()!=null){
                    sbSql.append(" ?,");
                }
             
                if(faultIn.getCras()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getDras()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getMcuLog()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getSystemEvent()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getDbillbox()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getDcollect()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getCfep()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getCerr()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getBrmEpVer()!=null){
                    sbSql.append(" ?,");
                }
                if(faultIn.getBvEpVer()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getMainEpver()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getBidEpver()!=null){
                    sbSql.append(" ?,");
                }
                
                if(faultIn.getEpVer()!=null){
                    sbSql.append(" ?,");
                }
                
                
                sbSql.append(" ? )");


            pstmt = conn.prepareStatement(sbSql.toString());

            int index = 0;
            pstmt.setInt(++index, faultIn.getId());
            pstmt.setInt(++index, faultIn.getOccurCondiction1());
            pstmt.setInt(++index, faultIn.getOccurCondiction2());
            pstmt.setString(++index, faultIn.getAppearance());
            pstmt.setString(++index, faultIn.getReason());
            pstmt.setString(++index, faultIn.getStrategy());
            pstmt.setString(++index, faultIn.getStrategyDetail());
            pstmt.setInt(++index, faultIn.getResultId());
            pstmt.setString(++index, faultIn.getOperation1());
            pstmt.setString(++index, faultIn.getOperation2());
            pstmt.setString(++index, faultIn.getOperation3());
            pstmt.setString(++index, faultIn.getOperation4());
            pstmt.setString(++index, faultIn.getOperation5());
            pstmt.setString(++index, faultIn.getOperation6());
            pstmt.setString(++index, faultIn.getOperation7());
            pstmt.setString(++index, faultIn.getOperation8());
            pstmt.setInt(++index, faultIn.getReset());
            pstmt.setInt(++index, faultIn.getCutPower());
            pstmt.setInt(++index, faultIn.getRebootNormally());
            pstmt.setString(++index, faultIn.getMotionCounter());
            pstmt.setString(++index, faultIn.getErrorNo());
            pstmt.setString(++index, faultIn.getNoRepon());
            pstmt.setString(++index, faultIn.getOtherDisplay());
            pstmt.setInt(++index, faultIn.getTraceInfomation());
            pstmt.setInt(++index, faultIn.getFaultRecord());
            pstmt.setInt(++index, faultIn.getStatistics());
            pstmt.setInt(++index, faultIn.getTradeLog());
            pstmt.setInt(++index, faultIn.getApplicationVersion1());
            pstmt.setString(++index, faultIn.getOthers());
    
            pstmt.setString(++index, faultIn.getApplyDate());
            pstmt.setString(++index, faultIn.getDeliverDate());
            pstmt.setString(++index, faultIn.getReplaceDate());
            
            if(faultIn.getErrorCode()!=null){
                pstmt.setString(++index, faultIn.getErrorCode());
            }
            if(faultIn.getRx278()!=null){
                pstmt.setString(++index, faultIn.getRx278());
            }
            if(faultIn.getCounter()!=null){
                pstmt.setString(++index, faultIn.getCounter());
            }
            if(faultIn.getCashLeft()!=null){
                pstmt.setInt(++index, faultIn.getCashLeft());
            }
            if(faultIn.getExceptionDisplay()!=null){
                pstmt.setInt(++index, faultIn.getExceptionDisplay());
            }
            if(faultIn.getDisplay()!=null){
                pstmt.setInt(++index, faultIn.getDisplay());
            }
            if(faultIn.getBackLight()!=null){
                pstmt.setInt(++index, faultIn.getBackLight());
            }
            if(faultIn.getDisplay()!=null){
                pstmt.setString(++index, faultIn.getDisplayContent());
            }
            if(faultIn.getInputable()!=null){
                pstmt.setInt(++index, faultIn.getInputable());
            }
            if(faultIn.getKnockSound()!=null){
                pstmt.setInt(++index, faultIn.getKnockSound());
            }
            
            if(faultIn.getEcErrorCode()!=null){
                pstmt.setString(++index, faultIn.getEcErrorCode());
            }
            if(faultIn.getMtcCode()!=null){
                pstmt.setString(++index, faultIn.getMtcCode());
            }
            if(faultIn.getCashControlLight()!=null){
                pstmt.setInt(++index, faultIn.getCashControlLight());
            }
            if(faultIn.getFaultLight()!=null){
                pstmt.setInt(++index, faultIn.getFaultLight());
            }
            if(faultIn.getShineLight()!=null){
                pstmt.setString(++index, faultIn.getShineLight());
            }
            if(faultIn.getStayShineLight()!=null){
                pstmt.setString(++index, faultIn.getStayShineLight());
            }
            if(faultIn.getOutLight()!=null){
                pstmt.setString(++index, faultIn.getOutLight());
            }
            
            if(faultIn.getLeftOnlineLight()!=null){
                pstmt.setInt(++index, faultIn.getLeftOnlineLight());
            }
            if(faultIn.getRightOnlineLight()!=null){
                pstmt.setInt(++index, faultIn.getRightOnlineLight());
            }
            
            if(faultIn.getCras()!=null){
                pstmt.setInt(++index, faultIn.getCras());
            }
            
            if(faultIn.getDras()!=null){
                pstmt.setInt(++index, faultIn.getDras());
            }
            if(faultIn.getMcuLog()!=null){
                pstmt.setInt(++index, faultIn.getMcuLog());
            }
            if(faultIn.getSystemEvent()!=null){
                pstmt.setInt(++index, faultIn.getSystemEvent());
            }
            if(faultIn.getDbillbox()!=null){
                pstmt.setInt(++index, faultIn.getDbillbox());
            }
            if(faultIn.getDcollect()!=null){
                pstmt.setInt(++index, faultIn.getDcollect());
            }
            if(faultIn.getCfep()!=null){
                pstmt.setInt(++index, faultIn.getCfep());
            }
            if(faultIn.getCerr()!=null){
                pstmt.setInt(++index, faultIn.getCerr());
            }
            
            if(faultIn.getBrmEpVer()!=null){
                pstmt.setString(++index, faultIn.getBrmEpVer());
            }
            if(faultIn.getBvEpVer()!=null){
                pstmt.setString(++index, faultIn.getBvEpVer());
            }
            
            if(faultIn.getMainEpver()!=null){
                pstmt.setString(++index, faultIn.getMainEpver());
            }
            
            if(faultIn.getBidEpver()!=null){
                pstmt.setString(++index, faultIn.getBidEpver());
            }
            
            if(faultIn.getEpVer()!=null){
                pstmt.setString(++index, faultIn.getEpVer());
            }
            
            pstmt.setString(++index, faultIn.getIhscReceiveDate());
            
            
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
     * modify fault detail information by ID.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void modifyById(Fault faultIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " UPDATE" +
                "   FAULT_DETAIL_TBL" +
                " SET" +
                "   OCCUR_CONDICTION_1 = ?, " +
                "   OCCUR_CONDICTION_2 = ?, " +
                "   ERROR_CODE = ?, " +
                "   RX278 = ?, " +
                "   COUNTER = ?, " +
                "   APPEARANCE = ?, " +
                "   REASON = ?, " +
                "   STRATEGY = ?, " +
                "   STRATEGY_DETAIL = ?, " +
                "   RESULT = ?, " +
                "   OPERATION_1 = ?, " +
                "   OPERATION_2 = ?, " +
                "   OPERATION_3 = ?, " +
                "   OPERATION_4 = ?, " +
                "   OPERATION_5 = ?, " +
                "   OPERATION_6 = ?, " +
                "   OPERATION_7 = ?, " +
                "   OPERATION_8 = ?, " +
                "   CASH_LEFT = ?, " +
                "   EXCEPTION_DISPLAY = ?, " +
                "   DISPLAY = ?, " +
                "   BACK_LIGHT = ?, " +
                "   DISPLAY_CONTENT = ?, " +
                "   INPUTABLE = ?, " +
                "   KNOCK_SOUND = ?, " +
                "   RESET = ?, " +
                "   CUT_POWER = ?, " +
                "   REBOOT_NORMALLY = ?, " +
                "   MOTION_COUNTER = ?, " +
                "   ERROR_NO = ?, " +
                "   NO_REPON = ?, " +
                "   OTHER_DISPLAY = ?, " +
                "   TRACE_INFOMATION = ?, " +
                "   FAULT_RECORD = ?, " +
                "   STATISTICS = ?, " +
                "   TRADE_LOG = ?, " +
                "   APPLICATION_VERSION = ?, " +
                "   OTHERS = ?, " +
                "   C_RAS = ?, " +
                "   D_RAS = ?, " +
                "   MCU_LOG = ?, " +
                "   SYSTEM_EVENT = ?, " +
                "   D_BILLBOX = ?, " +
                "   D_COLLECT = ?, " +
                "   C_FEP = ?, " +
                "   C_ERR = ?, " +
                "   APPLY_DATE = ?, " +
                "   DELIVER_DATE = ?, " +
                "   REPLACE_DATE = ?, " +
                "   RECEIVE_DATE = ? " +
                " WHERE " +
                "   ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultIn.getOccurCondiction1());
            pstmt.setInt(++index, faultIn.getOccurCondiction2());
            pstmt.setString(++index, faultIn.getErrorCode());
            pstmt.setString(++index, faultIn.getRx278());
            pstmt.setString(++index, faultIn.getCounter());
            pstmt.setString(++index, faultIn.getAppearance());
            pstmt.setString(++index, faultIn.getReason());
            pstmt.setString(++index, faultIn.getStrategy());
            pstmt.setString(++index, faultIn.getStrategyDetail());
            pstmt.setInt(++index, faultIn.getResultId());
            pstmt.setString(++index, faultIn.getOperation1());
            pstmt.setString(++index, faultIn.getOperation2());
            pstmt.setString(++index, faultIn.getOperation3());
            pstmt.setString(++index, faultIn.getOperation4());
            pstmt.setString(++index, faultIn.getOperation5());
            pstmt.setString(++index, faultIn.getOperation6());
            pstmt.setString(++index, faultIn.getOperation7());
            pstmt.setString(++index, faultIn.getOperation8());
            pstmt.setInt(++index, faultIn.getCashLeft());
            pstmt.setInt(++index, faultIn.getExceptionDisplay());
            pstmt.setInt(++index, faultIn.getDisplay());
            pstmt.setInt(++index, faultIn.getBackLight());
            pstmt.setString(++index, faultIn.getDisplayContent());
            pstmt.setInt(++index, faultIn.getInputable());
            pstmt.setInt(++index, faultIn.getKnockSound());
            pstmt.setInt(++index, faultIn.getReset());
            pstmt.setInt(++index, faultIn.getCutPower());
            pstmt.setInt(++index, faultIn.getRebootNormally());
            pstmt.setString(++index, faultIn.getMotionCounter());
            pstmt.setString(++index, faultIn.getErrorNo());
            pstmt.setString(++index, faultIn.getNoRepon());
            pstmt.setString(++index, faultIn.getOtherDisplay());
            pstmt.setInt(++index, faultIn.getTraceInfomation());
            pstmt.setInt(++index, faultIn.getFaultRecord());
            pstmt.setInt(++index, faultIn.getStatistics());
            pstmt.setInt(++index, faultIn.getTradeLog());
            pstmt.setInt(++index, faultIn.getApplicationVersion1());
            pstmt.setString(++index, faultIn.getOthers());
            pstmt.setInt(++index, faultIn.getCras());
            pstmt.setInt(++index, faultIn.getDras());
            pstmt.setInt(++index, faultIn.getMcuLog());
            pstmt.setInt(++index, faultIn.getSystemEvent());
            pstmt.setInt(++index, faultIn.getDbillbox());
            pstmt.setInt(++index, faultIn.getDcollect());
            pstmt.setInt(++index, faultIn.getCfep());
            pstmt.setInt(++index, faultIn.getCerr());
            pstmt.setString(++index, faultIn.getApplyDate());
            pstmt.setString(++index, faultIn.getDeliverDate());
            pstmt.setString(++index, faultIn.getReplaceDate());
            pstmt.setString(++index, faultIn.getIhscReceiveDate());
            pstmt.setInt(++index, faultIn.getId());
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
     * modify fault detail information by ID.
     * @author luyan
     * @since 1.0
     * @param faultIn fault information
     * @throws SQLException
     */
    public void modifyAbioById(Fault faultIn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " UPDATE" +
                "   FAULT_DETAIL_TBL" +
                " SET" +
                "   OCCUR_CONDICTION_1 = ?, " +
                "   OCCUR_CONDICTION_2 = ?, " +
                "   APPEARANCE = ?, " +
                "   REASON = ?, " +
                "   STRATEGY = ?, " +
                "   STRATEGY_DETAIL = ?, " +
                "   RESULT = ?, " +
                "   OPERATION_1 = ?, " +
                "   OPERATION_2 = ?, " +
                "   OPERATION_3 = ?, " +
                "   OPERATION_4 = ?, " +
                "   OPERATION_5 = ?, " +
                "   OPERATION_6 = ?, " +
                "   OPERATION_7 = ?, " +
                "   OPERATION_8 = ?, " +
                "   RESET = ?, " +
                "   CUT_POWER = ?, " +
                "   REBOOT_NORMALLY = ?, " +
                "   ERROR_NO = ?, " +
                "   NO_REPON = ?, " +
                "   OTHER_DISPLAY = ?, " +
                "   TRACE_INFOMATION = ?, " +
                "   FAULT_RECORD = ?, " +
                "   STATISTICS = ?, " +
                "   TRADE_LOG = ?, " +
                "   APPLICATION_VERSION = ?, " +
                "   OTHERS = ?, " +
                "   APPLY_DATE = ?, " +
                "   DELIVER_DATE = ?, " +
                "   REPLACE_DATE = ?, " ;
               
            
                StringBuffer sbSql = new StringBuffer(sql);
                
                if("ATM".equals(faultIn.getFaultMachineType())){
                    sbSql.append(" BRM_EP_VER = ?  , ");
                    sbSql.append(" BV_EP_VER = ?  , ");
                    sbSql.append(" ERROR_CODE = ?, ");
                    sbSql.append(" RX278 = ?, ");
                    sbSql.append(" COUNTER = ?, ");
                    sbSql.append(" MOTION_COUNTER = ?, ");
                    
                    sbSql.append(" CASH_LEFT = ?, ");
                    sbSql.append(" EXCEPTION_DISPLAY = ?, ");
                    sbSql.append(" DISPLAY = ?, ");
                    sbSql.append(" BACK_LIGHT = ?, ");
                    sbSql.append(" DISPLAY_CONTENT = ?, ");
                    sbSql.append(" INPUTABLE = ? , ");
                    sbSql.append(" KNOCK_SOUND = ? , ");
                    
                    sbSql.append(" C_RAS = ? , ");
                    sbSql.append(" D_RAS = ? , ");
                    sbSql.append(" MCU_LOG = ? , ");
                    sbSql.append(" SYSTEM_EVENT = ? , ");
                    sbSql.append(" D_BILLBOX = ? , ");
                    sbSql.append(" D_COLLECT = ? , ");
                    sbSql.append(" C_FEP = ?, ");
                    sbSql.append(" C_ERR = ? , ");
                    
                }else if("G-ABIO".equals(faultIn.getFaultMachineType())){
                    sbSql.append(" MAIN__EP_VER = ?  , ");
                    sbSql.append(" BID_EP_VER = ?   , ");
                    sbSql.append(" EC_ERROR_CODE = ?  , ");
                    sbSql.append(" MTC_CODE = ?  , ");
                    sbSql.append(" CASH_CONTROL_LIGHT = ?  , ");
                    sbSql.append(" FAULT_LIGHT = ?  , ");
                    sbSql.append(" SHINE_LIGHT = ?  , ");
                    sbSql.append(" STAY_SHINE_LIGHT = ?  , ");
                    sbSql.append(" OUT_LIGHT = ?  , ");
                    sbSql.append(" LEFT_ONLINE = ?  , ");
                    sbSql.append(" RIHGT_ONLINE = ?  , ");
                }else{
                    //标准
                    sbSql.append(" EP_VER = ?   , ");
                    sbSql.append(" ERROR_CODE = ?, ");
                    sbSql.append(" MOTION_COUNTER = ?, ");
                    
                    sbSql.append(" CASH_LEFT = ?, ");
                    sbSql.append(" EXCEPTION_DISPLAY = ?, ");
                    sbSql.append(" DISPLAY = ?, ");
                    sbSql.append(" BACK_LIGHT = ?, ");
                    sbSql.append(" DISPLAY_CONTENT = ?, ");
                    sbSql.append(" INPUTABLE = ? , ");
                    sbSql.append(" KNOCK_SOUND = ? , ");
                    
                }
                
                sbSql.append("   RECEIVE_DATE = ? ");
                sbSql.append("   WHERE   ");
                sbSql.append("   ID = ? ");

            pstmt = conn.prepareStatement(sbSql.toString());

            int index = 0;
            pstmt.setInt(++index, faultIn.getOccurCondiction1());
            pstmt.setInt(++index, faultIn.getOccurCondiction2());
            pstmt.setString(++index, faultIn.getAppearance());
            pstmt.setString(++index, faultIn.getReason());
            pstmt.setString(++index, faultIn.getStrategy());
            pstmt.setString(++index, faultIn.getStrategyDetail());
            pstmt.setInt(++index, faultIn.getResultId());
            pstmt.setString(++index, faultIn.getOperation1());
            pstmt.setString(++index, faultIn.getOperation2());
            pstmt.setString(++index, faultIn.getOperation3());
            pstmt.setString(++index, faultIn.getOperation4());
            pstmt.setString(++index, faultIn.getOperation5());
            pstmt.setString(++index, faultIn.getOperation6());
            pstmt.setString(++index, faultIn.getOperation7());
            pstmt.setString(++index, faultIn.getOperation8());
            pstmt.setInt(++index, faultIn.getReset());
            pstmt.setInt(++index, faultIn.getCutPower());
            pstmt.setInt(++index, faultIn.getRebootNormally());
            pstmt.setString(++index, faultIn.getErrorNo());
            pstmt.setString(++index, faultIn.getNoRepon());
            pstmt.setString(++index, faultIn.getOtherDisplay());
            pstmt.setInt(++index, faultIn.getTraceInfomation());
            pstmt.setInt(++index, faultIn.getFaultRecord());
            pstmt.setInt(++index, faultIn.getStatistics());
            pstmt.setInt(++index, faultIn.getTradeLog());
            pstmt.setInt(++index, faultIn.getApplicationVersion1());
            pstmt.setString(++index, faultIn.getOthers());
            pstmt.setString(++index, faultIn.getApplyDate());
            pstmt.setString(++index, faultIn.getDeliverDate());
            pstmt.setString(++index, faultIn.getReplaceDate());
           
            if("ATM".equals(faultIn.getFaultMachineType())){
                //ATM 
                pstmt.setString(++index, faultIn.getBrmEpVer());
                pstmt.setString(++index, faultIn.getBvEpVer());
                pstmt.setString(++index, faultIn.getErrorCode());
                pstmt.setString(++index, faultIn.getRx278());
                pstmt.setString(++index, faultIn.getCounter());
                pstmt.setString(++index, faultIn.getMotionCounter());
                
                pstmt.setInt(++index, faultIn.getCashLeft());
                pstmt.setInt(++index, faultIn.getExceptionDisplay());
                pstmt.setInt(++index, faultIn.getDisplay());
                pstmt.setInt(++index, faultIn.getBackLight());
                pstmt.setString(++index, faultIn.getDisplayContent());
                pstmt.setInt(++index, faultIn.getInputable());
                pstmt.setInt(++index, faultIn.getKnockSound());
                
                pstmt.setInt(++index, faultIn.getCras());
                pstmt.setInt(++index, faultIn.getDras());
                pstmt.setInt(++index, faultIn.getMcuLog());
                pstmt.setInt(++index, faultIn.getSystemEvent());
                pstmt.setInt(++index, faultIn.getDbillbox());
                pstmt.setInt(++index, faultIn.getDcollect());
                pstmt.setInt(++index, faultIn.getCfep());
                pstmt.setInt(++index, faultIn.getCerr());
                
            }else if("G-ABIO".equals(faultIn.getFaultMachineType())){
                //G-ABIO
                pstmt.setString(++index, faultIn.getMainEpver());
                pstmt.setString(++index, faultIn.getBidEpver());
                pstmt.setString(++index, faultIn.getEcErrorCode());
                pstmt.setString(++index, faultIn.getMtcCode());
                pstmt.setInt(++index, faultIn.getCashControlLight());
                pstmt.setInt(++index, faultIn.getFaultLight());
                pstmt.setString(++index, faultIn.getShineLight());
                pstmt.setString(++index, faultIn.getStayShineLight());
                pstmt.setString(++index, faultIn.getOutLight());
                pstmt.setInt(++index, faultIn.getLeftOnlineLight());
                pstmt.setInt(++index, faultIn.getRightOnlineLight());
                
                  
            }else{
                //标准
                pstmt.setString(++index, faultIn.getEpVer());
                pstmt.setString(++index, faultIn.getErrorCode());
                pstmt.setString(++index, faultIn.getMotionCounter());
                pstmt.setInt(++index, faultIn.getCashLeft());
                pstmt.setInt(++index, faultIn.getExceptionDisplay());
                pstmt.setInt(++index, faultIn.getDisplay());
                pstmt.setInt(++index, faultIn.getBackLight());
                pstmt.setString(++index, faultIn.getDisplayContent());
                pstmt.setInt(++index, faultIn.getInputable());
                pstmt.setInt(++index, faultIn.getKnockSound());
            }
            
            pstmt.setString(++index, faultIn.getIhscReceiveDate());
            pstmt.setInt(++index, faultIn.getId());
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
     * delete fault detail information by fault ID.
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

            String sql = " DELETE FROM " +
                "   FAULT_DETAIL_TBL " +
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
}
