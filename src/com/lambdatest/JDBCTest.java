package com.lambdatest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import oracle.jdbc.driver.OracleDriver;

public class JDBCTest {

	public static void main(String[] args) {
		
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		
		
		try {
			OracleDriver oracleDriver = new OracleDriver();
			DriverManager.deregisterDriver(oracleDriver);
			
			Properties properties = new Properties();
			
			properties.put("user", "system");
			properties.put("password", "songr");
			
			connection = oracleDriver.connect("jdbc:oracle:thin:@10.10.16.179:1521:songr", properties);
			
			System.out.println(connection);
			
			prepareStatement = connection.prepareStatement("SELECT * FROM SONGR_TEST");
			resultSet = prepareStatement.executeQuery();
			System.out.println(resultSet);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				
				System.out.println("id:" + id + ",name : " + name);
			}
			
			PreparedStatement prepareStatementWithParams = connection.prepareStatement("SELECT * FROM SONGR WHERE USERNAME = ? AND AGE = ?");
			prepareStatementWithParams.setString(1, "lisi");
			prepareStatementWithParams.setInt(2, 55);
			
			ResultSet resultSetWithParams = prepareStatementWithParams.executeQuery();
			
			while (resultSetWithParams.next()) {
				String sex = resultSetWithParams.getString("COMPUTERNAME");
				String name = resultSetWithParams.getString("USERNAME");
				int age = resultSetWithParams.getInt("AGE");
				String good = resultSetWithParams.getString("DATA");
				System.out.println("name : " + name + "age : " + age + "sex :" + sex + "good : " + good);
			}
			
			
			
			try {
				if (connection != null) {
					connection.close();
				}
				if (prepareStatement != null) {
					prepareStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testDriverManager() {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			String url = "jdbc:oracle:thin:@10.10.16.189:1521:songr";
			String user = "system";
			String password = "songr";
			String driverClassName = "oracle.jdbc.driver.OracleDriver";
			
			try {
				Class.forName(driverClassName).newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			connection = DriverManager.getConnection(url, user, password);
			
			System.out.println(connection);
			
			/*prepareStatement = connection.prepareStatement("SELECT * FROM SONGR_TEST");
			resultSet = prepareStatement.executeQuery();
			System.out.println(resultSet);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				String name = resultSet.getString("NAME");
				
				System.out.println("id:" + id + ",name : " + name);
			}*/
			
			PreparedStatement prepareStatementWithParams = connection.prepareStatement("SELECT * FROM PUB_USERS WHERE USER_ID = ? AND USER_NAME = ?");
			prepareStatementWithParams.setString(1, "DEMO");
			prepareStatementWithParams.setString(2, "demo");
			
			ResultSet resultSetWithParams = prepareStatementWithParams.executeQuery();
			
			while (resultSetWithParams.next()) {
//				String computer = resultSetWithParams.getString("COMPUTERNAME");
				String username = resultSetWithParams.getString("USER_NAME");
				String pwd = resultSetWithParams.getString("PASSWORD");
//				int age = resultSetWithParams.getInt("AGE");
//				String data = resultSetWithParams.getString("DATA");
				System.out.println("username : " + username + " pwd : " + pwd);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
