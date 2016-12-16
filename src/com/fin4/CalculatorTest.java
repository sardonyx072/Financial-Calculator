package com.fin4;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {


	@Test
	public void testGetInitialValue() {
		Calculator cPayment = new Calculator(1000.00,0.00,1);
		Calculator cNumber = new Calculator(1000.00,0.00,1.00);
		Calculator cAPR = new Calculator(1000.00,1,1.00);
		Calculator cInitial = new Calculator(1000,1.00,0.00);
		assertEquals(cPayment.getInitialValue(),1000.00,0);
		assertEquals(cNumber.getInitialValue(),1000.00,0);
		assertEquals(cAPR.getInitialValue(),1000.00,0);
		assertEquals(cInitial.getInitialValue(),1000.00,0);
	}

	@Test
	public void testGetAPR() {
		Calculator cPayment = new Calculator(1000.00,10.00,1);
		Calculator cNumber = new Calculator(1000.00,10.00,1000.00);
		Calculator cAPR = new Calculator(1000.00,1,1010.00);
		Calculator cInitial = new Calculator(1000,1.00,10.00);
		assertEquals(cPayment.getAPR(),10.00,0);
		assertEquals(cNumber.getAPR(),10.00,0);
		assertEquals(cAPR.getAPR(),12.00,0);
		assertEquals(cInitial.getAPR(),10.00,0);
	}

	@Test
	public void testGetNumberOfMonths() {
		Calculator cPayment = new Calculator(1.00,0.00,1);
		Calculator cNumber = new Calculator(1.00,0.00,1.00);
		Calculator cAPR = new Calculator(1.00,1,1.00);
		Calculator cInitial = new Calculator(1,1.00,0.00);
		assertEquals(cPayment.getNumberOfMonths(),1,0);
		assertEquals(cNumber.getNumberOfMonths(),1,0);
		assertEquals(cAPR.getNumberOfMonths(),1,0);
		assertEquals(cInitial.getNumberOfMonths(),1,0);
	}
	
	@Test
	public void testCalculatePayments() {
		Calculator c;
		try {
			c = new Calculator(-1,0.0,1.0);
			c = new Calculator(0,0.0,1.0);
			c = new Calculator(1,-1.0,1.0);
			c = new Calculator(1,0.0,-1.0);
			c = new Calculator(1,0.0,0.0);
			fail("failed to catch invalid args!");
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}
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
