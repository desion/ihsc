/**
 * 
 */
package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.DocumentProductCategory;

/**
 * 
 *
 */
public class DocumentProductCategoryDao extends BaseDao{

    /**
     * @param conn
     */
    public DocumentProductCategoryDao(Connection conn) {
        super(conn);
    }



    /**
     * delete one document product category relation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @throws  SQLException
     */
    public void deleteDocumentProductCategoryByDocId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_PROD_CATE_TBL " +
                    " WHERE  " +
                    " DOC_ID= ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index,documentId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteDocumentProductCategoryByDocId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteDocumentProductCategoryByDocId");
                throw e;
            }
        }
    }

    /**
     * delete the document product category
     * relation by product category id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   productCategoryId
     * @return  void
     * @throws  Exception
     */
    public void deleteDocumentProductCategoryByProCateId(Integer productCategoryId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_PROD_CATE_TBL " +
                    " WHERE  " +
                    " PROD_CATE_ID= ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index,productCategoryId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteDocumentProductCategoryByProCateId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteDocumentProductCategoryByProCateId");
                throw e;
            }
        }
    }

    
    /**
     * create the document product category relation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentProductCategory
     * @throws  SQLException
     */
    public void createDocumentProductCategory(DocumentProductCategory documentProductCategory) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO DOC_PROD_CATE_TBL (" +
                      " DOC_ID," +
                      " PROD_CATE_ID" +
                      " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentProductCategory.getDocumentId());
            pstmt.setInt(++index, documentProductCategory.getProductCategoryId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createDocumentProductCategory");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createDocumentProductCategory");
                throw e;
            }
        }
    }
    
    
    /**
     * get the document agent product category List.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @return  List<Integer>
     * @throws  SQLException
     */
    public List<Integer> getAgentProductCategoryListByDocumentId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> productCategoryIdList = new ArrayList<Integer>();
        try {
            // Start UOC
            String sql = 
                      " SELECT " +
                      " PROD_CATE_ID " +
                      " FROM " +
                      " DOC_PROD_CATE_TBL" +
                      " WHERE" +
                      " DOC_ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                index = 0;
                productCategoryIdList.add(rs.getInt(++index));
            }
            
            return productCategoryIdList;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getProductCategoryListByDocumentId");
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
                logSQLException(e, "getProductCategoryListByDocumentId");
                throw e;
            }
        }
    }

}
