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


public class CityDao extends BaseDao {
    public CityDao(Connection conn) {
        super(conn);
    }

    /**
     * delete city.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @throws  Exception
     */
    public void deleteCity(City city) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = " SELECT " +
                		     " EXCLUSIVE_KEY " +
                		     " FROM " +
                		     " CITY_TBL " +
                		     " WHERE " +
                		     " ID = ? " +
                		     " AND ID>0 " +
                		     " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, city.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != city.getExclusiveKey()) {
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
                       " DELETE " +
            		   " FROM " +
            		   " CITY_TBL " +
            		   " WHERE " +
            		   " ID = ?" +
            		   " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, city.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteCity");
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
                logSQLException(e, "deleteCity");
                throw e;
            }
        }
    }

    /**
     * create city.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @throws  Exception
     */
    public void createCity(City city) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                         " INSERT INTO CITY_TBL (" +
            		     " NAME," +
            		     " SHORT_NAME," +
            		     " PROVINCE_ID," +
            		     " POST_CODE," +
            		     " TEL_CODE" +
            		     " ) VALUES(?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, city.getName());
            pstmt.setString(++index, city.getShortName());
            pstmt.setInt(++index, city.getProvinceId());
            pstmt.setString(++index, city.getPostCode());
            pstmt.setString(++index, city.getTelCode());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createCity");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createCity");
                throw e;
            }
        }
    }



    /**
     * select the city for drop component.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCity&gt
     * @throws  SQLException
     */
    public List<City> getCityDrop(
            boolean isInCompany,
            Integer provinceId,
            Integer bankId,
            Integer saleComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <City>list = new ArrayList<City>();
            String sql = 
                         " SELECT  " +
            		     " A.ID, " +
            		     " A.NAME CITY_NAME," +
            		     " B.NAME PROVINCE_NAME," +
            		     " C.SHORT_NAME COUNTRY_NAME " +
                         " FROM " +
                         " CITY_TBL A,PROVINCE_TBL B,COUNTRY_TBL C " +
                         " WHERE " +
                         " A.PROVINCE_ID=B.ID " +
                         " AND B.COUNTRY_ID=C.ID" +
                         " AND A.ID>0 " ;
                        if(isInCompany){
                           sql = sql + 
                              " AND A.ID IN " +
                              " ( SELECT DISTINCT CITY_ID" +
                              "   FROM COMPANY_TBL " +
                              "   WHERE CITY_ID IS NOT NULL" +
                              "   AND CITY_ID > 0 " ;
                            if(bankId!=null){
                                 sql = sql + " AND BANK_ID = ?" ;
                            }
                            if(saleComId!=null){
                                 sql = sql + " AND ID = ? ";
                            }
                             sql = sql +  " ) ";
                         }
                         if(provinceId!=null){
                             sql = sql + " AND A.PROVINCE_ID = ? ";
                         }
                                     
                         sql = sql + " ORDER BY C.NAME,B.SHORT_NAME,A.SHORT_NAME";
                         

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
            if(provinceId!=null){
                pstmt.setInt(++index, provinceId);
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                City city = new City();
                index = 0;
                city.setId(rs.getInt(++index));
                city.setName(rs.getString(++index));
                city.setProvinceName(rs.getString(++index));
                city.setCountryName(rs.getString(++index));
                list.add(city);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCityDrop");
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
                logSQLException(e, "getCityDrop");
                throw e;
            }
        }
    }

    
    /**
     * search one city by city id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @return  City
     * @throws  Exception
     */
    public City searchCityById(City city) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        City cityOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                          " SELECT " +
                          " EXCLUSIVE_KEY " +
                          " FROM " +
                          " CITY_TBL " +
                          " WHERE ID = ? " +
                          " AND ID>0 ";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, city.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != city.getExclusiveKey()) {
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
            		     " ID," +
            		     " NAME," +
            		     " SHORT_NAME," +
            		     " POST_CODE," +
            		     " TEL_CODE," +
            		     " PROVINCE_ID," +
            		     " EXCLUSIVE_KEY " +
            		     " FROM " +
            		     " CITY_TBL " +
            		     " WHERE " +
            		     " ID=?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, city.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                cityOut = new City();
                index = 0;
                cityOut.setId(rs.getInt(++index));
                cityOut.setName(rs.getString(++index));
                cityOut.setShortName(rs.getString(++index));
                cityOut.setPostCode(rs.getString(++index));
                cityOut.setTelCode(rs.getString(++index));
                cityOut.setProvinceId(rs.getInt(++index));
                cityOut.setExclusiveKey(rs.getInt(++index));
            }

            return cityOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCityById");
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
                logSQLException(e, "searchCityById");
                throw e;
            }
        }
    }

    /**
     * get the count of the same name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @return  int
     * @throws  Exception
     */
    public int getCountByName(City city) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
            		     " COUNT(*) " +
            		     " FROM " +
            		     " CITY_TBL " +
            		     " WHERE " +
            		     " NAME = ?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, city.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByName");
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
                logSQLException(e, "selectByName");
                throw e;
            }
        }

        return count;
    }

    /**
     * modify city
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @throws  Exception
     */
    public void modifyCityInfo(City city) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
            if (exclusiveCheck) {
                String sql = " SELECT " +
                		     " EXCLUSIVE_KEY " +
                		     " FROM " +
                		     " CITY_TBL " +
                		     " WHERE " +
                		     " ID = ? " +
                		     " AND ID>0 " +
                		     " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, city.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != city.getExclusiveKey()) {
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
              
            Integer nowExclusiveKey=city.getExclusiveKey();
            city.setExclusiveKey(++nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = 
                         " UPDATE CITY_TBL SET " +
            		     " NAME=?, " +
            		     " SHORT_NAME=?, " +
            		     " PROVINCE_ID=?," +
            		     " POST_CODE=?," +
            		     " TEL_CODE=?," +
            		     " EXCLUSIVE_KEY=? " +
            		     " WHERE " +
            		     " ID = ?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, city.getName());
            pstmt.setString(++index, city.getShortName());
            pstmt.setInt(++index, city.getProvinceId());
            pstmt.setString(++index, city.getPostCode());
            pstmt.setString(++index, city.getTelCode());
            pstmt.setInt(++index, city.getExclusiveKey());
            pstmt.setInt(++index, city.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyCityInfo");
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
                logSQLException(e, "modifyCityInfo");
                throw e;
            }
        }
    }

    /**
     * get the count of the same name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   City
     * @return  int
     * @throws  Exception
     */
    public int getCountByNameModify(City city) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = " SELECT " +
            		     " COUNT(*) " +
            		     " FROM " +
            		     " CITY_TBL A WHERE EXISTS " + 
                         " ( SELECT " +
                         "   NAME " +
                         "   FROM " +
                         "   CITY_TBL B " +
                         "   WHERE " +
                         "   A.ID != B.ID " +
                         "   AND B.ID =?" +
                         "  ) " + 
                         " AND A.NAME= ?" +
                         " AND A.ID>0 ";
            
           
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, city.getId());
            pstmt.setString(++index, city.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByNameModify");
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
                logSQLException(e, "getCountByNameModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get the city by page.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   the begin and end record index.
     * @return  List&ltCity&gt
     * @throws  Exception
     */
    public List<City> getCitysByPage(City cityIn,int intBegin, int intEnd) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <City>list = new ArrayList<City>();
            
            String sql= 
                      " SELECT " +
                      " ID, " +
                      " NAME," +
                      " SHORT_NAME," +
                      " PROVINCE_ID," +
                      " PROVINCE_NAME," +
                      " COUNTRY_NAME," +
                      " POST_CODE, " +
                      " TEL_CODE, " +
                      " EXCLUSIVE_KEY " +
                      " FROM  "+
                      " (SELECT " +
                      "  ROWNUM NO, " +
                      "  T.*  " +
                      "  FROM  " +
                      "   (SELECT " +
                      "     A.*, " +
                      "     B.NAME PROVINCE_NAME," +
                      "     C.SHORT_NAME COUNTRY_NAME  " +
                      "     FROM " +
                      "     CITY_TBL A ,PROVINCE_TBL B,COUNTRY_TBL C"+
                      "     WHERE " +
                      "     A.PROVINCE_ID = B.ID  " +
                      "     AND B.COUNTRY_ID=C.ID" +
                      "     AND A.ID>0 " ;
                      
                      StringBuffer sb = new StringBuffer(sql);

                      if (cityIn.getCountryId()!=null) {
                          sb.append(" AND B.COUNTRY_ID = ? ");
                      }
                      if (cityIn.getProvinceId()!=null) {
                          sb.append(" AND A.PROVINCE_ID = ? ");
                      }
                      
                      if (cityIn.getName()!=null&&!"".equals(cityIn.getName())) {
                          sb.append(" AND A.NAME LIKE ? ");
                      }
                      
                      sb.append("ORDER BY C.NAME, B.SHORT_NAME,A.SHORT_NAME,A.ID )T WHERE ROWNUM<=? )WHERE NO>? ");
                
                
            pstmt = conn.prepareStatement(sb.toString());

            int index = 0;
            
            if (cityIn.getCountryId()!=null) {
                pstmt.setInt(++index, cityIn.getCountryId());
            }
            if (cityIn.getProvinceId()!=null) {
                pstmt.setInt(++index, cityIn.getProvinceId());
            }
            
            if (cityIn.getName()!=null&&!"".equals(cityIn.getName())) {
                pstmt.setString(++index, cityIn.getName()+"%");
            }
            
            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
          
            rs = pstmt.executeQuery();

            while (rs.next()) {
                City city = new City();
                index = 0;
                city.setId(rs.getInt(++index));
                city.setName(rs.getString(++index));
                city.setShortName(rs.getString(++index));
                city.setProvinceId(rs.getInt(++index));
                city.setProvinceName(rs.getString(++index));
                city.setCountryName(rs.getString(++index));
                city.setPostCode(rs.getString(++index));
                city.setTelCode(rs.getString(++index));
                city.setExclusiveKey(rs.getInt(++index));
                list.add(city);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByPage");
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
                logSQLException(e, "selectByPage");
                throw e;
            }
        }
    }

    /**
     * get the city counts.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  int
     * @throws  SQLException
     */
    public int getCityCounts(City city) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
            		     "  COUNT(*) " +
            		     "  FROM " +
                         "  CITY_TBL A ,PROVINCE_TBL B,COUNTRY_TBL C"+
                         "  WHERE " +
                         "  A.PROVINCE_ID = B.ID  " +
                         "  AND B.COUNTRY_ID=C.ID" +
                         "  AND A.ID>0 " ;
            
            StringBuffer sb = new StringBuffer(sql);

            if (city.getCountryId()!=null) {
                sb.append(" AND B.COUNTRY_ID = ? ");
            }
            if (city.getProvinceId()!=null) {
                sb.append(" AND A.PROVINCE_ID = ? ");
            }
            
            if (city.getName()!=null&&!"".equals(city.getName())) {
                sb.append(" AND A.NAME LIKE ? ");
            }
            
            pstmt = conn.prepareStatement(sb.toString());

            int index = 0;
            
            if (city.getCountryId()!=null) {
                pstmt.setInt(++index, city.getCountryId());
            }
            if (city.getProvinceId()!=null) {
                pstmt.setInt(++index, city.getProvinceId());
            }
            
            if (city.getName()!=null&&!"".equals(city.getName())) {
                pstmt.setString(++index, city.getName()+"%");
            }
            
            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCityCounts");
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
                logSQLException(e, "getCityCounts");
                throw e;
            }
        }
    }
    
    /**
     * get the city count by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   cityId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCityId(Integer cityId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
                         " COUNT(*) " +
                         " FROM " +
                         " CITY_TBL " +
                         " WHERE " +
                         " ID=? " +
                         " AND ID>0" ;     
            int index = 0;
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(++index, cityId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCityId");
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
                logSQLException(e, "getCountByCityId");
                throw e;
            }
        }
    }
}
