<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" href="webcollection/css/loginpages.css">
<script type="text/javascript" src="webcollection/JavaScript/Script.js"></script>
</head>
<body>
<header>
       <div class="head">
       <div class="logo"><img src="webcollection/images/logo2.jpeg"></div>
       <div class="nav">
                 <ul>
                 <li><a href="home">Home</a></li>
                 <c:if test="${login=='adminlogin'}">
                     <li><a href="userloginpage">Customer</a></li>
                 </c:if> 
                 <c:if test="${login=='customerlogin'}">
                     <li><a href="adminloginform">Admin</a></li>
                 </c:if>   
                     
                 </ul>
       </div>
       </div>
</header>
<center>
<h3>WELCOME TO MYSTIC MART</h3>
<c:if test="${msg1!=null}">
${msg1}
</c:if>
</center>
    <c:if test="${login=='customerlogin'}">
    <br><br><br><br><br><br>
        <div class="flex-wrap">
               <fieldset>               
                      <form action="useraccount" method="post">
                      <input type="radio" name="rg" id="sign-in" value="signin" checked/>
                      <input type="radio" name="rg" id="sign-up" value="signup"/>
                      <input type="radio" name="rg" id="reset" value="reset"/>
                      <label for="sign-in">Sign-In</label>
                      <label for="sign-up">Sign-up</label>
                      <label for="reset">Reset</label>
                      <input class="sign-up" type="text" name="fullname" placeholder="Fullname"/>
                       <input class="sign-up" type="text" name="gender" placeholder="Gender"/>
                       <input class="sign-up" type="date" name="dob" placeholder="DD-MM-YYYY"/>
                      <input class="sign-up" type="text" name="mobile" placeholder="Mobile"/>
                      <input class="sign-up" type="email" name="email" placeholder="Email"/>
                      <input class="sign-up sign-in reset" type="text" name="username" placeholder="Username" required/>
                      <input class="sign-up sign-in reset" type="password" name="password"  placeholder="Password" required/>
                      <button>Submit</button>
                      </form>
               </fieldset>
        </div>
    </c:if>    
        
        <c:if test="${login=='adminlogin'}">
            <div class="center">
            <h1>Admin Login</h1>
            <form action="AdminLogin" method="post" name="adminlogin" onsubmit="return AdminLogin();">
            <div class="txt_field">
            <input type="text" name="username">
            <span></span>
            <label>Username</label>
            </div>

            <div class="txt_field">
            <input type="password" name="password">
            <span></span>
            <label>Password</label>
            </div>
            
            <div class="pass">
            <a href="adminforgetpasswordform">Forgot Password?</a>
            </div>
            
            <input type="submit" value="Login">
            </form>
            </div>
        </c:if>
        <c:if test="${page=='adminforgotpassword'}">
             <div class="center">
            <h1>Change Password</h1>
            <form action="adminforgotpassword" method="post" name="adminforgotpassword" onsubmit="return AdminForgotPassword();">
            <div class="txt_field">
            <input type="text" name="username">
            <span></span>
            <label>Username</label>
            </div>

            <div class="txt_field">
            <input type="password" name="password">
            <span></span>
            <label>New Password</label>
            </div>
            <input type="submit" value="Change Password">
            </form>
            </div>
        </c:if>
</body>
</html>