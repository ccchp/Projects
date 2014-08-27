package com.lex.accounting.entity;

public class Account extends BaseEntity{

	private String name;
	private double initialMoney;
	private double incomeMoney;
	private double expenditureMoney;
	private double borrowMoney;
	private double loanMondy;
	private double inMoney;
	private double outMoney;
	private double balance;
	private String comments;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getInitialMoney() {
		return initialMoney;
	}
	public void setInitialMoney(double initialMoney) {
		this.initialMoney = initialMoney;
	}
	public double getIncomeMoney() {
		return incomeMoney;
	}
	public void setIncomeMoney(double incomeMoney) {
		this.incomeMoney = incomeMoney;
	}
	public double getExpenditureMoney() {
		return expenditureMoney;
	}
	public void setExpenditureMoney(double expenditureMoney) {
		this.expenditureMoney = expenditureMoney;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public double getBorrowMoney() {
		return borrowMoney;
	}
	public void setBorrowMoney(double borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	public double getLoanMondy() {
		return loanMondy;
	}
	public void setLoanMondy(double loanMondy) {
		this.loanMondy = loanMondy;
	}
	public double getInMoney() {
		return inMoney;
	}
	public void setInMoney(double inMoney) {
		this.inMoney = inMoney;
	}
	public double getOutMoney() {
		return outMoney;
	}
	public void setOutMoney(double outMoney) {
		this.outMoney = outMoney;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
