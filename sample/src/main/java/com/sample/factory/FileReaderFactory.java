package com.sample.factory;

import com.sample.reader.CSVFileReader;
import com.sample.reader.JsonFileReader;
import com.sample.reader.SampleFileReader;
import com.sample.util.Constants;

public class FileReaderFactory {
	
	public SampleFileReader getFileReaderFactory(String filePath) throws NullPointerException {
		
		SampleFileReader reader = null;
		
		if(filePath.endsWith(Constants.CSV_FILE_EXTENSION_VALUE)){
			
			reader = new CSVFileReader(filePath);
			
		}
		else if (filePath.endsWith(Constants.JSON_FILE_EXTENSION_VALUE)){
			
			reader = new JsonFileReader(filePath);
		}
		
		return reader;
	}
	
}
