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

<!-- slider stylesheet -->
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

<!-- bootstrap core css -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet" />
<!-- responsive style -->
<link href="css/responsive.css" rel="stylesheet" />

 <!-- Custom CSS for hobbies section -->
<style>
    .hobbies-section {
        margin-bottom: 15px;
    }

    .hobbies-section input[type="checkbox"] {
        display: inline-block;
        vertical-align: middle;
    }

    .hobbies-section label {
        display: inline-block;
        margin-left: 5px;
        vertical-align: middle;
    }
</style>


</head>

<body>
	<div class="hero_area">
		<!-- header section strats -->
		<header class="header_section">
			<nav class="navbar navbar-expand-lg custom_nav-container ">
				<a class="navbar-brand" href="index.html"> <span> Giftos
				</span>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class=""></span>
				</button>

				<div class="collapse navbar-collapse innerpage_navbar"
					id="navbarSupportedContent">
					<ul class="navbar-nav  ">
						<li class="nav-item "><a class="nav-link" href="index.jsp">Home
								<span class="sr-only">(current)</span>
						</a></li>
						<li class="nav-item active"><a class="nav-link"
							href="shop.jsp"> Shop </a></li>
						<li class="nav-item"><a class="nav-link" href="why.jsp">
								Why Us </a></li>
						<li class="nav-item"><a class="nav-link"
							href="testimonial.jsp"> Testimonial </a></li>
						<li class="nav-item"><a class="nav-link" href="contact.jsp">Contact
								Us</a></li>
					</ul>
					<div class="user_option">
						<a href=""> <i class="fa fa-user" aria-hidden="true"></i> <span>
								Login </span>
						</a> <a href=""> <i class="fa fa-shopping-bag" aria-hidden="true"></i>
						</a>
						<form class="form-inline ">
							<button class="btn nav_search-btn" type="submit">
								<i class="fa fa-search" aria-hidden="true"></i>
							</button>
						</form>
					</div>
				</div>
			</nav>
		</header>
		<!-- end header section -->

	</div>
	<!-- end hero area -->

	<!-- contact section -->

	<section class="contact_section layout_padding">
		<div class="container px-0">
			<div class="heading_container ">
				<h2 class="">Sign Up</h2>

				<c:if test="${not empty param.errorMessage}">
					<p style="color: red;">${param.errorMessage}</p>
				</c:if>


			</div>
		</div>
		<div class="container container-bg">
			<div class="row">
				<div class="col-md-6 col-lg-3 px-0"></div>
				<div class="col-md-6 col-lg-6 px-0">

					<!-- Start Form -->
					<form action="Register" method="POST">

						<div>
							<label>User Name </label> <input type="text" placeholder=""
								name="userName" required />
						</div>

						<div>
							<label>Password </label> <input type="password" placeholder=""
								name="password" id="password" required
								onkeyup="checkReEnterPassword()" />
						</div>

						<div>
							<label>Full Name </label> <input type="text" placeholder=""
								name="fullName" required />
						</div>

						<div>
							<label>Email </label> <input type="email" placeholder=""
								name="email" required />
						</div>

						<!-- Gender Selection (Dropdown) -->
						<div>
							<label>Gender </label> <select name="gender" required>
								<option value="" disabled selected></option>
								<option value="male">Male</option>
								<option value="female">Female</option>
								<option value="other">Other</option>
							</select>
						</div>

						<!-- Interested in (Checkbox) -->
						<!-- Hobbies (Checkbox) -->
<div class="hobbies-section">
    <label class="hobbies-label">Hobbies</label>
    <div class="hobby-checkbox">
        <input type="checkbox" id="hobby1" name="hobby" value="watches">
        <label for="hobby1">Watches</label>
    </div>
    <div class="hobby-checkbox">
        <input type="checkbox" id="hobby2" name="hobby" value="toys">
        <label for="hobby2">Toys</label>
    </div>
    <div class="hobby-checkbox">
        <input type="checkbox" id="hobby3" name="hobby" value="ring">
        <label for="hobby3">Ring</label>
    </div>
</div>


						<div class="d-flex align-items-center justify-content-end">
						
							<button>REGISTER</button>
						</div>
					</form>
					<!-- End Form -->

					<div>
						Already have an account? <a href="login.jsp">Sign In </a>
					</div>
				</div>
				<div class="col-md-6 col-lg-3 px-0"></div>
			</div>
		</div>
	</section>

	<!-- end contact section -->

	<!-- info section -->

	<section class="info_section  layout_padding2-top">
		<div class="social_container">
			<div class="social_box">
				<a href=""> <i class="fa fa-facebook" aria-hidden="true"></i>
				</a> <a href=""> <i class="fa fa-twitter" aria-hidden="true"></i>
				</a> <a href=""> <i class="fa fa-instagram" aria-hidden="true"></i>
				</a> <a href=""> <i class="fa fa-youtube" aria-hidden="true"></i>
				</a>
			</div>
		</div>
		<div class="info_container ">
			<div class="container">
				<div class="row">
					<div class="col-md-6 col-lg-3">
						<h6>ABOUT US</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed doLorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed doLorem ipsum dolor sit amet,</p>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="info_form ">
							<h5>Newsletter</h5>
							<form action="#">
								<input type="email" placeholder="Enter your email"> <input
									type="submit" value="Submit">
							</form>
						</div>
					</div>
					<div class="col-md-6 col-lg-3">
						<h6>NEED HELP</h6>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed doLorem ipsum dolor sit amet, consectetur adipiscing elit,
							sed doLorem ipsum dolor sit amet,</p>
					</div>
					<div class="col-md-6 col-lg-3">
						<h6>CONTACT US</h6>
						<div class="info_link-box">
							<a href=""> <i class="fa fa-map-marker" aria-hidden="true"></i>
								<span> Gb road 123 london Uk </span>
							</a> <a href=""> <i class="fa fa-phone" aria-hidden="true"></i> <span>+01
									12345678901</span>
							</a> <a href=""> <i class="fa fa-envelope" aria-hidden="true"></i>
								<span> demo@gmail.com</span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- footer section -->
		<footer class=" footer_section">
			<div class="container">
				<p>
					&copy; <span id="displayYear"></span> All Rights Reserved By <a
						href="https://html.design/">Free Html Templates</a>
				</p>
			</div>
		</footer>
		<!-- footer section -->

	</section>

	<!-- end info section -->


	<script src="js/jquery-3.4.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
		
	</script>
	<script src="js/custom.js"></script>


</body>

</html>