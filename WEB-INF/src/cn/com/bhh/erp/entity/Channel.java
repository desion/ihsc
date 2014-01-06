
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Channel implements Serializable {
    private Integer id;
    private String sname;
    private String channelName;
    private Float balance;
    private Float deposited;
    private Float predictDeposit;
    private Float monthAverage;
    private Float alertThreshold;
    private Float suspendThreshold;
    private Integer status;
    private String interfaceName;
    private Integer exclusiveKey;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getChannelName() {
		return channelName;
	}
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Float getDeposited() {
		return deposited;
	}
	public void setDeposited(Float deposited) {
		this.deposited = deposited;
	}
	public Float getPredictDeposit() {
		return predictDeposit;
	}
	public void setPredictDeposit(Float predictDeposit) {
		this.predictDeposit = predictDeposit;
	}
	public Float getMonthAverage() {
		return monthAverage;
	}
	public void setMonthAverage(Float monthAverage) {
		this.monthAverage = monthAverage;
	}
	public Float getAlertThreshold() {
		return alertThreshold;
	}
	public void setAlertThreshold(Float alertThreshold) {
		this.alertThreshold = alertThreshold;
	}
	public Float getSuspendThreshold() {
		return suspendThreshold;
	}
	public void setSuspendThreshold(Float suspendThreshold) {
		this.suspendThreshold = suspendThreshold;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public Integer getExclusiveKey() {
		return exclusiveKey;
	}
	public void setExclusiveKey(Integer exclusiveKey) {
		this.exclusiveKey = exclusiveKey;
	}
    
    
}
