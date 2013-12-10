//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/23
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.InstallationApply;
import cn.com.bhh.erp.entity.User;

public class InstApplyDao extends BaseDao {

    public InstApplyDao(Connection conn) {
        super(conn);
    }
    
    /**
     * the check of add
     * @author sunyx
     * @param instApp
     * @return count 
     * @throws SQLException
     */
    public int addCheck(InstallationApply instApp) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            int count = 0;
            String sql = " SELECT" +
                        " COUNT(*)" +
                        " FROM" +
                        " INST_APPLY_TBL" +
                        " WHERE" +
                        " TYPE = ?" +
                        " AND " +
                        " INSTALL_ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, instApp.getType());
            pstmt.setInt(++index, instApp.getInstallId());
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
            return count;
        } catch (SQLException e) {
            logSQLException(e, "addCheck");
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
                logSQLException(e, "addCheck");
                throw e;
            }
        }
    }
    
    /**
     * the check of add
     * @author sunyx
     * @param instApp
     * @return count 
     * @throws SQLException
     */
    public int updateCheck(InstallationApply instApp) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            int count = 0;
            String sql = " SELECT" +
                        " COUNT(*)" +
                        " FROM" +
                        " INST_APPLY_TBL" +
                        " WHERE" +
                        " TYPE = ?" +
                        " AND " +
                        " INSTALL_ID = ?" +
                        " AND " +
                        " ID <> ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, instApp.getType());
            pstmt.setInt(++index, instApp.getInstallId());
            pstmt.setInt(++index, instApp.getId());
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
            return count;
        } catch (SQLException e) {
            logSQLException(e, "updateCheck");
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
                logSQLException(e, "updateCheck");
                throw e;
            }
        }
    }
    
    /**
     * add an apply
     * @author sunyx
     * @param instApp
     * @throws SQLException
     */
    public void addApply(InstallationApply instApp) throws SQLException{
        PreparedStatement pstmt = null;

        try{
            // Start UOC
            String sql = "INSERT INTO INST_APPLY_TBL (" +
                        " TYPE," +
                        " APPLYER_ID," +
                        " APPLY_DATE," +
                        " APPLYER_NOTE," +
                        " ADMIN_NOTE," +
                        " INSTALL_ID," +
                        " PRODUCT_ID," +
                        " MANUFACTURE_NO," +
                        " CUSTOMER_ID," +
                        " SALES_CONTRACT_COMP_ID," +
                        " INSTALL_COMP_ID," +
                        " INSTALLER," +
                        " INSTALLER_ID," +
                        " FIRST_REPAIR_COMPANY_ID," +
                        " NOW_REPAIR_COMPANY_ID," +
                        " INDENTURE_NO," +
                        " FOB_DATE," +
                        " INSTALL_DATE," +
                        " OPEN_DATE," +
                        " ACCEPT_DATE," +
                        " GUARANTEE_START_DATE," +
                        " GUARANTEE_END_DATE," +
                        " GUARANTEE_PERIOD," +
                        " BRANCH_COMPANY_NAME," +
                        " INSTALL_PLACE," +
                        " INST_PLACE_TYPE_ID," +
                        " USE_STATUS_ID," +
                        " PURPOSE," +
                        " CONTACT," +
                        " OFFICE_PHONE," +
                        " MOBILE_PHONE," +
                        " FAX," +
                        " EMAIL," +
                        " BRM_EP_VER," +
                        " BV_EP_VER," +
                        " BHCL_EP_VER," +
                        " TRCL_EP_VER," +
                        " KEY_NO," +
                        " NOTE," +
                        " OP_SYS," +
                        " OS_PERMIT," +
                        " PLATFORM," +
                        " PLATFORM_REV," +
                        " MCU," +
                        " BV," +
                        " HCM," +
                        " JPR," +
                        " SPR," +
                        " DESKEY," +
                        " CREATE_TIME," +
                        " CREATOR_ID," +
                        " MODIFY_TIME," +
                        " MODIFIER_ID" +
                        " ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                        " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, instApp.getType());
            pstmt.setInt(++index, instApp.getApplyerId());
            pstmt.setString(++index, instApp.getApplyDate());
            pstmt.setString(++index, instApp.getApplyerNote());
            pstmt.setString(++index, instApp.getAdminNote());
            pstmt.setInt(++index, instApp.getInstallId());
            pstmt.setInt(++index, instApp.getProductId());
            pstmt.setString(++index, instApp.getManufactureNo());
            pstmt.setInt(++index, instApp.getCustomerId());
            pstmt.setInt(++index, instApp.getSaleContractCompId());
            pstmt.setInt(++index, instApp.getInstallCompId());
            pstmt.setString(++index, instApp.getInstaller());
            pstmt.setString(++index, instApp.getInstallerId());
            pstmt.setInt(++index, instApp.getFirstRepairCompanyId());
            pstmt.setInt(++index, instApp.getNowRepairCompanyId());
            pstmt.setString(++index, instApp.getIndentureNo());
            pstmt.setString(++index, instApp.getFobDate());
            pstmt.setString(++index, instApp.getInstallDate());
            pstmt.setString(++index, instApp.getOpenDate());
            pstmt.setString(++index, instApp.getAcceptDate());
            pstmt.setString(++index, instApp.getGuaranteeStartDate());
            pstmt.setString(++index, instApp.getGuaranteeEndDate());
            pstmt.setString(++index, instApp.getGuaranteePeriod());
            pstmt.setString(++index, instApp.getSubCompany());
            pstmt.setString(++index, instApp.getInstallPlace());
            pstmt.setInt(++index, instApp.getInstPlaceTypeId());
            pstmt.setInt(++index, instApp.getUseStatusId());
            pstmt.setInt(++index, instApp.getPurpose());
            pstmt.setString(++index, instApp.getContact());
            pstmt.setString(++index, instApp.getOfficePhone());
            pstmt.setString(++index, instApp.getMobilePhone());
            pstmt.setString(++index, instApp.getFax());
            pstmt.setString(++index, instApp.getEmail());
            pstmt.setString(++index, instApp.getBrmEpVer());
            pstmt.setString(++index, instApp.getBvEpVer());
            pstmt.setString(++index, instApp.getBhclEpVer());
            pstmt.setString(++index, instApp.getTrclEpVer());
            pstmt.setString(++index, instApp.getKeyNo());
            pstmt.setString(++index, instApp.getNote());
            pstmt.setBigDecimal(++index, instApp.getOs());
            pstmt.setBigDecimal(++index, instApp.getOsPermitId());
            pstmt.setInt(++index, instApp.getPlatform());
            pstmt.setString(++index, instApp.getPlatformRev());
            pstmt.setString(++index, instApp.getMcu());
            pstmt.setString(++index, instApp.getBv());
            pstmt.setString(++index, instApp.getHcm());
            pstmt.setString(++index, instApp.getJpr());
            pstmt.setString(++index, instApp.getSpr());
            pstmt.setString(++index, instApp.getDeskey());
            pstmt.setString(++index, instApp.getCreateTime());
            pstmt.setInt(++index, instApp.getCreatorId());
            pstmt.setString(++index, instApp.getModifyTime());
            pstmt.setInt(++index, instApp.getModifierId());
            
            pstmt.executeUpdate();
            
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "addApply");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "addApply");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @return
     * @throws Exception
     */
    public int getCounts() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            int count = 0;

            String sql = "SELECT " +
                        " COUNT(*)" +
                        " FROM " +
                        " INST_APPLY_TBL";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            if(rs.next()){
                int index = 0;
                count = rs.getInt(++index);
            }

            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCounts");
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
                logSQLException(e, "getCounts");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @param instApp
     * @throws Exception
     */
    public void doModify(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            if(exclusiveCheck){
                String sql = " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM " +
                            " INST_APPLY_TBL " +
                            " WHERE " +
                            " ID = ? " +
                            " FOR UPDATE NOWAIT ";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, instApp.getId());
                rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != instApp.getExclusiveKey()) {
                        throw new ExclusiveException();
                    }
                } else {
                    throw new RecordNoFoundException();
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            
            Integer exKey = instApp.getExclusiveKey();
            instApp.setExclusiveKey(++exKey);
            
            String sql = " UPDATE" +
                        " INST_APPLY_TBL" +
                        " SET" +
                        " TYPE = ? ," +
                        " APPLYER_NOTE = ? ," +
                        " ADMIN_NOTE = ? ," +
                        " USE_STATUS_ID = ? ," +
                        " INSTALL_PLACE = ? ," +
                        " INST_PLACE_TYPE_ID = ?," +
                        " GUARANTEE_START_DATE = ? ," +
                        " GUARANTEE_END_DATE = ? ," +
                        " GUARANTEE_PERIOD = ? ," +
                        " NOW_REPAIR_COMPANY_ID = ? ," +
                        " CONTACT = ? ," +
                        " OFFICE_PHONE = ? ," +
                        " MOBILE_PHONE = ? ," +
                        " FAX = ? ," +
                        " EMAIL = ? ," +
                        " MODIFY_TIME = ?," +
                        " MODIFIER_ID = ?," +
                        " EXCLUSIVE_KEY = ?" +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, instApp.getType());
            pstmt.setString(++index, instApp.getApplyerNote());
            pstmt.setString(++index, instApp.getAdminNote());
            pstmt.setInt(++index, instApp.getUseStatusId());
            pstmt.setString(++index, instApp.getInstallPlace());
            pstmt.setInt(++index, instApp.getInstPlaceTypeId());
            pstmt.setString(++index, instApp.getGuaranteeStartDate());
            pstmt.setString(++index, instApp.getGuaranteeEndDate());
            pstmt.setString(++index, instApp.getGuaranteePeriod());
            pstmt.setInt(++index, instApp.getNowRepairCompanyId());
            pstmt.setString(++index, instApp.getContact());
            pstmt.setString(++index, instApp.getOfficePhone());
            pstmt.setString(++index, instApp.getMobilePhone());
            pstmt.setString(++index, instApp.getFax());
            pstmt.setString(++index, instApp.getEmail());
            pstmt.setString(++index, instApp.getModifyTime());
            pstmt.setInt(++index, instApp.getModifierId());
            pstmt.setInt(++index, instApp.getExclusiveKey());
            pstmt.setInt(++index, instApp.getId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModify");
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
                logSQLException(e, "doModify");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @param id
     * @param excusiveKey
     * @throws Exception
     */
    public void delete(Integer id,Integer excusiveKey) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            if(exclusiveCheck){
                String sql = " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM " +
                            " INST_APPLY_TBL " +
                            " WHERE " +
                            " ID = ? " +
                            " FOR UPDATE NOWAIT ";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, id);
                rs = pstmt.executeQuery();
                
                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != excusiveKey) {
                        throw new ExclusiveException();
                    }
                } else {
                    throw new RecordNoFoundException();
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }

            String sql = 
                       " DELETE " +
                       " FROM " +
                       " INST_APPLY_TBL " +
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
                if (rs != null) {
                    rs.close();
                }

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
     * @author sunyx
     * @param
     * @return List&ltInstallationApply&gt
     * @throws SQLException
     */
    public List<InstallationApply> serchApplyList(int begin,int end,User user) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<InstallationApply> list = new ArrayList<InstallationApply>();
            
            String sql = "SELECT " +
                        " ID," +
                        " TYPE," +
                        " TYPENAME," +
                        " APPLYER_ID," +
                        " APPLYERNAME," +
                        " APPLY_DATE," +
                        " MANUFACTURE_NO," +
                        " INSTALL_ID," +
                        " NOW_REPAIR_COMPANY_ID," +
                        " NOWCOMNAME," +
                        " EXCLUSIVE_KEY" +
                        " FROM (" +
                            " SELECT" +
                            " ROWNUM AS TN,"+
                            " F.*" +
                            " FROM (" +                      
                                " SELECT " + 
                                " A.ID," +
                                " A.TYPE," +
                                " B.NAME AS TYPENAME," +
                                " A.APPLYER_ID," +
                                " C.NAME AS APPLYERNAME," +
                                " A.APPLY_DATE," +
                                " A.MANUFACTURE_NO," +
                                " A.INSTALL_ID," +
                                " A.NOW_REPAIR_COMPANY_ID," +
                                " D.SHORT_NAME NOWCOMNAME," +
                                " A.EXCLUSIVE_KEY" +
                                " FROM INST_APPLY_TBL A" +
                                " LEFT OUTER JOIN INST_APPLY_TYPE_TBL B ON A.TYPE=B.ID" +
                                " LEFT OUTER JOIN USER_TBL C ON A.APPLYER_ID=C.ID" +
                                " LEFT OUTER JOIN COMPANY_TBL D ON A.NOW_REPAIR_COMPANY_ID=D.ID" +
                                " ORDER BY " +
                                " A.ID DESC" +
                            " ) F " +
                        " )" +
                        " WHERE " +
                        " TN > ? AND" +
                        " TN <= ? ";
            if (!user.filter("install_mng_all_data")) {
                sql = sql + "AND NOW_REPAIR_COMPANY_ID = ?";
            }

            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, begin);
            pstmt.setInt(++index, end);
            if (!user.filter("install_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            
            rs = pstmt.executeQuery();
            InstallationApply InstApplyOut = null;
            while (rs.next()) {
                InstApplyOut = new InstallationApply();
                index = 0;
                InstApplyOut.setId(rs.getInt(++index));
                InstApplyOut.setType(rs.getInt(++index));
                InstApplyOut.setTypeName(rs.getString(++index));
                InstApplyOut.setApplyerId(rs.getInt(++index));
                InstApplyOut.setApplyerName(rs.getString(++index));
                InstApplyOut.setApplyDate(rs.getString(++index));
                InstApplyOut.setManufactureNo(rs.getString(++index));
                InstApplyOut.setInstallId(rs.getInt(++index));
                InstApplyOut.setNowRepairCompanyId(rs.getInt(++index));
                InstApplyOut.setNowRepairCompanyName(rs.getString(++index));
                InstApplyOut.setExclusiveKey(rs.getInt(++index));
                
                list.add(InstApplyOut);
            }
            
            return list;
            // End UOC
        } catch(SQLException e){
            logSQLException(e, "serchApplyList");
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
                logSQLException(e, "serchApplyList");
                throw e;
            }
        }
    }   
    
    /**
     * @author xiangzq
     * @param  user
     * @return List&ltInstallationApply&gt
     * @throws SQLException
     */
    public List<InstallationApply> serchApplyList(String subSql) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<InstallationApply> list = new ArrayList<InstallationApply>();
            
            String sql = 
                        " SELECT K.* FROM " +
                        " ( "+
                        "   SELECT " + 
                        "   A.ID," +
                        "   A.EXCLUSIVE_KEY," +
                        "   A.TYPE," +
                        "   B.NAME AS APPLY_TYPE_NAME," +
                        "   A.APPLYER_ID," +
                        "   C.NAME AS APPLYER_NAME," +
                        "   A.APPLY_DATE," +
                        "   A.MANUFACTURE_NO," +
                        "   A.INSTALL_ID," +
                        "   A.NOW_REPAIR_COMPANY_ID," +
                        "   D.SHORT_NAME NOW_REPAIR_COMPANY_NAME" +
                        "   FROM INST_APPLY_TBL A" +
                        "   LEFT OUTER JOIN INST_APPLY_TYPE_TBL B ON A.TYPE=B.ID" +
                        "   LEFT OUTER JOIN USER_TBL C ON A.APPLYER_ID=C.ID" +
                        "   LEFT OUTER JOIN COMPANY_TBL D ON A.NOW_REPAIR_COMPANY_ID=D.ID " +
                        "   WHERE 1 = 1 " ;
                            sql = sql + subSql;
                            sql = sql+ " AND A.AFFIRM_FLAG = 0  ORDER BY A.ID DESC " +
                        " ) K " +
                        " WHERE ROWNUM < = 20 " ;
      
            pstmt = conn.prepareStatement(sql);  
            int index = 0;
            rs = pstmt.executeQuery();
            InstallationApply InstApplyOut = null;
            while (rs.next()) {
                InstApplyOut = new InstallationApply();
                index = 0;
                InstApplyOut.setId(rs.getInt(++index));
                InstApplyOut.setExclusiveKey(rs.getInt(++index));
                InstApplyOut.setType(rs.getInt(++index));
                InstApplyOut.setTypeName(rs.getString(++index));
                InstApplyOut.setApplyerId(rs.getInt(++index));
                InstApplyOut.setApplyerName(rs.getString(++index));
                InstApplyOut.setApplyDate(rs.getString(++index));
                InstApplyOut.setManufactureNo(rs.getString(++index));
                InstApplyOut.setInstallId(rs.getInt(++index));
                InstApplyOut.setNowRepairCompanyId(rs.getInt(++index));
                InstApplyOut.setNowRepairCompanyName(rs.getString(++index));
                list.add(InstApplyOut);
            }
            
            return list;
            // End UOC
        } catch(SQLException e){
            logSQLException(e, "serchApplyList");
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
                logSQLException(e, "serchApplyList");
                throw e;
            }
        }
    }   
    
    /**
     * @author sunyx
     * @param instApp
     * @return
     * @throws Exception
     */
    public InstallationApply serchApply(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            if(exclusiveCheck){
                String sql = "SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM " +
                            " INST_APPLY_TBL" +
                            " WHERE " +
                            " ID = ? ";
                pstmt = conn.prepareStatement(sql);
                int index = 0;
                pstmt.setInt(++index, instApp.getId());
                
                rs = pstmt.executeQuery();
                
                if (rs.next()){
                    index = 0;
                    if(rs.getInt("EXCLUSIVE_KEY") != instApp.getExclusiveKey()){
                        throw new ExclusiveException();
                    }
                } else {
                    throw new RecordNoFoundException();
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            
            String sql = "SELECT " +
                        " A.ID," +
                        " A.TYPE," +
                        " B.NAME," +
                        " A.APPLYER_ID," +
                        " C.NAME," +
                        " A.APPLY_DATE," +
                        " A.APPLYER_NOTE," +
                        " A.ADMIN_NOTE," +
                        " A.INSTALL_ID," +
                        " A.PRODUCT_ID," +
                        " H.MODEL," +
                        " I.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " E.SHORT_NAME," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " M.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " N.SHORT_NAME," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " G.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " J.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " O.NAME," +
                        " A.USE_STATUS_ID," +
                        " K.NAME," +
                        " A.PURPOSE," +
                        " L.NAME," +
                        " A.CONTACT," +
                        " A.OFFICE_PHONE," +
                        " A.MOBILE_PHONE," +
                        " A.FAX," +
                        " A.EMAIL," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.EXCLUSIVE_KEY" +
                        " FROM INST_APPLY_TBL A" +
                        " LEFT OUTER JOIN INST_APPLY_TYPE_TBL B ON A.TYPE=B.ID" +
                        " LEFT OUTER JOIN USER_TBL C ON A.APPLYER_ID=C.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL E ON A.CUSTOMER_ID=E.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL M ON A.SALES_CONTRACT_COMP_ID=M.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL N ON A.INSTALL_COMP_ID=N.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL G ON A.FIRST_REPAIR_COMPANY_ID=G.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL J ON A.NOW_REPAIR_COMPANY_ID=J.ID" +
                        " LEFT OUTER JOIN INST_PLACE_TYPE_TBL O ON A.INST_PLACE_TYPE_ID=O.ID" +
                        " LEFT OUTER JOIN USE_STATUS_TBL K ON A.USE_STATUS_ID=K.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL L ON A.PURPOSE=L.ID" +
                        " LEFT OUTER JOIN PRODUCT_TBL H ON A.PRODUCT_ID=H.ID," +
                        " PRODUCT_CATEGORY_TBL I" +
                        " WHERE H.PRODUCT_CATEGORY_ID=I.ID" +
                        " AND A.ID=?";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, instApp.getId());
            
            rs = pstmt.executeQuery();
            InstallationApply instApplyOut = new InstallationApply();
            
            if (rs.next()) {
                index = 0;
                instApplyOut.setId(rs.getInt(++index));
                instApplyOut.setType(rs.getInt(++index));
                instApplyOut.setTypeName(rs.getString(++index));
                instApplyOut.setApplyerId(rs.getInt(++index));
                instApplyOut.setApplyerName(rs.getString(++index));
                instApplyOut.setApplyDate(rs.getString(++index));
                instApplyOut.setApplyerNote(rs.getString(++index));
                instApplyOut.setAdminNote(rs.getString(++index));
                instApplyOut.setInstallId(rs.getInt(++index));
                instApplyOut.setProductId(rs.getInt(++index));
                instApplyOut.setModel(rs.getString(++index));
                instApplyOut.setProductCategoryName(rs.getString(++index));
                instApplyOut.setManufactureNo(rs.getString(++index));
                instApplyOut.setCustomerId(rs.getInt(++index));
                instApplyOut.setCustomerName(rs.getString(++index));
                instApplyOut.setSaleContractCompId(rs.getInt(++index));
                instApplyOut.setSaleContractCompName(rs.getString(++index));
                instApplyOut.setInstallCompId(rs.getInt(++index));
                instApplyOut.setInstallCompName(rs.getString(++index));
                instApplyOut.setFirstRepairCompanyId(rs.getInt(++index));
                instApplyOut.setFirstRepairCompanyName(rs.getString(++index));
                instApplyOut.setNowRepairCompanyId(rs.getInt(++index));
                instApplyOut.setNowRepairCompanyName(rs.getString(++index));
                instApplyOut.setIndentureNo(rs.getString(++index));
                instApplyOut.setFobDate(rs.getString(++index));
                instApplyOut.setInstallDate(rs.getString(++index));
                instApplyOut.setOpenDate(rs.getString(++index));
                instApplyOut.setAcceptDate(rs.getString(++index));
                instApplyOut.setInstaller(rs.getString(++index));
                instApplyOut.setInstallerId(rs.getString(++index));
                instApplyOut.setGuaranteeStartDate(rs.getString(++index));
                instApplyOut.setGuaranteeEndDate(rs.getString(++index));
                instApplyOut.setGuaranteePeriod(rs.getString(++index));
                instApplyOut.setSubCompany(rs.getString(++index));
                instApplyOut.setInstallPlace(rs.getString(++index));
                instApplyOut.setInstPlaceTypeId(rs.getInt(++index));
                instApplyOut.setInstPlaceTypeName(rs.getString(++index));
                instApplyOut.setUseStatusId(rs.getInt(++index));
                instApplyOut.setUseStatus(rs.getString(++index));
                instApplyOut.setPurpose(rs.getInt(++index));
                instApplyOut.setPurposeName(rs.getString(++index));
                instApplyOut.setContact(rs.getString(++index));
                instApplyOut.setOfficePhone(rs.getString(++index));
                instApplyOut.setMobilePhone(rs.getString(++index));
                instApplyOut.setFax(rs.getString(++index));
                instApplyOut.setEmail(rs.getString(++index));
                instApplyOut.setBrmEpVer(rs.getString(++index));
                instApplyOut.setBvEpVer(rs.getString(++index));
                instApplyOut.setKeyNo(rs.getString(++index));
                instApplyOut.setNote(rs.getString(++index));
                instApplyOut.setExclusiveKey(rs.getInt(++index));
            }
                
            return instApplyOut; 
            // End UOC       
        } catch(SQLException e){
            logSQLException(e, "serchApply");
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
                logSQLException(e, "serchApply");
                throw e;
            }
        }
    }

    public int getCountByApplyerId(Integer applyerId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            // Start UOC
          
            String sql = 
                     " SELECT " +
                     " COUNT(*) " +
                     " FROM " +
                     " INST_APPLY_TBL " +
                     " WHERE " +
                     " APPLYER_ID=?"; 
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, applyerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByApplyerId");
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
                logSQLException(e, "getCountByApplyerId");
                throw e;
            }
        }
        return count;
    }

    /**
     * search count by product ID.
     * @param productId product ID
     * @return count
     * @throws Exception
     */
    public int searchCountByProductId (Integer productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql = " SELECT "
                + "  COUNT(*)"
                + " FROM "
                + "  INST_APPLY_TBL "
                + " WHERE "
                + "  PRODUCT_ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, productId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCountByProductId");
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
                logSQLException(e, "searchCountByProductId");
                throw e;
            }
        }
        return count;
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
    public int getCountByInstallId(Integer InstallId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " COUNT(*) " +
                        " FROM " + 
                        " INST_APPLY_TBL " + 
                        " WHERE " + 
                        " INSTALL_ID = ?";
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
            logSQLException(e, "getCountByInstallId");
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
                logSQLException(e, "getCountByInstallId");
                throw e;
            }
        }

        return count;
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
                       " INST_APPLY_TBL " +
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
                       " INST_APPLY_TBL " +
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
                       " INST_APPLY_TBL " +
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
                       " INST_APPLY_TBL " +
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
                       " INST_APPLY_TBL " +
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
    
    /**
     * @author sunyx
     * @return
     * @throws Exception
     */
    public int getCountsByNowCompId(Integer nowCompId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            int count = 0;

            String sql = "SELECT " +
                        " COUNT(*)" +
                        " FROM " +
                        " INST_APPLY_TBL" +
                        " WHERE " +
                        " NOW_REPAIR_COMPANY_ID = ?";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, nowCompId);
            
            rs = pstmt.executeQuery();

            if(rs.next()){
                index = 0;
                count = rs.getInt(++index);
            }

            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountsByAgentId");
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
                logSQLException(e, "getCountsByAgentId");
                throw e;
            }
        }
    }
}
