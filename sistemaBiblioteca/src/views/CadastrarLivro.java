package views;

import java.util.Scanner;

import utils.ClearConsole;

public class CadastrarLivro {

    private Scanner scanner;

    public String[] cadastrarLivro() {

        ClearConsole.clear();

        this.scanner = new Scanner(System.in);

        String[] dadosLivro = new String[5];

        System.out.println("Informe o título do livro:");
        dadosLivro[0] = scanner.nextLine();
        System.out.println("Informe o autor do livro:");
        dadosLivro[1] = scanner.nextLine();
        System.out.println("Informe o ISBN:");
        dadosLivro[2] = scanner.nextLine();
        System.out.println("Informe a categoria:");
        dadosLivro[3] = scanner.nextLine();
        System.out.println("Informe a quantidade de livros a ser adicionada:");
        dadosLivro[4] = scanner.nextLine();

        return dadosLivro;

    }
}
