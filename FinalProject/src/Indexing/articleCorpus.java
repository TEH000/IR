package Indexing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Class.MyDocument;
import Class.Path;
import PreProcess.PreProcess;

public class articleCorpus implements Corpus{

	private FileReader fr;
	private BufferedReader br;
	private PreProcess pre;
	
	public articleCorpus() throws IOException
	{
		pre = new PreProcess();
		fr = new FileReader(Path.path_article);
		br = new BufferedReader(fr);
	}
	public MyDocument next() throws IOException
	{
		String line = br.readLine();
		
		while(line != null)
		{
			if (line.equals("-----"))
			{
				String url = br.readLine().replace("<url>", "");
				String name = br.readLine().replace("<name>", "");
				String title = br.readLine().replace("<title>", "");
				String subtitle = br.readLine().replace("<subtitle>", "");
				String date = br.readLine().replace("<time>", "");
				String content = br.readLine().replace("<content>", "");
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
