package rl.medicine.service;

import rl.medicine.model.MedicineDetailModel;
import rl.medicine.utility.SuccessMessage;

public interface CrudService {

	SuccessMessage saveMedicineDetail(MedicineDetailModel model);

}
