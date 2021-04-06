package projectControllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		Connection con=null;
		int i=0;
		con=MainClass.getConnectionObject();
		PrintWriter out=res.getWriter();
		try {
		PreparedStatement pstmt=con.prepareStatement("insert into ptype1 values(?,?,?,?)");
		pstmt.setString(1,req.getParameter("t1"));
		pstmt.setString(2,req.getParameter("t2"));
		pstmt.setString(3,req.getParameter("t3"));
		pstmt.setString(4,req.getParameter("t4"));
		i=pstmt.executeUpdate();
		if(i>0)
		{
			RequestDispatcher rd=req.getRequestDispatcher("RegisterPage.html");
			rd.include(req, res);
			out.print("<body><center><h3>Registration completed Successfully click here to <a href=index.html>login</a></h3></center></body>");
		}
		else
		{
			RequestDispatcher rd=req.getRequestDispatcher("RegisterPage.html");
			rd.include(req,res);
			out.print("Registration Fail");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
	
