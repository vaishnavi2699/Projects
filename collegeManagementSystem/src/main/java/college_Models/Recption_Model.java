package college_Models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recption_Model 
{
	@Id
   private String username;
   private String fullname,email,password;


public String getFullname() 
{
	return fullname;
}

public void setFullname(String fullname) 
{
	this.fullname = fullname;
}

public String getUsername() 
{
	return username;
}

public void setUsername(String username) 
{
	this.username = username;
}

public String getEmail() 
{
	return email;
}

public void setEmail(String email) 
{
	this.email = email;
}

public String getPassword() 
{
	return password;
}

public void setPassword(String password) 
{
	this.password = password;
}
   
}
