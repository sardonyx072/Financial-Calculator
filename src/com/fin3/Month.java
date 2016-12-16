package com.fin3;

import java.text.NumberFormat;

public class Month {
	private double startBalance;
	private double interest;
	private double totalInterest;
	private double payment;
	private double endBalance;
	
	public Month(double startBalance, double interest, double totalInterest, double payment, double endBalance) {
		this.startBalance = startBalance;
		this.interest = interest;
		this.totalInterest = totalInterest;
		this.payment = payment;
		this.endBalance = endBalance;
	}
//	public String toString() {
//		return this.startBalance + "\t" + this.interest + "\t" + this.totalInterest + "\t" + this.payment + "\t" + this.endBalance;
//	}
	public String toString() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(this.startBalance) + "\t" + f.format(this.interest) + "\t" + f.format(this.totalInterest) + "\t" + f.format(this.payment) + "\t" + f.format(this.endBalance);
	}
}
