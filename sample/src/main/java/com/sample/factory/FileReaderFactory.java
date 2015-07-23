package com.sample.factory;

import com.sample.exception.FileReaderException;
import com.sample.reader.CSVFileReader;
import com.sample.reader.JsonFileReader;
import com.sample.reader.SampleFileReader;
import com.sample.util.Constants;

public class FileReaderFactory {
	
	public SampleFileReader getFileReaderFactory(String filePath) throws FileReaderException {
		
		SampleFileReader reader = null;
		
		if(filePath.endsWith(Constants.CSV_FILE_EXTENSION_VALUE)){
			
			reader = new CSVFileReader(filePath);
			
		}
		else if (filePath.endsWith(Constants.JSON_FILE_EXTENSION_VALUE)){
			
			reader = new JsonFileReader(filePath);
		}
		else{
			
			throw new FileReaderException("Please enter a valid file path/name");
			
		}
		
		return reader;
	}
	
}
