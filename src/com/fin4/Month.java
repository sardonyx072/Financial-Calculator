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
	public BigDecimal getInterest() {
		return this.interest;
	}
	public BigDecimal getTotalInterest() {
		return this.totalInterest;
	}
	public BigDecimal getPayment() {
		return this.payment;
	}
	public BigDecimal getEndBalance() {
		return this.endBalance;
	}
	public String toString() {
		NumberFormat f = NumberFormat.getCurrencyInstance();
		return f.format(startBalance.setScale(2,RoundingMode.HALF_UP).doubleValue()) + "\t" + f.format(interest.setScale(2,RoundingMode.HALF_UP).doubleValue()) + "\t" + f.format(totalInterest.setScale(2,RoundingMode.HALF_UP).doubleValue()) + "\t" + f.format(payment.setScale(2,RoundingMode.HALF_UP).doubleValue()) + "\t" + f.format(endBalance.setScale(2,RoundingMode.HALF_UP).doubleValue());
	}
}
