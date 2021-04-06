import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter out=res.getWriter();
		String fullname=req.getParameter("id");
		try
		{
			Class.forName("org.h2.Driver");
			Connection con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from ptype1 where fullname='"+fullname+"'");
			if(rs.next())
		      {
		    	  out.print("<body><form action=detailsupdate>fullName : <input type=text name= t1 readonly value="+rs.getString(1)+"><br>");
		    	  out.print("username : <input type=text name=t2 value="+rs.getString(2)+" ><br>");
		    	  out.print("email : <input type=text name=t3 value="+rs.getString(3)+"><br>");
		    	  out.print("<input type=submit value=Update><input type=Reset value=clear></form>");
		    }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}