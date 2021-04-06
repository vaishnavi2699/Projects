package ecommercewebsite.Dao_Class_And_Interface;

import java.sql.Date;
import java.util.ArrayList;

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

public interface eCommerce_Interface
{
	//Administrator Modules
   public boolean adminLogin(Admin_Model a);
   public boolean changePassword(Admin_Model a);
   public boolean addCatagory(Catagory_Model c);
   public ArrayList<Catagory_Model> getAllCatagories();
   public boolean addProducts(Product_Model p);
   public ArrayList<Product_Model> filter(Filter_Model fm);
   public String updateProductDetails(Product_Model p);
   public String deleteProduct(Product_Model p);
   public ArrayList<MyOrders_Model> viewAllOrders();
   public ArrayList<Product_Model> getSearchResult(Product_Model p);
   public ArrayList<Product_Model> getProductDetails(Product_Model p);
   public ArrayList<Product_Model> sortedSearchResult(Product_Model p, String sortingType);
   public ArrayList<MyOrders_Model> getOrdersOfBrand(String brand);
   public ArrayList<MyOrders_Model> getOrdersOfCategory(String category);
   public ArrayList<MyOrders_Model> checkCategoryOrdersBetweenTwoDates(Date startDate, Date endDate, String category);
   public ArrayList<MyOrders_Model> checkBrandOrdersBetweenTwoDates(Date startDate, Date endDate, String brand);
   public ArrayList<MyOrders_Model> getAllOrders();
   
   //User Modules
   public boolean userLogin(User_Model u);
   public boolean userRegister(User_Model u);
   public boolean userResetPassword(User_Model u);
   public ArrayList<User_Model> getCustomerDetails(User_Model um);
   public boolean updateCustomertDetails(User_Model um);
   public boolean addProductToCart(Cart_Model c);
   public ArrayList<Cart_Model> getAllProductsFromCart(Cart_Model c);
   public Cart_Model getProductDetailsFromCart(Cart_Model c);
   public boolean updateProductDetailsInCart(Cart_Model c);
   public boolean deleteProductFromCart(Cart_Model c);
   public ArrayList<Brands_Model> getAllBrands();
   public boolean addBrands(Brands_Model bm);
   public boolean addProductToWishList(WishList_Model w);
   public ArrayList<WishList_Model> getAllProductsFromWishList(WishList_Model w);
   public WishList_Model getProductDetailsFromWishlist(WishList_Model w);
   public boolean updateProductDetailsInWishlist(WishList_Model w);
   public boolean deleteProductFromWishlist(WishList_Model w);
   public boolean addProductToCartFromWishlist(WishList_Model w);
   public boolean clearAllProductInCart(Cart_Model c);
   public boolean clearAllProductsFromWishlist(WishList_Model w);
   public boolean addAllWishlistProductsToCart(WishList_Model w);
   public ArrayList<MyOrders_Model> getMyOrders(MyOrders_Model o);
   public boolean payment(Orders_Model o, MyOrders_Model m,int pid);
   public ArrayList<MyOrders_Model> payment1(Orders_Model o1, MyOrders_Model mm);
   public ArrayList<Product_Model> getProductsOfCategory(Catagory_Model c);
}
