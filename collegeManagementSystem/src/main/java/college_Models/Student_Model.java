package college_Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student_Model
{
	@Id
    private int sid;
    private int dob,fee,paid,due;
    public int getPaid()
    {
		return paid;
	}
	public void setPaid(int paid) 
	{
		this.paid = paid;
	}
	public int getDue() 
	{
		return due;
	}
	public void setDue(int due) 
	{
		this.due = due;
	}
	private String studentName,course,email;
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email) 
	{
		this.email = email;
	}
	public int getSid() 
	{
		return sid;
	}
	public void setSid(int sid) 
	{
		this.sid = sid;
	}
	public int getDob() 
	{
		return dob;
	}
	public void setDob(int dob) 
	{
		this.dob = dob;
	}
	public int getFee() 
	{
		return fee;
	}
	public void setFee(int fee) 
	{
		this.fee = fee;
	}
	public String getStudentName() 
	{
		return studentName;
	}
	public void setStudentName(String studentName) 
	{
		this.studentName = studentName;
	}
	public String getCourse() 
	{
		return course;
	}
	public void setCourse(String course) 
	{
		this.course = course;
	}
    
}
