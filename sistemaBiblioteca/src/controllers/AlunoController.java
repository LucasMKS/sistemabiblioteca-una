package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.LivroDAO;
import models.Credenciais;

import utils.ClearConsole;
import utils.ConnectionSQL;

public class AlunoController {

    public void processarOpcao(int opcao, Credenciais credenciaisValidas) {
        switch (opcao) {
            case 1:
                ClearConsole.clear();
                LivroDAO.listarLivros();
                break;
            case 2:
                ClearConsole.clear();
                verificarCadastro(credenciaisValidas);
                break;
            case 3:
                ClearConsole.clear();
                // Implementar a lógica para "Status Emprestimo"
                System.out.println("Status do Empréstimo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }


    public static void verificarCadastro(Credenciais credenciaisValidas) {
        String sql = "SELECT login, tipo, nome FROM usuarios WHERE login = ?";
    
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            // Define o parâmetro do login do usuário logado
            pstmt.setString(1, credenciaisValidas.getLogin());
    
            try (ResultSet rs = pstmt.executeQuery()) {
                // Verificar se há resultados
                if (rs.next()) {
                    // Imprimir cabeçalhos
                    System.out.printf("%-20s %-20s %-30s%n", 
                                      "Login", "Tipo", "Nome");
                    System.out.println("--------------------------------------------------------------");
    
                    String login = rs.getString("login");
                    String tipo = rs.getString("tipo");
                    String nome = rs.getString("nome");
    
                    // Imprimir dados
                    System.out.printf("%-20s %-20s %-30s%n", 
                                      login, tipo, nome);
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuário: " + e.getMessage());
        }
    }

}
