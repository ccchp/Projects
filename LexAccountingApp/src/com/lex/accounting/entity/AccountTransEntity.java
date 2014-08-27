package com.lex.accounting.entity;

public class AccountTransEntity extends BaseEntity {

	private long transTime;
	private String transFrom;
	private String transTo;
	private double transMoney;
	private String comments;
	public long getTransTime() {
		return transTime;
	}
	public void setTransTime(long transTime) {
		this.transTime = transTime;
	}
	public String getTransFrom() {
		return transFrom;
	}
	public void setTransFrom(String transFrom) {
		this.transFrom = transFrom;
	}
	public String getTransTo() {
		return transTo;
	}
	public void setTransTo(String transTo) {
		this.transTo = transTo;
	}
	public double getTransMoney() {
		return transMoney;
	}
	public void setTransMoney(double transMoney) {
		this.transMoney = transMoney;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
