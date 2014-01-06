
package cn.com.bhh.erp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import cn.com.bhh.erp.business.ChannelBusiness;
import cn.com.bhh.erp.entity.Channel;
import cn.com.bhh.erp.entity.ChannelAccumulate;

/**
 * prepare the data for the channel accumulation.
 * 
 */
@SuppressWarnings("serial")
public class ChannelAccumuAction extends BaseAction {
	private ChannelAccumulate channelAccumu= new ChannelAccumulate();
	private List<ChannelAccumulate> channelAccumuList = new ArrayList<ChannelAccumulate>();
	private Channel channel = new Channel();
    private String preAction = null;
    private Integer selectedAccuType = null;
   
    
    public String SearchChannelAccumuList()throws Exception {
        //unconfirmed installation list
        ChannelBusiness channelBussiness = new ChannelBusiness();
        if(channel.getId() != null){
        	channelAccumu.setChannelId(channel.getId());
        }
        int totalNum = channelBussiness.getChannelAccuCount(channelAccumu, loginUser);
        setTotalCount(totalNum);
        channelAccumuList = channelBussiness.searchChannelAccuList(channelAccumu, loginUser,currPage, pageSize);
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
    public String ChannelAccumulatePre() throws Exception {
    	channelAccumu= new ChannelAccumulate();
    	ChannelBusiness channelBusiness = new ChannelBusiness();
    	Channel channelOut = channelBusiness.getSingleChannel(channel,false);
    	if (channelBusiness.hasErrors()) {
            setActionMessages(getMessageText(channelBusiness.getErrors()));
            return INPUT;
        }
    	channelAccumu.setChannelId(channel.getId());
    	channelAccumu.setChannelSName(channelOut.getSname());
    	channelAccumu.setChannelName(channelOut.getChannelName());
    	channelAccumu.setChannelId(channelOut.getId());
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
    public String ChannelAccumulate() throws Exception {

        ChannelBusiness channelBusiness = new ChannelBusiness();
        channelBusiness.Accumulate(channelAccumu,loginUser);
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

	public ChannelAccumulate getChannelAccumu() {
		return channelAccumu;
	}

	public void setChannelAccumu(ChannelAccumulate channelAccumu) {
		this.channelAccumu = channelAccumu;
	}

	public String getPreAction() {
		return preAction;
	}

	public void setPreAction(String preAction) {
		this.preAction = preAction;
	}

	

	public List<ChannelAccumulate> getChannelAccumuList() {
		return channelAccumuList;
	}

	public void setChannelAccumuList(List<ChannelAccumulate> channelAccumuList) {
		this.channelAccumuList = channelAccumuList;
	}

	public Integer getSelectedAccuType() {
		return selectedAccuType;
	}

	public void setSelectedAccuType(Integer selectedAccuType) {
		this.selectedAccuType = selectedAccuType;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}


}
