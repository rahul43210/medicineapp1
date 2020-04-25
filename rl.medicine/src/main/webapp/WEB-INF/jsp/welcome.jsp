<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<!DOCTYPE html> --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="true" %> 
<!DOCTYPE html PUBLIC "-// W3C// DTD HTML 4.01  
 Transitional// EN" "http:// www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function submit_form(value){
		if(value.id == "login"){
			console.log(" Login action. ");
			document.getElementById("action_value").value="Login";
		}
		if(value.id == "signup"){
			console.log(" Sign up action. ");
			document.getElementById("action_value").value="Signup";
		}
		document.forms[0].submit();
	}
</script>
<body>

<h1> Welcome </h1>
<form action="login" method="post" >
<h3> User name : <input type="text" name="LoginUsername"/> </h3><br/>
<h3> Password : <input type="password" name="LoginPassword"/></h3><br/>
<input type="button" value = "Login" id="login" onclick="submit_form(this)"/><br/>
<input type="button" value = "Sign Up" id="signup" onclick="submit_form(this)"/><br/>
<input type="hidden" name="action_value" id="action_value"/>
</form>
</body>
</html>