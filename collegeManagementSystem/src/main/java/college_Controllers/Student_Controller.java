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
import college_Models.Fee_Remainder_Model;
import college_Models.Marks_Model;
import college_Models.Student_Model;

@WebServlet(urlPatterns = {"/StuLoginForm","/StudentLogin","/myfeeremainders","/feepayment","/payment","/checkmymarks","/myfeesturcture","/studentlogout"})
public class Student_Controller extends HttpServlet
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
    	Fee_Remainder_Model f=new Fee_Remainder_Model();
    	Student_Model s=new Student_Model();
    	if(path.equals("/StuLoginForm"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("StudentLogin.html");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/myfeeremainders"))
    	{
    		ArrayList<Fee_Remainder_Model> al=new ArrayList<Fee_Remainder_Model>();
    		HttpSession session=req.getSession();
    		s.setSid(Integer.parseInt(session.getAttribute("id").toString()));
    		al=cc.checkFeeRemainders(s);
    		req.setAttribute("myfeeremainder", al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/feepayment"))
    	{
    		f.setSid(Integer.parseInt(req.getParameter("id")));
    		ArrayList<Fee_Remainder_Model> al=new ArrayList<Fee_Remainder_Model>();
    		al=cc.feePaymentForm(f);
    		req.setAttribute("paymentform", al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/payment"))
    	{
    		f.setSid(Integer.parseInt(req.getParameter("t1")));
    		f.setFee(Integer.parseInt(req.getParameter("t2")));
    		f.setPaid(Integer.parseInt(req.getParameter("t3")));
    		f.setDue(Integer.parseInt(req.getParameter("t4")));
    		f.setPay(Integer.parseInt(req.getParameter("t5")));
    		boolean b=cc.payDueAmount(f);
            if(b)
            {
            	out.print("<body><center>Payment Succssfull Click here to <a href=myfeeremainders>Go Back</a></center></body>");
            }
            else
            {
            	out.print("<body><center>Payment Failed!!<br> Click here to <a href=myfeeremainders>Go Back</a> And Try Again...</center></body>");
            }
    	}
    	else if(path.equals("/checkmymarks"))
    	{
    		ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
    		HttpSession session=req.getSession();
    		s.setSid(Integer.parseInt(session.getAttribute("id").toString()));
    		al=cc.checkMyResult(s);
    		req.setAttribute("mymarks",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/myfeesturcture"))
    	{
    		ArrayList<Student_Model> al=new ArrayList<Student_Model>();
    		HttpSession session=req.getSession();
    		s.setSid(Integer.parseInt(session.getAttribute("id").toString()));
    		al=cc.checkMyFeeStructure(s);
    		req.setAttribute("myfeestructure",al);
    		RequestDispatcher rd=req.getRequestDispatcher("AlliNOne.jsp");
    		rd.forward(req, res);
    	}
    	else if(path.equals("/studentlogout"))
    	{
    		RequestDispatcher rd=req.getRequestDispatcher("index.html");
    		rd.forward(req, res);
    	}
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException, IOException
    {
    	String path=req.getServletPath();
    	Student_Model s=new Student_Model();
    	PrintWriter out=res.getWriter();
    	if(path.equals("/StudentLogin"))
    	{
    		s.setSid(Integer.parseInt(req.getParameter("t1")));
    		s.setDob(Integer.parseInt(req.getParameter("t2")));
    		boolean b=cc.stuedentLogin(s);
    		if(b)
    		{
    			HttpSession session=req.getSession();
    			session.setAttribute("id",s.getSid());
    			RequestDispatcher rd=req.getRequestDispatcher("StudentOptions.html");
        		rd.forward(req, res);
    		}
    		else
    		{
    			RequestDispatcher rd=req.getRequestDispatcher("StudentLogin.html");
        		rd.include(req, res);
        		out.print("<center>Invalid Id/DOB </center>");
    		}
    	}
    }
}
