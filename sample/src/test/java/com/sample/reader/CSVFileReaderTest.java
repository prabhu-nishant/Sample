package com.sample.reader;

import java.net.URISyntaxException;

import org.junit.After;
import org.junit.Test;

import com.sample.exception.FileReaderException;

public class CSVFileReaderTest {

	public CSVFileReaderImpl systemUnderTest;
	
	@Test
	public void testReadFile() throws FileReaderException {
		
		systemUnderTest = new CSVFileReaderImpl(getClass().getResource("/sample.csv").getFile());
		systemUnderTest.readFile();
	}
	
	@Test(expected=FileReaderException.class)
	public void testFileNotFoundException() throws FileReaderException {
	
		systemUnderTest = new CSVFileReaderImpl(getClass().getResource("").toString());
		systemUnderTest.readFile();
	}
	
	@After
	public void tearDown(){
		
	
	}
}
