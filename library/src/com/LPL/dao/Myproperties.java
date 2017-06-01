package com.LPL.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Myproperties extends Properties{

	private static Myproperties myProperties;
	
	private Myproperties() throws IOException{
		InputStream in = Myproperties.class.getClassLoader().getResourceAsStream("db.properties");
		this.load(in);
	}
	public static Myproperties getInstance() throws IOException{
		  if(null==myProperties){
			  return new Myproperties();
		  }
		  return myProperties;
	}
 
}
