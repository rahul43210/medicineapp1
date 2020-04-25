package rl.medicine.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rl.medicine.dao.LoginDao;
import rl.medicine.model.LoginDetailModel;
import rl.medicine.service.LoginSignupService;

@Service
public class LoginDetailServiceImpl implements LoginSignupService {

	@Autowired
	private LoginDao loginDao;
	
	public Boolean loginAction(LoginDetailModel loginDetail) {
		
		return loginDao.loginAction(loginDetail);
	}

}
