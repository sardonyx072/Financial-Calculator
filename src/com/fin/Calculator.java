package com.fin;

import java.util.LinkedList;

public class Calculator {
	private LinkedList<Month> months;
	
	public Calculator(double initialValue, double apr, double paymentPerMonth) {
		this.months = new LinkedList<Month>();
		do {
			this.months.add(new Month(new Field(FieldType.INITIAL,initialValue),new Field(FieldType.APR,apr),new Field(FieldType.PAYMENT,paymentPerMonth)));
			this.months.getLast().calculateFinalValue();
			initialValue = this.months.getLast().getFinalValue();
			System.out.println("New balance: " + initialValue);
		} while(initialValue > 0);
	}
	
	public int getMonthsToPayOff() {
		return this.months.size();
	}
}
