package com.Ust.ocs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public interface DBUtil {
    static Connection getDBConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/cbh";
        String username = "root";  // your MySQL username
        String password = "pass@word1";  // your MySQL password
        return DriverManager.getConnection(url, username, password);
    }
}
