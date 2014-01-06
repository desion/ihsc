//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleResultSet;
import oracle.sql.BLOB;
import cn.com.bhh.erp.entity.Document;


/**
 * AgentCustomer table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class DocumentDao extends BaseDao {
    public DocumentDao(Connection conn) {
        super(conn);
    }


    /**
     * create one record .
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   document
     * @param   inputStream
     * @throws  SQLException, IOException
     */
    public void createDocument(Document document,InputStream inputStream) throws SQLException, IOException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            int index = 0;
            String sql = 
                    "INSERT INTO DOC_TBL (" +
                    " ID," +
                    " NAME," +
                    " TITLE," +
                    " SUMMARY," +
                    " PUBLISH_DATE," +
                    " TYPE," +
                    " DOC_FILE," +
                    " CREATOR_ID," +
                    " MODIFIER_ID " +
                    " ) VALUES(?,?,?,?,?,?,empty_blob(),?,?)";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(++index, document.getId());
            pstmt.setString(++index, document.getName());
            pstmt.setString(++index, document.getTitle());
            pstmt.setString(++index, document.getSummary());
            pstmt.setString(++index, document.getPublishDate());
            pstmt.setInt(++index, document.getDocTypeId());
            pstmt.setInt(++index, document.getCreatorID());
            pstmt.setInt(++index, document.getModifierID());
            
            pstmt.executeUpdate();
            
            index = 0;
            sql = 
                  " SELECT " +
            	  " DOC_FILE " +
            	  " FROM DOC_TBL " +
            	  " WHERE " +
            	  " ID = ? " +
            	  " FOR UPDATE" ;
            pstmt = conn.prepareStatement(sql);;  
            pstmt.setInt(++index,document.getId());;  
             rs = pstmt.executeQuery();
            
            if (rs.next()){
                 index = 0;
                 BLOB blobDocData = (BLOB)rs.getBlob(++index);
                 sql = 
                     " UPDATE " +
                     " DOC_TBL " +
                     " SET " +
                     " DOC_FILE=? " +
                     " WHERE  " +
                     " ID =? " ;
                 
                 pstmt = conn.prepareStatement(sql);     
                 BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(blobDocData.getBinaryOutputStream(0L));                
                 byte[] buffer = new byte[1024];
                 int length = -1;
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                 while((length = bufferedInputStream.read(buffer)) != -1){
                     bufferedOutputStream.write(buffer,0,length);;  
                 }
               
                 bufferedInputStream.close();
                 bufferedOutputStream.close(); 
                 
                 index = 0;
                 pstmt.setBlob(++index,blobDocData); 
                 pstmt.setInt(++index,document.getId()); 
                 pstmt.executeUpdate();;
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createDocument");
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
                logSQLException(e, "createDocument");
                throw e;
            }
        }
    }



    
    /**
     * get document list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Document>
     * @throws  Exception
     */
    public List<Document> getDocumentList(String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Document> list = new ArrayList<Document>();
            String sql = 
                       " SELECT " +
                       " A.ID," +
                       " A.NAME," +
                       " A.TITLE," +
                       " A.SUMMARY," +
                       " A.PUBLISH_DATE," +
                       " B.NAME DOC_TYPE_NAME " +
                       " FROM " +
                       " DOC_TBL A,DOC_TYPE_TBL B " +
                       " WHERE " +
                       " A.TYPE = B.ID" ;
          
            if(null != subSql && !"".equals(subSql)){
                sql = sql + subSql;
            }
            sql = sql + " ORDER BY A.PUBLISH_DATE ";
            int index = 0;      
            pstmt = conn.prepareStatement(sql);
         
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Document document = new Document();
                index = 0;
                document.setId(rs.getInt(++index));
                document.setName(rs.getString(++index));
                document.setTitle(rs.getString(++index));
                document.setSummary(rs.getString(++index));
                document.setPublishDate(rs.getString(++index));
                document.setDocTypeName(rs.getString(++index));
                list.add(document);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getDocumentList");
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
                logSQLException(e, "getDocumentList");
                throw e;
            }
        }
    }
    
    /**
     * get all the documents.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   doc
     * @return  List<Document>
     * @throws  SQLException
     */
    public List<Document> getDocumentList(Document doc,String subSql) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Document> list = new ArrayList<Document>();
            String sql = 
                       " SELECT " +
                       " A.ID," +
                       " A.NAME," +
                       " A.TITLE," +
                       " A.SUMMARY," +
                       " A.PUBLISH_DATE," +
                       " B.NAME DOC_TYPE_NAME " +
                       " FROM " +
                       " DOC_TBL A,DOC_TYPE_TBL B " +
                       " WHERE " +
                       " A.TYPE = B.ID" ;
            StringBuffer dyaSql = new StringBuffer(sql);
            
            if(null != subSql && !"".equals(subSql)){
                dyaSql.append(subSql);
            }
            
            if(null != doc.getProductCategoryId()){
                dyaSql.append(" AND A.ID IN" +
                		      "( " +
                		      " SELECT " +
                		      " DOC_ID " +
                		      " FROM DOC_PROD_CATE_TBL " +
                		      " WHERE " +
                		      " PROD_CATE_ID =? " +
                		      " )" );
             }
            
            if(null != doc.getProductId()){
                dyaSql.append(" AND A.ID IN" +
                              "( " +
                              " SELECT " +
                              " DOC_ID " +
                              " FROM DOC_PRODUCT_TBL " +
                              " WHERE " +
                              " PRODUCT_ID =? " +
                              " )" );
             }
            
            if(null != doc.getDocTypeId()){
                dyaSql.append(" AND A.TYPE = ?" );
            }
            if(null != doc.getName() &&  !"".equals(doc.getName().trim())){
                dyaSql.append(" AND A.NAME LIKE ?" );
            }
            
            if(null != doc.getTitle() &&  !"".equals(doc.getTitle().trim())){
                dyaSql.append(" AND A.TITLE LIKE ?" );
            }
            
            if(null != doc.getSummary() &&  !"".equals(doc.getSummary().trim())){
                dyaSql.append(" AND A.SUMMARY LIKE ?" );
            }
            
            if(null != doc.getPublishDate() &&  !"".equals(doc.getPublishDate().trim())){
                dyaSql.append(" AND A.PUBLISH_DATE = ?" );
            }
            
            dyaSql.append(" ORDER BY A.PUBLISH_DATE ");
            
            int index = 0;      
            pstmt = conn.prepareStatement(dyaSql.toString());
            
            if(null != doc.getProductCategoryId()){
                pstmt.setInt(++index, doc.getProductCategoryId());
            }
            
            if(null != doc.getProductId()){
                pstmt.setInt(++index, doc.getProductId());
            }
            if(null != doc.getDocTypeId()){
                pstmt.setInt(++index, doc.getDocTypeId());
            }
            if(null != doc.getName() &&  !"".equals(doc.getName().trim())){
                pstmt.setString(++index, doc.getName()+"%");
            }
            
            if(null != doc.getTitle() &&  !"".equals(doc.getTitle().trim())){
                pstmt.setString(++index, doc.getTitle()+"%");
            }
            
            if(null != doc.getSummary() &&  !"".equals(doc.getSummary().trim())){
                pstmt.setString(++index, doc.getSummary()+"%");
            }
            
            if(null != doc.getPublishDate() &&  !"".equals(doc.getPublishDate().trim())){
                pstmt.setString(++index, doc.getPublishDate());
            }
           
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Document document = new Document();
                index = 0;
                document.setId(rs.getInt(++index));
                document.setName(rs.getString(++index));
                document.setTitle(rs.getString(++index));
                document.setSummary(rs.getString(++index));
                document.setPublishDate(rs.getString(++index));
                document.setDocTypeName(rs.getString(++index));
                list.add(document);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getDocumentList");
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
                logSQLException(e, "getDocumentList");
                throw e;
            }
        }
    }
    
    /**
     * get the binary data of the product document.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   productDoc
     * @return  Document
     * @throws  Exception
     */
    public Document searchDocumentDataById(Document doc) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            Document productDocumentOut = null;
            String sql = 
                       " SELECT " +
                       " DOC_FILE" +
                       " FROM " +
                       " DOC_TBL " +
                       " WHERE " +
                       " ID =? ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, doc.getId());
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                productDocumentOut = new Document();
                index = 0;
                productDocumentOut.setDocFile(((OracleResultSet)rs).getBLOB(++index));                
            }

            return productDocumentOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchDocumentDataByProductId");
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
                logSQLException(e, "searchDocumentDataByProductId");
                throw e;
            }
        }
    }
    
    /**
     * get the count of document
     * by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   docId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByDocId(Integer docId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*)" +
                       " FROM " +
                       " DOC_TBL " +
                       " WHERE " +
                       " ID =? ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, docId);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);               
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByDocId");
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
                logSQLException(e, "getCountByDocId");
                throw e;
            }
        }
    }
    
    
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   doc
     * @return  void
     * @throws  Exception
     */
    public void deleteDocumentById(Integer documentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " DOC_TBL " +
                       " WHERE " +
                       " ID = ? ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            pstmt.executeUpdate();
     

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteDocumentById");
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
                logSQLException(e, "deleteDocumentById");
                throw e;
            }
        }
    }
    
    /**
     * get the document by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   documentId
     * @return  Document
     * @throws  Exception
     */
    public Document getDocumetById(Integer documentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            Document document = null;
            String sql = 
                       " SELECT" +
                       " A.ID, " +
                       " A.NAME," +
                       " B.NAME TYPE_NAME," +
                       " A.TITLE," +
                       " A.SUMMARY," +
                       " A.PUBLISH_DATE " +
                       " FROM " +
                       " DOC_TBL A,DOC_TYPE_TBL B " +
                       " WHERE " +
                       " A.TYPE = B.ID " +
                       " AND" +
                       " A.ID =? ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, documentId);
            
            rs = pstmt.executeQuery();
            if (rs.next()) {
                document = new Document();
                index = 0;
                document.setId(rs.getInt(++index));
                document.setName(rs.getString(++index));
                document.setDocTypeName(rs.getString(++index));     
                document.setTitle(rs.getString(++index));
                document.setSummary(rs.getString(++index));
                document.setPublishDate(rs.getString(++index));
                 
            }

            return document;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getDocumetById");
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
                logSQLException(e, "getDocumetById");
                throw e;
            }
        }
    }

}
