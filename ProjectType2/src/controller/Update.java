package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_classes_objects.Class_Of_Operations;
import model.Register_Model;

public class Update extends HttpServlet
{
    public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
    {
    	PrintWriter out=res.getWriter();
    	Register_Model rm=new Register_Model();
    	rm.setFullName(req.getParameter("id"));
    	ResultSet rs=null;
    	Statement st=null;
    	st=new Class_Of_Operations().update(rm);
    	try 
    	{
    		System.out.println(rm.getFullName());
			rs=st.executeQuery("select * from ptype2 where fullname='"+rm.getFullName()+"'");
			if(rs.next())
			{
			out.print("<body><form action=detailsupdate>FullName: <input type=text name=t1 readonly value="+rs.getString(1)+"><br>");
			out.print("Useranme: <input type=text name=t2 value="+rs.getString(2)+"><br>");
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
