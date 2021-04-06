package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller_Classes.MainClass;
import projectModel.Model;

public class Controller extends HttpServlet
{
	 public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	    {
	    	PrintWriter out=res.getWriter();
	    	String path=req.getServletPath();
	    	Model m=new Model();
	    	if(path.equals("/login"))
	    	{
	    		m.setUserName(req.getParameter("t1"));
	    		m.setPassword(req.getParameter("t2"));
	    		boolean b=new MainClass().login(m.getUserName(),m.getPassword());
	    		if(b)
	    		{
	    			RequestDispatcher rd=req.getRequestDispatcher("view.html");
	    			rd.include(req, res);
	    		}
	    		else
	    		{
	    			RequestDispatcher rd=req.getRequestDispatcher("index.html");
	    			rd.include(req, res);
	    			out.print("invalid user");
	    		}
	    	}
	    	else if(path.equals("/register"))
	    	{
	    		m.setFullName(req.getParameter("t1"));
	    		m.setUserName(req.getParameter("t2"));
	    		m.setEmail(req.getParameter("t3"));
	    		m.setPassword(req.getParameter("t4"));
	    		boolean b=new MainClass().register(m.getFullName(),m.getUserName(),m.getEmail(),m.getPassword());
	    		if(b)
	    		{
	    			RequestDispatcher rd=req.getRequestDispatcher("RegisterPage.html");
	    			rd.include(req, res);
	    			out.print("<body><center><h3>Registered sucessfully <a href=index.html>login</a></h3></center></body>");
	    		}
	    		else
	    		{
	    			RequestDispatcher rd=req.getRequestDispatcher("RegisterPage.html");
	    			rd.include(req, res);
	    			out.print("Registration failed");
	    		}
	    	}
	    	else if(path.equals("/view"))
	    	{
	    		ResultSet rs=null;
	    		rs=new MainClass().ViewAll();
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
	    	else if(path.equals("/requpdate"))
	    	{
	    		ResultSet rs=null;
	    		m.setFullName(req.getParameter("id"));
	    		rs=new MainClass().update(m.getFullName());
	    		try
	    		{
	    			while(rs.next())
	    			{
	    				out.print("<body><form action=detailsupdate>FullName: <input type=text name=t1 readonly value="+rs.getString(1)+"><br>");
	    				out.print("Useranme: <input type=text name=t2 value="+rs.getString(2)+"><br>");
	    				out.print("Email: <input type=text name=t3 value="+rs.getString(3)+"><br>");
	    				out.print("<input type=submit value=update><input type=Reset value=clear></form>");
	    			}
	    		}
	    		catch(Exception e)
	    		{
	    			System.out.println(e);
	    		}
	    	}
	    	else if(path.equals("/detailsupdate"))
	    	{
	    		ResultSet rs=null;
	    		m.setFullName(req.getParameter("t1"));
	    		m.setUserName(req.getParameter("t2"));
	    		m.setEmail(req.getParameter("t3"));
	    		boolean b=new MainClass().updateDetails(m.getFullName(),m.getUserName(),m.getEmail());
				if(b)
				{
					RequestDispatcher rd=req.getRequestDispatcher("view");
					rd.forward(req, res);
				}
				else
				{
					out.print("failed");
				}
	    	}
	    	else if(path.equals("/reqdelete"))
	    	{
	    		m.setFullName(req.getParameter("del"));
	    		boolean b=new MainClass().delete(m.getFullName());
	    		if(b)
	    		{
	    			RequestDispatcher rd=req.getRequestDispatcher("view");
					rd.forward(req, res);
	    		}
	    		else
	    		{
	    			out.print("failed");
	    		}
	    	}
	    }
}
