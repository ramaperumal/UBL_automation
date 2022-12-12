package com.DOTC.supportLibraries;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Properties;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;

public class DBConnection {
	
	public static Properties properties = Settings.getInstance();
	
	public Connection AzureDBConnect(){
		// Connect to database
		
        String user = properties.getProperty("tts_username"); // update me
        String password = properties.getProperty("tts_password"); // update me
        String url = String.format(properties.getProperty("tts_jdbc_url"),user, password);
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
           
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return connection;
    }
	
	public ResultSet executeQuery(Connection connection,String selectSql){
		ResultSet resultSet =null;
		try {
			 Statement statement = connection.createStatement();
	         resultSet = statement.executeQuery(selectSql);
	         connection.close();
	          
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return resultSet;
	}
}


