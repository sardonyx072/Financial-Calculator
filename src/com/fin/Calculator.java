package com.fin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
	private static final int PRECISION = 100;
	private static final MathContext CONTEXT_UP = new MathContext(PRECISION,RoundingMode.UP);
	private static final MathContext CONTEXT_DOWN = new MathContext(PRECISION,RoundingMode.DOWN);
	private List<Month> months;
	private double initialBalance;
	private double apr;
	
	/*
	 * Equations from http://brownmath.com/bsci/loan.htm
	 * where:
	 * 	A = loan amount (principle sum)
	 * 	B = balance remaining on loan (after n payments)
	 * 	F = future amount accumulated by stream of payments
	 * 	P = payment amount
	 * 	r = monthly interest rate
	 * 	n = number of months elapsed
	 * 	N = number of payments needed
	 * 
	 * (1) Loan balance after n payments
	 * B = A((1+r)^n)-(P/r)[((1+r)^n)-1]
	 * 
	 * (2) Payment amount on a loan
	 *          rA
	 * P = ------------
	 *     1-((1+r)^-N)
	 * 
	 * (3) Number of payments on a loan
	 *     -log(1-r(A/P))
	 * N = --------------
	 *        log(1+r)
	 * 
	 * (4) Original loan amount
	 * A = (P/r)[1-((1+r)^-N)]
	 * 
	 * (5) Payment amount to reach an investment goal
	 *          rF
	 * P = ------------
	 *     ((1+r)^N)-1)
	 * 
	 * (6) Number of payments to reach an investment goal
	 *     log(1+r(F/P)
	 * N = ------------
	 *       log(1+r)
	 * 
	 * (7) Interest rate
	 * 
	 * r = 2u + 2(u^2)(N-1)[(-1/3) + (2N+1)(u/9) - (2N+1)(11N+7)((u^2)/135) + ((2N+1)^2)(13N+11)((u^3)/405) - ...]
	 * or
	 * r =approx= [((1+(P/A))^(1/q))-1]^q)-1
	 * 
	 *	          (PN/A)-1         log(1+(1/N))
	 * 	where u = -------- and q = ------------
	 * 	            N+1               log(2)
	 * 
	 */
	
	public Calculator(double initialBalance, double apr, int numberOfMonths) {
		System.out.println("Calculating monthly payment amounts from initial balance, apr, and number of months...");
		System.out.println("inputting initial balance = " + NumberFormat.getCurrencyInstance().format(initialBalance) + ", apr = " + NumberFormat.getPercentInstance().format(apr/100) + ", number of months = " + numberOfMonths);
		/*
		 * (2) Payment amount on a loan
		 *          rA
		 * P = ------------
		 *     1-((1+r)^-N)
		 */
		this.months = new ArrayList<Month>();
		this.initialBalance = initialBalance;
		this.apr = apr;
		BigDecimal startBalance = new BigDecimal(initialBalance*100);
		BigDecimal totalInterest = new BigDecimal(0);
		double mpr = apr/100/12;
		BigDecimal precisePayment;
		BigDecimal basePayment;
		int extra;
		if (initialBalance <= 0 || apr < 0 || numberOfMonths <= 0) {
			throw new IllegalArgumentException("Invalid arguments!");
		}
		if (apr == 0) {
			precisePayment = startBalance.divide(new BigDecimal(numberOfMonths),CONTEXT_UP);
		}
		else {
			precisePayment = new BigDecimal(mpr).multiply(startBalance).divide(new BigDecimal(1).subtract(new BigDecimal(1).divide(new BigDecimal(Math.pow(1+mpr, numberOfMonths)),CONTEXT_DOWN)),CONTEXT_UP);
		}
		basePayment = precisePayment.setScale(0, RoundingMode.DOWN);
		extra = precisePayment.multiply(new BigDecimal(numberOfMonths)).setScale(0, RoundingMode.UP).subtract(basePayment.multiply(new BigDecimal(numberOfMonths)).setScale(0, RoundingMode.DOWN)).intValue();
		for(int i = 0; i < numberOfMonths; i++) {
			BigDecimal interest = startBalance.multiply(new BigDecimal(mpr));
			totalInterest = totalInterest.add(interest);
			BigDecimal payment = basePayment;
			if (i == numberOfMonths-1) {
				//payment = payment.add(new BigDecimal(extra)); //always leaves some left over for no reason
				payment = startBalance.add(interest);
			}
			BigDecimal endBalance = startBalance.add(interest).subtract(payment);
			this.months.add(new Month(startBalance,interest,totalInterest,payment,endBalance));
			startBalance = endBalance;
		}
		System.out.println("Result is monthly payment amount = " + NumberFormat.getCurrencyInstance().format(basePayment.divide(new BigDecimal(100))) + " (plus a little extra in the last payment)");
	}
	public Calculator(double initialBalance, double apr, double payment) {
		System.out.println("Calculating number of payments from initial balance, apr, and monthly payment amount...");
		System.out.println("inputting initial balance = " + NumberFormat.getCurrencyInstance().format(initialBalance) + ", apr = " + NumberFormat.getPercentInstance().format(apr/100) + ", monthly payment amount = " + NumberFormat.getCurrencyInstance().format(payment));
		/*
		/*
		 * (3) Number of payments on a loan
		 *     -log(1-r(A/P))
		 * N = --------------
		 *        log(1+r)
		 */
		//but I can just count how many iterations it takes. BigDecimal doesn't have log anyway.
		this.months = new ArrayList<Month>();
		this.initialBalance = initialBalance;
		this.apr = apr;
		if (initialBalance <= 0 || apr < 0 || payment <= 0) {
			throw new IllegalArgumentException("Invalid arguments!");
		}
		BigDecimal startBalance = new BigDecimal(initialBalance*100);
		BigDecimal totalInterest = new BigDecimal(0);
		double mpr = apr/100/12;
		while(startBalance.compareTo(new BigDecimal(0)) > 0) {
			BigDecimal interest = startBalance.multiply(new BigDecimal(mpr));
			totalInterest = totalInterest.add(interest);
			BigDecimal endBalance = startBalance.add(interest).subtract(new BigDecimal(payment*100));
			if (endBalance.compareTo(startBalance) >= 0) {
				throw new RuntimeException("can never pay off this loan!");
			}
			this.months.add(new Month(startBalance,interest,totalInterest,new BigDecimal(payment*100),endBalance));
			startBalance = endBalance;
		}
		System.out.println("Result is number of months = " + this.months.size());
	}
	public Calculator(double initialBalance, int numberOfMonths, double payment) {
		System.out.println("Calculating apr from initial balance, number of months, and monthly payment amount...");
		System.out.println("inputting initial balance = " + NumberFormat.getCurrencyInstance().format(initialBalance) + ", number of months = " + numberOfMonths + ", monthly payment amount = " + NumberFormat.getCurrencyInstance().format(payment));
		/* 
		 * (7) Interest rate
		 * 
		 * r = 2u + 2(u^2)(N-1)[(-1/3) + (2N+1)(u/9) - (2N+1)(11N+7)((u^2)/135) + ((2N+1)^2)(13N+11)((u^3)/405) - ...]
		 * or
		 * r =approx= [((1+(P/A))^(1/q))-1]^q)-1
		 * 
		 *	          (PN/A)-1         log(1+(1/N))
		 * 	where u = -------- and q = ------------
		 * 	            N+1               log(2)
		 */
		//Calculator calc = new Calculator(100000.00,5,20250.69); //calculate apr
		this.months = new ArrayList<Month>();
		this.initialBalance = initialBalance;
		BigDecimal startBalance = new BigDecimal(initialBalance*100);
		BigDecimal totalInterest = new BigDecimal(0);
		if (initialBalance <= 0 || numberOfMonths <= 0 || payment <= 0) {
			throw new IllegalArgumentException("Invalid arguments!");
		}
		BigDecimal u = new BigDecimal(payment*100*numberOfMonths).divide(startBalance,CONTEXT_UP).subtract(new BigDecimal(1)).divide(new BigDecimal(1+numberOfMonths),CONTEXT_UP);
		BigDecimal rEst = new BigDecimal(2).multiply(u).add(
				new BigDecimal(2).multiply(u).multiply(u).multiply(new BigDecimal(numberOfMonths-1)).multiply(
						new BigDecimal(-1).divide(new BigDecimal(3),CONTEXT_UP) //-1/3
						.add(new BigDecimal((2*numberOfMonths)+1).multiply(u).divide(new BigDecimal(9),CONTEXT_UP)) //+(2N+1)(u/9)
						.subtract(new BigDecimal((2*numberOfMonths)+1).multiply(new BigDecimal((11*numberOfMonths)+7)).multiply(u).multiply(u).divide(new BigDecimal(135),CONTEXT_UP)) //-(2N+1)(11N+7)((u^2)/135)
						.add(new BigDecimal((2*numberOfMonths)+1).multiply(new BigDecimal((2*numberOfMonths)+1)).multiply(new BigDecimal((13*numberOfMonths)+11)).multiply(u).multiply(u).multiply(u).divide(new BigDecimal(405),CONTEXT_UP)) //+((2N+1)^2)(13N+11)((u^3)/405)
						)
				);
		this.apr = rEst.setScale(4, RoundingMode.UP).doubleValue()*100*12;
		System.out.println("Result is apr = " + NumberFormat.getPercentInstance().format(this.apr));
		for(int i = 0; i < numberOfMonths; i++) {
			BigDecimal interest = startBalance.multiply(rEst);
			totalInterest = totalInterest.add(interest);
			BigDecimal endBalance = startBalance.add(interest).subtract(new BigDecimal(payment*100));
			this.months.add(new Month(startBalance,interest,totalInterest,new BigDecimal(payment*100),endBalance));
			startBalance = endBalance;
		}
	}
	public Calculator(int numberOfMonths, double payment, double apr) {
		System.out.println("Calculating initial loan amount from number of months, monthly payment amount, and apr...");
		System.out.println("inputting number of months = " + numberOfMonths + ", monthly payment amount = " + NumberFormat.getCurrencyInstance().format(payment)+ ", apr = " + NumberFormat.getPercentInstance().format(apr/100));
		/*
		 * (4) Original loan amount
		 * A = (P/r)[1-((1+r)^-N)]
		 */
		this.months = new ArrayList<Month>();
		this.apr = apr;
		double mpr = apr/100/12;
		BigDecimal startBalance;
		if (numberOfMonths <= 0 || payment <= 0 || apr < 0) {
			throw new IllegalArgumentException("Invalid arguments!");
		}
		if (apr == 0) {
			this.initialBalance = payment*numberOfMonths;
			startBalance = new BigDecimal(this.initialBalance*100);
		}
		else {
			startBalance = new BigDecimal(payment*100).divide(new BigDecimal(mpr),CONTEXT_UP).multiply(new BigDecimal(1).subtract(new BigDecimal(1).divide(new BigDecimal(Math.pow(1+mpr, numberOfMonths)),CONTEXT_UP)));
			this.initialBalance = startBalance.setScale(0, RoundingMode.UP).intValue()/100.0;
		}
		BigDecimal totalInterest = new BigDecimal(0);
		System.out.println("Result is initial balance = " + NumberFormat.getCurrencyInstance().format(startBalance.divide(new BigDecimal(100))));
		for(int i = 0; i < numberOfMonths; i++) {
			BigDecimal interest = startBalance.multiply(new BigDecimal(mpr));
			totalInterest = totalInterest.add(interest);
			BigDecimal endBalance = startBalance.add(interest).subtract(new BigDecimal(payment*100));
			this.months.add(new Month(startBalance,interest,totalInterest,new BigDecimal(payment*100),endBalance));
			startBalance = endBalance;
		}
	}
	public double getInitialValue() {
		return this.initialBalance;
	}
	public double getAPR() {
		return this.apr;
	}
	public int getNumberOfMonths() {
		return this.months.size();
	}
	public List<Month> getMonths() {
		return this.months;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		int monthColLongest = "Month ".length();
		int startColLongest = "Starting Balance ".length();
		int interColLongest = "Interest ".length();
		int totInColLongest = "Total Interest ".length();
		int pymntColLongest = "Payment ".length();
		int endBaColLongest = "Ending Balance ".length();
		for (int i = 0; i < this.months.size(); i++) {
			monthColLongest = Math.max(monthColLongest, (i + ".").length() + 1);
			startColLongest = Math.max(startColLongest, this.months.get(i).getStartBalanceFormatted().length() + 1);
			interColLongest = Math.max(interColLongest, this.months.get(i).getInterestFormatted().length() + 1);
			totInColLongest = Math.max(totInColLongest, this.months.get(i).getTotalInterestFormatted().length() + 1);
			pymntColLongest = Math.max(pymntColLongest, this.months.get(i).getPaymentFormatted().length() + 1);
			endBaColLongest = Math.max(endBaColLongest, this.months.get(i).getEndBalanceFormatted().length() + 1);
		}
		
		builder.append("loan calculator for " + NumberFormat.getCurrencyInstance().format(initialBalance) + " at " + NumberFormat.getPercentInstance().format(apr/100.0) + " compounded monthly:\n");
		for(int i = -1; i < this.months.size(); i++) {
			if (i == -1) {
				String monthCol = "Month ";
				builder.append(monthCol);
				for (int j = monthCol.length(); j < monthColLongest; j++) {
					builder.append(' ');
				}
				String startCol = "Starting Balance ";
				builder.append(startCol);
				for (int j = startCol.length(); j < startColLongest; j++) {
					builder.append(' ');
				}
				String interCol = "Interest ";
				builder.append(interCol);
				for (int j = interCol.length(); j < interColLongest; j++) {
					builder.append(' ');
				}
				String totInCol = "Total Interest ";
				builder.append(totInCol);
				for (int j = totInCol.length(); j < totInColLongest; j++) {
					builder.append(' ');
				}
				String pymntCol = "Payment ";
				builder.append(pymntCol);
				for (int j = pymntCol.length(); j < pymntColLongest; j++) {
					builder.append(' ');
				}
				String endBaCol = "Ending Balance ";
				builder.append(endBaCol);
				for (int j = endBaCol.length(); j < endBaColLongest; j++) {
					builder.append(' ');
				}
			}
			else {
				builder.append('\n');
				String monthCol = (i+1) + ".";
				builder.append(monthCol);
				for (int j = monthCol.length(); j < monthColLongest; j++) {
					builder.append(' ');
				}
				String startCol = this.months.get(i).getStartBalanceFormatted();
				builder.append(startCol);
				for (int j = startCol.length(); j < startColLongest; j++) {
					builder.append(' ');
				}
				String interCol = this.months.get(i).getInterestFormatted();
				builder.append(interCol);
				for (int j = interCol.length(); j < interColLongest; j++) {
					builder.append(' ');
				}
				String totInCol = this.months.get(i).getTotalInterestFormatted();
				builder.append(totInCol);
				for (int j = totInCol.length(); j < totInColLongest; j++) {
					builder.append(' ');
				}
				String pymntCol = this.months.get(i).getPaymentFormatted();
				builder.append(pymntCol);
				for (int j = pymntCol.length(); j < pymntColLongest; j++) {
					builder.append(' ');
				}
				String endBaCol = this.months.get(i).getEndBalanceFormatted();
				builder.append(endBaCol);
				for (int j = endBaCol.length(); j < endBaColLongest; j++) {
					builder.append(' ');
				}
			}
		}
		return builder.toString();
	}
}
