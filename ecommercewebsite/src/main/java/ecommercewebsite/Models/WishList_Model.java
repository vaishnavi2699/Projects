package ecommercewebsite.Models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class WishList_Model 
{
	@Id
	 @GeneratedValue
	 private int cpid;
    public int getCpid() {
		return cpid;
	}
	public void setCpid(int cpid) {
		this.cpid = cpid;
	}
	private int pid;
    private String customerusername,productName,brand,catagory,productimage;
    public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	private int price,size,quantity;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getProductimage() {
		return productimage;
	}
	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}
}
