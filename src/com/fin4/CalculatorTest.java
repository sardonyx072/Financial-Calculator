package com.fin4;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {


	@Test
	public void testGetInitialValue() {
		Calculator cPayment = new Calculator(1000.00,0.00,1);
		Calculator cNumber = new Calculator(1000.00,0.00,1.00);
		Calculator cAPR = new Calculator(1000.00,1,0.00);
		Calculator cInitial = new Calculator(1,0.00,1.00);
		assertEquals(cPayment.getInitialValue(),1000.00,0);
		assertEquals(cNumber.getInitialValue(),1000.00,0);
		assertEquals(cAPR.getInitialValue(),1000.00,0);
		assertEquals(cInitial.getInitialValue(),1000.00,0);
	}

	@Test
	public void testGetAPR() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumberOfMonths() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMonths() {
		fail("Not yet implemented");
	}
	@Test
	public void testCalculatePayments() {
	}

	@Test
	public void testCalculateMonths() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateAPR() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateInitial() {
		fail("Not yet implemented");
	}

}
