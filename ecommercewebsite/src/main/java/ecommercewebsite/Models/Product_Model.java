package ecommercewebsite.Models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Product_Model 
{
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private int pid;
   private String productname,brand,catagory,image,info,type;
   public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
private int stock,sold,price;
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getProductName() {
	return productname;
}
public void setProductName(String productName) {
	this.productname = productName;
}
public String getBrand() {
	return brand;
}
public void setBrand(String brand) {
	this.brand = brand;
}
public String getCatagory() {
	return catagory;
}
public void setCatagory(String catagory) {
	this.catagory = catagory;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getStock() {
	return stock;
}
public void setStock(int stock) {
	this.stock = stock;
}
public int getSold() {
	return sold;
}
public void setSold(int sold) {
	this.sold = sold;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info;
}
   
}
