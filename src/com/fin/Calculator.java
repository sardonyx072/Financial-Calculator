package com.fin;

import java.util.LinkedList;

public class Calculator {
	private LinkedList<Month> months;
	
	public Calculator(double initialValue, double apr, double paymentPerMonth) {
		this.months = new LinkedList<Month>();
		do {
			this.months.add(new Month(new Field(FieldType.INITIAL,initialValue),new Field(FieldType.MPR,apr),new Field(FieldType.PAYMENT,paymentPerMonth)));
			this.months.getLast().calculateFinalValue();
			initialValue = this.months.getLast().getFinalValue();
			System.out.println(this.months.getLast());
		} while(initialValue > 0);
	}
	
	public int getMonthsToPayOff() {
		return this.months.size();
	}
	
	public String toString() {
		String s = "";
		for (Month m : this.months) {
			s += m;
		}
		return s;
	}
}
