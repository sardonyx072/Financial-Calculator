package com.fin2;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class BigCalculator {
	private BigDecimal trueInitialPrinciple;
	private BigDecimal trueAPR;
	private BigDecimal trueNumberOfPayments;
	private BigDecimal truePaymentAmount;
	
	private List<Month> months;
	
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
	public BigCalculator (double initialPrinciple, double apr, int numberOfPayments) {
		this.trueInitialPrinciple = new BigDecimal(initialPrinciple);
		this.trueAPR = new BigDecimal(apr);
		this.trueNumberOfPayments = new BigDecimal(numberOfPayments);
		this.months = new ArrayList<Month>();
		if (apr == 0) {
			this.truePaymentAmount = this.trueInitialPrinciple.divide(this.trueNumberOfPayments);
		}
		else {
			//calculate payment value
			/* (2) Payment amount on a loan
			 *          rA
			 * P = ------------
			 *     1-((1+r)^-N)
			 */
			BigDecimal mpr = new BigDecimal(apr/12);
			BigDecimal mprMult = mpr.add(new BigDecimal(1+(apr/12)));
			this.truePaymentAmount = new BigDecimal(initialPrinciple*apr/12).divide(new BigDecimal(1).subtract(mprMult.pow(-1*numberOfPayments))); //TODO: cannot do -N exponent
			BigDecimal trueTotalPayment = this.truePaymentAmount.multiply(this.trueNumberOfPayments);
			int actualPaymentBase = this.truePaymentAmount.multiply(new BigDecimal(100)).intValue();
			int missedMoney = trueTotalPayment.multiply(new BigDecimal(100)).subtract(new BigDecimal(actualPaymentBase*(numberOfPayments-1))).round(new MathContext(2,RoundingMode.UP)).intValue();
			BigDecimal runningBalance = this.trueInitialPrinciple;
			BigDecimal runningInterest = new BigDecimal(0);
			for(int i = 0; i < numberOfPayments; i++) {
				BigDecimal interest = runningBalance.multiply(mprMult);
				runningInterest=runningInterest.add(interest);
				double paymentThisPeriod = (actualPaymentBase + (i == numberOfPayments-1 ? missedMoney : 0))/100.0;
				BigDecimal endingBalance = runningBalance.add(interest).subtract(new BigDecimal(paymentThisPeriod));
				this.months.add(new Month(runningBalance.doubleValue(),interest.doubleValue(),runningInterest.doubleValue(),paymentThisPeriod,0,endingBalance.doubleValue()));
				runningBalance = endingBalance;
			}
		}
	}
	public BigCalculator (double initialPrinciple, double apr, double paymentAmount) {
		this.trueInitialPrinciple = new BigDecimal(initialPrinciple);
		this.trueAPR = new BigDecimal(apr);
		this.truePaymentAmount = new BigDecimal(paymentAmount);
		double mpr = apr/12;
		//calculate number of payments
		this.months = new ArrayList<Month>();
	}
	public BigCalculator (double initialPrinciple, int numberOfPayments, double paymentAmount) {
		this.trueInitialPrinciple = new BigDecimal(initialPrinciple);
		this.trueNumberOfPayments = new BigDecimal(numberOfPayments);
		this.truePaymentAmount = new BigDecimal(paymentAmount);
		//calculate apr
		this.months = new ArrayList<Month>();
	}
	public BigCalculator (int numberOfPayments, double paymentAmount, double apr) {
		this.trueNumberOfPayments = new BigDecimal(numberOfPayments);
		this.truePaymentAmount = new BigDecimal(paymentAmount);
		this.trueAPR = new BigDecimal(apr);
		double mpr = apr/12;
		//calculate initial principle
		this.months = new ArrayList<Month>();
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.months.size(); i++) {
			builder.append(this.months.get(i).toString());
		}
		return builder.toString();
	}
}
