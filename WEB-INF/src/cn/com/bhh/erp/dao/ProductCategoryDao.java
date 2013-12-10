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

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

/**
 * Class of Product Category Dao.
 * @author luyan
 * @version 1.0
 * @since 1.0
 */
public class ProductCategoryDao extends BaseDao {

    /**
     * construction method.
     * @author luyan
     * @since 1.0
     * @param conn connection
     */
    public ProductCategoryDao(Connection conn) {
        super(conn);
    }

    /**
     * insert the product category information.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @throws SQLException
     */
    public void insert(ProductCategory productCategory) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            String sql = "INSERT INTO PRODUCT_CATEGORY_TBL ("
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID "
                + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, productCategory.getName().trim());
            pstmt.setString(2, productCategory.getDescription().trim());
            pstmt.setInt(3, productCategory.getCateLevel());
            pstmt.setBigDecimal(4, productCategory.getParentId());
            pstmt.setString(5, TimeUtil.getNow());
            pstmt.setInt(6, productCategory.getCreatorId());
            pstmt.setString(7, TimeUtil.getNow());
            pstmt.setInt(8, productCategory.getCreatorId());
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
     * update product category information by ID.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @return new exclusive key
     * @throws Exception
     */
    public Integer modifyById(ProductCategory productCategory) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_CATEGORY_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productCategory.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productCategory.getExclusiveKey()) {
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
                + "  PRODUCT_CATEGORY_TBL "
                + " SET "
                + "  NAME = ?, "
                + "  DESCRIPTION = ?, "
                + "  MODIFY_TIME = ?, "
                + "  MODIFIER_ID = ?, "
                + "  EXCLUSIVE_KEY = ? "
                + " WHERE "
                + "  ID = ? ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, productCategory.getName().trim());
            pstmt.setString(++index, productCategory.getDescription().trim());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, productCategory.getModifierId());
            pstmt.setInt(++index, productCategory.getExclusiveKey() + 1);
            pstmt.setInt(++index, productCategory.getId());

            pstmt.executeUpdate();

            return productCategory.getExclusiveKey() + 1;
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
     * update product category's delete flag.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @throws Exception
     */
    public void modifyForDelete(ProductCategory productCategory) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_CATEGORY_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productCategory.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productCategory.getExclusiveKey()) {
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
                + "  PRODUCT_CATEGORY_TBL "
                + " SET "
                + "  DELETED = ?, "
                + "  MODIFY_TIME = ?, "
                + "  MODIFIER_ID = ?, "
                + "  EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE "
                + "  ID = ? "
                + "  OR ID IN ( "
                + "    SELECT ID "
                + "    FROM PRODUCT_CATEGORY_TBL "
                + "    WHERE PARENT_ID = ? ) "
                + "  OR ID IN ( "
                + "    SELECT ID "
                + "    FROM PRODUCT_CATEGORY_TBL "
                + "    WHERE PARENT_ID IN ( "
                + "      SELECT ID "
                + "      FROM PRODUCT_CATEGORY_TBL "
                + "      WHERE PARENT_ID = ? )) ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, productCategory.getDeleted());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, productCategory.getModifierId());
            pstmt.setInt(++index, productCategory.getId());
            pstmt.setInt(++index, productCategory.getId());
            pstmt.setInt(++index, productCategory.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyForDelete");
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
                logSQLException(e, "modifyForDelete");
                throw e;
            }
        }
    }

    /**
     * delete product category information by ID.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @throws Exception
     */
    public void deleteById(ProductCategory productCategory) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            int index = 0;
            // Start UOC
            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_CATEGORY_TBL "
                    + " WHERE "
                    + "  ID = ? "
                    + " FOR UPDATE NOWAIT ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productCategory.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productCategory.getExclusiveKey()) {
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
                + "  PRODUCT_CATEGORY_TBL "
                + " WHERE "
                + "  ID = ? ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setInt(++index, productCategory.getId());

            pstmt.executeUpdate();

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
     * select product category information by ID.
     * @author luyan
     * @since 1.0
     * @param productCategoryId product category ID
     * @return product category information
     * @throws SQLException
     */
    public ProductCategory searchById(Integer productCategoryId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            ProductCategory productCategory = new ProductCategory();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productCategoryId);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                productCategory.setId(rs.getInt(++index));
                productCategory.setName(rs.getString(++index));
                productCategory.setDescription(rs.getString(++index));
                productCategory.setCateLevel(rs.getInt(++index));
                productCategory.setParentId(rs.getBigDecimal(++index));
                productCategory.setDeleted(rs.getInt(++index));
                productCategory.setCreateTime(rs.getString(++index));
                productCategory.setCreatorId(rs.getInt(++index));
                productCategory.setModifyTime(rs.getString(++index));
                productCategory.setModifierId(rs.getInt(++index));
                productCategory.setExclusiveKey(rs.getInt(++index));
            }

            return productCategory;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchById");
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
                logSQLException(e, "searchById");
                throw e;
            }
        }
    }
    
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  String
     * @throws  Exception
     */
    public String getTopProCateNameByModel(String model) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String topProCateName = null;
            String sql = 
            " SELECT NAME  "
            + "  FROM PRODUCT_CATEGORY_TBL  "
            + "  WHERE ID = "
            + "  ( "
            + "      SELECT PARENT_ID "
            + "      FROM PRODUCT_CATEGORY_TBL "
            + "      WHERE ID =  "
            + "      ( "
            + "          SELECT PARENT_ID FROM  "
            + "          PRODUCT_CATEGORY_TBL  "
            + "          WHERE ID = "
            + "           ( "
            + "             SELECT    "
            + "            PRODUCT_CATEGORY_ID "
            + "             FROM PRODUCT_TBL  "
            + "             WHERE MODEL = ? "
            + "           ) AND  CATE_LEVEL =3 "
            + "       ) AND CATE_LEVEL =2 "
            + "    ) AND CATE_LEVEL =1 ";
             
             
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, model);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                topProCateName = rs.getString(++index);
            }

            return topProCateName;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getTopProCateNameByModel");
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
                logSQLException(e, "getTopProCateNameByModel");
                throw e;
            }
        }
    }
    
    
    /**
     * get product category count  by ID.
     * @author xiangzq
     * @since 1.0
     * @param productCategoryId product category ID
     * @return product category count
     * @throws SQLException
     */
    public int getCountByProductCategoryId(Integer productCategoryId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            // Start UOC
            String sql = 
                       " SELECT "+
                       " COUNT(*) "+
                       " FROM "+
                       " PRODUCT_CATEGORY_TBL "+
                       " WHERE "+
                       " ID = ? " +
                       " AND DELETED = 0";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productCategoryId);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
               index=0;
               count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProductCategoryId");
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
                logSQLException(e, "getCountByProductCategoryId");
                throw e;
            }
        }
    }

    /**
     * select product category information by ID for update.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @return product category information
     * @throws Exception
     */
    public ProductCategory searchByIdFm(ProductCategory productCategory) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = " SELECT "
                    + "  EXCLUSIVE_KEY "
                    + " FROM "
                    + "  PRODUCT_CATEGORY_TBL "
                    + " WHERE "
                    + "  ID = ? ";

                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(1, productCategory.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != productCategory.getExclusiveKey()) {
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

            ProductCategory productCategoryOut = new ProductCategory();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productCategory.getId());

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                productCategoryOut.setId(rs.getInt(++index));
                productCategoryOut.setName(rs.getString(++index));
                productCategoryOut.setDescription(rs.getString(++index));
                productCategoryOut.setCateLevel(rs.getInt(++index));
                productCategoryOut.setParentId(rs.getBigDecimal(++index));
                productCategoryOut.setDeleted(rs.getInt(++index));
                productCategoryOut.setCreateTime(rs.getString(++index));
                productCategoryOut.setCreatorId(rs.getInt(++index));
                productCategoryOut.setModifyTime(rs.getString(++index));
                productCategoryOut.setModifierId(rs.getInt(++index));
                productCategoryOut.setExclusiveKey(rs.getInt(++index));
            }

            return productCategoryOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByIdFm");
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
                logSQLException(e, "searchByIdFm");
                throw e;
            }
        }
    }

    /**
     * select product category information by name.
     * @author luyan
     * @since 1.0
     * @param productCategoryName product category name
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchByName(String productCategoryName) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ProductCategory productCategory = null;
        List<ProductCategory> pcList = new ArrayList<ProductCategory>();

        try {
            // Start UOC
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  NAME = ? "
                + "ORDER BY "
                + "  CATE_LEVEL ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setString(++index, productCategoryName);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                productCategory = new ProductCategory();
                productCategory.setId(rs.getInt(++index));
                productCategory.setName(rs.getString(++index));
                productCategory.setDescription(rs.getString(++index));
                productCategory.setCateLevel(rs.getInt(++index));
                productCategory.setParentId(rs.getBigDecimal(++index));
                productCategory.setDeleted(rs.getInt(++index));
                productCategory.setCreateTime(rs.getString(++index));
                productCategory.setCreatorId(rs.getInt(++index));
                productCategory.setModifyTime(rs.getString(++index));
                productCategory.setModifierId(rs.getInt(++index));
                productCategory.setExclusiveKey(rs.getInt(++index));
                pcList.add(productCategory);
            }

            return pcList;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByName");
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
                logSQLException(e, "searchByName");
                throw e;
            }
        }
    }
    
    

    /**
     * select product category list by parent ID.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @param delRight business delete right.
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchByParentId(
            ProductCategory productCategory, String delRight)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  PARENT_ID = ? ";

            if ("0".equals(delRight)) {
                sql = sql + " AND DELETED = 0 ";
            }
            sql = sql + "ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productCategory.getId());

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByParentId");
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
                logSQLException(e, "searchByParentId");
                throw e;
            }
        }
    }

    /**
     * select product category list by parent ID.
     * @author Xiangzq
     * @since 2.0
     * @param  parentId.
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchByParentId(Integer parentId,User loginUser)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "DELETED=0 "
                + "AND PARENT_ID = ? ";
            
                if(!loginUser.filter("prod_cate_mng_all_data")){
                    sql = sql +" AND ID IN " +
                            "  ( SELECT " +
                            "   PRODUCT_CATEGORY_ID" +
                            "   FROM " +
                            "   AGENT_PRODUCT_TBL" +
                            "   WHERE AGENT_ID = ? )";
                }
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index,parentId);
            if(!loginUser.filter("prod_cate_mng_all_data")){
                pstmt.setInt(++index,loginUser.getCompanyID());
            }

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByParentId");
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
                logSQLException(e, "searchByParentId");
                throw e;
            }
        }
    }
    
    /**
     * select product category list by parent ID.
     * @author Xiangzq
     * @since 1.0
     * @param  parentId.
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchByParentId(Integer parentId)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "DELETED=0 "
                + "AND PARENT_ID = ? ";
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index,parentId);

            

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByParentId");
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
                logSQLException(e, "searchByParentId");
                throw e;
            }
        }
    }
    /**
     * select product category list of base level.
     * @author luyan
     * @since 1.0
     * @param delRight business delete right
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchLevel1(String delRight) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  CATE_LEVEL = 1 ";

            if ("0".equals(delRight)) {
                sql = sql + " AND DELETED = 0 ";
            }
            sql = sql + "ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchLevel1");
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
                logSQLException(e, "searchLevel1");
                throw e;
            }
        }
    }
    
    
    /**
     * select product category list of base level.
     * @author xiangzq
     * @since 2.0
     * @param delRight business delete right
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchLevel1(String delRight,User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  CATE_LEVEL = 1 ";

            if ("0".equals(delRight)) {
                sql = sql + " AND DELETED = 0 ";
            }
            if(!loginUser.filter("prod_cate_mng_all_data")){
                sql = sql +" AND ID IN " +
                		"  ( SELECT " +
                		"   PRODUCT_CATEGORY_ID" +
                		"   FROM " +
                		"   AGENT_PRODUCT_TBL" +
                		"   WHERE AGENT_ID = ? )";
            }
            sql = sql + " ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            if(!loginUser.filter("prod_cate_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchLevel1");
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
                logSQLException(e, "searchLevel1");
                throw e;
            }
        }
    }

    /**
     * select product category list of base level.
     * @author xiangzq
     * @since 1.0
     * @param delRight business delete right
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchTopLevel() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  CATE_LEVEL = 1 " 
                + "ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchLevel1");
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
                logSQLException(e, "searchLevel1");
                throw e;
            }
        }
    }   
    
    /**
     * get product category list.
     * @author sunyx
     * @since 1.0
     * @return List&ltProductCategory&gt
     * @throws SQLException
     */
    public List<ProductCategory> getCategory() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT" 
                + " ID," 
                + " NAME," 
                + " DESCRIPTION," 
                + " CATE_LEVEL," 
                + " PARENT_ID"
                + " FROM PRODUCT_CATEGORY_TBL "
                + " ORDER BY CATE_LEVEL,PARENT_ID,NAME";
            pstmt = conn.prepareStatement(sql);
            ProductCategory productCateOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                productCateOut = new ProductCategory();
                index = 0;
                productCateOut.setId(rs.getInt(++index));
                productCateOut.setName(rs.getString(++index));
                productCateOut.setDescription(rs.getString(++index));
                productCateOut.setCateLevel(rs.getInt(++index));
                productCateOut.setParentId(rs.getBigDecimal(++index));

                list.add(productCateOut);
            }
            return list;

            // End UOC
        }catch(SQLException e){
            logSQLException(e, "getCategory");
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
                logSQLException(e, "getCategory");
                throw e;
            }
        }
    }
    
    /**
     * get the product category list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<ProductCategory>
     * @throws  SQLException
     */
    public List<ProductCategory> getProductCategoryList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT" 
                       + " ID," 
                       + " NAME " 
                       + " FROM PRODUCT_CATEGORY_TBL "
                       + " WHERE DELETED = 0 "
                       + " ORDER BY CATE_LEVEL ";
            pstmt = conn.prepareStatement(sql);
            ProductCategory productCateOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                productCateOut = new ProductCategory();
                index = 0;
                productCateOut.setId(rs.getInt(++index));
                productCateOut.setName(rs.getString(++index));
                list.add(productCateOut);
            }
            return list;

            // End UOC
        }catch(SQLException e){
            logSQLException(e, "getProductCategoryList");
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
                logSQLException(e, "getProductCategoryList");
                throw e;
            }
        }
    }
    
    
    /**
     * 
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   
     * @return  List<ProductCategory>
     * @throws  Exception
     */
    public List<ProductCategory> getProductCategoryThreeList() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT" 
                       + " ID," 
                       + " NAME " 
                       + " FROM PRODUCT_CATEGORY_TBL "
                       + " WHERE DELETED = 0 "
                       + " AND CATE_LEVEL = 3 ";
            pstmt = conn.prepareStatement(sql);
            ProductCategory productCateOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
                productCateOut = new ProductCategory();
                index = 0;
                productCateOut.setId(rs.getInt(++index));
                productCateOut.setName(rs.getString(++index));
                list.add(productCateOut);
            }
            return list;

            // End UOC
        }catch(SQLException e){
            logSQLException(e, "getProductCategoryThreeList");
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
                logSQLException(e, "getProductCategoryThreeList");
                throw e;
            }
        }
    }

    /**
     * get the agent product category 
     * of the company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentId
     * @return  List<ProductCategory>
     * @throws  Exception
     */
    public List<ProductCategory> getAgentProductCategoryList(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = 
                        " SELECT" +
                        " B.ID," +
                        " B.NAME" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL B ON A.PRODUCT_CATEGORY_ID = B.ID" +
                        " WHERE " +
                        " B.DELETED = 0" +
                        " AND " +
                        " A.AGENT_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            rs = pstmt.executeQuery();
            
            ProductCategory productCategory = null;
            while (rs.next()){
                productCategory = new ProductCategory();
                index = 0;
                productCategory.setId(rs.getInt(++index));
                productCategory.setName(rs.getString(++index));
                list.add(productCategory);
            }
                        
            return list;      
            // End UOC
        }catch(SQLException e){
            logSQLException(e, "getAgentProductCategoryList");
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
                logSQLException(e, "getAgentProductCategoryList");
                throw e;
            }
        }  
    }
    
    /**
     * get the agent product category which
     * the cate_level is 3.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentId
     * @return  List<ProductCategory>
     * @throws  Exception
     */
    public List<ProductCategory> getAgentProductCategoryThreeList(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = 
                        " SELECT" +
                        " B.ID," +
                        " B.NAME" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL B ON A.PRODUCT_CATEGORY_ID = B.ID" +
                        " WHERE " +
                        " B.DELETED = 0" +
                        " AND " +
                        " A.AGENT_ID = ? " +
                        " AND B.CATE_LEVEL = 3 ";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            rs = pstmt.executeQuery();
            
            ProductCategory productCategory = null;
            while (rs.next()){
                productCategory = new ProductCategory();
                index = 0;
                productCategory.setId(rs.getInt(++index));
                productCategory.setName(rs.getString(++index));
                list.add(productCategory);
            }
                        
            return list;      
            // End UOC
        }catch(SQLException e){
            logSQLException(e, "getAgentProductCategoryThreeList");
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
                logSQLException(e, "getAgentProductCategoryThreeList");
                throw e;
            }
        }  
    }
    
    /**
     * select product category list of base level with agent.
     * @author luyan
     * @since 1.0
     * @param agentId agent company ID
     * @param delRight business delete right
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchLevel1WA(Integer agentId, String delRight) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = " SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + " FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + " WHERE "
                + "  PRODUCT_CATEGORY_TBL.CATE_LEVEL = 1 "
                + "  AND PRODUCT_CATEGORY_TBL.ID IN ( "
                + "    SELECT PRODUCT_CATEGORY_ID "
                + "    FROM AGENT_PRODUCT_TBL "
                + "    WHERE AGENT_ID = ? ) ";

            if ("0".equals(delRight)) {
                sql = sql + " AND DELETED = 0 ";
            }
            sql = sql + "ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentId);
            rs = pstmt.executeQuery();

            index = 0;
            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchLevel1WA");
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
                logSQLException(e, "searchLevel1WA");
                throw e;
            }
        }
    }

    /**
     * select product category list by parent ID with agent ID.
     * @author luyan
     * @since 1.0
     * @param productCategory product category information
     * @param agentId agent company ID
     * @param delRight business delete right
     * @return product category list
     * @throws SQLException
     */
    public List<ProductCategory> searchByParentIdWA(
            ProductCategory productCategory, Integer agentId, String delRight)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<ProductCategory> list = new ArrayList<ProductCategory>();
            String sql = "SELECT "
                + "  ID, "
                + "  NAME, "
                + "  DESCRIPTION, "
                + "  CATE_LEVEL, "
                + "  PARENT_ID, "
                + "  DELETED, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID, "
                + "  EXCLUSIVE_KEY "
                + "FROM "
                + "  PRODUCT_CATEGORY_TBL "
                + "WHERE "
                + "  PARENT_ID = ? "
                + "  AND PRODUCT_CATEGORY_TBL.ID IN ( "
                + "    SELECT PRODUCT_CATEGORY_ID "
                + "    FROM AGENT_PRODUCT_TBL "
                + "    WHERE AGENT_ID = ? ) ";

            if ("0".equals(delRight)) {
                sql = sql + " AND DELETED = 0 ";
            }
            sql = sql + "ORDER BY ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, productCategory.getId());
            pstmt.setInt(++index, agentId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                ProductCategory tmpProductCategory = new ProductCategory();
                index = 0;
                tmpProductCategory.setId(rs.getInt(++index));
                tmpProductCategory.setName(rs.getString(++index));
                tmpProductCategory.setDescription(rs.getString(++index));
                tmpProductCategory.setCateLevel(rs.getInt(++index));
                tmpProductCategory.setParentId(rs.getBigDecimal(++index));
                tmpProductCategory.setDeleted(rs.getInt(++index));
                tmpProductCategory.setCreateTime(rs.getString(++index));
                tmpProductCategory.setCreatorId(rs.getInt(++index));
                tmpProductCategory.setModifyTime(rs.getString(++index));
                tmpProductCategory.setModifierId(rs.getInt(++index));
                tmpProductCategory.setExclusiveKey(rs.getInt(++index));
                list.add(tmpProductCategory);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByParentIdWA");
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
                logSQLException(e, "searchByParentIdWA");
                throw e;
            }
        }
    }
    
}
