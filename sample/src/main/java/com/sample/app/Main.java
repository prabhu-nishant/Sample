package com.sample.app;

import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

import org.json.JSONObject;

import com.sample.exception.FileReaderException;
import com.sample.exception.ParseException;
import com.sample.factory.FileReaderFactory;
import com.sample.reader.SampleFileReader;
import com.sample.util.Constants;
import com.sample.util.ExpressionEvaluator;

public class Main {

	public static void main(String args[]){
		
		int choice = 0;
		String input = null;
		ConcurrentMap<String,String> map = null;
		
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
			choice = userInput.nextInt();
			userInput.nextLine();
			switch(choice)
			{
				case 1: try {
							System.out.println("Please enter JSON or CSV file path ");
							input=userInput.nextLine().trim();
							FileReaderFactory readerFactory = new FileReaderFactory();
							SampleFileReader reader = readerFactory.getFileReaderFactory(input);
						    map= reader.readFile();
						} catch (FileReaderException e) {
							System.out.println("Exception while reading the file :"+e);
							e.printStackTrace();
						}
						
						break;
				case 2: try {
							System.out.println("Please enter valid expression in the following format without space");
							input=userInput.nextLine();
							String[] expression = input.split(Constants.EXPRESSION_SPLIT_IDENTIFIER);
							String leftExp = expression[0].trim();
							String rightExp = expression[1].trim();
							String temp = rightExp.replaceAll(Constants.NON_STRING_IDENTIFIER, Constants.COMMA_IDENTIFIER);
							String existingAttr[] = temp.split(Constants.COMMA_IDENTIFIER);
						
							if(map!=null){
								
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
								System.out.println("Evaluated value is "+evaluatedValue);
								if(map.containsKey(leftExp)){
							
									map.replace(leftExp, evaluatedValue);
							
								}
								else{
							
									map.put(leftExp, evaluatedValue);
							
								}
							}
							else{
								
								System.out.println("Please first import the existing attributes file in memory");
							}
							
						}catch (ParseException e) {
							System.out.println("ParseException while evaluating the expression :"+e);
							e.printStackTrace();
						}
						break;
				case 3: System.out.println("Exporting data....");
						
						if(map!=null && !map.isEmpty()){
							
							JSONObject json = new JSONObject(map);
							
						}
						else{
							System.out.println("There is no data to export.Please first import the existing attributes file in memory");
						}
				
						break;
				case 4:
						System.exit(0);
						break;
				default:
						System.out.println("You entered an invalid choice");
			}
		}while(choice != 4);

			
		
	}
}