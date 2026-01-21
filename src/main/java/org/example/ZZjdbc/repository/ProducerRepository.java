package org.example.ZZjdbc.repository;

import lombok.extern.log4j.Log4j2;
import org.example.ZZjdbc.dominio.ConnectionFactory;
import org.example.ZZjdbc.dominio.Producer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class ProducerRepository {
    public static void save(Producer producer){
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement()){
            int rowAffected = smt.executeUpdate(sql);
            log.info("Database rows affected {}",rowAffected);
        } catch (SQLException e){
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
            e.printStackTrace();
        }
    }
}
