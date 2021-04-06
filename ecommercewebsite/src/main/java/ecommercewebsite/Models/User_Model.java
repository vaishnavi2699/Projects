package ecommercewebsite.Models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class User_Model 
{
	@Id
   public String username;
   public String fullname,email,password,userimage,dob,gender;
   public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public Long mobile;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getUserimage() {
	return userimage;
}
public void setUserimage(String userimage) {
	this.userimage = userimage;
}
public String getDob() {
	return dob;
}
public void setDob(String dob) {
	this.dob = dob;
}
public Long getMobile() {
	return mobile;
}
public void setMobile(Long mobile) {
	this.mobile = mobile;
}
   
}
