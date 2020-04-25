<%@page import="rl.medicine.utility.AppLogger"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="rl.medicine.model.DataIdModel"
	import="rl.medicine.dto.MedicineDetailDTO"
	import="rl.medicine.utility.AppConstant" import="java.util.List"
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
<script type="text/javascript">
	
</script>
<body>
	<br />
	<h1>
		<a href="home"> Home </a>
	</h1>
	<br />

	<%
		String selectedMedicineBoxName = (String) request
			.getAttribute(AppConstant.ATTRIBUTE_MEDICINE_SELECTED_BOX_NAME);
			if (selectedMedicineBoxName == null) {
		selectedMedicineBoxName = "";
			}else{
		selectedMedicineBoxName = "Selected Box : "+selectedMedicineBoxName;
			}

			AppLogger.appDebug("Selected Medicine Name : " + selectedMedicineBoxName);

			final List<DataIdModel> medicineList = (List<DataIdModel>) request
			.getAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST);
			final List<DataIdModel> listBoxList = (List<DataIdModel>) request
			.getAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST);
			System.out.println(">> Medicine List : " + medicineList);
			System.out.println(">> List Box List : " + listBoxList);

			final HashMap<String, List<MedicineDetailDTO>> byMedicineNameValue = (HashMap<String, List<MedicineDetailDTO>>) request
			.getAttribute(AppConstant.ATTRIBUTE_MAP_MEDICINE_VALUE);

			System.out.println(" >> byBoxValueData " + byMedicineNameValue);

			final List<String> medicineBoxNameList = new ArrayList<String>();
			if (listBoxList != null && !listBoxList.isEmpty()) {
		for (DataIdModel model : listBoxList) {
			medicineBoxNameList.add(model.getData());
		}
			}
			System.out.println("Medicine Box List : " + medicineBoxNameList);

			final List<String> medicineNameList = new ArrayList<String>();
			if (medicineList != null && !medicineList.isEmpty()) {
		for (DataIdModel model : medicineList) {
			medicineNameList.add(model.getData());
		}
			}
			System.out.println(" medicineNameList : " + medicineNameList);
	%>
	<h2>
		<form action="show_medicine_box_list" , method="post">
			<br /> Medicine Box : <select name="medicine_box_list">
			
				<%
					for (int i = 0; i < medicineBoxNameList.size(); i++) {
				%>
				<option><%=medicineBoxNameList.get(i)%></option>
				<%
					}
				 %>
			</select><br /> <br /> <input type="submit" value="Show Data" />

		</form>
	</h2>
	<h2>
		<b><font color="red"><%=selectedMedicineBoxName%></font></b>
	</h2>
	<br />
	<br />
	<%
		if (medicineNameList != null && !medicineNameList.isEmpty()) {
			for (int index = 0; index < medicineNameList.size(); index++) {
				System.out.println(" Medicine Name : " + medicineNameList.get(index));
	%>
	<br />
	<h3>
		<%-- <font color="red"><b><%=medicineNameList.get(index)%></b></font> --%>
	</h3>
	<br />
	<%
		final List<MedicineDetailDTO> listByExpiryDate = byMedicineNameValue
						.get(medicineNameList.get(index));
				System.out.println(" listByExpiryDate : " + listByExpiryDate);
				if (listByExpiryDate != null && !listByExpiryDate.isEmpty()) {
					System.out.println(" listByExpiryDate : " + listByExpiryDate.size());
					final String expiryDate = listByExpiryDate.get(0).getMedicineName();
					System.out.println(" Expiry Date : " + expiryDate);
	%>
	<h3>
		<font color="blue"><%=expiryDate%></font>
	</h3>
	<%
		for (int num = 0; num < listByExpiryDate.size(); num++) {
		final SimpleDateFormat formatter = new SimpleDateFormat("MMMMM-yyyy");
	    final  String expiryDateData = formatter.format(listByExpiryDate.get(num).getMedicineExpiryDate());
	%>
	
	<%-- 
	<p>
		<span class="tab">Strips : <%=listByExpiryDate.get(num).getStrip()%></span>
		<span class="tab2">Left Quantity : <%=listByExpiryDate.get(num).getLeftQuantity()%></span>
		<span class="tab3">Expiry Date : <%=listByExpiryDate.get(num).getMedicineExpiryDate()%></span>
	</p> --%>
	<p>	<span class="tab1"><font color="black">Strips : </font><font color="green"><b><%=listByExpiryDate.get(num).getStrip() %></b></font></span> 
		<span class="tab2"><font color="black">Left Quantity : </font> <font color="green"><b><%=listByExpiryDate.get(num).getLeftQuantity() %></b></font></span>
		 <span class="tab3"><font color="black">Expiry Date : </font> <font color="green"><b><%=expiryDateData%></b></font></span>
		 </p>
	<%
		}
				}
	%>
	<%
		}
		}
	%>
</body>
</html>