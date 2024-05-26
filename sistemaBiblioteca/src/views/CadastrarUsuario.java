package views;
import java.util.Scanner;
import utils.ClearConsole;

public class CadastrarUsuario {

    private Scanner scanner;

    public String[] cadastrarUsuario() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosUsuario = new String[5];

        System.out.println("Informe o login usuário:");
        dadosUsuario[0] = scanner.nextLine();
        System.out.println("Informe a senha do usuário:");
        dadosUsuario[1] = scanner.nextLine();
        System.out.println("Informe o tipo do usuário:");
        dadosUsuario[2] = scanner.nextLine();
        System.out.println("Informe o nome do usuário:");
        dadosUsuario[3] = scanner.nextLine();
        System.out.println("Informe o RA do usuário:");
        dadosUsuario[4] = scanner.nextLine();

        return dadosUsuario;

    }
}
