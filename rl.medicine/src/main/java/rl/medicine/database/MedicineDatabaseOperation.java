package rl.medicine.database;

import static rl.medicine.utility.AppLogger.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.model.DataIdModel;
import rl.medicine.model.MedicineDetailModel;
import rl.medicine.utility.AppConstant;
import rl.medicine.utility.AppLogger;
import rl.medicine.utility.AppUtility;

@Repository
public class MedicineDatabaseOperation {

	private final static String TAG = MedicineDatabaseOperation.class.getSimpleName();

	@Autowired
	private JdbcRegister jdbcRegister;

	public List<DataIdModel> getMedicineList(){
		appDebug(TAG+" getMedicineList ");
		final List<DataIdModel> medicineList = new ArrayList<>();
		try {
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final ResultSet rset = statement.executeQuery("select * from medicine_list");
			if(rset != null) {
				while(rset.next()) {
					final String medicineName = rset.getString("medicine_name");
					final String medicineChemicalName = rset.getString("medicine_chemical_name");
					final int id = rset.getInt("id");
					medicineList.add(new DataIdModel(id,medicineName, medicineChemicalName));
				}
			}
			statement.close();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		appDebug(TAG+" medicineList : "+medicineList);
		return medicineList;
	}

	public List<DataIdModel> getBoxesList(){
		appDebug(TAG+" getBoxesList ");
		final List<DataIdModel> boxesList = new ArrayList<>();
		try {
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final ResultSet rset = statement.executeQuery("select * from boxes");
			if(rset != null) {
				while(rset.next()) {
					final String medicineName = rset.getString("name");
					final int id = rset.getInt("id");
					boxesList.add(new DataIdModel(id,medicineName));
				}
			}
			statement.close();
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return boxesList;
	}



	public boolean saveMedicineDetail(MedicineDetailModel model) {
		appDebug(TAG+" saveMedicineDetail :  "+model);
		try {
			final PreparedStatement preparedStatement 
			= jdbcRegister.connectDatabase().prepareStatement("insert into medicine_detail(medicine_name, medicine_box, expiry_date, strip, left_quantity) values(?,?,?,?,?)");
			preparedStatement.setInt(1, model.getMedicineNameId());
			preparedStatement.setInt(2, model.getMedicineBoxId());
			preparedStatement.setDate(3, new java.sql.Date(model.getMedicineExpiryDate().getTime()));
			preparedStatement.setInt(4, model.getStrip());
			preparedStatement.setInt(5, model.getLeftQuantity());
			int result = preparedStatement.executeUpdate();
			appDebug(TAG+" Result : "+result);
			preparedStatement.close();
			if(result == 1) {
				return true;
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return false;
	}

	public HashMap<String, List<MedicineDetailDTO>> getMedicineByName(String selectedMedicineName) {
		//final  HashMap<String, HashMap<java.sql.Date, MedicineDetailDTO>> medicineData = new LinkedHashMap<>();
		final HashMap<String, List<MedicineDetailDTO>> byBoxesData = new HashMap<>();

		try {
			final List<DataIdModel> medicineList = getMedicineList();
			final List<DataIdModel> medicineBox = getBoxesList();

			final HashMap<Integer, String> medicineListMap = AppUtility.getListDataTotMap(medicineList);
			final HashMap<Integer, String> medicineBoxMap = AppUtility.getListDataTotMap(medicineBox);
			final HashMap<String, Integer> medicineListMapString = AppUtility.getListDataTotMapInString(medicineList);
			appDebug("Selected Medicine Name : "+selectedMedicineName);
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final String queryForFetch = "select * from medicine_detail where medicine_name="+medicineListMapString.get(selectedMedicineName)+"";
			appDebug(TAG+" Fetch Query : "+queryForFetch);
			final ResultSet rset = statement.executeQuery(queryForFetch);

			if(rset != null) {
				appDebug(TAG+" Rset : "+rset);
				while(rset.next()) {
					final int id = rset.getInt("id");
					final int medicine_name= rset.getInt("medicine_name");
					final int medicine_box = rset.getInt("medicine_box");
					final java.sql.Date expiryDate = rset.getDate("expiry_date");
					final int strip = rset.getInt("strip");
					final int leftQuantity = rset.getInt("left_quantity");
					final String medicine_name_value = medicineListMap.get(medicine_name);
					final String medicine_box_value = medicineBoxMap.get(medicine_box);

					appDebug(TAG+" "+id+" medicine_name "+medicine_name+" medicine_box "+medicine_box+" expiryDate "+expiryDate+" strip "+strip+" leftQuantity "+leftQuantity);
					MedicineDetailDTO medicineDetailDTO = new MedicineDetailDTO();
					medicineDetailDTO.setLeftQuantity(leftQuantity);
					medicineDetailDTO.setMedicineBox(medicineBoxMap.get(medicine_box));
					medicineDetailDTO.setMedicineBoxId(medicine_box);
					medicineDetailDTO.setMedicineExpiryDate(new java.util.Date(expiryDate.getTime()));
					medicineDetailDTO.setMedicineId(id);
					medicineDetailDTO.setMedicineName(medicineListMap.get(medicine_name));
					medicineDetailDTO.setMedicineNameId(medicine_name);
					medicineDetailDTO.setStrip(strip);

					if(!byBoxesData.containsKey(medicine_box_value)) {
						byBoxesData.put(medicine_box_value, null);
					}
					List<MedicineDetailDTO> boxContainsValue = byBoxesData.get(medicine_box_value);
					if(boxContainsValue == null) {
						boxContainsValue = new ArrayList<>();
					}

					boolean isNewDataEntry = false;

					MedicineDetailDTO byDateData = null;
					for(int index = 0; index < boxContainsValue.size(); index++) {
						if(boxContainsValue.get(index).getMedicineExpiryDate().getTime() == expiryDate.getTime()) {
							appDebug(TAG+" Matched for "+boxContainsValue.get(index).getMedicineExpiryDate()+", to "+expiryDate);
							byDateData = boxContainsValue.get(index);
						}
					}

					if(byDateData == null) {
						appDebug("New Entry for expiry Date : "+expiryDate);
						byDateData = new MedicineDetailDTO();
						isNewDataEntry = true;
					}

					byDateData.setLeftQuantity(byDateData.getLeftQuantity()+leftQuantity);
					byDateData.setStrip(byDateData.getStrip()+ strip);
					byDateData.setMedicineBox(medicine_box_value);
					byDateData.setMedicineBoxId(medicine_box);
					byDateData.setMedicineExpiryDate(expiryDate);
					byDateData.setMedicineId(id);
					byDateData.setMedicineName(medicine_name_value);
					byDateData.setMedicineNameId(medicine_name);					
					if(isNewDataEntry){
						appDebug("New Entry added ");
						boxContainsValue.add(byDateData);
					}
					appDebug("new Data added in DTO for expiry Date : "+byDateData.getMedicineExpiryDate());
					byBoxesData.put(medicine_box_value, boxContainsValue);
					/*
					if(!medicineData.containsKey(medicineDetailDTO.getMedicineBox())) {
						medicineData.put(medicineDetailDTO.getMedicineBox(), null);
					}
					HashMap<java.util.Date, MedicineDetailDTO> dateMap = medicineData.get(medicineDetailDTO.getMedicineBox());
					if(dateMap == null) {
						dateMap = new HashMap<>();
					}
					if(!dateMap.containsKey(medicineDetailDTO.getMedicineExpiryDate())) {
						final MedicineDetailDTO quantityData = new MedicineDetailDTO();
						dateMap.put(medicineDetailDTO.getMedicineExpiryDate(), quantityData);
					}*/

				}
			}
			statement.close();
			return byBoxesData;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public HashMap<String, List<MedicineDetailDTO>> getMedicineByBox(String selectedBoxName) {
		appDebug(TAG+" getMedicineByBox ");
		final HashMap<String, List<MedicineDetailDTO>> byBoxesData = new HashMap<>();

		try {
			final List<DataIdModel> medicineList = getMedicineList();
			final List<DataIdModel> medicineBox = getBoxesList();

			final HashMap<Integer, String> medicineListMap = AppUtility.getListDataTotMap(medicineList);
			final HashMap<Integer, String> medicineBoxMap = AppUtility.getListDataTotMap(medicineBox);
			final HashMap<String, Integer> boxListMapString = AppUtility.getListDataTotMapInString(medicineBox);
			appDebug("Selected Box Name : "+selectedBoxName);
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final String queryForFetch = "select * from medicine_detail where medicine_box="+boxListMapString.get(selectedBoxName)+"";
			appDebug(TAG+" Fetch Query : "+queryForFetch);
			final ResultSet rset = statement.executeQuery(queryForFetch);

			if(rset != null) {
				appDebug(TAG+" Rset : "+rset);
				while(rset.next()) {
					final int id = rset.getInt("id");
					final int medicine_name= rset.getInt("medicine_name");
					final int medicine_box = rset.getInt("medicine_box");
					final java.sql.Date expiryDate = rset.getDate("expiry_date");
					final int strip = rset.getInt("strip");
					final int leftQuantity = rset.getInt("left_quantity");
					final String medicine_name_value = medicineListMap.get(medicine_name);
					final String medicine_box_value = medicineBoxMap.get(medicine_box);

					appDebug(TAG+" "+id+" medicine_name "+medicine_name+" medicine_box "+medicine_box+" expiryDate "+expiryDate+" strip "+strip+" leftQuantity "+leftQuantity);
					MedicineDetailDTO medicineDetailDTO = new MedicineDetailDTO();
					medicineDetailDTO.setLeftQuantity(leftQuantity);
					medicineDetailDTO.setMedicineBox(medicineBoxMap.get(medicine_box));
					medicineDetailDTO.setMedicineBoxId(medicine_box);
					medicineDetailDTO.setMedicineExpiryDate(new java.util.Date(expiryDate.getTime()));
					medicineDetailDTO.setMedicineId(id);
					medicineDetailDTO.setMedicineName(medicineListMap.get(medicine_name));
					medicineDetailDTO.setMedicineNameId(medicine_name);
					medicineDetailDTO.setStrip(strip);

					if(!byBoxesData.containsKey(medicine_name_value)) {
						byBoxesData.put(medicine_name_value, null);
					}
					List<MedicineDetailDTO> medicineContainsValue = byBoxesData.get(medicine_name_value);
					if(medicineContainsValue == null) {
						medicineContainsValue = new ArrayList<>();
					}

					boolean isNewDataEntry = false;

					MedicineDetailDTO byDateData = null;
					for(int index = 0; index < medicineContainsValue.size(); index++) {
						if(medicineContainsValue.get(index).getMedicineExpiryDate().getTime() == expiryDate.getTime()) {
							appDebug(TAG+" Matched for "+medicineContainsValue.get(index).getMedicineExpiryDate()+", to "+expiryDate);
							byDateData = medicineContainsValue.get(index);
						}
					}

					if(byDateData == null) {
						appDebug("New Entry for expiry Date : "+expiryDate);
						byDateData = new MedicineDetailDTO();
						isNewDataEntry = true;
					}

					byDateData.setLeftQuantity(byDateData.getLeftQuantity()+leftQuantity);
					byDateData.setStrip(byDateData.getStrip()+ strip);
					byDateData.setMedicineBox(medicine_box_value);
					byDateData.setMedicineBoxId(medicine_box);
					byDateData.setMedicineExpiryDate(expiryDate);
					byDateData.setMedicineId(id);
					byDateData.setMedicineName(medicine_name_value);
					byDateData.setMedicineNameId(medicine_name);					
					if(isNewDataEntry){
						appDebug("New Entry added ");
						medicineContainsValue.add(byDateData);
					}
					appDebug("new Data added in DTO for expiry Date : "+byDateData.getMedicineExpiryDate());
					byBoxesData.put(medicine_name_value, medicineContainsValue);
					/*
					if(!medicineData.containsKey(medicineDetailDTO.getMedicineBox())) {
						medicineData.put(medicineDetailDTO.getMedicineBox(), null);
					}
					HashMap<java.util.Date, MedicineDetailDTO> dateMap = medicineData.get(medicineDetailDTO.getMedicineBox());
					if(dateMap == null) {
						dateMap = new HashMap<>();
					}
					if(!dateMap.containsKey(medicineDetailDTO.getMedicineExpiryDate())) {
						final MedicineDetailDTO quantityData = new MedicineDetailDTO();
						dateMap.put(medicineDetailDTO.getMedicineExpiryDate(), quantityData);
					}*/

				}
			}
			statement.close();
			return byBoxesData;
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public List<MedicineDetailDTO> getAllMedicinesDetails() {
		appDebug(TAG+" getAllMedicinesDetails");
		final String METHOD_TAG = TAG+" getAllMedicinesDetails";
		try {
			final List<DataIdModel> listMedicine = getMedicineList();
			appDebug(METHOD_TAG+" list Medicine : "+listMedicine);
			final List<DataIdModel> listBox = getBoxesList();
			appDebug(METHOD_TAG+" list Box : "+listBox);
			
			final HashMap<Integer, String> listMedicineMap = AppUtility.getListDataTotMap(listMedicine);
			final HashMap<Integer, String> listBoxMap = AppUtility.getListDataTotMap(listBox);
			final HashMap<Integer, String> listMedicineNameChemicalName = AppUtility.getListDataTotMapChemicalName(listMedicine);
			
			appDebug(METHOD_TAG+" List MedicineMap "+listMedicineMap.size());
			appDebug(METHOD_TAG+" Lisy BoxMap "+listBoxMap);
			
			final List<MedicineDetailDTO> listAllMedicineData = new ArrayList<>();
			
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final String queryForFetch = "select * from medicine_detail";
			appDebug(TAG+" Fetch Query : "+queryForFetch);
			final ResultSet rset = statement.executeQuery(queryForFetch);
			appDebug(TAG+" rset getAllMedicinesDetails "+rset);
			if(rset != null) {
				while (rset.next()) {
					final int medicineNameId = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_MEDICINE_NAME);
					final int medicineBoxId = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_MEDICINE_BOX);
					final Date expiryDate = rset.getDate(AppConstant.TABLE_MEDICINE_DETAIL_COL_EXPIRY_DATE);
					final int medicineStrips = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_STRIP);
					final int medicineTotalQuantity = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_LEFT_QUANTITY);
					
					final String medicineName = listMedicineMap.get(medicineNameId);
					final String medicineBoxName = listBoxMap.get(medicineBoxId);
					final String medicineChemicalName = listMedicineNameChemicalName.get(medicineNameId);
					
					final MedicineDetailDTO medicineDetailDTO = new MedicineDetailDTO();
					medicineDetailDTO.setMedicineNameId(medicineNameId);
					medicineDetailDTO.setMedicineName(medicineName);
					medicineDetailDTO.setMedicineBoxId(medicineBoxId);
					medicineDetailDTO.setMedicineBox(medicineBoxName);
					medicineDetailDTO.setMedicineExpiryDate(new java.util.Date(expiryDate.getTime()));
					medicineDetailDTO.setStrip(medicineStrips);
					medicineDetailDTO.setLeftQuantity(medicineTotalQuantity);
					medicineDetailDTO.setMedicineChemicalName(medicineChemicalName);
					
					appDebug(METHOD_TAG+" Medicine Detail DTO "+medicineDetailDTO);
					
					listAllMedicineData.add(medicineDetailDTO);
				}
			}
			statement.close();
			appDebug(METHOD_TAG+" All medicine List Size "+listAllMedicineData.size());
			return listAllMedicineData; 
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MedicineDetailDTO> getMedicineExpiryDateList(String selectedMedicineName) {
		final String METHOD_TAG = TAG+" getMedicineExpiryDateList ";
		appDebug("Inside the "+METHOD_TAG);
		try {
			final List<DataIdModel> medicineList = getMedicineList();
			final List<DataIdModel> medicineBox = getBoxesList();
			
			final HashMap<String, Integer> medicineNameIdMap = AppUtility.getListDataTotMapInString(medicineList);
			final HashMap<Integer, String> medicineIdNameMap = AppUtility.getListDataTotMap(medicineList);
			final HashMap<Integer, String> medicineIdBoxMap = AppUtility.getListDataTotMap(medicineBox);
			final HashMap<Integer, String> medicineIdChemicalName =  AppUtility.getListDataTotMapChemicalName(medicineList);
			
			appDebug(METHOD_TAG+"Medicine Id : "+medicineNameIdMap.get(selectedMedicineName));
			
			final List<MedicineDetailDTO> medicineExpiryDateList = new ArrayList<>();
			final Statement statement = jdbcRegister.connectDatabase().createStatement();
			final String SQL_QUERY = "select * from medicine_detail where medicine_name="+medicineNameIdMap.get(selectedMedicineName.trim());
			appDebug(METHOD_TAG+"SQL Query : "+SQL_QUERY);
			final ResultSet rset = statement.executeQuery(SQL_QUERY);
			if(rset != null) {
				while(rset.next()) {
					final int medicineNameId = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_MEDICINE_NAME);
					final int medicineBoxId = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_MEDICINE_BOX);
					final Date expiryDate = rset.getDate(AppConstant.TABLE_MEDICINE_DETAIL_COL_EXPIRY_DATE);
					final int medicineStrips = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_STRIP);
					final int medicineTotalQuantity = rset.getInt(AppConstant.TABLE_MEDICINE_DETAIL_COL_LEFT_QUANTITY);
					
					final String medicineName = medicineIdNameMap.get(medicineNameId);
					final String medicineBoxName = medicineIdBoxMap.get(medicineBoxId);
					final String medicineChemicalName = medicineIdChemicalName.get(medicineNameId);
					
					final MedicineDetailDTO medicineDetailDTO = new MedicineDetailDTO();
					medicineDetailDTO.setMedicineNameId(medicineNameId);
					medicineDetailDTO.setMedicineName(medicineName);
					medicineDetailDTO.setMedicineBoxId(medicineBoxId);
					medicineDetailDTO.setMedicineBox(medicineBoxName);
					medicineDetailDTO.setMedicineExpiryDate(new java.util.Date(expiryDate.getTime()));
					medicineDetailDTO.setStrip(medicineStrips);
					medicineDetailDTO.setLeftQuantity(medicineTotalQuantity);
					medicineDetailDTO.setMedicineChemicalName(medicineChemicalName);
					
					appDebug(METHOD_TAG+" Medicine Detail DTO "+medicineDetailDTO);

					medicineExpiryDateList.add(medicineDetailDTO);
				}
				
				return medicineExpiryDateList;
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}



}
