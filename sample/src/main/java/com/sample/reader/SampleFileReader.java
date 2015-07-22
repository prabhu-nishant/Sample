package com.sample.reader;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.sample.exception.FileReaderException;

public interface SampleFileReader {

	public ConcurrentMap readFile() throws FileReaderException;
	
}
