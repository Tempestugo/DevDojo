package org.example.ZZjdbc.repository;

import lombok.extern.log4j.Log4j2;
import org.example.ZZjdbc.dominio.ConnectionFactory;
import org.example.ZZjdbc.dominio.Producer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProducerRepository {
    public static void save(Producer producer){
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement()){
            int rowAffected = smt.executeUpdate(sql);
            log.info("Database rows affected {}",producer.getName(), rowAffected);
        } catch (SQLException e){
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
            e.printStackTrace();
        }
    }

        public static void delete(int id){
            String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id` = '4');".formatted(id);
            try(Connection conn = ConnectionFactory.getConncection();
                Statement smt = conn.createStatement()){
                int rowAffected = smt.executeUpdate(sql);
                log.info("Deleted producer '{}'",id, rowAffected);
            } catch (SQLException e){
                log.error("Error while trying to delete producer '{}'", id, e);
                e.printStackTrace();
            }
        }
    public static void update(Producer producer){
        String sql = "UPDATE `anime_store`.`producer` SET `name` = '%s' WHERE (`id` = '%d');"
                .formatted(producer.getName(),producer.getId());
        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement()){
            int rowAffected = smt.executeUpdate(sql);
            log.info("Update producer '{}'",producer.getId(), rowAffected);
        } catch (SQLException e){
            log.error("Error while trying to update producer '{}'", producer.getId(), e);
            e.printStackTrace();
        }
    }


    public static  List<Producer>  findAll(){
        log.info("Finding all producers");
        List<Producer> producers = new ArrayList<>();

//        return findByName("");


        String sql = "SELECT id, name from anime_store.producer;";
        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql)){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e){
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }



    public static  List<Producer> findByName(String name2){
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();

        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE '%%%s%%';"
                .formatted(name2);

        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql)){
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e){
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }

    public static void showProducerMetaData(){
        log.info("Showing Producer Metadata");
        String sql = "SELECT * FROM anime_store.producer";

        try(Connection conn = ConnectionFactory.getConncection();
            Statement smt = conn.createStatement();
            ResultSet rs = smt.executeQuery(sql)){
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            log.info ("Columns count '{}'", columnCount);
            for(int i = 1; i <= columnCount; i++){
                log.info("Table name'{}'", rsMetaData.getTableName(i));
                log.info("Column name'{}'", rsMetaData.getColumnName(i));
                log.info("Column size'{}'", rsMetaData.getColumnDisplaySize(i));
                log.info("Column type'{}'", rsMetaData.getColumnType(i));


            }


        } catch (SQLException e){
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }

    }

    public static void showDriverMetaData(){
        log.info("Showing Producer Metadata");

        try(Connection conn = ConnectionFactory.getConncection()){
            DatabaseMetaData dbMetaData = conn.getMetaData();
            if(dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)){
                log.info("TYPE_SCROLL_INSENSITIVE is supported");
                if(dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                };
            };
            if(dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)){
                log.info("ResultSet.TYPE_FORWARD_ONLY is supported");
                if(dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE)){
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                };
            };


            if(dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)){
                log.info("TYPE_SCROLL_INSENSITIVE is supported");
                if(dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE)){
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                };
            };
        } catch (SQLException e){

            e.printStackTrace();
        }

    }
}
