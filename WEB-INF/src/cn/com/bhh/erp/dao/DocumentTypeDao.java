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

import cn.com.bhh.erp.entity.DocumentType;

public class DocumentTypeDao extends BaseDao{

    public DocumentTypeDao(Connection conn){
        super(conn);
    }

    
    public List<DocumentType> getAllDocumentType() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            List<DocumentType> list = new ArrayList<DocumentType>();
            String sql = "SELECT " +
            		    " ID," +
            		    " NAME" +
            		    " FROM" +
            		    " DOC_TYPE_TBL" +
            		    " ORDER BY ID";
            pstmt = conn.prepareStatement(sql);
            DocumentType documentType = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                documentType = new DocumentType();
                index = 0;
                documentType.setId(rs.getInt(++index));
                documentType.setName(rs.getString(++index));
                list.add(documentType);
            }
            return list;
        } catch(SQLException e){
            logSQLException(e, "getAllDocumentType");
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
                logSQLException(e, "getAllDocumentType");
                throw e;
            }
        }
    }
}
