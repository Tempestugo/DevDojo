package org.example.Exercicios.TestesUnitarios;

import org.example.Exercicios.Terceiro.ConnectionFactory;
import org.example.ZZJCrud.conn.dominio.Anime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Transactions {
    public static void realizarAdocaoTransacao(Anime pet) {

        String sqlUpdate = "UPDATE producer SET name = ? WHERE id = ?";


        String sqlLog = "INSERT INTO pet_logs (descricao) VALUES (?)";

        Connection conn = null;

        try {
            conn = ConnectionFactory.getConnection();


            conn.setAutoCommit(false);

            try (PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate)) {
                psUpdate.setString(1, "ADOTADO - " + pet.getName());

                psUpdate.setInt(2, pet.getId());
                psUpdate.execute();
                System.out.println("Update executado (mas ainda não comitado)...");
            }

            // --- SIMULAÇÃO DE ERRO ---
            // Se o ID for 1, força o erro para testar o Rollback.
            if (pet.getId() == 1) {
                throw new SQLException("ERRO FORÇADO: O sistema caiu antes de salvar o log!");
            }

            // --- OPERAÇÃO 2: INSERT LOG ---
            try (PreparedStatement psLog = conn.prepareStatement(sqlLog)) {
                psLog.setString(1, "O pet " + pet.getId() + " foi adotado.");
                psLog.execute();
                System.out.println("Log inserido...");
            }

            // 2. SE CHEGOU AQUI, SALVAR TUDO (COMMIT)
            conn.commit();
            System.out.println("Transação finalizada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                // 3. SE DEU ERRO, DESFAZER TUDO (ROLLBACK)
                System.out.println("Erro detectado! Desfazendo alterações...");
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            // Boa prática: Voltar o auto-commit para true antes de fechar, ou apenas fechar.
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
