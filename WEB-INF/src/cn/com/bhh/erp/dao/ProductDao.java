package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

/**
 * Class of Product Dao.
 * @author desion
 * @version 1.0
 * @since 1.0
 */
public class ProductDao extends BaseDao{

    public ProductDao(Connection conn){
        super(conn);
    }

    /**
     * search all product list.
     * @author sunyx
     * @since 1.0
     * @param 
     * @return List&ltProduct&gt
     * @throws SQLException
     */
    public List<Product> searchAll(Product product, User loginUser,int intBegin,int intEnd) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = "SELECT"
            		+ " ID,"
                    + " TITLE," 
                    + " ZONE,"
                    + " PRICE,"
                    + " VALUE,"
                    + " OPERATOR,"
                    + " STATUS,"
                    + " OP_USERID,"
                    + " OP_DATE,"
                    + " DELIST_REASON,"
                    + " PROVINCE_NAME,"
                    + " EXCLUSIVE_KEY,"
                    + " USER_NAME"
                    + " FROM"
                    + "( SELECT ROWNUM NO," 
                    + "  T.* " 
                    + "  FROM"
                    + "  (SELECT " 
                + " A.ID,"
                + " A.TITLE," 
                + " A.ZONE,"
                + " A.PRICE,"
                + " A.VALUE,"
                + " A.OPERATOR,"
                + " A.STATUS,"
                + " A.OP_USERID,"
                + " A.OP_DATE,"
                + " A.DELIST_REASON,"
                + " B.NAME PROVINCE_NAME,"
                + " A.EXCLUSIVE_KEY,"
                + " C.FULLNAME USER_NAME"
                + " FROM PRODUCT_TBL A "
                + " LEFT OUTER JOIN USER_TBL C ON C.ID = A.OP_USERID" 
                + " INNER JOIN PROVINCE_TBL B  ON A.ZONE = B.ID"
                + " WHERE 0 = 0 ";
            	
            
            StringBuffer sb = new StringBuffer(sql);
            int index = 0;
            if (product.getStatus()!=null) {
                sb.append(" AND A.STATUS = ? ");
            }
            if (product.getValue()!=null) {
                sb.append(" AND A.VALUE = ? ");
            }
            if (product.getZone()!=null) {
                sb.append(" AND A.ZONE = ? ");
            }
            if (product.getId()!=null) {
                sb.append(" AND A.ID = ? ");
            }
            
            sb.append(" )T ");
            sb.append(" WHERE ROWNUM<=? ");
            sb.append(" ) WHERE NO>? ");
            pstmt = conn.prepareStatement(sb.toString());
            
            if (product.getStatus()!=null) {
                pstmt.setInt(++index, product.getStatus());
            }
            if (product.getValue()!=null) {
                pstmt.setInt(++index, product.getValue());
            }
            if (product.getZone()!=null) {
                pstmt.setInt(++index, product.getZone());
            }
            if (product.getId()!=null) {
                pstmt.setInt(++index, product.getId());
            }
            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
            
            Product productOut = null;
            index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                productOut = new Product();
                index = 0;
                productOut.setId(rs.getInt(++index));
                productOut.setTitle(rs.getString(++index));
                productOut.setZone(rs.getInt(++index));
                productOut.setPrice(rs.getFloat(++index));
                productOut.setValue(rs.getInt(++index));
                productOut.setOperator(rs.getInt(++index));
                productOut.setStatus(rs.getInt(++index));
                productOut.setOperateUser(rs.getInt(++index));
                productOut.setOperateTime(rs.getString(++index));
                productOut.setDelistReason(rs.getString(++index));
                productOut.setZoneName(rs.getString(++index));
                productOut.setExclusiveKey(rs.getInt(++index));
                productOut.setOpUserName(rs.getString(++index));

                list.add(productOut);
            }
            return list;
            
            // End UOC
        } catch(SQLException e){
            logSQLException(e, "searchAll");
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
     * search product information by product ID.
     * @author desion
     * @since 1.0
     * @param model product model
     * @return product information
     * @throws SQLException
     */
    public Product searchByID(int pid) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            Product product = new Product();
            String sql = " SELECT "
                + "  ID, "
                + "  EXCLUSIVE_KEY "
                + " FROM "
                + "  PRODUCT_TBL "
                + " WHERE "
                + "  PRODUCT_TBL.ID = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, pid);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                product.setId(rs.getInt(++index));
                product.setExclusiveKey(rs.getInt(++index));
            }

            return product;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByID");
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
                logSQLException(e, "searchByModel");
                throw e;
            }
        }
    }


    
    /**
     * insert the product information.
     * @author luyan
     * @since 1.0
     * @param product product information
     * @throws SQLException
     */
    public void insert(Product product) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = " INSERT INTO PRODUCT_TBL ( "
            		+ " ID,"
                    + " TITLE," 
                    + " ZONE,"
                    + " PRICE,"
                    + " STATUS,"
                    + " OP_USERID,"
                    + " OP_DATE,"
                    + " DELIST_REASON"
                + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            
            pstmt.setInt(++index, product.getId());

            pstmt.setString(++index, product.getTitle().trim());
            pstmt.setInt(++index, product.getZone());
            pstmt.setFloat(++index, product.getPrice());
            pstmt.setInt(++index, product.getStatus());
            pstmt.setInt(++index, product.getOperateUser());
            pstmt.setString(++index, product.getOperateTime().trim());
            pstmt.setString(++index, product.getDelistReason().trim());
            
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert");
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
                logSQLException(e, "insert");
                throw e;
            }
        }
    }

    /**
     * modify product information by product ID.
     * @author desion
     * @since 1.0
     * @param productIn product information
     * @throws Exception
     */
    public void modifyById(Product productIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productIn.getExclusiveKey()) {
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

            String sql = " UPDATE "
                + "  PRODUCT_TBL"
                + " SET "
                + " PRICE = ?,"
                + " STATUS = ?,"
                + " OP_USERID = ?,"
                + " OP_DATE = ?,"
                + " DELIST_REASON = ?,"
                + " EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE " 
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setFloat(++index, productIn.getPrice());
            pstmt.setInt(++index, productIn.getStatus());
            pstmt.setInt(++index, productIn.getOperateUser());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setString(++index, productIn.getDelistReason());
            pstmt.setInt(++index, productIn.getId());

            rs = pstmt.executeQuery();

            return;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyById");
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
                logSQLException(e, "modifyById");
                throw e;
            }
        }
    }
    
    /**
     * modify product information by product ID.
     * @author desion
     * @since 1.0
     * @param productIn product information
     * @throws Exception
     */
    public void modifyStatusById(Product productIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productIn.getExclusiveKey()) {
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

            String sql = " UPDATE "
                + "  PRODUCT_TBL"
                + " SET "
                + " STATUS = ?,"
                + " OP_USERID = ?,"
                + " OP_DATE = ?,"
                + " DELIST_REASON = ?,"
                + " EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE " 
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setInt(++index, productIn.getStatus());
            pstmt.setInt(++index, productIn.getOperateUser());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setString(++index, productIn.getDelistReason());
            pstmt.setInt(++index, productIn.getId());

            rs = pstmt.executeQuery();

            return;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyStatusById");
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
                logSQLException(e, "modifyStatusById");
                throw e;
            }
        }
    }

    /**
     * modify product information by product ID for business delete.
     * @author luyan
     * @since 1.0
     * @param productIn product information
     * @param delFlg delete flag
     * @throws Exception
     */
    public void modifyByIdFd(Product productIn, Integer delFlg) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productIn.getExclusiveKey()) {
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

            String sql = " UPDATE "
                + "  PRODUCT_TBL"
                + " SET "
                + "  DELETED = ?, "
                + "  MODIFY_TIME = ?, "
                + "  MODIFIER_ID = ?, "
                + "  EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE " 
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setInt(++index, delFlg);
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, productIn.getId());

            rs = pstmt.executeQuery();

            return;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyByIdFd");
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
                logSQLException(e, "modifyByIdFd");
                throw e;
            }
        }
    }

    /**
     * modify product information by product category ID for business delete/recover.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @param delFlg delete flag
     * @throws Exception
     */
    public void modifyByPcIdFd(ProductCategory productCategory) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            String sql = " UPDATE "
                + "  PRODUCT_TBL"
                + " SET "
                + "  DELETED = ?, "
                + "  MODIFY_TIME = ?, "
                + "  MODIFIER_ID = ?, "
                + "  EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE " 
                + "  PRODUCT_CATEGORY_ID IN ( "
                + "    SELECT ID "
                + "    FROM PRODUCT_CATEGORY_TBL "
                + "    WHERE ID = ? "
                + "    OR ID IN ( "
                + "      SELECT ID "
                + "      FROM PRODUCT_CATEGORY_TBL "
                + "      WHERE PARENT_ID = ? ) "
                + "    OR ID IN ( "
                + "      SELECT ID "
                + "      FROM PRODUCT_CATEGORY_TBL "
                + "      WHERE PARENT_ID IN ( "
                + "        SELECT ID "
                + "        FROM PRODUCT_CATEGORY_TBL "
                + "        WHERE PARENT_ID = ? ))) ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setInt(++index, productCategory.getDeleted());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, productCategory.getModifierId());
            pstmt.setInt(++index, productCategory.getId());
            pstmt.setInt(++index, productCategory.getId());
            pstmt.setInt(++index, productCategory.getId());

            rs = pstmt.executeQuery();

            return;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyByPcIdFd");
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
                logSQLException(e, "modifyByPcIdFd");
                throw e;
            }
        }
    }

    /**
     * delete product information by product ID.
     * @author luyan
     * @since 1.0
     * @param productIn product information
     * @throws Exception
     */
    public void deleteById(Product productIn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC

            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productIn.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productIn.getExclusiveKey()) {
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

            String sql = " DELETE FROM "
                + "  PRODUCT_TBL"
                + " WHERE " 
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setInt(++index, productIn.getId());

            rs = pstmt.executeQuery();

            return;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteById");
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
                logSQLException(e, "deleteById");
                throw e;
            }
        }
    }
    
    /**
     * get count by producer company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   producerId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByProducerId(Integer producerId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " PRODUCT_TBL " +
                       " WHERE  " +
                       " PRODUCER_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, producerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProducerId");
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
                logSQLException(e, "getCountByProducerId");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get count by producer company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   producerId
     * @return  int
     * @throws  SQLException
     */
    public int getProducerCount(Product product,User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " PRODUCT_TBL " +
                       " WHERE  " +
                       " 0 = 0 ";
            StringBuffer sb = new StringBuffer(sql);
            int index = 0;
            if (product.getStatus()!=null) {
                sb.append(" AND PRODUCT_TBL.STATUS = ? ");
            }
            if (product.getValue()!=null) {
                sb.append(" AND PRODUCT_TBL.VALUE = ? ");
            }
            if (product.getZone()!=null) {
                sb.append(" AND PRODUCT_TBL.ZONE = ? ");
            }
            if (product.getId()!=null) {
                sb.append(" AND PRODUCT_TBL.ID = ? ");
            }
            
            pstmt = conn.prepareStatement(sb.toString());
            
            if (product.getStatus()!=null) {
                pstmt.setInt(++index, product.getStatus());
            }
            if (product.getValue()!=null) {
                pstmt.setInt(++index, product.getValue());
            }
            if (product.getZone()!=null) {
                pstmt.setInt(++index, product.getZone());
            }
            if (product.getId()!=null) {
                pstmt.setInt(++index, product.getId());
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getProducerCount");
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
                logSQLException(e, "getProducerCount");
                throw e;
            }
        }

        return count;
    }
}
