package views;

import java.util.Scanner;

public class DevolverLivro {

    private Scanner scanner;

    /**
     * Método para solicitar o RA do usuário e o ISBN do livro a ser devolvido.
     * 
     * @return Um array de Strings contendo o RA do usuário e o ISBN do livro.
     */
    public String[] devolverLivro() {

        String[] dados = new String[2];

        this.scanner = new Scanner(System.in);
        System.out.println("Informe o RA do usuário:");
        dados[0] = scanner.nextLine();
        System.out.println("Informe o ISBN do livro:");
        dados[1] = scanner.nextLine();

        return dados;

    }

}
