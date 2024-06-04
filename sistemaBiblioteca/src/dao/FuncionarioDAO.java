package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.ConnectionSQL;

public class FuncionarioDAO {
        public boolean adicionarFuncionario(String nome, String login, String senha, String tipo) {
        String sql = "INSERT INTO funcionarios (nome, login, senha, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, login);
            pstmt.setString(3, senha);
            pstmt.setString(4, tipo);
            pstmt.executeUpdate();
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
}