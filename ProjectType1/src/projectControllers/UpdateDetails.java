package projectControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateDetails extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
   {
	   PrintWriter out=res.getWriter();
       Connection con=MainClass.getConnectionObject();
       try
       {
    	   Statement stmt=con.createStatement();
		   int i=stmt.executeUpdate("update ptype1 set username='"+req.getParameter("t2")+"',email='"+req.getParameter("t3")+"' where fullname='"+req.getParameter("t1")+"'");
	   if(i>0)
	   {
		   RequestDispatcher rd=req.getRequestDispatcher("view");
		   rd.forward(req, res);
	   }
	   else
	   {
		   out.print("failed");
	   }
       }
       catch(Exception e)
       {
    	   System.out.println(e);
       }
   }
}
