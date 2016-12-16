package com.fin4;

public class Driver {
	public static void main(String[] args) {
		Calculator calc1 = new Calculator(100000.00,5.00,5); //calculate payment
		System.out.println(calc1);
		System.out.println();
		Calculator calc2 = new Calculator(100000.00,5.00,20250.69); //calculate number of months
		System.out.println(calc2);
		System.out.println();
		Calculator calc3 = new Calculator(100000.00,5,20250.69); //calculate apr
		System.out.println(calc3);
		System.out.println();
		Calculator calc4 = new Calculator(5,20250.69,5.00); //calculate starting principle
		System.out.println(calc4);
		System.out.println();
		Calculator calc5 = new Calculator(100000.00,0.00,5); //calculate payment
		System.out.println(calc5);
		System.out.println();
		Calculator calc6 = new Calculator(100000.00,0.00,20250.69); //calculate number of months
		System.out.println(calc6);
		System.out.println();
		Calculator calc7 = new Calculator(100000.00,5,20250.69); //calculate apr
		System.out.println(calc7);
		System.out.println();
		Calculator calc8 = new Calculator(5,20250.69,0.00); //calculate starting principle
		System.out.println(calc8);
		System.out.println();
	}
}
