<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>App Home</title>
<head>
  </head>
</head>
<body>

<h2> <%=request.getAttribute("welcome_page_message") %></h2>

<h3>
<br/> <a href="add_medicine_detail" >  Add Medicine </a>
<br/> <a href="view_medicine_detail"> View Medicine </a>
<br/> <a href="modify_medicine_detail"> Modify Medicine </a>
<br/> <a href="delete_medicine_detail"> Delete Medicine </a>



</h3>
</body>
</html>