package college_Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import college_Class_Interface.College_Class;
import college_Models.Course_Model;
import college_Models.Fee_Remainder_Model;
import college_Models.Lecturer_Model;
import college_Models.Marks_Model;
import college_Models.Recption_Model;
import college_Models.Student_Model;

@WebServlet(urlPatterns = {"/reccreatepassword","/RecLoginForm","/ReciptionistLogin","/RecCreatePassword","/addstudents","/AddStudents","/viewallstudents","/feeremainder","/displaystudentsbycourse","/viewcourses","/viewstudentsbycourse","/reciptionistlogout"})
public class Reciption_Controller extends HttpServlet
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
    	Student_Model s=new Student_Model();
    	Fee_Remainder_Model f=new Fee_Remainder_Model();
    	Course_Model c=new Course_Model();
    	Marks_Model m=new Marks_Model();
    	
    	if(path.equals("/reccreatepassword"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("ReciptionistCreatePassword.html");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/RecLoginForm"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("ReciptionistLogin.html");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/addstudents"))
    	{
    		ArrayList<Course_Model> al=new ArrayList<Course_Model>();
    		al=cc.getAllCources();
    		if(al!=null)
    		{
    			req.setAttribute("cources",al);
        		RequestDispatcher rd=req.getRequestDispatcher("ReciptionistADDStudent.jsp");
         	    rd.forward(req,res);
    		}
    	}
    	else if(path.equals("/viewallstudents"))
    	{
    		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
    		al=cc.viewAllStudents();
    		req.setAttribute("reciptionistviewallstudents", al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/feeremainder"))
    	{
    		f.setSid(Integer.parseInt(req.getParameter("id")));
    		boolean b=cc.sendFeeRemainderToStudent(f);
    		if(b)
    		{
    			out.print("<body><center><h3>Remainder Sent<a href=viewallstudents>Go Back</a></h3></center></body>");
    		}
    		else
    		{
    			out.print("<body><center><h3>Sending Remainder Failed<a href=viewallstudents>Go Back</a> And Try Again</h3></center></body>");
    		}
    	}
    	else if(path.equals("/viewcourses"))
    	{
    		ArrayList<Course_Model> al=new ArrayList<Course_Model>();
    		al=cc.viewCources();
    		req.setAttribute("reciptionistviewcources",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/viewstudentsbycourse"))
    	{
    		c.setCoursename(req.getParameter("id"));
    		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
    		al=cc.checkStudentsByCourse(c);
    		req.setAttribute("reciptionistviewstudentsbycourse", al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
     	    rd.forward(req,res);
    	}
    	else if(path.equals("/reciptionistlogout"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("index.html");
    		rd.forward(req, res);
    	}
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	String path=req.getServletPath();
    	Recption_Model r=new Recption_Model();
    	Student_Model s=new Student_Model();
    	PrintWriter out=res.getWriter();
    	Marks_Model m=new Marks_Model();
    	if(path.equals("/ReciptionistLogin"))
    	{
    		r.setUsername(req.getParameter("t1"));
    		r.setPassword(req.getParameter("t2"));
    		boolean b=cc.reciptionistLogin(r);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("ReciptionistOptions.html");
         	    rd.forward(req,res);
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("ReciptionistLogin.html");
         	    rd.include(req,res);
         	    out.print("<body><center><h2>Invalid Username/Password</h2></center></body>");
    		}
    	}
    	else if(path.equals("/RecCreatePassword"))
    	{
    		boolean b=false;
    		r.setUsername(req.getParameter("t1"));
    		r.setPassword(req.getParameter("t2"));
    		b=cc.reciptionistCreatePassword(r);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("ReciptionistCreatePassword.html");
         	    rd.include(req,res);
         	   out.print("<body><center><h2>Password Created Click Here For<a href=ReciptionistLogin.html>Login</a></h2></center></body>");
    		}
    	}
    	else if(path.equals("/AddStudents"))
    	{
    		HttpSession session=req.getSession();
    		s.setSid(Integer.parseInt(req.getParameter("t1")));
    		s.setStudentName(req.getParameter("t2"));
    		s.setEmail(req.getParameter("t3"));
    		s.setDob(Integer.parseInt(req.getParameter("t4")));
    		s.setCourse(req.getParameter("t5"));
    		boolean b=cc.reciptionistAddStudents(s);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("ReciptionistOptions.html");
    			rd.forward(req, res);
         	    out.print("<body><center><h2>Student Added Sucessfully</h2></center></body>");
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("ReciptionistOptions.html");
         	    rd.include(req,res);
         	    out.print("<body><center><h2>Adding Student Failed!!<br>Try Again</h2></center></body>");
    		}
    	}
    }
}
