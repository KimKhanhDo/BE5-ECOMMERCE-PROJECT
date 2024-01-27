<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
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

@media (min-width: 1025px) {
.h-custom {
height: 100vh !important;
}
}

.card-registration .select-input.form-control[readonly]:not([disabled]) {
font-size: 1rem;
line-height: 2.15;
padding-left: .75em;
padding-right: .75em;
}

.card-registration .select-arrow {
top: 13px;
}

.bg-grey {
background-color: #eae8e8;
}

@media (min-width: 992px) {
.card-registration-2 .bg-grey {
border-top-right-radius: 16px;
border-bottom-right-radius: 16px;
}
}

@media (max-width: 991px) {
.card-registration-2 .bg-grey {
border-bottom-left-radius: 16px;
border-bottom-right-radius: 16px;
}
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
		<!-- header section strats -->
		<header class="header_section">
			<nav class="navbar navbar-expand-lg custom_nav-container ">
				<a class="navbar-brand" href="index.jsp"> <span> Giftos </span>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class=""></span>
				</button>

				<!-- category menu & category's products href link -->
				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav  ">
						<li class="nav-item active"><a class="nav-link" href="Home">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<c:forEach items="${categories}" var="category">
							<li class="nav-item"><a class="nav-link"
								href="index.jsp?categoryId=${category.id}"> ${category.name}
							</a></li>
						</c:forEach>
					</ul>
					<!-- end category menu -->

					<!-- Login/ Logout section -->
					<div class="user_option">
						<c:if test="${not empty sessionScope.userName}">
							<i class="fa fa-user" aria-hidden="true"
								style="margin-right: 5px;"></i>
							<span style="margin-right: 5px;"> ${sessionScope.userName}
								&nbsp;</span>

							<a href="Authentication"> Logout <i class="fa fa-sign-out"
								aria-hidden="true"></i>
							</a>
						</c:if>

						<c:if test="${empty sessionScope.userName}">
							<a href="login.jsp"> <i class="fa fa-user" aria-hidden="true"></i>
								<span> Login </span></a>
						</c:if>
						<!-- End Login/ Logout section -->

						<!--  View Cart Section -->
						<a href="Cart?command=VIEW_CART"> <i
							class="fa fa-shopping-bag" aria-hidden="true"></i>
						</a>
						<!--  End View Cart Section -->

						<!-- search section -->
						<div class="search_section">
							<form action="search-section.jsp" method="post"
								class="form-inline">
								<input type="text" name="searchField" placeholder="Search" />
								<button class="btn nav_search-btn" type="submit">
									<i class="fa fa-search" aria-hidden="true"></i>
								</button>
							</form>
						</div>
						<!-- end search section -->

					</div>
				</div>
			</nav>
		</header>
		<!-- end header section -->
		
      <!-- Shoping Cart Section Begin -->
 <section class="h-100 h-custom" style="background-color: #d2c9ff;">
  <div class="container py-5 h-100">
    <div class="row d-flex justify-content-center align-items-center h-100">
      <div class="col-12">
        <div class="card card-registration card-registration-2" style="border-radius: 15px;">
          <div class="card-body p-0">
            <div class="row g-0">
              <div class="col-lg-8">
                <div class="p-5">
                  <div class="d-flex justify-content-between align-items-center mb-5">
                    <h1 class="fw-bold mb-0 text-black">Shopping Cart</h1>
                    <h6 class="mb-0 text-muted">3 items</h6>
                  </div>
                  <hr class="my-4">

                  <div class="row mb-4 d-flex justify-content-between align-items-center">
                    <div class="col-md-2 col-lg-2 col-xl-2">
                      <img
                        src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-shopping-carts/img5.webp"
                        class="img-fluid rounded-3" alt="Cotton T-shirt">
                    </div>
                    <div class="col-md-3 col-lg-3 col-xl-3">
                      <h6 class="text-muted">Shirt</h6>
                      <h6 class="text-black mb-0">Cotton T-shirt</h6>
                    </div>
                    <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                     <!--  <button class="btn btn-link px-2"
                        onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
                        <i class="fas fa-minus"></i>
                      </button> -->

                      <input id="form1" min="0" name="quantity" value="1" type="number"
                        class="form-control form-control-sm" />

              <!--         <button class="btn btn-link px-2"
                        onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
                        <i class="fas fa-plus"></i>
                      </button> -->
                    </div>
                    <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                      <h6 class="mb-0">$ 44.00</h6>
                    </div>
                    <div class="col-md-1 col-lg-1 col-xl-1 text-end">
                      <a href="#!" class="text-muted"><i class="fas fa-times"></i></a>
                    </div>
                  </div>

                  <hr class="my-4">

 

                  <div class="pt-5">
                    <h6 class="mb-0"><a href="#!" class="text-body"><i
                          class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
                  </div>
                </div>
              </div>
              <div class="col-lg-4 bg-grey">
                <div class="p-5">
                  <h3 class="fw-bold mb-5 mt-2 pt-1">Summary</h3>
                  <hr class="my-4">

                  <div class="d-flex justify-content-between mb-4">
                    <h5 class="text-uppercase">items 3</h5>
                    <h5>$ 132.00</h5>
                  </div>

                  <h5 class="text-uppercase mb-3">Shipping</h5>

                  <div class="mb-4 pb-2">
                    <select class="select">
                      <option value="1">Standard-Delivery- $5.00</option>
                      <option value="2">Two</option>
                      <option value="3">Three</option>
                      <option value="4">Four</option>
                    </select>
                  </div>

                  <h5 class="text-uppercase mb-3">Give code</h5>

                  <div class="mb-5">
                    <div class="form-outline">
                      <input type="text" id="form3Examplea2" class="form-control form-control-lg" />
                      <label class="form-label" for="form3Examplea2">Enter your code</label>
                    </div>
                  </div>

                  <hr class="my-4">

                  <div class="d-flex justify-content-between mb-5">
                    <h5 class="text-uppercase">Total price</h5>
                    <h5>$ 137.00</h5>
                  </div>

                  <button type="button" class="btn btn-dark btn-block btn-lg"
                    data-mdb-ripple-color="dark">BUY NOW</button>

                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
        <!-- Shoping Cart Section End -->

    </div>
<script>
document.addEventListener('DOMContentLoaded', function () {
    // Get the input element
    var quantityInput = document.getElementById('form1');

    // Get the buttons for increment and decrement
    var decrementButton = document.querySelector('.fa-minus');
    var incrementButton = document.querySelector('.fa-plus');

    // Decrement button click event
    decrementButton.addEventListener('click', function () {
        // Ensure the quantity is at least 1
        if (quantityInput.value > 1) {
            quantityInput.stepDown();
        }
    });

    // Increment button click event
    incrementButton.addEventListener('click', function () {
        quantityInput.stepUp();
    });
});
</script>
    
</body>

</html>