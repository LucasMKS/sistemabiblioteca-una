package controllers;

import utils.ConnectionSQL;
import views.CadastrarUsuario;
import views.menus.MenuFuncionario;
import java.sql.*;

public class AdministradorController extends FuncionarioController {

    public int processarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                break;
            case 2:
                // TO DO
                break;
            case 3:
                // TO DO
                break;
            case 4:
                MenuFuncionario.processarOpcao(opcao);

                break;
            // ----------------------------------------------------------------------
            // CADASTRAR USÚARIO EM CONSTRUÇÃO --- INICIO
            // ----------------------------------------------------------------------
            case 9:

                boolean cadastrar = false;
                String[] dadosUsuario = new CadastrarUsuario().cadastrarUsuario();
                // login, senha, tipo, nome, RA
                if (dadosUsuario[2].equals("aluno")) {
                    cadastrar = insertUsuario(dadosUsuario);
                /*     
                } else if (dadosUsuario[2].equals("fun")) {
                    cadastrar = insertUsuario(dadosUsuario);
                    insertUsuario(dadosUsuario);
                } else if (dadosUsuario[2].equals("admin")) {
                    cadastrar = insertUsuario(dadosUsuario);
                    insertUsuario(dadosUsuario);
                }*/
                // UsuarioController novoUsuario = new UsuarioController();

                if (cadastrar) {
                    System.out.println("Usuário Cadastrado com sucesso!");
                }

                break;

            // ----------------------------------------------------------------------
            // CADASTRAR USÚARIO EM CONSTRUÇÃO --- FIM
            // ----------------------------------------------------------------------
            default:
                System.out.println("Opção inválida");
        }

        return -1;
    }
/* 
    public boolean insertUsuario(String nome, String login, String senha, String tipo, String[] livroDadoStrings) {
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
*/
    public boolean insertUsuario(String[] usuarioDadoStrings) {
        String sql = "INSERT INTO funcionarios (login, senha, tipo, nome, RA) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionSQL.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    // login, senha, tipo, nome, RA
            pstmt.setString(1, usuarioDadoStrings[0]);
            pstmt.setString(2, usuarioDadoStrings[1]);
            pstmt.setString(3, usuarioDadoStrings[2]);
            pstmt.setString(4, usuarioDadoStrings[3]);
            pstmt.setString(4, usuarioDadoStrings[4]);
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
