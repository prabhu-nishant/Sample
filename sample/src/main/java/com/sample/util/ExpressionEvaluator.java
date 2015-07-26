package com.sample.util;

import java.util.Stack;

import com.sample.exception.ParseException;

public class ExpressionEvaluator {

	public String parseExpression(String expression) throws ParseException{
		
		Stack<Double> values = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		
		try
		{
			char[] tokens = expression.toCharArray();
			for (int i = 0; i < tokens.length; i++)
			{
				if (tokens[i] == ' ')
					continue;

				if (tokens[i] >= '0' && tokens[i] <= '9')
				{
					StringBuffer sbuf = new StringBuffer();
					while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9') || tokens[i] == '.' )){
						sbuf.append(tokens[i++]);
					}
					values.push(Double.parseDouble(sbuf.toString()));
				}
				else if (tokens[i] == '('){
					operators.push(tokens[i]);
				}
				else if (tokens[i] == ')')
				{
					while (operators.peek() != '('){
            	   values.push(applyOp(operators.pop(), values.pop(), values.pop()));
				}
					operators.pop();
			}
			else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/')
			{
               while (!operators.empty() && hasPrecedence(tokens[i], operators.peek())){
            	 values.push(applyOp(operators.pop(), values.pop(), values.pop()));
               }
               operators.push(tokens[i]);
           }
       }

       while (!operators.empty()){
    	   values.push(applyOp(operators.pop(), values.pop(), values.pop()));
       }
	}catch(Exception e ){
		throw new ParseException("Exception while parsing :",e);
	}
       return Double.toString(values.pop());
   }

   public static boolean hasPrecedence(char operator1, char operator2)
   {
       if (operator2 == '(' || operator2 == ')')
           return false;
       if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-'))
           return false;
       else
           return true;
   }

   public static double applyOp(char operator, double b, double a) throws ParseException
   {
       switch (operator)
       {
       case '+': return a + b;
       case '-': return a - b;
       case '*': return a * b;
       case '/':
           if (b == 0){
               throw new ParseException("Cannot divide by zero", new UnsupportedOperationException());
           }
           return a / b;
       }
       return 0;
   }
		
}
