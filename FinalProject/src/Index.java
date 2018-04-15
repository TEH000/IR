import Class.MyDocument;
import Indexing.MyIndexWriter;
import Indexing.articleCorpus;
import Indexing.guideCorpus;

public class Index {
	
	public static void main(String[] args) throws Exception
	{
		MyIndexWriter iwriter = new MyIndexWriter();
		MyDocument mydoc = null;
		
		guideCorpus guidecorpus = new guideCorpus();
			
		while ((mydoc = guidecorpus.next())!=null)
		{
			iwriter.index(mydoc);
		}
//		
		articleCorpus articlecorpus =new articleCorpus();
		while ((mydoc = articlecorpus.next())!=null)
		{
			iwriter.index(mydoc);
		}
//		
		iwriter.close();
	}

}
