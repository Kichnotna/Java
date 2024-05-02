package com.example.data;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class H2Connector {
    private static final String driver = "org.h2.Driver";
    private static final String url = "jdbc:h2:file:C:\\Users\\xaker\\Desktop\\Homework\\Test\\database\\database";
    private static final String user = "user";
    private static final String password = "user";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    private static final String insertQuery = "INSERT INTO WORDS (UUID, WORD, COUNT, PATH) VALUES ( '%s', '%s', %s, '%s' )";
    private static final String getQuery = "SELECT COUNT, PATH FROM WORDS WHERE WORD = '%s' ORDER BY COUNT DESC";

    private static H2Connector instance;
    private H2Connector() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static synchronized H2Connector getInstance() {
        if (instance == null) {
            instance = new H2Connector();
        }

        return instance;
    }

    public void truncate() {
        try {
            statement.execute("TRUNCATE TABLE WORDS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertWord(String word, Long count, String path) {
        String uid = UUID.randomUUID().toString();
        String query = String.format(insertQuery, uid, word, count, path);
        try {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Long> getData(String word) {
        String query = String.format(getQuery, word);
        Map<String, Long> result = new HashMap<>();
        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                result.put(resultSet.getString("PATH"), resultSet.getLong("COUNT"));
            }
            return result;
        } catch (SQLException ignored) {
            return result;
        }
    }
}
