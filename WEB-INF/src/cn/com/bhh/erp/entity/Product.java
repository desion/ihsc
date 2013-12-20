package cn.com.bhh.erp.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.crypto.Data;

import cn.com.bhh.erp.common.DateFormat;
import cn.com.bhh.erp.common.Operator;
import cn.com.bhh.erp.common.StatusEnum;
import cn.com.bhh.erp.common.TimeUtil;

/**
 * Class of Product Structure
 * @author desion
 *
 */
@SuppressWarnings("serial")
public class Product implements Serializable{

    private Integer id;
    private String title;
    private Integer zone;
    private String zoneName;
    private float price;
    private Integer status;
    private String delistReason;
    private Integer operateUser;
    private String opUserName;
    private String operateTime;
    private Integer exclusiveKey;
    private Integer value;
    private Integer operator;
    private String operatorName;
    private String statusName;
    private Integer index;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getZone() {
		return zone;
	}
	public void setZone(Integer zone) {
		this.zone = zone;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDelistReason() {
		return delistReason;
	}
	public void setDelistReason(String delistReason) {
		this.delistReason = delistReason;
	}
	public Integer getOperateUser() {
		return operateUser;
	}
	public void setOperateUser(Integer operateUser) {
		this.operateUser = operateUser;
	}
	public String getOperateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmSS");
		Date date;
		try {
			date = dateFormat.parse(operateTime);
			return date.toLocaleString();
		} catch (ParseException e) {
			return operateTime;
		}
		
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public Integer getExclusiveKey() {
		return exclusiveKey;
	}
	public void setExclusiveKey(Integer exclusiveKey) {
		this.exclusiveKey = exclusiveKey;
	}
	public String getZoneName() {
		return zoneName;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	public String getOperatorName() {
		Operator op;
		if(this.getOperator() == 1){
			op = Operator.MOBILE;
		}else if(this.getOperator() == 2){
			op = Operator.UNICOM;
		}else if(this.getOperator() == 3){
			op = Operator.TELECOM;
		}else{
			op = Operator.OTHER;
		}
		return op.toString();
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getStatusName() {
		return StatusEnum.nameOf(this.getStatus());
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getOpUserName() {
		return opUserName;
	}
	public void setOpUserName(String opUserName) {
		this.opUserName = opUserName;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
    


}
