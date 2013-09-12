package com.flipkart.digital.DatabaseStore;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum DBConnection {
	INSTANCE;
	public Connection getConnection() throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "ebooksclub" ;
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url+dbName,userName,password);


    }
		
}
