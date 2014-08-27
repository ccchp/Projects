package com.lex.accounting.entity;

public class IncomeExpenditureEntity extends BaseEntity{

	private long time;
	private int category_id;
	private double money;
	private int acctout_id;
	private String comments;
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getAcctout_id() {
		return acctout_id;
	}
	public void setAcctout_id(int acctout_id) {
		this.acctout_id = acctout_id;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
