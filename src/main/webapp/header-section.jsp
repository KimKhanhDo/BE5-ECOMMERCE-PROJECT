
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header section starts -->
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

				<!-- Send http request to Home with payload categoryID=???? -->
				<c:forEach items="${categories}" var="category">
					<li class="nav-item"><a class="nav-link"
						href="Home?action=SHOW_PRODUCT_BY_CATEGORY&categoryId=${category.id}">
							${category.name} </a></li>
				</c:forEach>
			</ul>
			<!-- end category menu -->

			<!-- Login/ Logout section -->
			<div class="user_option">
				<c:if test="${not empty sessionScope.user}">
					<i class="fa fa-user" aria-hidden="true" style="margin-right: 5px;"></i>
					<span style="margin-right: 5px;">
						${sessionScope.user.userName} &nbsp;</span>

					<form action="Authentication?action=LOGOUT" method="post"
						style="display: inline; margin-right: 20px;">
						<input type="hidden" name="logout" value="true">
						<button type="submit"
							style="background: none; border: none; padding: 0; margin: 0; cursor: pointer;">
							Logout <i class="fa fa-sign-out" aria-hidden="true"></i>
						</button>
					</form>
				</c:if>

				<c:if test="${empty sessionScope.user}">
					<a href="login.jsp"> <i class="fa fa-user" aria-hidden="true"></i>
						<span> Login </span></a>
				</c:if>
				<!-- End Login/ Logout section -->

				<br> <a href="CartController?ACTION=VIEW_CART"> <i class ="fa
						fa-shopping-bag" >
					</i> ${empty sessionScope.cart ? 0 : (empty sessionScope.cart.getItems() ? 0 : sessionScope.cart.getItems().size())}
					</a>

				<!-- search section -->
				<div class="search_section">
					<form action="Home" method="get" class="form-inline">
						<input type="text" name="searchValue" placeholder="Search" /> <input
							type="hidden" name="action" value="SEARCH" />
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

	<!-- slider section -->

		<section class="slider_section">
			<div class="slider_container">
				<div id="carouselExampleIndicators" class="carousel slide"
					data-ride="carousel">
					<div class="carousel-inner">
						<div class="carousel-item active">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-7">
										<div class="detail-box">
											<h1>
												Welcome To Our <br> Gift Shop
											</h1>
											<p>Sequi perspiciatis nulla reiciendis, rem, tenetur
												impedit, eveniet non necessitatibus error distinctio
												mollitia suscipit. Nostrum fugit doloribus consequatur
												distinctio esse, possimus maiores aliquid repellat beatae
												cum, perspiciatis enim, accusantium perferendis.</p>
											<a href=""> Contact Us </a>
										</div>
									</div>
									<div class="col-md-5 ">
										<div class="img-box">
											<img src="images/slider-img.png" alt="" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item ">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-7">
										<div class="detail-box">
											<h1>
												Welcome To Our <br> Gift Shop
											</h1>
											<p>Sequi perspiciatis nulla reiciendis, rem, tenetur
												impedit, eveniet non necessitatibus error distinctio
												mollitia suscipit. Nostrum fugit doloribus consequatur
												distinctio esse, possimus maiores aliquid repellat beatae
												cum, perspiciatis enim, accusantium perferendis.</p>
											<a href=""> Contact Us </a>
										</div>
									</div>
									<div class="col-md-5 ">
										<div class="img-box">
											<img src="images/slider-img.png" alt="" />
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="carousel-item ">
							<div class="container-fluid">
								<div class="row">
									<div class="col-md-7">
										<div class="detail-box">
											<h1>
												Welcome To Our <br> Gift Shop
											</h1>
											<p>Sequi perspiciatis nulla reiciendis, rem, tenetur
												impedit, eveniet non necessitatibus error distinctio
												mollitia suscipit. Nostrum fugit doloribus consequatur
												distinctio esse, possimus maiores aliquid repellat beatae
												cum, perspiciatis enim, accusantium perferendis.</p>
											<a href=""> Contact Us </a>
										</div>
									</div>
									<div class="col-md-5 ">
										<div class="img-box">
											<img src="images/slider-img.png" alt="" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="carousel_btn-box">
						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <i class="fa fa-arrow-left"
							aria-hidden="true"></i> <span class="sr-only">Previous</span>
						</a> <img src="images/line.png" alt="" /> <a
							class="carousel-control-next" href="#carouselExampleIndicators"
							role="button" data-slide="next"> <i class="fa fa-arrow-right"
							aria-hidden="true"></i> <span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</section>

		<!-- end slider section -->
	</div>
	<!-- end hero area -->