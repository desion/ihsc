
package cn.com.bhh.erp.component;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.action.BaseAction;
import cn.com.bhh.erp.business.ChannelBusiness;
import cn.com.bhh.erp.entity.Channel;


/**
 * this is a component to list all
 * the province from province_tbl,and select
 * the relative item according to the
 * selectedProvinceId
 * @author  xiangzq
 * @version 1.0
 * @since   1.0
 */
@SuppressWarnings("serial")
public class ChannelDropAction extends BaseAction {
    private List<Channel> channelDropList = new ArrayList<Channel>();
    private Integer selectedChannelId;
    public ChannelDropAction() {
       initData();
    }

    @Override
    public String execute() throws Exception {
        initData();
        return SUCCESS;
    }

    private void initData(){
        ChannelBusiness channelBusiness = new ChannelBusiness();
        channelDropList = channelBusiness.searchAllChannel();
    }

	public Integer getSelectedChannelId() {
		return selectedChannelId;
	}

	public void setSelectedChannelId(Integer selectedChannelId) {
		this.selectedChannelId = selectedChannelId;
	}

	public List<Channel> getChannelDropList() {
		return channelDropList;
	}
    
    
}
