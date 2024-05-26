import java.util.Scanner;
import controllers.AutenticacaoController;
import controllers.FuncionarioController;
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

            switch(tipoUser){

                case "administrador":
                    MenuAdministrador.mostrarOpcoes();
                    break;
                
                case "aluno":
                    MenuAluno.mostrarOpcoes();
                    System.out.println("Digite sua opçao:");
                break;
                
                case "funcionario":
                    MenuFuncionario.mostrarOpcoes();
                    System.out.println("Digite sua opçao:");
                    int opcao = scanner.nextInt();
                    FuncionarioController.processarOpcao(opcao);
                    break;

                default:

                System.out.println("Voce nao tem permissao para utilizar o sistema!");
                break;

            }

            System.out.println("10.Sair");

        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }
}
