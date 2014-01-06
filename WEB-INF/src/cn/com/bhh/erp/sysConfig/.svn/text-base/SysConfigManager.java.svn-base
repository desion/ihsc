package cn.com.bhh.erp.sysConfig;

import java.util.ArrayList;
import java.util.ResourceBundle;


/**
 * read system.properties manage
 * only one instance in the application. use the method getInstance()
 * to get the singleton instance.
 * @author liugd
 *
 */
public final class SysConfigManager {
    
    private static SysConfigManager instance;
    private static int countSize;
    private static ArrayList<Integer> pageSizeList;
    private static Integer defaultPageSize;
    
    private SysConfigManager() {
        ResourceBundle rb = null;
        try {
            rb = ResourceBundle.getBundle("system");
        } catch (Exception e) {
        }
        if (rb != null) {
            try {
                countSize = Integer.parseInt(rb.getString("dataCount"));
                if (countSize > 60000) {
                    countSize = 60000;
                }
            } catch (Exception e) {
                countSize = 60000;
            }
            pageSizeList = new ArrayList<Integer>();
            try {
                String pageCountStr = rb.getString("pageSize");
                String[] pageCountArr = pageCountStr.split(",");
                for (int i = 0; i < pageCountArr.length; i++) {
                    if (!"".equals(pageCountArr[i])) {
                        pageSizeList.add(new Integer(pageCountArr[i]));
                    }
                }
            } catch (Exception e) {
                pageSizeList = new ArrayList<Integer>();
                pageSizeList.add(new Integer("10"));
                pageSizeList.add(new Integer("20"));
                pageSizeList.add(new Integer("50"));
                pageSizeList.add(new Integer("100"));
            }
            try {
                defaultPageSize = new Integer(rb.getString("defaultPageSize"));
            } catch (Exception e) {
                defaultPageSize = new Integer("10");
            }
        }
    }

    public static SysConfigManager getInstance() {
        if (instance == null) {
            instance = new SysConfigManager();
        }
        return instance;
    }

    /**
     * @return the countSize
     */
    public static int getCountSize() {
        return countSize;
    }

    /**
     * @return the pageSizeList
     */
    public static ArrayList<Integer> getPageSizeList() {
        return pageSizeList;
    }

    /**
     * @return the defaultPageSize
     */
    public static Integer getDefaultPageSize() {
        return defaultPageSize;
    }

}
