package views;

import java.util.Scanner;

public class AlterarPrazoDevolucao {

    public String[] alterarPrazo() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite o ID do empréstimo: ");
        String emprestimoId = scanner.nextLine();
        
        System.out.print("Digite o número de dias para adicionar (ou subtrair, use um número negativo): ");
        String dias = scanner.nextLine();
        
        scanner.close();
        return new String[]{emprestimoId, dias};
    }
}
