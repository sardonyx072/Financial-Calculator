package com.fin;

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
}
