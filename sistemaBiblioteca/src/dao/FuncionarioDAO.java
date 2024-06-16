package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utils.ConnectionSQL;

public class FuncionarioDAO {

    public boolean adicionarFuncionario(String[] dadosUsuarios) {
        String checkLoginSql = "SELECT COUNT(*) FROM usuarios WHERE login = ?";
        String insertSql = "INSERT INTO usuarios (login, senha, tipo, nome, matricula) VALUES (?, ?, ?, ?, ?)";
        String lastMatriculaSql = "SELECT MAX(matricula) FROM usuarios";
    
        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement checkLoginStmt = conn.prepareStatement(checkLoginSql);
             PreparedStatement insertStmt = conn.prepareStatement(insertSql);
             Statement lastMatriculaStmt = conn.createStatement()) {
            // Verificar se o login já existe
            checkLoginStmt.setString(1, dadosUsuarios[0]);
            ResultSet rs = checkLoginStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("\nErro: Login já existente... Voltando ao menu principal.");
                pause(5);
                return false;
            }
    
            // Obter o último RA cadastrado
            int lastMatricula = 15129; // Valor inicial para o caso de não haver RA cadastrado
            ResultSet lastMatriculaRs = lastMatriculaStmt.executeQuery(lastMatriculaSql);
            if (lastMatriculaRs.next() && lastMatriculaRs.getInt(1) > 0) {
                lastMatricula = lastMatriculaRs.getInt(1);
            }
    
            // Inserir novo usuário
            insertStmt.setString(1, dadosUsuarios[0]);
            insertStmt.setString(2, dadosUsuarios[1]);
            insertStmt.setString(3, dadosUsuarios[2]);
            insertStmt.setString(4, dadosUsuarios[3]);
            insertStmt.setInt(5, lastMatricula + 1);
            insertStmt.executeUpdate();
            System.out.println("\nUsuário cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public boolean atualizarFuncionario(String dadosUsuario[]) {
        if (dadosUsuario == null || dadosUsuario.length < 2 || dadosUsuario[0] == null || dadosUsuario[1] == null) {
            throw new IllegalArgumentException("Dados de usuário inválidos");
        }

        String sql = "UPDATE usuarios SET tipo = ? WHERE login = ?";

        try (Connection conn = ConnectionSQL.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, dadosUsuario[1]);
            pstmt.setString(2, dadosUsuario[0]);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removerFuncionario(String matricula, Scanner scanner){
        String getAlunoIdSql = "SELECT id_usuarios FROM usuarios WHERE matricula = ?";
        String update_sql = "UPDATE usuarios SET tipo = 'null' WHERE matricula = ?";
        String delete_sql = "DELETE FROM emprestimos WHERE aluno_id = ?";
        try (Connection conn = ConnectionSQL.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(update_sql);
            PreparedStatement getAlunoIdStmt = conn.prepareStatement(getAlunoIdSql);
            PreparedStatement pstmtdel = conn.prepareStatement(delete_sql)) {

            System.out.println("Realmente deseja remover o funcionario de matricula" + matricula + "? (1 - Sim / 0 - Não)");
            int opcao = scanner.nextInt();
            if (opcao == 0) {
                return false;
            }

            int alunoId = getAlunoIdFromMatricula(getAlunoIdStmt, matricula);
            if (alunoId == -1) {
                System.out.println("Erro: Aluno não encontrado.");
                return false;
            }

            System.out.println("Removendo funcionario...");
            pstmt.setString(1, matricula);
            pstmt.executeUpdate();

            pstmtdel.setInt(1, alunoId);
            pstmtdel.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private int getAlunoIdFromMatricula(PreparedStatement stmt, String ra) throws SQLException {
        stmt.setString(1, ra);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuarios");
        }
        return -1; // Retornar -1 se o aluno não for encontrado
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
