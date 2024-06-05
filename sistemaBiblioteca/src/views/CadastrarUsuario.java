package views;
import java.util.Scanner;
import utils.ClearConsole;

public class CadastrarUsuario {

    private Scanner scanner;

    public String[] cadastrarUsuario() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosUsuario = new String[5];
        boolean dadosValidos = false;

        while (!dadosValidos) {
            System.out.println("Informe o login usuário:");
            dadosUsuario[0] = scanner.nextLine();
            System.out.println("Informe a senha do usuário:");
            dadosUsuario[1] = scanner.nextLine();
            System.out.println("Informe o tipo do usuário:");
            dadosUsuario[2] = scanner.nextLine();
            System.out.println("Informe o nome do usuário:");
            dadosUsuario[3] = scanner.nextLine();
            System.out.println("Informe o RA do usuário (deixe em branco se não for aluno):");
            dadosUsuario[4] = scanner.nextLine();

            if (dadosUsuario[2].equals("aluno") || dadosUsuario[2].equals("funcionario") || dadosUsuario[2].equals("administrador")) {
                dadosValidos = true;
            } else {
                System.out.println("Tipo de usuário inválido. Tente novamente.");
            }
        }

        return dadosUsuario;

    }
}
