package projectControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet
{
	  public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	  {
		  PrintWriter out=res.getWriter();
		  Connection con=null;
		  ResultSet rs=null;
		   con=MainClass.getConnectionObject(); 
		   try {
		   Statement stmt=con.createStatement();
		   rs=stmt.executeQuery("select username,password from ptype1 where username='"+req.getParameter("t1")+"' and password='"+req.getParameter("t2")+"'");
		   if(rs.next())
		   {
			   RequestDispatcher rd=req.getRequestDispatcher("view.html");
			   rd.include(req, res);
		   }
		   else
		   {
			   out.print("invalid user");
		   }
		   }
		   catch(Exception e)
		   {
			   System.out.println(e);
		   }
	  }
}
