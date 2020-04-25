<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="rl.medicine.model.DataIdModel"
import="rl.medicine.dto.MedicineDetailDTO" 
	import="rl.medicine.utility.AppConstant"
	import="java.util.List"
	import="java.util.ArrayList"
	import="java.util.HashMap"
	import="java.text.ParseException"
	import="java.text.SimpleDateFormat"
	import="java.util.Date"
%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Medicine By Name</title>
</head>
<style>

.tab {position:absolute;left:150px; }

}

.tab2 {position:absolute;left:600px; }

.tab3 {position:absolute;left:700px; }

}

</style>
<script type="text/javascript">
	
	 
	
</script>
<body>
<br/>
<h1><a href="home"> Home </a></h1>
<br/>

<%
	final List<DataIdModel> medicineList = (List<DataIdModel>) request.getAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST);
	final List<DataIdModel> listBoxList = (List<DataIdModel>) request.getAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST);
	System.out.println(">> Medicine List : "+medicineList);
	System.out.println(">> List Box List : "+listBoxList);
	
	final HashMap<String, List<MedicineDetailDTO>> byBoxValueData = (HashMap<String, List<MedicineDetailDTO>>) request.getAttribute(AppConstant.ATTRIBUTE_MAP_BOX_VALUE);
	
	System.out.println(" >> byBoxValueData "+byBoxValueData);
	
	final List<String> medicineBoxNameList = new ArrayList<String>();
	if(listBoxList != null && !listBoxList.isEmpty()){
	for(DataIdModel model:listBoxList){
		medicineBoxNameList.add(model.getData());
	}
	}
	System.out.println("Medicine Box List : "+medicineBoxNameList);
	
	final List<String> medicineNameList = new ArrayList<String>(medicineList.size());
	for(DataIdModel model:medicineList){
		medicineNameList.add(model.getData());
	}
	System.out.println(" medicineNameList : "+medicineNameList);
%>
<h2>
	<form action="show_medicine_name_list", method="post">
	<br/> Medicine Name : <select name = "medicine_name_list">
							<% for(int i=0; i<medicineNameList.size(); i++) {%>
							<option><%=medicineNameList.get(i) %></option>
							<%} %>
						</select><br/><br/>
						<input type="submit" value= "Show Data"/> 
						
	</form> 						
</h2>

<br/><br/>
<% 
if(medicineBoxNameList != null && !medicineBoxNameList.isEmpty()){
for(int index = 0; index < medicineBoxNameList.size(); index++){ 
	System.out.println(" Box Name : "+medicineBoxNameList.get(index));
%>
<br/>
<h3><font color="red"><b><%= medicineBoxNameList.get(index)%></b></font></h3>
<br/>
<% final List<MedicineDetailDTO> listByExpiryDate = byBoxValueData.get(medicineBoxNameList.get(index));
System.out.println(" listByExpiryDate : "+listByExpiryDate);
	if(listByExpiryDate != null && !listByExpiryDate.isEmpty()){
System.out.println(" listByExpiryDate : "+listByExpiryDate.size());
		final String expiryDate = listByExpiryDate.get(0).getMedicineName();
		System.out.println(" Expiry Date : "+expiryDate);
		%><h3><font color="blue"><%=expiryDate%></font></h3><% 
	 
	for(int num = 0; num < listByExpiryDate.size(); num++){
		final SimpleDateFormat formatter = new SimpleDateFormat("MMMMM-yyyy");
	    final  String expiryDateData = formatter.format(listByExpiryDate.get(num).getMedicineExpiryDate());
		%>
	<p>	<span class="tab1"><font color="black">Strips : </font><font color="green"><b><%=listByExpiryDate.get(num).getStrip() %></b></font></span> 
		<span class="tab2"><font color="black">Left Quantity : </font> <font color="green"><b><%=listByExpiryDate.get(num).getLeftQuantity() %></b></font></span>
		 <span class="tab3"><font color="black">Expiry Date : </font> <font color="green"><b><%=expiryDateData%></b></font></span>
		 </p>
		<% 
	}}
  %>
<%} 
}%>
</body>
</html>