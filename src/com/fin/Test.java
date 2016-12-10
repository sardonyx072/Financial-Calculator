package com.fin;

public class Test {
	public static void main(String[] args) {
		Calculator calc = new Calculator(20000,0.01,500);
		System.out.println(calc.getMonthsToPayOff());
	}
}
