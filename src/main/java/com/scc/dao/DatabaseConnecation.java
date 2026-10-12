package com.scc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnecation {
    private  static final  String DB_URL ="jdbc:sqlite:CAS.db";

    public static Connection getConnection()throws SQLException {
       return DriverManager.getConnection(DB_URL);

    }
}
