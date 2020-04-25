package rl.medicine.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.PreparedStatement;

import rl.medicine.utility.DatabaseData;

@Component
public class JdbcRegister {
	
	@Autowired
	private DatabaseData databaseData;
	
	private static Connection connection;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicine", "root", "root2018");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection connectDatabase() {
		try {
			return connection;
		} catch (Exception exception)  {
			exception.printStackTrace();
		}
		return null;
	}
	
	 
	
	@Override
	protected void finalize() throws Throwable { 
		super.finalize();
		connection.close();
	}

}
