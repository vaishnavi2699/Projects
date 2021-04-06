package college_Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course_Model 
{
	@Id
	private String coursename;
    private int coursefee;
	public String getCoursename() 
	{
		return coursename;
	}
	public void setCoursename(String coursename) 
	{
		this.coursename = coursename;
	}
	public int getCoursefee() 
	{
		return coursefee;
	}
	public void setCoursefee(int coursefee) 
	{
		this.coursefee = coursefee;
	}
}
