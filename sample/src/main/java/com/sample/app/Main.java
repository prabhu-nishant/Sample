package com.sample.app;

import org.apache.log4j.Logger;

import com.sample.exception.FileReaderException;
import com.sample.factory.FileReaderFactory;
import com.sample.reader.SampleFileReader;

public class Main {

	private final static Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String args[]){
		
		FileReaderFactory readerFactory = new FileReaderFactory();
		SampleFileReader reader =readerFactory.getFileReaderFactory("C:\\sample.json");
		try {
			
			reader.readFile();
			LOGGER.debug("Hello");
			
		} catch (FileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
