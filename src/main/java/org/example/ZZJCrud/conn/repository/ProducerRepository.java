package org.example.ZZJCrud.conn.repository;


import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.example.ZZJCrud.conn.ConnectionFactory;
import org.example.ZZJCrud.conn.dominio.Producer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ProducerRepository {

    public static List<Producer> findByName(String name) {
        log.info("Finding Producers by name '{}'", name);
        String sql = "SELECT * FROM anime_store.producer WHERE name like ?;";
        List<Producer> producers = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producer producer = Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find producer by name", e);
        }
        return producers;
    }

    public static Optional<Producer> findById(Integer id) {
        log.info("Finding Producer by id '{}'", id);
        String sql = "SELECT * FROM anime_store.producer WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producer producer = Producer.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();
                return Optional.of(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find producer by id", e);
        }
        return Optional.empty();
    }

    public static void delete(int id) {
        String sql = "DELETE FROM anime_store.producer WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            log.info("Deleted producer '{}'", id);
        } catch (SQLException e) {
            log.error("Error while trying to delete producer", e);
        }
    }

    public static void save(Producer producer) {
        log.info("Saving Producer '{}'", producer);
        String sql = "INSERT INTO anime_store.producer (name) VALUES (?);";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producer.getName());
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to save producer", e);
        }
    }

    public static void update(Producer producer) {
        log.info("Updating Producer '{}'", producer);
        String sql = "UPDATE anime_store.producer SET name = ? WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, producer.getName());
            ps.setInt(2, producer.getId());
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to update producer", e);
        }
    }
}
