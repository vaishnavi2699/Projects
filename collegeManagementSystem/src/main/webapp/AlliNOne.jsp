<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="college_Models.Marks_Model"%>
    <%@page import="college_Models.Student_Model"%>
    <%@page import="college_Models.Course_Model"%>
    <%@page import="college_Models.Fee_Remainder_Model"%>
    <%@page import="college_Models.Lecturer_Model"%>
    <%@page import="college_Models.Recption_Model"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<!-- LecturerMyStudentsMarks -->
<%
ArrayList<Marks_Model> al=new ArrayList<Marks_Model>();
al=(ArrayList<Marks_Model>)request.getAttribute("mystudentsmarks");
if(al!=null)
{
	out.print("<head><title>Marks Of Students</title></head>");
    out.print("<body bgcolor=#3d5a80>");
    out.print("<center>");
    out.print("<br><bri><br><br><br><br><br>");
    out.print("<table border=1 cellpadding=7 bgcolor=#f2e9e4><tr bgcolor=#ffe066><td>Student ID</td><td>Student Name</td><td>Course Name</td><td>Internal Marks</td><td>External Marks</td><td>Semester Marks</td><td>Total Marks</td><td>Grade</td></tr>");
    for(Marks_Model m1: al)
    {
	    out.print("<tr><td>"+m1.getSid()+"</td><td>"+m1.getStudentname()+"</td><td>"+m1.getCourse()+"</td><td>"+m1.getInternalmarks()+"</td><td>"+m1.getExternalmarks()+"</td><td>"+m1.getSemestermarks()+"</td><td>"+m1.getTotalmarks()+"</td><td>"+m1.getGrade()+"</td></tr>");
    }
    out.print("<center><h3>Click Here to <a href=LecturerOptions.html>Go Back</a></h3></center>");
}
%>


<!-- LecturerTotalMarks -->
<%
ArrayList<Marks_Model> al1=new ArrayList<Marks_Model>();
al1=(ArrayList<Marks_Model>)request.getAttribute("totalmarks");
if(al1!=null)
{
	out.print("<head><title>Enter Marks Of Student</title></head>");
    out.print("<body><center><h3>Enter Marks Of Student</h3><center>");
    for(Marks_Model m1: al1)
    {
	   out.print("<center><form action=updateTotalMarks><table border=1 cellpadding=7 bgcolor=#fcbf49><tr><td>Student ID</td><td><input type=text name=t1 readonly value="+m1.getSid()+"></td></tr>");
	   out.print("<tr><td>Student Name</td><td><input type=text name=t2 readonly value="+m1.getStudentname()+"></td>");
	   out.print("<tr><td>Course Name</td><td><input type=text name=t3 readonly value="+m1.getCourse()+"></td>");
	   out.print("<tr><td>Internal Marks</td><td><input type=text name=t4  value="+m1.getInternalmarks()+"></td>");
	   out.print("<tr><td>External Marks</td><td><input type=text name=t5  value="+m1.getExternalmarks()+"></td>");
	   out.print("<tr><td>Semester Marks</td><td><input type=text name=t6  value="+m1.getSemestermarks()+"></td>");
	   out.print("<tr><td><input type=submit value=Submit></td><td><input type=reset value=clear></td></table></form></center></body>");
    }
}
%>

<!-- LecturerViewStudents -->
<%
ArrayList<Student_Model> al2=new ArrayList<Student_Model>();
al2=(ArrayList<Student_Model>)request.getAttribute("mystudents");
if(al2!=null)
{
	out.print("<head><title>List Of Students</title></head>");
	out.print("<body bgcolor=#ffe066><br><br><br><br><br><br><br><center>");
	out.print("<table border=1 cellpadding=7 bgcolor=#edeff2><tr bgcolor=#f25f5c><td>Student ID</td><td>Student Name</td><td>Email ID</td><td>DOB</td><td>Course</td><td>Enter Marks</td></tr>");
    for(Student_Model s1: al2)
    {
	    out.print("<tr><td>"+s1.getSid()+"</td><td>"+s1.getStudentName()+"</td><td>"+s1.getEmail()+"</td><td>"+s1.getDob()+"</td><td>"+s1.getCourse()+"</td><td><a href=totalmarks?tm="+s1.getSid()+">Enter Marks</a></td></tr>");
    }
    out.print("<body><center><h2>Click Here To<a href=LecturerOptions.html>Go Back</a></h2>");
}
%>


<!-- ReciptionistViewAllStudents -->
<%
ArrayList<Student_Model> al3=new ArrayList<Student_Model>();
al3=(ArrayList<Student_Model>)request.getAttribute("reciptionistviewallstudents");
if(al3!=null)
{
	out.print("<head><title>List Of Students</title></head>");
	out.print("<body bgcolor=#edeff2><br><br><br><br><br><br><br><center>");
	out.print("<table border=1 cellpading=7 bgcolor=#fdfffc><tr bgcolor=#ff1654><td>Student ID</td><td>Student Name</td><td>Email ID</td><td>DOB</td><td>Course</td><td>Fee</td><td>Paid</td><td>Due</td><td>Fee Payment Remainder</td></tr>");
    for(Student_Model s1: al3)
    {
	   out.print("<tr><td>"+s1.getSid()+"</td><td>"+s1.getStudentName()+"</td><td>"+s1.getEmail()+"</td><td>"+s1.getDob()+"</td><td>"+s1.getCourse()+"</td><td>"+s1.getFee()+"</td><td>"+s1.getPaid()+"</td><td>"+s1.getDue()+"</td><td><a href=feeremainder?id="+s1.getSid()+">Remaind Student</a></td></tr>");
    }
    out.print("<body><center><h2>Click Here To<a href=ReciptionistOptions.html>Go Back</a></h2></center>");
}
%>


<!-- ReciptionistViewCourses -->
<%
ArrayList<Course_Model> al4=new ArrayList<Course_Model>();
al4=(ArrayList<Course_Model>)request.getAttribute("reciptionistviewcources");
if(al4!=null)
{
	out.print("<head><title>List Of Cources</title></head>");
	out.print("<body  bgcolor=#fcbf49><br><br><br><br><br><br><br><br>");
	out.print("<center><table border=1 cellpadding=7><tr><td>Course Name</td><td>View Students</td></tr>");
for(Course_Model c1: al4)
{
	out.print("<tr><td>"+c1.getCoursename()+"</td><td><a href=viewstudentsbycourse?id="+c1.getCoursename()+">View Students</a></td></tr>");
}
out.print("<body><center>Click Here To<a href=ReciptionistOptions.html>Go Back</a></center>");
}
%>

<!-- Reciptionist View Students By Course-->
<%
ArrayList<Student_Model> al5=new ArrayList<Student_Model>();
al5=(ArrayList<Student_Model>)request.getAttribute("reciptionistviewstudentsbycourse");
if(al5!=null)
{
	out.print("<head><title>List OF Students By Course</title></head>");
	out.print("<body bgcolor=#eff7f6><br><br><br><br><br><br><br><center>");
	out.print("<table border=1 cellpading=7 bgcolor=#ffe066><tr  bgcolor=#ffffff><td>Student ID</td><td>Student Name</td><td>Email ID</td><td>DOB</td><td>Course</td></tr>");
for(Student_Model s1: al5)
{
	out.print("<tr><td>"+s1.getSid()+"</td><td>"+s1.getStudentName()+"</td><td>"+s1.getEmail()+"</td><td>"+s1.getDob()+"</td><td>"+s1.getCourse()+"</td></tr>");
}
out.print("<body><center><h2>Click Here To<a href=ReciptionistOptions.html>Go Back</a></h2></center></body>");
}
%>

<!-- Student Fee Remainder -->
<%
ArrayList<Fee_Remainder_Model> al6=new ArrayList<Fee_Remainder_Model>();
al6=(ArrayList<Fee_Remainder_Model>)request.getAttribute("myfeeremainder");
if(al6!=null)
{
if(al6.size()!=0)
{
	out.print("<head><title>Fee Remainder</title></head><body bgcolor=#edeff2><br><br><br><br><br><br><br><center>");
	out.print("<table border=1 cellpading=7 bgcolor=#e9c46a><tr bgcolor=#edeff2><td>Student ID</td><td>Student Name</td><td>Course</td><td>Fee</td><td>Paid</td><td>Due</td><td>Pay Fee</td></tr>");
    for(Fee_Remainder_Model f1: al6)
    {
    	out.print("<tr><td>"+f1.getSid()+"</td><td>"+f1.getStudentname()+"</td><td>"+f1.getCourse()+"</td><td>"+f1.getFee()+"</td><td>"+f1.getPaid()+"</td><td>"+f1.getDue()+"</td><td><a href=feepayment?id="+f1.getSid()+">Pay Due Amount</a></td></tr>");
    }
    out.print("<body><center><h3>Click Here To<a href=StudentOptions.html>Go Back</a></h3></center></body>");
}
else
{
   	out.print("<body><center>No Fee Remainders!! <br>Click Here To<a href=StudentOptions.html>Go Back</a></center></body>");
}
}
%>


<!-- Student Fee Structure -->
<%
ArrayList<Student_Model>  al7=new ArrayList<Student_Model>();
al7=(ArrayList<Student_Model>)request.getAttribute("myfeestructure");
if(al7!=null)
{
	out.print("<head><title>My Fee Student Structure</title></head>");
	out.print("<body bgcolor=#f4a261><br><br><br><br><br><br><br><br>");
	out.print("<center><table border=1 cellpading=7 bgcolor=#ffe066><tr  bgcolor=#fdfcdc><td>Student ID</td><td>Student Name</td><td>Email ID</td><td>DOB</td><td>Course</td><td>Fee</td><td>Paid</td><td>Due</td></tr>");
for(Student_Model s1: al7)
{
	out.print("<tr><td>"+s1.getSid()+"</td><td>"+s1.getStudentName()+"</td><td>"+s1.getEmail()+"</td><td>"+s1.getDob()+"</td><td>"+s1.getCourse()+"</td><td>"+s1.getFee()+"</td><td>"+s1.getPaid()+"</td><td>"+s1.getDue()+"</td></tr>");
}
out.print("<body><center><h2>Click Here To<a href=StudentOptions.html>Go Back</a></h2></center></body>");
}
%>


<!-- Student Result -->
<%
ArrayList<Marks_Model> al8=new ArrayList<Marks_Model>();
al8=(ArrayList<Marks_Model>)request.getAttribute("mymarks");
if(al8!=null)
{
	out.print("<head><title>My Marks</title></head>");
	out.print("<body bgcolor=#bee3db><center><br><br><br><br><br><br><br>");
	out.print("<table border=1 cellpadding=7 bgcolor=#e8ddb5><tr bgcolor=white><td>Student ID</td><td>Student Name</td><td>Course Name</td><td>Internal Marks</td><td>External Marks</td><td>Semester Marks</td><td>Total Marks</td><td>Grade</td></tr>");
if(al8.size()!=0)
{
for(Marks_Model m: al8)
{
	out.print("<tr><td>"+m.getSid()+"</td><td>"+m.getStudentname()+"</td><td>"+m.getCourse()+"</td><td>"+m.getInternalmarks()+"</td><td>"+m.getExternalmarks()+"</td><td>"+m.getSemestermarks()+"</td><td>"+m.getTotalmarks()+"</td><td>"+m.getGrade()+"</td></tr>");
}
out.print("<center><h3>Click Here to <a href=StudentOptions.html>Go Back</a></h3></center>");
}
else
{
	out.print("<body><center>Result Not Yet Released!!<br>Click Here To<a href=StudentOptions.html>Go Back</a></center></body>");
}
}
%>


<!-- Student Payment Form -->
<%
ArrayList<Fee_Remainder_Model> al9=new ArrayList<Fee_Remainder_Model>();
al9=(ArrayList<Fee_Remainder_Model>)request.getAttribute("paymentform");
if(al9!=null)
{
	out.print("<head><title>Payment Form</title></head>");
	out.print("<body bgcolor=#ffe066><center><form action=payment><br><br><br><br><br><br><br>");
	out.print("<h3>PAYMENT FORM</h3>");
	out.print("<table border=1 cellpading=7 bgcolor=#edf2f4>");
for(Fee_Remainder_Model f1: al9)
{

	out.print("<tr><td>Sid:</td><td><input type=text name=t1 readonly value="+f1.getSid()+"></td></tr>");
	out.print("<tr><td>Fee:</td><td><input type=text name=t2 readonly value="+f1.getFee()+"></td></tr>");
	out.print("<tr><td>Paid:</td><td><input type=text name=t3 readonly value="+f1.getPaid()+"></td></tr>");
	out.print("<tr><td>Due:</td><td><input type=text name=t4  value="+f1.getDue()+"></td></tr>");
	out.print("<tr><td>Pay:</td><td><input type=text name=t5></td></tr>");
	out.print("<tr><td><input type=submit value=Pay Fee></td><td><input type=reset value=Clear></td></tr>");
}
}
%>

<!-- Principal View All students -->
<%
ArrayList<Student_Model>  al10=new ArrayList<Student_Model>();
al10=(ArrayList<Student_Model>)request.getAttribute("students");
if(al10!=null)
{
	out.print("<head><title>List Of Students</title></head>");
	out.print("<body bgcolor=#feefdd><br><br><br><br><br><br><br><br><center>");
	out.print("<table border=1 cellpading=7 bgcolor=#eae0d5><tr  bgcolor=#fdca40><td>Student ID</td><td>Student Name</td><td>Email ID</td><td>DOB</td><td>Course</td><td>Fee</td><td>Paid</td><td>Due</td></tr>");
for(Student_Model s1: al10)
{
	out.print("<tr><td>"+s1.getSid()+"</td><td>"+s1.getStudentName()+"</td><td>"+s1.getEmail()+"</td><td>"+s1.getDob()+"</td><td>"+s1.getCourse()+"</td><td>"+s1.getFee()+"</td><td>"+s1.getPaid()+"</td><td>"+s1.getDue()+"</td></tr>");
}
out.print("<body><center><h2>Click Here To<a href=PrincipalOptions.html>Go Back</a></h2></center></body>");
}
%>

<!-- Principal View Lecturers -->
<%
ArrayList<Lecturer_Model> al11=new ArrayList<Lecturer_Model>();
al11=(ArrayList<Lecturer_Model>)request.getAttribute("lecturers");
if(al11!=null)
{
	out.print("<head><title>List Of Lecturers</title></head><body bgcolor=#9ac6c5><br><br><br><br><br><br><br>");
out.print("<center><table border=1 cellpadding=7 bgcolor:yellow><tr><td>Lid</td><td>Lecturer Name</td><td>Username</td><td>Email ID</td><td>Subject</td></tr>");
for(Lecturer_Model l1:al11)
{
	out.print("<tr><td>"+l1.getFullname()+"</td><td>"+l1.getUsername()+"</td><td>"+l1.getEmail()+"<td>"+l1.getSubject()+"</td></tr>");
}
out.print("<body><center><h3>Click Here To<a href=PrincipalOptions.html>Go Back</a></h3></center></body>");
}
%>


<!-- Principal View Reciptionists -->
<%
ArrayList<Recption_Model> al12=new ArrayList<Recption_Model>();
al12=(ArrayList<Recption_Model>)request.getAttribute("reciptionist");
if(al12!=null)
{
	out.print("<head><title>List Of Reciptionist</title></head><body bgcolor=#87b6a7><br><br><br><br><br><br><br><br>");
out.print("<center><table border=1 cellpadding=7 bgcolor:yellow><tr><td>Reciptionist Name</td><td>Username</td><td>Email ID</td></tr>");
for(Recption_Model r1:al12)
{
	out.print("<tr><td>"+r1.getFullname()+"</td><td>"+r1.getUsername()+"</td><td>"+r1.getEmail()+"</td></tr>");
}
out.print("<body><center><h3>Click Here To<a href=PrincipalOptions.html>Go Back</a></h3></center></body>");
}
%>

<!-- Principal View Cources -->
<%
ArrayList<Course_Model> al13=new ArrayList<Course_Model>();
al13=(ArrayList<Course_Model>)request.getAttribute("cources");
if(al13!=null)
{
	out.print("<head><title>List Of Cources</title></head><body bgcolor=#c6ac8f><center><br><br><br><br><br><br><br>");
	out.print("<table border=1 cellpadding=7 bgcolor:yellow><tr><td>Course Name</td><td>Course Fee</td></tr>");
for(Course_Model c1: al13)
{
	out.print("<tr><td>"+c1.getCoursename()+"</td><td>"+c1.getCoursefee()+"</td></tr>");
}
out.print("<body><center><h3>Click Here To<a href=PrincipalOptions.html>Go Back</a></h3></center></body>");
}
%>
</html>