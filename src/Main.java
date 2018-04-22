import java.util.List;

import Class.MyDocument;
import Indexing.MyIndexReader;
import Search.QueryRetrievalModel;

public class Main {
	
	// testing
	
	public static void main(String[] args) throws Exception
	{
		MyIndexReader ireader = new MyIndexReader();
		QueryRetrievalModel model = new QueryRetrievalModel(ireader);
		String aQuery="rescue princess";
		List<MyDocument> res = model.retrieveQuery(aQuery, 100,"article");
		for (MyDocument mydoc : res)
		{
			System.out.println(mydoc.url());
			System.out.println(mydoc.title());
			System.out.println(mydoc.category());
			System.out.println(mydoc.date());
			System.out.println(mydoc.content());
			System.out.println("\n");
		}
		
	}
}
