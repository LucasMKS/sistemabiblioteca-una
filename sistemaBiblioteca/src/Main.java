
import java.util.Scanner;

import controllers.AutenticacaoController;
import models.Credenciais;
import views.menus.*;
import utils.ClearConsole;

public class Main {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        try {
            new Main().realizarLogin(scanner);
        } catch (Exception e) {
            System.out.println("Erro no sistema: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private void realizarLogin(Scanner scanner) {

        while (true) {
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


                new MenuPrincipal().menu(scanner, usuarioAutenticado);

                /*switch (tipoUser) {
                    case "administrador":
                        menu(scanner);
                        break;

                    case "aluno":
                        new MenuAluno().mostrarOpcoes();
                        System.out.println("Digite sua opçao:");
                        opcao = scanner.nextInt();
                        AlunoController.processarOpcao(opcao, credenciaisValidas);
                        break;

                    case "funcionario":
                        new MenuFuncionario().mostrarOpcoes();
                        System.out.println("Digite sua opçao:");
                        opcao = scanner.nextInt();
                        new FuncionarioController().processarOpcao(opcao);
                        break;

                    default:

                        System.out.println("Voce nao tem permissao para utilizar o sistema!");
                        break;

                } */

                System.out.println("10.Sair");
                break;

            } else {
                System.out.println("Login ou senha inválidos.");
            }
        }
    }

   /* public void menu(Scanner scanner, String tipoUser, Credenciais credenciaisValidas) {
        switch (tipoUser) {
            case "administrador":
                new MenuAdministrador().mostrarOpcoes();
                System.out.println("Digite sua opçao:");
                int opcao = scanner.nextInt();
                new AdministradorController().processarOpcao(opcao);
                break;

            case "aluno":
                new MenuAluno().mostrarOpcoes();
                System.out.println("Digite sua opçao:");
                opcao = scanner.nextInt();
                AlunoController.processarOpcao(opcao, credenciaisValidas);
                break;

            case "funcionario":
                new MenuFuncionario().mostrarOpcoes();
                System.out.println("Digite sua opçao:");
                opcao = scanner.nextInt();
                new FuncionarioController().processarOpcao(opcao);
                break;

            default:

                System.out.println("Voce nao tem permissao para utilizar o sistema!");
                break;

        }

    } */
}
