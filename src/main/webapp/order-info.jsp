<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <!-- Basic -->
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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

        ::placeholder {
            font-size: 12px;
            /* Adjust the font size as needed */
        }
    </style>

    <!-- slider stylesheet -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

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
                
                 
    <h2>Your Order Details</h2>
    <table border="1" >
        <tr>
            <th>Product ID</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Subtotal</th>
        </tr>
        <c:forEach items="${orderDetail}" var="item">
            <tr>
                <td>${item.productId}</td>
                <td>${item.quantity}</td>
                <td>${item.price}</td>
                <td>${item.subTotal}</td>
            </tr>
        </c:forEach>
    </table>
    
    <div style="margin-top: 20px; font-weight: bold;">
    Total: $${totalPrice}
    </div>

                </div>
            </div>
        </section>

        <script src="js/jquery-3.4.1.min.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
        <script src="js/custom.js"></script>
    </div>
</body>

</html>
