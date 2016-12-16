package com.fin2;

import java.text.NumberFormat;

public class Month {
	private double initialBalance;
	private double interestAccumulatedThisPeriod;
	private double totalInterestAccumulated;
	private double amountPaidThisPeriod;
	private double principleThisPeriod;
	private double endingBalance;
	
	public Month(double initialBalance, double interestAccumulatedThisPeriod, double totalInterestAccumulated, double amountPaidThisPeriod, double principleThisPeriod, double endingBalance) {
		this.initialBalance = initialBalance;
		this.interestAccumulatedThisPeriod = interestAccumulatedThisPeriod;
		this.totalInterestAccumulated = totalInterestAccumulated;
		this.amountPaidThisPeriod = amountPaidThisPeriod;
		this.principleThisPeriod = principleThisPeriod;
		this.endingBalance = endingBalance;
	}
	public String toString() {
		NumberFormat c = NumberFormat.getCurrencyInstance();
		NumberFormat p = NumberFormat.getPercentInstance();
		return "" + c.format(this.initialBalance) +"\t" + c.format(this.totalInterestAccumulated) + "\t" + c.format(this.interestAccumulatedThisPeriod) + "\t" + c.format(this.amountPaidThisPeriod) + "\t" + c.format(this.principleThisPeriod) + "\t" + c.format(this.endingBalance);
	}
}
