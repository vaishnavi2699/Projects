import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Delete extends HttpServlet
{

	public void service(HttpServletRequest req,HttpServletResponse res)
	{
		Connection con=null;
		int i=0;
	    String fullname=req.getParameter("del");
		try 
		{
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
		
			Statement stmt=con.createStatement();
			i=stmt.executeUpdate("delete from ptype1 where fullname='"+fullname+"'");
            if(i>0)
            {
            	RequestDispatcher rd=req.getRequestDispatcher("view");
            	rd.forward(req,res);
            }

	    }
		catch(Exception e)
		{
			System.out.println(e);
		}
    }
}
