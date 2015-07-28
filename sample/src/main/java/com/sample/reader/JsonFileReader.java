package com.sample.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sample.exception.FileReaderException;

public class JsonFileReader implements SampleFileReader{

	private String filepath ;
	
	public JsonFileReader (String filepath){
		
		this.filepath = filepath;
	}
	
	public List<Map<String, String>> readFile() throws FileReaderException{
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		try 
		{
		
			List<Map<String,String>> listOfMap = mapper.readValue(new File(filepath.trim()),mapper.getTypeFactory().constructCollectionType(List.class, HashMap.class));
			return listOfMap;
			
		} catch (JsonProcessingException e) {
			
			FileReaderException ex = new FileReaderException("Exception while processing Json",e);
			throw ex;
			
		} catch (FileNotFoundException e) {
			
			FileReaderException ex = new FileReaderException("Please ensure that Json file has been placed at the right location",e);
			throw ex;
			
		} catch (IOException e) {
			
			FileReaderException ex = new FileReaderException("Exception while reading Json file",e);
			throw ex;
		}
	
	}
	
}
