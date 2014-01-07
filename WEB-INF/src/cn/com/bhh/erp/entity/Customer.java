
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class Customer implements Serializable {
    private Integer id;
    private Integer userId;
    private String userName;
    private String sname;
    private String customerName;
    private Float balance;
    private String mobile;
    private String email;
    private String callbackUrl;
    private Integer exclusiveKey;
    private Integer status;
    private String signature;
    private String passWord;
    private Float totalCharge;
    private Float lastCharge;
    private String lastChargeTime;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public Integer getExclusiveKey() {
		return exclusiveKey;
	}
	public void setExclusiveKey(Integer exclusiveKey) {
		this.exclusiveKey = exclusiveKey;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Float getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Float totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Float getLastCharge() {
		return lastCharge;
	}
	public void setLastCharge(Float lastCharge) {
		this.lastCharge = lastCharge;
	}
	public String getLastChargeTime() {
		return lastChargeTime;
	}
	public void setLastChargeTime(String lastChargeTime) {
		this.lastChargeTime = lastChargeTime;
	}
    
    
}
