package views;
import java.util.Scanner;
import utils.ClearConsole;

public class AlterarPrazoDevolucao {

    private Scanner scanner;

    public String[] alterarPrazo() {

        ClearConsole.clear();
        this.scanner = new Scanner(System.in);
        String[] dadosalteracao = new String[2];

            System.out.print("Digite o ID do empréstimo: ");
            dadosalteracao[0] = scanner.nextLine();

            System.out.print("Digite o número de dias para adicionar (ou subtrair, use um número negativo): ");
            dadosalteracao[1] = scanner.nextLine();
            
        return dadosalteracao;

    }
}
