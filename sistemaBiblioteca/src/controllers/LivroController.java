package controllers;

import models.Livro;
import utils.ConnectionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    // Método para cadastrar um novo livro no sistema
    public boolean cadastrarLivro(String[] livroDadoStrings) {

        Livro livro = new Livro(livroDadoStrings[0],livroDadoStrings[1],livroDadoStrings[2],livroDadoStrings[3]);

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
    public boolean registrarEmprestimo(int livroId, int usuarioId) {
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
}