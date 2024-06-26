package controllers;

import java.util.Scanner;
import dao.FuncionarioDAO;
import models.Credenciais;
import views.AlterarPermissao;
import views.AlterarPrazoDevolucao;
import views.CadastrarUsuario;

public class AdministradorController extends FuncionarioController {
    /**
     * Processa a opção escolhida pelo administrador.
     * 
     * @param opcao              a opção escolhida
     * @param scanner            o scanner para entrada de dados
     * @param credenciaisValidas as credenciais válidas do administrador
     */
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
                String[] dados = alterarPrazo.alterarPrazo();
                LivroController adminController = new LivroController();
                adminController.alterarPrazoDevolucao(dados);
                break;
            case 8:
                System.out.println("Informe o RA do usuario: ");
                String ra = scanner.nextLine();
                FuncionarioDAO removerFuncionarioDAO = new FuncionarioDAO();
                removerFuncionarioDAO.removerFuncionario(ra, scanner);
                break;
            case 9:
                CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
                String[] cadUsuario = cadastrarUsuario.cadastrarUsuario();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
                funcionarioDAO.adicionarFuncionario(cadUsuario);
                break;
            case 10:
                AlterarPermissao alterarPermissao = new AlterarPermissao();
                String[] dadosUsuario = alterarPermissao.alterarPermissao();
                FuncionarioDAO alterarPermissaoDAO = new FuncionarioDAO();
                alterarPermissaoDAO.atualizarFuncionario(dadosUsuario);
                break;
            default:
                System.out.println("Opção inválida");
        }
    }
}