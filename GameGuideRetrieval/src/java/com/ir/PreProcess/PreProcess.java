package com.ir.PreProcess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ir.Class.Path;
import com.ir.Class.Stemmer;

public class PreProcess {
	
	private Pattern word_p;
	private HashSet stopwords;
	
	public PreProcess() throws IOException
	{
		word_p = Pattern.compile("([a-zA-Z0-9][a-zA-Z0-9-_']*)");
		
		stopwords = new HashSet<>();
		FileReader fr = new FileReader(Path.path_stopword);
		BufferedReader br = new BufferedReader(fr);
        String str = null;
        while ((str = br.readLine())!=null) stopwords.add(str);
        br.close();
        fr.close();
        
	}
	
	public String preProcess(String content)
	{
		StringBuilder strb = new StringBuilder();
		Stemmer s = new Stemmer();
		String word = "";
		
		// 1) tokenized
		Matcher match;
		match = word_p.matcher(content);
		
		while (match.find())
		{
			word=match.group();
			// 2) to lowercase
			word = word.toLowerCase();
			
			// 3) remove stop words
			if (!stopwords.contains(word))
			{
				// 4) stemming
				char[] w = word.toCharArray();
				s.add(w, w.length);
				s.stem();
				word = s.toString();
				// 5�� check whether the word in the index file

				strb.append(word);
				strb.append(" ");

			}
		}
		
		return strb.toString();
	}
	
}
