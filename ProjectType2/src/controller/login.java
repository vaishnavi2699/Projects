package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_classes_objects.Class_Of_Operations;
import model.Register_Model;

public class login extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
   {
	   PrintWriter out=res.getWriter();
	   Register_Model rm=new Register_Model();
	   rm.setUserName(req.getParameter("t1"));
	   rm.setPassword(req.getParameter("t2"));
	   ResultSet rs=null;
	   rs=new Class_Of_Operations().login(rm);
		    try
			   {
				if(rs.next())
				   {
					   RequestDispatcher rd=req.getRequestDispatcher("view.html");
					   rd.forward(req, res);
				   }
				   else
				   {
					   out.print("invalid user");
				   }
			   } 
			   catch (SQLException e) 
			   {

				System.out.println(e);
			}
     } 
   }

