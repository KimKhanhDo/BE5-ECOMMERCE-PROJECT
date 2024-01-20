<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

  <title>
    Giftos
  </title>

  <!-- slider stylesheet -->
    <link href="css/general.css" type="text/css" rel="stylesheet" />
  
  <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" />

  <!-- bootstrap core css -->
  <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />

  <!-- Custom styles for this template -->
  <link href="css/style.css" rel="stylesheet" />
  <!-- responsive style -->
  <link href="css/responsive.css" rel="stylesheet" />
  

</head>

<body> 
 
  <section class="contact_section layout_padding">
    <div class="container px-0">
      <div class="heading_container ">
        <h2 class="">
        Create Account
        </h2>
      </div>
    </div>
    <div class="container container-bg">
      <div class="row">
         <div class="col-md-6 col-lg-3 px-0"></div>
       
         
        <div class="col-md-6 col-lg-6 px-0">
   	<c:if test="${param.username != null && isUsernameRegistered}">
          <form action="Register" method="post" id="usernameForm">
	         <div>
	            <label>Username </label>
	            <input type="text" placeholder="Username" name="username" minlength="6" autofocus required/>
	            <div class="d-flex "><button>NEXT</button></div>
		             <c:if test="${param.username != null && isUsernameRegistered}">
		             	<span style="color: red">${usernameError}</span>             
		             </c:if>
	           </div>
	           
	           <div class="question-sign-in" >Already have an account? <a href="login.jsp">Sign In </a></div>
	           <div class="question-sign-in" >Back to Home Page <a href="Home">Home </a></div>	
	           <c:out value="param.username: ${param.username}" />
<c:out value="isUsernameRegistered: ${isUsernameRegistered}" />
           </form>  
	</c:if>
	
	<!-- Email Form -->
        <c:if test="${not empty param.username && !isUsernameRegistered && empty param.email}">
            <form action="Register" method="post" id="otherInputsForm">
                <span><strong>Your Username:</strong> ${enteredUsername}</span>
				<a href="register.jsp">change</a>
					<input type="hidden" name="username" value="${enteredUsername}" />
				<div>
					<label>Email </label>
					<input class="register-input" type="email" placeholder="sample@email.com" name="email" required/>
				<div class="d-flex">
					<button type="submit">NEXT</button>
				</div>
				</div>
					<c:if test="${param.email != null && !isEmailValid}">
						<span style="color: red">Email is invalid</span>
					</c:if>
			</form>
		</c:if>
		
        <c:if test="${not empty param.username && not empty param.email && !isUsernameRegistered && isEmailValid}">
             <div>            
          <form  action="Register" method="post" id="otherInputsForm">
             <input type="hidden" name="username" value="${enteredUsername}" />
   			 <input type="hidden" name="email" value="${enteredEmail}" />                
	            <label>Password </label>             
		          <input class="register-input" type="password" placeholder="At least 6 characters" name="password" minlength="6" id="input1" required/>
		          <div class="input-with-checkbox">
		         	  <input class = "show-password" type="checkbox" onclick="togglePasswordVisibility('input1')">
		              <label class = "label-show-password">Show Password</label>	              
              	  </div>
            </div>
             <div>
	            <label>Re - enter Password </label>
	              <input class="register-input" type="password" placeholder="" name="password" minlength="6" id="input2" required/>
	              <div class="input-with-checkbox">
		              <input class = "show-password" type="checkbox" onclick="togglePasswordVisibility('input2')">
		              <label class = "label-show-password">Show Password</label>
	              </div>
            </div>
            <div id="message"></div>
            <div>
            <label>Hobby</label>
            	<input type="checkbox" id="eat" name="hobby" value="eat">
					<label for="eat"> Eat</label><br>
					<input type="checkbox" id="sleep" name="hobby" value="sleep">
					<label for="sleep">Sleep</label><br>
					<input type="checkbox" id="dance" name="hobby" value="dance">
					<label for="dance"> Dance</label><br>
            </div>
            <div>
            <label>Gender</label>
           			<input type="radio" id="male" name="gender" value="male">
					<label for="male">male</label><br>
					<input type="radio" id="css" name="gender" value="female">
					<label for="female">Female</label><br>
					<input type="radio" id="other" name="gender" value="other">
					<label for="other">Other</label>
            </div>
		                        
            
          	<button>Register</button>
          	<div class="question-sign-in" >Already have an account? <a href="login.jsp">Sign In </a></div>	         
            </form>
        </c:if>         
           
        </div>
        <div class="col-md-6 col-lg-3 px-0">
         </div>
      </div>
    </div>
  </section>

   <script>
        // Get references to the input elements and the message element
        const input1 = document.getElementById('input1');
        const input2 = document.getElementById('input2');
        const message = document.getElementById('message');

        // Add an event listener to input2 to check the values
        input2.addEventListener('input', function() {
            if (input1.value === input2.value) {
                message.textContent = 'Passwords match!';
                message.style.color = 'green';
            } else {
                message.textContent = 'Passwords do not match!';
                message.style.color = 'red';
            }
        });
    </script>
<script>
function togglePasswordVisibility(inputId) {
  var x = document.getElementById(inputId);
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password";
  }
}

// Call the function with the appropriate input IDs
document.getElementById("showPassword1").addEventListener("click", function() {
  togglePasswordVisibility("input1");
});

document.getElementById("showPassword2").addEventListener("click", function() {
  togglePasswordVisibility("input2");
});
</script>
  <script src="js/jquery-3.4.1.min.js"></script>
  <script src="js/bootstrap.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js">
  </script>
  <script src="js/custom.js"></script>


</body>

</html>