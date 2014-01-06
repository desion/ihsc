//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * all the dao should extend from this class.
 * @author  fenghy
 * @version 1.0
 * @since   1.0
 */
public abstract class BaseDao {
    protected static final Log LOG = LogFactory.getLog(BaseDao.class);
    protected Connection conn;
    protected boolean exclusiveCheck = true;

    public BaseDao(Connection conn) {
        this.conn = conn;
    }

    /**
     * @return the exclusiveCheck
     */
    public boolean isExclusiveCheck() {
        return exclusiveCheck;
    }

    /**
     * @param exclusiveCheck the exclusiveCheck to set
     */
    public void setExclusiveCheck(boolean exclusiveCheck) {
        this.exclusiveCheck = exclusiveCheck;
    }

    /**
     *
     * @auther  fenghy
     * @version 1.0
     * @since   1.0
     * @param   SQLException e
     * @param   methodName
     * @return  void
     * @throws  Exception
     */
    protected final void logSQLException(SQLException e, String methodName) {
        if (LOG.isErrorEnabled()) {
            LOG.error("Class : " + this.getClass().getName() + ", Method : " + methodName + ", " + e.toString(), e);
        }
    }
}
