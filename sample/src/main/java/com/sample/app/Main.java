package com.sample.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

import org.codehaus.jackson.map.ObjectMapper;

import com.sample.exception.FileReaderException;
import com.sample.exception.ParseException;
import com.sample.factory.FileReaderFactory;
import com.sample.reader.SampleFileReader;
import com.sample.util.Constants;
import com.sample.util.ExpressionEvaluator;

public class Main {

	public static void main(String args[]){
		
		Main main = new Main();
		main.process();
		
	}

	public void process() {
		String choice = null;
		String input = null;
		Map<String,String> map = null;
		
		Scanner userInput = new Scanner(System.in);
		do
		{
			//Display our menu
			System.out.println("\n*** Menu ***");
			System.out.println("\n1. Import");
			System.out.println("2. Create/Update");
			System.out.println("3. Export");
			System.out.println("4. Exit");
			System.out.println("\n************");
			System.out.println("Please enter your choice:");

			//Get user input
			choice = userInput.nextLine();
			userInput.nextLine();
			switch(Integer.parseInt(choice))
			{
				case 1: System.out.println("Please enter JSON or CSV file path ");
						try {
							input=userInput.nextLine().trim();
							map = populateMap(input);
						} catch (FileReaderException e) {
							System.out.println("Exception while reading the file :"+e);
							e.printStackTrace();
						}
						
						break;
				case 2: System.out.println("Please enter valid expression with space btween brackets and operators");
						try {
							input=userInput.nextLine();
							evaluateExpression(input, map);
							
						}catch (ParseException e) {
							System.out.println("ParseException while evaluating the expression :"+e);
							e.printStackTrace();
						}
						break;
				case 3: System.out.println("Enter folder path where the results need to be exported....");
						try {
							input=userInput.nextLine();
							exportData(input, map);
							
						} catch (Exception e) {
							System.out.println("Exception while exporting data to a file :"+e);
							e.printStackTrace();
						}
						break;
				case 4:
						System.exit(0);
						break;
				default:
						System.out.println("You have entered an invalid choice");
			}
		}while(!choice.equals("4"));
	}

	public void exportData(String input, Map<String, String> map) throws Exception {
		if(map!=null && !map.isEmpty()){
			
			ObjectMapper mapper = new ObjectMapper();
			
			try {
				String json = mapper.writeValueAsString(map);
				FileWriter fw = new FileWriter(input+"\\Output.json");
				fw.write(json);
				fw.flush();
				fw.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			throw new Exception("There is no data to export.Please first import the existing attributes file in memory");
		}
	}

	public Map<String, String> evaluateExpression(String input, Map<String, String> map) throws ParseException {
		
		String[] expression = input.split(Constants.EXPRESSION_SPLIT_IDENTIFIER);
		String leftExp = expression[0].trim();
		String rightExp = expression[1].trim();
		String temp = rightExp.replaceAll(Constants.NON_STRING_IDENTIFIER, Constants.COMMA_IDENTIFIER);
		String existingAttr[] = temp.split(Constants.COMMA_IDENTIFIER);

		if(map!=null && !map.isEmpty()){
			
			for(String s:existingAttr){
				
				if(s!=null && !s.isEmpty() && !s.equals("")){
				
					if(map.containsKey(s.trim())){
									
						rightExp = rightExp.replaceAll(s.trim(), map.get(s.trim()));
						continue;
					}
				}
			}
		
			ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
			String evaluatedValue = expressionEvaluator.parseExpression(rightExp);
			map.put(leftExp, evaluatedValue);
		}
		else{
			
			throw new ParseException("Please first import the existing attributes file in memory");
		}
		return map;
	}

	public Map<String, String> populateMap(String input) throws FileReaderException {
		
		Map<String, String> map;
		FileReaderFactory readerFactory = new FileReaderFactory();
		SampleFileReader reader = readerFactory.getFileReaderFactory(input);
		map= reader.readFile();
		return map;
	}
}