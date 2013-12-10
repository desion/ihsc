//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/06
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
import cn.com.bhh.erp.entity.Message;
import cn.com.bhh.erp.entity.User;

public class MessageDao extends BaseDao{

    public MessageDao(Connection conn) {
        super(conn);
    }

    /**
     * get count by  company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   companyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCompanyId(Integer companyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " MESSAGE_COMPANY_TBL " +
                       " WHERE  " +
                       " COMPANY_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, companyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

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

        return count;
    }
    
    /**
     * delete a message by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   message
     * @throws  Exception
     */
    public void deleteMessage(Message message) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = 
                          " SELECT " +
                          " EXCLUSIVE_KEY " +
                          " FROM MESSAGE_TBL " +
                          " WHERE ID = ? " +
                          " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, message.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != message.getExclusiveKey()) {
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
                       " MESSAGE_TBL " +
                       " WHERE " +
                       " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, message.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteMessage");
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
                logSQLException(e, "deleteMessage");
                throw e;
            }
        }
    }
    
    /**
     * create a message
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   message
     * @throws  SQLException
     */
    public void createMessage(Message message) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO MESSAGE_TBL (" +
                      " ID," +
                      " CONTENT," +
                      " CREATOR_ID," +
                      " CREATE_TIME," +
                      " MODIFIER_ID," +
                      " MODIFY_TIME" +
                      " ) VALUES(?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, message.getId());
            pstmt.setString(++index, message.getContent());
            pstmt.setInt(++index, message.getCreatorId());
            pstmt.setString(++index, message.getCreateTime());
            pstmt.setInt(++index, message.getModifierId());
            pstmt.setString(++index, message.getModifyTime());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createMessage");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createMessage");
                throw e;
            }
        }
    }
    
     /**
      * get all the message infomation.
      * @auther  xiangzq
      * @version 1.0
      * @since   1.0
      * @return  List<Message>
      * @throws  SQLException
      */
    public List<Message> searchAllMessage(User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Message>list = new ArrayList<Message>();
            String sql = 
                      " SELECT " +
                      " ID," +
                      " CONTENT," +
                      " EXCLUSIVE_KEY " +
                      " FROM MESSAGE_TBL " ;
            if(!loginUser.hasPermission("BS009_31")){
                      sql = sql +
                      " WHERE ID IN( " +
                      "   SELECT " +
                      "   MESSAGE_ID " +
                      "     FROM  " +
                      "     MESSAGE_COMPANY_TBL" +
                      "    WHERE COMPANY_ID=?" +
                      "  )" ;
                
            }
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            if(!loginUser.hasPermission("BS009_31")){
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Message mesage = new Message();
                index = 0;
                mesage.setId(rs.getInt(++index));
                mesage.setContent(rs.getString(++index));
                mesage.setExclusiveKey(rs.getInt(++index));
                list.add(mesage);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchAllMessage");
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
                logSQLException(e, "searchAllMessage");
                throw e;
            }
        }
    }
    
    /**
     * list the scroll message for different company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  List<Message>
     * @throws  Exception
     */
    public List<Message> searchMessageByCompanyId(Integer companyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Message>list = new ArrayList<Message>();
            String sql = 
                       " SELECT  " +
                       " B.ID, " +
                       " B.CONTENT   " +
                       " FROM  MESSAGE_COMPANY_TBL A,MESSAGE_TBL B " +
                       " WHERE " +
                       " A.MESSAGE_ID=B.ID " +
                       " AND A.COMPANY_ID=? ";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index,companyId);
            rs = pstmt.executeQuery();
           
            while (rs.next()) {
                Message mesage = new Message();
                index = 0;
                mesage.setId(rs.getInt(++index));
                mesage.setContent(rs.getString(++index));
                list.add(mesage);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchMessageByCompanyId");
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
                logSQLException(e, "searchMessageByCompanyId");
                throw e;
            }
        }
    }

    /**
     * search message by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   message
     * @return  Message
     * @throws  Exception
     */
    public Message searchMessageById(Message message) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Message messageOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM " +
                            " MESSAGE_TBL " +
                            " WHERE ID = ? " ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, message.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != message.getExclusiveKey()) {
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
                       " CONTENT," +
                       " EXCLUSIVE_KEY " +
                       " FROM " +
                       " MESSAGE_TBL " +
                       " WHERE " +
                       " ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, message.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                messageOut = new Message();
                index = 0;
                messageOut.setId(rs.getInt(++index));
                messageOut.setContent(rs.getString(++index));
                messageOut.setExclusiveKey(rs.getInt(++index));
            }

            return messageOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchMessageById");
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
                logSQLException(e, "searchMessageById");
                throw e;
            }
        }
    }
    
    /**
     * modify a message.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   message
     * @return  void
     * @throws  Exception
     */
    public void modifyMessage(Message message) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
                if (exclusiveCheck) {
                    String sql = 
                        " SELECT " +
                        " EXCLUSIVE_KEY " +
                        " FROM MESSAGE_TBL " +
                        " WHERE ID = ? " +
                        " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, message.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != message.getExclusiveKey()) {
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
            Integer nowExclusiveKey=message.getExclusiveKey();
            message.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                      " UPDATE MESSAGE_TBL  SET " +
                      " CONTENT=?, " +
                      " MODIFIER_ID=?," +
                      " MODIFY_TIME=?, " +
                      " EXCLUSIVE_KEY=? " +
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);
            index = 0;
            pstmt.setString(++index, message.getContent());
            pstmt.setInt(++index, message.getModifierId());
            pstmt.setString(++index, message.getModifyTime());
            pstmt.setInt(++index, message.getExclusiveKey());
            pstmt.setInt(++index, message.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyMessage");
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
                logSQLException(e, "modifyMessage");
                throw e;
            }
        }
    }
}
