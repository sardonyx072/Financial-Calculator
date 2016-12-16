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
			c = new Calculator(-1.00,0.0,1);
			c = new Calculator(0.00,0.0,1);
			c = new Calculator(1.00,-1.0,1);
			c = new Calculator(1.00,0.0,-1);
			c = new Calculator(1.00,0.0,0);
			fail("failed to catch invalid args!");
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}

		c = new Calculator(1.00,0.00,1);
		assertEquals(c.getMonths().size(),1,0);
		assertEquals(c.getMonths().get(0).getPayment().doubleValue(),1.00,0);
		c = new Calculator(1.00,0.00,2);
		assertEquals(c.getMonths().size(),2,0);
		assertEquals(c.getMonths().get(0).getPayment().doubleValue(),0.50,0);
		assertEquals(c.getMonths().get(1).getPayment().doubleValue(),0.50,0);
		c = new Calculator(1.01,0.00,2);
		assertEquals(c.getMonths().size(),2,0);
		assertEquals(c.getMonths().get(0).getPayment().doubleValue(),0.50,0);
		assertEquals(c.getMonths().get(1).getPayment().doubleValue(),0.51,0);
	}

	@Test
	public void testCalculateMonths() {
		Calculator c;
		try {
			c = new Calculator(-1.00,0.0,1.0);
			c = new Calculator(0.00,0.0,1.0);
			c = new Calculator(1.00,-1.0,1.0);
			c = new Calculator(1.00,0.0,-1.0);
			c = new Calculator(1.00,0.0,0.0);
			fail("failed to catch invalid args!");
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}
		
		try {
			c = new Calculator(100.00,100.00,1.00);
			fail("failed to catch invalid args!");
		} catch (RuntimeException e) {
			assertTrue(true);
		}

		c = new Calculator(1.00,0.00,1.00);
		assertEquals(c.getMonths().size(),1,0);
		c = new Calculator(2.00,0.00,1.00);
		assertEquals(c.getMonths().size(),2,0);
		c = new Calculator(3.00,0.00,1.00);
		assertEquals(c.getMonths().size(),3,0);
	}

	@Test
	public void testCalculateAPR() {
		Calculator c;
		try {
			c = new Calculator(-1.00,1,1.0);
			c = new Calculator(0.00,1,1.0);
			c = new Calculator(1.00,-1,1.0);
			c = new Calculator(1.00,0,1.0);
			c = new Calculator(1.00,1,-1.0);
			c = new Calculator(1.00,1,0.0);
			fail("failed to catch invalid args!");
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}

		c = new Calculator(1.00,1,1.01);
		assertEquals(c.getAPR(),12.00,0);
		c = new Calculator(1.00,1,1.02);
		assertEquals(c.getAPR(),24.00,0);
	}

	@Test
	public void testCalculateInitial() {
		Calculator c;
		try {
			c = new Calculator(-1,1.00,0.0);
			c = new Calculator(0,1.00,0.0);
			c = new Calculator(1,-1.00,0.0);
			c = new Calculator(1,0.00,0.0);
			c = new Calculator(1,1.00,-1.0);
			fail("failed to catch invalid args!");
		} catch(IllegalArgumentException e) {
			assertTrue(true);
		}

		c = new Calculator(1,1.00,0.00);
		assertEquals(c.getInitialValue(),1.00,0);
		c = new Calculator(2,1.00,0.00);
		assertEquals(c.getInitialValue(),2.00,0);
		c = new Calculator(1,2.00,0.00);
		assertEquals(c.getInitialValue(),2.00,0);
	}

}
