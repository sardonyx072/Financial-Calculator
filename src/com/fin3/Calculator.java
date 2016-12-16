package com.fin3;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

public class Calculator {
	private List<Month> months;
	
//	public Calculator (double initialBalance, double apr, int numberOfPayments) {
//		this.months = new ArrayList<Month>();
//		double runningBalance = initialBalance;
//		double runningInterest = 0;
//		double interest = 0;
//		double mpr = apr/12, mprMult = mpr+1;
//		double payment = (mpr*initialBalance)/(1-(1/Math.pow(mprMult, numberOfPayments)));
//		int cleanPayment = (int)(payment*100);
//		double extra = (payment*numberOfPayments)-(numberOfPayments*cleanPayment/100.0);
//		int cleanExtra = (int)(extra*100);
//		for (int i = 0; i < numberOfPayments; i++) {
//			interest = runningBalance*mpr;
//			runningInterest += interest;
//			double curPayment = (cleanPayment + (i == numberOfPayments-1 ? cleanExtra : 0))/100.0;
//			double endBalance = runningBalance+interest-curPayment;
//			months.add(new Month(runningBalance,interest,runningInterest,curPayment,endBalance));
//			runningBalance = endBalance;
//		}
//	}
	public Calculator (double initialBalance, double apr, int numberOfPayments) {
		this.months = new ArrayList<Month>();
		int startBalance = (int)(initialBalance*100);
		int interest = 0;
		int totalInterest = 0;
		double mpr = apr/12;
		BigDecimal precise;
		double paymentPrecise;
		int payment;
		if (apr == 0) {
			precise = new BigDecimal(startBalance).divide(new BigDecimal(numberOfPayments), new MathContext(100,RoundingMode.UP));
			precise = precise.setScale(0, RoundingMode.UP);
			paymentPrecise = startBalance*1.0/numberOfPayments;
			payment = (int)(startBalance/numberOfPayments);
		}
		else {
			precise = new BigDecimal(mpr*startBalance).divide(new BigDecimal(1).subtract(new BigDecimal(1).divide(new BigDecimal(Math.pow(1+mpr, numberOfPayments)),new MathContext(100,RoundingMode.UP))),new MathContext(100,RoundingMode.UP));
			precise = precise.setScale(0, RoundingMode.UP);
			paymentPrecise = (mpr*startBalance)/(1-(1/Math.pow(1+mpr, numberOfPayments)));
			payment = (int)((mpr*startBalance)/(1-(1/Math.pow(1+mpr, numberOfPayments))));
			
		}
		System.out.println("precise 100 digits:" + precise);
		System.out.println("precise rounded up 100 digits:" + precise);
		System.out.println("double precise:" + paymentPrecise);
		System.out.println("int payment/payment base:" + payment);
		//int extra = (int)Math.ceil((paymentPrecise*numberOfPayments)-(numberOfPayments*payment));
		int extra = (numberOfPayments*precise.intValue())-(numberOfPayments*payment);
		System.out.println("difference between base and final payment:" + extra);
		for (int i = 0; i < numberOfPayments; i++) {
			interest = (int)(startBalance*mpr); //loss;
			totalInterest += interest;
			int curPayment = (payment + (i == numberOfPayments-1 ? extra : 0));
			int endBalance = startBalance+interest-curPayment;
			months.add(new Month(startBalance/100.0,interest/100.0,totalInterest/100.0,curPayment/100.0,endBalance/100.0));
			startBalance = endBalance;
		}
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < this.months.size(); i++) {
			builder.append((i == 0 ? "" : "\n") + i + ". " + this.months.get(i));
		}
		return builder.toString();
	}
}
