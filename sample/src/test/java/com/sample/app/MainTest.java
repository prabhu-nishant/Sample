package com.sample.app;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Main systemUnderTest;
	Scanner scanner;
	InputStream savedStandardInputStream;
		
	@Before
	public void setUp(){
		
		systemUnderTest = new Main();
		savedStandardInputStream = System.in;
		scanner = new Scanner(System.in);
	}
		
	
	@Test
	public void testForMenuChoice1() {
		
							
	}

}
