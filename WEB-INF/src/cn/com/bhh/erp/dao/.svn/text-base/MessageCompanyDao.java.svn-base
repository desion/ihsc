//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   09/01/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.MessageCompany;


public class MessageCompanyDao extends BaseDao {
    public MessageCompanyDao(Connection conn) {
        super(conn);
    }

    /**
     * add the company relation to message_company_tbl
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   messageCompany
     * @throws  SQLException
     */
    public void createMessageCompany(MessageCompany messageCompany) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                     " INSERT INTO MESSAGE_COMPANY_TBL (" +
                     " MESSAGE_ID," +
                     " COMPANY_ID" +
                     " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, messageCompany.getMessageId());
            pstmt.setInt(++index, messageCompany.getCompanyId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createMessageCompany");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createMessageCompany");
                throw e;
            }
        }
    }

   

   /**
    * delete the company message relation
    * by the company id.
    * @auther  xiangzq
    * @version 1.0
    * @since   1.0
    * @param   companyId
    * @throws  SQLException
    */
    public void deleteMessageGroupByMessageId(Integer messageId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                     " DELETE " +
                     " FROM " +
                     " MESSAGE_COMPANY_TBL " +
                     " WHERE " +
                     " MESSAGE_ID=? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, messageId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteMessageGroupByMessageId");
            throw e;
        } finally {
            try {

                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteMessageGroupByMessageId");
                throw e;
            }
        }
    }
    
    /**
     * search the company list by the message id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   messageId
     * @return  List<MessageCompany>
     * @throws  SQLException
     */
    public List<MessageCompany> searchCompanyByMessageId(Integer messageId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            
            List<MessageCompany> list = new ArrayList<MessageCompany>();
            String sql = 
                       " SELECT " +
                       " MESSAGE_ID," +
                       " COMPANY_ID " +
                       " FROM " +
                       " MESSAGE_COMPANY_TBL " +
                       " WHERE " +
                       " MESSAGE_ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, messageId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                MessageCompany messageCompany = new MessageCompany();
                index = 0;
                messageCompany.setMessageId(rs.getInt(++index));
                messageCompany.setCompanyId(rs.getInt(++index));

                list.add(messageCompany);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCompanyByMessageId");
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
                logSQLException(e, "searchCompanyByMessageId");
                throw e;
            }
        }
    }

   
}
