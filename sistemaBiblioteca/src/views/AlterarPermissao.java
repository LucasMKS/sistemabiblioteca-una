package views;
import java.util.Scanner;
import utils.ClearConsole;

public class AlterarPermissao {

    private Scanner scanner;

    public String[] alterarPermissao() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosalteracao = new String[2];

            System.out.println("Informe o login usuário:");
            dadosalteracao[0] = scanner.nextLine();

            System.out.println("Informe a permissao que o usuário terá:");
            dadosalteracao[1] = scanner.nextLine();
            
        return dadosalteracao;

    }
}
