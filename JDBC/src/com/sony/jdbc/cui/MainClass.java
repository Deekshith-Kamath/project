package com.sony.jdbc.cui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainClass {

	public static void main(String[] args) {
		
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		//  Step 1 : 
		//Register the driver
		//copy ojdbc6.jar file into lib folder
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			
			// Establish the connection
			//username:deekshith password:deekshith
			//return type of getConnection is Connection which is an interface
			// so create a connection and hold reference to that
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@INSISCILT-4089:1521:XE","deekshith","deekshith");
			
			//Execute a query
			
			 preparedStatement = connection.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?)"); //same for alter,delete
			
			preparedStatement.setString(1, "Deekshith");
			preparedStatement.setString(2, "pass");
			preparedStatement.setString(3, "E");
			
			int rowEffected = preparedStatement.executeUpdate();
			
			if(rowEffected>0){
				connection.commit(); //because if we do not put this, then if some exception occurs roll back will takes place.auto commit takes place when we connection.close() executes.
				System.out.println("Records inserted");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally{
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()){
					preparedStatement.close();
				}
				if(connection != null && !connection.isClosed()){
					connection.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
