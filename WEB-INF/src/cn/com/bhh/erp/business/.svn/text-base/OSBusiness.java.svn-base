package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.OSDao;
import cn.com.bhh.erp.entity.OS;

public class OSBusiness extends BaseBusiness{

    /**
     * get OS list
     * @author sunyx
     * @return
     */
    public List<OS> getOSListForDrop(){
        List<OS> list = new ArrayList<OS>();
        try{
            init();
            
            // Start UOC
            OSDao dao = new OSDao(conn);
            list = dao.getOSListForDrop();
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
}
