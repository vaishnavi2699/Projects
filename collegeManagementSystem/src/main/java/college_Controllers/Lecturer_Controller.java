package college_Controllers;

import java.io.IOException;
import java.io.PrintWriter;import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college_Class_Interface.College_Class;
import college_Models.Lecturer_Model;
import college_Models.Marks_Model;
import college_Models.Student_Model;

@WebServlet(urlPatterns = {"/createpassword","/LecCreatePassword","/LecLoginForm","/LecturerLogin","/viewstudents","/totalmarks","/updateTotalMarks","/checkstudentsmarks","/lecturerlogout"})
public class Lecturer_Controller extends HttpServlet
{
	College_Class cc;
	public void init() throws ServletException
	{
		super.init();
		cc=new College_Class();
	}
    public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	String path=req.getServletPath();
    	PrintWriter out=res.getWriter();
    	Lecturer_Model l=new Lecturer_Model();
    	Student_Model s=new Student_Model();
    	Marks_Model m=new Marks_Model();
    	if(path.equals("/createpassword"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("LecturerCreatePassword.html");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/LecLoginForm"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("LecturerLogin.html");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/viewstudents"))
    	{
    		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
    		HttpSession session=req.getSession();
    		l.setUsername((session.getAttribute("id").toString()));
    		al=cc.checkMyStudents(l);
    		req.setAttribute("mystudents",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/totalmarks"))
    	{
    		m.setSid(Integer.parseInt(req.getParameter("tm")));
    		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
    		al=cc.enterTotalMarksOfStudent(m);
    		req.setAttribute("totalmarks",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/updateTotalMarks"))
    	{
    		m.setSid(Integer.parseInt(req.getParameter("t1")));
    		m.setInternalmarks(Integer.parseInt(req.getParameter("t4")));
    		m.setExternalmarks(Integer.parseInt(req.getParameter("t5")));
    		m.setSemestermarks(Integer.parseInt(req.getParameter("t6")));
    		m.setTotalmarks(m.getInternalmarks()+m.getExternalmarks()+m.getSemestermarks());
    		boolean b=cc.totalMarksOfStudent(m);
    		if(b)
    		{
    			out.print("<body><center><h2>Marks Submitted Sucessfully Click Here To<a href=viewstudents>Go Back</a></h2></center></body>");
    		}
    		else
    		{
    			out.print("<body><center><h2>Submiting Marks Failed Click Here To<a href=viewstudents>Go Back</a></h2></center></body>");
    		}
    	}
    	else if(path.equals("/checkstudentsmarks"))
    	{
    		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
    		HttpSession session=req.getSession();
    		l.setUsername((session.getAttribute("id").toString()));
    		al=cc.checkMarksOfStudents(l);
    		req.setAttribute("mystudentsmarks",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/lecturerlogout"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("index.html");
    		rd.forward(req, res);
    	}
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	String path=req.getServletPath();
    	Lecturer_Model l=new Lecturer_Model();
    	Student_Model s=new Student_Model();
    	PrintWriter out=res.getWriter();
    	if(path.equals("/LecturerLogin"))
    	{
    		l.setUsername(req.getParameter("t1"));
    		l.setPassword(req.getParameter("t2"));
    		boolean b=cc.lecturerLogin(l);
    		if(b)
    		{
    			HttpSession session=req.getSession();
    			session.setAttribute("id",l.getUsername());
    			RequestDispatcher rd=req.getRequestDispatcher("LecturerOptions.html");
         	    rd.forward(req,res);
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("LecturerLogin.html");
         	    rd.include(req,res);
         	    out.print("<body><center><h2>Invalid Username/Password</h2></center></body>");
    		}
    	}
    	else if(path.equals("/LecCreatePassword"))
    	{
    		boolean b=false;
    		l.setUsername((req.getParameter("t1")));
    		l.setPassword(req.getParameter("t2"));
    		b=cc.createPassword(l);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("LecturerCreatePassword.html");
         	    rd.include(req,res);
         	   out.print("<body><center><h2>Password Created Click Here For<a href=LecturerLogin.html>Login</a></h2></center></body>");
    		}
    	}
    }
}
