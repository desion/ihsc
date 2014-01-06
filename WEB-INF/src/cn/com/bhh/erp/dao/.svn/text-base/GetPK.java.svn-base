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


/**
 * Get sequence key
 * @author fenghy
 *
 */
public class GetPK extends BaseDao {
    public GetPK(Connection conn) {
        super(conn);
    }
    /**
     * id generator
     * @auther  fenghy
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  Integer
     * @throws  Exception
     */
    public Integer getPK(String seqName) throws Exception {
        Integer sqlNumber = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = 
                      "SELECT " +
                       seqName + 
                       ".NEXTVAL FROM DUAL";
            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                sqlNumber = new Integer(rs.getInt(++index));
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getPK");
            e.printStackTrace();
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
                logSQLException(e, "getPK");
                e.printStackTrace();
                throw e;
            }
        }

        return sqlNumber;
    }
}
