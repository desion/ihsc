package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.bhh.erp.common.Operator;
import cn.com.bhh.erp.common.OrderStatusEnum;
import cn.com.bhh.erp.common.SourceEnum;

@SuppressWarnings("serial")
public class TopupRecord implements Serializable{
	private String phoneNum;
	private Long topupPhone;
	private Integer province;
	private String provinceName;
	private Integer sum;
	private String systemNo;
	private String topupNo;
	private String requestNo;
	private Integer status;
	private String createTime;
	private String updateTime;
	private Integer notify;
	private Integer source;
	private Integer proxy;
	private Integer operator;
	private Float salePrice;
	private Float inPrice;
	private Float profit;
	private String startDate;
	private String endDate;
	private String statusName;
	private String operatorName;
	private String noticeName;
	private String sourceName;
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public Long getTopupPhone() {
		return topupPhone;
	}
	public void setTopupPhone(Long topupPhone) {
		this.topupPhone = topupPhone;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getSum() {
		return sum;
	}
	public void setSum(Integer sum) {
		this.sum = sum;
	}
	public String getSystemNo() {
		return systemNo;
	}
	public void setSystemNo(String systemNo) {
		this.systemNo = systemNo;
	}
	public String getTopupNo() {
		return topupNo;
	}
	public void setTopupNo(String topupNo) {
		this.topupNo = topupNo;
	}
	public String getRequestNo() {
		return requestNo;
	}
	public void setRequestNo(String requestNo) {
		this.requestNo = requestNo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getCreateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		Date date;
		try {
			date = dateFormat.parse(createTime);
			dateFormat = new SimpleDateFormat("MM/dd HH:mm:SS");
			return dateFormat.format(date);
		} catch (ParseException e) {
			return createTime;
		}
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		Date date;
		try {
			date = dateFormat.parse(updateTime);
			dateFormat = new SimpleDateFormat("MM/dd HH:mm:SS");
			return dateFormat.format(date);
		} catch (ParseException e) {
			return updateTime;
		}
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getNotify() {
		return notify;
	}
	public void setNotify(Integer notify) {
		this.notify = notify;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public Integer getProxy() {
		return proxy;
	}
	public void setProxy(Integer proxy) {
		this.proxy = proxy;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public Float getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Float salePrice) {
		this.salePrice = salePrice;
	}
	public Float getInPrice() {
		return inPrice;
	}
	public void setInPrice(Float inPrice) {
		this.inPrice = inPrice;
	}
	public Float getProfit() {
		return profit;
	}
	public void setProfit(Float profit) {
		this.profit = profit;
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
	public String getStatusName() {
		return OrderStatusEnum.nameOf(this.status);
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getOperatorName() {
		return Operator.nameOf(this.getOperator());
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getNoticeName() {
		if(this.notify == 0){
			return "未通知";
		}else if(this.notify == 1){
			return "已通知";
		}else{
			return "--";
		}
	}
	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	public String getSourceName() {
		return SourceEnum.nameOf(this.source);
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	
}
