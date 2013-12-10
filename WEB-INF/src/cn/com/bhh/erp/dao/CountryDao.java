//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * Country table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class CountryDao extends BaseDao {
    public CountryDao(Connection conn) {
        super(conn);
    }

    /**
     * delete country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  void
     * @throws  Exception
     */
    public void delete(Country country) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " COUNTRY_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND ID>0 " +
                           " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, country.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != country.getExclusiveKey()) {
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
            		     " COUNTRY_TBL " +
            		     " WHERE " +
            		     " ID = ?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());

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
     * create country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @throws  SQLException
     */
    public void create(Country country) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " INSERT INTO COUNTRY_TBL (" +
                       " NAME," +
                       " SHORT_NAME," +
                       " NAME_EN," +
                       " SHORT_NAME_EN" +
                       " ) VALUES(?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, country.getName());
            pstmt.setString(++index, country.getShortName());
            pstmt.setString(++index, country.getNameEn());
            pstmt.setString(++index, country.getShortNameEn());
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
     * get all country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltCountry&gt
     * @throws  SQLException
     */
    public List<Country> searchAll() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Country> list = new ArrayList<Country>();
            String sql = 
                       " SELECT " +
                       " ID," +
                       " NAME," +
                       " SHORT_NAME," +
                       " NAME_EN," +
                       " SHORT_NAME_EN," +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " COUNTRY_TBL" +
                       " WHERE " +
                       " ID>0 "+
                       " ORDER BY ID ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Country country = new Country();
                index = 0;
                country.setId(rs.getInt(++index));
                country.setName(rs.getString(++index));
                country.setShortName(rs.getString(++index));
                country.setNameEn(rs.getString(++index));
                country.setShortNameEn(rs.getString(++index));
                country.setExclusiveKey(rs.getInt(++index));
                list.add(country);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchAll");
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
                logSQLException(e, "searchAll");
                throw e;
            }
        }
    }

    /**
     * get country by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  Country
     * @throws  Exception
     */
    public Country searchById(Country country) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Country ctryOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " COUNTRY_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND ID>0 " ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, country.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != country.getExclusiveKey()) {
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
                       " ID," +
                       " NAME," +
                       " SHORT_NAME," +
                       " NAME_EN," +
                       " SHORT_NAME_EN," +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " COUNTRY_TBL " +
                       " WHERE " +
                       " ID=?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                ctryOut = new Country();
                index = 0;
                ctryOut.setId(rs.getInt(++index));
                ctryOut.setName(rs.getString(++index));
                ctryOut.setShortName(rs.getString(++index));
                ctryOut.setNameEn(rs.getString(++index));
                ctryOut.setShortNameEn(rs.getString(++index));
                ctryOut.setExclusiveKey(rs.getInt(++index));
            }

            return ctryOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByPK");
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
                logSQLException(e, "selectByPK");
                throw e;
            }
        }
    }
    
    /**
     * get country by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  Exception
     */
    public int getCountByCountryId(Integer countryId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count =0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL " +
                       " WHERE " +
                       " ID=?" +
                       " AND ID>0";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, countryId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountId");
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
                logSQLException(e, "getCountByCountId");
                throw e;
            }
        }
    }

    /**
     * get country by name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryName(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL " +
                       " WHERE " +
                       " NAME = ? " +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, country.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryName");
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
                logSQLException(e, "getCountByCountryName");
                throw e;
            }
        }

        return count;
    }

    /**
     * get the company count
     * by short name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCounryShortName(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL " +
                       " WHERE " +
                       " SHORT_NAME = ? " +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, country.getShortName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCounryShortName");
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
                logSQLException(e, "getCountByCounryShortName");
                throw e;
            }
        }

        return count;
    }

    /**
     * get company count
     * by english short name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryShortNameEn(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL " +
                       " WHERE " +
                       " SHORT_NAME_EN = ? " +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, country.getShortNameEn());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryShortNameEn");
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
                logSQLException(e, "getCountByCountryShortNameEn");
                throw e;
            }
        }

        return count;
    }

    /**
     * get company count
     * by english name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryNameEn(Country country) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " + 
                       " COUNT(*) " + 
                       " FROM " + 
                       " COUNTRY_TBL " + 
                       " WHERE " + 
                       " NAME_EN=? " +
                       " AND ID>0";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, country.getNameEn());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryNameEn");
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
                logSQLException(e, "getCountByCountryNameEn");
                throw e;
            }
        }

        return count;
    }

    /**
     * modify country information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @throws  Exception
     */
    public void modifyCountryInfo(Country country) throws Exception {
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
                           " COUNTRY_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND ID>0 " +
                           " FOR UPDATE NOWAIT";
                
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, country.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != country.getExclusiveKey()) {
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

            Integer nowExclusiveKey = country.getExclusiveKey();
            ++nowExclusiveKey;
            country.setExclusiveKey(nowExclusiveKey); // set the exclusive key =
                                                      // exclusiveKey+1

            String sql = 
                       " UPDATE COUNTRY_TBL  SET " +
                       " NAME=?, " +
                       " SHORT_NAME=?,"+
                       " NAME_EN=?," +
                       " SHORT_NAME_EN=?," +
                       " EXCLUSIVE_KEY=?  " +
                       " WHERE " +
                       " ID = ?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, country.getName());
            pstmt.setString(++index, country.getShortName());
            pstmt.setString(++index, country.getNameEn());
            pstmt.setString(++index, country.getShortNameEn());
            pstmt.setInt(++index, country.getExclusiveKey());
            pstmt.setInt(++index, country.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateCountryInfo");
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
                logSQLException(e, "updateCountryInfo");
                throw e;
            }
        }
    }

    /**
     * get province count of country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getProvinceCountOfCountry(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
            		     " COUNT(*) " +
            		     " FROM " +
            		     " PROVINCE_TBL A, COUNTRY_TBL  B " +
            		     " WHERE " +
            		     " A.COUNTRY_ID = B.ID " +
            		     " AND B.ID= ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "provinceCounts");
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
                logSQLException(e, "provinceCounts");
                throw e;
            }
        }

        return count;
    }

    /**
     * get country count
     * by name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryNameModify(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM COUNTRY_TBL A " +
                       " WHERE EXISTS "  + 
                       " ( SELECT " +
                       "   NAME " +
                       "   FROM " +
                       "   COUNTRY_TBL B " +
                       "   WHERE " +
                       "   A.ID != B.ID " +
                       "   AND B.ID =? " +
                       "  ) " +
                       " AND A.NAME= ?" +
                       " AND A.ID>0 ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());
            pstmt.setString(++index, country.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryNameModify");
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
                logSQLException(e, "getCountByCountryNameModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get country count
     * by short name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryShortNameModify(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                      " SELECT " +
                      " COUNT(*) " +
                      " FROM " +
                      " COUNTRY_TBL A " +
                      " WHERE EXISTS " + 
                      " ( SELECT " +
                      "   NAME " +
                      "   FROM " +
                      "   COUNTRY_TBL B " +
                      "   WHERE A.ID != B.ID " +
                      "   AND B.ID =? " +
                      "   ) " +
                      " AND A.SHORT_NAME= ? " +
                      " AND A.ID>0 ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());
            pstmt.setString(++index, country.getShortName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryShortNameModify");
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
                logSQLException(e, "getCountByCountryShortNameModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get country count
     * by english short name
     * except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryShortNameEnModify(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql =
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL A " +
                       " WHERE EXISTS " +
                       " ( SELECT " +
                       "   NAME " +
                       "   FROM " +
                       "   COUNTRY_TBL B " +
                       "   WHERE A.ID != B.ID " +
                       "   AND B.ID =? " +
                       "  ) " +
                       " AND A.SHORT_NAME_EN= ? " +
                       " AND A.ID>0 ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());
            pstmt.setString(++index, country.getShortNameEn());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryShortNameEnModify");
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
                logSQLException(e, "getCountByCountryShortNameEnModify");
                throw e;
            }
        }

        return count;
    }

    /**
     * get country count
     * by english name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Country
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCountryNameEnModify(Country country) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " COUNTRY_TBL A " +
                       " WHERE EXISTS " +
                       " ( SELECT " +
                       "   NAME " +
                       "   FROM " +
                       "   COUNTRY_TBL B " +
                       "   WHERE " +
                       "   A.ID != B.ID " +
                       "   AND B.ID =?" +
                       "  ) " +
                       " AND A.NAME_EN= ? " +
                       " AND A.ID>0";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, country.getId());
            pstmt.setString(++index, country.getNameEn());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCountryNameEnModify");
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
                logSQLException(e, "getCountByCountryNameEnModify");
                throw e;
            }
        }

        return count;
    }
}
