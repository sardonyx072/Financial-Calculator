package com.fin4;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MonthTest {

	@Test
	public void testGetStartBalance() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.getStartBalance().doubleValue(),0.01,0);
	}

	@Test
	public void testGetInterest() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.getInterest().doubleValue(),0.02,0);
	}

	@Test
	public void testGetTotalInterest() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.getTotalInterest().doubleValue(),0.03,0);
	}

	@Test
	public void testGetPayment() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.getPayment().doubleValue(),0.04,0);
	}

	@Test
	public void testGetEndBalance() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.getEndBalance().doubleValue(),0.05,0);
	}

	@Test
	public void testToString() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.toString(),"$0.01\t$0.02\t$0.03\t$0.04\t$0.05");
	}

}
