package com.example.demo.handler.java;

import org.hibernate.sql.Select;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseWorker {
    protected static Connection connection;
    protected static Statement stmt;
    private static PreparedStatement psSelectInput;
    private static PreparedStatement psSelectOutput;
    static final String DB_URL = "jdbc:postgresql://localhost:5432/homework_checker";
    static final String USER = "postgres";
    static final String PASS = "Cattg2611&";

    public DatabaseWorker() {
        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected ArrayList<String> getInputData(String numberTask) {
        ArrayList<String> inputDataList = new ArrayList();
        try {
            psSelectInput.setString(1, numberTask);
            ResultSet rs = psSelectInput.executeQuery();
            for (int i = 0; rs.next(); i++) {
                if (rs.getString("input") != null) {
                    inputDataList.add(i, rs.getString("input"));
                } else {
                    inputDataList.add(i, rs.getString(""));
                }
            }
            return inputDataList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    protected ArrayList<String> getOutputData(String numberTask) {
        ArrayList<String> outputDataList = new ArrayList();
        try {
            psSelectOutput.setString(1, numberTask);
            ResultSet rs = psSelectOutput.executeQuery();
            for (int i = 0; rs.next(); i++) {
                if (rs.getString("output") != null) {
                    String data = rs.getString("output");
                    outputDataList.add(i, data);
                } else {
                    outputDataList.add(i, "");
                }
            }
            return outputDataList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void disconnect() {
        try {
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void connect() throws ClassNotFoundException, SQLException {
        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }
}
