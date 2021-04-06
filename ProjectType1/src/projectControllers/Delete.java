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

public class Delete extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
   {
	   PrintWriter out=res.getWriter();
	   Connection con=MainClass.getConnectionObject();
	   try
	   {
		   Statement st=con.createStatement();
		   int i=st.executeUpdate("delete from ptype1 where fullname='"+req.getParameter("del")+"'");
	       if(i>0)
	       {
		     RequestDispatcher rd=req.getRequestDispatcher("view");
		     rd.forward(req, res);
	       }
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
   }
}
