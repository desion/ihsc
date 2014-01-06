//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.action;

import cn.com.bhh.erp.entity.User;
import cn.com.bhh.erp.sysConfig.SysConfigManager;

import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;
import java.util.List;


/**
 * All actions must be extends from this.
 *
 * @author fenghy
 *
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
    
    /**
     * the illegal error.
     */
    protected static final String ILLEGAL_ERR = "illegal_error";

    /**
     * the user who has logined and is using the system.
     */
    protected User loginUser;

    /**
     * the current page default the first page;
     */
    protected int currPage = 1;

    /**
     * the count of the records in each page
     */
    protected int pageSize = SysConfigManager.getDefaultPageSize();

    /**
     * the count of the records in each page
     */
    protected ArrayList<Integer> pageSizeList = SysConfigManager.getPageSizeList();
    
    /**
     * the val should be caculated acorrding to the return of the DB
     */
    protected int totalPages;

    /**
     * the total records count
     */
    protected int totalCount;

    /**
     * translate message list from id to text.
     *
     * @param in
     *            the list of message id, translate the id to text by
     *            message_(language)_(COUNTRY).properties
     * @return the list of message text
     */
    protected final List<String> getMessageText2(List<String> in) {
        List<String> out = new ArrayList<String>();

        for (int i = 0; i < in.size(); ++i) {
            out.add(getText(in.get(i)));
        }

        return out;
    }

    
    /**
     * Nationalization of the message
     * @author xiangzq08
     * @return List&ltString&gt
     */
    protected final List<String> getMessageText(List<String> in) {
        List<String> out = new ArrayList<String>();

        for (int i = 0; i < in.size(); ++i) {
            String msg = in.get(i);
            String[] params = msg.split(",");
            int paramSize = params.length;

            if (paramSize > 1) {
                String text = params[0];
                List<String> args = new ArrayList<String>();

                for (int k = 1; k < paramSize; k++) {
                    args.add(getText(params[k]));
                }

                out.add(getText(text, args));
            } else {
                out.add(getText(msg));
            }
        }

        return out;
    }
    
    public final User getLoginUser() {
        return loginUser;
    }

    public final void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }
   
  
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calc();
    }

    /**
     * calculate the total pages and current page
     */
    private void calc() {
        totalPages = (int) (((totalCount + pageSize) - 1) / pageSize);

        if (totalPages == 0) {
            totalPages = 1;
        }

        if (totalCount < pageSize) {
            currPage = 1;
        }

        if (currPage > totalPages) {
            currPage = totalPages;
        }
    }


    /**
     * @return the pageSizeList
     */
    public ArrayList<Integer> getPageSizeList() {
        return pageSizeList;
    }


    /**
     * @param pageSizeList the pageSizeList to set
     */
    public void setPageSizeList(ArrayList<Integer> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }
    
}
