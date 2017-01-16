package com.os.vos;

import com.os.utils.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class HangupTabelInfoVO {
	private String billno;
	
	private String tableName;
	
	private BigDecimal consumAmount;
	
	private BigDecimal nodiscountAmount;
	
	private BigDecimal discountAmount;
	
	private Timestamp opendeskTime;
	
	private List<OrderItemVO> items; 

	public String getBillno() {
		return StringUtils.trimToEmpty(billno);
	}

	public String getTableName() {
		return tableName;
	}

	public BigDecimal getConsumAmount() {
		return consumAmount;
	}

	public BigDecimal getNodiscountAmount() {
		return nodiscountAmount;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public Timestamp getOpendeskTime() {
		return opendeskTime;
	}

	public void setBillno(String billno) {
		this.billno = billno;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setConsumAmount(BigDecimal consumAmount) {
		this.consumAmount = consumAmount;
	}

	public void setNodiscountAmount(BigDecimal nodiscountAmount) {
		this.nodiscountAmount = nodiscountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setOpendeskTime(Timestamp opendeskTime) {
		this.opendeskTime = opendeskTime;
	}

	public List<OrderItemVO> getItems() {
		return items;
	}

	public void setItems(List<OrderItemVO> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17,37).append(this.opendeskTime).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if(obj != null && HangupTabelInfoVO.class.isAssignableFrom(obj.getClass())){
			HangupTabelInfoVO o = (HangupTabelInfoVO) obj;
			isEqual = new EqualsBuilder().append(this.opendeskTime, o.opendeskTime).isEquals();
		}
		return isEqual;
	}
}
