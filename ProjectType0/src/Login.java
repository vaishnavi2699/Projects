import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login  extends HttpServlet 
{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
	PrintWriter out=res.getWriter();
	Connection con=null;
	String username=req.getParameter("t1");
	String password=req.getParameter("t2");
	ResultSet rs=null;
	try {
		Class.forName("org.h2.Driver");
		con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
	
		Statement stmt=con.createStatement();
	rs=stmt.executeQuery("select username,password from ptype1 where username='"+username+"' and password='"+password+"'");
	if(rs.next())
	{
		RequestDispatcher rd=req.getRequestDispatcher("view.html");
		rd.forward(req,res);
		
	}
	else
	{
		RequestDispatcher rd=req.getRequestDispatcher("index.html");
		rd.include(req,res);
		out.print("<center>Invalid username /password");
		
	}
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
}
	
}
