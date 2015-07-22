package com.sample.factory;

import com.sample.reader.CSVFileReader;
import com.sample.reader.JsonFileReader;
import com.sample.reader.SampleFileReader;

public class FileReaderFactory {

	public static final String CSV_FILE_EXTENSION_VALUE ="csv";
	public static final String JSON_FILE_EXTENSION_VALUE ="json";
	
	public SampleFileReader getFileReaderFactory(String filePath) throws NullPointerException {
		
		SampleFileReader reader = null;
		
		if(filePath.endsWith(CSV_FILE_EXTENSION_VALUE)){
			
			reader = new CSVFileReader(filePath);
			
		}
		else if (filePath.endsWith(JSON_FILE_EXTENSION_VALUE)){
			
			reader = new JsonFileReader(filePath);
		}
		
		return reader;
	}
	
}
