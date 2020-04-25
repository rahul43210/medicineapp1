/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.93
 * Generated at: 2020-04-09 10:47:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import rl.medicine.utility.AppLogger;
import rl.medicine.model.DataIdModel;
import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.utility.AppConstant;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class medicine_005fby_005fbox_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"ISO-8859-1\">\r\n");
      out.write("<title>Medicine By Name</title>\r\n");
      out.write("</head>\r\n");
      out.write("<style>\r\n");
      out.write(".tab {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 150px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write(".tab2 {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 400px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".tab3 {\r\n");
      out.write("\tposition: absolute;\r\n");
      out.write("\tleft: 600px;\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<h1>\r\n");
      out.write("\t\t<a href=\"home\"> Home </a>\r\n");
      out.write("\t</h1>\r\n");
      out.write("\t<br />\r\n");
      out.write("\r\n");
      out.write("\t");

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
	
      out.write("\r\n");
      out.write("\t<h2>\r\n");
      out.write("\t\t<form action=\"show_medicine_box_list\" , method=\"post\">\r\n");
      out.write("\t\t\t<br /> Medicine Box : <select name=\"medicine_box_list\">\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t");

					for (int i = 0; i < medicineBoxNameList.size(); i++) {
				
      out.write("\r\n");
      out.write("\t\t\t\t<option>");
      out.print(medicineBoxNameList.get(i));
      out.write("</option>\r\n");
      out.write("\t\t\t\t");

					}
				 
      out.write("\r\n");
      out.write("\t\t\t</select><br /> <br /> <input type=\"submit\" value=\"Show Data\" />\r\n");
      out.write("\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</h2>\r\n");
      out.write("\t<h2>\r\n");
      out.write("\t\t<b><font color=\"red\">");
      out.print(selectedMedicineBoxName);
      out.write("</font></b>\r\n");
      out.write("\t</h2>\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<br />\r\n");
      out.write("\t");

		if (medicineNameList != null && !medicineNameList.isEmpty()) {
			for (int index = 0; index < medicineNameList.size(); index++) {
				System.out.println(" Medicine Name : " + medicineNameList.get(index));
	
      out.write("\r\n");
      out.write("\t<br />\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t");
      out.write("\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t<br />\r\n");
      out.write("\t");

		final List<MedicineDetailDTO> listByExpiryDate = byMedicineNameValue
						.get(medicineNameList.get(index));
				System.out.println(" listByExpiryDate : " + listByExpiryDate);
				if (listByExpiryDate != null && !listByExpiryDate.isEmpty()) {
					System.out.println(" listByExpiryDate : " + listByExpiryDate.size());
					final String expiryDate = listByExpiryDate.get(0).getMedicineName();
					System.out.println(" Expiry Date : " + expiryDate);
	
      out.write("\r\n");
      out.write("\t<h3>\r\n");
      out.write("\t\t<font color=\"blue\">");
      out.print(expiryDate);
      out.write("</font>\r\n");
      out.write("\t</h3>\r\n");
      out.write("\t");

		for (int num = 0; num < listByExpiryDate.size(); num++) {
		final SimpleDateFormat formatter = new SimpleDateFormat("MMMMM-yyyy");
	    final  String expiryDateData = formatter.format(listByExpiryDate.get(num).getMedicineExpiryDate());
	
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\t<p>\t<span class=\"tab1\"><font color=\"black\">Strips : </font><font color=\"green\"><b>");
      out.print(listByExpiryDate.get(num).getStrip() );
      out.write("</b></font></span> \r\n");
      out.write("\t\t<span class=\"tab2\"><font color=\"black\">Left Quantity : </font> <font color=\"green\"><b>");
      out.print(listByExpiryDate.get(num).getLeftQuantity() );
      out.write("</b></font></span>\r\n");
      out.write("\t\t <span class=\"tab3\"><font color=\"black\">Expiry Date : </font> <font color=\"green\"><b>");
      out.print(expiryDateData);
      out.write("</b></font></span>\r\n");
      out.write("\t\t </p>\r\n");
      out.write("\t");

		}
				}
	
      out.write('\r');
      out.write('\n');
      out.write('	');

		}
		}
	
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
