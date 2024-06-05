package controllers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import dao.FuncionarioDAO;
import models.Credenciais;
import utils.ConnectionSQL;
import views.CadastrarUsuario;
import views.menus.MenuPrincipal;

public class AdministradorController extends FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void processarOpcao(int opcao, Scanner scanner, Credenciais usuarioAutencado) {
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
                new FuncionarioController().processarOpcao(opcao, usuarioAutencado);
                break;
            case 9:
                boolean cadastrar = false;
                String[] dadosUsuario = new CadastrarUsuario().cadastrarUsuario();
                cadastrar = insertUsuario(dadosUsuario);
                if (cadastrar) {
                    System.out.println("Usuário Cadastrado com sucesso!");
                }
                break;
            default:
                System.out.println("Opção inválida");
        }
        new MenuPrincipal().menu(scanner, usuarioAutencado);
    }

    public boolean adicionarFuncionario(String nome, String login, String senha, String tipo) {
        return funcionarioDAO.adicionarFuncionario(nome, login, senha, tipo);
    }

    public boolean insertUsuario(String[] usuarioDadoStrings) {
        String sql = "INSERT INTO usuarios (login, senha, tipo, nome, RA) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionSQL.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // login, senha, tipo, nome, RA
            pstmt.setString(1, usuarioDadoStrings[0]);
            pstmt.setString(2, usuarioDadoStrings[1]);
            pstmt.setString(3, usuarioDadoStrings[2]);
            pstmt.setString(4, usuarioDadoStrings[3]);
            if (usuarioDadoStrings[2].equals("aluno")) {
                pstmt.setString(5, usuarioDadoStrings[4]);
            } else {
                pstmt.setString(5, null);
            }
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizarFuncionario(int id, String nome, String login, String senha, String tipo) {
        return funcionarioDAO.atualizarFuncionario(id, nome, login, senha, tipo);
    }
}

// Adicione métodos para deletar e listar funcionários conforme necessário

/*
 * } else if (dadosUsuario[2].equals("fun")) {
 * cadastrar = insertUsuario(dadosUsuario);
 * insertUsuario(dadosUsuario);
 * } else if (dadosUsuario[2].equals("admin")) {
 * cadastrar = insertUsuario(dadosUsuario);
 * insertUsuario(dadosUsuario);
 * }
 */
// UsuarioController novoUsuario = new UsuarioController();


                // login, senha, tipo, nome, RA
                // if (dadosUsuario[2].equals("aluno")) {