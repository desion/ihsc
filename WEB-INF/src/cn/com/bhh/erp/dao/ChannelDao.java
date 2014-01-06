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
import cn.com.bhh.erp.entity.Channel;
import cn.com.bhh.erp.entity.ChannelAccumulate;
import cn.com.bhh.erp.entity.ChannelDetail;
import cn.com.bhh.erp.entity.Province;
import cn.com.bhh.erp.entity.User;


/**
 * Province table data access object.
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
public class ChannelDao extends BaseDao {
    public ChannelDao(Connection conn) {
        super(conn);
    }

    /**
     * delete province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  Exception
     */
    public void deleteProvince(Province province) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " PROVINCE_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, province.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != province.getExclusiveKey()) {
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

            // Start UOC
            String sql = 
                       " DELETE " +
                       " FROM " +
                       " PROVINCE_TBL " +
                       " WHERE " +
                       " ID = ?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "deleteProvince");
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
                logSQLException(e, "deleteProvince");
                throw e;
            }
        }
    }

    /**
     * create channel.
     * @auther  desionwang
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  SQLException
     */
    public void createChannel(Channel channel) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " INSERT INTO CHANNEL_TBL (" +
            		   " ID," +
            		   " CHANNEL_NAME," +
            		   " SNAME," +
            		   " ALERT_THRESHOLD," +
            		   " BALANCE," +
            		   " SUSPEND_THRESHOLD," +
            		   " INTERFACE_NAME," +
            		   " STATUS" +
            		   " ) VALUES(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, channel.getId());
            pstmt.setString(++index, channel.getChannelName());
            pstmt.setString(++index, channel.getSname());
            pstmt.setFloat(++index, channel.getAlertThreshold());
            pstmt.setFloat(++index, channel.getBalance());
            pstmt.setFloat(++index, channel.getSuspendThreshold());
            pstmt.setString(++index, channel.getInterfaceName());
            pstmt.setInt(++index, channel.getStatus());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createChannel");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createChannel");
                throw e;
            }
        }
    }
    
    /**
     * create channel.
     * @auther  desionwang
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  SQLException
     */
    public void createChannelDetail(ChannelDetail channelDetail) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                       " INSERT INTO CHANNEL_DETAIL_TBL (" +
            		   " ID," +
            		   " NAME," +
            		   " SNAME," +
            		   " VALUE," +
            		   " PROVINCE," +
            		   " OPERATOR," +
            		   " PRIORITY," +
            		   " REPEAT," +
            		   " DISCOUNT," +
            		   " COMMENTS," +
            		   " CHANNEL_ID," +
            		   " STATUS" +
            		   " ) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, channelDetail.getId());
            pstmt.setString(++index, channelDetail.getName());
            pstmt.setString(++index, channelDetail.getSname());
            pstmt.setInt(++index, channelDetail.getValue());
            pstmt.setInt(++index, channelDetail.getProvinceId());
            pstmt.setInt(++index, channelDetail.getOperator());
            pstmt.setInt(++index, channelDetail.getPriority());
            pstmt.setInt(++index, channelDetail.getRepeat());
            pstmt.setFloat(++index, channelDetail.getDiscount());
            pstmt.setString(++index, channelDetail.getComments());
            pstmt.setInt(++index, channelDetail.getChannelId());
            pstmt.setInt(++index, channelDetail.getStatus());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createChannelDetail");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createChannelDetail");
                throw e;
            }
        }
    }

    /**
     * get all province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Province> searchAllProvince(boolean isInCompany,Integer bankId,Integer saleComId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Province>list = new ArrayList<Province>();
            String sql = " SELECT " +
            		     " P.ID," +
            		     " P.NAME," +
            		     " P.SHORT_NAME," +
            		     " P.COUNTRY_ID," +
            		     " P.EXCLUSIVE_KEY," +
            		     " C.SHORT_NAME " +
            		     " FROM " +
            		     " PROVINCE_TBL P ,COUNTRY_TBL C " +
                         " WHERE " +
                         " P.COUNTRY_ID = C.ID" +
                         " AND P.ID>0 " ;
            if(isInCompany){
                 sql  = sql +  
                       " AND P.ID IN " +
                       " (" +
                       "  SELECT " +
                       "   DISTINCT PROVINCE_ID" +
                       "   FROM " +
                       "   COMPANY_TBL " +
                       "   WHERE ID > 0 " + 
                       "   AND PROVINCE_ID IS NOT NULL ";
                       if(bankId!=null){
                          sql = sql +" AND BANK_ID=? ";
                       }
                       if(saleComId!=null){
                           sql = sql +" AND ID = ? ";
                       }
                       sql = sql + " )";
   
            }

                         
            sql  = sql + " ORDER BY P.COUNTRY_ID,P.SHORT_NAME";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            
            if(isInCompany){
                if(bankId!=null){
                    pstmt.setInt(++index, bankId);
                }
                if(saleComId!=null){
                    pstmt.setInt(++index, saleComId);
                }
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                index = 0;
                province.setId(rs.getInt(++index));
                province.setName(rs.getString(++index));
                province.setShortName(rs.getString(++index));
                province.setCountryId(rs.getInt(++index));
                province.setExclusiveKey(rs.getInt(++index));
                province.setCountryName(rs.getString(++index));
                list.add(province);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectAll");
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
     * get all province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Channel> searchAllChannel() throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Channel> list = new ArrayList<Channel>();
            String sql = " SELECT " +
            		     " P.ID," +
            		     " P.CHANNEL_NAME" +
            		     " FROM " +
            		     " CHANNEL_TBL P" + 
            		     "   WHERE P.STATUS = 1";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Channel channel = new Channel();
                index = 0;
                channel.setId(rs.getInt(++index));
                channel.setChannelName(rs.getString(++index));
                list.add(channel);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchAllChannel");
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
                logSQLException(e, "searchAllChannel");
                throw e;
            }
        }
    }
    
    /**
	 * 
	 * @auther xiangzq
	 * @version 1.0
	 * @since 1.0
	 * @param subSql
	 * @return List<Installation>
	 * @throws Exception
	 */
	public List<Channel> searchAllChannel(Channel channel, User loginUser,int intBegin,int intEnd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Start UOC
			List<Channel> list = new ArrayList<Channel>();
			String sql = "  SELECT "
					+ "	ID,"
					+ "	CHANNEL_NAME,"
					+ "	SNAME,"
					+ "	BALANCE,"
					+ "	ALERT_THRESHOLD,"
					+ "	SUSPEND_THRESHOLD,"
					+ "	DEPOSITED,"
					+ " INTERFACE_NAME,"
					+ "	STATUS "
					+ "	FROM "
					+ "( SELECT ROWNUM NO,"
					+ "  T.* "
					+ "  FROM"
					+ "  (SELECT "
					+ "	A.ID,"
					+ "	A.CHANNEL_NAME,"
					+ "	A.SNAME,"
					+ "	A.BALANCE,"
					+ "	A.ALERT_THRESHOLD,"
					+ "	A.SUSPEND_THRESHOLD,"
					+ "	A.DEPOSITED,"
					+ "	A.INTERFACE_NAME,"
					+ "	A.STATUS"
					+ "   FROM CHANNEL_TBL A"
					+ "   WHERE 0=0 ";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			sb.append(" ORDER BY A.ID ");
			sb.append(" )T ");
			sb.append(" WHERE ROWNUM<=? ");
			sb.append(" ) WHERE NO>? ");
			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setInt(++index, intEnd);
	        pstmt.setInt(++index, intBegin);
			rs = pstmt.executeQuery();
			Channel channelOut = null;
			while (rs.next()) {
				channelOut = new Channel();
				index = 0;
				channelOut.setId(rs.getInt(++index));
				channelOut.setChannelName(rs.getString(++index));
				channelOut.setSname(rs.getString(++index));
				channelOut.setBalance(rs.getFloat(++index));
				channelOut.setAlertThreshold(rs.getFloat(++index));
				channelOut.setSuspendThreshold(rs.getFloat(++index));
				channelOut.setDeposited(rs.getFloat(++index));
				channelOut.setInterfaceName(rs.getString(++index));
				channelOut.setStatus(rs.getInt(++index));
				list.add(channelOut);
			}
			return list;
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "searchAllChannel");
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
				logSQLException(e, "searchAllChannel");
				throw e;
			}
		}
	}
	
	
	 /**
		 * 
		 * @auther xiangzq
		 * @version 1.0
		 * @since 1.0
		 * @param subSql
		 * @return List<Installation>
		 * @throws Exception
		 */
		public List<ChannelAccumulate> searchAllChannelAccu(ChannelAccumulate channelAccu, User loginUser,int intBegin,int intEnd) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				// Start UOC
				List<ChannelAccumulate> list = new ArrayList<ChannelAccumulate>();
				String sql = "  SELECT "
						+ "	ID,"
						+ "	SNAME,"
						+ "	CHANNEL_ID,"
						+ "	CHANNEL_NAME,"
						+ "	AMOUNT,"
						+ "	USER_ID,"
						+ "	OP_TIME,"
						+ " BALANCE_SNAP,"
						+ "	TYPE, "
						+ " FULLNAME,"
						+ " COMMENTS "
						+ "	FROM "
						+ "( SELECT ROWNUM NO,"
						+ "  T.* "
						+ "  FROM"
						+ "  (SELECT "
						+ "	A.ID,"
						+ "	A.CHANNEL_ID,"
						+ "	A.AMOUNT,"
						+ "	A.USER_ID,"
						+ "	A.OP_TIME,"
						+ "	A.BALANCE_SNAP,"
						+ "	A.TYPE,"
						+ "	A.COMMENTS,"
						+ "	B.SNAME SNAME,"
						+ "	B.CHANNEL_NAME CHANNEL_NAME,"
						+ "	C.FULLNAME FULLNAME"
						+ "   FROM CHANNEL_TBL B,CHANNEL_ACCUMU_TBL A"
						+ "   LEFT JOIN USER_TBL C ON C.ID = A.USER_ID"
						+ "   WHERE A.CHANNEL_ID = B.ID ";
				int index = 0;
				StringBuffer sb = new StringBuffer(sql);
				sb.append(" AND A.OP_TIME >= ? ");
				sb.append(" AND A.OP_TIME <= ? ");
				if(channelAccu.getChannelSName() != null && !"".equals(channelAccu.getChannelSName())){
					sb.append(" AND B.SNAME like ? ");
				}
				if(channelAccu.getType() != null){
					sb.append(" AND A.TYPE = ? ");
				}
				if(channelAccu.getChannelId() != null){
					sb.append(" AND A.CHANNEL_ID = ? ");
				}
				
				
				sb.append(" ORDER BY A.ID ");
				sb.append(" )T ");
				sb.append(" WHERE ROWNUM<=? ");
				sb.append(" ) WHERE NO>? ");
				
				pstmt = conn.prepareStatement(sb.toString());
				if(channelAccu.getStartDate() != null && !"".equals(channelAccu.getStartDate())){
					pstmt.setString(++index, channelAccu.getStartDate() + " 00:00:00");
				}else{
					pstmt.setString(++index, TimeUtil.getTodayStart());
				}
				if(channelAccu.getEndDate() != null && !"".equals(channelAccu.getStartDate())){
					pstmt.setString(++index, channelAccu.getEndDate() + "24:00:00");
				}else{
					pstmt.setString(++index, TimeUtil.getTodayEnd());
				}
				
				if(channelAccu.getChannelSName() != null && !"".equals(channelAccu.getChannelSName())){
					pstmt.setString(++index, channelAccu.getChannelSName() + "%");
				}
				if(channelAccu.getType() != null){
					pstmt.setInt(++index, channelAccu.getType());
				}
				if(channelAccu.getChannelId() != null){
					pstmt.setInt(++index, channelAccu.getChannelId());
				}
				pstmt.setInt(++index, intEnd);
		        pstmt.setInt(++index, intBegin);
				rs = pstmt.executeQuery();
				ChannelAccumulate channelAccuOut = null;
				
				while (rs.next()) {
					channelAccuOut = new ChannelAccumulate();
					index = 0;
					channelAccuOut.setId(rs.getString(++index));
					channelAccuOut.setChannelSName(rs.getString(++index));
					channelAccuOut.setChannelId(rs.getInt(++index));
					channelAccuOut.setChannelName(rs.getString(++index));
					channelAccuOut.setAmount(rs.getFloat(++index));
					channelAccuOut.setUserId(rs.getInt(++index));
					channelAccuOut.setOpTime(rs.getString(++index));
					channelAccuOut.setBalanceSnap(rs.getFloat(++index));
					channelAccuOut.setType(rs.getInt(++index));
					channelAccuOut.setUserName(rs.getString(++index));
					channelAccuOut.setCommnets(rs.getString(++index));
					channelAccuOut.setBlance(channelAccuOut.getBalanceSnap() + channelAccuOut.getAmount());
					list.add(channelAccuOut);
				}
				return list;
				// End UOC
			} catch (SQLException e) {
				logSQLException(e, "searchAllChannelAccu");
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
					logSQLException(e, "searchAllChannelAccu");
					throw e;
				}
			}
		}
	
	/**
	 * 
	 * @auther xiangzq
	 * @version 1.0
	 * @since 1.0
	 * @param subSql
	 * @return List<Installation>
	 * @throws Exception
	 */
	public List<ChannelDetail> searchAllChannelDetail(Channel channel, User loginUser,int intBegin,int intEnd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Start UOC
			List<ChannelDetail> list = new ArrayList<ChannelDetail>();
			String sql = "  SELECT "
					+ "	ID,"
					+ "	NAME,"
					+ "	SNAME,"
					+ "	VALUE,"
					+ "	PROVINCE,"
					+ "	OPERATOR,"
					+ "	PRIORITY,"
					+ " DISCOUNT,"
					+ " COMMENTS,"
					+ "	STATUS "
					+ "	FROM "
					+ "( SELECT ROWNUM NO,"
					+ "  T.* "
					+ "  FROM"
					+ "  (SELECT "
					+ "	B.ID,"
					+ "	B.NAME,"
					+ "	B.SNAME,"
					+ "	B.VALUE,"
					+ "	C.NAME PROVINCE,"
					+ "	B.OPERATOR,"
					+ "	B.PRIORITY,"
					+ "	B.DISCOUNT,"
					+ "	B.COMMENTS,"
					+ "	B.STATUS"
					+ "   FROM CHANNEL_TBL A,CHANNEL_DETAIL_TBL B"
					+ "   LEFT JOIN PROVINCE_TBL C ON C.ID = B.PROVINCE"
					+ "   WHERE A.ID=B.CHANNEL_ID ";
			StringBuffer sb = new StringBuffer(sql);
			if(channel.getId() != null){
				sb.append(" AND B.CHANNEL_ID = ? ");
			}
			int index = 0;
			sb.append(" ORDER BY A.ID ");
			sb.append(" )T ");
			sb.append(" WHERE ROWNUM<=? ");
			sb.append(" ) WHERE NO>? ");
			pstmt = conn.prepareStatement(sb.toString());

			if(channel.getId() != null){
				pstmt.setInt(++index, channel.getId());
			}
			pstmt.setInt(++index, intEnd);
	        pstmt.setInt(++index, intBegin);
			rs = pstmt.executeQuery();
			ChannelDetail channelOut = null;
			while (rs.next()) {
				channelOut = new ChannelDetail();
				index = 0;
				channelOut.setId(rs.getInt(++index));
				channelOut.setName(rs.getString(++index));
				channelOut.setSname(rs.getString(++index));
				channelOut.setValue(rs.getInt(++index));
				channelOut.setProvince(rs.getString(++index));
				channelOut.setOperator(rs.getInt(++index));
				channelOut.setPriority(rs.getInt(++index));
				channelOut.setDiscount(rs.getFloat(++index));
				channelOut.setComments(rs.getString(++index));
				channelOut.setStatus(rs.getInt(++index));
				list.add(channelOut);
			}
			return list;
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "searchAllChannelDetail");
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
				logSQLException(e, "searchAllChannelDetail");
				throw e;
			}
		}
	}
	
	/**
	 * 
	 * @auther desionwang
	 * @version 1.0
	 * @since 1.0
	 * @return count
	 * @throws Exception
	 */
	public int getChannelCount(Channel channel, User loginUser) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			
			// Start UOC
			String sql = "  SELECT "
					+ "   count(*)"
					+ "   FROM CHANNEL_TBL"
					+ "   WHERE DELETED = 0 ";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "getChannelCount");
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
				logSQLException(e, "getChannelCount");
				throw e;
			}
		}
		return count;
	}
	
	/**
	 * 
	 * @auther desionwang
	 * @version 1.0
	 * @since 1.0
	 * @return count
	 * @throws Exception
	 */
	public int getChannelDetailCount(ChannelDetail channelDetail, User loginUser) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			
			// Start UOC
			String sql = "  SELECT "
					+ "   count(*)"
					+ "   FROM CHANNEL_DETAIL_TBL"
					+ "   WHERE 0=0 ";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();
			if (rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "getChannelDetailCount");
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
				logSQLException(e, "getChannelDetailCount");
				throw e;
			}
		}
		return count;
	}
	
	/**
	 * 
	 * @auther desionwang
	 * @version 1.0
	 * @since 1.0
	 * @return count
	 * @throws Exception
	 */
	public int getChannelAccuCount(ChannelAccumulate channelAccu, User loginUser) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			
			// Start UOC
			String sql = " SELECT "
					+ "   count(*)"
					+ "   FROM CHANNEL_ACCUMU_TBL A,CHANNEL_TBL B"
					+ "   WHERE A.CHANNEL_ID = B.ID";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			sb.append(" AND A.OP_TIME >= ? ");
			sb.append(" AND A.OP_TIME <= ? ");
			if(channelAccu.getChannelSName() != null && !"".equals(channelAccu.getChannelSName())){
				sb.append(" AND B.SNAME like ? ");
			}
			if(channelAccu.getType() != null){
				sb.append(" AND A.TYPE = ? ");
			}
			if(channelAccu.getChannelId() != null){
				sb.append(" AND A.CHANNEL_ID = ? ");
			}
			pstmt = conn.prepareStatement(sb.toString());
			if(channelAccu.getStartDate() != null && !"".equals(channelAccu.getStartDate())){
				pstmt.setString(++index, channelAccu.getStartDate());
			}else{
				pstmt.setString(++index, TimeUtil.getTodayStart());
			}
			if(channelAccu.getEndDate() != null && !"".equals(channelAccu.getStartDate())){
				pstmt.setString(++index, channelAccu.getEndDate());
			}else{
				pstmt.setString(++index, TimeUtil.getTodayEnd());
			}
			if(channelAccu.getChannelSName() != null && !"".equals(channelAccu.getChannelSName())){
				pstmt.setString(++index, channelAccu.getChannelSName() + "%");
			}
			if(channelAccu.getType() != null){
				pstmt.setInt(++index, channelAccu.getType());
			}
			if(channelAccu.getChannelId() != null){
				pstmt.setInt(++index, channelAccu.getChannelId());
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "getChannelCount");
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
				logSQLException(e, "getChannelCount");
				throw e;
			}
		}
		return count;
	}
    
    /**
     * get all province of the country.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   countryId
     * @return  List&ltProvince&gt
     * @throws  SQLException
     */
    public List<Province> searchProvinceByCountryId(Integer countryId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List <Province>list = new ArrayList<Province>();
            String sql = " SELECT " +
                         " B.ID," +
                         " B.NAME," +
                         " B.COUNTRY_ID," +
                         " C.SHORT_NAME" +
                         " FROM " +
                         " PROVINCE_TBL B,COUNTRY_TBL C  " +
                         " WHERE " +
                         " B.COUNTRY_ID = C.ID" +
                         " AND B.COUNTRY_ID = ?" +
                         " AND B.ID>0 " +
                         " ORDER BY COUNTRY_ID,B.SHORT_NAME";
            pstmt = conn.prepareStatement(sql);
            int index = 0;
            pstmt.setInt(++index, countryId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                Province province = new Province();
                index = 0;
                province.setId(rs.getInt(++index));
                province.setName(rs.getString(++index));
                province.setCountryId(rs.getInt(++index));
                province.setCountryName(rs.getString(++index));
                list.add(province);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchProvinceByCountryId");
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
                logSQLException(e, "searchProvinceByCountryId");
                throw e;
            }
        }
    }
    

    /**
     * get channel by id.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   channel
     * @return  channel
     * @throws  Exception
     */
    public Channel searchChannelById(Channel channel) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Channel channelOut = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                String sql = 
                           " SELECT " +
                           " EXCLUSIVE_KEY " +
                           " FROM " +
                           " CHANNEL_TBL " +
                           " WHERE " +
                           " ID = ? " +
                           " AND DELETED = 0" ;
                pstmt = conn.prepareStatement(sql);

                int index = 0;
                pstmt.setInt(++index, channel.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != channel.getExclusiveKey()) {
                        throw new ExclusiveException("record has been changed.");
                    }
                } else {
                    throw new RecordNoFoundException("record is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            
            
            String sql = 
                       " SELECT " +
            		   " ID," +
            		   " CHANNEL_NAME," +
            		   " SNAME," +
            		   " EXCLUSIVE_KEY" +
            		   " FROM " +
            		   " CHANNEL_TBL " +
            		   " WHERE ID=?" +
            		   " AND DELETED = 0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, channel.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
            	channelOut = new Channel();
                index = 0;
                channelOut.setId(rs.getInt(++index));
                channelOut.setChannelName(rs.getString(++index));
                channelOut.setSname(rs.getString(++index));
                channelOut.setExclusiveKey(rs.getInt(++index));
            }

            return channelOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "searchChannelById");
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
                logSQLException(e, "searchChannelById");
                throw e;
            }
        }
    }

    /**
     * get channel count by name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCountByChannelName(Channel channel) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " CHANNEL_TBL " +
                       " WHERE " +
                       " CHANNEL_NAME = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, channel.getChannelName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByChannelName");
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
                logSQLException(e, "getCountByChannelName");
                throw e;
            }
        }

        return count;
    }
    
    
    /**
     * get channel count by name
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCountBySName(Channel channel) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " CHANNEL_TBL " +
                       " WHERE " +
                       " SNAME = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, channel.getSname());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountBySName");
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
                logSQLException(e, "getCountBySName");
                throw e;
            }
        }

        return count;
    }

    /**
     * modify province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  Exception
     */
    public void modifyProvinceInfo(Province province) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            int index = 0;

            // Start UOC
            if (exclusiveCheck) {
                    String sql = 
                               " SELECT " +
                               " EXCLUSIVE_KEY " +
                               " FROM PROVINCE_TBL " +
                               " WHERE ID = ? " +
                               " AND ID>0 " +
                               " FOR UPDATE NOWAIT";
                    pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, province.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != province.getExclusiveKey()) {
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
              
            Integer nowExclusiveKey=province.getExclusiveKey();
            province.setExclusiveKey(++nowExclusiveKey);//set the exclusive key = exclusiveKey+1
            
            String sql = 
                         " UPDATE PROVINCE_TBL  SET " +
            		     " NAME=?," +
            		     " SHORT_NAME=?, " +
            		     " COUNTRY_ID=?," +
            		     " EXCLUSIVE_KEY=? " +
            		     " WHERE " +
            		     " ID = ?" +
            		     " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, province.getName());
            pstmt.setString(++index, province.getShortName());
            pstmt.setInt(++index, province.getCountryId());
            pstmt.setInt(++index, province.getExclusiveKey());
            pstmt.setInt(++index, province.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateProvinceInfo");
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
                logSQLException(e, "updateProvinceInfo");
                throw e;
            }
        }
    }
    
    public boolean checkChannelBlance(ChannelAccumulate channelAccumu) throws Exception {
    	PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
                int index = 0;
                Float balanceSnap = null;
                // Start UOC
                String sql = 
                               " SELECT " +
                               " DEPOSITED, " +
                               " BALANCE " +
                               " FROM CHANNEL_TBL " +
                               " WHERE ID = ? " +
                               " AND DELETED = 0 " +
                               " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, channelAccumu.getChannelId());
                rs = pstmt.executeQuery();

                
                if (rs.next()) {
                	balanceSnap = rs.getFloat("BALANCE");
                } else {
                    throw new RecordNoFoundException("recode is not exsit.");
                }
                if(balanceSnap != null && balanceSnap + channelAccumu.getAmount() < 0){
                	return false;
                }
                // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getChannelBlance");
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
                logSQLException(e, "getChannelBlance");
                throw e;
            }
        }
        return true;
    }
    
    /**
     * channel account accumulate.
     * @auther  desionwang
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @throws  Exception
     */
    public void Accumulate(ChannelAccumulate channelAccumu) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
                int index = 0;
                Float balanceSnap = null;
                Float deposited = null;
                // Start UOC
                String sql = 
                               " SELECT " +
                               " DEPOSITED, " +
                               " BALANCE " +
                               " FROM CHANNEL_TBL " +
                               " WHERE ID = ? " +
                               " AND DELETED = 0 " +
                               " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, channelAccumu.getChannelId());
                rs = pstmt.executeQuery();

                
                if (rs.next()) {
                	balanceSnap = rs.getFloat("BALANCE");
                	deposited = rs.getFloat("DEPOSITED");
                } else {
                    throw new RecordNoFoundException("recode is not exsit.");
                }
                
                if (rs != null) {
                    rs.close();
                }
                
                if (pstmt != null) {
                    pstmt.close();
                }
              
            
            sql = 
                         " UPDATE CHANNEL_TBL SET " +
                         " DEPOSITED=? ," +
            		     " BALANCE=? " +
            		     " WHERE " +
            		     " ID = ?" +
            		     " AND DELETED = 0 ";
            pstmt = conn.prepareStatement(sql);
            
            index = 0;
            
            pstmt.setFloat(++index, channelAccumu.getAmount() + deposited);
            pstmt.setFloat(++index, channelAccumu.getAmount() + balanceSnap);
            pstmt.setInt(++index, channelAccumu.getChannelId());

            pstmt.executeUpdate();
            
            sql = 
                   " INSERT INTO CHANNEL_ACCUMU_TBL (" +
         		   " ID," +
         		   " AMOUNT," +
         		   " CHANNEL_ID," +
         		   " USER_ID," +
         		   " OP_TIME," +
         		   " BALANCE_SNAP," +
         		   " TYPE," +
         		   " COMMENTS" +
         		   " ) VALUES(?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql);

            index = 0;
            pstmt.setString(++index, channelAccumu.getId());
            pstmt.setFloat(++index, channelAccumu.getAmount());
            pstmt.setInt(++index, channelAccumu.getChannelId());
            pstmt.setInt(++index, channelAccumu.getUserId());
            pstmt.setString(++index, channelAccumu.getOpTime());
            pstmt.setFloat(++index, balanceSnap);
            pstmt.setInt(++index, channelAccumu.getType());
            pstmt.setString(++index, channelAccumu.getCommnets());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "ChannelAccumulate");
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
                logSQLException(e, "ChannelAccumulate");
                throw e;
            }
        }
    }

    /**
     * get the city count of province.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCityCountOfProvince(Province province) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM  " +
                       " CITY_TBL A, PROVINCE_TBL  B " +
                       " WHERE " +
                       " A.PROVINCE_ID = B.ID " +
                       " AND B.ID= ?  ";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "cityCounts");
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
                logSQLException(e, "cityCounts");
                throw e;
            }
        }

        return count;
    }

    /**
     * get province count
     * by name except self
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Province
     * @return  int
     * @throws  SQLException
     */
    public int getCountByProvinceNameModify(Province province) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                      " SELECT " +
            		  " COUNT(*) " +
            		  " FROM " +
            		  " PROVINCE_TBL A " +
            		  " WHERE EXISTS " + 
                      " ( " +
                      "  SELECT " +
                      "  NAME " +
                      "  FROM " +
                      "  PROVINCE_TBL B " +
                      "  WHERE " +
                      "  A.ID != B.ID " +
                      "  AND B.ID =? " +
                      " ) "  +
                      " AND A.NAME= ? " +
                      " AND A.ID>0";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, province.getId());
            pstmt.setString(++index, province.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProvinceNameModify");
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
                logSQLException(e, "getCountByProvinceNameModify");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get province by id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   Integer
     * @return  int
     * @throws  Exception
     */
    public int getCountByProvinceId(Integer provinceId) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count =0;

        try {
            // Start UOC
            
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " PROVINCE_TBL " +
                       " WHERE " +
                       " ID=?" +
                       " AND ID>0 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, provinceId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count=rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByProvinceId");
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
                logSQLException(e, "getCountByProvinceId");
                throw e;
            }
        }
    }

}
