<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Student Enrollment Signup</title>
</head>
<body>
	
	<form name="signupForm" method="POST" action='StudentController'>		
		<% Object userExists = request.getAttribute("userNameExists"); %>
		<br>
		<center><h4>New User Signup</h4></center>
		<center><input type="hidden" name="pageName" value="signup"></center><br>
		<!-- <center><input type="text" name="userNameExists" value="<%=request.getAttribute("userNameExists") %>"></center><br>-->
		<center><input type="text" name="userName" placeholder="User Name"></center><br>
		<center><input type="password" name="password" placeholder="Password"></center><br>
		<center><input type="text" name="firstName" placeholder="First Name"></center><br>
		<center><input type="text" name="lastName" placeholder="Last Name"></center><br>
		<center><input type="text" name="dateOfBirth" placeholder="Date of Birth(MM/dd/YYYY)"></center><br>
		<center><input type="text" name="emailAddress" placeholder="Email Address"></center><br>
		<center><input type="submit" name="signup" value="Signup"></center>
		<%
		if(request.getParameter("signup") != null) {			
			if(request.getAttribute("userNameExists").equals(true)) {
				%><script>
				alert("User Already Exists");
				</script><%
			}
		}
		%>
		
		<center><h4>Already an user? <a href="login.jsp">Log in</a></h4></center>
	</form>
	
</body>
</html>