//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.dao.ActionDao;
import cn.com.bhh.erp.dao.CompanyDao;
import cn.com.bhh.erp.dao.FaultDao;
import cn.com.bhh.erp.dao.FaultHandleDao;
import cn.com.bhh.erp.dao.GetPK;
import cn.com.bhh.erp.dao.GroupDao;
import cn.com.bhh.erp.dao.InstApplyDao;
import cn.com.bhh.erp.dao.UserDao;
import cn.com.bhh.erp.dao.UserGroupDao;
import cn.com.bhh.erp.entity.User;
import cn.com.bhh.erp.entity.UserGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * inlcude the user's business logic.
 * 
 * @author xiangzq
 * @version 1.0
 * @since 1.0
 */
public class UserBusiness extends BaseBusiness {
    /**
     * register the user info
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param user
     */
    public void createUser(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn); 
            if (userDao.getCountByUserName(user) > 0) {
                errors.add("BSE00000,user.name");
                return;
            }

            GroupDao groupDao = new GroupDao(conn);
            if (groupDao.getCountById(user.getGroupId()) == 0) {
                errors.add("BSE01013");
                return;
            }
            
//            ActionDao actionDao = new ActionDao(conn);
//            //if the selected group has not the manager right,
//            //the company must be selected
//            if(actionDao.getCountByGroupId(user.getGroupId())==0){
//                if(user.getCompanyID()==null){
//                    errors.add("BSE01017");
//                    return;
//                }else{
//                    CompanyDao companyDao = new CompanyDao(conn);
//                    if (companyDao.getCountByCompanyId(user.getCompanyID()) == 0) {
//                        errors.add("BSE01014");
//                        return;
//                    } 
//                }
//            }else{
//                //if the group has the mananger right, the company can't be  selected
//                user.setCompanyID(0);
//            }


            GetPK getPK = new GetPK(conn);
            Integer seqNumber = getPK.getPK("USER_TBL_ID_SEQ");
            user.setId(seqNumber);

            userDao.createUser(user);

            // create the user group information.
            UserGroupDao userGroupDao = new UserGroupDao(conn);
            UserGroup userGroup = new UserGroup();
            userGroup.setUserId(user.getId());
            userGroup.setGroupId(user.getGroupId());
            userGroupDao.create(userGroup);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * when the user login and verify the user rights.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param user
     * @return User
     */
    public User loginVerify(User user) {
        User userOut = null;

        try {
            init();

            // Start UOC
            
            UserDao userDao = new UserDao(conn);
            // verify the password
            userOut = userDao.selectByUserIDPwd(user);

            if (userOut != null) {
                userOut.setFullName(userOut.getFullName());

                ActionDao actionDao = new ActionDao(conn);

                // add the user's action rights to the user.
                List<String> actionOut = actionDao.selectAllActionByUserId(userOut);
                userOut.setActionList(actionOut);
                //get the  drop action list ,and add all into the user's actionList
                List<String> dropActionList=actionDao.searchAllDropAction();
                userOut.getActionList().addAll(dropActionList);
                List<String> dataFilter = actionDao.selectAllDataFilterByUserId(userOut);
                userOut.setFilterList(dataFilter);
            } else {
                errors.add("BSE01001");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return userOut;
    }

    /**
     * delete the user for temporarily
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void deleteUserForTemp(User user) {
        try {
            init();

            // Start UOC
            
            UserDao userDao = new UserDao(conn);
            user.setDeleted(1);
            user.setModifyTime(TimeUtil.getNow());
            userDao.deleteForTemp(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * delete the user from the database.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void deleteUserForever(User user) {
        try {
            init();

            // Start UOC
            
            Integer userId = user.getId();

            InstApplyDao instApplyDao = new InstApplyDao(conn);

            if (instApplyDao.getCountByApplyerId(userId) > 0) {
                errors.add("BSE01007");
                return;
            }

            FaultDao faultDao = new FaultDao(conn);

            if (faultDao.getFaultCountBySupporterId(userId) > 0) {
                errors.add("BSE01008");
                return;
            }

            FaultHandleDao faultHandleDao = new FaultHandleDao(conn);

            if (faultHandleDao.getFaultHandleCountBySupporterId(userId) > 0) {
                errors.add("BSE01009");
                return;
            }

            // delete the date related to the user in user_group_tbl
            UserGroupDao userGroupDao = new UserGroupDao(conn);
            UserGroup userGroup = new UserGroup();
            userGroup.setUserId(userId);
            userGroupDao.deleteUserGroupByUser(userGroup);

            // delete user
            UserDao userDao = new UserDao(conn);
            userDao.delete(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * lock the user.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void lockUser(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            user.setLocked(1);
            user.setModifyTime(TimeUtil.getNow());
            userDao.lockUser(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * unlock the user.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void unLockUser(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            user.setLocked(0);
            user.setModifyTime(TimeUtil.getNow());
            userDao.lockUser(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * recover the user locked.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void recoverUser(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            user.setDeleted(0);
            user.setModifyTime(TimeUtil.getNow());
            userDao.deleteForTemp(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the logon user info.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void modifyLogonInfo(User user) {
        try {
            init();

            // Start UOC
            
            UserDao userDao = new UserDao(conn);
            userDao.modifyLogonInfo(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * change the login user's password.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void changeLoginUserPassword(User loginUser, User user) {
        User userOut = null;
        try {
            init();

            // Start UOC
            
            UserDao userDao = new UserDao(conn);
            if ("root".equals(loginUser.getName())) {
                userOut=userDao.selectByRootID(loginUser);
            }else{
                userDao.setExclusiveCheck(false);
                userOut = userDao.selectByUserID(loginUser);
            }
            // check whether the user is still avaliable
            if (userOut == null) {
                errors.add("BSF00006");
                return;
            }
            // check whether the password the user input is the same with the
            // user's password
            if (!user.getOldPassword().equals(userOut.getPassword())) {
                errors.add("BSE01002");
                return;
            }

            user.setId(loginUser.getId());
            user.setModifyTime(TimeUtil.getNow());
            user.setPassword(user.getNewPassword());
            userDao.modifyPassword(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the user information.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     */
    public void modifyUserInfo(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);

            if (userDao.getCountByUserNameModify(user) > 0) {
                errors.add("BSE00000,user.name");
                return;
            }

            GroupDao groupDao = new GroupDao(conn);
            if (groupDao.getCountById(user.getGroupId()) == 0) {
                errors.add("BSE01013");
                return;
            }
            
            
//            ActionDao actionDao = new ActionDao(conn);
//            //if the selected group has not the manager right,
//            //the company must be selected
//            if(actionDao.getCountByGroupId(user.getGroupId())==0){
//                if(user.getCompanyID()==null){
//                    errors.add("BSE01017");
//                    return;
//                }else{
//                    CompanyDao companyDao = new CompanyDao(conn);
//                    if (companyDao.getCountByCompanyId(user.getCompanyID()) == 0) {
//                        errors.add("BSE01014");
//                        return;
//                    } 
//                }
//            }else{
//                //if the group has the mananger right, the company can't be  selected
//                user.setCompanyID(0);
//            }
            
            

            if (user.getNewPassword() != null && !user.getNewPassword().equals("")) {
                if (user.getRepeatPassword() == null || user.getRepeatPassword().equals("")) {
                    errors.add("BSE01015");
                    return;
                } else {
                    if (!user.getNewPassword().equals(user.getRepeatPassword())) {
                        errors.add("BSE01003");
                        return;
                    } else {
                        userDao.modifyUserInfoWithPwd(user);
                    }
                }
            } else {
                userDao.modifyUserInfo(user);
            }

            // modify the user group information.
            UserGroupDao userGroupDao = new UserGroupDao(conn);
            UserGroup userGroup = new UserGroup();
            userGroup.setGroupId(user.getGroupId());
            userGroup.setUserId(user.getId());
            userGroupDao.modifyUserGroup(userGroup);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * modify the login user info.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param user
     */
    public void modifyLoginUserInfo(User user) {
        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            User usr = userDao.selectByUserID(user);

            if (usr == null) {
                errors.add("BSE01011");
                return;
            }

            // if the password entered is wrong,show error message.
            if (!(usr.getPassword().equals(user.getPassword()))) {
                errors.add("BSE01010");
                return;
            }

            userDao.modifyLoginUserInfo(user);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
    }

    /**
     * get all the users.
     * 
     * @auther liugd
     * @version 1.0
     * @since 1.0
     * @return List&ltUser&gt
     */
    public List<User> getUserList(User loginUser) {
        List<User> usersOut = new ArrayList<User>();

        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            usersOut = userDao.getAllUsers(loginUser);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return usersOut;
    }

    /**
     * get one user by id
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param User
     * @return User
     */
    public User getUserById(User user) {
        User userOut = null;

        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            userOut = userDao.selectByUserID(user);
            if (userOut == null) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return userOut;
    }

    /**
     * get one user by id
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param user
     * @return User
     */
    public User getLoginUserById(User user) {
        User userOut = null;

        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            userDao.setExclusiveCheck(false);
            if (user.getName().equals("root")) {
                errors.add("BSE01016");
                return userOut;
            }
            userOut = userDao.selectByUserID(user);
            if (userOut == null) {
                errors.add("BSF00006");
            }

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return userOut;
    }

    /**
     * get the user counts.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @return int
     */
    public int getUserCounts(User user,User loginUser) {
        int counts = 0;

        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            counts = userDao.getUserCounts(user,loginUser);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return counts;
    }

    /**
     * get user list by page.
     * 
     * @auther xiangzq
     * @version 1.0
     * @since 1.0
     * @param currPage
     * @param pageSize
     * @param user
     * @return List&ltUser&gt
     */
    public List<User> getUsersByPage(int currPage, int pageSize, User user ,User loginUser) {
        List<User> usersOut = new ArrayList<User>();

        try {
            init();

            // Start UOC

            UserDao userDao = new UserDao(conn);
            int intBegin;
            int intEnd;

            if (currPage <= 1) {
                intBegin = 0;
                intEnd = pageSize;
            } else {
                intBegin = (currPage - 1) * pageSize;
                intEnd = intBegin + pageSize;
            }

            usersOut = userDao.getUserByPage(intBegin, intEnd, user,loginUser);

            // End UOC

        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }

        return usersOut;
    }
}
