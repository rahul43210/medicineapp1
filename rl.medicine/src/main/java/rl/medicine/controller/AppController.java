package rl.medicine.controller;

import static rl.medicine.utility.AppLogger.appDebug;

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
import org.springframework.web.servlet.ModelAndView;

import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.model.DataIdModel;
import rl.medicine.service.MedicineService;
import rl.medicine.utility.AppCache;
import rl.medicine.utility.AppConstant;

@Controller
public class AppController {
	
	private static final String TAG = AppController.class.getSimpleName();

	@Autowired
	private MedicineService medicineService;
	
	@RequestMapping(value = "/app", method = RequestMethod.GET)
	public ModelAndView getWelcomePage() {
		appDebug(TAG+" getWelcomePage method");
		ModelAndView model = new ModelAndView("welcome");
		 
		return model;
	}
	
	@RequestMapping(value=AppConstant.URL_ADD_MEDICINE_DETAIL, method=RequestMethod.GET)
	public String addMedicineDetail(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" addMedicineDetail");
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
		
		return AppConstant.JSP_ADD_MEDICINE;
	}
	
	@RequestMapping(value = AppConstant.URL_VIEW_MEDICINE_DETAIL, method=RequestMethod.GET)
	public String viewMedicineDetail(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewMedicineDetail");
		
		return AppConstant.JSP_VIEW_MEDICINE;
	}
	
	@RequestMapping(value = AppConstant.URL_MODIFY_MEDICINE_DETAIL, method=RequestMethod.GET)
	public String modifyMedicineDetail(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" modifyMedicineDetail");
		
		return AppConstant.JSP_MODIFY_MEDICINE;
	}
	
	@RequestMapping(value = AppConstant.URL_DELETE_MEDICINE_DETAIL, method=RequestMethod.GET)
	public String deleteMedicineDetail(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" deleteMedicineDetail");
		
		return AppConstant.JSP_DELETE_MEDICINE;
	}
	
	@RequestMapping(value = AppConstant.URL_MEDICINE_BY_NAME, method = RequestMethod.GET)
	public String viewMedicineByName(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		//final HashMap<String, Integer> cacheMedicineName = AppCache.CACHE_MEDICINE_NAME_LIST;
		final List<DataIdModel> medicineList = medicineService.listMedicines();
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineList);
		return AppConstant.JSP_MEDICINE_BY_NAME;
	}
	
	@RequestMapping(value = AppConstant.URL_MEDICINE_BY_BOX, method = RequestMethod.GET)
	public String viewMedicineByBox(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewMedicineByBox ");
		final List<DataIdModel> medicineBoxes = medicineService.listBoxes();
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST, medicineBoxes); 
		
		return AppConstant.JSP_MEDICINE_BY_BOX;
	}
	
	@RequestMapping(value = AppConstant.URL_MEDICINE_BY_DATE, method = RequestMethod.GET)
	public String viewMedicineByDate(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		final List<DataIdModel> medicineList = medicineService.listMedicines();
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineList);
		return AppConstant.JSP_MEDICINE_BY_DATE;
	}

	@RequestMapping(value = AppConstant.URL_SHOW_MEDICINE_NAME_LIST, method = RequestMethod.POST)
	public String viewMedicineByNameList(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewMedicineByNameList ");
		final String selectedMedicineName = httpServletRequest.getParameter(AppConstant.PARAMETER_MEDICINE_SELECTED_NAME);
		appDebug(TAG+" selectedMedicineName "+selectedMedicineName);
		final List<DataIdModel> listBoxList = medicineService.listBoxes();
		final HashMap<String, List<MedicineDetailDTO>> medicineNameListData = medicineService.getMedicineByName(selectedMedicineName);
		final List<DataIdModel> medicineList = medicineService.listMedicines();
		
		
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST, listBoxList);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MAP_BOX_VALUE, medicineNameListData);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineList);
		
		return AppConstant.JSP_MEDICINE_BY_NAME;
	}
	
	@RequestMapping(value = AppConstant.URL_SHOW_MEDICINE_BOX_LIST, method = RequestMethod.POST)
	public String viewMedicineByBoxList(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewMedicineByBoxList ");
		final String selectedBoxName = httpServletRequest.getParameter(AppConstant.PARAMETER_BOX_SELECTED_NAME);
		appDebug(TAG+" selectedBoxName "+selectedBoxName);
		final List<DataIdModel> listBoxList = medicineService.listBoxes();
		appDebug(TAG+" listBoxList : "+listBoxList);
		final HashMap<String, List<MedicineDetailDTO>> medicineBoxListData = medicineService.getMedicineByBox(selectedBoxName);
		final List<DataIdModel> medicineList = medicineService.listMedicines();
		appDebug(TAG+" medicineList : "+medicineList);
		appDebug(TAG+" medicineBoxListData "+medicineBoxListData);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_BOX_LIST, listBoxList);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MAP_MEDICINE_VALUE, medicineBoxListData);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineList);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_SELECTED_BOX_NAME, selectedBoxName);
		
		return AppConstant.JSP_MEDICINE_BY_BOX;
	}
	
	@RequestMapping(value = AppConstant.URL_HOME, method = RequestMethod.GET)
	public String viewHome(ModelMap modelMap) {
		modelMap.addAttribute(AppConstant.WELCOME_PAGE_MESSAGE, "App Home");
		return AppConstant.JSP_APP_HOME;
	}

	@RequestMapping(value = AppConstant.URL_ALL_MEDICINE, method = RequestMethod.GET)
	public String viewAllMedicine(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewAllMedicine");
		final List<MedicineDetailDTO> allMedicineDetailDTO = medicineService.getAllMedicines();
		modelMap.addAttribute(AppConstant.ATTRIBUTE_ALL_MEDICINE, allMedicineDetailDTO);
		return AppConstant.JSP_ALL_MEDICINE;
	}
	
	@RequestMapping(value = AppConstant.URL_ALL_MEDICINE_BY_CHEMICAL_NAME, method = RequestMethod.GET)
	public String viewAllMedicineByChemicalName(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewAllMedicineByChemicalName");
		final List<MedicineDetailDTO> allMedicineDetailDTO = medicineService.getAllMedicinesByChemicalName();
		modelMap.addAttribute(AppConstant.ATTRIBUTE_ALL_MEDICINE, allMedicineDetailDTO);
		return AppConstant.JSP_ALL_MEDICINE_BY_CHEMICAL_NAME;
	}
	
	@RequestMapping(value = AppConstant.URL_MEDICINE_BY_EXPIRY_DATE, method = RequestMethod.POST)
	public String viewMedicineByExpiryDate(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final ModelMap modelMap) {
		appDebug(TAG+" viewMedicineByExpiryDate ");
		final List<DataIdModel> medicineList = medicineService.listMedicines();
		appDebug(TAG+" medicineList : "+medicineList); 
		
		final String selectedMedicineName = httpServletRequest.getParameter(AppConstant.PARAMETER_MEDICINE_SELECTED_NAME);
		appDebug(TAG+" SeletedMedicineName "+selectedMedicineName);
		/*final List<DataIdModel> listBoxList = medicineService.listBoxes();
		appDebug(TAG+" listBoxList : "+listBoxList);*/
		final HashMap<Date, MedicineDetailDTO> medicineExpiryList = medicineService.getMedicineByExpiryDate(selectedMedicineName);// Date, Medicine detail chemical name expiry and count etc.
		
		
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_LIST, medicineList);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MAP_MEDICINE_EXPIRY_LIST, medicineExpiryList);
		modelMap.addAttribute(AppConstant.ATTRIBUTE_MEDICINE_SELECTED_NAME, selectedMedicineName);
		
		return AppConstant.JSP_MEDICINE_BY_DATE;
	}
	
}
