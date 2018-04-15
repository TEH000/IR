package Indexing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import Class.MyAnalyzer;
import Class.MyDocument;
import Class.Path;


public class MyIndexWriter {
	
	protected File dir;
	private Directory directory;
	private IndexWriter ixwriter;
	private FieldType type;
	
	
	public MyIndexWriter() throws IOException {
		
		// check the directory is exist. If not create it;
		String path = Path.path_index;
		
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) file.mkdir();
		
		directory = FSDirectory.open(Paths.get(path));  
		IndexWriterConfig indexConfig=new IndexWriterConfig(new MyAnalyzer());
		indexConfig.setMaxBufferedDocs(10000);
		ixwriter = new IndexWriter( directory, indexConfig);
		
		type = new FieldType();
		type.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
		type.setStored(true);
		type.setStoreTermVectors(true);
	}
	
	// create index
	public void index( MyDocument mydoc) throws IOException {
		Document doc = new Document();
		
		doc.add(new StoredField("URL",mydoc.url()));
		doc.add(new Field("NAME",mydoc.name(),type));
		doc.add(new StoredField("TITLE",mydoc.title()));
		doc.add(new StoredField("CATEGORY", mydoc.category()));
		doc.add(new Field("CONTENT", mydoc.content(), type));
		doc.add(new StoredField("DATE",mydoc.date()));		
		ixwriter.addDocument(doc);
	}
	
	// close file
	public void close() throws IOException {
		ixwriter.close();
		directory.close();
	}
	
}
