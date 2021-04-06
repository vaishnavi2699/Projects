package projectControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewAll extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
   {
	   PrintWriter out=res.getWriter();
	   ResultSet rs=null;
	   Connection con=null;
	   con=MainClass.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		   rs=st.executeQuery("select * from ptype1");
		   out.print("<table border=1 cellpadding=7><tr bgcolor=yellow><td>fullname</td><td>username</td><td>Email ID</td><td>update</td><td>delete</td></tr>");
		   while(rs.next())
		   {
			   out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td><a href=requpdate?id="+rs.getString(1)+">update</a></td><td><a href=reqdelete?del="+rs.getString(1)+">delete</a></td></tr>");
		   }
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
   }
}
