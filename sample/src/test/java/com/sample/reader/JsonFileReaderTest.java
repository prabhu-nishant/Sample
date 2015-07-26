package com.sample.reader;

import org.junit.After;
import org.junit.Test;

import com.sample.exception.FileReaderException;

public class JsonFileReaderTest {

	public JsonFileReader systemUnderTest;
	
	@Test
	public void testReadFile() throws FileReaderException {
		
		systemUnderTest = new JsonFileReader(getClass().getResource("/sample.json").getFile());
		systemUnderTest.readFile();
	}
	
	@Test(expected=FileReaderException.class)
	public void testJsonProcessingException() throws FileReaderException {
		
		systemUnderTest = new JsonFileReader(getClass().getResource("/sampleError.json").getFile());
		systemUnderTest.readFile();
	}
	
	
	@Test(expected=FileReaderException.class)
	public void testFileNotFoundException() throws FileReaderException {
	
		systemUnderTest = new JsonFileReader(getClass().getResource("").toString());
		systemUnderTest.readFile();
	}
	
	@After
	public void tearDown(){
		
	
	}

}
