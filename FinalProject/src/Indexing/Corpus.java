package Indexing;

import java.io.IOException;

import Class.MyDocument;

public interface Corpus {
		
	public MyDocument next() throws IOException;
	
}
