package views.menus;

import java.util.Scanner;

import controllers.AdministradorController;
import controllers.AlunoController;
import controllers.FuncionarioController;
import models.Credenciais;

public class MenuPrincipal {
        public void menu(Scanner scanner, Credenciais usuarioAutencado) {
            int opcao;
        switch (usuarioAutencado.getTipo()) {
            case "administrador":
                new MenuAdministrador().mostrarOpcoes();
                System.out.println("Digite sua opçao:");
                opcao = scanner.nextInt();
                new AdministradorController().processarOpcao(opcao, scanner, usuarioAutencado);
                break;

            case "aluno":
                new MenuAluno().mostrarOpcoes();
                System.out.println("Digite sua opçao:");
                opcao = scanner.nextInt();
                AlunoController.processarOpcao(opcao, usuarioAutencado);
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

    }
    
}
