package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_classes_objects.Class_Of_Operations;
import model.Register_Model;

public class ViewAll extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
   {
	   PrintWriter out=res.getWriter();
	   ResultSet rs=null;
	   Register_Model rm=new Register_Model();
	   rs=new Class_Of_Operations().view(rm);
	   out.print("<table border=1 cellpadding=7><tr bgcolor=yellow><td>fullname</td><td>username</td><td>Email ID</td><td>update</td><td>delete</td></tr>");
	   try
	   {
		   while(rs.next())
		   {
			   out.print("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td><a href=requpdate?id="+rs.getString(1)+">update</a></td><td><a href=reqdelete?del="+rs.getString(1)+">delete</a></td></tr>");
				
		   }
	   }
	   catch(Exception e)
	   {
		   System.out.println(e);
	   }
   }
}
