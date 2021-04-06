package ecommercewebsite.Models;

import java.sql.Date;

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
public class MyOrders_Model 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int moid;
	private int price,size,quantity;
	private Date orderdate;
    private Date deliverydate;
    private String productname,customerusername,shippingaddress,productimage,brand,status,category;
    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getProductimage() {
		return productimage;
	}
	public void setProductimage(String productimage) {
		this.productimage = productimage;
	}
	public String getShippingaddress() {
		return shippingaddress;
	}
	public void setShippingaddress(String shippingaddress) {
		this.shippingaddress = shippingaddress;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
    public String getCustomerusername() {
		return customerusername;
	}
	public void setCustomerusername(String customerusername) {
		this.customerusername = customerusername;
	}
	public Date getDeliverydate() {
		return deliverydate;
	}
	public void setDeliverydate(Date deliverydate) {
		this.deliverydate = deliverydate;
	}
	public int getMoid() {
		return moid;
	}
	public void setMoid(int moid) {
		this.moid = moid;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productName) {
		this.productname = productName;
	}
	public Date getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(Date currentdate) {
		this.orderdate = currentdate;
	}
    
}
