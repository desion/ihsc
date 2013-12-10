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

import cn.com.bhh.erp.entity.DocumentProduct;

/**
 * 
 *
 */
public class DocumentProductDao extends BaseDao{

    /**
     * @param conn
     */
    public DocumentProductDao(Connection conn) {
        super(conn);
    }



    /**
     * delete one product document relation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentProduct
     * @throws  SQLException
     */
    public void deleteProductDocumentByDocId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_PRODUCT_TBL " +
                    " WHERE  " +
                    " DOC_ID= ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index,documentId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteProductDocumentByDocId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteProductDocumentByDocId");
                throw e;
            }
        }
    }


    /**
     * delete the document product relation
     * by the product id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   productId
     * @return  void
     * @throws  SQLException
     */
    public void deleteDocumentProductByProductId(Integer productId) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE " +
                    " FROM " +
                    " DOC_PRODUCT_TBL " +
                    " WHERE  " +
                    " PRODUCT_ID= ? " ;
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index,productId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteDocumentProductByProductId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteDocumentProductByProductId");
                throw e;
            }
        }
    }
    
    /**
     * create the company document relation.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentProduct
     * @throws  SQLException
     */
    public void createProductDocument(DocumentProduct documentProduct) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO DOC_PRODUCT_TBL (" +
                      " DOC_ID," +
                      " PRODUCT_ID" +
                      " ) VALUES(?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentProduct.getDocumentId());
            pstmt.setInt(++index, documentProduct.getProductId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createProductDocument");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createProductDocument");
                throw e;
            }
        }
    }
    
    
    /**
     * get the document agent product List.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @return  List<Integer>
     * @throws  SQLException
     */
    public List<Integer> getAgentProductListByDocumentId(Integer documentId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> productIdList = new ArrayList<Integer>();
        try {
            // Start UOC
            String sql = 
                      " SELECT " +
                      " PRODUCT_ID " +
                      " FROM " +
                      " DOC_PRODUCT_TBL" +
                      " WHERE" +
                      " DOC_ID=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            rs = pstmt.executeQuery();
            while(rs.next()){
                index = 0;
                productIdList.add(rs.getInt(++index));
            }
            
            return productIdList;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getProductListByDocumentId");
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
                logSQLException(e, "getProductListByDocumentId");
                throw e;
            }
        }
    }

}
