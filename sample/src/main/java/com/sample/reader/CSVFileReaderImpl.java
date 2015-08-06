package com.sample.reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sample.exception.FileReaderException;
import com.sample.util.Constants;

public class CSVFileReaderImpl implements SampleFileReader{

	private String filepath ;
	
	public CSVFileReaderImpl(String filepath){
		
		this.filepath = filepath;
	}
	
	public List<Map<String, String>> readFile() throws FileReaderException {

		BufferedReader reader = null;
		String line = null;
		Map<String,String> map = null;
		List<Map<String, String>> listOfMap = null;
		
		try {
			
			map = new HashMap<String,String>();
			listOfMap = new ArrayList<Map<String, String>>();
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"UTF-8"));
			while((line=reader.readLine())!=null){
				
				String[] fields = line.split(Constants.COMMA_IDENTIFIER);
				
				for(int i=0;i<fields.length;i=i+2){
					
					map.put(fields[i], fields[i+1]);
				}
				
				listOfMap.add(map);
			}
			
		} catch (FileNotFoundException e) {
			
			FileReaderException ex = new FileReaderException("Please ensure that CSV file is placed at the right location",e);
			throw ex;
			
		} catch (IOException e) {
			
			FileReaderException ex = new FileReaderException("Exception while reading CSV file",e);
			throw ex;
			
		} finally{
			try {
				
				if(reader!=null){
					reader.close();
					reader = null;
				}
			
			} catch (IOException e) {
				
				FileReaderException ex = new FileReaderException("Exception while closing CSV file",e);
				throw ex;
			}
		}
		
		return listOfMap;
	}

}
