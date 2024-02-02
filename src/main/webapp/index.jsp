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
	
	<jsp:include page="header-section.jsp"/>

	<!-- shop section -->
	<c:if test="${not empty products}">
		<section class="shop_section layout_padding">
			<div class="container">
				<div class="heading_container heading_center">
					<h2>Products</h2>
				</div>
				<div class="row">
					<c:forEach items="${products}" var="product">
						<div class="col-sm-6 col-md-4 col-lg-3">
							<div class="box">
								<a href="ProductDetail?productId=${product.id}">
									<div class="img-box">
										<img src="images/${product.imgName}" alt="">
									</div>
									<div class="detail-box">
										<h6>${product.name}</h6>
										<h6>
											Price <span>$${product.price}</span>
										</h6>
									</div> <c:if test="${product.is_new == true}">
										<div class="new">
											<span> New </span>
										</div>
									</c:if>
								</a>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="btn-box">
					<a href="Home?action=SHOW_ALL"> View All Products </a>
				</div>
			</div>
		</section>
	</c:if>
	<!-- end shop section -->

	<c:if test="${empty products}">
		<section class="shop_section layout_padding">
			<div class="container">
				<div class="heading_container heading_center">
					<h2>No Results Found</h2>
				</div>
			</div>
		</section>
	</c:if>
	
	<jsp:include page="footer-section.jsp"/>

</body>

</html>