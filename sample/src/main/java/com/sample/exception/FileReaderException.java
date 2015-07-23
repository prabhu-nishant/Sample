package com.sample.exception;

public class FileReaderException extends Exception{

	public FileReaderException(String string,Exception e){
		super(string,e);
	}

	public FileReaderException(String string) {
		super(string);
	}
}
