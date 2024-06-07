package controllers;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import dao.FuncionarioDAO;
import dao.LivroDAO;
import models.Credenciais;
import utils.ConnectionSQL;
import views.AlterarPrazoDevolucao;
import views.CadastrarUsuario;
import views.EmprestarLivro;

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
                AlterarPrazoDevolucao alterarPrazo = new AlterarPrazoDevolucao();
                String[] dadosAlterarPrazo = alterarPrazo.alterarPrazo();
                LivroController adminController = new LivroController();
                adminController.alterarPrazoDevolucao(dadosAlterarPrazo);
                break;
            case 8:
                System.out.println("Alterar permissões de usuarios em desenvolvimento");
                break;
            case 9:
                CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
                String[] cadUsuario = cadastrarUsuario.cadastrarUsuario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.adicionarFuncionario(cadUsuario);
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}