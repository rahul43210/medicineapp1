package rl.medicine.daoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rl.medicine.dao.CrudDao;
import rl.medicine.database.MedicineDatabaseOperation;
import rl.medicine.model.MedicineDetailModel;
import rl.medicine.utility.SuccessMessage;

@Repository
public class CrudDaoImpl implements CrudDao{

	@Autowired
	private MedicineDatabaseOperation medicineDatabaseOperation;
	
	@Override
	public SuccessMessage saveMedicineDetail(MedicineDetailModel model) {
		final boolean successOperation = medicineDatabaseOperation.saveMedicineDetail(model);
		final SuccessMessage successMessage = new SuccessMessage(400, "Failed", false);
		if(successOperation) {
			successMessage.setCode(200);
			successMessage.setMessage("Success");
			successMessage.setSuccess(true);
		}
			
		return successMessage;
	}

}
