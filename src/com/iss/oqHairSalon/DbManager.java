package com.iss.oqHairSalon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DbManager {
    private static String url;
    private static String dbUser;
    private static String dbPassword;

    public static Connection getConnection() {
        Connection conn = null;
        Properties props = new Properties();
        File file = new File("src/db_oracle.properties");
        try {
            FileInputStream fis = new FileInputStream(file);
            props.load(fis);
            url = props.getProperty("url");
            dbUser = props.getProperty("dbUser");
            dbPassword = props.getProperty("dbPassword");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(url, dbUser, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, User> getAllUsers() {
        HashMap<String, User> users = new HashMap<String, User>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "select * from oqusers";
        conn = getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.put(rs.getString(1), new User(rs.getString(1), rs.getString(2)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(stmt);
            close(rs);
        }
        return users;
    }

    public static boolean addUser(String name, String password) {
        String sql = "insert into oqusers(name,password) values(?,?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        conn = getConnection();
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, password);
            int i = stmt.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn);
            close(stmt);
        }
        return false;
    }

//    public static void main(String[] args) {
//        getAllUsers();
//    }
}
