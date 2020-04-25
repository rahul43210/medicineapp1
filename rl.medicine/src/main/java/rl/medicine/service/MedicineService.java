package rl.medicine.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import rl.medicine.dto.MedicineDetailDTO;
import rl.medicine.model.DataIdModel;

public interface MedicineService {
	
	public List<DataIdModel> listMedicines();
	
	public List<DataIdModel> listBoxes();

	public HashMap<String, List<MedicineDetailDTO>> getMedicineByName(String selectedMedicineName);

	public HashMap<String, List<MedicineDetailDTO>> getMedicineByBox(String selectedBoxName);

	public HashMap<String, List<MedicineDetailDTO>> getBoxMedicineBoxByDate();

	public List<MedicineDetailDTO> getAllMedicines();

	public List<MedicineDetailDTO> getAllMedicinesByChemicalName();

	public HashMap<Date, MedicineDetailDTO> getMedicineByExpiryDate(String selectedMedicineName);
}
