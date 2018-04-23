package com.ir.Indexing;

import java.io.IOException;

import com.ir.Class.MyDocument;

public interface Corpus {
	
	
	public MyDocument next() throws IOException;
	
}
