<!DOCTYPE html>
<%@page import="college_Models.Course_Model"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Students</title>
<link rel="stylesheet" href="AddReciptionistRelated.css">
<script type="text/javascript">
function add()
{
   var id=document.addlecturer.t1.value;
   var fullname=document.addlecturer.t2.value;
   var dob=document.addlecturer.t4.value;
   var email=document.addlecturer.t3.value;
   var course=document.addlecturer.t5.value;
   if(id=="")
   {
      alert("Please Enter ID:")
      document.addlecturer.t1.focus()
      return false;
   }
   if(fullname=="")
   {
      alert("Please Enter fullname:")
      document.addlecturer.t2.focus()
      return false;
   }
   if(dob=="")
   {
      alert("Please Enter Date Of Birth:")
      document.addlecturer.t3.focus()
      return false;
   }
   if(email=="")
   {
      alert("Please Enter email:")
      document.addlecturer.t4.focus()
      return false;
   }
   if(course=="")
   {
      alert("Please Enter Course:")
      document.addlecturer.t5.focus()
      return false;
   }
}
</script>
</head>
<body>
<div class="center">
<h1>Add Student</h1>
<form action="AddStudents" method="post">

<div class="txt_field">
<input type="text" name="t1">
<span></span>
<label>Student ID</label>
</div>

<div class="txt_field">
<input type="text" name="t2">
<span></span>
<label>Student Name</label>
</div>

<div class="txt_field">
<input type="text" name="t3">
<span></span>
<label>Email ID</label>
</div>

<div class="txt_field">
<input type="text" name="t4">
<span></span>
<label>DOB</label>
</div>

<div class="txt_field">
<span></span>
<select name="t5">
<%
ArrayList<Course_Model> al=new ArrayList<Course_Model>();
al=(ArrayList<Course_Model>)request.getAttribute("cources");
out.print("<option>Select Course</option>");
for(Course_Model c: al)
{
	out.print("<option>"+c.getCoursename()+"</option>");
}
%>
</select>
</div>

<input type="submit" value="Add Student">
</form>
</div>
</body>
</html>