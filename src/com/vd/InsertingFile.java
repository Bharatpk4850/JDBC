package com.vd;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertingFile {
	

	
	public static final String driverClass = "com.mysql.cj.jdbc.Driver";
	public static final String url = "jdbc:mysql://localhost:3306/sys";
	public static final String username = "root";
	public static final String password = "root";
	
	
	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			// Load and Register the driver
			Class.forName(driverClass);
			// Establishing the connection
			con = DriverManager.getConnection(url, username, password);
			
			// getting file form below path
			File file=new File("C:\\Users\\kbharat\\Desktop\\vid\\vd.txt");
			FileReader fr=new FileReader(file);  

			ps=con.prepareStatement("insert into file_tbl (name,file) values(?,?)"); 
			ps.setString(1,"file-1");
			ps.setCharacterStream(2,fr,(int)file.length());
			int result=ps.executeUpdate();
			
			if(result ==0) {
				System.out.println("File not inserted ...please chec ");
			}else {
				System.out.println("File inderted secessfully");
			}
			
		}catch(Exception e) {
			System.out.println("please check the above steps"+e.getMessage());
		}finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
