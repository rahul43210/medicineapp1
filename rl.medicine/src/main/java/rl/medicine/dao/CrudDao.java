package rl.medicine.dao;

import rl.medicine.model.MedicineDetailModel;
import rl.medicine.utility.SuccessMessage;

public interface CrudDao {

	SuccessMessage saveMedicineDetail(MedicineDetailModel model);

}
