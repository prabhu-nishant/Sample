package com.sample.factory;

import com.sample.exception.FileReaderException;
import com.sample.reader.CSVFileReaderImpl;
import com.sample.reader.JsonFileReaderImpl;
import com.sample.reader.SampleFileReader;
import com.sample.util.Constants;

public class FileReaderFactory {
	
	private SampleFileReader reader = null;
	
	public SampleFileReader getFileReaderFactory(String filePath) throws FileReaderException {
		
		
		if(filePath!=null && filePath.endsWith(Constants.CSV_FILE_EXTENSION_VALUE)){
			
			reader = new CSVFileReaderImpl(filePath);
			
		}
		else if (filePath!=null && filePath.endsWith(Constants.JSON_FILE_EXTENSION_VALUE)){
			
			reader = new JsonFileReaderImpl(filePath);
		}
		else{
			
			throw new FileReaderException("Please enter a valid file path/name");
			
		}
		
		return reader;
	}
	
}
