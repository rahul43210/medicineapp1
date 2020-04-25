package rl.medicine.utility;

public class SuccessMessage {
	private int code;
	private String message;
	private boolean isSuccess = false;
	
	public SuccessMessage() {
		super();
	}
	
	public SuccessMessage(int code, String message, boolean isSuccess) {
		super();
		this.code = code;
		this.message = message;
		this.isSuccess = isSuccess;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public String toString() {
		return " Code : "+code+" Message : "+message+" isSuccess : "+isSuccess;
	}
	

}
