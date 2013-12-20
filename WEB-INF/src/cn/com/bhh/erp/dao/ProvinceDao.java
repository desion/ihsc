//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Province;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Province table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class ProvinceDao extends BaseDao {
    public ProvinceDao(Connection conn) {
        super(conn);
    }

    /**
     * delete province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  Exception
     */
    public void deleteProvince(Province province) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " PROVINCE_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, province.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != province.getExclusiveKey()) {
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

            // Start UOC
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " PROVINCE_TBL " +
                       " WHERE " +
                       " ID = ?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteProvince");
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
                logSQLException(e, "deleteProvince");
                throw e;
            }
        }
    }

    /**
     * create province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  SQLException
     */
    public void createProvince(Province province) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " INSERT INTO PROVINCE_TBL (" +
            		   " NAME," +
            		   " SHORT_NAME," +
            		   " COUNTRY_ID" +
            		   " ) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, province.getName());
            pstmt.setString(++index, province.getShortName());
            pstmt.setInt(++index, province.getCountryId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createProvince");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createProvince");
                throw e;
            }
        }
    }

    /**
     * get all province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Province> searchAllProvince(boolean isInCompany,Integer bankId,Integer saleComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Province>list = new ArrayList<Province>();
            String sql = " SELECT " +
            		     " P.ID," +
            		     " P.NAME," +
            		     " P.SHORT_NAME," +
            		     " P.COUNTRY_ID," +
            		     " P.EXCLUSIVE_KEY," +
            		     " C.SHORT_NAME " +
            		     " FROM " +
            		     " PROVINCE_TBL P ,COUNTRY_TBL C " +
                         " WHERE " +
                         " P.COUNTRY_ID = C.ID" +
                         " AND P.ID>0 " ;
            if(isInCompany){
                 sql  = sql +  
                       " AND P.ID IN " +
                       " (" +
                       "  SELECT " +
                       "   DISTINCT PROVINCE_ID" +
                       "   FROM " +
                       "   COMPANY_TBL " +
                       "   WHERE ID > 0 " + 
                       "   AND PROVINCE_ID IS NOT NULL ";
                       if(bankId!=null){
                          sql = sql +" AND BANK_ID=? ";
                       }
                       if(saleComId!=null){
                           sql = sql +" AND ID = ? ";
                       }
                       sql = sql + " )";
   
            }

                         
            sql  = sql + " ORDER BY P.COUNTRY_ID,P.SHORT_NAME";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            
            if(isInCompany){
                if(bankId!=null){
                    pstmt.setInt(++index, bankId);
                }
                if(saleComId!=null){
                    pstmt.setInt(++index, saleComId);
                }
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                index = 0;
                province.setId(rs.getInt(++index));
                province.setName(rs.getString(++index));
                province.setShortName(rs.getString(++index));
                province.setCountryId(rs.getInt(++index));
                province.setExclusiveKey(rs.getInt(++index));
                province.setCountryName(rs.getString(++index));
                list.add(province);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAll");
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
     * get all province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Province> searchAllProvince() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Province>list = new ArrayList<Province>();
            String sql = " SELECT " +
            		     " P.ID," +
            		     " P.NAME" +
            		     " FROM " +
            		     " PROVINCE_TBL P";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                index = 0;
                province.setId(rs.getInt(++index));
                province.setName(rs.getString(++index));
                list.add(province);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAll");
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
     * get all province of the country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   countryId
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Province> searchProvinceByCountryId(Integer countryId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Province>list = new ArrayList<Province>();
            String sql = " SELECT " +
                         " B.ID," +
                         " B.NAME," +
                         " B.COUNTRY_ID," +
                         " C.SHORT_NAME" +
                         " FROM " +
                         " PROVINCE_TBL B,COUNTRY_TBL C  " +
                         " WHERE " +
                         " B.COUNTRY_ID = C.ID" +
                         " AND B.COUNTRY_ID = ?" +
                         " AND B.ID>0 " +
                         " ORDER BY COUNTRY_ID,B.SHORT_NAME";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, countryId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                index = 0;
                province.setId(rs.getInt(++index));
                province.setName(rs.getString(++index));
                province.setCountryId(rs.getInt(++index));
                province.setCountryName(rs.getString(++index));
                list.add(province);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchProvinceByCountryId");
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
                logSQLException(e, "searchProvinceByCountryId");
                throw e;
            }
        }
    }
    

    /**
     * get province by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  Province
     * @throws  Exception
     */
    public Province searchProvinceById(Province province) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Province proOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " PROVINCE_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND ID>0" ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, province.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != province.getExclusiveKey()) {
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
            
            
            String sql = 
                       " SELECT " +
            		   " ID," +
            		   " NAME," +
            		   " SHORT_NAME," +
            		   " COUNTRY_ID," +
            		   " EXCLUSIVE_KEY" +
            		   " FROM " +
            		   " PROVINCE_TBL " +
            		   " WHERE ID=?" +
            		   " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                proOut = new Province();
                index = 0;
                proOut.setId(rs.getInt(++index));
                proOut.setName(rs.getString(++index));
                proOut.setShortName(rs.getString(++index));
                proOut.setCountryId(rs.getInt(++index));
                proOut.setExclusiveKey(rs.getInt(++index));
            }

            return proOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchProvinceById");
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
                logSQLException(e, "searchProvinceById");
                throw e;
            }
        }
    }

    /**
     * get province count by name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCountByProvinceName(Province province) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " PROVINCE_TBL " +
                       " WHERE " +
                       " NAME = ?" +
                       " AND ID>0  ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, province.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProvinceName");
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
                logSQLException(e, "getCountByProvinceName");
                throw e;
            }
        }

        return count;
    }

    /**
     * modify province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  Exception
     */
    public void modifyProvinceInfo(Province province) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
            if (exclusiveCheck) {
                    String sql = 
                               " SELECT " +
                               " EXCLUSIVE_KEY " +
                               " FROM PROVINCE_TBL " +
                               " WHERE ID = ? " +
                               " AND ID>0 " +
                               " FOR UPDATE NOWAIT";
                    pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, province.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != province.getExclusiveKey()) {
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
              
            Integer nowExclusiveKey=province.getExclusiveKey();
            province.setExclusiveKey(++nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = 
                         " UPDATE PROVINCE_TBL  SET " +
            		     " NAME=?," +
            		     " SHORT_NAME=?, " +
            		     " COUNTRY_ID=?," +
            		     " EXCLUSIVE_KEY=? " +
            		     " WHERE " +
            		     " ID = ?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, province.getName());
            pstmt.setString(++index, province.getShortName());
            pstmt.setInt(++index, province.getCountryId());
            pstmt.setInt(++index, province.getExclusiveKey());
            pstmt.setInt(++index, province.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateProvinceInfo");
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
                logSQLException(e, "updateProvinceInfo");
                throw e;
            }
        }
    }

    /**
     * get the city count of province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCityCountOfProvince(Province province) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM  " +
                       " CITY_TBL A, PROVINCE_TBL  B " +
                       " WHERE " +
                       " A.PROVINCE_ID = B.ID " +
                       " AND B.ID= ?  ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "cityCounts");
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
                logSQLException(e, "cityCounts");
                throw e;
            }
        }

        return count;
    }

    /**
     * get province count
     * by name except self
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCountByProvinceNameModify(Province province) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                      " SELECT " +
            		  " COUNT(*) " +
            		  " FROM " +
            		  " PROVINCE_TBL A " +
            		  " WHERE EXISTS " + 
                      " ( " +
                      "  SELECT " +
                      "  NAME " +
                      "  FROM " +
                      "  PROVINCE_TBL B " +
                      "  WHERE " +
                      "  A.ID != B.ID " +
                      "  AND B.ID =? " +
                      " ) "  +
                      " AND A.NAME= ? " +
                      " AND A.ID>0";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());
            pstmt.setString(++index, province.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProvinceNameModify");
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
                logSQLException(e, "getCountByProvinceNameModify");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get province by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  Exception
     */
    public int getCountByProvinceId(Integer provinceId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count =0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " PROVINCE_TBL " +
                       " WHERE " +
                       " ID=?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, provinceId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProvinceId");
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
                logSQLException(e, "getCountByProvinceId");
                throw e;
            }
        }
    }

}
