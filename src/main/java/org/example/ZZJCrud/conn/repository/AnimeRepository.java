package org.example.ZZJCrud.conn.repository;

import lombok.extern.log4j.Log4j2;
import org.example.ZZJCrud.conn.ConnectionFactory;
import org.example.ZZJCrud.conn.dominio.Anime;
import org.example.ZZJCrud.conn.dominio.Producer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class AnimeRepository {

    public static List<Anime> findByName(String name) {
        log.info("Finding Animes by name '{}'", name);
        // Inner Join para preencher o objeto Producer dentro do Anime
        String sql = """
                     SELECT a.id, a.name, a.episodes, a.producer_id, p.name as producer_name 
                     FROM anime_store.anime a 
                     INNER JOIN anime_store.producer p ON a.producer_id = p.id 
                     WHERE a.name like ?;
                     """;
        List<Anime> animes = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                animes.add(createAnime(rs));
            }
        } catch (SQLException e) {
            log.error("Error while trying to find anime by name", e);
        }
        return animes;
    }

    public static Optional<Anime> findById(Integer id) {
        log.info("Finding Anime by id '{}'", id);
        String sql = """
                     SELECT a.id, a.name, a.episodes, a.producer_id, p.name as producer_name 
                     FROM anime_store.anime a 
                     INNER JOIN anime_store.producer p ON a.producer_id = p.id 
                     WHERE a.id = ?;
                     """;
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(createAnime(rs));
            }
        } catch (SQLException e) {
            log.error("Error while trying to find anime by id", e);
        }
        return Optional.empty();
    }

    private static Anime createAnime(ResultSet rs) throws SQLException {
        Producer producer = Producer.builder()
                .name(rs.getString("producer_name"))
                .id(rs.getInt("producer_id"))
                .build();
        return Anime.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .episodes(rs.getInt("episodes"))
                .producer(producer)
                .build();
    }

    public static void delete(int id) {
        String sql = "DELETE FROM anime_store.anime WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.execute();
            log.info("Deleted anime '{}'", id);
        } catch (SQLException e) {
            log.error("Error while trying to delete anime", e);
        }
    }

    public static void save(Anime anime) {
        log.info("Saving Anime '{}'", anime);
        String sql = "INSERT INTO anime_store.anime (name, episodes, producer_id) VALUES (?, ?, ?);";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, anime.getName());
            ps.setInt(2, anime.getEpisodes());
            ps.setInt(3, anime.getProducer().getId());
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to save anime", e);
        }
    }

    public static void update(Anime anime) {
        log.info("Updating Anime '{}'", anime);
        String sql = "UPDATE anime_store.anime SET name = ?, episodes = ? WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, anime.getName());
            ps.setInt(2, anime.getEpisodes());
            ps.setInt(3, anime.getId());
            ps.execute();
        } catch (SQLException e) {
            log.error("Error while trying to update anime", e);
        }
    }
}
