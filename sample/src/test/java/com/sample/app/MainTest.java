package com.sample.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
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
	
		List<Map<String, String>> listOfMap = systemUnderTest.populateMap(getClass().getResource("/sample.json").getFile());
		Assert.assertTrue(listOfMap.equals(getMap()));
	}
	
	@Test
	public void testForEvaluateExpression() throws ParseException, JsonGenerationException, JsonMappingException, IOException {
	
		List<Map<String, String>> listOfMap = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",getMap());
		Assert.assertTrue(listOfMap.equals(getResultMap()));
	}
	
	@Test(expected=ParseException.class)
	public void testForEvaluateExpression_EmptyFile() throws ParseException {
	
		List<Map<String, String>> map = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",new ArrayList<Map<String, String>>());
	}
		
	@Test(expected=ParseException.class)
	public void testForEvaluateExpression_NullMap() throws ParseException {
	
		List<Map<String, String>> map = systemUnderTest.evaluateExpression("area = ( value / volume ) + 9 ",new ArrayList<Map<String, String>>());
	}
	
	@Test
	public void testForExportData() throws Exception {
	
		systemUnderTest.exportData("./target/test-classes",getMap());
		File f = new File("./target/test-classes/Output.json");
		Assert.assertTrue(f.exists());
	}
	
	@Test(expected=Exception.class)
	public void testForExportData_EmptyFile() throws Exception {
	
		systemUnderTest.exportData("./target/test-classes",new ArrayList<Map<String, String>>());
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
	
	private List<Map<String,String>> getMap(){
		
		List<Map<String,String>> listOfMap = new ArrayList<Map<String, String>>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("area", "10.2");
		map1.put("value", "10.2");
		map1.put("volume", "4");
		listOfMap.add(map1);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("value", "2.5");
		map2.put("volume", "5");
		listOfMap.add(map2);
		
		return listOfMap;
		
	}
	
	private List<Map<String,String>> getResultMap(){
		
		List<Map<String,String>> listOfMap = new ArrayList<Map<String, String>>();
		
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("area", "11.55");
		map1.put("value", "10.2");
		map1.put("volume", "4");
		listOfMap.add(map1);
		
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("area", "9.5");
		map2.put("value", "2.5");
		map2.put("volume", "5");
		listOfMap.add(map2);
		
		return listOfMap;
		
	}
}
