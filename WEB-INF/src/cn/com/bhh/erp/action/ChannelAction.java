
package cn.com.bhh.erp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.ChannelBusiness;
import cn.com.bhh.erp.entity.Channel;

/**
 * prepare the data for the channel.
 * 
 */
@SuppressWarnings("serial")
public class ChannelAction extends BaseAction {
	private Channel channel = new Channel();
    private List<Channel> channelList = new ArrayList<Channel>();
    private Integer selectedProvinceId = null;
    private Integer selectedChannelId = null;
    private List<String> affirmChkList = new ArrayList<String>();
   
    
    public String SearchChannelList()throws Exception {
        //unconfirmed installation list
        ChannelBusiness channelBussiness = new ChannelBusiness();
        int totalNum = channelBussiness.getChannelCount(channel, loginUser);
        setTotalCount(totalNum);
        channelList = channelBussiness.searchChannelList(channel, loginUser,currPage, pageSize);
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
        channel = new Channel();
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
        channelBusiness.createChannel(channel);
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


	public List<Channel> getChannelList() {
		return channelList;
	}


	public void setChannelList(List<Channel> channelList) {
		this.channelList = channelList;
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


	public List<String> getAffirmChkList() {
		return affirmChkList;
	}


	public void setAffirmChkList(List<String> affirmChkList) {
		this.affirmChkList = affirmChkList;
	}
    
}
