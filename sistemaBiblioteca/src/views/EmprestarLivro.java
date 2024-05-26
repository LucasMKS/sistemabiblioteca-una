package views;

import java.util.Scanner;

public class EmprestarLivro {

    private Scanner scanner;

    public String[] emprestarLivro() {

        this.scanner = new Scanner(System.in);

        String[] dados = new String[2];

        System.out.println("Informe o registro do aluno:");
        dados[0] = scanner.nextLine();
        System.out.println("Informe o ISBN:");
        dados[1] = scanner.nextLine();

        return dados;

    }
}
