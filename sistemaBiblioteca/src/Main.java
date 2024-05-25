import java.util.Scanner;

import controllers.AdministradorController;
import controllers.AutenticacaoController;
import models.Credenciais;
import views.menus.*;
import utils.ClearConsole;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            realizarLogin();
        } catch (Exception e) {
            System.out.println("Erro no sistema: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static void realizarLogin() {
        System.out.println("Digite seu login:");
        String login = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();

        AutenticacaoController autenticacaoController = new AutenticacaoController();
        Credenciais credenciaisValidas = new Credenciais(login, senha);
        Credenciais usuarioAutenticado = autenticacaoController.autenticarUsuario(credenciaisValidas);
        

        if (usuarioAutenticado != null) {

            ClearConsole.clear();
            System.out.println("Login realizado com sucesso! Bem vindo " + usuarioAutenticado.getNome());

            String tipoUser = usuarioAutenticado.getTipo();

            int opcao = 0;
            switch (tipoUser) {
                case "administrador":
                    MenuAdministrador.mostrarOpcoes();
                    opcao = scanner.nextInt();
                    AdministradorController.processarOpcao(opcao);
                    System.out.println("\nDigite sua opção:");
                    break;
                case "funcionario":
                    MenuFuncionario.mostrarOpcoes();
                    opcao = scanner.nextInt();
                    MenuFuncionario.processarOpcao(opcao);
                    System.out.println("\nDigite sua opção:");
                    break;
                case "aluno":
                    MenuAluno.mostrarOpcoes();
                    opcao = scanner.nextInt();
                    MenuAluno.processarOpcao(opcao);
                    System.out.println("\nDigite sua opção:");
                    break;
                default:
                    System.out.println("Voce nao tem permissao para utilizar o sistema!");
                    break;
            }

            System.err.println("10.Sair");

        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }
}
