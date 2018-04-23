package com.ir.Search;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.ir.Class.MyAnalyzer;
import com.ir.Class.MyDocument;
import com.ir.Class.Path;
import com.ir.Indexing.MyIndexReader;

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

	public List<MyDocument> retrieveQuery(String aQuery,int topN,String type) throws Exception {
		
		List<MyDocument> results = new ArrayList<MyDocument>();
		
		// building query
		Builder builder = new Builder();
		
		Query query = new MultiFieldQueryParser(new String[] {"NAME","CONTENT"},new MyAnalyzer()).parse(aQuery);
		
		// filter by different category
		if (!type.equals("all"))
		{
			Query category =null;
			switch(type)
			{
			case "guide":
				category = new MultiFieldQueryParser(new String[] {"CATEGORY"},new MyAnalyzer()).parse("guide");
				break;
			case "article":
				category = new MultiFieldQueryParser(new String[] {"CATEGORY"},new MyAnalyzer()).parse("article");
				break;
			}
			
			builder.add(category, Occur.MUST);
		}
		
		builder.add(query, Occur.SHOULD);
		
		// get search result
		ScoreDoc[] scoreDoc = indexSearcher.search(builder.build(),topN).scoreDocs;
		
		// highlight the query words in context
		QueryScorer scorer = new QueryScorer(query);
		SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");
		Highlighter highlighter = new Highlighter(simpleHtmlFormatter,scorer);
		highlighter.setTextFragmenter(new SimpleFragmenter(200));
		
		// get return document information
		for (ScoreDoc score : scoreDoc) {
			
			Document document = indexSearcher.doc(score.doc);
			Analyzer analyzer = new MyAnalyzer();
			
			String content = highlighter.getBestFragment(analyzer,"CONTENT",document.get("CONTENT"));
			// if there is no query word in content
			if(content == null) content=document.get("CONTENT").substring(0, 200);
			
			String title = highlighter.getBestFragment(analyzer,"TITLE",document.get("TITLE"));
			// if there is no query word in title
			if(title == null)title = document.get("TITLE");
			
			String url = indexReader.getURL(score.doc);
			String name = indexReader.getName(score.doc);
			String category = indexReader.getCategory(score.doc);
			String date = indexReader.getDate(score.doc);
			
			results.add(new MyDocument(url,name,title,category,content,date));
			
		}
		return results;
	}

}