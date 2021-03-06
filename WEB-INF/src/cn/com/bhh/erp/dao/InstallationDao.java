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

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.InstallationApply;
import cn.com.bhh.erp.entity.User;

/**
 * installation dao
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
public class InstallationDao extends BaseDao{
    
    public InstallationDao(Connection conn) {
        super(conn);
    }
    
    /**
     * get installation by id
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param Integer id
     * @return Installation
     * @throws SQLException
     */
    public Installation serchUseStatusDetail(Integer id,String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String sql = "SELECT" +
                        " A.ID," +
                        " A.PRODUCT_ID," +
                        " F.MODEL," +
                        " F.PRODUCT_CATEGORY_ID," +
                        " G.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " B.SHORT_NAME," +
                        " B.CITY_ID," +
                        " J.NAME," +
                        " K.NAME," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " L.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " M.SHORT_NAME," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " D.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " I.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " N.NAME," +
                        " A.USE_STATUS_ID," +
                        " H.NAME," +
                        " A.PURPOSE," +
                        " E.NAME," +
                        " A.CONTACT," +
                        " A.OFFICE_PHONE," +
                        " A.MOBILE_PHONE," +
                        " A.FAX," +
                        " A.EMAIL," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.BHCL_EP_VER," +
                        " A.TRCL_EP_VER," +   
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.OP_SYS," +
                        " O.NAME," +
                        " A.OS_PERMIT," +
                        " Q.NAME," +
                        " A.MCU," +
                        " A.HCM," +
                        " A.JPR," +
                        " A.SPR," +
                        " A.DESKEY," +
                        " A.BV," +
                        " A.PLATFORM," +
                        " P.NAME," +
                        " A.PLATFORM_REV," +
                        " A.AFFIRM_FLAG," +
                        " A.DELETED," +
                        " A.EXCLUSIVE_KEY" +
                        " FROM INSTALLATION_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.CUSTOMER_ID=B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL D ON A.FIRST_REPAIR_COMPANY_ID=D.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL I ON A.NOW_REPAIR_COMPANY_ID=I.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL E ON A.PURPOSE=E.ID" +
                        " LEFT OUTER JOIN PRODUCT_TBL F ON A.PRODUCT_ID=F.ID" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL G ON F.PRODUCT_CATEGORY_ID=G.ID" +
                        " LEFT OUTER JOIN USE_STATUS_TBL H ON A.USE_STATUS_ID=H.ID" +
                        " LEFT OUTER JOIN CITY_TBL J ON B.CITY_ID=J.ID" +
                        " LEFT OUTER JOIN PROVINCE_TBL K ON J.PROVINCE_ID=K.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL L ON A.SALES_CONTRACT_COMP_ID=L.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL M ON A.INSTALL_COMP_ID=M.ID" +
                        " LEFT OUTER JOIN INST_PLACE_TYPE_TBL N ON A.INST_PLACE_TYPE_ID=N.ID" +
                        " LEFT OUTER JOIN OS_TBL O ON A.OP_SYS=O.ID" +
                        " LEFT OUTER JOIN OS_PERMIT_TBL Q ON A.OS_PERMIT=Q.ID" +
                        " LEFT OUTER JOIN PLATFORM_TBL P ON A.PLATFORM=P.ID" +
                        " WHERE" +
                        "   A.ID=?";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = new Installation();
            int index = 0;
            pstmt.setInt(++index, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryId(rs.getInt(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerCityId(rs.getInt(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setSaleContractCompId(rs.getInt(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompId(rs.getInt(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setInstPlaceTypeId(rs.getInt(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));                
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setContact(rs.getString(++index));
                installationOut.setOfficePhone(rs.getString(++index));
                installationOut.setMobilePhone(rs.getString(++index));
                installationOut.setFax(rs.getString(++index));
                installationOut.setEmail(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setOs(rs.getBigDecimal(++index));
                installationOut.setOsName(rs.getString(++index));
                //2009/08/20 add   
                installationOut.setOsPermitId(rs.getBigDecimal(++index));
                installationOut.setOsPermitName(rs.getString(++index));
                
                installationOut.setMcu(rs.getString(++index));
                installationOut.setHcm(rs.getString(++index));
                installationOut.setJpr(rs.getString(++index));
                installationOut.setSpr(rs.getString(++index));
                installationOut.setDeskey(rs.getString(++index));
                installationOut.setBv(rs.getString(++index));
                installationOut.setPlatform(rs.getInt(++index));
                installationOut.setPlatformName(rs.getString(++index));
                installationOut.setPlatformRev(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setExclusiveKey(rs.getInt(++index));
            }
            return installationOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "serchUseStatusDetail");
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
                logSQLException(e, "serchUseStatusDetail");
                throw e;
            }
        }
    }
    
    
    /**
     * get Intallation by id
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param id
     * @return Installation
     * @throws Exception
     */
    public Installation getInstallationById(Integer id) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            int index = 0;
            String sql = "SELECT" +
                        " A.ID," +
                        " A.PRODUCT_ID," +
                        " F.MODEL," +
                        " F.PRODUCT_CATEGORY_ID," +
                        " G.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " B.SHORT_NAME," +
                        " B.CITY_ID," +
                        " J.NAME," +
                        " K.NAME," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " D.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " I.SHORT_NAME," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " G.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " L.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " N.NAME," +
                        " A.USE_STATUS_ID," +
                        " H.NAME," +
                        " A.PURPOSE," +
                        " E.NAME," +
                        " A.CONTACT," +
                        " A.OFFICE_PHONE," +
                        " A.MOBILE_PHONE," +
                        " A.FAX," +
                        " A.EMAIL," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.BHCL_EP_VER," +
                        " A.TRCL_EP_VER," +
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.OP_SYS," +
                        " O.NAME," +
                        " A.OS_PERMIT," +
                        " P.NAME," +
                        " A.MCU," +
                        " A.HCM," +
                        " A.JPR," +
                        " A.SPR," +
                        " A.DESKEY," +
                        " A.BV," +
                        " A.PLATFORM," +
                        " M.NAME," +
                        " A.PLATFORM_REV," +
                        " A.AFFIRM_FLAG," +
                        " A.AFFIRMANT_ID," +
                        " A.AFFIRM_TIME," +
                        " A.DELETED," +
                        " A.CREATE_TIME," +
                        " A.CREATOR_ID," +
                        " A.MODIFY_TIME," +
                        " A.MODIFIER_ID," +
                        " A.EXCLUSIVE_KEY," +
                        " A.START_DATE" +
                        " FROM INSTALLATION_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.CUSTOMER_ID=B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL D ON A.FIRST_REPAIR_COMPANY_ID=D.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL I ON A.NOW_REPAIR_COMPANY_ID=I.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL G ON A.SALES_CONTRACT_COMP_ID=G.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL E ON A.PURPOSE=E.ID" +
                        " LEFT OUTER JOIN INST_PLACE_TYPE_TBL N ON A.INST_PLACE_TYPE_ID=N.ID" +
                        " LEFT OUTER JOIN CITY_TBL J ON B.CITY_ID=J.ID" +
                        " LEFT OUTER JOIN PROVINCE_TBL K ON J.PROVINCE_ID=K.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL L ON A.INSTALL_COMP_ID=L.ID" +
                        " LEFT OUTER JOIN OS_TBL O ON A.OP_SYS=O.ID" +
                        " LEFT OUTER JOIN OS_PERMIT_TBL P ON A.OS_PERMIT=P.ID" +
                        " LEFT OUTER JOIN PLATFORM_TBL M ON A.PLATFORM=M.ID," +
                        " PRODUCT_TBL F" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL G ON F.PRODUCT_CATEGORY_ID=G.ID," +
                        " USE_STATUS_TBL H" +
                        " WHERE A.PRODUCT_ID=F.ID" +
                        " AND A.USE_STATUS_ID=H.ID" +
                        " AND A.ID=?";
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = new Installation();
            index = 0;
            pstmt.setInt(++index, id);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryId(rs.getInt(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerCityId(rs.getInt(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setSaleContractCompId(rs.getInt(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompId(rs.getInt(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setInstPlaceTypeId(rs.getInt(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setContact(rs.getString(++index));
                installationOut.setOfficePhone(rs.getString(++index));
                installationOut.setMobilePhone(rs.getString(++index));
                installationOut.setFax(rs.getString(++index));
                installationOut.setEmail(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setOs(rs.getBigDecimal(++index));
                installationOut.setOsName(rs.getString(++index));
                //2009/08/20 add
                installationOut.setOsPermitId(rs.getBigDecimal(++index));
                installationOut.setOsPermitName(rs.getString(++index));
                
                installationOut.setMcu(rs.getString(++index));
                installationOut.setHcm(rs.getString(++index));
                installationOut.setJpr(rs.getString(++index));
                installationOut.setSpr(rs.getString(++index));
                installationOut.setDeskey(rs.getString(++index));
                installationOut.setBv(rs.getString(++index));
                installationOut.setPlatform(rs.getInt(++index));
                installationOut.setPlatformName(rs.getString(++index));
                installationOut.setPlatformRev(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setAffirmantId(rs.getInt(++index));
                installationOut.setAffirmTime(rs.getString(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setCreateTime(rs.getString(++index));
                installationOut.setCreatorId(rs.getInt(++index));
                installationOut.setModifyTime(rs.getString(++index));
                installationOut.setModifierId(rs.getInt(++index));
                installationOut.setExclusiveKey(rs.getInt(++index));
                installationOut.setStartDate(rs.getString(++index));
            }

            return installationOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getInstallationById");
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
                logSQLException(e, "getInstallationById");
                throw e;
            }
        }
    }
    
    /**
     * get Intallation by id,exclusiveKey
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param id
     * @param exclusiveKey
     * @return Installation
     * @throws Exception
     */
    public Installation getInstallationByIdExclusive(Integer id, Integer exclusiveKey) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            int index = 0;
            if (exclusiveCheck) {
                String sql = "select exclusive_key from installation_tbl where id = ?";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != exclusiveKey) {
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
            String sql = "SELECT" +
                        " A.ID," +
                        " A.PRODUCT_ID," +
                        " F.MODEL," +
                        " F.PRODUCT_CATEGORY_ID, " +
                        " G.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " B.BANK_ID," +
                        " B.PROVINCE_ID," +
                        " B.SHORT_NAME," +
                        " B.CITY_ID," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " J.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " K.SHORT_NAME," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " D.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " I.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " A.USE_STATUS_ID," +
                        " H.NAME," +
                        " A.PURPOSE," +
                        " E.NAME," +
                        " A.CONTACT," +
                        " A.OFFICE_PHONE," +
                        " A.MOBILE_PHONE," +
                        " A.FAX," +
                        " A.EMAIL," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.BHCL_EP_VER," +
                        " A.TRCL_EP_VER," +
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.OP_SYS," +
                        " L.NAME," +
                        " A.OS_PERMIT," +
                        " P.NAME," +                        
                        " A.MCU," +
                        " A.HCM," +
                        " A.JPR," +
                        " A.SPR," +
                        " A.DESKEY," +
                        " A.BV," +
                        " A.PLATFORM," +
                        " M.NAME," +
                        " A.PLATFORM_REV," +
                        " A.AFFIRM_FLAG," +
                        " A.AFFIRMANT_ID," +
                        " A.AFFIRM_TIME," +
                        " A.DELETED," +
                        " A.CREATE_TIME," +
                        " A.CREATOR_ID," +
                        " A.MODIFY_TIME," +
                        " A.MODIFIER_ID," +
                        " A.EXCLUSIVE_KEY" +
                        " FROM INSTALLATION_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.CUSTOMER_ID=B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL D ON A.FIRST_REPAIR_COMPANY_ID=D.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL I ON A.NOW_REPAIR_COMPANY_ID=I.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL E ON A.PURPOSE=E.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL J ON A.SALES_CONTRACT_COMP_ID=J.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL K ON A.INSTALL_COMP_ID=K.ID" +
                        " LEFT OUTER JOIN OS_TBL L ON A.OP_SYS=L.ID" +
                        " LEFT OUTER JOIN OS_PERMIT_TBL P ON A.OS_PERMIT=P.ID" +
                        " LEFT OUTER JOIN PLATFORM_TBL M ON A.PLATFORM=M.ID," +
                        " PRODUCT_TBL F" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL G ON F.PRODUCT_CATEGORY_ID=G.ID," +
                        " USE_STATUS_TBL H" +
                        " WHERE A.PRODUCT_ID=F.ID" +
                        " AND A.USE_STATUS_ID=H.ID" +
                        " AND A.ID=?";
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = new Installation();
            index = 0;
            pstmt.setInt(++index, id);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryId(rs.getInt(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                
                installationOut.setUserCompanyBankId(rs.getInt(++index));
                installationOut.setUserCompanyProvinceId(rs.getInt(++index));
                
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerCityId(rs.getInt(++index));
                installationOut.setSaleContractCompId(rs.getInt(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompId(rs.getInt(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setInstPlaceTypeId(rs.getInt(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setContact(rs.getString(++index));
                installationOut.setOfficePhone(rs.getString(++index));
                installationOut.setMobilePhone(rs.getString(++index));
                installationOut.setFax(rs.getString(++index));
                installationOut.setEmail(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setOs(rs.getBigDecimal(++index));
                installationOut.setOsName(rs.getString(++index));
                //2009/08/20 add
                installationOut.setOsPermitId(rs.getBigDecimal(++index));
                installationOut.setOsPermitName(rs.getString(++index));
                
                installationOut.setMcu(rs.getString(++index));
                installationOut.setHcm(rs.getString(++index));
                installationOut.setJpr(rs.getString(++index));
                installationOut.setSpr(rs.getString(++index));
                installationOut.setDeskey(rs.getString(++index));
                installationOut.setBv(rs.getString(++index));
                installationOut.setPlatform(rs.getInt(++index));
                installationOut.setPlatformName(rs.getString(++index));
                installationOut.setPlatformRev(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setAffirmantId(rs.getInt(++index));
                installationOut.setAffirmTime(rs.getString(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setCreateTime(rs.getString(++index));
                installationOut.setCreatorId(rs.getInt(++index));
                installationOut.setModifyTime(rs.getString(++index));
                installationOut.setModifierId(rs.getInt(++index));
                installationOut.setExclusiveKey(rs.getInt(++index));
            }

            return installationOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getInstallationByIdExclusive");
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
                logSQLException(e, "getInstallationByIdExclusive");
                throw e;
            }
        }
    }
    
    /**
     * get installation info by manufactureNo
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param manufactureNo
     * @return Installation
     * @throws SQLException
     */
    public Installation getInstallByMamuNo(String manufactureNo,Integer productCategoryId, String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String sql = "SELECT"+
                        " A.ID," +
                        " A.PRODUCT_ID," +
                        " F.MODEL," +
                        " G.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " B.SHORT_NAME," +
                        " B.CITY_ID," +
                        " J.NAME," +
                        " K.NAME," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " D.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " I.SHORT_NAME," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " L.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " M.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " N.NAME," +
                        " A.USE_STATUS_ID," +
                        " H.NAME," +
                        " A.PURPOSE," +
                        " E.NAME," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.BHCL_EP_VER," +
                        " A.TRCL_EP_VER," +
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.AFFIRM_FLAG," +
                        " A.DELETED," +
                        " A.EXCLUSIVE_KEY" +
                        " FROM INSTALLATION_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.CUSTOMER_ID=B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL D ON A.FIRST_REPAIR_COMPANY_ID=D.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL I ON A.NOW_REPAIR_COMPANY_ID=I.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL L ON A.SALES_CONTRACT_COMP_ID=L.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL M ON A.INSTALL_COMP_ID=M.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL E ON A.PURPOSE=E.ID" +
                        " LEFT OUTER JOIN INST_PLACE_TYPE_TBL N ON A.INST_PLACE_TYPE_ID=N.ID" +
                        " LEFT OUTER JOIN PRODUCT_TBL F ON A.PRODUCT_ID=F.ID" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL G ON F.PRODUCT_CATEGORY_ID=G.ID" +
                        " LEFT OUTER JOIN USE_STATUS_TBL H ON A.USE_STATUS_ID=H.ID" +
                        " LEFT OUTER JOIN CITY_TBL J ON B.CITY_ID=J.ID" +
                        " LEFT OUTER JOIN PROVINCE_TBL K ON J.PROVINCE_ID=K.ID" +
                        " WHERE " +
                        " A.MANUFACTURE_NO = ? " +
                        " AND F.PRODUCT_CATEGORY_ID = ? " +
                        " AND G.CATE_LEVEL = 3 ";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = new Installation();
            int index = 0;
            pstmt.setString(++index, manufactureNo);
            pstmt.setInt(++index, productCategoryId);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerCityId(rs.getInt(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setSaleContractCompId(rs.getInt(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompId(rs.getInt(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setInstPlaceTypeId(rs.getInt(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setExclusiveKey(rs.getInt(++index));
            }

            return installationOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getInstallByMamuNo");
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
                logSQLException(e, "getInstallByMamuNo");
                throw e;
            }
        }
    }
    
    
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   manufactureNo
     * @param   productCategoryName
     * @return  Installation
     * @throws  Exception
     */
    public Installation getInstallByMamuNoAndProCateName(String manufactureNo,String productCategoryName,String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String sql = "SELECT"+
                        " A.ID," +
                        " A.PRODUCT_ID," +
                        " F.MODEL," +
                        " G.NAME," +
                        " A.MANUFACTURE_NO," +
                        " A.CUSTOMER_ID," +
                        " B.SHORT_NAME," +
                        " B.CITY_ID," +
                        " J.NAME," +
                        " K.NAME," +
                        " A.INSTALLER," +
                        " A.INSTALLER_ID," +
                        " A.FIRST_REPAIR_COMPANY_ID," +
                        " D.SHORT_NAME," +
                        " A.NOW_REPAIR_COMPANY_ID," +
                        " I.SHORT_NAME," +
                        " A.SALES_CONTRACT_COMP_ID," +
                        " L.SHORT_NAME," +
                        " A.INSTALL_COMP_ID," +
                        " M.SHORT_NAME," +
                        " A.INDENTURE_NO," +
                        " A.FOB_DATE," +
                        " A.INSTALL_DATE," +
                        " A.OPEN_DATE," +
                        " A.ACCEPT_DATE," +
                        " A.GUARANTEE_START_DATE," +
                        " A.GUARANTEE_END_DATE," +
                        " A.GUARANTEE_PERIOD," +
                        " A.BRANCH_COMPANY_NAME," +
                        " A.INSTALL_PLACE," +
                        " A.INST_PLACE_TYPE_ID," +
                        " N.NAME," +
                        " A.USE_STATUS_ID," +
                        " H.NAME," +
                        " A.PURPOSE," +
                        " E.NAME," +
                        " A.BRM_EP_VER," +
                        " A.BV_EP_VER," +
                        " A.BHCL_EP_VER," +
                        " A.TRCL_EP_VER," +
                        " A.KEY_NO," +
                        " A.NOTE," +
                        " A.AFFIRM_FLAG," +
                        " A.DELETED," +
                        " A.EXCLUSIVE_KEY" +
                        " FROM INSTALLATION_TBL A" +
                        " LEFT OUTER JOIN COMPANY_TBL B ON A.CUSTOMER_ID=B.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL D ON A.FIRST_REPAIR_COMPANY_ID=D.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL I ON A.NOW_REPAIR_COMPANY_ID=I.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL L ON A.SALES_CONTRACT_COMP_ID=L.ID" +
                        " LEFT OUTER JOIN COMPANY_TBL M ON A.INSTALL_COMP_ID=M.ID" +
                        " LEFT OUTER JOIN PURPOSE_TBL E ON A.PURPOSE=E.ID" +
                        " LEFT OUTER JOIN INST_PLACE_TYPE_TBL N ON A.INST_PLACE_TYPE_ID=N.ID" +
                        " LEFT OUTER JOIN PRODUCT_TBL F ON A.PRODUCT_ID=F.ID" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL G ON F.PRODUCT_CATEGORY_ID=G.ID" +
                        " LEFT OUTER JOIN USE_STATUS_TBL H ON A.USE_STATUS_ID=H.ID" +
                        " LEFT OUTER JOIN CITY_TBL J ON B.CITY_ID=J.ID" +
                        " LEFT OUTER JOIN PROVINCE_TBL K ON J.PROVINCE_ID=K.ID" +
                        " WHERE " +
                        " A.MANUFACTURE_NO = ? " +
                        " AND G.NAME = ? " +
                        " AND G.CATE_LEVEL = 3 ";
            
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = new Installation();
            int index = 0;
            pstmt.setString(++index, manufactureNo);
            pstmt.setString(++index, productCategoryName);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerCityId(rs.getInt(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setSaleContractCompId(rs.getInt(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompId(rs.getInt(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setInstPlaceTypeId(rs.getInt(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setExclusiveKey(rs.getInt(++index));
            }

            return installationOut;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getInstallByMamuNo");
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
                logSQLException(e, "getInstallByMamuNo");
                throw e;
            }
        }
    }
    
    /**
     * get all installation 
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws SQLException
     */
    public List<Installation> serchUseStatus(Installation install, User user, int intBegin, int intEnd) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Installation> list = new ArrayList<Installation>();
            String sql = "SELECT" +
                        "   ID," +
                        "   PRODUCT_ID," +
                        "   MANUFACTURE_NO," +
                        "   CUSTOMER_ID," +
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
                        "   MCU," +
                        "   HCM," +
                        "   JPR," +
                        "   SPR," +
                        "   DESKEY," +
                        "   BV," +
                        "   PLATFORM," +
                        "   PLATFORM_REV," +
                        "   AFFIRM_FLAG," +
                        "   DELETED," +
                        "   MODEL," +
                        "   PRODUCT_CATEGORY_ID," +
                        "   PRODUCT_CATEGORY_NAME," +
                        "   FIRST_REPAIR_COMPANY_NAME," +
                        "   NOW_REPAIR_COMPANY_NAME," +
                        "   FIRST_REPAIR_MAIN_COMPANY_NAME," +
                        "   NOW_REPAIR_MAIN_COMPANY_NAME," +
                        "   CUSTOMER_NAME," +
                        "   CUSTOMER_MAIN_NAME," +
                        "   CUSTOMER_SUB_NAME," +
                        "   CITY_NAME," +
                        "   PROVINCE_NAME," +
                        "   STATUS_NAME,"+
                        "   PURPOSE_NAME," +
                        "   SALE_COMP_NAME," +
                        "   INST_COMP_NAME," +
                        "   INST_PLACE_TYPE_NAME,"+
                        "   OS_NAME," +
                        "   OS_PERMIT_NAME," +
                        "   PLATFORM_NAME," +
                        "   SALE_COM_SHORT_NAME," +
                        "   INST_COM_SHORT_NAME " +
                        " FROM " +
                        "   (" +
                        "   SELECT" +
                        "   ROWNUM NO,K.*" +
                        "   FROM" +
                                " (" +
                                " SELECT" +
                                "   A.ID," +
                                "   A.PRODUCT_ID," +
                                "   A.MANUFACTURE_NO," +
                                "   A.CUSTOMER_ID," +
                                "   A.INSTALLER," +
                                "   A.INSTALLER_ID," +
                                "   A.FIRST_REPAIR_COMPANY_ID," +
                                "   A.NOW_REPAIR_COMPANY_ID," +
                                "   A.INDENTURE_NO," +
                                "   A.FOB_DATE," +
                                "   A.INSTALL_DATE," +
                                "   A.OPEN_DATE," +
                                "   A.ACCEPT_DATE," +
                                "   A.GUARANTEE_START_DATE," +
                                "   A.GUARANTEE_END_DATE," +
                                "   A.GUARANTEE_PERIOD," +
                                "   A.BRANCH_COMPANY_NAME," +
                                "   A.INSTALL_PLACE," +
                                "   A.USE_STATUS_ID," +
                                "   A.PURPOSE," +
                                "   A.CONTACT," +
                                "   A.OFFICE_PHONE," +
                                "   A.MOBILE_PHONE," +
                                "   A.FAX," +
                                "   A.EMAIL," +
                                "   A.BRM_EP_VER," +
                                "   A.BV_EP_VER," +
                                "   A.BHCL_EP_VER," +
                                "   A.TRCL_EP_VER," +
                                "   A.KEY_NO," +
                                "   A.NOTE," +
                                "   A.OP_SYS," +
                                "   A.OS_PERMIT," +
                                "   A.MCU," +
                                "   A.HCM," +
                                "   A.JPR," +
                                "   A.SPR," +
                                "   A.DESKEY," +
                                "   A.BV," +
                                "   A.PLATFORM," +
                                "   A.PLATFORM_REV," +
                                "   A.AFFIRM_FLAG," +
                                "   A.DELETED," +
                                "   B.MODEL," +
                                "   O.ID PRODUCT_CATEGORY_ID," +
                                "   O.NAME PRODUCT_CATEGORY_NAME," +
                                "   C.SHORT_NAME FIRST_REPAIR_COMPANY_NAME," +
                                "   D.SHORT_NAME NOW_REPAIR_COMPANY_NAME," +
                                "   C.MAIN_COMPANY_NAME FIRST_REPAIR_MAIN_COMPANY_NAME," +
                                "   D.MAIN_COMPANY_NAME NOW_REPAIR_MAIN_COMPANY_NAME," +
                                "   L.SHORT_NAME CUSTOMER_NAME," +
                                "   L.MAIN_COMPANY_NAME CUSTOMER_MAIN_NAME," +
                                "   L.SUB_COMPANY_NAME CUSTOMER_SUB_NAME," +
                                "   M.NAME CITY_NAME," +
                                "   N.NAME PROVINCE_NAME," +
                                "   G.NAME STATUS_NAME,"+
                                "   H.NAME PURPOSE_NAME,"+
                                "   SALECP.MAIN_COMPANY_NAME SALE_COMP_NAME,"+
                                "   INSTCP.MAIN_COMPANY_NAME INST_COMP_NAME," +
                                "   TYPETBL.NAME INST_PLACE_TYPE_NAME,"+
                                "   OSTBL.NAME OS_NAME," +
                                "   P.NAME OS_PERMIT_NAME," +
                                "   PLATFORMTBL.NAME PLATFORM_NAME," +
                                "   SALECP.SHORT_NAME SALE_COM_SHORT_NAME," +
                                "   INSTCP.SHORT_NAME INST_COM_SHORT_NAME " +
                                " FROM INSTALLATION_TBL A" +
                                "   LEFT OUTER JOIN PRODUCT_TBL B ON A.PRODUCT_ID=B.ID" +
                                "   LEFT OUTER JOIN COMPANY_TBL C ON A.FIRST_REPAIR_COMPANY_ID=C.ID" +
                                "   LEFT OUTER JOIN COMPANY_TBL D ON A.NOW_REPAIR_COMPANY_ID=D.ID" +
                                "   LEFT OUTER JOIN USE_STATUS_TBL G ON A.USE_STATUS_ID=G.ID" +
                                "   LEFT OUTER JOIN PURPOSE_TBL H ON A.PURPOSE=H.ID" +
                                "   LEFT OUTER JOIN COMPANY_TBL L ON A.CUSTOMER_ID=L.ID" +
                                "   LEFT OUTER JOIN CITY_TBL M ON L.CITY_ID=M.ID" +
                                "   LEFT OUTER JOIN PROVINCE_TBL N ON M.PROVINCE_ID=N.ID" +
                                "   LEFT OUTER JOIN PRODUCT_CATEGORY_TBL O ON B.PRODUCT_CATEGORY_ID=O.ID " +
                                "   LEFT OUTER JOIN COMPANY_TBL SALECP ON A.SALES_CONTRACT_COMP_ID=SALECP.ID" +
                                "   LEFT OUTER JOIN COMPANY_TBL INSTCP ON A.INSTALL_COMP_ID=INSTCP.ID" +
                                "   LEFT OUTER JOIN INST_PLACE_TYPE_TBL TYPETBL ON A.INST_PLACE_TYPE_ID=TYPETBL.ID" +
                                "   LEFT OUTER JOIN OS_TBL OSTBL ON A.OP_SYS=OSTBL.ID" +
                                "   LEFT OUTER JOIN OS_PERMIT_TBL P ON A.OS_PERMIT=P.ID" +
                                "   LEFT OUTER JOIN PLATFORM_TBL PLATFORMTBL ON A.PLATFORM=PLATFORMTBL.ID" +
                                " WHERE 0=0";

                                if (!user.filter("install_mng_all_data")) {
                                    sql = sql + " AND A.NOW_REPAIR_COMPANY_ID =?";
                                }
                                if (!user.hasPermission("PR005_34")) {
                                    sql = sql + " AND A.DELETED = '0'";
                                }
                                
                                if (install.getProductId() != null) {
                                    sql = sql + " AND A.PRODUCT_ID = ?";
                                }
                                
                                if (install.getProductCategoryId() != null) {
                                    sql = sql + " AND B.PRODUCT_CATEGORY_ID = ?";
                                }
                                
                                if (install.getManufactureNo() != null && !"".equals(install.getManufactureNo())) {
                                    sql = sql + " AND A.MANUFACTURE_NO = ?";
                                }
                                if (install.getCustomerId() != null) {
                                    sql = sql + " AND A.CUSTOMER_ID = ?";
                                }
                                if (install.getSaleContractCompId() != null) {
                                    sql = sql + " AND A.SALES_CONTRACT_COMP_ID = ?";
                                }
                                if (install.getInstallCompId() != null) {
                                    sql = sql + " AND A.INSTALL_COMP_ID = ?";
                                }
                                if (install.getInstaller() != null && !"".equals(install.getInstaller())) {
                                    sql = sql + " AND A.INSTALLER LIKE ?";
                                }
                                if (install.getInstallerId() != null && !"".equals(install.getInstallerId())) {
                                    sql = sql + " AND A.INSTALLER_ID LIKE ?";
                                }
                                if (install.getNowRepairCompanyId() != null) {
                                    sql = sql + " AND A.NOW_REPAIR_COMPANY_ID = ?";
                                }
                                if (install.getIndentureNo() != null && !"".equals(install.getIndentureNo())) {
                                    sql = sql + " AND A.INDENTURE_NO LIKE ?";
                                }
                                if (install.getFobDate() != null && !"".equals(install.getFobDate())) {
                                    sql = sql + " AND A.FOB_DATE = ?";
                                }
                                if (install.getInstallDate() != null && !"".equals(install.getInstallDate())) {
                                    sql = sql + " AND A.INSTALL_DATE = ?";
                                }
                                if (install.getOpenDate() != null && !"".equals(install.getOpenDate())) {
                                    sql = sql + " AND A.OPEN_DATE = ?";
                                }
                                if (install.getAcceptDate() != null && !"".equals(install.getAcceptDate())) {
                                    sql = sql + " AND A.ACCEPT_DATE = ?";
                                }
                                if (install.getGuaranteeStartDate() != null && !"".equals(install.getGuaranteeStartDate())) {
                                    sql = sql + " AND A.GUARANTEE_START_DATE = ?";
                                }
                                if (install.getGuaranteeEndDate() != null && !"".equals(install.getGuaranteeEndDate())) {
                                    sql = sql + " AND A.GUARANTEE_END_DATE = ?";
                                }
                                if (install.getGuaranteePeriod() != null && !"".equals(install.getGuaranteePeriod())) {
                                    sql = sql + " AND A.GUARANTEE_PERIOD = ?";
                                }
                                if (install.getBranchCompanyName() != null && !"".equals(install.getBranchCompanyName())) {
                                    sql = sql + " AND A.BRANCH_COMPANY_NAME LIKE ?";
                                }
                                if(install.getUserCompanyBankId() != null ){
                                    sql = sql + " AND L.BANK_ID = ?";
                                }
                                
                                if (install.getCustomerProvinceId() != null) {
                                    sql = sql + " AND M.PROVINCE_ID = ?";
                                }
                                if (install.getCustomerCityId() != null) {
                                    sql = sql + " AND L.CITY_ID = ?";
                                }
                                if (install.getInstallPlace() != null && !"".equals(install.getInstallPlace())) {
                                    sql = sql + " AND A.INSTALL_PLACE LIKE ?";
                                }
                                if (install.getInstPlaceTypeId() != null) {
                                    sql = sql + " AND A.INST_PLACE_TYPE_ID = ?";
                                }
                                if (install.getUseStatusId() != null) {
                                    sql = sql + " AND A.USE_STATUS_ID = ?";
                                }
                                if (install.getPurpose() != null) {
                                    sql = sql + " AND A.PURPOSE = ?";
                                }
                                if (install.getBrmEpVer() != null && !"".equals(install.getBrmEpVer())) {
                                    sql = sql + " AND A.BRM_EP_VER LIKE ?";
                                }
                                if (install.getBvEpVer() != null && !"".equals(install.getBvEpVer())) {
                                    sql = sql + " AND A.BV_EP_VER LIKE ?";
                                }
                                if (install.getKeyNo() != null && !"".equals(install.getKeyNo())) {
                                    sql = sql + " AND A.KEY_NO LIKE ?";
                                }
                                if (install.getNote() != null && !"".equals(install.getNote())) {
                                    sql = sql + " AND A.NOTE LIKE ?";
                                }
                                if (install.getAffirmFlag() != null) {
                                    sql = sql + " AND A.AFFIRM_FLAG = ?";
                                }
                                if (install.getManufactureNoStart() != null &&  !"".equals(install.getManufactureNoStart())) {
                                    sql = sql + " AND A.MANUFACTURE_NO >= ?";
                                }
                                if (install.getManufactureNoEnd() != null &&  !"".equals(install.getManufactureNoEnd())) {
                                    sql = sql + " AND A.MANUFACTURE_NO <= ?";
                                }
                                
                                if (install.getSort() == null || "".equals(install.getSort())) {
                                    sql = sql + " ORDER BY MODEL ASC, CUSTOMER_MAIN_NAME ASC, INSTALL_DATE DESC,A.MANUFACTURE_NO ASC";
                                } else {
                                    if ("productId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.PRODUCT_ID";
                                    } else if ("manufactureNo".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.MANUFACTURE_NO";
                                    } else if ("model".equals(install.getSort())) {
                                        sql = sql + " ORDER BY MODEL ";
                                    } else if ("customerId".equals(install.getSort())) {
                                          sql = sql + " ORDER BY CUSTOMER_MAIN_NAME";                                      
                                    } else if ("saleContractCompId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.SALES_CONTRACT_COMP_ID";
                                    } else if ("installCompId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INSTALL_COMP_ID";
                                    }else if ("nowRepairCompanyId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.NOW_REPAIR_COMPANY_ID";     
                                    } else if ("indentureNo".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INDENTURE_NO";
                                    } else if ("fobDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.FOB_DATE";
                                    } else if ("installDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INSTALL_DATE";
                                    } else if ("openDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.OPEN_DATE";
                                    } else if ("acceptDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.ACCEPT_DATE";
                                    } else if ("installer".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INSTALLER";
                                    } else if ("installerId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INSTALLER_ID";
                                    } else if ("guaranteeStartDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.GUARANTEE_START_DATE";
                                    } else if ("guaranteeEndDate".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.GUARANTEE_END_DATE";
                                    } else if ("guaranteePeriod".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.GUARANTEE_PERIOD";
                                    } else if ("branchCompanyName".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.BRANCH_COMPANY_NAME";
                                    } else if ("customerProvinceId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY PROVINCE_NAME";
                                    } else if ("customerCityId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY CITY_NAME";
                                    } else if ("installPlace".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.INSTALL_PLACE";
                                    } else if ("useStatusId".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.USE_STATUS_ID";
                                    } else if ("purpose".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.PURPOSE";
                                    } else if ("brmEpVer".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.BRM_EP_VER";
                                    } else if ("bvEpVer".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.BV_EP_VER";
                                    } else if ("keyNo".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.KEY_NO";
                                    } else if ("note".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.NOTE";
                                    } else if ("affirmFlag".equals(install.getSort())) {
                                        sql = sql + " ORDER BY A.AFFIRM_FLAG";
                                    } else {
                                        sql = sql + " ORDER BY A.PRODUCT_ID";
                                    }

                                    if("desc".equalsIgnoreCase(install.getSortType())){
                                        sql = sql + " DESC ";
                                    }else{
                                        sql = sql + " ASC ";
                                    }
                                    
                                    if ("manufactureNo".equals(install.getSort())) {
                                        sql = sql + " , MODEL ASC";
                                    } else if ("model".equals(install.getSort())) {
                                        sql = sql + " ,A.MANUFACTURE_NO ASC ";
                                    } else  {
                                        sql = sql + " ,A.MANUFACTURE_NO ASC , MODEL ASC";
                                    }
                                }
            
            sql = sql + " ) K " +
                        " WHERE" +
                        "   ROWNUM <= ?" +
            		    " ) " +
                        " WHERE" +
                        "   NO > ?";
            
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = null;
            int index = 0;
            if (!user.filter("install_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            
            if (install.getProductId() != null) {
                pstmt.setInt(++index, install.getProductId());
            }
            
            if (install.getProductCategoryId() != null) {
                pstmt.setInt(++index, install.getProductCategoryId());
            }
            
            if (install.getManufactureNo() != null && !"".equals(install.getManufactureNo())) {
                pstmt.setString(++index, install.getManufactureNo());
            }
            if (install.getCustomerId() != null) {
                pstmt.setInt(++index, install.getCustomerId());
            }
            if (install.getSaleContractCompId() != null) {
                pstmt.setInt(++index, install.getSaleContractCompId());
            }
            if (install.getInstallCompId() != null) {
                pstmt.setInt(++index, install.getInstallCompId());
            }
            if (install.getInstaller() != null && !"".equals(install.getInstaller())) {
                pstmt.setString(++index, install.getInstaller() + "%");
            }
            if (install.getInstallerId() != null && !"".equals(install.getInstallerId())) {
                pstmt.setString(++index, install.getInstallerId() + "%");
            }
            if (install.getNowRepairCompanyId() != null) {
                pstmt.setInt(++index, install.getNowRepairCompanyId());
            }
            if (install.getIndentureNo() != null && !"".equals(install.getIndentureNo())) {
                pstmt.setString(++index, install.getIndentureNo() + "%");
            }
            if (install.getFobDate() != null && !"".equals(install.getFobDate())) {
                pstmt.setString(++index, install.getFobDate());
            }
            if (install.getInstallDate() != null && !"".equals(install.getInstallDate())) {
                pstmt.setString(++index, install.getInstallDate());
            }
            if (install.getOpenDate() != null && !"".equals(install.getOpenDate())) {
                pstmt.setString(++index, install.getOpenDate());
            }
            if (install.getAcceptDate() != null && !"".equals(install.getAcceptDate())) {
                pstmt.setString(++index, install.getAcceptDate());
            }
            if (install.getGuaranteeStartDate() != null && !"".equals(install.getGuaranteeStartDate())) {
                pstmt.setString(++index, install.getGuaranteeStartDate());
            }
            if (install.getGuaranteeEndDate() != null && !"".equals(install.getGuaranteeEndDate())) {
                pstmt.setString(++index, install.getGuaranteeEndDate());
            }
            if (install.getGuaranteePeriod() != null && !"".equals(install.getGuaranteePeriod())) {
                pstmt.setString(++index, install.getGuaranteePeriod());
            }
            if (install.getBranchCompanyName() != null && !"".equals(install.getBranchCompanyName())) {
                pstmt.setString(++index, install.getBranchCompanyName() + "%");
            }
            
            if (install.getUserCompanyBankId() != null) {
                pstmt.setInt(++index, install.getUserCompanyBankId());
            }
            
            if (install.getCustomerProvinceId() != null) {
                pstmt.setInt(++index, install.getCustomerProvinceId());
            }
            if (install.getCustomerCityId() != null) {
                pstmt.setInt(++index, install.getCustomerCityId());
            }
            if (install.getInstallPlace() != null && !"".equals(install.getInstallPlace())) {
                pstmt.setString(++index, install.getInstallPlace() + "%");
            }
            if (install.getInstPlaceTypeId() != null) {
                pstmt.setInt(++index, install.getInstPlaceTypeId());
            }
            if (install.getUseStatusId() != null) {
                pstmt.setInt(++index, install.getUseStatusId());
            }
            if (install.getPurpose() != null) {
                pstmt.setInt(++index, install.getPurpose());
            }
            if (install.getBrmEpVer() != null && !"".equals(install.getBrmEpVer())) {
                pstmt.setString(++index, install.getBrmEpVer() + "%");
            }
            if (install.getBvEpVer() != null && !"".equals(install.getBvEpVer())) {
                pstmt.setString(++index, install.getBvEpVer() + "%");
            }
            if (install.getKeyNo() != null && !"".equals(install.getKeyNo())) {
                pstmt.setString(++index, install.getKeyNo() + "%");
            }
            if (install.getNote() != null && !"".equals(install.getNote())) {
                pstmt.setString(++index, install.getNote() + "%");
            }
            if (install.getAffirmFlag() != null) {
                pstmt.setInt(++index, install.getAffirmFlag());
            }
            
            if (install.getManufactureNoStart() != null &&  !"".equals(install.getManufactureNoStart())) {
                pstmt.setString(++index, install.getManufactureNoStart());
            }
            if (install.getManufactureNoEnd() != null &&  !"".equals(install.getManufactureNoEnd())) {
                pstmt.setString(++index, install.getManufactureNoEnd());
            }

            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                installationOut = new Installation();
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setContact(rs.getString(++index));
                installationOut.setOfficePhone(rs.getString(++index));
                installationOut.setMobilePhone(rs.getString(++index));
                installationOut.setFax(rs.getString(++index));
                installationOut.setEmail(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setOs(rs.getBigDecimal(++index));
                //2009/08/20 add
                installationOut.setOsPermitId(rs.getBigDecimal(++index));
                
                installationOut.setMcu(rs.getString(++index));
                installationOut.setHcm(rs.getString(++index));
                installationOut.setJpr(rs.getString(++index));
                installationOut.setSpr(rs.getString(++index));
                installationOut.setDeskey(rs.getString(++index));
                installationOut.setBv(rs.getString(++index));
                installationOut.setPlatform(rs.getInt(++index));
                installationOut.setPlatformRev(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryId(rs.getInt(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setFirstRepairMainCompanyName(rs.getString(++index));
                installationOut.setNowRepairMainCompanyName(rs.getString(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerMainName(rs.getString(++index));
                installationOut.setCustomerSubName(rs.getString(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));
                installationOut.setOsName(rs.getString(++index));
                //2009/08/20 add
                installationOut.setOsPermitName(rs.getString(++index));
                
                installationOut.setPlatformName(rs.getString(++index));
                installationOut.setSaleContractCompShortName(rs.getString(++index));
                installationOut.setInstallCompShortName(rs.getString(++index));
        
                list.add(installationOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "serchUseStatus");
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
                logSQLException(e, "serchUseStatus");
                throw e;
            }
        }
    }
    
    /**
     * get the checked installation 
     * @author xiangzq
     * @version 1.0
     * @since 1.0
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws SQLException
     */
    public List<Installation> serchUseStatusChecked(Installation install,String subSql, User user) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Installation> list = new ArrayList<Installation>();
            String sql =
                        " SELECT" +
                        "   A.ID," +
                        "   A.PRODUCT_ID," +
                        "   A.MANUFACTURE_NO," +
                        "   A.CUSTOMER_ID," +
                        "   A.INSTALLER," +
                        "   A.INSTALLER_ID," +
                        "   A.FIRST_REPAIR_COMPANY_ID," +
                        "   A.NOW_REPAIR_COMPANY_ID," +
                        "   A.INDENTURE_NO," +
                        "   A.FOB_DATE," +
                        "   A.INSTALL_DATE," +
                        "   A.OPEN_DATE," +
                        "   A.ACCEPT_DATE," +
                        "   A.GUARANTEE_START_DATE," +
                        "   A.GUARANTEE_END_DATE," +
                        "   A.GUARANTEE_PERIOD," +
                        "   A.BRANCH_COMPANY_NAME," +
                        "   A.INSTALL_PLACE," +
                        "   A.USE_STATUS_ID," +
                        "   A.PURPOSE," +
                        "   A.CONTACT," +
                        "   A.OFFICE_PHONE," +
                        "   A.MOBILE_PHONE," +
                        "   A.FAX," +
                        "   A.EMAIL," +
                        "   A.BRM_EP_VER," +
                        "   A.BV_EP_VER," +
                        "   A.BHCL_EP_VER," +
                        "   A.TRCL_EP_VER," +   
                        "   A.KEY_NO," +
                        "   A.NOTE," +
                        "   A.OP_SYS," +
                        "   A.OS_PERMIT," +
                        "   A.MCU," +
                        "   A.HCM," +
                        "   A.JPR," +
                        "   A.SPR," +
                        "   A.DESKEY," +
                        "   A.BV," +
                        "   A.PLATFORM," +
                        "   A.PLATFORM_REV," +
                        "   A.AFFIRM_FLAG," +
                        "   A.DELETED," +
                        "   B.MODEL," +
                        "   O.ID PRODUCT_CATEGORY_ID," +
                        "   O.NAME PRODUCT_CATEGORY_NAME," +
                        "   C.SHORT_NAME FIRST_REPAIR_COMPANY_NAME," +
                        "   D.SHORT_NAME NOW_REPAIR_COMPANY_NAME," +
                        "   C.MAIN_COMPANY_NAME FIRST_REPAIR_MAIN_COMPANY_NAME," +
                        "   D.MAIN_COMPANY_NAME NOW_REPAIR_MAIN_COMPANY_NAME," +
                        "   L.SHORT_NAME CUSTOMER_NAME," +
                        "   L.MAIN_COMPANY_NAME CUSTOMER_MAIN_NAME," +
                        "   L.SUB_COMPANY_NAME CUSTOMER_SUB_NAME," +
                        "   M.NAME CITY_NAME," +
                        "   N.NAME PROVINCE_NAME," +
                        "   G.NAME STATUS_NAME,"+
                        "   H.NAME PURPOSE_NAME,"+
                        "   SALECP.MAIN_COMPANY_NAME SALE_COMP_NAME,"+
                        "   INSTCP.MAIN_COMPANY_NAME INST_COMP_NAME," +
                        "   TYPETBL.NAME INST_PLACE_TYPE_NAME,"+
                        "   OSTBL.NAME OS_NAME," +
                        "   P.NAME OS_PERMIT_NAME," +
                        "   PLATFORMTBL.NAME PLATFORM_NAME," +
                        "   SALECP.SHORT_NAME SALE_COM_SHORT_NAME," +
                        "   INSTCP.SHORT_NAME INST_COM_SHORT_NAME " +
                        " FROM INSTALLATION_TBL A" +
                        "   LEFT OUTER JOIN PRODUCT_TBL B ON A.PRODUCT_ID=B.ID" +
                        "   LEFT OUTER JOIN COMPANY_TBL C ON A.FIRST_REPAIR_COMPANY_ID=C.ID" +
                        "   LEFT OUTER JOIN COMPANY_TBL D ON A.NOW_REPAIR_COMPANY_ID=D.ID" +
                        "   LEFT OUTER JOIN USE_STATUS_TBL G ON A.USE_STATUS_ID=G.ID" +
                        "   LEFT OUTER JOIN PURPOSE_TBL H ON A.PURPOSE=H.ID" +
                        "   LEFT OUTER JOIN COMPANY_TBL L ON A.CUSTOMER_ID=L.ID" +
                        "   LEFT OUTER JOIN CITY_TBL M ON L.CITY_ID=M.ID" +
                        "   LEFT OUTER JOIN PROVINCE_TBL N ON M.PROVINCE_ID=N.ID" +
                        "   LEFT OUTER JOIN PRODUCT_CATEGORY_TBL O ON B.PRODUCT_CATEGORY_ID=O.ID " +
                        "   LEFT OUTER JOIN COMPANY_TBL SALECP ON A.SALES_CONTRACT_COMP_ID=SALECP.ID" +
                        "   LEFT OUTER JOIN COMPANY_TBL INSTCP ON A.INSTALL_COMP_ID=INSTCP.ID" +
                        "   LEFT OUTER JOIN INST_PLACE_TYPE_TBL TYPETBL ON A.INST_PLACE_TYPE_ID=TYPETBL.ID" +
                        "   LEFT OUTER JOIN OS_TBL OSTBL ON A.OP_SYS=OSTBL.ID" +
                        "   LEFT OUTER JOIN OS_PERMIT_TBL P ON A.OS_PERMIT=P.ID" +
                        "   LEFT OUTER JOIN PLATFORM_TBL PLATFORMTBL ON A.PLATFORM=PLATFORMTBL.ID" +
                        " WHERE 0=0";

                        if (!user.filter("install_mng_all_data")) {
                            sql = sql + " AND A.NOW_REPAIR_COMPANY_ID =?";
                        }
                        if (!user.hasPermission("PR005_34")) {
                            sql = sql + " AND A.DELETED = '0'";
                        }

                        sql = sql + subSql;
                        
                        if (install.getSort() == null || "".equals(install.getSort())) {
                            sql = sql + " ORDER BY MODEL ASC, CUSTOMER_MAIN_NAME ASC, INSTALL_DATE DESC,A.MANUFACTURE_NO ASC";
                        } else {
                            if ("productId".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.PRODUCT_ID";
                            } else if ("manufactureNo".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.MANUFACTURE_NO";
                            } else if ("model".equals(install.getSort())) {
                                sql = sql + " ORDER BY MODEL ";
                            } else if ("customerId".equals(install.getSort())) {
                                  sql = sql + " ORDER BY CUSTOMER_MAIN_NAME";                                      
                            } else if ("saleContractCompId".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.SALES_CONTRACT_COMP_ID";
                            } else if ("installCompId".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.INSTALL_COMP_ID";
                            }else if ("nowRepairCompanyId".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.NOW_REPAIR_COMPANY_ID";     
                            }  else if ("installDate".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.INSTALL_DATE";
                            } else if ("useStatusId".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.USE_STATUS_ID";
                            }  else if ("affirmFlag".equals(install.getSort())) {
                                sql = sql + " ORDER BY A.AFFIRM_FLAG";
                            }   else {
                                sql = sql + " ORDER BY A.PRODUCT_ID";
                            }

                            if("desc".equalsIgnoreCase(install.getSortType())){
                                sql = sql + " DESC ";
                            }else{
                                sql = sql + " ASC ";
                            }
                            
                            if ("manufactureNo".equals(install.getSort())) {
                                sql = sql + " , MODEL ASC";
                            } else if ("model".equals(install.getSort())) {
                                sql = sql + " ,A.MANUFACTURE_NO ASC ";
                            } else  {
                                sql = sql + " ,A.MANUFACTURE_NO ASC , MODEL ASC";
                            }
                        }
 
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = null;
            int index = 0;
            if (!user.filter("install_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }

            rs = pstmt.executeQuery();
            while (rs.next()) {
                installationOut = new Installation();
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setProductId(rs.getInt(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setCustomerId(rs.getInt(++index));
                installationOut.setInstaller(rs.getString(++index));
                installationOut.setInstallerId(rs.getString(++index));
                installationOut.setFirstRepairCompanyId(rs.getInt(++index));
                installationOut.setNowRepairCompanyId(rs.getInt(++index));
                installationOut.setIndentureNo(rs.getString(++index));
                installationOut.setFobDate(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setOpenDate(rs.getString(++index));
                installationOut.setAcceptDate(rs.getString(++index));
                installationOut.setGuaranteeStartDate(rs.getString(++index));
                installationOut.setGuaranteeEndDate(rs.getString(++index));
                installationOut.setGuaranteePeriod(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setUseStatusId(rs.getInt(++index));
                installationOut.setPurpose(rs.getInt(++index));
                installationOut.setContact(rs.getString(++index));
                installationOut.setOfficePhone(rs.getString(++index));
                installationOut.setMobilePhone(rs.getString(++index));
                installationOut.setFax(rs.getString(++index));
                installationOut.setEmail(rs.getString(++index));
                installationOut.setBrmEpVer(rs.getString(++index));
                installationOut.setBvEpVer(rs.getString(++index));
                installationOut.setBhclEpVer(rs.getString(++index));
                installationOut.setTrclEpVer(rs.getString(++index));
                installationOut.setKeyNo(rs.getString(++index));
                installationOut.setNote(rs.getString(++index));
                installationOut.setOs(rs.getBigDecimal(++index));
                //2009/08/20 add
                installationOut.setOsPermitId(rs.getBigDecimal(++index));
                
                installationOut.setMcu(rs.getString(++index));
                installationOut.setHcm(rs.getString(++index));
                installationOut.setJpr(rs.getString(++index));
                installationOut.setSpr(rs.getString(++index));
                installationOut.setDeskey(rs.getString(++index));
                installationOut.setBv(rs.getString(++index));
                installationOut.setPlatform(rs.getInt(++index));
                installationOut.setPlatformRev(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryId(rs.getInt(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setFirstRepairCompanyName(rs.getString(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setFirstRepairMainCompanyName(rs.getString(++index));
                installationOut.setNowRepairMainCompanyName(rs.getString(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setCustomerMainName(rs.getString(++index));
                installationOut.setCustomerSubName(rs.getString(++index));
                installationOut.setCustomerCity(rs.getString(++index));
                installationOut.setCustomerProvince(rs.getString(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurposeName(rs.getString(++index));
                installationOut.setSaleContractCompName(rs.getString(++index));
                installationOut.setInstallCompName(rs.getString(++index));
                installationOut.setInstPlaceTypeName(rs.getString(++index));
                installationOut.setOsName(rs.getString(++index));
                //2009/08/20 add
                installationOut.setOsPermitName(rs.getString(++index));
                
                installationOut.setPlatformName(rs.getString(++index));
                installationOut.setSaleContractCompShortName(rs.getString(++index));
                installationOut.setInstallCompShortName(rs.getString(++index));
        
                list.add(installationOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "serchUseStatusChecked");
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
                logSQLException(e, "serchUseStatusChecked");
                throw e;
            }
        }
    }
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   subSql
     * @return  List<Installation>
     * @throws  Exception
     */
    public List<Installation> serchUseStatus(String subSql) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Installation> list = new ArrayList<Installation>();
            String sql = 
                                " SELECT K.* FROM " +
                                " ("+
                                "   SELECT" +
                                "       A.ID," +
                                "       A.MANUFACTURE_NO," +
                                "       A.INSTALL_DATE," +
                                "       A.INSTALL_PLACE," +
                                "       A.BRANCH_COMPANY_NAME," +
                                "       A.AFFIRM_FLAG," +
                                "       A.DELETED," +
                                "       B.MODEL," +
                                "       O.NAME PRODUCT_CATEGORY_NAME," +
                                "       L.SHORT_NAME CUSTOMER_NAME," +
                                "       M.SHORT_NAME NOW_REPAIR_COMPANY_NAME," +
                                "       G.NAME STATUS_NAME,"+
                                "       H.NAME PURPOSE_NAME"+
                                "   FROM INSTALLATION_TBL A" +
                                "       LEFT OUTER JOIN PRODUCT_TBL B ON A.PRODUCT_ID=B.ID" +
                                "       LEFT OUTER JOIN USE_STATUS_TBL G ON A.USE_STATUS_ID=G.ID" +
                                "       LEFT OUTER JOIN PURPOSE_TBL H ON A.PURPOSE=H.ID" +
                                "       LEFT OUTER JOIN COMPANY_TBL L ON A.CUSTOMER_ID=L.ID" +
                                "       LEFT OUTER JOIN COMPANY_TBL M ON A.NOW_REPAIR_COMPANY_ID=M.ID" +
                                "       LEFT OUTER JOIN PRODUCT_CATEGORY_TBL O ON B.PRODUCT_CATEGORY_ID=O.ID" +
                                "   WHERE 0=0 " +
                                "       AND A.DELETED = 0 " +
                                "       AND A.AFFIRM_FLAG =0 ";
                                        sql=sql+subSql;
                                        sql = sql + " ORDER BY B.MODEL ASC, CUSTOMER_NAME ASC, A.INSTALL_DATE DESC " +
                                " ) K" +
                                " WHERE ROWNUM <=20 ";
          
            pstmt = conn.prepareStatement(sql);
            Installation installationOut = null;
            int index = 0;
            rs = pstmt.executeQuery();
            while (rs.next()) {
                installationOut = new Installation();
                index = 0;
                installationOut.setId(rs.getInt(++index));
                installationOut.setManufactureNo(rs.getString(++index));
                installationOut.setInstallDate(rs.getString(++index));
                installationOut.setInstallPlace(rs.getString(++index));
                installationOut.setBranchCompanyName(rs.getString(++index));
                installationOut.setAffirmFlag(rs.getInt(++index));
                installationOut.setDeleted(rs.getInt(++index));
                installationOut.setModel(rs.getString(++index));
                installationOut.setProductCategoryName(rs.getString(++index));
                installationOut.setCustomerName(rs.getString(++index));
                installationOut.setNowRepairCompanyName(rs.getString(++index));
                installationOut.setUseStatus(rs.getString(++index));
                installationOut.setPurposeName(rs.getString(++index));
                list.add(installationOut);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "serchUseStatus");
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
                logSQLException(e, "serchUseStatus");
                throw e;
            }
        }
    }
    
    
    /**
     * get all installation count
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @return List&ltInstallation&gt
     * @throws SQLException
     */
     public int getInstallationCount(Installation install, User user) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try{
            // Start UOC
            String sql = "SELECT COUNT(*)" +
                        " FROM INSTALLATION_TBL A" +
                        "   LEFT OUTER JOIN COMPANY_TBL L ON A.CUSTOMER_ID=L.ID" +
                        "   LEFT OUTER JOIN CITY_TBL M ON L.CITY_ID=M.ID " +
                        "   LEFT OUTER JOIN PRODUCT_TBL N ON A.PRODUCT_ID=N.ID " +
                        " WHERE 0=0";
            
            if (!user.filter("install_mng_all_data")) {
                sql = sql + " AND A.NOW_REPAIR_COMPANY_ID = ?";
            }
            if (!user.hasPermission("PR005_34")) {
                sql = sql + " AND A.DELETED = '0'";
            }
            
            if (install.getProductId() != null) {
                sql = sql + " AND A.PRODUCT_ID = ?";
            }
            if (install.getProductCategoryId() != null) {
                sql = sql + " AND N.PRODUCT_CATEGORY_ID = ?";
            }
            if (install.getManufactureNo() != null && !"".equals(install.getManufactureNo())) {
                sql = sql + " AND A.MANUFACTURE_NO = ?";
            }
            if (install.getCustomerId() != null) {
                sql = sql + " AND A.CUSTOMER_ID = ?";
            }
            if (install.getSaleContractCompId() != null) {
                sql = sql + " AND A.SALES_CONTRACT_COMP_ID = ?";
            }
            if (install.getInstallCompId() != null) {
                sql = sql + " AND A.INSTALL_COMP_ID = ?";
            }
            if (install.getInstaller() != null && !"".equals(install.getInstaller())) {
                sql = sql + " AND A.INSTALLER LIKE ?";
            }
            if (install.getInstallerId() != null && !"".equals(install.getInstallerId())) {
                sql = sql + " AND A.INSTALLER_ID LIKE ?";
            }
            if (install.getNowRepairCompanyId() != null) {
                sql = sql + " AND A.NOW_REPAIR_COMPANY_ID = ?";
            }
            if (install.getIndentureNo() != null && !"".equals(install.getIndentureNo())) {
                sql = sql + " AND A.INDENTURE_NO LIKE ?";
            }
            if (install.getFobDate() != null && !"".equals(install.getFobDate())) {
                sql = sql + " AND A.FOB_DATE = ?";
            }
            if (install.getInstallDate() != null && !"".equals(install.getInstallDate())) {
                sql = sql + " AND A.INSTALL_DATE = ?";
            }
            if (install.getOpenDate() != null && !"".equals(install.getOpenDate())) {
                sql = sql + " AND A.OPEN_DATE = ?";
            }
            if (install.getAcceptDate() != null && !"".equals(install.getAcceptDate())) {
                sql = sql + " AND A.ACCEPT_DATE = ?";
            }
            if (install.getGuaranteeStartDate() != null && !"".equals(install.getGuaranteeStartDate())) {
                sql = sql + " AND A.GUARANTEE_START_DATE = ?";
            }
            if (install.getGuaranteeEndDate() != null && !"".equals(install.getGuaranteeEndDate())) {
                sql = sql + " AND A.GUARANTEE_END_DATE = ?";
            }
            if (install.getGuaranteePeriod() != null && !"".equals(install.getGuaranteePeriod())) {
                sql = sql + " AND A.GUARANTEE_PERIOD = ?";
            }
            if (install.getBranchCompanyName() != null && !"".equals(install.getBranchCompanyName())) {
                sql = sql + " AND A.BRANCH_COMPANY_NAME LIKE ?";
            }
            
            if (install.getUserCompanyBankId() != null) {
                sql = sql + " AND L.BANK_ID = ?";
            }
            
            if (install.getCustomerProvinceId() != null) {
                sql = sql + " AND M.PROVINCE_ID = ?";
            }
            
            if (install.getCustomerCityId() != null) {
                sql = sql + " AND L.CITY_ID = ?";
            }
            if (install.getInstallPlace() != null && !"".equals(install.getInstallPlace())) {
                sql = sql + " AND A.INSTALL_PLACE LIKE ?";
            }
            if (install.getInstPlaceTypeId() != null) {
                sql = sql + " AND A.INST_PLACE_TYPE_ID = ?";
            }

            if (install.getUseStatusId() != null) {
                sql = sql + " AND A.USE_STATUS_ID = ?";
            }
            if (install.getPurpose() != null) {
                sql = sql + " AND A.PURPOSE = ?";
            }
            if (install.getBrmEpVer() != null && !"".equals(install.getBrmEpVer())) {
                sql = sql + " AND A.BRM_EP_VER LIKE ?";
            }
            if (install.getBvEpVer() != null && !"".equals(install.getBvEpVer())) {
                sql = sql + " AND A.BV_EP_VER LIKE ?";
            }
            if (install.getKeyNo() != null && !"".equals(install.getKeyNo())) {
                sql = sql + " AND A.KEY_NO LIKE ?";
            }
            if (install.getNote() != null && !"".equals(install.getNote())) {
                sql = sql + " AND A.NOTE LIKE ?";
            }
            if (install.getAffirmFlag() != null) {
                sql = sql + " AND A.AFFIRM_FLAG = ?";
            }
            
            if (install.getManufactureNoStart() != null &&  !"".equals(install.getManufactureNoStart())) {
                sql = sql + " AND A.MANUFACTURE_NO >= ?";
            }
            if (install.getManufactureNoEnd() != null &&  !"".equals(install.getManufactureNoEnd())) {
                sql = sql + " AND A.MANUFACTURE_NO <= ?";
            }
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            if (!user.filter("install_mng_all_data")) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            
            if (install.getProductId() != null) {
                pstmt.setInt(++index, install.getProductId());
            }
            
            if (install.getProductCategoryId() != null) {
                pstmt.setInt(++index, install.getProductCategoryId());
            }
            
            if (install.getManufactureNo() != null && !"".equals(install.getManufactureNo())) {
                pstmt.setString(++index, install.getManufactureNo());
            }
            if (install.getCustomerId() != null) {
                pstmt.setInt(++index, install.getCustomerId());
            }
            if (install.getSaleContractCompId() != null) {
                pstmt.setInt(++index, install.getSaleContractCompId());
            }
            if (install.getInstallCompId() != null) {
                pstmt.setInt(++index, install.getInstallCompId());
            }
            if (install.getInstaller() != null && !"".equals(install.getInstaller())) {
                pstmt.setString(++index, install.getInstaller() + "%");
            }
            if (install.getInstallerId() != null && !"".equals(install.getInstallerId())) {
                pstmt.setString(++index, install.getInstallerId() + "%");
            }
            if (install.getNowRepairCompanyId() != null) {
                pstmt.setInt(++index, install.getNowRepairCompanyId());
            }
            if (install.getIndentureNo() != null && !"".equals(install.getIndentureNo())) {
                pstmt.setString(++index, install.getIndentureNo() + "%");
            }
            if (install.getFobDate() != null && !"".equals(install.getFobDate())) {
                pstmt.setString(++index, install.getFobDate());
            }
            if (install.getInstallDate() != null && !"".equals(install.getInstallDate())) {
                pstmt.setString(++index, install.getInstallDate());
            }
            if (install.getOpenDate() != null && !"".equals(install.getOpenDate())) {
                pstmt.setString(++index, install.getOpenDate());
            }

            if (install.getAcceptDate() != null && !"".equals(install.getAcceptDate())) {
                pstmt.setString(++index, install.getAcceptDate());
            }
            if (install.getGuaranteeStartDate() != null && !"".equals(install.getGuaranteeStartDate())) {
                pstmt.setString(++index, install.getGuaranteeStartDate());
            }
            if (install.getGuaranteeEndDate() != null && !"".equals(install.getGuaranteeEndDate())) {
                pstmt.setString(++index, install.getGuaranteeEndDate());
            }
            if (install.getGuaranteePeriod() != null && !"".equals(install.getGuaranteePeriod())) {
                pstmt.setString(++index, install.getGuaranteePeriod());
            }
            if (install.getBranchCompanyName() != null && !"".equals(install.getBranchCompanyName())) {
                pstmt.setString(++index, install.getBranchCompanyName() + "%");
            }
            
            if (install.getUserCompanyBankId() != null) {
                pstmt.setInt(++index, install.getUserCompanyBankId());
            }
            
            if (install.getCustomerProvinceId() != null) {
                pstmt.setInt(++index, install.getCustomerProvinceId());
            }
            
            if (install.getCustomerCityId() != null) {
                pstmt.setInt(++index, install.getCustomerCityId());
            }

            if (install.getInstallPlace() != null && !"".equals(install.getInstallPlace())) {
                pstmt.setString(++index, install.getInstallPlace() + "%");
            }
            if (install.getInstPlaceTypeId() != null) {
                pstmt.setInt(++index, install.getInstPlaceTypeId());
            }

            if (install.getUseStatusId() != null) {
                pstmt.setInt(++index, install.getUseStatusId());
            }
            if (install.getPurpose() != null) {
                pstmt.setInt(++index, install.getPurpose());
            }
            if (install.getBrmEpVer() != null && !"".equals(install.getBrmEpVer())) {
                pstmt.setString(++index, install.getBrmEpVer() + "%");
            }
            if (install.getBvEpVer() != null && !"".equals(install.getBvEpVer())) {
                pstmt.setString(++index, install.getBvEpVer() + "%");
            }
            if (install.getKeyNo() != null && !"".equals(install.getKeyNo())) {
                pstmt.setString(++index, install.getKeyNo() + "%");
            }
            if (install.getNote() != null && !"".equals(install.getNote())) {
                pstmt.setString(++index, install.getNote() + "%");
            }
            if (install.getAffirmFlag() != null) {
                pstmt.setInt(++index, install.getAffirmFlag());
            }
            
            if (install.getManufactureNoStart() != null &&  !"".equals(install.getManufactureNoStart())) {
                pstmt.setString(++index, install.getManufactureNoStart());
            }
            if (install.getManufactureNoEnd() != null &&  !"".equals(install.getManufactureNoEnd())) {
                pstmt.setString(++index, install.getManufactureNoEnd());
            }

            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getInstallationCount");
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
                logSQLException(e, "getInstallationCount");
                throw e;
            }
        }
    }
    
    /**
     * insert
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void create(Installation installation ) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = "INSERT INTO INSTALLATION_TBL(" +
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
                            "   CREATE_TIME," +
                            "   CREATOR_ID," +
                            "   MODIFY_TIME," +
                            "   MODIFIER_ID" +
                            " )VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?," +
                            " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, installation.getProductId());
            pstmt.setString(++index, installation.getManufactureNo());
            pstmt.setInt(++index, installation.getCustomerId());
            pstmt.setInt(++index, installation.getSaleContractCompId());
            pstmt.setInt(++index, installation.getInstallCompId());
            pstmt.setString(++index, installation.getInstaller());
            pstmt.setString(++index, installation.getInstallerId());
            pstmt.setInt(++index, installation.getInstallCompId());
            pstmt.setInt(++index, installation.getInstallCompId());
            pstmt.setString(++index, installation.getIndentureNo());
            pstmt.setString(++index, installation.getFobDate());
            pstmt.setString(++index, installation.getInstallDate());
            pstmt.setString(++index, installation.getOpenDate());
            pstmt.setString(++index, installation.getAcceptDate());
            pstmt.setString(++index, installation.getGuaranteeStartDate());
            pstmt.setString(++index, installation.getGuaranteeEndDate());
            pstmt.setString(++index, installation.getGuaranteePeriod());
            pstmt.setString(++index, installation.getBranchCompanyName());
            pstmt.setString(++index, installation.getInstallPlace());
            pstmt.setInt(++index, installation.getInstPlaceTypeId());
            pstmt.setInt(++index, installation.getUseStatusId());
            pstmt.setInt(++index, installation.getPurpose());
            pstmt.setString(++index, installation.getContact());
            pstmt.setString(++index, installation.getOfficePhone());
            pstmt.setString(++index, installation.getMobilePhone());
            pstmt.setString(++index, installation.getFax());
            pstmt.setString(++index, installation.getEmail());
            pstmt.setString(++index, installation.getBrmEpVer());
            pstmt.setString(++index, installation.getBvEpVer());
            pstmt.setString(++index, installation.getBhclEpVer());
            pstmt.setString(++index, installation.getTrclEpVer());
            
            pstmt.setString(++index, installation.getKeyNo());
            pstmt.setString(++index, installation.getNote());
            pstmt.setBigDecimal(++index, installation.getOs());
            pstmt.setBigDecimal(++index, installation.getOsPermitId());
            pstmt.setInt(++index, installation.getPlatform());
            pstmt.setString(++index, installation.getPlatformRev());
            pstmt.setString(++index, installation.getMcu());
            pstmt.setString(++index, installation.getBv());
            pstmt.setString(++index, installation.getHcm());
            pstmt.setString(++index, installation.getJpr());
            pstmt.setString(++index, installation.getSpr());
            pstmt.setString(++index, installation.getDeskey());
            pstmt.setString(++index, installation.getCreateTime());
            pstmt.setInt(++index, installation.getCreatorId());
            pstmt.setString(++index, installation.getModifyTime());
            pstmt.setInt(++index, installation.getModifierId());
            
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert installation");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "insert installation");
                throw e;
            }
        }
    }

    
    /**
     * UPDATE INSTALLATION
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @param modifyFlg 0:UPDATE INSTALLATION;1:HISTORY CREATE,UPDATE INSTALLATION
     * @throws Exception
     */
    public void modify(Installation installation ,int modifyFlg) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, installation.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != installation.getExclusiveKey()) {
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
              
            Integer nowExclusiveKey=installation.getExclusiveKey();
            installation.setExclusiveKey(++nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = "UPDATE " +
            		        " INSTALLATION_TBL" +
                            " SET " +
                            "   NOW_REPAIR_COMPANY_ID = ?," +
                            "   BRANCH_COMPANY_NAME = ?," +
                            "   INSTALL_PLACE = ?," +
                            "   INST_PLACE_TYPE_ID = ?," +
                            "   USE_STATUS_ID = ?," +
                            "   PURPOSE = ?," +
                            "   CONTACT = ?," +
                            "   OFFICE_PHONE = ?," +
                            "   MOBILE_PHONE = ?," +
                            "   FAX = ?," +
                            "   EMAIL = ?," +
                            "   EXCLUSIVE_KEY = ?" ;
            
            if (modifyFlg == 1) {
                sql = sql + "   ," +
                            "   START_DATE = ?";
            }
            if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
                sql = sql + "   ," +
                            "   CUSTOMER_ID = ?," +
                            "   SALES_CONTRACT_COMP_ID = ?," +
                            "   INSTALL_COMP_ID = ?," +
                            "   INSTALLER = ?," +
                            "   INSTALLER_ID = ?," +
                            "   FIRST_REPAIR_COMPANY_ID = ?," +
                            "   INDENTURE_NO = ?," +
                            "   FOB_DATE = ?," +
                            "   INSTALL_DATE = ?," +
                            "   OPEN_DATE = ?," +
                            "   ACCEPT_DATE = ?," +
                            "   GUARANTEE_START_DATE = ?," +
                            "   GUARANTEE_END_DATE = ?," +
                            "   GUARANTEE_PERIOD = ?," +
                            "   BRM_EP_VER = ?," +
                            "   BV_EP_VER = ?," +
                            "   BHCL_EP_VER = ?," +
                            "   TRCL_EP_VER = ?," +
                            "   KEY_NO = ?," +
                            "   NOTE = ?," +
                            "   OP_SYS = ?," +
                            "   OS_PERMIT = ?," +
                            "   PLATFORM = ?," +
                            "   PLATFORM_REV = ?," +
                            "   MCU = ?," +
                            "   BV = ?," +
                            "   HCM = ?," +
                            "   JPR = ?," +
                            "   SPR = ?," +
                            "   DESKEY = ?," +
                            "   MODIFY_TIME = ?," +
                            "   MODIFIER_ID = ?";
            }
            
                sql = sql + " WHERE " +
                            "     INSTALLATION_TBL.ID = ?";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, installation.getNowRepairCompanyId());
            pstmt.setString(++index, installation.getBranchCompanyName());
            pstmt.setString(++index, installation.getInstallPlace());
            pstmt.setInt(++index, installation.getInstPlaceTypeId());
            pstmt.setInt(++index, installation.getUseStatusId());
            pstmt.setInt(++index, installation.getPurpose());
            pstmt.setString(++index, installation.getContact());
            pstmt.setString(++index, installation.getOfficePhone());
            pstmt.setString(++index, installation.getMobilePhone());
            pstmt.setString(++index, installation.getFax());
            pstmt.setString(++index, installation.getEmail());
            pstmt.setInt(++index, installation.getExclusiveKey());
            if (modifyFlg == 1) {
                pstmt.setString(++index, TimeUtil.getNowNextDay());
            }
            if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
                pstmt.setInt(++index, installation.getCustomerId());
                pstmt.setInt(++index, installation.getSaleContractCompId());
                pstmt.setInt(++index, installation.getInstallCompId());
                pstmt.setString(++index, installation.getInstaller());
                pstmt.setString(++index, installation.getInstallerId());
                pstmt.setInt(++index, installation.getFirstRepairCompanyId());
                pstmt.setString(++index, installation.getIndentureNo());
                pstmt.setString(++index, installation.getFobDate());
                pstmt.setString(++index, installation.getInstallDate());
                pstmt.setString(++index, installation.getOpenDate());
                pstmt.setString(++index, installation.getAcceptDate());
                pstmt.setString(++index, installation.getGuaranteeStartDate());
                pstmt.setString(++index, installation.getGuaranteeEndDate());
                pstmt.setString(++index, installation.getGuaranteePeriod());
                pstmt.setString(++index, installation.getBrmEpVer());
                pstmt.setString(++index, installation.getBvEpVer());
                pstmt.setString(++index, installation.getBhclEpVer());
                pstmt.setString(++index, installation.getTrclEpVer());
                pstmt.setString(++index, installation.getKeyNo());
                pstmt.setString(++index, installation.getNote());
                pstmt.setBigDecimal(++index, installation.getOs());
                pstmt.setBigDecimal(++index, installation.getOsPermitId());
                pstmt.setInt(++index, installation.getPlatform());
                pstmt.setString(++index, installation.getPlatformRev());
                pstmt.setString(++index, installation.getMcu());
                pstmt.setString(++index, installation.getBv());
                pstmt.setString(++index, installation.getHcm());
                pstmt.setString(++index, installation.getJpr());
                pstmt.setString(++index, installation.getSpr());
                pstmt.setString(++index, installation.getDeskey());
                pstmt.setString(++index, installation.getModifyTime());
                pstmt.setInt(++index, installation.getModifierId());
            }
            pstmt.setInt(++index, installation.getId());
            
            pstmt.executeUpdate();

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
     * UPDATE INSTALLATION by upload
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @param modifyFlg 0:UPDATE INSTALLATION;1:HISTORY CREATE,UPDATE INSTALLATION
     * @throws Exception
     */
    public void modifyByUpload(Installation installation ,int modifyFlg) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, installation.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != installation.getExclusiveKey()) {
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
              
            Integer nowExclusiveKey=installation.getExclusiveKey();
            installation.setExclusiveKey(++nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = "UPDATE " +
                            " INSTALLATION_TBL" +
                            " SET " +
                            "   NOW_REPAIR_COMPANY_ID = ?," +
                            "   BRANCH_COMPANY_NAME = ?," +
                            "   INSTALL_PLACE = ?," +
                            "   INST_PLACE_TYPE_ID = ?," +
                            "   USE_STATUS_ID = ?," +
                            "   PURPOSE = ?," +
                            "   CONTACT = ?," +
                            "   OFFICE_PHONE = ?," +
                            "   MOBILE_PHONE = ?," +
                            "   FAX = ?," +
                            "   EMAIL = ?," +
                            "   EXCLUSIVE_KEY = ?" ;
            
            if (modifyFlg == 1) {
                sql = sql + "   ," +
                            "   START_DATE = ?";
            }
            if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
                sql = sql + "   ," +
                            "   CUSTOMER_ID = ?," +
                            "   SALES_CONTRACT_COMP_ID = ?," +
                            "   INSTALL_COMP_ID = ?," +
                            "   INSTALLER = ?," +
                            "   INSTALLER_ID = ?," +
                            "   FIRST_REPAIR_COMPANY_ID = ?," +
                            "   INDENTURE_NO = ?," +
                            "   FOB_DATE = ?," +
                            "   INSTALL_DATE = ?," +
                            "   OPEN_DATE = ?," +
                            "   ACCEPT_DATE = ?," +
                            "   GUARANTEE_START_DATE = ?," +
                            "   GUARANTEE_END_DATE = ?," +
                            "   GUARANTEE_PERIOD = ?," +
                            "   BRM_EP_VER = ?," +
                            "   BV_EP_VER = ?," +
                            "   KEY_NO = ?," +
                            "   NOTE = ?," +
                            "   OP_SYS = ?," +
                            "   OS_PERMIT = ?," +
                            "   PLATFORM = ?," +
                            "   PLATFORM_REV = ?," +
                            "   MCU = ?," +
                            "   BV = ?," +
                            "   HCM = ?," +
                            "   JPR = ?," +
                            "   SPR = ?," +
                            "   DESKEY = ?," +
                            "   MODIFY_TIME = ?," +
                            "   MODIFIER_ID = ?";
            }
            
                sql = sql + " WHERE " +
                            "     INSTALLATION_TBL.ID = ?";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, installation.getNowRepairCompanyId());
            pstmt.setString(++index, installation.getBranchCompanyName());
            pstmt.setString(++index, installation.getInstallPlace());
            pstmt.setInt(++index, installation.getInstPlaceTypeId());
            pstmt.setInt(++index, installation.getUseStatusId());
            pstmt.setInt(++index, installation.getPurpose());
            pstmt.setString(++index, installation.getContact());
            pstmt.setString(++index, installation.getOfficePhone());
            pstmt.setString(++index, installation.getMobilePhone());
            pstmt.setString(++index, installation.getFax());
            pstmt.setString(++index, installation.getEmail());
            pstmt.setInt(++index, installation.getExclusiveKey());
            if (modifyFlg == 1) {
                pstmt.setString(++index, TimeUtil.getNowNextDay());
            }
            if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
                pstmt.setInt(++index, installation.getCustomerId());
                pstmt.setInt(++index, installation.getSaleContractCompId());
                pstmt.setInt(++index, installation.getInstallCompId());
                pstmt.setString(++index, installation.getInstaller());
                pstmt.setString(++index, installation.getInstallerId());
                pstmt.setInt(++index, installation.getFirstRepairCompanyId());
                pstmt.setString(++index, installation.getIndentureNo());
                pstmt.setString(++index, installation.getFobDate());
                pstmt.setString(++index, installation.getInstallDate());
                pstmt.setString(++index, installation.getOpenDate());
                pstmt.setString(++index, installation.getAcceptDate());
                pstmt.setString(++index, installation.getGuaranteeStartDate());
                pstmt.setString(++index, installation.getGuaranteeEndDate());
                pstmt.setString(++index, installation.getGuaranteePeriod());
                pstmt.setString(++index, installation.getBrmEpVer());
                pstmt.setString(++index, installation.getBvEpVer());
                pstmt.setString(++index, installation.getKeyNo());
                pstmt.setString(++index, installation.getNote());
                pstmt.setBigDecimal(++index, installation.getOs());
                pstmt.setBigDecimal(++index, installation.getOsPermitId());
                pstmt.setInt(++index, installation.getPlatform());
                pstmt.setString(++index, installation.getPlatformRev());
                pstmt.setString(++index, installation.getMcu());
                pstmt.setString(++index, installation.getBv());
                pstmt.setString(++index, installation.getHcm());
                pstmt.setString(++index, installation.getJpr());
                pstmt.setString(++index, installation.getSpr());
                pstmt.setString(++index, installation.getDeskey());
                pstmt.setString(++index, installation.getModifyTime());
                pstmt.setInt(++index, installation.getModifierId());
            }
            pstmt.setInt(++index, installation.getId());
            
            pstmt.executeUpdate();

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
     * UPDATE INSTALLATION
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void UploadModify(Installation installation) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String sql = "UPDATE " +
                        " INSTALLATION_TBL" +
                        " SET " +
                        "   CUSTOMER_ID = ?," +
                        "   INSTALLER = ?," +
                        "   INSTALLER_ID = ?," +
                        "   NOW_REPAIR_COMPANY_ID = ?," +
                        "   INDENTURE_NO = ?," +
                        "   FOB_DATE = ?," +
                        "   INSTALL_DATE = ?," +
                        "   ACCEPT_DATE = ?," +
                        "   GUARANTEE_START_DATE = ?," +
                        "   GUARANTEE_END_DATE = ?," +
                        "   GUARANTEE_PERIOD = ?," +
                        "   BRANCH_COMPANY_NAME = ?," +
                        "   INSTALL_PLACE = ?," +
                        "   USE_STATUS_ID = ?," +
                        "   PURPOSE = ?," +
                        "   BRM_EP_VER = ?," +
                        "   BV_EP_VER = ?," +
                        "   KEY_NO = ?," +
                        "   NOTE = ?," +
                        "   MODIFY_TIME = ?," +
                        "   MODIFIER_ID = ?" +
                        " WHERE " +
                        "   INSTALLATION_TBL.ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, installation.getCustomerId());
            pstmt.setString(++index, installation.getInstaller());
            pstmt.setString(++index, installation.getInstallerId());
            pstmt.setInt(++index, installation.getNowRepairCompanyId());
            pstmt.setString(++index, installation.getIndentureNo());
            pstmt.setString(++index, installation.getFobDate());
            pstmt.setString(++index, installation.getInstallDate());
            pstmt.setString(++index, installation.getAcceptDate());
            pstmt.setString(++index, installation.getGuaranteeStartDate());
            pstmt.setString(++index, installation.getGuaranteeEndDate());
            pstmt.setString(++index, installation.getGuaranteePeriod());
            pstmt.setString(++index, installation.getBranchCompanyName());
            pstmt.setString(++index, installation.getInstallPlace());
            pstmt.setInt(++index, installation.getUseStatusId());
            pstmt.setInt(++index, installation.getPurpose());
            pstmt.setString(++index, installation.getBrmEpVer());
            pstmt.setString(++index, installation.getBvEpVer());
            pstmt.setString(++index, installation.getKeyNo());
            pstmt.setString(++index, installation.getNote());
            pstmt.setString(++index, installation.getModifyTime());
            pstmt.setInt(++index, installation.getModifierId());
            pstmt.setInt(++index, installation.getId());
            
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "UploadModify");
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
                logSQLException(e, "UploadModify");
                throw e;
            }
        }
    }
    
    /**
     * modify installation affirmFlg,deleted
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyState(Integer id, Integer exclusiveKey, int modifyType, Integer modifyFlg, String timeNow, Integer userId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != exclusiveKey) {
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
            
            String sql = "UPDATE " +
                            " INSTALLATION_TBL" +
                            " SET " +
                            "   EXCLUSIVE_KEY = ?" ;
            
            if (modifyType == 1) {
                if (modifyFlg.compareTo(new Integer(1)) == 0) {
                    sql = sql + "   ," +
                                "   AFFIRM_FLAG = ?," +
                                "   AFFIRMANT_ID = ?," +
                                "   AFFIRM_TIME = ?";
                } else {
                    sql = sql + "   ," +
                                "   AFFIRM_FLAG = ?," +
                                "   AFFIRMANT_ID = null," +
                                "   AFFIRM_TIME = ''";
                }
            } else if (modifyType == 2) {
                sql = sql + "   ," +
                            "   DELETED = ?";
            }
            
                sql = sql + " WHERE " +
                            "   INSTALLATION_TBL.ID = ?";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, ++exclusiveKey);
            if (modifyType == 1) {
                if (modifyFlg.compareTo(new Integer(1)) == 0) {
                    pstmt.setInt(++index, modifyFlg);
                    pstmt.setInt(++index, userId);
                    pstmt.setString(++index, timeNow);
                } else {
                    pstmt.setInt(++index, modifyFlg);
                }
            } else if (modifyType == 2) {
                pstmt.setInt(++index, modifyFlg);
            }
            pstmt.setInt(++index, id);

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyState");
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
                logSQLException(e, "modifyState");
                throw e;
            }
        }
    }
    
    
    /**
     * modify installation affirmFlg,deleted
     * @author xinagzq
     * @version 1.0
     * @since 1.0
     * @param installation
     * @throws Exception
     */
    public void modifyState(Integer id, Integer modifyFlg,String timeNow, Integer userId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
           
            // Start UOC
           
            int index = 0;
            String sql = "UPDATE " +
                            " INSTALLATION_TBL" +
                            " SET " +
                            " AFFIRM_FLAG = ?," +
                            " AFFIRMANT_ID = ?," +
                            " AFFIRM_TIME = ? "+
                            " WHERE " +
                            " ID = ?";
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(++index, modifyFlg);
            pstmt.setInt(++index, userId);
            pstmt.setString(++index, timeNow);
            pstmt.setInt(++index, id);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyState");
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
                logSQLException(e, "modifyState");
                throw e;
            }
        }
    }
    
    
    /**
     * delete installation
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @param Integer id
     * @param Integer exclusiveKey
     * @throws Exception
     */
    public void deleteById(Integer id, Integer exclusiveKey) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, id);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != exclusiveKey) {
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
            
            String sql = "DELETE " +
                        " FROM " +
                        "   INSTALLATION_TBL" +
                        " WHERE " +
                        "   ID = ?" ;
            
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, id);
            
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
     * get installation count by manufactureNo
     * @author liugd
     * @version 1.0
     * @since  1.0
     * @param  manufactureNo
     * @param  productCategoryId
     * @return int
     * @throws SQLException
     */
    public int getCountByManufactureNo(String manufactureNo,Integer productCategoryId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            // Start UOC
//            String sql = "SELECT COUNT(*)" +
//                            " FROM INSTALLATION_TBL" +
//                            " WHERE MANUFACTURE_NO = ?";
//            sql = sql + subSql;
            
            String sql =
                        " SELECT " +
                        " COUNT(*) " + 
                        " FROM INSTALLATION_TBL A ,PRODUCT_TBL B " +
                        " WHERE " +
                        " A.PRODUCT_ID =B.ID  AND " +
                        " A.MANUFACTURE_NO = ? " +
                        " AND B.PRODUCT_CATEGORY_ID = ? " ;
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, manufactureNo);
            pstmt.setInt(++index, productCategoryId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "manufacture_no exists ");
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
                logSQLException(e, "get manufacture_no");
                throw e;
            }
        }
        return count;
    }

    /**
     * search count by product ID.
     * @author luyan
     * @version 1.0
     * @since 1.0
     * @param productId product ID
     * @return count
     * @throws Exception
     */
    public int searchCountByProductId(Integer productId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql = " SELECT "
                + "  COUNT(*)"
                + " FROM "
                + "  INSTALLATION_TBL "
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
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param instApp
     * @throws Exception
     */
    public void doModifyUseStatusForApply(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            String sql = " UPDATE" +
                        " INSTALLATION_TBL" +
                        " SET" +
                        " USE_STATUS_ID = ? " +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, instApp.getUseStatusId());
            pstmt.setInt(++index, instApp.getInstallId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModifyUseStatusForApply");
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
                logSQLException(e, "doModifyUseStatusForApply");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param instApp
     * @throws Exception
     */
    public void doModifyInstPlaceForApply(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            String sql = " UPDATE" +
                        " INSTALLATION_TBL" +
                        " SET" +
                        " INSTALL_PLACE = ? ," +
                        " INST_PLACE_TYPE_ID = ? " +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, instApp.getInstallPlace());
            pstmt.setInt(++index, instApp.getInstPlaceTypeId());
            pstmt.setInt(++index, instApp.getInstallId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModifyInstPlaceForApply");
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
                logSQLException(e, "doModifyInstPlaceForApply");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param instApp
     * @throws Exception
     */
    public void doModifyGuaranteeForApply(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            String sql = " UPDATE" +
                        " INSTALLATION_TBL" +
                        " SET" +
                        " GUARANTEE_START_DATE = ? ," +
                        " GUARANTEE_END_DATE = ? ," +
                        " GUARANTEE_PERIOD = ? " +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, instApp.getGuaranteeStartDate());
            pstmt.setString(++index, instApp.getGuaranteeEndDate());
            pstmt.setString(++index, instApp.getGuaranteePeriod());
            pstmt.setInt(++index, instApp.getInstallId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModifyGuaranteeForApply");
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
                logSQLException(e, "doModifyGuaranteeForApply");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param instApp
     * @throws Exception
     */
    public void doModifyContactForApply(InstallationApply instApp) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            String sql = " UPDATE" +
                        " INSTALLATION_TBL" +
                        " SET" +
                        " CONTACT = ? ," +
                        " OFFICE_PHONE = ? ," +
                        " MOBILE_PHONE = ? ," +
                        " FAX = ? ," +
                        " EMAIL = ? " +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, instApp.getContact());
            pstmt.setString(++index, instApp.getOfficePhone());
            pstmt.setString(++index, instApp.getMobilePhone());
            pstmt.setString(++index, instApp.getFax());
            pstmt.setString(++index, instApp.getEmail());
            pstmt.setInt(++index, instApp.getInstallId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModifyGuaranteeForApply");
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
                logSQLException(e, "doModifyGuaranteeForApply");
                throw e;
            }
        }
    }
    
    /**
     * @author sunyx
     * @version 1.0
     * @since 1.0
     * @param instApp
     * @throws Exception
     */
    public void doModifyWrongForApply(InstallationApply instApp, Integer deletedFlag) throws Exception{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try{
            // Start UOC
            String sql = " UPDATE" +
                        " INSTALLATION_TBL" +
                        " SET" +
                        " DELETED = ?" +
                        " WHERE" +
                        " ID = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, deletedFlag);
            pstmt.setInt(++index, instApp.getInstallId());

            pstmt.executeUpdate();
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "doModifyWrongForApply");
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
                logSQLException(e, "doModifyWrongForApply");
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
                       " INSTALLATION_TBL " +
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
                       " INSTALLATION_TBL " +
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
     * get count by first sale company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   salesContractCompId
     * @return  int
     * @throws  SQLException
     */
    public int getCountBySalesContractCompId(Integer salesContractCompId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_TBL " +
                       " WHERE  " +
                       " SALES_CONTRACT_COMP_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, salesContractCompId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountBySalesContractCompId");
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
     * get count by first sale company ID.
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
                       " INSTALLATION_TBL " +
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
                       " INSTALLATION_TBL " +
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
     * get count by installation ID.
     * @author  luyan
     * @since   1.0
     * @param   installationId installation ID
     * @return  count
     * @throws  SQLException
     */
    public int getCountById(Integer installationId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " INSTALLATION_TBL " +
                       " WHERE  " +
                       " ID= ? " +
                       " AND DELETED = 0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, installationId);
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
}
