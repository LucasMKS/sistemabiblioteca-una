package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.ConnectionSQL;

public class FuncionarioDAO {

    public boolean adicionarFuncionario(String[] dadosUsuarios) {
        String checkLoginSql = "SELECT COUNT(*) FROM usuarios WHERE login = ?";
        String insertSql = "INSERT INTO usuarios (login, senha, tipo, nome, ra) VALUES (?, ?, ?, ?, ?)";
        String lastRaSql = "SELECT MAX(ra) FROM usuarios";
    
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement checkLoginStmt = conn.prepareStatement(checkLoginSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             Statement lastRaStmt = conn.createStatement()) {
    
            // Verificar se o login já existe
            checkLoginStmt.setString(1, dadosUsuarios[0]);
            ResultSet rs = checkLoginStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("\nErro: Login já existente... Voltando ao menu principal.");
                pause(5);
                return false;
            }
    
            // Obter o último RA cadastrado
            int lastRa = 15129; // Valor inicial para o caso de não haver RA cadastrado
            ResultSet lastRaRs = lastRaStmt.executeQuery(lastRaSql);
            if (lastRaRs.next() && lastRaRs.getInt(1) > 0) {
                lastRa = lastRaRs.getInt(1);
            }
    
            // Inserir novo usuário
            insertStmt.setString(1, dadosUsuarios[0]);
            insertStmt.setString(2, dadosUsuarios[1]);
            insertStmt.setString(3, dadosUsuarios[2]);
            insertStmt.setString(4, dadosUsuarios[3]);
            insertStmt.setInt(5, lastRa + 1);
            insertStmt.executeUpdate();
            System.out.println("\nUsuário cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    

    public boolean atualizarFuncionario(int id, String nome, String login, String senha, String tipo) {
        String sql = "UPDATE funcionarios SET nome = ?, login = ?, senha = ?, tipo = ? WHERE id = ?";
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setString(4, tipo);
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
