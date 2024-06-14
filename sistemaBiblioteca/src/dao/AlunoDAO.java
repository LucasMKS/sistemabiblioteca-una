package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Credenciais;
import utils.ConnectionSQL;

public class AlunoDAO {

    public static void verificarCadastro(Credenciais credenciaisValidas) {
        String sql = "SELECT login, tipo, nome, matricula FROM usuarios WHERE login = ?";

        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Define o parâmetro do login do usuário logado
            pstmt.setString(1, credenciaisValidas.getLogin());

            try (ResultSet rs = pstmt.executeQuery()) {
                // Verificar se há resultados
                if (rs.next()) {
                    String login = rs.getString("login");
                    String tipo = rs.getString("tipo");
                    String nome = rs.getString("nome");
                    int matricula = rs.getInt("matricula");
                    
                    System.out.printf("%-20s %-20s %-30s %-30s", 
                                      "Login", "Tipo", "Nome", "Matricula");
                    System.out.println("\n-------------------------------------------------------------------------------------");

                    // Imprimir dados
                    System.out.printf("%-20s %-20s %-30s %-30s \n", 
                                      login, tipo, nome, matricula);                  

                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuário: " + e.getMessage());
        }
    }
    
}
