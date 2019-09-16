package com.sony.jdbc.cui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainClas {

	public static void main(String[] args) {
			
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());			
			 connection = DriverManager.getConnection("jdbc:oracle:thin:@INSISCILT-4089:1521:XE","deekshith","deekshith");
		
			 
			 
			 
			
			 preparedStatement = connection.prepareStatement("INSERT INTO LOGIN VALUES(?,?,?)"); 			
				preparedStatement.setString(1, "Ankith");
				preparedStatement.setString(2, "pass");
				preparedStatement.setString(3, "E");
				
				int rowEffected = preparedStatement.executeUpdate();
				
				if(rowEffected>0){
					connection.commit(); 
					System.out.println("Records inserted");
				}
			 
				
				
			 
			 
			 
				preparedStatement = connection.prepareStatement("UPDATE LOGIN SET PASSWD = ? WHERE USERID = ?"); 			
				preparedStatement.setString(1, "newpasswd");
				preparedStatement.setString(2, "deekshith");
			
				
				int rowEffected1 = preparedStatement.executeUpdate();
				
				if(rowEffected1>0){
					connection.commit(); 
					System.out.println("Records updated");
				}
				
			
			
			 
			 
			 preparedStatement = connection.prepareStatement("DELETE FROM LOGIN WHERE USERID = ?"); 			
				preparedStatement.setString(1, "Ankith");
						
				int rowEffected2 = preparedStatement.executeUpdate();
				
				if(rowEffected2>0){
					connection.commit(); 
					System.out.println("Record successfully deleted");
				}
			 
			 
			 
			 
			 
			 preparedStatement = connection.prepareStatement("SELECT * FROM LOGIN"); 
			
		
			
			 resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				System.out.println(resultSet.getString(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
			}
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		finally{
			try {
				if(preparedStatement != null && !preparedStatement.isClosed()){
					preparedStatement.close();
				}
				if(resultSet != null && !resultSet.isClosed()){
					resultSet.close();
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
