
package cn.com.bhh.erp.entity;

import java.io.Serializable;


@SuppressWarnings("serial")
public class HhmtRequest implements Serializable {
    private String id;
    private Integer customerId;
    private String customerName;
    private Float requestSum;
    private String requestTime;
    private Integer type;
    private Integer requestUser;
    private Integer status;
    private Integer approveUser;
    private String approveTime;
    private String commnets;
    private Float balanceSnap;
    private String startDate;
    private String endDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Float getRequestSum() {
		return requestSum;
	}
	public void setRequestSum(Float requestSum) {
		this.requestSum = requestSum;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getRequestUser() {
		return requestUser;
	}
	public void setRequestUser(Integer requestUser) {
		this.requestUser = requestUser;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getApproveUser() {
		return approveUser;
	}
	public void setApproveUser(Integer approveUser) {
		this.approveUser = approveUser;
	}
	public String getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}
	public String getCommnets() {
		return commnets;
	}
	public void setCommnets(String commnets) {
		this.commnets = commnets;
	}
	public Float getBalanceSnap() {
		return balanceSnap;
	}
	public void setBalanceSnap(Float balanceSnap) {
		this.balanceSnap = balanceSnap;
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
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
    
}
