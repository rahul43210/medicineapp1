package rl.medicine.daoimpl;

import org.springframework.stereotype.Repository;

import rl.medicine.dao.LoginDao;
import rl.medicine.model.LoginDetailModel;

@Repository
public class LoginDaoImpl implements LoginDao {

	 
	public Boolean loginAction(LoginDetailModel loginDetail) { 
		return Boolean.TRUE; 
	}
	
	 

}
