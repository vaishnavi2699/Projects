package ecommercewebsite.Models;

import java.util.ArrayList;

public class Filter_Model 
{
   private ArrayList<String> catagories;
   private ArrayList<String> brands;
   private int price;
   
   
public ArrayList<String> getCatagories() {
	return catagories;
}
public void setCatagories(ArrayList<String> al) {
	this.catagories = al;
}
public ArrayList<String> getBrands() {
	return brands;
}
public void setBrands(ArrayList<String> brands) {
	this.brands = brands;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}

   
}
