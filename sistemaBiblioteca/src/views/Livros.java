package views;

import models.Livro;
import java.util.Scanner;

public class Livros {

    private Scanner scanner;

    public void cadastrarLivro() {
        System.out.println("Informe o título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Informe o autor do livro:");
        String autor = scanner.nextLine();
        System.out.println("Informe o ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Informe a categoria:");
        String categoria = scanner.nextLine();

        Livro livro = new Livro(titulo, autor, isbn, categoria);
        /*if (funcionarioController.cadastrarLivro(livro)) {
            System.out.println("Livro cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar livro.");
        }*/
    }
}
