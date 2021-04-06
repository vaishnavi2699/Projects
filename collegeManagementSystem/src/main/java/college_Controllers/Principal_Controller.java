package college_Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import college_Models.Lecturer_Model;
import college_Models.Principal_Model;
import college_Models.Recption_Model;
import college_Models.Student_Model;

@WebServlet(urlPatterns = {"/PLoginForm","/Principallogin","/addlecturers","/AddLecturer","/addreciptions","/AddRecptionist","/addcourse","/AddCourse","/viewlecturers","/viewreciptions","/viewcources","/ViewStudents","/principallogout"})
public class Principal_Controller extends HttpServlet
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
    	Recption_Model r=new Recption_Model();
    	Course_Model c=new Course_Model();
    	if(path.equals("/PLoginForm"))
    	{
    	   RequestDispatcher rd=req.getRequestDispatcher("PrincipalLogin.html");
    	   rd.forward(req, res);
    	}
    	else if(path.equals("/addlecturers"))
    	{
    		    RequestDispatcher rd=req.getRequestDispatcher("AddLecturers.html");
    		    rd.forward(req, res);
    	}
    	else if(path.equals("/addreciptions"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("AddReciptionist.html");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/addcourse"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("AddCourse.html");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/viewlecturers"))
    	{
    		ArrayList<Lecturer_Model> al=new ArrayList<Lecturer_Model>();
    		al=cc.viewlecturers();
    		req.setAttribute("lecturers",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/viewreciptions"))
    	{
    		ArrayList<Recption_Model> al=new ArrayList<Recption_Model>();
    		al=cc.viewRecptionist();
    		req.setAttribute("reciptionist",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/viewcources"))
    	{
    		ArrayList<Course_Model> al=new ArrayList<Course_Model>();
    		al=cc.viewCources();
    		req.setAttribute("cources",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/ViewStudents"))
    	{
    		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
    		al=cc.viewAllStudents();
    		req.setAttribute("students",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/principallogout"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("index.html");
    		rd.forward(req, res);
    	}
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	PrintWriter out=res.getWriter();
    	String path=req.getServletPath();
    	Principal_Model p=new Principal_Model();
    	Lecturer_Model l=new Lecturer_Model();
    	Recption_Model r=new Recption_Model();
    	Course_Model c=new Course_Model();
    	if(path.equals("/Principallogin"))
    	{
    		boolean b=false;
    		p.setUsername(req.getParameter("t1"));
    		p.setPassword(req.getParameter("t2"));
    		b=cc.principalLogin(p);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("PrincipalOptions.html");
    			rd.forward(req, res);
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("PrincipalLogin.html");
    			rd.include(req, res);
    			out.print("<body><center><h2>Invalid Username/Password</h2></center></body>");
    		}
    	}
    	else if(path.equals("/AddLecturer"))
    	{
    		boolean b=false;
    		l.setFullname(req.getParameter("t2"));
    		l.setUsername(req.getParameter("t3"));
    		l.setEmail(req.getParameter("t4"));
    		l.setSubject(req.getParameter("t5"));
    		b=cc.addLecturer(l);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddLecturers.html");
    			rd.include(req, res);
    			out.print("<body><center><h2>Lecturer Added Sucessfully Click here to <a href=PrincipalOptions.html>Go Back</a></h2></center></body>");
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddLecturers.html");
    			rd.include(req, res);
    			out.print("<body><center><h2> Adding Lecturer Failed</h2></center></body>");
    		}
    	}
    	else if(path.equals("/AddRecptionist"))
    	{
    		boolean b=false;
    		r.setFullname(req.getParameter("t2"));
    		r.setUsername(req.getParameter("t3"));
    		r.setEmail(req.getParameter("t4"));
    		b=cc.addRecptionist(r);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddReciptionist.html");
    			rd.include(req, res);
    			out.print("<body><center><h2>Reciptionist Added Sucessfully Click here to <a href=PrincipalOptions.html>Go Back</a></h2></center></body>");
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddReciptionist.html");
    			rd.include(req, res);
    			out.print("<body><center><h2> Adding Recpitionist Failed</h2></center></body>");
    		}
    	}
    	else if(path.equals("/AddCourse"))
    	{
    		boolean b=false;    		
    		c.setCoursename(req.getParameter("t2"));
    		c.setCoursefee(Integer.parseInt(req.getParameter("t3")));
    		b=cc.addCourse(c);
    		if(b)
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddCourse.html");
    			rd.include(req, res);
    			out.print("<body><center><h2>Course Added Sucessfully Click here to <a href=PrincipalOptions.html>Go Back</a></h2></center></body>");
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("AddCourse.html");
    			rd.include(req, res);
    			out.print("<body><center><h2>Adding Course Failed Click here to <a href=PrincipalOptions.html>Go Back</a></h2></center></body>");
    		
    		}
    	}
    }
}
