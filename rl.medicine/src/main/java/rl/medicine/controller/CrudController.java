package rl.medicine.controller;

import static rl.medicine.utility.AppLogger.appDebug;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import rl.medicine.model.DataIdModel;
import rl.medicine.model.MedicineDetailModel;
import rl.medicine.service.CrudService;
import rl.medicine.service.MedicineService;
import rl.medicine.utility.AppCache;
import rl.medicine.utility.AppConstant;
import rl.medicine.utility.AppLogger;
import rl.medicine.utility.AppUtility;
import rl.medicine.utility.SuccessMessage;

@Controller
public class CrudController {

	private static final String TAG = CrudController.class.getSimpleName();
	
	@Autowired
	private MedicineService medicineService;
	
	@Autowired
	private CrudService crudService;
	
	@RequestMapping(value = AppConstant.URL_SAVE_MEDICINE, method = RequestMethod.POST)
	public String saveMedicineDetail(final HttpServletRequest request, final HttpServletResponse response, final ModelMap modelMap) throws ParseException {
		final String leftQuantity = request.getParameter(AppConstant.PARAMETER_QUANTITY);
		final String strips = request.getParameter(AppConstant.PARAMETER_STRIP);
		String medicineName = request.getParameter(AppConstant.PARAMETER_MEDICINE_NAME);
		medicineName = medicineName.trim();
		final String medicineBox = request.getParameter(AppConstant.PARAMETER_MEDICINE_BOX);
		final String expiryDate = request.getParameter(AppConstant.PARAMETER_EXPIRY_DATE);
		appDebug(TAG+" Expiry Date : "+expiryDate);
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
	    final  Date expiryDateValue = formatter.parse(expiryDate);
	    
	    final List<DataIdModel> medicineList = medicineService.listMedicines();
	    final List<DataIdModel> boxList = medicineService.listBoxes();
	    final HashMap<String, Integer> medicineListToMap = AppUtility.getListDataTotMapInString(medicineList);
	    final HashMap<String, Integer> boxListToMap = AppUtility.getListDataTotMapInString(boxList);
	    
	    appDebug(TAG+" Left Quantity : "+leftQuantity+", Strips : "+strips+", Medicine Name : "+medicineName+", Medicine Box : "+medicineBox+", Expiry Date : "+expiryDateValue);
	    appDebug(TAG+" Cache medicine name list size : "+medicineListToMap.size());
	    appDebug(TAG+" Name hashCode "+medicineName.hashCode());
	    appDebug(TAG+" Cache medicine box size : "+boxListToMap.size());
	    appDebug(TAG+" Medicine Contains : "+medicineListToMap.get(medicineName));
	    AppLogger.hashMapDebug(medicineListToMap);
	    appDebug(TAG+" Medicine Name id : "+medicineListToMap.get(medicineName.toString())+", Medicine Box id : "+boxListToMap.get(medicineBox));
	    
	    final MedicineDetailModel medicineDetailModel = new MedicineDetailModel();
	    medicineDetailModel.setLeftQuantity(leftQuantity != null? Integer.parseInt(leftQuantity):0);
	    medicineDetailModel.setStrip(strips != null?Integer.parseInt(strips):0);
	    medicineDetailModel.setMedicineBox(medicineBox);
	    medicineDetailModel.setMedicineExpiryDate(expiryDateValue);
	    medicineDetailModel.setMedicineName(medicineName);
	    medicineDetailModel.setMedicineBoxId(boxListToMap.get(medicineBox) != null?Integer.parseInt(boxListToMap.get(medicineBox).toString()):-1);
	    medicineDetailModel.setMedicineNameId(medicineListToMap.get(medicineName) != null?Integer.parseInt(medicineListToMap.get(medicineName).toString()):-1);
	    
	    
	    
	    appDebug(TAG+" Model for save : "+medicineDetailModel);
	    
	    final SuccessMessage successMessage = crudService.saveMedicineDetail(medicineDetailModel);
	    		if(successMessage.getCode() == 200) {
	    			modelMap.addAttribute(AppConstant.CRUD_RESULT_PAGE_MESSAGE, "Save successfully.");
	    		}else {
	    			modelMap.addAttribute(AppConstant.CRUD_RESULT_PAGE_MESSAGE, "Save unsuccess.");
	    		}
	    		appDebug(TAG+" Success response : "+successMessage);
	    		
	    		final List<DataIdModel> medicineNames = medicineService.listMedicines();
	    		final List<DataIdModel> medicineBoxes = medicineService.listBoxes();
	    		
	    		final HashMap<String, Integer> cacheMedicineList = AppCache.CACHE_MEDICINE_NAME_LIST;
	    		cacheMedicineList.clear();
	    		for(DataIdModel dataIdModel : medicineNames) {
	    			cacheMedicineList.put(dataIdModel.getData(), dataIdModel.getId());
	    		}
	    		
	    		final HashMap<String, Integer> cacheMedicineBoxList = AppCache.CACHE_MEDICINE_BOX_LIST;
	    		cacheMedicineBoxList.clear();
	    		for(DataIdModel dataIdModel : medicineBoxes) {
	    			cacheMedicineBoxList.put(dataIdModel.getData(), dataIdModel.getId());
	    		}
	    		
	    		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineNames);
	    		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST, medicineBoxes);

	    		
		//return AppConstant.JSP_APP_HOME;
	    		return AppConstant.JSP_ADD_MEDICINE;
	}
}
