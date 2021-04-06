package projectControllers;

import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Forgot extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
   {
	   PrintWriter out=res.getWriter();
	   Connection con=null;
	   con=MainClass.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		   int i=st.executeUpdate("update ptype1 set password='"+req.getParameter("t2")+"' where email='"+req.getParameter("t1")+"'");
		   if(i>0)
		   {
			   RequestDispatcher rd=req.getRequestDispatcher("forgot.html");
			   rd.include(req, res);
			   out.print("<body><center><h3>Password changed sucessfully login with new password<a href=index.html>Login</h3></center></body>");
		   }
		   else
		   {
			   RequestDispatcher rd=req.getRequestDispatcher("forgot.html");
			   rd.include(req,res);
			   out.print("<body><center><h3>Incorrect Email</h3></center></body>");
		   }
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
   }
}
