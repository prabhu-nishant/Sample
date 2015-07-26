package com.sample.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sample.exception.ParseException;



public class ExpressionEvaluatorTest {

	
	private ExpressionEvaluator systemUnderTest;
	
	@Before
	public void setUp(){
		systemUnderTest = new ExpressionEvaluator();
	}
	
	
	@Test
	public void testValidExpression() throws ParseException {
	
		Assert.assertEquals(systemUnderTest.parseExpression("( 15 * 4 ) + 9 / 4.3 - 4"),"58.093023255813954");
		
	}
	
	@Test(expected=ParseException.class)
	public void testInValidExpression() throws ParseException {
	
		Assert.assertEquals(systemUnderTest.parseExpression("( 5 * 4 + 9"),"29.0");
		
	}

	@Test(expected=ParseException.class)
	public void testDivideByZeroExpression() throws ParseException {
	
		Assert.assertEquals(systemUnderTest.parseExpression("( 5 * 4 ) + 9 / 0"),"29.0");
		
	}
	
	@After
	public void tearDown(){
		
	
	}
}
