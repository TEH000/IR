package com.ir.Indexing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import com.ir.Class.Path;


public class MyIndexReader {
	protected File dir;
	private Directory directory;
	private DirectoryReader ireader;
	private IndexSearcher isearcher;
	
	public MyIndexReader( ) throws IOException {

		directory = FSDirectory.open(Paths.get(Path.path_index)); 
		ireader = DirectoryReader.open(directory);
		isearcher = new IndexSearcher(ireader);
	}

	public int getDocid( String docno ) throws IOException {
		// you should implement this method.
		Query query = new TermQuery(new Term("DOCNO", docno));
		TopDocs tops= isearcher.search(query,1);
		return tops.scoreDocs[0].doc;
	}
	
	public String getURL( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("URL");
	}
	
	public String getTitle( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("TITLE");
	}
	
	public String getCategory( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("CATEGORY");
	}
	
	public String getDate( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("DATE");
		
	}
	
	public String getContent( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("CONTENT");
	}

	public String getName( int docid ) throws IOException {
		Document doc = ireader.document(docid);
		return (doc==null)?null:doc.get("NAME");
	}
	
	public int[][] getPostingList( String token ) throws IOException {
		Term tm = new Term("CONTENT", token);
		int df = ireader.docFreq(tm);
		if(df==0)
			return null;
		Query query = new TermQuery(tm);
		TopDocs tops= isearcher.search(query,df);		
		ScoreDoc[] scoreDoc = tops.scoreDocs;
		int[][] posting = new int[df][];
		int ix = 0;
		Terms vector;
		TermsEnum termsEnum;
		BytesRef text;
		for (ScoreDoc score : scoreDoc){
			int id = score.doc;
			int freq=0;
			vector = ireader.getTermVector(id, "CONTENT");
			termsEnum = vector.iterator();
			while ((text = termsEnum.next()) != null) {
			    if(text.utf8ToString().equals(token))
			    	freq+= (int) termsEnum.totalTermFreq();
			}
			posting[ix] = new int[] { id, freq };
			ix++;
		}
		return posting;
	}
	
	public int DocFreq( String token ) throws IOException {
		Term tm = new Term("CONTENT", token);
		int df = ireader.docFreq( tm );
		return df;
	}

	public long CollectionFreq( String token ) throws IOException {
		Term tm = new Term("CONTENT", token);
		long ctf=ireader.totalTermFreq(tm);
		return ctf;
	}
	
	public int docLength( int docid ) throws IOException {
		int doc_length = 0;
		Terms vector = ireader.getTermVector( docid, "CONTENT" );
		TermsEnum termsEnum = vector.iterator();
		BytesRef text;
		while ((text = termsEnum.next()) != null) {
			doc_length+= (int) termsEnum.totalTermFreq();
		}
		return doc_length;
	}
	
	public void close() throws IOException {
		ireader.close();
		directory.close();
	}
	
	public double REF() throws IOException
	{
		double REF=0;
		Fields fields = MultiFields.getFields(ireader);
		Terms terms = fields.terms("CONTENT");
		TermsEnum termsEnum = terms.iterator();
		BytesRef text;
		while ((text = termsEnum.next()) != null) {
			REF+= (int) termsEnum.totalTermFreq();
		}
		return REF;
	}
	
	public int getTokenFreqById(String token,int docid) throws IOException
	{
		int freq=0;
		Terms vector;
		TermsEnum termsEnum;
		BytesRef text;
		vector = ireader.getTermVector(docid, "CONTENT");
		termsEnum = vector.iterator();
		while ((text = termsEnum.next()) != null) {
		    if(text.utf8ToString().equals(token))
		    	freq+= (int) termsEnum.totalTermFreq();
		}
		return freq;
	}
	
}
