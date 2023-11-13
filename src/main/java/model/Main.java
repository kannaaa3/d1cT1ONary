package model;

import model.user.CredentialManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
            System.out.println("Database Opened...\n");
            stmt = c.createStatement();

            String sql = "";

            stmt.executeUpdate(sql);

            stmt.close();

            c.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table Product Created Successfully!!!");

        // CredentialManager.loginWithUsernameAndPassword("aa", "hello");
    }
}