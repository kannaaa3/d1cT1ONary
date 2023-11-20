package model;

import model.database.Database;
import model.user.CredentialManager;

import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String[] synonym = Database.getSynonym("hello");
        for (String s : synonym) {
            System.out.println(s);
        }
    }
}