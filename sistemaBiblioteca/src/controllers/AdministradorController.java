package controllers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.FuncionarioDAO;
import models.Credenciais;
import utils.ConnectionSQL;
import views.CadastrarUsuario;
import views.menus.MenuPrincipal;

public class AdministradorController extends FuncionarioController {
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

    public void processarOpcao(int opcao, Scanner scanner, Credenciais credenciaisValidas) {
        AlunoController alunoController = new AlunoController();
        FuncionarioController funcionarioController = new FuncionarioController();
        switch (opcao) {
            case 1:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 2:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 3:
                alunoController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 4:
                funcionarioController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 5:
                funcionarioController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 6:
                funcionarioController.processarOpcao(opcao, credenciaisValidas);
                break;
            case 7:
                System.out.println("Alterar Prazo devolução em desenvolvimento");
                break;
            case 8:
                System.out.println("Alterar permissões de usuarios em desenvolvimento");
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
    }

    public boolean adicionarFuncionario(String nome, String login, String senha, String tipo) {
        return funcionarioDAO.adicionarFuncionario(nome, login, senha, tipo);
    }

    public boolean insertUsuario(String[] usuarioDadoStrings) {
        String checkLoginSql = "SELECT COUNT(*) FROM usuarios WHERE login = ?";
        String insertSql = "INSERT INTO usuarios (login, senha, tipo, nome, ra) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionSQL.getConnection();
            PreparedStatement checkLoginStmt = conn.prepareStatement(checkLoginSql);
            PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

            // Verificar se o login já existe
            checkLoginStmt.setString(1, usuarioDadoStrings[0]);
            ResultSet rs = checkLoginStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("\nErro: Login já existente... Voltando ao menu principal.");
                pause(5);
                return false;
            }

            // Inserir novo usuário
            insertStmt.setString(1, usuarioDadoStrings[0]);
            insertStmt.setString(2, usuarioDadoStrings[1]);
            insertStmt.setString(3, usuarioDadoStrings[2]);
            insertStmt.setString(4, usuarioDadoStrings[3]);
            if (usuarioDadoStrings[2].equals("aluno")) {
                insertStmt.setString(5, usuarioDadoStrings[4]);
            } else {
                insertStmt.setString(5, null);
            }
            insertStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean atualizarFuncionario(int id, String nome, String login, String senha, String tipo) {
        return funcionarioDAO.atualizarFuncionario(id, nome, login, senha, tipo);
    }

    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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