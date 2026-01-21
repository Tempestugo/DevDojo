package org.example.ZZjdbc.dominio;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
    public static Connection getConncection(){
        String url = "jdbc:mysql://127.0.0.1:3306/anime_store";
        String username = "root";
        String password = "XQDprXg$@gHH!AU ";
        try {
          return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}