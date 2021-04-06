import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException   
   {
  PrintWriter out=res.getWriter();
  String fullname=req.getParameter("t1");
  String username=req.getParameter("t2");
  String email=req.getParameter("t3");
  String password=req.getParameter("t4");
  int i=0;
  try {
  Class.forName("org.h2.Driver");
  Connection con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
  PreparedStatement pstmt=con.prepareStatement("insert into ptype1 values(?,?,?,?)");
  pstmt.setString(1,fullname);
  pstmt.setString(2,username);
  pstmt.setString(3,email);
  pstmt.setString(4,password);;
  i= pstmt.executeUpdate();
  if(i>0)
  {
      RequestDispatcher rd=req.getRequestDispatcher("index.html");
      rd.forward(req, res);
      out.print("<body><center><h3>Registration done<a href=index.html>login</a></h3></center></body>");
  }
  else
  {
	  RequestDispatcher rd=req.getRequestDispatcher("RegisterPage.html");
      rd.forward(req, res);
      out.print("registration failed");
  }
  }
  catch(Exception e)
  {
  System.out.println(e);
  }
   out.close();
   
   }
}