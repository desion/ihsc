//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.entity.AgentCustomer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * AgentCustomer table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class AgentCustomerDao extends BaseDao {
    public AgentCustomerDao(Connection conn) {
        super(conn);
    }

    /**
     * delete the customers company of the agent company.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentComId
     * @param   cateFlag
     * @throws  SQLException
     */
    public void deleteAgentCustomerByAgentComId(Integer agentComId,Integer cateFlag) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " DELETE  " +
                    " FROM " +
                    " AGENT_CUSTOMER_TBL " +
                    " WHERE " +
                    " AGENT_ID = ? " ;
                  if(cateFlag!=null){
                    sql = sql + " AND CATE_FLAG =? ";
                  }
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentComId);
            if(cateFlag!=null){
                pstmt.setInt(++index, cateFlag);
            }
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteAgentCustomerByAgentComId");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "deleteAgentCustomerByAgentComId");
                throw e;
            }
        }
    }

    /**
     * create one record .
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   AgentCustomer
     * @throws  Exception
     */
    public void create(AgentCustomer agentCustomer) throws Exception {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                        "INSERT INTO AGENT_CUSTOMER_TBL (" +
                        " AGENT_ID," +
                        " CUSTOMER_ID," +
                        " CATE_FLAG" +
                        " ) VALUES(?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentCustomer.getAgentId());
            pstmt.setInt(++index, agentCustomer.getCustomerId());
            pstmt.setInt(++index, agentCustomer.getCateFlag());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "insert");
            throw e;
        } finally {
            try {
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
     * select the customers company of
     * the agent company
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   agentComId
     * @param   cateFlag 0:银行 1：销售商
     * @return  List&ltAgentCustomer&gt
     * @throws  Exception
     */
    public List<AgentCustomer> searchCustomerByAgent(Integer agentComId,Integer cateFlag) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<AgentCustomer> list = new ArrayList<AgentCustomer>();
            String sql = 
                       " SELECT " +
                       " AGENT_ID," +
                       " CUSTOMER_ID " +
                       " FROM " +
                       " AGENT_CUSTOMER_TBL " +
                       " WHERE " +
                       " AGENT_ID=? " +
                       " AND CATE_FLAG = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, agentComId);
            pstmt.setInt(++index, cateFlag);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                AgentCustomer agentCustomer = new AgentCustomer();
                index = 0;
                agentCustomer.setAgentId(rs.getInt(++index));
                agentCustomer.setCustomerId(rs.getInt(++index));
                agentCustomer.setCateFlag(cateFlag);
                list.add(agentCustomer);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectCustomerByAgent");
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
                logSQLException(e, "selectCustomerByAgent");
                throw e;
            }
        }
    }
    
    
    /**
     * get count by customer company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   customerId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCustomerId(Integer customerId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " AGENT_CUSTOMER_TBL " +
                       " WHERE  " +
                       " CUSTOMER_ID= ? " +
                       " AND AGENT_ID!=? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, customerId);
            pstmt.setInt(++index, customerId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCustomerId");
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
                logSQLException(e, "getCountByCustomerId");
                throw e;
            }
        }

        return count;
    }
}
