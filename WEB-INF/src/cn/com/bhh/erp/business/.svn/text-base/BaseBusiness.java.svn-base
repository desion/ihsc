//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.business;

import cn.com.bhh.erp.db.ConnectionManager;
import cn.com.bhh.erp.db.ExclusiveException;
import cn.com.bhh.erp.db.NoConnectionAvailableException;
import cn.com.bhh.erp.db.RecordNoFoundException;
import cn.com.bhh.erp.db.TimeoutException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


/**
 * All business must be extends from this.
 * @author fenghy
 *
 */
public abstract class BaseBusiness {
    protected ConnectionManager dcm;
    protected Connection conn = null;
    protected List<String> errors;

    public BaseBusiness() {
        dcm = ConnectionManager.getInstance();
        errors = new ArrayList<String>();
    }

    protected final void handleException(Exception e) {
        // when SQLException, NoConnectionAvailableException, TimeoutException
        // were caught, we have already call the method "e.printStackTrace()",
        // so it's unnecessary at this moment.
        if (e instanceof SQLException) {
            errors.add("BSF00004");
        } else if (e instanceof NoConnectionAvailableException) {
            errors.add("BSF00002");
        } else if (e instanceof TimeoutException) {
            errors.add("BSF00003");
        } else if (e instanceof ExclusiveException) {
            errors.add("BSF00005");
        } else if (e instanceof RecordNoFoundException) {
            errors.add("BSF00006");
        } else {
            e.printStackTrace();
            errors.add("BSF00001");
        }
    }
    
    protected final void init() throws SQLException, NoConnectionAvailableException, TimeoutException {
        conn = dcm.getConnection(ConnectionManager.ORACLE);
        conn.setAutoCommit(false);
    }
    
    protected final void finish() {
        try {
            if (conn != null) {
                if (errors.isEmpty()) {
                    conn.commit();
                } else {
                    conn.rollback();
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            if(!errors.contains("BSF00004")){
                 errors.add("BSF00004");
            }
        }
        if (conn != null) {
            dcm.freeConnection(ConnectionManager.ORACLE, conn);
        }
    }

    public final List<String> getErrors() {
        return errors;
    }

    public final boolean hasErrors() {
        return !errors.isEmpty();
    }
}
