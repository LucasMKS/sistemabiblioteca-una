package controllers;

import utils.ConnectionSQL;
import views.menus.MenuFuncionario;
import java.sql.*;

public class AdministradorController extends FuncionarioController {

    public static int processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                break;
            case 2:
            //TO DO
                break;
            case 3:
            //TO DO
                break;
            case 4:
            MenuFuncionario.processarOpcao(opcao);

            break;
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }

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

    // Adicione métodos para deletar e listar funcionários conforme necessário
}
