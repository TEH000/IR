package Class;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class MyAnalyzer extends Analyzer{
	
	protected TokenStreamComponents createComponents(String fieldName)
	{
		Tokenizer source = new StandardTokenizer();
		TokenStream  result = new StandardFilter(source);
		result = new LowerCaseFilter(result);
		result = new StopFilter(result,EnglishAnalyzer.getDefaultStopSet());
		result = new PorterStemFilter(result);
		return new TokenStreamComponents(source,result);
	}

}
