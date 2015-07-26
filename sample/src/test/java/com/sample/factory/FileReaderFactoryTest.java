package com.sample.factory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sample.exception.FileReaderException;
import com.sample.reader.CSVFileReader;
import com.sample.reader.JsonFileReader;
import com.sample.reader.SampleFileReader;

public class FileReaderFactoryTest {

	private FileReaderFactory systemUnderTest;
	
	@Before
	public void initialize(){
		systemUnderTest = new FileReaderFactory();
	}
	
	@Test
	public void testForCSVFile() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory(".csv");
		Assert.assertEquals(reader.getClass(), CSVFileReader.class);
	}

	@Test
	public void testForJsonFile() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory(".json");
		Assert.assertEquals(reader.getClass(), JsonFileReader.class);
	}

	@Test(expected=FileReaderException.class)
	public void testForInValidFileType() throws FileReaderException {
		SampleFileReader reader = systemUnderTest.getFileReaderFactory("ABCDEF");
	}
}
