
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

            } else {
                System.out.println("Login ou senha inválidos.");
            }
        
    }

}
