
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
	margin-right: 5px;
	/* Adjust the right margin of the input */
}

:placeholder {
	font-size: 12px;
	/* Adjust the font size as needed */
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

					<c:if test="${not empty sessionScope.cart.items}">
						<h2 class="cart-heading">Your Cart</h2>
						<div class="row">
							<c:forEach var="productInCart" items="${sessionScope.cart.items}">
								<div class="col-sm-6 col-md-4 col-lg-3">
									<div class="box cart-product-box">
										<div class="img-box cart-product-img">
											<img src="images/${productInCart.imgName}" alt="">
										</div>
										<div class="cart-product-details">
											${productInCart.name} $${productInCart.price}<br>
											Quantity : ${productInCart.quantity}<br> Total :$
											${productInCart.subTotal}<br> <a
												href="CartController?ACTION=REMOVE&productId=${productInCart.id}">
												<button
													style="outline: none; color: red; background-color: none; border: 1px solid #ccc; padding: 5px; cursor: pointer;">
													Remove</button>
											</a>


										</div>
									</div>
								</div>
							</c:forEach>
						</div>

						<!-- total price -->
						<div class="box">
							<strong>Total Cart Price: </strong> $<span id="totalCartPrice">${sessionScope.cart.total}</span>
						</div>

						<div class="box">
							<c:if test="${empty sessionScope.user}">
   						 Please <a href="login.jsp">LOGIN</a> to CheckOut
						</c:if>
						</div>

						<c:if test="${not empty sessionScope.user}">
							<!-- Proceed to Checkout button -->
							<div class="box">
								<form action="CheckOutController?action=CHECK_OUT" method="post">
									<button
										style="outline: none; color: red; background-color: none; border: 1px solid #ccc;"
										type="submit">Proceed to Checkout</button>
								</form>
							</div>
						</c:if>


					</c:if>

					<c:if test="${empty sessionScope.cart.items}">
						<h2 class="cart-heading">Your Cart Is Empty</h2>
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






