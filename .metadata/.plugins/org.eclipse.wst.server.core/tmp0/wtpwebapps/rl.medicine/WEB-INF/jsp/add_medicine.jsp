<%@page import="rl.medicine.utility.AppLogger"%>
<%@page import="rl.medicine.utility.AppConstant"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Medicine</title>
</head>
<%@ page import="java.util.List"
import="java.util.ArrayList" 
  import="rl.medicine.model.DataIdModel"%>

<%
	final String TAG = "add_medicine.jsp ";
	final Object data =  request.getAttribute("attribute_medicine_list");
	final List<DataIdModel> medicineList = (List<DataIdModel>)data;
	final List<String> medicineNameList = new ArrayList<String>(medicineList.size());
	
	for(DataIdModel model:medicineList){
		medicineNameList.add(model.getData());
	} 
	
	
	AppLogger.appDebug(TAG+" medicineNameList "+medicineNameList);
	
	final List<DataIdModel> medicineBox = (List<DataIdModel>) request.getAttribute("attribute_medicine_box");
	final List<String> boxesNameList = new ArrayList<String>(medicineBox.size());
	
	for(DataIdModel model:medicineBox){
		boxesNameList.add(model.getData());
	} 
	AppLogger.appDebug(TAG+" boxesNameList "+boxesNameList);
	String messageResponse = (String) request.getAttribute(AppConstant.CRUD_RESULT_PAGE_MESSAGE);
	AppLogger.appDebug(TAG+" Message Response "+messageResponse);
	
	if(messageResponse == null){
		messageResponse = "";
	}else{
		messageResponse = "Op response : "+messageResponse;
	}
		 
%>
<script type="text/javascript">
	function save_medicine_detail(button) {
		console.log(" Save medicine detail function ");
		
	}
	

	
	$('.datepicker').datepicker({
    format: 'mm/yyyy'
});

</script>
<body>
<h2> <font color="red"><b><%=messageResponse%></b></font></h2><br/>

<a href="home"> Home </a>
<br/>
<br/>

	<h3>
		<form action="save" method="post">
		<br /> Medicine Names : <select name="parameter_medicine_name"
			id="parameter_medicine_name">
		 <% for(int i=0; i<medicineNameList.size();i++) {%>
				<option><%=medicineNameList.get(i)%></option>
				<%} %>
		</select> <br /> Expiry Date : <!-- <input class ="datepicker" type="date" name="parameter_expiry_date"
			id="parameter_expiry_date" /> -->
			<input  type="month" name="parameter_expiry_date"
			id="parameter_expiry_date" />
			<br/> Strip <input type="number" name="parameter_strip"/> 
			<br/> Left Quantity <input type="number" name="parameter_quantity"/>
			<br /> Box Place : <select name="parameter_medicine_box"
			id="parameter_medicine_box">
			 <% for(int i=0; i<boxesNameList.size();i++) {%>
				<option><%=boxesNameList.get(i)%></option>
				<%} %>
			</select> 
			<!-- onclick="save_medicine_detail(this)" -->
			<br /> <br /> <input type="submit" name="save"
			id="save" value="Save"  />
			
			</form>
	</h3>

</body>
</html>