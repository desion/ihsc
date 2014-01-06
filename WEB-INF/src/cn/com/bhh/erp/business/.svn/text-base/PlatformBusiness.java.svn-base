package cn.com.bhh.erp.business;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.dao.PlatformDao;
import cn.com.bhh.erp.entity.Platform;

public class PlatformBusiness extends BaseBusiness{

    /**
     * get OS list
     * @author sunyx
     * @return
     */
    public List<Platform> getPlatformListForDrop(){
        List<Platform> list = new ArrayList<Platform>();
        try{
            init();
            
            // Start UOC
            PlatformDao dao = new PlatformDao(conn);
            list = dao.getPlatformListForDrop();
            // End UOC
        } catch (Exception e) {
            handleException(e);
        } finally {
            finish();
        }
        return list;
    }
}
