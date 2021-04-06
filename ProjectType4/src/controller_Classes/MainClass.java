package controller_Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import controller_Classes.MainClass;
import projectModel.Model;

public class MainClass 
{
	static Connection con=null;
	static Model m=null;
	public static Connection getConnectionObject()
	{
		try {
			Class.forName("org.h2.Driver");
			con=DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test","karthik","Karthik@04");
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return con;
	}
	public boolean login(String username,String password)// 
	{
		boolean b=false;
		ResultSet rs=null;
		con=MainClass.getConnectionObject();
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery("select username,password from ptype3 where username='"+username+"' and password='"+password+"'");
			if(rs.next())
			{
				b=true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
	public boolean register(String fullname,String username,String email,String password)
	{
		boolean b=false;
		int i=0;
		con=MainClass.getConnectionObject();
		try
		{
			PreparedStatement pst=con.prepareStatement("insert into ptype3 values(?,?,?,?)");
			pst.setString(1,fullname);
			pst.setString(2,username);
			pst.setString(3,email);
			pst.setString(4,password);
			i=pst.executeUpdate();
			if(i>0)
			{
				b=true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
	public ResultSet ViewAll()
	{
		ResultSet rs=null;
		con=MainClass.getConnectionObject();
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery("select * from ptype3");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	public ResultSet update(String fullname)
	{
		ResultSet rs=null;
		con=MainClass.getConnectionObject();
		try
		{
			Statement st=con.createStatement();
			rs=st.executeQuery("select * from ptype2 where fullname='"+fullname+"'");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return rs;
	}
	public boolean updateDetails(String fullname,String username,String email)
	{
		boolean b=false;
		ResultSet rs=null;
		con=MainClass.getConnectionObject();
		int i=0;
		try
		{
			Statement st=con.createStatement();
			i=st.executeUpdate("update ptype3 set username='"+username+"',email='"+email+"' where fullname='"+fullname+"'");
			if(i>0)
			{
				b=true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
	public boolean delete(String fullname)
	{
		boolean b=false;
		con=MainClass.getConnectionObject();
		int i=0;
		try
		{
			Statement st=con.createStatement();
			i=st.executeUpdate("delete from ptype3 where fullname='"+fullname+"'");
			if(i>0)
			{
				b=true;
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return b;
	}
}
