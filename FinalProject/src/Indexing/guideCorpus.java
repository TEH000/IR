package Indexing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Class.MyDocument;
import Class.Path;
import PreProcess.PreProcess;


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
			if (line.equals("-----"))
			{
				String url = br.readLine().replace("<url>", "");
				String name = br.readLine().replace("<name>", "");
				String title = br.readLine().replace("<head>", "");
				String date = br.readLine().replace("<time>", "");
				String content = br.readLine().replace("<content>", "");
//				MyDocument mydoc = new MyDocument(url,pre.preProcess(name),title,"guide",pre.preProcess(title+" "+content),date);
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
