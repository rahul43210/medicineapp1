package rl.medicine.model;

public class LoginDetailModel {
	
	private String userName;
	private String password;

	public LoginDetailModel() {}
	
	public LoginDetailModel(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
