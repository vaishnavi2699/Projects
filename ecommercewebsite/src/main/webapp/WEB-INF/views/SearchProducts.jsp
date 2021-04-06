<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@page import="java.io.File"%>
<%@page import="ecommercewebsite.Models.Product_Model"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Products</title>
<link rel="stylesheet" href="webcollection/css/searchproducts.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script type="text/javascript" src="webcollection/JavaScript/Script.js"></script>
</head>
<body>
<header>
       <div class="head">
       <div class="logo"><img src="webcollection/images/logo2.jpeg"></div>
       <div class="nav">
                 <ul>
                    <c:if test="${logintype=='customerlogin'}">
                     <li><a href=cart><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>
                     <li><a href="wishlist">WishList</a></li>
                     <li><a href="home">Home</a></li>                
                         <li><a href="reqprofile">Profile</a></li>
                         <li><a href="home">Home</a></li>
                         <li><a href="signout" onclick="return to disableBackButton()">Logout</a></li>
                       </c:if> 
                        <c:if test="${logintype=='adminlogin'}"> <li><a href="adminoptions">Operations</a></li>
                        <li><a href="home">Home</a></li>
                         <li><a href="signout" onclick="return to disableBackButton()">Logout</a></li></c:if>
                         <c:if test="${logintype==null}">
                         <li><a href="home">Home</a></li>
                         <li>
                         <div class="dropdown1"> 
		                     <button class="dropdown-button1">Login</button>
		                        <div class="dropdown-list1">
			                          <a href="adminloginform">Admin</a>
			                          <a href="userloginpage">Customer</a>
		                        </div>
	                     </div>
	                    </li></c:if>                      
                 </ul>
       </div>
       </div>
       </header>
<div class="search">
<form action="getproducts" name="search" onsubmit="return searchFunction();"><input type="text" name="brand"  placeholder="Ex:Jeans,Watches"><input type="submit" value="Search"> </form>	
<div class="dropdown"> 
		<button class="dropdown-button">Sort</button>
		<div class="dropdown-list">
			<a href="sorting?value=lowtohigh">Low To High</a>
			<a href="sorting?value=hightolow">High To Low</a>
		</div>
	</div>
 
<div class="dropdown">

    <button class="dropbtn">Filter</button>
    <form action="reqfilter">
     <div class="content">
       <a href="#"><div class="menu">
          <ul>
              <li>Catagory
                 <ul><c:forEach items="${categories}" var="ca">
                     <li><label class="container">${ca.getCatagory()}<input type="checkbox" name="catagories" value="${ca.getCatagory()}"><span class="checkmark"></span></label></li>
                     </c:forEach></ul>
              </li>
              <li>Brand
                  <ul><c:forEach items="${brands}" var="b">
                     <li><label class="container">${b.getBrand()}<input type="checkbox" name="brands" value="${b.getBrand()}"><span class="checkmark"></span></label></li>
                     </c:forEach></ul>
              </li>
              <li>Price
                  <ul>
                     <li><label class="container1">2000<input type="radio" name="price" value="2000"><span class="checkmark1"></span></label></li>
                     <li><label class="container1">3000<input type="radio" name="price" value="3000"><span class="checkmark1"></span></label></li>
                     <li><label class="container1">4000<input type="radio" name="price" value="4000"><span class="checkmark1"></span></label></li>
                     <li><label class="container1">5000<input type="radio" name="price" value="5000"><span class="checkmark1"></span></label></li>
                     <li><label class="container1">6000<input type="radio" name="price" value="6000"><span class="checkmark1"></span></label></li>
                     <li><label class="container1">7000<input type="radio" name="price" value="7000"><span class="checkmark1"></span></label></li>
                 </ul>
              </li>
          </ul>
          <input type="submit" value="Apply"> 
       </div></a>   
       </div>
    </form>
</div>
</div>		
<div class="result">
${msg}
          <c:forEach items="${productdetails}" var="pd">
              <c:forEach items="${productimages}" var="pi">
              <table>
                  <c:if test="${pd.getImage()==pi.getName() }">
                    <tr><td><a href="getproductdetails?pid=${pd.getPid()}"><img src="${folderpath}/${pi.getName()}" height=200 width=200></a></td><td><a href="getproductdetails?pid=${pd.getPid()}">${pd.getProductName()}</a><br><br>Price: Rs ${pd.getPrice()}/-<br><br>Brand: ${pd.getBrand()}<br><br>${pd.getInfo()}<br><br></td></tr>
                  </c:if>
              </table>      
              </c:forEach>
          </c:forEach>
</div>
</body>
</html>