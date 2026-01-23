package org.example.ZZJdbc.dominio;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
    public static JdbcRowSet getJdbcRowSet() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/anime_store";
        String username = "root";
        String password = "XQDprXg$@gHH!AUz";
        JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();
        jdbcRowSet.setUrl(url);
        jdbcRowSet.setUsername(username);
        jdbcRowSet.setPassword(password);
        return jdbcRowSet;

    }
    public static CachedRowSet CachedRowSet() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/anime_store";
        String username = "root";
        String password = "XQDprXg$@gHH!AUz";
        CachedRowSet cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
        cachedRowSet.setUrl(url);
        cachedRowSet.setUsername(username);
        cachedRowSet.setPassword(password);
        return cachedRowSet;

    }
}