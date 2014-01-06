
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
    private int[] width;
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
    
	public int[] getWidth() {
		return width;
	}
	
	public void setWidth(int[] width) {
		this.width = width;
	}
    
}
