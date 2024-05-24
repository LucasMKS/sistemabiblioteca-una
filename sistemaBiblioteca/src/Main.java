import java.io.IOException;
import java.util.Scanner;
import controllers.AutenticacaoController;
import controllers.FuncionarioController;
import models.Credenciais;
import utils.MenuFuncionarios;
import views.MenuAluno;
import views.MenuAdministrador;
import views.MenuFuncionario;
import utils.ClearConsole;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static FuncionarioController funcionarioController = new FuncionarioController();
    private static MenuFuncionarios menuOperations = new MenuFuncionarios(funcionarioController, scanner);

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

            MenuAdministrador.mostrarOpcoes();
            System.err.println("10.Sair");

        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }

    private static void gerenciarFuncionarios() {
        System.out.println("Gerenciamento de Funcionários (1 para adicionar, 2 para atualizar):");
        int acao = scanner.nextInt();
        scanner.nextLine();  // Consumir linha

        if (acao == 1) {
            
            // Implementar adição de funcionário
        } else if (acao == 2) {
            System.out.println("Atualizando funcionário existente...");
            // Implementar atualização de funcionário
        } else {
            System.out.println("Ação inválida.");
        }
    }

    // Outros métodos...
}
