package com.fin;

public class Month {
	private Field initialValue;
	private Field mpr;
	private Field payment;
	private Field finalValue;
	
	//payment=(initial*apr)/(1-(1/(1+r)^2))
	
	// (initial-payment)*(1+apr)=final

	// initial=(final+payment)/(1+mpr)
	// mpr=((final+payment)/initial)-1
	// payment=(initial*(1+mpr))-final
	// final=(initial*(1+mpr))-payment
	
	public Month(Field...fields) {
		for (Field field : fields) {
			switch(field.getType()) {
			case INITIAL:
				this.initialValue = field;
				break;
			case MPR:
				this.mpr = field;
				break;
			case PAYMENT:
				this.payment = field;
				break;
			case FINAL:
				this.finalValue = field;
				break;
			}
		}
	}
	
	public int getNumNullFields() {
		int i = 0;
		if(this.initialValue == null)
			i++;
		if(this.mpr == null)
			i++;
		if(this.payment == null)
			i++;
		if(this.finalValue == null)
			i++;
		return i;
	}
	
	public double getInitialValue() {
		return this.initialValue.getValue();
	}
	
	public void setInitialValue(double initialValue) {
		this.initialValue = new Field(FieldType.INITIAL,initialValue);
	}


	// initial=(final+payment)/(1+mpr)
	// mpr=((final+payment)/initial)-1
	// payment=(initial*(1+mpr))-final
	// final=(initial*(1+mpr))-payment
	public boolean calculateInitialValue() {
		if(!(this.getNumNullFields() > 1)) {
			this.setInitialValue((this.getFinalValue()+this.getPayment())/(1+this.getMPR()));
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getMPR() {
		return this.mpr.getValue();
	}
	
	public void setMPR(double mpr) {
		this.mpr = new Field(FieldType.MPR,mpr);
	}


	// initial=(final+payment)/(1+mpr)
	// mpr=((final+payment)/initial)-1
	// payment=(initial*(1+mpr))-final
	// final=(initial*(1+mpr))-payment
	public boolean calculateMPR() {
		if(!(this.getNumNullFields() > 1)) {
			this.setMPR(((this.getFinalValue()+this.getPayment())/this.getInitialValue())-1);
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getPayment() {
		return this.payment.getValue();
	}
	
	public void setPayment(double payment) {
		this.payment = new Field(FieldType.PAYMENT,payment);
	}


	// initial=(final+payment)/(1+mpr)
	// mpr=((final+payment)/initial)-1
	// payment=(initial*(1+mpr))-final
	// final=(initial*(1+mpr))-payment
	public boolean calculatePayment() {
		if(!(this.getNumNullFields() > 1)) {
			this.setPayment((this.getInitialValue()*(1+this.getMPR()))-this.getFinalValue());
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getFinalValue() {
		return this.finalValue.getValue();
	}
	
	public void setFinalValue(double finalValue) {
		this.finalValue = new Field(FieldType.FINAL,finalValue);
	}


	// initial=(final+payment)/(1+mpr)
	// mpr=((final+payment)/initial)-1
	// payment=(initial*(1+mpr))-final
	// final=(initial*(1+mpr))-payment
	public boolean calculateFinalValue() {
		if(!(this.getNumNullFields() > 1)) {
			this.setFinalValue((this.getInitialValue()*(1+this.getMPR()))-this.getPayment());
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "[" + this.initialValue + " + " + this.mpr + " - " + this.payment + " = " + this.finalValue + "]";
	}
}
