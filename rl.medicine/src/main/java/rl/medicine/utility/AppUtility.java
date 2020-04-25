package rl.medicine.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import rl.medicine.model.DataIdModel;
import static rl.medicine.utility.AppLogger.*;

public class AppUtility {
 
	private static final String TAG = AppUtility.class.getSimpleName();
	
	
	public static List<DataIdModel> getListFromHashMap(HashMap<String, Integer> cacheMedicineName) {
		final List<DataIdModel> dataIdModel = new ArrayList<>(cacheMedicineName.size());
		final Set<Map.Entry<String, Integer>> mapEntry = cacheMedicineName.entrySet();
		final DataIdModel[] dataIdModelArray = new DataIdModel[cacheMedicineName.size()];
		for(DataIdModel dataIdModelData : dataIdModelArray) {
			dataIdModel.add(dataIdModelData);
		}
		for(Map.Entry<String, Integer> mapEntryData : mapEntry) {
			dataIdModel.set(mapEntryData.getValue()-1, new DataIdModel(mapEntryData.getValue(), mapEntryData.getKey()));
		}
		return dataIdModel;
	}


	public static HashMap<Integer, String> getListDataTotMap(List<DataIdModel> medicineList) {
		appDebug(TAG+" getListDataTotMap ");
		final HashMap<Integer, String> mapData = new HashMap<>(medicineList.size());
		for(DataIdModel dataIdModel : medicineList) {
			mapData.put(dataIdModel.getId(), dataIdModel.getData());
			appDebug("Id "+dataIdModel.getId()+", Data : "+dataIdModel.getData());
		}
		appDebug(TAG+" Map Size : "+mapData.size());
		return mapData;
	}
	
	public static HashMap<Integer, String> getListDataTotMapChemicalName(List<DataIdModel> medicineList) {
		appDebug(TAG+" getListDataTotMap ");
		final HashMap<Integer, String> mapData = new HashMap<>(medicineList.size());
		for(DataIdModel dataIdModel : medicineList) {
			mapData.put(dataIdModel.getId(), dataIdModel.getDataSubName());
			appDebug("Id "+dataIdModel.getId()+", Data : "+dataIdModel.getDataSubName());
		}
		appDebug(TAG+" Map Size : "+mapData.size());
		return mapData;
	}
	
	public static HashMap<String, Integer> getListDataTotMapInString(List<DataIdModel> medicineList) {
		appDebug(TAG+" getListDataTotMap ");
		final HashMap<String, Integer>  mapData = new HashMap<>(medicineList.size());
		for(DataIdModel dataIdModel : medicineList) {
			mapData.put(dataIdModel.getData().trim(), dataIdModel.getId());
			appDebug("Id "+dataIdModel.getId()+", Data : "+dataIdModel.getData());
		}
		appDebug(TAG+" Map Size : "+mapData.size());
		return mapData;
	}

}
