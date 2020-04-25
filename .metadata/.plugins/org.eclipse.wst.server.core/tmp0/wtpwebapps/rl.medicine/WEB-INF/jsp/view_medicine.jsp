<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="true"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Medicine</title>
</head>
<script type="text/javascript">
function resetHref() {
    location.hash = '/medicine_by_name';
}

</script>
<body>
<!-- <a href="http://localhost:8080/rl.medicine/medicine_by_name" >Medicine By Name </a> <br/><br/>
 -->
 <a href="medicine_by_name"  >Medicine By Name </a> <br/><br/>
 <!-- <a href="http://localhost:8080/rl.medicine/medicine_by_box" >Medicine By Box </a> <br/><br/>
<a href="http://localhost:8080/rl.medicine/medicine_by_expiry_date" >Medicine By Expiry Date </a> <br/><br/>
<a href="http://localhost:8080/rl.medicine/all_medicines" >All Medicines </a> <br/> 
<a href="http://localhost:8080/rl.medicine/all_medicines_by_chemical_name" >All Medicines by chemical name</a> <br/>
 -->
 
 <a href="medicine_by_box" >Medicine By Box </a> <br/><br/>
<a href="medicine_by_expiry_date" >Medicine By Expiry Date </a> <br/><br/>
<a href="all_medicines" >All Medicines </a> <br/> 
<a href="all_medicines_by_chemical_name" >All Medicines by chemical name</a> <br/>
 
</body>
</html>