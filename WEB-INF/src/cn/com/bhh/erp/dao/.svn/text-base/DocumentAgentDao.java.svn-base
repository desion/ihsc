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

import cn.com.bhh.erp.entity.DocumentAgent;


/**
 * document_agent_tbl table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class DocumentAgentDao extends BaseDao {
    public DocumentAgentDao(Connection conn) {
        super(conn);
    }


    /**
     * delete the document company relation by
     * document id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  void
     * @throws  SQLException
     */
    public void deleteAgentByDocumentId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_AGENT_TBL " +
                    " WHERE  " +
                    " DOC_ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteAgentByDocumentId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteAgentByDocumentId");
                throw e;
            }
        }
    }

    /**
     * delete the document company relation by
     * company id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  void
     * @throws  SQLException
     */
    public void deleteAgentByCompanytId(Integer companyId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_AGENT_TBL " +
                    " WHERE  " +
                    " COMPANY_ID=?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, companyId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteAgentByCompanytId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteAgentByCompanytId");
                throw e;
            }
        }
    }
    
    
    /**
     * create the document company relation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentAgent
     * @return  void
     * @throws  Exception
     */
    public void createDocumentAgent(DocumentAgent documentAgent) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO DOC_AGENT_TBL (" +
            		  " DOC_ID," +
            		  " COMPANY_ID" +
            		  " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentAgent.getDocumentId());
            pstmt.setInt(++index, documentAgent.getCompanyId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createDocumentAgent");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createDocumentAgent");
                throw e;
            }
        }
    }

    /**
     * get the company list by the document id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @return  List<DocumentAgent>
     * @throws  SQLException
     */
    public List<DocumentAgent> searchCompanyByDocumentId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<DocumentAgent> list = new ArrayList<DocumentAgent>();
            String sql = 
                       " SELECT " +
                       " DOC_ID," +
                       " COMPANY_ID " +
                       " FROM " +
                       " DOC_AGENT_TBL " +
                       " WHERE " +
                       " DOC_ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                DocumentAgent documentAgent = new DocumentAgent();
                index = 0;
                documentAgent.setDocumentId(rs.getInt(++index));
                documentAgent.setCompanyId(rs.getInt(++index));
                list.add(documentAgent);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCompanyByDocumentId");
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
                logSQLException(e, "searchCompanyByDocumentId");
                throw e;
            }
        }
    }
    
    /**
     * get the document list of 
     * of the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentCompanyId
     * @return  List<Integer>
     * @throws  SQLException
     */
    public List<Integer> searchDocumentByCompanyId(Integer agentCompanyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Start UOC
            
            List<Integer> docIdList = new ArrayList<Integer>();
            String sql = 
                       " SELECT " +
                       " DOC_ID " +
                       " FROM " +
                       " DOC_AGENT_TBL " +
                       " WHERE " +
                       " COMPANY_ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentCompanyId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                docIdList.add(rs.getInt(++index));
            }

            return docIdList;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCompanyByDocumentId");
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
                logSQLException(e, "searchCompanyByDocumentId");
                throw e;
            }
        }
    }
    

}
