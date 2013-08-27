package com.student.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.student.model.Student;
import com.student.util.DbUtil;

public class StudentDao {
	private Connection dbConnection;
	
	public StudentDao() {
		dbConnection = DbUtil.getConnection();
	}
	
	public void addStudent(Student student) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("insert into student(userName, firstName, lastName, password, emailAddress, dateOfBirth) values (?, ?, ?, ?, ?, ?)");
			prepStatement.setString(1, student.getUserName());
			prepStatement.setString(2, student.getFirstName());
			prepStatement.setString(3, student.getLastName());
			prepStatement.setString(4, student.getPassword());
			prepStatement.setString(5, student.getEmailAddress());
			prepStatement.setDate(6, new Date(student.getDateOfBirth().getTime()));
			
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUserName(String userName) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("select count(*) from student where userName = ?");
			prepStatement.setString(1, userName);	
						
			ResultSet result = prepStatement.executeQuery();
			if (result != null) {	
				while (result.next()) {
					if (result.getInt(1) == 1) {
						return true;
					}				
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean verifyStudent(String userName, String password) {
		try {
			PreparedStatement prepStatement = dbConnection.prepareStatement("select password from student where userName = ?");
			prepStatement.setString(1, userName);			
			
			ResultSet result = prepStatement.executeQuery();
			if (result != null) {
				while (result.next()) {
					if (result.getString(1).equals(password)) {
						return true;
					}
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
