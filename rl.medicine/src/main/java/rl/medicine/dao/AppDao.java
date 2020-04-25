package rl.medicine.dao;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.model.DataIdModel;

public interface AppDao {

	public List<DataIdModel> getMedicineList();

	public List<DataIdModel> getMedicineBoxes();

	public HashMap<String, List<MedicineDetailDTO>> getMedicineByName(String selectedMedicineName);

	public HashMap getMedicineByBox(String selectedBoxName);

	public List<MedicineDetailDTO> getAllMedicinesList();

	public List<MedicineDetailDTO> getMedicineExpiryDateList(String selectedMedicineName);
}
