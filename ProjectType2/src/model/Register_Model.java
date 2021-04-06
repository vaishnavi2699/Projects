package model;

public class Register_Model 
{
   private String fullname,username,email,pwd;
   
   public String getFullName()
   {
	   return fullname;
   }
   public void setFullName(String fullname)
   {
	   this.fullname=fullname;
   }
   
   public String getUserName()
   {
	   return username;
   }
   public void setUserName(String username)
   {
	   this.username=username;
   }
   
   public String getEmail()
   {
	   return email;
   }
   public void setEmail(String email)
   {
	   this.email=email;
   }
   
   public String getPassword()
   {
	   return pwd;
   }
   public void setPassword(String pwd)
   {
	   this.pwd=pwd;
   }
}
