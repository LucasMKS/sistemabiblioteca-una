package views;
    
import java.util.Scanner;
import utils.ClearConsole;

public class RemoverPermissaoUsuario {

    private Scanner scanner;

    public String[] removerPermissao() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosUsuario = new String[2];
        boolean dadosValidos = false;

        while (!dadosValidos) {
            System.out.println("Informe o RA do usuário:");
            dadosUsuario[0] = scanner.nextLine();
        }

        return dadosUsuario;

    }
}
