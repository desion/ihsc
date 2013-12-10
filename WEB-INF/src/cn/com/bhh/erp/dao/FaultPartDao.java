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
import cn.com.bhh.erp.entity.FaultPart;

public class FaultPartDao extends BaseDao{

    public FaultPartDao(Connection conn){
        super(conn);
    }

    /**
     * create a faultPart
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   faultPart
     * @throws  SQLException
     */
    public void createFaultPart(FaultPart faultPart) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO FAULT_PART_TBL (" +
                      " NAME," +
                      " TYPE " +
                      " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, faultPart.getName());
            pstmt.setString(++index, faultPart.getFaultMachineType());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createFaultPart");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createFaultPart");
                throw e;
            }
        }
    }
    
    /**
     * modify a FaultPart.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   FaultPart
     * @return  void
     * @throws  Exception
     */
    public void modifyFaultPart(FaultPart faultPart) throws Exception {
        PreparedStatement pstmt = null;

        try {
            int index = 0;

            // Start UOC
      
            
            String sql = 
                      " UPDATE FAULT_PART_TBL  SET " +
                      " NAME = ?  " +
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);
            index = 0;
            pstmt.setString(++index, faultPart.getName());
            pstmt.setInt(++index, faultPart.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyFaultPart");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "modifyFaultPart");
                throw e;
            }
        }
    }
    
    
    /**
     * delete a FaultPart by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   FaultPart
     * @throws  Exception
     */
    public void deleteFaultPart(FaultPart faultPart) throws Exception {
        PreparedStatement pstmt = null;

        try {
            
            // Start UOC
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " FAULT_PART_TBL " +
                       " WHERE " +
                       " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultPart.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteFaultPart");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteFaultPart");
                throw e;
            }
        }
    }
    
    /**
     * get count by id.
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  SQLException
     */
    public int getCountByID(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " FAULT_PART_TBL " +
                    " WHERE " +
                    " ID=?  ";
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
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @param   String
     * @return  Integer
     * @throws  SQLException
     */
    public Integer getIdByName(String name,String faultMachineType) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            // Start UOC
            String sql= 
                    " SELECT " +
                    " ID " +
                    " FROM " +
                    " FAULT_PART_TBL " +
                    " WHERE " +
                    " NAME=? " +
                    " AND TYPE = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, name);
            pstmt.setString(++index, faultMachineType);
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
    
    public List<FaultPart> searchAll(String faultMachineType) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            List<FaultPart> list = new ArrayList<FaultPart>();
            String sql = "SELECT " +
            		    " ID," +
            		    " NAME," +
            		    " TYPE " +
            		    " FROM " +
            		    " FAULT_PART_TBL" ;
            		    if(faultMachineType!=null){
            		        sql = sql + " WHERE TYPE = ? " ;
            		    }
            		    
            		    sql = sql + " ORDER BY TYPE,ID ";
            pstmt = conn.prepareStatement(sql);
            FaultPart faultPartOut = null;
            int index = 0;
            if(faultMachineType!=null){
                pstmt.setString(++index, faultMachineType);
            }
            
            rs = pstmt.executeQuery();
            while (rs.next()) {
                faultPartOut = new FaultPart();
                index = 0;
                faultPartOut.setId(rs.getInt(++index));
                faultPartOut.setName(rs.getString(++index));
                faultPartOut.setFaultMachineType(rs.getString(++index));

                list.add(faultPartOut);
            }
            return list;
        } catch(SQLException e){
            logSQLException(e, "search error");
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
     * get the count of the same name except self.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   faultPart
     * @return  int
     * @throws  Exception
     */
    public int getCountByNameModify(FaultPart faultPart) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = " SELECT " +
                         " COUNT(*) " +
                         " FROM " +
                         " FAULT_PART_TBL A WHERE EXISTS " + 
                         " ( SELECT " +
                         "   NAME " +
                         "   FROM " +
                         "   FAULT_PART_TBL B " +
                         "   WHERE " +
                         "   A.ID != B.ID " +
                         "   AND B.ID =?" +
                         "  ) " + 
                         " AND A.NAME= ? " +
                         " AND A.TYPE = ? " ;
            
           
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultPart.getId());
            pstmt.setString(++index, faultPart.getName());
            pstmt.setString(++index, faultPart.getFaultMachineType());
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
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   faultPart
     * @return  FaultPart
     * @throws  Exception
     */
    public FaultPart searchFaultPartById(FaultPart faultPart) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FaultPart faultPartOut = null;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " ID," +
                       " NAME," +
                       " TYPE " +
                       " FROM FAULT_PART_TBL " +
                       " WHERE ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, faultPart.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                faultPartOut = new FaultPart();
                index = 0;
                faultPartOut.setId(rs.getInt(++index));
                faultPartOut.setName(rs.getString(++index));
                faultPartOut.setFaultMachineType(rs.getString(++index));
            }

            return faultPartOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchFaultPartById");
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
                logSQLException(e, "searchFaultPartById");
                throw e;
            }
        }
    }
}
