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
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.ProductCategory;
import cn.com.bhh.erp.entity.User;

/**
 * Class of Product Dao.
 * @author luyan
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
    public List<Product> searchAll() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = "SELECT"
                + " ID,"
                + " MODEL," 
                + " PRODUCT_CATEGORY_ID,"
                + " EXCLUSIVE_KEY"
                + " FROM PRODUCT_TBL "
                + " WHERE "
                + " ID >0"
                + " AND DELETED = 0"
                + " ORDER BY PRODUCT_CATEGORY_ID,MODEL";
            pstmt = conn.prepareStatement(sql);
            Product productOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
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
     * get all the product model list.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List<Product>
     * @throws  SQLException
     */
    public List<Product> searchAllProductModel() throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            // Start UOC
            List<Product> list = new ArrayList<Product>();
            String sql = "SELECT"
                + " A.ID,"
                + " A.MODEL," 
                + " A.PRODUCT_CATEGORY_ID,"
                + " A.EXCLUSIVE_KEY,"
                + " B.NAME"
                + " FROM PRODUCT_TBL A,PRODUCT_CATEGORY_TBL B"
                + " WHERE "
                + " A.ID >0"
                + " AND A.PRODUCT_CATEGORY_ID=B.ID "
                + " AND A.DELETED = 0"
                + " ORDER BY A.PRODUCT_CATEGORY_ID,A.MODEL";
            pstmt = conn.prepareStatement(sql);
            Product productOut = null;
            int index = 0;

            rs = pstmt.executeQuery();
            while (rs.next()) {
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
        } catch(SQLException e){
            logSQLException(e, "searchAllModel");
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
                logSQLException(e, "searchAllModel");
                throw e;
            }
        }
    }
    
    /**
     * get count by product ID.
     * @author liugd
     * @since 1.0
     * @param productId product ID
     * @return int count
     * @throws SQLException
     */
    public int getCountById(Integer productId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            // Start UOC
            String sql = 
                        " SELECT COUNT(*) " +
                        " FROM " +
                        "  PRODUCT_TBL " +
                        " WHERE " +
                        "  PRODUCT_TBL.ID = ? " +
                        "  AND PRODUCT_TBL.DELETED = 0";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountById");
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
                logSQLException(e, "getCountById");
                throw e;
            }
        }
    }
    
    /**
     * 确定某一产品是否属于代理商
     * 所代理的产品分类下的产品
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   productId
     * @param   agentId
     * @return  int
     * @throws  Exception
     */
    public int getCountByAgentId(Integer productId,Integer agentId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            // Start UOC
            String sql = 
                        "  SELECT " +
                        "    COUNT(*) " +
                        "    FROM PRODUCT_TBL " +
                        "    WHERE   " +
                        "    ID = ? " +
                        "    AND PRODUCT_CATEGORY_ID " +
                        "    IN  " +
                        "    (  " +
                        "         SELECT  PRODUCT_CATEGORY_ID " +
                        "         FROM AGENT_PRODUCT_TBL " +
                        "         WHERE  " +
                        "         AGENT_ID = ? " +
                        "     ) ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productId);
            pstmt.setInt(2, agentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
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
    }
    
    
    /**
     * get count by product model.
     * @author liugd
     * @since 1.0
     * @param model product model
     * @return Integer id
     * @throws SQLException
     */
    public Integer getIdByModel(String model) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;
        try {
            // Start UOC
            String sql = 
                        " SELECT" +
                        " ID " +
                        " FROM " +
                        "  PRODUCT_TBL " +
                        " WHERE " +
                        "  PRODUCT_TBL.MODEL = ? " +
                        "  AND PRODUCT_TBL.DELETED = 0";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, model);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByModel");
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
                logSQLException(e, "getCountByModel");
                throw e;
            }
        }
    }
    
    /**
     * search product list by product category ID.
     * @author luyan
     * @since 1.0
     * @param productCategoryId product category ID
     * @param delRight delete right
     * @return product list
     * @throws SQLException
     */
    public List<Product> searchByPcId(Integer productCategoryId, String delRight) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<Product> productList = new ArrayList<Product>();
            String sql = " SELECT "
                + "  PRODUCT_TBL.ID, "
                + "  PRODUCT_TBL.MODEL, "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID, "
                + "  PRODUCT_TBL.PRODUCER_ID, "
                + "  COMPANY_TBL.SHORT_NAME, "
                + "  PRODUCT_TBL.OPERATION_TYPE, "
                + "  PRODUCT_TBL.LENGTH, "
                + "  PRODUCT_TBL.WIDTH, "
                + "  PRODUCT_TBL.HEIGHT, "
                + "  PRODUCT_TBL.WEIGHT, "
                + "  PRODUCT_TBL.GUARD_LEVEL, "
                + "  PRODUCT_TBL.RUNTIME_TEMP, "
                + "  PRODUCT_TBL.STANDBY_TEMP, "
                + "  PRODUCT_TBL.HUMIDITY, "
                + "  PRODUCT_TBL.VOLTAGE, "
                + "  PRODUCT_TBL.FREQUENCY, "
                + "  PRODUCT_TBL.WAVE_FREQUENCY, "
                + "  PRODUCT_TBL.GROUNDING, "
                + "  PRODUCT_TBL.IMPACT_CURRENT, "
                + "  PRODUCT_TBL.RUNTIME_POWER, "
                + "  PRODUCT_TBL.STANDBY_POWER, "
                + "  PRODUCT_TBL.LIGHT_POWER, "
                + "  PRODUCT_TBL.RUNTIME_HEAT, "
                + "  PRODUCT_TBL.STANDBY_HEAT, "
                + "  PRODUCT_TBL.CUSTOMER_LCD, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT1, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT2, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT3, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT4, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT5, "
                + "  PRODUCT_TBL.CUSTOMER_LOUDSPEAKER, "
                + "  PRODUCT_TBL.CUSTOMER_VOICE_WIZARD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_LCD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_INPUT, "
                + "  PRODUCT_TBL.OPERATION_SYSTEM, "
                + "  PRODUCT_TBL.DELETED, "
                + "  PRODUCT_TBL.CREATE_TIME, "
                + "  PRODUCT_TBL.CREATOR_ID, "
                + "  PRODUCT_TBL.MODIFY_TIME, "
                + "  PRODUCT_TBL.MODIFIER_ID, "
                + "  PRODUCT_TBL.EXCLUSIVE_KEY "
                + " FROM "
                + "  PRODUCT_TBL "
                + " LEFT OUTER JOIN "
                + "  COMPANY_TBL "
                + " ON "
                + "  PRODUCT_TBL.PRODUCER_ID = COMPANY_TBL.ID "
                + " WHERE "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID = ? ";

            if ("0".equals(delRight)) {
                sql = sql + " AND PRODUCT_TBL.DELETED = 0 ";
            }

            sql = sql + "ORDER BY PRODUCT_TBL.ID ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;
            
            pstmt.setInt(++index, productCategoryId);

            index = 0;

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                index = 0;
                product.setId(rs.getInt(++index));
                product.setModel(rs.getString(++index));
                product.setProductCategoryId(rs.getInt(++index));
                product.setProducerId(rs.getInt(++index));
                product.setProducerName(rs.getString(++index));
                product.setOperationType(rs.getInt(++index));
                product.setLength(rs.getString(++index));
                product.setWidth(rs.getString(++index));
                product.setHeight(rs.getString(++index));
                product.setWeight(rs.getString(++index));
                product.setGuardLevel(rs.getString(++index));
                product.setRuntimeTemp(rs.getString(++index));
                product.setStandbyTemp(rs.getString(++index));
                product.setHumidity(rs.getString(++index));
                product.setVoltage(rs.getString(++index));
                product.setFrequency(rs.getString(++index));
                product.setWaveFrequency(rs.getString(++index));
                product.setGrounding(rs.getString(++index));
                product.setImpactCurrent(rs.getString(++index));
                product.setRuntimePower(rs.getString(++index));
                product.setStandbyPower(rs.getString(++index));
                product.setLightPower(rs.getString(++index));
                product.setRuntimeHeat(rs.getString(++index));
                product.setStandbyHeat(rs.getString(++index));
                product.setCustomerLcd(rs.getString(++index));
                product.setCustomerInput1(rs.getString(++index));
                product.setCustomerInput2(rs.getString(++index));
                product.setCustomerInput3(rs.getString(++index));
                product.setCustomerInput4(rs.getString(++index));
                product.setCustomerInput5(rs.getString(++index));
                product.setCustomerLoudspeaker(rs.getInt(++index));
                product.setCustomerVoiceWizard(rs.getInt(++index));
                product.setAdministratorLcd(rs.getString(++index));
                product.setAdministratorInput(rs.getString(++index));
                product.setOperationSystem(rs.getString(++index));
                product.setDeleted(rs.getInt(++index));
                product.setCreateTime(rs.getString(++index));
                product.setCreatorId(rs.getInt(++index));
                product.setModifyTime(rs.getString(++index));
                product.setModifierId(rs.getInt(++index));
                product.setExclusiveKey(rs.getInt(++index));
                productList.add(product);
            }

            return productList;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByPcId");
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
                logSQLException(e, "searchByPcId");
                throw e;
            }
        }
    }

    /**
     * search product information by product model.
     * @author luyan
     * @since 1.0
     * @param model product model
     * @return product information
     * @throws SQLException
     */
    public Product searchByModel(String model) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            Product product = new Product();
            String sql = " SELECT "
                + "  PRODUCT_TBL.ID, "
                + "  PRODUCT_TBL.MODEL, "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID, "
                + "  PRODUCT_CATEGORY_TBL.NAME, "
                + "  PRODUCT_TBL.PRODUCER_ID, "
                + "  COMPANY_TBL.SHORT_NAME, "
                + "  PRODUCT_TBL.OPERATION_TYPE, "
                + "  PRODUCT_TBL.LENGTH, "
                + "  PRODUCT_TBL.WIDTH, "
                + "  PRODUCT_TBL.HEIGHT, "
                + "  PRODUCT_TBL.WEIGHT, "
                + "  PRODUCT_TBL.GUARD_LEVEL, "
                + "  PRODUCT_TBL.RUNTIME_TEMP, "
                + "  PRODUCT_TBL.STANDBY_TEMP, "
                + "  PRODUCT_TBL.HUMIDITY, "
                + "  PRODUCT_TBL.VOLTAGE, "
                + "  PRODUCT_TBL.FREQUENCY, "
                + "  PRODUCT_TBL.WAVE_FREQUENCY, "
                + "  PRODUCT_TBL.GROUNDING, "
                + "  PRODUCT_TBL.IMPACT_CURRENT, "
                + "  PRODUCT_TBL.RUNTIME_POWER, "
                + "  PRODUCT_TBL.STANDBY_POWER, "
                + "  PRODUCT_TBL.LIGHT_POWER, "
                + "  PRODUCT_TBL.RUNTIME_HEAT, "
                + "  PRODUCT_TBL.STANDBY_HEAT, "
                + "  PRODUCT_TBL.CUSTOMER_LCD, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT1, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT2, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT3, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT4, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT5, "
                + "  PRODUCT_TBL.CUSTOMER_LOUDSPEAKER, "
                + "  PRODUCT_TBL.CUSTOMER_VOICE_WIZARD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_LCD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_INPUT, "
                + "  PRODUCT_TBL.OPERATION_SYSTEM, "
                + "  PRODUCT_TBL.DELETED, "
                + "  PRODUCT_TBL.CREATE_TIME, "
                + "  PRODUCT_TBL.CREATOR_ID, "
                + "  PRODUCT_TBL.MODIFY_TIME, "
                + "  PRODUCT_TBL.MODIFIER_ID, "
                + "  PRODUCT_TBL.EXCLUSIVE_KEY "
                + " FROM "
                + "  PRODUCT_TBL "
                + " LEFT OUTER JOIN "
                + "  PRODUCT_CATEGORY_TBL "
                + " ON "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID = PRODUCT_CATEGORY_TBL.ID "
                + " LEFT OUTER JOIN "
                + "  COMPANY_TBL "
                + " ON "
                + "  PRODUCT_TBL.PRODUCER_ID = COMPANY_TBL.ID "
                + " WHERE "
                + "  PRODUCT_TBL.MODEL = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, model);

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                product.setId(rs.getInt(++index));
                product.setModel(rs.getString(++index));
                product.setProductCategoryId(rs.getInt(++index));
                product.setProductCategoryName(rs.getString(++index));
                product.setProducerId(rs.getInt(++index));
                product.setProducerName(rs.getString(++index));
                product.setOperationType(rs.getInt(++index));
                product.setLength(rs.getString(++index));
                product.setWidth(rs.getString(++index));
                product.setHeight(rs.getString(++index));
                product.setWeight(rs.getString(++index));
                product.setGuardLevel(rs.getString(++index));
                product.setRuntimeTemp(rs.getString(++index));
                product.setStandbyTemp(rs.getString(++index));
                product.setHumidity(rs.getString(++index));
                product.setVoltage(rs.getString(++index));
                product.setFrequency(rs.getString(++index));
                product.setWaveFrequency(rs.getString(++index));
                product.setGrounding(rs.getString(++index));
                product.setImpactCurrent(rs.getString(++index));
                product.setRuntimePower(rs.getString(++index));
                product.setStandbyPower(rs.getString(++index));
                product.setLightPower(rs.getString(++index));
                product.setRuntimeHeat(rs.getString(++index));
                product.setStandbyHeat(rs.getString(++index));
                product.setCustomerLcd(rs.getString(++index));
                product.setCustomerInput1(rs.getString(++index));
                product.setCustomerInput2(rs.getString(++index));
                product.setCustomerInput3(rs.getString(++index));
                product.setCustomerInput4(rs.getString(++index));
                product.setCustomerInput5(rs.getString(++index));
                product.setCustomerLoudspeaker(rs.getInt(++index));
                product.setCustomerVoiceWizard(rs.getInt(++index));
                product.setAdministratorLcd(rs.getString(++index));
                product.setAdministratorInput(rs.getString(++index));
                product.setOperationSystem(rs.getString(++index));
                product.setDeleted(rs.getInt(++index));
                product.setCreateTime(rs.getString(++index));
                product.setCreatorId(rs.getInt(++index));
                product.setModifyTime(rs.getString(++index));
                product.setModifierId(rs.getInt(++index));
                product.setExclusiveKey(rs.getInt(++index));
            }

            return product;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchByModel");
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
     * get the product by product category.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   productType
     * @return  Product
     * @throws  SQLException
     */
    public List<Product> searchProductByProductCategory(Integer productCategoryId,User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<Product>();
        
        try {
            // Start UOC
           
            String sql = 
                " SELECT "
                + "  ID, "
                + "  MODEL, "
                + "  PRODUCT_CATEGORY_ID "
                + " FROM "
                + "  PRODUCT_TBL "
                + " WHERE "
                + "  PRODUCT_CATEGORY_ID = ? "
                + "  AND DELETED = 0 ";

//            if(!loginUser.filter("product_mng_all_data")){
//                sql = sql +" AND ID IN " +
//                        "  ( SELECT " +
//                        "   PRODUCT_CATEGORY_ID" +
//                        "   FROM " +
//                        "   AGENT_PRODUCT_TBL" +
//                        "   WHERE AGENT_ID = ? )";
//            }
            
            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productCategoryId);
//            if(!loginUser.filter("product_mng_all_data")){
//                pstmt.setInt(2, loginUser.getCompanyID());
//            }
            int index = 0;

            rs = pstmt.executeQuery();

            while(rs.next()) {
                index = 0;
                Product product = new Product();
                product.setId(rs.getInt(++index));
                product.setModel(rs.getString(++index));
                product.setProductCategoryId(rs.getInt(++index));
                productList.add(product);
            }

            return productList;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchProductByProductCategory");
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
                logSQLException(e, "searchProductByProductCategory");
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
                + "  MODEL, "
                + "  PRODUCT_CATEGORY_ID, "
                + "  PRODUCER_ID, "
                + "  OPERATION_TYPE, "
                + "  LENGTH, "
                + "  WIDTH, "
                + "  HEIGHT, "
                + "  WEIGHT, "
                + "  GUARD_LEVEL, "
                + "  RUNTIME_TEMP, "
                + "  STANDBY_TEMP, "
                + "  HUMIDITY, "
                + "  VOLTAGE, "
                + "  FREQUENCY, "
                + "  WAVE_FREQUENCY, "
                + "  GROUNDING, "
                + "  IMPACT_CURRENT, "
                + "  RUNTIME_POWER, "
                + "  STANDBY_POWER, "
                + "  LIGHT_POWER, "
                + "  RUNTIME_HEAT, "
                + "  STANDBY_HEAT, "
                + "  CUSTOMER_LCD, "
                + "  CUSTOMER_INPUT1, "
                + "  CUSTOMER_INPUT2, "
                + "  CUSTOMER_INPUT3, "
                + "  CUSTOMER_INPUT4, "
                + "  CUSTOMER_INPUT5, "
                + "  CUSTOMER_LOUDSPEAKER, "
                + "  CUSTOMER_VOICE_WIZARD, "
                + "  ADMINISTRATOR_LCD, "
                + "  ADMINISTRATOR_INPUT, "
                + "  OPERATION_SYSTEM, "
                + "  CREATE_TIME, "
                + "  CREATOR_ID, "
                + "  MODIFY_TIME, "
                + "  MODIFIER_ID "
                + " ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setString(++index, product.getModel().trim());
            pstmt.setInt(++index, product.getProductCategoryId());
            pstmt.setInt(++index, product.getProducerId());
            if (product.getOperationType() == null || Integer.valueOf(-1).equals(product.getOperationType())) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, product.getOperationType());
            }
            pstmt.setString(++index, product.getLength());
            pstmt.setString(++index, product.getWidth());
            pstmt.setString(++index, product.getHeight());
            pstmt.setString(++index, product.getWeight());
            pstmt.setString(++index, product.getGuardLevel());
            pstmt.setString(++index, product.getRuntimeTemp());
            pstmt.setString(++index, product.getStandbyTemp());
            pstmt.setString(++index, product.getHumidity());
            pstmt.setString(++index, product.getVoltage());
            pstmt.setString(++index, product.getFrequency());
            pstmt.setString(++index, product.getWaveFrequency());
            pstmt.setString(++index, product.getGrounding());
            pstmt.setString(++index, product.getImpactCurrent());
            pstmt.setString(++index, product.getRuntimePower());
            pstmt.setString(++index, product.getStandbyPower());
            pstmt.setString(++index, product.getLightPower());
            pstmt.setString(++index, product.getRuntimeHeat());
            pstmt.setString(++index, product.getStandbyHeat());
            pstmt.setString(++index, product.getCustomerLcd());
            pstmt.setString(++index, product.getCustomerInput1());
            pstmt.setString(++index, product.getCustomerInput2());
            pstmt.setString(++index, product.getCustomerInput3());
            pstmt.setString(++index, product.getCustomerInput4());
            pstmt.setString(++index, product.getCustomerInput5());
            pstmt.setInt(++index, product.getCustomerLoudspeaker());
            pstmt.setInt(++index, product.getCustomerVoiceWizard());
            pstmt.setString(++index, product.getAdministratorLcd());
            pstmt.setString(++index, product.getAdministratorInput());
            pstmt.setString(++index, product.getOperationSystem());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, product.getCreatorId());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, product.getCreatorId());
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
     * search product information by product ID for modify.
     * @author luyan
     * @since 1.0
     * @param productIn product information
     * @return product information
     * @throws SQLException
     */
    public Product searchByIdFm(Product productIn) throws Exception {
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
                    + "  ID = ? ";

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

            Product product = new Product();
            String sql = " SELECT "
                + "  PRODUCT_TBL.ID, "
                + "  PRODUCT_TBL.MODEL, "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID, "
                + "  PRODUCT_CATEGORY_TBL.NAME, "
                + "  PRODUCT_TBL.PRODUCER_ID, "
                + "  COMPANY_TBL.SHORT_NAME COMPANY_NAME, "
                + "  PRODUCT_TBL.OPERATION_TYPE, "
                + "  OPERATION_TYPE_TBL.NAME OPERATION_TYPE_NAME, "
                + "  PRODUCT_TBL.LENGTH, "
                + "  PRODUCT_TBL.WIDTH, "
                + "  PRODUCT_TBL.HEIGHT, "
                + "  PRODUCT_TBL.WEIGHT, "
                + "  PRODUCT_TBL.GUARD_LEVEL, "
                + "  PRODUCT_TBL.RUNTIME_TEMP, "
                + "  PRODUCT_TBL.STANDBY_TEMP, "
                + "  PRODUCT_TBL.HUMIDITY, "
                + "  PRODUCT_TBL.VOLTAGE, "
                + "  PRODUCT_TBL.FREQUENCY, "
                + "  PRODUCT_TBL.WAVE_FREQUENCY, "
                + "  PRODUCT_TBL.GROUNDING, "
                + "  PRODUCT_TBL.IMPACT_CURRENT, "
                + "  PRODUCT_TBL.RUNTIME_POWER, "
                + "  PRODUCT_TBL.STANDBY_POWER, "
                + "  PRODUCT_TBL.LIGHT_POWER, "
                + "  PRODUCT_TBL.RUNTIME_HEAT, "
                + "  PRODUCT_TBL.STANDBY_HEAT, "
                + "  PRODUCT_TBL.CUSTOMER_LCD, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT1, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT2, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT3, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT4, "
                + "  PRODUCT_TBL.CUSTOMER_INPUT5, "
                + "  PRODUCT_TBL.CUSTOMER_LOUDSPEAKER, "
                + "  PRODUCT_TBL.CUSTOMER_VOICE_WIZARD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_LCD, "
                + "  PRODUCT_TBL.ADMINISTRATOR_INPUT, "
                + "  PRODUCT_TBL.OPERATION_SYSTEM, "
                + "  PRODUCT_TBL.DELETED, "
                + "  PRODUCT_TBL.CREATE_TIME, "
                + "  PRODUCT_TBL.CREATOR_ID, "
                + "  PRODUCT_TBL.MODIFY_TIME, "
                + "  PRODUCT_TBL.MODIFIER_ID, "
                + "  PRODUCT_TBL.EXCLUSIVE_KEY "
                + " FROM "
                + "  PRODUCT_TBL "
                + " LEFT OUTER JOIN "
                + "  PRODUCT_CATEGORY_TBL "
                + " ON "
                + "  PRODUCT_TBL.PRODUCT_CATEGORY_ID = PRODUCT_CATEGORY_TBL.ID "
                + " LEFT OUTER JOIN "
                + "  COMPANY_TBL "
                + " ON "
                + "  PRODUCT_TBL.PRODUCER_ID = COMPANY_TBL.ID "
                + " LEFT OUTER JOIN "
                + "  OPERATION_TYPE_TBL "
                + " ON "
                + "  PRODUCT_TBL.OPERATION_TYPE = OPERATION_TYPE_TBL.ID "
                + " WHERE "
                + "  PRODUCT_TBL.ID = ? ";

            pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, productIn.getId());

            int index = 0;

            rs = pstmt.executeQuery();

            if (rs.next()) {
                product.setId(rs.getInt(++index));
                product.setModel(rs.getString(++index));
                product.setProductCategoryId(rs.getInt(++index));
                product.setProductCategoryName(rs.getString(++index));
                product.setProducerId(rs.getInt(++index));
                product.setProducerName(rs.getString(++index));
                if (rs.getBigDecimal(++index) != null) {
                    product.setOperationType(rs.getInt(index));
                }
                product.setOperationTypeName(rs.getString(++index));
                product.setLength(rs.getString(++index));
                product.setWidth(rs.getString(++index));
                product.setHeight(rs.getString(++index));
                product.setWeight(rs.getString(++index));
                product.setGuardLevel(rs.getString(++index));
                product.setRuntimeTemp(rs.getString(++index));
                product.setStandbyTemp(rs.getString(++index));
                product.setHumidity(rs.getString(++index));
                product.setVoltage(rs.getString(++index));
                product.setFrequency(rs.getString(++index));
                product.setWaveFrequency(rs.getString(++index));
                product.setGrounding(rs.getString(++index));
                product.setImpactCurrent(rs.getString(++index));
                product.setRuntimePower(rs.getString(++index));
                product.setStandbyPower(rs.getString(++index));
                product.setLightPower(rs.getString(++index));
                product.setRuntimeHeat(rs.getString(++index));
                product.setStandbyHeat(rs.getString(++index));
                product.setCustomerLcd(rs.getString(++index));
                product.setCustomerInput1(rs.getString(++index));
                product.setCustomerInput2(rs.getString(++index));
                product.setCustomerInput3(rs.getString(++index));
                product.setCustomerInput4(rs.getString(++index));
                product.setCustomerInput5(rs.getString(++index));
                product.setCustomerLoudspeaker(rs.getInt(++index));
                product.setCustomerVoiceWizard(rs.getInt(++index));
                product.setAdministratorLcd(rs.getString(++index));
                product.setAdministratorInput(rs.getString(++index));
                product.setOperationSystem(rs.getString(++index));
                product.setDeleted(rs.getInt(++index));
                product.setCreateTime(rs.getString(++index));
                product.setCreatorId(rs.getInt(++index));
                product.setModifyTime(rs.getString(++index));
                product.setModifierId(rs.getInt(++index));
                product.setExclusiveKey(rs.getInt(++index));
            }

            return product;

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
     * modify product information by product ID.
     * @author luyan
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
                + "  MODEL = ?, "
                + "  PRODUCT_CATEGORY_ID = ?, "
                + "  PRODUCER_ID = ?, "
                + "  OPERATION_TYPE = ?, "
                + "  LENGTH  = ?, "
                + "  WIDTH = ?, "
                + "  HEIGHT = ?, "
                + "  WEIGHT = ?, "
                + "  GUARD_LEVEL = ?, "
                + "  RUNTIME_TEMP = ?, "
                + "  STANDBY_TEMP = ?, "
                + "  HUMIDITY = ?, "
                + "  VOLTAGE = ?, "
                + "  FREQUENCY = ?, "
                + "  WAVE_FREQUENCY = ?, "
                + "  GROUNDING = ?, "
                + "  IMPACT_CURRENT = ?, "
                + "  RUNTIME_POWER = ?, "
                + "  STANDBY_POWER = ?, "
                + "  LIGHT_POWER = ?, "
                + "  RUNTIME_HEAT = ?, "
                + "  STANDBY_HEAT = ?, "
                + "  CUSTOMER_LCD = ?, "
                + "  CUSTOMER_INPUT1 = ?, "
                + "  CUSTOMER_INPUT2 = ?, "
                + "  CUSTOMER_INPUT3 = ?, "
                + "  CUSTOMER_INPUT4 = ?, "
                + "  CUSTOMER_INPUT5 = ?, "
                + "  CUSTOMER_LOUDSPEAKER = ?, "
                + "  CUSTOMER_VOICE_WIZARD = ?, "
                + "  ADMINISTRATOR_LCD = ?, "
                + "  ADMINISTRATOR_INPUT = ?, "
                + "  OPERATION_SYSTEM = ?, "
                + "  MODIFY_TIME = ?, "
                + "  MODIFIER_ID = ?, "
                + "  EXCLUSIVE_KEY = EXCLUSIVE_KEY + 1 "
                + " WHERE " 
                + "  ID = ? ";

            pstmt = conn.prepareStatement(sql);

            int index = 0;

            pstmt.setString(++index, productIn.getModel().trim());
            pstmt.setInt(++index, productIn.getProductCategoryId());
            pstmt.setInt(++index, productIn.getProducerId());
            if (productIn.getOperationType() == null || Integer.valueOf(-1).equals(productIn.getOperationType())) {
                pstmt.setBigDecimal(++index, null);
            } else {
                pstmt.setInt(++index, productIn.getOperationType());
            }
            pstmt.setString(++index, productIn.getLength());
            pstmt.setString(++index, productIn.getWidth());
            pstmt.setString(++index, productIn.getHeight());
            pstmt.setString(++index, productIn.getWeight());
            pstmt.setString(++index, productIn.getGuardLevel());
            pstmt.setString(++index, productIn.getRuntimeTemp());
            pstmt.setString(++index, productIn.getStandbyTemp());
            pstmt.setString(++index, productIn.getHumidity());
            pstmt.setString(++index, productIn.getVoltage());
            pstmt.setString(++index, productIn.getFrequency());
            pstmt.setString(++index, productIn.getWaveFrequency());
            pstmt.setString(++index, productIn.getGrounding());
            pstmt.setString(++index, productIn.getImpactCurrent());
            pstmt.setString(++index, productIn.getRuntimePower());
            pstmt.setString(++index, productIn.getStandbyPower());
            pstmt.setString(++index, productIn.getLightPower());
            pstmt.setString(++index, productIn.getRuntimeHeat());
            pstmt.setString(++index, productIn.getStandbyHeat());
            pstmt.setString(++index, productIn.getCustomerLcd());
            pstmt.setString(++index, productIn.getCustomerInput1());
            pstmt.setString(++index, productIn.getCustomerInput2());
            pstmt.setString(++index, productIn.getCustomerInput3());
            pstmt.setString(++index, productIn.getCustomerInput4());
            pstmt.setString(++index, productIn.getCustomerInput5());
            pstmt.setInt(++index, productIn.getCustomerLoudspeaker());
            pstmt.setInt(++index, productIn.getCustomerVoiceWizard());
            pstmt.setString(++index, productIn.getAdministratorLcd());
            pstmt.setString(++index, productIn.getAdministratorInput());
            pstmt.setString(++index, productIn.getOperationSystem());
            pstmt.setString(++index, TimeUtil.getNow());
            pstmt.setInt(++index, productIn.getModifierId());
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
            pstmt.setInt(++index, productIn.getModifierId());
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
}
