package com.sample.factory;

import com.sample.util.Constants;

public class ExpressionFactory {

	public String[] parseExpression(String expression){
		
		String [] stringArray = new String[3];
		String[] temp = expression.split(Constants.EXPRESSION_SPLIT_IDENTIFIER);
		String[] attribute = temp[1].split(Constants.ATTRIBUTE_SPLIT_IDENTIFIER);
		stringArray[0] = temp[0];
		stringArray[1] = attribute[1];
		stringArray[2] = attribute[2];
		return stringArray;
	}
	
	public String evaluateExpression(String expression){
		
		String string = null;
		
		return string;
	}
	
}
