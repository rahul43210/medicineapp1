package rl.medicine.daoimpl;

import static rl.medicine.utility.AppLogger.appDebug;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rl.medicine.dao.AppDao;
import rl.medicine.database.MedicineDatabaseOperation;
import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.model.DataIdModel;

@Repository
public class AppDaoImpl implements AppDao{

	private static final String TAG = AppDaoImpl.class.getSimpleName();
	
	@Autowired
	private MedicineDatabaseOperation medicineDatabaseOperation;
	
	public List<DataIdModel> getMedicineList() {
		appDebug(TAG+" getMedicineList ");
		final List<DataIdModel> medicineList = medicineDatabaseOperation.getMedicineList();
		appDebug("Medicine List : "+medicineList);
		/*final List<String> medicineList = new ArrayList<>();
		jdbcRegister.connectDatabase();
		
		medicineList.add("Serlift 100mg");
		medicineList.add("Serlift 50mg");
		medicineList.add("Serlift 25mg");
		medicineList.add("Sizodon 0.5mg");
		medicineList.add("Petril 0.5MD");
		medicineList.add("Petril 0.5");
		medicineList.add("Petril 0.25MD");
		medicineList.add("Saridon");
		medicineList.add("Nor TZ");*/
		
		return medicineList;
	}

	@Override
	public List<DataIdModel> getMedicineBoxes() {
		final List<DataIdModel> medicineBoxes = medicineDatabaseOperation.getBoxesList();
		/*final List<String> medicineBoxes = new ArrayList<>();
		medicineBoxes.add("B1");
		medicineBoxes.add("B2");
		medicineBoxes.add("B3");
		medicineBoxes.add("B4");
		medicineBoxes.add("B5");
		medicineBoxes.add("B6");
		medicineBoxes.add("B7");
		medicineBoxes.add("B8");
		medicineBoxes.add("B9");*/
		return medicineBoxes;
	}

	@Override
	public HashMap<String, List<MedicineDetailDTO>> getMedicineByName(String selectedMedicineName) {
		final HashMap<String, List<MedicineDetailDTO>> medicineDetails = medicineDatabaseOperation.getMedicineByName(selectedMedicineName);
		return medicineDetails;
	}

	@Override
	public HashMap getMedicineByBox(String selectedBoxName) {
		final HashMap<String, List<MedicineDetailDTO>> boxMapDetails = medicineDatabaseOperation.getMedicineByBox(selectedBoxName);
		return boxMapDetails;
	}

	@Override
	public List getAllMedicinesList() {
		final String METHOD_TAG = TAG+" getAllMedicinesList";
		appDebug(METHOD_TAG+" method");
		final List<MedicineDetailDTO> allMedicinesListDatabase = medicineDatabaseOperation.getAllMedicinesDetails();
		return allMedicinesListDatabase;
	}
	
	@Override
	public List<MedicineDetailDTO> getMedicineExpiryDateList(String selectedMedicineName) {
		final String METHOD_TAG = TAG+" getMedicineExpiryDateList ";
		final List<MedicineDetailDTO> medicineExpiryDateList = medicineDatabaseOperation.getMedicineExpiryDateList(selectedMedicineName);
	
		return medicineExpiryDateList;
	}

}
