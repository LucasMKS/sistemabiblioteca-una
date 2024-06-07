package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import models.Livro;
import utils.ConnectionSQL;

public class LivroDAO {
    private Connection conn;

    public LivroDAO() {
        this.conn = ConnectionSQL.getConnection(); // Obter conexão com o banco de dados
    }

    public boolean cadastrarLivro(String[] livroDadoStrings) {
        Livro livro = new Livro(livroDadoStrings[0], livroDadoStrings[1], livroDadoStrings[2], livroDadoStrings[3], livroDadoStrings[4] );

        String checkSql = "SELECT titulo FROM livros WHERE isbn = ?";
        String insertSql = "INSERT INTO livros (titulo, autor, isbn, categoria, quantidade) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql);
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

    public boolean emprestarLivro(String[] dados) {
        // Obter a data e hora atual
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataEmprestimoStr = now.format(formatter);

        // Calcular data de devolução
        LocalDateTime dataDevolucao = now.plusDays(7);
        String dataDevolucaoStr = dataDevolucao.format(formatter);

        String getAlunoIdSql = "SELECT id_usuarios FROM usuarios WHERE ra = ?";
        String checkQuantidadeSql = "SELECT quantidade FROM livros WHERE isbn = ?";
        String checkEmprestadosSql = "SELECT COUNT(*) AS emprestados FROM emprestimos WHERE isbn = ? AND status = true";
        String checkEmprestimoAlunoSql = "SELECT COUNT(*) AS emprestados FROM emprestimos WHERE aluno_id = ? AND isbn = ? AND status = true";
        String checkEmprestimoExistenteSql = "SELECT * FROM emprestimos WHERE aluno_id = ? AND isbn = ?";
        String atualizarEmprestimoSql = "UPDATE emprestimos SET status = true, data_emprestimo = ?, data_devolucao = ? WHERE aluno_id = ? AND isbn = ?";
        String inserirEmprestimoSql = "INSERT INTO emprestimos (aluno_id, isbn, data_emprestimo, data_devolucao, status) VALUES (?, ?, ?, ?, true)";

        try (PreparedStatement getAlunoIdStmt = conn.prepareStatement(getAlunoIdSql);
             PreparedStatement checkQuantidadeStmt = conn.prepareStatement(checkQuantidadeSql);
             PreparedStatement checkEmprestadosStmt = conn.prepareStatement(checkEmprestadosSql);
             PreparedStatement checkEmprestimoAlunoStmt = conn.prepareStatement(checkEmprestimoAlunoSql);
             PreparedStatement checkEmprestimoExistenteStmt = conn.prepareStatement(checkEmprestimoExistenteSql);
             PreparedStatement atualizarEmprestimoStmt = conn.prepareStatement(atualizarEmprestimoSql);
             PreparedStatement insertStmt = conn.prepareStatement(inserirEmprestimoSql)) {

            // Obter o ID do aluno a partir do RA
            int alunoId = getAlunoIdFromRa(getAlunoIdStmt, dados[0]);
            if (alunoId == -1) {
                System.out.println("Erro: Aluno não encontrado.");
                return false;
            }
            dados[0] = String.valueOf(alunoId); // Atualiza o array de dados com o ID do aluno

            // Verificar a quantidade disponível
            int quantidadeDisponivel = verificarQuantidadeDisponivel(checkQuantidadeStmt, checkEmprestadosStmt, dados[1]);
            if (quantidadeDisponivel <= 0) {
                System.out.println("Erro ao registrar empréstimo: Nenhum exemplar disponível.");
                return false;
            }

            // Verificar se o aluno já possui um exemplar com o mesmo ISBN emprestado
            if (alunoPossuiEmprestimoAtivo(checkEmprestimoAlunoStmt, dados)) {
                System.out.println("Erro ao registrar empréstimo: O aluno já possui um exemplar com o mesmo ISBN emprestado.");
                return false;
            }

            // Verificar se já existe um empréstimo para este aluno e livro
            checkEmprestimoExistenteStmt.setInt(1, Integer.parseInt(dados[0]));
            checkEmprestimoExistenteStmt.setInt(2, Integer.parseInt(dados[1]));
            ResultSet emprestimoExistenteResult = checkEmprestimoExistenteStmt.executeQuery();
            if (emprestimoExistenteResult.next()) {
                // Atualizar o empréstimo existente
                atualizarEmprestimoStmt.setString(1, dataEmprestimoStr);
                atualizarEmprestimoStmt.setString(2, dataDevolucaoStr);
                atualizarEmprestimoStmt.setInt(3, Integer.parseInt(dados[0]));
                atualizarEmprestimoStmt.setInt(4, Integer.parseInt(dados[1]));
                atualizarEmprestimoStmt.executeUpdate();
            } else {
                // Inserir um novo empréstimo
                insertStmt.setInt(1, Integer.parseInt(dados[0]));
                insertStmt.setInt(2, Integer.parseInt(dados[1]));
                insertStmt.setString(3, dataEmprestimoStr);
                insertStmt.setString(4, dataDevolucaoStr);
                insertStmt.executeUpdate();
            }

            String nomeLivro = obterNomeLivro(dados[1]);
            String nomeUsuario = obterNomeUsuario(dados[0]);

            // Exibir mensagem de sucesso com detalhes
            System.out.println("--------------------------------------------------");
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Livro: " + nomeLivro);
            System.out.println("Usuário: " + nomeUsuario);
            System.out.println("Data de Devolução: " + dataDevolucaoStr);
            System.out.println("--------------------------------------------------");

            return true;

        } catch (SQLException e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
            return false;
        }
    }

    private boolean alunoPossuiEmprestimoAtivo(PreparedStatement stmt, String[] dados) throws SQLException {
        stmt.setInt(1, Integer.parseInt(dados[0]));
        stmt.setString(2, dados[1]);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int emprestadosAluno = rs.getInt("emprestados");
            return emprestadosAluno > 0;
        }
        return false;
    }
    

    private int verificarQuantidadeDisponivel(PreparedStatement checkQuantidadeStmt, PreparedStatement checkEmprestadosStmt, String isbn) throws SQLException {
        checkQuantidadeStmt.setInt(1, Integer.parseInt(isbn));
        ResultSet quantidadeResult = checkQuantidadeStmt.executeQuery();
        if (quantidadeResult.next()) {
            int quantidadeTotal = quantidadeResult.getInt("quantidade");

            checkEmprestadosStmt.setInt(1, Integer.parseInt(isbn));
            ResultSet emprestadosResult = checkEmprestadosStmt.executeQuery();
            if (emprestadosResult.next()) {
                int exemplaresEmprestados = emprestadosResult.getInt("emprestados");

                return quantidadeTotal - exemplaresEmprestados;
            }
        }
        return 0;
    }

    public boolean devolverLivro(String[] dados) {
        // Obter a data atual
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dataDevolucaoStr = now.format(formatter);
        
        String getAlunoIdSql = "SELECT id_usuarios FROM usuarios WHERE ra = ?";
        String sql = "UPDATE emprestimos SET data_devolucao = ?, status = false WHERE aluno_id = ? AND isbn = ? AND status = true";
        String obterNomeLivroSql = "SELECT titulo FROM livros WHERE isbn = ?";
        String obterNomeUsuarioSql = "SELECT nome FROM usuarios WHERE id_usuarios = ?";
    
        try (PreparedStatement getAlunoIdStmt = conn.prepareStatement(getAlunoIdSql);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             PreparedStatement obterNomeLivroStmt = conn.prepareStatement(obterNomeLivroSql);
             PreparedStatement obterNomeUsuarioStmt = conn.prepareStatement(obterNomeUsuarioSql)) {

            int alunoId = getAlunoIdFromRa(getAlunoIdStmt, dados[0]);
            if (alunoId == -1) {
                System.out.println("Erro: Aluno não encontrado.");
                return false;
            }
            dados[0] = String.valueOf(alunoId);
    
            // Atualizar a data de devolução e o status do empréstimo
            pstmt.setString(1, dataDevolucaoStr);
            pstmt.setInt(2, Integer.parseInt(dados[0]));
            pstmt.setInt(3, Integer.parseInt(dados[1]));
            int rowsUpdated = pstmt.executeUpdate();
    
            if (rowsUpdated > 0) {
                String nomeLivro = obterNomeLivro(dados[1]);
                String nomeUsuario = obterNomeUsuario(dados[0]);
    
                // Exibir mensagem de sucesso com detalhes
                System.out.println("\nDevolução realizada com sucesso!");
                System.out.println("Livro: " + nomeLivro);
                System.out.println("Usuário: " + nomeUsuario);
                System.out.println("Data de Devolução: " + dataDevolucaoStr);
    
                return true;
            } else {
                System.out.println("Erro ao registrar devolução: Empréstimo não encontrado.");
                return false;
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao registrar devolução: " + e.getMessage());
            return false;
        }
    }

    public static void listarLivros() {
        String sql = "SELECT id_livro, titulo, autor, isbn, categoria, quantidade FROM livros";

        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            // Imprimir cabeçalhos
            System.out.printf("%-5s %-30s %-20s %-15s %-25s %-10s%n", 
                              "ID", "Título", "Autor", "ISBN", "Categoria", "Quantidade");
            System.out.println("----------------------------------------------------------------------------------------------------------------");

            while (rs.next()) {
                int idLivro = rs.getInt("id_livro");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                String isbn = rs.getString("isbn");
                String categoria = rs.getString("categoria");
                int quantidade = rs.getInt("quantidade");

                // Imprimir dados
                System.out.printf("%-5d %-30s %-20s %-15s %-25s %-10d%n", 
                                  idLivro, titulo, autor, isbn, categoria, quantidade);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    private String obterNomeLivro(String isbn) throws SQLException {
        String sql = "SELECT titulo FROM livros WHERE isbn = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(isbn));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("titulo");
            }
        }
        return "Desconhecido";
    }

    private String obterNomeUsuario(String idUsuario) throws SQLException {
        String sql = "SELECT nome FROM usuarios WHERE id_usuarios = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(idUsuario));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("nome");
            }
        }
        return "Desconhecido";
    }

    private int getAlunoIdFromRa(PreparedStatement stmt, String ra) throws SQLException {
        stmt.setString(1, ra);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuarios");
        }
        return -1; // Retornar -1 se o aluno não for encontrado
    }

    public List<String> verificarStatusEmprestimo(String ra) {
        String getAlunoIdSql = "SELECT id_usuarios FROM usuarios WHERE ra = ?";
        String query = "SELECT livros.titulo, emprestimos.data_emprestimo, emprestimos.data_devolucao " +
                       "FROM emprestimos " +
                       "JOIN livros ON emprestimos.isbn = livros.isbn " +
                       "WHERE emprestimos.aluno_id = ? AND emprestimos.status = true";

        List<String> livrosEmprestados = new ArrayList<>();

        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement getAlunoIdStmt = conn.prepareStatement(getAlunoIdSql);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Obtém o aluno_id a partir do RA
            int alunoId = getAlunoIdFromRa(getAlunoIdStmt, ra);
            if (alunoId == -1) {
                System.out.println("Erro: Aluno não encontrado.");
                return livrosEmprestados;
            }

            // Define o parâmetro para a consulta de empréstimos
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();

            // Processa os resultados da consulta
            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String dataEmprestimo = rs.getString("data_emprestimo");
                String dataDevolucao = rs.getString("data_devolucao");
                livrosEmprestados.add(String.format("Título: %s, Data de Empréstimo: %s, Data de Devolução: %s", titulo, dataEmprestimo, dataDevolucao));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao verificar status de empréstimo: " + e.getMessage());
        }

        return livrosEmprestados;
    }

    public boolean alterarPrazoDevolucao(String[] dados) {
        String query = "UPDATE emprestimos SET data_devolucao = ? WHERE id = ?";
        String getDataDevolucaoQuery = "SELECT data_devolucao FROM emprestimos WHERE id = ?";
    
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement getDataStmt = conn.prepareStatement(getDataDevolucaoQuery);
             PreparedStatement updateStmt = conn.prepareStatement(query)) {
    
            // Obter a data de devolução atual
            getDataStmt.setInt(1, Integer.parseInt(dados[0]));
            ResultSet rs = getDataStmt.executeQuery();
    
            if (rs.next()) {
                LocalDate dataDevolucaoAtual = rs.getDate("data_devolucao").toLocalDate();
                System.out.println("Data de devolução atual: " + dataDevolucaoAtual);
    
                // Calcular a nova data de devolução
                LocalDate novaDataDevolucao = dataDevolucaoAtual.plusDays(Integer.parseInt(dados[1]));
    
                // Atualizar a data de devolução
                updateStmt.setString(1, novaDataDevolucao.toString());
                updateStmt.setInt(2, Integer.parseInt(dados[0]));
    
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Prazo de devolução atualizado com sucesso para: " + novaDataDevolucao);
                    return true;
                } else {
                    System.out.println("Erro: Empréstimo não encontrado.");
                    return false;
                }
            } else {
                System.out.println("Erro: Empréstimo não encontrado.");
                return false;
            }
    
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar prazo de devolução: " + e.getMessage());
            return false;
        }
    }


}


