package com.sample.app;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sample.exception.FileReaderException;
import com.sample.exception.ParseException;

public class MainTest {

	private Main systemUnderTest;
	
	@Before
	public void setUp(){
		
		systemUnderTest = new Main();
	}
		
	
	@Test
	public void testForPopulateMap() throws FileReaderException {
	
		Map<String, String> map = systemUnderTest.populateMap(getClass().getResource("/sample.json").getFile());
		Assert.assertTrue(map.equals(getMap()));
	}
	
	@Test
	public void testForEvaluateExpression() throws ParseException {
	
		Map<String, String> map = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",getMap());
		Assert.assertEquals(map.get("area"),"11.55");
	}
	
	@Test(expected=ParseException.class)
	public void testForEvaluateExpression_EmptyFile() throws ParseException {
	
		Map<String, String> map = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",new HashMap<String,String>());
		Assert.assertEquals(map.get("area"),"11.55");
	}
		
	@Test(expected=ParseException.class)
	public void testForEvaluateExpression_NullMap() throws ParseException {
	
		Map<String, String> map = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",null);
		Assert.assertEquals(map.get("area"),"11.55");
	}
	
	@Test
	public void testForExportData() throws Exception {
	
		systemUnderTest.exportData("./target/test-classes",getMap());
		File f = new File("./target/test-classes/Output.json");
		Assert.assertTrue(f.exists());
	}
	
	@Test(expected=Exception.class)
	public void testForExportData_EmptyFile() throws Exception {
	
		systemUnderTest.exportData("./target/test-classes",new HashMap<String,String>());
		File f = new File("./target/test-classes/Output.json");
		Assert.assertTrue(f.exists());
	}
	
	@Test(expected=Exception.class)
	public void testForExportData_NullMap() throws Exception {
	
		systemUnderTest.exportData("./target/test-classes",null);
		File f = new File("./target/test-classes/Output.json");
		Assert.assertTrue(f.exists());
	}
	
	
	@After
	public void tearDown(){
		
	
	}
	
	private Map<String, String> getMap(){
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("value", "10.2");
		map.put("volume", "4");
		return map;
		
		
	}
}
