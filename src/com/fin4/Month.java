package com.fin4;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;

public class Month {
	private BigDecimal startBalance;
	private BigDecimal interest;
	private BigDecimal totalInterest;
	private BigDecimal payment;
	private BigDecimal endBalance;
	
	public Month(BigDecimal startBalance, BigDecimal interest, BigDecimal totalInterest, BigDecimal payment, BigDecimal endBalance) {
		this.startBalance = startBalance.divide(new BigDecimal(100), new MathContext(100,RoundingMode.DOWN));
		this.interest = interest.divide(new BigDecimal(100), new MathContext(100,RoundingMode.DOWN));
		this.totalInterest = totalInterest.divide(new BigDecimal(100), new MathContext(100,RoundingMode.DOWN));
		this.payment = payment.divide(new BigDecimal(100), new MathContext(100,RoundingMode.DOWN));
		this.endBalance = endBalance.divide(new BigDecimal(100), new MathContext(100,RoundingMode.DOWN));
	}
	public BigDecimal getStartBalance() {
		return this.startBalance;
	}
	public String getStartBalanceFormatted() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(startBalance.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
	public BigDecimal getInterest() {
		return this.interest;
	}
	public String getInterestFormatted() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(interest.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
	public BigDecimal getTotalInterest() {
		return this.totalInterest;
	}
	public String getTotalInterestFormatted() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(totalInterest.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
	public BigDecimal getPayment() {
		return this.payment;
	}
	public String getPaymentFormatted() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(payment.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
	public BigDecimal getEndBalance() {
		return this.endBalance;
	}
	public String getEndBalanceFormatted() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(endBalance.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
	public String toString() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return this.getStartBalanceFormatted() + "\t" + this.getInterestFormatted() + "\t" + this.getTotalInterestFormatted() + "\t" + this.getPaymentFormatted() + "\t" + this.getEndBalanceFormatted();
	}
}
