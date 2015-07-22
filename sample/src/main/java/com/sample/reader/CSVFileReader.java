package com.sample.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sample.exception.FileReaderException;

public class CSVFileReader implements SampleFileReader{

	private String filepath ;
	
	public CSVFileReader(String filepath){
		
		this.filepath = filepath;
	}
	
	public Map readFile() throws FileReaderException {

		BufferedReader reader = null;
		String line = null;
		String cvsSplitBy = ",";
		String fieldSplitBy = ":";
		Map<String,String> map = new ConcurrentHashMap<String,String>();
		
		try {
			
			reader = new BufferedReader(new FileReader(filepath));
			while((line=reader.readLine())!=null){
				
				String[] fields = line.split(cvsSplitBy);
				
				for(String field:fields){
					
					String[] temp = field.split(fieldSplitBy);
					map.put(temp[0], temp[1]);
				}
			}
			
		} catch (FileNotFoundException e) {
			
			FileReaderException ex = new FileReaderException(e.getMessage());
			throw ex;
			
		} catch (IOException e) {
			
			FileReaderException ex = new FileReaderException(e.getMessage());
			throw ex;
		}
		
		return map;
	}

}
