package model;

import model.database.Database;
import model.user.CredentialManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Database.register("giangvien01", "abc123");
    }
}