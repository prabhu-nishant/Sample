package com.sample.reader;

import java.util.Map;

import com.sample.exception.FileReaderException;

public interface SampleFileReader {

	public Map readFile() throws FileReaderException;
	
}
