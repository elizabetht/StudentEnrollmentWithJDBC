<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Enrollment Login</title>
</head>
<body>
<form method="POST" action="StudentController">
	
	<br>
	<br>
	<center><input type="hidden" name="pageName" value="login"></center><br>
	<center><input type="text" name="userName" placeholder="User Name"></center><br>
	<center><input type="password" name="password" placeholder="Password"></center><br>
	<center><input type="submit" name="login" value="Login"></center>	
	</form>
</body>
</html>