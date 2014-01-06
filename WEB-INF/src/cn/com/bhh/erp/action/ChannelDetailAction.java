
package cn.com.bhh.erp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.ChannelBusiness;
import cn.com.bhh.erp.entity.Channel;
import cn.com.bhh.erp.entity.ChannelDetail;

/**
 * prepare the data for the channel.
 * 
 */
@SuppressWarnings("serial")
public class ChannelDetailAction extends BaseAction {
	private Channel channel = new Channel();
	private ChannelDetail channelDetail = new ChannelDetail();
    private List<ChannelDetail> channelDetailList = new ArrayList<ChannelDetail>();
    private Integer selectedProvinceId = null;
    private Integer selectedChannelId = null;
    private String preAction = null;
    private Integer selectOperator = null;
   
    
    public String SearchChannelList()throws Exception {
        //unconfirmed installation list
        ChannelBusiness channelBussiness = new ChannelBusiness();
        int totalNum = channelBussiness.getChannelDetailCount(channelDetail, loginUser);
        setTotalCount(totalNum);
        channelDetailList = channelBussiness.searchChannelDetailList(channel, loginUser,currPage, pageSize);
        if (channelBussiness.hasErrors()) {
            setActionMessages(getMessageText(channelBussiness.getErrors()));
            return INPUT;
        }      

        return SUCCESS;
    } 
    
    /**
     * direct to create channel page.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createChannelPre() throws Exception {
    	channelDetail = new ChannelDetail();
    	ChannelBusiness channelBusiness = new ChannelBusiness();
    	Channel channelOut = channelBusiness.getSingleChannel(channel,false);
    	if (channelBusiness.hasErrors()) {
            setActionMessages(getMessageText(channelBusiness.getErrors()));
            return INPUT;
        }
    	channelDetail.setSname(channelOut.getSname());
    	channelDetail.setName(channelOut.getChannelName());
    	channelDetail.setChannelId(channelOut.getId());
        return SUCCESS;
    }
    
    /**
     * create the channel operation.
     * 
     * @auther desionwang
     * @version 1.0
     * @since 1.0
     * @return String
     * @throws Exception
     */
    public String createChannel() throws Exception {

        ChannelBusiness channelBusiness = new ChannelBusiness();
        channelBusiness.createChannelDetail(channelDetail);
        if (channelBusiness.hasErrors()) {
            setActionMessages(getMessageText(channelBusiness.getErrors()));
            return INPUT;
        }

        return SUCCESS;
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


	public Channel getChannel() {
		return channel;
	}


	public void setChannel(Channel channel) {
		this.channel = channel;
	}




	public Integer getSelectedProvinceId() {
		return selectedProvinceId;
	}


	public void setSelectedProvinceId(Integer selectedProvinceId) {
		this.selectedProvinceId = selectedProvinceId;
	}


	public Integer getSelectedChannelId() {
		return selectedChannelId;
	}


	public void setSelectedChannelId(Integer selectedChannelId) {
		this.selectedChannelId = selectedChannelId;
	}

	public ChannelDetail getChannelDetail() {
		return channelDetail;
	}

	public void setChannelDetail(ChannelDetail channelDetail) {
		this.channelDetail = channelDetail;
	}

	public List<ChannelDetail> getChannelDetailList() {
		return channelDetailList;
	}

	public void setChannelDetailList(List<ChannelDetail> channelDetailList) {
		this.channelDetailList = channelDetailList;
	}

	public String getPreAction() {
		return preAction;
	}

	public void setPreAction(String preAction) {
		this.preAction = preAction;
	}

	public Integer getSelectOperator() {
		return channelDetail.getOperator();
	}

	public void setSelectOperator(Integer selectOperator) {
		this.selectOperator = selectOperator;
	}


    
}
