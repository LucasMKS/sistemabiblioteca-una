package controllers;

import models.Livro;
import utils.ConnectionSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Date;


public class FuncionarioController {

    // Método para cadastrar um novo livro no sistema
    public static boolean cadastrarLivro() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Informe o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Informe o ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Informe a categoria:");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn, categoria);
        scanner.close();

        String sql = "INSERT INTO livros (titulo, autor, isbn, categoria) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, livro.getTitulo());
            pstmt.setString(2, livro.getAutor());
            pstmt.setString(3, livro.getISBN());
            pstmt.setString(4, livro.getCategoria());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            return false;
        }

        
    }

    // Método para registrar um empréstimo de livro
    public static boolean registrarEmprestimo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe o ID do livro:");
        int livroId = scanner.nextInt();

        System.out.println("Informe o ID do usuario:");
        int usuarioId = scanner.nextInt();


        scanner.close();
        String sql = "INSERT INTO emprestimos (livro_id, usuario_id, data_emprestimo) VALUES (?, ?, ?)";
        // Obtém a data atual
        Date dataEmprestimo = new Date(System.currentTimeMillis());
        
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, livroId);
            pstmt.setInt(2, usuarioId);
            pstmt.setDate(3, dataEmprestimo);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
            return false;
        }
    }

    // Método para registrar a devolução de um livro
    public boolean registrarDevolucao(int emprestimoId, Date dataDevolucao) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, dataDevolucao);
            pstmt.setInt(2, emprestimoId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
            return false;
        }
    }
    
    // Adicionar mais funcionalidades conforme a necessidade do sistema
}
