<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>View Product</title>
<link rel="stylesheet" href="webcollection/css/viewproduct.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<script type="text/javascript" src="webcollection/JavaScript/Script.js"></script>
</head>
<body>
   <header>
       <div class="head">
       <div class="logo"><img src="webcollection/images/logo2.jpeg"></div>
       <div class="nav">
                 <ul>
                     <li><a href=searchproductspage><i class="fa fa-search" aria-hidden="true"></i></a></li>
                     <c:if test="${logintype=='customerlogin'}">
                         <c:if test="${page!='cart'}"><li><a href=cart><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li></c:if>
                         <li><a href="reqprofile">Profile</a></li>
                          <c:if test="${page!='wishlist'}"><li><a href=wishlist>Wishlist</a></li></c:if>
                          <c:if test="${page!='myorders'}"><li><a href=myorders>Orders</a></li></c:if>
                     </c:if>
                     <c:if test="${logintype=='adminlogin'}">
                       <li><a href="adminoptions">Operations</a></li>
                     </c:if>
                     <li><a href="home">Home</a></li>
                     <li><a href="signout" onclick="return to disableBackButton()">Logout</a></li>
                 </ul>
       </div>
       </div>
</header>
<c:if test="${msg!=null}">
<center><h3>${msg}</h3></center>
</c:if>
<c:if test="${page=='viewproduct'}">
<div class="row">
<div class="column">
  <c:forEach items="${productdetails}" var="pd">
    
    <c:forEach items="${productimage}" var="pi">     
    
        <c:if test="${pd.getImage()==pi.getName()}">
             <img src="${folderpath}/${pi.getName()}" height=500 width=500/>
        </c:if>
    </c:forEach>
    
</c:forEach>
  </div>
  <div class="column1">
  <c:forEach items="${productdetails}" var="pd">
  <form action="addtocart" method="post" name="add" onsubmit="return addToCart();">
  <input type="hidden" name="pid" value="${pd.getPid()}">
       Product Name: ${pd.getProductName()}<input type="hidden" name="productName" value="${pd.getProductName()}">
       <br><br>
        Brand: ${pd.getBrand()}<input type="hidden" name="brand" value="${pd.getBrand()}">
        <br><br>
        Category: ${pd.getCatagory()}<input type="hidden" name="catagory" value="${pd.getCatagory()}">
          <br><br>
          Price: ${pd.getPrice()}<input type="hidden" name="price" value="${pd.getPrice()}">  
          <br><br>
           <c:if test="${logintype=='customerlogin'}">       
           <c:if test="${category=='Shirts'}">
           <select name="size" id="size">
           <option value="Size">Size</option>
           <option>38</option>
           <option>40</option>
           <option>42</option>
           <option>44</option>
           </select>
           <br><br>
           </c:if>
           <c:if test="${category=='T-Shirts' || category=='Dresses' || category=='Kurtas'}">
           <select name="size" id="size">
           <option value="Size">Size</option>
           <option>S</option>
           <option>M</option>
           <option>L</option>
           <option>XL</option>
           <option>XXL</option>
           </select>
           <br><br>
           </c:if>
           <c:if test="${category=='Jeans'}">
           <select name="size" id="size">
           <option value="Size">Size</option>
           <option>32</option>
           <option>34</option>
           <option>36</option>
           <option>38</option>
           </select>
           <br><br>
           </c:if>
           
           <c:if test="${category=='Footware'}">
           <select name="size" id="size">
           <option value="Size">Size</option>
           <option>7</option>
           <option>8</option>
           <option>9</option>
           <option>10</option>
           </select>
           <br><br>
           </c:if>
        Note:You can select maximum 5 products at a time.<br>
        <select name="quantity" id="quantity">
        <option value="Qty">Qty</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        </select>
        <br><br>
        <c:if test="${stock>10}">
        .Available<br>          
        <input type="submit" class="button" name="action" value="Add To Cart"><input type="submit" class="button" name="action" value="Add To Wishlist">
       <br><br>
       <center><input type="submit" class="button" name="action" value="Buy Now"></center></c:if>
       <c:if test="${stock>0 && stock<=10}">
           *Only ${stock} are available....Hurry Up!<br>
           <input type="submit" class="button" name="action" value="Add To Cart"><input type="submit" class="button" name="action" value="Add To Wishlist">
       <br><br>
           <center><input type="submit" class="button" name="action" value="Buy Now"></center>
       </c:if>
       <c:if test="${stock==0}"><center><h3>SOLD OUT</h3></center></c:if>
  </c:if>
  </form>
  <c:if test="${logintype=='adminlogin'}">
       <a href="getproductdetailstoupdate?pid=${pd.getPid()}"><input type="submit" value="Update Product"></a>
       <br><br>
       <a href="DeleteProduct?pid=${pd.getPid()}"><input type="submit" value="Delete Product"></a>
  </c:if>
  </c:forEach>
   </div>
</div>
</c:if>
<c:if test="${page=='updateproduct'}">
<center>
<c:forEach items="${productdetailstoupdate}" var="pd">
             <c:forEach items="${productimages}" var="pi">
                 <c:if test="${pd.getImage()==pi.getName()}">
                   <form action="updateproductdetails" method="post" enctype="multipart/form-data">
                     <table cellpadding=10px border="1">
                          <tr><td> <img src="${folderpath}/${pi.getName()}" height=200 width=250><br><br><input type="file" name="image"></td>
                                <td><input type="hidden" name="pid" value="${pd.getPid()}">
                                    Product Name:<input type="text" name="productname" value="${pd.getProductName()}"><br>
                                    Stock:<input type="text" name="stock" value="${pd.getStock()}"> <br>
                                    Price:<input type="text" name="price" value="${pd.getPrice()}"><br>
                                    Info:<input type="text" name="info" value="${pd.getInfo()}"><br>            
                                 </td></tr>
                     </table>
                           <input type="submit" value="Update Product">           
                   </form>         
                   </c:if>
             </c:forEach>
          </c:forEach>   
</center>
</c:if>

<c:if test="${page=='cart' || page=='wishlist'}">
<center>
    <c:forEach items="${productdetails}" var="pd">
       <table cellpadding=10px>
             <c:forEach items="${productimages}" var="pi">
                 <c:if test="${pd.getProductimage()==pi.getName()}">
           <tr><td><img src="${folderpath}/${pi.getName()}" height=200 width=200></td>
                <td>Product Name:${pd.getProductName()}<br>   
                    Brand:${pd.getBrand()}<br>
                    Category:${pd.getCatagory()}<br>
                    Size:${pd.getSize()}<br>
                    Qty:${pd.getQuantity()}<br>
                    Price:${pd.getPrice()}<br></td>
             <c:if test="${page=='cart'}"><td><h3><a href=updateproductincart?cpid=${pd.getCpid()}><i class="fa fa-pencil" aria-hidden="true"></i></a>
                 <br><br><a href=deleteproductfromcart?cpid=${pd.getCpid()} ><i class="fa fa-trash" aria-hidden="true"></i></a><br><br></h3></td></c:if>
                 
              <c:if test="${page=='wishlist'}"><td><h3><a href=updateproductinwishlist?cpid=${pd.getCpid()}><i class="fa fa-pencil" aria-hidden="true"></i></a>
                 <br><br><a href=deleteproductfromwishlist?cpid=${pd.getCpid()}><i class="fa fa-trash" aria-hidden="true"></i></a><br><br>
                 <a href=addtocartfromwishlist?cpid=${pd.getCpid()}><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></h3></td></c:if></tr>
        </c:if>  
       </c:forEach>
     </table>
      </c:forEach>
      <c:if test="${page=='cart'}">
         <h4>Total Bill: Rs ${totalbill}/-</h4>
         <a href="clearcart"><input type="button" value="Clear Cart"></a><a href="placeorder?orderamount=${totalbill}"><input type="button" value="Place Order"></a>   
      </c:if>
      <c:if test="${page=='wishlist'}">
         <h4>Total Bill: Rs ${totalbill}/-</h4>
         <a href="clearwishlist"><input type="button" value="Clear Wishlist"></a><a href="addwishlistproductstocart"><input type="button" value="Add To Cart"></a>   
      </c:if> 
</center>    
</c:if>

<c:if test="${page=='updateproductincart' || page=='updateproductinwishlist'}">
<center>
             <c:forEach items="${productimages}" var="pi">
                 <c:if test="${productdetails.getProductimage()==pi.getName()}">
                   <form action="updateproductdetailsincartorwishlist" method="post">
                    <table>
                         <tr><td><img src="${folderpath}/${pi.getName()}" height=200 width=250></td>
                             <td><h2>
                             <input type="hidden" name="cpid" value="${productdetails.getCpid()}">
                                Product Name:${productdetails.getProductName()}<br>
                                Brand:${productdetails.getBrand()}<br>
                                Category:${productdetails.getCatagory()}<br>
                                Price:${productdetails.getPrice()}<br>
                                Qty:<select name="quantity">
                                   <option>${productdetails.getQuantity()}</option>
                                   <option>1</option>
                                   <option>2</option>
                                   <option>3</option>
                                   <option>4</option>
                                   <option>5</option>
                                </select><br>
                                
                                Size:<c:if test="${category=='Shirts'}">
                                    <select name="size">
                                    <option>${productdetails.getSize()}</option>
                                    <option>38</option>
                                    <option>40</option>
                                    <option>42</option>
                                    <option>44</option>
                                    </select>
                               </c:if>
                               <c:if test="${category=='T-Shirts'}">
                                   <select name="size">
                                   <option>${productdetails.getSize()}</option>
                                   <option>S</option>
                                   <option>M</option>
                                   <option>L</option>
                                   <option>XL</option>
                                   <option>XXL</option>
                                   </select>
                               </c:if>
                              <c:if test="${category=='Jeans'}">
                                <select name="size">
                                <option>${productdetails.getSize()}</option>
                                <option>32</option>
                                <option>34</option>
                                <option>36</option>
                                <option>38</option>
                                </select>
                              </c:if>
                             </h2></td>
                    </table>
                    <c:if test="${page=='updateproductincart'}"><input type="submit" value="Save Changes In Cart" name="action"></c:if>
                    <c:if test="${page=='updateproductinwishlist'}"><input type="submit" value="Save Changes In Wishlist" name="action"></c:if>
                  </form>  
                 </c:if> 
             </c:forEach>             
</center>
</c:if>
<c:if test="${page=='payment'}">
<center>
         <form action="payment" method="post" name="payment" onsubmit="return PaymentValidation();">
           <input type="hidden" name="pid" value="${productdetails.getPid()}">
            <input type="hidden" name="productname" value="${productdetails.getProductName()}">
            <input type="hidden" name="price" value="${productdetails.getPrice()}">
            <input type="hidden" name="size" value="${productdetails.getSize()}">
            <input type="hidden" name="quantity" value="${productdetails.getQuantity()}">
            <input type="hidden" name="brand" value="${productdetails.getBrand()}">
            <input type="hidden" name="category" value="${productdetails.getCatagory()}">
           <table>
           <tr><td><h3>Address:</h3><br><textarea rows="5" cols="20" name="shippingaddress"></textarea></td></tr>
           <tr><td><h3>Payment:</h3><br><h3>Card Details:<br></h3>
                   Card Number:<input type="text" name="cardnumber" placeholder="16 Digit Card Number"><br>
                   Month/Year:<input type="text" name="validity" placeholder="MM/YYYY"><br>
                   CVV:<input type="text" name="cvv" placeholder="3 Digit CVV"><br></td></tr>
           <tr><td>Order Amount:<input type="text" name="orderamount" value="${orderamount}" readonly><br></td></tr> 
           <tr><td><c:if test="${page=='payment'}"><input type="submit" class="button" value="Pay"></c:if></td></tr>  
           </table>
        </form>   
</center>
</c:if>

<c:if test="${page=='placeorder'}">
<center>
   <form action="payment1" method="post" name="payment" onsubmit="return PaymentValidation();">
   <table>
           <tr><td><h3>Address:</h3><br><textarea rows="5" cols="20" name="shippingaddress"></textarea></td></tr>
           <tr><td><h3>Payment:</h3><br><h3>Card Details:<br></h3>
                   Card Number:<input type="text" name="cardnumber" placeholder="16 Digit Card Number"><br>
                   Month/Year:<input type="text" name="validity" placeholder="MM/YYYY"><br>
                   CVV:<input type="text" name="cvv" placeholder="3 Digit CVV"><br></td></tr>
           <tr><td>Order Amount:<input type="text" name="orderamount" value="${orderamount}" readonly><br></td></tr>
           <tr><td><input type="submit" class="button" value="pay"></td></tr>     
   </table>
   </form>
</center>   
</c:if>

<c:if test="${page=='sucessmsg'}">
<center>
           <center><p><u>Thanks For Shopping</u><br><br>${brand} ${productname} will be delivered on ${deliverydate} <br> To: ${shippingadress}</p></center>
</center>
</c:if>

<c:if test="${page=='paymentsucess'}">
<center>
      <p><u>Thanks For Shopping</u></p><br><br>
       <c:forEach items="${myorders}" var="mo">
          <p>${mo.getBrand()} ${mo.getProductname()} will be delivered on ${mo.getDeliverydate()} <br> To: ${mo.getShippingaddress()}</p>
       </c:forEach>
</center>       
</c:if>

<c:if test="${page=='myorders'}">
      <center>
              <c:if test="${type=='category'}"><a href="filtercategoryordersbydate?category=${pd.getCategory()}">Filter By Date</a></c:if>
                <c:if test="${type=='brand'}"><a href="filterbrandordersbydate?brand=${pd.getBrand()}">Filter By Date</a></c:if>
                <c:forEach items="${productdetails}" var="pd">
              <table cellpadding=10px>
               <c:forEach items="${productimages}" var="pi">
                 <c:if test="${pd.getProductimage()==pi.getName()}">
                 <tr><td><img src="${folderpath}/${pi.getName()}" height=200 width=200></td>
                <td>Product Name:${pd.getProductname()}<br>   
                    Brand:${pd.getBrand()}<br>
                    Size:${pd.getSize()}<br>
                    Qty:${pd.getQuantity()}<br>
                    Price:${pd.getPrice()}<br>
                    Order Date:${pd.getOrderdate()}<br>
                    Delivery Date:${pd.getDeliverydate()}<br>
                    <c:if test="${customer=='customer'}">Customer:${pd.getCustomerusername()}</c:if></td>
                </tr>
                </c:if>
                </c:forEach>
                </table>
                </c:forEach>    
      </center>
</c:if>


</body>
</html>