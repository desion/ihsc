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

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.City;
import cn.com.bhh.erp.entity.Company;
import cn.com.bhh.erp.entity.CompanyType;


public class CompanyDao extends BaseDao {
    public CompanyDao(Connection conn) {
        super(conn);
    }

    /**
     * delete company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @throws  Exception
     */
    public void delete(Company com) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                       " SELECT " +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " COMPANY_TBL " +
                       " WHERE " +
                       " ID = ? " +
                       " AND ID>0 " +
                       " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, com.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != com.getExclusiveKey()) {
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
            
            String sql = 
                      " DELETE " +
                      " FROM " +
                      " COMPANY_TBL " +
                      " WHERE " +
                      " ID = ? " +
                      " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());

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
     * create company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @throws  Exception
     */
    public void create(Company com) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                     " INSERT INTO COMPANY_TBL (" +
                     " ID," +
                     " CODE, " +
                     " START_DATE, " +
                     " END_DATE, " +
                     " TYPE_ID," +
                     " MAIN_COMPANY_NAME, " +
                     " SUB_COMPANY_NAME, " +
                     " SHORT_NAME, " +
                     " ADDRESS1, " +
                     " ADDRESS2," ;
                 if(com.getBankId()!=null){
                     sql = sql + " BANK_ID,";
                 }
                                                
                 sql = sql + 
                     " PROVINCE_ID, " +
                     " CITY_ID, " +
                     " ZIP_CODE, " +
                     " TEL1, " +
                     " TEL2, " +
                     " FAX, "+
                     " HOME_PAGE," +
                     " EMAIL, " +
                     " CREATOR_ID, " +
                     " MODIFIER_ID" +
                     " ) VALUES(?,?,?,?,?,?,?,?,?,?," ;
                     if(com.getBankId()!=null){ 
                         sql = sql +"?," ;
                     }
                    sql = sql +  "?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());
            pstmt.setString(++index, com.getCode());
            pstmt.setString(++index, com.getAplyStartDate());
            pstmt.setString(++index, com.getAplyEndDate());
            pstmt.setString(++index, com.getTypeId());
            pstmt.setString(++index, com.getMainCompanyName());
            pstmt.setString(++index, com.getSubCompanyName());
            pstmt.setString(++index, com.getShortName());
            pstmt.setString(++index, com.getAddress1());
            pstmt.setString(++index, com.getAddress2());
            if(com.getBankId()!=null){
                pstmt.setInt(++index,com.getBankId());
            }
            pstmt.setInt(++index,com.getProvinceId());
            pstmt.setInt(++index, com.getCityId());
            pstmt.setString(++index, com.getZipCode());
            pstmt.setString(++index, com.getTel1());
            pstmt.setString(++index, com.getTel2());
            pstmt.setString(++index, com.getFax());
            pstmt.setString(++index, com.getHomePage());
            pstmt.setString(++index, com.getEmail());
            pstmt.setInt(++index, com.getCreatorId());
            pstmt.setInt(++index, com.getModifierId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert");
            throw e;
        } finally {
            try {
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
     * create company history record.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @throws  Exception
     */
    public void createHistory(Company com) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " INSERT INTO COMPANY_HISTORY_TBL (" +
                       " ID," +
                       " CODE," +
                       " START_DATE, " +
                       " END_DATE," +
                       " TYPE_ID," +
                       " MAIN_COMPANY_NAME, " +
                       " SUB_COMPANY_NAME, " +
                       " SHORT_NAME, " +
                       " ADDRESS1," +
                       " ADDRESS2, " ;
                       if(com.getBankId()!=null){
                           sql = sql + " BANK_ID,";
                       }
                               
                       sql = sql + 
                       " PROVINCE_ID, " +
                       " CITY_ID," +
                       " ZIP_CODE," +
                       " TEL1," +
                       " TEL2," +
                       " FAX, "+
                       " HOME_PAGE," +
                       " EMAIL," +
                       " CREATOR_ID," +
                       " CREATE_TIME," +
                       " MODIFIER_ID," +
                       " MODIFY_TIME," +
                       " EXCLUSIVE_KEY" +
                       " )  VALUES(?,?,?,?,?,?,?,?,?,?," ;
                       if(com.getBankId()!=null){ 
                           sql = sql +"?," ;
                       } 
                       sql = sql + "?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());
            pstmt.setString(++index, com.getCode());
            pstmt.setString(++index, com.getAplyStartDate());
            pstmt.setString(++index, com.getAplyEndDate());
            pstmt.setString(++index, com.getTypeId());
            pstmt.setString(++index, com.getMainCompanyName());
            pstmt.setString(++index, com.getSubCompanyName());
            pstmt.setString(++index, com.getShortName());
            pstmt.setString(++index, com.getAddress1());
            pstmt.setString(++index, com.getAddress2());
            if(com.getBankId()!=null){
                pstmt.setInt(++index,com.getBankId());
            }
            pstmt.setInt(++index,com.getProvinceId());            
            pstmt.setInt(++index, com.getCityId());
            pstmt.setString(++index, com.getZipCode());
            pstmt.setString(++index, com.getTel1());
            pstmt.setString(++index, com.getTel2());
            pstmt.setString(++index, com.getFax());
            pstmt.setString(++index, com.getHomePage());
            pstmt.setString(++index, com.getEmail());
            pstmt.setInt(++index, com.getCreatorId());
            pstmt.setString(++index, com.getCreateTime());
            pstmt.setInt(++index, com.getModifierId());
            pstmt.setString(++index, com.getModifyTime());
            pstmt.setInt(++index, com.getExclusiveKey());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createHistory");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createHistory");
                throw e;
            }
        }
    }

   

    /**
     * get all the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getUserCompany() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_TBL" +
                    " WHERE ID>0" +
                    " AND DELETED=0 " +
                    " AND TYPE_ID LIKE '%,3,%'" ;
 
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getUserCompany");
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
                logSQLException(e, "getUserCompany");
                throw e;
            }
        }
    }
    /**获取拥有代理商和最终用户的公司
     * get all the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getSaleCustomerCompany() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_TBL" +
                    " WHERE ID>0" +
                    " AND DELETED=0 " +
                    " AND TYPE_ID LIKE '%,3,%'" +
                    " AND TYPE_ID LIKE '%,4,%'" ;
 
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getUserCompany");
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
                logSQLException(e, "getUserCompany");
                throw e;
            }
        }
    }   
    
    /**
     * get all the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getCompanyForDelegate() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_TBL" +
                    " WHERE ID>0" +
                    " AND DELETED=0 " +
                    " AND TYPE_ID LIKE '%,2,%' " +
                    " ORDER BY SHORT_NAME ";
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyForDelegate");
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
                logSQLException(e, "getCompanyForDelegate");
                throw e;
            }
        }
    }

    /**
     * get all the bank company.
     * @auther  xiangzq
     * @version 2.0
     * @since   1.0
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getCompanyCustomer(
            Integer bankId,
            Integer provinceId,
            Integer cityId,
            Integer saleComId)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_AGENT_VIEW" +
                    " WHERE ID>0" +
                    " AND DELETED=0 " +
                    " AND TYPE_ID LIKE '%,4,%' " ;
            if(bankId!=null){
                sql = sql + " AND BANK_ID = ? ";
            }
            if(provinceId!=null){
                sql = sql + " AND PROVINCE_ID = ? ";        
            }
            if(cityId!=null){
                sql = sql + " AND CITY_ID = ? ";
            }
            if(saleComId!=null){
                sql = sql + " AND ID = ? ";
            }
            sql = sql + " ORDER BY COM_NAME_PINYIN ";
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            
            if(bankId!=null){
               pstmt.setInt(++index, bankId);
            }
            if(provinceId!=null){
                pstmt.setInt(++index, provinceId);
            }
            if(cityId!=null){
                pstmt.setInt(++index, cityId);
            }
            if(saleComId!=null){
                pstmt.setInt(++index, saleComId);
            }
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyCustomer");
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
                logSQLException(e, "getCompanyCustomer");
                throw e;
            }
        }
    }
    
    /**
     * select the agent company and direct company 
     * for the message.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Company>
     * @throws  SQLException
     */
    public List<Company> getCompanyForMessage() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_TBL" +
                    " WHERE ID>0" +
                    " AND DELETED=0 " +
                    " AND TYPE_ID LIKE '%,3,%'" +
                    " ORDER BY ID";
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyForMessage");
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
                logSQLException(e, "getCompanyForMessage");
                throw e;
            }
        }
    }
    
    
    /**
     * get list of the company that has the proxy
     * of the product.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  List<Company>
     * @throws  Exception
     */
    public List<Company> getDocumentAgentCompany(Integer productId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                        "  SELECT " +
                        "  A.AGENT_ID, " +
                        "  B.SHORT_NAME" +
                        "  FROM AGENT_PRODUCT_TBL A,COMPANY_TBL B " + 
                        "  WHERE  " +
                        "  PRODUCT_CATEGORY_ID=" +
                        "  (" +
                        "     SELECT  " +
                        "     PRODUCT_CATEGORY_ID " +
                        "     FROM " +
                        "     PRODUCT_TBL " +
                        "     WHERE " +
                        "     ID = ?" +
                        "  )" +
                        "  AND A.AGENT_ID=B.ID" +
                        "  AND B.DELETED = 0 ";
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index,productId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getDocumentAgentCompany");
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
                logSQLException(e, "getDocumentAgentCompany");
                throw e;
            }
        }
    }
    
    
    /**
     * customer company,agent company,manufactury company,repair company
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   subSql
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getCompanyByType(String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_AGENT_VIEW" +
                    " WHERE " +
                    "   DELETED = 0" +
                    "   AND ID>0 ";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            sql = sql + "   ORDER BY COM_NAME_PINYIN";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyByType");
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
                logSQLException(e, "getCompanyByType");
                throw e;
            }
        }
    }
    
    /**
     * get  the company by the company type.
     * @auther  xiangzq
     * @version 1.0
     * @param   comTypeId
     * @since   1.0
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getCompanyByTypeId(String comType) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    " ID," +
                    " SHORT_NAME" +
                    " FROM " +
                    " COMPANY_TBL" +
                    " WHERE " +
                    " TYPE_ID LIKE ? " +
                    " AND DELETED=0 " +
                    " AND ID>0 ";
                   
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, comType);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setShortName(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyByTypeId");
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
                logSQLException(e, "getCompanyByTypeId");
                throw e;
            }
        }
    }
    
    

  
    /**xxxxxxxx
     * get company by company type
     * @author liugd
     * @param subSql
     * @return
     * @throws SQLException
     */
    public List<Company> getCompanyByCategory(String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String sql = 
                    " SELECT" +
                    "   ID," +
                    "   MAIN_COMPANY_NAME," +
                    "   SHORT_NAME," +
                    "   TYPE_ID" +
                    " FROM " +
                    "   COMPANY_TBL " +
                    " WHERE 0=0" +
                    " AND ID>0 ";
            
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            sql = sql + " ORDER BY ID,TYPE_ID";
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setMainCompanyName(rs.getString(++index));
                com.setShortName(rs.getString(++index));
                com.setTypeId(rs.getString(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyByCategory");
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
                logSQLException(e, "getCompanyByCategory");
                throw e;
            }
        }
    }

    /**
     * get company by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  Company
     * @throws  Exception
     */
    public Company searchById(Company com) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Company comOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                       " SELECT " +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " COMPANY_TBL " +
                       " WHERE " +
                       " ID = ? " +
                       " AND ID>0 ";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, com.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != com.getExclusiveKey()) {
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
            
            String sql = 
                        " SELECT " +
                        " A.ID, " +
                        " A.CODE," +
                        " A.START_DATE," +
                        " A.END_DATE," +
                        " A.MAIN_COMPANY_NAME," +
                        " A.SUB_COMPANY_NAME," +
                        " A.SHORT_NAME,"+ 
                        " A.TYPE_ID," +
                        " A.ADDRESS1," +
                        " A.ADDRESS2," +
                        " A.BANK_ID," +
                        " A.PROVINCE_ID," +
                        " A.CITY_ID," +
                        " C.NAME CITY_NAME,"+
                        " A.ZIP_CODE," +
                        " A.TEL1," +
                        " A.TEL2," +
                        " A.FAX, "+ 
                        " A.HOME_PAGE," +
                        " A.EMAIL," +
                        " A.CREATE_TIME," +
                        " A.CREATOR_ID," +
                        " A.MODIFY_TIME," +
                        " A.MODIFIER_ID, "+ 
                        " A.DELETED ,"+
                        " A.EXCLUSIVE_KEY " +
                        " FROM " +
                        " COMPANY_TBL A,CITY_TBL C" +
                        " WHERE " +
                        " A.CITY_ID=C.ID " +
                        " AND A.ID=? " +
                        " AND A.ID>0" ;
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, com.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                comOut = new Company();
                index = 0;
                comOut.setId(rs.getInt(++index));
                comOut.setCode(rs.getString(++index));
                comOut.setAplyStartDate(rs.getString(++index));
                comOut.setAplyEndDate(rs.getString(++index));
                comOut.setMainCompanyName(rs.getString(++index));
                comOut.setSubCompanyName(rs.getString(++index));
                comOut.setShortName(rs.getString(++index));
                comOut.setTypeId(rs.getString(++index));
                comOut.setAddress1(rs.getString(++index));
                comOut.setAddress2(rs.getString(++index));
                comOut.setBankId(rs.getInt(++index));
                comOut.setProvinceId(rs.getInt(++index));
                comOut.setCityId(rs.getInt(++index));
                comOut.setCityName(rs.getString(++index));
                comOut.setZipCode(rs.getString(++index));
                comOut.setTel1(rs.getString(++index));
                comOut.setTel2(rs.getString(++index));
                comOut.setFax(rs.getString(++index));
                comOut.setHomePage(rs.getString(++index));
                comOut.setEmail(rs.getString(++index));
                comOut.setCreateTime(rs.getString(++index));
                comOut.setCreatorId(rs.getInt(++index));
                comOut.setModifyTime(rs.getString(++index));
                comOut.setModifierId(rs.getInt(++index));
                comOut.setDeleted(rs.getInt(++index));
                comOut.setExclusiveKey(rs.getInt(++index));
            }

            return comOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchById");
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
                logSQLException(e, "searchById");
                throw e;
            }
        }
    }

    /**
     * get company count by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   comId
     * @return  the company count
     * @throws  SQLException
     */
    public int getCountByCompanyId(Integer comId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;

        try {
            // Start UOC
            
            String sql = 
                        " SELECT " +
                        " COUNT(*) " +
                        " FROM " +
                        " COMPANY_TBL " +
                        " WHERE " +
                        " ID=? " +
                        " AND DELETED=0 " +
                        " AND ID>0 " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }
            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCompanyId");
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
                logSQLException(e, "getCountByCompanyId");
                throw e;
            }
        }
    }
    
    /**
     * delete or reovery the company,
     * just modify the deleted field.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   com
     * @throws  Exception
     */
    public void deleteOrRecoverCompany (Company company) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Start UOC
            int index = 0;
            if (exclusiveCheck) {
                String sql = 
                       " SELECT " +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " COMPANY_TBL " +
                       " WHERE " +
                       " ID = ? " +
                       " AND ID>0 " +
                       " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                
                pstmt.setInt(++index, company.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != company.getExclusiveKey()) {
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
            
            Integer nowExclusiveKey = company.getExclusiveKey();
            company.setExclusiveKey(++nowExclusiveKey); 
            
            String sql = 
                        " UPDATE COMPANY_TBL SET" +
                        " DELETED=?, " +
                        " EXCLUSIVE_KEY =? "+
                        " WHERE " +
                        " ID=? " +
                        " AND ID>0 " ;
            pstmt = conn.prepareStatement(sql);
            index=0;
            pstmt.setInt(++index, company.getDeleted());
            pstmt.setInt(++index, company.getExclusiveKey());
            pstmt.setInt(++index, company.getId());
            pstmt.executeUpdate();
          
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCompanyId");
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
                logSQLException(e, "getCountByCompanyId");
                throw e;
            }
        }
    }
    /**
     * get the company count
     * by company type
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @return  int
     * @throws  SQLException
     */
    public int getCompSizeByComType(CompanyType comType) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            
            String sql = 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    " TYPE_ID LIKE ?" +
                    " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, "%,"+comType.getId()+",%");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompSizeByComType");
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
                logSQLException(e, "getCompSizeByComType");
                throw e;
            }
        }
    }

    /**
     * get count by id.
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @param Integer
     * @return int
     * @throws SQLException
     */
    public int getCountByID(Integer id, String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    "   ID = ?  " +
                    "   AND DELETED = 0" +
                    "   AND ID>0 ";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
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
            logSQLException(e, "getCountByID");
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
                logSQLException(e, "getCountByID");
                throw e;
            }
        }
    }
    

    /**
     * get id by name.
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @param String:COMPANY_NAME
     * @param subSql
     * @return Integer:ID
     * @throws SQLException
     */
    public Integer getIdByName(String mainName,String subName, String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    " MAIN_COMPANY_NAME = ? " ;
                if (subName != null && !"".equals(subName.trim())) {
                    sql = sql +" AND SUB_COMPANY_NAME LIKE ? ";
                }else{
                    sql = sql +" AND SUB_COMPANY_NAME IS NULL ";
                }
                sql = sql +                    
                "   AND DELETED = 0" +
                "   AND ID>0 ";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setString(++index, mainName);
            if (subName != null && !"".equals(subName)) {
                pstmt.setString(++index, subName);
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                id = rs.getInt(++index);
            }

            return id;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getIdByName");
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
                logSQLException(e, "getIdByName");
                throw e;
            }
        }
    }
    

    /**
     * get id by short name.
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @param String:COMPANY_NAME
     * @param subSql
     * @return Integer:ID
     * @throws SQLException
     */
    public Integer getIdByShortName(String shortName, String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    "   SHORT_NAME = ?  " +
                    "   AND DELETED = 0" +
                    "   AND ID>0 ";
            if (!"".equals(subSql)) {
                sql = sql + subSql;
            }
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, shortName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                id = rs.getInt(++index);
            }

            return id;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getIdByShortName");
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
                logSQLException(e, "getIdByShortName");
                throw e;
            }
        }
    }
    
    /**
     * get the company count by name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  SQLException
     */
    public int getCountByMainAndSubName(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    " MAIN_COMPANY_NAME=? " +
                    " AND SUB_COMPANY_NAME=? " +
                    " AND ID>0  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, com.getMainCompanyName());
            pstmt.setString(++index, com.getSubCompanyName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByMainAndSubName");
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
                logSQLException(e, "getCountByMainAndSubName");
                throw e;
            }
        }
    }
    
    /**
     * get the count by company code.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  Exception
     */
    public int getCountByMainComName(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql= 
                     " SELECT " +
                     " COUNT(*) " +
                     " FROM " +
                     " COMPANY_TBL " +
                     " WHERE " +
                     " MAIN_COMPANY_NAME=? " +
                     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, com.getMainCompanyName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByMainComName");
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
                logSQLException(e, "get the same code count");
                throw e;
            }
        }
    }

    

    /**
     * get the count by company code.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  Exception
     */
    public int getCountByComCode(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql= 
                     " SELECT " +
                     " COUNT(*) " +
                     " FROM " +
                     " COMPANY_TBL " +
                     " WHERE " +
                     " CODE=? " +
                     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, com.getCode());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "get the same code count");
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
                logSQLException(e, "get the same code count");
                throw e;
            }
        }
    }

    /**
     * get the company count by short name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  SQLException
     */
    public int getCountByShortName(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql= 
                      " SELECT " +
                      " COUNT(*) " +
                      " FROM " +
                      " COMPANY_TBL " +
                      " WHERE " +
                      " SHORT_NAME=? " +
                      " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, com.getShortName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "get the same shortname count");
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
                logSQLException(e, "get the same shortname count");
                throw e;
            }
        }
    }

    /**
     * modify company information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @throws  SQLException
     */
    public void modifyCompanyInfo(Company com,boolean historyModifyFlag) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " COMPANY_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND ID>0 " +
                           " FOR UPDATE NOWAIT";
                   
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, com.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != com.getExclusiveKey()) {
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

            Integer nowExclusiveKey = com.getExclusiveKey();
            com.setExclusiveKey(++nowExclusiveKey); 
            // set the exclusive key = exclusiveKey+1

            String sql = 
                      " UPDATE COMPANY_TBL SET " +
                      " MAIN_COMPANY_NAME=?," +
                      " SUB_COMPANY_NAME=?," +
                      " SHORT_NAME=?," +
                      " TYPE_ID =?, ";
                      if(historyModifyFlag){
                        sql=sql+ " START_DATE=?, ";
                      }
                      sql=sql+
                      " ADDRESS1=?, " +
                      " ADDRESS2=?," ;
                      if(com.getBankId()!=null){
                        sql = sql + " BANK_ID = ?,";  
                      }else{
                        sql = sql + " BANK_ID = null,"; 
                      }
           sql = sql + " PROVINCE_ID=?," +
                      " CITY_ID=?, " +
                      " ZIP_CODE=?," +
                      " TEL1=?," +
                      " TEL2=?," +
                      " FAX=?, "+ 
                      " HOME_PAGE=?," +
                      " EMAIL=?," +
                      " MODIFY_TIME=?," +
                      " MODIFIER_ID=?," +
                      " EXCLUSIVE_KEY=? " +
                      " WHERE " +
                      " ID=? " +
                      " AND ID>0 ";

            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, com.getMainCompanyName());
            pstmt.setString(++index, com.getSubCompanyName());
            pstmt.setString(++index, com.getShortName());
            pstmt.setString(++index, com.getTypeId());
            if(historyModifyFlag){
               pstmt.setString(++index, com.getAplyStartDate());
            }
            pstmt.setString(++index, com.getAddress1());
            pstmt.setString(++index, com.getAddress2());
            if(com.getBankId()!=null){
                pstmt.setInt(++index, com.getBankId());
            }
            pstmt.setInt(++index, com.getProvinceId());
            pstmt.setInt(++index, com.getCityId());
            pstmt.setString(++index, com.getZipCode());
            pstmt.setString(++index, com.getTel1());
            pstmt.setString(++index, com.getTel2());
            pstmt.setString(++index, com.getFax());
            pstmt.setString(++index, com.getHomePage());
            pstmt.setString(++index, com.getEmail());
            pstmt.setString(++index, com.getModifyTime());
            pstmt.setInt(++index, com.getModifierId());
            pstmt.setInt(++index, com.getExclusiveKey());
            pstmt.setInt(++index, com.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateCompanyInfo");
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
                logSQLException(e, "updateCompanyInfo");
                throw e;
            }
        }
    }

    /**
     * get the company count by
     * main and sub company name except self
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  SQLException
     */
    public int getCountByMainAndSubNameModify(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                     " SELECT" +
                     " COUNT(*) " +
                     " FROM " +
                     " COMPANY_TBL A " +
                     " WHERE " +
                     " EXISTS "+ 
                     " ( " +
                     "  SELECT " +
                     "  SHORT_NAME " +
                     "  FROM " +
                     "  COMPANY_TBL B " +
                     "  WHERE A.ID != B.ID  " +
                     "  AND B.ID =? " +
                     "   )  " +
                     " AND A.MAIN_COMPANY_NAME= ?" +
                     " AND A.SUB_COMPANY_NAME=? " +
                     " AND A.ID>0  ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());
            pstmt.setString(++index, com.getMainCompanyName());
            pstmt.setString(++index, com.getSubCompanyName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByMainAndSubNameModify");
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
                logSQLException(e, "getCountByMainAndSubNameModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get the company count
     * by short name except self
     *@return int
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  SQLException
     */
    public int getCountByShortNameModify(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                     " SELECT  " +
                     " COUNT(*) " + 
                     " FROM " + "" +
                     " COMPANY_TBL A " + 
                     " WHERE EXISTS " + 
                     " ( "+
                     "  SELECT " + 
                     "  SHORT_NAME " + 
                     "  FROM " + 
                     "  COMPANY_TBL B " +
                     "  WHERE"  +
                     "  A.ID != B.ID  "+
                     "  AND B.ID =? " +
                     "  )  " + 
                     " AND A.SHORT_NAME= ? " +
                     " AND A.ID>0  ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());
            pstmt.setString(++index, com.getShortName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByShortNameModify");
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
                logSQLException(e, "getCountByShortNameModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get the company count
     * by main company name except self
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Company
     * @return  int
     * @throws  SQLException
     */
    public int getCountByMainComNameModify(Company com) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                     " SELECT  " +
                     " COUNT(*) " + 
                     " FROM " + "" +
                     " COMPANY_TBL A " + 
                     " WHERE EXISTS " + 
                     " ( "+
                     "  SELECT " + 
                     "  SHORT_NAME " + 
                     "  FROM " + 
                     "  COMPANY_TBL B " +
                     "  WHERE"  +
                     "  A.ID != B.ID  "+
                     "  AND B.ID =? " +
                     "  )  " + 
                     " AND A.MAIN_COMPANY_NAME= ? " +
                     " AND A.SUB_COMPANY_NAME IS NULL" +
                     " AND A.ID>0  ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, com.getId());
            pstmt.setString(++index, com.getMainCompanyName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByMainComNameModify");
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
                logSQLException(e, "getCountByMainComNameModify");
                throw e;
            }
        }

        return count;
    }
    
   

    /**
     * get the company count
     * by city.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @return  int
     * @throws  SQLException
     */
    public int getCompanyCountByCity(City city) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC

            String sql = 
                      " SELECT " +
                      " COUNT(*) " +
                      " FROM " +
                      " COMPANY_TBL " +
                      " WHERE " +
                      " CITY_ID=? " +
                      " AND ID>0 " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, city.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyCountByCity");
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
                logSQLException(e, "getCompanyCountByCity");
                throw e;
            }
        }

        return count;
    }


    /**
     * get the company by page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   intBegin
     * @param   intEnd
     * @param   company
     * @return  List&ltCompany&gt
     * @throws  SQLException
     */
    public List<Company> getAllCompanyByPage(int intBegin, int intEnd,Company company,Integer userCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String tempSql=
                        " SELECT " +
                        " ID, " +
                        " CODE," +
                        " START_DATE," +
                        " MAIN_COMPANY_NAME," +
                        " SUB_COMPANY_NAME," +
                        " SHORT_NAME, " +
                        " TYPE_ID," +
                        " CITY_ID," +
                        " CITY_NAME," +
                        " ZIP_CODE," +
                        " TEL1," +
                        " EMAIL, " +
                        " DELETED, " +
                        " EXCLUSIVE_KEY  "+
                        " FROM "+
                        " ( " +
                        "   SELECT ROWNUM NO," +
                        "   T.* " +
                        "   FROM " +
                        "    (SELECT " +
                        "     A.* " +
                        "     FROM " +
                        "     COMPANY_AGENT_VIEW A " +
                        "     WHERE " +
                        "     1 = 1 " ;

            
            StringBuffer sb = new StringBuffer(tempSql);
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                sb.append(" AND A.ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE AGENT_ID=? )");
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                sb.append(" AND A.MAIN_COMPANY_NAME LIKE ?");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                sb.append(" AND A.SUB_COMPANY_NAME LIKE ?");
            }
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                sb.append(" AND A.SHORT_NAME LIKE ?");
            }
           
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                sb.append(" AND A.TYPE_ID LIKE ? ");
            }
            if(company.getCode()!=null && !"".equals(company.getCode().trim())){
                sb.append(" AND A.CODE LIKE ? ");
            }
            
            if(company.getProvinceId()!=null){
                sb.append(" AND A.PROVINCE_ID = ? ");
            }
            
            if(company.getCityId()!=null){
                sb.append(" AND A.CITY_ID = ? ");
            }
            
            if (company.getSort() == null || "".equals(company.getSort())) {
                sb.append(" ORDER BY A.COM_NAME_PINYIN  ");
            } else {
               
                if ("comName".equals(company.getSort())) {
                    sb.append(" ORDER BY A.COM_NAME_PINYIN ");
                } else if ("comShortName".equals(company.getSort())) {
                    sb.append(" ORDER BY A.SHORT_NAME ");
                }else if ("cityName".equals(company.getSort())) {
                    sb.append(" ORDER BY A.CITY_SHORT_NAME ");
                }else if ("comTypeName".equals(company.getSort())) {
                    sb.append(" ORDER BY A.TYPE_ID ");
                } else if ("comDeleted".equals(company.getSort())) {
                    sb.append(" ORDER BY A.DELETED ");
                }
               
                if("desc".equalsIgnoreCase(company.getSortType())){
                    sb.append(" DESC ");
                }else{
                    sb.append(" ASC ");
                }
            }
                
                sb.append(",A.ID ASC");
                
            sb.append("  )T ");
            sb.append(" WHERE ROWNUM<=? ");
            sb.append(" ) WHERE NO>? ");
            
            String sql=sb.toString();
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                pstmt.setInt(++index, userCompanyId);
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                pstmt.setString(++index, company.getMainCompanyName()+"%");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                pstmt.setString(++index, company.getSubCompanyName()+"%");
            }
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                pstmt.setString(++index, company.getShortName()+"%");
            }
            
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                pstmt.setString(++index, "%,"+company.getTypeId()+",%");
            }
            
            if(company.getCode()!=null && !"".equals(company.getCode().trim())){
                pstmt.setString(++index, company.getCode()+"%");
            }
            
            if(company.getProvinceId()!=null){
                pstmt.setInt(++index, company.getProvinceId());
            }
            
            if(company.getCityId()!=null){
                pstmt.setInt(++index, company.getCityId());
            }
            
            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
           

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setCode(rs.getString(++index));
                com.setAplyStartDate(rs.getString(++index));
                com.setMainCompanyName(rs.getString(++index));
                com.setSubCompanyName(rs.getString(++index));
                com.setShortName(rs.getString(++index));
                com.setTypeId(rs.getString(++index));
                com.setCityId(rs.getInt(++index));
                com.setCityName(rs.getString(++index));
                com.setZipCode(rs.getString(++index));
                com.setTel1(rs.getString(++index));
                com.setEmail(rs.getString(++index));
                com.setDeleted(rs.getInt(++index));
                com.setExclusiveKey(rs.getInt(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyByPage");
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
                logSQLException(e, "getCompanyByPage");
                throw e;
            }
        }
    }

    /**
     * if user has no permission of browsing all
     * the data,then execute this method,select
     * the user's company and its company's proxy
     * customer company and all company with type_id =1
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   intBegin
     * @param   intEnd
     * @param   company
     * @param   userCompanyId
     * @return  List<Company>
     * @throws  Exception
     */
    public List<Company> getCompanyByPage(int intBegin, int intEnd,Company company,Integer userCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Company> list = new ArrayList<Company>();
            String tempSql=
               " SELECT"+ 
               " ID, "+  
               " CODE,"+  
               " START_DATE,"+  
               " MAIN_COMPANY_NAME," +
               " SUB_COMPANY_NAME," +
               " SHORT_NAME,"+   
               " TYPE_ID,"+  
               " CITY_ID,"+  
               " CITY_NAME, "+ 
               " ZIP_CODE,"+  
               " TEL1, "+ 
               " EMAIL, "+  
               " DELETED, "+  
               " EXCLUSIVE_KEY"+     
               " FROM ("+ 
                          " SELECT "+ 
                          " ROWNUM NO,"+  
                          " T.* "+ 
                          " FROM "+ 
                          " (  "+ 
                               " SELECT *"+ 
                               " FROM"+ 
                                " ("+ 
                                   " SELECT"+ 
                                   " B.* "+                   
                                   " FROM  AGENT_CUSTOMER_TBL A,  " +
                                   " COMPANY_AGENT_VIEW B " +
                                   " WHERE"+ 
                                   " A.CUSTOMER_ID=B.ID "+ 
                                   " AND A.AGENT_ID=? "+   
                                   " UNION"+                             
                                   " SELECT  "+        
                                   " A.* "+ 
                                   " FROM"+ 
                                   " COMPANY_AGENT_VIEW A   " +
                                   " WHERE "+ 
                                   " A.ID=? "+ 
                                   " ) " +
                                   " WHERE 1=1 " ;
            
            StringBuffer sb = new StringBuffer(tempSql);
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                sb.append(" AND ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE AGENT_ID=? )");
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                sb.append(" AND MAIN_COMPANY_NAME LIKE ?");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                sb.append(" AND SUB_COMPANY_NAME LIKE ?");
            }
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                sb.append(" AND SHORT_NAME LIKE ?");
            }
           
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                sb.append(" AND TYPE_ID LIKE ? ");
            }
            
            if(company.getCode()!=null && !"".equals(company.getCode().trim())){
                sb.append(" AND CODE LIKE ?" );
            }
            
            if(company.getProvinceId()!=null){
                sb.append(" AND PROVINCE_ID = ? ");
            }
            
            
            if(company.getCityId()!=null){
                sb.append(" AND CITY_ID = ? ");
            }
            
     
           
           
            if (company.getSort() == null || "".equals(company.getSort())) {
                sb.append(" ORDER BY COM_NAME_PINYIN ");
            } else {
               
                if ("comName".equals(company.getSort())) {
                    sb.append(" ORDER BY COM_NAME_PINYIN ");
                } else if ("comShortName".equals(company.getSort())) {
                    sb.append(" ORDER BY SHORT_NAME ");
                }else if ("cityName".equals(company.getSort())) {
                    sb.append(" ORDER BY CITY_SHORT_NAME ");
                }else if ("comTypeName".equals(company.getSort())) {
                    sb.append(" ORDER BY TYPE_ID ");
                } else if ("comDeleted".equals(company.getSort())) {
                    sb.append(" ORDER BY DELETED ");
                }
               
                if("desc".equalsIgnoreCase(company.getSortType())){
                    sb.append(" DESC ");
                }else{
                    sb.append(" ASC ");
                }
            }
                
            sb.append(",ID ASC ");
            sb.append("  )T ");
            sb.append(" WHERE ROWNUM<=? ");
            sb.append(" ) WHERE NO>? ");
            
            String sql=sb.toString();
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, userCompanyId);
            pstmt.setInt(++index, userCompanyId);
            
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                pstmt.setInt(++index, userCompanyId);
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                pstmt.setString(++index, company.getMainCompanyName()+"%");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                pstmt.setString(++index, company.getSubCompanyName()+"%");
            }
            
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                pstmt.setString(++index, company.getShortName()+"%");
            }
            
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                pstmt.setString(++index, "%,"+company.getTypeId()+",%");
            }
            
            if(company.getCode()!=null && !"".equals(company.getCode().trim())){
                pstmt.setString(++index, company.getCode()+"%");
            }
            if(company.getProvinceId()!=null){
                pstmt.setInt(++index, company.getProvinceId());
            }
            
            
            if(company.getCityId()!=null){
                pstmt.setInt(++index, company.getCityId());
            }
            
            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
           

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Company com = new Company();
                index = 0;
                com.setId(rs.getInt(++index));
                com.setCode(rs.getString(++index));
                com.setAplyStartDate(rs.getString(++index));
                com.setMainCompanyName(rs.getString(++index));
                com.setSubCompanyName(rs.getString(++index));
                com.setShortName(rs.getString(++index));
                com.setTypeId(rs.getString(++index));
                com.setCityId(rs.getInt(++index));
                com.setCityName(rs.getString(++index));
                com.setZipCode(rs.getString(++index));
                com.setTel1(rs.getString(++index));
                com.setEmail(rs.getString(++index));
                com.setDeleted(rs.getInt(++index));
                com.setExclusiveKey(rs.getInt(++index));
                list.add(com);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyByPage");
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
                logSQLException(e, "getCompanyByPage");
                throw e;
            }
        }
    }
    
    
    /**
     * get all the company counts by the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   company
     * @return  int
     * @throws  SQLException
     */
    public int getAllCompanyCountByRules(Company company,Integer userCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
                    String tempSql=
                        " SELECT " +
                        " COUNT(*)  "+
                        " FROM "+
                        " ( " +
                        "   SELECT " +
                        "   ROWNUM NO," +
                        "   A.* " +
                        "   FROM " +
                        "   COMPANY_AGENT_VIEW A  " +
                        "   WHERE " +
                        "   1 = 1 " ;
            
            StringBuffer sb = new StringBuffer(tempSql);
            
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                sb.append(" AND A.ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE AGENT_ID=? )");
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                sb.append(" AND A.MAIN_COMPANY_NAME LIKE ?");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                sb.append(" AND A.SUB_COMPANY_NAME LIKE ?");
            }
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                sb.append(" AND A.SHORT_NAME LIKE ?");
            }
           
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                sb.append(" AND A.TYPE_ID LIKE ? ");
            }
            
            if (company.getCode() != null && !"".equals(company.getCode())) {
                sb.append(" AND A.CODE LIKE ?");
            }
            
            if(company.getProvinceId()!=null){
                sb.append(" AND A.PROVINCE_ID = ? ");
            }
            
            if(company.getCityId()!=null){
                sb.append(" AND A.CITY_ID = ? ");
            }
           
            sb.append("  ORDER BY A.COM_NAME_PINYIN ) WHERE ID>0 ");
            
            String sql=sb.toString();
            pstmt = conn.prepareStatement(sql);
        
            int index = 0;
            
            if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                pstmt.setInt(++index, userCompanyId);
            }
            
            if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                pstmt.setString(++index, company.getMainCompanyName()+"%");
            }
            
            if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                pstmt.setString(++index, company.getSubCompanyName()+"%");
            }
            
            if (company.getShortName() != null && !"".equals(company.getShortName())) {
                pstmt.setString(++index, company.getShortName()+"%");
            }
            
            if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                pstmt.setString(++index, "%,"+company.getTypeId()+",%");
            }
            
            if (company.getCode() != null && !"".equals(company.getCode())) {
                pstmt.setString(++index, company.getCode()+"%");
            }
            
            if(company.getProvinceId()!=null){
                pstmt.setInt(++index, company.getProvinceId());
            }
            
            if(company.getCityId()!=null){
                pstmt.setInt(++index, company.getCityId());
            }
        
            rs = pstmt.executeQuery();
        
            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyCounts");
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
                logSQLException(e, "getCompanyCounts");
                throw e;
            }
        }
    }
    
    /**
     * 
     * get the company count that the user's
     * company has no permission of browsing of
     * all companys' data.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   company
     * @param   userCompanyId
     * @return  int
     * @throws  SQLException
     */
    public int getCompanyCountByRules(Company company,Integer userCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
                    String tempSql=
                        " SELECT"+ 
                        " COUNT(*)"+     
                        " FROM ("+ 
                                " SELECT *"+ 
                                " FROM"+ 
                                 " ("+ 
                                    " SELECT"+ 
                                    " B.* "+ 
                                    " FROM  AGENT_CUSTOMER_TBL A, " +
                                    " COMPANY_AGENT_VIEW B " +
                                    
                                  
                                 
                                    " WHERE"+ 
                                    " A.CUSTOMER_ID=B.ID "+ 
                                    " AND A.AGENT_ID=? "+ 
                                    " UNION"+ 
                                    " SELECT  "+        
                                    " A.*"+ 
                                    " FROM"+ 
                                    " COMPANY_AGENT_VIEW A " +
                                    " WHERE"+ 
                                    " A.ID=? "+ 
                                    " ) " +
                                    " WHERE 1=1 " ;
                     
             StringBuffer sb = new StringBuffer(tempSql);
             
             if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                 sb.append(" AND ID IN (SELECT CUSTOMER_ID FROM AGENT_CUSTOMER_TBL WHERE AGENT_ID=? )");
             }
             
             if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                 sb.append(" AND MAIN_COMPANY_NAME LIKE ?");
             }
             
             if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                 sb.append(" AND SUB_COMPANY_NAME LIKE ?");
             }
             
             if (company.getShortName() != null && !"".equals(company.getShortName())) {
                 sb.append(" AND SHORT_NAME LIKE ?");
             }
            
             if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                 sb.append(" AND TYPE_ID LIKE ? ");
             }
             
             if (company.getCode() != null && !"".equals(company.getCode())) {
                 sb.append(" AND CODE LIKE ?");
             }
             
             if(company.getProvinceId()!=null){
                 sb.append(" AND PROVINCE_ID = ? ");
             }
             if(company.getCityId()!=null){
                 sb.append(" AND CITY_ID = ? ");
             }
            
             sb.append(" ORDER BY COM_NAME_PINYIN ) WHERE ID>0 ");
             
             String sql=sb.toString();
             pstmt = conn.prepareStatement(sql);

             int index = 0;
             pstmt.setInt(++index, userCompanyId);
             pstmt.setInt(++index, userCompanyId);
             
             if (company.getCustomerFlag() != null && company.getCustomerFlag().compareTo(1) == 0) {
                 pstmt.setInt(++index, userCompanyId);
             }
             
             if (company.getMainCompanyName() != null && !"".equals(company.getMainCompanyName())) {
                 pstmt.setString(++index, company.getMainCompanyName()+"%");
             }
             
             if (company.getSubCompanyName() != null && !"".equals(company.getSubCompanyName())) {
                 pstmt.setString(++index, company.getSubCompanyName()+"%");
             }
             
             
             if (company.getShortName() != null && !"".equals(company.getShortName())) {
                 pstmt.setString(++index, company.getShortName()+"%");
             }
             
             if(company.getTypeId() != null && !"".equals(company.getTypeId())){
                 pstmt.setString(++index, "%,"+company.getTypeId()+",%");
             }
             
             if (company.getCode() != null && !"".equals(company.getCode())) {
                 pstmt.setString(++index, company.getCode()+"%");
             }
             
             if(company.getProvinceId()!=null){
                 pstmt.setInt(++index, company.getProvinceId());
             }
             
             if(company.getCityId()!=null){
                 pstmt.setInt(++index, company.getCityId());
             }
             rs = pstmt.executeQuery();

        
            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyCounts");
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
                logSQLException(e, "getCompanyCounts");
                throw e;
            }
        }
    }


    /**
     * get company code by company ID.
     * @author  luyan
     * @since   1.0
     * @param   comId company ID
     * @return  company code
     * @throws  SQLException
     */
    public String getCodeByCompanyId(Integer comId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String code = null;

        try {
            // Start UOC
            
            String sql = " SELECT " +
                " CODE " +
                " FROM " +
                " COMPANY_TBL " +
                " WHERE " +
                " ID = ? " +
                " AND DELETED = 0 " +
                " AND ID > 0 " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                code = rs.getString(++index);
            }
            return code;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getShortNameByCompanyId");
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
                logSQLException(e, "getShortNameByCompanyId");
                throw e;
            }
        }
    }
    
    /**
     * install company drop
     * @author sunyx
     * @return
     */
    public List<Company> getInstallCompany(String subSql) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Company> list = new ArrayList<Company>();

        try {
            // Start UOC
            
            String sql = " SELECT" +
                        " ID," +
                        " SHORT_NAME" +
                        " FROM " +
                        " COMPANY_AGENT_VIEW" +
                        " WHERE " +
                        " TYPE_ID LIKE '%,3,%'" +
                        " AND DELETED=0 " +
                        " AND ID>0 ";
            if(!"".equals(subSql)){
                sql = sql + subSql;
            }
            sql = sql + " ORDER BY COM_NAME_PINYIN ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            int index = 0;
            Company com = null;
            while (rs.next()) {
                index = 0;
                com = new Company();
                com.setId(rs.getInt(++index)); 
                com.setShortName(rs.getString(++index)); 
                
                list.add(com);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyAgentRepaire");
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
                logSQLException(e, "getCompanyAgentRepaire");
                throw e;
            }
        }
    }
    
    /**
     * 获取改公司所代理的银行公司列表
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentId
     * @return  List<Company>
     * @throws  Exception
     */
    public List<Company> getCustomerByAgentId(Integer agentId,Integer cateFlag) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Company> list = new ArrayList<Company>();

        try {
            // Start UOC
            
            String sql =
                " SELECT A.CUSTOMER_ID,B.SHORT_NAME " +
                " FROM  AGENT_CUSTOMER_TBL A " +
                " LEFT OUTER JOIN COMPANY_AGENT_VIEW B " +
                " ON A.CUSTOMER_ID = B.ID" +
                " WHERE " +
                " A.AGENT_ID = ? AND " +
                " A.CATE_FLAG = ?  " +
                " ORDER BY B.COM_NAME_PINYIN " ;
            
            int index = 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(++index, agentId);
            pstmt.setInt(++index, cateFlag);
            rs = pstmt.executeQuery();

            Company com = null;
            while (rs.next()) {
                index = 0;
                com = new Company();
                com.setId(rs.getInt(++index)); 
                com.setShortName(rs.getString(++index)); 
                
                list.add(com);
            }
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCustomerByAgentId");
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
                logSQLException(e, "getCustomerByAgentId");
                throw e;
            }
        }
    }
    
    /**
     * get count by id.
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param Integer
     * @return int
     * @throws SQLException
     */
    public int getCountByBankId(Integer bankId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " COMPANY_TBL " +
                    " WHERE " +
                    "   BANK_ID = ?  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, bankId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByBankId");
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
                logSQLException(e, "getCountByBankId");
                throw e;
            }
        }
    }
}
