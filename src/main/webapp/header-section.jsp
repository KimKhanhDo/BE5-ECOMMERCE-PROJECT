
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ page import="java.util.HashMap"%>
<%@ page import="entity.ProductInCart"%>
<%@ page import="javax.servlet.http.HttpSession" %> --%>



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

					<!-- <a href="Authentication"> Logout <i class="fa fa-sign-out"
								aria-hidden="true"></i>
							</a>  -->

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