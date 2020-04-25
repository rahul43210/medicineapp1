package rl.medicine.utility;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseData {

	private String driverName;
	private String databaseUrl;
	private String userName;
	private String password;
	
	@Value("$database.drivername")
	public String getDriverName() {
		return driverName;
	}
	
	@Value("$database.url")
	public String getDatabaseUrl() {
		return databaseUrl;
	}
	
	@Value("$database.username")
	public String getUserName() {
		return userName;
	}
	
	@Value("$database.password")
	public String getPassword() {
		return password;
	}
	
	
}
