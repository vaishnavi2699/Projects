<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
<script type="text/javascript">
function disableBackButton()
{
window.history.forward();
}
setTimeout("disableBackButton()", 0);
</script>
<link rel="stylesheet" href="webcollection/css/viewproduct.css">
<link rel="stylesheet" href="webcollection/css/updateproduct.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
</head>
<body>
<header>
       <div class="head">
       <div class="logo"><img src="webcollection/images/logo2.jpeg"></div>
       <div class="nav">
                 <ul>
                     <li><a href=searchproductspage><i class="fa fa-search" aria-hidden="true"></i></a></li>
                     <li><a href=cart><i class="fa fa-shopping-cart" aria-hidden="true"></i></a></li>
                     <li><a href=wishlist>Wishlist</a></li>
                     <li><a href=myorders>Orders</a></li>
                     <li><a href="home">Home</a></li>
                     <li><a href="signout" onclick="return to disableBackButton()">Logout</a></li>
                 </ul>
       </div>
       </div>
</header>
<center>
<c:if test="${msg!='null'}">
${msg}
</c:if>
      <form action="profilechanges" method="post" enctype="multipart/form-data">
          <table border="1" cellpadding=10px>
                    <c:forEach items="${profiledetails}" var="pd">
                    <tr>
                    
                       <c:forEach items="${profilepicture}" var="pp">   
                                       
                          <c:if test="${pd.getUserimage()==pp.getName()}">
                             <td> <img src="${folderpath}/${pp.getName()}" width="400" height="400"><br><input type="file" name="profilepicture">                               
                             </td></c:if>
                       </c:forEach>
                       
                       
                       
                     <c:if test="${pd.getUserimage()==null}"><td><img src="${folderpath}/Emptyprofilepicture.png" width="400" height="400"><br><input type="file" name="profilepicture" placeholder="Update Profile Picture"></td></c:if>
                                
                       <td>Name:<input type="text" name="fullname" value="${pd.getFullname()}"><br>
                                       Gender:<input type="text" name="gender" value="${pd.getGender()}"><br>
                                       DOB:<input type="text" name="dob" value="${pd.getDob()}"><br>
                                       Email:<input type="text" name="email" value="${pd.getEmail()}"><br>
                                       Mobile:<input type="text" name="mobile" value="${pd.getMobile()}"><br>
                                       UserName:<input type="text" name="username" readonly="readonly" value="${pd.getUsername()}"><br>
                                       Password:<input type="password" name="password" value="${pd.getPassword()}"></td></tr>
                    </c:forEach>     
          </table>
                          <input type="submit" value="Save Changes"> 
      </form>
</center>      
</body>
</html>