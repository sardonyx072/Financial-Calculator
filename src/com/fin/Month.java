package com.fin;

public class Month {
	private Field initialValue;
	private Field apr;
	private Field payment;
	private Field finalValue;
	
	// (initial-payment)*(1+apr)=final
	
	//payment=(initial*apr)/(1-(1/(1+r)^2))
	
	public Month(Field...fields) {
		for (Field field : fields) {
			switch(field.getType()) {
			case INITIAL:
				this.initialValue = field;
				break;
			case APR:
				this.apr = field;
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
		if(this.apr == null)
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
		this.initialValue = new Field(FieldType.FINAL,initialValue);
	}
	
	public boolean calculateInitialValue() {
		if(!(this.getNumNullFields() > 1)) {
			this.setInitialValue((this.getFinalValue()/(1+this.getAPR()))+this.getPayment());
			return true;
		}
		else {
			return false;
		}
	}
	
	public double getAPR() {
		return this.apr.getValue();
	}
	
	public void setAPR(double apr) {
		this.apr = new Field(FieldType.FINAL,apr);
	}
	
	public boolean calculateAPR() {
		if(!(this.getNumNullFields() > 1)) {
			this.setAPR((this.getFinalValue()/(this.getInitialValue()-this.getPayment()))-1);
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
		this.payment = new Field(FieldType.FINAL,payment);
	}
	
	public boolean calculatePayment() {
		if(!(this.getNumNullFields() > 1)) {
			this.setPayment(this.getInitialValue()-(this.getFinalValue()/(1+this.getAPR())));
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
	
	public boolean calculateFinalValue() {
		if(!(this.getNumNullFields() > 1)) {
			this.setFinalValue((this.getInitialValue()-this.getPayment())*(1+this.getAPR()));
			return true;
		}
		else {
			return false;
		}
	}
}
