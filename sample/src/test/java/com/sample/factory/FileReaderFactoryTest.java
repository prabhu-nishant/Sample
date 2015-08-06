package com.sample.factory;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sample.exception.FileReaderException;
import com.sample.reader.CSVFileReaderImpl;
import com.sample.reader.JsonFileReaderImpl;
import com.sample.reader.SampleFileReader;

public class FileReaderFactoryTest {

	private FileReaderFactory systemUnderTest;
	
	@Before
	public void setUp(){
		systemUnderTest = new FileReaderFactory();
	}
	
	@Test
	public void testForCSVFile() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory(getClass().getResource("/sample.csv").getFile());
		Assert.assertEquals(reader.getClass(), CSVFileReaderImpl.class);
	}

	@Test
	public void testForJsonFile() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory(getClass().getResource("/sample.json").getFile());
		Assert.assertEquals(reader.getClass(), JsonFileReaderImpl.class);
	}

	@Test(expected=FileReaderException.class)
	public void testForInValidFileType() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory("ABCDEF");
	}
	
	@After
	public void tearDown(){
		
	
	}
}
