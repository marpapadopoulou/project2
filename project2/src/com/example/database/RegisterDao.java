package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.product.Product;

public class RegisterDao {
	private String dbUrl = "jdbc:mysql://localhost:3306/productdb?serverTimezone=UTC";
	private String dbUname = "root";
	private String dbPassword = "a2a4a5p54473";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";



	
	public void loadDriver(String dbDriver){
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Connection getConnection(){
		Connection con = null ;
		try {
			con = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	public boolean validate(Product product)
	{
		boolean status=false;
		
		loadDriver(dbDriver);
		Connection con = getConnection();
		
		String result = "Product is already registered";
		String sql = "SELECT * FROM product WHERE barcode= ? ";
		
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1,product.getBarcode());
		
		ResultSet rs = ps.executeQuery();
		status= rs.next();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(result);
		}
		return status;
	}
	
	public void insert(Product product)
	{
		loadDriver(dbDriver);
		Connection con = getConnection();
		//String result = "Data entered successfully";
		String sql = "INSERT INTO product values(?,?,?,?)";
		
		PreparedStatement ps;
		try {
		ps = con.prepareStatement(sql);
		ps.setString(1, product.getBarcode());
		ps.setString(2, product.getName());
		ps.setString(3, product.getColor());
		ps.setString(4, product.getDescription());
		ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
