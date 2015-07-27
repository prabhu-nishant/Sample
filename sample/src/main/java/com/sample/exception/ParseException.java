package com.sample.exception;

public class ParseException extends Exception{

	public ParseException(String string,Exception e){
		super(string,e);
	}
	
	public ParseException(String string){
		super(string);
	}
	
}
