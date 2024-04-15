package com.myp.myproject.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    public static Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/quanlykho",
                    "root", "root");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
