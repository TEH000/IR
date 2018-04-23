package com.ir.Indexing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ir.Class.MyDocument;
import com.ir.Class.Path;


public class guideCorpus implements Corpus {

	private FileReader fr;
	private BufferedReader br;
	
	public guideCorpus() throws IOException
	{
		fr = new FileReader(Path.path_guide);
		br = new BufferedReader(fr);
	}
	
	public MyDocument next() throws IOException
	{
		String line = br.readLine();
		
		while(line != null)
		{
			if (line.equals("-----")) // document start
			{
				// get properties from document
				String url = br.readLine().replace("<url>", "");
				String name = br.readLine().replace("<name>", "");
				String title = br.readLine().replace("<head>", "");
				String date = br.readLine().replace("<time>", "");
				String content = br.readLine().replace("<content>", "");
				
				// content = title + content
				MyDocument mydoc = new MyDocument(url,name,title,"guide",title+" "+content,date);
				return mydoc;
			}
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		return null;
	}
	
}
