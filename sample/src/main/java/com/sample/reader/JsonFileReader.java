package com.sample.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
	
	public HashMap<String,String> readFile() throws FileReaderException{
		JsonNode rootNode = null;
		HashMap<String,String> map = new HashMap<String,String>();
		
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		try 
		{
		
			rootNode = mapper.readTree(new File(filepath.trim()));
			map = getJsonFields(rootNode);
		
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
		
		return map;
	}
	
	public HashMap<String,String> getJsonFields(JsonNode rootNode){
		
		HashMap<String,String> map = new HashMap<String,String>(); 
		
		Iterator<Map.Entry<String,JsonNode>> fieldIterator = rootNode.getFields();
		while(fieldIterator.hasNext()){
			
			Map.Entry<String,JsonNode> field = fieldIterator.next();
			map.putIfAbsent(field.getKey(), field.getValue().getTextValue());
		}
		
		return map;
	}
	

}
