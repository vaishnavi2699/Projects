import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
		Connection con=null;
		ResultSet rs=null;
		try {
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
		
			Statement stmt=con.createStatement();
		rs=stmt.executeQuery("select * from ptype1");
		out.print("<table border=3 cellpadding=7><tr bgcolor=yellow><td>fullname</td><td>username</td><td>Email</td><td>updateuser</td><td>deleteuser</td></tr>");
		while(rs.next())
		{
			out.print("<body><tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td><a href=requpdate?id="+rs.getString(1)+">Update</a></td><td><a href=reqdelete?del="+rs.getString(1)+">delete</a></td></tr>");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}

}
