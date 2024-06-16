
import java.util.Scanner;
import controllers.AutenticacaoController;
//Importando o controlador de Autenticação
import models.Credenciais;
//Importação dos modelos de credenciais
import views.menus.*;
//Importação das classes do pacotes de menus
import utils.ClearConsole;

public class Main {
    /**
     * Método principal da aplicação.
     * 
     * @param args Os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        try {
            new Main().realizarLogin(scanner);
            // Entrada de Login do usuário
        } catch (Exception e) {
            System.out.println("Erro no sistema: " + e.getMessage());
            // Mensagem de erro, se houver
        } finally {
            scanner.close();
            // Leitura do Scanner fechada
        }
    }

    /**
     * Método para realizar o login do usuário.
     * 
     * @param scanner O objeto scanner para entrada.
     */
    private void realizarLogin(Scanner scanner) {

        System.out.println("Digite seu login:");
        String login = scanner.nextLine();
        System.out.println("Digite sua senha:");
        String senha = scanner.nextLine();
        // Solicitação do login e senha do usuário

        AutenticacaoController autenticacaoController = new AutenticacaoController();
        // Cria uma instância do controlador de autenticação
        Credenciais credenciaisValidas = new Credenciais(login, senha);
        // Cria uma instância de Credenciais com os valores fornecidos
        Credenciais usuarioAutenticado = autenticacaoController.autenticarUsuario(credenciaisValidas);
        // E então autentica o usuário

        if (usuarioAutenticado != null) {
            // Se a autenticação for bem-sucedida
            ClearConsole.clear();
            System.out.println("Login realizado com sucesso! Bem vindo " + usuarioAutenticado.getNome());
            // O console é limpado e exibe a mensagem de boas vindas
            new MenuPrincipal().menu(scanner, usuarioAutenticado);
            // Exibição do menu principal
        } else {
            System.out.println("Login ou senha inválidos.");
            // Mensagem exibida para erro de login ou senha inválidos
        }

    }

}
