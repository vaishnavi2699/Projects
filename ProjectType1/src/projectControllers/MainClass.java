package projectControllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainClass 
{
	  static Connection con=null;
	  public static Connection getConnectionObject()
	  {
		  try {
			  Class.forName("org.h2.Driver");
			  con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  return con;
	  }
}
