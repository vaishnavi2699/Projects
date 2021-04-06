import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDetails extends HttpServlet
{

	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter out=res.getWriter();
		Connection con=null;
		String fullname=req.getParameter("t1");
		String username=req.getParameter("t2");
		String email=req.getParameter("t3");
		int i=0;
		try {
				Class.forName("org.h2.Driver");
				con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
		Statement stmt=con.createStatement();
		i=stmt.executeUpdate("update ptype1 set username='"+username+"',email='"+email+"' where fullname='"+fullname+"'");
		if(i>0)
		{
			RequestDispatcher rd=req.getRequestDispatcher("view");
        	rd.forward(req,res);
		}
		else
		{
			out.print("Update fail");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
