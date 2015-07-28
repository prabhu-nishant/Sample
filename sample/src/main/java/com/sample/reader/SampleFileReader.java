package com.sample.reader;

import java.util.List;
import java.util.Map;

import com.sample.exception.FileReaderException;

public interface SampleFileReader {

	public List<Map<String, String>> readFile() throws FileReaderException;
	
}
