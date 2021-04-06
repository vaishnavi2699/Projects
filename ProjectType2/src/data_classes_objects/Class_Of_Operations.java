package data_classes_objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Register_Model;

public class Class_Of_Operations 
{
	static Connection con=null;
   public static Connection getConnectionObject()
   {
	   try
	   {
		   Class.forName("org.h2.Driver");
		   con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	return con;
   }
   public int register(Register_Model rm)
   {
	   int i=0;
	   con=Class_Of_Operations.getConnectionObject();
		  try 
		  {
		  PreparedStatement pstmt=con.prepareStatement("insert into ptype2 values(?,?,?,?)");
		  pstmt.setString(1,rm.getFullName());
		  pstmt.setString(2,rm.getUserName());
		  pstmt.setString(3,rm.getEmail());
		  pstmt.setString(4,rm.getPassword());
		  i=pstmt.executeUpdate();
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  return i;
   }
   public ResultSet login(Register_Model rm)
	{
	      ResultSet rs=null;
	      con=Class_Of_Operations.getConnectionObject();
		  try 
		  {
			  Statement st=con.createStatement();
			   rs=st.executeQuery("select username,password from ptype2 where username='"+rm.getUserName()+"' and password='"+rm.getPassword()+"'");
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  return rs;
	}
   public int forget(Register_Model rm)
   {
	   int i=0;
	   con=Class_Of_Operations.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		    i=st.executeUpdate("update ptype2 set password='"+rm.getPassword()+"' where email='"+rm.getEmail()+"'");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   return i;
   }
   public ResultSet view(Register_Model rm)
   {
	   ResultSet rs=null;
	   con=Class_Of_Operations.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		   rs=st.executeQuery("select * from ptype2");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   return rs;
   }
   public Statement update(Register_Model rm)
   {
	   ResultSet rs=null;
	   Statement stmt=null;
	   con=Class_Of_Operations.getConnectionObject();
	   try
	   {
		    stmt=con.createStatement();
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   return stmt;
   }
   public int updateDetails(Register_Model rm)
   {
	   int i=0;
	   con=Class_Of_Operations.getConnectionObject();
	   try
	   { 
		   Statement stmt=con.createStatement();
		   i=stmt.executeUpdate("update ptype2 set username='"+rm.getUserName()+"',email='"+rm.getEmail()+"' where fullname='"+rm.getFullName()+"'");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   return i;
   }
   public int deleteuser(Register_Model rm)
   {
	   int i=0;
	   con=Class_Of_Operations.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		   i=st.executeUpdate("delete from ptype2 where fullname='"+rm.getFullName()+"'");
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
	   return i;
   }
}
