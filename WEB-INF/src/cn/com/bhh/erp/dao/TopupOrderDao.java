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
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.InstallationApply;
import cn.com.bhh.erp.entity.TopupRecord;
import cn.com.bhh.erp.entity.User;

/**
 * installation dao
 * 
 * @author liugd
 * @version 1.0
 * @since 1.0
 */
public class TopupOrderDao extends BaseDao {

	public TopupOrderDao(Connection conn) {
		super(conn);
	}

	public int getTopupCount(TopupRecord order, User loginuser)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// Start UOC
			String sql = " SELECT " + " COUNT(*) " + " FROM "
					+ " TOPUP_ORDER_TBL A " + " WHERE  " + " 0 = 0 ";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			if (order.getPhoneNum() != null &&!"".equals(order.getPhoneNum())) {
				sb.append(" AND A.PHONE_NO = ? ");
			}
			if (order.getTopupPhone() != null) {
				sb.append(" AND A.TOPUP_PHONE = ? ");
			}
			if (order.getSum() != null) {
				sb.append(" AND A.SUM = ? ");
			}
			if (order.getRequestNo() != null &&!"".equals(order.getRequestNo())) {
				sb.append(" AND A.REQUEST_NO = ? ");
			}

			if (order.getProvince() != null) {
				sb.append(" AND A.PROVINCE = ? ");
			}

			if (order.getStatus() != null) {
				sb.append(" AND A.STATUS = ? ");
			}

			if (order.getSource() != null) {
				sb.append(" AND A.SOURCE = ? ");
			}
			
			if (order.getNotify() != null) {
				sb.append(" AND A.NOTIFY = ? ");
			}

			if (order.getOperator() != null) {
				sb.append(" AND A.OPERATOR = ? ");
			}

			if (order.getProxy() != null &&!"".equals(order.getProxy())) {
				sb.append(" AND A.PROXY = ? ");
			}

			if (order.getStartDate() != null &&!"".equals(order.getStartDate())) {
				sb.append(" AND A.CREATE_TIME >= ? ");
			}

			if (order.getEndDate() != null &&!"".equals(order.getEndDate())) {
				sb.append(" AND A.CREATE_TIME <= ? ");
			}

			pstmt = conn.prepareStatement(sb.toString());

			if (order.getPhoneNum() != null &&!"".equals(order.getPhoneNum())) {
				pstmt.setString(++index, order.getPhoneNum());
			}
			if (order.getTopupPhone() != null) {
				pstmt.setLong(++index, order.getTopupPhone());
			}
			if (order.getSum() != null) {
				pstmt.setInt(++index, order.getSum());
			}
			if (order.getRequestNo() != null &&!"".equals(order.getRequestNo())) {
				pstmt.setString(++index, order.getRequestNo() );
			}

			if (order.getProvince() != null) {
				pstmt.setInt(++index, order.getProvince());
			}

			if (order.getStatus() != null) {
				pstmt.setInt(++index, order.getStatus());
			}

			if (order.getSource() != null) {
				pstmt.setInt(++index, order.getSource());
			}
			
			if (order.getNotify() != null) {
				pstmt.setInt(++index, order.getNotify());
			}

			if (order.getOperator() != null) {
				pstmt.setInt(++index, order.getOperator());
			}

			if (order.getProxy() != null) {
				pstmt.setInt(++index, order.getProxy());
			}

			if (order.getStartDate() != null &&!"".equals(order.getStartDate())) {
				pstmt.setString(++index, order.getStartDate());
			}

			if (order.getEndDate() != null &&!"".equals(order.getEndDate())) {
				pstmt.setString(++index, order.getEndDate());
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

	/**
	 * 
	 * @auther xiangzq
	 * @version 1.0
	 * @since 1.0
	 * @param subSql
	 * @return List<Installation>
	 * @throws Exception
	 */
	public List<TopupRecord> serchUseStatus(TopupRecord order, User loginUser,int intBegin,int intEnd) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Start UOC
			List<TopupRecord> list = new ArrayList<TopupRecord>();
			String sql = "  SELECT "
					+ "	PHONE_NO,"
					+ "	TOPUP_PHONE,"
					+ "	PROVINCE,"
					+ "	SUM,"
					+ "	SYSTEM_NO,"
					+ "	TOPUP_NO,"
					+ "	REQUEST_NO,"
					+ "	STATUS,"
					+ "	CREATE_TIME,"
					+ "	UPDATE_TIME,"
					+ "	NOTIFY,"
					+ "	SOURCE,"
					+ "	PROXY,"
					+ "	OPERATOR,"
					+ "	SALE_PRICE,"
					+ "	IN_PRICE,"
					+ "	PROFIT, "
					+ " PROVINCE_NAME "
					+ "	FROM "
					+ "( SELECT ROWNUM NO,"
					+ "  T.* "
					+ "  FROM"
					+ "  (SELECT "
					+ "	A.PHONE_NO,"
					+ "	A.TOPUP_PHONE,"
					+ "	A.PROVINCE,"
					+ "	A.SUM,"
					+ "	A.SYSTEM_NO,"
					+ "	A.TOPUP_NO,"
					+ "	A.REQUEST_NO,"
					+ "	A.STATUS,"
					+ "	A.CREATE_TIME,"
					+ "	A.UPDATE_TIME,"
					+ "	A.NOTIFY,"
					+ "	A.SOURCE,"
					+ "	B.CHANNEL_NAME PROXY,"
					+ "	A.OPERATOR,"
					+ "	A.SALE_PRICE,"
					+ "	A.IN_PRICE,"
					+ "	A.PROFIT,"
					+ " P.NAME PROVINCE_NAME"
					+ "   FROM TOPUP_ORDER_TBL A"
					+ "       LEFT OUTER JOIN PROVINCE_TBL P ON A.PROVINCE=P.ID"
					+ "       LEFT OUTER JOIN CHANNEL_TBL B ON A.PROXY=B.ID"
					+ "   WHERE 0=0 ";
			StringBuffer sb = new StringBuffer(sql);
			int index = 0;
			if (order.getPhoneNum() != null &&!"".equals(order.getPhoneNum())) {
				sb.append(" AND A.PHONE_NO = ? ");
			}
			if (order.getTopupPhone() != null) {
				sb.append(" AND A.TOPUP_PHONE = ? ");
			}
			if (order.getSum() != null) {
				sb.append(" AND A.SUM = ? ");
			}
			if (order.getRequestNo() != null &&!"".equals(order.getRequestNo())) {
				sb.append(" AND A.REQUEST_NO = ? ");
			}

			if (order.getProvince() != null) {
				sb.append(" AND A.PROVINCE = ? ");
			}

			if (order.getStatus() != null) {
				sb.append(" AND A.STATUS = ? ");
			}

			if (order.getSource() != null) {
				sb.append(" AND A.SOURCE = ? ");
			}
			
			if (order.getNotify() != null) {
				sb.append(" AND A.NOTIFY = ? ");
			}

			if (order.getOperator() != null) {
				sb.append(" AND A.OPERATOR = ? ");
			}

			if (order.getProxy() != null &&!"".equals(order.getProxy())) {
				sb.append(" AND A.PROXY = ? ");
			}

			if (order.getStartDate() != null &&!"".equals(order.getStartDate())) {
				sb.append(" AND A.CREATE_TIME >= ? ");
			}

			if (order.getEndDate() != null &&!"".equals(order.getEndDate())) {
				sb.append(" AND A.CREATE_TIME <= ? ");
			}
			sb.append(" ORDER BY A.CREATE_TIME DESC");
			sb.append(" )T ");
			sb.append(" WHERE ROWNUM<=? ");
			sb.append(" ) WHERE NO>? ");
			pstmt = conn.prepareStatement(sb.toString());
			TopupRecord topupOut = null;
			if (order.getPhoneNum() != null &&!"".equals(order.getPhoneNum())) {
				pstmt.setString(++index, order.getPhoneNum());
			}
			if (order.getTopupPhone() != null) {
				pstmt.setLong(++index, order.getTopupPhone());
			}
			if (order.getSum() != null) {
				pstmt.setInt(++index, order.getSum());
			}
			if (order.getRequestNo() != null &&!"".equals(order.getRequestNo())) {
				pstmt.setString(++index, order.getRequestNo());
			}

			if (order.getProvince() != null) {
				pstmt.setInt(++index, order.getProvince());
			}

			if (order.getStatus() != null) {
				pstmt.setInt(++index, order.getStatus());
			}

			if (order.getSource() != null) {
				pstmt.setInt(++index, order.getSource());
			}

			if (order.getNotify() != null) {
				pstmt.setInt(++index, order.getNotify());
			}
			
			if (order.getOperator() != null) {
				pstmt.setInt(++index, order.getOperator());
			}

			if (order.getProxy() != null) {
				pstmt.setInt(++index, order.getProxy());
			}

			if (order.getStartDate() != null &&!"".equals(order.getStartDate())) {
				pstmt.setString(++index, order.getStartDate());
			}

			if (order.getEndDate() != null &&!"".equals(order.getEndDate())) {
				pstmt.setString(++index, order.getEndDate());
			}
			 pstmt.setInt(++index, intEnd);
	         pstmt.setInt(++index, intBegin);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				topupOut = new TopupRecord();
				index = 0;
				topupOut.setPhoneNum(rs.getString(++index));
				topupOut.setTopupPhone(rs.getLong(++index));
				topupOut.setProvince(rs.getInt(++index));
				topupOut.setSum(rs.getInt(++index));
				topupOut.setSystemNo(rs.getString(++index));
				topupOut.setTopupNo(rs.getString(++index));
				topupOut.setRequestNo(rs.getString(++index));
				topupOut.setStatus(rs.getInt(++index));
				topupOut.setCreateTime(rs.getString(++index));
				topupOut.setUpdateTime(rs.getString(++index));
				topupOut.setNotify(rs.getInt(++index));
				topupOut.setSource(rs.getInt(++index));
				topupOut.setProxy(rs.getInt(++index));
				topupOut.setOperator(rs.getInt(++index));
				topupOut.setSalePrice(rs.getFloat(++index));
				topupOut.setInPrice(rs.getFloat(++index));
				topupOut.setProfit(rs.getFloat(++index));
				topupOut.setProvinceName(rs.getString(++index));
				list.add(topupOut);
			}
			return list;
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "serchTopupOrder");
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
				logSQLException(e, "serchTopupOrder");
				throw e;
			}
		}
	}

	
	/**
	 * UPDATE INSTALLATION
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @throws Exception
	 */
	public void ModifyStatus(List<String> affirmChkList, int statusType) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// Start UOC
			String sql = "UPDATE " + " TOPUP_ORDER_TBL" + " SET "
					+ "   UPDATE_TIME = ?,"
					+ "   STATUS = ?" + " WHERE "
					+ "   TOPUP_ORDER_TBL.SYSTEM_NO = ?";
			pstmt = conn.prepareStatement(sql);
			int index;
			for(String sysid:affirmChkList){
				index = 0;
				pstmt.setString(++index, TimeUtil.getNow());
				pstmt.setInt(++index, statusType);
				pstmt.setString(++index, sysid);
				pstmt.addBatch();
			}
			
			
			pstmt.executeBatch();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "ModifyStatus");
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
				logSQLException(e, "ModifyStatus");
				throw e;
			}
		}
	}
	/**
	 * insert
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @throws Exception
	 */
	public void create(Installation installation) throws Exception {
		PreparedStatement pstmt = null;

		try {
			// Start UOC
			String sql = "INSERT INTO INSTALLATION_TBL("
					+ "   PRODUCT_ID,"
					+ "   MANUFACTURE_NO,"
					+ "   CUSTOMER_ID,"
					+ "   SALES_CONTRACT_COMP_ID,"
					+ "   INSTALL_COMP_ID,"
					+ "   INSTALLER,"
					+ "   INSTALLER_ID,"
					+ "   FIRST_REPAIR_COMPANY_ID,"
					+ "   NOW_REPAIR_COMPANY_ID,"
					+ "   INDENTURE_NO,"
					+ "   FOB_DATE,"
					+ "   INSTALL_DATE,"
					+ "   OPEN_DATE,"
					+ "   ACCEPT_DATE,"
					+ "   GUARANTEE_START_DATE,"
					+ "   GUARANTEE_END_DATE,"
					+ "   GUARANTEE_PERIOD,"
					+ "   BRANCH_COMPANY_NAME,"
					+ "   INSTALL_PLACE,"
					+ "   INST_PLACE_TYPE_ID,"
					+ "   USE_STATUS_ID,"
					+ "   PURPOSE,"
					+ "   CONTACT,"
					+ "   OFFICE_PHONE,"
					+ "   MOBILE_PHONE,"
					+ "   FAX,"
					+ "   EMAIL,"
					+ "   BRM_EP_VER,"
					+ "   BV_EP_VER,"
					+ "   BHCL_EP_VER,"
					+ "   TRCL_EP_VER,"
					+ "   KEY_NO,"
					+ "   NOTE,"
					+ "   OP_SYS,"
					+ "   OS_PERMIT,"
					+ "   PLATFORM,"
					+ "   PLATFORM_REV,"
					+ "   MCU,"
					+ "   BV,"
					+ "   HCM,"
					+ "   JPR,"
					+ "   SPR,"
					+ "   DESKEY,"
					+ "   CREATE_TIME,"
					+ "   CREATOR_ID,"
					+ "   MODIFY_TIME,"
					+ "   MODIFIER_ID"
					+ " )VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ " ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			int index = 0;
			pstmt.setInt(++index, installation.getProductId());
			pstmt.setString(++index, installation.getManufactureNo());
			pstmt.setInt(++index, installation.getCustomerId());
			pstmt.setInt(++index, installation.getSaleContractCompId());
			pstmt.setInt(++index, installation.getInstallCompId());
			pstmt.setString(++index, installation.getInstaller());
			pstmt.setString(++index, installation.getInstallerId());
			pstmt.setInt(++index, installation.getInstallCompId());
			pstmt.setInt(++index, installation.getInstallCompId());
			pstmt.setString(++index, installation.getIndentureNo());
			pstmt.setString(++index, installation.getFobDate());
			pstmt.setString(++index, installation.getInstallDate());
			pstmt.setString(++index, installation.getOpenDate());
			pstmt.setString(++index, installation.getAcceptDate());
			pstmt.setString(++index, installation.getGuaranteeStartDate());
			pstmt.setString(++index, installation.getGuaranteeEndDate());
			pstmt.setString(++index, installation.getGuaranteePeriod());
			pstmt.setString(++index, installation.getBranchCompanyName());
			pstmt.setString(++index, installation.getInstallPlace());
			pstmt.setInt(++index, installation.getInstPlaceTypeId());
			pstmt.setInt(++index, installation.getUseStatusId());
			pstmt.setInt(++index, installation.getPurpose());
			pstmt.setString(++index, installation.getContact());
			pstmt.setString(++index, installation.getOfficePhone());
			pstmt.setString(++index, installation.getMobilePhone());
			pstmt.setString(++index, installation.getFax());
			pstmt.setString(++index, installation.getEmail());
			pstmt.setString(++index, installation.getBrmEpVer());
			pstmt.setString(++index, installation.getBvEpVer());
			pstmt.setString(++index, installation.getBhclEpVer());
			pstmt.setString(++index, installation.getTrclEpVer());

			pstmt.setString(++index, installation.getKeyNo());
			pstmt.setString(++index, installation.getNote());
			pstmt.setBigDecimal(++index, installation.getOs());
			pstmt.setBigDecimal(++index, installation.getOsPermitId());
			pstmt.setInt(++index, installation.getPlatform());
			pstmt.setString(++index, installation.getPlatformRev());
			pstmt.setString(++index, installation.getMcu());
			pstmt.setString(++index, installation.getBv());
			pstmt.setString(++index, installation.getHcm());
			pstmt.setString(++index, installation.getJpr());
			pstmt.setString(++index, installation.getSpr());
			pstmt.setString(++index, installation.getDeskey());
			pstmt.setString(++index, installation.getCreateTime());
			pstmt.setInt(++index, installation.getCreatorId());
			pstmt.setString(++index, installation.getModifyTime());
			pstmt.setInt(++index, installation.getModifierId());

			pstmt.executeUpdate();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "insert installation");
			throw e;
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				logSQLException(e, "insert installation");
				throw e;
			}
		}
	}

	/**
	 * UPDATE INSTALLATION
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @param modifyFlg
	 *            0:UPDATE INSTALLATION;1:HISTORY CREATE,UPDATE INSTALLATION
	 * @throws Exception
	 */
	public void modify(Installation installation, int modifyFlg)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int index = 0;
			// Start UOC
			if (exclusiveCheck) {
				String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++index, installation.getId());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("exclusive_key") != installation
							.getExclusiveKey()) {
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

			Integer nowExclusiveKey = installation.getExclusiveKey();
			installation.setExclusiveKey(++nowExclusiveKey);// set the exclusive
															// key =
															// exclusiveKey+1

			String sql = "UPDATE " + " INSTALLATION_TBL" + " SET "
					+ "   NOW_REPAIR_COMPANY_ID = ?,"
					+ "   BRANCH_COMPANY_NAME = ?," + "   INSTALL_PLACE = ?,"
					+ "   INST_PLACE_TYPE_ID = ?," + "   USE_STATUS_ID = ?,"
					+ "   PURPOSE = ?," + "   CONTACT = ?,"
					+ "   OFFICE_PHONE = ?," + "   MOBILE_PHONE = ?,"
					+ "   FAX = ?," + "   EMAIL = ?," + "   EXCLUSIVE_KEY = ?";

			if (modifyFlg == 1) {
				sql = sql + "   ," + "   START_DATE = ?";
			}
			if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
				sql = sql + "   ," + "   CUSTOMER_ID = ?,"
						+ "   SALES_CONTRACT_COMP_ID = ?,"
						+ "   INSTALL_COMP_ID = ?," + "   INSTALLER = ?,"
						+ "   INSTALLER_ID = ?,"
						+ "   FIRST_REPAIR_COMPANY_ID = ?,"
						+ "   INDENTURE_NO = ?," + "   FOB_DATE = ?,"
						+ "   INSTALL_DATE = ?," + "   OPEN_DATE = ?,"
						+ "   ACCEPT_DATE = ?,"
						+ "   GUARANTEE_START_DATE = ?,"
						+ "   GUARANTEE_END_DATE = ?,"
						+ "   GUARANTEE_PERIOD = ?," + "   BRM_EP_VER = ?,"
						+ "   BV_EP_VER = ?," + "   BHCL_EP_VER = ?,"
						+ "   TRCL_EP_VER = ?," + "   KEY_NO = ?,"
						+ "   NOTE = ?," + "   OP_SYS = ?,"
						+ "   OS_PERMIT = ?," + "   PLATFORM = ?,"
						+ "   PLATFORM_REV = ?," + "   MCU = ?," + "   BV = ?,"
						+ "   HCM = ?," + "   JPR = ?," + "   SPR = ?,"
						+ "   DESKEY = ?," + "   MODIFY_TIME = ?,"
						+ "   MODIFIER_ID = ?";
			}

			sql = sql + " WHERE " + "     INSTALLATION_TBL.ID = ?";
			pstmt = conn.prepareStatement(sql);

			index = 0;
			pstmt.setInt(++index, installation.getNowRepairCompanyId());
			pstmt.setString(++index, installation.getBranchCompanyName());
			pstmt.setString(++index, installation.getInstallPlace());
			pstmt.setInt(++index, installation.getInstPlaceTypeId());
			pstmt.setInt(++index, installation.getUseStatusId());
			pstmt.setInt(++index, installation.getPurpose());
			pstmt.setString(++index, installation.getContact());
			pstmt.setString(++index, installation.getOfficePhone());
			pstmt.setString(++index, installation.getMobilePhone());
			pstmt.setString(++index, installation.getFax());
			pstmt.setString(++index, installation.getEmail());
			pstmt.setInt(++index, installation.getExclusiveKey());
			if (modifyFlg == 1) {
				pstmt.setString(++index, TimeUtil.getNowNextDay());
			}
			if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
				pstmt.setInt(++index, installation.getCustomerId());
				pstmt.setInt(++index, installation.getSaleContractCompId());
				pstmt.setInt(++index, installation.getInstallCompId());
				pstmt.setString(++index, installation.getInstaller());
				pstmt.setString(++index, installation.getInstallerId());
				pstmt.setInt(++index, installation.getFirstRepairCompanyId());
				pstmt.setString(++index, installation.getIndentureNo());
				pstmt.setString(++index, installation.getFobDate());
				pstmt.setString(++index, installation.getInstallDate());
				pstmt.setString(++index, installation.getOpenDate());
				pstmt.setString(++index, installation.getAcceptDate());
				pstmt.setString(++index, installation.getGuaranteeStartDate());
				pstmt.setString(++index, installation.getGuaranteeEndDate());
				pstmt.setString(++index, installation.getGuaranteePeriod());
				pstmt.setString(++index, installation.getBrmEpVer());
				pstmt.setString(++index, installation.getBvEpVer());
				pstmt.setString(++index, installation.getBhclEpVer());
				pstmt.setString(++index, installation.getTrclEpVer());
				pstmt.setString(++index, installation.getKeyNo());
				pstmt.setString(++index, installation.getNote());
				pstmt.setBigDecimal(++index, installation.getOs());
				pstmt.setBigDecimal(++index, installation.getOsPermitId());
				pstmt.setInt(++index, installation.getPlatform());
				pstmt.setString(++index, installation.getPlatformRev());
				pstmt.setString(++index, installation.getMcu());
				pstmt.setString(++index, installation.getBv());
				pstmt.setString(++index, installation.getHcm());
				pstmt.setString(++index, installation.getJpr());
				pstmt.setString(++index, installation.getSpr());
				pstmt.setString(++index, installation.getDeskey());
				pstmt.setString(++index, installation.getModifyTime());
				pstmt.setInt(++index, installation.getModifierId());
			}
			pstmt.setInt(++index, installation.getId());

			pstmt.executeUpdate();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "modify");
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
				logSQLException(e, "modify");
				throw e;
			}
		}
	}

	/**
	 * UPDATE INSTALLATION by upload
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @param modifyFlg
	 *            0:UPDATE INSTALLATION;1:HISTORY CREATE,UPDATE INSTALLATION
	 * @throws Exception
	 */
	public void modifyByUpload(Installation installation, int modifyFlg)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int index = 0;
			// Start UOC
			if (exclusiveCheck) {
				String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++index, installation.getId());
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("exclusive_key") != installation
							.getExclusiveKey()) {
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

			Integer nowExclusiveKey = installation.getExclusiveKey();
			installation.setExclusiveKey(++nowExclusiveKey);// set the exclusive
															// key =
															// exclusiveKey+1

			String sql = "UPDATE " + " INSTALLATION_TBL" + " SET "
					+ "   NOW_REPAIR_COMPANY_ID = ?,"
					+ "   BRANCH_COMPANY_NAME = ?," + "   INSTALL_PLACE = ?,"
					+ "   INST_PLACE_TYPE_ID = ?," + "   USE_STATUS_ID = ?,"
					+ "   PURPOSE = ?," + "   CONTACT = ?,"
					+ "   OFFICE_PHONE = ?," + "   MOBILE_PHONE = ?,"
					+ "   FAX = ?," + "   EMAIL = ?," + "   EXCLUSIVE_KEY = ?";

			if (modifyFlg == 1) {
				sql = sql + "   ," + "   START_DATE = ?";
			}
			if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
				sql = sql + "   ," + "   CUSTOMER_ID = ?,"
						+ "   SALES_CONTRACT_COMP_ID = ?,"
						+ "   INSTALL_COMP_ID = ?," + "   INSTALLER = ?,"
						+ "   INSTALLER_ID = ?,"
						+ "   FIRST_REPAIR_COMPANY_ID = ?,"
						+ "   INDENTURE_NO = ?," + "   FOB_DATE = ?,"
						+ "   INSTALL_DATE = ?," + "   OPEN_DATE = ?,"
						+ "   ACCEPT_DATE = ?,"
						+ "   GUARANTEE_START_DATE = ?,"
						+ "   GUARANTEE_END_DATE = ?,"
						+ "   GUARANTEE_PERIOD = ?," + "   BRM_EP_VER = ?,"
						+ "   BV_EP_VER = ?," + "   KEY_NO = ?,"
						+ "   NOTE = ?," + "   OP_SYS = ?,"
						+ "   OS_PERMIT = ?," + "   PLATFORM = ?,"
						+ "   PLATFORM_REV = ?," + "   MCU = ?," + "   BV = ?,"
						+ "   HCM = ?," + "   JPR = ?," + "   SPR = ?,"
						+ "   DESKEY = ?," + "   MODIFY_TIME = ?,"
						+ "   MODIFIER_ID = ?";
			}

			sql = sql + " WHERE " + "     INSTALLATION_TBL.ID = ?";
			pstmt = conn.prepareStatement(sql);

			index = 0;
			pstmt.setInt(++index, installation.getNowRepairCompanyId());
			pstmt.setString(++index, installation.getBranchCompanyName());
			pstmt.setString(++index, installation.getInstallPlace());
			pstmt.setInt(++index, installation.getInstPlaceTypeId());
			pstmt.setInt(++index, installation.getUseStatusId());
			pstmt.setInt(++index, installation.getPurpose());
			pstmt.setString(++index, installation.getContact());
			pstmt.setString(++index, installation.getOfficePhone());
			pstmt.setString(++index, installation.getMobilePhone());
			pstmt.setString(++index, installation.getFax());
			pstmt.setString(++index, installation.getEmail());
			pstmt.setInt(++index, installation.getExclusiveKey());
			if (modifyFlg == 1) {
				pstmt.setString(++index, TimeUtil.getNowNextDay());
			}
			if (installation.getAffirmFlag().compareTo(new Integer("0")) == 0) {
				pstmt.setInt(++index, installation.getCustomerId());
				pstmt.setInt(++index, installation.getSaleContractCompId());
				pstmt.setInt(++index, installation.getInstallCompId());
				pstmt.setString(++index, installation.getInstaller());
				pstmt.setString(++index, installation.getInstallerId());
				pstmt.setInt(++index, installation.getFirstRepairCompanyId());
				pstmt.setString(++index, installation.getIndentureNo());
				pstmt.setString(++index, installation.getFobDate());
				pstmt.setString(++index, installation.getInstallDate());
				pstmt.setString(++index, installation.getOpenDate());
				pstmt.setString(++index, installation.getAcceptDate());
				pstmt.setString(++index, installation.getGuaranteeStartDate());
				pstmt.setString(++index, installation.getGuaranteeEndDate());
				pstmt.setString(++index, installation.getGuaranteePeriod());
				pstmt.setString(++index, installation.getBrmEpVer());
				pstmt.setString(++index, installation.getBvEpVer());
				pstmt.setString(++index, installation.getKeyNo());
				pstmt.setString(++index, installation.getNote());
				pstmt.setBigDecimal(++index, installation.getOs());
				pstmt.setBigDecimal(++index, installation.getOsPermitId());
				pstmt.setInt(++index, installation.getPlatform());
				pstmt.setString(++index, installation.getPlatformRev());
				pstmt.setString(++index, installation.getMcu());
				pstmt.setString(++index, installation.getBv());
				pstmt.setString(++index, installation.getHcm());
				pstmt.setString(++index, installation.getJpr());
				pstmt.setString(++index, installation.getSpr());
				pstmt.setString(++index, installation.getDeskey());
				pstmt.setString(++index, installation.getModifyTime());
				pstmt.setInt(++index, installation.getModifierId());
			}
			pstmt.setInt(++index, installation.getId());

			pstmt.executeUpdate();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "modify");
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
				logSQLException(e, "modify");
				throw e;
			}
		}
	}

	

	/**
	 * modify installation affirmFlg,deleted
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @throws Exception
	 */
	public void modifyState(Integer id, Integer exclusiveKey, int modifyType,
			Integer modifyFlg, String timeNow, Integer userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int index = 0;
			// Start UOC
			if (exclusiveCheck) {
				String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++index, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("exclusive_key") != exclusiveKey) {
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

			String sql = "UPDATE " + " INSTALLATION_TBL" + " SET "
					+ "   EXCLUSIVE_KEY = ?";

			if (modifyType == 1) {
				if (modifyFlg.compareTo(new Integer(1)) == 0) {
					sql = sql + "   ," + "   AFFIRM_FLAG = ?,"
							+ "   AFFIRMANT_ID = ?," + "   AFFIRM_TIME = ?";
				} else {
					sql = sql + "   ," + "   AFFIRM_FLAG = ?,"
							+ "   AFFIRMANT_ID = null," + "   AFFIRM_TIME = ''";
				}
			} else if (modifyType == 2) {
				sql = sql + "   ," + "   DELETED = ?";
			}

			sql = sql + " WHERE " + "   INSTALLATION_TBL.ID = ?";
			pstmt = conn.prepareStatement(sql);

			index = 0;
			pstmt.setInt(++index, ++exclusiveKey);
			if (modifyType == 1) {
				if (modifyFlg.compareTo(new Integer(1)) == 0) {
					pstmt.setInt(++index, modifyFlg);
					pstmt.setInt(++index, userId);
					pstmt.setString(++index, timeNow);
				} else {
					pstmt.setInt(++index, modifyFlg);
				}
			} else if (modifyType == 2) {
				pstmt.setInt(++index, modifyFlg);
			}
			pstmt.setInt(++index, id);

			pstmt.executeUpdate();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "modifyState");
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
				logSQLException(e, "modifyState");
				throw e;
			}
		}
	}

	/**
	 * modify installation affirmFlg,deleted
	 * 
	 * @author xinagzq
	 * @version 1.0
	 * @since 1.0
	 * @param installation
	 * @throws Exception
	 */
	public void modifyState(Integer id, Integer modifyFlg, String timeNow,
			Integer userId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// Start UOC

			int index = 0;
			String sql = "UPDATE " + " INSTALLATION_TBL" + " SET "
					+ " AFFIRM_FLAG = ?," + " AFFIRMANT_ID = ?,"
					+ " AFFIRM_TIME = ? " + " WHERE " + " ID = ?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(++index, modifyFlg);
			pstmt.setInt(++index, userId);
			pstmt.setString(++index, timeNow);
			pstmt.setInt(++index, id);
			pstmt.executeUpdate();

			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "modifyState");
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
				logSQLException(e, "modifyState");
				throw e;
			}
		}
	}

	/**
	 * delete installation
	 * 
	 * @author liugd
	 * @version 1.0
	 * @since 1.0
	 * @param Integer
	 *            id
	 * @param Integer
	 *            exclusiveKey
	 * @throws Exception
	 */
	public void deleteById(Integer id, Integer exclusiveKey) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			int index = 0;
			// Start UOC
			if (exclusiveCheck) {
				String sql = "select exclusive_key from installation_tbl where id = ? for update nowait";
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(++index, id);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					if (rs.getInt("exclusive_key") != exclusiveKey) {
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

			String sql = "DELETE " + " FROM " + "   INSTALLATION_TBL"
					+ " WHERE " + "   ID = ?";

			pstmt = conn.prepareStatement(sql);

			index = 0;
			pstmt.setInt(++index, id);

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
	 * search count by product ID.
	 * 
	 * @author luyan
	 * @version 1.0
	 * @since 1.0
	 * @param productId
	 *            product ID
	 * @return count
	 * @throws Exception
	 */
	public int searchCountByProductId(Integer productId) throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			// Start UOC
			String sql = " SELECT " + "  COUNT(*)" + " FROM "
					+ "  INSTALLATION_TBL " + " WHERE " + "  PRODUCT_ID = ? ";
			pstmt = conn.prepareStatement(sql);

			int index = 0;
			pstmt.setInt(++index, productId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "searchCountByProductId");
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
				logSQLException(e, "searchCountByProductId");
				throw e;
			}
		}
		return count;
	}

	/**
	 * @author sunyx
	 * @version 1.0
	 * @since 1.0
	 * @param instApp
	 * @throws Exception
	 */
	public void doModifyUseStatusForApply(InstallationApply instApp)
			throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// Start UOC
			String sql = " UPDATE" + " INSTALLATION_TBL" + " SET"
					+ " USE_STATUS_ID = ? " + " WHERE" + " ID = ? ";
			pstmt = conn.prepareStatement(sql);

			int index = 0;
			pstmt.setInt(++index, instApp.getUseStatusId());
			pstmt.setInt(++index, instApp.getInstallId());

			pstmt.executeUpdate();
			// End UOC
		} catch (SQLException e) {
			logSQLException(e, "doModifyUseStatusForApply");
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
				logSQLException(e, "doModifyUseStatusForApply");
				throw e;
			}
		}
	}

	/**
	 * get count by installation ID.
	 * 
	 * @author luyan
	 * @since 1.0
	 * @param installationId
	 *            installation ID
	 * @return count
	 * @throws SQLException
	 */
	public int getCountById(Integer installationId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			// Start UOC
			String sql = " SELECT " + " COUNT(*) " + " FROM "
					+ " INSTALLATION_TBL " + " WHERE  " + " ID= ? "
					+ " AND DELETED = 0 ";
			pstmt = conn.prepareStatement(sql);

			int index = 0;
			pstmt.setInt(++index, installationId);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				index = 0;
				count = rs.getInt(++index);
			}

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

		return count;
	}
}
