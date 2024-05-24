import java.io.IOException;
import java.util.Scanner;
import controllers.AutenticacaoController;
import controllers.FuncionarioController;
import controllers.MenuController;
import models.Credenciais;
import utils.MenuFuncionarios;

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

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
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

            clearConsole();
            System.out.println("Login realizado com sucesso! Bem vindo " + usuarioAutenticado.getNome());

            String tipoUser = usuarioAutenticado.getTipo();

            MenuController.mostrarMenu(tipoUser);

        } else {
            System.out.println("Login ou senha inválidos.");
        }
    }

    /*private static void mostrarMenuFuncionario() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenu Funcionário:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Registrar Empréstimo");
            System.out.println("3. Registrar Devolução");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha

            switch (opcao) {
	            case 1:
	                menuOperations.cadastrarLivro();
	                break;
	            case 2:
	                menuOperations.registrarEmprestimo();
	                break;
	            case 3:
	                menuOperations.registrarDevolucao();
	                break;
	            case 4:
                    System.out.println("Saindo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void mostrarMenuAdministrador() {
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenu Administrador:");
            System.out.println("1. Gerenciar Funcionários (Adicionar/Atualizar)");
            System.out.println("2. Cadastrar Livro");
            System.out.println("3. Registrar Empréstimo");
            System.out.println("4. Registrar Devolução");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir linha

            switch (opcao) {
                case 1:
                    gerenciarFuncionarios();
                    break;
                case 2:
                    // cadastrarLivro();
                    break;
                case 3:
                    // registrarEmprestimo();
                    break;
                case 4:
                    // registrarDevolucao();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }*/

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
