
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ChannelAccumulate implements Serializable {
    private String id;
    private String channelName;
    private Float amount;
    private Integer channelId;
    private String opTime;
    private Integer userId;
    private String userName;
    private Float balanceSnap;
    private Integer type;
    private String typeName;
    private String commnets;
    private String channelSName;
    private String startDate;
    private String endDate;
    private Float blance;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getOpTime() {
		return opTime;
	}
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getCommnets() {
		return commnets;
	}
	public void setCommnets(String commnets) {
		this.commnets = commnets;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endData) {
		this.endDate = endData;
	}
	public String getChannelSName() {
		return channelSName;
	}
	public void setChannelSName(String channelSName) {
		this.channelSName = channelSName;
	}
	public Float getBalanceSnap() {
		return balanceSnap;
	}
	public void setBalanceSnap(Float balanceSnap) {
		this.balanceSnap = balanceSnap;
	}
	public Float getBlance() {
		return blance;
	}
	public void setBlance(Float blance) {
		this.blance = blance;
	}
   
}
