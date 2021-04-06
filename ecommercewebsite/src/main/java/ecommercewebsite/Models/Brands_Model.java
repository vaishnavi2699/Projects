package ecommercewebsite.Models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class Brands_Model 
{
   @Id
   private String brand;

public String getBrand() {
	return brand;
}

public void setBrand(String brand) {
	this.brand = brand;
}
}
