package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Bank;


public class BankDao extends BaseDao{

    public BankDao(Connection conn) {
        super(conn);
    }

    /**
     * get bank list
     * @author xiangzq
     * @param List
     * @return
     * @throws SQLException
     */
    public List<Bank> getBankListForDrop(boolean isInCompany) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Bank> list = new ArrayList<Bank>();
        
        try {
            // Start UOC
            String sql = 
                        " SELECT " + 
                        " ID," +
                        " NAME," +
                        " SHORT_NAME," +
                        " CATE_FLAG," +
                        " EXCLUSIVE_KEY " +
                        " FROM " + 
                        " BANK_TBL" +
                        " WHERE" +
                        " CATE_FLAG=0 ";
            if(isInCompany){
                sql = sql +
                " AND ID IN " +
                " ( SELECT DISTINCT BANK_ID" +
                "   FROM COMPANY_TBL " +
                "   WHERE BANK_ID IS NOT NULL" +
                " )";
            }
            sql = sql + " ORDER BY SHORT_NAME ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            rs = pstmt.executeQuery();

            Bank bank = null;
            while (rs.next()) {
                index = 0;
                bank = new Bank();
                bank.setId(rs.getInt(++index));
                bank.setName(rs.getString(++index));
                bank.setShortName(rs.getString(++index));
                bank.setCateFlag(rs.getInt(++index));
                bank.setExclusiveKey(rs.getInt(++index));
                
                list.add(bank);
            }
            
            return list;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getBankListForDrop");
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
                logSQLException(e, "getBankListForDrop");
                throw e;
            }
        }
    }
    
    /**
     * get count by id
     * @author xiangzq
     * @param id
     * @return
     * @throws SQLException
     */
    public int getCountByBankId(Integer id) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            // Start UOC
            String sql = 
                    " SELECT " + 
                    " COUNT(*)" +
                    " FROM " + 
                    " BANK_TBL " +
                    " WHERE" +
                    " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
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
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   bankId
     * @return  Bank
     * @throws  Exception
     */
    public Bank searchBankById(Integer bankId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Bank bankOut = null;

        try {
            // Start UOC

            String sql = " SELECT " +
                         " ID," +
                         " NAME," +
                         " SHORT_NAME," +
                         " CATE_FLAG," +
                         " EXCLUSIVE_KEY " +
                         " FROM " +
                         " BANK_TBL " +
                         " WHERE " +
                         " ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, bankId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                bankOut = new Bank();
                index = 0;
                bankOut.setId(rs.getInt(++index));
                bankOut.setName(rs.getString(++index));
                bankOut.setShortName(rs.getString(++index));
                bankOut.setCateFlag(rs.getInt(++index));
                bankOut.setExclusiveKey(rs.getInt(++index));
            }

            return bankOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchBankById");
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
                logSQLException(e, "searchBankById");
                throw e;
            }
        }
    }
    
    
    /**
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   bank
     * @return  void
     * @throws  Exception
     */
    public void createBank(Bank bank) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                         " INSERT INTO BANK_TBL (" +
                         " NAME," +
                         " SHORT_NAME," +
                         " CATE_FLAG," +
                         " CREATOR_ID," +
                         " CREATE_TIME," +
                         " MODIFIER_ID," +
                         " MODIFY_TIME" +
                         " ) VALUES(?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, bank.getName());
            pstmt.setString(++index, bank.getShortName());
            pstmt.setInt(++index, bank.getCateFlag());
            pstmt.setInt(++index, bank.getCreatorId());
            pstmt.setString(++index, bank.getCreateTime());
            pstmt.setInt(++index, bank.getModifierId());
            pstmt.setString(++index, bank.getModifyTime());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createBank");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createBank");
                throw e;
            }
        }
    }

    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   bank
     * @return  void
     * @throws  Exception
     */
    public void modifyBank(Bank bank) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
                if (exclusiveCheck) {
                    String sql = 
                        " SELECT " +
                        " EXCLUSIVE_KEY " +
                        " FROM BANK_TBL " +
                        " WHERE ID = ? " +
                        " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, bank.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != bank.getExclusiveKey()) {
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
            Integer nowExclusiveKey=bank.getExclusiveKey();
            bank.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                      " UPDATE BANK_TBL  SET " +
                      " NAME=?, " +
                      " SHORT_NAME=?," +
                      " MODIFIER_ID=?," +
                      " MODIFY_TIME=?, " +
                      " EXCLUSIVE_KEY=? " +
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);
            index = 0;
            pstmt.setString(++index, bank.getName());
            pstmt.setString(++index, bank.getShortName());
            pstmt.setInt(++index, bank.getModifierId());
            pstmt.setString(++index, bank.getModifyTime());
            pstmt.setInt(++index, bank.getExclusiveKey());
            pstmt.setInt(++index, bank.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyBank");
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
                logSQLException(e, "modifyBank");
                throw e;
            }
        }
    }
    
    
    /**
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   bank
     * @return  void
     * @throws  Exception
     */
    public void deleteBank(Bank bank) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = " SELECT " +
                             " EXCLUSIVE_KEY " +
                             " FROM " +
                             " BANK_TBL " +
                             " WHERE " +
                             " ID = ? " +
                             " AND ID>0 " +
                             " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, bank.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != bank.getExclusiveKey()) {
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
                       " BANK_TBL " +
                       " WHERE " +
                       " ID = ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, bank.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteBank");
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
                logSQLException(e, "deleteBank");
                throw e;
            }
        }
    }
    
    public int getCountByName(Bank bank) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT " +
                         " COUNT(*) " +
                         " FROM " +
                         " BANK_TBL " +
                         " WHERE " +
                         " NAME = ?" ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, bank.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByName");
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
                logSQLException(e, "getCountByName");
                throw e;
            }
        }

        return count;
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
    public int getCountByNameModify(Bank bank) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = " SELECT " +
                         " COUNT(*) " +
                         " FROM " +
                         " BANK_TBL A WHERE EXISTS " + 
                         " ( SELECT " +
                         "   NAME " +
                         "   FROM " +
                         "   BANK_TBL B " +
                         "   WHERE " +
                         "   A.ID != B.ID " +
                         "   AND B.ID =?" +
                         "  ) " + 
                         " AND A.NAME= ? " ;
            
           
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, bank.getId());
            pstmt.setString(++index, bank.getName());
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

    
   
}
