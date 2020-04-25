<%@page import="rl.medicine.utility.AppUtility"%>
<%@page import="rl.medicine.utility.AppLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="rl.medicine.model.DataIdModel"
	import="rl.medicine.dto.MedicineDetailDTO"
	import="rl.medicine.utility.AppConstant" 
	import="java.util.List"
	import="java.util.ArrayList" 
	import="java.util.Set"
	import="java.util.HashMap"
	import="java.text.ParseException" import="java.text.SimpleDateFormat"
	import="java.util.Date"
	import="java.time.LocalDateTime"
	import="java.time.ZoneId"
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Medicine</title>
</head>

<style>
.tab {
	position: absolute;
	left: 150px;
}

}
.tab2 {
	position: absolute;
	left: 400px;
}

.tab3 {
	position: absolute;
	left: 600px;
}


}
</style>
<%
	final String JSP_TAG = "all_medicine.jsp";

	final List<MedicineDetailDTO> allMedicines = (List<MedicineDetailDTO>) 
			request.getAttribute(AppConstant.ATTRIBUTE_ALL_MEDICINE);
	
 %>


<body>
<h1> <font color="black"><b>All Medicine </b></font></h1><br/>
<%
	for(int index = 0; index < allMedicines.size(); index++){
	final MedicineDetailDTO medicineDetailName = allMedicines.get(index);
	final String medicineChemicalName = medicineDetailName.getMedicineChemicalName();
	final MedicineDetailDTO medicineCountDetail = medicineDetailName.getMedicineDetailDTO();
	final Set<String> medicineNames = medicineCountDetail.getMedicineNames();
	String medicineName = "";
	for(String medicineNameData : medicineNames){
		if(medicineName.isEmpty()){
			medicineName = medicineNameData;
		}else{
			medicineName = medicineName+", "+medicineNameData;
		}
	}
 	AppLogger.appDebug(JSP_TAG+" medicineCountDetail "+medicineCountDetail);
	final int totalStrips = medicineCountDetail.getStrip();
	final int totalLeftQuantity = medicineCountDetail.getLeftQuantity();
	final Date minimumExpiryDate =  medicineCountDetail.getMedicineExpiryDate();
	final Date maximumExpiryDate = medicineCountDetail.getMaxExpiryDate();
	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMMM-yyyy");
	final String minimumExpiryDateString = simpleDateFormat.format(minimumExpiryDate);
	final String maximumExpiryDateString = simpleDateFormat.format(maximumExpiryDate);
	int totalTablets = 0;
	if(medicineName.contains("15")){
		AppLogger.appDebug(JSP_TAG+" medicine Name "+medicineName+" is 15 tab");
		totalTablets = totalStrips * 15;
	}else if(medicineName.contains("6")){
		AppLogger.appDebug(JSP_TAG+" medicine Name "+medicineName+" is 6 tab");
		totalTablets = totalStrips * 6;
	}else{
		totalTablets = totalStrips * 10;
	}
	
	AppLogger.appDebug(JSP_TAG+" total  Tablets : "+totalTablets+", total Quantity : "+medicineCountDetail.getTotalQuantity()+" for chemical name : "+medicineChemicalName);
	totalTablets = medicineCountDetail.getTotalQuantity();
	totalTablets = totalTablets + totalLeftQuantity;
	LocalDateTime today =  LocalDateTime.now();     //Today
  
System.out.println(today);          //2018-07-14
 
 long days = 0l;
 if(medicineName.toLowerCase().contains("serlift")){
 AppLogger.appDebug(JSP_TAG+" Medicine Name : "+medicineName+" is divide by 2 ");
 	days = (long)totalTablets / 2;
 }else{
 	days = totalTablets;
 }
LocalDateTime sameDayNextDateYear = today.plusDays(days);     //2019-07-14
 
//Get Date from LocalDateTime 
//Not recommended. Use when you have no options in legacy code.
Date currentDatePlusOneDay = Date.from(sameDayNextDateYear.atZone(ZoneId.systemDefault()).toInstant());
 
	
	
	String nextTillDate = new String();
	Date dateOfUseFinal = new Date();
	final SimpleDateFormat simpleDateFormatForDay = new SimpleDateFormat("dd-MMMMM-yyyy");
	nextTillDate = simpleDateFormatForDay.format(currentDatePlusOneDay);
	
	
	%>
	<h3><font color = "dark_red"><b> <%= medicineChemicalName%></b></font></h2> 
	
		
		<table name = "medicine_data"  border="1">
			<tr>
				<th><font color="#EA8D3F">Medicine Name </font></th>
				<th><font color="#EA8D3F">Total Strips </font></th>
				<th><font color="#EA8D3F">Left Quantity </font></th>
				<th><font color="#EA8D3F">Minimum Expiry Date </font></th>
				<th><font color="#EA8D3F">Maximum Expiry Date </font></th>
				<th><font color="#EA8D3F">Total Tablet counts </font></th>
				<th><font color="#EA8D3F">Next date as dose current from Today </font></th>
				
			</tr>
			<tr>
			<td><b><font color="blue"><%= medicineName%></font></b></td>
			<td><b><font color="blue"><%= totalStrips%></font></b></td>
			<td><b><font color="blue"><%= totalLeftQuantity%></font></b></td>
			<td><b><font color="blue"><%= minimumExpiryDateString%></font></b></td>
			<td><b><font color="blue"><%= maximumExpiryDateString%></font></b></td>
			<td><b><font color="blue"><%= totalTablets %></font></b></td>
			<td><b><font color="blue"><%= nextTillDate%></font></b></td>
			</tr>
		</table>
		<br/>
	<% 
	 
	}// end of medicine Name for loop
 %>
</body>
</html>