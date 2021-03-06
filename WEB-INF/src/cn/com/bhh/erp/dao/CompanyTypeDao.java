//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.CompanyType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Company Type  table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class CompanyTypeDao extends BaseDao {
    public CompanyTypeDao(Connection conn) {
        super(conn);
    }

    /**
     * delete company type.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @throws  Exception
     */
    public void deleteCompanyType(CompanyType comType) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql =
                             " SELECT " +
                             " EXCLUSIVE_KEY " +
                             " FROM" +
                             " COMPANY_TYPE_TBL" +
                             " WHERE " +
                             " ID = ? " +
                             " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, comType.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != comType.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("record is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }

            // Start UOC
            String sql = 
                      " DELETE FROM" +
                      " COMPANY_TYPE_TBL " +
                      " WHERE " +
                      " ID = ?";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comType.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteCompanyType");
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
                logSQLException(e, "deleteCompanyType");
                throw e;
            }
        }
    }

    /**
     * create company type.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @return  void
     * @throws  SQLException
     */
    public void createCompanyType(CompanyType comType) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql =
                         " INSERT INTO COMPANY_TYPE_TBL (" +
                         " NAME," +
                         " DESCRIPTION," +
                         " CREATOR_ID," +
                         " MODIFIER_ID" +
                         " ) VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, comType.getName());
            pstmt.setString(++index, comType.getDescription());
            pstmt.setInt(++index, comType.getCreatorID());
            pstmt.setInt(++index, comType.getModifierID());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createCompanyType");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createCompanyType");
                throw e;
            }
        }
    }

    /**
     * get all the users' information from user table
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCompanyType&gt
     * @throws  SQLException
     */
    public List<CompanyType> selectAllCompanyType() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <CompanyType>list = new ArrayList<CompanyType>();
            String sql = 
                        " SELECT " +
                        " ID," +
                        " NAME," +
                        " DESCRIPTION," +
                        " CREATE_TIME," +
                        " CREATOR_ID," +
                        " MODIFY_TIME," +
                        " MODIFIER_ID," +
                        " EXCLUSIVE_KEY, " +
                        " MODIFY_FLG " +
                        " FROM " +
                        " COMPANY_TYPE_TBL"+
                        " ORDER BY ID ASC ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                CompanyType comSt = new CompanyType();
                index = 0;
                comSt.setId(rs.getInt(++index));
                comSt.setName(rs.getString(++index));
                comSt.setDescription(rs.getString(++index));
                comSt.setCreateTime(rs.getString(++index));
                comSt.setCreatorID(rs.getInt(++index));
                comSt.setModifyTime(rs.getString(++index));
                comSt.setModifierID(rs.getInt(++index));
                comSt.setExclusiveKey(rs.getInt(++index));
                comSt.setModifyFlg(rs.getInt(++index));
                list.add(comSt);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAllCompanyType");
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
                logSQLException(e, "selectAllCompanyType");
                throw e;
            }
        }
    }

    /**
     * get the company type map with the key id,name value
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  Map<Integer,String>
     * @throws  Exception
     */
    public Map<Integer,String> selectComTypeMap() throws SQLException {
        Map<Integer, String> comTypeMap = new HashMap<Integer, String>();
        List<CompanyType> comTypesOut = new ArrayList<CompanyType>();
        comTypesOut = selectAllCompanyType();
        for (CompanyType comType : comTypesOut) {
            comTypeMap.put(comType.getId(), comType.getName());
        }
        return comTypeMap;
        
    }
    /**
     * search company type by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @return  CompanyType
     * @throws  Exception
     */
    public CompanyType searchCompanyTypeById(CompanyType comType) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CompanyType comTypeOut = null;

        
        try {
            // Start UOC
            
            if (exclusiveCheck) {
                String sql =
                             " SELECT " +
                             " EXCLUSIVE_KEY " +
                             " FROM" +
                             " COMPANY_TYPE_TBL" +
                             " WHERE " +
                             " ID = ? " ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, comType.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != comType.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("record is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
                
            }
            
            String    sql = 
                        " SELECT " +
                        " ID," +
                        " NAME," +
                        " DESCRIPTION," +
                        " CREATE_TIME," +
                        " CREATOR_ID," +
                        " MODIFY_TIME," +
                        " MODIFIER_ID," +
                        " EXCLUSIVE_KEY, " +
                        " MODIFY_FLG " +
                        " FROM " +
                        " COMPANY_TYPE_TBL" +
                        " WHERE " +
                        " ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comType.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                comTypeOut = new CompanyType();
                index = 0;
                comTypeOut.setId(rs.getInt(++index));
                comTypeOut.setName(rs.getString(++index));
                comTypeOut.setDescription(rs.getString(++index));
                comTypeOut.setCreateTime(rs.getString(++index));
                comTypeOut.setCreatorID(rs.getInt(++index));
                comTypeOut.setModifyTime(rs.getString(++index));
                comTypeOut.setModifierID(rs.getInt(++index));
                comTypeOut.setExclusiveKey(rs.getInt(++index));
                comTypeOut.setModifyFlg(rs.getInt(++index));
            }

            return comTypeOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCompanyTypeById");
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
                logSQLException(e, "searchCompanyTypeById");
                throw e;
            }
        }
    }

    /**
     * get the count of the same name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @return  int
     * @throws  SQLException
     */
    public int getCountByComTypeName(CompanyType comType) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                        " SELECT " +
                        " COUNT(*) " +
                        " FROM " +
                        " COMPANY_TYPE_TBL " +
                        " WHERE " +
                        " NAME = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, comType.getName());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByComTypeName");
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
                logSQLException(e, "getCountByComTypeName");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get the count of company type  by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   comTypeId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByComTypeId(Integer comTypeId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                        " SELECT " +
                        " COUNT(*) " +
                        " FROM " +
                        " COMPANY_TYPE_TBL " +
                        " WHERE " +
                        " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comTypeId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByComTypeId");
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
                logSQLException(e, "getCountByComTypeId");
                throw e;
            }
        }

        return count;
    }

    /**
     * modify company information
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @throws  Exception
     */
    public void modifyCompanyTypeInfo(CompanyType comType) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
                if (exclusiveCheck) {
                    String sql = 
                                " SELECT" +
                                " EXCLUSIVE_KEY " +
                                " FROM " +
                                " COMPANY_TYPE_TBL " +
                                " WHERE " +
                                " ID = ? " +
                                " FOR UPDATE NOWAIT";
                    pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, comType.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != comType.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("record is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }

            Integer nowExclusiveKey = comType.getExclusiveKey();
            ++nowExclusiveKey;
            comType.setExclusiveKey(nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = 
                         " UPDATE "                +
                         " COMPANY_TYPE_TBL SET "  +
                         " NAME=?, "               +
                         " DESCRIPTION=?,"         + 
                         " MODIFY_TIME=?,"         +
                         " MODIFIER_ID=?,"         +
                         " EXCLUSIVE_KEY=?  "      +
                         " WHERE "                 +
                         " ID = ?";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, comType.getName());
            pstmt.setString(++index, comType.getDescription());
            pstmt.setString(++index, comType.getModifyTime());
            pstmt.setInt(++index, comType.getModifierID());
            pstmt.setInt(++index, comType.getExclusiveKey());
            pstmt.setInt(++index, comType.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyCompanyTypeInfo");
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
                logSQLException(e, "modifyCompanyTypeInfo");
                throw e;
            }
        }
    }

    /**
     * get the count of same name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   CompanyType
     * @return  int
     * @throws  SQLException
     */
    public int getCountByComTypeNameModify(CompanyType comType) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                        " SELECT " +
                        " COUNT(*) " +
                        " FROM " +
                        " COMPANY_TYPE_TBL A " +
                        " WHERE " +
                        " EXISTS " +
                        " ( SELECT" +
                        "  NAME " +
                        "  FROM " +
                        "  COMPANY_TYPE_TBL B " +
                        "  WHERE " +
                        "  A.ID != B.ID " +
                        "  AND " +
                        "  B.ID =? ) " +
                        " AND" +
                        " A.NAME= ? ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, comType.getId());
            pstmt.setString(++index, comType.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByComTypeNameModify");
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
                logSQLException(e, "getCountByComTypeNameModify");
                throw e;
            }
        }

        return count;
    }

}
