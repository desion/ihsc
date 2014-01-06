package cn.com.bhh.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.entity.AgentProduct;
import cn.com.bhh.erp.entity.Product;

/**
 * agent_product_tbl
 * @author sunyx
 * @since 1.0
 * @version 1.0
 */
public class AgentProductDao extends BaseDao{

    public AgentProductDao(Connection conn) {
        super(conn);
    }

    /**
     * seach information of product category 
     * @author sunyx
     * @param agentId
     * @return
     * @throws SQLException
     */
    public List<AgentProduct> serchByAgentId(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<AgentProduct> list = new ArrayList<AgentProduct>();
            String sql = " SELECT" +
                        " A.AGENT_ID," +
                        " A.PRODUCT_CATEGORY_ID," +
                        " B.ID," +
                        " B.NAME," +
                        " B.DESCRIPTION," +
                        " B.CATE_LEVEL," +
                        " B.PARENT_ID" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A" +
                        " LEFT OUTER JOIN PRODUCT_CATEGORY_TBL B ON A.PRODUCT_CATEGORY_ID = B.ID" +
                        " WHERE " +
                        " AGENT_ID = ? ";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            
            rs = pstmt.executeQuery();
            
            AgentProduct agentOut = null;
            while (rs.next()){
                agentOut = new AgentProduct();
                index = 0;
                agentOut.setAgentId(rs.getInt(++index));
                agentOut.setProductCategoryId(rs.getInt(++index));
                agentOut.setPCId(rs.getInt(++index));
                agentOut.setPCName(rs.getString(++index));
                agentOut.setPCDescription(rs.getString(++index));
                agentOut.setPCCateLevel(rs.getInt(++index));
                agentOut.setPCParentId(rs.getBigDecimal(++index));
                
                list.add(agentOut);
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
     * seach information of product category 
     * @author liugd
     * @param agentId
     * @return
     * @throws SQLException
     */
    public List<Product> serchProductByAgentId(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = " SELECT" +
                        " B.ID," +
                        " B.MODEL," +
                        " B.PRODUCT_CATEGORY_ID," +
                        " B.EXCLUSIVE_KEY" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A,PRODUCT_TBL B" +
                        " WHERE " +
                        " A.PRODUCT_CATEGORY_ID = B.PRODUCT_CATEGORY_ID" +
                        " AND A.AGENT_ID = ? " +
                        " ORDER BY B.MODEL";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            
            rs = pstmt.executeQuery();
            
            Product productOut = null;
            while (rs.next()){
                productOut = new Product();
                index = 0;
                productOut.setId(rs.getInt(++index));
                productOut.setModel(rs.getString(++index));
                productOut.setProductCategoryId(rs.getInt(++index));
                productOut.setExclusiveKey(rs.getInt(++index));

                list.add(productOut);
            }
                        
            return list;      
            // End UOC
        }catch(SQLException e){
            logSQLException(e, "serchProductByAgentId");
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
                logSQLException(e, "serchProductByAgentId");
                throw e;
            }
        }  
    }
    
    /**
     * search the product model
     * by agent company id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentId
     * @return  List<Product>
     * @throws  Exception
     */
    public List<Product> serchProductModelByAgentId(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = " SELECT" +
                        " B.ID," +
                        " B.MODEL," +
                        " B.PRODUCT_CATEGORY_ID," +
                        " B.EXCLUSIVE_KEY," +
                        " C.NAME" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A,PRODUCT_TBL B,PRODUCT_CATEGORY_TBL C" +
                        " WHERE " +
                        " A.PRODUCT_CATEGORY_ID = B.PRODUCT_CATEGORY_ID " +
                        " AND A.PRODUCT_CATEGORY_ID = C.ID" +
                        " AND B.DELETED = 0 " +
                        " AND A.AGENT_ID = ? " +
                        " ORDER BY B.MODEL";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            
            rs = pstmt.executeQuery();
            
            Product productOut = null;
            while (rs.next()){
                productOut = new Product();
                index = 0;
                productOut.setId(rs.getInt(++index));
                productOut.setModel(rs.getString(++index));
                productOut.setProductCategoryId(rs.getInt(++index));
                productOut.setExclusiveKey(rs.getInt(++index));
                productOut.setProductCategoryName(rs.getString(++index));
                list.add(productOut);
            }
                        
            return list;      
            // End UOC
        }catch(SQLException e){
            logSQLException(e, "serchProductModelByAgentId");
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
                logSQLException(e, "serchProductModelByAgentId");
                throw e;
            }
        }  
    }
    
    /**
     * seach product information by agent id 
     * @author liugd
     * @param agentId
     * @return product list
     * @throws SQLException
     */
    public List<Product> serchAllProductByAgentId(Integer agentId) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = " SELECT" +
                        " B.ID," +
                        " B.MODEL" +
                        " FROM " +
                        " AGENT_PRODUCT_TBL A," +
                        " PRODUCT_TBL B" +
                        " WHERE " +
                        " A.PRODUCT_CATEGORY_ID = B.PRODUCT_CATEGORY_ID" +
                        " AND A.AGENT_ID = ? " +
                        " AND B.DELETED = 0";
            pstmt = conn.prepareStatement(sql);
            
            int index = 0;
            pstmt.setInt(++index, agentId);
            
            rs = pstmt.executeQuery();
            
            Product dataOut = null;
            while (rs.next()){
                index = 0;
                dataOut = new Product();
                dataOut.setId(rs.getInt(++index));
                dataOut.setModel(rs.getString(++index));
                
                list.add(dataOut);
            }
            
            return list;      
            // End UOC
        }catch(SQLException e){
            logSQLException(e, "serchAllProductByAgentId");
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
                logSQLException(e, "serchAllProductByAgentId");
                throw e;
            }
        }  
    }
    
    
    /**
     * create one record .
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentProduct
     * @throws  SQLException
     */
    public void createAgentProduct(AgentProduct agentProduct) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                        " INSERT INTO AGENT_PRODUCT_TBL(" +
                        " AGENT_ID," +
                        " PRODUCT_CATEGORY_ID" +
                        ")values(?, ?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentProduct.getAgentId());
            pstmt.setInt(++index, agentProduct.getProductCategoryId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createAgentProduct");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createAgentProduct");
                throw e;
            }
        }
    }

    /**
     * get the company proxy product category
     * @auther xiangzq
     * @version 1.0
     * @param agentComId
     * @since 1.0
     * @return List&ltInteger&gt
     * @throws SQLException
     */
    public List<Integer> getCompanyAgentProductCategory(Integer agentComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Integer> productCateIdList = new ArrayList<Integer>();
        try {
            // Start UOC
            String sql = 
                     " SELECT  " +
                     " PRODUCT_CATEGORY_ID " +
                     " FROM " +
                     " AGENT_PRODUCT_TBL " +
                     " WHERE " +
                     " AGENT_ID=?";
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, agentComId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                index = 0;
                Integer productCategoryId = rs.getInt(++index);
                productCateIdList.add(productCategoryId);
            }

            return productCateIdList;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCompanyAgentProductCategory");
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
                logSQLException(e, "getCompanyAgentProductCategory");
                throw e;
            }
        }
    }

    /**
     * delete data from agent_product_tbl 
     * by agent company id
     * @auther xiangzq
     * @version 1.0
     * @param agentComId
     * @since 1.0
     * @throws SQLException
     */
    public void deleteAgentProductByAgentComId(Integer agentComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // Start UOC
            String sql = 
                     " DELETE  " +
                     " FROM " +
                     " AGENT_PRODUCT_TBL " +
                     " WHERE " +
                     " AGENT_ID=?";
            
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, agentComId);
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteAgentProductByAgentComId");
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
                logSQLException(e, "deleteAgentProductByAgentComId");
                throw e;
            }
        }
    }

   
    
    /**
     * get count by agent company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByAgentId(Integer agentId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " AGENT_PRODUCT_TBL " +
                       " WHERE  " +
                       " AGENT_ID= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByAgentId");
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
                logSQLException(e, "getCountByAgentId");
                throw e;
            }
        }

        return count;
    }

    /**
     * search count by product category ID.
     * @author luyan
     * @since 1.0
     * @param productId product category ID
     * @return count
     * @throws Exception
     */
    public int searchCountByPcId(Integer pcId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            // Start UOC
            String sql = " SELECT "
                + "  COUNT(*)"
                + " FROM "
                + "  AGENT_PRODUCT_TBL "
                + " WHERE "
                + "  product_category_id = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, pcId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchCountByPcId");
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
                logSQLException(e, "searchCountByPcId");
                throw e;
            }
        }
        return count;
    }
}

