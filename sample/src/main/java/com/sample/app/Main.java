package com.sample.app;

import com.sample.exception.FileReaderException;
import com.sample.factory.FileReaderFactory;
import com.sample.reader.SampleFileReader;

public class Main {

	public static void main(String args[]){
		
		FileReaderFactory readerFactory = new FileReaderFactory();
		SampleFileReader reader =readerFactory.getFileReaderFactory("C:\\sample.json");
		try {
			
			reader.readFile();
			
		} catch (FileReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
