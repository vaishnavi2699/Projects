package ecommercewebsite.Dao_Class_And_Interface;

import java.sql.Date;
import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.web.servlet.ModelAndView;

import ecommercewebsite.Models.Admin_Model;
import ecommercewebsite.Models.Brands_Model;
import ecommercewebsite.Models.Cart_Model;
import ecommercewebsite.Models.Catagory_Model;
import ecommercewebsite.Models.Filter_Model;
import ecommercewebsite.Models.MyOrders_Model;
import ecommercewebsite.Models.Orders_Model;
import ecommercewebsite.Models.Product_Model;
import ecommercewebsite.Models.User_Model;
import ecommercewebsite.Models.WishList_Model;

public class EDao implements eCommerce_Interface 
{

	SessionFactory sf=null;
	public EDao()
	{
		Configuration cfg=new Configuration().configure().addAnnotatedClass(Admin_Model.class).addAnnotatedClass(Catagory_Model.class).addAnnotatedClass(Cart_Model.class).addAnnotatedClass(Brands_Model.class).addAnnotatedClass(MyOrders_Model.class).addAnnotatedClass(Product_Model.class).addAnnotatedClass(User_Model.class).addAnnotatedClass(WishList_Model.class);
		sf=cfg.buildSessionFactory();
	}
	//Administrator Related Methods
	public boolean adminLogin(Admin_Model a)
	{
		boolean b=false;
		Session s=sf.openSession();
		a=(Admin_Model) s.createQuery("from Admin_Model where username='"+a.getUsername()+"' and password='"+a.getPassword()+"'").uniqueResult();
		if(a!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean changePassword(Admin_Model a) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.update(a);
		t.commit();
		b=true;
		s.close();
		return b;
	}
	public boolean addCatagory(Catagory_Model c)
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj=null;
		try
		{
		obj=s.save(c);
		t.commit();
		if(obj!=null)
		{
			b=true;
		}
		}
		catch (Exception e) 
		{
			ModelAndView mav=new ModelAndView("AdminWelcomepage");
			mav.addObject("msg","Category Already Exists.");
		}
		s.close();
		return b;
	}
	public boolean addBrands(Brands_Model bm)
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj=null;
		try
		{
		obj=s.save(bm);
		t.commit();
		if(obj!=null)
		{
			b=true;
		}
		}
		catch (Exception e)
		{
			ModelAndView mav=new ModelAndView("AdminWelcomepage");
			mav.addObject("msg","Brand Already Exists.");
		}
		
		s.close();
		return b;
	}
	public ArrayList<Catagory_Model> getAllCatagories()
	{
		ArrayList<Catagory_Model> al=new ArrayList<Catagory_Model>();
		Session s=sf.openSession();
		Query q= s.createQuery("from Catagory_Model");
		al=(ArrayList<Catagory_Model>) q.list();
		q.setCacheable(true);
		
		return al;
	}
	public ArrayList<Brands_Model> getAllBrands()
	{
		ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
		Session s=sf.openSession();
		brands=(ArrayList<Brands_Model>)s.createQuery("From Brands_Model").list();
		
		s.close();
		return brands;
	}
	public boolean addProducts(Product_Model p) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj=s.save(p);
		t.commit();
		if(obj!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public ArrayList<Product_Model> filter(Filter_Model fm)
	{
		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
        ArrayList<Product_Model> al1=new ArrayList<Product_Model>();
		Session session=sf.openSession();
		for(String catagory:fm.getCatagories())
		{
			for(String brands:fm.getBrands())
			{
				al=(ArrayList<Product_Model>)session.createQuery("from Product_Model where catagory='"+catagory+"' and brand='"+brands+"' and price<='"+fm.getPrice()+"'").list();
			    al1.addAll(al);
			}
	    }
		session.close();
		return al1;
	}
	public ArrayList<Product_Model> getProductDetails(Product_Model p) 
	{
		Session s=sf.openSession();
		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
		p=(Product_Model) s.get(Product_Model.class,p.getPid());
		al.add(p);
		s.close();
		return al;
	}
	public String updateProductDetails(Product_Model p)
	{
		boolean b=false;
		Product_Model p1=new Product_Model();
		Session ss=sf.openSession();
		p1=(Product_Model)ss.get(Product_Model.class,p.getPid());
		String productImage=p1.getImage();
		if(p.getImage()!="")
		{
		   p1.setImage(p.getImage());
		}
		p1.setInfo(p.getInfo());
		p1.setPrice(p.getPrice());
		p1.setProductName(p.getProductName());
		p1.setStock(p.getStock());
		ss.close();
		if(p1!=null)
		{
		   Session s=sf.openSession();
		   Transaction t=s.beginTransaction();
		   s.update(p1);
		   t.commit();
		   b=true;
		   s.close();
		}
		return productImage;
	}
	public String deleteProduct(Product_Model p)
	{
		boolean b=false;
		Session ss=sf.openSession();
		Product_Model p1=new Product_Model();
		p1=(Product_Model) ss.get(Product_Model.class,p.getPid());
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.delete(p);
		t.commit();		
		s.close();
		return p1.getImage();
	}
	public ArrayList<MyOrders_Model> viewAllOrders()
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>) s.createQuery("from Orders_Model").list();
		return al;
	}
	public ArrayList<Product_Model> getSearchResult(Product_Model p)
	{
		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
		Session s=sf.openSession();
		al=(ArrayList<Product_Model>) s.createQuery("from Product_Model where productname like '%"+p.getProductName()+"%' or brand like '%"+p.getBrand()+"%' or catagory like '%"+p.getCatagory()+"%'").list();
		s.close();
		return al;
	}
	
	
	
	//User Related Methods
	public boolean userLogin(User_Model u) 
	{
		boolean b=false;
		Session s=sf.openSession();
		u=(User_Model) s.createQuery("from User_Model where username='"+u.getUsername()+"' and password='"+u.getPassword()+"'").uniqueResult();
		if(u!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean userRegister(User_Model u) 
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		Object obj=s.save(u);
		t.commit();
		if(obj!=null)
		{
			b=true;
		}
		s.close();
		return b;
	}
	public boolean userResetPassword(User_Model u) 
	{
		boolean b=false;
		Session s=sf.openSession();
		User_Model u1=new User_Model();
		u1=(User_Model) s.get(User_Model.class,u.getUsername());
		u1.setPassword(u.getPassword());
		s.close();
		if(u1!=null)
		{
			Session ss=sf.openSession();
			Transaction t=ss.beginTransaction();
			ss.update(u1);
			t.commit();
			b=true;
			ss.close();
		}
		return b;
	}
	public ArrayList<Product_Model> sortedSearchResult(Product_Model p, String sortingType) 
	{
		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
		Session s=sf.openSession();
		if(sortingType.equals("lowtohigh"))
		{
		   al=(ArrayList<Product_Model>) s.createQuery("from Product_Model where productName like '%"+p.getProductName()+"%' or brand like '%"+p.getBrand()+"%' or catagory like '%"+p.getCatagory()+"%' order by price").list();
		}
		else
		{
		   al=(ArrayList<Product_Model>) s.createQuery("from Product_Model where productName like '%"+p.getProductName()+"%' or brand like '%"+p.getBrand()+"%' or catagory like '%"+p.getCatagory()+"%' order by price desc").list();
		}
		s.close();
		return al;
	}
	public ArrayList<User_Model> getCustomerDetails(User_Model um)
	{
		ArrayList<User_Model> al=new ArrayList<User_Model>();
		Session s=sf.openSession();
		um=(User_Model) s.get(User_Model.class,um.getUsername());
		al.add(um);
		s.close();
		return al;
	}
	public boolean updateCustomertDetails(User_Model um)
	{
		boolean b=false;
		if(um.getUserimage()=="")
		{
			Session s=sf.openSession();
			User_Model um1=new User_Model();
			um1=(User_Model) s.get(User_Model.class,um.getUsername());
			um.setUserimage(um1.getUserimage());
			s.close();
		}
		Session ss=sf.openSession();
	    Transaction t=ss.beginTransaction();
   	    ss.update(um);
	    t.commit();
        b=true;
		ss.close();		
		return b;
	}
	public boolean addProductToCart(Cart_Model c)
	{
		boolean b=false;
		Session si=sf.openSession();
		Product_Model p=new Product_Model();
		p.setPid(c.getPid());
		p=(Product_Model)si.get(Product_Model.class,p.getPid());
		c.setProductimage(p.getImage());
		si.close();
		
		ArrayList<Cart_Model> al2=new ArrayList<Cart_Model>();
		Session session=sf.openSession();
		al2=(ArrayList<Cart_Model>)session.createQuery("from Cart_Model where pid='"+c.getPid()+"'  and customerusername='"+c.getCustomerusername()+"'").list();
		session.close();
		//if product does'nt exists in cart
		if(al2.isEmpty())
		{
			Session s=sf.openSession();
			Transaction t=s.beginTransaction();
			Object obj=s.save(c);
			t.commit();
			if(obj!=null)
			{
				b=true;
			}
			s.close();
		}		
		else
		{   //if product exists and if customer added same product with same size.
			Session ses=sf.openSession();
			Cart_Model cm=new Cart_Model();
			cm=(Cart_Model) ses.createQuery("from Cart_Model where pid='"+c.getPid()+"' and size='"+c.getSize()+"' and customerusername='"+c.getCustomerusername()+"'").uniqueResult();
			ses.close();
			if(cm!=null)
			{
				        c.setCpid(cm.getCpid());
						c.setPrice(c.getPrice()+cm.getPrice());
						c.setQuantity(c.getQuantity()+cm.getQuantity());
						Session sess=sf.openSession();
						Transaction tr=sess.beginTransaction();
						sess.update(c);
						tr.commit();
						b=true;
						sess.close();
			}
			else
			{   //if product exists and if customer added same product with diff size.
			   			Session ss=sf.openSession();
			   			Transaction tx=ss.beginTransaction();
			   			Object obj=ss.save(c);
			   			tx.commit();
						if(obj!=null)
						{
							b=true;
						}
						ss.close();
			   		
			}
		}
		return b;
	}
	public ArrayList<Cart_Model> getAllProductsFromCart(Cart_Model c)
	{
		ArrayList<Cart_Model> al=new ArrayList<Cart_Model>();
		Session s=sf.openSession();
		al=(ArrayList<Cart_Model>) s.createQuery("from Cart_Model where customerusername='"+c.getCustomerusername()+"'").list();
		s.close();
		return al;
		
	}
	public Cart_Model getProductDetailsFromCart(Cart_Model c)
	{
		Session s=sf.openSession();
		ArrayList<Cart_Model> al=new ArrayList<Cart_Model>();
		c=(Cart_Model) s.createQuery("from Cart_Model where customerusername='"+c.getCustomerusername()+"' and cpid='"+c.getCpid()+"'").uniqueResult();
		s.close();
		return c;
		
	}
	public boolean updateProductDetailsInCart(Cart_Model c)
	{
		boolean b=false;
		Session ss=sf.openSession();
		ArrayList<Cart_Model> al=new ArrayList<Cart_Model>();
		al=(ArrayList<Cart_Model>)ss.createQuery("from Cart_Model where customerusername='"+c.getCustomerusername()+"' and cpid='"+c.getCpid()+"'").list();
        ss.close();
		for(Cart_Model c1:al)
		{
			Session ses=sf.openSession();
			Product_Model p=new Product_Model();
			p.setPid(c1.getPid());
			p=(Product_Model)ses.get(Product_Model.class,p.getPid());
			ses.close();
			if(p!=null)
			{
				c1.setQuantity(c.getQuantity());
				c1.setPrice(c1.getQuantity()*p.getPrice());
				c1.setSize(c.getSize());
				c1.setCustomerusername(c.getCustomerusername());
				Session s=sf.openSession();
				Transaction t=s.beginTransaction();
				s.update(c1);
				t.commit();
				s.close();
				b=true;  
			}
		}
		return b;
	}
	public boolean deleteProductFromCart(Cart_Model c)
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.delete(c);
		t.commit();
		b=true;
		return b;
	}
	public boolean clearAllProductInCart(Cart_Model c)
	{
		boolean b=false;
		Session s=sf.openSession();
		Query q=s.createQuery("delete from Cart_Model where customerusername='"+c.getCustomerusername()+"'");
		q.executeUpdate();
		s.close();
		if(q!=null)
		{
			b=true;
		}
		return b;
	}
	public boolean addProductToWishList(WishList_Model w)
	{
		boolean b=false;
		Session si=sf.openSession();
		Product_Model p=new Product_Model();
		p.setPid(w.getPid());
		p=(Product_Model)si.get(Product_Model.class,p.getPid());
		w.setProductimage(p.getImage());
		si.close();
		
		ArrayList<Cart_Model> al2=new ArrayList<Cart_Model>();
		Session session=sf.openSession();
		al2=(ArrayList<Cart_Model>)session.createQuery("from WishList_Model where pid='"+w.getPid()+"' and customerusername='"+w.getCustomerusername()+"'").list();
		session.close();
		//if product does'nt exists in wishlist
		if(al2.isEmpty())
		{
			Session s=sf.openSession();
			Transaction t=s.beginTransaction();
			Object obj=s.save(w);
			t.commit();
			if(obj!=null)
			{
				b=true;
			}
			s.close();
		}		
		else
		{   //if product exists and if customer added same product with same size.
			Session ses=sf.openSession();
			WishList_Model wl=new WishList_Model();
			wl=(WishList_Model) ses.createQuery("from WishList_Model where pid='"+w.getPid()+"' and size='"+w.getSize()+"' and customerusername='"+w.getCustomerusername()+"'").uniqueResult();
			ses.close();
			if(wl!=null)
			{
				        w.setCpid(wl.getCpid());
						w.setPrice(w.getPrice()+wl.getPrice());
						w.setQuantity(w.getQuantity()+wl.getQuantity());
						Session sess=sf.openSession();
						Transaction tr=sess.beginTransaction();
						sess.update(w);
						tr.commit();
						b=true;
						sess.close();
			}
			else
			{   //if product exists and if customer added same product with diff size.
			   			Session ss=sf.openSession();
			   			Transaction tx=ss.beginTransaction();
			   			Object obj=ss.save(w);
			   			tx.commit();
						if(obj!=null)
						{
							b=true;
						}
						ss.close();
			   		
			}
		}
		return b;
	}
	public ArrayList<WishList_Model> getAllProductsFromWishList(WishList_Model w)
	{
		ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
		Session s=sf.openSession();
		al=(ArrayList<WishList_Model>) s.createQuery("from WishList_Model where customerusername='"+w.getCustomerusername()+"'").list();
		s.close();
		return al;
	}
	public WishList_Model getProductDetailsFromWishlist(WishList_Model w)
	{
		Session s=sf.openSession();
		ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
		w=(WishList_Model) s.createQuery("from WishList_Model where customerusername='"+w.getCustomerusername()+"' and cpid='"+w.getCpid()+"'").uniqueResult();
		s.close();
		return w;
	}
	public boolean updateProductDetailsInWishlist(WishList_Model w)
	{
		boolean b=false;
		Session ss=sf.openSession();
		ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
		al=(ArrayList<WishList_Model>)ss.createQuery("from WishList_Model where customerusername='"+w.getCustomerusername()+"' and cpid='"+w.getCpid()+"'").list();
		ss.close();
		for(WishList_Model wl:al)
		{
			Session ses=sf.openSession();
			Product_Model p=new Product_Model();
			p.setPid(wl.getPid());
			p=(Product_Model)ses.get(Product_Model.class,p.getPid());
			if(p!=null)
			{
				wl.setQuantity(w.getQuantity());
				wl.setPrice(wl.getQuantity()*p.getPrice());
				wl.setSize(w.getSize());
				wl.setCustomerusername(w.getCustomerusername());
				Session s=sf.openSession();
				Transaction t=s.beginTransaction();
				s.update(wl);
				t.commit();
				s.close();
				b=true;  
			}
		}
		return b;
	}
	public boolean deleteProductFromWishlist(WishList_Model w)
	{
		boolean b=false;
		Session s=sf.openSession();
		Transaction t=s.beginTransaction();
		s.delete(w);
		t.commit();
		b=true;
		return b;
	}
	public boolean addProductToCartFromWishlist(WishList_Model w)
	{
		boolean b=false;
	   Session s=sf.openSession();
	   ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
       al=(ArrayList<WishList_Model>)s.createQuery("from WishList_Model where customerusername='"+w.getCustomerusername()+"' and cpid='"+w.getCpid()+"'").list();
	   s.close();
	   for(WishList_Model wm:al)
	   {
		   Cart_Model c=new Cart_Model();
		   c.setCpid(wm.getCpid());
		   c.setBrand(wm.getBrand());
		   c.setCatagory(wm.getCatagory());
		   c.setCustomerusername(wm.getCustomerusername());
		   c.setPid(wm.getPid());
		   c.setPrice(wm.getPrice());
		   c.setProductimage(wm.getProductimage());
		   c.setProductName(wm.getProductName());
		   c.setQuantity(wm.getQuantity());
		   c.setSize(wm.getSize());
		   if(c!=null)
		   {
			   Session ss=sf.openSession();
			   Transaction t=ss.beginTransaction();
			   Object obj=ss.save(c);
			   t.commit();
			   ss.close();
			   if(obj!=null)
			   {
				   Session ses=sf.openSession();
				   Transaction tx=ses.beginTransaction();
				   ses.delete(wm);
				   tx.commit();
				   ses.close();
				   b=true;
			   }
		   }
	   }
	   return b;
	}
	public boolean addAllWishlistProductsToCart(WishList_Model w)
	{
		boolean b=false;
		Session s=sf.openSession();
		ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
		al=(ArrayList<WishList_Model>)s.createQuery("from WishList_Model where customerusername='"+w.getCustomerusername()+"'").list();
		s.close();
		for(WishList_Model wm:al)
		{
			Cart_Model c=new Cart_Model();
			   c.setCpid(wm.getCpid());
			   c.setBrand(wm.getBrand());
			   c.setCatagory(wm.getCatagory());
			   c.setCustomerusername(wm.getCustomerusername());
			   c.setPid(wm.getPid());
			   c.setPrice(wm.getPrice());
			   c.setProductimage(wm.getProductimage());
			   c.setProductName(wm.getProductName());
			   c.setQuantity(wm.getQuantity());
			   c.setSize(wm.getSize());
			   Session ss=sf.openSession();
			   Transaction t=ss.beginTransaction();
			   ss.save(c);
			   t.commit();
			   ss.close();
		}
		for(WishList_Model wm:al)
		{
			Session ss=sf.openSession();
			Transaction t=ss.beginTransaction();
			ss.delete(wm);
			t.commit();
			ss.close();
		}
		b=true;
		return b;
	}
	public boolean clearAllProductsFromWishlist(WishList_Model w)
	{
		boolean b=false;
		Session s=sf.openSession();
		Query q=s.createQuery("delete from WishList_Model where customerusername='"+w.getCustomerusername()+"'");
		q.executeUpdate();
		s.close();
		if(q!=null)
		{
			b=true;
		}
		return b;
	}
	public ArrayList<MyOrders_Model> getMyOrders(MyOrders_Model m)
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model where customerusername='"+m.getCustomerusername()+"' order by orderdate desc").list();
		s.close();
		return al;
	}
	public boolean payment(Orders_Model o, MyOrders_Model m,int pid)
	{
		boolean b=false;
		Session s=sf.openSession();
		Product_Model p=new Product_Model();
		p.setPid(pid);
		p=(Product_Model) s.get(Product_Model.class,p.getPid());
		m.setProductimage(p.getImage());
		s.close();
		
		Session ss=sf.openSession();
		Transaction t=ss.beginTransaction();
		Object obj=ss.save(o);
		t.commit();
		ss.close();
		if(obj!=null)
		{
			m.setStatus("Processing");
			Session sss=sf.openSession();
			Transaction tx=sss.beginTransaction();
			Object obj1=sss.save(m);
			tx.commit();
			sss.close();
			if(obj1!=null)
			{
				p.setStock(p.getStock()-m.getQuantity());
				p.setSold(p.getStock()+m.getQuantity());
				Session ses=sf.openSession();
				Transaction tra=ses.beginTransaction();
				ses.update(p);
				tra.commit();
				b=true;
				ses.close();
			}
		}
		return b;
	}
	public ArrayList<MyOrders_Model> payment1(Orders_Model o1, MyOrders_Model mm)
	{
		
		ArrayList<Cart_Model> cart=new ArrayList<Cart_Model>();
		Session ss=sf.openSession();
		cart=(ArrayList<Cart_Model>)ss.createQuery("from Cart_Model where customerusername='"+mm.getCustomerusername()+"'").list();
		ss.close();
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		for(Cart_Model c:cart)
		{
		    MyOrders_Model mo=new MyOrders_Model();
		    mo.setProductname(c.getProductName());
		    mo.setBrand(c.getBrand());
		    mo.setCustomerusername(c.getCustomerusername());
		    mo.setPrice(c.getPrice());
		    mo.setQuantity(c.getQuantity());
		    mo.setSize(c.getSize());
		    mo.setCategory(c.getCatagory());
		    mo.setProductimage(c.getProductimage());
		    mo.setOrderdate(mm.getOrderdate());
		    mo.setDeliverydate(mm.getDeliverydate());
		    mo.setShippingaddress(mm.getShippingaddress());
		    mo.setStatus("Processing");
		    Session s=sf.openSession();
		    Transaction t=s.beginTransaction();
		    s.save(mo);
		    t.commit();
		    s.close();
		    
		    al.add(mo);
		    
		    Session se=sf.openSession();
		    Product_Model p=new Product_Model();
		    p.setPid(c.getPid());
		    p=(Product_Model)se.get(Product_Model.class,p.getPid());
		    p.setStock(p.getStock()-mo.getQuantity());
		    p.setSold(p.getSold()+mo.getQuantity());
		    se.close();
		    
		    Session sess=sf.openSession();
		    Transaction tx=sess.beginTransaction(); 
		    sess.update(p);
		    tx.commit();
		    sess.close();
		    
		    Session session=sf.openSession();
		    Transaction tra=session.beginTransaction();
		    session.delete(c);
		    tra.commit();
		    session.close();
   
		}
		Session si=sf.openSession();
	    Transaction tt=si.beginTransaction();
	    si.save(o1);
	    tt.commit();
	    si.close();
		return al;
	}
	public ArrayList<MyOrders_Model> checkCategoryOrdersBetweenTwoDates(Date startDate, Date endDate,String Category)
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model where category='"+Category+"' and orderdate between '"+startDate+"' and '"+endDate+"'").list();
		s.close();
		return al;
	}
	public ArrayList<MyOrders_Model> getOrdersOfBrand(String brand) 
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model where brand='"+brand+"'").list();
		s.close();
		return al;
	}
	public ArrayList<MyOrders_Model> getOrdersOfCategory(String category)
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model where category='"+category+"'").list();
		s.close();
		return al;
	}
	public ArrayList<MyOrders_Model> checkBrandOrdersBetweenTwoDates(Date startDate, Date endDate, String brand) 
	{
		ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model where brand='"+brand+"' and orderdate between '"+startDate+"' and '"+endDate+"'").list();
		s.close();
		return al;
	}
	public ArrayList<MyOrders_Model> getAllOrders() 
	{
	    ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
		Session s=sf.openSession();
		al=(ArrayList<MyOrders_Model>)s.createQuery("from MyOrders_Model order by orderdate desc").list();
		s.close();
		return al;
	}
	public ArrayList<Product_Model> getProductsOfCategory(Catagory_Model c)
	{
		ArrayList<Product_Model> products=new ArrayList<Product_Model>();
		Session s=sf.openSession();
		products=(ArrayList<Product_Model>)s.createQuery("from Product_Model where catagory='"+c.getCatagory()+"'").list();
		s.close();
		return products;
	}
}
