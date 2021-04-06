<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title>Admin Welcome Page</title>
<link rel="stylesheet" href="webcollection/css/adminwelcomepage.css">
<script type="text/javascript" src="webcollection/JavaScript/Script.js"></script>
</head>
<body>
         <nav>
            <a href=addproductsform>Add Products</a>
            <a href=addcatagoryform>Add Catagory</a>
            <a href="addbrandform">Add Brand</a>
            <a href=viewproductsbyfilterpage>View Products</a>
            <div class="dropdown"> 
		                     <button class="dropdown-button">Check Sales</button>
		                        <div class="dropdown-list">
			                          <a href="brandwise">Brand Wise</a>
			                          <a href="categorywise">Category Wise</a>
		                        </div>
	                     </div>
            <a href=checkorders>Check Orders</a>
            <a href=searchproductspage>Search</a>
            <a href="signout">Logout</a>
            <div class="animation start-home"></div>
         </nav>
                
    <c:if test="${msg!='null'}">
       <center>
            <h3>${msg}</h3>
       </center>
    </c:if>       
         <c:if test="${page=='addproducts'}">
         <div class="center">
<h1>Add Product</h1>
<form action="AddProductDetails" method="post" name="addproducts" onsubmit="return addproduct();" enctype="multipart/form-data">

<div class="txt_field"><select name="type" id="type">
   <option value="Select Type">Select Type</option>
   <option value="Men">Men</option>
   <option value="Women">Women</option>
</select></div>

<div class="txt_field"><select name="catagory" id="catagory">
   <option value="Select Catagory">Select Catagory</option>
   <c:forEach items="${catagories}" var="ca">
   <option value="${ca.getCatagory()}">${ca.getCatagory()}</option>
   </c:forEach>
</select></div>

<div class="txt_field">
<input type="text" name="productname">
<span></span>
<label>Product Name:</label>
</div>

<div class="txt_field">
<select name="brand" id="brand">
   <option value="Select Brand">Select Brand</option>
   <c:forEach items="${brands}" var="b">
   <option value="${b.getBrand()}">${b.getBrand()}</option>
   </c:forEach>
</select></div>

<div class="txt_field">
<input type="text" name="stock">
<span></span>
<label>Stock:</label>
</div>

<div class="txt_field">
<input type="text" name="price">
<span></span>
<label>Price:</label>
</div>

<div class="txt_field">
<input type="text" name="info">
<span></span>
<label>Info:</label>
</div>


<div class="txt_field">
<input type="file" name="productimage">
</div>

<input type="submit" value="Add Product">
</form>
</div> 
</c:if> 
<c:if test="${page=='addcatagory'}">
<div class="center">
<h1>Add Catagory</h1>
<form action="AddCatagory" method="post" name="addCatagory" onsubmit="return addcatagory();">

<div class="txt_field">
<input type="text" name="catagory">
<span></span>
<label>Catagory:</label>
</div>

<input type="submit" value="Add Catagory">
</form>
</div>
</c:if> 

<c:if test="${filter=='filter'}">
        <form action="reqfilter">
       <div class="menu">
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
                     <li><label class="container1">7000<input type="radio" name="price" value="7000"><span class="checkmark1" checked></span></label></li>
                 </ul>
              </li>
              <li><div class="button"><input type="submit" value="Apply"></div></li>
          </ul>
       </div>
       </form>
</c:if> 

<c:if test="${page=='addbrand'}">
<div class="center">
<h1>Add Brand</h1>
<form action="AddBrand" method="post" name="addbrand" onsubmit="return addBrand();">

<div class="txt_field">
<input type="text" name="brand">
<span></span>
<label>Brand:</label>
</div>

<input type="submit" value="Add Brand">
</form>
</div>
</c:if>

<c:if test="${page=='brands'}">
<center>
         <div class="center1">
         <h1>Brands</h1>
           <c:forEach items="${brands}" var="brands">
                 <a href="getordersofbrand?brand=${brands.getBrand()}"><input type="submit" value="${brands.getBrand()}"></a>
           </c:forEach>
         </div>  
</center>
</c:if>

<c:if test="${page=='categories'}">
<center>
         <div class="center1">
         <h1>Categories</h1>
           <c:forEach items="${categories}" var="categories">
                 <a href="getordersofcategory?category=${categories.getCatagory()}"><input type="submit" value="${categories.getCatagory()}"></a>
           </c:forEach>
         </div>  
</center>
</c:if>

 <c:if test="${page=='filtercategorybydate'}">
<div class="center">
<h1>Check Sales Of ${category}</h1>
<form action="checksalesbycategory" method="post" name="checksales" onsubmit="return checksales();">

<input type="hidden" name=category value="${category}">

<div class="txt_field">
<input type="date" name="startdate">
<span></span>
<label>Start Date:</label>
</div>

<div class="txt_field">
<input type="date" name="enddate">
<span></span>
<label>End Date:</label>
</div>

<input type="submit" value="Check">
</form>
</div>
</c:if>
 
<c:if test="${page=='filterbrandbydate'}">
<div class="center">
<h1>Check Sales Of ${brand}</h1>
<form action="checksalesbybrand" method="post" name="checksales" onsubmit="return checksales();">

<input type="hidden" name=brand value="${brand}">



<div class="txt_field">
<input type="date" name="startdate">
<span></span>
<label>Start Date:</label>
</div>

<div class="txt_field">
<input type="date" name="enddate">
<span></span>
<label>End Date:</label>
</div>

<input type="submit" value="Check">
</form>
</div>
</c:if> 
 
</body>
</html>