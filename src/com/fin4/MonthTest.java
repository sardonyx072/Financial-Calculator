package com.fin4;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class MonthTest {

	@Test
	public void toStringTest() {
		Month test = new Month(new BigDecimal(1),new BigDecimal(2),new BigDecimal(3),new BigDecimal(4),new BigDecimal(5));
		assertEquals(test.toString(),"$0.01\t$0.02\t$0.03\t$0.04\t$0.05");
	}

}
