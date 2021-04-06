package projectControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Update extends HttpServlet
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
    	PrintWriter out=res.getWriter();
    	ResultSet rs=null;
    	Statement st=null;
    	Connection con=null;
    	con=MainClass.getConnectionObject();
    	try 
    	{
    		st=con.createStatement();
			rs=st.executeQuery("select * from ptype1 where fullname='"+req.getParameter("id")+"'");
			if(rs.next())
			{
			   out.print("<body><form action=detailsupdate>FullName: <input type=text name=t1 readonly value="+rs.getString(1)+"><br>");
			   out.print("Username: <input type=text name=t2 value="+rs.getString(2)+"><br>");
			   out.print("Email: <input type=text name=t3 value="+rs.getString(3)+"><br>");
			   out.print("<input type=submit value=update><input type=Reset value=clear></form>");
			}
		} 
    	catch (SQLException e) 
    	{
			System.out.println(e);
		}
    	
    }
}
