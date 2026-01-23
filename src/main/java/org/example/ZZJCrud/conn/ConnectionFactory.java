package org.example.ZZJCrud.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConncection(){
        String url = "jdbc:mysql://127.0.0.1:3306/anime_store";
        String username = "root";
        String password = "XQDprXg$@gHH!AUz";
        try {
          return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}