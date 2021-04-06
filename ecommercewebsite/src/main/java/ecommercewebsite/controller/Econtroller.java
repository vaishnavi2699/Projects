package ecommercewebsite.controller;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ecommercewebsite.Dao_Class_And_Interface.EDao;
import ecommercewebsite.Dao_Class_And_Interface.eCommerce_Interface;
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

@Controller
public class Econtroller
{
	    static eCommerce_Interface e=new EDao();
	    public static String cloud="C:\\Users\\KARTHIK\\git\\ConnectedToEcilipse\\e-commerce-website\\src\\main\\webapp\\webcollection\\cloud";
	    @RequestMapping("/")   
	    public ModelAndView homePage()
	    {
	    	
	    	ModelAndView mav=new ModelAndView("index");
	    	mav.addObject("msg","signin");
	    	return mav;
	    }
	    @RequestMapping("/about")
	    public ModelAndView aboutPage(HttpSession s)
	    {
	    	ModelAndView mav=new ModelAndView("About");
	    	mav.addObject("logintype",s.getAttribute("login"));
	    	return mav;
	    }
	    @RequestMapping("/getproductsofcategory")
	    public ModelAndView getProductsOfCategory(@ModelAttribute("catagory_model") Catagory_Model c,HttpSession ses)
	    {
	    	ModelAndView mav=null;
	    	ArrayList<Product_Model> products=new ArrayList<Product_Model>();
	    	ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	    	categories=e.getAllCatagories();
	    	brands=e.getAllBrands();
	    	products=e.getProductsOfCategory(c);
	    	if(products!=null)
	    	{
	    		File f=new File(cloud);
    			File arr[]=f.listFiles();
    			mav=new ModelAndView("SearchProducts");
    			ses.setAttribute("searchvalue",c.getCatagory());
    			mav.addObject("categories", categories);
	 		    mav.addObject("brands",brands);
    			mav.addObject("productdetails",products);
    			mav.addObject("productimages",arr);
    			mav.addObject("logintype",ses.getAttribute("login"));
    			mav.addObject("folderpath", cloud);
    			return mav;
	    	}
			return mav;
	    }
	    @RequestMapping("/adminloginform")
    	public ModelAndView adminLoginPage()
    	{
    		ModelAndView mav=new ModelAndView("LoginPages");
    		mav.addObject("login", "adminlogin");
    		return mav;
    	}
	    @RequestMapping("/adminoptions")
    	public String adminWelcomePage()
    	{
    		return "AdminWelcomepage";
    	}
	    @RequestMapping("/userloginpage")
    	public ModelAndView userLoginPage()
    	{
    		ModelAndView mav=new ModelAndView("LoginPages");
    		mav.addObject("login", "customerlogin");
    		return mav;
    	}
	    @RequestMapping("/addcatagoryform")
    	public ModelAndView addCatagoryForm()
    	{
    		ModelAndView mav=new ModelAndView("AdminWelcomepage");
    		mav.addObject("page", "addcatagory");
    		return mav;
    	}
	    @RequestMapping("/AddCatagory")
    	public ModelAndView addCatagory(@ModelAttribute("catagory_model") Catagory_Model c)
    	{
    		ModelAndView mav=new ModelAndView();
    		boolean b=e.addCatagory(c);
    		if(b)
    		{
    			mav.addObject("msg","Catagory Added");
    			mav.setViewName("AdminWelcomepage");
    			return mav;
    		}
    		else
    		{
    			mav.addObject("msg","Adding Catagory Failed");
    			mav.setViewName("AdminWelcomepage");
    			return mav;
    		}
    	}
	    @RequestMapping("/addbrandform")
	    public ModelAndView addBrandFrom()
	    {
	    	ModelAndView mav=new ModelAndView("AdminWelcomepage");
	    	mav.addObject("page", "addbrand");
	    	return mav;
	    }
	    @RequestMapping("/AddBrand")
	    public ModelAndView addBrand(@ModelAttribute("brands_model") Brands_Model bm)
	    {
	    	ModelAndView mav=null;
	    	boolean b=e.addBrands(bm);
	    	if(b)
	    	{
	    		mav=new ModelAndView("AdminWelcomepage");
	    		mav.addObject("msg","Brand Added");
	    	}
	    	return mav;
	    }
	    @RequestMapping("/addproductsform")
    	public ModelAndView addProductsPage()
    	{
    		ModelAndView mav=null;
    		ArrayList<Catagory_Model> catagories=new ArrayList<Catagory_Model>();
    		catagories=e.getAllCatagories();
    		ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
    		brands=e.getAllBrands();
    		if(catagories!=null)
    		{
    			mav=new ModelAndView("AdminWelcomepage");
    			mav.addObject("catagories",catagories);
    			mav.addObject("brands", brands);
        		mav.addObject("page", "addproducts");
        		return mav;
    		}
			return mav;
    	}
	    @RequestMapping(value= {"/AddProductDetails"},method=RequestMethod.POST)
    	public ModelAndView addProducts(HttpServletRequest req)
    	{
    		Product_Model p=new Product_Model();
    		ModelAndView mav=new ModelAndView();
    		if(ServletFileUpload.isMultipartContent(req))
    		{
    			try
    			{
    				List<FileItem> multiparts=new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
    				for(FileItem item:multiparts)
    				{
    					if(!item.isFormField())
    					{
    						p.setImage(new File(item.getName()).getName());
    						item.write(new File(cloud+File.separator+p.getImage()));
    					}
    					else
    					{
    						if(item.getFieldName().equals("productname")) 
    						{
    						    p.setProductName(item.getString());	
    						}
    						if(item.getFieldName().equals("catagory"))
    						{
    							p.setCatagory(item.getString());
    						}
    						if(item.getFieldName().equals("brand"))
    						{
    							p.setBrand(item.getString());
    						}
    						if(item.getFieldName().equals("stock"))
    						{
    							p.setStock(Integer.parseInt(item.getString()));
    						}
    						if(item.getFieldName().equals("price"))
    						{
    							p.setPrice(Integer.parseInt(item.getString()));
    						}
    						if(item.getFieldName().equals("info"))
    						{
    							p.setInfo(item.getString());
    						}
    						if(item.getFieldName().equals("type"))
    						{
    							p.setType(item.getString());
    						}
    					}
       				}
    				boolean b=e.addProducts(p);
    				if(b)
    				{
    					mav.addObject("msg","Product Added Sucessfully");
    					mav.setViewName("AdminWelcomepage");
    					return mav;
    				}
    				else
    				{
    					mav.addObject("msg","Adding Product Is Failed.....Try Again!");
    					mav.setViewName("AdminWelcomepage");
    					return mav;
    				}
    			}
    			catch (Exception e)
    			{
    				mav.addObject("msg","Request is not multipart type");
    				mav.setViewName("AdminWelcomepage");
					return mav;
				}
    		}
			return mav;
    	}
	    @RequestMapping("/searchproductspage")
    	public ModelAndView SearchProducts(HttpSession ss)
    	{
	    	ModelAndView mav=null;
	    	ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	    	categories=e.getAllCatagories();
	    	brands=e.getAllBrands();
	    	if(categories!=null && brands!=null)
	    	{
	    		mav=new ModelAndView("SearchProducts");
	    		mav.addObject("categories", categories);
	 		    mav.addObject("brands",brands);
	    		mav.addObject("logintype",ss.getAttribute("login"));
	    		return mav;
	    	}
			return mav;
    	}
	    @RequestMapping("/getproducts")
    	public ModelAndView getProducts(@ModelAttribute("product_model") Product_Model p,HttpSession ses)
    	{
    		p.setProductName(p.getBrand());
    		p.setCatagory(p.getBrand());
    		ses.setAttribute("searchvalue",p.getProductName());
    		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
    		al=e.getSearchResult(p);
    		if(al!=null)
    		{
    			File f=new File(cloud);
    			File arr[]=f.listFiles();
    			ModelAndView mav=new ModelAndView("SearchProducts");
    			mav.addObject("productdetails", al);
    			mav.addObject("productimages",arr);
    			mav.addObject("logintype",ses.getAttribute("login"));
    			mav.addObject("folderpath", cloud);
    			return mav;
    		}
    		else
    		{
    			ModelAndView mav=new ModelAndView("SearchProducts");
    			mav.addObject("msg","Searched Product Not Available");
    			mav.addObject("logintype",ses.getAttribute("login"));
    			mav.addObject("folderpath", cloud);
    			return mav;
    		}
    	}
	    @RequestMapping("/sorting")
    	public ModelAndView sortingSearchResult(@RequestParam("value") String value,HttpSession ss)
    	{
    		Product_Model p=new Product_Model();
    		ModelAndView mav=new ModelAndView();
    		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
    		p.setProductName(ss.getAttribute("searchvalue").toString());
    		p.setBrand((ss.getAttribute("searchvalue").toString()));
    		p.setCatagory((ss.getAttribute("searchvalue").toString()));
    		ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	    	categories=e.getAllCatagories();
	    	brands=e.getAllBrands();
    		al=e.sortedSearchResult(p,value);
    		mav.addObject("productdetails", al);
    		if(al!=null)
    		{
    			File f=new File(cloud);
    			File arr[]=f.listFiles();
    			mav.addObject("folderpath", cloud);
    			mav.addObject("productimages",arr);
    			mav.addObject("categories", categories);
	 		    mav.addObject("brands",brands);
    			mav.addObject("logintype",ss.getAttribute("login"));
    			mav.setViewName("SearchProducts");
    			return mav;
    		}
    		else
    		{
    			mav.addObject("msg","NO Products Available");
    			mav.addObject("msg", "signout");
    			mav.setViewName("SearchProducts");
    			return mav;
    		}
    	}
	    @RequestMapping("/getproductdetails")
		public ModelAndView getProductDetails(@ModelAttribute("product_model") Product_Model p,HttpSession ss) 
		{ 
			ArrayList<Product_Model> al=new ArrayList<Product_Model>();
			ModelAndView mav=new ModelAndView();
			al=e.getProductDetails(p);
			if(al!=null)
			{
				for(Product_Model pm:al)
				{
				if(ss.getAttribute("login")!=null)
				{
					mav.addObject("logintype", ss.getAttribute("login"));
					mav.addObject("productdetails",al); 
				    File f=new File(cloud);
    			    File arr[]=f.listFiles();
    			    mav.addObject("folderpath", cloud);
    			    mav.addObject("productimage",arr);
    			    mav.addObject("category",pm.getCatagory());
    			    mav.addObject("stock",pm.getStock());
    			    mav.addObject("page", "viewproduct");
    			    if(ss.getAttribute("cartmsg")!=null)
    			    {
    			    	mav.addObject("msg",ss.getAttribute("cartmsg"));
    			    	ss.removeAttribute("cartmsg");
    			    	
    			    }
    			    if(ss.getAttribute("wishlistmsg")!=null)
    			    {
    			    	mav.addObject("msg",ss.getAttribute("wishlistmsg"));
    			    	ss.removeAttribute("wishlistmsg");
    			    }
    			    mav.setViewName("viewproduct");
				    return mav;
				}
				else
				{
					mav.addObject("msg1","Login To Check Product");
					mav.setViewName("LoginPages");
					mav.addObject("login", "customerlogin");
					ss.setAttribute("pid",p.getPid());
					return mav;
				}
				}
			}
			else
			{
				mav.addObject("msg","Try Again");
				mav.setViewName("SearchProducts");
			    return mav;
			}
			return mav;
		}
	    @RequestMapping("/getproductdetailstoupdate")
    	public ModelAndView getProductDetailsToUpdate(@ModelAttribute("product_model") Product_Model p,HttpSession ss)
    	{
    		ModelAndView mav=new ModelAndView();
    		ArrayList<Product_Model> al=new ArrayList<Product_Model>();
            al=e.getProductDetails(p);
            if(al!=null)
            {
            	mav.addObject("productdetailstoupdate",al);
            	File f=new File(cloud);
    			File arr[]=f.listFiles();
    			mav.addObject("logintype", ss.getAttribute("login"));
    			mav.addObject("folderpath", cloud);
    			mav.addObject("productimages",arr);
    			mav.addObject("page","updateproduct");
    			mav.setViewName("viewproduct");
    			return mav;
            }
            else
            {
            	mav.addObject("msg","Invalid Product");
            	mav.setViewName("viewproduct");
    			return mav;
            }
    	}
	    @RequestMapping(value= {"/updateproductdetails"},method = RequestMethod.POST)
    	public ModelAndView updateProduct(HttpServletRequest req,HttpSession ss)
    	{
    		ModelAndView mav=null;
    		Product_Model p=new Product_Model();
    		List<FileItem> multiparts=null;
    		if(ServletFileUpload.isMultipartContent(req))
    		{
    			try
    			{
    		        multiparts=new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
    				for(FileItem item:multiparts)
    				{
    					if(!item.isFormField())
     					{
     						p.setImage(new File(item.getName()).getName());
     						if(p.getImage()!=null)
     						{
     						   item.write(new File(cloud+File.separator+p.getImage()));
     						}
     					}
    					else 
    					{    					
    						if(item.getFieldName().equals("pid")) 
    						{
    						    p.setPid(Integer.parseInt(item.getString()));	  					
    						}
    						if(item.getFieldName().equals("productname"))
    						{
    						    p.setProductName(item.getString());	
    						}
    						if(item.getFieldName().equals("stock"))
    						{
    							p.setStock(Integer.parseInt(item.getString()));
    						}
    						if(item.getFieldName().equals("price"))
    						{
    							p.setPrice(Integer.parseInt(item.getString()));
    						}
    						if(item.getFieldName().equals("info"))
    						{
    							p.setInfo(item.getString());
    						}
    					}    				     					
    				}
					p.setImage(new EDao().updateProductDetails(p));
					if(p.getImage()!=null)
					{
						File f=new File(cloud);
		    			File arr[]=f.listFiles();
		    			for(File f1:arr)
		    			{
		    				if(f1.getName().equals(p.getImage()))
		    				{
		    					if(f1.delete())
		    					{
		    						mav=new ModelAndView("SearchProducts");
		    						mav.addObject("msg","Product Details Updated");
		    						mav.addObject("logintype", ss.getAttribute("login"));
		    		    			return mav;
		    					}
		    				}
		    			}
					}
    			}
    			catch (Exception e)
    			{
    				for(FileItem item:multiparts)
    				{
    					if(item.getFieldName().equals("pid")) 
						{
						    p.setPid(Integer.parseInt(item.getString()));	  					
						}
						if(item.getFieldName().equals("productname"))
						{
						    p.setProductName(item.getString());	
						}
						if(item.getFieldName().equals("stock"))
						{
							p.setStock(Integer.parseInt(item.getString()));
						}
						if(item.getFieldName().equals("price"))
						{
							p.setPrice(Integer.parseInt(item.getString()));
						}
						if(item.getFieldName().equals("info"))
						{
							p.setInfo(item.getString());
						}
    				}
    				p.setImage(new EDao().updateProductDetails(p));
					if(p.getImage()!=null)
					{
						mav=new ModelAndView();
						mav.addObject("msg","Product Details Updated");
						mav.addObject("logintype", ss.getAttribute("login"));
						mav.setViewName("redirect:getproducts?brand="+ss.getAttribute("searchvalue").toString()+"");
		    			return mav;
					}
				}
    		}
			return mav;   		
    	}
	    @RequestMapping("/DeleteProduct")
    	public ModelAndView deleteProduct(@ModelAttribute("product_model") Product_Model p,HttpSession ss)
    	{
            ModelAndView mav=new ModelAndView();
            ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	    	categories=e.getAllCatagories();
	    	brands=e.getAllBrands();
    		p.setImage(e.deleteProduct(p));
    		if(p.getImage()!=null)
    		{
    			File f=new File(cloud);
    			File arr[]=f.listFiles();
    			for(File f1:arr)
    			{
    				if(f1.getName().equals(p.getImage()))
    				{
    					if(f1.delete())
    					{
    						mav.addObject("msg","Product Deleted");
    						mav.addObject("categories", categories);
    			 		    mav.addObject("brands",brands);
    		    			mav.setViewName("redirect:getproducts?brand="+ss.getAttribute("searchvalue").toString()+"");
    		    			return mav;
    					}
    				}
    			}
    			
    		}
    		else
    		{
    			mav.addObject("msg","Product Not Deleted");
    			mav.setViewName("SearchProducts");
    			mav.addObject("logintype", ss.getAttribute("login"));
    			return mav;
    		}
			return mav;
    	}
	    @RequestMapping("/viewproductsbyfilterpage")
    	public ModelAndView viewProductsByFilterPage()
    	{
	    	ModelAndView mav=null;
	    	ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	    	categories=e.getAllCatagories();
	    	brands=e.getAllBrands();
	    	if(categories!=null && brands!=null)
	    	{
    		   mav=new ModelAndView("AdminWelcomepage");
    		   mav.addObject("categories", categories);
    		   mav.addObject("brands",brands);
    		   mav.addObject("filter","filter");
    		   return mav;
	    	}
			return mav;
    	}
	    @RequestMapping("/reqfilter")
	       public ModelAndView filter(@RequestParam("catagories") String[] arr,@RequestParam("brands") String[] arr1,@RequestParam("price") int price,HttpSession ss)
	       {
	    	  ModelAndView mav=null;
	    	  Filter_Model fm=new Filter_Model();
	    	  ArrayList<String> al=new ArrayList<String>();
	    	  for(String catagories:arr)
	    	  {
	    		  al.add(catagories);
	    	  }
	    	  fm.setCatagories(al);
	    	  ArrayList<String> al2=new ArrayList<String>();
	    	  for(String brands:arr1)
	     	  {
	   		    al2.add(brands);
	      	  }
	    	  fm.setBrands(al2);
	    	  fm.setPrice(price);
	    	  ArrayList<Product_Model> al1=new ArrayList<Product_Model>();
	    	  al1=e.filter(fm);
	    	  if(al1!=null)
	    	  {
	    		ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
	  	    	ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
	  	    	categories=e.getAllCatagories();
	  	    	brands=e.getAllBrands(); 
	  	    	
	    		File f=new File(cloud);
	   			File arr2[]=f.listFiles();
	   			mav=new ModelAndView("SearchProducts");
	   			mav.addObject("categories", categories);
	    		mav.addObject("brands",brands);
	   			mav.addObject("productdetails", al1);
	   			mav.addObject("productimages",arr2);
	   			mav.addObject("folderpath", cloud);
	   			mav.addObject("logintype",ss.getAttribute("login"));
	   			return mav;
	    	  }
	    	  else
	    	  {
	    		  mav=new ModelAndView("SearchProducts");
	     		  mav.addObject("msg","No Products Found");
	     		  mav.addObject("logintype", ss.getAttribute("login"));
	     		  return mav;
	    	  }
	       }
    	@RequestMapping(value= {"/AdminLogin"},method = RequestMethod.POST)
    	public ModelAndView adminLogin(@ModelAttribute("admin_model") Admin_Model a,HttpSession ss)
    	{
    		ModelAndView mav=new ModelAndView();
    		boolean b=e.adminLogin(a);
    		if(b)
    		{
    			if(ss.getAttribute("pid")!=null)
    			{
    				ss.setAttribute("login","adminlogin");
    				mav.setViewName("redirect:getproductdetails?pid="+ss.getAttribute("pid")+"");
    				return mav;
    			}
    			ss.setAttribute("login","adminlogin");
    			mav.setViewName("AdminWelcomepage");
    			return mav;
    		}
    		else
    		{
    			mav.addObject("msg1","Invalid Username/Password");
    			mav.setViewName("redirect:adminloginform");
    			return mav;
    		}
    	}
    	@RequestMapping("/adminforgetpasswordform")
    	public ModelAndView adminForgetPasswordForm()
    	{
    		ModelAndView mav=new ModelAndView("LoginPages");
    		mav.addObject("page","adminforgotpassword");
    		return mav;
    	}
    	@RequestMapping(value= {"/adminforgotpassword"},method = RequestMethod.POST)
    	public ModelAndView adminForgotPassword(@ModelAttribute("admin_model") Admin_Model a)
    	{
    		ModelAndView mav=new ModelAndView();
    		boolean b=e.changePassword(a);
    		if(b)
    		{
    			mav.addObject("msg1","Password Has Been Changed");
    			mav.setViewName("redirect:adminloginform");
    			return mav;
    		}
    		else
    		{
    			mav.addObject("msg1","Invalid Username.....Try Again With Correct Username");
    			mav.setViewName("redirect:adminloginform");
    			return mav;
    		}
    	} 
    	@RequestMapping(value= {"/useraccount"},method = RequestMethod.POST)
    	public ModelAndView userSignInSignUpResetPassword(@ModelAttribute("user_model") User_Model u,@RequestParam("rg") String type,HttpSession ss)
    	{
    		ModelAndView mav=new ModelAndView();
    		if(type.equals("signin"))
    		{
    			boolean b=e.userLogin(u);
    			if(b)
    			{
    				if(ss.getAttribute("pid")!=null)
    				{
    					ss.setAttribute("login","customerlogin");
        				ss.setAttribute("username",u.getUsername());
        				mav.setViewName("redirect:getproductdetails?pid="+ss.getAttribute("pid")+"");
        				return mav;
    				}
    				else
    				{
    					ss.setAttribute("login","customerlogin");
        				ss.setAttribute("username",u.getUsername());
        				mav.addObject("username",u.getUsername());
        				mav.addObject("logintype",ss.getAttribute("login"));
        				mav.setViewName("index");
        				return mav;
    				}
    			}
    			else
    			{
    				mav.addObject("msg1","Invalid Email/Password");
    				mav.addObject("login", "customerlogin");
 				   mav.setViewName("LoginPages");
    				return mav;
    			}
    		}	
    		else if(type.equals("signup"))
    		{
       			   boolean b=e.userRegister(u);
    			   if(b)
    			   {
    				   mav.addObject("msg1","Login With Your Registered Details");
    				   mav.addObject("login", "customerlogin");
    				   mav.setViewName("LoginPages");
       				   return mav;
    			   }
    			   else
    			   { 
    				   mav.addObject("msg1","Enter Proper Details");
    				   mav.addObject("login", "customerlogin");
    				   mav.setViewName("LoginPages");
       				   return mav;
    			   }
    		}
    		else if(type.equals("reset"))
    		{
    			boolean b=e.userResetPassword(u);
    			if(b)
    			{
    				mav.addObject("msg1","Password Has Been Changed");
    				mav.setViewName("LoginPages");
    				return mav;
    			}
    			else
    			{
    				mav.addObject("msg1","Invalid Email");
    				mav.addObject("login", "customerlogin");
    				mav.setViewName("LoginPages");
    				return mav;
    			}
    		}
			return mav;
       }     
       @RequestMapping("/reqprofile")
       public ModelAndView profile(HttpSession ss)
       {
    	   ModelAndView mav=null;
    	   User_Model um=new User_Model();
    	   um.setUsername(ss.getAttribute("username").toString());
    	   ArrayList<User_Model> al=new ArrayList<User_Model>();
    	   al=e.getCustomerDetails(um);
    	   if(al!=null)
    	   {
    		   File f=new File(cloud);
      		   File arr[]=f.listFiles();
    		   mav=new ModelAndView("Profile");
    		   mav.addObject("profiledetails",al);
    		   mav.addObject("profilepicture",arr);
      		   mav.addObject("folderpath", cloud);
      		   mav.addObject("logintype", ss.getAttribute("login"));
    	   }
    	   return mav;
       }
       @RequestMapping(value= {"/profilechanges"},method = RequestMethod.POST)
       public ModelAndView updateCustomerDetails(HttpServletRequest req)
       {
    	   ModelAndView mav=null;
    	   User_Model um=new User_Model();
    	   List<FileItem> multipart=null;
    	   if(ServletFileUpload.isMultipartContent(req))
    	   {
    		   try 
    		   {
				   multipart=new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
			       for(FileItem item:multipart)
			       {
			    	   if(!item.isFormField())
			    	   {
			    		   um.setUserimage(new File(item.getName()).getName());
			    		   if(um.getUserimage()!=null)
			    		   {
			    		      item.write(new File(cloud+File.separator+um.getUserimage()));
			    		   }
			    	   }
			    	   else
			    	   {
			    		   if(item.getFieldName().equals("fullname"))
			    		   {
			    			   um.setFullname(item.getString());
			    		   }
			    		   if(item.getFieldName().equals("gender"))
			    		   {
			    			   um.setGender(item.getString());
			    		   }
			    		   if(item.getFieldName().equals("dob"))
			    		   {
			    			   um.setDob(item.getString());
			    		   }
			    		   if(item.getFieldName().equals("email"))
			    		   {
			    			   um.setEmail(item.getString());
			    		   }
			    		   if(item.getFieldName().equals("mobile"))
			    		   {
			    			   um.setMobile(Long.parseLong(item.getString()));
			    		   }
			    		   if(item.getFieldName().equals("username"))
			    		   {
			    			   um.setUsername(item.getString());
			    		   }
			    		   if(item.getFieldName().equals("password"))
			    		   {
			    			   um.setPassword(item.getString());
			    		   }
			    	   }
			       }
			       boolean b=e.updateCustomertDetails(um);
			       if(b)
			       {
			    	   mav=new ModelAndView("redirect:reqprofile?username="+um.getUsername()+"");
			    	   mav.addObject("msg","Details Updated");
			    	   return mav;
			       }
			       else
    			   {
    				   mav=new ModelAndView("redirect:reqprofile?username="+um.getUsername()+"");
			    	   mav.addObject("msg","Details Not Updated");
			    	   return mav;
    			   }
    		   } 
    		   catch (Exception ex) 
    		   {
    			   for(FileItem item:multipart)
			       {
    				   if(item.getFieldName().equals("fullname"))
    	    		   {
    	    			   um.setFullname(item.getString());
    	    		   }
    	    		   if(item.getFieldName().equals("gender"))
    	    		   {
    	    			   um.setGender(item.getString());
    	    		   }
    	    		   if(item.getFieldName().equals("dob"))
    	    		   {
    	    			   um.setDob(item.getString());
    	    		   }
    	    		   if(item.getFieldName().equals("email"))
    	    		   {
    	    			   um.setEmail(item.getString());
    	    		   }
    	    		   if(item.getFieldName().equals("mobile"))
    	    		   {
    	    			   um.setMobile(Long.parseLong(item.getString()));
    	    		   }
    	    		   if(item.getFieldName().equals("username"))
    	    		   {
    	    			   um.setUsername(item.getString());
    	    		   }
    	    		   if(item.getFieldName().equals("password"))
    	    		   {
    	    			   um.setPassword(item.getString());
    	    		   }
			       }
    			   boolean b=e.updateCustomertDetails(um);
        		   if(b)
        		   {
        			   mav=new ModelAndView("redirect:reqprofile?username="+um.getUsername()+"");
        	    	   mav.addObject("msg","Details Updated");
        	    	   return mav;
        		   }
        		   else
        		   {
        			   mav=new ModelAndView("redirect:reqprofile?username="+um.getUsername()+"");
        	    	   mav.addObject("msg","Details Not Updated");
        	    	   return mav;
        		   }
    		   }   		   
    	   }
		return mav;
       }
       @RequestMapping(value= "/addtocart", method = RequestMethod.POST)
       public ModelAndView addProductToCart(@ModelAttribute("cart_model") Cart_Model c,HttpSession s,@RequestParam("action") String action,@ModelAttribute("wishlist_model") WishList_Model w)
       {
    	   ModelAndView mav=null;
    	   if(action.equals("Add To Cart"))
    	   {
        	   c.setCustomerusername(s.getAttribute("username").toString());
        	   c.setPrice(c.getPrice()*c.getQuantity());
        	   boolean b=e.addProductToCart(c);
        	   if(b)
        	   {
        		   mav=new ModelAndView();
        		   s.setAttribute("cartmsg","Product Added To Cart"); 
        		   mav.setViewName("redirect:getproductdetails?pid="+c.getPid()+"");
        	   }
        	   else
        	   {
        		   mav=new ModelAndView();
        		   s.setAttribute("cartmsg","Product Not Added To Cart"); 
        		   mav.setViewName("redirect:getproductdetails?pid="+c.getPid()+"");
        	   }
    	   }
    	   if(action.equals("Add To Wishlist"))
    	   {
        	   w.setCustomerusername(s.getAttribute("username").toString());
        	   w.setPrice(c.getPrice()*c.getQuantity());
        	   boolean b=e.addProductToWishList(w);
        	   if(b)
        	   {
        		   mav=new ModelAndView();
        		   s.setAttribute("wishlistmsg","Product Added To Wishlist"); 
        		   mav.setViewName("redirect:getproductdetails?pid="+w.getPid()+"");
        	   }
        	   else
        	   {
        		   mav=new ModelAndView();
        		   s.setAttribute("wishlistmsg","Product Not Added To Wishlist"); 
        		   mav.setViewName("redirect:getproductdetails?pid="+w.getPid()+"");
        	   }
    	   }
    	   if(action.equals("Buy Now"))
    	   {
    		   c.setCustomerusername(s.getAttribute("username").toString());
        	   c.setPrice(c.getPrice()*c.getQuantity());
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("productdetails",c);
    		   mav.addObject("page","payment");
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("orderamount",c.getPrice());
    		   return mav;
    	   }
    	   return mav;
       }
       @RequestMapping("/cart")
       public ModelAndView cart(HttpSession s)
       {
    	   ModelAndView mav=null;
    	   int total=0;
    	   Cart_Model c=new Cart_Model();
    	   c.setCustomerusername(s.getAttribute("username").toString());
    	   ArrayList<Cart_Model> al=new ArrayList<Cart_Model>();
    	   al=e.getAllProductsFromCart(c);
    	   if(al!=null)
    	   {   
    		   for(Cart_Model cm:al)
    		   {
    			   total=total+cm.getPrice();
    		   }
    		    mav=new ModelAndView("viewproduct");
    		    mav.addObject("productdetails",al); 
			    File f=new File(cloud);
			    File arr[]=f.listFiles();
			    mav.addObject("folderpath", cloud);
			    mav.addObject("productimages",arr);
			    mav.addObject("totalbill",total);
			    mav.addObject("logintype",s.getAttribute("login"));
			    mav.addObject("page","cart");
    	   }
    	   else
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("msg","Cart Is Empty");
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/updateproductincart")
       public ModelAndView getProductDetailsFromCart(@ModelAttribute("cart_model") Cart_Model c,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   c.setCustomerusername(s.getAttribute("username").toString());
    	   c=e.getProductDetailsFromCart(c);
    	   if(c!=null)
    	   {
    		    mav=new ModelAndView("viewproduct");
   		        mav.addObject("productdetails",c); 
			    File f=new File(cloud);
			    File arr[]=f.listFiles();
			    mav.addObject("folderpath", cloud);
			    mav.addObject("productimages",arr);
			    mav.addObject("category",c.getCatagory());
			    mav.addObject("logintype",s.getAttribute("login"));
			    mav.addObject("page","updateproductincart");
    	   }
		   return mav;
       }
       @RequestMapping(value= {"/updateproductdetailsincartorwishlist"},method = RequestMethod.POST)
       public ModelAndView updateProductDetailsInCart(@ModelAttribute("cart_model") Cart_Model c,HttpSession s,@RequestParam("action") String action,@ModelAttribute("wishlist_model") WishList_Model w)
       {
    	   ModelAndView mav=null;
    	   if(action.equals("Save Changes In Cart"))
    	   {
        	   boolean b=false;
        	   c.setCustomerusername(s.getAttribute("username").toString());
        	   b=e.updateProductDetailsInCart(c);
        	   if(b)
        	   {
        		   mav=new ModelAndView("redirect:cart");
        		   s.setAttribute("username",s.getAttribute("username"));
        		   return mav;
        	   }
    		   return mav;
    	   }
    	   if(action.equals("Save Changes In Wishlist"))
    	   {
    		   boolean b=false;
        	   w.setCustomerusername(s.getAttribute("username").toString());
        	   b=e.updateProductDetailsInWishlist(w);
        	   if(b)
        	   {
        		   mav=new ModelAndView("redirect:cart");
        		   s.setAttribute("username",s.getAttribute("username"));
        		   return mav;
        	   }
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/deleteproductfromcart")
       public ModelAndView deleteProductFromCart(@ModelAttribute("cart_model") Cart_Model c,HttpSession s)
       {
    	   ModelAndView mav=new ModelAndView();
    	   boolean b=false;
    	   c.setCustomerusername(s.getAttribute("username").toString());
    	   b=e.deleteProductFromCart(c);
    	   if(b)
    	   {
    		   mav=new ModelAndView("redirect:cart");
    		   s.setAttribute("username",s.getAttribute("username"));
    		   return mav;   
    	   }
    	   return mav;
       }
       @RequestMapping("/clearcart")
       public ModelAndView clearCart(HttpSession s)
       {
    	   Cart_Model c=new Cart_Model();
    	   c.setCustomerusername(s.getAttribute("username").toString());
    	   boolean b=e.clearAllProductInCart(c);
    	   if(b)
    	   {
    		   ModelAndView mav=new ModelAndView("redirect:searchproductspage");
    		   return mav;
    	   }
		return null;
       }
       @RequestMapping("/wishlist")
       public ModelAndView wishlist(HttpSession s)
       {
    	   ModelAndView mav=null;
    	   int total=0;
    	   WishList_Model w=new WishList_Model();
    	   w.setCustomerusername(s.getAttribute("username").toString());
    	   ArrayList<WishList_Model> al=new ArrayList<WishList_Model>();
    	   al=e.getAllProductsFromWishList(w);
    	   if(al!=null)
    	   {
    		   for(WishList_Model wl:al)
    		   {
    			   total=total+wl.getPrice();
    		   }
    		    mav=new ModelAndView("viewproduct");
    		    mav.addObject("productdetails",al); 
			    File f=new File(cloud);
			    File arr[]=f.listFiles();
			    mav.addObject("folderpath", cloud);
			    mav.addObject("productimages",arr);
			    mav.addObject("totalbill",total);
			    mav.addObject("logintype",s.getAttribute("login"));
			    mav.addObject("page","wishlist");
    	   }
    	   else
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("msg","WishList Is Empty");
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/updateproductinwishlist")
       public ModelAndView updateProductInWishlist(@ModelAttribute("wishlist_model") WishList_Model w,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   w.setCustomerusername(s.getAttribute("username").toString());
    	   w=e.getProductDetailsFromWishlist(w);
    	   if(w!=null)
    	   {
    		    mav=new ModelAndView("viewproduct");
   		        mav.addObject("productdetails",w); 
			    File f=new File(cloud);
			    File arr[]=f.listFiles();
			    mav.addObject("folderpath", cloud);
			    mav.addObject("productimages",arr);
			    mav.addObject("category",w.getCatagory());
			    mav.addObject("logintype",s.getAttribute("login"));
			    mav.addObject("page","updateproductinwishlist");
			    return mav;
    	   }
		   return mav;
       }
       @RequestMapping("/deleteproductfromwishlist")
       public ModelAndView deleteProductFromWishlist(@ModelAttribute("wishlist_model") WishList_Model w,HttpSession s)
       {
    	   ModelAndView mav=new ModelAndView();
    	   boolean b=false;
    	   w.setCustomerusername(s.getAttribute("username").toString());
    	   b=e.deleteProductFromWishlist(w);
    	   if(b)
    	   {
    		   mav=new ModelAndView("redirect:cart");
    		   s.setAttribute("username",s.getAttribute("username"));
    		   return mav;   
    	   }
    	   return mav;
       }
       @RequestMapping("/addtocartfromwishlist")
       public ModelAndView addProductToCartFromWishList(@ModelAttribute("wishlist_model") WishList_Model w,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   w.setCustomerusername(s.getAttribute("username").toString());
    	   boolean b=e.addProductToCartFromWishlist(w);
    	   if(b)
    	   {
    		   mav=new ModelAndView("redirect:wishlist");
    		   s.setAttribute("username",s.getAttribute("username"));
    		   return mav;
    	   }
		return mav;   
       }
       @RequestMapping("/addwishlistproductstocart")
       public ModelAndView addAllWishlistProductsToCart(HttpSession s)
       {
    	   ModelAndView mav=null;
    	  WishList_Model w=new WishList_Model();
   	      w.setCustomerusername(s.getAttribute("username").toString());
   	      boolean b=e.addAllWishlistProductsToCart(w);
   	      if(b)
   	      {
   	    	  mav=new ModelAndView("redirect:cart");
   	    	  s.setAttribute("username",s.getAttribute("username"));
   	    	  return mav;
   	      }
		return mav;
       }
       @RequestMapping("/clearwishlist")
       public ModelAndView clearWishlist(HttpSession s)
       {
    	   ModelAndView mav=null;
    	   WishList_Model w=new WishList_Model();
    	   w.setCustomerusername(s.getAttribute("username").toString());
    	   boolean b=e.clearAllProductsFromWishlist(w);
    	   if(b)
    	   {
    		   mav=new ModelAndView("redirect:searchproductspage");
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/myorders")
       public ModelAndView myOrders(HttpSession s)
       {
    	   ModelAndView mav=null;
    	   MyOrders_Model o=new MyOrders_Model();
    	   o.setCustomerusername(s.getAttribute("username").toString());
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.getMyOrders(o);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("folderpath",cloud);
    		   return mav;
    	   }
    	   else
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("msg","You Have'nt Purchased Products");
    		   mav.addObject("logintype",s.getAttribute("login"));
    	   }
		return mav;
       }
       @RequestMapping(value= {"/payment"},method = RequestMethod.POST)
       public ModelAndView payment(@RequestParam("productname") String productname,@RequestParam("category") String category,@RequestParam("price") int price,@RequestParam("size") int size,@RequestParam("quantity") int quantity,@RequestParam("shippingaddress") String shippingaddress,@RequestParam("cardnumber") String cardnumber,@RequestParam("validity") String validity,@RequestParam("cvv") int cvv,@RequestParam("orderamount") Long orderamount,@RequestParam("brand") String brand,HttpSession s,@RequestParam("pid") int pid)
       {
    	   ModelAndView mav=null;
    	   java.sql.Date currentdate = new java.sql.Date(new java.util.Date().getTime());
    	   java.sql.Date deliverydate = this.addDays(currentdate,4);	   
        	   MyOrders_Model m=new MyOrders_Model();
        	   m.setPrice(price);
        	   m.setProductname(productname);
        	   m.setBrand(brand);
        	   m.setQuantity(quantity);
        	   m.setShippingaddress(shippingaddress);
        	   m.setSize(size);
        	   m.setCategory(category);
        	   m.setCustomerusername(s.getAttribute("username").toString());	   
        	   m.setOrderdate(currentdate);
        	   m.setDeliverydate(deliverydate);
        	   
        	   
        	   Orders_Model o=new Orders_Model();
        	   o.setCardnumber(cardnumber);
        	   o.setCvv(cvv);
        	   o.setOrderamount(orderamount);
        	   o.setShippingaddress(shippingaddress);
        	   o.setValidity(validity);
        	   o.setCustomerusername(s.getAttribute("username").toString());
        	   o.setPaymentdate(currentdate);
        	   boolean b=e.payment(o,m,pid);
        	   if(b)
        	   {
        		   mav=new ModelAndView("viewproduct");
        		   mav.addObject("brand",m.getBrand());
        		   mav.addObject("productname",m.getProductname());
        		   mav.addObject("deliverydate",m.getDeliverydate());
        		   mav.addObject("shippingadress",m.getShippingaddress());
        		   mav.addObject("logintype",s.getAttribute("login"));
        		   mav.addObject("page","sucessmsg");
        		   return mav;
        	   }
		return mav;    	   
       }
       public Date addDays(Date date, int days) 
       {
           Calendar c = Calendar.getInstance();
           c.setTime(date);
           c.add(Calendar.DATE, days);
           return new Date(c.getTimeInMillis());
       }
       @RequestMapping("/placeorder")
       public ModelAndView placeOrder(@RequestParam("orderamount") String orderamount,HttpSession s)
       {
    	   ModelAndView mav=new ModelAndView("viewproduct");
    	   mav.addObject("orderamount", orderamount);
    	   mav.addObject("page","placeorder");
    	   mav.addObject("logintype",s.getAttribute("login"));
    	   return mav;
       }
       @RequestMapping(value= {"/payment1"},method = RequestMethod.POST)
       public ModelAndView payment1(@ModelAttribute("orders_model") Orders_Model o,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   MyOrders_Model mm=new MyOrders_Model();
    	   java.sql.Date currentdate = new java.sql.Date(new java.util.Date().getTime());
    	   java.sql.Date deliverydate = this.addDays(currentdate,4);
    	   mm.setCustomerusername(s.getAttribute("username").toString());	   
    	   mm.setOrderdate(currentdate);
    	   mm.setDeliverydate(deliverydate);
    	   mm.setShippingaddress(o.getShippingaddress());
    	   o.setPaymentdate(currentdate);
    	   o.setCustomerusername(s.getAttribute("username").toString());
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.payment1(o, mm);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("page","paymentsucess");
    		   mav.addObject("myorders",al);
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/brandwise")
       public ModelAndView brandWise()
       {
    	   ModelAndView mav=null;
    	   ArrayList<Brands_Model> brands=new ArrayList<Brands_Model>();
    	   brands=e.getAllBrands();
    	   if(brands!=null)
    	   {
    		   mav=new ModelAndView("AdminWelcomepage");
    		   mav.addObject("brands",brands);
    		   mav.addObject("page","brands");
    		   return mav;
    	   }
    	   return mav;
       }
       @RequestMapping("/getordersofbrand")
       public ModelAndView getOrdersOfBrand(@RequestParam("brand") String brand,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.getOrdersOfBrand(brand);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("folderpath",cloud);
    		   mav.addObject("customer", "customer");
    		   mav.addObject("type", "brand");
    		   return mav;	   
    	   }
    	   return mav;
       }
       @RequestMapping("/categorywise")
       public ModelAndView categoryWise()
       {
    	   ModelAndView mav=null;
    	   ArrayList<Catagory_Model> categories=new ArrayList<Catagory_Model>();
    	   categories=e.getAllCatagories();
    	   if(categories!=null)
    	   {
    		   mav=new ModelAndView("AdminWelcomepage");
    		   mav.addObject("categories", categories);
    		   mav.addObject("page","categories");
    		   return mav;
    	   }
    	   return mav;
       }
       @RequestMapping("/getordersofcategory")
       public ModelAndView getOrdersOfCategory(@RequestParam("category") String category,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.getOrdersOfCategory(category);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("folderpath",cloud);
    		   mav.addObject("customer", "customer");
    		   mav.addObject("type", "category");
    		   return mav;	   
    	   }
    	   return mav;
       }
       @RequestMapping("/filtercategoryordersbydate")
       public ModelAndView filterCategoryOrderByDate(@RequestParam("category") String category)
       {
    	   ModelAndView mav=new ModelAndView("AdminWelcomepage");
    	   mav.addObject("category", category);
    	   mav.addObject("page","filtercategorybydate");
    	   return mav;
       }
       @RequestMapping("/filterbrandordersbydate")
       public ModelAndView filterBrandOrderByDate(@RequestParam("brand") String brand)
       {
    	   ModelAndView mav=new ModelAndView("AdminWelcomepage");
    	   mav.addObject("brand", brand);
    	   mav.addObject("page","filterbrandbydate");
    	   return mav;
       }
       @RequestMapping("/checksalesbycategory")
       public ModelAndView checkSalesByCategory(@RequestParam("startdate") Date startDate,@RequestParam("enddate") Date endDate,@RequestParam("category") String category,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.checkCategoryOrdersBetweenTwoDates(startDate,endDate,category);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("folderpath",cloud);
    		   mav.addObject("customer", "customer");
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/checksalesbybrand")
       public ModelAndView checkSalesByBrand(@RequestParam("startdate") Date startDate,@RequestParam("enddate") Date endDate,@RequestParam("brand") String brand,HttpSession s)
       {
    	   ModelAndView mav=null;
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.checkBrandOrdersBetweenTwoDates(startDate,endDate,brand);
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("folderpath",cloud);
    		   mav.addObject("customer", "customer");
    		   return mav;
    	   }
		return mav;
       }
       @RequestMapping("/checkorders")
       public ModelAndView checkOrders(HttpSession s)
       {
    	   ModelAndView mav=null;
    	   ArrayList<MyOrders_Model> al=new ArrayList<MyOrders_Model>();
    	   al=e.getAllOrders();
    	   if(al!=null)
    	   {
    		   mav=new ModelAndView("viewproduct");
    		   mav.addObject("page","myorders");
    		   mav.addObject("productdetails",al);
    		   File f=new File(cloud);
    		   File arr[]=f.listFiles();
    		   mav.addObject("productimages",arr);
    		   mav.addObject("logintype",s.getAttribute("login"));
    		   mav.addObject("folderpath",cloud);
    		   return mav;
    	   }
		return mav;
       }
		@RequestMapping("/home")
	    public ModelAndView Home(HttpSession s)
	    {
	    	if(s.getAttribute("username")!=null)
	    	{
	    	   ModelAndView mav=new ModelAndView("index");
	    	   mav.addObject("msg","signout");
	    	   mav.addObject("logintype",s.getAttribute("login"));
	    	   mav.addObject("username",s.getAttribute("username"));
	    	   return mav;
	    	}
	    	else
	    	{
	    		ModelAndView mav=new ModelAndView("index");
		    	mav.addObject("msg","signin");
		        return mav;
	    	}	    	
	    }
	    @RequestMapping("/signout")
	    public ModelAndView signOut(HttpSession ss)
	    {
	    	ModelAndView mav=new ModelAndView("index");
	    	mav.addObject("msg","signin");
	    	ss.invalidate();
	    	return mav;
	    }
}
