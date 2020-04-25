package rl.medicine.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rl.medicine.dao.CrudDao;
import rl.medicine.model.MedicineDetailModel;
import rl.medicine.service.CrudService;
import rl.medicine.utility.SuccessMessage;

@Service
public class CrudServiceImpl implements CrudService{

	@Autowired
	private CrudDao crudDao;
	
	@Override
	public SuccessMessage saveMedicineDetail(MedicineDetailModel model) {
		
		return crudDao.saveMedicineDetail(model);
	} 

}
