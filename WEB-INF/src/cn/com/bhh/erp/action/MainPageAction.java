
package cn.com.bhh.erp.action;

import java.util.ArrayList;
import java.util.List;

import cn.com.bhh.erp.business.TopupOrderBusiness;
import cn.com.bhh.erp.entity.TopupRecord;

/**
 * prepare the data for the main page.
 * 
 */
@SuppressWarnings("serial")
public class MainPageAction extends BaseAction {
	private TopupRecord topupRecord = new TopupRecord();
    private List<TopupRecord> topupOrderList = new ArrayList<TopupRecord>();
   

    public String execute() throws Exception {
        //unconfirmed installation list
        TopupOrderBusiness topupBussiness = new TopupOrderBusiness();
        topupOrderList = topupBussiness.serchUseStatus(loginUser);
        if (topupBussiness.hasErrors()) {
            setActionMessages(getMessageText(topupBussiness.getErrors()));
            return INPUT;
        }      

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

    

    
    
}
