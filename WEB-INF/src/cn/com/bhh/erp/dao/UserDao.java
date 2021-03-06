
package cn.com.bhh.erp.dao;

import cn.com.bhh.erp.business.ValidationSignature;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * User table data access object.
 * @author  desion
 * @version 1.0
 * @since   1.0
 */
public class UserDao extends BaseDao {
    public UserDao(Connection conn) {
        super(conn);
    }

    /**
     * delete user.
     * @auther  desion
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  Exception
     */
    public void delete(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            // Start UOC
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1 " +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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
                       " DELETE " +
                       " FROM " +
                       " USER_TBL " +
                       " WHERE " +
                       " ID = ?" +
                       " AND DELETABLE=1";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "delete user forever");
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
                logSQLException(e, "delete user forever");
                throw e;
            }
        }
    }

    /**
     * delete the user temporarily
     * set the deleted flag deleted=1
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  Exception
     */
    public void deleteForTemp(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
       
        try {
            // Start UOC
            
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1" +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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
            
            Integer nowExclusiveKey = user.getExclusiveKey();
            user.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                      "  UPDATE " +
                      "  USER_TBL " +
                      "  SET " +
                      "  DELETED=?, " +
                      "  EXCLUSIVE_KEY=? " +
                      "  WHERE " +
                      "  ID = ?" +
                      "  AND DELETABLE=1";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getDeleted());
            pstmt.setInt(++index,user.getExclusiveKey());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "delete user for temp");
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
                logSQLException(e, "delete user for temp");
                throw e;
            }
        }
    }

    /**
     * lock the user
     * set the locked flag=1
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  Exception
     */
    public void lockUser(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            // Start UOC
            
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1" +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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
            
            Integer nowExclusiveKey = user.getExclusiveKey();
            user.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                      " UPDATE " +
                      " USER_TBL SET  " +
                      " LOCKED=?, " +
                      " EXCLUSIVE_KEY=? " +
                      " WHERE ID = ?" +
                      " AND DELETABLE=1";
            
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getLocked());
            pstmt.setInt(++index,user.getExclusiveKey());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "lock user");
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
                logSQLException(e, "lock user");
                throw e;
            }
        }
    }

    /**
     * create user.
     * @auther  desion
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  SQLException
     */
    public void createUser(User user) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " INSERT INTO USER_TBL ( " +
                      " ID," +
                      " NAME," +
                      " FULLNAME," +
                      " SEX," +
                      " PASSWORD," +
                      " MOBILE_PHONE, " +
                      " EMAIL," +
                      " DEPARTMENT," +
                      " CREATOR_ID," +
                      " MODIFIER_ID, " +
                      " SIGN " +
                      " ) VALUES(?,?,?,?,?,?,?,?,?,?,? )";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());
            pstmt.setString(++index, user.getName());
            pstmt.setString(++index, user.getFullName());
            pstmt.setInt(++index, user.getSex());
            pstmt.setString(++index, ValidationSignature.Encryption(user.getPassword()));
            pstmt.setString(++index, user.getMobilePhone());
            pstmt.setString(++index, user.getEmail());
            pstmt.setString(++index, user.getDepartment());
            pstmt.setInt(++index, user.getCreatorID());
            pstmt.setInt(++index, user.getModifierID());
            String signpwd = ValidationSignature.Encryption(user.getPassword());
            pstmt.setString(++index, ValidationSignature.Encryption(user.getId() + user.getName() + signpwd));

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "createUser");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "createUser");
                throw e;
            }
        }
    }

    

    /**
     * get all the users' information from user table
     * @auther  liugd
     * @version 1.0
     * @since   1.0
     * @return  List<User>
     * @throws  SQLException
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List list = new ArrayList();
            String sql = 
                     " SELECT " +
                     " A.ID," +
                     " A.NAME," +
                     " A.FAMILY_NAME," +
                     " A.GIVEN_NAME," +
                     " A.COMPANY_ID," +
                     " B.SHORT_NAME" +
                     " FROM " +
                     " USER_TBL A,COMPANY_TBL B,USER_GROUP_TBL C,GROUP_TBL D " +
                     " WHERE" +
                     " A.COMPANY_ID=B.ID " +
                     " AND A.ID = C.USER_ID " +
                     " AND C.GROUP_ID=D.ID " +
                     " AND A.id > 0" +
                     " AND A.DELETED=0" +
                     " AND B.DELETED=0" ;
            
           StringBuffer sb = new StringBuffer(sql);
           if(!loginUser.filter("user_mng_all_data")){
                sb.append(" AND  A.COMPANY_ID = ? ");
            }
            
            sb.append(" ORDER BY D.NAME,A.NAME ");
            pstmt = conn.prepareStatement(sb.toString());

            int index = 0;
            
            if(!loginUser.filter("user_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                index = 0;
                user.setId(rs.getInt(++index));
                user.setName(rs.getString(++index));
                user.setFamilyName(rs.getString(++index));
                user.setGivenName(rs.getString(++index));
                user.setCompanyID(rs.getInt(++index));
                user.setCompanyName(rs.getString(++index));
                
                list.add(user);
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
     * get user by user id
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  User
     * @throws  Exception
     */
    public User selectByUserID(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User userSt = null;

        try {
            // Start UOC
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1 " ;
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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
                       " A.ID," +
                       " A.NAME," +
                       " A.PASSWORD," +
                       " A.FULLNAME," +
                       " A.SEX,"  + 
                       " A.MOBILE_PHONE," +
                       " A.EMAIL," +
                       " A.DEPARTMENT," +
                       " A.CREATOR_ID," +
                       " A.MODIFIER_ID," +
                       " A.LOCKED," +
                       " A.DELETED," +
                       " A.DELETABLE," +
                       " A.EXCLUSIVE_KEY," +
                       " B.GROUP_ID," +
                       " C.NAME GROUP_NAME" +
                       " FROM " +
                       " USER_TBL A,USER_GROUP_TBL B,GROUP_TBL C " +
                       " WHERE " +
                       " A.ID=B.USER_ID" +
                       " AND B.GROUP_ID=C.ID" +
                       " AND A.ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                userSt = new User();
                index = 0;
                userSt.setId(rs.getInt(++index));
                userSt.setName(rs.getString(++index));
                userSt.setPassword(rs.getString(++index));
                userSt.setFullName(rs.getString(++index));
                userSt.setSex(rs.getInt(++index));
                userSt.setMobilePhone(rs.getString(++index));
                userSt.setEmail(rs.getString(++index));
                userSt.setDepartment(rs.getString(++index));
                userSt.setCreatorID(rs.getInt(++index));
                userSt.setModifierID(rs.getInt(++index));
                userSt.setLocked(rs.getInt(++index));
                userSt.setDeleted(rs.getInt(++index));
                userSt.setDeletable(rs.getInt(++index));
                userSt.setExclusiveKey(rs.getInt(++index));
                userSt.setGroupId(rs.getInt(++index));
                userSt.setGroupName(rs.getString(++index));
            }

            return userSt;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByUserID");
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
                logSQLException(e, "selectByUserID");
                throw e;
            }
        }
    }
    
    public User selectByRootID(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User userSt = null;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " ID," +
                       " NAME," +
                       " PASSWORD," +
                       " FULLNAME," +
                       " SEX,"  + 
                       " MOBILE_PHONE," +
                       " EMAIL," +
                       " DEPARTMENT," +
                       " CREATOR_ID," +
                       " MODIFIER_ID," +
                       " LOCKED," +
                       " DELETED," +
                       " DELETABLE," +
                       " EXCLUSIVE_KEY" +
                       " FROM " +
                       " USER_TBL  " +
                       " WHERE " +
                       " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());

            rs = pstmt.executeQuery();

            if (rs.next()) {
                userSt = new User();
                index = 0;
                userSt.setId(rs.getInt(++index));
                userSt.setName(rs.getString(++index));
                userSt.setPassword(rs.getString(++index));
                userSt.setFullName(rs.getString(++index));
                userSt.setSex(rs.getInt(++index));
                userSt.setMobilePhone(rs.getString(++index));
                userSt.setEmail(rs.getString(++index));
                userSt.setDepartment(rs.getString(++index));
                userSt.setCreatorID(rs.getInt(++index));
                userSt.setModifierID(rs.getInt(++index));
                userSt.setLocked(rs.getInt(++index));
                userSt.setDeleted(rs.getInt(++index));
                userSt.setDeletable(rs.getInt(++index));
                userSt.setExclusiveKey(rs.getInt(++index));
            }

            return userSt;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByRootID");
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
                logSQLException(e, "selectByRootID");
                throw e;
            }
        }
    }

    /**
     * get user by name and password.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  User
     * @throws  SQLException
     */
    public User selectByUserIDPwd(User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
          
            
            String sql = 
                     " SELECT " +
                     " A.ID," +
                     " A.NAME," +
                     " A.PASSWORD," +
                     " A.FULLNAME," +
                     " A.MOBILE_PHONE," +
                     " A.EMAIL," +
                     " A.DEPARTMENT," +
                     " A.SIGN," +
                     " A.CREATOR_ID," +
                     " A.MODIFIER_ID," +
                     " A.DELETED," +
                     " A.DELETABLE" +
                     " FROM " +
                     " USER_TBL A " +
                     " WHERE  " +
                     " A.DELETED=0 " +
                     " AND A.LOCKED=0 " +
                     " AND A.NAME = ? " +
                     " AND A.PASSWORD = ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, user.getName());
            pstmt.setString(++index, ValidationSignature.Encryption(user.getPassword()));
            rs = pstmt.executeQuery();
            User userOut = null;

            if (rs.next()) {
                userOut = new User();
                index = 0;
                userOut.setId(rs.getInt(++index));
                userOut.setName(rs.getString(++index));
                userOut.setPassword(rs.getString(++index));
                userOut.setFullName(rs.getString(++index));
                userOut.setMobilePhone(rs.getString(++index));
                userOut.setEmail(rs.getString(++index));
                userOut.setDepartment(rs.getString(++index));
                userOut.setSignature(rs.getString(++index));
                userOut.setCreatorID(rs.getInt(++index));
                userOut.setModifierID(rs.getInt(++index));
                userOut.setDeleted(rs.getInt(++index));
                userOut.setDeletable(rs.getInt(++index));
            }

            return userOut;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectByUserIDPwd");
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
                logSQLException(e, "selectByUserIDPwd");
                throw e;
            }
        }
    }
    
   /**
    * modify logon user infomation.
    * @auther  xiangzq
    * @version 1.0
    * @since   1.0
    * @param   User
    * @throws  SQLException
    */
    public void modifyLogonInfo(User user) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                    " UPDATE USER_TBL SET " +
                    " LAST_LOGON_IP=?," +
                    " LAST_LOGON_TIME=? " + 
                    " WHERE ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, user.getLastLoginIP());
            pstmt.setString(++index, user.getLastLoginTime());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateLogonInfo");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "updateLogonInfo");
                throw e;
            }
        }
    }

    /**
     * modify user password.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  SQLException
     */
    public void modifyPassword(User user) throws SQLException {
        PreparedStatement pstmt = null;

        try {
            // Start UOC
            String sql = 
                      " UPDATE USER_TBL SET " +
                      " PASSWORD=?," +
                      " MODIFY_TIME=?" + 
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, ValidationSignature.Encryption(user.getPassword()));
            pstmt.setString(++index, user.getModifyTime());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updatePassword");
            throw e;
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                logSQLException(e, "updatePassword");
                throw e;
            }
        }
    }

    /**
     * modify user information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  void
     * @throws  Exception
     */
    public void modifyUserInfo(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1" +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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

            Integer nowExclusiveKey = user.getExclusiveKey();
            user.setExclusiveKey(++nowExclusiveKey);

            // Start UOC
            String sql = 
                      " UPDATE USER_TBL SET " +
                      " NAME=?," +
            		  " FULLNAME=?," +
            		  " SEX=?,"+ 
                      " MOBILE_PHONE=?," + 
                      " EMAIL=?," +
                      " DEPARTMENT=?," +
                      " MODIFY_TIME=?," +
                      " MODIFIER_ID=?," +
                      " EXCLUSIVE_KEY=?" +
                      " WHERE " +
                      " ID = ?" +
                      " AND DELETABLE=1";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, user.getName());
            pstmt.setString(++index, user.getFullName());
            pstmt.setInt(++index, user.getSex());
            pstmt.setString(++index, user.getMobilePhone());
            pstmt.setString(++index, user.getEmail());
            pstmt.setString(++index, user.getDepartment());
            pstmt.setString(++index, user.getModifyTime());
            pstmt.setInt(++index, user.getModifierID());
            pstmt.setInt(++index, user.getExclusiveKey());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "updateUserInfo");
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
                logSQLException(e, "updateUserInfo");
                throw e;
            }
        }
    }
    
    /**
     * modify user information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  void
     * @throws  Exception
     */
    public void modifyUserInfoWithPwd(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1" +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("exclusive_key") != user.getExclusiveKey()) {
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

            Integer nowExclusiveKey = user.getExclusiveKey();
            user.setExclusiveKey(++nowExclusiveKey);

            // Start UOC
            String sql = 
                      " UPDATE USER_TBL SET " +
                      " NAME=?," +
                      " FULLNAME=?," +
                      " PASSWORD=?," +
                      " SEX=?,"+ 
                      " MOBILE_PHONE=?," + 
                      " EMAIL=?," +
                      " DEPARTMENT=?," +
                      " MODIFY_TIME=?," +
                      " MODIFIER_ID=?," +
                      " EXCLUSIVE_KEY=?" +
                      " WHERE " +
                      " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, user.getName());
            pstmt.setString(++index, user.getFullName());
            pstmt.setString(++index, ValidationSignature.Encryption(user.getNewPassword()));////////
            pstmt.setInt(++index, user.getSex());
            pstmt.setString(++index, user.getMobilePhone());
            pstmt.setString(++index, user.getEmail());
            pstmt.setString(++index, user.getDepartment());
            pstmt.setString(++index, user.getModifyTime());
            pstmt.setInt(++index, user.getModifierID());
            pstmt.setInt(++index, user.getExclusiveKey());
            pstmt.setInt(++index, user.getId());

            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyUserInfoWithPwd");
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
                logSQLException(e, "modifyUserInfoWithPwd");
                throw e;
            }
        }
    }

    /**
     * modify login user information.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @throws  Exception
     */
    public void modifyLoginUserInfo(User user) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {

            // Start UOC
            
            if (exclusiveCheck) {
                int index=0;
                String sql =
                            " SELECT " +
                            " EXCLUSIVE_KEY " +
                            " FROM USER_TBL " +
                            " WHERE ID = ? " +
                            " AND DELETABLE=1" +
                            " FOR UPDATE NOWAIT";
                pstmt = conn.prepareStatement(sql);

                pstmt.setInt(++index, user.getId());
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    if (rs.getInt("EXCLUSIVE_KEY") != user.getExclusiveKey()) {
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

            Integer nowExclusiveKey = user.getExclusiveKey();
            user.setExclusiveKey(++nowExclusiveKey);
            
            String sql = 
                       " UPDATE USER_TBL  SET  " +
                       " SEX=?," +
                       " MOBILE_PHONE=?, " +
                       " EMAIL=?," +
                       " DEPARTMENT=?," +
                       " MODIFY_TIME=?," +
                       " MODIFIER_ID=?," +
                       " EXCLUSIVE_KEY=? " +
                       " WHERE " +
                       " ID = ?";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getSex());
            pstmt.setString(++index, user.getMobilePhone());
            pstmt.setString(++index, user.getEmail());
            pstmt.setString(++index, user.getDepartment());
            pstmt.setString(++index, user.getModifyTime());
            pstmt.setInt(++index, user.getModifierID());
            pstmt.setInt(++index, user.getExclusiveKey());
            pstmt.setInt(++index, user.getId());
            pstmt.executeUpdate();

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "modifyLoginUserInfo");
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
                logSQLException(e, "modifyLoginUserInfo");
                throw e;
            }
        }
    }

    /**
     * get user count by user name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  int
     * @throws  SQLException
     */
    public int getCountByUserName(User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " USER_TBL " +
                       " WHERE  " +
                       " NAME= ? ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setString(++index, user.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByUserName");
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
                logSQLException(e, "getCountByUserName");
                throw e;
            }
        }

        return count;
    }
    
    /**
     * get user count by company ID.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   companyId
     * @return  int
     * @throws  SQLException
     */
    public int getCountByCompanyId(Integer companyId) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = 
                       " SELECT " +
                       " COUNT(*) " +
                       " FROM " +
                       " USER_TBL " +
                       " WHERE  " +
                       " COMPANY_ID= ?" +
                       " AND DELETABLE=1 ";
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, companyId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByCompanyId");
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
                logSQLException(e, "getCountByCompanyId");
                throw e;
            }
        }

        return count;
    }

    /**
     * get user count by user name.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   User
     * @return  int
     * @throws  SQLException
     */
    public int getCountByUserNameModify(User user) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
          
            String sql = 
                       " SELECT" +
                       " COUNT(*) " +
                       " FROM " +
                       " USER_TBL A " +
                       " WHERE EXISTS " +
                       " ( " +
                       "    SELECT " +
                       "    NAME " +
                       "    FROM " +
                       "    USER_TBL B " +
                       "    WHERE " +
                       "    A.ID != B.ID " +
                       "    AND B.ID =? " +
                       "  ) " + 
                       " AND A.NAME= ? ";
     
            pstmt = conn.prepareStatement(sql);

            int index = 0;
            pstmt.setInt(++index, user.getId());
            pstmt.setString(++index, user.getName());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                count = rs.getInt(++index);
            }

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getCountByUserNameModify");
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
                logSQLException(e, "getCountByUserNameModify");
                throw e;
            }
        }

        return count;
    }

   
    /**
     * get user count.
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   user
     * @return  int
     * @throws  SQLException
     */
    public int getUserCounts(User user,User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            
            String sql =  
                    " SELECT " +
                    " COUNT(*) " +
                    " FROM " +
                    " USER_TBL A,USER_GROUP_TBL B,GROUP_TBL C " +
                    " WHERE " +
                    " A.ID=B.USER_ID " +
                    " AND B.GROUP_ID=C.ID " +
                    " AND A.DELETABLE=1 ";
            StringBuffer sb = new StringBuffer(sql);
            
            if(!loginUser.filter("user_mng_all_data")){
                sb.append(" AND A.COMPANY_ID=? ");
            }else{
                if(!loginUser.hasPermission("BS006_40")){
                    //日立成员时不能看到管理员组的用户数据
                    sb.append("AND B.GROUP_ID NOT IN ");
                    sb.append("( ");
                    sb.append("  SELECT DISTINCT ");
                    sb.append("  E.GROUP_ID FROM   ");
                    sb.append("  ACTION_GROUP_TBL E ,");
                    sb.append("  ACTION_TBL  F");
                    sb.append("  WHERE  E.ACTION_ID = F.ID");
                    sb.append("  AND F.NAME='BS006_40'");
                    sb.append(" )");
                }
            }

            if (user.getName()!=null&&!"".equals(user.getName())) {
                sb.append(" AND A.NAME LIKE ? ");
            }
            if (user.getFamilyName()!=null&&!"".equals(user.getFamilyName())) {
                sb.append(" AND A.FAMILY_NAME LIKE ? ");
            }
            
            if (user.getGivenName()!=null&&!"".equals(user.getGivenName())) {
                sb.append(" AND A.GIVEN_NAME LIKE ? ");
            }
            
            if (user.getCompanyID()!=null) {
                sb.append(" AND A.COMPANY_ID=? ");
            }

            pstmt = conn.prepareStatement(sb.toString());

            int index = 0;
            if(!loginUser.filter("user_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            
            if (user.getName()!=null&&!"".equals(user.getName())) {
                pstmt.setString(++index, user.getName()+"%");
            }
            if (user.getFamilyName()!=null&&!"".equals(user.getFamilyName())) {
                pstmt.setString(++index, user.getFamilyName()+"%");
            }
            
            if (user.getGivenName()!=null&&!"".equals(user.getGivenName())) {
                pstmt.setString(++index, user.getGivenName()+"%");
            }
            
            if (user.getCompanyID()!=null) {
                pstmt.setInt(++index, user.getCompanyID());
            }
            
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index=0;
                count = rs.getInt(++index);
            }

            return count;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getUserCounts");
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
                logSQLException(e, "getUserCounts");
                throw e;
            }
        }
    }

    
    /**
     * search the users by rules
     * @auther  xiangzq
     * @version 1.0
     * @since   1.0
     * @param   intBegin
     * @param   intEnd
     * @param   user
     * @return  user list
     * @throws  SQLException
     */
    public List<User> getUserByPage(int intBegin, int intEnd, User user,User loginUser) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Start UOC
            List<User> list = new ArrayList<User>();
            
            String sql = 
                     " SELECT " +
                     " ID," +
                     " NAME," +
                     " FULLNAME," +
                     " EMAIL,"+
                     " DEPARTMENT,"+
                     " GROUP_ID," +
                     " GROUP_NAME," +
                     " EXCLUSIVE_KEY," +
                     " LOCKED," +
                     " DELETED " +
                     " FROM " +
                     "( SELECT ROWNUM NO," +
                     "  T.* " +
                     "  FROM" +
                     "  (" +
                     "    SELECT " +
                     "    A.*," +
                     "    B.GROUP_ID, " +
                     "    C.NAME " +
                     "    GROUP_NAME " +
                     "    FROM " +
                     "    USER_TBL A,USER_GROUP_TBL B,GROUP_TBL C " +
                     "    WHERE " +
                     "    A.ID=B.USER_ID " +
                     "    AND B.GROUP_ID=C.ID " +
                     "    AND A.DELETABLE=1  ";
                        
           

            StringBuffer sb = new StringBuffer(sql);

            if(!loginUser.filter("user_mng_all_data")){
                sb.append(" AND A.COMPANY_ID=? ");
            }else{
                if(!loginUser.hasPermission("BS006_40")){
                    //日立成员时不能看到管理员组的用户数据
                    sb.append("AND B.GROUP_ID NOT IN ");
                    sb.append("( ");
                    sb.append("  SELECT DISTINCT ");
                    sb.append("  E.GROUP_ID FROM   ");
                    sb.append("  ACTION_GROUP_TBL E ,");
                    sb.append("  ACTION_TBL  F");
                    sb.append("  WHERE  E.ACTION_ID = F.ID");
                    sb.append("  AND F.NAME='BS006_40'");
                    sb.append(" )");
                }
            }
            
            if (user.getName()!=null&&!"".equals(user.getName())) {
                sb.append(" AND A.NAME LIKE ? ");
            }
            if (user.getFamilyName()!=null&&!"".equals(user.getFamilyName())) {
                sb.append(" AND A.FAMILY_NAME LIKE ? ");
            }
            
            if (user.getGivenName()!=null&&!"".equals(user.getGivenName())) {
                sb.append(" AND A.GIVEN_NAME LIKE ? ");
            }
            
            if (user.getCompanyID()!=null) {
                sb.append(" AND A.COMPANY_ID=? ");
            }

            sb.append(" ORDER BY A.NAME ASC,A.ID ) T ");
            sb.append(" WHERE ROWNUM<=? ");
            sb.append(" ) WHERE NO>? ");
            
            pstmt = conn.prepareStatement(sb.toString());

            int index = 0;
            
            if(!loginUser.filter("user_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            
            if (user.getName()!=null&&!"".equals(user.getName())) {
                pstmt.setString(++index, user.getName()+"%");
            }
            if (user.getFamilyName()!=null&&!"".equals(user.getFamilyName())) {
                pstmt.setString(++index, user.getFamilyName()+"%");
            }
            
            if (user.getGivenName()!=null&&!"".equals(user.getGivenName())) {
                pstmt.setString(++index, user.getGivenName()+"%");
            }
            
            if (user.getCompanyID()!=null) {
                pstmt.setInt(++index, user.getCompanyID());
            }

            pstmt.setInt(++index, intEnd);
            pstmt.setInt(++index, intBegin);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                User userSt = new User();
                index = 0;
                userSt.setId(rs.getInt(++index));
                userSt.setName(rs.getString(++index));
                userSt.setFullName(rs.getString(++index));
                userSt.setEmail(rs.getString(++index));
                userSt.setDepartment(rs.getString("DEPARTMENT"));
                userSt.setGroupId(rs.getInt("GROUP_ID"));
                userSt.setGroupName(rs.getString("GROUP_NAME"));
                userSt.setLocked(rs.getInt("LOCKED"));
                userSt.setDeleted(rs.getInt("DELETED"));
                userSt.setExclusiveKey(rs.getInt("EXCLUSIVE_KEY"));
//                userSt.setGroupName(rs.getString(++index));
//                userSt.setExclusiveKey(rs.getInt(++index));
//                userSt.setLocked(rs.getInt(++index));
//                userSt.setDeleted(rs.getInt(++index));
                list.add(userSt);
            }

            return list;

            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "selectUserByPage");
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
                logSQLException(e, "selectUserByPage");
                throw e;
            }
        }
    }
    
    /**
     * get count by id
     * @author sunyx
     * @param id
     * @return
     * @throws SQLException
     */
    public int getCountById(Integer id,User loginUser) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;

        try {
            // Start UOC
            String sql = " SELECT" +
                        " COUNT(*)" +
                        " FROM" +
                        " USER_TBL" +
                        " WHERE " +
                        " ID = ?" +
                        " AND " +
                        " ID > 0";
            
            StringBuffer sb = new StringBuffer(sql);
            if(!loginUser.filter("user_mng_all_data")){
                 sb.append(" AND " +
                           "(" +
                           "   COMPANY_ID IN " +
                           "  ( "+
                           "    SELECT CUSTOMER_ID " +
                           "    FROM AGENT_CUSTOMER_TBL " +
                           "    WHERE AGENT_ID = ? " +
                           "  ) OR " +
                           "  COMPANY_ID = ?" +
                           ") ");
             }
            
            pstmt = conn.prepareStatement(sb.toString());
            
            int index = 0;
            pstmt.setInt(++index, id);
            
            if(!loginUser.filter("user_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
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
    
    
    /**
     * get id name
     * @author liugd
     * @param userName
     * @return id
     * @throws SQLException
     */
    public Integer getIdByName(String userName,User loginUser) throws SQLException{
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer id = null;

        try {
            // Start UOC
            String sql = " SELECT ID" +
                        " FROM" +
                        " USER_TBL" +
                        " WHERE " +
                        " ID > 0 " +
                        " AND FAMILY_NAME || GIVEN_NAME = ? ";
            
            StringBuffer sb = new StringBuffer(sql);
            if(!loginUser.filter("user_mng_all_data")){
                 sb.append(" AND " +
                           "(" +
                           "   COMPANY_ID IN " +
                           "  ( "+
                           "    SELECT CUSTOMER_ID " +
                           "    FROM AGENT_CUSTOMER_TBL " +
                           "    WHERE AGENT_ID = ? " +
                           "  ) OR " +
                           "  COMPANY_ID = ?" +
                           ") ");
             }
            
            pstmt = conn.prepareStatement(sb.toString());
            int index = 0;
            pstmt.setString(++index, userName);
            if(!loginUser.filter("user_mng_all_data")){
                pstmt.setInt(++index, loginUser.getCompanyID());
                pstmt.setInt(++index, loginUser.getCompanyID());
            }
            rs = pstmt.executeQuery();

            if (rs.next()) {
                index = 0;
                id = rs.getInt(++index);
            }
                        
            // End UOC
        } catch (SQLException e) {
            logSQLException(e, "getIdByName");
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
                logSQLException(e, "getIdByName");
                throw e;
            }
        }
        return id;
    }
}
