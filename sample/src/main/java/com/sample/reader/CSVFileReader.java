package com.sample.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.sample.exception.FileReaderException;
import com.sample.util.Constants;

public class CSVFileReader implements SampleFileReader{

	private String filepath ;
	
	public CSVFileReader(String filepath){
		
		this.filepath = filepath;
	}
	
	public Map<String,String> readFile() throws FileReaderException {

		BufferedReader reader = null;
		String line = null;
		
		Map<String,String> map = new HashMap<String,String>();
		
		try {
			
			reader = new BufferedReader(new FileReader(filepath.trim()));
			while((line=reader.readLine())!=null){
				
				String[] fields = line.split(Constants.COMMA_IDENTIFIER);
				
				for(String field:fields){
					
					String[] temp = field.split(Constants.FIELD_SPLIT_IDENTIFIER);
					map.put(temp[0], temp[1]);
				}
			}
			
		} catch (FileNotFoundException e) {
			
			FileReaderException ex = new FileReaderException("Please ensure that CSV file has been placed at the right location",e);
			throw ex;
			
		} catch (IOException e) {
			
			FileReaderException ex = new FileReaderException("Exception while reading CSV file",e);
			throw ex;
		}
		
		return map;
	}

}
