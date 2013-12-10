//****************************************
// ProjectName  ITインフラ改造作業
// CreateDate   08/12/07
// Copyright    © Beijing Hitachi Huasun Information Systems Co., Ltd. 2008. All rights reserved.
//****************************************
package cn.com.bhh.erp.entity;

import java.io.Serializable;

import java.util.List;


/**
 * download data
 * @author liugd
 * @since 1.0
 * @version 1.0
 */
@SuppressWarnings("serial")
public class DownloadData implements Serializable {
    // head
    private String[] head;
    // data
    @SuppressWarnings("unchecked")
    private List dataList;
    
    public String[] getHead() {
        return head;
    }
    public void setHead(String[] head) {
        this.head = head;
    }
    @SuppressWarnings("unchecked")
    public List getDataList() {
        return dataList;
    }
    @SuppressWarnings("unchecked")
    public void setDataList(List dataList) {
        this.dataList = dataList;
    }
    
}
