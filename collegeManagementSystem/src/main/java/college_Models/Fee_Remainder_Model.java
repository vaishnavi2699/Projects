package college_Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fee_Remainder_Model 
{
	@Id
    private int sid;
    private int fee,paid,due,pay;
    public int getPay() 
    {
		return pay;
	}
	public void setPay(int pay) 
	{
		this.pay = pay;
	}
	private String studentname,course;
	public int getSid() 
	{
		return sid;
	}
	public void setSid(int sid) 
	{
		this.sid = sid;
	}
	public int getFee() 
	{
		return fee;
	}
	public void setFee(int fee) 
	{
		this.fee = fee;
	}
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
	public String getStudentname() 
	{
		return studentname;
	}
	public void setStudentname(String studentname) 
	{
		this.studentname = studentname;
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
