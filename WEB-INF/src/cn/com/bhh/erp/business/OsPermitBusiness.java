package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.OsPermitDao;
import cn.com.bhh.erp.entity.OsPermit;

public class OsPermitBusiness extends BaseBusiness{

    /**
     * get OSPermit list
     * @author sunyx
     * @return
     */
    public List<OsPermit> getOsPermitListForDrop(){
        List<OsPermit> list = new ArrayList<OsPermit>();
        try{
            init();
            
            // Start UOC
            OsPermitDao osPermitDao = new OsPermitDao(conn);
            list = osPermitDao.getOsPermitListForDrop();
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }

 
}
