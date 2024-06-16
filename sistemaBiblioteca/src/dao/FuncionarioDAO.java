package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import utils.ConnectionSQL;

public class FuncionarioDAO {
    /**
     * Adiciona um novo funcionário ao banco de dados.
     * 
     * @param dadosUsuarios Um array contendo os dados do novo funcionário: [login,
     *                      senha, tipo, nome].
     * @return true se o funcionário foi adicionado com sucesso, false caso
     *         contrário.
     */
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

    /**
     * Atualiza o tipo de um funcionário no banco de dados.
     * 
     * @param dadosUsuario Um array contendo os dados do funcionário a ser
     *                     atualizado: [login, tipo].
     * @return true se a atualização for bem-sucedida, false caso contrário.
     * @throws IllegalArgumentException se os dados do usuário forem inválidos.
     */
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

    /**
     * Remove um funcionário do banco de dados com base na matrícula fornecida.
     * 
     * @param matricula A matrícula do funcionário a ser removido.
     * @param scanner   O objeto Scanner para entrada de dados.
     * @return true se o funcionário foi removido com sucesso, false caso contrário.
     */
    public boolean removerFuncionario(String matricula, Scanner scanner) {
        String getAlunoIdSql = "SELECT id_usuarios FROM usuarios WHERE matricula = ?";
        String update_sql = "UPDATE usuarios SET tipo = 'null' WHERE matricula = ?";
        String delete_sql = "DELETE FROM emprestimos WHERE aluno_id = ?";
        try (Connection conn = ConnectionSQL.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(update_sql);
                PreparedStatement getAlunoIdStmt = conn.prepareStatement(getAlunoIdSql);
                PreparedStatement pstmtdel = conn.prepareStatement(delete_sql)) {

            System.out.println(
                    "Realmente deseja remover o funcionario de matricula" + matricula + "? (1 - Sim / 0 - Não)");
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

    /**
     * Obtém o ID de um aluno a partir de sua matrícula.
     *
     * @param stmt O PreparedStatement para executar a consulta.
     * @param ra   A matrícula do aluno.
     * @return O ID do aluno se encontrado, -1 caso contrário.
     * @throws SQLException se ocorrer um erro ao acessar o banco de dados.
     */
    private int getAlunoIdFromMatricula(PreparedStatement stmt, String ra) throws SQLException {
        stmt.setString(1, ra);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_usuarios");
        }
        return -1; // Retornar -1 se o aluno não for encontrado
    }

    /**
     * Pausa a execução do programa por um determinado número de segundos.
     *
     * @param seconds O número de segundos para pausar a execução.
     */
    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
