package com.sample.reader;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.sample.exception.FileReaderException;

public interface SampleFileReader {

	public HashMap readFile() throws FileReaderException;
	
}
