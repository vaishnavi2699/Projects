function disableBackButton()
{
   window.history.forward();
   setTimeout("disableBackButton()", 0);
}
function AdminLogin()
{
   var username=document.adminlogin.username.value;
   var password=document.adminlogin.password.value;
   if(username=="")
   {
	  alert("Please Enter Username:");
	  document.adminlogin.username.focus();
	  return false;
   }
   if(password=="")
   {
	   alert("Please Enter Password:");
	   document.adminlogin.password.focus();
	   return false;
   }
}
function AdminForgotPassword()
{
   var username=document.adminforgotpassword.username.value;
   var password=document.adminforgotpassword.password.value;
   if(username=="")
   {
	  alert("Please Enter Username:");
	  document.adminforgotpassword.username.focus();
	  return false;
   }
   if(password=="")
   {
	   alert("Please Enter Password:");
	   document.adminforgotpassword.password.focus();
	   return false;
   }
}
function addproduct()
{
   var catagory=document.getElementById("catagory").value;
   var brand=document.getElementById("brand").value;
   var type=document.getElementById("type").value;
   var productname=document.addproducts.productname.value;
   var stock=document.addproducts.stock.value;
   var price=document.addproducts.price.value;
   var info=document.addproducts.info.value;
   if(type=="Select Type")
   {
	   alert("Please Select Type");
	   document.addproducts.type.focus();
	   return false;
   }
   if(catagory=="Select Catagory")
   {
	   alert("Please Select Category");
	   document.addproducts.catagory.focus();
	   return false;
   }
   if(productname=="")
   {
	   alert("Please Enter Product Name:");
	   document.addproducts.productname.focus();
	   return false;
   }
   if(brand=="Select Brand")
   {
	   alert("Please Select Brand");
	   document.addproducts.brand.focus();
	   return false;
   }
   if(stock=="")
   {
	  alert("Please Enter Stock:");
	  document.addproducts.stock.focus();
	  return false;
   }
   if(price=="")
   {
	  alert("Please Enter Price:");
	  document.addproducts.price.focus();
	  return false;
   }
   if(info=="")
   {
	   alert("Please Enter Info:");
	   document.addproducts.info.focus();
	   return false;
   }
}
function addcatagory()
{
   var catagory=document.addCatagory.catagory.value;
   if(catagory=="")
   {
	  alert("Please Enter Catagory:");
	  document.addCatagory.catagory.focus();
	  return false;
   }

}
function addBrand()
{
   var brand=document.addbrand.brand.value; 
   if(brand=="")
   {
	  alert("Please Enter brand:");
	  document.addbrand.brand.focus();
	  return false;
   }
}
function searchFunction()
{ 
	var brand=document.search.brand.value;
	if(brand=="")
	{
        alert("Enter Search Value");
        document.search.brand.focus();
        return false;
	}
}
function checksales() 
{
	 var startdate=document.checksales.startdate.value; 
	 var enddate=document.checksales.enddate.value; 
	   if(startdate=="")
	   {
		  alert("Please Enter Start Date:");
		  document.checksales.startdate.focus();
		  return false;
	   }
	   if(enddate=="")
	   {
		  alert("Please Enter End Date:");
		  document.checksales.enddate.focus();
		  return false;
	   }
}
function addToCart() 
{
   var size=document.getElementById("size").value;;
   var quantity=document.getElementById("quantity").value;;
   if(size=="Size")
   {
	   alert("Please Select Size:");
	   document.add.size.focus();
	   return false;
   }
   if(quantity=="Qty")
   {
	   alert("Please Select Quantity:");
	   document.add.quantity.focus();
	   return false;
   }
}
function PaymentValidation()
{
	var shippingAddress=document.payment.shippingaddress.value;
	var cardNumber=document.payment.cardnumber.value;
	var validity=document.payment.validity.value;
	var cvv=document.payment.cvv.value;
	if(shippingAddress=="")
	{
        alert("Please Enter Shipping Address");
        document.payment.shippingaddress.focus();
        return false;
	}
	if(cardNumber=="")
	{
       alert("Please Enter Card Number");
       document.payment.cardnumber.focus();
       return false;
	}
	if(cardNumber.length!=16)
	{
       alert("Card Number Must Be 16 Digits");
       document.payment.cardnumber.focus();
       return false;
	}
	if(validity=="")
	{
		alert("Please Enter Card Validity");
	    document.payment.validity.focus();
	    return false;	
	}
	if(cvv=="")
	{
		alert("Please Enter CVV");
	    document.payment.cvv.focus();
	    return false;
	}
	if(cvv.length!=3)
	{
       alert("CVV Must Be 3 Digits");
       document.payment.cvv.focus();
       return false;
	}
}