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
				<div class="heading_container heading_center">
					<h2>Product's Details</h2>
				</div>
				<div class="row">
					<div clas s="col-sm-6 col-md-4 col-lg-3">
						<div class="box">
							<a href="">
								<div class="img-box">
									<img src="images/${product.imgName}" alt="">
								</div>

								<div class="detail-box">
									<h6>${product.name}</h6>
									<h6>
										<span>$${product.price}</span>
									</h6>
								</div>
								<div class="new">
									<span> New </span>
								</div>
							</a>
						</div>
					</div>


					<div class="col-sm-6 col-md-4 col-lg-9">
					
						<!-- <form action="Cart" class="product-form"> -->
						<div class="box">
							Quantity: ${product.quantity} <br> Description:
							${product.description}
						</div>

						<div class="product__details__spacing" style="margin-top: 20px;"></div>

						<div class="product__details__rating">
							<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star"></i> <i class="fa fa-star"></i> <i
								class="fa fa-star-half-o"></i> <span>(18 reviews)</span>
						</div>

						<div class="product__details__spacing" style="margin-top: 20px;"></div>


						<div class="product__details__quantity">
							<div class="quantity">
								<div class="pro-qty-small"
									style="display: flex; align-items: center;">
									<input type="text" name="quantity" id="quantity" value="1"
										style="color: red; width: 40px; text-align: center; border: 1px solid #ccc; padding: 5px; margin-right: 5px;" />

									<!-- Add To Cart Form Section -->
									<form action="CartController" class="product-form">
										<input type="hidden" name="ACTION" value="ADD_TO_CART" />
										<input type="hidden" name="productId" value="${product.id}" />
										<button type="submit" value="ADD TO CART" class="cart-btn"
											style="outline: none; background-color: transparent; border: 1px solid #ccc; padding: 5px; cursor: pointer;">
											<i class="fa fa-cart-plus"
												style="color: red; font-size: 16px;"></i> Add To Cart
										</button>
									</form>
								</div>
							</div>
						</div>
						<!-- end cart section -->


						<script src="js/jquery-3.4.1.min.js"></script>
						<script src="js/bootstrap.js"></script>
						<script
							src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
							
						</script>
						<script src="js/custom.js"></script>
</body>

</html>