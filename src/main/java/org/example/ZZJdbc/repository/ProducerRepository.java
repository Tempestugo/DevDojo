package org.example.ZZJdbc.repository;

import lombok.extern.log4j.Log4j2;
import org.example.ZZJdbc.dominio.ConnectionFactory;
import org.example.ZZJdbc.dominio.Producer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
public class ProducerRepository {
    public static void save(Producer producer) {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES ('%s');".formatted(producer.getName());
        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement()) {
            int rowAffected = smt.executeUpdate(sql);
            log.info("Database rows affected {}", producer.getName(), rowAffected);
        } catch (SQLException e) {
            log.error("Error while trying to insert producer '{}'", producer.getName(), e);
            e.printStackTrace();
        }
    }


    public static void saveTransaction(List<Producer> producers) {

        try (Connection conn = ConnectionFactory.getConncection()) {
            preparedStatementsaveTransaction(conn,producers);
            conn.commit();


        }
        catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producers, e);
            e.printStackTrace();
        }
    }

    private static void  preparedStatementsaveTransaction(Connection connection, List<Producer> producers) throws SQLException {
        String sql = "INSERT INTO `anime_store`.`producer` (`name`) VALUES (?);";
        boolean shouldRollback = false;
        for (Producer p : producers) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                log.info("Saving Producer: '{}'",p.getName());
                ps.setString(1, p.getName());
                ps.executeUpdate();

            }catch (SQLException e){
                e.printStackTrace();
                shouldRollback = true;

            }
        }
        if(shouldRollback){
            log.warn("Transaction rolled back");
            connection.rollback();
        }

    }


    public static void delete(int id) {
        String sql = "DELETE FROM `anime_store`.`producer` WHERE (`id` = '4');".formatted(id);
        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement()) {
            int rowAffected = smt.executeUpdate(sql);
            log.info("Deleted producer '{}'", id, rowAffected);
        } catch (SQLException e) {
            log.error("Error while trying to delete producer '{}'", id, e);
            e.printStackTrace();
        }
    }

    public static void update(Producer producer) {
        String sql = "UPDATE `anime_store`.`producer` SET `name` = '%s' WHERE (`id` = '%d');"
                .formatted(producer.getName(), producer.getId());
        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement()) {
            int rowAffected = smt.executeUpdate(sql);
            log.info("Update producer '{}'", producer.getId(), rowAffected);
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producer.getId(), e);
            e.printStackTrace();
        }
    }

    public static void updatePreparedStatement(Producer producer) {

        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = preparedStatementUpdate(conn, producer)) {
            int rowAffected = ps.executeUpdate();
            log.info("Update producer '{}'", producer.getId(), rowAffected);
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", producer.getId(), e);
            e.printStackTrace();
        }
    }

    private static PreparedStatement preparedStatementUpdate(Connection connection, Producer producer) throws SQLException {
        String sql = "UPDATE `anime_store`.`producer` SET `name` = ? WHERE (`id` = ?);";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", producer.getName()));
        ps.setInt(2, producer.getId());
        return ps;
    }


    public static List<Producer> findAll() {
        log.info("Finding all producers");
        List<Producer> producers = new ArrayList<>();

//        return findByName("");


        String sql = "SELECT id, name from anime_store.producer;";
        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }


    public static List<Producer> findByName(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();

        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE '%%%s%%';"
                .formatted(name2);

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }

    public static Optional<Producer> findById(Integer id) {
        log.info("Finding producer by id '{}'", id);
        String sql = "SELECT id, name FROM anime_store.producer WHERE id = ?;";
        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(Producer.builder().id(rs.getInt("id")).name(rs.getString("name")).build());
                }
            }
        } catch (SQLException e) {
            log.error("Error while trying to find producer by id", e);
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static List<Producer> findByNamePreparedStatement(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = preparedStatementFindByName(conn, name2);
             ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }

    private static PreparedStatement preparedStatementFindByName(Connection connection, String name) throws SQLException {
        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE ?;";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.format("%%%s%%", name));
        return ps;
    }


    public static List<Producer> findByNameCallableStatement(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();


        try (Connection conn = ConnectionFactory.getConncection();
             PreparedStatement ps = callableStatementFindByName(conn, name2);
             ResultSet rs = ps.executeQuery();
        ) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Producer producer = Producer.builder().id(id).name(name).build();
                producers.add(producer);
            }
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }

    private static CallableStatement callableStatementFindByName(Connection connection, String name) throws SQLException {
        String sql = "CALL `anime_store`.`sp_get_producer_by_name`('NHK');";
        CallableStatement cs = connection.prepareCall(sql);
        cs.setString(1, String.format("%%%s%%", name));
        return cs;
    }


    public static void showProducerMetaData() {
        log.info("Showing Producer Metadata");
        String sql = "SELECT * FROM anime_store.producer";

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery(sql)) {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            log.info("Columns count '{}'", columnCount);
            for (int i = 1; i <= columnCount; i++) {
                log.info("Table name'{}'", rsMetaData.getTableName(i));
                log.info("Column name'{}'", rsMetaData.getColumnName(i));
                log.info("Column size'{}'", rsMetaData.getColumnDisplaySize(i));
                log.info("Column type'{}'", rsMetaData.getColumnType(i));


            }


        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }

    }

    public static void showTypeScrollWorking() {
        String sql = "SELECT id, name FROM anime_store.producer;";

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = smt.executeQuery(sql)) {
            log.info("First row?'{}'", rs.last());


            log.info("Last row? '{}", rs.last());
            log.info("Row number: '{}'", rs.getRow());
            log.info(Producer
                    .builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build());
            log.info("Row Absolute'{}", rs.absolute(2));
            log.info("Row number'{}", rs.getRow());

            log.info("Row Absolute'{}", rs.relative(-1));
            log.info("Row number ''{}", rs.getRow());

            log.info("is last? '{}'", rs.isLast());
            log.info("Row number ''{}", rs.getRow());

            log.info("is first? '{}'", rs.isFirst());
            log.info("Row number'{}", rs.getRow());

            log.info("is isBeforeFirst? '{}'", rs.isBeforeFirst());
            log.info("Row number'{}", rs.getRow());

            log.info("is isAfterLast? '{}'", rs.isAfterLast());
            log.info("Row number'{}", rs.getRow());


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Producer> findByNameAndUpdateToUpperCase(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();

        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE '%%%s%%';";

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = smt.executeQuery(sql)) {
            while (rs.next()) {
                rs.updateString("name", rs.getString("name").toUpperCase());
//                rs.cancelRowUpdates();
                rs.updateRow();
                Producer producer = Producer
                        .builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build();


            }
        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }


    public static List<Producer> findByNameAndInsertWhereNotFound(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();

        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE '%%%s%%';";

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = smt.executeQuery(sql)) {
            if (rs.next()) return producers;
            insertNewProducer(name2, rs);


        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
        return producers;
    }

    private static void insertNewProducer(String name2, ResultSet rs) throws SQLException {
        rs.moveToInsertRow();
        rs.updateString("name", name2);
        rs.insertRow();
    }

    private static Producer getProducer(ResultSet rs) throws SQLException {
        rs.beforeFirst();
        rs.next();
        return Producer
                .builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .build();
    }

    public static void findByNameAndDelete(String name2) {
        log.info("Finding producer by name");
        List<Producer> producers = new ArrayList<>();

        String sql = "SELECT id, name FROM anime_store.producer WHERE name LIKE '%%%s%%';";

        try (Connection conn = ConnectionFactory.getConncection();
             Statement smt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = smt.executeQuery(sql)) {
            while (rs.next()) {
                log.info("Deleting '{}'", rs.getString("name"));
                rs.deleteRow();
            }


        } catch (SQLException e) {
            log.error("Error while trying to update producer '{}'", e);
            e.printStackTrace();
        }
    }

    public static void showDriverMetaData() {
        log.info("Showing Producer Metadata");

        try (Connection conn = ConnectionFactory.getConncection()) {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                log.info("TYPE_SCROLL_INSENSITIVE is supported");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                }
                ;
            }
            ;
            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                log.info("ResultSet.TYPE_FORWARD_ONLY is supported");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                }
                ;
            }
            ;


            if (dbMetaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                log.info("TYPE_SCROLL_INSENSITIVE is supported");
                if (dbMetaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                    log.info("ResultSet.CONCUR_UPDATABLE is supported");

                }
                ;
            }
            ;
        } catch (SQLException e) {

            e.printStackTrace();


        }


    }


}
