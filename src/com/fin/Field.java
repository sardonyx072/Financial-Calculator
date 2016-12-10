package com.fin;

import java.text.NumberFormat;

public class Field {
	private final FieldType type;
	double value;
	
	public Field(FieldType type, double value) {
		this.type = type;
		this.setValue(value);
	}
	
	public FieldType getType() {
		return this.type;
	}
	
	public double getValue() {
		return this.value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public String toString() {
		switch(this.getType()) {
		case INITIAL:
			return NumberFormat.getCurrencyInstance().format(this.getValue());
		case MPR:
			return NumberFormat.getPercentInstance().format(this.getValue());
		case PAYMENT:
			return NumberFormat.getCurrencyInstance().format(this.getValue());
		case FINAL:
			return NumberFormat.getCurrencyInstance().format(this.getValue());
		default:
			return "error";
		}
	}
}
