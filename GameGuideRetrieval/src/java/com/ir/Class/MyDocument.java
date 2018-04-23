package com.ir.Class;

public class MyDocument {
	
	private String url;
	private String title;
	private String category;
	private String name;
	private String content;
	private String date;
	
	// constructor
	public MyDocument(String url, String name,String title,String category,String content,String date)
	{
		this.url=url;
		this.name = name;
		this.title = title;
		this.category = category;
		this.content = content;
		this.date = date;
	}
	
	// set and get function
	public String name()
	{
		return this.name;
	}
	public String url()
	{
		return this.url;
	}
	public String title()
	{
		return this.title;
	}
	public String category()
	{
		return this.category;
	}
	public String content()
	{
		return this.content;
	}
	public String date()
	{
		return this.date;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}

}
