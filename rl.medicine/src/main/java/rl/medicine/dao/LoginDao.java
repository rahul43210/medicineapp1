package rl.medicine.dao;

import rl.medicine.model.LoginDetailModel;

public interface LoginDao {

	 Boolean loginAction(final LoginDetailModel loginDetail);
}
