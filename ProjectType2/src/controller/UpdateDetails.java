package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data_classes_objects.Class_Of_Operations;
import model.Register_Model;

public class UpdateDetails extends HttpServlet
{
   public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
   {
	   PrintWriter out=res.getWriter();
	   Register_Model rm=new Register_Model();
	   rm.setFullName(req.getParameter("t1"));
	   System.out.println(req.getParameter("t1")+"fullname of id");
	   rm.setUserName(req.getParameter("t2"));
	   rm.setEmail(req.getParameter("t3"));
	   int i=new Class_Of_Operations().updateDetails(rm);
	   if(i>0)
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
