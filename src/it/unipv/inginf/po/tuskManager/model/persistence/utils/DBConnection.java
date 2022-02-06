package it.unipv.inginf.po.tuskManager.model.persistence.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
public class DBConnection {
	
	public static Connection startConnection(Connection conn) throws FileNotFoundException, IOException {
		
		String db_driver = null;
		String db_url = null;
		String username = null;
		String pw = null;
		
		Properties p = System.getProperties();
		p.load(new FileInputStream("config/db.txt"));
		
		db_driver = "com.mysql.cj.jdbc.Driver";
//		db_url = "jdbc:mysql://localhost:3306/"+schema;
		db_url = "jdbc:mysql://" + p.getProperty("db") + ":" + p.getProperty("porta") + "/" + p.getProperty("schema");
		username = p.getProperty("user");
		pw = p.getProperty("password");
		if(isOpen(conn))
			closeConnection(conn);
		
		try {
			
			Class.forName(db_driver);
			conn = DriverManager.getConnection(db_url, username, pw);
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
		return conn;
	}
	
	public static Connection startConnection() throws FileNotFoundException, IOException {
		return startConnection(null);
	}
	
	
	
	public static boolean isOpen(Connection con) {
		if(con == null)
			return false;
		return true;
	}
	
	public static Connection closeConnection(Connection con) {
		if(!isOpen(con))
			return null;
		try {
			con.close();
			con = null;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return con;
		
	}
	
}
