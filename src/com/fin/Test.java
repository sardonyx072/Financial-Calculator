package com.fin;

public class Test {
	public static void main(String[] args) {
		Calculator calc = new Calculator(1000,0.1,100);
		System.out.println(calc.getMonthsToPayOff());
	}
}
