package views;

import java.util.Scanner;
import utils.ClearConsole;

public class CadastrarLivro {

    private Scanner scanner;

    /**
     * Método para cadastrar um livro.
     * Este método solicita ao usuário informações sobre o livro a ser cadastrado,
     * como título, autor, ISBN, categoria e quantidade.
     * 
     * @return Um array de strings com os dados do livro cadastrado.
     */
    public String[] cadastrarLivro() {

        ClearConsole.clear();

        this.scanner = new Scanner(System.in);

        String[] dadosLivro = new String[5];

        System.out.println("Informe o título do livro:");
        dadosLivro[0] = scanner.nextLine();
        System.out.println("Informe o(a) autor(a) do livro:");
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
