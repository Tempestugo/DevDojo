package org.example.Exercicios.Terceiro;

import java.sql.*;

import static org.example.Exercicios.Terceiro.ConnectionFactory.*;

public class ProducerRepository {
    
    public void save() {
        String sql = "INSERT INTO producer (name) VALUES ('Studio Ghibli')";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            int linhasAfetadas = stmt.executeUpdate(sql);
            System.out.println("Linhas afetadas: " + linhasAfetadas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void findAll() {
        String sql = "SELECT %d FROM producer";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("name");
                System.out.println(id + " - " + nome);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertSeguro() {
        String sql = "INSERT INTO producer (name) VALUES (?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "nome");

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        String sql = "UPDATE producer SET name = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "nome");
            ps.setInt(2,1);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
