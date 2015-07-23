package com.sample.app;

import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

import com.sample.exception.FileReaderException;
import com.sample.factory.ExpressionFactory;
import com.sample.factory.FileReaderFactory;
import com.sample.reader.SampleFileReader;

public class Main {

	public static void main(String args[]){
		
		int choice;
		String input;
		ConcurrentMap<String,String> map;
		
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
		
			switch(choice)
			{
				case 1: System.out.println("Please enter JSON or CSV file path ");
						input=userInput.next();
						try {
							FileReaderFactory readerFactory = new FileReaderFactory();
							SampleFileReader reader = readerFactory.getFileReaderFactory(input);
						    map= reader.readFile();
							
						} catch (FileReaderException e) {
							System.out.println("Exception while reading the file :"+e);
						}
						
						break;
				case 2: System.out.println("Please enter valid expression in the following format");
						System.out.println("attribute1 = attribute2/attribute3 * 5");
						input=userInput.next();
						ExpressionFactory expressionFactory = new ExpressionFactory();
						expressionFactory.parseExpression(input);
						
						
						break;
				case 3: System.out.println("Exporting data....");
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