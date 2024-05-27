package controllers;

import models.Livro;
import utils.ConnectionSQL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.LivroDAO;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    // Método para cadastrar um novo livro no sistema
    public boolean cadastrarLivro(String[] livroDadoStrings) {
        Livro livro = new Livro(livroDadoStrings[0], livroDadoStrings[1], livroDadoStrings[2], livroDadoStrings[3], livroDadoStrings[4] );

        String checkSql = "SELECT titulo FROM livros WHERE isbn = ?";
        String insertSql = "INSERT INTO livros (titulo, autor, isbn, categoria, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionSQL.getConnection();
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            
            // Verificar se o ISBN já existe
            checkStmt.setString(1, livro.getISBN());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) {
                String existingBookTitle = rs.getString("titulo");
                System.out.println("Erro ao cadastrar livro: ISBN já cadastrado para o livro '" + existingBookTitle + "'.");
                return false;
            }

            // Inserir o livro
            insertStmt.setString(1, livro.getTitulo());
            insertStmt.setString(2, livro.getAutor());
            insertStmt.setString(3, livro.getISBN());
            insertStmt.setString(4, livro.getCategoria());
            insertStmt.setString(5, livro.getQuantidade());
            insertStmt.executeUpdate();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar livro: " + e.getMessage());
            return false;
        }
    }  

    // Método para registrar um empréstimo de livro
    public boolean emprestarLivro(String[] dados) {
        // Obter a data e hora atual
        LocalDateTime now = LocalDateTime.now();
        // Formatar datas para strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataEmprestimoStr = now.format(formatter);
    
        String checkQuantidadeSql = "SELECT quantidade FROM livros WHERE isbn = ?";
        String checkEmprestadosSql = "SELECT COUNT(*) AS emprestados FROM emprestimos WHERE isbn = ? AND data_devolucao IS NULL";
        String checkEmprestimoAlunoSql = "SELECT COUNT(*) AS emprestados FROM emprestimos WHERE aluno_id = ? AND isbn = ? AND data_devolucao IS NULL";
        String inserirEmprestimoSql = "INSERT INTO emprestimos (aluno_id, isbn, data_emprestimo) VALUES (?, ?, ?)";
    
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement checkQuantidadeStmt = conn.prepareStatement(checkQuantidadeSql);
             PreparedStatement checkEmprestadosStmt = conn.prepareStatement(checkEmprestadosSql);
             PreparedStatement checkEmprestimoAlunoStmt = conn.prepareStatement(checkEmprestimoAlunoSql);
             PreparedStatement insertStmt = conn.prepareStatement(inserirEmprestimoSql)) {
    
            // Verificar se o aluno já possui um exemplar com o mesmo ISBN emprestado
            checkEmprestimoAlunoStmt.setInt(1, Integer.parseInt(dados[0]));
            checkEmprestimoAlunoStmt.setInt(2, Integer.parseInt(dados[1]));
            ResultSet emprestimoAlunoResult = checkEmprestimoAlunoStmt.executeQuery();
            if (emprestimoAlunoResult.next()) {
                int emprestadosAluno = emprestimoAlunoResult.getInt("emprestados");
                if (emprestadosAluno > 0) {
                    System.out.println("Erro ao registrar empréstimo: O aluno já possui um exemplar com o mesmo ISBN emprestado.");
                    return false;
                }
            }
    
            // Verificar a quantidade disponível
            checkQuantidadeStmt.setString(1, dados[1]);
            ResultSet quantidadeResult = checkQuantidadeStmt.executeQuery();
            if (quantidadeResult.next()) {
                int quantidadeTotal = quantidadeResult.getInt("quantidade");
    
                // Verificar quantos exemplares estão emprestados
                checkEmprestadosStmt.setString(1, dados[1]);
                ResultSet emprestadosResult = checkEmprestadosStmt.executeQuery();
                if (emprestadosResult.next()) {
                    int exemplaresEmprestados = emprestadosResult.getInt("emprestados");
    
                    // Calcular quantidade disponível
                    int disponiveis = quantidadeTotal - exemplaresEmprestados;
                    if (disponiveis > 0) {
                        // Registrar o empréstimo
                        insertStmt.setInt(1, Integer.parseInt(dados[0]));
                        insertStmt.setInt(2, Integer.parseInt(dados[1]));
                        insertStmt.setString(3, dataEmprestimoStr);
                        insertStmt.executeUpdate();
                        return true;
                    } else {
                        System.out.println("Erro ao registrar empréstimo: Nenhum exemplar disponível.");
                        return false;
                    }
                }
            }
    
            System.out.println("Erro ao registrar empréstimo: Livro não encontrado ou erro de banco de dados.");
            return false;
    
        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
            return false;
        }
    }
    

    // Método para registrar a devolução de um livro
    /*public boolean devolverLivro(String[] dados) {
        String sql = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, dados[0]);
            pstmt.setInt(2, dados[1]);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
            return false;
        }
    }
    */

    public static void listarLivros() {
        String sql = "SELECT id_livro, titulo, autor, isbn, categoria, quantidade FROM livros";

        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Imprimir cabeçalhos
            System.out.printf("%-10s %-30s %-20s %-15s %-30s %-10s%n", 
                              "ID", "Título", "Autor", "ISBN", "Categoria", "Quantidade");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int idLivro = rs.getInt("id_livro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");

                // Imprimir dados
                System.out.printf("%-10d %-30s %-20s %-15s %-30s %-10d%n", 
                                  idLivro, titulo, autor, isbn, categoria, quantidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

}