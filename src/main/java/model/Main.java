package model;

import model.database.Database;
import model.user.CredentialManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        for (int i = 1; i < 20; i++) {
            System.out.println(database.register("aa", "bb"));
        }
    }
}