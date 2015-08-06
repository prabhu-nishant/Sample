package com.sample.reader;

import org.junit.After;
import org.junit.Test;

import com.sample.exception.FileReaderException;

public class JsonFileReaderTest {

	public JsonFileReaderImpl systemUnderTest;
	
	@Test
	public void testReadFile() throws FileReaderException {
		
		systemUnderTest = new JsonFileReaderImpl(getClass().getResource("/sample.json").getFile());
		systemUnderTest.readFile();
	}
	
	@Test(expected=FileReaderException.class)
	public void testJsonProcessingException() throws FileReaderException {
		
		systemUnderTest = new JsonFileReaderImpl(getClass().getResource("/sampleError.json").getFile());
		systemUnderTest.readFile();
	}
	
	
	@Test(expected=FileReaderException.class)
	public void testFileNotFoundException() throws FileReaderException {
	
		systemUnderTest = new JsonFileReaderImpl(getClass().getResource("").toString());
		systemUnderTest.readFile();
	}
	
	@After
	public void tearDown(){
		
	
	}

}
