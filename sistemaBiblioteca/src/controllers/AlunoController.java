package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

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
                LivroDAO statusEmprestimo = new LivroDAO();
                Scanner scanner = new Scanner(System.in);
                String ra = scanner.nextLine();
                List<String> livrosEmprestados = statusEmprestimo.verificarStatusEmprestimo(ra);

                if (livrosEmprestados.isEmpty()) {
                    System.out.println("Nenhum livro emprestado para o aluno com RA: " + ra);
                } else {
                    System.out.println("Livros emprestados para o aluno com RA: " + ra);
                    for (String livro : livrosEmprestados) {
                        System.out.println(livro);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
    }


    public static void verificarCadastro(Credenciais credenciaisValidas) {
        String sql = "SELECT login, tipo, nome FROM usuarios WHERE login = ?";
        String sqlAluno = "SELECT login, tipo, nome, RA FROM usuarios WHERE login = ?";

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

                    // Imprimir cabeçalhos
                    if (tipo.equals("aluno")) {
                        // Se for aluno, adicionar coluna de RA
                        System.out.printf(" %-8s", "RA");
                    }
                    System.out.printf("%-20s %-20s %-30s", 
                                      "Login", "Tipo", "Nome");
                    System.out.println("\n--------------------------------------------------------------");


                    if (tipo.equals("aluno")) {
                        // Obter o RA se for aluno
                        try (PreparedStatement pstmtAluno = conn.prepareStatement(sqlAluno)) {
                            pstmtAluno.setString(1, credenciaisValidas.getLogin());
                            try (ResultSet rsAluno = pstmtAluno.executeQuery()) {
                                if (rsAluno.next()) {
                                    String ra = rsAluno.getString("RA");
                                    System.out.printf(" %-8s", ra);
                                }
                            }
                        }
                    }
                    // Imprimir dados
                    System.out.printf("%-20s %-20s %-30s", 
                                      login, tipo, nome);

                    

                    System.out.println();
                } else {
                    System.out.println("Usuário não encontrado.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuário: " + e.getMessage());
        }
    }
}
