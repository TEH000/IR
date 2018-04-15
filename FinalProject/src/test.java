import java.util.List;

import Class.MyDocument;
import Indexing.MyIndexReader;
import Search.QueryRetrievalModel;

public class test{
	
	
	public static void main(String[] args) throws Exception
	{

		MyIndexReader ireader = new MyIndexReader();
		QueryRetrievalModel model = new QueryRetrievalModel(ireader);
		String aQuery="rescue princess";
		List<MyDocument> res = model.retrieveQuery(aQuery, 100);
		for (MyDocument mydoc : res)
		{
			System.out.println(mydoc.url());
			System.out.println(mydoc.title());
			System.out.println(mydoc.category());
			System.out.println(mydoc.date());
			System.out.println(mydoc.content());
			System.out.println("\n");
		}
		
//		try
//		{
//			MyAnalyzer analyzer = new MyAnalyzer();
//			TokenStream ts = analyzer.tokenStream("text", "Location, a the what's locate ");
//			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
//			ts.reset();
//			while(ts.incrementToken())
//			{
//				System.out.println(term.toString());
//			}
//			ts.end();
//			ts.close();
//		}
//		catch (IOException e)
//		{
//			e.printStackTrace();
//		}

	}

	

}
