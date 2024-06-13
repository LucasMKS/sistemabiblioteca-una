package views.menus;

import java.util.Scanner;

import controllers.AdministradorController;
import controllers.AlunoController;
import controllers.FuncionarioController;
import models.Credenciais;

public class MenuPrincipal {
        public void menu(Scanner scanner, Credenciais usuarioAutenticado) {

            int opcao;
            Boolean contMenu = true;
            while (contMenu == true) {
                switch (usuarioAutenticado.getTipo()) {
                    case "aluno":
                        new MenuAluno().mostrarOpcoes();
                        System.out.println("Digite sua opçao:");
                        opcao = scanner.nextInt();
                        limparScanner(scanner);
                        new AlunoController().processarOpcao(opcao, usuarioAutenticado);
                        break;
                    case "funcionario":
                        new MenuFuncionario().mostrarOpcoes();
                        System.out.println("Digite sua opçao:");
                        opcao = scanner.nextInt();
                        limparScanner(scanner);
                        new FuncionarioController().processarOpcao(opcao, usuarioAutenticado);
                        break;
                    case "administrador":
                        new MenuAdministrador().mostrarOpcoes();
                        System.out.println("Digite sua opçao:");
                        opcao = scanner.nextInt();
                        limparScanner(scanner);
                        new AdministradorController().processarOpcao(opcao, scanner, usuarioAutenticado);  
                        break;  
                    default:
                        System.out.println("Voce nao tem permissao para utilizar o sistema!");
                        System.exit(0);
                        break;
                }     
                System.out.println("\n\nDeseja continuar no menu? (1 - Sim / 0 - Não)");
                opcao = scanner.nextInt();
                limparScanner(scanner); 
                if (opcao == 0) {
                    contMenu = false;
                }      
            }
    }
    
    private void limparScanner(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
}