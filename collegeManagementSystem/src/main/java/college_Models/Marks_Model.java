package college_Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Marks_Model 
{
	@Id
   private int sid;
   private int internalmarks,externalmarks,semestermarks,totalmarks;
   private String studentname,course,grade;
public String getGrade() 
{
	return grade;
}
public void setGrade(String grade) 
{
	this.grade = grade;
}
public int getSid() 
{
	return sid;
}
public String getCourse() 
{
	return course;
}
public void setCourse(String course) 
{
	this.course = course;
}
public void setSid(int sid) 
{
	this.sid = sid;
}
public int getInternalmarks() 
{
	return internalmarks;
}
public void setInternalmarks(int internalmarks) 
{
	this.internalmarks = internalmarks;
}
public int getExternalmarks() 
{
	return externalmarks;
}
public void setExternalmarks(int externalmarks) 
{
	this.externalmarks = externalmarks;
}
public int getSemestermarks() 
{
	return semestermarks;
}
public void setSemestermarks(int semestermarks) 
{
	this.semestermarks = semestermarks;
}
public int getTotalmarks() 
{
	return totalmarks;
}
public void setTotalmarks(int totalmarks) 
{
	this.totalmarks = totalmarks;
}
public String getStudentname() 
{
	return studentname;
}
public void setStudentname(String studentname) 
{
	this.studentname = studentname;
}
   
}
