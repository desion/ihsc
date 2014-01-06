
package cn.com.bhh.erp.entity;

import java.io.Serializable;

import cn.com.bhh.erp.common.ChannelStatusEnum;
import cn.com.bhh.erp.common.Operator;


@SuppressWarnings("serial")
public class ChannelDetail implements Serializable {
    private Integer id;
    private Integer channelId;
    private String name;
    private String sname;
    private Integer value;
    private Integer provinceId;
    private String province;
    private Integer operator;
    private String operatorName;
    private Integer status;
    private String statusName;
    private Integer priority;
    private Float discount;
    private Integer repeat;
    private String interfaceName;
    private String comments;
    private String valueStr;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Float getDiscount() {
		return discount;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public void setDiscount(Float discount) {
		this.discount = discount;
	}
	public Integer getRepeat() {
		return repeat;
	}
	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getValueStr() {
		return valueStr;
	}
	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}
	public String getOperatorName() {
		return Operator.nameOf(this.operator);
	}
	public void setOperatorName(String opertorName) {
		this.operatorName = opertorName;
	}
	public String getStatusName() {
		return ChannelStatusEnum.nameOf(this.status);
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
    
}
