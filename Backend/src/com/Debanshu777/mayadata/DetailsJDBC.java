package com.Debanshu777.mayadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

public class DetailsJDBC {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3307/details";
	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";

//SQL
private static String SELECT_ALL_DETAILS="SELECT slNo,MName,NPeople,SDate,STime,ETime from details;";
private static String SEARCH_DETAILS=" SELECT slNo,MName,NPeople,SDate,STime,ETime from details where MName=?;";
private static String INSERT_DETAILS="INSERT INTO details(slNo,MName,NPeople,SDate,STime,ETime) " + 
				" VALUES (?, ?, ?, ?, ?, ?);";
private static String DELETE_DETAILS="DELETE FROM details WHERE slNo = ?;";
protected Connection getConnection() {
	Connection connection = null;
	try {
		Class.forName(JDBC_DRIVER);
		connection = DriverManager.getConnection(DB_URL, USER, PASS);
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return connection;
}
public void deleteDetails(long slNo) throws SQLException{
	System.out.println(DELETE_DETAILS);
	try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DETAILS)) {
		preparedStatement.setLong(1,slNo);
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
	}catch (SQLException e) {
		printSQLException(e);
	}
}
public void insertDetails(DetailsPojo newDetails) throws SQLException {
	System.out.println(INSERT_DETAILS);
	// try-with-resource statement will auto close the connection.
	try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DETAILS)) {
			
		preparedStatement.setInt(1, newDetails.getSlNo());
		preparedStatement.setString(2, newDetails.getMName());
		preparedStatement.setInt(3, newDetails.getNPeople());
		preparedStatement.setString(4, newDetails.getSDate());
		preparedStatement.setString(5, newDetails.getSTime());
		preparedStatement.setString(6, newDetails.getETime());
		System.out.println(preparedStatement);
		preparedStatement.executeUpdate();
	} catch (SQLException e) {
		printSQLException(e);
	}
}
 
public String searchDetails(int slNo) {
	ArrayList<DetailsPojo> details = new ArrayList<DetailsPojo>();
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_DETAILS);) {
		preparedStatement.setInt(1, slNo);
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();

		
		// Step 4: Process the ResultSet object.
		while (rs.next()) {
			DetailsPojo detail=new DetailsPojo();
			
			detail.setSlNo(rs.getInt("slNo"));
			detail.setMName(rs.getString("MName"));
			detail.setNPeople(rs.getInt("NPeople"));
			detail.setSDate(rs.getString("SDate"));
			detail.setSTime(rs.getString("STime"));
			detail.setETime(rs.getString("ETime"));
		
			details.add(detail);
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	String json = new Gson().toJson(details);
	return json;
}
public String selectDetails() {

	// using try-with-resources to avoid closing resources (boiler plate code)
	ArrayList<DetailsPojo> details = new ArrayList<DetailsPojo>();
	
	// Step 1: Establishing a Connection
	try (Connection connection = getConnection();

		// Step 2:Create a statement using connection object
		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DETAILS);) {		
		System.out.println(preparedStatement);
		// Step 3: Execute the query or update query
		ResultSet rs = preparedStatement.executeQuery();

		
		// Step 4: Process the ResultSet object.
		while (rs.next()) {
			DetailsPojo detail=new DetailsPojo();
			
			detail.setSlNo(rs.getInt("slNo"));
			detail.setMName(rs.getString("MName"));
			detail.setNPeople(rs.getInt("NPeople"));
			detail.setSDate(rs.getString("SDate"));
			detail.setSTime(rs.getString("STime"));
			detail.setETime(rs.getString("ETime"));
			
			
		
			details.add(detail);
		}
	} catch (SQLException e) {
		printSQLException(e);
	}
	String json = new Gson().toJson(details);
	return json;
}

private void printSQLException(SQLException ex) {
	for (Throwable e : ex) {
		if (e instanceof SQLException) {
			e.printStackTrace(System.err);
			System.err.println("SQLState: " + ((SQLException) e).getSQLState());
			System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
			System.err.println("Message: " + e.getMessage());
			Throwable t = ex.getCause();
			while (t != null) {
				System.out.println("Cause: " + t);
				t = t.getCause();
			}
		}
	}
}



}
