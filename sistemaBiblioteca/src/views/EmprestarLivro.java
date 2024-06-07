package views;

import java.util.Scanner;

public class EmprestarLivro {

    private Scanner scanner;

    public String[] emprestarLivro() {

        this.scanner = new Scanner(System.in);

        String[] dados = new String[2];

        System.out.println("Informe o RA do usuário:");
        dados[0] = scanner.nextLine();
        System.out.println("Informe o ISBN do livro:");
        dados[1] = scanner.nextLine();

        return dados;

    }
}
