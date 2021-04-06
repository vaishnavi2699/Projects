<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title>Home Page</title>
<script type="text/javascript" src="webcollection/JavaScript/Script.js"></script>
<link rel="stylesheet" href="webcollection/css/index.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
       <header>
       <div class="head">
       <div class="logo"><img src="webcollection/images/logo2.jpeg"></div>
       <div class="nav">
                 <ul>
                     <li><a href=searchproductspage><i class="fa fa-search" aria-hidden="true"></i></a></li>
                     <c:if test="${logintype=='customerlogin'}">
                     <li><a href=cart><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>
                     <li><a href=myorders>MyOrders</a></li>
                     <li><a href=about>About</a></li>
                         <c:if test="${username!=null}"><li><a href="reqprofile?username=${username}">Profile</a></li></c:if>
                         <li><a href="signout"><button class="dropdown-button" onclick="return to disableBackButton()">Logout</button></a></li>                         
                     </c:if>
                     <c:if test="${logintype=='adminlogin'}"><li><a href="adminoptions">Operations</a></li>
                     <li><a href="signout" onclick="return to disableBackButton()">Logout</a></li> </c:if>
                     <c:if test="${msg=='signin'}">
                         <li>
			                   <a href="userloginpage">Login</a>
	                    </li>
                    </c:if>
                 </ul>
       </div>
       </div>
       </header>
       <div class="head2">
           <div class="first">
               <a href="getproductsofcategory?catagory=Dresses"><img  src="webcollection/images/Dresses.jpg"/></a>
            </div>   
           <div class="second">
               <a href="getproductsofcategory?catagory=Kurtas"><img src="webcollection/images/kurtas.jpg"/></a>
           </div>    
           <div class="third">
               <a href="getproductsofcategory?catagory=Sarees"><img src="webcollection/images/sarees.jpg"/></a>
           </div> 
           <div class="third">
               <a href="getproductsofcategory?catagory=Handbags"><img src="webcollection/images/women handbags.jpg"/></a>
           </div>  
           <div class="third">
               <a href="getproductsofcategory?catagory=Footware"><img src="webcollection/images/women heels.jpg"/></a>
           </div>  
       </div>
       <div class="head2">
           <div class="first">
               <a href="getproductsofcategory?catagory=Jeans"><img  src="webcollection/images/men jeans.jpg"/></a>
            </div>   
           <div class="second">
               <a href="getproductsofcategory?catagory=Shirts"><img src="webcollection/images/shirts.jpg"/></a>
           </div>    
           <div class="third">
               <a href="getproductsofcategory?catagory=Shorts"><img src="webcollection/images/men shorts.jpg"/></a>
           </div>
           <div class="third">
              <a href="getproductsofcategory?catagory=Footware"> <img src="webcollection/images/men causal shoes.jpg"/></a>
           </div> 
           <div class="third">
              <a href="getproductsofcategory?catagory=Watches"> <img src="webcollection/images/watches m&w.jpg"/></a>
           </div>     
       </div>
       <footer>
            <h3>2020 Mystic Mart. All Rights Reserved</h3>
       </footer>
</body>
</html>