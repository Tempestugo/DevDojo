package org.example.ZZJdbc.repository;

import org.example.ZZJdbc.dominio.ConnectionFactory;
import org.example.ZZJdbc.dominio.Producer;
import org.example.ZZJdbc.listener.CustomRowSetListener;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProducerRepositoryRowSet {
    public static List<Producer> findByNameJdbcRowSet(String name){
        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE ?;";
        List<Producer> producers = new ArrayList<>();
        try(JdbcRowSet jdbcRowSet = ConnectionFactory.getJdbcRowSet()){
            jdbcRowSet.addRowSetListener(new CustomRowSetListener());
            jdbcRowSet.setCommand(
                    sql);
            jdbcRowSet.setString(1, String.format("%%%s%%", name));
            jdbcRowSet.execute();
            while (jdbcRowSet.next()){
                Producer producer = Producer.builder()
                        .id(jdbcRowSet.getInt("id"))
                        .name(jdbcRowSet.getString("name"))
                        .build();
                producers.add(producer);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producers;
    }


//
//    public static void updateJdbcRowSet(Producer producer){
//        String sql = "UPDATE `anime_store`.`producer` SET `name` = '%s' WHERE (`id` = '%d');";
//        try(JdbcRowSet jdbcRowSet = ConnectionFactory.getJdbcRowSet()){
//            jdbcRowSet.setCommand(
//                    sql);
//            jdbcRowSet.setString(1, producer.getName());
//            jdbcRowSet.setInt(1, producer.getId());
//
//            jdbcRowSet.execute();
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public static void updateJdbcRowSet(Producer producer){
        String sql = "SELECT id, name FROM anime_store.producer WHERE (`id` = ?);";

        try(JdbcRowSet jdbcRowSet = ConnectionFactory.getJdbcRowSet()){
            jdbcRowSet.addRowSetListener(new CustomRowSetListener());

            jdbcRowSet.setCommand(sql);


            jdbcRowSet.setString(1, producer.getName());
            jdbcRowSet.setInt(1, producer.getId());
            jdbcRowSet.execute();
            if(jdbcRowSet.next()) return;
            jdbcRowSet.updateString("name",producer.getName());
            jdbcRowSet.updateRow();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateCachedRowSet(Producer producer){
        String sql = "SELECT id, name FROM anime_store.producer WHERE (`id` = ?);";

        try(CachedRowSet cachedRowSet = ConnectionFactory.CachedRowSet();
            Connection conn = ConnectionFactory.getConncection()){
            conn.setAutoCommit(false);

            cachedRowSet.setCommand(sql);


            cachedRowSet.setString(1, producer.getName());
            cachedRowSet.setInt(1, producer.getId());
            cachedRowSet.execute(conn);
            if(cachedRowSet.next()) return;
            cachedRowSet.updateString("name",producer.getName());
            cachedRowSet.updateRow();

            cachedRowSet.acceptChanges();


        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
