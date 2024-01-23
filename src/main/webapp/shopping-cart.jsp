<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>

<head>
<!-- Basic -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<!-- Mobile Metas -->
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- Site Metas -->
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="author" content="" />
<link rel="shortcut icon" href="images/favicon.png" type="image/x-icon">

<title>Giftos</title>

<!--Search Form style -->
<style>
.user_option form {
	margin-left: 10px;
	/* Adjust the left margin to create space between input and button */
}

.user_option input {
	margin-right: 5px; /* Adjust the right margin of the input */
}

::placeholder {
	font-size: 12px; /* Adjust the font size as needed */
}
</style>

<!-- slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />
</head>

<body>
	<div class="hero_area">

		<jsp:include page="header-section.jsp" />

<!-- shop section -->
<section class="shop_section layout_padding">
    <div class="container">
        <div class="heading_container text-center">
        
        <c:if test="${not empty cart}"> 
                <h2 class="cart-heading"> Your Cart </h2>
                
<%--  <c:forEach var="entry" items="${cart}">
 <div class="img-box">
						
            <div>
                Product ID: ${entry.key}<br>
                Quantity: ${entry.value.quantity}<br>
               
                <hr>
            </div>
        </c:forEach>  --%>
        

<c:forEach var="productInCart" items="${cart.values()}" varStatus="status">
    <tr>
        <td class="shoping__cart__item">
            <img src="images/${productInCart.product.getImgName()}" style="width: 225px; height: 225px;" alt="">
            <h5>${productInCart.product.getName()}</h5>
        </td>
        <td class="shoping__cart__price">${productInCart.product.getPrice()}</td>
        <td class="shoping__cart__quantity">
            <div class="quantity">
                <div class="pro-qty">
                    <input type="text" name="quantity_${productInCart.product.getId()}" id="quantity"
                           value="${productInCart.quantity}"
                           oninput="updateSubtotal(this, ${productInCart.product.getPrice()})">
                </div>
            </div>
        </td>
        <td class="shoping__cart__total">${productInCart.subTotalPrice}</td>
        <td class="shoping__cart__item__close">
            <a href="CartServlet?command=REMOVE&productId=${productInCart.product.getId()}" onclick="clicked(event)">X</a>
        </td>
    </tr>
</c:forEach>


           </c:if> 

            <c:if test="${empty cart}">
                <h2 class="cart-heading"> Your Cart Is Empty </h2>
            </c:if> 
        </div>
    </div>
</section>



					
					


						<script src="js/jquery-3.4.1.min.js"></script>
						<script src="js/bootstrap.js"></script>
						<script
							src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
							
						</script>
						<script src="js/custom.js"></script>
</body>

</html>