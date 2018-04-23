package com.ir.Indexing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.ir.Class.MyDocument;
import com.ir.Class.Path;

public class articleCorpus implements Corpus{

	private FileReader fr;
	private BufferedReader br;
	
	public articleCorpus() throws IOException
	{
		fr = new FileReader(Path.path_article);
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
				String title = br.readLine().replace("<title>", "");
				String subtitle = br.readLine().replace("<subtitle>", "");
				String date = br.readLine().replace("<time>", "");
				String content = br.readLine().replace("<content>", "");
				
				// content = content + title + subtitle
				MyDocument mydoc = new MyDocument(url,name,title,"article",title+" "+subtitle+" "+content,date);
				return mydoc;
			}
			line = br.readLine();
		}
		
		br.close();
		fr.close();
		return null;
	}
}
