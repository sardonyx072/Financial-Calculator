package com.fin;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in;
		boolean validChoice = false;
		do {
			validChoice = false;
			System.out.println("What would you like to calculate?");
			System.out.println("0. Quit");
			System.out.println("1. Payments given initial loan value, APR, and number of payments");
			System.out.println("2. Number of payments given initial loan value, APR, and monthly payment value");
			System.out.println("3. APR given initial loan value, number of payments, and monthly payment value");
			System.out.println("4. Initial loan value given number of payments, monthly payment value, and APR");
			System.out.print("Choice >");
			in = scan.nextLine();
			try {
				int choice = Integer.parseInt(in);
				if (choice < 1 && choice > 4) {
					System.out.println("Invalid command.");
					System.out.println();
				}
				else {
					double init = 0;
					double apr = 0;
					int num = 0;
					double payment = 0;
					boolean valueOK = false;
					switch(choice) {
					case 0:
						continue;
					case 1:
						do {
							valueOK = false;
							try {
								System.out.print("Please enter initial loan value: ");
								in = scan.nextLine();
								init = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter APR: ");
								in = scan.nextLine();
								apr = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter number of payments: ");
								in = scan.nextLine();
								num = Integer.parseInt(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						System.out.println();
						try {
							System.out.println(new Calculator(init,apr,num));
						} catch (Exception e) {
							System.out.println("Bad values entered! Check values and try again.");
						}
						System.out.println();
						break;
					case 2:
						do {
							valueOK = false;
							try {
								System.out.print("Please enter initial loan value: ");
								in = scan.nextLine();
								init = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter APR: ");
								in = scan.nextLine();
								apr = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter monthly payment amount: ");
								in = scan.nextLine();
								payment = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						System.out.println();
						try {
							System.out.println(new Calculator(init,apr,payment));
						} catch (Exception e) {
							System.out.println("Bad values entered! Check values and try again.");
						}
						System.out.println();
						break;
					case 3:
						do {
							valueOK = false;
							try {
								System.out.print("Please enter initial loan value: ");
								in = scan.nextLine();
								init = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter number of payments: ");
								in = scan.nextLine();
								num = Integer.parseInt(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter monthly payment amount: ");
								in = scan.nextLine();
								payment = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						System.out.println();
						try {
							System.out.println(new Calculator(init,num,payment));
						} catch (Exception e) {
							System.out.println("Bad values entered! Check values and try again.");
						}
						System.out.println();
						break;
					case 4:
						do {
							valueOK = false;
							try {
								System.out.print("Please enter APR: ");
								in = scan.nextLine();
								apr = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter number of payments: ");
								in = scan.nextLine();
								num = Integer.parseInt(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						do {
							valueOK = false;
							try {
								System.out.print("Please enter monthly payment amount: ");
								in = scan.nextLine();
								payment = Double.parseDouble(in);
								valueOK = true;
							} catch (NumberFormatException e) {
								System.out.println("Invalid number entered.");
							}
							
						} while(!valueOK);
						System.out.println();
						try {
							System.out.println(new Calculator(num,payment,apr));
						} catch (Exception e) {
							System.out.println("Bad values entered! Check values and try again.");
						}
						System.out.println();
						break;
					default:
						System.out.println("Invalid command.");
						System.out.println();
					}
				}
			} catch (Exception e) {
				System.out.println("Invalid command.");
				System.out.println();
			}
		} while(!validChoice && !in.equals("0"));
		scan.close();
	}
}
