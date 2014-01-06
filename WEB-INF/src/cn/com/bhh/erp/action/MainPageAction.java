
package cn.com.bhh.erp.action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.com.bhh.erp.business.InstallationBusiness;
import cn.com.bhh.erp.business.TopupOrderBusiness;
import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.ExcelHandle;
import cn.com.bhh.erp.common.TimeUtil;
import cn.com.bhh.erp.entity.DownloadData;
import cn.com.bhh.erp.entity.Installation;
import cn.com.bhh.erp.entity.Product;
import cn.com.bhh.erp.entity.TopupRecord;
import cn.com.bhh.erp.sysConfig.SysConfigManager;

/**
 * prepare the data for the main page.
 * 
 */
@SuppressWarnings("serial")
public class MainPageAction extends BaseAction {
	private TopupRecord topupRecord = new TopupRecord();
    private List<TopupRecord> topupOrderList = new ArrayList<TopupRecord>();
    private Integer selectedProvinceId = null;
    private Integer currentTotalNum = null;
    private String selectedChannelId = null;
    private List<String> affirmChkList = new ArrayList<String>();
    private Integer statusType = null;
   

//    public String execute() throws Exception {
//        //unconfirmed installation list
//        TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
//        setTotalCount(topupBussiness.getTopupOrderCount(topupRecord, loginUser));
//        topupOrderList = topupBussiness.serchUseStatus(topupRecord, loginUser,currPage, pageSize);
//        if (topupBussiness.hasErrors()) {
//            setActionMessages(getMessageText(topupBussiness.getErrors()));
//            return INPUT;
//        }      
//
//        return SUCCESS;
//    }
    
    public String SearchOrderList()throws Exception {
        //unconfirmed installation list
        TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
        int totalNum = topupBussiness.getTopupOrderCount(topupRecord, loginUser);
        setTotalCount(totalNum);
        setCurrentTotalNum(totalNum);
        topupOrderList = topupBussiness.serchUseStatus(topupRecord, loginUser,currPage, pageSize);
        if (topupBussiness.hasErrors()) {
            setActionMessages(getMessageText(topupBussiness.getErrors()));
            return INPUT;
        }      

        return SUCCESS;
    } 
    
    public void outXml(int totalNum) throws Exception{
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");  
        sb.append("<root>"); 
        sb.append("<totalNum>");
        sb.append(totalNum);
        sb.append("</totalNum>");
        sb.append("</root>"); 
        HttpServletResponse response = ServletActionContext.getResponse();  
        //设置编码  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out = response.getWriter();  
        out.write(sb.toString());  
        out.flush();  
        out.close(); 
     }
    
    public void outXml(String xml) throws Exception{
        StringBuilder sb = new StringBuilder();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");  
        sb.append("<root>"); 
        sb.append(xml);
        sb.append("</root>"); 
        HttpServletResponse response = ServletActionContext.getResponse();  
        //设置编码  
        response.setCharacterEncoding("UTF-8");  
        response.setContentType("text/xml;charset=utf-8");  
        response.setHeader("Cache-Control", "no-cache");  
        PrintWriter out = response.getWriter();  
        out.write(sb.toString());  
        out.flush();  
        out.close(); 
     }
    
    public String getCurrTotal()throws Exception {
    	int totalNum = 0;
    	TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
        totalNum = topupBussiness.getTopupOrderCount(topupRecord, loginUser);
        outXml(totalNum);
        return SUCCESS;
    }
    
    /**
     * excel file download
     * @author liugd
     * @version 1.0
     * @since 1.0
     * @exception Exception
     */
    @SuppressWarnings("unchecked")
    public String download() throws Exception {

        String fileName = "topup_info_" + TimeUtil.getNow();
        DownloadData downloadData = new DownloadData();

        // head info       
        String[] headInfo = { 
        				"order.phoneNo",
        				"order.province",
        				"order.topupPhone",
        				"order.price",
        				"order.sysNo",
        				"order.topupNo",
        				"order.requestNo",
        				"order.createTime",
        				"order.updateTime",
        				"order.status",
        				"order.notify",
        				"order.source",
        				"order.proxy",
        				"order.operator",
        				"order.salePrice",
        				"order.inPrice"
                };

        int[] widthInfo = {
        	15,
        	10,
        	15,
        	10,
        	20,
        	20,
        	20,
        	20,
        	20,
        	10,
        	10,
        	10,
        	15,
        	10,
        	10,
        	10
        };
        for (int i = 0; i < headInfo.length; i++) {
            headInfo[i] = getText(headInfo[i]);
        }
        downloadData.setHead(headInfo);
        downloadData.setWidth(widthInfo);

        int countSize = SysConfigManager.getCountSize();

        topupOrderList = new ArrayList<TopupRecord>();
        TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
        topupOrderList = topupBussiness.getDownLoadData(topupRecord, loginUser,0, countSize);
        if (topupBussiness.hasErrors()) {
            setActionMessages(getMessageText(topupBussiness.getErrors()));
            return INPUT;
        }
        
        List dataList = new ArrayList();
        DateFormat dateFormat = new DateFormat();
        for (int i = 0; i < topupOrderList.size(); i++) {
            List row = new ArrayList();
            TopupRecord record = topupOrderList.get(i);
            row.add(record.getPhoneNum());
            row.add(record.getProvinceName());
            row.add(record.getTopupPhone());
            row.add(record.getSum());
            row.add(record.getSystemNo());
            row.add(record.getTopupNo());
            row.add(record.getRequestNo());
            row.add(record.getCreateTime());
            row.add(record.getUpdateTime());
            row.add(record.getStatusName());
            row.add(record.getNoticeName());
            row.add(record.getSourceName());
            row.add(record.getProxy());
            row.add(record.getOperatorName());
            row.add(record.getSalePrice());
            row.add(record.getInPrice());
            dataList.add(row);
        }
        
        downloadData.setDataList(dataList);
        ExcelHandle excelHandle = new ExcelHandle();

        ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
        HttpServletResponse response = ServletActionContext.getResponse();

        response.setContentType("application/msexcel");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xls");

        OutputStream os = response.getOutputStream();
        excelHandle.export(os, downloadData);
        if (excelHandle.hasErrors()) {
            ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(true);
            setActionMessages(getMessageText(excelHandle.getErrors()));
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String changeStatus()throws Exception {
    	if(this.getStatusType() == 1){
    		//TODO 重新充值
    	} else if(this.getStatusType() == 2){
    		//TODO 更新为成功，发送通知
    	}else if(this.getStatusType() == 3){
    		//TODO 更新为失败，重新充值，活直接发送通知
    	}
    	TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
        int ret = topupBussiness.updateStatus(loginUser,affirmChkList, statusType);
        StringBuilder sb = new StringBuilder();
        ActionContext.getContext().getActionInvocation().getProxy().setExecuteResult(false);
        if(ret == 0){
        	sb.append("<ret>");
        	sb.append(1);
        	sb.append("</ret>");
        }else{
        	sb.append("<ret>");
        	sb.append(0);
        	sb.append("</ret>");
        }
        outXml(sb.toString());
        return SUCCESS;
    }
    
    /**
     * @return the installationList
     */
    public List<TopupRecord> getTopupOrderList() {
        return topupOrderList;
    }
 
	public TopupRecord getTopupRecord() {
		return topupRecord;
	}

	public void setTopupRecord(TopupRecord topupRecord) {
		this.topupRecord = topupRecord;
	}

	public Integer getSelectedProvinceId() {
		return selectedProvinceId;
	}

	public void setSelectedProvinceId(Integer selectedProvinceId) {
		this.selectedProvinceId = selectedProvinceId;
	}

	public String getSelectedChannelId() {
		return selectedChannelId;
	}

	public void setSelectedChannelId(String selectedChannelId) {
		this.selectedChannelId = selectedChannelId;
	}

	public Integer getCurrentTotalNum() {
		return currentTotalNum;
	}

	public void setCurrentTotalNum(Integer currentTotalNum) {
		this.currentTotalNum = currentTotalNum;
	}

	public List<String> getAffirmChkList() {
		return affirmChkList;
	}

	public void setAffirmChkList(List<String> affirmChkList) {
		this.affirmChkList = affirmChkList;
	}

	public Integer getStatusType() {
		return statusType;
	}

	public void setStatusType(Integer statusType) {
		this.statusType = statusType;
	}

    

    
    
}
