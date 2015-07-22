package com.sample.reader;

import java.io.File;
import java.io.IOException;
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
	
	public ConcurrentMap readFile() throws FileReaderException{
		JsonNode rootNode = null;
		ConcurrentMap<String,String> map = new ConcurrentHashMap<String,String>();
		
		JsonFactory factory = new JsonFactory();
		ObjectMapper mapper = new ObjectMapper(factory);
		try 
		{
		
			rootNode = mapper.readTree(new File(filepath));
			map = getJsonFields(rootNode);
		
		} catch (JsonProcessingException e) {
			
			FileReaderException ex = new FileReaderException(e.getMessage());
			throw ex;
			
		} catch (IOException e) {
			
			FileReaderException ex = new FileReaderException(e.getMessage());
			throw ex;
		}
		
		return map;
	}
	
	public ConcurrentMap<String,String> getJsonFields(JsonNode rootNode){
		
		ConcurrentMap<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>(); 
		
		Iterator<Map.Entry<String,JsonNode>> fieldIterator = rootNode.getFields();
		while(fieldIterator.hasNext()){
			
			Map.Entry<String,JsonNode> field = fieldIterator.next();
			concurrentHashMap.putIfAbsent(field.getKey(), field.getValue().getTextValue());
		}
		
		return concurrentHashMap;
	}
	

}
