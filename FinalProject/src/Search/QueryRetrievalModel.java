package Search;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import Class.MyAnalyzer;
import Class.MyDocument;
import Class.Path;
import Indexing.MyIndexReader;

public class QueryRetrievalModel {

	protected MyIndexReader indexReader;
	private Directory directory;
	private DirectoryReader ireader;
	private IndexSearcher indexSearcher;

	public QueryRetrievalModel(MyIndexReader ixreader) {
		indexReader = ixreader;
		try {
			directory = FSDirectory.open(Paths.get(Path.path_index));
			ireader = DirectoryReader.open(directory);
			indexSearcher = new IndexSearcher(ireader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MyDocument> retrieveQuery(String aQuery,int topN) throws Exception {
		List<MyDocument> results = new ArrayList<MyDocument>();
//		Query theQ = new QueryParser("CONTENT", new WhitespaceAnalyzer()).parse(aQuery);
//		Query theQ = new MultiFieldQueryParser(new String[] {"NAME","CONTENT"},new WhitespaceAnalyzer()).parse(aQuery);
		Query theQ = new MultiFieldQueryParser(new String[] {"NAME","CONTENT"},new MyAnalyzer()).parse(aQuery);
		ScoreDoc[] scoreDoc = indexSearcher.search(theQ,topN).scoreDocs;
		
		QueryScorer scorer = new QueryScorer(theQ);
		SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<B>","</B>");
		Highlighter highlighter = new Highlighter(simpleHtmlFormatter,scorer);
		highlighter.setTextFragmenter(new SimpleFragmenter(200));
		
		for (ScoreDoc score : scoreDoc) {
			
			Document document = indexSearcher.doc(score.doc);

			Analyzer analyzer = new MyAnalyzer();
			
			String content = highlighter.getBestFragment(analyzer,"CONTENT",document.get("CONTENT"));
			String title = highlighter.getBestFragment(analyzer,"TITLE",document.get("TITLE"));
			if(title == null)title = document.get("TITLE");
			
			String url = indexReader.getURL(score.doc);
			String name = indexReader.getName(score.doc);
//			String title = indexReader.getTitle(score.doc);
			String category = indexReader.getCategory(score.doc);
			String date = indexReader.getDate(score.doc);
//			String content = indexReader.getContent(score.doc);
			results.add(new MyDocument(url,name,title,category,content,date));
			
		}
		return results;
	}

}